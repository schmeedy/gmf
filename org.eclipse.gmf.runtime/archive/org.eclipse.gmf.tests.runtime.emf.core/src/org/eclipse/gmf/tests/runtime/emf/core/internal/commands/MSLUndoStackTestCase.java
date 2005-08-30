/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */

package org.eclipse.gmf.tests.runtime.emf.core.internal.commands;

import org.eclipse.gmf.runtime.emf.core.internal.commands.MSLUndoStack;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;
import org.eclipse.gmf.runtime.emf.core.internal.exceptions.MSLActionProtocolException;
import org.eclipse.gmf.tests.runtime.emf.core.BaseTestCase;


/**
 * Unit tests for the {@link MSLUndoStack} class.
 *
 * @author Christian W. Damus (cdamus)
 */
public class MSLUndoStackTestCase
	extends BaseTestCase {
	
	private MSLUndoStack stack;
	
	public MSLUndoStackTestCase(String name) {
		super(name);
	}
	
	/**
	 * Tests that nesting a read in a read is OK.
	 */
	public void test_readRead() {
		startRead();
		startRead();
		complete();
		complete();
	}
	
	/**
	 * Tests that nesting a read in a write is OK.
	 */
	public void test_writeRead() {
		openUndo();
		startWrite();
		startRead();
		complete();
		complete();
		closeUndo();
	}
	
	/**
	 * Tests that nesting a write action in a read is <b>not</b> OK.
	 */
	public void test_readWrite() {
		startRead();
		
		try {
			stack.startAction(MSLUndoStack.ActionLockMode.WRITE);
			fail("Did not get a protocol exception."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
		
		complete();
		
		try {
			stack.completeAction();
			fail("Should not have had an action to complete."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
	}
	
	/**
	 * Tests that nesting an undo interval in a read is <b>not</b> OK.
	 */
	public void test_readOpenUndo() {
		startRead();
		
		try {
			stack.openUndoInterval("Label", "Description"); //$NON-NLS-1$ //$NON-NLS-2$
			fail("Did not get a protocol exception."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
		
		try {
			stack.closeUndoInterval();
			fail("Should not have had an undo interval to close."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
		
		complete();
	}
	
	/**
	 * Tests that nesting an undo interval in an undo interval is <b>not</b> OK.
	 */
	public void test_openUndoOpenUndo() {
		openUndo();

		try {
			stack.openUndoInterval("Label", "Description"); //$NON-NLS-1$ //$NON-NLS-2$
			fail("Did not get a protocol exception."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
		
		closeUndo();
				
		try {
			stack.closeUndoInterval();
			fail("Should not have had an undo interval to close."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
	}
	
	/**
	 * Tests that nesting an undo interval in a write action is <b>not</b> OK.
	 */
	public void test_writeOpenUndo() {
		openUndo();
		startWrite();

		try {
			stack.openUndoInterval("Label", "Description"); //$NON-NLS-1$ //$NON-NLS-2$
			fail("Did not get a protocol exception."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
				
		try {
			stack.closeUndoInterval();
			fail("Should not have had an undo interval to close."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
		
		complete();
		closeUndo();
	}
	
	/**
	 * Tests that nesting a write in a write is <b>not</b> OK.
	 */
	public void test_writeWrite() {
		openUndo();
		startWrite();

		try {
			stack.startAction(MSLUndoStack.ActionLockMode.WRITE);
			fail("Did not get a protocol exception."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
		
		complete();
		
		try {
			stack.completeAction();
			fail("Should not have had an action to complete."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
		
		closeUndo();
	}
	
	/**
	 * Tests that nesting a read in an unchecked is OK.
	 */
	public void test_uncheckedRead() {
		startUnchecked();
		startRead();
		complete();
		complete();
	}
	
	/**
	 * Tests that nesting an unchecked in a read is OK.
	 */
	public void test_readUnchecked() {
		startRead();
		startUnchecked();
		complete();
		complete();
	}
	
	/**
	 * Tests that nesting a write in an unchecked is OK.
	 */
	public void test_uncheckedWrite() {
		startUnchecked();
		openUndo();
		startWrite();
		complete();
		closeUndo();
		complete();
	}
	
	/**
	 * Tests that nesting an unchecked in a write is OK.
	 */
	public void test_writeUnchecked() {
		openUndo();
		startWrite();
		startUnchecked();
		complete();
		complete();
		closeUndo();
	}
	
	/**
	 * Tests that nesting an unchecked in an unchecked is OK.
	 */
	public void test_uncheckedUnchecked() {
		startUnchecked();
		startUnchecked();
		complete();
		complete();
	}
	
	/**
	 * Tests that a write in an undo interval is OK.
	 */
	public void test_writeWithUndo() {
		openUndo();
		startWrite();
		complete();
		closeUndo();
	}
	
	/**
	 * Tests that a write without an undo interval is <b>not</b> OK.
	 */
	public void test_writeWithoutUndo() {
		try {
			stack.startAction(MSLUndoStack.ActionLockMode.WRITE);
			fail("Should have got a protocol exception."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
		
		try {
			stack.completeAction();
			fail("Should not have an action to complete."); //$NON-NLS-1$
		} catch (MSLActionProtocolException e) {
			// success
		}
	}
	
	
	//
	// Fixture methods
	//
	
	
	protected void setUp()
		throws Exception {
		
		super.setUp();
		
		stack = new MSLUndoStack(new MSLEditingDomain());
	}
	
	protected void tearDown()
		throws Exception {
		
		stack = null;

		super.tearDown();
	}
	
	private void startRead() {
		try {
			stack.startAction(MSLUndoStack.ActionLockMode.READ);
		} catch (MSLActionProtocolException e) {
			fail("Got a protocol exception: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
	
	private void startUnchecked() {
		try {
			stack.startAction(MSLUndoStack.ActionLockMode.UNCHECKED);
		} catch (MSLActionProtocolException e) {
			fail("Got a protocol exception: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
	
	private void startWrite() {
		try {
			stack.startAction(MSLUndoStack.ActionLockMode.WRITE);
		} catch (MSLActionProtocolException e) {
			fail("Got a protocol exception: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
	
	private void complete() {
		try {
			stack.completeAction();
		} catch (MSLActionProtocolException e) {
			fail("Got a protocol exception: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
	
	private void openUndo() {
		try {
			stack.openUndoInterval("Label", "Description");  //$NON-NLS-1$//$NON-NLS-2$
		} catch (MSLActionProtocolException e) {
			fail("Got a protocol exception: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
	
	private void closeUndo() {
		try {
			stack.closeUndoInterval();
		} catch (MSLActionProtocolException e) {
			fail("Got a protocol exception: " + e.getLocalizedMessage()); //$NON-NLS-1$
		}
	}
}
