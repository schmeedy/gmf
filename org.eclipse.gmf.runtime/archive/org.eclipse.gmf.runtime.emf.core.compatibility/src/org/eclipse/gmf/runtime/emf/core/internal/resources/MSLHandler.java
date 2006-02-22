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

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * Specialized handler for decoding HREF fragments when constructing proxies, to
 * balance the encoding done by the helper.
 *
 * @author Christian W. Damus (cdamus)
 */
class MSLHandler extends GMFHandler {

	public MSLHandler(XMLResource xmiResource, XMLHelper helper, Map options) {
		super(xmiResource, helper, options);
	}

	protected void handleProxy(InternalEObject proxy, String uriLiteral) {
		// decode the URI fragment up to the "fragment query" portion, if any.
		//   Don't decode the query because we didn't encode it when we saved
		int fragmentStart = uriLiteral.indexOf('#') + 1;
		
		if (fragmentStart > 0) {  // == 0 means index of '#' was -1 (not found)
			int length = uriLiteral.length();
			int fragmentEnd = uriLiteral.indexOf('?', fragmentStart);
			
			if (fragmentEnd < fragmentStart) {
				fragmentEnd = length;
			}
			
			String fragment = uriLiteral.substring(fragmentStart, fragmentEnd);
			
			StringBuffer buf = new StringBuffer();
			buf.append(uriLiteral.substring(0, fragmentStart));
			buf.append(URI.decode(fragment));
			buf.append(uriLiteral.substring(fragmentEnd, length));
			uriLiteral = buf.toString();
		}
		
		super.handleProxy(proxy, uriLiteral);
	}
}
