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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.examples.library.Book;
import org.eclipse.emf.examples.library.Library;
import org.eclipse.emf.examples.library.RMPLibraryFactory;
import org.eclipse.emf.examples.library.RMPLibraryPackage;
import org.eclipse.emf.examples.library.Writer;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResource;


/**
 * Tests the the management of cross-references (between units and logical
 * resources) in a logical resource, as well as the loading of (non-root)
 * sub-units in logical and physical mode.
 *
 * @author Christian W. Damus (cdamus)
 */
public class CrossReferenceTest
	extends BaseLogicalResourceTest {
	
	public CrossReferenceTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new TestSuite(CrossReferenceTest.class, "Cross-Reference Tests"); //$NON-NLS-1$
	}

	/**
	 * Tests resolution of cross-unit references on loading.  This case has
	 * a bidirectional reference between unrelated units.
	 */
	public void test_crossUnitReferences_unrelated() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Book book = (Book) find(subunit1, "Level2 Book"); //$NON-NLS-1$
		String bookFrag = logres.getURIFragment(book);
		
		Writer writer = (Writer) find(subunit3, "Level2-1 Writer"); //$NON-NLS-1$
		writer.getBooks().add(book);  // cross-unit reference
		String writerFrag = logres.getURIFragment(writer);
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		book = lookupBook(bookFrag);
		assertNotNull(book);
		
		writer = lookupWriter(writerFrag);
		assertNotNull(writer);
		
		assertTrue(writer.getBooks().contains(book));
		assertSame(writer, book.getAuthor());
	}
	
	/**
	 * Tests resolution of cross-unit references on loading.  This case has
	 * a bidirectional reference between parent and child units.
	 */
	public void test_crossUnitReferences_parentChild() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Book book = (Book) find(subunit1, "Level2 Book"); //$NON-NLS-1$
		String bookFrag = logres.getURIFragment(book);
		
		Writer writer = (Writer) find(subunit2, "Level4 Writer"); //$NON-NLS-1$
		writer.getBooks().add(book);  // cross-unit reference
		String writerFrag = logres.getURIFragment(writer);
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		book = lookupBook(bookFrag);
		assertNotNull(book);
		
		writer = lookupWriter(writerFrag);
		assertNotNull(writer);
		
		assertTrue(writer.getBooks().contains(book));
		assertSame(writer, book.getAuthor());
	}
	
	/**
	 * Tests resolution of cross-unit references on loading.  This case has
	 * a bidirectional reference between the root unit and a child unit.
	 */
	public void test_crossUnitReferences_rootChild() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Book book = (Book) find("root/Root Book"); //$NON-NLS-1$
		String bookFrag = logres.getURIFragment(book);
		
		Writer writer = (Writer) find(subunit1, "Level2 Writer"); //$NON-NLS-1$
		writer.getBooks().add(book);  // cross-unit reference
		String writerFrag = logres.getURIFragment(writer);
		
		saveLogicalResource();
		
		// discard and re-load the resource
		createLogicalResource(RESOURCE_NAME);
		
		loadLogicalResource();
		
		book = lookupBook(bookFrag);
		assertNotNull(book);
		
		writer = lookupWriter(writerFrag);
		assertNotNull(writer);
		
		assertTrue(writer.getBooks().contains(book));
		assertSame(writer, book.getAuthor());
	}
	
	/**
	 * Tests the on-the-fly replacement of the logical resource by its proper
	 * root when loading a non-root unit, loading the units required to trace
	 * containment to the root unit.
	 */
	public void test_loadNonRootUnit() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME3));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Book bookRoot = (Book) find(root, "Root Book"); //$NON-NLS-1$
		String bookRootFrag = logres.getURIFragment(bookRoot);
		Book book1 = (Book) find(subunit1, "Level2 Book"); //$NON-NLS-1$
		String book1Frag = logres.getURIFragment(book1);
		Book book2 = (Book) find(subunit2, "Level4 Book"); //$NON-NLS-1$
		String book2Frag = logres.getURIFragment(book2);
		Book book3 = (Book) find(subunit3, "Level2-1 Book"); //$NON-NLS-1$
		String book3Frag = logres.getURIFragment(book3);
		
		saveLogicalResource();
		
		URI logicalUri = logres.getURI();
		URI subunitUri = URI.createPlatformResourceURI(SUBUNIT_NAME2);
		
		// discard the logical resource and load the subunit
		unloadLogicalResource();
		createLogicalResource(SUBUNIT_NAME2);
		
		// so, we're trying to load the second-level unit.  We want to
		//   actually load the second-, first-, and root-level units, but not
		//   the other unit that contains book3 (subunit3)
		loadLogicalResource(false);
		
		// the logical resource has assumed the logical URI, despite its having
		//    been created with a subunit's URI
		assertEquals(logicalUri, logres.getURI());
		
		// make sure we didn't get an extra logical resource by this means
		assertNull(domain.getResourceSet().getResource(subunitUri, false));
		
		// make sure that we didn't get too many physical resources, either
		ResourceSet rset = (ResourceSet) logres.getAdapter(ResourceSet.class);
		assertEquals(4, rset.getResources().size());
		
		// make sure that we find the books we expect and not the ones we don't.
		bookRoot = lookupBook(bookRootFrag);
		assertNotNull(bookRoot);
		book1 = lookupBook(book1Frag);
		assertNotNull(book1);
		book2 = lookupBook(book2Frag);
		assertNotNull(book2);
		book3 = lookupBook(book3Frag);
		assertNull(book3);  // didn't load this unit
		
		// Repeat by walking the tree instead of looking by fragment, to check
		// that the content tree is correctly connected
		Set notFound = new java.util.HashSet();
		notFound.add(bookRootFrag);
		notFound.add(book1Frag);
		notFound.add(book2Frag);
		notFound.add(book3Frag);
		
		for (Iterator iter = logres.getAllContents(); iter.hasNext();) {
			notFound.remove(logres.getURIFragment((EObject) iter.next()));
		}
		
		assertEquals(Collections.singleton(book3Frag), notFound);  // didn't load this unit
	}
	
	/**
	 * Tests the on-the-fly replacement of the logical resource by its proper
	 * root when loading a non-root unit storing a logical root.
	 */
	public void test_loadNonRootUnit_logicalRoot() {
		logres.getContents().add(subunit3);  // create another root
		
		try {
			// separating a root element
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME3));
			
			// just for fun, separate another root's descendent into same unit
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME3));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Book bookRoot = (Book) find(root, "Root Book"); //$NON-NLS-1$
		String bookRootFrag = logres.getURIFragment(bookRoot);
		Book book1 = (Book) find(subunit1, "Level2 Book"); //$NON-NLS-1$
		String book1Frag = logres.getURIFragment(book1);
		Book book2 = (Book) find(subunit2, "Level4 Book"); //$NON-NLS-1$
		String book2Frag = logres.getURIFragment(book2);
		Book book3 = (Book) find(subunit3, "Level2-1 Book"); //$NON-NLS-1$
		String book3Frag = logres.getURIFragment(book3);
		
		saveLogicalResource();
		
		URI logicalUri = logres.getURI();
		URI subunitUri = URI.createPlatformResourceURI(SUBUNIT_NAME3);
		
		// discard the logical resource and load the subunit
		unloadLogicalResource();
		createLogicalResource(SUBUNIT_NAME3);
		
		// so, we're trying to load the second-level unit with the logical root
		loadLogicalResource();
		
		// the logical resource has assumed the logical URI, despite its having
		//    been created with a subunit's URI
		assertEquals(logicalUri, logres.getURI());
		
		// make sure we didn't get an extra logical resource by this means
		assertNull(domain.getResourceSet().getResource(subunitUri, false));
		
		// make sure that we didn't get too many physical resources, either
		ResourceSet rset = (ResourceSet) logres.getAdapter(ResourceSet.class);
		assertEquals(2, rset.getResources().size());
		assertEquals(3, logres.getMappedResources().size());
		
		// make sure that we find the books we expect
		bookRoot = lookupBook(bookRootFrag);
		assertNotNull(bookRoot);
		book1 = lookupBook(book1Frag);
		assertNotNull(book1);
		book2 = lookupBook(book2Frag);
		assertNotNull(book2);
		book3 = lookupBook(book3Frag);
		assertNotNull(book3);
		
		// Repeat by walking the tree instead of looking by fragment, to check
		// that the content tree is correctly connected
		Set notFound = new java.util.HashSet();
		notFound.add(bookRootFrag);
		notFound.add(book1Frag);
		notFound.add(book2Frag);
		notFound.add(book3Frag);
		
		for (Iterator iter = logres.getAllContents(); iter.hasNext();) {
			notFound.remove(logres.getURIFragment((EObject) iter.next()));
		}
		
		assertEquals(Collections.EMPTY_SET, notFound);
	}
	
	/**
	 * Tests the case where the sub-unit that we are trying to load has
	 * multiple parent units.  Both parents must be loaded, recursively.
	 */
	public void test_loadNonRootUnit_multiParents() {
		Library subunit4 = RMPLibraryFactory.eINSTANCE.createLibrary();
		subunit3.getBranches().add(subunit4);
		subunit4.setName("level 3-1-Subunit"); //$NON-NLS-1$
		Book book4 = RMPLibraryFactory.eINSTANCE.createBook();
		subunit4.getBooks().add(book4);
		book4.setTitle("Level3-1 Book"); //$NON-NLS-1$
		
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
			logres.separate(subunit3, URI.createPlatformResourceURI(SUBUNIT_NAME3));
			
			// another 2nd-level sub-unit into the same file as subunit2, with
			// a different parent unit
			logres.separate(subunit4, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Book bookRoot = (Book) find(root, "Root Book"); //$NON-NLS-1$
		String bookRootFrag = logres.getURIFragment(bookRoot);
		Book book1 = (Book) find(subunit1, "Level2 Book"); //$NON-NLS-1$
		String book1Frag = logres.getURIFragment(book1);
		Book book2 = (Book) find(subunit2, "Level4 Book"); //$NON-NLS-1$
		String book2Frag = logres.getURIFragment(book2);
		Book book3 = (Book) find(subunit3, "Level2-1 Book"); //$NON-NLS-1$
		String book3Frag = logres.getURIFragment(book3);
		String book4Frag = logres.getURIFragment(book4);
		
		saveLogicalResource();
		
		URI logicalUri = logres.getURI();
		URI subunitUri = URI.createPlatformResourceURI(SUBUNIT_NAME2);
		
		// discard the logical resource and load the subunit
		unloadLogicalResource();
		createLogicalResource(SUBUNIT_NAME2);
		
		// so, we're trying to load the second-level unit.  We want to
		//   actually load the second-, first-, and root-level units, and this
		//   time also the other unit that contains book3 (subunit3)
		loadLogicalResource(false);
		
		// the logical resource has assumed the logical URI, despite its having
		//    been created with a subunit's URI
		assertEquals(logicalUri, logres.getURI());
		
		// make sure we didn't get an extra logical resource by this means
		assertNull(domain.getResourceSet().getResource(subunitUri, false));
		
		// make sure that we didn't get too many physical resources, either
		ResourceSet rset = (ResourceSet) logres.getAdapter(ResourceSet.class);
		assertEquals(4, rset.getResources().size());
		
		// make sure that we find the books we expect
		bookRoot = lookupBook(bookRootFrag);
		assertNotNull(bookRoot);
		book1 = lookupBook(book1Frag);
		assertNotNull(book1);
		book2 = lookupBook(book2Frag);
		assertNotNull(book2);
		book3 = lookupBook(book3Frag);
		assertNotNull(book3);  // did load this unit this time
		book4 = lookupBook(book4Frag);
		assertNotNull(book4);
		
		// Repeat by walking the tree instead of looking by fragment, to check
		// that the content tree is correctly connected
		Set notFound = new java.util.HashSet();
		notFound.add(bookRoot);
		notFound.add(book1);
		notFound.add(book2);
		notFound.add(book3);
		notFound.add(book4);
		
		for (Iterator iter = logres.getAllContents(); iter.hasNext();) {
			notFound.remove(iter.next());
		}
		
		assertEquals(Collections.EMPTY_SET, notFound);  // did load book3 this time
	}
	
	/**
	 * Tests the support for loading only a single physical resource.
	 */
	public void test_loadAsPhysical() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Book bookRoot = (Book) find(root, "Root Book"); //$NON-NLS-1$
		String bookRootFrag = logres.getURIFragment(bookRoot);
		Book book1 = (Book) find(subunit1, "Level2 Book"); //$NON-NLS-1$
		String book1Frag = logres.getURIFragment(book1);
		Book book2 = (Book) find(subunit2, "Level4 Book"); //$NON-NLS-1$
		String book2Frag = logres.getURIFragment(book2);
		
		saveLogicalResource();
		
		URI logicalUri = logres.getURI();
		URI subunitUri = URI.createPlatformResourceURI(SUBUNIT_NAME1);
		
		// discard the logical resource and load the subunit
		unloadLogicalResource();
		createLogicalResource(SUBUNIT_NAME1);
		
		// we're trying to load the middle-level unit, and only it
		Map options = new java.util.HashMap();
		options.put(ILogicalResource.OPTION_LOAD_AS_LOGICAL, Boolean.FALSE);
		loadLogicalResource(logres, options);
		
		// the logical resource has not assumed the logical URI, despite its having
		//    been created with a subunit's URI
		assertEquals(subunitUri, logres.getURI());
		
		// make sure we didn't get an extra logical resource by this means
		assertNull(domain.getResourceSet().getResource(logicalUri, false));
		
		// make sure that we didn't get too many physical resources, either
		ResourceSet rset = (ResourceSet) logres.getAdapter(ResourceSet.class);
		assertEquals(2, rset.getResources().size());  // the resource and its sub-unit
		
		// make sure that we find the books we expect, and not the ones we don't
		bookRoot = lookupBook(bookRootFrag);
		assertNull(bookRoot);
		book1 = lookupBook(book1Frag);
		assertNotNull(book1);
		book2 = lookupBook(book2Frag);
		assertNull(book2);
		
		// check that the resource has the expected contents list
		assertEquals(
			Collections.singletonList(book1.eContainer()),
			logres.getContents());
	}
	
	/**
	 * Tests the support for saving a resource loaded as a single physical
	 * resource.
	 */
	public void test_savePhysical() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
			logres.separate(subunit2, URI.createPlatformResourceURI(SUBUNIT_NAME2));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		Book bookRoot = (Book) find(root, "Root Book"); //$NON-NLS-1$
		String bookRootFrag = logres.getURIFragment(bookRoot);
		Book book1 = (Book) find(subunit1, "Level2 Book"); //$NON-NLS-1$
		String book1Frag = logres.getURIFragment(book1);
		Book book2 = (Book) find(subunit2, "Level4 Book"); //$NON-NLS-1$
		String book2Frag = logres.getURIFragment(book2);
		
		saveLogicalResource();
		
		// discard the logical resource and load the subunit
		unloadLogicalResource();
		createLogicalResource(SUBUNIT_NAME1);
		
		// we're trying to load the middle-level unit, and only it
		Map options = new java.util.HashMap();
		options.put(ILogicalResource.OPTION_LOAD_AS_LOGICAL, Boolean.FALSE);
		loadLogicalResource(logres, options);
		
		// get the book and change its title
		book1 = lookupBook(book1Frag);
		assertNotNull(book1);
		String newTitle = "New Title"; //$NON-NLS-1$
		book1.setTitle(newTitle);
		
		saveLogicalResource();
		
		// discard the logical resource and load the whole logical resource
		unloadLogicalResource();
		createLogicalResource(RESOURCE_NAME);
		loadLogicalResource();
		
		// make sure that we find the books we expect with the right containment
		bookRoot = lookupBook(bookRootFrag);
		assertNotNull(bookRoot);
		book1 = lookupBook(book1Frag);
		assertNotNull(book1);
		book2 = lookupBook(book2Frag);
		assertNotNull(book2);
		
		assertTrue(EcoreUtil.isAncestor(logres.getContents(), book1));

		// check the title
		assertEquals(newTitle, book1.getTitle());
	}
	
	/**
	 * Tests that we can correctly resolve references between logical models.
	 */
	public void test_interLogicalModelReference() {
		try {
			logres.separate(subunit1, URI.createPlatformResourceURI(SUBUNIT_NAME1));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
		
		ILogicalResource logres2 = createNewLogicalResource(
			URI.createPlatformResourceURI(RESOURCE_NAME2));
		Library library2 = RMPLibraryFactory.eINSTANCE.createLibrary();
		logres2.getContents().add(library2);
		library2.setName("Library 2"); //$NON-NLS-1$
		
		subunit3 = RMPLibraryFactory.eINSTANCE.createLibrary();
		library2.getBranches().add(subunit3);
		subunit3.setName("Library 2 Subunit"); //$NON-NLS-1$
		
		String library2SubunitName = "/" + PROJECT_NAME + "/logres2.1.rmplibrary"; //$NON-NLS-1$//$NON-NLS-2$
		try {
			logres2.separate(subunit3, URI.createPlatformResourceURI(library2SubunitName));
		} catch (CannotSeparateException e) {
			fail("Should not fail to separate: " + e); //$NON-NLS-1$
		}
			
		Book book = (Book) find(subunit1, "Level2 Book"); //$NON-NLS-1$
		String bookFrag = logres.getURIFragment(book);
		
		Writer writer = RMPLibraryFactory.eINSTANCE.createWriter();
		subunit3.getWriters().add(writer);
		writer.setName("Level2 Writer"); //$NON-NLS-1$
		writer.getBooks().add(book);  // cross-resource reference
		String writerFrag = logres2.getURIFragment(writer);
		
		saveLogicalResource();
		saveLogicalResource(logres2);
		
		// discard and re-load the resources
		createLogicalResource(RESOURCE_NAME);
		logres2 = createNewLogicalResource(
			URI.createPlatformResourceURI(RESOURCE_NAME2));
		
		loadLogicalResource();
		loadLogicalResource(logres2);
		
		book = lookupBook(bookFrag);
		assertNotNull(book);
		
		writer = lookupWriter(logres2, writerFrag);
		assertNotNull(writer);
		
		// first, test the proxies without resolving them
		EObject proxy = (EObject) ((InternalEList) writer.getBooks()).basicGet(0);
		assertTrue(proxy.eIsProxy());
		assertEquals(logres.getURI(), EcoreUtil.getURI(proxy).trimFragment());
		
		proxy = (EObject) book.eGet(RMPLibraryPackage.eINSTANCE.getBook_Author(), false);
		// cannot assertTrue(proxy.eIsProxy()) because MSL's indexer resolves it
		assertEquals(logres2.getURI(), EcoreUtil.getURI(proxy).trimFragment());
		
		// these method invocations will cause proxy resolution
		assertTrue(writer.getBooks().contains(book));
		assertSame(writer, book.getAuthor());
	}
}
