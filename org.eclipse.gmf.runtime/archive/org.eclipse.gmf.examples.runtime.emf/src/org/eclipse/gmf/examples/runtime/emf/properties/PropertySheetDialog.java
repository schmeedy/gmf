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

package org.eclipse.gmf.examples.runtime.emf.properties;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.gmf.examples.runtime.emf.internal.l10n.MSLExampleMessages;
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
		
		newShell.setText(MSLExampleMessages.PropertySheetDialog_title);
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
		reset.setText(MSLExampleMessages.PropertySheetDialog_resetProperty);
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