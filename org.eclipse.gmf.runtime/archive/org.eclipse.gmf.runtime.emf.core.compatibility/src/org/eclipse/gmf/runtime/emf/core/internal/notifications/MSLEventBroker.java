/******************************************************************************
 * Copyright (c) 2002, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core.internal.notifications;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.emf.core.edit.MFilter;
import org.eclipse.gmf.runtime.emf.core.edit.MListener;
import org.eclipse.gmf.runtime.emf.core.edit.MRunOption;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MSemProcProvider;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;
import org.eclipse.gmf.runtime.emf.core.internal.l10n.MSLCoreMessages;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLDebugOptions;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLPlugin;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLUtil;
import org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupport;

/**
 * This the MSL event broker class. The MSL uses a single content adapter to
 * listen to EMF notifications. The MSL event broker then forwards these events
 * to the proper listeners.
 * 
 * @author rafikj
 */
public class MSLEventBroker extends ResourceSetListenerImpl {

	private final MSLEditingDomain domain;

	private final Map listeners = new WeakHashMap();

	private final Map semProcProviders = new WeakHashMap();

	private static final Map universalListeners = new WeakHashMap();

	private final Map undoRedoEvents = new WeakHashMap();

	private boolean sendEventsToMetaModel = true;

	/**
	 * Constructor.
	 */
	public MSLEventBroker(MSLEditingDomain domain) {
		super();
		this.domain = domain;
	}

	public NotificationFilter getFilter() {
		// some MListeners want to get "touch" events
		return NotificationFilter.ANY;
	}
	
	public boolean isPostcommitOnly() {
		// I am a post-commit listener (the classic MSL event timing)
		return true;
	}
	
	/**
	 * Adds a listener.
	 */
	public void addListener(MListener listener) {
		listeners.put(listener, Boolean.TRUE);
	}

