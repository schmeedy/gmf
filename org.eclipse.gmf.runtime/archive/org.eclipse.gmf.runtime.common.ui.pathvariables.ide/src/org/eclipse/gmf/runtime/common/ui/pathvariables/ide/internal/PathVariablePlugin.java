/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2004, 2005.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */

package org.eclipse.gmf.runtime.common.ui.pathvariables.ide.internal;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The main plugin class to be used in the desktop.
 */
public class PathVariablePlugin
	extends AbstractUIPlugin {

	//The shared instance.
	private static PathVariablePlugin plugin;

	//Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 */
	public PathVariablePlugin() {
		super();
		plugin = this;
		resourceBundle = null; // currently we have no need for a bundle...
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return the shared instance of <code>PathVariablePlugin</code>
	 */
	public static PathVariablePlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 * 
	 * @return an instance of <code>IWorkspace</code>
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 * 
	 * @param key the <code>String</code> key for a resource
	 * @return resource associated with the specified key, or 'key' if not found
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = PathVariablePlugin.getDefault()
			.getResourceBundle();
		try {
			return (bundle != null ? bundle.getString(key)
				: key);
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns a new PathVariableManager for the specified preference store.
	 * 
	 * @param preferences the preference store
	 * @return the <code>IPathVariableManager</code> for specified <code>Preferences</code>
	 */
	public static IPathVariableManager getPathVariableManager(
			Preferences preferences) {
		return new PathVariableManager(preferences);
	}

	/**
	 * Returns the plugin's resource bundle.
	 * 
	 * @return <code>ResourceBundle</code> for this plugin
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}