/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
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
