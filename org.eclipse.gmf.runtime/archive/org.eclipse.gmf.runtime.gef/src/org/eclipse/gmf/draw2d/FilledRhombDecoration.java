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

import org.eclipse.swt.graphics.Color;


public class FilledRhombDecoration extends RhombDecoration {

	public FilledRhombDecoration() {
	}

	public Color getLocalBackgroundColor() {
		if (super.getLocalForegroundColor() != null) {
			// use local, once set
			return super.getLocalForegroundColor();
		}
		// otherwise, make sure we use foreground color to fill
		return getForegroundColor();
	}
}
