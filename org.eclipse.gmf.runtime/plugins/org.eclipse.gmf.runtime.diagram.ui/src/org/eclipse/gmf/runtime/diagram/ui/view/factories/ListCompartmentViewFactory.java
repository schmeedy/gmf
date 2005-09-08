/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.diagram.ui.view.factories; 

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This is the bas factory class for all  List compartment views, it will 
 * create the view and decorate it using the default decorations
 * you can subclass it to add more decorations, or customize the 
 * way it looks, like adding new style
 * @see #createView(IAdaptable, View, String, int, boolean, String)
 * @see #decorateView(View, View, IAdaptable, String, int, boolean)
 * @see #createStyles(View)
 * @author mmostafa
 * 
 */
public class ListCompartmentViewFactory extends ResizableCompartmentViewFactory {


	/**
	 * this method is called by @link #createView(IAdaptable, View, String, int, boolean) to 
	 * create the styles for the view that will be created, you can override this 
	 * method in you factory sub class to provide additional styles
	 * @return a list of style for the newly created view or an empty list if none (do not return null)
	 */
	protected List createStyles(View view) {
		List styles = super.createStyles(view);
		styles.add(NotationFactory.eINSTANCE.createSortingStyle());
		styles.add(NotationFactory.eINSTANCE.createFilteringStyle());
		return styles;
	}
}