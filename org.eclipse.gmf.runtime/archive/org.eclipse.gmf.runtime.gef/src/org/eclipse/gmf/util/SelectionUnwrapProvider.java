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
package org.eclipse.gmf.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

public class SelectionUnwrapProvider implements ISelectionProvider, ISelectionChangedListener {
	private final GraphicalViewer myViewer;
	private final List myListeners = new ArrayList();
	private ISelection mySelection;

	public SelectionUnwrapProvider(GraphicalViewer provider) {
		myViewer = provider;
		myViewer.addSelectionChangedListener(this);
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		myListeners.add(listener);
	}

	public ISelection getSelection() {
		if (mySelection == null) {
			mySelection = unwrap(myViewer.getSelection());
		}
		return mySelection;
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		myListeners.remove(listener);
	}

	public void setSelection(ISelection selection) {
		mySelection = selection;
		myViewer.setSelection(wrap(mySelection));
	}

	public void selectionChanged(SelectionChangedEvent event) {
		mySelection = unwrap(event.getSelection());
		fireSelectionChanged();
	}

	private void fireSelectionChanged() {
		final SelectionChangedEvent ev = new SelectionChangedEvent(this, mySelection);
		for (Iterator it = myListeners.iterator(); it.hasNext();) {
			((ISelectionChangedListener) it.next()).selectionChanged(ev);
		}
	}

	private ISelection wrap(ISelection diagramElements) {
		if (diagramElements instanceof IStructuredSelection == false || diagramElements.isEmpty()) {
			return StructuredSelection.EMPTY;
		}
		Object[] sel = ((IStructuredSelection) diagramElements).toArray();
		ArrayList rv = new ArrayList();
		for (int i = 0; i < sel.length; i++) {
			Object editPart = myViewer.getEditPartRegistry().get(sel[i]);
			if (editPart != null) {
				rv.add(editPart);
			}
		}
		return rv.isEmpty() ? StructuredSelection.EMPTY : new StructuredSelection(rv);
	}

	private ISelection unwrap(ISelection editParts) {
		if (editParts instanceof IStructuredSelection == false || editParts.isEmpty()) {
			return StructuredSelection.EMPTY;
		}
		Object[] sel = ((IStructuredSelection) editParts).toArray();
		ArrayList rv = new ArrayList();
		for (int i = 0; i < sel.length; i++) {
			Object diagramModelObj = ((EditPart) sel[i]).getModel();
			if (diagramModelObj != null) {
				rv.add(diagramModelObj);
			}
		}
		return rv.isEmpty() ? StructuredSelection.EMPTY : new StructuredSelection(rv);
	}
}
