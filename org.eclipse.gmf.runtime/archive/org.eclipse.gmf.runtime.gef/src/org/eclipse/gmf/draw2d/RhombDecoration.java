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
package org.eclipse.gmf.draw2d;

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Color;

public class RhombDecoration extends PolygonDecoration {
	public static final PointList RHOMB_TIP = new PointList();
	static {
		RHOMB_TIP.addPoint(-1, 1);
		RHOMB_TIP.addPoint(0, 0);
		RHOMB_TIP.addPoint(-1, -1);
		RHOMB_TIP.addPoint(-2, 0);
	}
	
	public RhombDecoration() {
		setFill(true);
		super.setTemplate(RHOMB_TIP);
		//setScale();
	}

	public final void setTemplate(PointList pl) {
		throw new UnsupportedOperationException();
	}

	public Color getLocalBackgroundColor() {
		// use parent's background
		return null;
	}
}
