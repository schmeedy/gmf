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

package org.eclipse.gmf.runtime.emf.core.internal.domain;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;


/**
 * Factory registered in the plugin.xml to provide the singleton editing domain
 * instance.
 *
 * @author Christian W. Damus (cdamus)
 */
public class MSLEditingDomainFactory
	extends GMFEditingDomainFactory {

	public TransactionalEditingDomain createEditingDomain() {
		return MEditingDomain.INSTANCE;
	}
}
