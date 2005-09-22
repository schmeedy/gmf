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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.Bundle;

import org.eclipse.emf.examples.extlibrary.AudoVisualItem;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Periodical;
import org.eclipse.emf.examples.extlibrary.Person;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.emf.examples.extlibrary.util.EXTLibrarySwitch;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MResourceOption;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.internal.commands.MSLUndoStack;
import org.eclipse.gmf.runtime.emf.core.internal.commands.MSLUndoStack.ActionLockMode;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;
import org.eclipse.gmf.runtime.emf.core.internal.resources.AbstractResourceWrapper;
import org.eclipse.gmf.runtime.emf.core.internal.resources.LogicalResourceWrapper;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResource;


/**
 * Mini-framework class for logical resource test cases.
 *
 * @author Christian W. Damus (cdamus)
 */
public abstract class BaseLogicalResourceTest extends TestCase {
	static final Bundle MslCoreTestsBundle =
		Platform.getBundle("org.eclipse.gmf.tests.runtime.emf.core"); //$NON-NLS-1$
	
	protected static final String PROJECT_NAME = "logrestest"; //$NON-NLS-1$
	protected static final String RESOURCE_NAME = "/" + PROJECT_NAME + "/logres.extlibrary";  //$NON-NLS-1$//$NON-NLS-2$
	protected static final String RESOURCE_NAME2 = "/" + PROJECT_NAME + "/logres2.extlibrary";  //$NON-NLS-1$//$NON-NLS-2$
	protected static final String SUBUNIT_NAME1 = "/" + PROJECT_NAME + "/logres.1.extlibrary";  //$NON-NLS-1$//$NON-NLS-2$
	protected static final String SUBUNIT_NAME2 = "/" + PROJECT_NAME + "/logres.2.extlibrary";  //$NON-NLS-1$//$NON-NLS-2$
	protected static final String SUBUNIT_NAME3 = "/" + PROJECT_NAME + "/logres.3.extlibrary";  //$NON-NLS-1$//$NON-NLS-2$
	
	protected MEditingDomain domain;
	private boolean isReading;
	
	protected IProject project;
	protected ILogicalResource logres;
	
	//
	// Model structure created by setUp():
	//
	// root
	//  +- level1
	//  |    +- level2-subunit  (== subunit1)
	//  |          +- level3
	//  |               +- level4-subunit   (== subunit2)
	//  +- level1-1
	//       +- level2-1-subunit   (== subunit3)
	//
	// In each library at each level, there is a book and a writer, named
	// according to the library name (nesting level).
	// 
	
	protected Library root;
	protected Library subunit1;
	protected Library subunit2;
	protected Library subunit3;

	/**
	 * Initializes me with my name.
	 * 
	 * @param name my name
	 */
	protected BaseLogicalResourceTest(String name) {
		super(name);
	}

	/**
	 * Test suite encompassing all of the logical resource tests.
	 * 
	 * @return the suite of logical resource tests
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("MSL Logical Resource Tests"); //$NON-NLS-1$

		suite.addTest(LogicalResourceTest.suite());
		suite.addTest(CrossReferenceTest.suite());
		suite.addTest(IncrementalLoadingTest.suite());
		suite.addTest(RefactoringTest.suite());
		suite.addTest(LogicalResourcePolicyManagerTest.suite());
		suite.addTest(LogicalResourceWrapperTest.suite());
		suite.addTest(UnmodifiableResourceViewTest.suite());
		suite.addTest(ErrorConditionsTest.suite());
		suite.addTest(RegressionTest.suite());
		suite.addTest(GarbageCollectionTest.suite());

		return suite;
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
	
		domain = MEditingDomain.INSTANCE;
		
		startWriting();
		
		createLogicalResource(RESOURCE_NAME);
		
		initializeTestModel();
	}

	protected void tearDown()
		throws Exception {
		
		stopWriting();  // in case a write action is left open
		stopReading();  // in case a read action is left open
		
		unloadLogicalResource();
		
		// clean up any others that a unit test may have created
		for (Iterator iter = new java.util.HashSet(domain.getResourceSet().getResources()).iterator(); iter.hasNext();) {
			unloadResource((Resource) iter.next());
		}
		
		subunit1 = null;
		root = null;
		
		if ((project != null) && project.exists()) {
			project.delete(true, true, null);
		}
		
		project = null;
		
		domain = null;
	}

	//
	// Other framework methods
	//
	
	/**
	 * Initializes the test model by loading it and stores certain key
	 * elements in the <tt>root</tt> and <tt>subunit<i>n</i></tt>
	 * variables.
	 */
	protected void initializeTestModel() {
		loadFromTestModel(logres);
		
		root = (Library) find("root"); //$NON-NLS-1$
		subunit1 = (Library) find(root, "level1/level2-subunit"); //$NON-NLS-1$
		subunit2 = (Library) find(subunit1, "level3/level4-subunit"); //$NON-NLS-1$
		subunit3 = (Library) find(root, "level1-1/level2-1-subunit"); //$NON-NLS-1$
	}
	
