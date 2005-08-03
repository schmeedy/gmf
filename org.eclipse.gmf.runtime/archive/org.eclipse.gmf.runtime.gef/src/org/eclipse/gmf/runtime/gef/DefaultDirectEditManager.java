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
package org.eclipse.gmf.runtime.gef;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Text;

public class DefaultDirectEditManager extends DirectEditManager {
	private final DirectEditInfoProvider myInfoProvider;

	public DefaultDirectEditManager(GraphicalEditPart source, DirectEditInfoProvider infoProvider) {
		super(source, TextCellEditor.class, new DefaultCellEditorLocator(infoProvider.getDirectEditFigure()));
		myInfoProvider = infoProvider;
	}

	protected DirectEditRequest createDirectEditRequest() {
		DirectEditRequest deRequest = super.createDirectEditRequest();
		deRequest.setDirectEditFeature(getInfoProvider().getDirectEditFeature());
		return deRequest;
	}

	protected DirectEditInfoProvider getInfoProvider() {
		return myInfoProvider;
	}

	protected void initCellEditor() {
		Text textControl = (Text) getCellEditor().getControl();
		textControl.setText(getInfoProvider().getInitialText());
		textControl.setFont(getInfoProvider().getTextFont());
		textControl.selectAll();
	}

	public interface DirectEditInfoProvider {
		Object getDirectEditFeature();
		String getInitialText();
		IFigure getDirectEditFigure();
		Font getTextFont();
	}
}
