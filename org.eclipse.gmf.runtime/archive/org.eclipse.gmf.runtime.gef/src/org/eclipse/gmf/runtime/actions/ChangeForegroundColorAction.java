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

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.diagramrt.DiagramNode;
import org.eclipse.gmf.runtime.gef.ColorSupport;
import org.eclipse.gmf.runtime.gef.commands.ChangeNodeColorCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;

public class ChangeForegroundColorAction extends AbstractDiagramNodeAction implements IInputValidator {
	private ColorSupport myColorSupport;

	public void run(IAction action) {
		myColorSupport = new ColorSupport();
		String defValue = getSelectedNodes().size() == 1 ? ((DiagramNode) getSelectedNodes().get(0)).getForegroundColor() : "";
		InputDialog dlg = new InputDialog(getShell(), "Foreground color", "Enter new color value", defValue, this);
		if (InputDialog.OK == dlg.open()) {
			CompoundCommand ccmd = new CompoundCommand();
			for (Iterator it = getSelectedNodes().iterator(); it.hasNext(); ) {
				DiagramNode next = (DiagramNode) it.next();
				ccmd.add(new ChangeNodeColorCommand(next, dlg.getValue(), true));
			}
			getCommandStack().execute(ccmd.unwrap());
		}
		myColorSupport.dispose();
		myColorSupport = null;
	}

	public String isValid(String newText) {
		return myColorSupport.isValid(newText) ? null : "Color value is incorrect";
	}

}
