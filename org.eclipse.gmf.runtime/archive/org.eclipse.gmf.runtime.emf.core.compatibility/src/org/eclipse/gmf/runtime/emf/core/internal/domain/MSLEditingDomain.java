/******************************************************************************
 * Copyright (c) 2004-2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core.internal.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.OverrideableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
import org.eclipse.emf.transaction.impl.TransactionValidator;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.emf.core.EventTypes;
import org.eclipse.gmf.runtime.emf.core.OperationListener;
import org.eclipse.gmf.runtime.emf.core.ResourceSetModifyOperation;
import org.eclipse.gmf.runtime.emf.core.ResourceSetOperation;
import org.eclipse.gmf.runtime.emf.core.ResourceSetReadOperation;
import org.eclipse.gmf.runtime.emf.core.clipboard.CopyingResource;
import org.eclipse.gmf.runtime.emf.core.clipboard.CopyingResourceSet;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MResourceOption;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLRuntimeException;
import org.eclipse.gmf.runtime.emf.core.internal.exceptions.MSLActionProtocolException;
import org.eclipse.gmf.runtime.emf.core.internal.index.MSLCrossReferenceAdapter;
import org.eclipse.gmf.runtime.emf.core.internal.l10n.MSLCoreMessages;
import org.eclipse.gmf.runtime.emf.core.internal.notifications.MSLEventBroker;
import org.eclipse.gmf.runtime.emf.core.internal.notifications.MSLObjectListener;
import org.eclipse.gmf.runtime.emf.core.internal.notifications.MSLResourceListener;
import org.eclipse.gmf.runtime.emf.core.internal.notifications.MSLResourceSetListener;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLDebugOptions;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLPlugin;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLStatusCodes;
import org.eclipse.gmf.runtime.emf.core.internal.resources.MResource;
import org.eclipse.gmf.runtime.emf.core.internal.resources.MSLExtendedMetaData;
import org.eclipse.gmf.runtime.emf.core.internal.resources.PathmapManager;
import org.eclipse.gmf.runtime.emf.core.internal.resources.GMFResource;
import org.eclipse.gmf.runtime.emf.core.internal.util.ConstraintStatusAdapter;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLAdapterFactoryManager;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLComposedAdapterFactory;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLConstants;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLUtil;

import com.ibm.icu.util.StringTokenizer;

/**
 * This is the implementation of the MSL editing domain interface. The
 * implementation wraps and delegates to an EMF-TX {@link TransactionalEditingDomain}.
 * 
 * @author rafikj
 * 
 * @deprecated The MSL's editing domain implementation is replaced by the
 *     EMF Transaction API's {@link org.eclipse.emf.transaction.TransactionalEditingDomain}.
 *     See the deprecation documentation of the {@link MEditingDomain} class
 *     for details.
 */
