/******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/


package org.eclipse.gmf.runtime.emf.core.edit;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * An optional extension to the {@link IDemuxedListener} interface for listeners
 * interested in separation and absorption events in logical resources.
 *
 * @author Christian W. Damus (cdamus)
 * @author Vishy Ramaswamy
 * 
 * @deprecated Attach a {@link org.eclipse.emf.transaction.DemultiplexingListener}
 *     to the {@link org.eclipse.emf.transaction.TXEditingDomain}, instead
 *     (for post-commit events).  For pre-commit events (to create triggers),
 *     use a {@link org.eclipse.emf.transaction.TriggerListener}.
 */
public interface IDemuxedMListener2
	extends IDemuxedMListener {
	
	/**
	 * Notifies me of an event indicating that a root has been added to the resource
	 * 
	 * @param notification the raw notification
	 * @param resource the resource into which the root object was added
	 * @param eObject the root object that was added
	 */
	public void handleRootAddedEvent(Notification notification, Resource resource, EObject eObject);

	/**
	 * Notifies me of an event indicating that a root has been removed from the resource
	 * 
	 * @param notification the raw notification
	 * @param resource the resource from which the root object was removed
	 * @param eObject the root object that was removed
	 */
	public void handleRootRemovedEvent(Notification notification, Resource resource, EObject eObject);
}
