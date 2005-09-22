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

import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.impl.EXTLibraryFactoryImpl;


/**
 * Example of a custom {@link org.eclipse.emf.ecore.EFactory} implementation
 * for auto-loading the sub-units of a logical resource, for the EXT Library
 * example metamodel.
 */
public class LoadingEXTLibraryFactoryImpl
	extends EXTLibraryFactoryImpl {

	/**
	 * Initializes me.
	 */
	public LoadingEXTLibraryFactoryImpl() {
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
		// must appear to belong to the EXT Library Package.  Note that we
		//   cannot assign our EPackage using the setEPackage(...) method
		//   because that would inverse-add us to the EPackage, which is
		//   not good
		return EXTLibraryPackage.eINSTANCE;
	}

}
