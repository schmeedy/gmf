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

import org.eclipse.emf.examples.library.Library;
import org.eclipse.emf.examples.library.RMPLibraryPackage;
import org.eclipse.emf.examples.library.impl.LibraryImpl;
import org.eclipse.gmf.runtime.emf.core.util.EObjectContainmentWithInverseLoadingEList;


/**
 * Example of a custom implementation for an {@link org.eclipse.emf.ecore.EClass}
 * that needs to support auto-loading of sub-unit resource in one or more of
 * its containment features.
 */
class LoadingLibraryImpl
	extends LibraryImpl {

	/**
	 * Initializes me.
	 */
	public LoadingLibraryImpl() {
		super();
		
		// use the custom containment-with-inverse list to perform automatic
		//   loading of contained elements in this feature.  This pre-empts
		//   the superclass's lazy initialization of this list
		branches = new EObjectContainmentWithInverseLoadingEList(
			Library.class, this, RMPLibraryPackage.LIBRARY__BRANCHES,
			RMPLibraryPackage.LIBRARY__PARENT_BRANCH);
	}

}
