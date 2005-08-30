/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */

package org.eclipse.gmf.tests.runtime.emf.core.internal.expressions;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.uml2.UML2Package;

import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MObjectType;
import org.eclipse.gmf.runtime.emf.core.internal.expressions.EObjectPropertyTester;
import org.eclipse.gmf.runtime.emf.core.internal.resources.MResource;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;


/**
 * Unit tests for the {@link EObjectPropertyTester} class.
 *
 * @author Christian W. Damus (cdamus)
 */
public class EObjectPropertyTesterTest
	extends TestCase {

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
		resource = ResourceUtil.create(
			null,
			UML2Package.eINSTANCE.getModel());
		element = ResourceUtil.getFirstRoot(resource);
		
		tester = new EObjectPropertyTester();
	}
	
	protected void tearDown() throws Exception {
		tester = null;
		resource.getContents().remove(element);
		ResourceSet rset = resource.getResourceSet();
		ResourceUtil.unload(resource);
		rset.getResources().remove(resource);
		element = null;
	}

	/**
	 * Tests that the {@link EObjectPropertyTester#test} method returns
	 * <code>true</code> when it should.
	 */
	public void test_test_true() {
		assertTrue(tester.test(element, OBJECT_TYPE_PROPERTY, TEST_ARGS,
			MODELING_OBJECT_TYPE));
		
		Resource myResource = new MyResource();
		MEditingDomain.INSTANCE.getResourceSet().getResources().add(myResource);
		myResource.getContents().add(element);

		try {
			assertTrue(tester.test(element, OBJECT_TYPE_PROPERTY, TEST_ARGS,
				TEST_OBJECT_TYPE));
		} finally {
			MEditingDomain.INSTANCE.getResourceSet().getResources().remove(myResource);
		}
	}

	/**
	 * Tests that the {@link EObjectPropertyTester#test} method returns
	 * <code>false</code> when it should.
	 */
	public void test_test_false() {
		assertFalse(tester.test(element, OBJECT_TYPE_PROPERTY, TEST_ARGS,
			TEST_OBJECT_TYPE));
		
		Resource myResource = new MyResource();
		MEditingDomain.INSTANCE.getResourceSet().getResources().add(myResource);
		myResource.getContents().add(element);

		try {
			assertFalse(tester.test(element, OBJECT_TYPE_PROPERTY, TEST_ARGS,
				MODELING_OBJECT_TYPE));
		} finally {
			MEditingDomain.INSTANCE.getResourceSet().getResources().remove(myResource);
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
