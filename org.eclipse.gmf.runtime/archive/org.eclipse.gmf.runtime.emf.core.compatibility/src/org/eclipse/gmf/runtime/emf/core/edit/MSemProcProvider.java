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

import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;

/**
 * <p>
 * MSL SemProc Providers are used to provide model modifications in response to events.
 * To define a new SemProc, one
 * would subclass <code>MSemProcPRovider</code> and implement the abstract method
 * <code>onEvent</code>.
 * </p>
 * <p>
 * A SemProc provider can have only one filter associated with it. For the provider to
 * start providing for semproc, one must attach a filter to the provider and call
 * <code>startListening</code>.
 * </p>
 * 
 * @author mgoyal
 * 
 * @deprecated Attach a {@link org.eclipse.emf.transaction.ResourceSetListener}
 *     to the {@link org.eclipse.emf.transaction.TXEditingDomain}, instead.
 *     The most common usage of <code>MSemProcProvider</code> is for injecting
 *     further changes into an undo interval; implement the
 *     {@link org.eclipse.emf.transaction.ResourceSetListener#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)}
 *     callback to add trigger commands to the transaction before it commits. 
 */
public abstract class MSemProcProvider extends MListener {

	/**
	 * Constructor. Constructs a semproc provider that provides 
	 * on events associated with the MSL default editing domain.
	 */
	public MSemProcProvider() {
		super(MEditingDomain.INSTANCE);
	}

	/**
	 * Constructor. Constructs a semproc provider that provides 
	 * on events associated with the MSL default editing domain
	 * and attaches a filter to the SemProc Provider.
	 * 
	 * <code>filter</code> must not be null.
	 * 
	 * @param filter The filter.
	 */
	public MSemProcProvider(MFilter filter) {
		super(MEditingDomain.INSTANCE, filter);
	}

	/**
	 * Constructor. Constructs a semproc provider that provides
	 * on events associated with the given editing domain. 
	 * <code>domain</code> must not be null.
	 */
	public MSemProcProvider(MEditingDomain domain) {
		super(domain);
		assert domain != null;
	}

	/**
	 * Constructor. Constructs a semproc provider that provides 
	 * on events associated with the given editing domain and 
	 * attaches a filter to the listener.
	 * 
	 * <code>filter</code> must not be null. 
	 * <code>domain</code> must not be null.
	 * 
	 * @param filter The filter.
	 */
	public MSemProcProvider(MEditingDomain domain, MFilter filter) {
		super(domain, filter);
		assert domain != null;
		assert filter != null;
	}

	/**
	 * Starts listening.
	 */
	public void startListening() {
		((MSLEditingDomain) domain).getEventBroker().addSemProcProvider(this);
	}

	/**
	 * Stops listening.
	 */
	public void stopListening() {
		((MSLEditingDomain) domain).getEventBroker().removeSemProcProvider(this);
	}
}
