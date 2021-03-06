/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core;

import org.eclipse.emf.common.notify.Notification;

/**
 * A list of event types that the modeling platform supports.
 * <p>
 * API clients should <b>not </b> instantiate this class.
 * </p>
 *            
 * @deprecated Use the event types defined by the
 *     {@link org.eclipse.emf.common.notify.Notification} interface.
 *     The {@link org.eclipse.emf.transaction.TXEditingDomain} API does not
 *     provide any additional kinds of events (including "create").
 */
public final class EventTypes {

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that the notifier has been created.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int CREATE = 0; // Notification.CREATE;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that a feature of the notifier has been set. This applies for
	 * simple features.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int SET = Notification.SET;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that a feature of the notifier has been unset. This applies
	 * for unsettable features.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int UNSET = Notification.UNSET;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that a value has been inserted into a list-based feature of
	 * the notifier.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int ADD = Notification.ADD;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that a value has been removed from a list-based feature of the
	 * notifier.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int REMOVE = Notification.REMOVE;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that a several values have been added into a list-based
	 * feature of the notifier.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int ADD_MANY = Notification.ADD_MANY;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that a several values have been removed from a list-based
	 * feature of the notifier.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int REMOVE_MANY = Notification.REMOVE_MANY;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that a value has been moved within a list-based feature of the
	 * notifier.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int MOVE = Notification.MOVE;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that an adapter is being removed from the notifier.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int REMOVING_ADAPTER = Notification.REMOVING_ADAPTER;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that a feature of the notifier has been resolved from a proxy.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int RESOLVE = Notification.RESOLVE;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that the notifier has been destroyed.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int DESTROY = 1000;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that the notifier has been uncreated.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int UNCREATE = 1001;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that the notifier has been undestroyed.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int UNDESTROY = 1002;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that the notifier has been unresolved to a proxy.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int UNRESOLVE = 1003;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that the notifier has a new import.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int IMPORT = 1004;

	/**
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that the notifier has a new export.
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 */
	public static final int EXPORT = 1005;

	/**<p>
	 * An
	 * {@link org.eclipse.emf.common.notify.Notification#getEventType event type}
	 * indicating that the notifier is about to be immediately destroyed.
	 * The object being destroyed is both the {@link Notification#getOldValue()} 
	 * and the {@link Notification#getNewValue()} of the notification.
	 * <p>
	 * <p>
	 * <b>Note:</b> This notification is propagated through the EMF adapter mechanism
	 *  because the notification will be required "live."
	 * </p>
	 * 
	 * @see org.eclipse.emf.common.notify.Notification#getEventType
	 * @see org.eclipse.emf.common.notify.Adapter
	 */
	public static final int PRE_DESTROY = 1009;
	
	/**
	 * Private constructor - this class should never be intantiated
	 */
	private EventTypes() {
		//No operation
	};
}