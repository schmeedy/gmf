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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.Resource.IOWrappedException;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.ClassNotFoundException;
import org.eclipse.emf.ecore.xmi.FeatureNotFoundException;
import org.eclipse.emf.ecore.xmi.PackageNotFoundException;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.gmf.runtime.emf.core.edit.MResourceOption;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLRuntimeException;
import org.eclipse.gmf.runtime.emf.core.internal.exceptions.AbortResourceLoadException;
import org.eclipse.gmf.runtime.emf.core.internal.resources.MSLResource;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLUtil;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;

/**
 * Regression tests for resolved defects.
 * 
 * @author Christian W. Damus (cdamus)
 */
public class RegressionTestCase extends BaseCoreTests {

	private Resource resource = null;

	public RegressionTestCase(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(RegressionTestCase.class,
				"Defect Regression Tests"); //$NON-NLS-1$
	}

	/** Tests undo/redo of addition of a root element to a resource. */
	public void test_undoRootElementAdd_RATLC00537680() {
		if (writing()) {
			Library lib = EXTLibraryFactory.eINSTANCE.createLibrary();
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
		final Library secondRoot = EXTLibraryFactory.eINSTANCE.createLibrary();
		secondRoot.setName("secondRoot"); //$NON-NLS-1$

		// first, add a second root that we can move
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
						public Object run() {
							testResource.getContents().add(secondRoot);
							return null;
						}
					});
				} catch (MSLActionAbandonedException e) {
					fail(e);
				}
			}
		});

		// now, move it
		MUndoInterval undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
						public Object run() {
							testResource.getContents().move(0, secondRoot);
							return null;
						}
					});
				} catch (MSLActionAbandonedException e) {
					fail(e);
				}
			}
		});

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
		Resource res = createTestResource("test_unknownPackage.extlibrary"); //$NON-NLS-1$

		try {
			// try regular mode
			domain.loadResource(res);
			fail("Should not have loaded successfully."); //$NON-NLS-1$
		} catch (MSLRuntimeException e) {
			Throwable t = e.getCause();
			assertTrue(t instanceof IOWrappedException);

			t = ((IOWrappedException) t).getWrappedException();
            assertTrue(t instanceof AbortResourceLoadException);
            
            t = ((AbortResourceLoadException) t).getCause();
			assertTrue(t instanceof PackageNotFoundException);
		}

		try {
			// try compatibility mode
			domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}

		// TODO: Restore when EMF BasicExtendedMetaData reusability issue is
		// addressed
		// try {
		// // try saving
		// domain.saveResource(res, MResourceOption.COMPATIBILITY_MODE);
		// domain.unloadResource(res);
		// } catch (Exception e) {
		// e.printStackTrace();
		// fail("Should not have thrown: " + e.getLocalizedMessage());
		// //$NON-NLS-1$
		// }
		//		
		// try {
		// // try loading again in compatibility mode
		// domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
		// } catch (Exception e) {
		// e.printStackTrace();
		// fail("Should not have thrown: " + e.getLocalizedMessage());
		// //$NON-NLS-1$
		// }
	}

	/**
	 * Tests that we can successfully load a resource in "compatibility mode"
	 * when it has an unresolved class reference.
	 */
	public void test_loadWithUnresolvedClass_RATLC00537775() {
		Resource res = createTestResource("test_unknownClass.extlibrary"); //$NON-NLS-1$

		try {
			// try regular mode
			domain.loadResource(res);
			fail("Should not have loaded successfully."); //$NON-NLS-1$
		} catch (MSLRuntimeException e) {
			Throwable t = e.getCause();
			assertTrue(t instanceof IOWrappedException);

            t = ((IOWrappedException) t).getWrappedException();
            assertTrue(t instanceof AbortResourceLoadException);
            
            t = ((AbortResourceLoadException) t).getCause();
            assertTrue(t instanceof ClassNotFoundException);
		}

		try {
			// try compatibility mode
			domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}

		// TODO: Restore when EMF BasicExtendedMetaData reusability issue is
		// addressed
		// try {
		// // try saving
		// domain.saveResource(res, MResourceOption.COMPATIBILITY_MODE);
		// domain.unloadResource(res);
		// } catch (Exception e) {
		// e.printStackTrace();
		// fail("Should not have thrown: " + e.getLocalizedMessage());
		// //$NON-NLS-1$
		// }
		//		
		// try {
		// // try loading again in compatibility mode
		// domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
		// } catch (Exception e) {
		// e.printStackTrace();
		// fail("Should not have thrown: " + e.getLocalizedMessage());
		// //$NON-NLS-1$
		// }
	}

	/**
	 * Tests that we can successfully load a resource in "compatibility mode"
	 * when it has an unresolved feature reference.
	 */
	public void test_loadWithUnresolvedFeature_RATLC00537775() {
		Resource res = createTestResource("test_unknownFeature.extlibrary"); //$NON-NLS-1$

		try {
			// try regular mode
			domain.loadResource(res);
			fail("Should not have loaded successfully."); //$NON-NLS-1$
		} catch (MSLRuntimeException e) {
			Throwable t = e.getCause();
			assertTrue(t instanceof IOWrappedException);

            t = ((IOWrappedException) t).getWrappedException();
            assertTrue(t instanceof AbortResourceLoadException);
            
            t = ((AbortResourceLoadException) t).getCause();
            assertTrue(t instanceof FeatureNotFoundException);
		}

		try {
			// try compatibility mode
			domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Should not have thrown: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}

		// TODO: Restore when EMF BasicExtendedMetaData reusability issue is
		// addressed
		// try {
		// // try saving
		// domain.saveResource(res, MResourceOption.COMPATIBILITY_MODE);
		// domain.unloadResource(res);
		// } catch (Exception e) {
		// e.printStackTrace();
		// fail("Should not have thrown: " + e.getLocalizedMessage());
		// //$NON-NLS-1$
		// }
		//		
		// try {
		// // try loading again in compatibility mode
		// domain.loadResource(res, MResourceOption.COMPATIBILITY_MODE);
		// } catch (Exception e) {
		// e.printStackTrace();
		// fail("Should not have thrown: " + e.getLocalizedMessage());
		// //$NON-NLS-1$
		// }
	}

	/**
	 * Tests that the MSLUtil::addObject() method does the correct thing when
	 * adding an object that is already in the list. It formerly attempted to
	 * move the object, but moved the wrong one.
	 */
	public void test_MSLUtil_addObject_113261() {
		EList list = new BasicEList();

		list.add("a"); //$NON-NLS-1$
		list.add("b"); //$NON-NLS-1$
		list.add("c"); //$NON-NLS-1$
		list.add("d"); //$NON-NLS-1$

		Object object = list.get(3);

		// on a copy of this list, do the correct change
		EList expected = new BasicEList(list);
		expected.move(0, 3);

		MSLUtil.addObject(list, object, 0);

		// check that MSLUtil made the correct change
		assertEquals(expected, list);
	}

	/**
	 * Tests consistency of the <code>isTrackingModification()</code> method
	 * with the actual tracking of modification provided by the
	 * <code>MSLObjectListener</code>.
	 */
	public void test_modificationTracking_112531() {
		class TestAdapter extends AdapterImpl {
			boolean modified;

			public void notifyChanged(Notification msg) {
				if (msg.getFeatureID(null) == Resource.RESOURCE__IS_MODIFIED) {
					modified = msg.getNewBooleanValue();
				}
			}

			void reset() {
				modified = false;
			}
		}

		if (writing()) {
			// MSL resources initially track modification
			assertTrue(testResource.isTrackingModification());

			TestAdapter adapter = new TestAdapter();
			testResource.eAdapters().add(adapter);

			assertFalse(adapter.modified);

			// make a change
			Library lib = EXTLibraryFactory.eINSTANCE.createLibrary();
			lib.setName("NewlyAdded"); //$NON-NLS-1$
			root.getBranches().add(lib);

			assertTrue(adapter.modified);
			adapter.reset();

			// disable modification tracking
			testResource.setModified(false);
			testResource.setTrackingModification(false);

			// make a change
			root.getBranches().remove(lib);

			assertFalse(adapter.modified);

			// re-enable tracking
			testResource.setTrackingModification(true);

			// make a change
			root.getBranches().add(lib);

			assertTrue(adapter.modified);
		}
	}
	
	/**
	 * Tests for correct encoding of fragments on URIs from non-GMF resources.
	 * Test this by checking for the encoding of the space ( ) character.
	 */
	public void test_fragmentEncoding_126761() {
		class TestXMIResource extends XMIResourceImpl {
			public TestXMIResource(URI uri) {
				super(uri);
			}

			public String getURIFragment(EObject eObject) {
				EList roots = getContents();
				if (!roots.isEmpty() && eObject == roots.get(0)) {
					return " test "; //$NON-NLS-1$
				}
				
				return null;
			}
			
			public EObject getEObject(String uriFragment) {
				EList roots = getContents();
				if (" test ".equals(uriFragment) && !roots.isEmpty()) {//$NON-NLS-1$
					return (EObject) roots.get(0);
				}
				return null;
			}
		}
		
		class TestMSLResource extends MSLResource {
			public TestMSLResource(URI uri) {
				super(uri);
			}

			protected org.eclipse.emf.ecore.xmi.XMLHelper createXMLHelper() {
				return super.createXMLHelper();
			}
		}

		ResourceSet rset = new ResourceSetImpl();
		
		Resource referenced = new TestXMIResource(URI.createURI("null://referenced")); //$NON-NLS-1$
		rset.getResources().add(referenced);
		Book book = EXTLibraryFactory.eINSTANCE.createBook();
		referenced.getContents().add(book);
		
		TestMSLResource referencer = new TestMSLResource(
				URI.createURI("null://referencer")); //$NON-NLS-1$
		rset.getResources().add(referencer);
		
		Writer writer = EXTLibraryFactory.eINSTANCE.createWriter();
		referencer.getContents().add(writer);
		writer.getBooks().add(book);
		
		XMLHelper helper = referencer.createXMLHelper();
		String href = helper.getHREF(book);
		
		// check that the helper performs the encoding
		assertEquals("null://referenced#%20test%20", href); //$NON-NLS-1$
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			referencer.save(baos, Collections.EMPTY_MAP);
			baos.close();
			
			byte[] bytes = baos.toByteArray();
			String serial = new String(bytes, "UTF-8"); //$NON-NLS-1$
			
			assertFalse(serial.indexOf(" test ") >= 0); //$NON-NLS-1$
			assertTrue(serial.indexOf("%20test%20") >= 0); //$NON-NLS-1$
			
			referencer.unload();
			
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			referencer.load(bais, Collections.EMPTY_MAP);
			bais.close();
			
			assertEquals(1, referencer.getContents().size());
			writer = (Writer) referencer.getContents().get(0);
			
			assertEquals(1, writer.getBooks().size());
			
			EObject referencedObject = (EObject) writer.getBooks().get(0);
			
			// check that proxy resolution works (fragment is decoded correctly)
			System.out.println(EcoreUtil.getURI(referencedObject));
			assertFalse(referencedObject.eIsProxy());
			assertSame(book, referencedObject);
		} catch (Exception e) {
			fail("Unexpected exception: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}

	protected void setUp() throws Exception {

		super.setUp();

		resource = ResourceUtil.create("/tmp/testres.xmi", //$NON-NLS-1$
				EcorePackage.eINSTANCE.getEPackage());
	}

	protected void tearDown() throws Exception {

		try {
			if (resource != null) {
				if (resource.isLoaded()) {
					resource.unload();
				}

				resource.getResourceSet().getResources().remove(resource);
			}
		} finally {
			super.tearDown();
		}
	}
}
