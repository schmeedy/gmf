package org.eclipse.gmf.examples.runtime.emf.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.examples.runtime.emf.editor.MSLLibraryEditor;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

/**
 * This action demonstrate finding referencing EObjects using the MSL.
 * 
 * @author cmcgee
 */
public class FindReferencers
	implements IActionDelegate, IEditorActionDelegate{

	private EObject selectedEObject;
	private MSLLibraryEditor editor;

	public FindReferencers() {
		super();
	}

	public void run(IAction action) {
		editor.setSelectionToViewer(EObjectUtil.getReferencers(selectedEObject,null));
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selectedEObject = null;
		try {
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				
				if (!structuredSelection.isEmpty() &&
						structuredSelection.toList().get(0) instanceof EObject) {
					this.selectedEObject = (EObject)structuredSelection.toList().get(0);
				}
			}
		} finally {
			action.setEnabled((null != selectedEObject));
		}
	}

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.editor = (MSLLibraryEditor)targetEditor;
	}
}
