/*
 * Copyright (c) 2005 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.runtime.actions;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.diagramrt.DiagramRTFactory;
import org.eclipse.gmf.diagramrt.DiagramRTPackage;
import org.eclipse.gmf.runtime.gef.Plugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class NewDiagramAction implements IObjectActionDelegate, IExecutableExtension, IInputValidator {
	private IContainer mySelection;
	private String myRunTimeModelNsURI;
	private String myFileExtension;
	private String myDefaultFileName;
	private IWorkbenchPart myPart;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		myPart = targetPart;
	}

	public void run(IAction action) {
		InputDialog d = new InputDialog(getShell(), "Diagram file name", "Please provide diagram file name", myDefaultFileName, this);
		if (d.open() != InputDialog.OK) {
			return;
		}
		Object dc = createCanvas();
		if (dc == null) {
			MessageDialog.openError(getShell(), "Error", "Failed to create diagram canvas object");
			return;
		}
		String filePath = mySelection.getFullPath().append(d.getValue()).addFileExtension(myFileExtension).toString();
		save(filePath, dc);
	}

	private Shell getShell() {
		return myPart.getSite().getShell();
	}
	private Object createCanvas() {
		// easy case
		if (myRunTimeModelNsURI.equals(DiagramRTPackage.eNS_URI)) {
			return DiagramRTFactory.eINSTANCE.createDiagramCanvas();
		}
		EPackage p = EPackage.Registry.INSTANCE.getEPackage(myRunTimeModelNsURI);
		EClass canvasClass = findCanvasClass(p);
		if (canvasClass == null) {
			return null;
		}
		return p.getEFactoryInstance().create(canvasClass);
	}

	private EClass findCanvasClass(EPackage p) {
		final EClass diagramCanvasClass = DiagramRTPackage.eINSTANCE.getDiagramCanvas();
		for (TreeIterator it = p.eAllContents(); it.hasNext();) {
			Object next = it.next();
			if (next instanceof EClass == false) {
				continue;
			}
			EClass candidate = (EClass) next;
			if (candidate.getEAllSuperTypes().contains(diagramCanvasClass)) {
				return candidate;
			}
		}
		return null;
	}

	private void save(String filePath, Object canvas) {
		Resource r = new ResourceSetImpl().createResource(URI.createPlatformResourceURI(filePath));
		r.getContents().add(canvas);
		try {
			r.save(Collections.EMPTY_MAP);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		mySelection = null;
		action.setEnabled(false);
		if (selection instanceof IStructuredSelection == false || selection.isEmpty()) {
			return;
		}
		mySelection = (IContainer) ((IStructuredSelection) selection).getFirstElement();
		action.setEnabled(true);
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		myDefaultFileName = config.getAttribute("defaultName");
		myFileExtension = config.getAttribute("fileExtension");
		myRunTimeModelNsURI = config.getAttribute("rtModelNsURI");
		if (myRunTimeModelNsURI == null) {
			throw new CoreException(new Status(IStatus.ERROR, Plugin.getPluginID(), 0, "Need nsURI of rtModel", null));
		}
		if (!EPackage.Registry.INSTANCE.containsKey(myRunTimeModelNsURI) && !myRunTimeModelNsURI.equals(DiagramRTPackage.eNS_URI)) {
			throw new CoreException(new Status(IStatus.ERROR, Plugin.getPluginID(), 0, "No package with specified nsURI", null));
		}
		if (myFileExtension == null) {
			throw new CoreException(new Status(IStatus.ERROR, Plugin.getPluginID(), 0, "Need file extension attribute", null));
		}
	}

	public String isValid(String newText) {
		IStatus s = ResourcesPlugin.getWorkspace().validateName(newText, IResource.FILE);
		if (!s.isOK()) {
			return s.getMessage();
		}
		if (mySelection.getFile(new Path(newText).addFileExtension(myFileExtension)).exists()) {
			return "File already exists, choose another name";
		}
		return null;
	}
}
