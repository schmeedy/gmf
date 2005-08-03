/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmf.diagramrt.DiagramBaseNode;
import org.eclipse.gmf.diagramrt.DiagramRTPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram Base Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramBaseNodeImpl#getBackgroundColor <em>Background Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DiagramBaseNodeImpl extends DiagramBaseElementImpl implements DiagramBaseNode {
	/**
	 * The default value of the '{@link #getBackgroundColor() <em>Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackgroundColor()
	 * @generated
	 * @ordered
	 */
	protected static final String BACKGROUND_COLOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBackgroundColor() <em>Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackgroundColor()
	 * @generated
	 * @ordered
	 */
	protected String backgroundColor = BACKGROUND_COLOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramBaseNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DiagramRTPackage.eINSTANCE.getDiagramBaseNode();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBackgroundColor(String newBackgroundColor) {
		String oldBackgroundColor = backgroundColor;
		backgroundColor = newBackgroundColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_BASE_NODE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case DiagramRTPackage.DIAGRAM_BASE_NODE__RUN_TIME_PROPERTIES:
					return ((InternalEList)getRunTimeProperties()).basicRemove(otherEnd, msgs);
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DiagramRTPackage.DIAGRAM_BASE_NODE__DOMAIN_MODEL_ELEMENT:
				if (resolve) return getDomainModelElement();
				return basicGetDomainModelElement();
			case DiagramRTPackage.DIAGRAM_BASE_NODE__FOREGROUND_COLOR:
				return getForegroundColor();
			case DiagramRTPackage.DIAGRAM_BASE_NODE__UIN:
				return new Integer(getUin());
			case DiagramRTPackage.DIAGRAM_BASE_NODE__VISUAL_ID:
				return new Integer(getVisualID());
			case DiagramRTPackage.DIAGRAM_BASE_NODE__RUN_TIME_PROPERTIES:
				return getRunTimeProperties();
			case DiagramRTPackage.DIAGRAM_BASE_NODE__BACKGROUND_COLOR:
				return getBackgroundColor();
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
			case DiagramRTPackage.DIAGRAM_BASE_NODE__DOMAIN_MODEL_ELEMENT:
				setDomainModelElement((EObject)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__FOREGROUND_COLOR:
				setForegroundColor((String)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__UIN:
				setUin(((Integer)newValue).intValue());
				return;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__VISUAL_ID:
				setVisualID(((Integer)newValue).intValue());
				return;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__RUN_TIME_PROPERTIES:
				getRunTimeProperties().clear();
				getRunTimeProperties().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__BACKGROUND_COLOR:
				setBackgroundColor((String)newValue);
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
			case DiagramRTPackage.DIAGRAM_BASE_NODE__DOMAIN_MODEL_ELEMENT:
				setDomainModelElement((EObject)null);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__FOREGROUND_COLOR:
				setForegroundColor(FOREGROUND_COLOR_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__UIN:
				setUin(UIN_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__VISUAL_ID:
				setVisualID(VISUAL_ID_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__RUN_TIME_PROPERTIES:
				getRunTimeProperties().clear();
				return;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__BACKGROUND_COLOR:
				setBackgroundColor(BACKGROUND_COLOR_EDEFAULT);
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
			case DiagramRTPackage.DIAGRAM_BASE_NODE__DOMAIN_MODEL_ELEMENT:
				return domainModelElement != null;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__FOREGROUND_COLOR:
				return FOREGROUND_COLOR_EDEFAULT == null ? foregroundColor != null : !FOREGROUND_COLOR_EDEFAULT.equals(foregroundColor);
			case DiagramRTPackage.DIAGRAM_BASE_NODE__UIN:
				return uin != UIN_EDEFAULT;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__VISUAL_ID:
				return visualID != VISUAL_ID_EDEFAULT;
			case DiagramRTPackage.DIAGRAM_BASE_NODE__RUN_TIME_PROPERTIES:
				return runTimeProperties != null && !runTimeProperties.isEmpty();
			case DiagramRTPackage.DIAGRAM_BASE_NODE__BACKGROUND_COLOR:
				return BACKGROUND_COLOR_EDEFAULT == null ? backgroundColor != null : !BACKGROUND_COLOR_EDEFAULT.equals(backgroundColor);
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
		result.append(" (backgroundColor: ");
		result.append(backgroundColor);
		result.append(')');
		return result.toString();
	}

} //DiagramBaseNodeImpl
