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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.provider.EXTLibraryEditPlugin;
import org.eclipse.emf.examples.extlibrary.provider.EXTLibraryItemProviderAdapterFactory;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLAdapterFactoryManager;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLMetaModelManager;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class MSLExamplePlugin
	extends AbstractUIPlugin {

	//The shared instance.
	private static MSLExamplePlugin plugin;

	//Library item provider factory
	private static AdapterFactory libraryFactory = new EXTLibraryItemProviderAdapterFactory();

	/**
	 * The constructor.
	 */
	public MSLExamplePlugin() {
		super();
		plugin = this;
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
}