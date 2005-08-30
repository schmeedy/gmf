/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.tests.runtime.emf.core.internal;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.Model;
import org.eclipse.uml2.NamedElement;
import org.eclipse.uml2.UML2Package;

import org.eclipse.gmf.runtime.emf.core.EditingDomain;
import org.eclipse.gmf.runtime.emf.core.IOperationEvent;
import org.eclipse.gmf.runtime.emf.core.OperationListener;
import org.eclipse.gmf.runtime.emf.core.ResourceSetModifyOperation;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;
import org.eclipse.gmf.tests.runtime.emf.core.BaseTestCase;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;

/**
 * Tests for Operation Listener
 * 
 * @author mgoyal
 */
public class OperationListenerTestCase
	extends BaseTestCase {

	/**
	 * Base Constructor.
	 * 
	 * @param name
	 */
	public OperationListenerTestCase(String name) {
		super(name);
	}

	private static final String CLASS_NAME = "MyTestClass"; //$NON-NLS-1$

	private Resource res;

	private Model model;
	private EditingDomain domain = MSLEditingDomain.INSTANCE;
	private TestOperationListener listener;

	protected void setUp()
		throws Exception {

		super.setUp();

		res = ResourceUtil.create("\\tmp\\operationUtilTest.emx", //$NON-NLS-1$
			UML2Package.eINSTANCE.getModel(), 0);

		model = (Model) ResourceUtil.getFirstRoot(res);
		listener = new TestOperationListener();
		domain.addOperationListener(listener);
	}

	protected void tearDown()
		throws Exception {
		model = null;

		domain.removeOperationListener(listener);
		listener = null;

		ResourceSet rset = res.getResourceSet();
		ResourceUtil.unload(res);
		rset.getResources().remove(res);

		super.tearDown();
	}

	/**
	 * @author mgoyal
	 *
	 */
	private class ModifyOperation extends ResourceSetModifyOperation implements Runnable {

		private boolean bSuccessful = false;
		/**
		 * @param label
		 */
		public ModifyOperation() {
			super("Test Modify Operation"); //$NON-NLS-1$
		}
		
		/**
		 * @see org.eclipse.gmf.runtime.emf.core.ResourceSetOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
		 */
		protected void execute(IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {
			// change the model
			((NamedElement) model.createOwnedMember(
				UML2Package.eINSTANCE.getClass_())).setName(CLASS_NAME);
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			try {
				domain.run(this, new NullProgressMonitor());
				bSuccessful = true;
			} catch (InvocationTargetException e) {
				bSuccessful = false;
			} catch (InterruptedException e) {
				bSuccessful = false;
			}
		}
		
		public boolean isSuccessful() {
			return bSuccessful;
		}
	}
	
	private class TestOperationListener extends OperationListener {
		public Thread doneThread;
		public Thread undoneThread;
		public Thread redoneThread;
		/**
		 * @see org.eclipse.gmf.runtime.emf.core.OperationListener#done(org.eclipse.gmf.runtime.emf.core.IOperationEvent)
		 */
		public void done(IOperationEvent event) {
			doneThread = Thread.currentThread();
			super.done(event);
		}
		
		
		/**
		 * @see org.eclipse.gmf.runtime.emf.core.OperationListener#undone(org.eclipse.gmf.runtime.emf.core.IOperationEvent)
		 */
		public void undone(IOperationEvent event) {
			undoneThread = Thread.currentThread();
			super.undone(event);
		}
		
		
		/**
		 * @see org.eclipse.gmf.runtime.emf.core.OperationListener#redone(org.eclipse.gmf.runtime.emf.core.IOperationEvent)
		 */
		public void redone(IOperationEvent event) {
			redoneThread = Thread.currentThread();
			super.redone(event);
		}
		
		public void reset() {
			undoneThread = null;
			doneThread = null;
			redoneThread = null;
		}
	}
	
	/**
	 * Tests if the operation listener was notified on the right thread.
	 */
	public void test_OperationListenerNotified() {
		ModifyOperation operation = new ModifyOperation();
		Thread thread = new Thread(operation);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			assertTrue(false);
		}
		
		assertTrue(listener.doneThread == thread);
		assertTrue(listener.undoneThread == null);
		assertTrue(listener.redoneThread == null);
		
		listener.reset();
	}
}
