/******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/


package org.eclipse.gmf.tests.runtime.emf.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Employee;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MFilter;
import org.eclipse.gmf.runtime.emf.core.edit.MListener;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.internal.exceptions.MSLActionProtocolException;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;


/**
 * Tests for extensibility of the MSL's editing domain, particularly for
 * integration with other application frameworks.
 *
 * @author Christian W. Damus (cdamus)
 */
public class EditingDomainExtensibilityTests
	extends BaseCoreTests {
	
	private static final String OTHER_PROJECT_NAME = PROJECT_NAME + '2';
	
	private ResourceSet rset;
	private IProject otherProject;
	private ResourceSet otherRset;
	
	public EditingDomainExtensibilityTests(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new TestSuite(EditingDomainExtensibilityTests.class, "Extensibility Tests"); //$NON-NLS-1$
	}
	
	/**
	 * Tests initialization and usage of an MEditingDomain with a custom
	 * resource set implementation.
	 * 
	 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=110279">Bug 110279</a>
	 */
	public void test_customResourceSet_110279() {
		assertNotNull(testResource);
		assertTrue(testResource.isLoaded());
		
		assertSame(rset, testResource.getResourceSet());
		assertSame(domain, MEditingDomain.getEditingDomain(testResource));
	}
	
	/**
	 * Tests that the editing domain does not interfere with our custom resource
	 * set's resource creating and loading behaviour.
	 * 
	 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=110279">Bug 110279</a>
	 */
	public void test_customResourceLoading_110279() {
		// let the project's resource set be created on demand by URI resolution
		// Note that this is an unresolvable proxy, because we didn't actually
		// create the referent :-)
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		URI proxyUri = URI.createPlatformResourceURI(
			otherProject.getName() + "/nosuchlibrary.extlibrary"); //$NON-NLS-1$
		((InternalEObject) book).eSetProxyURI(proxyUri.appendFragment("foo")); //$NON-NLS-1$
		
		// stick this proxy into a writer's books reference list
		final Writer rootWriter = (Writer) find(testResource, "root/Root Writer"); //$NON-NLS-1$
		assertNotNull(rootWriter);
		
		final Book finalBook = book;
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							rootWriter.getBooks().add(0, finalBook);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update writer: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
		
		// attempt to resolve the proxy
		book = (Book) rootWriter.getBooks().get(0);
		
		// it shouldn't have resolved
		assertTrue(book.eIsProxy());
		
		// but, we should have created the resource that was referenced
		ResourceSet other = MyResourceSet.getResourceSet(otherProject);
		assertNotNull(other);
		List otherResources = other.getResources();
		assertEquals(1, otherResources.size());
		Resource otherRes = (Resource) otherResources.get(0);
		assertEquals(proxyUri, otherRes.getURI());
		
		// check that the referencing resource's resource set did not also
		//   create the resource
		for (Iterator iter = rset.getResources().iterator(); iter.hasNext();) {
			if (proxyUri.equals(((Resource) iter.next()).getURI())) {
				fail("Default resource set also loaded the referenced resource"); //$NON-NLS-1$
			}
		}
		
		// now, get the MEditingDomain of this other resource and check
		//   that it is different from the referencing resource's domain
		MEditingDomain otherDomain = MEditingDomain.getEditingDomain(otherRes);
		assertNotNull(otherDomain);
		assertNotSame(domain, otherDomain);
	}
	
	/**
	 * Tests the action protocol enforcement on custom resource sets.
	 * 
	 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=110279">Bug 110279</a>
	 */
	public void test_actionProtocol_110279() {
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		book.setTitle("A Book"); //$NON-NLS-1$
		
		// attempt to modify without the correct write action
		Writer rootWriter = (Writer) find(testResource, "root/Root Writer"); //$NON-NLS-1$
		assertNotNull(rootWriter);

		try {
			rootWriter.getBooks().add(book);
			fail("Should have thrown protocol exception"); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
	}
	
	/**
	 * Tests the MSL's batched listener mechanism.
	 * 
	 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=110279">Bug 110279</a>
	 */
	public void test_listener_110279() {
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		book.setTitle("A Book"); //$NON-NLS-1$
		
		final Writer rootWriter = (Writer) find(testResource, "root/Root Writer"); //$NON-NLS-1$
		assertNotNull(rootWriter);
		
		final List eventList = new java.util.ArrayList();
		
		MListener listener = new MListener(domain, MFilter.WILDCARD_FILTER) {
			
			public void onEvent(List events) {
				eventList.addAll(events);
			}};
		listener.startListening();
		
		final Book finalBook = book;
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							root.getStock().add(finalBook);
							rootWriter.getBooks().add(0, finalBook);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update writer: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
		
		// check that we got events
		assertFalse(eventList.isEmpty());
		
		// now, check that we got a SET event on the book's author feature
		boolean found = false;
		for (Iterator iter = eventList.iterator(); iter.hasNext();) {
			Notification next = (Notification) iter.next();
			
			if ((next.getNotifier() == book)
					&& (next.getFeature() == EXTLibraryPackage.eINSTANCE.getBook_Author())
					&& (next.getEventType() == Notification.SET)
					&& (next.getNewValue() == rootWriter)) {
				found = true;
			}
		}
		if (!found) {
			fail("Did not find expected event"); //$NON-NLS-1$
		}
	}
	
	/**
	 * Tests the MSL's cross-referencer mechanism.
	 * 
	 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=110279">Bug 110279</a>
	 */
	public void test_crossReferences_110279() {
		final Employee underling = EXTLibraryFactory.eINSTANCE.createEmployee();
		underling.setFirstName("Joe"); //$NON-NLS-1$
		underling.setLastName("Underling"); //$NON-NLS-1$
		
		final Employee boss = EXTLibraryFactory.eINSTANCE.createEmployee();
		boss.setFirstName("Jane"); //$NON-NLS-1$
		boss.setLastName("Boss"); //$NON-NLS-1$
		
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							root.getEmployees().add(underling);
							root.getEmployees().add(boss);
							
							// this is a unidirectional reference
							underling.setManager(boss);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update writer: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection xrefs = EObjectUtil.getReferencers(
			boss,
			new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});
		
		assertTrue(xrefs.contains(underling));
	}
	
	/**
	 * Tests undo/redo support on custom resource sets.
	 * 
	 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=110279">Bug 110279</a>
	 */
	public void test_undoRedo_110279() {
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		book.setTitle("A Book"); //$NON-NLS-1$
		
		final Writer rootWriter = (Writer) find(testResource, "root/Root Writer"); //$NON-NLS-1$
		assertNotNull(rootWriter);
		
		final Book finalBook = book;
		MUndoInterval undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							root.getStock().add(finalBook);
							rootWriter.getBooks().add(0, finalBook);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update writer: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
		
		// check that the change was successfully performed
		assertTrue(root.getStock().contains(book));
		assertTrue(rootWriter.getBooks().contains(book));
		assertSame(rootWriter, book.getAuthor());
		
		// undo
		assertTrue(undo.canUndo());
		undo.undo();
		
		// check that the change was successfully undone
		assertFalse(root.getStock().contains(book));
		assertFalse(rootWriter.getBooks().contains(book));
		assertNull(book.getAuthor());
		
		// redo
		assertTrue(undo.canRedo());
		undo.redo();
		
		// check that the change was successfully redone
		assertTrue(root.getStock().contains(book));
		assertTrue(rootWriter.getBooks().contains(book));
		assertSame(rootWriter, book.getAuthor());
	}
	
	/**
	 * Tests cross-resource-set listening.
	 * 
	 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=110279">Bug 110279</a>
	 */
	public void test_crossResourceSetListener_110279() {
		// re-initialize our domain
		rset = new DelegatingResourceSet();
		domain = MEditingDomain.createNewDomain(rset);

		// associate the old resource set with this new domain
		MEditingDomain.setEditingDomain(testResource.getResourceSet(), domain);
		
		URI otherUri = URI.createPlatformResourceURI(
			otherProject.getName() + "/otherlibrary.extlibrary"); //$NON-NLS-1$
		final Resource otherRes = rset.createResource(otherUri);
		
		// check that the two resources we have are in different resource sets
		assertNotSame(testResource.getResourceSet(), otherRes.getResourceSet());
		
		// get a writer from the test resource, which we will modify
		final Writer rootWriter = (Writer) find(testResource, "root/Root Writer"); //$NON-NLS-1$
		assertNotNull(rootWriter);
		
		final List eventList = new java.util.ArrayList();
		
		MListener listener = new MListener(domain, MFilter.WILDCARD_FILTER) {
			
			public void onEvent(List events) {
				eventList.addAll(events);
			}};
		listener.startListening();
		
		// do a cross-resource-set modification
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							Book book = EXTLibraryFactory.eINSTANCE.createBook();
							otherRes.getContents().add(book);
							
							// make mods in both resources, each of which is
							//   in a different resource set
							book.setTitle("Another Book"); //$NON-NLS-1$
							rootWriter.getBooks().add(0, book);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update writer: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
		
		// check that we got events
		assertFalse(eventList.isEmpty());
		
		assertEquals(1, otherRes.getContents().size());
		Book book = (Book) otherRes.getContents().get(0);
		
		// now, check that we got a SET event on the book's author feature and
		//    an ADD on the writer's books feature
		boolean foundAuthorChange = false;
		boolean foundBooksChange = false;
		for (Iterator iter = eventList.iterator(); iter.hasNext();) {
			Notification next = (Notification) iter.next();
			
			if ((next.getNotifier() == book)
					&& (next.getFeature() == EXTLibraryPackage.eINSTANCE.getBook_Author())
					&& (next.getEventType() == Notification.SET)
					&& (next.getNewValue() == rootWriter)) {
				foundAuthorChange = true;
			} else if ((next.getNotifier() == rootWriter)
					&& (next.getFeature() == EXTLibraryPackage.eINSTANCE.getWriter_Books())
					&& (next.getEventType() == Notification.ADD)
					&& (next.getNewValue() == book)) {
				foundBooksChange = true;
			}
		}
		if (!foundAuthorChange) {
			fail("Did not find expected author change event"); //$NON-NLS-1$
		}
		if (!foundBooksChange) {
			fail("Did not find expected books change event"); //$NON-NLS-1$
		}
	}
	
	/**
	 * Tests support for cross-resource-set undo/redo using a resource set
	 * that delegates to multiple "sub" resource sets.
	 * 
	 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=110279">Bug 110279</a>
	 */
	public void test_crossResourceSetUndoRedo_110279() {
		// re-initialize our domain
		rset = new DelegatingResourceSet();
		domain = MEditingDomain.createNewDomain(rset);

		// associate the old resource set with this new domain
		MEditingDomain.setEditingDomain(testResource.getResourceSet(), domain);
		
		URI otherUri = URI.createPlatformResourceURI(
			otherProject.getName() + "/otherlibrary.extlibrary"); //$NON-NLS-1$
		final Resource otherRes = rset.createResource(otherUri);
		
		// check that the two resources we have are in different resource sets
		assertNotSame(testResource.getResourceSet(), otherRes.getResourceSet());
		
		// get a writer from the test resource, which we will modify
		final Writer rootWriter = (Writer) find(testResource, "root/Root Writer"); //$NON-NLS-1$
		assertNotNull(rootWriter);
		
		// do a cross-resource-set modification
		MUndoInterval undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							Book book = EXTLibraryFactory.eINSTANCE.createBook();
							otherRes.getContents().add(book);
							
							// make mods in both resources, each of which is
							//   in a different resource set
							
							// this is a local mod in one resource
							book.setTitle("Another Book"); //$NON-NLS-1$
							
							// this is a cross-resource mod
							rootWriter.getBooks().add(0, book);
							
							// this is a local mod in the other resource
							rootWriter.setAddress("Foo Street"); //$NON-NLS-1$
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update writer: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
		
		// check that the book is configured as expected
		assertEquals(1, otherRes.getContents().size());
		Book book = (Book) otherRes.getContents().get(0);
		assertEquals("Another Book", book.getTitle()); //$NON-NLS-1$
		assertEquals("Foo Street", rootWriter.getAddress()); //$NON-NLS-1$
		assertSame(rootWriter, book.getAuthor());
		assertTrue(rootWriter.getBooks().contains(book));
		
		// undo the cross-resource-set modification
		assertTrue(undo.canUndo());
		undo.undo();
		
		// check that the book does not exist in these resources
		assertEquals(0, otherRes.getContents().size());
		assertFalse("Another Book".equals(book.getTitle())); //$NON-NLS-1$
		assertFalse("Foo Street".equals(rootWriter.getAddress())); //$NON-NLS-1$
		assertNull(book.getAuthor());
		
		// redo the cross-resource-set modification
		assertTrue(undo.canRedo());
		undo.redo();
		
		// check that the book is once again configured as expected
		assertEquals(1, otherRes.getContents().size());
		assertSame(book, otherRes.getContents().get(0));
		assertEquals("Another Book", book.getTitle()); //$NON-NLS-1$
		assertEquals("Foo Street", rootWriter.getAddress()); //$NON-NLS-1$
		assertSame(rootWriter, book.getAuthor());
		assertTrue(rootWriter.getBooks().contains(book));
	}
	
	/**
	 * Tests the cross-resource-set cross-references.
	 * 
	 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=110279">Bug 110279</a>
	 */
	public void test_crossResourceSetCrossReferences_110279() {
		final Employee underling = EXTLibraryFactory.eINSTANCE.createEmployee();
		final Employee boss = EXTLibraryFactory.eINSTANCE.createEmployee();
		
		// re-initialize our domain
		rset = new DelegatingResourceSet();
		domain = MEditingDomain.createNewDomain(rset);

		// associate the old resource set with this new domain
		MEditingDomain.setEditingDomain(testResource.getResourceSet(), domain);
		
		URI otherUri = URI.createPlatformResourceURI(
			otherProject.getName() + "/otherlibrary.extlibrary"); //$NON-NLS-1$
		final Resource otherRes = rset.createResource(otherUri);
		
		// check that the two resources we have are in different resource sets
		assertNotSame(testResource.getResourceSet(), otherRes.getResourceSet());
		
		// do a cross-resource-set modification
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							// make mods in both resources, each of which is
							//   in a different resource set
							otherRes.getContents().add(underling);
							underling.setFirstName("Joe"); //$NON-NLS-1$
							underling.setLastName("Underling"); //$NON-NLS-1$
							
							root.getEmployees().add(boss);
							boss.setFirstName("Jane"); //$NON-NLS-1$
							boss.setLastName("Boss"); //$NON-NLS-1$
							
							// this is a unidirectional reference
							underling.setManager(boss);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update writer: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
		
		assertEquals("Underling", underling.getLastName()); //$NON-NLS-1$

		Collection xrefs = EObjectUtil.getReferencers(
			boss,
			new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});
		
		assertTrue(xrefs.contains(underling));
	}
	
	//
	// Framework methods
	//
	
	/** Create an editing domain on client-provided resource set. */
	protected MEditingDomain createEditingDomain() {
		rset = new MyResourceSet(project);
		
		return MEditingDomain.createNewDomain(rset);
	}
	
	protected void setUp()
		throws Exception {
		
		super.setUp();
		
		otherProject = ResourcesPlugin.getWorkspace().getRoot().getProject(
			OTHER_PROJECT_NAME);
		if (!otherProject.exists()) {
			otherProject.create(null);
		}
		
		otherProject.open(null);
		
		otherRset = MyResourceSet.getResourceSet(otherProject);
		assertNotNull(otherRset);
	}
	
	protected void tearDown()
		throws Exception {
		
		if ((otherProject != null) && otherProject.exists()) {
			otherProject.delete(true, true, null);
		}
		
		otherProject = null;
		otherRset = null;
		
		MyResourceSet.clearMap();
		
		super.tearDown();
	}
	
	IProject createProject(String name) {
		IProject result = ResourcesPlugin.getWorkspace().getRoot().getProject(name);
		
		if (!project.exists()) {
			try {
				result.create(null);
			} catch (CoreException e) {
				fail("Failed to create project: " + e.getLocalizedMessage()); //$NON-NLS-1$
			}
		}
		
		return result;
	}
	
	/**
	 * Custom resource set implementation for domain extensibility testing.
	 * This resource set provides a canonical mapping of workspace projects
	 * to resource sets and files to resources, after the fashion (though
	 * simplified) of the WTP's ProjectResourceSet in its EMF workbench
	 * integration.
	 *
	 * @author Christian W. Damus (cdamus)
	 */
	private static class MyResourceSet extends ResourceSetImpl {
		private static Map projectRsets = new java.util.HashMap();
		
		private final IProject project;
		
		MyResourceSet(IProject project) {
			super();
			
			this.project = project;
		}
		
		static void clearMap() {
			projectRsets.clear();
		}
		
		static ResourceSet getResourceSet(IProject project) {
			ResourceSet result = (ResourceSet) projectRsets.get(project);
			if (result == null) {
				result = new MyResourceSet(project);
				MEditingDomain.createNewDomain(result);
				projectRsets.put(project, result);
			}
			
			return result;
		}
		
		static ResourceSet getResourceSet(URI uri) {
			ResourceSet result = null;
			
			if ("platform".equals(uri.scheme())) { //$NON-NLS-1$
				// find the appropriate resource set to load this URI
				String projectName = uri.segment(1);
				
				IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(
					projectName);
				
				if (proj != null) {
					result = getResourceSet(proj);
				}
			}
			
			return result;
		}
		
		public IProject getProject() {
			return project;
		}
		
		public Resource getResource(URI uri, boolean loadOnDemand) {
			MyResourceSet delegate = (MyResourceSet) getResourceSet(uri);
					
			if ((delegate != null) && (delegate != this)) {
				return delegate.getResource0(uri, loadOnDemand);
			}
			
			return getResource0(uri, loadOnDemand);
		}
		
		public Resource createResource(URI uri) {
			MyResourceSet delegate = (MyResourceSet) getResourceSet(uri);
			
			if ((delegate != null) && (delegate != this)) {
				return delegate.createResource0(uri);
			}
			
			return createResource0(uri);
		}
		
		private Resource getResource0(URI uri, boolean loadOnDemand) {
			return super.getResource(uri, loadOnDemand);
		}
		
		private Resource createResource0(URI uri) {
			return super.createResource(uri);
		}
	}
	
	/**
	 * Another resource set implementation that just provides a facade
	 * delegating to the per-project <code>MyResourceSet</code>s.  Used in
	 * situations where we want only a single {@link MEditingDomain} to govern
	 * multiple resource sets.
	 *
	 * @author Christian W. Damus (cdamus)
	 */
	private static class DelegatingResourceSet extends ResourceSetImpl {
		DelegatingResourceSet() {
			super();
		}
		
		public Resource getResource(URI uri, boolean loadOnDemand) {
			Resource result;
			ResourceSet delegate = MyResourceSet.getResourceSet(uri);
					
			if (delegate != null) {
				result = delegate.getResource(uri, loadOnDemand);
				
				// associate it with the correct editing domain
				MEditingDomain.setEditingDomain(
					result.getResourceSet(),
					MEditingDomain.getEditingDomain(this));
			} else {
				result = super.getResource(uri, loadOnDemand);
			}
			
			return result;
		}
		
		public Resource createResource(URI uri) {
			Resource result;
			ResourceSet delegate = MyResourceSet.getResourceSet(uri);
			
			if (delegate != null) {
				result = delegate.createResource(uri);
				
				// associate it with the correct editing domain
				MEditingDomain.setEditingDomain(
					result.getResourceSet(),
					MEditingDomain.getEditingDomain(this));
			} else {
				result = super.createResource(uri);
			}
			
			return result;
		}
		
	}
}
