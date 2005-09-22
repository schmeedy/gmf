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
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.gmf.runtime.emf.core.EventTypes;
import org.eclipse.gmf.runtime.emf.core.edit.DemuxedMListener;
import org.eclipse.gmf.runtime.emf.core.edit.MFilter;
import org.eclipse.gmf.runtime.emf.core.internal.exceptions.MSLActionProtocolException;
import org.eclipse.gmf.runtime.emf.core.internal.resources.LogicalResourceUtil;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResource;


/**
 * Tests the incremental loading support in the logical resource implementation
 * (both automatic and not).
 *
 * @author Christian W. Damus (cdamus)
 */
public class IncrementalLoadingTest
	extends BaseLogicalResourceTest {
	
	public IncrementalLoadingTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new TestSuite(IncrementalLoadingTest.class, "Incremental Loading Tests"); //$NON-NLS-1$
	}
	
	/**
	 * Tests incremental loading of a logical resource, where there are
	 * references between the sub-units.
	 */
	public void test_incrementalLoading() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		subunit1.getBooks().add(book);
		book.setTitle("Book"); //$NON-NLS-1$
		String bookFrag = logres.getURIFragment(book);
		
		Writer writer = EXTLibraryFactory.eINSTANCE.createWriter();
		subunit3.getWriters().add(writer);
		writer.setName("Sherlock Holmes"); //$NON-NLS-1$
		writer.getBooks().add(book);  // cross-unit reference
		String writerFrag = logres.getURIFragment(writer);
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource(false); // don't load sub-units, yet
		
		// check that the subunits are unloaded
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		assertFalse(subunit1.eIsProxy());
		assertFalse(logres.isLoaded(subunit1));
		
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		assertFalse(subunit3.eIsProxy());
		assertFalse(logres.isLoaded(subunit3));
		
		// check that the book and writer don't exist
		book = lookupBook(bookFrag);
		assertNull(book);
		
		writer = lookupWriter(writerFrag);
		assertNull(writer);
		
		// check that we can load subunit1 and its contents (incl. the book)
		try {
			logres.load(subunit1);
		} catch (IOException e) {
			fail("Should not fail to load sub-unit: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		assertTrue(logres.isLoaded(subunit1));
		book = lookupBook(bookFrag);
		assertNotNull(book);
		
		// the author reference (intra-logical-resource) must not have been
		//   discarded on loading, and must be unresolved
		assertNotNull(book.getAuthor());
		assertTrue(book.getAuthor().eIsProxy());
		
		// check that we can load subunit3 and its contents (incl. the writer)
		try {
			logres.load(subunit3);
		} catch (IOException e) {
			fail("Should not fail to load sub-unit: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		assertTrue(logres.isLoaded(subunit3));
		writer = lookupWriter(writerFrag);
		assertNotNull(writer);
		
		// now the references resolve
		assertTrue(writer.getBooks().contains(book));
		assertSame(writer, book.getAuthor());
	}
	
	/**
	 * Tests that we can incrementally load logical resources where other
	 * logical resources have references to the as-yet-unloaded units.
	 */
	public void test_incrementalLoadingWithCrossModelRefs() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		String library2Name = "/" + PROJECT_NAME + "/logres2.extlibrary"; //$NON-NLS-1$//$NON-NLS-2$
		ILogicalResource logres2 = createNewLogicalResource(
			URI.createPlatformResourceURI(library2Name));
		Library library2 = EXTLibraryFactory.eINSTANCE.createLibrary();
		logres2.getContents().add(library2);
		library2.setName("Library 2"); //$NON-NLS-1$
		
		subunit3 = EXTLibraryFactory.eINSTANCE.createLibrary();
		library2.getBranches().add(subunit3);
		subunit3.setName("Library 2 Subunit"); //$NON-NLS-1$
		
		String library2SubunitName = "/" + PROJECT_NAME + "/logres2.1.extlibrary"; //$NON-NLS-1$//$NON-NLS-2$
		try {
			logres2.separate(subunit3, URI.createPlatformResourceURI(library2SubunitName));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
			
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		subunit1.getBooks().add(book);
		book.setTitle("Book"); //$NON-NLS-1$
		String bookFrag = logres.getURIFragment(book);
		
		Writer writer = EXTLibraryFactory.eINSTANCE.createWriter();
		subunit3.getWriters().add(writer);
		writer.setName("Sherlock Holmes"); //$NON-NLS-1$
		writer.getBooks().add(book);  // cross-unit reference
		String writerFrag = logres.getURIFragment(writer);
		
		saveLogicalResource();
		saveLogicalResource(logres2);
		
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		// discard and re-load the resources
		createLogicalResource(RESOURCE_NAME);
		logres2 = createNewLogicalResource(
			URI.createPlatformResourceURI(library2Name));
		
		loadLogicalResource(false);
		loadLogicalResource(logres2, false, false);
				
		// check that the subunits are unloaded
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		assertFalse(subunit1.eIsProxy());
		assertFalse(logres.isLoaded(subunit1));
		
		subunit3 = lookupLibrary(logres2, subunit3Frag);
		assertNotNull(subunit3);
		assertFalse(subunit3.eIsProxy());
		assertFalse(logres2.isLoaded(subunit3));
		
		// check that the book and writer don't exist
		book = lookupBook(bookFrag);
		assertNull(book);
		
		writer = lookupWriter(logres2, writerFrag);
		assertNull(writer);
		
		// check that we can load subunit1 and its contents (incl. the book)
		try {
			logres.load(subunit1);
		} catch (IOException e) {
			fail("Should not fail to load sub-unit: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		assertTrue(logres.isLoaded(subunit1));
		book = lookupBook(bookFrag);
		assertNotNull(book);
		
		// the author reference (intra-logical-resource) must not have been
		//   discarded on loading, and must be unresolved
		assertNotNull(book.getAuthor());
		assertTrue(book.getAuthor().eIsProxy());
		
		// check that we can load subunit3 and its contents (incl. the writer)
		try {
			logres2.load(subunit3);
		} catch (IOException e) {
			fail("Should not fail to load sub-unit: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		assertTrue(logres2.isLoaded(subunit3));
		writer = lookupWriter(logres2, writerFrag);
		assertNotNull(writer);
		
		// now the references resolve
		assertTrue(writer.getBooks().contains(book));
		assertSame(writer, book.getAuthor());
	}
	
	/**
	 * Tests the notification of incremental load events, EMF style.
	 */
	public void test_incrementalLoading_basicEventing() {
		class TestAdapter extends AdapterImpl {
			List notifications = new java.util.ArrayList();
			
			public void notifyChanged(Notification msg) {
				notifications.add(msg);
			}
		}
		
		TestAdapter adapter = new TestAdapter();
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource(false); // don't load sub-units, yet
		
		root = (Library) logres.getContents().get(0);
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		
		// listen for notifications of loading on the parent element
		Library parent = subunit1.getParentBranch();
		assertNotNull(parent);
		parent.eAdapters().add(adapter);

		try {
			logres.load(subunit1);
		} catch (IOException e) {
			fail("Should not fail to load unit: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		assertEquals(1, adapter.notifications.size());
		Notification event = (Notification) adapter.notifications.get(0);
		adapter.notifications.clear();
		
		assertSame(parent, event.getNotifier());
		assertEquals(EventTypes.LOAD, event.getEventType());
		assertSame(EXTLibraryPackage.eINSTANCE.getLibrary_Branches(), event.getFeature());
		assertSame(subunit1, parent.getBranches().get(event.getPosition()));
		
		// listen for notifications of loading on the parent element
		parent = subunit3.getParentBranch();
		assertNotNull(parent);
		parent.eAdapters().add(adapter);

		try {
			logres.load(subunit3);
		} catch (IOException e) {
			fail("Should not fail to load unit: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		assertEquals(1, adapter.notifications.size());
		event = (Notification) adapter.notifications.get(0);
		adapter.notifications.clear();
		
		assertSame(parent, event.getNotifier());
		assertEquals(EventTypes.LOAD, event.getEventType());
		assertSame(EXTLibraryPackage.eINSTANCE.getLibrary_Branches(), event.getFeature());
		assertSame(subunit3, parent.getBranches().get(event.getPosition()));
	}
	
	/**
	 * Tests the notification of incremental load events, MSL style.
	 */
	public void test_incrementalLoading_mslEventing() {
		class TestListener extends DemuxedMListener {
			Notification event;
			ILogicalResource res;
			EObject element;
			
			TestListener() {
				super(domain);
			}
			
			public MFilter getFilter() {
				return MFilter.ELEMENT_LOADED_FILTER;
			}
			
			public void handleElementLoadedEvent(
					Notification notification, ILogicalResource resource,
					EObject eObject) {
				event = notification;
				res = resource;
				element = eObject;
			}
		}
		
		TestListener listener = new TestListener();
		listener.startListening();
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource(false); // don't load sub-units, yet
		
		root = (Library) logres.getContents().get(0);
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);

		try {
			logres.load(subunit1);
		} catch (IOException e) {
			fail("Should not fail to load unit: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		// must complete the write action in order for events to fire
		stopWriting();
		
		assertSame(subunit1, listener.element);
		assertEquals(EventTypes.LOAD, listener.event.getEventType());
		assertSame(logres, listener.res);
		
		startWriting();
		
		try {
			logres.load(subunit3);
		} catch (IOException e) {
			fail("Should not fail to load unit: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		// must complete the write action in order for events to fire
		stopWriting();
		
		assertSame(subunit3, listener.element);
		assertEquals(EventTypes.LOAD, listener.event.getEventType());
		assertSame(logres, listener.res);
	}
	
	/**
	 * Tests that we do not need a write operation to incrementally load a
	 * model that we are reading; a read operation is OK.
	 */
	public void test_incrementalLoading_readAction() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		subunit1.getBooks().add(book);
		book.setTitle("Book"); //$NON-NLS-1$
		String bookFrag = logres.getURIFragment(book);
		
		Writer writer = EXTLibraryFactory.eINSTANCE.createWriter();
		subunit3.getWriters().add(writer);
		writer.setName("Sherlock Holmes"); //$NON-NLS-1$
		writer.getBooks().add(book);  // cross-unit reference
		String writerFrag = logres.getURIFragment(writer);
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource(false);
		
		// go into read-only mode
		stopWriting();
		startReading();
		
		// get the sub-units again
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		
		// check that the book and writer don't exist
		book = lookupBook(bookFrag);
		assertNull(book);
		
		writer = lookupWriter(writerFrag);
		assertNull(writer);
		
		// check that we can load subunit1 and its contents (incl. the book)
		try {
			logres.load(subunit1);
		} catch (IOException e) {
			fail("Should not fail to load sub-unit: " + e.getLocalizedMessage()); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			fail("Should have been able to load in a read action: " + e); //$NON-NLS-1$
		}
		assertTrue(logres.isLoaded(subunit1));
		book = lookupBook(bookFrag);
		assertNotNull(book);
		
		// check that we can load subunit3 and its contents (incl. the writer)
		try {
			logres.load(subunit3);
		} catch (IOException e) {
			fail("Should not fail to load sub-unit: " + e.getLocalizedMessage()); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			fail("Should have been able to load in a read action: " + e); //$NON-NLS-1$
		}
		assertTrue(logres.isLoaded(subunit3));
		writer = lookupWriter(writerFrag);
		assertNotNull(writer);
		
		// now the references resolve
		assertTrue(writer.getBooks().contains(book));
		assertSame(writer, book.getAuthor());
	}
	
	/**
	 * Tests the utility method that loads all unloaded units of a resource.
	 */
	public void test_loadAllUnloadedUnits() {
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
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource(false);  // don't load any sub-units
		
		assertFalse(logres.isModified());
		
		// only the root is loaded
		Map resources = logres.getMappedResources();
		assertEquals(2, resources.size());
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		assertFalse(logres.isLoaded(subunit1));
		subunit2 = lookupLibrary(subunit2Frag);
		assertNull(subunit2);
		
		try {
			LogicalResourceUtil.loadAllUnloadedUnits(logres);
		} catch (IOException e) {
			fail("Should not fail to load all unloaded units: " + e); //$NON-NLS-1$
		}
		
		// all units are now loaded
		assertEquals(3, resources.size());
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		assertTrue(logres.isLoaded(subunit1));
		subunit2 = lookupLibrary(subunit2Frag);
		assertNotNull(subunit2);
		assertTrue(logres.isLoaded(subunit2));

		// verify the model structure just to be sure
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		assertTrue(EcoreUtil.isAncestor(root, subunit2));
		assertTrue(EcoreUtil.isAncestor(subunit1, subunit2));
	}
	
	/**
	 * Tests automatic loading by walking the containment tree.
	 */
	public void test_autoLoading() {
		String rootFrag = logres.getURIFragment(root);
		String subunit1ParentFrag = logres.getURIFragment(subunit1.eContainer());
		String subunit2ParentFrag = logres.getURIFragment(subunit2.eContainer());
		
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
		
		// do not load all sub-units, but load them automatically as needed
		loadLogicalResource(false, true);
		
		assertFalse(logres.isModified());
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		
		Library subunit1Parent = lookupLibrary(subunit1ParentFrag);
		assertNotNull(subunit1Parent); // this one should exist
		Library subunit2Parent = lookupLibrary(subunit2ParentFrag);
		assertNull(subunit2Parent);  // shouldn't exist, yet
		
		// use internal list capabilities for non-resolving/non-loading access
		InternalEList branches = (InternalEList) subunit1Parent.getBranches();
		assertEquals(1, branches.size());
		subunit1 = (Library) branches.basicGet(0);
		assertNotNull(subunit1);
		assertFalse(logres.isLoaded(subunit1));
		
		// loading access (branches.get(0) would work just as well)
		subunit1 = (Library) branches.iterator().next();
		assertNotNull(subunit1);
		assertTrue(logres.isLoaded(subunit1));
		
		subunit2Parent = lookupLibrary(subunit2ParentFrag);
		assertNotNull(subunit2Parent);  // now it should exist
		
		// use internal list capabilities for non-resolving/non-loading access
		branches = (InternalEList) subunit2Parent.getBranches();
		assertEquals(1, branches.size());
		subunit2 = (Library) branches.basicGet(0);
		assertNotNull(subunit2);
		assertFalse(logres.isLoaded(subunit2));
		
		// loading access (branches.iterator().next() would work just as well)
		subunit2 = (Library) branches.get(0);
		assertNotNull(subunit2);
		assertTrue(logres.isLoaded(subunit2));
	}
	
	/**
	 * Tests that loading a resource to load one of its roots also loads
	 * other unloaded elements from its other roots.
	 */
	public void test_loadingMultiRootedUnit() {
		String rootFrag = logres.getURIFragment(root);
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		try {
			// separate these two elements into the same resource
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		// do not load all sub-units
		loadLogicalResource(false);
		
		assertFalse(logres.isModified());
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);

		// not loaded, yet
		assertFalse(logres.isLoaded(subunit1));
		assertFalse(logres.isLoaded(subunit3));
		
		// load one of them
		try {
			logres.load(subunit3);
		} catch (IOException e) {
			fail("Should not fail to load unit: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		assertTrue(logres.isLoaded(subunit3));
		
		// check that subunit1 was also loaded
		assertTrue(logres.isLoaded(subunit1));
		
		// check some contents
		assertTrue(!subunit1.eContents().isEmpty());
	}
	
	/**
	 * Tests that loading a resource to auto-load one of its roots also loads
	 * other unloaded elements from its other roots.
	 */
	public void test_autoLoadingMultiRootedUnit() {
		String rootFrag = logres.getURIFragment(root);
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		try {
			// separate these two elements into the same resource
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		// do not load all sub-units, but let them load automatically
		loadLogicalResource(false, true);
		
		assertFalse(logres.isModified());
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);

		// not loaded, yet
		assertFalse(logres.isLoaded(subunit1));
		assertFalse(logres.isLoaded(subunit3));
		
		// load one of them automatically
		assertSame(subunit3, ((Library) root.getBranches().get(1)).getBranches().get(0));
		assertTrue(logres.isLoaded(subunit3));
		
		// check that subunit1 was also loaded
		assertTrue(logres.isLoaded(subunit1));
		
		// check some contents
		assertTrue(!subunit1.eContents().isEmpty());
	}
	
	/**
	 * Tests that separating an element into a sub-unit that already exists but
	 * is not yet loaded, will first load and resolve its roots.
	 */
	public void test_separatingIntoUnloadedUnit() {
		String rootFrag = logres.getURIFragment(root);
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		// do not load all sub-units
		loadLogicalResource(false);
		
		assertFalse(logres.isModified());
		
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);

		assertFalse(logres.isLoaded(subunit1));
		assertTrue(logres.isLoaded(subunit3));
		
		try {
			// separate into the same resource (not previously loaded)
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		// now subunit1 is loaded
		assertTrue(logres.isLoaded(subunit1));
		assertTrue(logres.isLoaded(subunit3)); // still loaded
		
		// check some contents
		assertTrue(!subunit1.eContents().isEmpty());
	}
	
	/**
	 * Tests that incremental loading does not apply to the logical roots;
	 * they are always loaded.
	 */
	public void test_logicalRoots() {
		// create another couple of roots
		logres.getContents().add(subunit1);
		logres.getContents().add(subunit3);
		
		try {
			logres.separate(root, URI.createPlatformResourceURI(SUBUNIT_NAME2));
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME3));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		String rootFrag = logres.getURIFragment(root);
		String subunit1Frag = logres.getURIFragment(subunit1);
		String subunit3Frag = logres.getURIFragment(subunit3);
		
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		subunit1.getBooks().add(book);
		book.setTitle("Book"); //$NON-NLS-1$
		String bookFrag = logres.getURIFragment(book);
		
		Writer writer = EXTLibraryFactory.eINSTANCE.createWriter();
		subunit3.getWriters().add(writer);
		writer.setName("Sherlock Holmes"); //$NON-NLS-1$
		writer.getBooks().add(book);  // cross-unit reference
		String writerFrag = logres.getURIFragment(writer);
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		// don't load sub-units, yet (except, we have to load the roots)
		loadLogicalResource(false);
		
		// check that the subunits are loaded
		root = lookupLibrary(rootFrag);
		assertNotNull(root);
		assertFalse(root.eIsProxy());
		assertTrue(logres.isLoaded(root));
		
		subunit1 = lookupLibrary(subunit1Frag);
		assertNotNull(subunit1);
		assertFalse(subunit1.eIsProxy());
		assertTrue(logres.isLoaded(subunit1));
		
		subunit3 = lookupLibrary(subunit3Frag);
		assertNotNull(subunit3);
		assertFalse(subunit3.eIsProxy());
		assertTrue(logres.isLoaded(subunit3));
		
		// check books and cross-refs
		
		book = lookupBook(bookFrag);
		assertNotNull(book);
		writer = lookupWriter(writerFrag);
		assertNotNull(book);
		assertSame(writer, book.getAuthor());
	}
}
