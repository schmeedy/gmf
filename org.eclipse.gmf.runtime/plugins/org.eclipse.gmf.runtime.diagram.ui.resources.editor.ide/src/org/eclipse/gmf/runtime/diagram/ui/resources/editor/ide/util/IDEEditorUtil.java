/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.util;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ide.IDE;

import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.internal.EditorIDEDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.EditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.EditorFileCreator;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.EditorUtil;

/**
 * Diagram utilities for IDE editors
 * 
 * @author wdiu, Wayne Diu, refactored from EditorUtil
 */
public class IDEEditorUtil extends EditorUtil {
	
	/**
	 * Opens the diagram in an IEditorPart
	 *  
	 * @param file IFile that contains the diagram
	 * @param dWindow the workbench window
	 * @param saveDiagram true to save the diagram after opening, false to not
	 * save it.
	 * @param progressMonitor used when opening the diagram.
	 * For now, it is only used when the diagram is being saved after opening.
	 *  
	 * @return DiagramEditPart for the diagram opened in an IEditorPart.
	 */
	public static final DiagramEditPart openDiagram(IFile file,
			IWorkbenchWindow dWindow, boolean saveDiagram,
			IProgressMonitor progressMonitor) {
		IEditorPart editorPart = null;
		try {
			IWorkbenchPage page = dWindow.getActivePage();
			if (page != null) {
				editorPart = IDE.openEditor(page, file, true);

				if (saveDiagram)
					editorPart.doSave(progressMonitor);
			}
			file.refreshLocal(IResource.DEPTH_ZERO, null);
			return ((IDiagramWorkbenchPart) editorPart).getDiagramEditPart();
		} catch (Exception e) {
			Trace.catching(EditorPlugin.getInstance(),
				EditorIDEDebugOptions.EXCEPTIONS_CATCHING,
				EditorUtil.class, "openDiagram", e); //$NON-NLS-1$
		}

		return null;
	}
	
	/**
	 * Create and open a diagram.
	 * 
	 * @param diagramFileCreator that the new diagram is created from.
	 * @param containerPath IPath for the container of the diagram file
	 * @param fileName String of the name of the diagram
	 * @param initialContents initial contents of the new diagram file
	 * @param kind String of the kind of diagram to create
	 * @param dWindow 
	 * @param progressMonitor IProgressMonitor to use when creating the
	 * new diagram and opening it
	 * @param openEditor boolean true to open the editor after creating it,
	 * false to not open it after creating it
	 * @param saveDiagram boolean true to save the diagram after creating it
	 * 
	 * @return IFile containing the created diagram
	 */
	public static IFile createAndOpenDiagram(
			EditorFileCreator diagramFileCreator,
			IPath containerPath, String fileName, InputStream initialContents,
			String kind, IWorkbenchWindow dWindow,
			IProgressMonitor progressMonitor, boolean openEditor,
			boolean saveDiagram) {
		IFile newFile = EditorUtil.createNewDiagramFile(diagramFileCreator,
			containerPath, fileName, initialContents, kind, dWindow.getShell(),
			progressMonitor, PreferencesHint.USE_DEFAULTS);

		if (newFile != null && openEditor) {
			//Since the file resource was created fine, open it for editing
			// iff requested by the user
			IDEEditorUtil.openDiagram(newFile, dWindow, saveDiagram,
				progressMonitor);
		}

		return newFile;
	}	
}
