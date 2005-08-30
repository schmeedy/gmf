/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
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
