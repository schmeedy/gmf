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
import org.eclipse.gmf.diagramrt.DiagramLink;
import org.eclipse.gmf.edit.provider.metainfo.AdapterFactoryMetaInfoSource;
import org.eclipse.gmf.edit.provider.metainfo.LinkMetaInfoProvider;
import org.eclipse.gmf.edit.provider.metainfo.MetaInfoSource;

public abstract class AbstractDiagramLinkCommand extends Command {
	protected final DiagramLink myLink;

	protected AbstractDiagramLinkCommand(String label, DiagramLink link) {
		super(label);
		myLink = link;
	}

	protected final DiagramLink getLink() {
		return myLink;
	}

	protected abstract DiagramCanvas getDiagram();

	protected EStructuralFeature getContainmentFeature() {
		LinkMetaInfoProvider lmi = getMetaInfo();
		assert lmi != null;
		return lmi.getContainmentFeature(getLink());
	}

	protected EStructuralFeature getLinkTargetFeature() {
		LinkMetaInfoProvider lmi =getMetaInfo();
		assert lmi != null;
		return lmi.getTargetFeature(getLink());
	}

	protected LinkMetaInfoProvider getMetaInfo() {
		MetaInfoSource metaInfoSource = AdapterFactoryMetaInfoSource.getMetaInfoSource(getDiagram());
		return metaInfoSource.getLinkInfo(getLink());
	}
}
