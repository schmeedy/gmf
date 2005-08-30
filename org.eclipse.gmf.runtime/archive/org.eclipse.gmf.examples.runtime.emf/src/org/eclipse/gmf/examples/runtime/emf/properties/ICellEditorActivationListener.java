/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2003, 2004.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.examples.runtime.emf.properties;

import org.eclipse.jface.viewers.CellEditor;
/**
 * A listener which is notified when a cell editor is 
 * activated/deactivated
 */
public interface ICellEditorActivationListener {
	/**
	 * Notifies that the cell editor has been activated
	 *
	 * @param cellEditor the cell editor which has been activated
	 */
	public void cellEditorActivated(CellEditor cellEditor);
	
	/**
	 * Notifies that the cell editor has been deactivated
	 *
	 * @param cellEditor the cell editor which has been deactivated
	 */
	public void cellEditorDeactivated(CellEditor cellEditor);
}