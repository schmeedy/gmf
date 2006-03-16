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

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.impl.EMFCommandTransaction;
import org.eclipse.emf.transaction.impl.TriggerCommandTransaction;
import org.eclipse.emf.transaction.util.TriggerCommand;
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
	
	public EMFCommandTransaction createTransaction(Command command, Map options)
		throws InterruptedException {
		EMFCommandTransaction result;
		
		if (command instanceof TriggerCommand) {
			result = new TriggerCommandTransaction((TriggerCommand) command,
					getDomain(), options);
		} else {
			result = new MSLOperationTransaction(command, getDomain(), options);
		}
		
		result.start();
		
		return result;
	}
}
