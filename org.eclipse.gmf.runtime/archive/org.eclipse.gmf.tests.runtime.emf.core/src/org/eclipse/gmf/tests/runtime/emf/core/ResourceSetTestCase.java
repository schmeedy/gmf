/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2002, 2003.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.tests.runtime.emf.core;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;

/**
 * @author rafikj
 *
 * Resource set test case.
 */
public class ResourceSetTestCase
	extends BaseTestCase {

	public ResourceSetTestCase(String name) {
		super(name);
	}

	public void test_resourceSet1() {

		ResourceSet set = ResourceUtil.getResourceSet();

		assertNotNull(set);

		Resource resource = set.createResource(URI
			.createFileURI("\\tmp\\test1.emx")); //$NON-NLS-1$

		assertNotNull(resource);
	}
}