/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.runtime.notation.validation;

import org.eclipse.gmf.runtime.notation.Image;

/**
 * A sample validator interface for {@link org.eclipse.gmf.runtime.notation.ImageBufferStyle}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ImageBufferStyleValidator {
	boolean validate();

	boolean validateImageBuffer(Image value);
}