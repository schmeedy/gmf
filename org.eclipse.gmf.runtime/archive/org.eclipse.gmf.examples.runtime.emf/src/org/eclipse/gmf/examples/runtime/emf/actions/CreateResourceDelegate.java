/******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.examples.runtime.emf.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionDelegate2;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import org.eclipse.emf.examples.library.RMPLibraryPackage;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.examples.runtime.emf.MSLExamplePlugin;
import org.eclipse.gmf.examples.runtime.emf.editor.MSLLibraryEditor;

/**
 * An action delegate whose purpose is to allow the user to create a new
 *  resource in the resource set of the active MSL Library editor.
 * 
 * @author cmcgee
 */
public class CreateResourceDelegate
	implements IEditorActionDelegate, IActionDelegate2 {

	private static final String DEFAULT_RESOURCE_NAME = "My1.rmplibrary"; //$NON-NLS-1$
	
	/**
	 * Error message to display when an exception occured
	 */
	protected static final String MESSAGE_EXCEPTION = MSLExamplePlugin.getResourceString("message.exception"); //$NON-NLS-1$

	/**
	 * The shell this action is hosted in
	 */
	protected Shell shell = null;

	/**
	 * The active editor
	 */
	protected MSLLibraryEditor editor = null;

	/**
	 * Selected EObjects
	 */
	protected Collection selectedResources = null;

	public static class CreateResourceDialog
		extends Dialog {

		public static int CONTROL_OFFSET = 10;

		protected EditingDomain domain;

		protected Text resourceURIField;

		protected String resourceURIs;

		public CreateResourceDialog(Shell parent) {
			this(parent, null);
		}

		public CreateResourceDialog(Shell parent, EditingDomain domain) {
			super(parent);
			setShellStyle(getShellStyle() | SWT.MAX | SWT.RESIZE);
			this.domain = domain;
		}

		protected void configureShell(Shell shell) {
			super.configureShell(shell);
			shell.setText(MSLExamplePlugin.getResourceString("CreateResourceDelegate.title")); //$NON-NLS-1$
		}

		protected Control createDialogArea(Composite parent) {
			boolean resourcesBundleIsAvailable = (Platform
				.getBundle("org.eclipse.core.resources") != null); //$NON-NLS-1$

			Composite composite = (Composite) super.createDialogArea(parent);
			{
				FormLayout layout = new FormLayout();
				composite.setLayout(layout);

				GridData data = new GridData();
				data.verticalAlignment = GridData.FILL;
				data.grabExcessVerticalSpace = true;
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				if (!resourcesBundleIsAvailable) {
					data.widthHint = 330;
				}
				composite.setLayoutData(data);
			}

			Composite buttonComposite = new Composite(composite, SWT.NONE);
			{
				FormData data = new FormData();
				data.top = new FormAttachment(0, CONTROL_OFFSET);
				data.left = new FormAttachment(30, 0);
				data.right = new FormAttachment(100, -CONTROL_OFFSET);
				buttonComposite.setLayoutData(data);

				buttonComposite.setLayout(new FormLayout());
			}

			Label resourceURILabel = new Label(composite, SWT.LEFT);
			{
				resourceURILabel.setText(EMFEditUIPlugin.INSTANCE
					.getString("_UI_ResourceURI_label")); //$NON-NLS-1$
				FormData data = new FormData();
				data.top = new FormAttachment(buttonComposite, CONTROL_OFFSET,
					SWT.CENTER);
				data.left = new FormAttachment(0, CONTROL_OFFSET);
				resourceURILabel.setLayoutData(data);
			}

			resourceURIField = new Text(composite, SWT.BORDER);
			{
				FormData data = new FormData();
				data.top = new FormAttachment(buttonComposite, CONTROL_OFFSET);
				data.left = new FormAttachment(0, CONTROL_OFFSET);
				data.right = new FormAttachment(100, -CONTROL_OFFSET);
				resourceURIField.setLayoutData(data);
			}

			Button resourceURIBrowseFileSystemButton = new Button(
				buttonComposite, SWT.PUSH);
			resourceURIBrowseFileSystemButton.setText(EMFEditUIPlugin.INSTANCE
				.getString("_UI_BrowseFileSystem_label")); //$NON-NLS-1$
			resourceURIBrowseFileSystemButton
				.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent event) {
						DirectoryDialog fileDialog = new DirectoryDialog(
							getShell());
						String directoryName = fileDialog.open();
						if (directoryName != null && directoryName.length() > 0) {
							String filePath = fileDialog.getFilterPath();
							resourceURIField.setText(filePath+"/"+DEFAULT_RESOURCE_NAME);  //$NON-NLS-1$
						}
					}
				});

			if (resourcesBundleIsAvailable) {
				Button resourceURIBrowseWorkspaceButton = new Button(
					buttonComposite, SWT.PUSH);
				{
					FormData data = new FormData();
					data.right = new FormAttachment(100);
					resourceURIBrowseWorkspaceButton.setLayoutData(data);
				}
				{
					FormData data = new FormData();
					data.right = new FormAttachment(
						resourceURIBrowseWorkspaceButton, -CONTROL_OFFSET);
					resourceURIBrowseFileSystemButton.setLayoutData(data);
				}
				resourceURIBrowseWorkspaceButton
					.setText(EMFEditUIPlugin.INSTANCE
						.getString("_UI_BrowseWorkspace_label")); //$NON-NLS-1$
				resourceURIBrowseWorkspaceButton
					.addSelectionListener(new SelectionAdapter() {

						public void widgetSelected(SelectionEvent event) {
							ContainerSelectionDialog containerSelectionDialog = new ContainerSelectionDialog(
								getShell(), ResourcesPlugin.getWorkspace()
									.getRoot(), false, EMFEditUIPlugin.INSTANCE
									.getString("_UI_SelectTheResource_label")); //$NON-NLS-1$

							containerSelectionDialog.open();
							Object[] result = containerSelectionDialog
								.getResult();

							if (result != null) {
								StringBuffer text = new StringBuffer();
								IResource resource = ResourcesPlugin
									.getWorkspace().getRoot().findMember(
										(IPath) result[0]);
								if (resource.getType() == IResource.FOLDER
									|| resource.getType() == IResource.PROJECT) {
									text.append(resource.getLocation()
										.toOSString());
								}

								resourceURIField.setText(text.toString()+"/"+DEFAULT_RESOURCE_NAME); //$NON-NLS-1$
							}
						}
					});
			} else {
				FormData data = new FormData();
				data.right = new FormAttachment(100);
				resourceURIBrowseFileSystemButton.setLayoutData(data);
			}

			Label separatorLabel = new Label(composite, SWT.SEPARATOR
				| SWT.HORIZONTAL);
			{
				FormData data = new FormData();
				data.top = new FormAttachment(resourceURIField,
					(int) (1.5 * CONTROL_OFFSET));
				data.left = new FormAttachment(0, -CONTROL_OFFSET);
				data.right = new FormAttachment(100, CONTROL_OFFSET);
				separatorLabel.setLayoutData(data);
			}

			composite.setTabList(new Control[] {resourceURIField,
				buttonComposite});
			return composite;
		}

		protected void okPressed() {
			resourceURIs = getResourceURIs();
			if (domain != null) {
				Resource res = ((MEditingDomain) domain).createResource(resourceURIs,RMPLibraryPackage.eINSTANCE.getLibrary());
				((MEditingDomain) domain).saveResource(res);
			}
			super.okPressed();
		}

		public boolean close() {
			return super.close();
		}

		public String getResourceURIs() {
			return resourceURIField != null && !resourceURIField.isDisposed() ? resourceURIField
				.getText()
				: resourceURIs;
		}

		public List getURIs() {
			List uris = new ArrayList();
			for (StringTokenizer stringTokenizer = new StringTokenizer(
				getResourceURIs()); stringTokenizer.hasMoreTokens();) {
				String resourceURI = stringTokenizer.nextToken();
				uris.add(URI.createURI(resourceURI));
			}

			return uris;
		}
	}
	
	/*
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, final ISelection selection) {
		// No-op
	}

	/*
	 * @see org.eclipse.ui.IActionDelegate2#dispose()
	 */
	public void dispose() {
		//No-op
	}

	/*
	 * @see org.eclipse.ui.IEditorActionDelegate#setActiveEditor(org.eclipse.jface.action.IAction,
	 *      org.eclipse.ui.IEditorPart)
	 */
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.editor = (MSLLibraryEditor) targetEditor;
		if ( targetEditor != null ) {
			this.shell = targetEditor.getSite().getShell();
		}
	}

	/*
	 * @see org.eclipse.ui.IActionDelegate2#init(org.eclipse.jface.action.IAction)
	 */
	public void init(IAction action) {
		// No-op
	}

	/*
	 * @see org.eclipse.ui.IActionDelegate2#runWithEvent(org.eclipse.jface.action.IAction,
	 *      org.eclipse.swt.widgets.Event)
	 */
	public void runWithEvent(IAction action, Event event) {
		run(action);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		CreateResourceDialog dialog = new CreateResourceDialog(shell,editor.getEditingDomain());
		
		dialog.open();
	}
}