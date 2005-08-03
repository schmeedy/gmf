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
package org.eclipse.gmf.runtime.gef.commands;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.diagramrt.DiagramNode;

/**
 * @author artem
 */
public class ChangeNodeColorCommand extends Command {
	private final String myNewColor;
	private final boolean myIsForeground;
	private final DiagramNode myNode;
	private String myOldColor;

	public ChangeNodeColorCommand(DiagramNode diagramNode, String newColor, boolean isForeground) {
		myNode = diagramNode;
		myNewColor = newColor;
		myIsForeground = isForeground;
	}

	public void execute() {
		if (myIsForeground) {
			myOldColor = myNode.getForegroundColor();
			myNode.setForegroundColor(myNewColor);
		} else {
			myOldColor = myNode.getBackgroundColor();
			myNode.setBackgroundColor(myNewColor);
		}
	}

	public void undo() {
		if (myIsForeground) {
			myNode.setForegroundColor(myOldColor);
		} else {
			myNode.setBackgroundColor(myOldColor);
		}
	}
}
