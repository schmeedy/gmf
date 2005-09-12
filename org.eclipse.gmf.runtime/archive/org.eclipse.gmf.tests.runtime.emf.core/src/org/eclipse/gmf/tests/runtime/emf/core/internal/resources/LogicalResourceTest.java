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

import java.io.IOException;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.examples.library.Book;
import org.eclipse.emf.examples.library.Library;
import org.eclipse.emf.examples.library.RMPLibraryFactory;
import org.eclipse.emf.examples.library.RMPLibraryPackage;
import org.eclipse.emf.examples.library.Writer;
import org.eclipse.gmf.runtime.emf.core.EventTypes;
import org.eclipse.gmf.runtime.emf.core.edit.DemuxedMListener;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MFilter;
import org.eclipse.gmf.runtime.emf.core.edit.MResourceOption;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.internal.resourcemap.ResourceMap;
import org.eclipse.gmf.runtime.emf.core.resources.CannotAbsorbException;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResource;


/**
 * Tests the {@link org.eclipse.gmf.runtime.emf.core.internal.resources.LogicalResource}
 * class and its load/save/handler/helper/util cohorts.
 *
 * @author Christian W. Damus (cdamus)
 */
public class LogicalResourceTest
	extends BaseLogicalResourceTest {
	
	public LogicalResourceTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new TestSuite(LogicalResourceTest.class, "Basic Tests"); //$NON-NLS-1$
	}
	
	DemuxedMListener aListener;

	/**
	 * Tests the getMappedResources() method.
	 */
	public void test_getMappedResources() {
		Map resources = logres.getMappedResources();
		assertEquals(1, resources.size());
		
		Resource nested = (Resource) resources.get(root);
		assertNotNull(nested);
		assertEquals(logres.getURI(), nested.getURI());
		
		nested = (ILogicalResource) resources.get(subunit1);
		assertNull(nested);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		nested = (Resource) resources.get(subunit1);
		assertNotNull(nested);
		
		try {
			logres.absorb(subunit1);
		} catch (CannotAbsorbException e) {
			fail("Should not fail to absorb: " + e); //$NON-NLS-1$
		}
		nested = (Resource) resources.get(subunit1);
		assertNull(nested);
		
		assertEquals(1, resources.size());
	}

	/**
	 * Specifically tests the representation of the logical resource's roots
	 * in the getMappedResources() map.
	 */
	public void test_getMappedResources_logicalRoots() {
		Map resources = logres.getMappedResources();
		assertEquals(1, resources.size());
		
		Resource unit = (Resource) resources.get(root);
		assertNotNull(unit);
		assertEquals(logres.getURI(), unit.getURI());
		
		// test adding another root
		Library otherRoot = RMPLibraryFactory.eINSTANCE.createLibrary();
		logres.getContents().add(otherRoot);
		
		assertEquals(2, resources.size());
		unit = (Resource) resources.get(otherRoot);
		assertNotNull(unit);
		assertEquals(logres.getURI(), unit.getURI());
		
		// test replacing a root using the set() method
		Library yetAnotherRoot = RMPLibraryFactory.eINSTANCE.createLibrary();
		logres.getContents().set(1, yetAnotherRoot);
		
		assertEquals(2, resources.size());
		unit = (Resource) resources.get(otherRoot);
		assertNull(unit);  // the old root is no longer mapped to a unit
		unit = (Resource) resources.get(yetAnotherRoot);
		assertNotNull(unit);  // the new one is
		assertEquals(logres.getURI(), unit.getURI());
		
		// test removing a root
		logres.getContents().remove(yetAnotherRoot);
		
		assertEquals(1, resources.size());
		unit = (Resource) resources.get(yetAnotherRoot);
		assertNull(unit);  // the old root is no longer mapped to a unit
	}
	
	/**
	 * Tests the URIs of sub-units and objects in the logical resource.
	 */
	public void test_uris() {
		String subunitFrag = logres.getURIFragment(subunit1);
		URI subunitUri = EcoreUtil.getURI(subunit1);
		
		URI nestedUri = URI.createPlatformResourceURI(SUBUNIT_NAME1);
		
		try {
			logres.separate(subunit1, nestedUri);
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Resource nested = (Resource) logres.getMappedResources().get(
			subunit1);
		assertNotNull(nested);
		assertEquals(nestedUri, nested.getURI());
		
		assertEquals(subunitFrag, logres.getURIFragment(subunit1));
		assertEquals(subunitUri, EcoreUtil.getURI(subunit1));
	}
	
	/**
	 * Tests a monolithic resource (no sub-units).
	 */
	public void test_saveLoad_monolith() {
		String rootFrag = logres.getURIFragment(root);
		String subunitFrag = logres.getURIFragment(subunit1);
		
		saveLogicalResource();
		
		assertFalse(logres.isModified());
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		assertFalse(logres.isModified());
		
		Map resources = logres.getMappedResources();
		assertEquals(1, resources.size());
		
		assertEquals(1, logres.getContents().size());
		assertFalse(logres.getContents().get(0) instanceof ResourceMap);
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		
		// in monolithic case, there should be no resource map.  Should look
		//    just like any old XMI resource
		assertFalse(
			((Resource) resources.get(root)).getContents().get(0) instanceof ResourceMap);
		
		subunit1 = lookupLibrary(subunitFrag);
		assertNotNull(subunit1);
		assertTrue(EcoreUtil.isAncestor(root, subunit1));
	}
	
	/**
	 * Tests single-level subunit nesting.
	 */
	public void test_saveLoad_singleNesting() {
		String rootFrag = logres.getURIFragment(root);
		String subunitFrag = logres.getURIFragment(subunit1);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		assertTrue(logres.isModified());
		
		saveLogicalResource();
		
		assertFalse(logres.isModified());
		
		IFile subunitFile = project.getParent().getFile(new Path(SUBUNIT_NAME1));
		assertNotNull(subunitFile);
		assertTrue(subunitFile.exists());
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		assertFalse(logres.isModified());
		
		Map resources = logres.getMappedResources();
		assertEquals(2, resources.size());
		
		assertEquals(1, logres.getContents().size());
		assertFalse(logres.getContents().get(0) instanceof ResourceMap);
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		
		subunit1 = lookupLibrary(subunitFrag);
		assertNotNull(subunit1);
		assertTrue(EcoreUtil.isAncestor(root, subunit1));
	}
	
	/**
	 * Tests multi-level subunit nesting.
	 */
	public void test_saveLoad_multiNesting() {
		String rootFrag = logres.getURIFragment(root);
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit2Frag = logres.getURIFragment(subunit2);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		assertTrue(logres.isModified());
		
		saveLogicalResource();
		
		assertFalse(logres.isModified());
		
		IFile subunitFile = project.getParent().getFile(new Path(SUBUNIT_NAME1));
		assertNotNull(subunitFile);
		assertTrue(subunitFile.exists());
		
		subunitFile = project.getParent().getFile(new Path(SUBUNIT_NAME2));
		assertNotNull(subunitFile);
		assertTrue(subunitFile.exists());
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		assertFalse(logres.isModified());
		
		Map resources = logres.getMappedResources();
		assertEquals(3, resources.size());
		
		assertEquals(1, logres.getContents().size());
		assertFalse(logres.getContents().get(0) instanceof ResourceMap);
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit2 = lookupLibrary(subunit2Frag);
		assertNotNull(subunit2);
		assertTrue(EcoreUtil.isAncestor(root, subunit2));
		assertTrue(EcoreUtil.isAncestor(subunit1, subunit2));
	}
	
	/**
	 * Tests multi-level subunit nesting in which two different sub-units
	 * are stored in the same physical resource.
	 */
	public void test_saveLoad_multiNestingSameUnit() {
		String rootFrag = logres.getURIFragment(root);
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit2Frag = logres.getURIFragment(subunit2);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME2)); // same file
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		assertTrue(logres.isModified());
		
		saveLogicalResource();
		
		assertFalse(logres.isModified());
		
		IFile subunitFile = project.getParent().getFile(new Path(SUBUNIT_NAME1));
		assertNotNull(subunitFile);
		assertTrue(subunitFile.exists());
		
		subunitFile = project.getParent().getFile(new Path(SUBUNIT_NAME2));
		assertNotNull(subunitFile);
		assertTrue(subunitFile.exists());
		
		try {
			// the sub-unit file should only have been written to once, to write
			//   both roots
			IFileState[] history = subunitFile.getHistory(null);
			assertNotNull(history);
			assertEquals(0, history.length);  // file was only written once
		} catch (CoreException e) {
			fail("Failed to get file history: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		Map resources = logres.getMappedResources();
		assertEquals(4, resources.size());
		
		assertEquals(1, logres.getContents().size());
		assertFalse(logres.getContents().get(0) instanceof ResourceMap);
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit2 = lookupLibrary(subunit2Frag);
		assertNotNull(subunit2);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		assertTrue(EcoreUtil.isAncestor(subunit1, subunit2));
		assertTrue(EcoreUtil.isAncestor(root, subunit3));
	}
	
	/**
	 * Tests logical roots as sub-units.
	 */
	public void test_saveLoad_separateLogicalRoot() {
		String rootFrag = logres.getURIFragment(root);
		
		try {
			logres.separate(root, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		assertTrue(logres.isModified());
		
		saveLogicalResource();
		
		assertFalse(logres.isModified());
		
		IFile subunitFile = project.getParent().getFile(new Path(SUBUNIT_NAME1));
		assertNotNull(subunitFile);
		assertTrue(subunitFile.exists());
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		assertFalse(logres.isModified());
		
		Map resources = logres.getMappedResources();
		assertEquals(1, resources.size());  // nothing maps to the physical root resource
		
		assertEquals(1, logres.getContents().size());
		assertFalse(logres.getContents().get(0) instanceof ResourceMap);
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		assertTrue(logres.isSeparate(root));
		assertNull(root.eContainer());
		assertTrue(logres.getContents().contains(root));
		
		// absorb the root again
		try {
			logres.absorb(root);
		} catch (CannotAbsorbException e) {
			fail("Should not fail to absorb: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		assertFalse(logres.isSeparate(root));
		assertNull(root.eContainer());
		assertTrue(logres.getContents().contains(root));
	}
	
	/**
	 * Tests multiple sub-units under the same parent, to verify that they are
	 * loaded again in the correct order.  This method uses the
	 * <tt>subunit<i>n</i></tt> fields to store different elements than the
	 * other tests.
	 */
	public void test_multipleSubunitsOfSameParent() {
		// create a third child of the root element
		subunit3 = RMPLibraryFactory.eINSTANCE.createLibrary();
		root.getBranches().add(subunit3);
		subunit3.setName("level1-2"); //$NON-NLS-1$
		
		subunit1 = (Library) root.getBranches().get(0);
		subunit2 = (Library) root.getBranches().get(1);
		subunit3 = (Library) root.getBranches().get(2);
		
		String rootFrag = logres.getURIFragment(root);
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit2Frag = logres.getURIFragment(subunit2);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		assertTrue(logres.isModified());
		
		saveLogicalResource();
		
		assertFalse(logres.isModified());
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit2 = lookupLibrary(subunit2Frag);
		assertNotNull(subunit2);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		
		// verify the ordering of the sub-units
		assertEquals(0, root.getBranches().indexOf(subunit1));
		assertEquals(1, root.getBranches().indexOf(subunit2));
		assertEquals(2, root.getBranches().indexOf(subunit3));
	}
	
	/**
	 * Tests absorption of an internal (middle) unit in multi-level subunit
	 * nesting.
	 */
	public void test_absorb_internal_multiNesting() {
		String rootFrag = logres.getURIFragment(root);
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit2Frag = logres.getURIFragment(subunit2);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// get the file modification stamps for later comparison
		IFile file = project.getParent().getFile(new Path(RESOURCE_NAME));
		assertNotNull(file);
		long rootStamp = file.getModificationStamp();
		file = project.getParent().getFile(new Path(SUBUNIT_NAME1));
		assertNotNull(file);
		long subunit1Stamp = file.getModificationStamp();
		file = project.getParent().getFile(new Path(SUBUNIT_NAME2));
		assertNotNull(file);
		long subunit2Stamp = file.getModificationStamp();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		Map resources = logres.getMappedResources();
		assertEquals(3, resources.size());
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit2 = lookupLibrary(subunit2Frag);
		assertNotNull(subunit2);
		assertTrue(EcoreUtil.isAncestor(root, subunit2));
		assertTrue(EcoreUtil.isAncestor(subunit1, subunit2));
		
		assertTrue(logres.isSeparate(subunit1));
		
		try {
			logres.absorb(subunit1);
		} catch (CannotAbsorbException e) {
			fail("Should not fail to absorb: " + e); //$NON-NLS-1$
		}
		
		assertFalse(logres.isSeparate(subunit1));
		assertEquals(2, resources.size());
		
		saveLogicalResource();
		
		// all files needed to be rewritten by the save, because the subunit2
		//   was updated to reference the root unit as its parent when subunit1
		//   was absorbed
		file = project.getParent().getFile(new Path(RESOURCE_NAME));
		assertNotNull(file);
		assertFalse(rootStamp == file.getModificationStamp());
		file = project.getParent().getFile(new Path(SUBUNIT_NAME1));
		assertNotNull(file);
		assertFalse(subunit1Stamp == file.getModificationStamp());
		file = project.getParent().getFile(new Path(SUBUNIT_NAME2));
		assertNotNull(file);
		assertFalse(subunit2Stamp == file.getModificationStamp());
	}
	
	/**
	 * Tests absorption of a leaf unit in multi-level subunit nesting.
	 */
	public void test_absorb_leaf_multiNesting() {
		String rootFrag = logres.getURIFragment(root);
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit2Frag = logres.getURIFragment(subunit2);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// get the file modification stamps for later comparison
		IFile file = project.getParent().getFile(new Path(RESOURCE_NAME));
		assertNotNull(file);
		long rootStamp = file.getModificationStamp();
		file = project.getParent().getFile(new Path(SUBUNIT_NAME1));
		assertNotNull(file);
		long subunit1Stamp = file.getModificationStamp();
		file = project.getParent().getFile(new Path(SUBUNIT_NAME2));
		assertNotNull(file);
		long subunit2Stamp = file.getModificationStamp();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		Map resources = logres.getMappedResources();
		assertEquals(3, resources.size());
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit2 = lookupLibrary(subunit2Frag);
		assertNotNull(subunit2);
		assertTrue(EcoreUtil.isAncestor(root, subunit2));
		assertTrue(EcoreUtil.isAncestor(subunit1, subunit2));
		
		assertTrue(logres.isSeparate(subunit2));
		
		try {
			logres.absorb(subunit2);
		} catch (CannotAbsorbException e) {
			fail("Should not fail to absorb: " + e); //$NON-NLS-1$
		}
		
		assertFalse(logres.isSeparate(subunit2));
		assertEquals(2, resources.size());
		
		saveLogicalResource();
		
		// the subunit1 and subunit2 files should have been written by the
		//   second save, but not the root file
		file = project.getParent().getFile(new Path(RESOURCE_NAME));
		assertNotNull(file);
		assertEquals(rootStamp, file.getModificationStamp());
		file = project.getParent().getFile(new Path(SUBUNIT_NAME1));
		assertNotNull(file);
		assertFalse(subunit1Stamp == file.getModificationStamp());
		file = project.getParent().getFile(new Path(SUBUNIT_NAME2));
		assertNotNull(file);
		assertFalse(subunit2Stamp == file.getModificationStamp());
	}
	
	/**
	 * Test saving a logical resource with sub-units under a new URI.
	 */
	public void test_saveAs() {
		String rootFrag = logres.getURIFragment(root);
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit2Frag = logres.getURIFragment(subunit2);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		assertTrue(logres.isModified());
		
		saveLogicalResource();
		
		assertFalse(logres.isModified());
		
		// record the modification stamps of the various units for later
		//   comparison
		IFile file = project.getParent().getFile(new Path(RESOURCE_NAME));
		assertNotNull(file);
		long rootStamp = file.getModificationStamp();
		
		file = project.getParent().getFile(new Path(SUBUNIT_NAME1));
		assertNotNull(file);
		long subunit1Stamp = file.getModificationStamp();
		
		file = project.getParent().getFile(new Path(SUBUNIT_NAME2));
		assertNotNull(file);
		long subunit2Stamp = file.getModificationStamp();
		
		Map resources = logres.getMappedResources();
		assertEquals(3, resources.size());
		
		String newName = "/" + PROJECT_NAME + "/newuri.rmplibrary"; //$NON-NLS-1$ //$NON-NLS-2$
		logres.setURI(URI.createPlatformResourceURI(newName));
		
		// reduced to a single physical resource
		assertEquals(1, resources.size());
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(newName);
		
		loadLogicalResource();
		resources = logres.getMappedResources();
		assertEquals(1, resources.size());
		
		// verify the structure of the file (that nothing is missing)
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit2 = lookupLibrary(subunit2Frag);
		assertNotNull(subunit2);
		assertTrue(EcoreUtil.isAncestor(root, subunit2));
		assertTrue(EcoreUtil.isAncestor(subunit1, subunit2));
		
		// load the former URI again
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		// the old one still has three units
		resources = logres.getMappedResources();
		assertEquals(3, resources.size());
		
		// verify the structure of the file (that nothing is missing)
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit2 = lookupLibrary(subunit2Frag);
		assertNotNull(subunit2);
		assertTrue(EcoreUtil.isAncestor(root, subunit2));
		assertTrue(EcoreUtil.isAncestor(subunit1, subunit2));
		
		// check the modification stamps to verify that the old units are
		//   not changed
		file = project.getParent().getFile(new Path(RESOURCE_NAME));
		assertNotNull(file);
		assertEquals(rootStamp, file.getModificationStamp());
		
		file = project.getParent().getFile(new Path(SUBUNIT_NAME1));
		assertNotNull(file);
		assertEquals(subunit1Stamp, file.getModificationStamp());
		
		file = project.getParent().getFile(new Path(SUBUNIT_NAME2));
		assertNotNull(file);
		assertEquals(subunit2Stamp, file.getModificationStamp());
	}
	
	/** Tests undo/redo of unit separation and absorption. */
	public void test_undoRedo() {
		Map resources = logres.getMappedResources();
		assertEquals(1, resources.size());
		
		Resource nested = (Resource) resources.get(root);
		assertNotNull(nested);
		assertEquals(logres.getURI(), nested.getURI());
		
		nested = (Resource) resources.get(subunit1);
		assertNull(nested);
		
		stopWriting();
		startWriting();
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		nested = (Resource) resources.get(subunit1);
		assertNotNull(nested);
		
		MUndoInterval undo = stopWriting();
		
		// test undo of separation
		undo.undo();
		
		startReading();
		
		nested = (Resource) resources.get(subunit1);
		assertNull(nested);
		assertFalse(logres.isSeparate(subunit1));
		
		stopReading();
		
		// test redo of separation
		undo.redo();
		
		startReading();
		
		nested = (Resource) resources.get(subunit1);
		assertNotNull(nested);
		assertTrue(logres.isSeparate(subunit1));
		
		stopReading();
		
		startWriting();
		
		try {
			logres.absorb(subunit1);
		} catch (CannotAbsorbException e) {
			fail("Should not fail to absorb: " + e); //$NON-NLS-1$
		}
		nested = (Resource) resources.get(subunit1);
		assertNull(nested);
		
		undo = stopWriting();
		
		// test undo of absorption
		undo.undo();
		
		startReading();
		
		nested = (Resource) resources.get(subunit1);
		assertNotNull(nested);
		assertTrue(logres.isSeparate(subunit1));
		
		stopReading();
		
		// test undo of absorption
		undo.redo();
		
		startReading();
		
		nested = (Resource) resources.get(subunit1);
		assertNull(nested);
		assertFalse(logres.isSeparate(subunit1));
		
		stopReading();
	}
	
	/**
	 * Tests that the dirty states of units are correctly updated when
	 * separating an element.
	 */
	public void test_dirtyState_separate() {
		saveLogicalResource();
		
		Resource rootRes = (Resource) logres.getMappedResources().get(root);
		
		assertFalse(logres.isModified());
		assertFalse(rootRes.isModified());
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Resource sub1Res = (Resource) logres.getMappedResources().get(subunit1);
		
		assertTrue(logres.isModified());
		assertTrue(rootRes.isModified());
		assertTrue(sub1Res.isModified());
		
		saveLogicalResource();
		
		assertFalse(logres.isModified());
		assertFalse(rootRes.isModified());
		assertFalse(sub1Res.isModified());
	}
	
	/**
	 * Tests that the dirty states of units are correctly updated when
	 * absorbing an element.
	 */
	public void test_dirtyState_absorb() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		Resource rootRes = (Resource) logres.getMappedResources().get(root);
		Resource sub1Res = (Resource) logres.getMappedResources().get(subunit1);
		
		assertFalse(logres.isModified());
		assertFalse(rootRes.isModified());
		assertFalse(sub1Res.isModified());
		
		try {
			logres.absorb(subunit1);
		} catch (CannotAbsorbException e) {
			fail("Should not fail to absorb: " + e); //$NON-NLS-1$
		}
		
		assertTrue(logres.isModified());
		assertTrue(rootRes.isModified());
		assertTrue(sub1Res.isModified());
		
		saveLogicalResource();
		
		assertFalse(logres.isModified());
		assertFalse(rootRes.isModified());
		assertFalse(sub1Res.isModified());
	}
	
	/**
	 * Tests that the dirty states of units are correctly updated when
	 * modifying an element.
	 */
	public void test_dirtyState_modify() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		Resource rootRes = (Resource) logres.getMappedResources().get(root);
		Resource sub1Res = (Resource) logres.getMappedResources().get(subunit1);
		
		assertFalse(logres.isModified());
		assertFalse(rootRes.isModified());
		assertFalse(sub1Res.isModified());
		
		subunit1.setAddress("500 Pennsylvania Ave."); //$NON-NLS-1$
		
		assertTrue(logres.isModified());
		assertFalse(rootRes.isModified());  // didn't update the root unit
		assertTrue(sub1Res.isModified());
		
		saveLogicalResource();
		
		assertFalse(logres.isModified());
		assertFalse(rootRes.isModified());
		assertFalse(sub1Res.isModified());
		
		root.setAddress("10 Downing St."); //$NON-NLS-1$
		
		assertTrue(logres.isModified());
		assertTrue(rootRes.isModified());
		assertFalse(sub1Res.isModified());  // didn't update the sub-unit
	}
	
	/** Tests the EMF-style listening for notifications from adapters. */
	public void test_basicEventing() {
		class TestAdapter extends AdapterImpl {
			List notifications = new java.util.ArrayList();
			
			public void notifyChanged(Notification msg) {
				if (!(msg.getNotifier() instanceof Resource) ||
						(msg.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS)) {
					// don't want the touch events for resource isModified
					notifications.add(msg);
				}
			}
		}
		
		TestAdapter adapter = new TestAdapter();
		
		URI logicalUri = logres.getURI();
		URI subunitUri = URI.createPlatformResourceURI(SUBUNIT_NAME1);
		
		// listen for notifications of separation/absorption on the parent
		EObject parent = subunit1.eContainer();
		parent.eAdapters().add(adapter);
		
		try {
			logres.separate(subunit1, subunitUri);
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		assertEquals(1, adapter.notifications.size());
		Notification event = (Notification) adapter.notifications.get(0);
		adapter.notifications.clear();
		
		assertSame(parent, event.getNotifier());
		assertEquals(EventTypes.SEPARATE, event.getEventType());
		assertSame(RMPLibraryPackage.eINSTANCE.getLibrary_Branches(), event.getFeature());
		assertEquals(logicalUri, ((Resource) event.getOldValue()).getURI());
		assertEquals(subunitUri, ((Resource) event.getNewValue()).getURI());
		
		try {
			logres.absorb(subunit1);
		} catch (CannotAbsorbException e) {
			fail("Should not fail to absorb: " + e); //$NON-NLS-1$
		}
		
		assertEquals(1, adapter.notifications.size());
		event = (Notification) adapter.notifications.get(0);
		adapter.notifications.clear();
		
		assertSame(parent, event.getNotifier());
		assertEquals(EventTypes.ABSORB, event.getEventType());
		assertSame(RMPLibraryPackage.eINSTANCE.getLibrary_Branches(), event.getFeature());
		assertEquals(subunitUri, ((Resource) event.getOldValue()).getURI());
		assertEquals(logicalUri, ((Resource) event.getNewValue()).getURI());
		
		logres.eAdapters().add(adapter);
		
		try {
			logres.separate(root, subunitUri); // logical root
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		assertEquals(1, adapter.notifications.size());
		event = (Notification) adapter.notifications.get(0);
		adapter.notifications.clear();
		
		assertSame(logres, event.getNotifier());
		assertEquals(EventTypes.SEPARATE, event.getEventType());
		assertSame(null, event.getFeature());
		assertEquals(Resource.RESOURCE__CONTENTS, event.getFeatureID(Resource.class));
		assertEquals(logicalUri, ((Resource) event.getOldValue()).getURI());
		assertEquals(subunitUri, ((Resource) event.getNewValue()).getURI());
		
		try {
			logres.absorb(root);  // logical root
		} catch (CannotAbsorbException e) {
			fail("Should not fail to absorb: " + e); //$NON-NLS-1$
		}
		
		assertEquals(1, adapter.notifications.size());
		event = (Notification) adapter.notifications.get(0);
		adapter.notifications.clear();
		
		assertSame(logres, event.getNotifier());
		assertEquals(EventTypes.ABSORB, event.getEventType());
		assertSame(null, event.getFeature());
		assertEquals(Resource.RESOURCE__CONTENTS, event.getFeatureID(Resource.class));
		assertEquals(subunitUri, ((Resource) event.getOldValue()).getURI());
		assertEquals(logicalUri, ((Resource) event.getNewValue()).getURI());
	}
	
	/** Tests the MSL-style listening for notifications from MListeners. */
	public void test_mslEventing() {
		class TestListener extends DemuxedMListener {
			Notification event;
			ILogicalResource res;
			EObject element;
			Resource subunit;
			
			TestListener() {
				super(domain);
			}
			
			public MFilter getFilter() {
				return MFilter.SEPARATED_ABSORBED_FILTER;
			}
			
			public void handleElementSeparatedEvent(
					Notification notification, ILogicalResource resource,
					EObject eObject, Resource newResource) {
				event = notification;
				res = resource;
				element = eObject;
				subunit = newResource;
			}
			
			public void handleElementAbsorbedEvent(
					Notification notification, ILogicalResource resource,
					EObject eObject, Resource oldResource) {
				event = notification;
				res = resource;
				element = eObject;
				subunit = oldResource;
			}
		}
		
		TestListener listener = new TestListener();
		listener.startListening();
		
		URI subunitUri = URI.createPlatformResourceURI(SUBUNIT_NAME1);
		
		try {
			logres.separate(subunit1, subunitUri);
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		// must complete the write action in order for events to fire
		stopWriting();
		
		assertSame(subunit1, listener.element);
		assertEquals(EventTypes.SEPARATE, listener.event.getEventType());
		assertSame(logres, listener.res);
		assertEquals(subunitUri, listener.subunit.getURI());
		
		startWriting();
		
		try {
			logres.absorb(subunit1);
		} catch (CannotAbsorbException e) {
			fail("Should not fail to absorb: " + e); //$NON-NLS-1$
		}
		
		// must complete the write action in order for events to fire
		stopWriting();
		
		assertSame(subunit1, listener.element);
		assertEquals(EventTypes.ABSORB, listener.event.getEventType());
		assertSame(logres, listener.res);
		assertEquals(subunitUri, listener.subunit.getURI());
		
		try {
			logres.separate(root, subunitUri);  // logical root
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		// must complete the write action in order for events to fire
		stopWriting();
		
		assertSame(root, listener.element);
		assertEquals(EventTypes.SEPARATE, listener.event.getEventType());
		assertSame(logres, listener.res);
		assertEquals(subunitUri, listener.subunit.getURI());
		
		startWriting();
		
		try {
			logres.absorb(root);  // logical root
		} catch (CannotAbsorbException e) {
			fail("Should not fail to absorb: " + e); //$NON-NLS-1$
		}
		
		// must complete the write action in order for events to fire
		stopWriting();
		
		assertSame(root, listener.element);
		assertEquals(EventTypes.ABSORB, listener.event.getEventType());
		assertSame(logres, listener.res);
		assertEquals(subunitUri, listener.subunit.getURI());
	}
	
	/**
	 * Tests that we can correctly load models from the 6.x physical structure.
	 */
	public void test_compatibility_6_0() {
		URI uri = URI.createURI(MslCoreTestsBundle.getEntry(
				"/test_models/test_6_0_format.rmplibrary").toString()); //$NON-NLS-1$
		logres = createNewLogicalResource(uri);
		loadLogicalResource();
		
		assertTrue(logres.isLoaded());
		assertEquals(1, ((ResourceSet) logres.getAdapter(ResourceSet.class)).getResources().size());
		
		assertEquals(1, logres.getContents().size());
		Library library = (Library) logres.getContents().get(0);
		
		assertEquals("Library of Congress", library.getName()); //$NON-NLS-1$
		
		assertEquals(1, library.getWriters().size());
		Writer writer = (Writer) library.getWriters().get(0);
		assertEquals("Master Librarian", writer.getName()); //$NON-NLS-1$
		
		assertEquals(1, library.getBooks().size());
		Book book = (Book) library.getBooks().get(0);
		assertEquals("All Books In Existence", book.getTitle()); //$NON-NLS-1$
		
		assertSame(writer, book.getAuthor());
		assertEquals(1, writer.getBooks().size());
		assertSame(book, writer.getBooks().get(0));
	}
	
	/**
	 * Tests that we properly propagate errors from the unit to the logical
	 *  resource.
	 */
	public void test_load_errors_RATLC00537775() {
		URI uri = URI.createURI(MslCoreTestsBundle.getEntry(
				"/test_models/test_corrupted_model.rmplibrary").toString()); //$NON-NLS-1$
		logres = createNewLogicalResource(uri);

		Map options = new java.util.HashMap();
		
		options.put(ILogicalResource.OPTION_LOAD_ALL_UNITS,
			Boolean.TRUE);
		options.put(ILogicalResource.OPTION_AUTO_LOAD_UNITS,
			Boolean.TRUE);
		
		try {
			logres.load(options);
		} catch (IOException e) {
			// Check to ensure that the resource is loaded but it has
			//  errors.
			assertTrue(logres.isLoaded());
			assertTrue(!logres.getErrors().isEmpty());
			return;
		}
		
		fail("Logical resource load should have failed because of corrupted XML."); //$NON-NLS-1$
	}
	
	/**
	 * Tests that the DemuxingMListener will not report a model unloaded when
	 *  the errors list of a resource is emptied.
	 */
	public void test_load_errors_RATLC00537693() {
		final MEditingDomain editingDomain = MEditingDomain.createNewDomain();
		URI uri = URI.createURI(MslCoreTestsBundle.getEntry(
				"/test_models/test_corrupted_model.rmplibrary").toString()); //$NON-NLS-1$
		
		final Resource[] r = new Resource[1];
		
		try {
			r[0] = editingDomain.createResource(uri.toString(),MResourceOption.URI);
			r[0].unload();
		} catch (Exception e) {
			// Do nothing, just proceed.
		}
		
		Map options = new java.util.HashMap();
		
		options.put(ILogicalResource.OPTION_LOAD_ALL_UNITS,
			Boolean.TRUE);
		options.put(ILogicalResource.OPTION_AUTO_LOAD_UNITS,
			Boolean.TRUE);
		
		try {
			r[0].load(options);
			// We shouldn't get this far.
			fail();
		} catch (IOException e) {
			// Do nothing.
		}
		
		// Make sure that there are errors to be cleared from the resource.
		assertTrue(r[0].getErrors().size() > 0);
		
		final int[] listenerCalled = new int[1];
		listenerCalled[0] = 0;
		
		aListener = new DemuxedMListener(editingDomain) {
			public void handleResourceUnloadedEvent(Notification notification, Resource resource, EObject modelRoot) {
				listenerCalled[0]++;
			}
		};
		aListener.startListening();
		
		// Clear all of the errors from the resource in a proper write action.
		editingDomain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					editingDomain.runAsWrite(new MRunnable() {
						public Object run() {
							r[0].getErrors().clear();
							return null;
						}
					});
				} catch (MSLActionAbandonedException e) {
					fail();
				}
			}
		});
		
		assertEquals(0,listenerCalled[0]);
	}
}
