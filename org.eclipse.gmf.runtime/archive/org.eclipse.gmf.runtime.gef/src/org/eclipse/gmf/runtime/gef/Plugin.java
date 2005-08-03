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

import org.osgi.framework.BundleContext;

public class Plugin extends org.eclipse.core.runtime.Plugin {
	private static Plugin ourInstance;
	
	public Plugin() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		ourInstance = this;
	}

	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		ourInstance = null;
	}

	public static Plugin getDefault() {
		return ourInstance;
	}

	public static String getPluginID() {
		return getDefault().getBundle().getSymbolicName();
	}
}