	/**
	 * Populates the specified resource with data from the test model file.
	 * 
	 * @param res the resource to load
	 */
	protected void loadFromTestModel(ILogicalResource res) {
		URI original = res.getURI();
		
		try {
			res.setURI(URI.createURI(MslCoreTestsBundle.getEntry(
				"/test_models/test_model.extlibrary").toString())); //$NON-NLS-1$
		
			// no special options needed to load the monolithic test model
			res.load(Collections.EMPTY_MAP);
		} catch (IOException e) {
			fail("Failed to load test model: " + e.getLocalizedMessage()); //$NON-NLS-1$
		} finally {
			res.setURI(original);
		}
	}
	
	/**
	 * Unloads the test resource.
	 */
	protected void unloadLogicalResource() {
		unloadLogicalResource(logres);
		
		logres = null;
	}

	/**
	 * Unloads the specified resource.
	 * 
	 * @param res the resource to unload
	 */
	protected void unloadResource(Resource res) {
		if (res != null) {
			if (res.isLoaded()) {
				res.unload();
			}
			
			if (res.getResourceSet() != null) {
				res.getResourceSet().getResources().remove(res);
			}
		}
	}

	/**
	 * Unloads the specified resource.
	 * 
	 * @param res the resource to unload
	 */
	protected void unloadLogicalResource(ILogicalResource res) {
		unloadResource(AbstractResourceWrapper.unwrap(res));
	}

	/**
	 * Creates a new logical resource on the specified path and assigns it
	 * to the <tt>logres</tt> test resource variable.
	 * 
	 * @param path a path relative to the workspace root
	 */
	protected void createLogicalResource(String path) {
		logres = createNewLogicalResource(
			URI.createPlatformResourceURI(path));
	}

	/**
	 * Creates a new logical resource on the specified URI.
	 * 
	 * @param uri the new resource's URI
	 * @return the new resource (not assigned to <tt>logres</tt>)
	 */
	protected ILogicalResource createNewLogicalResource(URI uri) {
		unloadResource(
			MEditingDomain.INSTANCE.getResourceSet().getResource(uri, false));
		
		return domain.asLogicalResource(
			domain.createResource(
				uri.toString(),
				MResourceOption.URI));
	}

	/**
	 * Saves the test resource.  This method stops writing and
	 * resumes again after saving.
	 */
	protected void saveLogicalResource() {
		saveLogicalResource(logres);
	}

