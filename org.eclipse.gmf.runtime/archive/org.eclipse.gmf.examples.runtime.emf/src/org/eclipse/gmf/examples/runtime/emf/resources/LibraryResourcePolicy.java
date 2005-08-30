/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */

package org.eclipse.gmf.examples.runtime.emf.resources;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

import org.eclipse.emf.examples.library.Library;
import org.eclipse.gmf.runtime.emf.core.resources.AbstractLogicalResourcePolicy;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResource;


/**
 * Logical resource management policy implementation for the example library
 * metamodel.
 */
public class LibraryResourcePolicy
	extends AbstractLogicalResourcePolicy {

	/**
	 * Initializes me.
	 */
	public LibraryResourcePolicy() {
		super();
	}

	/**
	 * Only libraries may be stored in separate physical units.
	 */
	public boolean canSeparate(ILogicalResource resource, EObject eObject) {
		return eObject instanceof Library;
	}
	
	/**
	 * Suggests a URI for the new library sub-resource.
	 * <p>
	 * <b>Note</b> that this URI convention is not intended as a common design
	 * practice.  It is just a sample.
	 * </p>
	 */
	public URI preSeparate(ILogicalResource resource, EObject eObject, URI uri)
		throws CannotSeparateException {
		// construct a sensibly unique URI for the new
		//    controlled unit by constructing a path based on library names
		//    or, where not available, IDs
		URI result = resource.getURI().trimFileExtension();
		
		result = appendSegments(result, resource, (Library) eObject);
		
		result = result.appendFileExtension(resource.getURI().fileExtension());
		
		return result;
	}
	
	/**
	 * Appends segments to a URI corresponding to the nesting of libraries.
	 * 
	 * @param uri a uri to append segments to
	 * @param resource the resource containing the <code>library</code>
	 * @param library a library whose "name path" is to be appended to the URI
	 * @return the appended URI
	 */
	private URI appendSegments(URI uri, Resource resource, Library library) {
		Library parent = library.getParentBranch();
		if (parent != null) {
			uri = appendSegments(uri, resource, parent);
		}
		
		String segment = library.getName();
		if ((segment == null) || (segment.length() == 0)) {
			segment = ((XMIResource) resource).getID(library);
		}
		
		return uri.appendSegment(segment);
	}
}
