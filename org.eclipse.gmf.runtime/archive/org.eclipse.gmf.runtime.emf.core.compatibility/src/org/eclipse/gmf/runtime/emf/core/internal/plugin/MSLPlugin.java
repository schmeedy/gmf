/******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core.internal.plugin;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLAdapterFactoryManager;
import org.osgi.framework.BundleContext;

/**
 * MSL plugin class.
 * 
 * @author rafikj
 */
public class MSLPlugin
	extends Plugin {

	private static MSLPlugin plugin;
		
	/**
	 * Constructor.
	 */
	public MSLPlugin() {
		super();
		plugin = this;
	}

	/**
	 * Get one instance of MSL plugin.
	 */
	public static MSLPlugin getDefault() {
		return plugin;
	}

	/**
	 * Get plugin ID.
	 */
	public static String getPluginId() {
		return getDefault().getBundle().getSymbolicName();
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		MSLAdapterFactoryManager.init();

		// This event listener must be registered _after_ all of the rest of
		//  the MSL stuff is initialized. Otherwise, strange problems occur when
		//  tracing is turned on.
		if (Trace.shouldTrace(MSLPlugin.getDefault(),
			MSLDebugOptions.EVENTS)) {
			new TraceEventListener();
		}
        
        // Use of MEditingDomain.INSTANCE does not cause the MSLEditingDomain to
        // be registered, which means listeners are not attached.
        //
        // By asking for the editing domain from the registry, I will cause it
        // to be added to the registry which will then go and add listeners from
        // the org.eclipse.emf.transaction.listeners extension point.
        TransactionalEditingDomain.Registry.INSTANCE
            .getEditingDomain("org.eclipse.gmf.runtime.emf.core.compatibility.MSLEditingDomain"); //$NON-NLS-1$
    
	}
}