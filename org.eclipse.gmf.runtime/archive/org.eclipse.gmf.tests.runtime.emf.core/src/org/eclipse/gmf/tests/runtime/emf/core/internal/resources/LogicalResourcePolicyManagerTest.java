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


package org.eclipse.gmf.tests.runtime.emf.core.internal.resources;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.internal.resources.LogicalResourcePolicyManager;
import org.eclipse.gmf.runtime.emf.core.resources.CannotAbsorbException;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResource;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResourcePolicy;
import org.eclipse.gmf.runtime.emf.core.resources.MResourceFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests the {@link LogicalResourcePolicyManager} class.
 *
 * @author Christian W. Damus (cdamus)
 */
public class LogicalResourcePolicyManagerTest
	extends TestCase {
	
	private EPackage dynamicPackage;
	private EClass dynamicClass;
	static EAttribute dynamicPoliciesAttribute;
	private EObject dynamicObject;
	private ILogicalResource res;
	
	public static Test suite() {
		return new TestSuite(LogicalResourcePolicyManagerTest.class, "Policy Manager Tests"); //$NON-NLS-1$
	}
	
	public void test_canSeparate() {
		assertTrue(LogicalResourcePolicyManager.getInstance().canSeparate(
			res, dynamicObject));
		checkTokens();
		
		TestPolicy.shouldBalk = true;
		
		assertFalse(LogicalResourcePolicyManager.getInstance().canSeparate(
			res, dynamicObject));
		checkTokens();
	}
	
	public void test_preSeparate() {
		URI uri = null;
		try {
			uri = LogicalResourcePolicyManager.getInstance().preSeparate(
				res, dynamicObject, null);
		} catch (CannotSeparateException e) {
			fail("Should not have thrown: " + e); //$NON-NLS-1$
		}
		checkTokens();
		assertNotNull(uri);
		// check that the first suggestion was accepted
		assertEquals(URI.createFileURI("/tmp/Foo.xmi"), uri); //$NON-NLS-1$
		
		try {
			uri = LogicalResourcePolicyManager.getInstance().preSeparate(
				res, dynamicObject, URI.createFileURI("/tmp/User.xmi")); //$NON-NLS-1$
		} catch (CannotSeparateException e) {
			fail("Should not have thrown: " + e); //$NON-NLS-1$
		}
		checkTokens();
		assertNotNull(uri);
		// check that the no suggestion was accepted
		assertEquals(URI.createFileURI("/tmp/User.xmi"), uri); //$NON-NLS-1$
		
		TestPolicy.shouldBalk = true;
		
		try {
			uri = LogicalResourcePolicyManager.getInstance().preSeparate(
				res, dynamicObject, null);
			fail("Should have thrown exception."); //$NON-NLS-1$
		} catch (CannotSeparateException e) {
			checkTokens();
		}
	}
	
	public void test_postSeparate() {
		LogicalResourcePolicyManager.getInstance().postSeparate(
			res, dynamicObject, URI.createFileURI("/tmp/User.xmi")); //$NON-NLS-1$
		checkTokens();
	}
	
	public void test_preAbsorb() {
		try {
			LogicalResourcePolicyManager.getInstance().preAbsorb(
				res, dynamicObject);
		} catch (CannotAbsorbException e) {
			fail("Should not have thrown: " + e); //$NON-NLS-1$
		}
		checkTokens();
		
		TestPolicy.shouldBalk = true;
		
		try {
			LogicalResourcePolicyManager.getInstance().preAbsorb(
				res, dynamicObject);
			fail("Should have thrown exception."); //$NON-NLS-1$
		} catch (CannotAbsorbException e) {
			checkTokens();
		}
	}
	
	public void test_postAbsorb() {
		LogicalResourcePolicyManager.getInstance().postAbsorb(
			res, dynamicObject);
		checkTokens();
	}
	
	/**
	 * Tests the robustness of the policy manager against run-time exceptions
	 * in the policies.
	 */
	public void test_robustness() {
		TestPolicy.shouldThrow = true;
		
		LogicalResourcePolicyManager.getInstance().canSeparate(res, dynamicObject);
		checkTokens();
		
		try {
			LogicalResourcePolicyManager.getInstance().preSeparate(res, dynamicObject, null);
			checkTokens();
		} catch (CannotSeparateException e) {
			fail("Should not have thrown: " + e); //$NON-NLS-1$
		}
		
		LogicalResourcePolicyManager.getInstance().postSeparate(res, dynamicObject, null);
		checkTokens();
		
		try {
			LogicalResourcePolicyManager.getInstance().preAbsorb(res, dynamicObject);
			checkTokens();
		} catch (CannotAbsorbException e) {
			fail("Should not have thrown: " + e); //$NON-NLS-1$
		}
		
		LogicalResourcePolicyManager.getInstance().postAbsorb(res, dynamicObject);
		checkTokens();
	}
	
	//
	// Fixtures
	//
	
	protected void setUp()
		throws Exception {
		
		super.setUp();
		
		// create the dynamic metamodel and an object
		dynamicPackage = EcoreFactory.eINSTANCE.createEPackage();
		dynamicPackage.setName("logrestest"); //$NON-NLS-1$
		dynamicPackage.setNsPrefix("logrestest"); //$NON-NLS-1$
		dynamicPackage.setNsURI("http:///com/ibm/xtools/emf/msl/core/tests/logrestest.ecore"); //$NON-NLS-1$
		
		EFactory factory = EcoreFactory.eINSTANCE.createEFactory();
		factory.setEPackage(dynamicPackage);
		dynamicPackage.setEFactoryInstance(factory);

		dynamicClass = EcoreFactory.eINSTANCE.createEClass();
		dynamicPackage.getEClassifiers().add(dynamicClass);
		dynamicClass.setName("TestClass"); //$NON-NLS-1$
		
		dynamicPoliciesAttribute = EcoreFactory.eINSTANCE.createEAttribute();
		dynamicClass.getEStructuralFeatures().add(dynamicPoliciesAttribute);
		dynamicPoliciesAttribute.setName("policies"); //$NON-NLS-1$
		dynamicPoliciesAttribute.setUpperBound(EAttribute.UNBOUNDED_MULTIPLICITY);
		dynamicPoliciesAttribute.setEType(EcorePackage.eINSTANCE.getEJavaObject());
		
		dynamicObject = factory.create(dynamicClass);
		
		res = (ILogicalResource) new MResourceFactory().createResource(
			URI.createFileURI("/tmp/logrespolmgrtest.xmi")); //$NON-NLS-1$
		MEditingDomain.INSTANCE.getResourceSet().getResources().add(res);
	}
	
	protected void tearDown()
		throws Exception {
		
		MEditingDomain.INSTANCE.unloadResource(res);
		MEditingDomain.INSTANCE.getResourceSet().getResources().remove(res);
		
		TestPolicy.shouldThrow = false;
		TestPolicy.shouldBalk = false;
		
		dynamicObject = null;
		dynamicPoliciesAttribute = null;
		dynamicClass = null;
		dynamicPackage = null;
		
		super.tearDown();
	}

	/**
	 * Checks that both tokens were added to the dynamic eObject by the test,
	 * then clears them.
	 */
	private void checkTokens() {
		EList value = (EList) dynamicObject.eGet(dynamicPoliciesAttribute);
		
		// check that both tokens were added
		assertEquals(2, value.size());
		
		if (TestPolicy.shouldThrow) {
			assertTrue(value.get(0) instanceof RuntimeException);
			assertTrue(value.get(1) instanceof RuntimeException);
		} else {
			assertTrue(value.get(0) instanceof TestPolicy);
			assertTrue(value.get(1) instanceof TestPolicy);
		}
		
		value.clear();
	}
	
	/**
	 * Test policy implementation.  Two instances are created, 0 and 1.
	 * If the 1 instance is invoked when the <code>shouldBalk</code> flag is
	 * set, then it either returns false or throws an exception, as appropriate
	 * to the method being called.  The 0 instance always behaves nicely.
	 *
	 * @author Christian W. Damus (cdamus)
	 */
	public static class TestPolicy implements ILogicalResourcePolicy {
		static boolean shouldBalk = false;
		static boolean shouldThrow = false;
		
		private static int nextSerialNum = 0;
		
		private final int serialNum;
		
		public TestPolicy() {
			serialNum = nextSerialNum++;
		}
		
		public boolean canSeparate(ILogicalResource resource, EObject eObject) {
			recordToken(eObject);
			
			return serialNum == 0 || !shouldBalk;
		}

		public URI preSeparate(ILogicalResource resource, EObject eObject, URI uri)
			throws CannotSeparateException {
			
			recordToken(eObject);
			
			if (serialNum == 0) {
				return URI.createFileURI("/tmp/Foo.xmi"); //$NON-NLS-1$
			} else {
				if (shouldBalk) {
					throw new CannotSeparateException("I should balk."); //$NON-NLS-1$
				}
				
				return URI.createFileURI("/tmp/Bar.xmi"); //$NON-NLS-1$;
			}
		}

		public void postSeparate(ILogicalResource resource, EObject eObject, URI uri) {
			recordToken(eObject);
		}

		public void preAbsorb(ILogicalResource resource, EObject eObject)
			throws CannotAbsorbException {
			
			recordToken(eObject);
			
			if (serialNum == 1 && shouldBalk) {
				throw new CannotAbsorbException("I should balk."); //$NON-NLS-1$
			}
		}

		public void postAbsorb(ILogicalResource resource, EObject eObject) {
			recordToken(eObject);
		}
		
		private void recordToken(EObject eObject) {
			EList value = (EList) eObject.eGet(dynamicPoliciesAttribute);
			
			if (shouldThrow) {
				RuntimeException e = new RuntimeException(
					"I am supposed to throw."); //$NON-NLS-1$
				
				value.add(e);
				throw e; 
			} else {
				value.add(this);
			}
		}
	}
}
