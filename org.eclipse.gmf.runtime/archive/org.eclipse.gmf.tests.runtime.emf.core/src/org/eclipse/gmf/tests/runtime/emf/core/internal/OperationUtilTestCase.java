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


package org.eclipse.gmf.tests.runtime.emf.core.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.Model;
import org.eclipse.uml2.NamedElement;
import org.eclipse.uml2.UML2Package;

import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.internal.commands.MSLUndoStack.ActionLockMode;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;
import org.eclipse.gmf.tests.runtime.emf.core.BaseTestCase;
import org.eclipse.gmf.runtime.emf.core.util.OperationUtil;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;


/**
 * JUnit tests for the {@link OperationUtilTestCase} class.
 *
 * @author Christian W. Damus (cdamus)
 */
public class OperationUtilTestCase
	extends BaseTestCase {

	private static final String CLASS_NAME = "MyTestClass"; //$NON-NLS-1$
	
	private Resource res;
	private Model model;
	private CancelingRunnable runnable;
	
	public OperationUtilTestCase(String name) {
		super(name);
	}
	
	/**
	 * Tests the cancellation support in the {@link MRunnable} class.
	 */
	public void test_cancelRunnable() {
		runnable.setCanceling(true);
		
		assertFalse("Runnable already canceled", //$NON-NLS-1$
			runnable.isAbandoned());
		
		runnable.abandon();
		
		assertTrue("Runnable not canceled", //$NON-NLS-1$
			runnable.isAbandoned());
		
		// cancellation is idempotent
		runnable.abandon();
		
		assertTrue("Runnable not canceled", //$NON-NLS-1$
			runnable.isAbandoned());
	}
	
	/**
	 * Tests that cancellation of an {@link MRunnable} abandons model changes.
	 */
	public void test_executeCancelRunnable() {
		runnable.setCanceling(true);
		
		assertFalse("Runnable already canceled", //$NON-NLS-1$
			runnable.isAbandoned());
		
		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				try {
					OperationUtil.runAsWrite(runnable);
				} catch (MSLActionAbandonedException e) {
					fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
				}
			}});
		
		assertTrue("Runnable not canceled", //$NON-NLS-1$
			runnable.isAbandoned());
		assertEquals("Wrong status severity", //$NON-NLS-1$
			IStatus.CANCEL, runnable.getStatus().getSeverity());
		
		OperationUtil.runAsRead(new MRunnable() {

			public Object run() {
				assertNull("Model change not abandoned", //$NON-NLS-1$
					model.getOwnedMember(CLASS_NAME));
				
				return null;
			}});
	}
	
	/**
	 * Tests that cancellation of a nested {@link MRunnable} cancels all
	 * nesting runnables.
	 */
	public void test_executeNestedCancelRunnable() {
		runnable.setCanceling(true);
		
		assertFalse("Runnable already canceled", //$NON-NLS-1$
			runnable.isAbandoned());
		
		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				try {
					OperationUtil.runAsWrite(new MRunnable() {

						public Object run() {
							// nested
							try {
								return OperationUtil.runAsWrite(runnable);
							} catch (MSLActionAbandonedException e) {
								fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
								return null;
							}
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
				}
			}});
		
		assertTrue("Runnable not canceled", //$NON-NLS-1$
			runnable.isAbandoned());
		
		// cancellation should have no effect on the status of this runnable
		assertEquals("Wrong status severity", //$NON-NLS-1$
			IStatus.OK, runnable.getStatus().getSeverity());
		
		OperationUtil.runAsRead(new MRunnable() {

			public Object run() {
				// cancellation should cause action to be abandoned
				assertNull("Model change was not abandoned", //$NON-NLS-1$
					model.getOwnedMember(CLASS_NAME));
				
				return null;
			}});
	}
	
	/**
	 * Tests that cancellation of a nesting {@link MRunnable} cancels all
	 * nested runnables.
	 */
	public void test_executeNestingCancelRunnable() {
		runnable.setCanceling(false);  // a nesting runnable will cancel
		
		assertFalse("Runnable already canceled", //$NON-NLS-1$
			runnable.isAbandoned());
		
		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				try {
					OperationUtil.runAsWrite(new MRunnable() {

						public Object run() {
							// cancel the nesting runnable
							this.abandon();
							
							// nested
							try {
								return OperationUtil.runAsWrite(runnable);
							} catch (MSLActionAbandonedException e) {
								fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
								return null;
							}
						}});
				} catch (MSLActionAbandonedException e) {
					fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
				}
			}});
		
		assertTrue("Runnable not canceled", //$NON-NLS-1$
			runnable.isAbandoned());
		
		// cancellation should have no effect on the status of this runnable
		assertEquals("Wrong status severity", //$NON-NLS-1$
			IStatus.OK, runnable.getStatus().getSeverity());
		
		OperationUtil.runAsRead(new MRunnable() {

			public Object run() {
				// cancellation should cause action to be abandoned
				assertNull("Model change was not abandoned", //$NON-NLS-1$
					model.getOwnedMember(CLASS_NAME));
				
				return null;
			}});
	}
	
	/**
	 * Tests upgrading a read action to a write action using the MRunnable API.
	 * This variant opens the undo interval in the read action.
	 */
	public void test_upgradeReadAction_MRunnable() {
		OperationUtil.runAsRead(new MRunnable() {

			public Object run() {
				// check that we are reading
				assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
				
				OperationUtil.runInUndoInterval(new Runnable() {

					public void run() {
						try {
							OperationUtil.runAsWrite(runnable);
						} catch (MSLActionAbandonedException e) {
							fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
						}
					}});
				
				// we must be reading again
				assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
				
				assertNotNull("Change was abandoned", //$NON-NLS-1$
					model.getOwnedMember(CLASS_NAME));
				
				return null;
			}});
	}
	
	/**
	 * Tests upgrading a read action to a write action using the MRunnable API.
	 * This variant opens the read action in the undo interval.
	 */
	public void test_upgradeReadAction_openUndoFirst_MRunnable() {
		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
					OperationUtil.runAsRead(new MRunnable() {
			
						public Object run() {
							// check that we are reading
							assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
							
						try {
							OperationUtil.runAsWrite(runnable);
						} catch (MSLActionAbandonedException e) {
							fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
						}
						
						// we must be reading again
						assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
						
						assertNotNull("Change was abandoned", //$NON-NLS-1$
							model.getOwnedMember(CLASS_NAME));
						
						return null;
					}});
			}});
	}
	
	/**
	 * Tests upgrading multiple nested read actions to a write action using the
	 * MRunnable API.
	 */
	public void test_upgradeNestedReadActions_MRunnable() {
		((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().startAction(ActionLockMode.READ);
		
		// check that we are reading
		assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
		
		// force a nested read action (runAsRead() would not nest)
		((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().startAction(ActionLockMode.READ);
		
		// check that we are reading
		assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
		
		OperationUtil.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					OperationUtil.runAsWrite(runnable);
				} catch (MSLActionAbandonedException e) {
					fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
				}
			}});
		
		// we must be reading again
		assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$

		((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().completeAction();
		
		// we must be reading still
		assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$

		((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().completeAction();
		
		assertNotNull("Change was abandoned", //$NON-NLS-1$
			model.getOwnedMember(CLASS_NAME));
	}
	
	/**
	 * Tests upgrading multiple read actions to a write action using the
	 * MRunnable API.
	 * This variant opens the read action in the undo interval.
	 */
	public void test_upgradeNestedReadActions_openUndoFirst_MRunnable() {
		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().startAction(ActionLockMode.READ);
				
				// check that we are reading
				assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
				
				// force a nested read action (runAsRead() would not nest)
				((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().startAction(ActionLockMode.READ);
				
				// check that we are reading
				assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
		
				try {
					OperationUtil.runAsWrite(runnable);
				} catch (MSLActionAbandonedException e) {
					fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
				}
				
				// we must be reading again
				assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$

				((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().completeAction();
				
				// we must be reading still
				assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$

				((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().completeAction();
				
				assertNotNull("Change was abandoned", //$NON-NLS-1$
					model.getOwnedMember(CLASS_NAME));
			}});
	}
	
	/**
	 * Tests upgrading a read action to a write action using the Runnable API.
	 * @deprecated Tests deprecated API.
	 */
	public void test_upgradeReadAction_Runnable() {
		try {
			OperationUtil.runAsRead(new Runnable() {
	
				public void run() {
					// check that we are reading
					assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
					
					OperationUtil.runInUndoInterval(new Runnable() {

						public void run() {
							try {
								OperationUtil.runAsWrite(new PlainRunnable());
							} catch (MSLActionAbandonedException e) {
								fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
							}
						}});
					
					// we must be reading again
					assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
					
					assertNotNull("Change was abandoned", //$NON-NLS-1$
						model.getOwnedMember(CLASS_NAME));
				}});
		} catch (MSLActionAbandonedException e) {
			fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
		}
	}
	
	/**
	 * Tests upgrading a read action to a write action using the Runnable API.
	 * This variant opens the read action in the undo interval.
	 * @deprecated Tests deprecated API.
	 */
	public void test_upgradeReadAction_openUndoFirst_Runnable() {
		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				try {
					OperationUtil.runAsRead(new Runnable() {
			
						public void run() {
							// check that we are reading
							assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
							
							try {
								OperationUtil.runAsWrite(new PlainRunnable());
							} catch (MSLActionAbandonedException e) {
								fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
							}
							
							// we must be reading again
							assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
							
							assertNotNull("Change was abandoned", //$NON-NLS-1$
								model.getOwnedMember(CLASS_NAME));
					}});
				} catch (MSLActionAbandonedException e) {
					fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
				}
			}});
	}
	
	/**
	 * Tests upgrading multiple nested read actions to a write action using the
	 * Runnable API.
	 * @deprecated Tests deprecated API.
	 */
	public void test_upgradeNestedReadActions_Runnable() {
		OperationUtil.startRead();
		
		// check that we are reading
		assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
		
		// force a nested read action (runAsRead() would not nest)
		OperationUtil.startRead();
		
		// check that we are reading
		assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
		
		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				try {
					OperationUtil.runAsWrite(new PlainRunnable());
				} catch (MSLActionAbandonedException e) {
					fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
				}
			}});
		
		// we must be reading again
		assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$

		OperationUtil.complete();
		
		// we must be reading still
		assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$

		OperationUtil.complete();
		
		assertNotNull("Change was abandoned", //$NON-NLS-1$
			model.getOwnedMember(CLASS_NAME));
	}
	
	/**
	 * Tests upgrading multiple read actions to a write action using the
	 * Runnable API.
	 * This variant opens the read action in the undo interval.
	 * @deprecated Tests deprecated API.
	 */
	public void test_upgradeNestedReadActions_openUndoFirst_Runnable() {
		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				OperationUtil.startRead();
				
				// check that we are reading
				assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
				
				// force a nested read action (runAsRead() would not nest)
				OperationUtil.startRead();
				
				// check that we are reading
				assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$
		
				try {
					OperationUtil.runAsWrite(new PlainRunnable());
				} catch (MSLActionAbandonedException e) {
					fail("Should not throw MSLActionAbandonedException"); //$NON-NLS-1$
				}
				
				// we must be reading again
				assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$

				OperationUtil.complete();
				
				// we must be reading still
				assertTrue("Not reading", isReadActionAtTopOfStack()); //$NON-NLS-1$

				OperationUtil.complete();
				
				assertNotNull("Change was abandoned", //$NON-NLS-1$
					model.getOwnedMember(CLASS_NAME));
			}});
	}
	
	//
	// Fixture methods
	//
	
	static boolean isReadActionAtTopOfStack() {
		return OperationUtil.canRead() && !OperationUtil.canWrite();
	}
	
	protected void setUp()
		throws Exception {
		
		super.setUp();
		
		res = ResourceUtil.create("\\tmp\\operationUtilTest.emx", //$NON-NLS-1$
			UML2Package.eINSTANCE.getModel(), 0);

		model = (Model) ResourceUtil.getFirstRoot(res);
		
		runnable = new CancelingRunnable();
	}
	
	protected void tearDown()
		throws Exception {
		
		runnable = null;
		
		model = null;
		
		ResourceSet rset = res.getResourceSet();
		res.unload();
		rset.getResources().remove(res);
		
		super.tearDown();
	}
	
	private class CancelingRunnable extends MRunnable {
		private boolean isCanceling;
		
		void setCanceling(boolean b) {
			isCanceling = b;
		}
		
		public Object run() {
			// change the model
			((NamedElement) model.createOwnedMember(
				UML2Package.eINSTANCE.getClass_())).setName(CLASS_NAME);
			
			if (isCanceling) {
				// cancel myself
				this.abandon();
			}
			
			return null;
		}		
	}
	
	private class PlainRunnable implements Runnable {
		public void run() {
			// change the model
			((NamedElement) model.createOwnedMember(
				UML2Package.eINSTANCE.getClass_())).setName(CLASS_NAME);
		}		
	}
}
