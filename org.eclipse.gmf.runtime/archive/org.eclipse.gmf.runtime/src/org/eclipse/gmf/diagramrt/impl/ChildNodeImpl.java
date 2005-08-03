/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt.impl;

import java.util.Collection;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmf.diagramrt.ChildNode;
import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramNode;
import org.eclipse.gmf.diagramrt.DiagramRTPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Child Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.ChildNodeImpl#getParentNode <em>Parent Node</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.ChildNodeImpl#getGroupID <em>Group ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChildNodeImpl extends DiagramNodeImpl implements ChildNode {
	/**
	 * The default value of the '{@link #getGroupID() <em>Group ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupID()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroupID() <em>Group ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupID()
	 * @generated
	 * @ordered
	 */
	protected String groupID = GROUP_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChildNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DiagramRTPackage.eINSTANCE.getChildNode();
	}

	public DiagramCanvas getDiagram() {
		return getParentNode() == null ? null : getParentNode().getDiagram();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramNode getParentNode() {
		if (eContainerFeatureID != DiagramRTPackage.CHILD_NODE__PARENT_NODE) return null;
		return (DiagramNode)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroupID() {
		return groupID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupID(String newGroupID) {
		String oldGroupID = groupID;
		groupID = newGroupID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.CHILD_NODE__GROUP_ID, oldGroupID, groupID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case DiagramRTPackage.CHILD_NODE__DIAGRAM:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, DiagramRTPackage.CHILD_NODE__DIAGRAM, msgs);
				case DiagramRTPackage.CHILD_NODE__INCOMING_LINKS:
					return ((InternalEList)getIncomingLinks()).basicAdd(otherEnd, msgs);
				case DiagramRTPackage.CHILD_NODE__OUTGOING_LINKS:
					return ((InternalEList)getOutgoingLinks()).basicAdd(otherEnd, msgs);
				case DiagramRTPackage.CHILD_NODE__CHILD_NODES:
					return ((InternalEList)getChildNodes()).basicAdd(otherEnd, msgs);
				case DiagramRTPackage.CHILD_NODE__PARENT_NODE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, DiagramRTPackage.CHILD_NODE__PARENT_NODE, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case DiagramRTPackage.CHILD_NODE__RUN_TIME_PROPERTIES:
					return ((InternalEList)getRunTimeProperties()).basicRemove(otherEnd, msgs);
				case DiagramRTPackage.CHILD_NODE__DIAGRAM:
					return eBasicSetContainer(null, DiagramRTPackage.CHILD_NODE__DIAGRAM, msgs);
				case DiagramRTPackage.CHILD_NODE__INCOMING_LINKS:
					return ((InternalEList)getIncomingLinks()).basicRemove(otherEnd, msgs);
				case DiagramRTPackage.CHILD_NODE__OUTGOING_LINKS:
					return ((InternalEList)getOutgoingLinks()).basicRemove(otherEnd, msgs);
				case DiagramRTPackage.CHILD_NODE__CHILD_NODES:
					return ((InternalEList)getChildNodes()).basicRemove(otherEnd, msgs);
				case DiagramRTPackage.CHILD_NODE__PARENT_NODE:
					return eBasicSetContainer(null, DiagramRTPackage.CHILD_NODE__PARENT_NODE, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case DiagramRTPackage.CHILD_NODE__DIAGRAM:
					return eContainer.eInverseRemove(this, DiagramRTPackage.DIAGRAM_CANVAS__NODES, DiagramCanvas.class, msgs);
				case DiagramRTPackage.CHILD_NODE__PARENT_NODE:
					return eContainer.eInverseRemove(this, DiagramRTPackage.DIAGRAM_NODE__CHILD_NODES, DiagramNode.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DiagramRTPackage.CHILD_NODE__DOMAIN_MODEL_ELEMENT:
				if (resolve) return getDomainModelElement();
				return basicGetDomainModelElement();
			case DiagramRTPackage.CHILD_NODE__FOREGROUND_COLOR:
				return getForegroundColor();
			case DiagramRTPackage.CHILD_NODE__UIN:
				return new Integer(getUin());
			case DiagramRTPackage.CHILD_NODE__VISUAL_ID:
				return new Integer(getVisualID());
			case DiagramRTPackage.CHILD_NODE__RUN_TIME_PROPERTIES:
				return getRunTimeProperties();
			case DiagramRTPackage.CHILD_NODE__BACKGROUND_COLOR:
				return getBackgroundColor();
			case DiagramRTPackage.CHILD_NODE__DIAGRAM:
				return getDiagram();
			case DiagramRTPackage.CHILD_NODE__INCOMING_LINKS:
				return getIncomingLinks();
			case DiagramRTPackage.CHILD_NODE__OUTGOING_LINKS:
				return getOutgoingLinks();
			case DiagramRTPackage.CHILD_NODE__CHILD_NODES:
				return getChildNodes();
			case DiagramRTPackage.CHILD_NODE__LOCATION:
				return getLocation();
			case DiagramRTPackage.CHILD_NODE__SIZE:
				return getSize();
			case DiagramRTPackage.CHILD_NODE__PARENT_NODE:
				return getParentNode();
			case DiagramRTPackage.CHILD_NODE__GROUP_ID:
				return getGroupID();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DiagramRTPackage.CHILD_NODE__DOMAIN_MODEL_ELEMENT:
				setDomainModelElement((EObject)newValue);
				return;
			case DiagramRTPackage.CHILD_NODE__FOREGROUND_COLOR:
				setForegroundColor((String)newValue);
				return;
			case DiagramRTPackage.CHILD_NODE__UIN:
				setUin(((Integer)newValue).intValue());
				return;
			case DiagramRTPackage.CHILD_NODE__VISUAL_ID:
				setVisualID(((Integer)newValue).intValue());
				return;
			case DiagramRTPackage.CHILD_NODE__RUN_TIME_PROPERTIES:
				getRunTimeProperties().clear();
				getRunTimeProperties().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.CHILD_NODE__BACKGROUND_COLOR:
				setBackgroundColor((String)newValue);
				return;
			case DiagramRTPackage.CHILD_NODE__DIAGRAM:
				setDiagram((DiagramCanvas)newValue);
				return;
			case DiagramRTPackage.CHILD_NODE__INCOMING_LINKS:
				getIncomingLinks().clear();
				getIncomingLinks().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.CHILD_NODE__OUTGOING_LINKS:
				getOutgoingLinks().clear();
				getOutgoingLinks().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.CHILD_NODE__CHILD_NODES:
				getChildNodes().clear();
				getChildNodes().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.CHILD_NODE__LOCATION:
				setLocation((Point)newValue);
				return;
			case DiagramRTPackage.CHILD_NODE__SIZE:
				setSize((Dimension)newValue);
				return;
			case DiagramRTPackage.CHILD_NODE__GROUP_ID:
				setGroupID((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DiagramRTPackage.CHILD_NODE__DOMAIN_MODEL_ELEMENT:
				setDomainModelElement((EObject)null);
				return;
			case DiagramRTPackage.CHILD_NODE__FOREGROUND_COLOR:
				setForegroundColor(FOREGROUND_COLOR_EDEFAULT);
				return;
			case DiagramRTPackage.CHILD_NODE__UIN:
				setUin(UIN_EDEFAULT);
				return;
			case DiagramRTPackage.CHILD_NODE__VISUAL_ID:
				setVisualID(VISUAL_ID_EDEFAULT);
				return;
			case DiagramRTPackage.CHILD_NODE__RUN_TIME_PROPERTIES:
				getRunTimeProperties().clear();
				return;
			case DiagramRTPackage.CHILD_NODE__BACKGROUND_COLOR:
				setBackgroundColor(BACKGROUND_COLOR_EDEFAULT);
				return;
			case DiagramRTPackage.CHILD_NODE__DIAGRAM:
				setDiagram((DiagramCanvas)null);
				return;
			case DiagramRTPackage.CHILD_NODE__INCOMING_LINKS:
				getIncomingLinks().clear();
				return;
			case DiagramRTPackage.CHILD_NODE__OUTGOING_LINKS:
				getOutgoingLinks().clear();
				return;
			case DiagramRTPackage.CHILD_NODE__CHILD_NODES:
				getChildNodes().clear();
				return;
			case DiagramRTPackage.CHILD_NODE__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case DiagramRTPackage.CHILD_NODE__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case DiagramRTPackage.CHILD_NODE__GROUP_ID:
				setGroupID(GROUP_ID_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DiagramRTPackage.CHILD_NODE__DOMAIN_MODEL_ELEMENT:
				return domainModelElement != null;
			case DiagramRTPackage.CHILD_NODE__FOREGROUND_COLOR:
				return FOREGROUND_COLOR_EDEFAULT == null ? foregroundColor != null : !FOREGROUND_COLOR_EDEFAULT.equals(foregroundColor);
			case DiagramRTPackage.CHILD_NODE__UIN:
				return uin != UIN_EDEFAULT;
			case DiagramRTPackage.CHILD_NODE__VISUAL_ID:
				return visualID != VISUAL_ID_EDEFAULT;
			case DiagramRTPackage.CHILD_NODE__RUN_TIME_PROPERTIES:
				return runTimeProperties != null && !runTimeProperties.isEmpty();
			case DiagramRTPackage.CHILD_NODE__BACKGROUND_COLOR:
				return BACKGROUND_COLOR_EDEFAULT == null ? backgroundColor != null : !BACKGROUND_COLOR_EDEFAULT.equals(backgroundColor);
			case DiagramRTPackage.CHILD_NODE__DIAGRAM:
				return getDiagram() != null;
			case DiagramRTPackage.CHILD_NODE__INCOMING_LINKS:
				return incomingLinks != null && !incomingLinks.isEmpty();
			case DiagramRTPackage.CHILD_NODE__OUTGOING_LINKS:
				return outgoingLinks != null && !outgoingLinks.isEmpty();
			case DiagramRTPackage.CHILD_NODE__CHILD_NODES:
				return childNodes != null && !childNodes.isEmpty();
			case DiagramRTPackage.CHILD_NODE__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case DiagramRTPackage.CHILD_NODE__SIZE:
				return SIZE_EDEFAULT == null ? size != null : !SIZE_EDEFAULT.equals(size);
			case DiagramRTPackage.CHILD_NODE__PARENT_NODE:
				return getParentNode() != null;
			case DiagramRTPackage.CHILD_NODE__GROUP_ID:
				return GROUP_ID_EDEFAULT == null ? groupID != null : !GROUP_ID_EDEFAULT.equals(groupID);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (groupID: ");
		result.append(groupID);
		result.append(')');
		return result.toString();
	}

} //ChildNodeImpl
