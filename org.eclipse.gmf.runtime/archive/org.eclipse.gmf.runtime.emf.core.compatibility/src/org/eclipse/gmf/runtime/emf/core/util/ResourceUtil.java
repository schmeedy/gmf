/******************************************************************************
 * Copyright (c) 2002, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MObjectType;
import org.eclipse.gmf.runtime.emf.core.internal.resources.MResource;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLAdapterFactoryManager;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLUtil;

/**
 * This class contains a set of <code>Resource</code> related utility methods.
 * 
 * @author rafikj
 * 
 * @deprecated See individual methods for replacements.
 */
public class ResourceUtil {

	/**
	 * Gets the MSL framework's resource set. The resource set is the root
	 * object from which we can get the resources from which we can get the
	 * resource objects.
	 * 
	 * @return The resource set.
	 * 
	 * @deprecated The singleton editing domain is deprecated.  Obtain the
	 *    resource set of your editing domain.
	 */
	public static ResourceSet getResourceSet() {
		return MEditingDomain.INSTANCE.getResourceSet();
	}

	/**
	 * Gets the editing domain.
	 * 
	 * @return The editing domain.
	 * 
	 * @deprecated The singleton editing domain is deprecated.  Use your
	 *    editing domain.
	 */
	public static EditingDomain getEditingDomain() {
		return MEditingDomain.INSTANCE;
	}

	/**
	 * Gets the adapter factory.
	 * 
	 * @return The adapter factory.
	 * 
	 * @deprecated The <code>MSLAdapterFactoryManager</code> is no longer
	 *    required, as by default the
	 *    {@link org.eclipse.emf.transaction.TransactionalEditingDomain} composes
	 *    the adapters registered on the
	 *    <code>org.eclipse.emf.edit.itemProviderAdapterFactories</code>
	 *    extension point.
	 */
	public static AdapterFactory getAdapterFactory() {
		return MSLAdapterFactoryManager.getAdapterFactory();
	}
	
	/**
	 * Gets the path of the resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The file path of the resource.
	 * 
	 * @deprecated Obtain the file path from the resource's URI, if it is a
	 *    file URI or can be
	 *    {@link org.eclipse.emf.ecore.resource.URIConverter converted} to a
	 *    file URI.
	 */
	public static String getFilePath(Resource resource) {
		return MEditingDomain.INSTANCE.getResourceFileName(resource);
	}

	/**
	 * Sets the path of the resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @param path
	 *            The new file path.
	 * 
	 * @deprecated Set the resource's URI to a file URI.
	 */
	public static void setFilePath(Resource resource, String path) {
		MEditingDomain.INSTANCE.setResourceFileName(resource, path);
	}

	/**
	 * Finds a resource given its path.
	 * 
	 * @param path
	 *            The resource path.
	 * @return The found resource.
	 * 
	 * @deprecated Use the
	 *     {@link org.eclipse.emf.ecore.resource.ResourceSet#getResource(org.eclipse.emf.common.util.URI, boolean)}
	 *     API, instead.
	 */
	public static Resource findResource(String path) {
		return MEditingDomain.INSTANCE.findResource(path);
	}

	/**
	 * Sets the path of the resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @param path
	 *            The new file path.
	 * @param options
	 *            The file path options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 *            
	 * @deprecated Just set the resource's URI.
	 */
	public static void setFilePath(Resource resource, String path, int options) {
		MEditingDomain.INSTANCE.setResourceFileName(resource, path, options);
	}

	/**
	 * Finds a resource given its path.
	 * 
	 * @param path
	 *            The resource path.
	 * @param options
	 *            The file path options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * @return The found resource.
	 * 
	 * @deprecated Use the
	 *     {@link org.eclipse.emf.ecore.resource.ResourceSet#getResource(org.eclipse.emf.common.util.URI, boolean)}
	 *     API, instead.
	 */
	public static Resource findResource(String path, int options) {
		return MEditingDomain.INSTANCE.findResource(path, options);
	}

	/**
	 * Gets the first root object in the resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The first object.
	 * 
	 * @deprecated Just take the first element of the resource's
	 *    {@link org.eclipse.emf.ecore.resource.Resource#getContents() contents}
	 *    list.
	 */
	public static EObject getFirstRoot(Resource resource) {

		if (resource.isLoaded()) {

			List contents = resource.getContents();

			if (!contents.isEmpty())
				return (EObject) contents.get(0);
		}

		return null;
	}

