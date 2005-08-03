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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.diagramrt.DiagramBaseElement;
import org.eclipse.gmf.diagramrt.DiagramCanvas;

/**
 * Perhaps, even more generic 'ChangeDomainAttributeValue' as it has nothing to do with name, actually?
 * @author artem
 */
public class ChangeDiagramElementNameCommand extends Command {
	private final org.eclipse.emf.common.command.Command myChangeNameCommand; 

	public ChangeDiagramElementNameCommand(DiagramBaseElement diagramElement, EAttribute nameFeature, String newValue) {
		DiagramCanvas dc = diagramElement.getDiagram();
		EditingDomain domainModelEditDomain = AdapterFactoryEditingDomain.getEditingDomainFor(dc.getDomainContainerObject());
		myChangeNameCommand = SetCommand.create(domainModelEditDomain, diagramElement.getDomainModelElement(), nameFeature, newValue);
	}

	public boolean canExecute() {
		return myChangeNameCommand != null && myChangeNameCommand.canExecute();
	}

	public void execute() {
		myChangeNameCommand.execute();
		// XXX use diagramElement to notify?
	}

	public boolean canUndo() {
		return myChangeNameCommand.canUndo();
	} 

	public void undo() {
		myChangeNameCommand.undo();
	}
}
