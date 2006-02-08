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

/**
 * @author rafikj
 * 
 * A universal listener listens to changes that span all the currently present
 * resource sets.
 * 
 * @deprecated Attach a {@link org.eclipse.emf.transaction.ResourceSetListener}
 *     to the {@link org.eclipse.emf.transaction.TXEditingDomain}, instead.
 *     Although the EMF-TX API does not support an absolutely universal
 *     listener, a listener can be automatically attached to all registered
 *     (sharable) editing domains by implementing an extension on the
 *     <code>org.eclipse.emf.transaction.listeners</code> point. 
 */
public abstract class MUniversalListener
	extends MListener {

	/**
	 * Constructor.
	 */
	public MUniversalListener() {
		super(null, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param filter
	 *            The filter.
	 */
	public MUniversalListener(MFilter filter) {
		super(null, filter);
	}
}