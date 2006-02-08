/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core.edit;

/**
 * Enumeration that describes options that can be passed to run methods.
 * 
 * @see MEditingDomain#runWithOptions(MRunnable, int)
 * 
 * @author rafikj
 * 
 * @deprecated Use the {@link org.eclipse.emf.transaction.TXEditingDomain}'s
 *     {@link org.eclipse.emf.common.command.CommandStack} or
 *     {@link org.eclipse.emf.workbench.AbstractEMFOperation}s for modifying
 *     the resource set.  Where special options are required, these map to
 *     the {@link org.eclipse.emf.transaction.Transaction} options as follows:
 *     <ul>
 *       <li>SILENT ==&gt; Transaction.OPTION_NO_NOTIFICATIONS combined with
 *             Transaction.OPTION_NO_TRIGGERS</li>
 *       <li>UNVALIDATED ==&gt; Transaction.OPTION_NO_VALIDATION</li>
 *       <li>UNCHECKED ==&gt; Transaction.OPTION_UNPROTECTED</li>
 *       <li>NO_SEM_PROCS does not yet map; if/when clients adopt the trigger
 *           model instead, then this will map to Transaction.OPTION_NO_TRIGGERS</li>
 *     </ul>
 */
public final class MRunOption {

	/**
	 * Indicates that the runnable should be run without causing events to be
	 * sent.
	 * 
	 * @see MEditingDomain#runSilent(MRunnable)
	 */
	public static final int SILENT = 1;

	/**
	 * Indicates that the runnable should be run without being validated.
	 * 
	 * @see MEditingDomain#runUnvalidated(MRunnable)
	 */
	public static final int UNVALIDATED = 2;

	/**
	 * Indicates that the runnable should be run without causing semantic
	 * procedures to be run.
	 * 
	 * @see MEditingDomain#runWithNoSemProcs(MRunnable)
	 */
	public static final int NO_SEM_PROCS = 4;

	/**
	 * Indicates that the runnable should be run in an unchecked action and will
	 * take care of starting and completing the unchecked action.
	 * 
	 * @see MEditingDomain#runAsUnchecked(MRunnable)
	 */
	public static final int UNCHECKED = 8;

	/**
	 * Indicates that the runnable should be run without building reverse
	 * reference map.
	 * 
	 * @deprecated This option is discontinued.
	 */
	public static final int NO_REFERENCE_MANAGER = 16;

	/**
	 * Indicates that the runnable should build reverse reference map.
	 * 
	 * @deprecated This option is discontinued.
	 */
	public static final int REFERENCE_MANAGER = 32;

	private MRunOption() {
		// private
	}
}