public class MSLEditingDomain
	extends MEditingDomain implements InternalTransactionalEditingDomain {

	private InternalTransactionalEditingDomain delegate = null;
	
	private MSLEventBroker eventBroker = null;

	private MSLObjectListener objectListener = null;

	private MSLResourceListener resourceListener = null;

	private MSLResourceSetListener resourceSetListener = null;

	private MSLOperationListenerBroker operationListenerBroker = null;

	private PathmapManager pathmap = null;

	private MSLCrossReferenceAdapter crossReferenceAdapter = null;

	private Map defaultTransactionOptions = new java.util.HashMap();

	private static Map resourceSets = new WeakHashMap();
	
	private ThreadLocal undoInterval = new ThreadLocal();

	private boolean isUndoing;
	private boolean isRedoing;
	
	private Map resourceSetListeners = new java.util.HashMap();
	
	/**
	 * The extended metadata for MSL resources.
	 */
	private ExtendedMetaData extendedMetaData = null;

	/**
	 * Stack of {@link org.eclipse.gmf.runtime.emf.core.internal.MRunnable}s that
	 * are running cancellable write operations.
	 */
	private List writers = new ArrayList();

	/**
	 * Constructor.
	 */
	public MSLEditingDomain() {

		this((ResourceSet) null);
	}

	/**
	 * Constructor.
	 */
	public MSLEditingDomain(ResourceSet rset) {

		MSLComposedAdapterFactory composedFactory = MSLAdapterFactoryManager
			.getAdapterFactory();
		
		eventBroker = new MSLEventBroker(this);

		objectListener = new MSLObjectListener(this);

		resourceListener = new MSLResourceListener(this);

		resourceSetListener = new MSLResourceSetListener(this);

		crossReferenceAdapter = new MSLCrossReferenceAdapter(this);

		extendedMetaData = new MSLExtendedMetaData();

		if (rset == null) {
			rset = new MSLResourceSet();
		}
		
		// create the TXEditingDomain delegate
		delegate = new MSLTXEditingDomain(
				composedFactory,
				new MSLCommandStack(
						OperationHistoryFactory.getOperationHistory()),
				rset) {
			
			protected TransactionChangeRecorder createChangeRecorder(ResourceSet rs) {
				return new MSLChangeRecorder(MSLEditingDomain.this, this, rs);
			}};

		pathmap = new PathmapManager();
			
		attachTo(rset);
	}

	private void attachTo(ResourceSet rset) {
		// for each resource that is loaded, ensure that the new domain
		//   know that it has finished loading
		for (Iterator iter = rset.getResources().iterator(); iter.hasNext();) {
			Resource next = (Resource) iter.next();
			
			if (next.isLoaded()) {
				getResouceListener().markResourceFinishedLoading(next);
			}
		}
		
		MSLChangeRecorder rec = (MSLChangeRecorder) delegate.getChangeRecorder();
		rec.addResourceSet(rset);

		rset.eAdapters().add(crossReferenceAdapter);
		rset.eAdapters().add(pathmap);
		
		delegate.addResourceSetListener(eventBroker);

		for (Iterator i = MSLAdapterFactoryManager.getAdapterFactory().getFactories().iterator();
				i.hasNext();) {

			AdapterFactory next = (AdapterFactory) i.next();

			Collection factories = rset.getAdapterFactories();

			factories.remove(next);
			factories.add(next);
		}
		
		resourceSets.put(rset, new WeakReference(this));
		((TransactionalEditingDomainImpl.FactoryImpl)
				TransactionalEditingDomain.Factory.INSTANCE).mapResourceSet(this);
	}
	
	private void detachFrom(ResourceSet rset) {
		resourceSets.remove(rset);

		MSLChangeRecorder rec = (MSLChangeRecorder) delegate.getChangeRecorder();
		rec.removeResourceSet(rset);
		
		rset.eAdapters().remove(pathmap);
		rset.eAdapters().remove(crossReferenceAdapter);
		
		// forcibly detach the change recorder for all elements in the rset
		rset.eAdapters().remove(rec);
		for (Iterator iter = rset.getAllContents(); iter.hasNext();) {
			Collection adapters = ((Notifier) iter.next()).eAdapters();
			adapters.remove(rec);
			adapters.remove(crossReferenceAdapter);
		}
	}

	/**
	 * Returns the event broker.
	 */
	public MSLEventBroker getEventBroker() {
		return eventBroker;
	}

	/**
	 * Returns the EObject listener.
	 */
	public MSLObjectListener getObjectListener() {
		return objectListener;
	}

	/**
	 * Returns the Resource listener.
	 */
	public MSLResourceListener getResouceListener() {
		return resourceListener;
	}

	/**
	 * Returns the ResourceSet listener.
	 */
	public MSLResourceSetListener getResouceSetListener() {
		return resourceSetListener;
	}

	/**
	 * Returns the path map.
	 */
	public PathmapManager getPathmap() {
		return pathmap;
	}

	/**
	 * Returns the cross-referencing adapter.
	 */
	public MSLCrossReferenceAdapter getCrossReferenceAdapter() {
		return crossReferenceAdapter;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#create(org.eclipse.emf.ecore.EClass)
	 */
	public EObject create(EClass eClass) {
		return MSLUtil.create(this, eClass, true);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#getResourceFileName(org.eclipse.emf.ecore.resource.Resource)
	 */
	public String getResourceFileName(Resource resource) {
		return MSLUtil.getFilePath(resource);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#setResourceFileName(org.eclipse.emf.ecore.resource.Resource,
	 *      java.lang.String)
	 */
	public void setResourceFileName(Resource resource, String fileNameURI) {
		setResourceFileName(resource, fileNameURI, 0);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#setResourceFileName(org.eclipse.emf.ecore.resource.Resource,
	 *      java.lang.String, int)
	 */
	public void setResourceFileName(Resource resource, String fileNameURI,
			int options) {

		URI uri = null;

		if ((options & MResourceOption.URI) != 0)
			uri = URI.createURI(fileNameURI);

		else
			uri = URI.createFileURI(new File(fileNameURI).getAbsolutePath());

		resource.setURI(uri);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#findResource(java.lang.String)
	 */
	public Resource findResource(String fileNameURI) {
		return findResource(fileNameURI, 0);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#findResource(java.lang.String,
	 *      int)
	 */
	public Resource findResource(String fileNameURI, int options) {

		URI uri = null;

		if ((options & MResourceOption.URI) != 0)
			uri = URI.createURI(fileNameURI);

		else
			uri = URI.createFileURI(new File(fileNameURI).getAbsolutePath());

		ResourceSet resourceSet = getResourceSet();

		Resource resource = resourceSet.getResource(uri, false);

		if (resource == null) {
			URI convertedURI = convertURI(uri);

			if (!convertedURI.equals(uri))
				resource = resourceSet.getResource(convertedURI, false);
		}

		return resource;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#convertURI(org.eclipse.emf.common.util.URI)
	 */
	public URI convertURI(URI uri) {

		URI resolvedURI = uri;

		if (MSLConstants.PLATFORM_SCHEME.equals(resolvedURI.scheme())) {

			String filePath = MSLUtil
				.getFilePath(getResourceSet(), resolvedURI);

			if ((filePath != null) && (filePath.length() > 0))
				resolvedURI = URI.createFileURI(filePath);
		}

		if ((resolvedURI != null) && (resolvedURI.isFile())) {

			ResourceSet resourceSet = getResourceSet();

			String fileName = resolvedURI.lastSegment();

			// attempt to convert the URI to a path map URI.
			if (fileName != null) {

				URI prefix = resolvedURI.trimSegments(1);

				// find a matching pathmap.
				URI foundKeyURI = null;
				URI foundValURI = null;
				int minDiff = Integer.MAX_VALUE;

				Iterator i = resourceSet.getURIConverter().getURIMap()
					.entrySet().iterator();

				while (i.hasNext()) {

					Map.Entry entry = (Map.Entry) i.next();

					if (entry != null) {

						URI keyURI = (URI) entry.getKey();
						URI valURI = (URI) entry.getValue();

						if ((keyURI.isHierarchical())
							&& (MSLConstants.PATH_MAP_SCHEME.equals(keyURI
								.scheme())) && (valURI.isFile())) {

							int diff = computeDiff(valURI, prefix);

							if ((diff >= 0) && (diff < minDiff)) {

								minDiff = diff;

								foundKeyURI = keyURI;
								foundValURI = valURI;

								if (minDiff == 0)
									break;
							}
						}
					}
				}

				if ((foundKeyURI != null) && (foundValURI != null))
					return resolvedURI.replacePrefix(foundValURI, foundKeyURI);
			}

			// attempt to convert URI to a platform URI.
			URI platformURI = getPlatformURI(uri);

			if (platformURI != null)
				return platformURI;
		}

		return uri;
	}

	/**
	 * @see org.eclipse.emf.edit.domain.EditingDomain#createResource(java.lang.String)
	 */
	public Resource createResource(String fileNameURI) {
		return createResource(fileNameURI, null, 0);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#createResource(java.lang.String,
	 *      int)
	 */
	public Resource createResource(String fileNameURI, int options) {
		return createResource(fileNameURI, null, options);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#createResource(java.lang.String,
	 *      org.eclipse.emf.ecore.EClass)
	 */
	public Resource createResource(String fileNameURI, EClass rootEClass) {
		return createResource(fileNameURI, rootEClass, 0);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#createResource(java.lang.String,
	 *      org.eclipse.emf.ecore.EClass, int)
	 */
	public Resource createResource(String fileNameURI, EClass rootEClass,
			int options) {

		URI uri = null;
		
		boolean wasCreated = false;

		if ((fileNameURI == null)
			|| (fileNameURI.equals(MSLConstants.EMPTY_STRING)))
			uri = URI.createFileURI(new File(EcoreUtil.generateUUID()
				+ MSLConstants.INVALID_PATH).getPath());

		else if (fileNameURI.equals(MSLConstants.EPHEMERAL_INVALID_PATH))
			uri = URI.createFileURI(new File(EcoreUtil.generateUUID()
				+ MSLConstants.EPHEMERAL_INVALID_PATH).getPath());

		else if ((options & MResourceOption.URI) != 0)
			uri = URI.createURI(fileNameURI);

		else
			uri = URI.createFileURI(new File(fileNameURI).getAbsolutePath());

		if (uri != null) {

			ResourceSet resourceSet = getResourceSet();

			URI convertedURI = convertURI(uri);

			Resource resource = resourceSet.getResource(uri, false);

			if ((resource == null) && (!convertedURI.equals(uri)))
				resource = resourceSet.getResource(convertedURI, false);

			if (resource == null) {
				resource = resourceSet.createResource(convertedURI);
				wasCreated = true;
			}

			else if (resource.isLoaded()) {

				RuntimeException e = new IllegalStateException(
					"resource already created and loaded"); //$NON-NLS-1$

				Trace.throwing(MSLPlugin.getDefault(),
					MSLDebugOptions.EXCEPTIONS_THROWING, getClass(),
					"createResource", e); //$NON-NLS-1$

				throw e;
			}

			if (resource != null) {

				if (rootEClass != null) {

					EObject root = null;

					if (resource instanceof MResource)
						((MResource) resource).getHelper().create(this,
							rootEClass);
					else
						root = MSLUtil.create(this, rootEClass, false);

					if (root != null) {
						final EObject finalRoot = root;
						final Resource finalResource = resource;
						runAsUnchecked(new MRunnable() {
							public Object run() {
								finalResource.getContents().add(finalRoot);
								return finalRoot;
							}});
					}
					
					MSLUtil.postProcessResource(resource);
				}

				if (wasCreated)
					sendNotification(resource, EventTypes.CREATE);

				return resource;
			}
		}

		return null;
	}

	/**
	 * @see org.eclipse.emf.edit.domain.EditingDomain#loadResource(java.lang.String)
	 * @deprecated as the inherited specification is
	 */
	public Resource loadResource(String fileNameURI) {
		return loadResource(fileNameURI, 0, null);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#loadResource(java.lang.String,
	 *      int)
	 * @deprecated as the inherited specification is
	 */
	public Resource loadResource(String fileNameURI, int options) {
		return loadResource(fileNameURI, options, null);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#loadResource(java.lang.String,
	 *      int, java.io.InputStream)
	 * @deprecated as the inherited specification is
	 */
	public Resource loadResource(String fileNameURI, int options,
			InputStream inputStream) {

		URI uri = null;

		if (fileNameURI != null) {

			if ((options & MResourceOption.URI) != 0)
				uri = URI.createURI(fileNameURI);

			else
				uri = URI
					.createFileURI(new File(fileNameURI).getAbsolutePath());
		} else
			uri = null;

		if (uri != null) {

			ResourceSet resourceSet = getResourceSet();

			URI convertedURI = convertURI(uri);

			Resource resource = resourceSet.getResource(uri, false);

			if ((resource == null) && (!convertedURI.equals(uri)))
				resource = resourceSet.getResource(convertedURI, false);

			if (resource == null)
				resource = resourceSet.createResource(convertedURI);

			if (resource != null) {
				
				try {
					loadResource(resource, options, inputStream);
				} catch (RuntimeException e) {
					// If an exception is thrown then we will try to automatically unload the resource.
					if (resource.isLoaded()) {
						resource.unload();
					}
					throw e;
				}
				
				return resource;
			}
		}

		return null;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#loadResource(org.eclipse.emf.ecore.resource.Resource)
	 */
	public void loadResource(Resource resource) {
		loadResource(resource, 0, null);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#loadResource(org.eclipse.emf.ecore.resource.Resource,
	 *      int)
	 */
	public void loadResource(Resource resource, int options) {
		loadResource(resource, options, null);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#loadResource(org.eclipse.emf.ecore.resource.Resource,
	 *      int, java.io.InputStream)
	 */
	public void loadResource(Resource resource, int options,
			InputStream inputStream) {
		try {
			Map loadOptions = null;

			if (resource instanceof XMLResource) {

				Map defaultLoadOptions = ((XMLResource) resource)
					.getDefaultLoadOptions();

				if (defaultLoadOptions != null)
					loadOptions = new HashMap(defaultLoadOptions);
			}

			if (loadOptions == null)
				loadOptions = new HashMap();

			if ((options & MResourceOption.COMPATIBILITY_MODE) != 0) {

				loadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE,
					Boolean.TRUE);

				loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA,
					extendedMetaData);
			} else {
				loadOptions.put(GMFResource.OPTION_ABORT_ON_ERROR,
					Boolean.TRUE);
			}
			
			if (null == inputStream)
				resource.load(loadOptions);
			else
				resource.load(inputStream, loadOptions);

		} catch (Exception e) {
			RuntimeException newE = null;

			if (e instanceof MSLRuntimeException)
				newE = (MSLRuntimeException) e;
			else
				newE = new MSLRuntimeException(e);

			Trace.throwing(MSLPlugin.getDefault(),
				MSLDebugOptions.EXCEPTIONS_THROWING, getClass(),
				"loadResource", newE); //$NON-NLS-1$

			throw newE;
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#unloadResource(org.eclipse.emf.ecore.resource.Resource)
	 * @deprecated as the inherited specification is
	 */
	public void unloadResource(Resource resource) {
		unloadResource(resource, 0);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#unloadResource(org.eclipse.emf.ecore.resource.Resource,
	 *      int)
	 * @deprecated as the inherited specification is
	 */
	public void unloadResource(Resource resource, int options) {

		try {

			resource.unload();

		} catch (Exception e) {
			RuntimeException newE = null;

			if (e instanceof MSLRuntimeException)
				newE = (MSLRuntimeException) e;
			else
				newE = new MSLRuntimeException(e);

			Trace.throwing(MSLPlugin.getDefault(),
				MSLDebugOptions.EXCEPTIONS_THROWING, getClass(),
				"unloadResource", newE); //$NON-NLS-1$

			throw newE;
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#saveResource(org.eclipse.emf.ecore.resource.Resource)
	 */
	public void saveResource(Resource resource) {
		saveResource(resource, 0);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#saveResource(org.eclipse.emf.ecore.resource.Resource,
	 *      int)
	 */
	public void saveResource(Resource resource, int options) {

		if (resource.isLoaded()) {

			try {

				Map saveOptions = null;

				if (resource instanceof XMLResource) {

					Map defaultSaveOptions = ((XMLResource) resource)
						.getDefaultSaveOptions();

					if (defaultSaveOptions != null)
						saveOptions = new HashMap(defaultSaveOptions);
				}

				if (saveOptions == null)
					saveOptions = new HashMap();

				if ((options & MResourceOption.COMPATIBILITY_MODE) != 0)
					saveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA,
						extendedMetaData);

				else if (resource instanceof XMLResource)
					((XMLResource) resource).getEObjectToExtensionMap().clear();

				if ((options & MResourceOption.USE_FILE_BUFFER) != 0) {

					saveOptions.put(XMLResource.OPTION_USE_FILE_BUFFER,
						Boolean.TRUE);
					saveOptions.put(XMLResource.OPTION_FLUSH_THRESHOLD,
						MSLConstants.OUTPUT_BUFFER_SIZE);
				}
				
				resource.save(saveOptions);

			} catch (Exception e) {

				RuntimeException newE = null;

				if (e instanceof MSLRuntimeException)
					newE = (MSLRuntimeException) e;
				else
					newE = new MSLRuntimeException(e);

				Trace.throwing(MSLPlugin.getDefault(),
					MSLDebugOptions.EXCEPTIONS_THROWING, getClass(),
					"saveResource", newE); //$NON-NLS-1$

				throw newE;
			}
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#saveResourceAs(org.eclipse.emf.ecore.resource.Resource,
	 *      java.lang.String)
	 */
	public void saveResourceAs(Resource resource, String fileNameURI) {
		saveResourceAs(resource, fileNameURI, 0);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#saveResourceAs(org.eclipse.emf.ecore.resource.Resource,
	 *      java.lang.String, int)
	 */
	public void saveResourceAs(Resource resource, String fileNameURI,
			int options) {

		setResourceFileName(resource, fileNameURI, options);
		saveResource(resource, options);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#openUndoInterval()
	 * @deprecated overrides a deprecated method from MEditingDomain
	 */
	public void openUndoInterval() {
		openUndoInterval("", "");  //$NON-NLS-1$//$NON-NLS-2$
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#openUndoInterval(java.lang.String)
	 * @deprecated overrides a deprecated method from MEditingDomain
	 */
	public void openUndoInterval(String label) {
		openUndoInterval(label, "");  //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#openUndoInterval(java.lang.String,
	 *      java.lang.String)
	 * @deprecated overrides a deprecated method from MEditingDomain
	 */
	public void openUndoInterval(String label, String description) {
		if (undoInterval.get() != null) {
			throw new MSLActionProtocolException("undo interval already open"); //$NON-NLS-1$
		}
		
		undoInterval.set(new MSLUndoInterval(this, label, description));
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#closeUndoInterval()
	 * @deprecated overrides a deprecated method from MEditingDomain
	 */
	public MUndoInterval closeUndoInterval() {
		MSLUndoInterval result = getUndoInterval();
		
		if (result == null) {
			throw new MSLActionProtocolException("no undo interval is open"); //$NON-NLS-1$
		}
		
		undoInterval.set(null);
		
		if (!result.isEmpty()) {
			sendNotification(result, EventTypes.CREATE);
		}
		
		return result;
	}
	
	private MSLUndoInterval getUndoInterval() {
		return (MSLUndoInterval) undoInterval.get();
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#canUndoCurrentInterval()
	 */
	public boolean canUndoCurrentInterval() {
		MSLUndoInterval interval = getUndoInterval();
		return (interval != null) && interval.canUndo();
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#canRedoCurrentInterval()
	 */
	public boolean canRedoCurrentInterval() {
		MSLUndoInterval interval = getUndoInterval();
		return (interval != null) && interval.canRedo();
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#setCanUndoCurrentInterval(boolean)
	 */
	public void setCanUndoCurrentInterval(boolean canUndo) {
		MSLUndoInterval interval = getUndoInterval();
		if (interval != null) {
			interval.setCanUndo(canUndo);
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#setCanRedoCurrentInterval(boolean)
	 */
	public void setCanRedoCurrentInterval(boolean canRedo) {
		MSLUndoInterval interval = getUndoInterval();
		if (interval != null) {
			interval.setCanRedo(canRedo);
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#runInUndoInterval(java.lang.Runnable)
	 */
	public MUndoInterval runInUndoInterval(Runnable runnable) {
		return runInUndoInterval(MSLConstants.EMPTY_STRING, runnable);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#runInUndoInterval(java.lang.String,
	 *      java.lang.Runnable)
	 */
	public MUndoInterval runInUndoInterval(String label, Runnable runnable) {
		return runInUndoInterval(label, MSLConstants.EMPTY_STRING, runnable);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#runInUndoInterval(java.lang.String,
	 *      java.lang.String, java.lang.Runnable)
	 */
	public MUndoInterval runInUndoInterval(String label, String description,
			Runnable runnable) {

		boolean open = isUndoIntervalOpen();

		if (!open)
			openUndoInterval(label, description);

		MUndoInterval result = null;

		try {

			runnable.run();

		} finally {
			if (!open)
				result = closeUndoInterval();
		}

		return result;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#isUndoIntervalOpen()
	 */
	public boolean isUndoIntervalOpen() {
		return getUndoInterval() != null;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#startRead()
	 * @deprecated overrides a deprecated method from MEditingDomain
	 */
	public void startRead() {
		try {
			MSLTransactionImpl tx = (MSLTransactionImpl) delegate.startTransaction(true, null);
			tx.setStandAlone(true);
		} catch (InterruptedException e) {
			throw new MSLRuntimeException(e);
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#startWrite()
	 * @deprecated overrides a deprecated method from MEditingDomain
	 */
	public void startWrite() {
		startWrite(defaultTransactionOptions);
	}

	/**
	 * Starts a write transaction with the specified options.
	 * 
	 * @deprecated Use {@link #runAsWrite(MRunnable, Map)}, instead
	 */
	private void startWrite(Map options) {
		try {
			MSLTransactionImpl tx = (MSLTransactionImpl) delegate.startTransaction(false, options);
			tx.setStandAlone(true);
		} catch (InterruptedException e) {
			throw new MSLRuntimeException(e);
		}
	}
	
	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#startUnchecked()
	 * @deprecated overrides a deprecated method from MEditingDomain
	 */
	public void startUnchecked() {
		Object previousUnprotected = setDefaultTransactionOption(
			Transaction.OPTION_UNPROTECTED,
			Boolean.TRUE);
		
		try {
			startWrite();
		} finally {
			setDefaultTransactionOption(
				Transaction.OPTION_UNPROTECTED,
				previousUnprotected);
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#complete()
	 * @deprecated overrides a deprecated method from MEditingDomain
	 */
	public void complete() {
		try {
			Transaction tx = delegate.getActiveTransaction();
			tx.commit();
			
			MSLUndoInterval undo = getUndoInterval();
			if ((undo != null) && (tx.getParent() == null)) {
				// only add the change description of root-level transactions
				undo.addChangeDescription(tx.getChangeDescription());
			}
			
			if (!tx.isReadOnly()
					&& !Boolean.TRUE.equals(tx.getOptions().get(
						Transaction.OPTION_UNPROTECTED))
					&& !Boolean.TRUE.equals(tx.getOptions().get(
						Transaction.OPTION_NO_NOTIFICATIONS))) {
				// MSL only manufactures CREATE events for protected writes that
				//    are not silent (the MSLEventBroker did not resume
				//    broadcasting events until after the action completed)
				sendNotification(tx, EventTypes.CREATE);
			}
		} catch (RollbackException e) {
			throw new MSLRuntimeException(e);
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#completeAndValidate()
	 * @deprecated overrides a deprecated method from MEditingDomain
	 */
	public IStatus completeAndValidate()
		throws MSLActionAbandonedException {
		try {
			Transaction tx = delegate.getActiveTransaction();
			tx.commit();
			
			MSLUndoInterval undo = getUndoInterval();
			if ((undo != null) && (tx.getParent() == null)) {
				// only add the change description of root-level transactions
				undo.addChangeDescription(tx.getChangeDescription());
			}
			
			if (!tx.isReadOnly()
					&& !Boolean.TRUE.equals(tx.getOptions().get(
						Transaction.OPTION_UNPROTECTED))
					&& !Boolean.TRUE.equals(tx.getOptions().get(
						Transaction.OPTION_NO_NOTIFICATIONS))) {
				// MSL only manufactures CREATE events for protected writes that
				//    are not silent (the MSLEventBroker did not resume
				//    broadcasting events until after the action completed)
				sendNotification(tx, EventTypes.CREATE);
			}
			
			return tx.getStatus();
		} catch (RollbackException e) {
			throw new MSLActionAbandonedException(
					ConstraintStatusAdapter.wrapStatus(e.getStatus()));
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#abandon()
	 * @deprecated overrides a deprecated method from MEditingDomain
	 */
	public void abandon() {
		delegate.getActiveTransaction().rollback();
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#runAsRead(org.eclipse.gmf.runtime.emf.core.internal.MRunnable)
	 */
	public Object runAsRead(MRunnable runnable) {
		startRead();
		
		Object result = null;

		try {
			result = runnable.run();
		} finally {
			complete();
		}
		
		return result;
	}

	/**
	 * Sets an option that subsequent {@link #runAsWrite(MRunnable)} invocations
	 * should apply by default to their write transactions.  Note that, because
	 * this is in effect a write-only map, the caller must restore the previous
	 * value (returned by this method) at the close of the context in which it
	 * needed the option to be set.
	 * 
	 * @param option the option to set
	 * @param value the required value of the option, or <code>null</code> to
	 *     unset it
	 * 
	 * @return the previous option, or <code>null</code> if it was not set
	 */
	public Object setDefaultTransactionOption(String option, Object value) {
		return (value == null)? defaultTransactionOptions.remove(option)
			: defaultTransactionOptions.put(option, value);
	}
	
	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#runAsWrite(org.eclipse.gmf.runtime.emf.core.internal.MRunnable)
	 */
	public Object runAsWrite(MRunnable runnable)
		throws MSLActionAbandonedException {

		return runAsWrite(runnable, defaultTransactionOptions);
	}

	/**
	 * Runs an bit of code within a write transaction using the specified
	 * options.
	 * 
	 * @param runnable a bit of code (presumably modifying a model)
	 * @param options the transaction options to apply
	 * 
	 * @return the result of the bit of code
	 * 
	 * @throws MSLActionAbandonedException if the options do not disable
	 *     validation and validation fails with an error or worse
	 */
	private Object runAsWrite(MRunnable runnable, Map options)
		throws MSLActionAbandonedException {

		startWrite(options);

		MRunnable top = peekWriter();
		pushWriter(runnable); // push the runnable onto the stack

		Object result = null;

		try {
			if ((top != null) && top.isAbandoned()) {
				// the previous runnable already canceled, so propagate
				//   up the stack
				runnable.abandon();
			}

			result = runnable.run();

		} finally {
			popWriter(); // pop the runnable

			if (runnable.isAbandoned() && (top != null)) {
				// propagate cancellation down the runnable stack
				top.abandon();
			}

			if (runnable.isAbandoned()) {
				abandon();
				runnable.setStatus(new Status(IStatus.CANCEL,
					MSLPlugin.getPluginId(),
					MSLStatusCodes.OPERATION_CANCELED_BY_USER,
					MSLCoreMessages.operation_canceled,
					null));
			} else {
				try {

					// complete and validate may throw, so that we
					// will
					// set the status in the catch block
					runnable.setStatus(completeAndValidate());

				} catch (MSLActionAbandonedException e) {

					runnable.setStatus(e.getStatus());

					throw e; // propagate the exception to the
							 // caller
				}
			}
		}
		return result;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#runAsUnchecked(org.eclipse.gmf.runtime.emf.core.internal.MRunnable)
	 */
	public Object runAsUnchecked(MRunnable runnable) {
		startUnchecked();

		Object result = null;

		try {
			result = runnable.run();
		} finally {
			complete();
		}
		
		return result;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#runSilent(org.eclipse.gmf.runtime.emf.core.internal.MRunnable)
	 */
	public Object runSilent(MRunnable runnable) {
		return eventBroker.runSilent(runnable);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#runWithNoSemProcs(org.eclipse.gmf.runtime.emf.core.internal.MRunnable)
	 */
	public Object runWithNoSemProcs(MRunnable runnable) {
		return eventBroker.runWithNoSemProcs(runnable);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#runUnvalidated(org.eclipse.gmf.runtime.emf.core.internal.MRunnable)
	 */
	public Object runUnvalidated(MRunnable runnable) {
		return eventBroker.runUnvalidated(runnable);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#runWithOptions(org.eclipse.gmf.runtime.emf.core.internal.MRunnable,
	 *      int)
	 */
	public Object runWithOptions(MRunnable runnable, int options) {
		return eventBroker.runWithOptions(runnable, options);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#canRead()
	 */
	public boolean canRead() {
		return canCurrentThreadRead();
	}
	
	protected boolean canCurrentThreadRead() {
		Transaction active = delegate.getActiveTransaction();
		
		return (active != null) && (active.getOwner() == Thread.currentThread());
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#canWrite()
	 */
	public boolean canWrite() {
		Transaction active = delegate.getActiveTransaction();
		
		return (active != null)
			&& (active.getOwner() == Thread.currentThread())
			&& (!active.isReadOnly() || Boolean.TRUE.equals(
						active.getOptions().get(
								Transaction.OPTION_UNPROTECTED)));
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#isWriteInProgress()
	 */
	public boolean isWriteInProgress() {
		Transaction active = delegate.getActiveTransaction();
		
		return (active != null) && !active.isReadOnly();
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#isUncheckedInProgress()
	 */
	public boolean isUncheckedInProgress() {
		Transaction active = delegate.getActiveTransaction();
		
		return (active != null) && Boolean.TRUE.equals(
					active.getOptions().get(Transaction.OPTION_UNPROTECTED));
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#isUndoNotification(org.eclipse.emf.common.notify.Notification)
	 */
	public boolean isUndoNotification(Notification notification) {
		return eventBroker.isUndoEvent(notification);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#isRedoNotification(org.eclipse.emf.common.notify.Notification)
	 */
	public boolean isRedoNotification(Notification notification) {
		return eventBroker.isRedoEvent(notification);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#sendNotification(org.eclipse.emf.common.notify.Notification)
	 */
	public void sendNotification(Notification notification) {
		// record the event in the current transaction (if any) otherwise
		//     dispatch immediately to resource-set listeners
		delegate.getChangeRecorder().notifyChanged(notification);
		
		// let the event broker forward to immediate semantic procedures
		eventBroker.addEvent(notification);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#sendNotification(java.lang.Object,
	 *      int)
	 */
	public void sendNotification(Object notifier, int eventType) {
		sendNotification(MSLEventBroker.createNotification(notifier, eventType));
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.EditingDomain#run(org.eclipse.gmf.runtime.emf.core.ResourceSetOperation,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(final ResourceSetOperation operation,
			final IProgressMonitor monitor)
		throws InvocationTargetException, InterruptedException {

		final Exception[] pendingException = new Exception[1];

		if (operation instanceof ResourceSetModifyOperation) {
			try {
				run((ResourceSetModifyOperation) operation, monitor);
			} catch (Exception e) {
				pendingException[0] = e;
			}
		} else if (operation instanceof ResourceSetReadOperation) {
			try {
				run((ResourceSetReadOperation) operation, monitor);
			} catch (Exception e) {
				pendingException[0] = e;
			}
		} else {

			RuntimeException e = new IllegalArgumentException(
				"ResourceSetModifyOperation and ResourceSetReadOperation are the only supported kind of operations"); //$NON-NLS-1$

			Trace.throwing(MSLPlugin.getDefault(),
				MSLDebugOptions.EXCEPTIONS_THROWING, getClass(), "run", e); //$NON-NLS-1$

			throw e;
		}

		// check whether the operation failed with some kind of exception and,
		//    if so, re-throw it according to the exceptions declared by
		//    this method
		Throwable exception = pendingException[0];

		if (exception instanceof InterruptedException) {

			Trace.throwing(MSLPlugin.getDefault(),
				MSLDebugOptions.EXCEPTIONS_THROWING, getClass(),
				"run", exception); //$NON-NLS-1$

			throw (InterruptedException) exception;

		} else if (exception instanceof InvocationTargetException) {

			Trace.throwing(MSLPlugin.getDefault(),
				MSLDebugOptions.EXCEPTIONS_THROWING, getClass(),
				"run", exception); //$NON-NLS-1$

			throw (InvocationTargetException) exception;

		} else if (exception instanceof Exception) {

			InvocationTargetException e = new InvocationTargetException(
				exception);

			Trace.throwing(MSLPlugin.getDefault(),
				MSLDebugOptions.EXCEPTIONS_THROWING, getClass(), "run", e); //$NON-NLS-1$

			throw e;
		}
	}

	/**
	 * Runs a resource set modification operation.
	 * 
	 * @param modifyOp
	 *            the modify operation to run
	 * @param monitor
	 *            the progress monitor to pass to the operation
	 * 
	 * @throws Exception
	 *             an exception to proagate to the client
	 */
	private void run(final ResourceSetModifyOperation modifyOp,
			final IProgressMonitor monitor)
		throws Exception {

		final Exception[] pendingException = new Exception[1];

		Runnable runnable = new Runnable() {

			public void run() {
				MRunnable mrun = new MRunnable() {

					public Object run() {
						try {
							modifyOp.run(monitor);
						} catch (Exception e) {
							modifyOp.setResult(new Status(IStatus.ERROR,
								MSLPlugin.getPluginId(),
								MSLStatusCodes.OPERATION_FAILED,
								MSLCoreMessages.operation_failed,
								e));
							pendingException[0] = e;
						}

						return null;
					}
				};

				try {
					runAsWrite(mrun);
				} catch (MSLActionAbandonedException e) {
					pendingException[0] = e;
				} finally {
					if (modifyOp.getResult() == null) {
						// if the exception handler didn't set a status,
						//    inherit the MRunnable's status
						modifyOp.setResult(mrun.getStatus());
					}
				}
			}
		};

		runInUndoInterval(modifyOp.getLabel(), runnable);

		if (pendingException[0] != null) {
			// don't log at this point because we will do that in the
			//   calling method
			throw pendingException[0];
		}
	}

	/**
	 * Runs a resource set read operation.
	 * 
	 * @param readOp
	 *            the read operation to run
	 * @param monitor
	 *            the progress monitor to pass to the operation
	 * 
	 * @throws Exception
	 *             an exception to proagate to the client
	 */
	private void run(final ResourceSetReadOperation readOp,
			final IProgressMonitor monitor)
		throws Exception {

		final Exception[] pendingException = new Exception[1];

		runAsRead(new MRunnable() {

			public Object run() {
				try {

					readOp.run(monitor);

					readOp.setResult(new Status(IStatus.OK, MSLPlugin
						.getPluginId(), MSLStatusCodes.OK,
						MSLCoreMessages.operation_ok,
						null));
				} catch (Exception e) {

					readOp.setResult(new Status(IStatus.ERROR, MSLPlugin
						.getPluginId(), MSLStatusCodes.OPERATION_FAILED,
						MSLCoreMessages.operation_failed,
						e));

					pendingException[0] = e;
				}

				return null;
			}
		});

		if (pendingException[0] != null) {
			// don't log at this point because we will do that in the
			//   calling method
			throw pendingException[0];
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.EditingDomain#addOperationListener(org.eclipse.gmf.runtime.emf.core.OperationListener)
	 */
	public void addOperationListener(OperationListener listener) {

		if (operationListenerBroker == null)
			operationListenerBroker = new MSLOperationListenerBroker(this);

		operationListenerBroker.addOperationListener(listener);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.EditingDomain#removeOperationListener(org.eclipse.gmf.runtime.emf.core.OperationListener)
	 */
	public void removeOperationListener(OperationListener listener) {

		if (operationListenerBroker != null)
			operationListenerBroker.removeOperationListener(listener);
	}

	/**
	 * Computes segement count difference between two URIs if one is a subset of
	 * the other.
	 */
	private static int computeDiff(URI subURI, URI containerURI) {

		int subSegmentCount = subURI.segmentCount();
		int containerSegmentCount = containerURI.segmentCount();

		if ((subSegmentCount > 0)
			&& (subURI.segment(subSegmentCount - 1)
				.equals(MSLConstants.EMPTY_STRING))) {

			subURI = subURI.trimSegments(1);
			subSegmentCount--;
		}

		if ((containerSegmentCount > 0)
			&& (containerURI.segment(containerSegmentCount - 1)
				.equals(MSLConstants.EMPTY_STRING))) {

			containerURI = containerURI.trimSegments(1);
			containerSegmentCount--;
		}

		int diff = containerSegmentCount - subSegmentCount;

		if (diff < 0)
			return -1;

		else if (diff > 0)
			containerURI = containerURI.trimSegments(diff);

		if (!subURI.equals(containerURI))
			return -1;

		return diff;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#getSchemaToLocationMap(org.eclipse.emf.common.util.URI)
	 */
	public Map getSchemaToLocationMap(URI uri)
		throws IOException {

		InputStream inputStream = null;
		Map requiredLocations = null;

		// create an input stream using the passed uri.
		try {

			inputStream = getResourceSet().getURIConverter().createInputStream(
				uri);

			requiredLocations = getSchemaToLocationMap(inputStream);

		} finally {

			if (inputStream != null)
				inputStream.close();
		}

		return requiredLocations;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#getSchemaToLocationMap(org.eclipse.emf.common.util.URI)
	 */
	public Map getSchemaToLocationMap(InputStream inputStream)
		throws IOException {

		Map requiredLocations = new HashMap();

		// create a buffer reader using the stream we just created
		BufferedReader bufferedReader = null;

		try {

			bufferedReader = new BufferedReader(new InputStreamReader(
				inputStream));

			String line;
			int indexOfSchemasLocations = -1;

			// read the file line by line
			while ((line = bufferedReader.readLine()) != null) {

				// try to find the schema's location line
				indexOfSchemasLocations = line.indexOf(XMLResource.XSI_NS
					+ ":" + XMLResource.SCHEMA_LOCATION); //$NON-NLS-1$

				if (indexOfSchemasLocations != -1) {

					int schemaStart = line.indexOf(
						"\"", indexOfSchemasLocations + 1); //$NON-NLS-1$

					int schemaEnd = line.indexOf("\"", schemaStart + 1); //$NON-NLS-1$

					String schemas = line.substring(schemaStart + 1, schemaEnd);

					// trimming the white spaces
					schemas = schemas.trim();
					StringTokenizer st = new StringTokenizer(schemas);

					while (st.hasMoreTokens()) {

						String schema = st.nextToken();
						String requiredLocation = ""; //$NON-NLS-1$

						if (st.hasMoreTokens())
							requiredLocation = st.nextToken();

						int hashLocation = requiredLocation.indexOf("#"); //$NON-NLS-1$

						if (hashLocation != -1)
							requiredLocation = requiredLocation.substring(0,
								hashLocation);

						if (requiredLocation.length() > 0)
							requiredLocations.put(schema, URI
								.decode(requiredLocation));
					}

					break;
				}
			}

		} finally {

			if (bufferedReader != null)
				bufferedReader.close();
		}

		return requiredLocations;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#setPathVariable(java.lang.String,
	 *      java.lang.String)
	 */
	public void setPathVariable(String var, String val) {
		PathmapManager.setPathVariable(var, val);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#removePathVariable(java.lang.String)
	 */
	public void removePathVariable(String var) {
		PathmapManager.removePathVariable(var);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#getPathVariable(java.lang.String)
	 */
	public String getPathVariable(String var) {
		return pathmap.getPathVariable(var);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#getImports(org.eclipse.emf.ecore.resource.Resource)
	 */
	public Collection getImports(Resource resource) {
		return crossReferenceAdapter.getImports(resource);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#getExports(org.eclipse.emf.ecore.resource.Resource)
	 */
	public Collection getExports(Resource resource) {
		return crossReferenceAdapter.getExports(resource);
	}
	
	/**
	 * <P>
	 * Provides the extended metadata object that would be used
	 *  to load resources in compatibility mode.
	 * </P>
	 * <P>
	 * NOTE: This is not intended to be used externally or even
	 *  internally if it can be avoided.
	 * </P> 
	 *  
	 * @see MResourceOption#COMPATIBILITY_MODE
	 * 
	 * @return The extended metadata object used in this resource set.
	 */
	public ExtendedMetaData getExtendedMetaData() {
		return extendedMetaData;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#getAllImports(org.eclipse.emf.ecore.resource.Resource)
	 */
	public Collection getAllImports(Resource resource) {

		Collection imports = new HashSet();
		Collection unload = new HashSet();

		getAllImports(resource, imports, unload);

		for (Iterator i = unload.iterator(); i.hasNext();)
			unloadResource((Resource) i.next());

		return imports;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#getAllExports(org.eclipse.emf.ecore.resource.Resource)
	 */
	public Collection getAllExports(Resource resource) {

		Collection exports = new HashSet();
		Collection unload = new HashSet();

		getAllExports(resource, exports, unload);

		for (Iterator i = unload.iterator(); i.hasNext();)
			unloadResource((Resource) i.next());

		return exports;
	}

	/**
	 * Get all imports of a resource.
	 */
	private void getAllImports(Resource resource, Collection imports,
			Collection unload) {

		if (!resource.isLoaded()) {

			try {
				loadResource(resource);
			} catch (Exception e) {
				// ignore resources that fail to load.
			}

			unload.add(resource);
		}

		Collection directImports = getImports(resource);

		for (Iterator i = directImports.iterator(); i.hasNext();) {

			Resource directImport = (Resource) i.next();

			if (!imports.contains(directImport)) {

				imports.add(directImport);

				getAllImports(directImport, imports, unload);
			}
		}
	}

	/**
	 * Get all exports of a resource.
	 */
	private void getAllExports(Resource resource, Collection exports,
			Collection unload) {

		if (!resource.isLoaded()) {

			try {
				loadResource(resource);
			} catch (Exception e) {
				// ignore resources that fail to load.
			}

			unload.add(resource);
		}

		Collection directExports = getExports(resource);

		for (Iterator i = directExports.iterator(); i.hasNext();) {

			Resource directExport = (Resource) i.next();

			if (!exports.contains(directExport)) {

				exports.add(directExport);

				getAllExports(directExport, exports, unload);
			}
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#getResourceSets()
	 */
	public static Set getResourceSets() {
		return Collections.unmodifiableSet(resourceSets.keySet());
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#getEditingDomain(org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public static MEditingDomain getEditingDomain(ResourceSet resourceSet) {

		WeakReference reference = (WeakReference) resourceSets.get(resourceSet);

		if (reference != null)
			return (MSLEditingDomain) reference.get();

		return null;
	}
	
	/**
	 * Associates the specified resource set with an editing domain.
	 * 
	 * @param rset the resource set to associate with the <code>domain</code>
	 * @param domain the editing domain to associate with the resource set
	 */
	public static void setEditingDomain(ResourceSet rset, MEditingDomain domain) {
		
		MEditingDomain currentDomain = getEditingDomain(rset);
		
		if (currentDomain != domain) {
			if (currentDomain instanceof MSLEditingDomain) {
				MSLEditingDomain oldDomain = (MSLEditingDomain) currentDomain;
				
				oldDomain.detachFrom(rset);
			}
			
			if (domain instanceof MSLEditingDomain) {
				MSLEditingDomain newDomain = (MSLEditingDomain) domain;
				
				newDomain.attachTo(rset);
			}
			
			resourceSets.put(rset, new WeakReference(domain));
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#getEditingDomain(org.eclipse.emf.ecore.resource.Resource)
	 */
	public static MEditingDomain getEditingDomain(Resource resource) {

		ResourceSet resourceSet = resource.getResourceSet();

		if (resourceSet != null)
			return getEditingDomain(resourceSet);

		return null;
	}

	/**
	 * Converts a file URI to a platform URI.
	 */
	private static URI getPlatformURI(URI uri) {

		if (MSLConstants.PLATFORM_SCHEME.equals(uri.scheme()))
			return URI.createURI(uri.toString(), true);

		IFile file = findFileInWorkspace(uri);

		if (file != null) {

			IProject project = file.getProject();

			if (project != null) {

				StringBuffer pathName = new StringBuffer(project.getName());

				pathName.append(MSLConstants.PATH_SEPARATOR);
				pathName.append(file.getProjectRelativePath().toString());

				return URI.createURI(URI.createPlatformResourceURI(
					pathName.toString(),true).toString(), true);
			}
		}

		return null;
	}

	/**
	 * Finds a file in the workspace given its file URI.
	 */
	private static IFile findFileInWorkspace(URI uri) {

		IWorkspace workspace = ResourcesPlugin.getWorkspace();

		if (workspace != null) {

			IWorkspaceRoot root = workspace.getRoot();

			if (root != null) {

				IFile[] files = root.findFilesForLocation(new Path(uri
					.toFileString()));

				if (files != null) {

					for (int i = 0; i < files.length; i++) {

						IFile file = files[i];

						IProject project = file.getProject();

						if (project != null)
							return file;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Creates re-gui-ided copies of the resources provided in the map. The
	 * resources must be loaded resources
	 * 
	 * @param resource2URI
	 *            a map with the resources to copy as keys and their new URIs as
	 *            values
	 * @throws IOException
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain#copyResources(java.util.Map)
	 */
	public void copyResources(Map resource2URI)
		throws IOException {

		CopyingResourceSet mslCopyingResourceSet = new CopyingResourceSet(
			getResourceSet());

		Iterator it = resource2URI.entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry entry = (Map.Entry) it.next();
			new CopyingResource((XMLResource) entry.getKey(), (URI) entry
				.getValue(), mslCopyingResourceSet);
		}

		it = mslCopyingResourceSet.getResources().iterator();

		while (it.hasNext()) {

			CopyingResource copyingRes = (CopyingResource) it.next();
			copyingRes.save(Collections.EMPTY_MAP);
		}
	}

	/**
	 * Pushes a writer onto the stack of currently executing runnables.
	 * 
	 * @param writer
	 * 
	 * @see #runAsWrite(MRunnable)
	 * @see #popWriter()
	 */
	protected synchronized final void pushWriter(MRunnable writer) {
		writers.add(writers.size(), writer);
	}

	/**
	 * Pops the topmost writer from the stack of currently executing runnables.
	 * 
	 * @return the topmost writer from the stack
	 */
	protected synchronized final MRunnable popWriter() {
		return (MRunnable) writers.remove(writers.size() - 1);
	}

	/**
	 * Peeks at the top of the stack of currently executing runnables.
	 * 
	 * @return the top writer, or <code>null</code> if there are no writers
	 */
	protected synchronized final MRunnable peekWriter() {
		return writers.isEmpty() ? null
			: (MRunnable) writers.get(writers.size() - 1);
	}

	public void yieldForReads() {
		delegate.yield();
	}

	private static class MSLResourceSet
		extends ResourceSetImpl
		implements IEditingDomainProvider {

		/**
		 * Constructor.
		 */
		public MSLResourceSet() {
			super();
		}

		/**
		 * @see org.eclipse.emf.ecore.resource.ResourceSet#getResource(org.eclipse.emf.common.util.URI,
		 *      boolean)
		 */
		public Resource getResource(URI uri, boolean loadOnDemand) {

			MEditingDomain domain = MEditingDomain.getEditingDomain(this);

			// no editing domain.
			if (domain == null)
				return super.getResource(uri, loadOnDemand);

			// convert URI to use platform scheme.
			URI convertedURI = domain.convertURI(uri);

			// no conversion happened.
			if (convertedURI.equals(uri))
				return super.getResource(uri, loadOnDemand);

			// try to get resource without loading using file URI.
			Resource resource = super.getResource(uri, false);

			// not found, find resource using platform URI.
			if (resource == null)
				resource = super.getResource(convertedURI, loadOnDemand);

			// need to load resource.
			else if (loadOnDemand)
				resource = super.getResource(uri, true);

			return resource;
		}
		
		public EList getResources() {
			if (resources == null) {
				// make sure that any resource that is removed from me is also
				//    removed from my package registry, in case it contained
				//    the EPackage for some namespace in one of my resources.
				//    Take this opportunity also to clean out proxies
				resources = new ResourcesEList() {
					private static final long serialVersionUID = 401816160908753449L;

					protected void didRemove(int index, Object oldObject) {
						EPackage.Registry registry = getPackageRegistry();
						
						if (registry != EPackage.Registry.INSTANCE) {
							for (Iterator iter = getPackageRegistry().entrySet().iterator(); iter.hasNext();) {
								Map.Entry next = (Map.Entry) iter.next();
								EPackage ePackage = (EPackage) next.getValue();
								
								if (ePackage.eIsProxy()
										|| (ePackage.eResource() == oldObject)) {
									iter.remove();
									
									// must continue in case multiple packages are
									//    in the same resource
								}
							}
						}
					}
					
					protected void didClear(int oldSize, Object[] oldObjects) {
						EPackage.Registry registry = getPackageRegistry();
						
						if (registry != EPackage.Registry.INSTANCE) {
							// just clear the registry, since no resources
							//    remain to use it anyway
							registry.clear();
						}
					}
				};
			}
			
			return resources;
		}

		public EditingDomain getEditingDomain() {
			return MEditingDomain.getEditingDomain(this);
		}
	}
	
	public boolean isUndoInProgress() {
		return isUndoing;
	}
	
	public boolean isRedoInProgress() {
		return isRedoing;
	}
	
	public boolean isAbandonInProgress() {
		InternalTransaction tx = delegate.getActiveTransaction();
		
		return (tx != null) && tx.isRollingBack();
	}
	
	void setUndoInProgress(boolean undoing) {
		this.isUndoing = undoing;
	}
	
	void setRedoInProgress(boolean redoing) {
		this.isRedoing = redoing;
	}
	
	//
	// Implementation of the TXEditingDomain interface
	//

	// Documentation copied from the inherited specification
	public Command createCommand(Class commandClass, CommandParameter commandParameter) {
		return delegate.createCommand(commandClass, commandParameter);
	}

	// Documentation copied from the inherited specification
	public Command createOverrideCommand(OverrideableCommand command) {
		return delegate.createOverrideCommand(command);
	}

	// Documentation copied from the inherited specification
	public void dispose() {
		delegate.dispose();
	}

	// Documentation copied from the inherited specification
	public Collection getChildren(Object object) {
		return delegate.getChildren(object);
	}

	// Documentation copied from the inherited specification
	public Collection getClipboard() {
		return delegate.getClipboard();
	}

	// Documentation copied from the inherited specification
	public CommandStack getCommandStack() {
		return delegate.getCommandStack();
	}

	// Documentation copied from the inherited specification
	public String getID() {
		return delegate.getID();
	}

	// Documentation copied from the inherited specification
	public Collection getNewChildDescriptors(Object object, Object sibling) {
		return delegate.getNewChildDescriptors(object, sibling);
	}

	// Documentation copied from the inherited specification
	public boolean getOptimizeCopy() {
		return delegate.getOptimizeCopy();
	}

	// Documentation copied from the inherited specification
	public Object getParent(Object object) {
		return delegate.getParent(object);
	}

	// Documentation copied from the inherited specification
	public ResourceSet getResourceSet() {
		return delegate.getResourceSet();
	}

	// Documentation copied from the inherited specification
	public Object getRoot(Object object) {
		return delegate.getRoot(object);
	}

	// Documentation copied from the inherited specification
	public List getTreePath(Object object) {
		return delegate.getTreePath(object);
	}

	// Documentation copied from the inherited specification
	public boolean isReadOnly(Resource resource) {
		return delegate.isReadOnly(resource);
	}

	// Documentation copied from the inherited specification
	public boolean isControllable(Object object) {
		return delegate.isControllable(object);
	}

	// Documentation copied from the inherited specification
	public Object runExclusive(Runnable read) throws InterruptedException {
		return delegate.runExclusive(read);
	}

	// Documentation copied from the inherited specification
	public void setClipboard(Collection clipboard) {
		delegate.setClipboard(clipboard);
	}

	// Documentation copied from the inherited specification
	public void setID(String id) {
		delegate.setID(id);
	}

	// Documentation copied from the inherited specification
	public TreeIterator treeIterator(Object object) {
		return delegate.treeIterator(object);
	}

	// Documentation copied from the inherited specification
	public void yield() {
		delegate.yield();
	}
	
	// Documentation copied from the inherited specification
	public InternalTransaction startTransaction(boolean readOnly, Map options)
		throws InterruptedException {
		return delegate.startTransaction(readOnly, options);
	}
	
	// Documentation copied from the inherited specification
	public void activate(InternalTransaction tx)
		throws InterruptedException {
		delegate.activate(tx);
	}
	
	// Documentation copied from the inherited specification
	public InternalTransaction getActiveTransaction() {
		return delegate.getActiveTransaction();
	}

	// Documentation copied from the inherited specification
	public void precommit(InternalTransaction tx)
		throws RollbackException {
		delegate.precommit(tx);
	}
	
	// Documentation copied from the inherited specification
	public void deactivate(InternalTransaction tx) {
		delegate.deactivate(tx);
	}
	
	// Documentation copied from the inherited specification
	public TransactionChangeRecorder getChangeRecorder() {
		return delegate.getChangeRecorder();
	}
	
	// Documentation copied from the inherited specification
	public TransactionValidator getValidator() {
		return delegate.getValidator();
	}
	
	// Documentation copied from the inherited specification
	public void broadcastUnbatched(Notification notification) {
		delegate.broadcastUnbatched(notification);
	}
	
	// Documentation copied from the inherited specification
	public void addResourceSetListener(ResourceSetListener l) {
		l = wrap(l);
		
		if (l != null) {
			delegate.addResourceSetListener(l);
		}
	}

	// Documentation copied from the inherited specification
	public void removeResourceSetListener(ResourceSetListener l) {
		l = unwrap(l);
		
		if (l != null) {
			delegate.removeResourceSetListener(l);
		}
	}
	
	private ResourceSetListener wrap(ResourceSetListener l) {
		ResourceSetListener result = (ResourceSetListener) resourceSetListeners.get(l);
		
		if (result == null) {
			result = new ResourceSetListenerWrapper(l);
			resourceSetListeners.put(l, result);
		}
		
		return result;
	}
	
	private ResourceSetListener unwrap(ResourceSetListener l) {
		ResourceSetListener result = (ResourceSetListener) resourceSetListeners.remove(l);
		
		if (result != null) {
			resourceSetListeners.remove(l);
		}
		
		return result;
	}
	
	/**
	 * Wraps a resource set listener to provide events whose source is the
	 * MSLEditingDomain, not its delegate.
	 *
	 * @author Christian W. Damus (cdamus)
	 */
	private class ResourceSetListenerWrapper implements ResourceSetListener {
		private final ResourceSetListener delegateListener;
		
		ResourceSetListenerWrapper(ResourceSetListener l) {
			this.delegateListener = l;
		}
		
		public NotificationFilter getFilter() {
			return delegateListener.getFilter();
		}
		
		public boolean isPostcommitOnly() {
			return delegateListener.isPostcommitOnly();
		}
		
		public boolean isPrecommitOnly() {
			return delegateListener.isPrecommitOnly();
		}
		
		public boolean isAggregatePrecommitListener() {
			return delegateListener.isAggregatePrecommitListener();
		}
		
		public void resourceSetChanged(ResourceSetChangeEvent event) {
			delegateListener.resourceSetChanged(clone(event));
		}
		
		public Command transactionAboutToCommit(ResourceSetChangeEvent event)
			throws RollbackException {
			return delegateListener.transactionAboutToCommit(clone(event));
		}
		
		private ResourceSetChangeEvent clone(ResourceSetChangeEvent event) {
			return new ResourceSetChangeEvent(
				MSLEditingDomain.this,
				event.getTransaction(),
				event.getNotifications());
		}
	}
}