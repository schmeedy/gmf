/******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core.internal.domain;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.impl.FilterManager;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.emf.core.internal.l10n.MSLCoreMessages;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLDebugOptions;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLPlugin;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLStatusCodes;


/**
 * An extension of the default transactional editing domain implementation
 * to which the {@link MSLEditingDomain} delegates the transaction API.
 *
 * @author Christian W. Damus (cdamus)
 */
class MSLTXEditingDomain
	extends TransactionalEditingDomainImpl {
	
	private List pendingPostcommits;

	/**
	 * Initializes me with my adapter factory, command stack, and resource set.
	 * 
	 * @param adapterFactory my adapter factory
	 * @param stack my command stack
	 * @param resourceSet my resource set
	 */
	MSLTXEditingDomain(AdapterFactory adapterFactory,
			TransactionalCommandStack stack, ResourceSet resourceSet) {
		super(adapterFactory, stack, resourceSet);
	}
	
	public InternalTransaction startTransaction(boolean readOnly, Map options)
		throws InterruptedException {
		
		MSLTransactionImpl result = new MSLTransactionImpl(
				this, readOnly, options);
		
		result.start();
		
		return result;
	}
	
	public void activate(InternalTransaction tx)
		throws InterruptedException {
		
		if (!(tx.isReadOnly() ||
				Boolean.TRUE.equals(tx.getOptions().get(Transaction.OPTION_UNPROTECTED)))) {
			
			if (tx instanceof MSLTransaction) {
				((MSLTransaction) tx).setReadTransactionsToRewind(
					unwindReadTransactions());
			}
		}
		
		super.activate(tx);
	}
	
	public void deactivate(InternalTransaction tx) {
		super.deactivate(tx);
		
		if (tx instanceof MSLTransaction) {
			rewindReadTransactions(
				((MSLTransaction) tx).getReadTransactionsToRewind());
		}
	}
	
	/**
	 * Unwinds the read-only transactions that are currently active, if any.
	 * 
	 * @return the number (zero or more) of transactions that were closed.
	 *         These should be restored later by a call to
	 *         {@link #rewindReadTransactions(int)}
	 * 
	 * @see #rewindReadTransactions(int)
	 */
	private int unwindReadTransactions() {
		int result = 0;

		final Thread current = Thread.currentThread();
		Transaction tx = getActiveTransaction();
		
		while ((tx != null) && tx.isReadOnly() && (tx.getOwner() == current)) {
			result++;
			
			try {
				tx.commit();
			} catch (RollbackException e) {
				// ignore rollback
				Trace.catching(
					MSLPlugin.getDefault(),
					MSLDebugOptions.EXCEPTIONS_CATCHING,
					MSLTXEditingDomain.class,
					"unwindReadTransactions", //$NON-NLS-1$
					e);
				MSLPlugin.getDefault().getLog().log(e.getStatus());
			}
			
			tx = getActiveTransaction();
		}
		
		return result;
	}

	/**
	 * Re-opens read-only transactions actions previously closed by a call to
	 * {@link #unwindReadTransactions()}
	 * 
	 * @param count the number (zero or more) of transactions to restore
	 * 
	 * @see #unwindReadTransactions()
	 */
	private void rewindReadTransactions(int count) {
		for (int i = 0; i < count; i++) {
			try {
				startTransaction(true, null);
			} catch (InterruptedException e) {
				// cannot afford to be interrupted, because we absolutely must
				//    re-open all of the transactions that we closed originally.
				//    Decrement counter because we did not succeed in opening a
				//    transaction
				i--;
				
				Log.log(
					MSLPlugin.getDefault(),
					IStatus.WARNING,
					MSLStatusCodes.IGNORED_EXCEPTION_WARNING,
					MSLCoreMessages.ignored_exception,
					e);
			}
		}
	}
	
	public Object runExclusive(Runnable read)
		throws InterruptedException {
		
		Transaction tx = startTransaction(true, null);
		
		final RunnableWithResult rwr = (read instanceof RunnableWithResult)?
			(RunnableWithResult) read : null;
		
		try {
			read.run();
		} finally {
			// MSL allows upgrading read actions to write actions by
			//   unwinding the read-transaction stack.  So, we need to
			//   check the active transaction again in case that has
			//   been done during execution of this runnable
			tx = getActiveTransaction();
			
			if ((tx != null) && (tx.isActive())) {
				// commit the transaction now
				try {
					tx.commit();
					
					if (rwr != null) {
						rwr.setStatus(Status.OK_STATUS);
					}
				} catch (RollbackException e) {
					Trace.catching(
							MSLPlugin.getDefault(),
							MSLDebugOptions.EXCEPTIONS_CATCHING,
							MSLTXEditingDomain.class,
							"runExclusive", //$NON-NLS-1$
							e);
					Log.log(MSLPlugin.getDefault(), new MultiStatus(
						MSLPlugin.getPluginId(),
						MSLStatusCodes.IGNORED_EXCEPTION_WARNING,
						new IStatus[] {e.getStatus()},
						e.getLocalizedMessage(),
						null));
					
					if (rwr != null) {
						rwr.setStatus(e.getStatus());
					}
				}
			}
		}
		
		return (rwr != null)? rwr.getResult() : null;
	}
	
	/**
	 * Performs post-commit processing of the specified transaction.  This
	 * consists of broadcasting the post-commit events to my resource set
	 * listeners.
	 * 
	 * @param tx the transaction that has committed
	 */
	protected void postcommit(final InternalTransaction tx) {
		List notifications = getValidator().getNotificationsForPostcommit(tx);
		if ((notifications == null) || notifications.isEmpty()) {
			return;
		}
		
		boolean rootPostcommit = (pendingPostcommits == null);
		if (rootPostcommit) {
			pendingPostcommits = new java.util.ArrayList();
		}
		
		pendingPostcommits.add(notifications);
		
		// dispose the validator now because starting the read-only transaction
		//    below will replace it with a new validator
		getValidator().dispose();
		
		if (rootPostcommit) {
			final ResourceSetListener[] listeners = getPostcommitListeners();
			
			while (!pendingPostcommits.isEmpty()) {
				final List batch = (List) pendingPostcommits.remove(0);
				
				try {
					runExclusive(new Runnable() {
						public void run() {
							for (int i = 0; i < listeners.length; i++) {
								try {
									List filtered = FilterManager.getInstance().select(
											batch,
											listeners[i].getFilter());
									
									if (!filtered.isEmpty()) {
										listeners[i].resourceSetChanged(
												new ResourceSetChangeEvent(
														MSLTXEditingDomain.this,
														tx,
														filtered));
									}
								} catch (Exception e) {
									Trace.catching(
										MSLPlugin.getDefault(),
										MSLDebugOptions.EXCEPTIONS_CATCHING,
										MSLTXEditingDomain.class,
										"postcommit", e); //$NON-NLS-1$
									IStatus status = new Status(
										IStatus.ERROR,
										MSLPlugin.getPluginId(),
										MSLStatusCodes.POSTCOMMIT_FAILED,
										MSLCoreMessages.postcommitFailed,
										e);
									Log.log(MSLPlugin.getDefault(), status);
								}
							}
						}});
				} catch (InterruptedException e) {
					Trace.catching(
						MSLPlugin.getDefault(),
						MSLDebugOptions.EXCEPTIONS_CATCHING,
						MSLTXEditingDomain.class,
						"postcommit", e); //$NON-NLS-1$
					IStatus status = new Status(
						IStatus.ERROR,
						MSLPlugin.getPluginId(),
						MSLStatusCodes.POSTCOMMIT_INTERRUPTED,
						MSLCoreMessages.postcommitInterrupted,
						e);
					Log.log(MSLPlugin.getDefault(), status);
				}
			}
			
			pendingPostcommits = null;
		}
	}
}
