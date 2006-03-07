/******************************************************************************
 * Copyright (c) 2002, 2006 IBM Corporation and others.
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.emf.core.internal.resources.MSLResource;
import org.eclipse.gmf.runtime.emf.core.internal.util.EMFCoreConstants;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;

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
	extends GMFResourceFactory implements IExtendedResourceFactory {

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
		
		resource.eAdapters().add(new MSLResourceHelper());
		
		return resource;
	}

	public String getProxyName(EObject proxy) {
		return MSLUtil.getProxyName(proxy);
	}

	public String getProxyQualifiedName(EObject proxy) {
		return MSLUtil.getProxyQName(proxy);
	}

	public String getProxyID(EObject proxy) {
		return MSLUtil.getProxyID(proxy);
	}

	public String getProxyClassID(EObject proxy) {
		return MSLUtil.getProxyClassID(proxy);
	}

	public EObject resolve(TransactionalEditingDomain domain, EObject proxy) {
		return MSLUtil.resolve(domain, proxy, false);
	}
	
	private static class MSLResourceHelper extends ResourceHelperImpl {

		public MSLResourceHelper() {
			super();
		}

		public EObject create(EClass eClass) {
			TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(
					getResource());
			
			EObject result = MSLUtil.create(domain, eClass, true);
			
			if (domain != null) {
				// this object is to be managed by this editing domain
				result.eAdapters().add(
					((InternalTransactionalEditingDomain) domain).getChangeRecorder());
			}

			return result;
		}
	}
}