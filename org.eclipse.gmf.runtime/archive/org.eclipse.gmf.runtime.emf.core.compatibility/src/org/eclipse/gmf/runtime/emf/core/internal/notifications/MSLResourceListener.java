/******************************************************************************
 * Copyright (c) 2002, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core.internal.notifications;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.impl.InternalTransaction;

import org.eclipse.gmf.runtime.emf.core.EventTypes;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLUtil;

/**
 * Resource event listener.
 * 
 * @author rafikj
 */
public class MSLResourceListener {
	/**
	 * Special notification wrapper 
	 * @author vramaswa
	 *
	 */
	public class UnloadNotification
		extends NotificationWrapper {
		private EObject resourceRoot = null;

		/**
		 * Constructor
		 * @param notifier
		 * @param notification
		 */
		public UnloadNotification(EObject resourceRoot, Notification notification) {
			super(notification);
			
			this.resourceRoot = resourceRoot;

		}
		
		/**
		 * @return Returns the resourceRoot.
		 */
		public final EObject getResourceRoot() {
			return resourceRoot;
		}

	}
	
	private MSLEditingDomain domain = null;

	private Map loadedResources = new WeakHashMap();

	private Map unloadedResourcesRoot = new HashMap();
	
	/**
	 * Constructor.
	 */
	public MSLResourceListener(MSLEditingDomain domain) {
		super();
		this.domain = domain;
	}

	/**
	 * Handle resource event.
	 */
	public void handleEvent(Notification notification) {

		Resource notifier = (Resource) notification.getNotifier();

		int eventType = notification.getEventType();

		if (eventType == EventTypes.CREATE)
			return;
		
		Object newValue = notification.getNewValue();
		Object oldValue = notification.getOldValue();

		int featureID = notification.getFeatureID(Resource.class);

		if ((eventType == Notification.ADD)
				|| (eventType == Notification.ADD_MANY)) {
			if (featureID == Resource.RESOURCE__CONTENTS) {
				/* Only propagate resource content changes that are not part of the
				 * loading the resource */
				if ( !resourceFinishedLoading(notifier) ) {
					return;
				}
			}			
		} else if ((eventType == Notification.REMOVE)
				|| (eventType == Notification.REMOVE_MANY)) {
			if (featureID == Resource.RESOURCE__CONTENTS) {
				/* Only propagate resource content changes that are not part of the
				 * unloading the resource */
				if ( isResourceUnLoading(notifier) ) {
					if ( !unloadedResourcesRoot.containsKey(notifier)) {
						if (oldValue instanceof Collection) {
							Iterator j = ((Collection) oldValue).iterator();
							while (j.hasNext()) {
								Object value = j.next();
								if (value instanceof EObject) {
									unloadedResourcesRoot.put(notifier, value);
									break;
								}
							}
						} else if (oldValue instanceof EObject) {
							unloadedResourcesRoot.put(notifier, oldValue);
						}
					}

					return;
				}
			}
		} else if (eventType == Notification.SET) {

			if (featureID == Resource.RESOURCE__IS_LOADED) {

				if ((newValue instanceof Boolean)
					&& (oldValue instanceof Boolean)) {

					boolean newBooleanValue = notification.getNewBooleanValue();
					boolean oldBooleanValue = notification.getOldBooleanValue();

					if (newBooleanValue && !oldBooleanValue) {
						loadedResources.put(notifier, Boolean.TRUE);

						MSLUtil.postProcessResource(notifier);
					} else if (!newBooleanValue && oldBooleanValue
							&& !(notification instanceof UnloadNotification)) {

						loadedResources.remove(notifier);

						domain.getCommandStack().flush();

						EObject root = null;
						if ( unloadedResourcesRoot.containsKey(notifier)) {
							root = (EObject)unloadedResourcesRoot.get(notifier);
						}
						
						UnloadNotification unloadNotification = new UnloadNotification(root, notification);
						unloadedResourcesRoot.remove(notifier);
						
						// remove the notification that we got from the
						//    current transaction
						InternalTransaction tx = domain.getActiveTransaction();
						if (tx != null) {
							tx.getNotifications().remove(notification);
						}
						
						// and send this one in its place
						domain.sendNotification(unloadNotification);
						
						return;
					}
				}
			}
		}

		// forward event to broker.
		domain.getEventBroker().addEvent(notification);
	}

	/**
	 * Has resource finished loading?
	 */
	public boolean resourceFinishedLoading(Resource resource) {
		return loadedResources.containsKey(resource);
	}

	/**
	 * Is resource unloading?
	 */
	public boolean isResourceUnLoading(Resource resource) {
		return !resource.isLoaded() && loadedResources.containsKey(resource);
	}
	
	/**
	 * Mark resource as finished loading.
	 */
	public void markResourceFinishedLoading(Resource resource) {

		if (resource.isLoaded())
			loadedResources.put(resource, Boolean.TRUE);
		else
			loadedResources.remove(resource);
	}
}