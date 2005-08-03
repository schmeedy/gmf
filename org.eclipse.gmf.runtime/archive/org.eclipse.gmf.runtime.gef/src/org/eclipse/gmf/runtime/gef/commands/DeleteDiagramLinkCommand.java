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

import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramLink;
import org.eclipse.gmf.diagramrt.DiagramNode;

public class DeleteDiagramLinkCommand extends AbstractDiagramLinkCommand {
	private DiagramCanvas myOwningDiagram;
	private DiagramNode myOldSource;
	private DiagramNode myOldTarget;
	private final org.eclipse.emf.common.command.Command myDomainModelDeleteCommand;

	public DeleteDiagramLinkCommand(DiagramLink diagramLink) {
		super("Delete link both from diagram and model", diagramLink);
		EditingDomain domainModelEditDomain = AdapterFactoryEditingDomain.getEditingDomainFor(getLink().getDiagram().getDomainContainerObject());
		if (getLink().getDomainModelElement() != null) {
			// XXX need for link owning feature
			myDomainModelDeleteCommand = RemoveCommand.create(domainModelEditDomain, diagramLink.getDomainModelElement());
		} else {
			if (diagramLink.getSource() != null && diagramLink.getTarget() != null) {
				myDomainModelDeleteCommand = RemoveCommand.create(domainModelEditDomain, 
					diagramLink.getSource().getDomainModelElement(),
					getLinkTargetFeature(),
					diagramLink.getTarget().getDomainModelElement());
			} else {
				myDomainModelDeleteCommand = IdentityCommand.INSTANCE;
			}
		}
	}

	public boolean canExecute() {
		return getLink().getDiagram() != null && myDomainModelDeleteCommand.canExecute();
	}

	public boolean canUndo() {
		return myOwningDiagram != null && myDomainModelDeleteCommand.canUndo();
	}

	public void execute() {
		myOwningDiagram = getLink().getDiagram();
		myOldSource = getLink().getSource();
		myOldTarget = getLink().getTarget();
		myOwningDiagram.getLinks().remove(getLink());
		getLink().setSource(null);
		getLink().setTarget(null);
		myDomainModelDeleteCommand.execute();
	}

	public void undo() {
		myDomainModelDeleteCommand.undo();
		setLinkSourceAndTarget();
		myOwningDiagram.getLinks().add(getLink());
		myOwningDiagram = null;
	}

	private void setLinkSourceAndTarget() {
		boolean oldSourceDeliverValue = myOldSource == null ? false : myOldSource.eDeliver();
		boolean oldTargetDeliverValue = myOldTarget == null ? false : myOldTarget.eDeliver();
		try {
			conditionalSetDeliver(myOldSource, false);
			conditionalSetDeliver(myOldTarget, false);
			getLink().setSource(myOldSource);
			getLink().setTarget(myOldTarget);
		} finally {
			conditionalSetDeliver(myOldSource, oldSourceDeliverValue);
			conditionalSetDeliver(myOldTarget, oldTargetDeliverValue);
		}
	}

	protected DiagramCanvas getDiagram() {
		return myOwningDiagram;
	}

	private static void conditionalSetDeliver(EObject object, boolean value) {
		if (object != null) {
			object.eSetDeliver(value);
		}
	}
}