	/**
	 * Creates a resource and object of type rootEClass that becomes the root of
	 * the resource.
	 * 
	 * @param path
	 *            Optional path to be assigned to the resource.
	 * @param rootEClass
	 *            The root object's type (optional)
	 * @param options
	 *            The create options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * @return The new resource.
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.ecore.resource.ResourceSet#createResource(org.eclipse.emf.common.util.URI)}
	 *    API to create a new resource and add an element to its
	 *    {@link org.eclipse.emf.ecore.resource.Resource#getContents() contents}
	 *    list.
	 */
	public static Resource create(String path, EClass rootEClass, int options) {
		return MEditingDomain.INSTANCE
			.createResource(path, rootEClass, options);
	}

	/**
	 * Loads a resource from a given file.
	 * 
	 * @param path
	 *            The resource's file path.
	 * @param options
	 *            The load options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * @return The loaded resource.
	 * 
	 * @deprecated Create or get an existing resource in the resource set
	 *     and {@link org.eclipse.emf.ecore.resource.Resource#load(Map) load} it.
	 */
	public static Resource load(String path, int options) {
		return MEditingDomain.INSTANCE.loadResource(path, options);
	}
	
	/**
	 * Produces a resource for a given file path and with the provided
	 *  options. Unless specified with the appropriate option
	 *  {@link MResourceOption#URI}, the path is assumed to be a file path.
	 * 
	 * @param uri
	 *            Optional uri to be assigned to the resource.
	 * @param options
	 *            The create options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 *            
	 * @return The resource object.
	 * 
	 * @throws IllegalStateException if the resource is already loaded.
	 * 
	 * @deprecated Use the
	 *     {@link org.eclipse.emf.ecore.resource.ResourceSet#createResource(org.eclipse.emf.common.util.URI)}
	 *     API.
	 */
	public static Resource create(String path, int options) {
		return MEditingDomain.INSTANCE
			.createResource(path, options);
	}

	/**
	 * Loads a resource with a given file path from the given input stream using
	 * the given load options.
	 * 
	 * @param path
	 *            The resource's file path.
	 * @param options
	 *            The load options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * @param inputStream
	 *            The input stream from which to load the resource's contents.
	 * @return The loaded resource.
	 * 
	 * @deprecated Create or get an existing resource in the resource set
	 *     and {@link org.eclipse.emf.ecore.resource.Resource#load(InputStream, Map) load}
	 *     it.
	 */
	public static Resource load(String path, int options,
			InputStream inputStream) {
		return MEditingDomain.INSTANCE.loadResource(path, options, inputStream);
	}

	/**
	 * Loads an unloaded resource. It is the client's responsibility
	 *  to catch any exceptions that are thrown because of the loading
	 *  of the resource. Also, once a resource is loaded, it could have
	 *  errors (@link Resource#getErrors()}. The resource may be in the
	 *  loaded state after this method is called. The client must decide
	 *  whether to unload the resource in this case.
	 * 
	 * @param resource
	 *            The resource to load.
	 * @param options
	 *            The load options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * 
	 * @deprecated Simply {@link org.eclipse.emf.ecore.resource.Resource#load(Map) load}
	 *     the resource, specifying the required options.
	 */
	public static void load(Resource resource, int options) {
		MEditingDomain.INSTANCE.loadResource(resource, options);
	}

	/**
	 * Loads an unloaded resource from the given input stream using the given
	 * load options. It is the client's responsibility
	 *  to catch any exceptions that are thrown because of the loading
	 *  of the resource. Also, once a resource is loaded, it could have
	 *  errors (@link Resource#getErrors()}. The resource may be in the
	 *  loaded state after this method is called. The client must decide
	 *  whether to unload the resource in this case.
	 * 
	 * @param resource
	 *            The resource to load.
	 * @param options
	 *            The load options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * @param inputStream
	 *            The input stream from which to load the resource's contents.
	 * 
	 * @deprecated Simply
	 *     {@link org.eclipse.emf.ecore.resource.Resource#load(InputStream, Map) load}
	 *     the resource, specifying the required options.
	 */
	public static void load(Resource resource, int options,
			InputStream inputStream) {
		MEditingDomain.INSTANCE.loadResource(resource, options, inputStream);
	}

	/**
	 * Unloads a loaded resource.
	 * 
	 * @param resource
	 *            The resource to unload.
	 * @param options
	 *            The unload options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * @deprecated Use {@link Resource#unload()} instead.
	 */
	public static void unload(Resource resource, int options) {
		MEditingDomain.INSTANCE.unloadResource(resource, options);
	}

