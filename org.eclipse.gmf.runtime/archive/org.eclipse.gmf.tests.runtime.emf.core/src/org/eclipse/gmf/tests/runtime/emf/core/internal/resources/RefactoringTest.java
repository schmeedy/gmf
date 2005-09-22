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

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResource;


/**
 * Tests a variety of refactoring scenarios in logical resources, involving
 * the relocation in the content tree of separate elements, to check that the
 * resource map is correctly updated on saving.
 *
 * @author Christian W. Damus (cdamus)
 */
public class RefactoringTest
	extends BaseLogicalResourceTest {
	
	public RefactoringTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new TestSuite(RefactoringTest.class, "Refactoring Tests"); //$NON-NLS-1$
	}

	/**
	 * Tests correct save and re-load when moving a separate element to a
	 * different position in its containment reference.
	 */
	public void test_moveWithinContainmentList() {
		String subunit1Frag = logres.getURIFragment(subunit1);
		
		Library parent = subunit1.getParentBranch();
		EList branches = parent.getBranches();
		
		Library newLibrary = EXTLibraryFactory.eINSTANCE.createLibrary();
		branches.add(newLibrary);
		newLibrary.setName("New Library"); //$NON-NLS-1$
		String newFrag = logres.getURIFragment(newLibrary);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		subunit1 = lookupLibrary(subunit1Frag);
		newLibrary = lookupLibrary(newFrag);
		
		parent = subunit1.getParentBranch();
		assertNotNull(parent);
		
		branches = parent.getBranches();
		
		assertEquals(0, branches.indexOf(subunit1));
		assertEquals(1, branches.indexOf(newLibrary));
		
		// reposition the sub-unit
		branches.move(1, subunit1);
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		subunit1 = lookupLibrary(subunit1Frag);
		newLibrary = lookupLibrary(newFrag);
		
		parent = subunit1.getParentBranch();
		assertNotNull(parent);
		
		branches = parent.getBranches();
		
		// correctly restored the reverse order
		assertEquals(1, branches.indexOf(subunit1));
		assertEquals(0, branches.indexOf(newLibrary));
	}

	/**
	 * Tests correct save and re-load when moving a separate element to a
	 * different containment reference in its parent.
	 */
	public void test_moveWithinParentElement() {
		// create a test metamodel that has two features of the same kind
		EPackage dynamicPackage;
		EClass dynamicClass;
		EReference dynamicRef1;
		EReference dynamicRef2;
		
		dynamicPackage = EcoreFactory.eINSTANCE.createEPackage();
		dynamicPackage.setName("logresrefactor"); //$NON-NLS-1$
		dynamicPackage.setNsPrefix("lrr"); //$NON-NLS-1$
		dynamicPackage.setNsURI("http:///com/ibm/xtools/emf/msl/core/tests/logresrefactor.ecore"); //$NON-NLS-1$
		// make sure that we can serialize references to the package's contents
		new ResourceImpl(URI.createURI(dynamicPackage.getNsURI())).getContents().add(
			dynamicPackage);
		EPackage.Registry.INSTANCE.put(dynamicPackage.getNsURI(), dynamicPackage);
		
		EFactory factory = EcoreFactory.eINSTANCE.createEFactory();
		factory.setEPackage(dynamicPackage);
		dynamicPackage.setEFactoryInstance(factory);

		dynamicClass = EcoreFactory.eINSTANCE.createEClass();
		dynamicPackage.getEClassifiers().add(dynamicClass);
		dynamicClass.setName("TestClass"); //$NON-NLS-1$
		
		dynamicRef1 = EcoreFactory.eINSTANCE.createEReference();
		dynamicClass.getEStructuralFeatures().add(dynamicRef1);
		dynamicRef1.setName("ref1"); //$NON-NLS-1$
		dynamicRef1.setChangeable(true);
		dynamicRef1.setContainment(true);
		dynamicRef1.setUpperBound(EAttribute.UNBOUNDED_MULTIPLICITY);
		dynamicRef1.setEType(dynamicClass);
		
		dynamicRef2 = EcoreFactory.eINSTANCE.createEReference();
		dynamicClass.getEStructuralFeatures().add(dynamicRef2);
		dynamicRef2.setName("ref2"); //$NON-NLS-1$
		dynamicRef2.setChangeable(true);
		dynamicRef2.setContainment(true);
		dynamicRef2.setUpperBound(EAttribute.UNBOUNDED_MULTIPLICITY);
		dynamicRef2.setEType(dynamicClass);
		
		try {
			EObject dynamicContainer = factory.create(dynamicClass);
			EObject dynamicSubunit = factory.create(dynamicClass);

			logres.getContents().add(dynamicContainer);
			((EList) dynamicContainer.eGet(dynamicRef1)).add(dynamicSubunit);
			
			String containerFrag = logres.getURIFragment(dynamicContainer);
			String subunitFrag = logres.getURIFragment(dynamicSubunit);
			
			try {
				logres.separate(dynamicSubunit, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			} catch (CannotSeparateException e) {
				fail("Should not fail to separate: " + e); //$NON-NLS-1$
			}
			
			saveLogicalResource();
			
			// discard the resource and re-load from disk
			createLogicalResource(RESOURCE_NAME);
			loadLogicalResource();
			
			dynamicContainer = logres.getEObject(containerFrag);
			dynamicSubunit = logres.getEObject(subunitFrag);
			assertNotNull(dynamicContainer);
			assertNotNull(dynamicSubunit);
			
			assertSame(dynamicContainer, dynamicSubunit.eContainer());
			assertSame(dynamicRef1, dynamicSubunit.eContainmentFeature());
			
			// move the sub-unit to another containment reference of the same parent
			((EList) dynamicContainer.eGet(dynamicRef2)).add(dynamicSubunit);
			
			saveLogicalResource();
			
			// discard the resource and re-load from disk
			createLogicalResource(RESOURCE_NAME);
			loadLogicalResource();
			
			dynamicContainer = logres.getEObject(containerFrag);
			dynamicSubunit = logres.getEObject(subunitFrag);
			assertNotNull(dynamicContainer);
			assertNotNull(dynamicSubunit);
			
			// correctly restored the containment reference
			assertSame(dynamicContainer, dynamicSubunit.eContainer());
			assertSame(dynamicRef2, dynamicSubunit.eContainmentFeature());
		} finally {			
			// must unload before we remove the dynamic package
			unloadLogicalResource();
			EPackage.Registry.INSTANCE.remove(dynamicPackage.getNsURI());
		}
	}

	/**
	 * Tests correct save and re-load when moving a separate element to a
	 * different parent element in the same (parent) unit.
	 */
	public void test_moveWithinParentUnit() {
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		
		subunit3.getBranches().add(subunit1);
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		
		// correctly restored the parent relationship
		assertSame(subunit3, subunit1.getParentBranch());
	}

	/**
	 * Tests correct save and re-load when moving a separate element to a
	 * different parent element in a different (parent) unit.
	 */
	public void test_moveToNewParentUnit() {
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME3));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		
		subunit3.getBranches().add(subunit1);
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		
		// correctly restored the parent relationship
		assertSame(subunit3, subunit1.getParentBranch());
	}
	
	/**
	 * Tests correct save and re-load when deleting a separate element from
	 * the logical model.
	 */
	public void test_deleteSeparateElement() {
		String subunit2Frag = logres.getURIFragment(subunit2);
		
		try {
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		subunit2 = lookupLibrary(subunit2Frag);
		assertNotNull(subunit2);
		
		assertEquals(2, logres.getMappedResources().size());
		
		// disconnect from the logical contents tree
		subunit2.getParentBranch().getBranches().remove(subunit2);
		
		assertEquals(1, logres.getMappedResources().size());
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();

		// check that it doesn't exist any more, even as an unloaded "proxy"
		subunit2 = lookupLibrary(subunit2Frag);
		assertNull(subunit2);
	}

	/**
	 * Tests correct save and re-load when moving a separate element from one
	 * logical resource to another.
	 */
	public void test_moveToNewLogicalResource() {
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		ILogicalResource logres2 = createNewLogicalResource(
			URI.createPlatformResourceURI(RESOURCE_NAME2));
		Library library2 = EXTLibraryFactory.eINSTANCE.createLibrary();
		logres2.getContents().add(library2);
		library2.setName("Library 2"); //$NON-NLS-1$
		
		String root2Frag = logres2.getURIFragment(library2);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME3));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		saveLogicalResource(logres2);
		
		// discard the resources and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		logres2 = createNewLogicalResource(
			URI.createPlatformResourceURI(RESOURCE_NAME2));
		loadLogicalResource(logres2);
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		library2 = lookupLibrary(logres2, root2Frag);
		assertNotNull(library2);
		
		assertEquals(3, logres.getMappedResources().size());
		assertEquals(1, logres2.getMappedResources().size());
		
		library2.getBranches().add(subunit1); // inter-resource move
		subunit1Frag = logres2.getURIFragment(subunit1);  // in case of re-guid
		
		assertEquals(2, logres.getMappedResources().size());
		assertEquals(1, logres2.getMappedResources().size()); // not a sub-unit here
		
		saveLogicalResource();
		saveLogicalResource(logres2);
		
		// discard the resources and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		logres2 = createNewLogicalResource(
			URI.createPlatformResourceURI(RESOURCE_NAME2));
		loadLogicalResource(logres2);
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNull(subunit1);
		subunit1 = lookupLibrary(logres2, subunit1Frag);
		assertNotNull(subunit1);  // found it in the other resource this time
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		library2 = lookupLibrary(logres2, root2Frag);
		assertNotNull(library2);
		
		// correctly restored the parent relationship
		assertSame(library2, subunit1.getParentBranch());
	}

	/**
	 * Tests correct save and re-load when moving a separate root to a
	 * different position in its containment reference.
	 */
	public void test_moveRootWithinContentsList() {
		String rootFrag = logres.getURIFragment(root);
		
		Library newLibrary = EXTLibraryFactory.eINSTANCE.createLibrary();
		EList contents = logres.getContents();
		contents.add(newLibrary);
		newLibrary.setName("New Library"); //$NON-NLS-1$
		String newFrag = logres.getURIFragment(newLibrary);
		
		try {
			logres.separate(root, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		root = lookupLibrary(rootFrag);
		newLibrary = lookupLibrary(newFrag);
		
		contents = logres.getContents();
		
		assertEquals(0, contents.indexOf(root));
		assertEquals(1, contents.indexOf(newLibrary));
		
		// reposition the sub-unit
		contents.move(1, root);
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		root = lookupLibrary(rootFrag);
		newLibrary = lookupLibrary(newFrag);
		
		contents = logres.getContents();
		
		// correctly restored the reverse order
		assertEquals(1, contents.indexOf(root));
		assertEquals(0, contents.indexOf(newLibrary));
	}

	/**
	 * Tests correct save and re-load when moving a separate root to a
	 * different parent element in the same (root) unit.
	 */
	public void test_moveRootWithinParentUnit() {
		String rootFrag = logres.getURIFragment(root);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		EList contents = logres.getContents();
		contents.add(subunit3);  // second root in the root resource
		
		try {
			logres.separate(root, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		
		subunit3.getBranches().add(root);
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		
		// correctly restored the parent relationship
		assertSame(subunit3, root.getParentBranch());
	}

	/**
	 * Tests correct save and re-load when moving a separate root to a
	 * different parent element in a different (parent) unit.
	 */
	public void test_moveRootToNewParentUnit() {
		String rootFrag = logres.getURIFragment(root);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		logres.getContents().add(subunit3);  // make it another root
		
		try {
			logres.separate(root, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME3));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		
		subunit3.getBranches().add(root);
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		
		// correctly restored the parent relationship
		assertSame(subunit3, root.getParentBranch());
	}
	
	/**
	 * Tests correct save and re-load when deleting a separate root from
	 * the logical model.
	 */
	public void test_deleteSeparateRootElement() {
		String rootFrag = logres.getURIFragment(root);
		String subunit2Frag = logres.getURIFragment(subunit2);
		
		logres.getContents().add(subunit2);
		
		try {
			logres.separate(root, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		subunit2 = lookupLibrary(subunit2Frag);
		assertNotNull(subunit2);
		
		assertEquals(2, logres.getMappedResources().size());
		
		// disconnect from the logical contents tree
		logres.getContents().remove(root);
		
		assertEquals(1, logres.getMappedResources().size());
		
		saveLogicalResource();
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();

		subunit2 = lookupLibrary(subunit2Frag);
		assertNotNull(subunit2);
		
		// check that it doesn't exist any more, even as an unloaded "proxy"
		root = lookupLibrary(rootFrag);
		assertNull(root);
	}

	/**
	 * Tests correct save and re-load when moving a separate root from one
	 * logical resource to another.  This is an extra special case, because
	 * not only is a logical root moved, but it brings with it content from
	 * another sub-unit also.
	 */
	public void test_moveRootToNewLogicalResource() {
		String rootFrag = logres.getURIFragment(root);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		ILogicalResource logres2 = createNewLogicalResource(
			URI.createPlatformResourceURI(RESOURCE_NAME2));
		Library library2 = EXTLibraryFactory.eINSTANCE.createLibrary();
		logres2.getContents().add(library2);
		library2.setName("Library 2"); //$NON-NLS-1$
		
		String root2Frag = logres2.getURIFragment(library2);
		
		try {
			logres.separate(root, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME3));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		saveLogicalResource(logres2);
		
		// discard the resources and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		logres2 = createNewLogicalResource(
			URI.createPlatformResourceURI(RESOURCE_NAME2));
		loadLogicalResource(logres2);
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		library2 = lookupLibrary(logres2, root2Frag);
		assertNotNull(library2);
		
		assertEquals(2, logres.getMappedResources().size());
		assertEquals(1, logres2.getMappedResources().size());
		
		library2.getBranches().add(root); // inter-resource move
		rootFrag = logres2.getURIFragment(root);  // in case of re-guid
		subunit3Frag = logres2.getURIFragment(subunit3);  // in case of re-guid
		
		assertEquals(1, logres.getMappedResources().size());
		assertEquals(1, logres2.getMappedResources().size()); // not a sub-unit here
		
		saveLogicalResource();
		saveLogicalResource(logres2);
		
		// discard the resources and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		logres2 = createNewLogicalResource(
			URI.createPlatformResourceURI(RESOURCE_NAME2));
		loadLogicalResource(logres2);
		
		root = lookupLibrary(rootFrag);
		assertNull(root);
		root = lookupLibrary(logres2, rootFrag);
		assertNotNull(root);  // found it in the other resource this time
		subunit3 = lookupLibrary(logres2, subunit3Frag);
		assertNotNull(subunit3);  // this sub-unit content followed the root!
		library2 = lookupLibrary(logres2, root2Frag);
		assertNotNull(library2);
		
		// correctly restored the parent relationship
		assertSame(library2, root.getParentBranch());
	}
}
