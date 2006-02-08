/******************************************************************************
 * Copyright (c) 2002, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core.internal.resources;


/**
 * This class manages MSL pathmaps.
 * 
 * @author rafikj
 * 
 * @deprecated Use the {@link PathmapManager} class, instead.
 */
public class MSLPathmap {
	/**
	 * Constructor.
	 */
	private MSLPathmap() {
		super();
	}

	/**
	 * Set the value of a pathmap variable.
	 * 
	 * @param var the path map variable name
	 * @param val the path map variable value (a URI)
	 */
	public static void setPathVariable(String var, String val) {

		PathmapManager.setPathVariable(var, val);
	}

	/**
	 * Remove a pathmap variable.
	 */
	public static void removePathVariable(String var) {

		PathmapManager.removePathVariable(var);
	}
}