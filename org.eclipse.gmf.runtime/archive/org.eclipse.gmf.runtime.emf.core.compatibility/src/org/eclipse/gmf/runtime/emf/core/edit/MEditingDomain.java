/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core.edit;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;

/**
 * <p>
 * This class implements an EMF <code>EditingDomain</code> and adds some MSL
 * specific features like undo intervals, adhoc undo/redo, some resource
 * utilities and some eventing features.
 * </p>
 * <p>
 * This is the entry point to the MSL API; one would first call
 * <code>createNewDomain()</code> to create a new instance of the editing
 * domain with its own undo stack and resource set. The modeling tools use the
 * single instance of the editing domain: <code>INSTANCE</code>.
 * </p>
 * 
 * @author rafikj
 * 
 * @deprecated Use the {@link org.eclipse.emf.transaction.TXEditingDomain} API,
 *    instead.  See the methods of this class for more specific API
 *    equivalences. 
 */
public abstract class MEditingDomain
	extends org.eclipse.gmf.runtime.emf.core.EditingDomain
	implements TransactionalEditingDomain {

	/**
	 * A singleton instance of the editing domain.
	 */
	public final static MEditingDomain INSTANCE = new MSLEditingDomain();

	/**
	 * Creates a new MSL editing domain.
	 * 
	 * @return The new editing domain.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain.Factory#createEditingDomain()}
	 *    API to create new editing domains.
	 */
	public static MEditingDomain createNewDomain() {
		return new MSLEditingDomain();
	}

	/**
	 * Creates a new MSL editing domain on the specified resource set.
	 * The client can provide a custom resource set implementation, and the MSL
	 * will apply its read/write protocol, eventing, validation, etc. semantics
	 * to it.
	 * 
	 * @param rset a resource set provided by the client
	 * 
	 * @return The new editing domain
	 * 
	 * @deprecated Use the
	 *   {@link org.eclipse.emf.transaction.TXEditingDomain.Factory#createEditingDomain(ResourceSet)}
	 *    API to create new editing domains.
	 */
	public static MEditingDomain createNewDomain(ResourceSet rset) {
		return new MSLEditingDomain(rset);
	}

	/**
	 * Creates an EObject given its EClass. The object is detached.
	 * 
	 * @param eClass
	 *            The <code>EClass</code>.
	 * @return The new <code>EObject</code>.
	 * 
	 * @deprecated Use the {@link org.eclipse.gmf.runtime.emf.core.util.EObjectUtil#create(EClass)}
	 *    method or one of its variants to instantiate EClasses.
	 */
	public abstract EObject create(EClass eClass);

	/**
	 * Gets the file name of the resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The file name of the resource.
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.common.util.URI#path()} API.
	 */
	public abstract String getResourceFileName(Resource resource);

	/**
	 * Sets the file name of the resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @param fileNameURI
	 *            The new file path.
	 * 
	 * @deprecated Use the {@link Resource#setURI(URI)} API.
	 */
	public abstract void setResourceFileName(Resource resource,
			String fileNameURI);

	/**
	 * Sets the file name of the resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @param fileNameURI
	 *            The new file path.
	 * @param options
	 *            The file name options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * 
	 * @deprecated Use the {@link Resource#setURI(URI)} API.
	 */
	public abstract void setResourceFileName(Resource resource,
			String fileNameURI, int options);

	/**
	 * Finds a resource given its path.
	 * 
	 * @param fileNameURI
	 *            The resource path.
	 * @param options
	 *            the file name options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * @return The found resource.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.ecore.resource.ResourceSet#getResource(URI, boolean)}
	 *    API.
	 */
	public abstract Resource findResource(String fileNameURI, int options);

	/**
	 * Finds a resource given its path.
	 * 
	 * @param fileNameURI
	 *            The resource path.
	 * @return The found resource.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.ecore.resource.ResourceSet#getResource(URI, boolean)}
	 *    API.
	 */
	public abstract Resource findResource(String fileNameURI);

	/**
	 * Converts a URI to use a pathmap or platform URI.
	 * 
	 * @param uri
	 *            The URI to convert.
	 * @return The converted URI.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.ecore.resource.URIConverter} of your
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain}'s
	 *    {@link org.eclipse.emf.edit.domain.EditingDomain#getResourceSet() resource set}.
	 */
	public abstract URI convertURI(URI uri);

	/**
	 * Produces a resource for a given file name URI. If the
	 *  resource is already loaded then an exception is thrown. The
	 *  resource will be in the unloaded state when returned.
	 * 
	 * @param fileNameURI
	 *            Optional file name to be assigned to the resource.
	 * @return The resource object.
     *
	 * @throws IllegalStateException if the resource is already loaded.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.edit.domain.EditingDomain#createResource(String)}
	 *    or {@link org.eclipse.emf.ecore.resource.ResourceSet#createResource(URI)}
	 *    API.
	 */
	public abstract Resource createResource(String fileNameURI);

	/**
	 * Produces a resource for a given URI and with the provided
	 *  options. Unless specified with the appropriate option
	 *  {@link MResourceOption#URI}, the URI is assumed to be a file path.
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
	 *    {@link org.eclipse.emf.ecore.resource.ResourceSet#createResource(URI)}
	 *    API.
	 */
	public abstract Resource createResource(String uri, int options);

	/**
	 * Creates a resource and object of type rootEClass that becomes the root of
	 * the resource.
	 * 
	 * @param fileNameURI
	 *            Optional file name to be assigned to the resource.
	 * @param rootEClass
	 *            The root object's type (optional).
	 * @return The new resource.
	 * @throws IllegalStateException if the resource is already loaded.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.edit.domain.EditingDomain#createResource(String)}
	 *    or {@link org.eclipse.emf.ecore.resource.ResourceSet#createResource(URI)}
	 *    API and add a new root element to its contents list.
	 */
	public abstract Resource createResource(String fileNameURI,
			EClass rootEClass);

	/**
	 * Creates a resource and object of type rootEClass that becomes the root of
	 * the resource.
	 * 
	 * @param fileNameURI
	 *            Optional file name to be assigned to the resource.
	 * @param rootEClass
	 *            The root object's type (optional)
	 * @return The new resource.
	 * @throws IllegalStateException if the resource is already loaded.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.ecore.resource.ResourceSet#createResource(URI)}
	 *    API and add a new root element to its contents list.
	 */
	public abstract Resource createResource(String fileNameURI,
			EClass rootEClass, int options);

	/**
	 * Loads an unloaded resource. It is the responsibility of callers to catch
	 *  any exceptions that will be thrown as a result of the resource being loaded
	 *  with errors. Note that the state of the resource could be loaded (ie. it could
	 *  be necessary to unload it) but the {@link Resource#getErrors()} could be non-empty.
	 * 
	 * @param resource
	 *            The resource to load.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.ecore.resource.Resource#load(Map)} API.
	 */
	public abstract void loadResource(Resource resource);
	
	/**
	 * Loads an unloaded resource. It is the responsibility of callers to catch
	 *  any exceptions that will be thrown as a result of the resource being loaded
	 *  with errors. Note that the state of the resource could be loaded (ie. it could
	 *  be necessary to unload it) but the {@link Resource#getErrors()} could be non-empty.
	 * 
	 * @param resource
	 *            The resource to load.
	 * @param options
	 *            The load options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.ecore.resource.Resource#load(Map)} API,
	 *    populating the options map as described in the {@link MResourceOption}
	 *    documentation.
	 */
	public abstract void loadResource(Resource resource, int options);

	/**
	 * Loads an unloaded resource from the given input stream using the given
	 * load options. It is the responsibility of callers to catch
	 *  any exceptions that will be thrown as a result of the resource being loaded
	 *  with errors. Note that the state of the resource could be loaded
	 *  (ie. it could be necessary to unload it) but the {@link Resource#getErrors()} 
	 *  could be non-empty.
	 * 
	 * @param resource
	 *            The resource to load.
	 * @param options
	 *            The load options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * @param inputStream
	 *            The input stream from which to load the resource's contents.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.ecore.resource.Resource#load(InputStream, Map)} API,
	 *    populating the options map as described in the {@link MResourceOption}
	 *    documentation.
	 */
	public abstract void loadResource(Resource resource, int options,
			InputStream inputStream);

	/**
	 * Unloads a loaded resource.
	 * 
	 * @param resource
	 *            The resource to unload.
	 *            
	 * @deprecated Use {@link Resource#unload()}, instead.
	 */
	public abstract void unloadResource(Resource resource);

	/**
	 * Unloads a loaded resource.
	 * 
	 * @param resource
	 *            The resource to unload.
	 * @param options
	 *            The unload options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 *            
	 * @deprecated Use {@link Resource#unload()}, instead.
	 */
	public abstract void unloadResource(Resource resource, int options);

	/**
	 * Saves a resource.
	 * 
	 * @param resource
	 *            The resource to save.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.ecore.resource.Resource#save(Map)} API.
	 */
	public abstract void saveResource(Resource resource);

	/**
	 * Saves a resource.
	 * 
	 * @param resource
	 *            The resource to save.
	 * @param options
	 *            The save options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.ecore.resource.Resource#save(Map)} API,
	 *    populating the options map as described in the {@link MResourceOption}
	 *    documentation.
	 */
	public abstract void saveResource(Resource resource, int options);

	/**
	 * Sets the path of a resource then saves it.
	 * 
	 * @param resource
	 *            The resource to save.
	 * @param fileNameURI
	 *            The new file path.
	 * 
	 * @deprecated Set the resource's {@link Resource#setURI(URI) new URI} and
	 *    then save it using the
	 *    {@link org.eclipse.emf.ecore.resource.Resource#save(Map)} API.
	 */
	public abstract void saveResourceAs(Resource resource, String fileNameURI);

	/**
	 * Sets the path of a resource then saves it.
	 * 
	 * @param resource
	 *            The resource to save.
	 * @param fileNameURI
	 *            The new file path.
	 * @param options
	 *            The save options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * 
	 * @deprecated Set the resource's {@link Resource#setURI(URI) new URI} and
	 *    then save it using the
	 *    {@link org.eclipse.emf.ecore.resource.Resource#save(Map)} API,
	 *    populating the options map as described in the {@link MResourceOption}
	 *    documentation.
	 */
	public abstract void saveResourceAs(Resource resource, String fileNameURI,
			int options);

	/**
	 * Opens an undo interval. An undo interval must be open before starting a
	 * write action.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.  Transactions
	 *    are implicitly created for the execution of any
	 *    {@link org.eclipse.core.commands.Command} on the command stack and
	 *    by {@link org.eclipse.emf.workbench.AbstractEMFOperation}s when they
	 *    are executed on the operation history.
	 */
	public abstract void openUndoInterval();

	/**
	 * Opens an undo interval. An undo interval must be open before starting a
	 * write action.
	 * 
	 * @param label
	 *            The label.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.  Transactions
	 *    are implicitly created for the execution of any
	 *    {@link org.eclipse.core.commands.Command} on the command stack and
	 *    by {@link org.eclipse.emf.workbench.AbstractEMFOperation}s when they
	 *    are executed on the operation history.  Commands and operations have
	 *    their own labels.
	 */
	public abstract void openUndoInterval(String label);

	/**
	 * Opens an undo interval. An undo interval must be open before starting a
	 * write action.
	 * 
	 * @param label
	 *            The label.
	 * @param description
	 *            The description.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.  Transactions
	 *    are implicitly created for the execution of any
	 *    {@link org.eclipse.core.commands.Command} on the command stack and
	 *    by {@link org.eclipse.emf.workbench.AbstractEMFOperation}s when they
	 *    are executed on the operation history.  Commands and operations have
	 *    their own labels and (in some cases) descriptions.
	 */
	public abstract void openUndoInterval(String label, String description);

	/**
	 * Closes the currently open undo interval and returns a reference to the
	 * closed interval if interval is not empty. If interval is empty returns
	 * null;
	 * 
	 * @return The undo interval or null if interval is empty.
	 * 
	 * @deprecated Use the {@link #runInUndoInterval(Runnable)} method or
	 *    a variant, instead.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.  Transactions
	 *    are implicitly closed by the successful execution of any
	 *    {@link org.eclipse.core.commands.Command} on the command stack and
	 *    by {@link org.eclipse.emf.workbench.AbstractEMFOperation}s on the
	 *    operation history.
	 */
	public abstract MUndoInterval closeUndoInterval();

	/**
	 * Can the current interval be undone?
	 * 
	 * @return True if current interval can be undone.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.
	 */
	public abstract boolean canUndoCurrentInterval();

	/**
	 * Can the current interval be redone?
	 * 
	 * @return True if current interval can be redone.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.
	 */
	public abstract boolean canRedoCurrentInterval();

	/**
	 * Sets can the current interval be undone.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.
	 */
	public abstract void setCanUndoCurrentInterval(boolean canUndo);

	/**
	 * Sets can the current interval be redone.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.
	 */
	public abstract void setCanRedoCurrentInterval(boolean canRedo);

	/**
	 * Runs the runnable instance in an undo interval and will take care of
	 * opening and closing an undo interval.
	 * 
	 * @param runnable
	 *            The runnable.
	 * @return The undo interval.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.  Transactions
	 *    are implicitly created for the execution of any
	 *    {@link org.eclipse.core.commands.Command} on the command stack and
	 *    by {@link org.eclipse.emf.workbench.AbstractEMFOperation}s when they
	 *    are executed on the operation history.
	 */
	public abstract MUndoInterval runInUndoInterval(Runnable runnable);

	/**
	 * Runs the runnable instance in an undo interval and will take care of
	 * opening and closing an undo interval.
	 * 
	 * @param label
	 *            The label.
	 * @param runnable
	 *            The runnable.
	 * @return The undo interval.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.  Transactions
	 *    are implicitly created for the execution of any
	 *    {@link org.eclipse.core.commands.Command} on the command stack and
	 *    by {@link org.eclipse.emf.workbench.AbstractEMFOperation}s when they
	 *    are executed on the operation history.  Commands and operations have
	 *    their own labels.
	 */
	public abstract MUndoInterval runInUndoInterval(String label,
			Runnable runnable);

	/**
	 * Runs the runnable instance in an undo interval and will take care of
	 * opening and closing an undo interval.
	 * 
	 * @param label
	 *            The label.
	 * @param description
	 *            The description.
	 * @param runnable
	 *            The runnable.
	 * @return The undo interval.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.  Transactions
	 *    are implicitly created for the execution of any
	 *    {@link org.eclipse.core.commands.Command} on the command stack and
	 *    by {@link org.eclipse.emf.workbench.AbstractEMFOperation}s when they
	 *    are executed on the operation history.  Commands and operations have
	 *    their own labels and (in some cases) descriptions.
	 */
	public abstract MUndoInterval runInUndoInterval(String label,
			String description, Runnable runnable);

	/**
	 * Checks if there is an open undo interval.
	 * 
	 * @return True if an undo interval is open, false otherwise.
	 * 
	 * @deprecated Undo intervals are replaced by
	 *    {@link org.eclipse.emf.transaction.Transaction} in the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain} API.  At any
	 *    point in time, the <code>TXEditingDomain</code> may have an
	 *    {@link org.eclipse.emf.transaction.impl.InternalTXEditingDomain#getActiveTransaction() active transaction}.
	 *    If it exists and is not read-only, then "an undo interval is open."
	 */
	public abstract boolean isUndoIntervalOpen();
	
	/**
	 * Yields for other read actions on other threads. Only the actions with
	 * read actions open (NO WRITE) can yield. This is a blocking call.
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.transaction.TXEditingDomain#yield()}
	 *    API.
	 */
	public abstract void yieldForReads();

	/**
	 * Starts a read action. Read operations are required for reading models.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain#runExclusive(Runnable)}
	 *    API.
	 */
	public abstract void startRead();

	/**
	 * Starts a write action. Write operations are required for modifying
	 * models.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain#runExclusive(Runnable)}
	 *    API.
	 */
	public abstract void startWrite();

	/**
	 * Starts an unchecked action. Unchecked operations are required for
	 * modifying models outside of any undo interval. These should be used only
	 * in extreme situations for making modifications outside of undo intervals.
	 * 
	 * @deprecated Transactions in {@link org.eclipse.emf.transaction.TXEditingDomain}s
	 *    are implicitly created for the execution of any
	 *    {@link org.eclipse.core.commands.Command} on the command stack and
	 *    by {@link org.eclipse.emf.workbench.AbstractEMFOperation}s when they
	 *    are executed on the operation history.  For "unchecked" access
	 *    (i.e., for making changes in a read-only context), use the
	 *    {@link org.eclipse.emf.transaction.Transaction#OPTION_UNPROTECTED} in
	 *    the options map of the
	 *    {@link org.eclipse.emf.transaction.TXCommandStack#execute(org.eclipse.emf.common.command.Command, Map)}
	 *    method or the
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation#AbstractEMFOperation(TXEditingDomain, String, Map)}
	 *    constructor.
	 */
	public abstract void startUnchecked();

	/**
	 * Completes the currently open action and adds it to the currently open
	 * undo interval if applicable.
	 * 
	 * @deprecated Transactions in {@link org.eclipse.emf.transaction.TXEditingDomain}s
	 *    are implicitly closed at completion of execution of
	 *    {@link org.eclipse.core.commands.Command}s on the command stack and
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation}s on the
	 *    operation history.  For transaction commit without validation, use
	 *    the {@link org.eclipse.emf.transaction.Transaction#OPTION_UNPROTECTED} in
	 *    the options map of the
	 *    {@link org.eclipse.emf.transaction.TXCommandStack#execute(org.eclipse.emf.common.command.Command, Map)}
	 *    method or the
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation#AbstractEMFOperation(TXEditingDomain, String, Map)}
	 *    constructor.
	 */
	public abstract void complete();

	/**
	 * <p>
	 * Completes the currently open action and adds it to the currently open
	 * undo interval if applicable.
	 * </p>
	 * <p>
	 * The resulting status indicates warnings or informational messages from
	 * validation. Note that, if errors are reported by validation, then the
	 * action is automatically abandoned and an
	 * {@link MSLActionAbandonedException}is thrown instead of a status being
	 * returned. Thus, clients are required to handle the situation in which the
	 * action is abandoned because the model changes that they expected to have
	 * applied will not have been effected.
	 * </p>
	 * 
	 * @return a status object containing any warnings or informational messages
	 *         produced by validation. This may be a multi-status if there are
	 *         multiple validation messages
	 * 
	 * @throws MSLActionAbandonedException
	 *             if live constraints find errors
	 * 
	 * @deprecated Transactions in {@link org.eclipse.emf.transaction.TXEditingDomain}s
	 *    are implicitly closed at completion of execution of
	 *    {@link org.eclipse.core.commands.Command}s on the command stack and
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation}s on the
	 *    operation history.  If a transaction fails to commit because of
	 *    validation errors, it will throw a
	 *    {@link org.eclipse.emf.transaction.RollbackException}.
	 */
	public abstract IStatus completeAndValidate()
		throws MSLActionAbandonedException;

	/**
	 * Abandons and discards the currently open action.
	 * 
	 * @deprecated Transactions in {@link org.eclipse.emf.transaction.TXEditingDomain}s
	 *    are implicitly created for the execution of any
	 *    {@link org.eclipse.core.commands.Command} on the command stack and
	 *    by {@link org.eclipse.emf.workbench.AbstractEMFOperation}s when they
	 *    are executed on the operation history.  To force a transaction to
	 *    rollback when it attempts to commit, a
	 *    {@link org.eclipse.emf.transaction.ResourceSetListener} can throw a
	 *    {@link org.eclipse.emf.transaction.RollbackException} from its
	 *    {@link org.eclipse.emf.transaction.ResourceSetListener#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)}
	 *    call-back.  Otherwise, a transaction can be
	 *    {@link org.eclipse.emf.transaction.impl.InternalTransaction#abort(IStatus) aborted}
	 *    during its execution (but it will not roll back until it attempts to
	 *    commit).
	 */
	public abstract void abandon();

	/**
	 * Runs the runnable instance in a read action and will take care of
	 * starting and completing the read action.
	 * 
	 * @param runnable
	 *            The runnable.
	 * 
	 * @deprecated Use the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain#runExclusive(Runnable)}
	 *    API.
	 */
	public abstract Object runAsRead(MRunnable runnable);

	/**
	 * <p>
	 * Runs the runnable instance in a write action and will take care of
	 * starting and completing the write action.
	 * </p>
	 * <p>
	 * Note that if this method does not need to start a write action (because
	 * one is already in progress), then it will not attempt to complete it,
	 * either. In such cases, the <code>runnable</code>'s status will be
	 * {@link MRunnable#setStatus(IStatus) set}to an OK status because no
	 * validation is performed.
	 * </p>
	 * <p>
	 * At any point during the execution of the <code>runnable</code>, it may
	 * be {@linkplain MRunnable#abandon() abandoned}. In this case, its status
	 * will be set to a {@link IStatus#CANCEL}value and the write action will
	 * be abandoned when the <code>runnable</code> returns.
	 * </p>
	 * 
	 * @param runnable
	 *            The runnable. The runnable's status is assigned according to
	 *            the results of live validation
	 * 
	 * @throws MSLActionAbandonedException
	 *             if the action is abandoned because live validation detects
	 *             errors
	 * 
	 * @see MRunnable#abandon()
	 * 
	 * @deprecated To modify a {@link org.eclipse.emf.transaction.TXEditingDomain}'s
	 *    resource set, execute {@link org.eclipse.core.commands.Command} on its
	 *    command stack or execute
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation}s on the
	 *    operation history.
	 */
	public abstract Object runAsWrite(MRunnable runnable)
		throws MSLActionAbandonedException;

	/**
	 * Runs the runnable instance in an unchecked action and will take care of
	 * starting and completing the unchecked action.
	 * 
	 * @param runnable
	 *            The runnable.
	 * 
	 * @deprecated To modify a {@link org.eclipse.emf.transaction.TXEditingDomain}'s
	 *    resource set, execute {@link org.eclipse.core.commands.Command} on its
	 *    command stack or execute
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation}s on the
	 *    operation history.  For "unchecked" access (i.e., modification within
	 *    a read-only context), provide transaction options in the
	 *    {@link org.eclipse.emf.transaction.TXCommandStack#execute(org.eclipse.emf.common.command.Command, Map)}
	 *    method or the
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation#AbstractEMFOperation(TXEditingDomain, String, Map)}
	 *    constructor, as described in the {@link MRunOption} documentation.
	 */
	public abstract Object runAsUnchecked(MRunnable runnable);

	/**
	 * Runs the runnable instance without sending events.
	 * 
	 * @param runnable
	 *            The runnable.
	 * 
	 * @deprecated To modify a {@link org.eclipse.emf.transaction.TXEditingDomain}'s
	 *    resource set, execute {@link org.eclipse.core.commands.Command} on its
	 *    command stack or execute
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation}s on the
	 *    operation history.  For "silent" modifications, provide transaction
	 *    options in the
	 *    {@link org.eclipse.emf.transaction.TXCommandStack#execute(org.eclipse.emf.common.command.Command, Map)}
	 *    method or the
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation#AbstractEMFOperation(TXEditingDomain, String, Map)}
	 *    constructor, as described in the {@link MRunOption} documentation.
	 */
	public abstract Object runSilent(MRunnable runnable);

	/**
	 * Runs the runnable instance without semantic procedures.
	 * 
	 * @param runnable
	 *            The runnable.
	 * 
	 * @deprecated To modify a {@link org.eclipse.emf.transaction.TXEditingDomain}'s
	 *    resource set, execute {@link org.eclipse.core.commands.Command} on its
	 *    command stack or execute
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation}s on the
	 *    operation history.  For "no sem procs" modifications, provide transaction
	 *    options in the
	 *    {@link org.eclipse.emf.transaction.TXCommandStack#execute(org.eclipse.emf.common.command.Command, Map)}
	 *    method or the
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation#AbstractEMFOperation(TXEditingDomain, String, Map)}
	 *    constructor, as described in the {@link MRunOption} documentation.
	 */
	public abstract Object runWithNoSemProcs(MRunnable runnable);

	/**
	 * Runs the runnable instance without validation.
	 * 
	 * @param runnable
	 *            The runnable.
	 * 
	 * @deprecated To modify a {@link org.eclipse.emf.transaction.TXEditingDomain}'s
	 *    resource set, execute {@link org.eclipse.core.commands.Command} on its
	 *    command stack or execute
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation}s on the
	 *    operation history.  For "unvalidated" modifications, provide transaction
	 *    options in the
	 *    {@link org.eclipse.emf.transaction.TXCommandStack#execute(org.eclipse.emf.common.command.Command, Map)}
	 *    method or the
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation#AbstractEMFOperation(TXEditingDomain, String, Map)}
	 *    constructor, as described in the {@link MRunOption} documentation.
	 */
	public abstract Object runUnvalidated(MRunnable runnable);

	/**
	 * Runs the runnable instance with options. This method could be used to
	 * combine the effects of runSilent, runUnchecked, runUnvalidated and
	 * runWithNoSemProcs.
	 * 
	 * @see MRunOption
	 * 
	 * @param runnable
	 *            The runnable.
	 * @param options
	 *            The run options. This is a bit mask of values from
	 *            <code>MResourceOption</code>.
	 * 
	 * @deprecated To modify a {@link org.eclipse.emf.transaction.TXEditingDomain}'s
	 *    resource set, execute {@link org.eclipse.core.commands.Command} on its
	 *    command stack or execute
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation}s on the
	 *    operation history.  For special modification behaviours, provide
	 *    options in the
	 *    {@link org.eclipse.emf.transaction.TXCommandStack#execute(org.eclipse.emf.common.command.Command, Map)}
	 *    method or the
	 *    {@link org.eclipse.emf.workbench.AbstractEMFOperation#AbstractEMFOperation(TXEditingDomain, String, Map)}
	 *    constructor, as described in the {@link MRunOption} documentation.
	 */
	public abstract Object runWithOptions(MRunnable runnable, int options);

	/**
	 * Checks if one can read, i.e., a read or write action is in progress.
	 * 
	 * @return True if a read or write action is in progress.
	 * 
	 * @deprecated A {@link org.eclipse.emf.transaction.TXEditingDomain}'s
	 *    resource set can safely be read if it has an
	 *    {@link org.eclipse.emf.transaction.impl.InternalTXEditingDomain#getActiveTransaction() active transaction}.
	 */
	public abstract boolean canRead();

	/**
	 * Checks if one can write, i.e., a write or unchecked action is in
	 * progress.
	 * 
	 * @return True if a write or unchecked action is in progress.
	 * 
	 * @deprecated A {@link org.eclipse.emf.transaction.TXEditingDomain}'s
	 *    resource set can safely be modified if it has an
	 *    {@link org.eclipse.emf.transaction.impl.InternalTXEditingDomain#getActiveTransaction() active transaction}
	 *    and that transaction is not
	 *    {@link org.eclipse.emf.transaction.Transaction#isReadOnly() read-only}.
	 */
	public abstract boolean canWrite();

	/**
	 * Checks if a write action is in progress.
	 * 
	 * @return True if a write action is in progress.
	 * 
	 * @deprecated A {@link org.eclipse.emf.transaction.TXEditingDomain}'s
	 *    resource set can safely be modified if it has an
	 *    {@link org.eclipse.emf.transaction.impl.InternalTXEditingDomain#getActiveTransaction() active transaction}
	 *    and that transaction is not
	 *    {@link org.eclipse.emf.transaction.Transaction#isReadOnly() read-only}.
	 *    Moreover, that transaction is not "unchecked" if it does not have
	 *    the {@link org.eclipse.emf.transaction.Transaction#OPTION_UNPROTECTED}
	 *    option.
	 */
	public abstract boolean isWriteInProgress();

	/**
	 * Checks if an unchecked action is in progress.
	 * 
	 * @return True if an unchecked action is in progress.
	 * 
	 * @deprecated A {@link org.eclipse.emf.transaction.TXEditingDomain}'s
	 *    resource set can safely be modified if it has an
	 *    {@link org.eclipse.emf.transaction.impl.InternalTXEditingDomain#getActiveTransaction() active transaction}
	 *    and that transaction is not
	 *    {@link org.eclipse.emf.transaction.Transaction#isReadOnly() read-only}.
	 *    Moreover, that transaction is "unchecked" if it has the
	 *    {@link org.eclipse.emf.transaction.Transaction#OPTION_UNPROTECTED}
	 *    option.
	 */
	public abstract boolean isUncheckedInProgress();

	/**
	 * Checks if given notification is caused by undo.
	 * 
	 * @param notification
	 *            The notification.
	 * @return True if notification is caused by undo.
	 * 
	 * @deprecated Notifications from undo or redo should not be ignored by
	 *    {@link org.eclipse.emf.transaction.ResourceSetListener}s.  Trigger
	 *    listeners (implementing the
	 *    {@link org.eclipse.emf.transaction.ResourceSetListener#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)}
	 *    call-back) don't need to worry about the disction of undo and redo
	 *    notifications because they are not invoked when transactions are
	 *    undone or redone.
	 */
	public abstract boolean isUndoNotification(Notification notification);

	/**
	 * Checks if given notification is caused by redo.
	 * 
	 * @param notification
	 *            The notification.
	 * @return True if notification is caused by redo.
	 * 
	 * @deprecated Notifications from undo or redo should not be ignored by
	 *    {@link org.eclipse.emf.transaction.ResourceSetListener}s.  Trigger
	 *    listeners (implementing the
	 *    {@link org.eclipse.emf.transaction.ResourceSetListener#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)}
	 *    call-back) don't need to worry about the disction of undo and redo
	 *    notifications because they are not invoked when transactions are
	 *    undone or redone.
	 */
	public abstract boolean isRedoNotification(Notification notification);

	/**
	 * Sends notification to registered listeners.
	 * 
	 * @param notification
	 *            The notification.
	 *            
	 * @deprecated Use the {@link org.eclipse.emf.common.notify.Notifier#eNotify(Notification)}
	 *     API, instead.
	 */
	public abstract void sendNotification(Notification notification);

	/**
	 * Sends notification to registered listeners.
	 * 
	 * @param notifier
	 *            The notifier.
	 * @param eventType
	 *            The event type.
	 *            
	 * @deprecated Use the {@link org.eclipse.emf.common.notify.Notifier#eNotify(Notification)}
	 *     API, instead.
	 */
	public abstract void sendNotification(Object notifier, int eventType);

	/**
	 * returns a the map of Schema to Location passed file.
	 * 
	 * @param uri
	 *            the file uri
	 * @return a collection of required schema's location
	 */
	public abstract Map getSchemaToLocationMap(URI uri)
		throws IOException;

	/**
	 * returns a the map of Schema to Location passed file.
	 * 
	 * @param inputStream
	 *            input stream to use
	 * @return a collection of required schema's location
	 */
	public abstract Map getSchemaToLocationMap(InputStream inputStream)
		throws IOException;

	/**
	 * Sets the value of a path variable.
	 * 
	 * @param var
	 *            The variable name.
	 * @param val
	 *            The variable value (a URI, not just a file path).
	 */
	public abstract void setPathVariable(String var, String val);

	/**
	 * Removes a path variable.
	 * 
	 * @param var
	 *            The variable name.
	 */
	public abstract void removePathVariable(String var);

	/**
	 * Gets the value of a path variable.
	 * 
	 * @param var
	 *            The variable name.
	 * @return The variable value (a URI, not just a file path),
	 *    or an empty string if the variable is undefined.
	 */
	public abstract String getPathVariable(String var);

	/**
	 * Gets the imports of a resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The imports of the resource.
	 * 
	 * @deprecated Use the {@link org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil#getImports(Resource)}
	 *     API, instead.
	 */
	public abstract Collection getImports(Resource resource);

	/**
	 * Gets the exports of a resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The exports of the resource.
	 * 
	 * @deprecated Use the {@link org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil#getExports(Resource)}
	 *     API, instead.
	 */
	public abstract Collection getExports(Resource resource);

	/**
	 * Gets all imports of a resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The imports of the resource.
	 * 
	 * @deprecated Use the {@link org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil#getTransitiveImports(Resource)}
	 *     API, instead.
	 */
	public abstract Collection getAllImports(Resource resource);

	/**
	 * Gets all exports of a resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The exports of the resource.
	 * 
	 * @deprecated Use the {@link org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil#getTransitiveExports(Resource)}
	 *     API, instead.
	 */
	public abstract Collection getAllExports(Resource resource);

	/**
	 * Gets all resource sets.
	 * 
	 * @return The set of all resource sets.
	 *            
	 * @deprecated To find an editing domain by its resource set, use the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain.Factory#getEditingDomain(ResourceSet)}
	 *    API.  To find an editing domain by ID, use the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain.Registry#getEditingDomain(String)}
	 *    API.
	 */
	public static Set getResourceSets() {
		return MSLEditingDomain.getResourceSets();
	}

	/**
	 * Gets the editing domain of a given resource set.
	 * 
	 * @param resourceSet
	 *            The resource set.
	 * @return The editing domain.
	 *            
	 * @deprecated To find an editing domain by its resource set, use the
	 *    {@link org.eclipse.emf.transaction.TXEditingDomain.Factory#getEditingDomain(ResourceSet)}
	 *    API.
	 */
	public static MEditingDomain getEditingDomain(ResourceSet resourceSet) {
		return MSLEditingDomain.getEditingDomain(resourceSet);
	}
	
	/**
	 * Associates the specified resource set with an editing domain.
	 * 
	 * @param rset the resource set to associate with the <code>domain</code>
	 * @param domain the editing domain to associate with the resource set
	 *            
	 * @deprecated To create an editing domain on a specified resource set, use
	 *    the {@link org.eclipse.emf.transaction.TXEditingDomain.Factory#createEditingDomain(ResourceSet)}
	 *    API.  To replace the editing domain's resource set, it is necessary
	 *    to define a custom editing domain implementation.
	 */
	public static void setEditingDomain(ResourceSet rset, MEditingDomain domain) {
		
		MSLEditingDomain.setEditingDomain(rset, domain);
	}

	/**
	 * Gets the editing domain of a given resource.
	 * 
	 * @param resource
	 *            The resource.
	 * @return The editing domain.
	 *            
	 * @deprecated To find an editing domain by its resource set, use the
	 *    {@link org.eclipse.emf.transaction.util.TransactionUtil#getEditingDomain(Resource)}
	 *    API.
	 */
	public static MEditingDomain getEditingDomain(Resource resource) {
		return MSLEditingDomain.getEditingDomain(resource);
	}

	/**
	 * Creates re-gui-ided copies of the resources provided in the map. The
	 * resources must be loaded resources
	 * 
	 * @param resource2URI
	 *            a map with the resources to copy as keys and their new URIs as
	 *            values
	 * @throws IOException
	 */
	public abstract void copyResources(Map resource2URI)
		throws IOException;
}