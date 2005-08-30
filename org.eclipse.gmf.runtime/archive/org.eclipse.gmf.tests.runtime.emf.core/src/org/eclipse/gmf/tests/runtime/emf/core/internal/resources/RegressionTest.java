/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */

package org.eclipse.gmf.tests.runtime.emf.core.internal.resources;

import java.util.Collections;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.examples.library.Library;
import org.eclipse.emf.examples.library.RMPLibraryFactory;
import org.eclipse.emf.examples.library.RMPLibraryPackage;
import org.eclipse.gmf.runtime.emf.core.internal.resourcemap.ResourceMap;
import org.eclipse.gmf.runtime.emf.core.internal.resources.AbstractResourceWrapper;
import org.eclipse.gmf.runtime.emf.core.resources.CannotAbsorbException;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;


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
		Library other = RMPLibraryFactory.eINSTANCE.createLibrary();
		logres.getContents().add(other);
		
		root.getBranches().add(other);
		
		assertSame(root, other.eContainer());
		assertSame(
			RMPLibraryPackage.eINSTANCE.getLibrary_Branches(),
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
}
