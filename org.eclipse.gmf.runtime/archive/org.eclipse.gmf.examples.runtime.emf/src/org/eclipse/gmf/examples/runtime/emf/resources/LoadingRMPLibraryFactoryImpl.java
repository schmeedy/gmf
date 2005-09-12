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