	/**
	 * Removes a listener.
	 */
	public void removeListener(MListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Adds a SemProc Provider.
	 */
	public void addSemProcProvider(MSemProcProvider listener) {
		semProcProviders.put(listener, Boolean.TRUE);
	}

	/**
	 * Removes a SemProc Provider.
	 */
	public void removeSemProcProvider(MSemProcProvider listener) {
		semProcProviders.remove(listener);
	}

	/**
	 * Adds a listener.
	 */
	public static void addUniversalListener(MListener listener) {
		universalListeners.put(listener, Boolean.TRUE);
	}

	/**
	 * Removes a listener.
	 */
	public static void removeUniversalListener(MListener listener) {
		universalListeners.remove(listener);
	}

	/**
	 * Runs the runnable instance without sending events.
	 */
	public Object runSilent(MRunnable runnable) {

		return runWithOptions(runnable, MRunOption.SILENT);
	}

	/**
	 * Runs the runnable instance without semantic procedures.
	 */
	public Object runWithNoSemProcs(MRunnable runnable) {

		return runWithOptions(runnable, MRunOption.NO_SEM_PROCS);
	}

	/**
	 * Runs the runnable instance without validation.
	 */
	public Object runUnvalidated(MRunnable runnable) {

		return runWithOptions(runnable, MRunOption.UNVALIDATED);
	}

	/**
	 * Runs the runnable instance with options. This method could be used to
	 * combine the effects of runSilent, runUnchecked, runUnvalidated and
	 * runWithNoSemProcs.
	 * 
	 * @see MRunOption
	 */
	public Object runWithOptions(MRunnable runnable, int options) {

		Object result = null;

		Object previousNoNotifications = null;
		Object previousNoTriggers = null;
		if ((options & MRunOption.SILENT) != 0) {
			previousNoNotifications = domain.setDefaultTransactionOption(
				Transaction.OPTION_NO_NOTIFICATIONS,
				Boolean.TRUE);
			previousNoTriggers = domain.setDefaultTransactionOption(
				Transaction.OPTION_NO_TRIGGERS,
				Boolean.TRUE);
		}

		boolean previousSendEventsToMetamodel = sendEventsToMetaModel;
		if ((options & MRunOption.NO_SEM_PROCS) != 0) {
			sendEventsToMetaModel = false;
		}
		
		Object previousNoValidation = null;
		if ((options & MRunOption.UNVALIDATED) != 0) {
			previousNoValidation = domain.setDefaultTransactionOption(
				Transaction.OPTION_NO_VALIDATION,
				Boolean.TRUE);
		}
		
		boolean createdTransaction = false;
		
		try {
			if ((options & MRunOption.UNCHECKED) != 0) {
				domain.startUnchecked();
				createdTransaction = true;
			} else {
				Transaction active = domain.getActiveTransaction();
				
				if (active != null) {
					// must create a nested transaction to apply the options,
					//    because the client may not be creating one explicitly
					//    (having one already in hand)
					if (active.isReadOnly()) {
						domain.startRead();
					} else {
						domain.startWrite();
					}
					
					createdTransaction = true;
				}
			}
			
			result = runnable.run();
		} finally {
			
			if ((options & MRunOption.SILENT) != 0) {
				domain.setDefaultTransactionOption(
					Transaction.OPTION_NO_NOTIFICATIONS,
					previousNoNotifications);
				domain.setDefaultTransactionOption(
					Transaction.OPTION_NO_TRIGGERS,
					previousNoTriggers);
			}

			sendEventsToMetaModel = previousSendEventsToMetamodel;
			
			if ((options & MRunOption.UNVALIDATED) != 0) {
				domain.setDefaultTransactionOption(
					Transaction.OPTION_NO_VALIDATION,
					previousNoValidation);
			}
			
			if (createdTransaction) {
				domain.complete();
			}
		}

		return result;
	}
	
	public static Notification createNotification(final Object object, int eventType) {
		if (object instanceof InternalEObject) {
			return new ENotificationImpl((InternalEObject) object, eventType,
				null, (Object) null, (Object) null, -1);
		} else {
			return new NotificationImpl(eventType, 
				(Object) null, (Object) null, -1) {

				public Object getNotifier() {
					return object;
				}};
		}
	}

	/**
	 * Adds a new event to the pending list of events.
	 */
	public void addEvent(Notification event) {

		notifyMetaModel(event);

		if (domain.isUndoInProgress())
			undoRedoEvents.put(event, Boolean.TRUE);

		else if (domain.isRedoInProgress())
			undoRedoEvents.put(event, Boolean.FALSE);
	}

	/**
	 * Checks if event is undo event.
	 */
	public boolean isUndoEvent(Notification event) {

		Boolean isUndo = (Boolean) undoRedoEvents.get(event);

		return (isUndo != null) && (isUndo.booleanValue());
	}

	/**
	 * Checks if event is redo event.
	 */
	public boolean isRedoEvent(Notification event) {

		Boolean isUndo = (Boolean) undoRedoEvents.get(event);

		return (isUndo != null) && (!isUndo.booleanValue());
	}

	/**
	 * Notify the meta-model about the modification.
	 */
	public void notifyMetaModel(Notification event) {

		if (sendEventsToMetaModel) {

			Object notifier = event.getNotifier();

			if (((domain.isWriteInProgress()) || (domain
				.isUncheckedInProgress()))
				&& (!domain.isUndoInProgress())
				&& (!domain.isRedoInProgress())
				&& (!domain.isAbandonInProgress())
				&& (notifier instanceof EObject)) {

				IMetamodelSupport metaModel = MSLUtil.getMetaModel((EObject) notifier);

				if (metaModel != null)
					metaModel.handleEvent(event);
			}
		}
	}

	/**
	 * Propagates the notifications to registered MListeners.
	 */
	public void resourceSetChanged(ResourceSetChangeEvent event) {
		fireEvents(event.getNotifications());
	}
	
	/**
	 * Fires events.
	 */
	private void fireEvents(List events) {

		if (events.isEmpty())
			return;

		// in case of non-batched eventing (no transaction context), we may
		//    see a raw resource-unload event that will later be superseded
		//    by a custom unload event that contains the root object
		if (shouldSuppressUnbatchedResourceEvent(events)) {
			return;
		}
		
		List allListeners = getAllListeners();

		if (allListeners.isEmpty())
			return;

		if (events.size() == 1) {
			// If there is only one event then we can optimize the memory that we are
			//  consuming and save the time of extracting the single Notification from the list.
			Notification singleEvent = (Notification)events.get(0);
			
		for (Iterator iter = allListeners.iterator(); iter.hasNext();) {
				MListener listener = (MListener)iter.next();
				fireSingleEvent(listener, singleEvent, events);
			}
		} else {
			// We will create a reusable array list with the upper bound being
			//  the size of the events list we are firing. This means that we will
			//  only have two lists occupying memory that are the same size rather
			//  than allocating lists of different sizes for each listener.
			List listenerEventCache = new ArrayList(events.size());

			for (Iterator iter = allListeners.iterator(); iter.hasNext();) {
				MListener listener = (MListener)iter.next();
				fireEvents(listener, events, listenerEventCache);
		}
	}
	}
	
	private boolean shouldSuppressUnbatchedResourceEvent(List events) {
		boolean result = false;
		
		if (events.size() == 1) {
			Notification notification = (Notification) events.get(0);
			
			if (notification.getNotifier() instanceof Resource) {
				if (notification.getFeatureID(null) == Resource.RESOURCE__IS_LOADED) {
					result = !notification.getNewBooleanValue() && !(notification instanceof MSLResourceListener.UnloadNotification);
				}
			}
		}
		
		return result;
	}

	/**
	 * Gets a snapshot of all listeners. After this method is called
	 *  all of the listeners will not be garbage collected until after
	 *  the returned list is garbage collected.
	 */
	private List getAllListeners() {

		List allListeners = new ArrayList(listeners.size()
			+ universalListeners.size() + semProcProviders.size());

		allListeners.addAll(semProcProviders.keySet());
		allListeners.addAll(listeners.keySet());
		allListeners.addAll(universalListeners.keySet());

		return allListeners;
	}

	/**
	 * Sends events to a listener.
	 */
	private void fireEvents(MListener listener, List events, List eventsCache) {
		MFilter filter = listener.getFilter();

		if (filter == null)
			return;

		List eventsToSend;

		if (filter == MFilter.WILDCARD_FILTER) {
			eventsToSend = events;
		} else {
			eventsCache.clear();
			eventsToSend = eventsCache;

			Iterator j = events.iterator();

			while (j.hasNext()) {

				Notification event = (Notification) j.next();

				if (filter.matches(event))
					eventsToSend.add(event);
			}
		}

		if (eventsToSend.isEmpty())
			return;

		try {

			listener.onEvent(eventsToSend);

		} catch (Exception e) {

			// this is a bad listener so remove it so the next
			// listeners can get their events.
			// bugzilla110334: Do not remove listener as it will cause
			// apps to fail entirely in certain cases.
			//removeListener(listener);

			Log.error(MSLPlugin.getDefault(), 1,
				MSLCoreMessages.logError_badListener, e);

			Trace.catching(MSLPlugin.getDefault(),
				MSLDebugOptions.EXCEPTIONS_CATCHING, getClass(),
				"fireEvents", e); //$NON-NLS-1$
		}
	}
	
	/**
	 * Sends a single event to a listener.
	 */
	private void fireSingleEvent(MListener listener, Notification event, List eventsToSend) {
		MFilter filter = listener.getFilter();

		if (filter == null)
			return;

		if (!filter.matches(event)) {
			return;
		}
		
		try {
			listener.onEvent(eventsToSend);
		} catch (Exception e) {
			Log.error(MSLPlugin.getDefault(), 1,
				MSLCoreMessages.logError_badListener, e);

			Trace.catching(MSLPlugin.getDefault(),
				MSLDebugOptions.EXCEPTIONS_CATCHING, getClass(),
				"fireEvent", e); //$NON-NLS-1$
		}
	}
}