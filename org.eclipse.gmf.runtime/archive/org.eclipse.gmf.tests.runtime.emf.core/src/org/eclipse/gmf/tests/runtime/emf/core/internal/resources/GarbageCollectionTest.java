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

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResource;


/**
 * Tests that we do not introduce uncollected or uncollectable garbage in the
 * logical resources API.
 *
 * @author Christian W. Damus (cdamus)
 */
public class GarbageCollectionTest
	extends BaseLogicalResourceTest {
	
	public GarbageCollectionTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new TestSuite(GarbageCollectionTest.class, "Garbage Collection Tests"); //$NON-NLS-1$
	}

	/**
	 * Tests that sub-unit resources and their unmodifiable views are properly
	 * collectable.
	 */
	public void test_collectSubunitResources() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		ReferenceQueue q = new ReferenceQueue();
		WeakReference ref = new WeakReference(
			logres.getMappedResources().get(subunit1), q);
		
		unloadLogicalResource();
		
		// just in case, clear all fields that can reference this resource
		root = null;
		subunit1 = null;
		subunit2 = null;
		subunit3 = null;
		
		// stop writing and clear the MSL undo stack
		stopWriting();
		((MSLEditingDomain) domain).getUndoStack().flushAll();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// won't happen
		}
		
		Runtime.getRuntime().gc();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// won't happen
		}
		
		Reference enqueued = q.poll();
		assertSame(ref, enqueued);
	}

	/**
	 * Tests that resources and their logical views are properly
	 * collectable.
	 */
	public void test_collectLogicalResourceWrappers() {
		Resource res = new ResourceImpl();
		ILogicalResource logical = domain.asLogicalResource(res);
		
		ReferenceQueue q = new ReferenceQueue();
		WeakReference ref = new WeakReference(logical, q);
		
		logical = null;
		res = null;
		
		// stop writing and clear the MSL undo stack
		stopWriting();
		((MSLEditingDomain) domain).getUndoStack().flushAll();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// won't happen
		}
		
		Runtime.getRuntime().gc();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// won't happen
		}
		
		Reference enqueued = q.poll();
		assertSame(ref, enqueued);
	}
}
