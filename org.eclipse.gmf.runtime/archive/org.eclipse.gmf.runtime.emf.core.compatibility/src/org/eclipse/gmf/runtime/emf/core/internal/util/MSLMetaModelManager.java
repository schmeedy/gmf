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

package org.eclipse.gmf.runtime.emf.core.internal.util;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.ENamedElement;

/**
 * This class manages meta-models and provide localization of meta-class names.
 * 
 * @author rafikj
 * 
 * @deprecated Use the {@link MetamodelManager} class, instead.
 */
public class MSLMetaModelManager {

	/**
	 * Register meta-model object.
	 * 
	 * @deprecated Use the {@link MetamodelManager#register(ENamedElement)}
	 *     or {@link MetamodelManager#register(ENamedElement, ResourceLocator)}
	 *     method, instead
	 */
	public static void register(ENamedElement element,
			ResourceLocator resourceLocator) {

		MetamodelManager.register(element, resourceLocator);
	}

	/**
	 * Get the ID of a meta-model object.
	 * 
	 * @deprecated Use the {@link MetamodelManager#getID(ENamedElement)}
	 *     method, instead
	 */
	public static String getID(ENamedElement element) {

		return MetamodelManager.getID(element);
	}

	/**
	 * Get the localized name of a meta-model object. Name does not contain
	 * spaces.
	 * 
	 * @deprecated Use the {@link MetamodelManager#getLocalName(ENamedElement)}
	 *     method, instead
	 */
	public static String getLocalName(ENamedElement element) {

		return MetamodelManager.getLocalName(element);
	}

	/**
	 * Get the localized name of a meta-model object. Name may contain spaces.
	 * 
	 * @deprecated Use the {@link MetamodelManager#getDisplayName(ENamedElement)}
	 *     method, instead
	 */
	public static String getDisplayName(ENamedElement element) {

		return MetamodelManager.getDisplayName(element);
	}

	/**
	 * Find meta-model object given its ID.
	 * 
	 * @deprecated Use the {@link MetamodelManager#getElement(String)}
	 *     method, instead
	 */
	public static ENamedElement getElement(String id) {
		return MetamodelManager.getElement(id);
	}
}