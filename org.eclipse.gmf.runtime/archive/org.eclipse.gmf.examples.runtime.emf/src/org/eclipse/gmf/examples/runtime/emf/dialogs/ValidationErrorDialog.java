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

package org.eclipse.gmf.examples.runtime.emf.dialogs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmf.examples.runtime.emf.internal.l10n.MSLExampleMessages;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListDialog;


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
		setMessage(MSLExampleMessages.ValidationErrorDialog_errorMessage);
		
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
							prefix = MSLExampleMessages.BatchValidationDelegate_errorPrefix;
							break;
						case IStatus.WARNING:
							prefix = MSLExampleMessages.BatchValidationDelegate_warningPrefix;
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