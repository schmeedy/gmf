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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.diagramrt.ChildNode;
import org.eclipse.gmf.diagramrt.DiagramNode;
import org.eclipse.gmf.edit.provider.metainfo.AdapterFactoryMetaInfoSource;
import org.eclipse.gmf.edit.provider.metainfo.MetaInfoSource;
import org.eclipse.gmf.edit.provider.metainfo.NodeMetaInfoProvider;

public class CreateChildNodeCommand extends Command {

	final ChildNode myCreatedNode;

	final DiagramNode myParent;

	private final org.eclipse.emf.common.command.Command myDomainModelAddCommand;

	public CreateChildNodeCommand(ChildNode childNode, DiagramNode parent) {
		myCreatedNode = childNode;
		myParent = parent;
		myDomainModelAddCommand = createCommand();
	}

	protected org.eclipse.emf.common.command.Command createCommand() {
		EditingDomain domainModelEditDomain = AdapterFactoryEditingDomain.getEditingDomainFor(myParent.getDiagram().getDomainContainerObject());
		return AddCommand.create(domainModelEditDomain, myParent.getDomainModelElement(), getContainmentFeature(), myCreatedNode.getDomainModelElement());
	}

	public boolean canExecute() {
		return myDomainModelAddCommand.canExecute();
	}

	public void execute() {
		myDomainModelAddCommand.execute();
		myParent.getChildNodes().add(myCreatedNode);
	}

	public boolean canUndo() {
		return myDomainModelAddCommand.canUndo();
	}

	public void undo() {
		myParent.getChildNodes().remove(myCreatedNode);
		myDomainModelAddCommand.undo();
	}

	protected EStructuralFeature getContainmentFeature() {
		NodeMetaInfoProvider nmi = getParentMetaInfo();
		assert nmi != null;
		if (nmi == null) return null; // FIXME remove
		return nmi.getContainmentFeature(myParent, myCreatedNode);
	}

	protected NodeMetaInfoProvider getParentMetaInfo() {
		MetaInfoSource metaInfoSource = AdapterFactoryMetaInfoSource.getMetaInfoSource(myParent.getDiagram());
		return metaInfoSource.getNodeInfo(myParent);
	}
}
