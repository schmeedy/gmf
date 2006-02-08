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


package org.eclipse.gmf.tests.runtime.emf.core.internal.expressions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.gmf.runtime.emf.core.edit.MObjectType;
import org.eclipse.gmf.runtime.emf.core.internal.expressions.EObjectPropertyTester;
import org.eclipse.gmf.runtime.emf.core.internal.resources.MResource;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;
import org.eclipse.gmf.tests.runtime.emf.core.BaseCoreTests;
import org.eclipse.uml2.UML2Package;


/**
 * Unit tests for the {@link EObjectPropertyTester} class.
 *
 * @author Christian W. Damus (cdamus)
 */
public class EObjectPropertyTesterTest
	extends BaseCoreTests {

	private static final String TEST_OBJECT_TYPE = "MyObject"; //$NON-NLS-1$
	private static final String MODELING_OBJECT_TYPE = MObjectType.MODELING.getName();
	private static final String OBJECT_TYPE_PROPERTY = "objectType"; //$NON-NLS-1$
	private static final Object[] TEST_ARGS = new Object[0];
	
	private EObjectPropertyTester tester;  // fixture
	private EObject element;      // fixture
	private Resource resource;    // fixture
	
	/**
	 * Initializes me.
	 */
	public EObjectPropertyTesterTest() {
		super();
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		
		resource = domain.createResource(
			null,
			UML2Package.eINSTANCE.getModel());
		element = ResourceUtil.getFirstRoot(resource);
		
		tester = new EObjectPropertyTester();
	}
	
	protected void tearDown() throws Exception {
		tester = null;
		resource.getContents().remove(element);
		ResourceSet rset = resource.getResourceSet();
		resource.unload();
		rset.getResources().remove(resource);
		element = null;
		
		super.tearDown();
	}

	/**
	 * Tests that the {@link EObjectPropertyTester#test} method returns
	 * <code>true</code> when it should.
	 */
	public void test_test_true() {
		if (writing()) {
			assertTrue(tester.test(element, OBJECT_TYPE_PROPERTY, TEST_ARGS,
				MODELING_OBJECT_TYPE));
			
			Resource myResource = new MyResource();
			domain.getResourceSet().getResources().add(myResource);
			myResource.getContents().add(element);
	
			try {
				assertTrue(tester.test(element, OBJECT_TYPE_PROPERTY, TEST_ARGS,
					TEST_OBJECT_TYPE));
			} finally {
				domain.getResourceSet().getResources().remove(myResource);
			}
		}
	}

	/**
	 * Tests that the {@link EObjectPropertyTester#test} method returns
	 * <code>false</code> when it should.
	 */
	public void test_test_false() {
		if (writing()) {
			assertFalse(tester.test(element, OBJECT_TYPE_PROPERTY, TEST_ARGS,
				TEST_OBJECT_TYPE));
			
			Resource myResource = new MyResource();
			domain.getResourceSet().getResources().add(myResource);
			myResource.getContents().add(element);
	
			try {
				assertFalse(tester.test(element, OBJECT_TYPE_PROPERTY, TEST_ARGS,
					MODELING_OBJECT_TYPE));
			} finally {
				domain.getResourceSet().getResources().remove(myResource);
			}
		}
	}
	
	private static class MyResource extends XMIResourceImpl implements MResource {
		public Helper getHelper() {
			return new Helper() {
				public MObjectType getType() {
					return MyObjectType.INSTANCE;
				}};
		}
	}
	
	private static class MyObjectType extends MObjectType {
		final static MObjectType INSTANCE = new MyObjectType();
		
		private MyObjectType() {
			super(TEST_OBJECT_TYPE);
		}
		
	}
}
