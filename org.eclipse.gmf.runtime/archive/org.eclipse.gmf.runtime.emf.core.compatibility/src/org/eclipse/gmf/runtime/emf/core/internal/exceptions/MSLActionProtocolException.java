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

package org.eclipse.gmf.runtime.emf.core.internal.exceptions;

import org.eclipse.gmf.runtime.emf.core.exceptions.MSLRuntimeException;

/**
 * Exception thrown when the action protocol is violated.
 * 
 * @author rafikj
 * 
 * @deprecated The {@link org.eclipse.emf.transaction.TXEditingDomain} enforces
 *     the usage of read/write transactions for modifying its resource set
 *     by throwing {@link IllegalStateException}s (except when the editing
 *     domain implements the MSL Compatibility Layer MEditingDomain APi, in
 *     which case it still throws this exception). 
 */
public class MSLActionProtocolException
	extends MSLRuntimeException {

	private static final long serialVersionUID = 6373560684111580870L;

	/**
	 * Initializes me without any details.
	 */
	public MSLActionProtocolException() {
		super();
	}

	/**
	 * Initializes me with a useful message.
	 * 
	 * @param message
	 *            a message
	 */
	public MSLActionProtocolException(String message) {
		super(message);
	}

	/**
	 * Initializes me with a cause.
	 * 
	 * @param cause
	 *            the cause of this exception.
	 */
	public MSLActionProtocolException(Throwable cause) {
		super(null == cause ? null
			: cause.toString(), cause);
	}
}