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

package org.eclipse.gmf.examples.runtime.emf.metamodel;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.examples.library.Book;
import org.eclipse.emf.examples.library.Library;
import org.eclipse.emf.examples.library.RMPLibraryPackage;
import org.eclipse.emf.examples.library.Writer;
import org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupport;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;

/**
 * Meta-model support implementation for the RMP library meta model.
 * 
 * @see org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupport
 */
public class LibraryMetaModel
	implements IMetamodelSupport {

	private static final String CORE_LIBRARIES_PATHMAP = "CORE_LIBRARIES"; //$NON-NLS-1$
	
	/*
	 * @see org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupport#canDestroy(org.eclipse.emf.ecore.EObject)
	 */
	public boolean canDestroy(EObject eObject) {
		return true;
	}

	/*
	 * @see org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupport#canContain(org.eclipse.emf.ecore.EClass,
	 *      org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EClass)
	 */
	public boolean canContain(EClass eContainer, EReference eReference,
			EClass eClass) {
		return true;
	}

	/*
	 * @see org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupport#handleEvent(org.eclipse.emf.common.notify.Notification)
	 */
	public void handleEvent(Notification event) {
		if (!(event.getNotifier() instanceof EObject)) {
			return;
		}
		
		if (event.getFeature() instanceof EReference &&
				((EReference)event.getFeature()).isContainment()) {
			if (event.getEventType() == Notification.ADD) {
				autoName((EObject)event.getNotifier(), (EReference)event.getFeature(), (EObject)event.getNewValue());
			} else if (event.getEventType() == Notification.ADD_MANY) {
				autoName((EObject)event.getNotifier(), (EReference)event.getFeature(), (Collection)event.getNewValue());
			}
		}
	}
	
	private void setName(EObject eObj, String name) {
		EClass eCls = eObj.eClass();
		
		if (eCls == RMPLibraryPackage.eINSTANCE.getLibrary()) {
			((Library)eObj).setName(name);
		} else if (eCls == RMPLibraryPackage.eINSTANCE.getBook()) {
			((Book)eObj).setTitle(name);
		} else if (eCls == RMPLibraryPackage.eINSTANCE.getWriter()) {
			((Writer)eObj).setName(name);
		}
	}
	
	private String getName(EObject eObj) {
		if (eObj.eClass() == RMPLibraryPackage.eINSTANCE.getBook()) {
			return ((Book)eObj).getTitle();
		} else {
			return EObjectUtil.getName(eObj);
		}
	}
	
	/**
	 * Auto-name object.
	 */
	private void autoName(EObject container, EReference reference,
			EObject eObject) {

		if (reference.isMany()) {
			int max = 1;
			for (Iterator i = ((Collection)container.eGet(reference)).iterator(); i.hasNext();) {
				String name = getName((EObject)i.next());
				if (name == null)
					continue;
				int number = extractNumeric(eObject.eClass().getName(),name);
				if ( number >= max) {
					max = number+1;
				}
			}
			
			setName(eObject, eObject.eClass().getName() + max);
		} else {
			setName(eObject, eObject.eClass().getName());
		}
	}

	private int extractNumeric(String prefix, String fullName) {
		if (fullName.startsWith(prefix)) {
			String suffix = fullName.substring(prefix.length(),fullName.length());
			if (suffix.length() > 0) {
				try {
					return Integer.parseInt(suffix);
				} catch (NumberFormatException e) {
					// Do nothing, fall out and return 1;
				}
			} 
		}
		
		return 0;
	}

	/**
	 * Auto-name objects.
	 */
	private void autoName(EObject container, EReference reference,
			Collection objects) {

		for (Iterator i = objects.iterator(); i.hasNext();) {

			Object object = i.next();

			if (object instanceof EObject) {

				EObject eObject = (EObject) object;

				autoName(container, reference, eObject);
			}
		}
	}

	/*
	 * @see org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupport#postProcess(org.eclipse.emf.ecore.EObject)
	 */
	public void postProcess(final EObject root) {
		// The library of congress will always exist
		//  and its model should always be in the resource set.
		root
			.eResource()
			.getResourceSet()
			.getResource(
				URI.createURI("pathmap://" + CORE_LIBRARIES_PATHMAP + "/" + "LibraryOfCongress.rmplibrary"), true); //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
	}
}