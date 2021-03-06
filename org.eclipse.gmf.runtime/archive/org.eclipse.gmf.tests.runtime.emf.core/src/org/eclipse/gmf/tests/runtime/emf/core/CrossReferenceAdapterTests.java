/******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.tests.runtime.emf.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.BookOnTape;
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Employee;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;
import org.eclipse.gmf.runtime.emf.core.internal.index.MSLCrossReferenceAdapter;
import org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;

/**
 * Test cases for the MSLCrossReferenceAdapter.
 * 
 * @author Christian Vogt (cvogt)
 */
public class CrossReferenceAdapterTests extends BaseCoreTests {

	private Resource otherRes;
	private Library otherRoot;
	private Book otherBook;
	private Writer otherWriter;
	private Employee otherEmp;
	
	private Library nestedLibrary;
	private BookOnTape nestedBookOnTape;
	private Writer nestedWriter;
	private Book nestedBook;
	private BookOnTape otherBookOnTape;

	/**
	 * Tests to ensure that every object in the resource has
	 * an attached CrossReferenceAdapter
	 */
	public void test_hasCrossReferenceAdapter() {
		checkHasCrossReferenceAdapter(testResource);
	}
	
	/**
	 * Helper method to check that the notifier and all its contents
	 * have an attached CrossReferenceAdapter.
	 * 
	 * @param notifier
	 */
	private void checkHasCrossReferenceAdapter(Notifier notifier) {
		boolean result = hasCrossRefenenceAdapter(notifier);
		if (result) {
			TreeIterator iter = null;
			if (notifier instanceof ResourceSet) {
				iter = ((ResourceSet)notifier).getAllContents();
			} else if (notifier instanceof Resource) {
				iter = ((Resource)notifier).getAllContents();
			} else if (notifier instanceof EObject) {
				iter = ((EObject)notifier).eAllContents();
			} else {
				fail("Invalid Object Type"); //$NON-NLS-1$
			}
			
			while (iter.hasNext()) {
				if (!hasCrossRefenenceAdapter((Notifier)iter.next())) {
					result = false;
					break;
				}
			}
		}
		if (!result) {
			fail("CrossReferenceAdapter is missing."); //$NON-NLS-1$
		}
	}
	
	/**
	 * Returns true if the notifier has an attached CrossReferenceAdapter.
	 * 
	 * @param notifier the notifier to check
	 * @return
	 */
	private boolean hasCrossRefenenceAdapter(Notifier notifier) {
		boolean result = false;
		for (Iterator i = notifier.eAdapters().iterator(); i.hasNext(); ) {
			if (i.next() instanceof MSLCrossReferenceAdapter) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Tests that resource import and exports information is properly
	 * maintained when many cross references are added and removed.
	 */
	public void test_importsExportsAddRemoveMany() {
		Book rootBook = (Book)root.getBooks().get(0);
		Book level1Book = (Book)((Library)root.getBranches().get(0)).getBooks().get(0);
		final List books = new ArrayList();
		books.add(rootBook);
		books.add(level1Book);
		
		MUndoInterval undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							otherWriter.getBooks().addAll(books);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection imports = domain.getImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		imports = domain.getAllImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		Collection exports = domain.getExports(testResource);
		assertTrue(exports.contains(otherRes));
		
		exports = domain.getAllExports(testResource);
		assertTrue(exports.contains(otherRes));

		// remove books by undo
		undo.undo();
		
		imports = domain.getImports(otherRes);
		assertFalse(imports.contains(testResource));
		
		imports = domain.getAllImports(otherRes);
		assertFalse(imports.contains(testResource));

		exports = domain.getExports(testResource);
		assertFalse(exports.contains(otherRes));
		
		exports = domain.getAllExports(testResource);
		assertFalse(exports.contains(otherRes));
	}

	/**
	 * Tests that resource import and exports information is properly
	 * maintained when many cross references are added and removed
	 * one at a time.
	 */
	public void test_importsExportsAddManyRemoveOneAtATime() {
		final Book rootBook = (Book)root.getBooks().get(0);
		final Book level1Book = (Book)((Library)root.getBranches().get(0)).getBooks().get(0);
		final List books = new ArrayList();
		books.add(rootBook);
		books.add(level1Book);
		
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							otherWriter.getBooks().addAll(books);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
		
		Collection imports = domain.getImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		imports = domain.getAllImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		Collection exports = domain.getExports(testResource);
		assertTrue(exports.contains(otherRes));
		
		exports = domain.getAllExports(testResource);
		assertTrue(exports.contains(otherRes));

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				// run unvalidated in case the validation example "book must
				//    have author" constraint is installed
				domain.runUnvalidated(new MRunnable() {
					public Object run() {
						try {
							return domain.runAsWrite(new MRunnable() {
							
								public Object run() {
									otherWriter.getBooks().remove(rootBook);
									
									return null;
								}});
						} catch (MSLActionAbandonedException e) {
							fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
							return null;
						}
					}});
			}});
		
