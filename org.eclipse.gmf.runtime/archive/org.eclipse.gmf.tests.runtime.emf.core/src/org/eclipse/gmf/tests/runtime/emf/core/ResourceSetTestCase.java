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