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

package org.eclipse.gmf.runtime.emf.core.internal.util;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.core.EventTypes;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * Advice that fires a <code>PRE_DESTROY</code> event before the destruction of
 * an {@link EObject} and <code>DESTROY</code> after.
 * 
 * @author Christian W. Damus (cdamus)
 */
public class EObjectDestructionAdvice
	extends AbstractEditHelperAdvice {
	
	static final CommandResult OK_RESULT = new CommandResult(Status.OK_STATUS);
	
	protected ICommand getBeforeDestroyElementCommand(DestroyElementRequest request) {
		final EObject eObject = request.getElementToDestroy();
		
		return new AbstractCommand("") { //$NON-NLS-1$
			
			protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
				eObject.eNotify(new ENotificationImpl(
						(InternalEObject) eObject, EventTypes.PRE_DESTROY,
						(EStructuralFeature) null,
						eObject, eObject));
				return OK_RESULT;
			}

			protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
				eObject.eNotify(new ENotificationImpl(
						(InternalEObject) eObject, EventTypes.PRE_DESTROY,
						(EStructuralFeature) null,
						eObject, eObject));
				return OK_RESULT;
			}

			protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
				// there is no inverse of pre-destroy
				return OK_RESULT;
			}};
	}
	
	protected ICommand getAfterDestroyElementCommand(DestroyElementRequest request) {
		final EObject eObject = request.getElementToDestroy();
		
		return new AbstractCommand("") { //$NON-NLS-1$
			
			protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
				eObject.eNotify(new ENotificationImpl(
						(InternalEObject) eObject, EventTypes.DESTROY,
						(EStructuralFeature) null,
						eObject, eObject));
				return OK_RESULT;
			}

			protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
				// MSL change recorder takes care of the redo event
				return OK_RESULT;
			}

			protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
				// MSL change recorder takes care of the UNDESTROY event
				return OK_RESULT;
			}};
	}
}