	/**
	 * Saves the specified logical resource.  This method stops writing and
	 * resumes again after saving.
	 * 
	 * @param res the resource to save
	 */
	protected void saveLogicalResource(ILogicalResource res) {
		try {
			stopWriting();
			
			res.save(Collections.EMPTY_MAP);
			
			startWriting();
		} catch (IOException e) {
			fail("Exception in saving: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Loads the test resource from its workspace location, initially loading
	 * all units.
	 */
	protected void loadLogicalResource() {
		loadLogicalResource(true);
	}

	/**
	 * Loads the test resource.
	 * 
	 * @param loadAllUnits whether to initially load all units.  If
	 *    <code>false</code>, units are not auto-loaded
	 */
	protected void loadLogicalResource(boolean loadAllUnits) {
		loadLogicalResource(logres, loadAllUnits, false);
	}

	/**
	 * Loads the test resource.
	 * 
	 * @param loadAllUnits whether to initially load all units
	 * @param autoLoadUnits if <code>loadAllUnits</code> is <code>false</code>,
	 *     whether to auto-load the units
	 */
	protected void loadLogicalResource(boolean loadAllUnits, boolean autoLoadUnits) {
		loadLogicalResource(logres, loadAllUnits, autoLoadUnits);
	}

	/**
	 * Loads the specified resource from its workspace location, initially loading
	 * all units.
	 * 
	 * @param res the resource to load
	 */
	protected void loadLogicalResource(ILogicalResource res) {
		loadLogicalResource(res, true, false);
	}

	/**
	 * Loads the specified resource.
	 * 
	 * @param res the resource to load
	 * @param loadAllUnits whether to initially load all units
	 * @param autoLoadUnits if <code>loadAllUnits</code> is <code>false</code>,
	 *     whether to auto-load the units
	 */
	protected void loadLogicalResource(ILogicalResource res, boolean loadAllUnits, boolean autoLoadUnits) {
		Map options = new java.util.HashMap();
		
		options.put(ILogicalResource.OPTION_LOAD_ALL_UNITS,
			Boolean.valueOf(loadAllUnits));
		options.put(ILogicalResource.OPTION_AUTO_LOAD_UNITS,
			Boolean.valueOf(autoLoadUnits));
		
		loadLogicalResource(res, options);
	}

	/**
	 * Loads the test resource with options.
	 * 
	 * @param res the resource to load
	 * @param options the load options to apply
	 */
	protected void loadLogicalResource(ILogicalResource res, Map options) {
		try {
			res.load(options);
			
			// verify that none of the physical resources was accidentally
			//    loaded by the resource set containing the logical resource,
			//    if the resource is not a wrapper
			if (!(res instanceof LogicalResourceWrapper)) {
				Set resources = new java.util.HashSet();
				resources.addAll(res.getMappedResources().values());
				assertFalse(resources.removeAll(res.getResourceSet().getResources()));
			}
		} catch (IOException e) {
			fail("Exception in loading: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Finds the object in the test model having the specified qualified name.
	 * 
	 * @param qname a slash-delimited qualified name
	 * @return the matching object, or <code>null</code> if not found
	 */
	protected EObject find(String qname) {
		return find(logres, qname);
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
	
	/** Looks up a library by its URI fragment. */
	protected Library lookupLibrary(String fragment) {
		return lookupLibrary(logres, fragment);
	}

	/** Looks up a library by its URI fragment. */
	protected Library lookupLibrary(ILogicalResource res, String fragment) {
		return (Library) res.getEObject(fragment);
	}

	/** Looks up a writer by its URI fragment. */
	protected Writer lookupWriter(String fragment) {
		return lookupWriter(logres, fragment);
	}

	/** Looks up a writer by its URI fragment. */
	protected Writer lookupWriter(ILogicalResource res, String fragment) {
		return (Writer) res.getEObject(fragment);
	}

	/** Looks up a book by its URI fragment. */
	protected Book lookupBook(String fragment) {
		return lookupBook(logres, fragment);
	}

	/** Looks up a book by its URI fragment. */
	protected Book lookupBook(ILogicalResource res, String fragment) {
		return (Book) res.getEObject(fragment);
	}

	/**
	 * Opens a write action (if necessary) in an undo interval (if necessary).
	 */
	protected void startWriting() {
		MSLUndoStack stack = ((MSLEditingDomain) domain).getUndoStack();
		
		if (!stack.isUndoIntervalOpen()) {
			stack.openUndoInterval("Test", "Logical Resource Test");  //$NON-NLS-1$//$NON-NLS-2$
		}
		
		if (!stack.isWriteActionInProgress()) {
			stack.startAction(ActionLockMode.WRITE);
		}
	}

	/**
	 * Stops writing, if we were writing.
	 * 
	 * @return the closed undo interval, if any needed to be closed; otherwise
	 *    <code>null</code>
	 */
	protected MUndoInterval stopWriting() {
		MSLUndoStack stack = ((MSLEditingDomain) domain).getUndoStack();
		MUndoInterval result = null;
		
		if (stack.isWriteActionInProgress()) {
			stack.completeAction();
		}
		
		if (stack.isUndoIntervalOpen()) {
			result = stack.closeUndoInterval();
		}
		
		return result;
	}

	/**
	 * Opens a read action (if necessary).
	 */
	protected void startReading() {
		MSLUndoStack stack = ((MSLEditingDomain) domain).getUndoStack();
		stack.startAction(ActionLockMode.READ);
		isReading = true;
	}

	/**
	 * Closes a read action, if one was open.
	 */
	protected void stopReading() {
		MSLUndoStack stack = ((MSLEditingDomain) domain).getUndoStack();
		if (isReading) {
			isReading = false;
			stack.completeAction();
		}
	}
	
	/**
	 * Gets the file corresponding to the specified workspace path.
	 * 
	 * @param path a path relative to the workspace root
	 * @return the corresponding file
	 */
	protected IFile getFile(String path) {
		return getFile(new Path(path));
	}
	
	/**
	 * Gets the file corresponding to the specified workspace path.
	 * 
	 * @param path a path relative to the workspace root
	 * @return the corresponding file
	 */
	protected IFile getFile(IPath path) {
		IWorkspaceRoot wsRoot = (IWorkspaceRoot) project.getParent();
		return wsRoot.getFile(path);
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
	 * Switch to compute the names of library objects.
	 *
	 * @author Christian W. Damus (cdamus)
	 */
	private static final class GetName extends EXTLibrarySwitch {
		static final GetName INSTANCE = new GetName();
		
		private GetName() {
			super();
		}
		
		public Object caseAudoVisualItem(AudoVisualItem object) {
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
