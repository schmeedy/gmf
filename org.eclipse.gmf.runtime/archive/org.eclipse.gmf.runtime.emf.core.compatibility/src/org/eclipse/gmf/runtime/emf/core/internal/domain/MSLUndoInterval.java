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
package org.eclipse.gmf.runtime.emf.core.internal.domain;

import java.util.Map;

import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionChangeDescription;
import org.eclipse.emf.transaction.impl.TransactionImpl;
import org.eclipse.emf.transaction.util.CompositeChangeDescription;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLRuntimeException;

/**
 * Default implementation of the undo interval.
 *
 * @author Christian W. Damus (cdamus)
 */
class MSLUndoInterval implements MUndoInterval {
	private MSLEditingDomain domain;
	private final String label;
	private final String description;
	
	private boolean isRedone = true;
	
	// client overrides for undoability/redoability
	private boolean undoable = true;
	private boolean redoable = true;
	
	private CompositeChangeDescription change = new CompositeChangeDescription();
	
	static final Map UNDO_REDO_OPTIONS;
	
	static {
		UNDO_REDO_OPTIONS = new java.util.HashMap(
			TransactionImpl.DEFAULT_UNDO_REDO_OPTIONS);
		
		// add the unprotected option because MSL allows undo/redo in a
		//     read action (read-only transaction)
		UNDO_REDO_OPTIONS.put(Transaction.OPTION_UNPROTECTED, Boolean.TRUE);
	}
	
	MSLUndoInterval(MSLEditingDomain domain, String label, String description) {
		this.domain = domain;
		this.label = label;
		this.description = description;
	}
	
	void addChangeDescription(TransactionChangeDescription change) {
		this.change.add(change);
	}
	
	// Documentation copied from the inherited specification
	public String getLabel() {
		return label;
	}

	// Documentation copied from the inherited specification
	public String getDescription() {
		return description;
	}

	// Documentation copied from the inherited specification
	public boolean canUndo() {
		return isRedone && undoable && (change == null || change.canApply());
	}
	
	/**
	 * Allows the client to override undoability to <code>false</code>.
	 * 
	 * @param canUndo <code>false</code> to force non-undoability;
	 *     <code>true</code>, otherwise
	 */
	void setCanUndo(boolean canUndo) {
		undoable = canUndo;
	}

	// Documentation copied from the inherited specification
	public boolean canRedo() {
		return !isRedone && redoable && (change == null || change.canApply());
	}

	/**
	 * Allows the client to override redoability to <code>false</code>.
	 * 
	 * @param canRedo <code>false</code> to force non-redoability;
	 *     <code>true</code>, otherwise
	 */
	void setCanRedo(boolean canRedo) {
		redoable = canRedo;
	}

	// Documentation copied from the inherited specification
	public void undo() {
		Transaction tx = null;
		domain.setUndoInProgress(true);
		
		try {
			tx = domain.startTransaction(false, UNDO_REDO_OPTIONS);
			isRedone = false;
			if (change != null) {
				change.applyAndReverse();
			}
		} catch (InterruptedException e) {
			throw new MSLRuntimeException(e);
		} finally {
			try {
				if (tx != null) {
					tx.commit();
				}
			} catch (RollbackException e) {
				// shouldn't happen because we don't validate undo/redo
				throw new MSLRuntimeException(e);
			} finally {
				domain.setUndoInProgress(false);
			}
		}
	}

	// Documentation copied from the inherited specification
	public void redo() {
		Transaction tx = null;
		domain.setRedoInProgress(true);
		
		try {
			tx = domain.startTransaction(false, UNDO_REDO_OPTIONS);
			isRedone = true;
			if (change != null) {
				change.applyAndReverse();
			}
		} catch (InterruptedException e) {
			throw new MSLRuntimeException(e);
		} finally {
			try {
				if (tx != null) {
					tx.commit();
				}
			} catch (RollbackException e) {
				// shouldn't happen because we don't validate undo/redo
				throw new MSLRuntimeException(e);
			} finally {
				domain.setRedoInProgress(false);
			}
		}
	}

	// Documentation copied from the inherited specification
	public boolean isEmpty() {
		return (change == null) || change.isEmpty();
	}

	// Documentation copied from the inherited specification
	public void flush() {
		domain = null;
		change = null;
	}
}
