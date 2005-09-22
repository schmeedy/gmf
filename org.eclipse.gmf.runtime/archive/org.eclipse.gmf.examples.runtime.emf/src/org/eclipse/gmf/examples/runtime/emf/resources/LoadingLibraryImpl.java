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


package org.eclipse.gmf.examples.runtime.emf.resources;

import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.impl.LibraryImpl;
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
			Library.class, this, EXTLibraryPackage.LIBRARY__BRANCHES,
			EXTLibraryPackage.LIBRARY__PARENT_BRANCH);
	}

}
