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
package org.eclipse.gmf.tests.runtime.emf.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.examples.extlibrary.AudioVisualItem;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Periodical;
import org.eclipse.emf.examples.extlibrary.Person;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.emf.examples.extlibrary.util.EXTLibrarySwitch;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MResourceOption;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.osgi.framework.Bundle;


/**
 * Base TestCase for all emf core tests.
 * 
 * @author Christian W. Damus (cdamus)
 * @author mgoyal
 */
public class BaseCoreTests
	extends TestCase {
	public static final Bundle MslCoreTestsBundle =
		Platform.getBundle("org.eclipse.gmf.tests.runtime.emf.core"); //$NON-NLS-1$

	private MRunnable mrun;
	private MUndoInterval lastUndo;
	
	protected IProject project;
	protected MEditingDomain domain;
	protected Resource testResource;
	protected Library root;
	
	protected static final String PROJECT_NAME = "mslcoretests"; //$NON-NLS-1$
	protected static final String RESOURCE_NAME = "/" + PROJECT_NAME + "/logres.extlibrary";  //$NON-NLS-1$//$NON-NLS-2$

	public BaseCoreTests() {
		super();
	}
	
	public BaseCoreTests(String name) {
		super(name);
	}
	
	//
	// Test configuration methods
	//
	
	protected void setUp()
		throws Exception {
		
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_NAME);
		if (!project.exists()) {
			project.create(null);
		}
		
		project.open(null);
	
		domain = createEditingDomain();
		
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
						public Object run() {
							try {
								Resource originalRes = domain.createResource(URI
									.createURI(MslCoreTestsBundle.getEntry(
										"/test_models/test_model.extlibrary") //$NON-NLS-1$
										.toString()).toString(), MResourceOption.URI);
								domain.loadResource(originalRes);
								
								originalRes.setURI(URI.createPlatformResourceURI(RESOURCE_NAME));
								originalRes.save(Collections.EMPTY_MAP);
								testResource = originalRes;
								root = (Library) find("root"); //$NON-NLS-1$
							} catch (IOException e) {
								fail("Failed to load test model: " + e.getLocalizedMessage()); //$NON-NLS-1$
								
							}
							return testResource;
						}
					});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to load test model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
				
			}
		});
	}
	
	/** May be overridden by subclasses to create non-default editing domains. */
	protected MEditingDomain createEditingDomain() {
		return MEditingDomain.INSTANCE;
	}

	protected void tearDown()
		throws Exception {
		
		lastUndo = null;
		
		root = null;
		if (testResource != null) {
			if (testResource.isLoaded()) {
				testResource.unload();
			}
			
			if (testResource.getResourceSet() != null) {
				testResource.getResourceSet().getResources().remove(testResource);
			}
			testResource = null;
		}
		
		if ((project != null) && project.exists()) {
			project.delete(true, true, null);
		}
		
		project = null;
		domain = null;
	}

	//
	// Other framework methods
	//
	
	protected Resource createTestResource(String name) {
		Resource result = null;
		
		try {
			InputStream input =
				MslCoreTestsBundle.getEntry("/test_models/" + name).openStream(); //$NON-NLS-1$
			
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(
				new Path(PROJECT_NAME + '/' + name));
			file.create(input, true, null);
			
			result = domain.createResource(
				URI.createPlatformResourceURI(file.getFullPath().toString()).toString(),
				MResourceOption.URI);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception creating test resource: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
		return result;
	}
	
	/**
	 * Must be called first in every test method that needs to run in a write
	 * action.  If we are not in a write action, we return <code>false</code>.
	 * However, in this case, we do also re-execute the original test method
	 * inside of a new write action, in which nested execution this method will
	 * return <code>true</code>.  Therefore, the entire test method should be
	 * in an <code>if</code> block conditional on this result.  Following this
	 * <code>if</code> block, it is safe to access the undo interval created
	 * during the test via the {@link #getLastUndo()} method in an
	 * <code>else</code> block.
	 * <p>
	 * Example:
	 * </p>
	 * <pre>
	 *     if (writing()) {
	 *        // ... do stuff in a write action ...
	 *     }
	 * </pre>
	 * 
	 * @return whether we are in a write action or not
	 * 
	 * @see #getLastUndo()
	 */
	protected boolean writing() {
		boolean result = (mrun != null);
		
		if (!result) {
			mrun = new MRunnable() {
				public Object run() {
					try {
						runTest();
					} catch (AssertionFailedError e) {
						throw e;
					} catch (Exception e) {
						e.printStackTrace();
						fail("Unexpected exception: " + e.getLocalizedMessage()); //$NON-NLS-1$
					} catch (Throwable t) {
						throw (Error) t;
					} finally {
						mrun = null;
					}
					
					return null;
				}};
				
			lastUndo = domain.runInUndoInterval(new Runnable() {
				public void run() {
					try {
						domain.runAsWrite(mrun);
					} catch (MSLActionAbandonedException e) {
						fail("Write action abandoned: " + e.getLocalizedMessage()); //$NON-NLS-1$
					}
				}});
		}
		
		return result;
	}
	
	/**
	 * Gets the undo interval created by the test within its
	 * <pre>
	 *     if (writing()) {
	 *        // ... do stuff in a write action ...
	 *     } else {
	 *         MUndoInterval undo = getLastUndo();
	 *         
	 *         // ... do stuff with the undo interval ...
	 *     }
	 * </pre>
	 * block.
	 * 
	 * @return the test's undo interval
	 * 
	 * @see #writing()
	 */
	protected MUndoInterval getLastUndo() {
		return lastUndo;
	}
	
	/**
	 * Records a failure due to an exception that should not have been thrown.
	 * 
	 * @param e the exception
	 */
	protected void fail(Exception e) {
		e.printStackTrace();
		fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
	}
	
	/**
	 * Asserts that we can find an object having the specified name.
	 * 
	 * @param name the name to seek
	 * 
	 * @see #find(String)
	 */
	protected void assertFound(String name) {
		assertNotNull("Did not find " + name, find(testResource, name)); //$NON-NLS-1$
	}
	
	/**
	 * Asserts that we can find an object having the specified name, relative
	 * to the specified starting object.
	 * 
	 * @param start the object from which to start looking (to which the
	 *     <code>name</code> is relative).  This can be a resource or an
	 *     element
	 * @param name the name to seek
	 * 
	 * @see #find(Object, String)
	 */
	protected void assertFound(Object start, String name) {
		assertNotNull("Did not find " + name, find(testResource, name)); //$NON-NLS-1$
	}
	
	/**
	 * Asserts that we cannot find an object having the specified name.
	 * 
	 * @param name the name to (not) seek
	 * 
	 * @see #find(String)
	 */
	protected void assertNotFound(String name) {
		assertNull("Found " + name, find(testResource, name)); //$NON-NLS-1$
	}
	
	/**
	 * Asserts that we cannot find an object having the specified name, relative
	 * to the specified starting object.
	 * 
	 * @param start the object from which to start looking (to which the
	 *     <code>name</code> is relative).  This can be a resource or an
	 *     element
	 * @param name the name to (not) seek
	 * 
	 * @see #find(Object, String)
	 */
	protected void assertNotFound(Object start, String name) {
		assertNull("Found " + name, find(testResource, name)); //$NON-NLS-1$
	}
	
	/**
	 * Finds the object in the test model having the specified qualified name.
	 * 
	 * @param qname a slash-delimited qualified name
	 * @return the matching object, or <code>null</code> if not found
	 */
	protected EObject find(String qname) {
		return find(testResource, qname);
	}
	
	/**
	 * Finds the object in the test model having the specified qualified name,
	 * starting from some object.
	 * 
	 * @param object the starting object (resource or element)
	 * @param qname a slash-delimited qualified name, relative to the
	 *     provided <code>object</code>
	 * @return the matching object, or <code>null</code> if not found
	 */
	protected EObject find(Object start, String qname) {
		EObject result = null;
		Object current = start;
		
		String[] names = tokenize(qname);
		
		for (int i = 0; (current != null) && (i < names.length); i++) {
			String name = names[i];
			result = null;
			
			for (Iterator iter = getContents(current).iterator(); iter.hasNext();) {
				EObject child = (EObject) iter.next();
				
				if (name.equals(getName(child))) {
					result = child;
					break;
				}
			}
			
			current = result;
		}
		
		return result;
	}

	/**
	 * Gets the name of a library object.
	 * 
	 * @param object the object
	 * @return its name
	 */
	private String getName(EObject object) {
		return (String) GetName.INSTANCE.doSwitch(object);
	}
	
	/**
	 * Gets the contents of an object.
	 * 
	 * @param object an object, which may be a resource or an element
	 * @return its immediate contents (children)
	 */
	private List getContents(Object object) {
		if (object instanceof EObject) {
			return ((EObject) object).eContents();
		} else if (object instanceof Resource) {
			return ((Resource) object).getContents();
		} else {
			return Collections.EMPTY_LIST;
		}
	}
	
	/**
	 * Tokenizes a qualified name on the slashes.
	 * 
	 * @param qname a qualified name
	 * @return the parts between the slashes
	 */
	private String[] tokenize(String qname) {
		return qname.split("/"); //$NON-NLS-1$
	}
	
	/**
	 * Switch to compute the names of library objects.
	 *
	 * @author Christian W. Damus (cdamus)
	 */
	private static final class GetName extends EXTLibrarySwitch {
		static final GetName INSTANCE = new GetName();
		
		private GetName() {
			super();
		}
		
		public Object caseAudoVisualItem(AudioVisualItem object) {
			return object.getTitle();
		}

		public Object caseBook(Book object) {
			return object.getTitle();
		}

		public Object caseLibrary(Library object) {
			return object.getName();
		}

		public Object casePeriodical(Periodical object) {
			return object.getTitle();
		}
		
		public Object caseWriter(Writer object) {
			return object.getName();
		}

		public Object casePerson(Person object) {
			if (object.getFirstName() == null) {
				if (object.getLastName() == null) {
					return ""; //$NON-NLS-1$
				} else {
					return object.getLastName();
				}
			} else if (object.getLastName() == null) {
				return object.getFirstName();
			} else {
				StringBuffer result = new StringBuffer();

				result.append(object.getFirstName()).append(' ').append(
					object.getLastName());

				return result.toString();
			}
		}

		public Object defaultCase(EObject object) {
			return ""; //$NON-NLS-1$
		}
	}
}
