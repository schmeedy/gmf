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

package org.eclipse.gmf.tests.runtime.emf.core;

import org.eclipse.emf.ecore.EcoreFactory;

import org.eclipse.gmf.runtime.emf.core.util.EObjectContainmentUtil;

import junit.framework.TestCase;

public class EObjectContainmentUtilTest extends TestCase {
	public void test_isSameKind_RATLC00534508() {
		// We will check to make sure that if we place a garbage EClass into
		//  this utility method, we won't get an NPE.
		try {
			assertFalse(EObjectContainmentUtil.isSameKind(EcoreFactory.eINSTANCE.createEObject(),null));
		} catch (NullPointerException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
