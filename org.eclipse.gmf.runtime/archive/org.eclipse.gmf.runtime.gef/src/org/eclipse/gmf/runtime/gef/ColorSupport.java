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

import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ColorSupport {
	public static final String DARK_BLUE = "darkBlue";
	public static final String BLUE = "blue";
	public static final String LIGHT_BLUE = "lightBlue";
	public static final String CYAN = "cyan";
	public static final String DARK_GREEN = "darkGreen";
	public static final String LIGHT_GREEN = "lightGreen";
	public static final String GREEN = "green";
	public static final String YELLOW = "yellow";
	public static final String ORANGE = "orange";
	public static final String RED = "red";
	public static final String BLACK = "black";
	public static final String DARK_GRAY = "darkGray";
	public static final String GRAY = "gray";
	public static final String LIGHT_GRAY = "lightGray";
	public static final String WHITE = "white";

	private ColorRegistryEx myRegistry;
	private static final Map ourColorConstants = new TreeMap();
	private static int ourConstantsUsers = 0;
	private final Color myDefaultColor;

	public ColorSupport() {
		this(ColorConstants.red);
	}

	public ColorSupport(Color defaultColor) {
		assert defaultColor != null;
		myDefaultColor = defaultColor;
	}

	public Color get(String colorKey) {
		if (colorKey == null) {
			assert false;
			return myDefaultColor;
		}
		String colorKeyLower = colorKey.toLowerCase();
		Color known = tryKnown(colorKeyLower);
		if (known != null) {
			return known;
		}
		RGB parsed = parse(colorKey);
		if (parsed == null) {
			return myDefaultColor;
		}
		getRegistry().put(colorKeyLower, parsed);
		return getRegistry().get(colorKeyLower);
	}

	public boolean isValid(String colorKey) {
		return colorKey != null && (tryKnown(colorKey.toLowerCase()) != null || parse(colorKey) != null);
	}

	public void dispose() {
		if (myRegistry != null) {
			myRegistry.dispose();
			myRegistry = null;
		}
		if (--ourConstantsUsers == 0) {
			ourColorConstants.clear();
		}
	}

	private Color tryKnown(String colorKeyLower) {
		Color rv = tryAsConstant(colorKeyLower);
		if (rv != null) {
			return rv;
		}
		return getRegistry().get(colorKeyLower);
	}

	private RGB parse(String colorKey) {
		if (colorKey.indexOf(',') == -1) {
			try {
				int value = Integer.parseInt(colorKey, 16);
				return new RGB((value >> 16) & 0xFF, (value >> 8) & 0xFF, value & 0xFF);
			} catch (NumberFormatException ex) {
				return null;
			}
		}
		StringTokenizer st = new StringTokenizer(colorKey, ",");
		if (st.countTokens() != 3) {
			return null;
		}
		try {
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			return new RGB(r, g, b);
		} catch (NumberFormatException ex) {
			return null;
		} catch (IllegalArgumentException ex) {
			return null;
		}
	}

	private Color tryAsConstant(String colorKey) {
		if (ourColorConstants.isEmpty()) {
			assert ourConstantsUsers == 0;
			ourConstantsUsers++;
			initConstants();
		}
		return (Color) ourColorConstants.get(colorKey);
	}

	private void initConstants() {
		ourColorConstants.put(WHITE, ColorConstants.white);
		ourColorConstants.put(LIGHT_GRAY.toLowerCase(), ColorConstants.lightGray);
		ourColorConstants.put(GRAY, ColorConstants.gray);
		ourColorConstants.put(DARK_GRAY.toLowerCase(), ColorConstants.darkGray);
		ourColorConstants.put(BLACK, ColorConstants.black);
		ourColorConstants.put(RED, ColorConstants.red);
		ourColorConstants.put(ORANGE, ColorConstants.orange);
		ourColorConstants.put(YELLOW, ColorConstants.yellow);
		ourColorConstants.put(GREEN, ColorConstants.green);
		ourColorConstants.put(LIGHT_GREEN.toLowerCase(), ColorConstants.lightGreen);
		ourColorConstants.put(DARK_GREEN.toLowerCase(), ColorConstants.darkGreen);
		ourColorConstants.put(CYAN, ColorConstants.cyan);
		ourColorConstants.put(LIGHT_BLUE.toLowerCase(), ColorConstants.lightBlue);
		ourColorConstants.put(BLUE, ColorConstants.blue);
		ourColorConstants.put(DARK_BLUE.toLowerCase(), ColorConstants.darkBlue);
	}

	private ColorRegistry getRegistry() {
		if (myRegistry == null) {
			myRegistry = new ColorRegistryEx();
		}
		return myRegistry;
	}

	private static final class ColorRegistryEx extends ColorRegistry {
		public ColorRegistryEx() {
			super(Display.getDefault(), true);
		}
		public void dispose() {
			super.clearCaches();
		}
	}
}
