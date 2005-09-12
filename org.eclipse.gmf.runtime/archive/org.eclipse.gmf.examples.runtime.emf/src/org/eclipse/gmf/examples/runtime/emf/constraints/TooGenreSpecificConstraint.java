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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;

import org.eclipse.emf.examples.library.Book;
import org.eclipse.emf.examples.library.BookCategory;
import org.eclipse.emf.examples.library.Writer;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;


/**
 * A simple constraint that warns if an author has written
 *  too many books in one genre.
 * 
 * @author cmcgee
 */
public class TooGenreSpecificConstraint
	extends AbstractModelConstraint {

	private static final int MAX = 5;
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	public IStatus validate(IValidationContext ctx) {
		Writer writer = (Writer)ctx.getTarget();
		
		int scifi = 0;
		int mystery = 0;
		int bio = 0;
		
		Collection books = writer.getBooks();
		for (Iterator i = books.iterator(); i.hasNext();) {
			Book b = (Book)i.next();
			
			if (b.getCategory() == BookCategory.SCIENCE_FICTION_LITERAL) {
				scifi++;

				if (scifi > MAX) {
					return ctx.createFailureStatus(new Object[] {
						EObjectUtil.getName(writer),
						BookCategory.SCIENCE_FICTION_LITERAL.getName()});
				}
			} else if (b.getCategory() == BookCategory.MYSTERY_LITERAL) {
				mystery++;

				if (mystery > MAX) {
					return ctx.createFailureStatus(new Object[] {
						EObjectUtil.getName(writer),
						BookCategory.MYSTERY_LITERAL.getName()});
				}
			} else if (b.getCategory() == BookCategory.BIOGRAPHY_LITERAL) {
				bio++;

				if (bio > MAX) {
					return ctx.createFailureStatus(new Object[] {
						EObjectUtil.getName(writer),
						BookCategory.BIOGRAPHY_LITERAL.getName()});
				}
			}
		}
		
		return ctx.createSuccessStatus();
	}
}