		imports = domain.getImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		imports = domain.getAllImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		exports = domain.getExports(testResource);
		assertTrue(exports.contains(otherRes));
		
		exports = domain.getAllExports(testResource);
		assertTrue(exports.contains(otherRes));
		
		domain.runUnvalidated(new MRunnable() {
			public Object run() {
				return domain.runInUndoInterval(new Runnable() {
					public void run() {
						try {
							domain.runAsWrite(new MRunnable() {
							
								public Object run() {
									otherWriter.getBooks().remove(level1Book);
									
									return null;
								}});
						} catch (MSLActionAbandonedException e) {
							fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
						}
					}
				});
			}
		});
		
		imports = domain.getImports(otherRes);
		assertFalse(imports.contains(testResource));
		
		imports = domain.getAllImports(otherRes);
		assertFalse(imports.contains(testResource));

		exports = domain.getExports(testResource);
		assertFalse(exports.contains(otherRes));
		
		exports = domain.getAllExports(testResource);
		assertFalse(exports.contains(otherRes));
	}

	/**
	 * Tests that resource import and exports information is properly
	 * maintained when a cross reference is set and unset.
	 */
	public void test_importsExportsSetUnset() {
		final Writer rootWriter = (Writer)root.getWriters().get(0);

		
		MUndoInterval undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							otherBook.setAuthor(rootWriter);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection imports = domain.getImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		imports = domain.getAllImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		Collection exports = domain.getExports(testResource);
		assertTrue(exports.contains(otherRes));
		
		exports = domain.getAllExports(testResource);
		assertTrue(exports.contains(otherRes));

		// unset author by undo
		undo.undo();
		
		imports = domain.getImports(otherRes);
		assertFalse(imports.contains(testResource));
		
		imports = domain.getAllImports(otherRes);
		assertFalse(imports.contains(testResource));

		exports = domain.getExports(testResource);
		assertFalse(exports.contains(otherRes));
		
		exports = domain.getAllExports(testResource);
		assertFalse(exports.contains(otherRes));
	}
	
	/**
	 * Tests that resource import and exports information is properly
	 * maintained when a cross reference is added and removed.
	 */
	public void test_importsExportsAddRemove() {
		final Book rootBook = (Book)root.getBooks().get(0);

		MUndoInterval undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							otherWriter.getBooks().add(rootBook);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection imports = domain.getImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		imports = domain.getAllImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		Collection exports = domain.getExports(testResource);
		assertTrue(exports.contains(otherRes));
		
		exports = domain.getAllExports(testResource);
		assertTrue(exports.contains(otherRes));

		// remove book by undo
		undo.undo();
		
		imports = domain.getImports(otherRes);
		assertFalse(imports.contains(testResource));
		
		imports = domain.getAllImports(otherRes);
		assertFalse(imports.contains(testResource));

		exports = domain.getExports(testResource);
		assertFalse(exports.contains(otherRes));
		
		exports = domain.getAllExports(testResource);
		assertFalse(exports.contains(otherRes));
	}

	/**
	 * Tests that when a resource containing a cross reference is unload,
	 * the cross reference information is maintained and not accessible.
	 */
	public void test_unloadedCrossReference() {
		final Employee boss = EXTLibraryFactory.eINSTANCE.createEmployee();

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							root.getEmployees().add(boss);
							otherEmp.setManager(boss);

							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
		
		Collection xrefs = EObjectUtil.getReferencers(
				boss,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});
			
		assertTrue(xrefs.contains(otherEmp));

		// unload the resource containing the employee
		otherRes.unload();
		
		xrefs = EObjectUtil.getReferencers(
				boss,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});
			
		// cannot find references because resource was unloaded and are not returned
		assertTrue(xrefs.isEmpty());
	}

	/**
	 * Tests when a resource containing a cross reference and the referencer
	 * is destroyed, that the cross reference information is updated.
	 */
	public void test_destroyReferencer() {
		final Employee boss = EXTLibraryFactory.eINSTANCE.createEmployee();

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							root.getEmployees().add(boss);
							domain.getResourceSet().eAdapters().add(new ECrossReferenceAdapter());
							otherEmp.setManager(boss);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection xrefs = EObjectUtil.getReferencers(
				boss,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});

		assertTrue(xrefs.contains(otherEmp));

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							EObjectUtil.destroy(otherEmp);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
		xrefs = EObjectUtil.getReferencers(
				boss,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});
			
		assertTrue(xrefs.isEmpty());
	}

	/**
	 * Tests when a resource containing a cross reference and the referencer
	 * is detached, that the cross reference information is maintained.
	 */
	public void test_detachReferencer() {
		final Employee boss = EXTLibraryFactory.eINSTANCE.createEmployee();

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							root.getEmployees().add(boss);
							otherEmp.setManager(boss);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection xrefs = EObjectUtil.getReferencers(
				boss,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});

		assertTrue(xrefs.contains(otherEmp));

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							otherRoot.getEmployees().remove(otherEmp);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
		xrefs = EObjectUtil.getReferencers(
				boss,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});
			
		assertTrue(xrefs.contains(otherEmp));
	}

	/**
	 * Tests when a resource containing a cross reference and the referenced object
	 * is detached, that the cross reference information is maintained.
	 */
	public void test_detachReferenced() {
		final Employee boss = EXTLibraryFactory.eINSTANCE.createEmployee();

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							root.getEmployees().add(boss);
							otherEmp.setManager(boss);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection xrefs = EObjectUtil.getReferencers(
				boss,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});

		assertTrue(xrefs.contains(otherEmp));

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							root.getEmployees().remove(boss);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
		xrefs = EObjectUtil.getReferencers(
				boss,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});
			
		assertTrue(xrefs.contains(otherEmp));
	}

	/**
	 * Tests when a resource containing a bi-directional cross reference
	 * to an object and the reference is destroyed, that the cross
	 * reference information is updated correctly
	 */
	public void test_biDirectionalReference() {
		final Book rootBook = (Book)root.getBooks().get(0);

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							otherWriter.getBooks().add(rootBook);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection xrefs = EObjectUtil.getReferencers(
				rootBook,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getWriter_Books()});
			
		assertTrue(xrefs.contains(otherWriter));

		domain.runUnvalidated(new MRunnable() {
			public Object run() {
				return domain.runInUndoInterval(new Runnable() {
					public void run() {
						try {
							domain.runAsWrite(new MRunnable() {
							
								public Object run() {
									EObjectUtil.destroy(otherWriter);
									otherWriter = null;
									return null;
								}});
						} catch (MSLActionAbandonedException e) {
							fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
						}
					}
				});
			}
		});

		xrefs = EObjectUtil.getReferencers(
				rootBook,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getWriter_Books()});
			
		assertTrue(xrefs.isEmpty());
	}

	/**
	 * Tests when a resource containing a uni-directional cross reference
	 * to an object in another resource and the reference is destroyed,
	 * that the cross reference information is updated correctly
	 */
	public void test_uniDirectionalReference() {
		final Employee boss = EXTLibraryFactory.eINSTANCE.createEmployee();

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							root.getEmployees().add(boss);
							otherEmp.setManager(boss);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection xrefs = EObjectUtil.getReferencers(
				boss,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});
			
		assertTrue(xrefs.contains(otherEmp));

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							EObjectUtil.destroy(otherEmp);
							otherEmp = null;
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		xrefs = EObjectUtil.getReferencers(
				boss,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});
			
		assertTrue(xrefs.isEmpty());
	}

	/**
	 * Tests retrieving referencers of a specific type.
	 */
	public void test_getReferencersOfType() {
		final Employee boss = EXTLibraryFactory.eINSTANCE.createEmployee();

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							root.getEmployees().add(boss);
							otherEmp.setManager(boss);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		CrossReferenceAdapter crossReferenceAdapter =
			CrossReferenceAdapter.getExistingCrossReferenceAdapter(boss);
		
		// tests valid type
		Collection xrefs = crossReferenceAdapter.getInverseReferencers(boss,
				EXTLibraryPackage.eINSTANCE.getEmployee_Manager(),
				EXTLibraryPackage.eINSTANCE.getEmployee());
			
		assertTrue(xrefs.contains(otherEmp));
		
		// tests valid type
		xrefs = crossReferenceAdapter.getInverseReferencers(boss,
				null,
				EXTLibraryPackage.eINSTANCE.getEmployee());
			
		assertTrue(xrefs.contains(otherEmp));
		
		// tests invalid type
		xrefs = crossReferenceAdapter.getInverseReferencers(boss,
				EXTLibraryPackage.eINSTANCE.getEmployee_Manager(),
				EXTLibraryPackage.eINSTANCE.getLibrary());
			
		assertTrue(xrefs.isEmpty());
		
		// tests invalid type
		xrefs = crossReferenceAdapter.getInverseReferencers(boss,
				null,
				EXTLibraryPackage.eINSTANCE.getLibrary());
		
		assertTrue(xrefs.isEmpty());
	}

	/**
	 * Tests retrieving referencers when the cross reference adapter
	 * is not added to the ResourceSet when the domain is created, but rather
	 * added at a later point in time (lazily).
	 */
	public void test_addLateCrossReferenceAdapter() {
		MSLEditingDomain mslDomain = (MSLEditingDomain)domain;
		
		// remove the cross reference adapter from the ResourceSet
		mslDomain.getResourceSet().eAdapters().remove(mslDomain.getCrossReferenceAdapter());
		
		final Employee boss = EXTLibraryFactory.eINSTANCE.createEmployee();

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							root.getEmployees().add(boss);
							otherEmp.setManager(boss);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection xrefs = EObjectUtil.getReferencers(
				boss,
				new EReference[] {EXTLibraryPackage.eINSTANCE.getEmployee_Manager()});
			
		assertTrue(xrefs.contains(otherEmp));
	}

	/**
	 * Tests that resource import and exports information is correct when there
	 * are uni-directional references between resources that are loaded. These references
	 * do not have opposites. Added to this the cross reference adapter
	 * is not added to the ResourceSet when the domain is created, but rather
	 * added at a later point in time (lazily).
	 */
	public void test_importsExports_UnidirectionalReferences() {
		MUndoInterval undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							// now attach an object that contains a (nested)
							//     element having an existing cross-reference
							root.getBranches().add(nestedLibrary);
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							nestedBookOnTape.setAuthor(otherWriter);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		MSLEditingDomain mslDomain = (MSLEditingDomain)domain;
		
		// remove the cross reference adapter from the ResourceSet
		mslDomain.getResourceSet().eAdapters().remove(mslDomain.getCrossReferenceAdapter());

		
		Collection imports = EMFCoreUtil.getImports(testResource);
		assertTrue(imports.contains(otherRes));
		
		imports = EMFCoreUtil.getTransitiveImports(testResource);
		assertTrue(imports.contains(otherRes));
		
		Collection exports = EMFCoreUtil.getExports(otherRes);
		assertTrue(exports.contains(testResource));
		
		exports = EMFCoreUtil.getTransitiveExports(otherRes);
		assertTrue(exports.contains(testResource));

		// remove the author
		undo.undo();
		
		imports = EMFCoreUtil.getImports(testResource);
		assertFalse(imports.contains(otherRes));
		
		imports = EMFCoreUtil.getTransitiveImports(testResource);
		assertFalse(imports.contains(otherRes));
		
		exports = EMFCoreUtil.getExports(otherRes);
		assertFalse(exports.contains(testResource));
		
		exports = EMFCoreUtil.getTransitiveExports(otherRes);
		assertFalse(exports.contains(testResource));
	}	
	/**
	 * Tests that resource import and exports information is properly
	 * maintained when a sub-tree containing an element having a cross reference
	 * is added and removed.  This differs from the
	 * {@link #test_importsExportsAddRemove()} test case in needing to crawl an
	 * added or deleted sub-tree to find cross-references in nested elements.
	 */
	public void test_importsExportsAddRemove_subtree_130981() {
		// no need for a transaction because this is a unidirectional reference
		//    from an object that is not yet attached
		nestedBookOnTape.setAuthor(otherWriter);

		MUndoInterval undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							// now attach an object that contains a (nested)
							//     element having an existing cross-reference
							root.getBranches().add(nestedLibrary);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection imports = EMFCoreUtil.getImports(testResource);
		assertTrue(imports.contains(otherRes));
		
		imports = EMFCoreUtil.getTransitiveImports(testResource);
		assertTrue(imports.contains(otherRes));
		
		Collection exports = EMFCoreUtil.getExports(otherRes);
		assertTrue(exports.contains(testResource));
		
		exports = EMFCoreUtil.getTransitiveExports(otherRes);
		assertTrue(exports.contains(testResource));

		// remove sub-tree by undo
		undo.undo();
		
		imports = EMFCoreUtil.getImports(testResource);
		assertFalse(imports.contains(otherRes));
		
		imports = EMFCoreUtil.getTransitiveImports(testResource);
		assertFalse(imports.contains(otherRes));
		
		exports = EMFCoreUtil.getExports(otherRes);
		assertFalse(exports.contains(testResource));
		
		exports = EMFCoreUtil.getTransitiveExports(otherRes);
		assertFalse(exports.contains(testResource));
	}
	
	/**
	 * Converse of the previous test, in which an attached element references
	 * something in a detached sub-tree which then becomes attached to a
	 * resource, thus adding to the imports of the first resource.
	 */
	public void test_importsExportsAddRemove_inverseSubtree_130981() {
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							// first, set up the reference from the attached
							//    book-on-tape to the detached (nested) writer
							otherBookOnTape.setAuthor(nestedWriter);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		MUndoInterval undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							// now attach an the (nested) writer's subtree to
							//   the test resource
							root.getBranches().add(nestedLibrary);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		Collection imports = EMFCoreUtil.getImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		imports = EMFCoreUtil.getTransitiveImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		Collection exports = EMFCoreUtil.getExports(testResource);
		assertTrue(exports.contains(otherRes));
		
		exports = EMFCoreUtil.getTransitiveExports(testResource);
		assertTrue(exports.contains(otherRes));

		// remove sub-tree by undo
		undo.undo();
		
		imports = EMFCoreUtil.getImports(otherRes);
		assertFalse(imports.contains(testResource));
		
		imports = EMFCoreUtil.getTransitiveImports(otherRes);
		assertFalse(imports.contains(testResource));
		
		exports = EMFCoreUtil.getExports(testResource);
		assertFalse(exports.contains(otherRes));
		
		exports = EMFCoreUtil.getTransitiveExports(testResource);
		assertFalse(exports.contains(otherRes));
	}
	
	/**
	 * Tests that resource import and exports information is properly
	 * maintained when a sub-tree containing an element having a cross reference
	 * is added and removed.  This differs from the
	 * {@link #test_importsExportsAddRemove_subtree_130981()} test case in using
	 * a bidirectional reference.
	 */
	public void test_importsExportsAddRemove_subtreeBidirectional_130981() {
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							// first, set up the reference from the attached
							//    book-on-tape to the detached (nested) writer
							nestedBook.setAuthor(otherWriter);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		MUndoInterval undo = domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							// now attach an object that contains a (nested)
							//     element having an existing cross-reference
							root.getBranches().add(nestedLibrary);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to update model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});

		// mutually import
		Collection imports = EMFCoreUtil.getImports(testResource);
		assertTrue(imports.contains(otherRes));
		imports = EMFCoreUtil.getImports(otherRes);
		assertTrue(imports.contains(testResource));
		
		// mutually export
		Collection exports = EMFCoreUtil.getExports(otherRes);
		assertTrue(exports.contains(testResource));
		exports = EMFCoreUtil.getExports(testResource);
		assertTrue(exports.contains(otherRes));

		// remove sub-tree by undo
		undo.undo();

		// mutually import
		imports = EMFCoreUtil.getImports(testResource);
		assertFalse(imports.contains(otherRes));
		imports = EMFCoreUtil.getImports(otherRes);
		assertFalse(imports.contains(testResource));
		
		// mutually export
		exports = EMFCoreUtil.getExports(otherRes);
		assertFalse(exports.contains(testResource));
		exports = EMFCoreUtil.getExports(testResource);
		assertFalse(exports.contains(otherRes));
	}

	/* 
	 * Sets up a library test model:
	 * 
	 * Library     (otherRoot)
	 *  - Book     (otherBook)
	 *  - Writer   (otherWiter)
	 *  - Employee (otherEmp)
	 */
	protected void setUp() throws Exception {

		super.setUp();

		otherRes = domain.createResource("/tmp/otherLibrary.extlibrary", //$NON-NLS-1$
				EXTLibraryPackage.eINSTANCE.getLibrary());
	
		domain.getResourceSet().getResources().add(otherRes);

		otherRoot = (Library)otherRes.getContents().get(0);
		otherBook = EXTLibraryFactory.eINSTANCE.createBook();
		otherWriter = EXTLibraryFactory.eINSTANCE.createWriter();
		otherEmp = EXTLibraryFactory.eINSTANCE.createEmployee();
		
		nestedLibrary = EXTLibraryFactory.eINSTANCE.createLibrary();
		nestedBookOnTape = EXTLibraryFactory.eINSTANCE.createBookOnTape();
		nestedLibrary.getStock().add(nestedBookOnTape);
		nestedWriter = EXTLibraryFactory.eINSTANCE.createWriter();
		nestedLibrary.getWriters().add(nestedWriter);
		nestedBook = EXTLibraryFactory.eINSTANCE.createBook();
		nestedLibrary.getBooks().add(nestedBook);
		otherBookOnTape = EXTLibraryFactory.eINSTANCE.createBookOnTape();

		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
					
						public Object run() {
							
							otherRoot.getBooks().add(otherBook);
							otherRoot.getWriters().add(otherWriter);
							otherRoot.getEmployees().add(otherEmp);
							otherRoot.getStock().add(otherBookOnTape);
							
							return null;
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to setup test model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}
			}});
	}

	protected void tearDown() throws Exception {

		try {
			if (otherRes != null) {
				if (otherRes.isLoaded()) {
					otherRes.unload();
				}

				otherRes.getResourceSet().getResources().remove(otherRes);
			}
		} finally {
			super.tearDown();
		}
	}
}
