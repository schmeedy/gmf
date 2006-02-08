/******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.gmf.runtime.emf.core.internal.domain;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.ecore.change.impl.FeatureChangeImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
import org.eclipse.gmf.runtime.emf.core.EventTypes;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.internal.exceptions.MSLActionProtocolException;
import org.eclipse.gmf.runtime.emf.core.internal.notifications.MSLEventBroker;

/**
 * MSL customization of the change recorder, supporting multiple resource
 * sets and throwing an MSLActionProtocolException on violations of the
 * protocol (instead of IllegalStateException).
 *
 * @author Christian W. Damus (cdamus)
 */
class MSLChangeRecorder extends TransactionChangeRecorder {
	// fake structural features used for our custom changes because the
	//    change description requires features
	private static final EAttribute CREATED_ATTRIBUTE =
		EcoreFactory.eINSTANCE.createEAttribute();
	private static final EAttribute DESTROYED_ATTRIBUTE =
		EcoreFactory.eINSTANCE.createEAttribute();
	private static final EAttribute UNRESOLVED_ATTRIBUTE =
		EcoreFactory.eINSTANCE.createEAttribute();

	private final MSLEditingDomain msl;
	private final Collection resourceSets = new java.util.HashSet();
	
	MSLChangeRecorder(MSLEditingDomain msl, InternalTransactionalEditingDomain domain,
			ResourceSet rset) {
		super(domain, rset);
		
		this.msl = msl;
		
		addResourceSet(rset);
	}

	protected void appendNotification(Notification notification) {
		if (!(NotificationFilter.READ.matches(notification)
				|| (notification.getNotifier() instanceof Resource))) {
			assertWriting();
		}
		
		InternalTransaction tx = getEditingDomain().getActiveTransaction();
		
		if (tx != null) {
			tx.add(notification);
		} else {
			// can't batch it
			getEditingDomain().broadcastUnbatched(notification);
		}
	}
	
	protected void assertWriting() {
		try {
			super.assertWriting();
		} catch (IllegalStateException e) {
			// convert to MSLActionProtocol
			throw new MSLActionProtocolException(
					e.getLocalizedMessage());
		}
	}
	
	/**
	 * Starts recording changes in my editing domain.
	 */
	public void beginRecording() {
		beginRecording(resourceSets);
	}
	
	/**
	 * Adds a resource set for me to track.
	 * 
	 * @param rset a resource set
	 */
	final void addResourceSet(ResourceSet rset) {
		resourceSets.add(rset);
	}
	
	/**
	 * Removes a resource set that I was tracking.
	 * 
	 * @param rset a resource set
	 */
	final void removeResourceSet(ResourceSet rset) {
		resourceSets.remove(rset);
		
		super.dispose();
	}
	
	/**
	 * Unlike the basic EMF change recorder, we want to continue
	 * recording changes from detached objects, once they have entered
	 * our scope.
	 */
	protected void consolidateChanges() {
		for (Iterator iter = changeDescription.getObjectChanges().iterator();
				iter.hasNext();) {
			
			Map.Entry mapEntry = (Map.Entry) iter.next();
			EObject eObject = (EObject) mapEntry.getKey();
			for (Iterator featureChangeIter = ((List) mapEntry.getValue()).iterator();
					featureChangeIter.hasNext();) {
				
				finalizeChange((FeatureChange) featureChangeIter.next(),
					eObject);
			}
		}

		for (Iterator iter = changeDescription.getResourceChanges().iterator();
				iter.hasNext();) {
			
			ResourceChange resourceChange = (ResourceChange) iter.next();
			finalizeChange(resourceChange);
		}
	}
	
	/**
	 * Extends the inherited implementation to add undo/redo information for
	 * CREATE, DESTROY, and UNRESOLVE events.
	 */
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		
		switch (notification.getEventType()) {
		case EventTypes.CREATE:
			addCreateChange(notification);
			break;
		case EventTypes.DESTROY:
			addDestroyChange(notification);
			break;
		case EventTypes.UNRESOLVE:
			addUnresolveChange(notification);
			break;
		}

