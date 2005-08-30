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

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.examples.library.Library;
import org.eclipse.emf.examples.library.RMPLibraryPackage;
import org.eclipse.emf.examples.library.impl.RMPLibraryFactoryImpl;


/**
 * Example of a custom {@link org.eclipse.emf.ecore.EFactory} implementation
 * for auto-loading the sub-units of a logical resource, for the RMP Library
 * example metamodel.
 */
public class LoadingRMPLibraryFactoryImpl
	extends RMPLibraryFactoryImpl {

	/**
	 * Initializes me.
	 */
	public LoadingRMPLibraryFactoryImpl() {
		super();
	}

	/**
	 * Creates {@link Library} implementations that automatically load their
	 * contained {@link Library#getBranches() branches}.
	 */
	public Library createLibrary() {
		return new LoadingLibraryImpl();
	}
	
	public EPackage getEPackage() {
		// must appear to belong to the RMP Library Package.  Note that we
		//   cannot assign our EPackage using the setEPackage(...) method
		//   because that would inverse-add us to the EPackage, which is
		//   not good
		return RMPLibraryPackage.eINSTANCE;
	}

}
