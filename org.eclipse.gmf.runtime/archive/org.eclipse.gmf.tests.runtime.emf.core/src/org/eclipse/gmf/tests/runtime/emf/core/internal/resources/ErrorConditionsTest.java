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
import java.util.Collections;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.examples.library.Library;
import org.eclipse.gmf.runtime.emf.core.resources.CannotAbsorbException;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResource;


/**
 * Tests a variety of error conditions in loading and saving, to see that the
 * appropriate response is relayed to the client.
 *
 * @author Christian W. Damus (cdamus)
 */
public class ErrorConditionsTest
	extends BaseLogicalResourceTest {
	
	public ErrorConditionsTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new TestSuite(ErrorConditionsTest.class, "Error Condition Tests"); //$NON-NLS-1$
	}

	/**
	 * Tests failure to load a monolithic resource that does not exist.
	 */
	public void test_loadNonExistentFile_monolith() {
		saveLogicalResource();
		
		delete(RESOURCE_NAME);
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		
		try {
			logres.load(Collections.EMPTY_MAP);
			fail("Should have thrown IOException on non-existent file."); //$NON-NLS-1$
		} catch (IOException e) {
			// success
		}
	}

	/**
	 * Tests failure to load a sub-unit resource that does not exist.
	 */
	public void test_loadNonExistentFile_subunit() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		delete(SUBUNIT_NAME1);
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		
		try {
			Map options = new java.util.HashMap();
			options.put(ILogicalResource.OPTION_LOAD_ALL_UNITS, Boolean.TRUE);
			
			logres.load(options);
		} catch (IOException e) {
			// non-existent sub-units just cause proxies to be unresolved
			//   and separate elements to be unresolved
			fail("Should not have thrown IOException on non-existent file."); //$NON-NLS-1$
		}
		
		subunit1 = (Library) find("root/level1/level2-subunit"); //$NON-NLS-1$
		assertNotNull(subunit1);
		assertFalse(logres.isLoaded(subunit1));
		
		try {
			// now we should get an exception, when explicitly trying to
			//    load the non-existent sub-unit
			logres.load(subunit1);
			fail("Should have thrown IOException on non-existent file."); //$NON-NLS-1$
		} catch (IOException e) {
			// success
		}
	}

	/**
	 * Tests failure to save a monolithic resource that is read-only.
	 */
	public void test_saveReadOnlyFile_monolith() {
		saveLogicalResource();
		
		setReadOnly(RESOURCE_NAME);
		
		// modify the resource
		root.setName("Library of Congress"); //$NON-NLS-1$
		
		try {
			logres.save(Collections.EMPTY_MAP);
			fail("Should have thrown IOException on read-only file."); //$NON-NLS-1$
		} catch (IOException e) {
			// success
		}
	}

	/**
	 * Tests failure to save a sub-unit resource that is read-only.
	 */
	public void test_saveReadOnlyFile_subunit() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		setReadOnly(SUBUNIT_NAME1);
		
		// modify the resource (subunit2 isin the subunit1 file)
		subunit2.setName("Library of Congress"); //$NON-NLS-1$
		
		try {
			logres.save(Collections.EMPTY_MAP);
			fail("Should have thrown IOException on read-only file."); //$NON-NLS-1$
		} catch (IOException e) {
			// success
		}
	}

	/**
	 * Tests the exception to the don't-save-what-isn't-dirty rule for the
	 * case of a monolithic resource, which behaviour should be compatible with
	 * the basic XMI resource behaviour.
	 */
	public void test_saveNonDirtyReadOnlyFile_monolith() {
		saveLogicalResource();
		
		setReadOnly(RESOURCE_NAME);
		
		// do not modify the resource, so that it is not dirty when we save
		//    again
		
		try {
			logres.save(Collections.EMPTY_MAP);
			fail("Should have thrown IOException on read-only file."); //$NON-NLS-1$
		} catch (IOException e) {
			// success
		}
	}

	/**
	 * Tests that when we have multiple units in our resource, we only save
	 * what's dirty, if we save anything at all.
	 */
	public void test_saveNonDirtyReadOnlyFile_subunit() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		setReadOnly(RESOURCE_NAME);
		setReadOnly(SUBUNIT_NAME1);
		
		// do not modify the resource, so that it is not dirty when we save
		//    again
		
		try {
			logres.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			fail("Should not have thrown IOException on read-only file."); //$NON-NLS-1$
		}
	}

	/**
	 * Tests that when we have multiple units in our resource, we only save
	 * what's dirty.
	 */
	public void test_saveOnlyDirtyFile_subunit() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		setReadOnly(RESOURCE_NAME);
		
		// modify only the sub-unit, which is not read-only
		subunit2.setName("Library of Congress"); //$NON-NLS-1$
		
		try {
			logres.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			fail("Should not have thrown IOException on read-only file."); //$NON-NLS-1$
		}
	}
	
	/**
	 * Tests that absorption of an unloaded element is not allowed.
	 */
	public void test_absorbUnloadedElement() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		delete(SUBUNIT_NAME1);
		
		// discard the resource and re-load from disk
		createLogicalResource(RESOURCE_NAME);
		
		try {
			Map options = new java.util.HashMap();
			options.put(ILogicalResource.OPTION_LOAD_ALL_UNITS, Boolean.TRUE);
			
			logres.load(options);
		} catch (IOException e) {
			// non-existent sub-units just cause proxies to be unresolved
			//   and separate elements to be unresolved
			fail("Should not have thrown IOException on non-existent file."); //$NON-NLS-1$
		}
		
		subunit1 = (Library) find("root/level1/level2-subunit"); //$NON-NLS-1$
		assertNotNull(subunit1);
		assertFalse(logres.isLoaded(subunit1));
		
		try {
			logres.absorb(subunit1);
			fail("Should have thrown IllegalArgumentException"); //$NON-NLS-1$
		} catch (CannotAbsorbException e) {
			fail("Should not thrown CannotAbsorbException: " + e.getLocalizedMessage()); //$NON-NLS-1$
		} catch (IllegalArgumentException e) {
			// success
		}
	}
	
	/**
	 * Tests that seaparation of an element into its current storage unit is
	 * not allowed.
	 */
	public void test_separateIntoSameUnit() {
		URI subunitUri = URI.createPlatformResourceURI(SUBUNIT_NAME1);
		
		try {
			logres.separate(subunit1, subunitUri);
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		try {
			logres.separate(subunit2, subunitUri);
			fail("Should have thrown IllegalArgumentException"); //$NON-NLS-1$
		} catch (CannotSeparateException e) {
			// success
		} catch (IllegalArgumentException e) {
			fail("Should not thrown IllegalArgumentException: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
	
	//
	// Framework methods
	//
	
	/**
	 * Deletes the file specified by a workspace-relative path.
	 * 
	 * @param filePath the workspace-relative path
	 */
	private void delete(String filePath) {
		IFile file = getFile(filePath);
		assertNotNull(file);
		
		try {
			file.delete(true, null);
		} catch (CoreException e) {
			fail("Should not fail to delete file: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
	
	/**
	 * Sets the file specified by a workspace-relative path as read-only.
	 * 
	 * @param filePath the workspace-relative path
	 */
	private void setReadOnly(String filePath) {
		IFile file = getFile(filePath);
		assertNotNull(file);
		
		try {
			ResourceAttributes attrs = new ResourceAttributes();
			attrs.setReadOnly(true);
			file.setResourceAttributes(attrs);
		} catch (CoreException e) {
			fail("Should not fail to set read-only: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
}
