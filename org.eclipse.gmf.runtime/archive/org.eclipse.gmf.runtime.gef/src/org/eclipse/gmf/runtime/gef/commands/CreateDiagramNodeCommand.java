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

import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramNode;

public class CreateDiagramNodeCommand extends AbstractDiagramNodeCommand {
	protected final DiagramCanvas myDiagram;
	protected final DiagramNode myCreatedNode;
	private final org.eclipse.emf.common.command.Command myDomainModelAddCommand;

	public CreateDiagramNodeCommand(DiagramNode diagramNode, DiagramCanvas diagram) {
		myDiagram = diagram;
		myCreatedNode = diagramNode;
		myDomainModelAddCommand = createCommand();
	}

	protected org.eclipse.emf.common.command.Command createCommand() {
		EditingDomain domainModelEditDomain = AdapterFactoryEditingDomain.getEditingDomainFor(myDiagram.getDomainContainerObject());
		return AddCommand.create(domainModelEditDomain, myDiagram.getDomainContainerObject(), getContainmentFeature(), myCreatedNode.getDomainModelElement());
	}

	public boolean canExecute() {
		return myDomainModelAddCommand.canExecute();
	}

	public void execute() {
		myCreatedNode.setUin(myDiagram.nextAvailableUin());
		myDomainModelAddCommand.execute();
		myDiagram.getNodes().add(myCreatedNode);
	}

	public boolean canUndo() {
		return myDomainModelAddCommand.canUndo();
	}

	public void undo() {
		myDiagram.getNodes().remove(myCreatedNode);
		myDomainModelAddCommand.undo();
	}

	protected DiagramCanvas getDiagram() {
		return myDiagram;
	}

	protected DiagramNode getNode() {
		return myCreatedNode;
	}
}
