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
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.workspace.impl.EMFOperationTransaction;


/**
 * Customization of the basic operation transaction for MSL, to implement the
 * {@link MSLTransaction} interface.
  *
 * @author Christian W. Damus (cdamus)
 */
public class MSLOperationTransaction
	extends EMFOperationTransaction
	implements MSLTransaction {
	
	private int readTransactionsToRewind = 0;
	
	public MSLOperationTransaction(Command command, InternalTransactionalEditingDomain domain, Map options) {
		super(command, domain, options);
	}

	public final void setReadTransactionsToRewind(int reads) {
		readTransactionsToRewind = reads;
	}
	
	public final int getReadTransactionsToRewind() {
		return readTransactionsToRewind;
	}

}
