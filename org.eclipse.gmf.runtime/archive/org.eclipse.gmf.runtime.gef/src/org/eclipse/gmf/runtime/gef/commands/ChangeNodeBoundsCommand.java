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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.diagramrt.DiagramNode;

public class ChangeNodeBoundsCommand extends Command {
	private final DiagramNode myNode;
	private final Point myNewLocation;
	private final Dimension myNewSize;
	private Point myOldLocation;
	private Dimension myOldSize;

	public ChangeNodeBoundsCommand(DiagramNode node, Point newLocation, Dimension newSize) {
		super("Change node position/size");
		myNode = node;
		myNewLocation = newLocation;
		myNewSize = newSize;
	}

	public boolean canExecute() {
		return true;
	}

	public void execute() {
		myOldSize = myNode.getSize();
		myOldLocation = myNode.getLocation();
		redo();
	}

	public void redo() {
		myNode.setLocation(myNewLocation);
		myNode.setSize(myNewSize);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		myNode.setLocation(myOldLocation);
		myNode.setSize(myOldSize);
	}
}