		Object notifier = notification.getNotifier();

		if (notifier instanceof EObject)
			msl.getObjectListener().handleEvent(notification);

		else if (notifier instanceof Resource)
			msl.getResouceListener().handleEvent(notification);

		else if (notifier instanceof ResourceSet)
			msl.getResouceSetListener().handleEvent(notification);
	}
	
	public void dispose() {
		super.dispose();
		
		resourceSets.clear();
	}
	
	/**
	 * Adds a change to me that records a CREATE event.
	 * 
	 * @param createEvent the CREATE event
	 */
	private void addCreateChange(Notification createEvent) {
		if (isRecording()) {
			getFeatureChanges(wrap(createEvent.getNotifier())).add(
					new CreateChange());
		}
	}
	
	/**
	 * Adds a change to me that records a DESTROY event.
	 * 
	 * @param createEvent the DESTROY event
	 */
	private void addDestroyChange(Notification createEvent) {
		if (isRecording()) {
			getFeatureChanges(wrap(createEvent.getNotifier())).add(
					new DestroyChange());
		}
	}
	
	/**
	 * Adds a change to me that records an UNRESOLVE event.
	 * 
	 * @param createEvent the UNRESOLVE event
	 */
	private void addUnresolveChange(Notification createEvent) {
		if (isRecording() && (createEvent.getNotifier() instanceof InternalEObject)) {
			getFeatureChanges(wrap(createEvent.getNotifier())).add(
					new UnresolveChange());
		}
	}
	
	/**
	 * Wraps the specified object so that it looks like an EObject.  This is
	 * only used to wrap MUndoIntervals and Resources, for which we fire
	 * CREATE events, but which are not EObjects.
	 * 
	 * @param object the object to wrap (which may already be an EObject)
	 * 
	 * @return the object, if an EObject, otherwise a wrapper
	 */
	EObject wrap(Object object) {
		EObject result;
		
		if (object instanceof EObject) {
			result = (EObject) object;
		} else {
			result = new EObjectWrapper(object);
		}
		
		return result;
	}
	
	/**
	 * Unwraps the specified Eobject to get the original.  This is
	 * only used to unwrap MUndoIntervals and Resources, for which we fire
	 * CREATE events, but which are not EObjects.
	 * 
	 * @param object the object to unwrap (which may be a natural EObject)
	 * 
	 * @return the eObject, if not a wrapper, otherwise the wrapped object
	 */
	Object unwrap(EObject eObject) {
		Object result = eObject;
		
		if (result instanceof EObjectWrapper) {
			result = ((EObjectWrapper) result).unwrap();
		}
		
		return result;
	}
	
	/**
	 * Wrapper for objects to make them look like EObjects.  This is
	 * only used to wrap MUndoIntervals and Resources, for which we fire
	 * CREATE events, but which are not EObjects.
	 *
	 * @author Christian W. Damus (cdamus)
	 */
	class EObjectWrapper extends EObjectImpl {
		Object wrapped;
		
		EObjectWrapper(Object wrapped) {
			this.wrapped = wrapped;
		}
		
		Object unwrap() {
			return wrapped;
		}
	}
	
	/**
	 * A partial implementation of a change in a fake feature designating a
	 * kind of change to an object as a whole, which the Ecore change model
	 * does not support.
	 *
	 * @author Christian W. Damus (cdamus)
	 */
	abstract class AbstractChange extends FeatureChangeImpl {
		protected boolean isReversed;
		
		/**
		 * Initializes me with the fake feature denoting the kind of change
		 * that I represent.
		 * 
		 * @param feature the fake feature
		 */
		AbstractChange(EStructuralFeature feature) {
			super();
			
			setFeature(feature);
		}
		
		public void preApply(EObject originalObject, boolean reverse) {
			// do nothing, and don't call super because we don't have a
			//    valid feature in the EMF sense
		}
		
		public void apply(EObject originalObject) {
			applyAndReverse(originalObject);
		}
	}
	
	/**
	 * Change denoting "creation" of an object.
	 *
	 * @author Christian W. Damus (cdamus)
	 */
	class CreateChange extends AbstractChange {
		
		CreateChange() {
			super(CREATED_ATTRIBUTE);
		}
		
		public void applyAndReverse(EObject originalObject) {
			Object created = unwrap(originalObject);
			
			if (isReversed) {
				isReversed = false;
				msl.sendNotification(
						MSLEventBroker.createNotification(
								created, EventTypes.CREATE));
			} else {
				isReversed = true;
				msl.sendNotification(
						MSLEventBroker.createNotification(
								created, EventTypes.UNCREATE));
			}
		}
	}
	
	/**
	 * Change denoting "destruction" of an object.
	 *
	 * @author Christian W. Damus (cdamus)
	 */
	class DestroyChange extends AbstractChange {
		DestroyChange() {
			super(DESTROYED_ATTRIBUTE);
		}
		
		public void applyAndReverse(EObject originalObject) {
			Object destroyed = unwrap(originalObject);
			
			if (isReversed) {
				isReversed = false;
				msl.sendNotification(
						MSLEventBroker.createNotification(
								destroyed, EventTypes.DESTROY));
			} else {
				isReversed = true;
				msl.sendNotification(
						MSLEventBroker.createNotification(
								destroyed, EventTypes.UNDESTROY));
			}
		}
	}
	
	/**
	 * Change denoting "proxification" of an object.
	 *
	 * @author Christian W. Damus (cdamus)
	 */
	class UnresolveChange extends AbstractChange {
		private URI proxyUri;
		
		UnresolveChange() {
			super(UNRESOLVED_ATTRIBUTE);
		}
		
		public void applyAndReverse(EObject originalObject) {
			if (isReversed) {
				isReversed = false;
				((InternalEObject) originalObject).eSetProxyURI(proxyUri);
				
				msl.sendNotification(
						MSLEventBroker.createNotification(
								originalObject, EventTypes.UNRESOLVE));
			} else {
				proxyUri = ((InternalEObject) originalObject).eProxyURI();
				isReversed = true;
				
				if (originalObject.eIsProxy()) {

					// attempt a resolve.
					EObject resolved = EcoreUtil.resolve(
						originalObject, getEditingDomain().getResourceSet());

					// the famous "proxy hack"
					if ((resolved != null) && (!resolved.eIsProxy())
						&& (resolved != originalObject)) {
						copyContents(resolved, originalObject);
					}
				}
			}
		}
		
		private void copyContents(final EObject source, final EObject target) {

			final EClass eClass = source.eClass();

			assert eClass == target.eClass();

			if (eClass == target.eClass()) {

				// copy the guts of the resolved object to the proxy
				// object.
				msl.runSilent(new MRunnable() {

					public Object run() {

						for (Iterator i = eClass
							.getEAllStructuralFeatures().iterator(); i
							.hasNext();) {

							EStructuralFeature feature = (EStructuralFeature) i
								.next();

							if ((!feature.isChangeable())
								|| (feature.isDerived()))
								continue;

							if ((feature instanceof EReference)
								&& (((EReference) feature)
									.isContainer()))
								continue;

							if (feature.isMany()) {

								if (source.eIsSet(feature)) {

									Collection list = (Collection) source
										.eGet(feature);

									Collection newList = (Collection) target
										.eGet(feature);

									newList.clear();
									newList.addAll(list);

								} else if (target.eIsSet(feature)) {

									target.eUnset(feature);
								}

							} else if (source.eIsSet(feature)) {

								Object object = source.eGet(feature);

								target.eSet(feature, object);

							} else
								target.eUnset(feature);
						}

						// make the proxy object non proxy.
						((InternalEObject) target).eSetProxyURI(null);

						// remove the resolved object from its
						// container.
						EObject container = source.eContainer();

						if (container != null) {

							EReference reference = source
								.eContainmentFeature();

							if (reference.isMany())
								((Collection) container.eGet(reference))
									.remove(source);
							else
								container.eSet(reference, null);
						}

						// make the resolved object a proxy.
						((InternalEObject) source)
							.eSetProxyURI(proxyUri);

						return null;
					}
				});
			}
		}
	}
}
