/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */

package org.eclipse.gmf.tests.runtime.emf.core;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.IOWrappedException;
import org.eclipse.emf.ecore.xmi.ClassNotFoundException;
import org.eclipse.emf.ecore.xmi.FeatureNotFoundException;
import org.eclipse.emf.ecore.xmi.PackageNotFoundException;

import org.eclipse.emf.examples.library.Library;
import org.eclipse.emf.examples.library.RMPLibraryFactory;
import org.eclipse.gmf.runtime.emf.core.edit.MResourceOption;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLRuntimeException;


/**
 * Regression tests for resolved defects.
 *
 * @author Christian W. Damus (cdamus)
 */
public class RegressionTestCase
	extends BaseCoreTests {

	public RegressionTestCase(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(RegressionTestCase.class, "Defect Regression Tests"); //$NON-NLS-1$
	}
	
	/** Tests undo/redo of addition of a root element to a resource. */
	public void test_undoRootElementAdd_RATLC00537680() {
		if (writing()) {
			Library lib = RMPLibraryFactory.eINSTANCE.createLibrary();
			lib.setName("NewlyAdded"); //$NON-NLS-1$
			testResource.getContents().add(lib);
		} else {
			MUndoInterval undo = getLastUndo();
			
			// try undoing
			undo.undo();
			
			// should have removed the root that we added
			assertNotFound("NewlyAdded"); //$NON-NLS-1$
			
			// try redoing
			undo.redo();
			
			// should be able to find it again
			assertFound("NewlyAdded"); //$NON-NLS-1$
		}
	}

	/** Tests undo/redo of removal of a root element to a resource. */
	public void test_undoRootElementRemove_RATLC00537680() {
		if (writing()) {
			testResource.getContents().remove(root);
		} else {
			MUndoInterval undo = getLastUndo();
			
			// try undoing
			undo.undo();
			
			// should be able to find it again
			assertFound("root"); //$NON-NLS-1$
			
			// try redoing
			undo.redo();
			
			// should have removed it again			
			assertNotFound("root"); //$NON-NLS-1$
		}
	}

	/** Tests undo/redo of moving of a root element in a resource. */
	public void test_undoRootElementMove_RATLC00537680() {
		final Library secondRoot = RMPLibraryFactory.eINSTANCE.createLibrary();
		secondRoot.setName("secondRoot"); //$NON-NLS-1$
		
		// first, add a second root that we can move
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
						public Object run() {
							testResource.getContents().add(secondRoot);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail(e);
				}
			}});
		
		// now, move it
		MUndoInterval undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
						public Object run() {
							testResource.getContents().move(0, secondRoot);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail(e);
				}
			}});
		
		assertEquals(0, testResource.getContents().indexOf(secondRoot));
		
		// try undoing
		undo.undo();
		
		// should be at position 1 again
		assertEquals(1, testResource.getContents().indexOf(secondRoot));
		
		// try redoing
		undo.redo();
		
		// should be back to position 0	
		assertEquals(0, testResource.getContents().indexOf(secondRoot));
	}
	
	/**
	 * Tests that we can successfully load a resource in "compatibility mode"
	 * when it has an unresolved schema reference.
	 */
	public void test_loadWithUnresolvedPackage_RATLC00537775() {
		Resource res = createTestResource("test_unknownPackage.rmplibrary"); //$NON-NLS-1$
		
		try {
			// try regular mode
			domain.loadResource(res);
			fail("Should not have loaded successfully."); //$NON-NLS-1$
		} catch (MSLRuntimeException e) {
			Throwable t = e.getCause();
			assertTrue(t instanceof IOWrappedException);
			
			t = ((IOWrappedException) t).getWrappedException();
			assertTrue(t instanceof PackageNotFoundException);
		}
		
		try {
			// try compatibility mode
			domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
//		 TODO: Restore when EMF BasicExtendedMetaData reusability issue is addressed
//		try {
//			// try saving
//			domain.saveResource(res, MResourceOption.COMPATIBILITY_MODE);
//			domain.unloadResource(res);
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
//		}
//		
//		try {
//			// try loading again in compatibility mode
//			domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
//		}
	}
	
	/**
	 * Tests that we can successfully load a resource in "compatibility mode"
	 * when it has an unresolved class reference.
	 */
	public void test_loadWithUnresolvedClass_RATLC00537775() {
		Resource res = createTestResource("test_unknownClass.rmplibrary"); //$NON-NLS-1$
		
		try {
			// try regular mode
			domain.loadResource(res);
			fail("Should not have loaded successfully."); //$NON-NLS-1$
		} catch (MSLRuntimeException e) {
			Throwable t = e.getCause();
			assertTrue(t instanceof IOWrappedException);
			
			t = ((IOWrappedException) t).getWrappedException();
			assertTrue(t instanceof ClassNotFoundException);
		}
		
		try {
			// try compatibility mode
			domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}

// TODO: Restore when EMF BasicExtendedMetaData reusability issue is addressed
//		try {
//			// try saving
//			domain.saveResource(res, MResourceOption.COMPATIBILITY_MODE);
//			domain.unloadResource(res);
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
//		}
//		
//		try {
//			// try loading again in compatibility mode
//			domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
//		}
	}
	
	/**
	 * Tests that we can successfully load a resource in "compatibility mode"
	 * when it has an unresolved feature reference.
	 */
	public void test_loadWithUnresolvedFeature_RATLC00537775() {
		Resource res = createTestResource("test_unknownFeature.rmplibrary"); //$NON-NLS-1$
		
		try {
			// try regular mode
			domain.loadResource(res);
			fail("Should not have loaded successfully."); //$NON-NLS-1$
		} catch (MSLRuntimeException e) {
			Throwable t = e.getCause();
			assertTrue(t instanceof IOWrappedException);
			
			t = ((IOWrappedException) t).getWrappedException();
			assertTrue(t instanceof FeatureNotFoundException);
		}
		
		try {
			// try compatibility mode
			domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		
//		 TODO: Restore when EMF BasicExtendedMetaData reusability issue is addressed
//		try {
//			// try saving
//			domain.saveResource(res, MResourceOption.COMPATIBILITY_MODE);
//			domain.unloadResource(res);
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
//		}
//		
//		try {
//			// try loading again in compatibility mode
//			domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
//		}
	}
}
