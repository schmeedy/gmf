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

import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.impl.EMFCommandTransaction;
import org.eclipse.emf.transaction.util.TriggerCommand;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;


/**
 * Customization of the transactional command stack for MSL, to create
 * {@link MSLTransaction}s where appropriate.
 *
 * @author Christian W. Damus (cdamus)
 */
public class MSLCommandStack
	extends WorkspaceCommandStackImpl {

	/**
	 * Initializes me with the operation history to which I delegate.
	 * 
	 * @param history my delegated history
	 */
	public MSLCommandStack(IOperationHistory history) {
		super(history);
	}

	public void executeTriggers(Command command, List triggers, Map options)
		throws InterruptedException, RollbackException {
		
		if (!triggers.isEmpty()) {
			TriggerCommand trigger = (command == null)
				? new TriggerCommand(triggers)
				: new TriggerCommand(command, triggers);
			
			Transaction tx = createTransaction(trigger, options);
			
			try {
				trigger.execute();
				
				// if the triggers ultimately stem from a command operation, then
				//    it needs to know the triggers to undo/redo
				EMFCommandOperation oper = getEMFCommandOperation(tx);
				if (oper != null) {
					oper.setTriggerCommand(trigger);
				} else if (tx.getParent() instanceof MSLTransactionImpl) {
					MSLTransactionImpl msltx = (MSLTransactionImpl) tx.getParent();
					
					if (msltx.isStandAlone()) {
						msltx.addTriggerCommand(trigger);
					}
				}
			} finally {
				if ((tx != null) && (tx.isActive())) {
					// commit the transaction now
					tx.commit();
				}
			}
		}
	}
	
	public EMFCommandTransaction createTransaction(Command command, Map options)
		throws InterruptedException {
		EMFCommandTransaction result;
		
		if (command instanceof TriggerCommand) {
			// do not create an MSLOperationTransaction for the triggers because
			//     triggers are commands, not operations, and we don't want to
			//     find this transaction to add triggers to
			result = new MSLCommandTransaction(command, getDomain(), options);
		} else {
			result = new MSLOperationTransaction(command, getDomain(), options);
		}
		
		result.start();
		
		return result;
	}
}
