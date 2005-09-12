/******************************************************************************
 * Copyright (c) 2002, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.ui.transfer.providers.internal;

import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.TransferData;

import org.eclipse.gmf.runtime.common.ui.services.dnd.core.AbstractTransferAdapterProvider;
import org.eclipse.gmf.runtime.common.ui.services.dnd.core.TransferAgent;
import org.eclipse.gmf.runtime.common.ui.services.dnd.drag.ITransferDragSourceListener;
import org.eclipse.gmf.runtime.common.ui.services.dnd.drag.SelectionDragAdapter;
import org.eclipse.gmf.runtime.common.ui.services.dnd.drop.ITransferDropTargetListener;
import org.eclipse.gmf.runtime.common.ui.services.dnd.drop.SelectionDropAdapter;

/**
 * Concrete implementation for ECore transfer adapters
 * 
 * @author Vishy Ramaswamy
 */
public class EcoreTransferAdapterProvider
    extends AbstractTransferAdapterProvider {
    /**
     * Constructor for EcoreTransferAdapterProvider.
     */
    public EcoreTransferAdapterProvider() {
        super();
    }

	/*
	 * @see org.eclipse.gmf.runtime.common.ui.internal.services.dnd.ITransferAdapterProvider#getTransferDropTargetAdapter(java.lang.String)
	 */
	 public ITransferDropTargetListener getTransferDropTargetAdapter(String transferId) {
		 TransferAgent agent =
			 new TransferAgent(transferId, LocalTransfer.getInstance(), true) {
			 /*
			  * @see org.eclipse.gmf.runtime.common.ui.internal.dnd.TransferAgent#getSelection(org.eclipse.swt.dnd.TransferData)
			  */
			 public ISelection getSelection(TransferData transferData) {
				 Object selection =
					 LocalTransfer.getInstance().nativeToJava(transferData);
				 return (selection instanceof ISelection)
					 ? (ISelection) selection
					 : new StructuredSelection(selection);
			 }
		 };

		 return new SelectionDropAdapter(agent);
	 }

	/*
	 * @see org.eclipse.gmf.runtime.common.ui.internal.services.dnd.ITransferAdapterProvider#getTransferDragSourceAdapter(java.lang.String)
	 */
	public ITransferDragSourceListener getTransferDragSourceAdapter(String transferId) {
		TransferAgent agent =
			new TransferAgent(transferId, LocalTransfer.getInstance(), true) {
			/*
			 * @see org.eclipse.gmf.runtime.common.ui.internal.dnd.ITransferAgent#setSelection(org.eclipse.jface.viewers.ISelection)
			 */
			public void setSelection(ISelection selection) {
				LocalTransfer.getInstance().javaToNative(
					selection,
					new TransferData());
			}

			/*
			 * @see org.eclipse.gmf.runtime.common.ui.internal.dnd.TransferAgent#getSelection(org.eclipse.swt.dnd.TransferData)
			 */
			public ISelection getSelection(TransferData transferData) {
				return (ISelection)LocalTransfer.getInstance().nativeToJava(
					transferData);
			}
		};

		return new SelectionDragAdapter(agent);
	}
}
