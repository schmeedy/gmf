/******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.examples.runtime.emf;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.provider.EXTLibraryEditPlugin;
import org.eclipse.emf.examples.extlibrary.provider.EXTLibraryItemProviderAdapterFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

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
	private static AdapterFactory libraryFactory = new EXTLibraryItemProviderAdapterFactory();

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

		MSLMetaModelManager.register(EXTLibraryPackage.eINSTANCE,
			EXTLibraryEditPlugin.INSTANCE);
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