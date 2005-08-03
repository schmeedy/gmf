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
package org.eclipse.gmf.util;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;

public class DefaultPalettePreferences implements FlyoutPreferences {
	private static final String PALETTE_DOCK_LOCATION = "Dock location"; //$NON-NLS-1$
	private static final String PALETTE_SIZE = "Palette Size"; //$NON-NLS-1$
	private static final String PALETTE_STATE = "Palette state"; //$NON-NLS-1$

	private final Preferences myPreferences;

	public DefaultPalettePreferences(Preferences prefs) {
		assert prefs != null;
		myPreferences = prefs;
	}

	public int getDockLocation() {
		return myPreferences.getInt(PALETTE_DOCK_LOCATION);
	}

	public int getPaletteState() {
		return myPreferences.getInt(PALETTE_STATE);
	}

	public int getPaletteWidth() {
		return myPreferences.getInt(PALETTE_SIZE);
	}

	public void setDockLocation(int location) {
		myPreferences.setValue(PALETTE_DOCK_LOCATION, location);
	}

	public void setPaletteState(int state) {
		myPreferences.setValue(PALETTE_STATE, state);
	}

	public void setPaletteWidth(int width) {
		myPreferences.setValue(PALETTE_SIZE, width);
	}

}
