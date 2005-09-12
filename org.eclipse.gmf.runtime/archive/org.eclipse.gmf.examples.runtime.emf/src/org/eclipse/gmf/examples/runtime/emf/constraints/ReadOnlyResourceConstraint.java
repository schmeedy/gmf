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
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;


/**
 * A simple constraint that ensures that any modifications that are
 *  made to EObjects are made in resources that are modifiable.
 * 
 * @author cmcgee
 */
public class ReadOnlyResourceConstraint
	extends AbstractModelConstraint {

	public IStatus validate(IValidationContext ctx) {
		EObject target = ctx.getTarget();
		
		if (ctx.getEventType() != EMFEventType.NULL &&
				!ResourceUtil.isModifiable(target.eResource())) {
			return ctx.createFailureStatus(new Object[] {EObjectUtil.getName(target)});
		}
		
		return ctx.createSuccessStatus();
	}
}
