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

package org.eclipse.gmf.runtime.emf.core.internal.index;

import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmf.runtime.emf.core.EventTypes;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;

/**
 * An adapter that maintains itself as an adapter for all contained objects.
 * It can be installed for an {@link EObject}, a {@link Resource}, or a {@link ResourceSet}.
 * <p>
 * This adapter maintain information on inverse references, resource imports, and resource
 * exports.
 * 
 * @author Christian Vogt (cvogt)
 */
public class MSLCrossReferenceAdapter extends CrossReferenceAdapter {

	private MSLEditingDomain domain;

	/**
	 * Constructor.
	 * 
	 * @param domain a (@link MSLEditingDomain}
	 */
	public MSLCrossReferenceAdapter(MSLEditingDomain domain) {
		super();
		this.domain = domain;
	}

	/**
	 * Implemented to send an IMPORT event for the new import.
	 */
	protected void importAdded(final Resource referencer, Resource referenced) {
		domain.sendNotification(new NotificationImpl(
			EventTypes.IMPORT, (Object) null, referenced, -1) {

			public Object getNotifier() {
				return referencer;
			}
		});
	}
	
	/**
	 * Implemented to send an EXPORT event for the new export.
	 */
	protected void exportAdded(final Resource referenced, Resource referencer) {
		domain.sendNotification(new NotificationImpl(
			EventTypes.EXPORT, (Object) null, referencer, -1) {

			public Object getNotifier() {
				return referenced;
			}
		});
	}

	/**
	 * Implemented to send an un-IMPORT event for the removed import.
	 */
	protected void importRemoved(final Resource referencer, Resource referenced) {
		domain.sendNotification(new NotificationImpl(
			EventTypes.IMPORT, referenced, (Object) null, -1) {

			public Object getNotifier() {
				return referencer;
			}
		});
	}

	/**
	 * Implemented to send an un-EXPORT event for the removed export.
	 */
	protected void exportRemoved(final Resource referenced, Resource referencer) {
		domain.sendNotification(new NotificationImpl(
			EventTypes.EXPORT, referencer, (Object) null, -1) {

			public Object getNotifier() {
				return referenced;
			}
		});
	}
}
