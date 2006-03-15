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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.gmf.runtime.emf.core.EventTypes;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;
import org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter;

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
		super(false);
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
	
	/**
	 * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#isIncluded(org.eclipse.emf.ecore.EReference)
	 */
	protected boolean isIncluded(EReference eReference) {
		return super.isIncluded(eReference) 
			&& !eReference.isContainer() && !eReference.isContainment();
	}
	
	/**
	 * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#getInverseReferences(org.eclipse.emf.ecore.EObject)
	 */
	public Collection getInverseReferences(EObject eObject) {
		Collection result = new ArrayList();

		// removed the addition of eContainer from default behavior
		
		Collection nonNavigableInverseReferences = (Collection)inverseCrossReferencer.get(eObject);
		if (nonNavigableInverseReferences != null) {
			result.addAll(nonNavigableInverseReferences);
		}
		
	    EContentsEList.FeatureIterator crossReferences =
	    	(EContentsEList.FeatureIterator) eObject.eCrossReferences().iterator();
		while (crossReferences.hasNext()) {
			InternalEObject referent = (InternalEObject) crossReferences.next();
			EReference eReference = (EReference) crossReferences.feature();
			EReference eOpposite = eReference.getEOpposite();
			
			if (referent != null && eOpposite != null) {
				result.add(referent.eSetting(eOpposite));
			}
		}
		
		return result;
	}	
}
