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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl;
import org.eclipse.emf.transaction.TransactionChangeDescription;
import org.eclipse.emf.workspace.internal.EMFWorkspacePlugin;
import org.eclipse.emf.workspace.internal.EMFWorkspaceStatusCodes;
import org.eclipse.emf.workspace.internal.l10n.Messages;
import org.eclipse.osgi.util.NLS;

/**
 * A change description that simply wraps a {@link Command}, asking
 * it to undo or redo when {@link ChangeDescription#applyAndReverse() applying}.
 * 
 * @author Christian W. Damus (cdamus)
 */
public class CommandChangeDescription
		extends ChangeDescriptionImpl
		implements TransactionChangeDescription {
	private boolean isRedone = true;
	private Command command;

	/**
	 * Initializes me with the command that I encapsulate.
	 * 
	 * @param command the command that I encapsulate
	 */
	public CommandChangeDescription(Command command) {
		this.command = command;
	}
	
	/**
	 * I can apply if my wrapped command can undo or redo, according to whether
	 * it was last undone or redone.
	 * 
	 * @return <code>true</code> if my command can undo/redo;
	 *    <code>false</code>, otherwise
	 *    
	 * @see Command#canUndo()
	 */
	public boolean canApply() {
		return (command != null)
				&& (isRedone? command.canUndo() : true);
	}
	
	/**
	 * I apply my change by undoing the encapsulated command.  After it is
	 * undone, I dispose myself.
	 */
	public void apply() {
		try {
			command.undo();
		} catch (Exception e) {
			EMFWorkspacePlugin.INSTANCE.log(new Status(
				IStatus.ERROR,
				EMFWorkspacePlugin.getPluginId(),
				EMFWorkspaceStatusCodes.ROLLBACK_FAILED,
				NLS.bind(Messages.rollbackFailed, command.getLabel()),
				e));
		} finally {
			dispose();
		}
	}

	/**
	 * I apply-and-reverse by alternately undoing and redoing the encapsulated
	 * command.
	 */
	public void applyAndReverse() {
		try {
			if (isRedone) {
				command.undo();
				isRedone = false;
			} else {
				command.redo();
				isRedone = true;
			}
		} catch (Exception e) {
			EMFWorkspacePlugin.INSTANCE.log(new Status(
				IStatus.ERROR,
				EMFWorkspacePlugin.getPluginId(),
				EMFWorkspaceStatusCodes.ROLLBACK_FAILED,
				NLS.bind(Messages.rollbackFailed, command.getLabel()),
				e));
		}
	}
	
	/**
	 * I can only assume that the command I wrap makes some kind of change.
	 * 
	 * @return <code>false</code>, always
	 */
	public boolean isEmpty() {
		return command == null;
	}
	
	/**
	 * Disposes and forgets my command.
	 */
	public void dispose() {
		command.dispose();
		command = null;
	}
}
