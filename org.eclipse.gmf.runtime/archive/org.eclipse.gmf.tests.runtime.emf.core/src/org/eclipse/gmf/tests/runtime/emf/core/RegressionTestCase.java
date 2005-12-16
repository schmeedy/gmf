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

import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.IOWrappedException;
import org.eclipse.emf.ecore.xmi.ClassNotFoundException;
import org.eclipse.emf.ecore.xmi.FeatureNotFoundException;
import org.eclipse.emf.ecore.xmi.PackageNotFoundException;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.BookCategory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.gmf.runtime.emf.core.edit.MResourceOption;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLRuntimeException;
import org.eclipse.gmf.runtime.emf.core.internal.commands.MSLCompoundCommand;
import org.eclipse.gmf.runtime.emf.core.internal.commands.MSLSetCommand;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLUtil;
import org.eclipse.gmf.runtime.emf.core.util.OperationUtil;
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
	 * Tests that we do not record in the current undo interval and MCommands
	 * for sets or unsets that do not change the value of an EAttribute.
	 */
	public void test_idempotentCommands_117131() {
		final EPackage pkg = (EPackage) ResourceUtil.getFirstRoot(resource);

		MUndoInterval undo = OperationUtil.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					OperationUtil.runAsWrite(new MRunnable() {
						public Object run() {
							EClass cls = EcoreFactory.eINSTANCE.createEClass();

							// MSL will not get this notification because the
							// EClass is not yet attached to the resource set
							cls.setName("MyClass"); //$NON-NLS-1$

							// now we attach the EClass
							pkg.getEClassifiers().add(cls);

							// this should go into the undo interval
							cls.setName("NewName"); //$NON-NLS-1$

							// so should this one
							cls.setName("FinalName"); //$NON-NLS-1$

							// this should *not* go into the undo interval.
							// Note that we must not just use the string
							// literal because it is the same instance
							StringBuffer buf = new StringBuffer(10);
							buf.append("FinalName"); //$NON-NLS-1$
							cls.setName(buf.toString());
							return null;
						}
					});
				} catch (MSLActionAbandonedException e) {
					fail("Action abandoned: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}
		});

		assertTrue(undo instanceof MSLCompoundCommand);

		MSLCompoundCommand compound = (MSLCompoundCommand) undo;

		// there is a single compound command in the undo interval,
		// corresponding to the write action
		assertEquals(1, compound.getCommands().size());
		assertTrue(compound.getCommands().get(0) instanceof MSLCompoundCommand);
		compound = (MSLCompoundCommand) compound.getCommands().get(0);

		MSLSetCommand setCommand = null;
		MSLSetCommand badCommand = null;
		int commandCount = 0;

		// get the EClass that we created
		EClass eclass = (EClass) pkg.getEClassifier("FinalName"); //$NON-NLS-1$

		for (Iterator iter = compound.getCommands().iterator(); iter.hasNext();) {
			Object next = iter.next();

			if (next instanceof MSLSetCommand) {
				MSLSetCommand cmd = (MSLSetCommand) next;

				// is this the particular command of interest?
				if ((cmd.getFeature() == EcorePackage.eINSTANCE
						.getENamedElement_Name())
						&& (cmd.getOwner() == eclass)) {
					// this command changes the eclass name
					commandCount++;

					if ("FinalName".equals(cmd.getValue())) { //$NON-NLS-1$
						if ("NewName".equals(cmd.getOldValue())) { //$NON-NLS-1$
							// this is the "good" command, which changed the
							// name
							setCommand = cmd;
						} else if ("FinalName".equals(cmd.getOldValue())) { //$NON-NLS-1$
							// this is the "bad" command, which does not change
							// the name
							badCommand = cmd;
						}
					}
				}
			}
		}

		// should not have found this command
		assertNull("Found incorrect EClass name change", badCommand); //$NON-NLS-1$

		// should have found the command that changes MyClass to NewName
		assertNotNull("Did not find the correct EClass name change", setCommand); //$NON-NLS-1$

		// should only have two commands that chang the class's name, not three
		assertEquals("Too many changes of the EClass name", 2, commandCount); //$NON-NLS-1$
	}

	/**
	 * Tests that we do not record in the current undo interval and MCommands
	 * for sets or unsets that do not change the value of an EAttribute. This
	 * test, in particular, tests handling of null values
	 */
	public void test_idempotentCommands_nulls_117131() {
		final EPackage pkg = (EPackage) ResourceUtil.getFirstRoot(resource);

		MUndoInterval undo = OperationUtil.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					OperationUtil.runAsWrite(new MRunnable() {
						public Object run() {
							EClass cls = EcoreFactory.eINSTANCE.createEClass();

							// MSL will not get this notification because the
							// EClass is not yet attached to the resource set
							cls.setName("MyClass"); //$NON-NLS-1$

							// now we attach the EClass
							pkg.getEClassifiers().add(cls);

							// this should go into the undo interval
							cls.setName("NewName"); //$NON-NLS-1$

							// so should this one
							cls.setName(null); //$NON-NLS-1$

							// this should *not* go into the undo interval
							cls.setName(null); //$NON-NLS-1$
							return null;
						}
					});
				} catch (MSLActionAbandonedException e) {
					fail("Action abandoned: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}
		});

		assertTrue(undo instanceof MSLCompoundCommand);

		MSLCompoundCommand compound = (MSLCompoundCommand) undo;

		// there is a single compound command in the undo interval,
		// corresponding to the write action
		assertEquals(1, compound.getCommands().size());
		assertTrue(compound.getCommands().get(0) instanceof MSLCompoundCommand);
		compound = (MSLCompoundCommand) compound.getCommands().get(0);

		MSLSetCommand setCommand = null;
		MSLSetCommand badCommand = null;
		int commandCount = 0;

		// get the EClass that we created
		EClass eclass = (EClass) pkg.getEClassifiers().get(0);

		for (Iterator iter = compound.getCommands().iterator(); iter.hasNext();) {
			Object next = iter.next();

			if (next instanceof MSLSetCommand) {
				MSLSetCommand cmd = (MSLSetCommand) next;

				// is this the particular command of interest?
				if ((cmd.getFeature() == EcorePackage.eINSTANCE
						.getENamedElement_Name())
						&& (cmd.getOwner() == eclass)) {
					// this command changes the eclass name
					commandCount++;

					if (cmd.getValue() == null) { //$NON-NLS-1$
						if ("NewName".equals(cmd.getOldValue())) { //$NON-NLS-1$
							// this is the "good" command, which changed the
							// name
							setCommand = cmd;
						} else if (cmd.getOldValue() == null) { //$NON-NLS-1$
							// this is the "bad" command, which does not change
							// the name
							badCommand = cmd;
						}
					}
				}
			}
		}

		// should not have found this command
		assertNull("Found incorrect EClass name change", badCommand); //$NON-NLS-1$

		// should have found the command that changes MyClass to NewName
		assertNotNull("Did not find the correct EClass name change", setCommand); //$NON-NLS-1$

		// should only have two commands that chang the class's name, not three
		assertEquals("Too many changes of the EClass name", 2, commandCount); //$NON-NLS-1$
	}

	/**
	 * Tests that we do not record in the current undo interval and MCommands
	 * for sets or unsets that do not change the value of an EAttribute. This
	 * particular test checks for correct handling of EEnums.
	 */
	public void test_idempotentCommands_enums_117131() {
		MUndoInterval undo = OperationUtil.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					OperationUtil.runAsWrite(new MRunnable() {
						public Object run() {
							Library library = EXTLibraryFactory.eINSTANCE
									.createLibrary();
							resource.getContents().set(0, library);

							Book book = EXTLibraryFactory.eINSTANCE
									.createBook();

							// MSL will not get this notification because the
							// book is not yet attached to the resource set
							book.setCategory(BookCategory.BIOGRAPHY_LITERAL);

							// now we attach the book
							library.getStock().add(book);

							// this should go into the undo interval
							book.setCategory(BookCategory.MYSTERY_LITERAL);

							// so should this one
							book
									.setCategory(BookCategory.SCIENCE_FICTION_LITERAL);

							// this should *not* go into the undo interval
							book
									.setCategory(BookCategory.SCIENCE_FICTION_LITERAL);
							return null;
						}
					});
				} catch (MSLActionAbandonedException e) {
					fail("Action abandoned: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}
		});

		assertTrue(undo instanceof MSLCompoundCommand);

		MSLCompoundCommand compound = (MSLCompoundCommand) undo;

		// there is a single compound command in the undo interval,
		// corresponding to the write action
		assertEquals(1, compound.getCommands().size());
		assertTrue(compound.getCommands().get(0) instanceof MSLCompoundCommand);
		compound = (MSLCompoundCommand) compound.getCommands().get(0);

		MSLSetCommand setCommand = null;
		MSLSetCommand badCommand = null;
		int commandCount = 0;

		// get the Book that we created
		Library library = (Library) ResourceUtil.getFirstRoot(resource);
		Book book = (Book) library.getBooks().get(0);

		for (Iterator iter = compound.getCommands().iterator(); iter.hasNext();) {
			Object next = iter.next();

			if (next instanceof MSLSetCommand) {
				MSLSetCommand cmd = (MSLSetCommand) next;

				// is this the particular command of interest?
				if ((cmd.getFeature() == EXTLibraryPackage.eINSTANCE
						.getBook_Category())
						&& (cmd.getOwner() == book)) {
					// this command changes the component visibility
					commandCount++;

					if (BookCategory.SCIENCE_FICTION_LITERAL.equals(cmd
							.getValue())) {
						if (BookCategory.MYSTERY_LITERAL.equals(cmd
								.getOldValue())) {
							// this is the "good" command, which changed the
							// visibility
							setCommand = cmd;
						} else if (BookCategory.SCIENCE_FICTION_LITERAL
								.equals(cmd.getOldValue())) {
							// this is the "bad" command, which does not change
							// the visibility
							badCommand = cmd;
						}
					}
				}
			}
		}

		// should not have found this command
		assertNull("Found incorrect component visibility change", badCommand); //$NON-NLS-1$

		// should have found the command that changes Private to Package
		assertNotNull(
				"Did not find the correct component visibility change", setCommand); //$NON-NLS-1$

		// should only have two commands that chang the visibility, not three
		assertEquals(
				"Too many changes of the component visibility", 2, commandCount); //$NON-NLS-1$
	}

	//
	// Fixture methods.
	//

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
