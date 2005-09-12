/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.examples.runtime.emf.metamodel;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.emf.examples.library.RMPLibraryPackage;
import org.eclipse.gmf.runtime.emf.core.services.metamodel.GetMetamodelSupportOperation;
import org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupport;
import org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupportProvider;

/**
 * Implementation of meta-model provider for the RMP library meta-model.
 * 
 * @see org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupportProvider
 */
public class LibraryMetaModelProvider
	extends AbstractProvider
	implements IMetamodelSupportProvider {

	/* Attribute for the meta model support object */
	private IMetamodelSupport metaModelSupport = null;

	/*
	 * @see org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupportProvider#getMetamodelSupport(org.eclipse.emf.ecore.EPackage)
	 */
	public IMetamodelSupport getMetamodelSupport(EPackage ePackage) {
		if (metaModelSupport == null) {
			metaModelSupport = new LibraryMetaModel();
		}

		return metaModelSupport;
	}

	/*
	 * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetMetamodelSupportOperation) {
			GetMetamodelSupportOperation getMetaModelOperation = (GetMetamodelSupportOperation) operation;

			EPackage ePackage = getMetaModelOperation.getEPackage();

			return (ePackage == RMPLibraryPackage.eINSTANCE);
		}

		return false;
	}
}