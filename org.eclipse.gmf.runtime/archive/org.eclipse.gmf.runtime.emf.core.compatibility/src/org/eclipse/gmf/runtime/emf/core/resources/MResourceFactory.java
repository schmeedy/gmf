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

package org.eclipse.gmf.runtime.emf.core.resources;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.gmf.runtime.emf.core.internal.resources.MSLResource;
import org.eclipse.gmf.runtime.emf.core.internal.util.EMFCoreConstants;

/**
 * A custom implementation of a resource factory. This factory when registered
 * against some file extensions or protocol schemas will create an MSLResource
 * and assigns it default save a load options.
 * 
 * @author rafikj
 * 
 * @deprecated Use the {@link GMFResourceFactory} class, instead.
 */
public class MResourceFactory
	extends GMFResourceFactory {

	public MResourceFactory() {
		super();
	}

	/**
	 * Get default load options.
	 */
	public static Map getDefaultLoadOptions() {
		return GMFResourceFactory.getDefaultLoadOptions();
	}

	/**
	 * Get default save options.
	 */
	public static Map getDefaultSaveOptions() {
		return GMFResourceFactory.getDefaultSaveOptions();
	}

	public Resource createResource(URI uri) {

		XMIResource resource = new MSLResource(uri);

		resource.getDefaultLoadOptions().putAll(getDefaultLoadOptions());
		resource.getDefaultSaveOptions().putAll(getDefaultSaveOptions());

		if (!resource.getEncoding().equals(EMFCoreConstants.XMI_ENCODING)) {
			resource.setEncoding(EMFCoreConstants.XMI_ENCODING);
		}
		
		return resource;
	}
}