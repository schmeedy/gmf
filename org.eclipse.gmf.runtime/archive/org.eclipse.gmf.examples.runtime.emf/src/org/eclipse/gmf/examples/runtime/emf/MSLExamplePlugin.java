/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.examples.runtime.emf;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import org.eclipse.emf.examples.library.RMPLibraryPackage;
import org.eclipse.emf.examples.library.provider.RMPLibraryEditPlugin;
import org.eclipse.emf.examples.library.provider.RMPLibraryItemProviderAdapterFactory;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLAdapterFactoryManager;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLMetaModelManager;

/**
 * The main plugin class to be used in the desktop.
 */
public class MSLExamplePlugin
	extends AbstractUIPlugin {

	//The shared instance.
	private static MSLExamplePlugin plugin;

	//Resource bundle.
	private ResourceBundle resourceBundle;

	//Library item provider factory
	private static AdapterFactory libraryFactory = new RMPLibraryItemProviderAdapterFactory();

	/**
	 * The constructor.
	 */
	public MSLExamplePlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle
				.getBundle("org.eclipse.gmf.examples.runtime.emf.MSLExamplePluginResources"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context)
		throws Exception {
		super.start(context);

		MSLMetaModelManager.register(RMPLibraryPackage.eINSTANCE,
			RMPLibraryEditPlugin.INSTANCE);
		MSLAdapterFactoryManager.register(libraryFactory);
		MSLAdapterFactoryManager.register(new ResourceItemProviderAdapterFactory());
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context)
		throws Exception {
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 */
	public static MSLExamplePlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = MSLExamplePlugin.getDefault()
			.getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key)
				: key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}