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

import org.eclipse.emf.examples.library.Library;
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
