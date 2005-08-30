/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.examples.runtime.emf.properties;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetEntry;

import org.eclipse.gmf.examples.runtime.emf.MSLExamplePlugin;


public class PropertySheetDialog
	extends Dialog {

	AdapterFactoryContentProvider contentProvider;
	Object context;
	
	public PropertySheetDialog(Shell parentShell, AdapterFactoryContentProvider contentProvider, Object context) {
		super(parentShell);
		
		setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
		
		this.contentProvider = contentProvider;
		this.context = context;
	}
	
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		
		newShell.setText(MSLExamplePlugin.getResourceString("PropertySheetDialog.title")); //$NON-NLS-1$
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		 
		/* Create a property source provider */
		IPropertySourceProvider propertySourceProvider = contentProvider;

		/* Create a new viewer */
		final PropertySheetViewer propertyViewer = new PropertySheetViewer(composite, // the parent is the SWT composite
				SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL,
				true); //true is for editing

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		
		propertyViewer.getControl().setLayoutData(gridData);
		
		/* Create the property sheet entry */
		PropertySheetEntry root = new PropertySheetEntry();

		/* Set the property source provider */
		root.setPropertySourceProvider(propertySourceProvider);

		/* Set the root entry for the viewer */
		propertyViewer.setRootEntry(root);
		
		propertyViewer.setInput(new Object[] {context});
		
		parent.setSize(400,800);
		
		final Button reset = new Button(composite,SWT.LEFT);
		reset.setText(MSLExamplePlugin.getResourceString("PropertySheetDialog.resetProperty")); //$NON-NLS-1$
		reset.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				propertyViewer.resetProperties();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// Not needed
			}
		});
		reset.setEnabled(false);
		
		propertyViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
	            // see if item is editable
	            reset.setEnabled(propertyViewer.getActiveCellEditor() != null);
			}
		});
		
		 
		return composite;
	}
}