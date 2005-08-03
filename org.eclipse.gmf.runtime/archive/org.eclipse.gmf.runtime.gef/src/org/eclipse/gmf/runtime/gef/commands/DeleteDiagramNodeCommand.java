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

import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramNode;

public class DeleteDiagramNodeCommand extends Command {
	private final DiagramNode myNode;
	private final org.eclipse.emf.common.command.Command myDomainDeleteCommand;
	private int myOldIndex = -1;
	private DiagramCanvas myDiagram; 

	public DeleteDiagramNodeCommand(DiagramNode diagramNode) {
		myNode = diagramNode;
		EditingDomain domainModelEditDomain = AdapterFactoryEditingDomain.getEditingDomainFor(diagramNode.getDiagram().getDomainContainerObject());
		myDomainDeleteCommand = RemoveCommand.create(domainModelEditDomain, diagramNode.getDomainModelElement());
	}

	public boolean canExecute() {
		// FIXME - handle incoming and outgoing links correctly
		boolean tempHack = myNode.getIncomingLinks().isEmpty() && myNode.getOutgoingLinks().isEmpty();
		return tempHack && myDomainDeleteCommand.canExecute() && myNode.getDiagram().getNodes().indexOf(myNode) != -1;
	}

	public void execute() {
		// preserve index, to avoid node reordering on undo
		myDiagram = myNode.getDiagram();
		myOldIndex = myDiagram.getNodes().indexOf(myNode);
		myDomainDeleteCommand.execute();
		myDiagram.getNodes().remove(myOldIndex);
	}

	public boolean canUndo() {
		return myDomainDeleteCommand.canUndo() && myOldIndex != -1 && myDiagram != null;
	}

	public void undo() {
		myDomainDeleteCommand.undo();
		myDiagram.getNodes().add(myOldIndex, myNode);
	}
}
