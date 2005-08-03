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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramLink;
import org.eclipse.gmf.diagramrt.DiagramNode;

public class CreateDiagramLinkCommand extends AbstractDiagramLinkCommand {
	protected final DiagramNode mySource;
	private DiagramNode myTarget;
	private EStructuralFeature myLinkTargetFeature;
	private final org.eclipse.emf.common.command.Command myDomainModelAddLinkCommand;
	private org.eclipse.emf.common.command.Command myDomainModelSetLinkTargetCommand;

	public CreateDiagramLinkCommand(DiagramLink diagramLink, DiagramNode sourceNode) {
		super("Create diagram link", diagramLink);
		mySource = sourceNode;
		if (diagramLink.getDomainModelElement() != null) {
			myDomainModelAddLinkCommand = createCommand();
		} else {
			// nothing to add, link has no dedicated metaclass in the meta model, just a reference
			myDomainModelAddLinkCommand = null;
		}
		myLinkTargetFeature = getLinkTargetFeature();
	}

	protected org.eclipse.emf.common.command.Command createCommand() {
		EditingDomain domainModelEditDomain = getEditingDomain();
		// FIXME select feature of sourceNode.domainModelElement to add links to (DONE, but... see next line)
		// perhaps, it's not even feature of sourceNode.domainModelElement, but another container?
		// Also, it may not be necessarily AddCommand (e.g. link with strict '1' multiplicity 
		return AddCommand.create(domainModelEditDomain, mySource.getDomainModelElement(), getContainmentFeature(), getLink().getDomainModelElement());
	}

	private EditingDomain getEditingDomain() {
		return AdapterFactoryEditingDomain.getEditingDomainFor(getDiagram().getDomainContainerObject());
	}

	public void setTargetNode(DiagramNode targetNode) {
		myTarget = targetNode;
		assert myLinkTargetFeature != null;
		EObject linkTargetOwner;
		if (getLink().getDomainModelElement() == null) {
			linkTargetOwner = mySource.getDomainModelElement();
		} else {
			linkTargetOwner = getLink().getDomainModelElement();
		}
		if (myLinkTargetFeature.isMany()) {
			myDomainModelSetLinkTargetCommand = AddCommand.create(getEditingDomain(), linkTargetOwner, myLinkTargetFeature, myTarget.getDomainModelElement());
		} else {
			myDomainModelSetLinkTargetCommand = SetCommand.create(getEditingDomain(), linkTargetOwner, myLinkTargetFeature, myTarget.getDomainModelElement());
		}
	}

	public boolean canExecute() {
		boolean result = myDomainModelAddLinkCommand == null ? true : myDomainModelAddLinkCommand.canExecute();
		result &= myDomainModelSetLinkTargetCommand == null ? true : myDomainModelSetLinkTargetCommand.canExecute();
		return result;
	}

	public void execute() {
		if (myDomainModelSetLinkTargetCommand == null) {
			return;
		}
		getLink().setUin(getDiagram().nextAvailableUin());
		if (myDomainModelAddLinkCommand != null) {
			myDomainModelAddLinkCommand.execute();
		}
		myDomainModelSetLinkTargetCommand.execute();
		boolean oldSourceDeliverValue = mySource.eDeliver();
		boolean oldTargetDeliverValue = myTarget.eDeliver();
		try {
			mySource.eSetDeliver(false);
			myTarget.eSetDeliver(false);
			getLink().setSource(mySource);
			getLink().setTarget(myTarget);
		} finally {
			mySource.eSetDeliver(oldSourceDeliverValue);
			myTarget.eSetDeliver(oldTargetDeliverValue);
		}
		getDiagram().getLinks().add(getLink());
	}

	public boolean canUndo() {
		boolean result = myDomainModelAddLinkCommand == null ? true : myDomainModelAddLinkCommand.canUndo();
		result &= myDomainModelSetLinkTargetCommand == null ? false : myDomainModelSetLinkTargetCommand.canUndo();
		return result;
	}

	public void undo() {
		if (myDomainModelSetLinkTargetCommand == null) {
			// nothing done
			return;
		}
		myDomainModelSetLinkTargetCommand.undo();
		if (myDomainModelAddLinkCommand != null) {
			myDomainModelAddLinkCommand.undo();
		}
		getDiagram().getLinks().remove(getLink());
		getLink().setSource(null);
		getLink().setTarget(null);
	}

	protected DiagramCanvas getDiagram() {
		return mySource.getDiagram();
	}
}
