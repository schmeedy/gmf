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
package org.eclipse.gmf.runtime.update;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.diagramrt.ChildNode;
import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramLink;
import org.eclipse.gmf.diagramrt.DiagramNode;
import org.eclipse.gmf.diagramrt.DiagramRTPackage;
import org.eclipse.gmf.util.NotificationDump;

/**
 * <em>IMPORTANT</em> {@link org.eclipse.emf.ecore.change.util.ChangeRecorder} might be of interest to track changes in a "delta" way
 * @author artem
 *
 */
public abstract class DiagramUpdateManager extends EContentAdapter {
	private static final boolean NEEDS_DUMP = true;

	private boolean myIsDiagram;
	private boolean myIsNode;
	private boolean myIsLink;
	private boolean myIsSubNode;
	private Object myModelElementChanged;
	private List myAffectedElements;
	private boolean myIsStructural;
	private Notification myNotification;

	protected DiagramUpdateManager() {
	}

	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (notification.isTouch()) {
			return;
		}
		System.out.println("diagram changed, refreshing children...");

		if (NEEDS_DUMP) {
			NotificationDump.print(notification);
		}
		initializeFrom(notification);
		handleState();
		clean();
	}

	/**
	 * @see org.eclipse.gef.EditPartViewer#getEditPartRegistry()
	 * @return Map of diagram model elements 
	 * ({@link org.eclipse.gmf.diagramrt.DiagramNode} or {@link DiagramLink}
	 * to their edit parts.
	 */
	protected abstract Map getEditPartRegistry();

	protected void initializeFrom(Notification notification) {
		myNotification = notification;
		myModelElementChanged = notification.getNotifier();
		myIsDiagram = myModelElementChanged instanceof DiagramCanvas;
		myIsNode = myModelElementChanged instanceof DiagramNode;
		myIsLink = myModelElementChanged instanceof DiagramLink;
		myIsSubNode = myModelElementChanged instanceof ChildNode;
		myAffectedElements = Collections.EMPTY_LIST;
		switch (notification.getEventType()) {
		case Notification.ADD : 
			myIsStructural = true;
			myAffectedElements = Collections.singletonList(notification.getNewValue());
			break;
		case Notification.REMOVE :
			myIsStructural = true;
			myAffectedElements = Collections.singletonList(notification.getOldValue());
			break;
		case Notification.ADD_MANY :
			myIsStructural = true;
			myAffectedElements = (List) notification.getNewValue();
			break;
		case Notification.REMOVE_MANY : 
			myIsStructural = true;
			myAffectedElements = (List) notification.getOldValue();
		case Notification.MOVE :
			myIsStructural = true;
			break;
		case Notification.SET :
		case Notification.UNSET :
			myIsStructural = false;
			break;
		default :
			throw new IllegalStateException();
		}
	}

	protected void clean() {
		myAffectedElements = null;
		myModelElementChanged = null;
		myNotification = null;
	}

	protected void handleState() {
		if (isDiagramLinks()) {
			handleLinkContainmentChange();
		} else if (isNode()) {
			if (isNodeIncomingLinks()) {
				refreshIncomingLinks((DiagramNode) myModelElementChanged);
			} else if (isNodeOutgoingLinks()) {
				refreshOutgoingLinks((DiagramNode) myModelElementChanged);
			} else {
				refreshElement(myModelElementChanged);
			}
		} else {
			refreshElement(myModelElementChanged);
		}
	}

	private boolean isNode() {
		return myIsNode;
	}

	private boolean isNodeOutgoingLinks() {
		return isNode() && getNodeFeatureID() == DiagramRTPackage.DIAGRAM_NODE__OUTGOING_LINKS;
	}

	private boolean isNodeIncomingLinks() {
		return isNode() && getNodeFeatureID() == DiagramRTPackage.DIAGRAM_NODE__INCOMING_LINKS;
	}

	private boolean isDiagramLinks() {
		return myIsDiagram && getDiagramFeatureID() == DiagramRTPackage.DIAGRAM_CANVAS__LINKS;
	}

	private void handleLinkContainmentChange() {
		for (Iterator it = myAffectedElements.iterator(); it.hasNext();) {
			DiagramLink next = (DiagramLink) it.next();
			refreshOutgoingLinks(next.getSource());
			refreshIncomingLinks(next.getTarget());
		}
	}

	private void refreshIncomingLinks(DiagramNode node) {
		EditPart ep = getEditPart(node);
		if (ep instanceof UpdatablePart) {
			((UpdatablePart) ep).incomingLinksChanged();
		} else if (ep != null) {
			ep.refresh();
		}
	}

	private void refreshOutgoingLinks(DiagramNode node) {
		EditPart ep = getEditPart(node);
		if (ep instanceof UpdatablePart) {
			((UpdatablePart) ep).outgoingLinksChanged();
		} else if (ep != null) {
			ep.refresh();
		}
	}

	private void refreshElement(Object modelElementToRefresh) {
		EditPart ep = getEditPart(modelElementToRefresh);
		if (ep != null) {
			if (ep instanceof UpdatablePart) {
				update((UpdatablePart) ep);
			} else {
				ep.refresh();
			}
		}
	}

	private EditPart getEditPart(Object modelElementToRefresh) {
		EditPart ep = (EditPart) getEditPartRegistry().get(modelElementToRefresh);
		if (ep == null) {
			System.err.println("can't find editPart for " + modelElementToRefresh);
		}
		return ep;
	}

	private boolean isBounds() {
		if (isNode()) {
			final int nodeFeatureID = getNodeFeatureID();
			return (nodeFeatureID == DiagramRTPackage.DIAGRAM_NODE__LOCATION || nodeFeatureID == DiagramRTPackage.DIAGRAM_NODE__SIZE);
		}
		if (myIsLink) {
			// XXX decide on bendpoints
			return false;
		}
		return false;
	}

	private boolean isVisual() {
		if (isNode()) {
			return getNodeFeatureID() == DiagramRTPackage.DIAGRAM_NODE__BACKGROUND_COLOR || getNodeFeatureID() == DiagramRTPackage.DIAGRAM_BASE_ELEMENT__FOREGROUND_COLOR;
		}
		if (isNode()) {
			return getLinkFeatureID() == DiagramRTPackage.DIAGRAM_BASE_ELEMENT__FOREGROUND_COLOR;
		}
		return false;
	}

	private int getNodeFeatureID() {
		return myNotification.getFeatureID(DiagramNode.class);
	}

	private int getLinkFeatureID() {
		return myNotification.getFeatureID(DiagramLink.class);
	}

	private int getDiagramFeatureID() {
		return myNotification.getFeatureID(DiagramCanvas.class);
	}

	private void update(UpdatablePart editPart) {
		if (myIsStructural) {
			editPart.structuralPropertiesChanged();
		}
		if (isVisual()) {
			editPart.visualPropertiesChanged();
		}
		if (isBounds()) {
			editPart.boundsChanged();
		}
	}
}
