/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.examples.runtime.emf.dialogs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListDialog;

import org.eclipse.gmf.examples.runtime.emf.MSLExamplePlugin;


/**
 * A general dialog that presents validation failures from the IStatus
 *  returned by the validation framework.
 * 
 * @author cmcgee
 */
public class ValidationErrorDialog extends ListDialog {
	IStatus statusObject;

	public ValidationErrorDialog(Shell parent, String title, IStatus status) {
		super(parent);
		
		this.statusObject = status;
		
		setInput(status);
		setTitle(title);
		setBlockOnOpen(true);
		setMessage(MSLExamplePlugin
			.getResourceString("ValidationErrorDialog.errorMessage")); //$NON-NLS-1$
		
		setContentProvider(new IStructuredContentProvider() {
			public void dispose() {
				// nothing to dispose
			}

			public Object[] getElements(Object inputElement) {
				if (statusObject != null && statusObject.isMultiStatus() && statusObject == inputElement) {
					return statusObject.getChildren();
				} else if (statusObject != null && statusObject == inputElement) {
					return new Object[] {statusObject};
				}
				return new Object[0];
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				// Do nothing.
			}
		});
		
		setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				if (element instanceof IStatus) {
					String prefix = new String();
					int severity = ((IStatus)element).getSeverity();
					switch(severity) {
						case IStatus.ERROR:
							prefix = MSLExamplePlugin.getResourceString("BatchValidationDelegate.errorPrefix"); //$NON-NLS-1$
							break;
						case IStatus.WARNING:
							prefix = MSLExamplePlugin.getResourceString("BatchValidationDelegate.warningPrefix"); //$NON-NLS-1$
							break;
					}
					return prefix+((IStatus)element).getMessage();
				}
				return null;
			}
		});
	}
	
	protected void setShellStyle(int newShellStyle) {
		super.setShellStyle(newShellStyle | SWT.RESIZE);
	}
}