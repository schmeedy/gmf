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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionImpl;


/**
 * Customization of the basic transaction class for MSL, to implement the
 * {@link MSLTransaction} interface.
  *
 * @author Christian W. Damus (cdamus)
 */
public class MSLTransactionImpl
	extends TransactionImpl
	implements MSLTransaction {

	private boolean isStandAlone;
	private int readTransactionsToRewind = 0;
	
	public MSLTransactionImpl(TransactionalEditingDomain domain, boolean readOnly, Map options) {
		super(domain, readOnly, options);
	}

	/**
	 * Sets whether I am an autonomous transaction, not created by execution of
	 * a command on the command stack nor by execution of an operation on the
	 * operation history.
	 * 
	 * @param standAlone <code>true</code> if I was simply created as an
	 *     undo interval in a vacuum; <code>false</code>, otherwise
	 */
	public void setStandAlone(boolean standAlone) {
		isStandAlone = standAlone;
	}
	
	/**
	 * Queries whether I am an autonomous transaction, not created by execution
	 * of a command on the command stack nor by execution of an operation on the
	 * operation history.
	 * 
	 * @return <code>true</code> if I was simply created as an
	 *     undo interval in a vacuum; <code>false</code>, otherwise
	 */
	public boolean isStandAlone() {
		return isStandAlone;
	}
	
	public final void setReadTransactionsToRewind(int reads) {
		readTransactionsToRewind = reads;
	}
	
	public final int getReadTransactionsToRewind() {
		return readTransactionsToRewind;
	}
	
	/**
	 * Adds a trigger command to me, to be included in my change description for
	 * undo/redo.
	 * 
	 * @param triggerCommand the trigger command to add
	 */
	public void addTriggerCommand(Command triggerCommand) {
		change.add(new CommandChangeDescription(triggerCommand));
	}
}
