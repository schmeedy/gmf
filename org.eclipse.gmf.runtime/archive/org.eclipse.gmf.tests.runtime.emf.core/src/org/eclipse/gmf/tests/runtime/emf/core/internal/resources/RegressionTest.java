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

import java.util.Collections;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.gmf.runtime.emf.core.internal.resourcemap.ResourceMap;
import org.eclipse.gmf.runtime.emf.core.internal.resources.AbstractResourceWrapper;
import org.eclipse.gmf.runtime.emf.core.resources.CannotAbsorbException;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;


/**
 * Tests for regressions in RATLC defects.
 *
 * @author Christian W. Damus (cdamus)
 */
public class RegressionTest
	extends BaseLogicalResourceTest {
	
	public RegressionTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new TestSuite(RegressionTest.class, "Regression Tests"); //$NON-NLS-1$
	}

	/**
	 * Tests that moving a logical root into another container does not get
	 * it moved back into the resource contents.
	 */
	public void test_reparentLogicalRoot_RATLC00537428() {
		Library other = EXTLibraryFactory.eINSTANCE.createLibrary();
		logres.getContents().add(other);
		
		root.getBranches().add(other);
		
		assertSame(root, other.eContainer());
		assertSame(
			EXTLibraryPackage.eINSTANCE.getLibrary_Branches(),
			other.eContainmentFeature());
		assertEquals(Collections.singletonList(root), logres.getContents());
	}
	
	/**
	 * Tests that units correctly transfer ownership of their sub-units to
	 * other units when intervening elements in the containment tree are
	 * separated.
	 */
	public void test_unitsTransferSubunitOwnership_separate_RATLC00538037() {
		try {
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
			
			// now, separating parent is supposed to cause it to get subunit2
			//    as a child instead of the root unit owning it
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Resource unit = (Resource) logres.getMappedResources().get(root);
		
		// unwrap so that we don't get a view that hides the resource map
		unit = AbstractResourceWrapper.unwrap(unit);
		ResourceMap resmap = (ResourceMap) unit.getContents().get(0);
		
		assertEquals(
			"Root resource has wrong number of child units", //$NON-NLS-1$
			1,
			resmap.getParentEntries().size());
		
		// this unit must also have a single child unit
		unit = (Resource) logres.getMappedResources().get(subunit1);
		unit = AbstractResourceWrapper.unwrap(unit);
		ResourceMap resmap2 = (ResourceMap) unit.getContents().get(0);
		
		assertEquals(
			"Root resource has wrong number of child units", //$NON-NLS-1$
			1,
			resmap2.getParentEntries().size());
		
		// the leaf unit must have middle unit as its parent
		unit = (Resource) logres.getMappedResources().get(subunit2);
		unit = AbstractResourceWrapper.unwrap(unit);
		ResourceMap resmap3 = (ResourceMap) unit.getContents().get(0);
		
		assertSame(
			"Subunit has wrong parent", //$NON-NLS-1$
			resmap2,
			resmap3.getChildEntry(subunit2).getParentMap());
	}
	
	/**
	 * Tests that units correctly transfer ownership of their sub-units to
	 * other units when intervening elements in the containment tree are
	 * absorbed.
	 */
	public void test_unitsTransferSubunitOwnership_absorb_RATLC00538037() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
			
			// take out the middle unit.  subunit2's parent should now be the
			//    root
			logres.absorb(subunit1);
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		} catch (CannotAbsorbException e) {
			fail("Should not fail to absorb: " + e); //$NON-NLS-1$
		}
		
		Resource unit = (Resource) logres.getMappedResources().get(root);
		
		// unwrap so that we don't get a view that hides the resource map
		unit = AbstractResourceWrapper.unwrap(unit);
		ResourceMap resmap = (ResourceMap) unit.getContents().get(0);
		
		assertEquals(
			"Root resource has wrong number of child units", //$NON-NLS-1$
			1,
			resmap.getParentEntries().size());
		
		// the remaining sub-unit must reference the root unit as parent
		unit = (Resource) logres.getMappedResources().get(subunit2);
		unit = AbstractResourceWrapper.unwrap(unit);
		ResourceMap resmap2 = (ResourceMap) unit.getContents().get(0);
		
		assertSame(
			"Subunit has wrong parent", //$NON-NLS-1$
			resmap,
			resmap2.getChildEntry(subunit2).getParentMap());
	}

	/**
	 * Tests that after moving a logical root into another container and then
	 * back to being a logical root, the logical root is properly saved.
	 */
	public void test_rerootLogicalRoot_RATLC00538194() {
		Library library = EXTLibraryFactory.eINSTANCE.createLibrary();

		// add the library to the logical resource
		logres.getContents().add(library);
		
		// logical resource should contain library
		assertEquals(2, logres.getContents().size());
		assertSame(logres.getContents().get(1), library);
		
		// remove the library from the logical resource
		logres.getContents().remove(library);

		assertFalse(logres.getMappedResources().containsKey(library));

		// move the library into the branches containment list of another library
		root.getBranches().add(library);
		
		// library should not be part of the resource map because it was added
		// to the list of branches in the root
		assertFalse(logres.getMappedResources().containsKey(library));
		
		// move the library back to being a root
		logres.getContents().add(library);
		
		// logical resource should contain library
		assertEquals(2, logres.getContents().size());
		assertSame(logres.getContents().get(1), library);

		// store the library ID for comparison later
		String libraryID = EObjectUtil.getID(library);

		saveLogicalResource();
		
		assertFalse(logres.isModified());
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		// logical resource unit should contain library after reloading
		assertEquals(2, logres.getContents().size());
		assertEquals(libraryID, EObjectUtil.getID((EObject)logres.getContents().get(1)));
	}
}
