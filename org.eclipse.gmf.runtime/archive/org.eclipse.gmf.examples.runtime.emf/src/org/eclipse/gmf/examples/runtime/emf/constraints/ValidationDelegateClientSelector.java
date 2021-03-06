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

package org.eclipse.gmf.examples.runtime.emf.constraints;

import org.eclipse.emf.validation.model.IClientSelector;


/**
 * An EMF validation client selector class with the simple property that
 *  the MSL example plugin client context occurs when the {@link #running}
 *  flag is set to true. 
 * 
 * @author cmcgee
 */
public class ValidationDelegateClientSelector
	implements IClientSelector {

	public static boolean running = false;
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.model.IClientSelector#selects(java.lang.Object)
	 */
	public boolean selects(Object object) {
		return running;
	}
}
