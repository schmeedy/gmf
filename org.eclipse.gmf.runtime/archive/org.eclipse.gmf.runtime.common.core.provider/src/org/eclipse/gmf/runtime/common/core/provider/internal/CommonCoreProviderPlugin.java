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

import org.eclipse.core.runtime.Plugin;

/**
 * The common core provider plug-in.
 * 
 * @author khussey
 * @deprecated This entire plug-in has been scheduled for removal.
 * This plug-in was deprecated November 2, 2005 for https://bugs.eclipse.org/bugs/show_bug.cgi?id=112797
 * This plug-in will be removed December 19, 2005 before declaration of the M4 milestone.
 */
public class CommonCoreProviderPlugin extends Plugin {

    /**
     * This plug-in's shared instance.
     */
    private static CommonCoreProviderPlugin INSTANCE;

    /**
     * Creates a new plug-in runtime object.
     */
    public CommonCoreProviderPlugin() {
        super();

        INSTANCE = this;
    }

    /**
     * Retrieves this plug-in's shared instance.
     * 
     * @return This plug-in's shared instance.
     */
    public static CommonCoreProviderPlugin getDefault() {
        return INSTANCE;
    }

    /**
     * Retrieves the unique identifier of this plug-in.
     * 
     * @return A non-empty string which is unique within the plug-in registry.
     */
    public static String getPluginId() {
        return getDefault().getBundle().getSymbolicName();
    }

}
