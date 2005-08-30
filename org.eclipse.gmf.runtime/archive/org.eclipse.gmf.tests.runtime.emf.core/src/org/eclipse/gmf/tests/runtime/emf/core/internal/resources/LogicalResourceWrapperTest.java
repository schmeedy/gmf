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
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.examples.library.Library;
import org.eclipse.emf.examples.library.RMPLibraryFactory;
import org.eclipse.gmf.runtime.emf.core.internal.resources.AbstractResourceWrapper;
import org.eclipse.gmf.runtime.emf.core.resources.CannotAbsorbException;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResource;


/**
 * Tests the {@link LogicalResourceWrapper} class.
 *
 * @author Christian W. Damus (cdamus)
 */
public class LogicalResourceWrapperTest
	extends BaseLogicalResourceTest {
	
	public LogicalResourceWrapperTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new TestSuite(LogicalResourceWrapperTest.class, "Wrapper Tests"); //$NON-NLS-1$
	}

	/**
	 * Tests that nothing can be separated in a non-logical resource.
	 */
	public void test_canSeparate() {
		assertFalse(logres.canSeparate(root)); // wouldn't be separable, anyway
		assertFalse(logres.canSeparate(subunit1)); // would be separable in MSL resource
	}

	/**
	 * Tests that nothing appears to be separate in a non-logical resource.
	 */
	public void test_isSeparate() {
		assertFalse(logres.isSeparate(root)); // wouldn't be separate, anyway
		assertFalse(logres.isSeparate(subunit1)); // same here, actually
	}

	/**
	 * Tests that everything appears to be loaded in a non-logical resource.
	 */
	public void test_isLoaded() {
		saveLogicalResource();
		logres.unload();
		
		Map options = new java.util.HashMap();
		options.put(ILogicalResource.OPTION_LOAD_ALL_UNITS, Boolean.FALSE);
		options.put(ILogicalResource.OPTION_AUTO_LOAD_UNITS, Boolean.FALSE);
		
		// try loading without loading sub-units and without auto-loading
		loadLogicalResource(false, false);
		
		assertTrue(logres.isLoaded(root)); // wouldn't be unloaded, anyway
		assertTrue(logres.isLoaded(subunit1)); // same here, actually
	}
	
	/**
	 * Tests that attempting to load an element always fails.
	 */
	public void test_load() {
		try {
			logres.load(root);  // would fail, anyway
			fail("Should have failed to load"); //$NON-NLS-1$
		} catch (IllegalArgumentException e) {
			// passed the test
		} catch (IOException e) {
			fail("Wrong exception thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		try {
			logres.load(subunit1);  // would also fail in MSL resource because it's not separate
			fail("Should have failed to load"); //$NON-NLS-1$
		} catch (IllegalArgumentException e) {
			// passed the test
		} catch (IOException e) {
			fail("Wrong exception thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
	
	/**
	 * Tests that attempting to separate always fails.
	 */
	public void test_separate() {
		URI subunitUri = URI.createPlatformResourceURI(SUBUNIT_NAME1);
		
		try {
			logres.separate(root, subunitUri);  // would fail, anyway
			fail("Should have failed to separate"); //$NON-NLS-1$
		} catch (IllegalArgumentException e) {
			// passed the test
		} catch (CannotSeparateException e) {
			fail("Wrong exception thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		try {
			logres.separate(subunit1, subunitUri);  // would not fail in MSL resource
			fail("Should have failed to separate"); //$NON-NLS-1$
		} catch (IllegalArgumentException e) {
			// passed the test
		} catch (CannotSeparateException e) {
			fail("Wrong exception thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
	
	/**
	 * Tests that attempting to absorb always fails.
	 */
	public void test_absorb() {
		try {
			logres.absorb(root);  // would fail, anyway
			fail("Should have failed to absorb"); //$NON-NLS-1$
		} catch (IllegalArgumentException e) {
			// passed the test
		} catch (CannotAbsorbException e) {
			fail("Wrong exception thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		try {
			logres.absorb(subunit1);  // would actually fail in MSL resource, too
			fail("Should have failed to absorb"); //$NON-NLS-1$
		} catch (IllegalArgumentException e) {
			// passed the test
		} catch (CannotAbsorbException e) {
			fail("Wrong exception thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
	
	/**
	 * Tests that we do get a correct mapped-resources map.
	 */
	public void test_getMappedResources() {
		Map resources = logres.getMappedResources();
		
		assertEquals(1, resources.size());
		assertSame(
			AbstractResourceWrapper.unwrap(logres),
			AbstractResourceWrapper.unwrap((Resource) resources.get(root)));
		
		Library second = RMPLibraryFactory.eINSTANCE.createLibrary();
		logres.getContents().add(second);  // test adding a root
		
		// check that the resource map is dynamically updated
		assertEquals(2, resources.size());
		assertSame(
			AbstractResourceWrapper.unwrap(logres),
			AbstractResourceWrapper.unwrap((Resource) resources.get(root)));
		assertSame(
			AbstractResourceWrapper.unwrap(logres),
			AbstractResourceWrapper.unwrap((Resource) resources.get(second)));
		
		Library third = RMPLibraryFactory.eINSTANCE.createLibrary();
		logres.getContents().set(1, third);  // test setting a root
		assertEquals(2, resources.size());
		assertSame(
			AbstractResourceWrapper.unwrap(logres),
			AbstractResourceWrapper.unwrap((Resource) resources.get(third)));
		
		logres.getContents().remove(0);  // test removing a root
		assertEquals(1, resources.size());
		assertSame(
			AbstractResourceWrapper.unwrap(logres),
			AbstractResourceWrapper.unwrap((Resource) resources.get(third)));
	}
	
	/** Test that notifications appear correctly to come from the wrapper. */
	public void test_notifications() {
		class TestAdapter extends AdapterImpl {
			Notification notification;
			
			public void notifyChanged(Notification msg) {
				notification = msg;
			}
		}
		
		TestAdapter adapter = new TestAdapter();
		
		logres.eAdapters().add(adapter);
		
		logres.setModified(true);
		
		Notification notification = adapter.notification;
		assertNotNull(notification);
		assertSame(logres, notification.getNotifier());  // not the wrapped XMI
		assertEquals(Resource.RESOURCE__IS_MODIFIED, notification.getFeatureID(null));
		assertFalse(notification.getOldBooleanValue());
		assertTrue(notification.getNewBooleanValue());
	}
	
	public void test_unwrap() {
		Resource unwrapped = AbstractResourceWrapper.unwrap(logres);
		
		assertNotNull(unwrapped);
		assertNotSame(logres, unwrapped);
		assertSame(
			logres.getResourceSet().getResource(getXmiUri(), false),
			unwrapped);
	}
	
	//
	// Framework methods
	//
	
	/**
	 * Replaces the *.rmplibrary extension with *.xmi so that the editing domain
	 * will not create logical resources, but instead default XMI resources.
	 */
	protected ILogicalResource createNewLogicalResource(URI uri) {
		return super.createNewLogicalResource(getXmiUri(uri));
	}
	
	/**
	 * Converts a URI to *.xmi.
	 * 
	 * @param uri a URI
	 * @return the same URI, except with a *.xmi extension
	 */
	private URI getXmiUri(URI uri) {
		return uri.trimFileExtension().appendFileExtension("xmi"); //$NON-NLS-1$
	}
	
	/**
	 * Gets the default test resource URI as an XMI URI.
	 * 
	 * @return the *.xmi variant of our default test resource URI
	 */
	private URI getXmiUri() {
		return getXmiUri(URI.createPlatformResourceURI(RESOURCE_NAME));
	}
}
