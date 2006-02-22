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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.emf.core.internal.util.EMFCoreConstants;
import org.eclipse.gmf.runtime.emf.core.internal.util.Util;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;

/**
 * Custom helper that encodes the fragments of cross-referenced objects upon
 * saving the resource, so that the referenced resource doesn't always have
 * to encode and decode structured object identifiers.
 *
 * @author Christian W. Damus (cdamus)
 */
class MSLHelper extends GMFHelper {

	public MSLHelper(XMLResource resource) {
		super(resource);
	}

	/**
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl#getHREF(org.eclipse.emf.ecore.resource.Resource,
	 *      org.eclipse.emf.ecore.EObject)
	 */
	protected URI getHREF(Resource otherResource, EObject obj) {
		URI result;
		
		if (otherResource instanceof GMFResource) {
			// no need to escape because the fragment is a base-64 number
			result = otherResource.getURI().appendFragment(
					getURIFragment(otherResource, obj));
			
			String qName = EMFCoreUtil.getQualifiedName(obj, true);

			if (qName.length() > 0) {
				// we only want fragment queries in HREFs, not in IDREFs, so
				//   compute this here according to the superclass convention
				//   rather than override the getURIFragmentQuery() method
				StringBuffer buffer = new StringBuffer(result.toString());

				buffer.append(EMFCoreConstants.FRAGMENT_SEPARATOR);
				buffer.append(Util.encodeQualifiedName(qName));
				buffer.append(EMFCoreConstants.FRAGMENT_SEPARATOR);

				return URI.createURI(buffer.toString());
			}
		} else {
			// encode the URI fragment.  Re-escape any %s in escape sequences
			//    because we will always decode in the handler when loading
			result = otherResource.getURI().appendFragment(
					URI.encodeFragment(getURIFragment(otherResource, obj), false));
		}

		return result;
	}
}
