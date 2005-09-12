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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate2;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.examples.runtime.emf.MSLExamplePlugin;
import org.eclipse.gmf.examples.runtime.emf.editor.MSLLibraryEditor;

/**
 * This action delegate gives the user the capability of loading an unloaded
 *  resource in the MSL Library editor's resource set.
 * 
 * @author cmcgee
 */
public class LoadResourceDelegate
	implements IEditorActionDelegate, IActionDelegate2 {

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

	/**
	 * The InputDialog title
	 */
	private String title = MSLExamplePlugin.getResourceString("LoadResourceDelegate.title"); //$NON-NLS-1$

	/*
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, final ISelection selection) {
		this.selectedResources = null;
		try {
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				this.selectedResources = structuredSelection.toList();
			}
		} catch (Exception e) {
			// Exceptions are not expected
			MessageDialog.openInformation(shell, title, MESSAGE_EXCEPTION);
			throw new RuntimeException(e);
		} finally {
			action.setEnabled((null != selectedResources));
		}
		
		for (Iterator i = selectedResources.iterator(); i.hasNext();) {
			Object o = i.next();
			if (!(o instanceof Resource)) {
				action.setEnabled(false);
				break;
			}
		}
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
		if (selectedResources != null) {
			for (Iterator i = selectedResources.iterator(); i.hasNext();) {
				Resource res = (Resource)i.next();
				
				((MEditingDomain)editor.getEditingDomain()).loadResource(res);
			}
		}
	}
}