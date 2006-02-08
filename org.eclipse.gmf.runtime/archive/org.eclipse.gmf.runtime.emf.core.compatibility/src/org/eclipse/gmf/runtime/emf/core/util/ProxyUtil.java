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

package org.eclipse.gmf.runtime.emf.core.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLConstants;

/**
 * This class contains a set of utility methods that control the use of Proxy
 * objects. The MSL stores extra information with Proxy URI (the qualified name
 * of the refernced object incase of cross-resource references) using a
 * proprietary format. The following methods provide access to the data.
 * 
 * @author rafikj
 * @author sgutz
 * 
 * @deprecated See the documentation of individual methods for replacements.
 */
public class ProxyUtil {

	/**
	 * Gets the proxy name by parsing the proxy URI.
	 * 
	 * @param eObject
	 *            The proxy object.
	 * @return The name.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getName(EObject)} method, instead.
	 */
	public static String getProxyName(EObject eObject) {

		if (eObject == null)
			return MSLConstants.EMPTY_STRING;

		return EMFCoreUtil.getName(eObject);
	}

	/**
	 * Gets the proxy qualified name by parsing the proxy URI.
	 * 
	 * @param eObject
	 *            The proxy object.
	 * @return The qualified name.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getQualifiedName(EObject)} method, instead.
	 */
	public static String getProxyQName(EObject eObject) {

		if (eObject == null)
			return MSLConstants.EMPTY_STRING;

		return EMFCoreUtil.getQualifiedName(eObject, false);
	}

	/**
	 * Gets the proxy ID by parsing the proxy URI.
	 * 
	 * @param eObject
	 *            The proxy object.
	 * @return The ID.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getProxyID(EObject)} method, instead.
	 */
	public static String getProxyID(EObject eObject) {

		if (eObject == null)
			return MSLConstants.EMPTY_STRING;

		return EMFCoreUtil.getProxyID(eObject);
	}

	/**
	 * Gets the proxy class ID name by parsing the proxy URI.
	 * 
	 * @param eObject
	 *            The proxy object.
	 * @return The class ID.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getProxyClass(EObject)} method,
	 *    instead, then use {@link MetaModelUtil#getID(ENamedElement)} to get
	 *    the EClass's ID.
	 */
	public static String getProxyClassID(EObject eObject) {

		if (eObject == null)
			return MSLConstants.EMPTY_STRING;

		if (!eObject.eIsProxy())
			return MetaModelUtil.getID(eObject.eClass());

		return MetaModelUtil.getID(EMFCoreUtil.getProxyClass(eObject));
	}

	/**
	 * Gets the proxy class by parsing the proxy URI.
	 * 
	 * @param eObject
	 *            The proxy object.
	 * @return The class.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getProxyClass(EObject)} method, instead.
	 */
	public static EClass getProxyClass(EObject eObject) {

		String id = getProxyClassID(eObject);

		if (id != null) {

			ENamedElement element = MetaModelUtil.getElement(id);

			if ((element != null) && (element instanceof EClass))
				return (EClass) element;
		}

		return null;
	}

	/**
	 * Creates a proxy of the specified type with the specified proxy URI.
	 * 
	 * @param classID
	 *            The type of proxy to create.
	 * @param uri
	 *            The URI for the new proxy.
	 * @return The new proxy.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#createProxy(EClass, URI)} method, instead.
	 */
	public static EObject createProxy(String classID, URI uri) {
		EObject proxy = null;

		if (null != classID) {

			ENamedElement element = MetaModelUtil.getElement(classID);

			if (EClass.class.isInstance(element)) {

				EClass eClass = (EClass) element;

				proxy = EMFCoreUtil.createProxy(eClass, uri);
			}
		}

		return proxy;
	}

	/**
	 * This method returns <code>null</code> if the proxy is not resolved
	 * otherwise returns the object itself.
	 * 
	 * @param eObject
	 *            The proxy object.
	 * @return The resolved object.
	 * 
	 * @deprecated Use the
	 *    {@link EMFCoreUtil#resolve(org.eclipse.emf.transaction.TransactionalEditingDomain, EObject)}
	 *    method, instead.
	 */
	public static EObject resolve(EObject eObject) {
		return resolve(MEditingDomain.INSTANCE, eObject, false);
	}

	/**
	 * This method returns <code>null</code> if the proxy is not resolved
	 * otherwise returns the object itself.
	 * 
	 * @param domain
	 *            Th editing domain.
	 * @param eObject
	 *            The proxy object.
	 * @return The resolved object.
	 * 
	 * @deprecated Use the
	 *    {@link EMFCoreUtil#resolve(org.eclipse.emf.transaction.TransactionalEditingDomain, EObject)}
	 *    method, instead.
	 */
	public static EObject resolve(MEditingDomain domain, EObject eObject) {
		return resolve(domain, eObject, false);
	}

	/**
	 * This method resolves the proxy if the <code>resolve</code> is set to
	 * TRUE. And returns null if the proxy is not resolved otherwise returns the
	 * object itself.
	 * 
	 * @param domain
	 *            Th editing domain.
	 * @param eObject
	 *            The proxy object.
	 * @param resolve
	 *            Force resolve.
	 * @return The resolved object.
	 * 
	 * @deprecated Use the
	 *    {@link EMFCoreUtil#resolve(org.eclipse.emf.transaction.TransactionalEditingDomain, EObject)}
	 *    method, instead.
	 */
	public static EObject resolve(MEditingDomain domain,
			EObject eObject, boolean resolve) {

		if (eObject == null)
			return null;

		if (!eObject.eIsProxy())
			return eObject;

		return EMFCoreUtil.resolve(domain, eObject);
	}

	private ProxyUtil() {
		// private
	}
}