	/**
	 * Saves a resource.
	 * 
	 * @param resource
	 *            The resource to save.
	 * @param options
	 *            The save options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * 
	 * @deprecated Simply {@link org.eclipse.emf.ecore.resource.Resource#save(Map) save}
	 *     the resource, specifying the required options.
	 */
	public static void save(Resource resource, int options) {
		MEditingDomain.INSTANCE.saveResource(resource, options);
	}

	/**
	 * Sets the path of a resource then saves it.
	 * 
	 * @param resource
	 *            The resource to save.
	 * @param path
	 *            The new file path.
	 * @param options
	 *            The save options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * 
	 * @deprecated Set the resource's new URI and
	 *     {@link org.eclipse.emf.ecore.resource.Resource#save(Map) save} it,
	 *     specifying the required options.
	 */
	public static void saveAs(Resource resource, String path, int options) {
		MEditingDomain.INSTANCE.saveResourceAs(resource, path, options);
	}

	/**
	 * Creates a resource and object of type rootEClass that becomes the root of
	 * the resource.
	 * 
	 * @param path
	 *            Optional path to be assigned to the resource.
	 * @param eClass
	 *            The root object's type (optional)
	 * 
	 * @return The new resource.
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.ecore.resource.ResourceSet#createResource(org.eclipse.emf.common.util.URI)}
	 *    API to create a new resource and add an element to its
	 *    {@link org.eclipse.emf.ecore.resource.Resource#getContents() contents}
	 *    list.
	 */
	public static Resource create(String path, EClass eClass) {
		return MEditingDomain.INSTANCE.createResource(path, eClass);
	}
	
	/**
	 * Produces a resource for a given file path. If the
	 *  resource is already loaded then an exception is thrown. The
	 *  resource will be in the unloaded state when returned.
	 * 
	 * @param path
	 *            Optional file name to be assigned to the resource.
	 * 
	 * @return The resource object.
	 * 
	 * @throws IllegalStateException if the resource is already loaded.
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.ecore.resource.ResourceSet#createResource(org.eclipse.emf.common.util.URI)}
	 *    API to create a new resource.
	 */
	public static Resource create(String path) {
		return MEditingDomain.INSTANCE.createResource(path);
	}

	/**
	 * Loads a resource from a given file.
	 * 
	 * @param path
	 *            The resource's file path.
	 * @return The loaded resource.
	 * 
	 * @deprecated Create or get an existing resource from the resource set,
	 *     by the required file URI, and
	 *     {@link org.eclipse.emf.ecore.resource.Resource#load(Map) load} it.
	 */
	public static Resource load(String path) {
		return MEditingDomain.INSTANCE.loadResource(path);
	}

	/**
	 * Loads an unloaded resource. It is the client's responsibility
	 *  to catch any exceptions that are thrown because of the loading
	 *  of the resource. Also, once a resource is loaded, it could have
	 *  errors (@link Resource#getErrors()}. The resource may be in the
	 *  loaded state after this method is called. The client must decide
	 *  whether to unload the resource in this case.
	 * 
	 * @param resource
	 *            The resource to load.
	 * 
	 * @deprecated Simply {@link org.eclipse.emf.ecore.resource.Resource#load(Map) load}
	 *     the resource.
	 */
	public static void load(Resource resource) {
		MEditingDomain.INSTANCE.loadResource(resource);
	}

	/**
	 * Unloads a loaded resource.
	 * 
	 * @param resource
	 *            The resource to unload.
	 * @deprecated Use {@link Resource#unload()} instead.
	 */
	public static void unload(Resource resource) {
		MEditingDomain.INSTANCE.unloadResource(resource);
	}

	/**
	 * Saves a resource.
	 * 
	 * @param resource
	 *            The resource to save.
	 * 
	 * @deprecated Simply {@link org.eclipse.emf.ecore.resource.Resource#save(Map) save}
	 *     the resource.
	 */
	public static void save(Resource resource) {
		MEditingDomain.INSTANCE.saveResource(resource);
	}

	/**
	 * Sets the path of a resource then saves it.
	 * 
	 * @param resource
	 *            The resource to save.
	 * @param path
	 *            The new file path.
	 * 
	 * @deprecated Set the resource's URI to the required file URI, and
	 *     {@link org.eclipse.emf.ecore.resource.Resource#save(Map) save} it.
	 */
	public static void saveAs(Resource resource, String path) {
		MEditingDomain.INSTANCE.saveResourceAs(resource, path);
	}

	/**
	 * Finds an object in a resource given its ID.
	 * 
	 * @param resource
	 *            The resource.
	 * @param id
	 *            The ID.
	 * @return The found object.
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.ecore.resource.Resource#getEObject(String)}
	 *      API.
	 */
	public static EObject findObject(Resource resource, String id) {

		if (resource.isLoaded())
			return resource.getEObject(id);
		else
			return null;
	}

