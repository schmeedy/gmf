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

package org.eclipse.gmf.runtime.emf.core.internal.resources;

import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.SAXWrapper;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Specialized loader that constructs the MSL's custom handler to decode
 * the fragment portions of HREFs/
 *
 * @author Christian W. Damus (cdamus)
 */
public class MSLLoad extends GMFLoad {

	public MSLLoad(XMLHelper helper) {
		super(helper);
	}

	/**
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl#makeDefaultHandler()
	 */
	protected DefaultHandler makeDefaultHandler() {
		return new SAXWrapper(new MSLHandler(resource, helper, options));
	}
}
