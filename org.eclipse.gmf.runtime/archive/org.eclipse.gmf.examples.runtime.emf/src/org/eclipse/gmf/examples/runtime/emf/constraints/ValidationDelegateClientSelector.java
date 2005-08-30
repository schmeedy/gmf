/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
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
