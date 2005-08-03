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
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Control;

public class DefaultCellEditorLocator implements CellEditorLocator {
	private final IFigure myDirectEditFigure;

	public DefaultCellEditorLocator(IFigure figure) {
		assert figure != null;
		myDirectEditFigure = figure;
	}

	public void relocate(CellEditor celleditor) {
		Control text = celleditor.getControl();

		Rectangle rect = null;
		if (myDirectEditFigure instanceof Label) {
			rect = ((Label)myDirectEditFigure).getTextBounds().intersect(myDirectEditFigure.getClientArea());
		} else {
			rect = myDirectEditFigure.getClientArea(Rectangle.SINGLETON);
		}
		myDirectEditFigure.translateToAbsolute(rect);
		
		int xOffset = -1;
		int wOffset = 1;
		int yOffset = -1;
		int hOffset = 1;
		text.setBounds(rect.x + xOffset, rect.y + yOffset, rect.width + wOffset, rect.height + hOffset);	
	}
}
