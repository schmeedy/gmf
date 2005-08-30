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

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;


/**
 * Tests the unmodifiable resource views that are made available to clients
 * by the {@link ILogicalResource#getMappedResources()} method and the
 * logical-resource-related notifications.
 *
 * @author Christian W. Damus (cdamus)
 */
public class UnmodifiableResourceViewTest
	extends BaseLogicalResourceTest {
	
	public UnmodifiableResourceViewTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new TestSuite(UnmodifiableResourceViewTest.class, "Unmodifiable Resource View Tests"); //$NON-NLS-1$
	}

	/**
	 * Tests that the APIs clients need to access are available.
	 */
	public void test_getURI() {
		// get the map first, to test that its view of the resource set changes
		//    in synch with reality
		Map resources = logres.getMappedResources();
		
		URI subunitUri = URI.createPlatformResourceURI(SUBUNIT_NAME1);
		
		try {
			logres.separate(subunit1, subunitUri);
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Resource unit = (Resource) resources.get(subunit1);
		assertNotNull(unit);
		
		try {
			assertEquals(subunitUri, unit.getURI());
		} catch (UnsupportedOperationException e) {
			fail("Operation should be supported: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Tests that the APIs clients need to access are available.
	 */
	public void test_isModified() {
		// get the map first, to test that its view of the resource set changes
		//    in synch with reality
		Map resources = logres.getMappedResources();
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Resource unit = (Resource) resources.get(subunit1);
		assertNotNull(unit);
		
		try {
			assertTrue(unit.isModified());
		} catch (UnsupportedOperationException e) {
			fail("Operation should be supported: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		try {
			assertFalse(unit.isModified());
		} catch (UnsupportedOperationException e) {
			fail("Operation should be supported: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Tests that the APIs clients need to access are available.
	 */
	public void test_isLoaded() {
		// get the map first, to test that its view of the resource set changes
		//    in synch with reality
		Map resources = logres.getMappedResources();
		
		String subunit1Frag = logres.getURIFragment(subunit1);
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Resource unit = (Resource) resources.get(subunit1);
		assertNotNull(unit);
		
		try {
			assertTrue(unit.isLoaded());
		} catch (UnsupportedOperationException e) {
			fail("Operation should be supported: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		logres.unload();
		
		loadLogicalResource(false, false);  // load without sub-units
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		
		unit = (Resource) resources.get(subunit1);
		assertNotNull(unit);
		
		try {
			assertFalse(unit.isLoaded());
		} catch (UnsupportedOperationException e) {
			fail("Operation should be supported: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Tests that the APIs clients need to access are available.
	 */
	public void test_eAdapters() {
		// get the map first, to test that its view of the resource set changes
		//    in synch with reality
		Map resources = logres.getMappedResources();
		
		String subunit1Frag = logres.getURIFragment(subunit1);
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Resource unit = (Resource) resources.get(subunit1);
		assertNotNull(unit);
		
		try {
			assertTrue(unit.isLoaded());
		} catch (UnsupportedOperationException e) {
			fail("Operation should be supported: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		logres.unload();
		
		loadLogicalResource(false, false);  // load without sub-units
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		
		unit = (Resource) resources.get(subunit1);
		assertNotNull(unit);
		
		class TestAdapter extends AdapterImpl {
			Notification notification;
			
			public void notifyChanged(Notification msg) {
				// capture the change in isLoaded feature
				if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__IS_LOADED) {
					notification = msg;
				}
			}
		}
		
		TestAdapter adapter = new TestAdapter();
		
		try {
			unit.eAdapters().add(adapter);
		} catch (UnsupportedOperationException e) {
			fail("Operation should be supported: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		try {
			logres.load(subunit1);
		} catch (IOException e) {
			fail("Should not have failed to load: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		Notification notification = adapter.notification;
		assertNotNull(notification);
		assertSame(unit, notification.getNotifier()); // view is the notifier
		assertEquals(
			Resource.RESOURCE__IS_LOADED,
			notification.getFeatureID(Resource.class));
		assertFalse(notification.getOldBooleanValue());
		assertTrue(notification.getNewBooleanValue());
	}

	/**
	 * Tests that the APIs clients need to access are available.
	 */
	public void test_getContents() {
		// get the map first, to test that its view of the resource set changes
		//    in synch with reality
		Map resources = logres.getMappedResources();
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Resource unit = (Resource) resources.get(subunit1);
		assertNotNull(unit);
		
		try {
			List contents = unit.getContents();
			assertNotNull(contents);
			assertFalse(contents.isEmpty());
			assertSame(subunit1, contents.get(0));
		} catch (UnsupportedOperationException e) {
			fail("Operation should be supported: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Tests that the APIs clients need to access are available.
	 */
	public void test_getAllContents() {
		// get the map first, to test that its view of the resource set changes
		//    in synch with reality
		Map resources = logres.getMappedResources();
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Resource unit = (Resource) resources.get(subunit1);
		assertNotNull(unit);
		
		try {
			Iterator allContents = unit.getAllContents();
			assertNotNull(allContents);
			
			boolean foundSubunit2 = false;
			while (allContents.hasNext() && !foundSubunit2) {
				foundSubunit2 = allContents.next() == subunit2;
			}
			
			assertTrue(foundSubunit2);
		} catch (UnsupportedOperationException e) {
			fail("Operation should be supported: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Tests that the APIs clients need to access are available.
	 */
	public void test_getEObject() {
		// get the map first, to test that its view of the resource set changes
		//    in synch with reality
		Map resources = logres.getMappedResources();
		
		String subunit2Frag = logres.getURIFragment(subunit2);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Resource unit = (Resource) resources.get(subunit1);
		assertNotNull(unit);
		
		try {
			assertSame(subunit2, unit.getEObject(subunit2Frag));
		} catch (UnsupportedOperationException e) {
			fail("Operation should be supported: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Tests that the APIs clients need to access are available.
	 */
	public void test_getURIFragment() {
		// get the map first, to test that its view of the resource set changes
		//    in synch with reality
		Map resources = logres.getMappedResources();
		
		String subunit2Frag = logres.getURIFragment(subunit2);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Resource unit = (Resource) resources.get(subunit1);
		assertNotNull(unit);
		
		try {
			assertEquals(subunit2Frag, unit.getURIFragment(subunit2));
		} catch (UnsupportedOperationException e) {
			fail("Operation should be supported: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
}
