/*
 * Copyright (c) 2005 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.runtime.actions;

import java.util.Arrays;
import java.util.List;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.diagramrt.DiagramNode;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public abstract class AbstractDiagramNodeAction implements IObjectActionDelegate {
	private List mySelectedNodes;
	private IWorkbenchPart myPart;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		myPart = targetPart;
	}

	protected List getSelectedNodes() {
		return mySelectedNodes;
	}

	protected CommandStack getCommandStack() {
		return (CommandStack) myPart.getAdapter(CommandStack.class);
	}

	protected IWorkbenchPart getTargetPart() {
		return myPart;
	}

	protected Shell getShell() {
		return myPart.getSite().getShell();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection == false || selection.isEmpty()) {
			action.setEnabled(false);
			return;
		}
		Object[] sel = ((IStructuredSelection) selection).toArray();
		for (int i = 0; i < sel.length; i++) {
			if (sel[i] instanceof DiagramNode == false) {
				action.setEnabled(false);
				return;
			}
		}
		action.setEnabled(true);
		mySelectedNodes = Arrays.asList(sel);
	}
}
