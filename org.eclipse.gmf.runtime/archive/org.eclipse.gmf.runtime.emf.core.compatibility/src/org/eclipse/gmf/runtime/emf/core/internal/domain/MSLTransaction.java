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


/**
 * Mix-in interface for {@link org.eclipse.emf.transaction.Transaction}s, to
 * support upgrading read-only transactions to read/write.
 *
 * @author Christian W. Damus (cdamus)
 */
public interface MSLTransaction {
	/**
	 * Records, on this transaction, the number of read-only transactions that
	 * we had to commit when activating this one, in order to upgrade a read
	 * to a write.  We will later retrieve this number to restore the read-only
	 * transactions.
	 * 
	 * @param reads the number of read-only transactions that we will need to
	 *     restore upon deactivation of this transaction
	 */
	void setReadTransactionsToRewind(int reads);
	
	/**
	 * Obtains the number of read-only transactions to restore upon deactivation
	 * of this transaction.
	 * 
	 * @return the number of read actions to restor
	 */
	int getReadTransactionsToRewind();
}
