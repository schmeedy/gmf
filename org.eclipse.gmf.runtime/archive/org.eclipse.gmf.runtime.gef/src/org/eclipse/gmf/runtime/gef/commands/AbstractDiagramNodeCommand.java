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
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramNode;
import org.eclipse.gmf.edit.provider.metainfo.AdapterFactoryMetaInfoSource;
import org.eclipse.gmf.edit.provider.metainfo.MetaInfoSource;
import org.eclipse.gmf.edit.provider.metainfo.NodeMetaInfoProvider;

public abstract class AbstractDiagramNodeCommand extends Command {

	protected abstract DiagramCanvas getDiagram();
	protected abstract DiagramNode getNode();

	protected EStructuralFeature getContainmentFeature() {
		NodeMetaInfoProvider nmi = getMetaInfo();
		assert nmi != null;
		if (nmi == null) return null; // FIXME remove
		return nmi.getContainmentFeature(getNode());
	}

	protected NodeMetaInfoProvider getMetaInfo() {
		MetaInfoSource metaInfoSource = AdapterFactoryMetaInfoSource.getMetaInfoSource(getDiagram());
		return metaInfoSource.getNodeInfo(getNode());
	}
}