	/**
	 * Checks if a resource is modifiable.
	 * 
	 * @param resource
	 *            The resource.
	 * @return True if resource is modifiable.
	 * 
	 * @deprecated Get the {@link org.eclipse.emf.workspace.util.WorkspaceSynchronizer#getFile(Resource) file}
	 *     corresponding to the resource and query it for read-only state.
	 */
	public static boolean isModifiable(Resource resource) {

		if (resource instanceof MResource)
			return ((MResource) resource).getHelper().isModifiable(resource);

		return MSLUtil.isModifiable(resource);
	}

	/**
	 * Gets the resource's type which could be either: MODELING: the resource is
	 * a modeling resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The resource's type.
	 * 
	 * @deprecated The object type concept is obsolete;  see the {@link MObjectType}
	 *     documentation for details.
	 */
	public static MObjectType getType(Resource resource) {

		if (resource instanceof MResource)
			return ((MResource) resource).getHelper().getType();

		return MObjectType.MODELING;
	}

	/**
	 * Gets the imports of a resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The imports of the resource.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getImports(Resource)} API,
	 *    instead.
	 */
	public static Collection getImports(Resource resource) {
		return EMFCoreUtil.getImports(resource);
	}

	/**
	 * Gets the exports of a resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The exports of the resource.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getExports(Resource)} API,
	 *    instead.
	 */
	public static Collection getExports(Resource resource) {
		return EMFCoreUtil.getExports(resource);
	}

	/**
	 * Gets all imports of a resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The imports of the resource.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getTransitiveImports(Resource)} API,
	 *    instead.
	 */
	public static Collection getAllImports(Resource resource) {
		return EMFCoreUtil.getTransitiveImports(resource);
	}

	/**
	 * Gets all exports of a resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The exports of the resource.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getTransitiveExports(Resource)} API,
	 *    instead.
	 */
	public static Collection getAllExports(Resource resource) {
		return EMFCoreUtil.getTransitiveExports(resource);
	}

	/**
	 * Sets the value of a path variable.
	 * 
	 * @param var
	 *            The variable name.
	 * @param val
	 *            The variable value (a URI, not just a file path).
	 */
	public static void setPathVariable(String var, String val) {
		MEditingDomain.INSTANCE.setPathVariable(var, val);
	}

	/**
	 * Removes a path variable.
	 * 
	 * @param var
	 *            The variable name.
	 */
	public static void removePathVariable(String var) {
		MEditingDomain.INSTANCE.removePathVariable(var);
	}

	/**
	 * Gets the value of a path variable.
	 * 
	 * @param var
	 *            The variable name.
	 * @return The variable value (a URI, not just a file path), or
	 *    an empty string if the variable is undefined.
	 */
	public static String getPathVariable(String var) {
		return MEditingDomain.INSTANCE.getPathVariable(var);
	}

	/**
	 * Gets the path of the resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The file path of the resource.
	 * 
	 * @deprecated If the resource's URI is a file URI or can be
	 *     {@link org.eclipse.emf.ecore.resource.URIConverter converted} to
	 *     one, then it will provide a file path.
	 */
	public static String getFileString(Resource resource) {
		return getFilePath(resource);
	}

	/**
	 * Return the file in the workspace for a resource.
	 * 
	 * @param resource
	 *            the resource.
	 * @return the file in the workspace for a resource.
	 * 
	 * @deprecated Use the
	 *     {@link org.eclipse.emf.workspace.util.WorkspaceSynchronizer#getFile(Resource)}
	 *     API, instead.
	 */
	public static IFile getFile(Resource resource) {

		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		String fileString = getFilePath(resource);

		if (fileString != null)
			return workspaceRoot.getFileForLocation(new Path(fileString));

		return null;
	}

	/**
	 * Creates re-gui-ided copies of the resources provided in the map
	 * 
	 * @param resource2URI
	 *            a map with the resources to copy as keys and their new URIs as
	 *            values
	 * @throws IOException
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#copyResources(java.util.Map)
	 * 
	 * @deprecated Use the {@link org.eclipse.gmf.runtime.emf.core.clipboard.CopyingResourceSet}
	 *     and {@link org.eclipse.gmf.runtime.emf.core.clipboard.CopyingResource} API
	 *     to copy resources to new locations.
	 */
	public static void copyResources(Map resource2URI)
		throws IOException {
		MEditingDomain.INSTANCE.copyResources(resource2URI);
	}

	private ResourceUtil() {
		// private
	}
}