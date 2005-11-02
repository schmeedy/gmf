/******************************************************************************
 * Copyright (c) 2002, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.common.core.provider.internal;

import org.eclipse.gmf.runtime.common.core.l10n.AbstractResourceManager;
import org.eclipse.gmf.runtime.common.core.plugin.XToolsPlugin;

/**
 * The common core provider plug-in.
 * 
 * @author khussey
 * @deprecated This entire plug-in has been scheduled for removal.
 * Please see Bugzilla 112797.
 */
public class CommonCoreProviderPlugin extends XToolsPlugin {

    /**
     * This plug-in's shared instance.
     */
    private static CommonCoreProviderPlugin plugin;

    /**
     * Creates a new plug-in runtime object.
     */
    public CommonCoreProviderPlugin() {
        super();

        plugin = this;
    }

    /**
     * Retrieves this plug-in's shared instance.
     * 
     * @return This plug-in's shared instance.
     */
    public static CommonCoreProviderPlugin getDefault() {
        return plugin;
    }

    /**
     * Retrieves the unique identifier of this plug-in.
     * 
     * @return A non-empty string which is unique within the plug-in registry.
     */
    public static String getPluginId() {
        return getDefault().getBundle().getSymbolicName();
    }

    /**
     * Retrieves the resource manager for this plug-in.
     * 
     * @return The resource manager for this plug-in.
     * 
     * @see org.eclipse.gmf.runtime.common.core.plugin.XToolsPlugin#getResourceManager()
     */
    public AbstractResourceManager getResourceManager() {
        return null;
    }

}
