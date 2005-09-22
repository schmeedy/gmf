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

package org.eclipse.gmf.examples.runtime.emf.constraints;

import org.eclipse.core.runtime.IStatus;

import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;


/**
 * A simple constraint that produces an error if a library will blow its
 *  budget because of they are keeping too many writers on staff.
 * 
 * @author cmcgee
 */
public class TooManyWritersConstraint
	extends AbstractModelConstraint {

	public IStatus validate(IValidationContext ctx) {
		Library l = (Library)ctx.getTarget();
		
		if (l.getWriters().size() > 5) {
			return ctx.createFailureStatus(new Object[] {EObjectUtil.getName(l)});
		}
		
		return ctx.createSuccessStatus();
	}

}
