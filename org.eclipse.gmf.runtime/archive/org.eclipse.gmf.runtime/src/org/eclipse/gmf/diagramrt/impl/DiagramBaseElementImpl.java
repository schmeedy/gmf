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

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmf.diagramrt.DiagramBaseElement;
import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramRTPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram Base Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramBaseElementImpl#getDomainModelElement <em>Domain Model Element</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramBaseElementImpl#getForegroundColor <em>Foreground Color</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramBaseElementImpl#getUin <em>Uin</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramBaseElementImpl#getVisualID <em>Visual ID</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramBaseElementImpl#getRunTimeProperties <em>Run Time Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DiagramBaseElementImpl extends EObjectImpl implements DiagramBaseElement {
	/**
	 * The cached value of the '{@link #getDomainModelElement() <em>Domain Model Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainModelElement()
	 * @generated
	 * @ordered
	 */
	protected EObject domainModelElement = null;

	/**
	 * The default value of the '{@link #getForegroundColor() <em>Foreground Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForegroundColor()
	 * @generated
	 * @ordered
	 */
	protected static final String FOREGROUND_COLOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getForegroundColor() <em>Foreground Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForegroundColor()
	 * @generated
	 * @ordered
	 */
	protected String foregroundColor = FOREGROUND_COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getUin() <em>Uin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUin()
	 * @generated
	 * @ordered
	 */
	protected static final int UIN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getUin() <em>Uin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUin()
	 * @generated
	 * @ordered
	 */
	protected int uin = UIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getVisualID() <em>Visual ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisualID()
	 * @generated
	 * @ordered
	 */
	protected static final int VISUAL_ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getVisualID() <em>Visual ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisualID()
	 * @generated
	 * @ordered
	 */
	protected int visualID = VISUAL_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRunTimeProperties() <em>Run Time Properties</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRunTimeProperties()
	 * @generated
	 * @ordered
	 */
	protected EMap runTimeProperties = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramBaseElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DiagramRTPackage.eINSTANCE.getDiagramBaseElement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getDomainModelElement() {
		if (domainModelElement != null && domainModelElement.eIsProxy()) {
			EObject oldDomainModelElement = domainModelElement;
			domainModelElement = (EObject)eResolveProxy((InternalEObject)domainModelElement);
			if (domainModelElement != oldDomainModelElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramRTPackage.DIAGRAM_BASE_ELEMENT__DOMAIN_MODEL_ELEMENT, oldDomainModelElement, domainModelElement));
			}
		}
		return domainModelElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetDomainModelElement() {
		return domainModelElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomainModelElement(EObject newDomainModelElement) {
		EObject oldDomainModelElement = domainModelElement;
		domainModelElement = newDomainModelElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_BASE_ELEMENT__DOMAIN_MODEL_ELEMENT, oldDomainModelElement, domainModelElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getForegroundColor() {
		return foregroundColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForegroundColor(String newForegroundColor) {
		String oldForegroundColor = foregroundColor;
		foregroundColor = newForegroundColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_BASE_ELEMENT__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getUin() {
		return uin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUin(int newUin) {
		int oldUin = uin;
		uin = newUin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_BASE_ELEMENT__UIN, oldUin, uin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getVisualID() {
		return visualID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisualID(int newVisualID) {
		int oldVisualID = visualID;
		visualID = newVisualID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_BASE_ELEMENT__VISUAL_ID, oldVisualID, visualID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap getRunTimeProperties() {
		if (runTimeProperties == null) {
			runTimeProperties = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, DiagramRTPackage.DIAGRAM_BASE_ELEMENT__RUN_TIME_PROPERTIES);
		}
		return runTimeProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public abstract DiagramCanvas getDiagram();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getProperty(String propertyName) {
		EAttribute metaAttr = findEAttribute(propertyName);
		if (metaAttr != null) {
			return (String) eGet(metaAttr);
		}
		return (String) getRunTimeProperties().get(propertyName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setProperty(String propertyName, String value) {
		EAttribute metaAttr = findEAttribute(propertyName);
		if (metaAttr != null) {
			eSet(metaAttr, value);
			return;
		}
		if (value == null) {
			getRunTimeProperties().remove(propertyName);
		} else {
			getRunTimeProperties().put(propertyName, value);
		}
	}

	private EAttribute findEAttribute(String attrName) {
		EStructuralFeature sf = eClass().getEStructuralFeature(attrName);
		if (sf instanceof EAttribute) {
			return (EAttribute) sf;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__RUN_TIME_PROPERTIES:
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
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__DOMAIN_MODEL_ELEMENT:
				if (resolve) return getDomainModelElement();
				return basicGetDomainModelElement();
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__FOREGROUND_COLOR:
				return getForegroundColor();
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__UIN:
				return new Integer(getUin());
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__VISUAL_ID:
				return new Integer(getVisualID());
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__RUN_TIME_PROPERTIES:
				return getRunTimeProperties();
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
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__DOMAIN_MODEL_ELEMENT:
				setDomainModelElement((EObject)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__FOREGROUND_COLOR:
				setForegroundColor((String)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__UIN:
				setUin(((Integer)newValue).intValue());
				return;
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__VISUAL_ID:
				setVisualID(((Integer)newValue).intValue());
				return;
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__RUN_TIME_PROPERTIES:
				getRunTimeProperties().clear();
				getRunTimeProperties().addAll((Collection)newValue);
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
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__DOMAIN_MODEL_ELEMENT:
				setDomainModelElement((EObject)null);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__FOREGROUND_COLOR:
				setForegroundColor(FOREGROUND_COLOR_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__UIN:
				setUin(UIN_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__VISUAL_ID:
				setVisualID(VISUAL_ID_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__RUN_TIME_PROPERTIES:
				getRunTimeProperties().clear();
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
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__DOMAIN_MODEL_ELEMENT:
				return domainModelElement != null;
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__FOREGROUND_COLOR:
				return FOREGROUND_COLOR_EDEFAULT == null ? foregroundColor != null : !FOREGROUND_COLOR_EDEFAULT.equals(foregroundColor);
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__UIN:
				return uin != UIN_EDEFAULT;
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__VISUAL_ID:
				return visualID != VISUAL_ID_EDEFAULT;
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT__RUN_TIME_PROPERTIES:
				return runTimeProperties != null && !runTimeProperties.isEmpty();
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
		result.append(" (foregroundColor: ");
		result.append(foregroundColor);
		result.append(", uin: ");
		result.append(uin);
		result.append(", visualID: ");
		result.append(visualID);
		result.append(')');
		return result.toString();
	}

} //DiagramBaseElementImpl
