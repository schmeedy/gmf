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


package org.eclipse.gmf.runtime.emf.core.internal.expressions;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;


/**
 * Property tester for MSL extended properties of {@link EObject}s.  Currently
 * supported properties are
 * <dl>
 *   <dt>objectType</dt>
 *     <dd>string-valued property denoting an <code>EObject</code>'s
 *         {@linkplain org.eclipse.gmf.runtime.emf.core.internal.MObjectType object type}</dd>
 * </dl>
 *
 * @author Christian W. Damus (cdamus)
 * 
 * @deprecated The {@link org.eclipse.gmf.runtime.emf.core.edit.MObjectType}
 *    concept is obsolete (see documentation on that class).
 */
public class EObjectPropertyTester extends PropertyTester {
	private static final String OBJECT_TYPE_PROPERTY = "objectType"; //$NON-NLS-1$
	
	/**
	 * Initializes me.
	 */
	public EObjectPropertyTester() {
		super();
	}
	
	public boolean test(
			Object receiver,
			final String property,
			final Object[] args,
			final Object expectedValue) {
		
		boolean result = false;
		
		final EObject eObject = (EObject) receiver;
		
		Resource res = eObject.eResource();
		if (res != null) {
			MEditingDomain domain = MEditingDomain.getEditingDomain(res);
			
			if (domain != null) {
				result = ((Boolean) domain.runAsRead(new MRunnable() {
					public Object run() {
						return doTest(
							eObject,
							property,
							args,
							expectedValue);
					}})).booleanValue();
			}
		}
		
		return result;
	}
	
	/**
	 * Implementation of the {@link #test(Object, String, Object[], Object)} to
	 * be executed in a read operation in the <code>eObject</code>'s editing
	 * domain.
	 * 
	 * @param eObject the model element to be tested
	 * @param property the property to be tested
	 * @param args optional property arguments
	 * @param expectedValue the expected value of the property (if not boolean)
	 * 
	 * @return the result of the test, as a boolean object
	 */
	Boolean doTest(EObject eObject, String property, Object[] args, Object expectedValue) {
		boolean result = false;
		
		if (property.equals(OBJECT_TYPE_PROPERTY)) {
			result = EObjectUtil.getType(eObject).getName().equals(expectedValue);
		}
		
		return Boolean.valueOf(result);
	}

}
