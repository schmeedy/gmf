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

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramLink;
import org.eclipse.gmf.diagramrt.DiagramNode;
import org.eclipse.gmf.diagramrt.DiagramRTPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramLinkImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramLinkImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramLinkImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DiagramLinkImpl extends DiagramBaseElementImpl implements DiagramLink {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected DiagramNode source = null;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected DiagramNode target = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DiagramRTPackage.eINSTANCE.getDiagramLink();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramCanvas getDiagram() {
		if (eContainerFeatureID != DiagramRTPackage.DIAGRAM_LINK__DIAGRAM) return null;
		return (DiagramCanvas)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagram(DiagramCanvas newDiagram) {
		if (newDiagram != eContainer || (eContainerFeatureID != DiagramRTPackage.DIAGRAM_LINK__DIAGRAM && newDiagram != null)) {
			if (EcoreUtil.isAncestor(this, newDiagram))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDiagram != null)
				msgs = ((InternalEObject)newDiagram).eInverseAdd(this, DiagramRTPackage.DIAGRAM_CANVAS__LINKS, DiagramCanvas.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newDiagram, DiagramRTPackage.DIAGRAM_LINK__DIAGRAM, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_LINK__DIAGRAM, newDiagram, newDiagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramNode getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(DiagramNode newSource, NotificationChain msgs) {
		DiagramNode oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_LINK__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(DiagramNode newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, DiagramRTPackage.DIAGRAM_NODE__OUTGOING_LINKS, DiagramNode.class, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, DiagramRTPackage.DIAGRAM_NODE__OUTGOING_LINKS, DiagramNode.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_LINK__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramNode getTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(DiagramNode newTarget, NotificationChain msgs) {
		DiagramNode oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_LINK__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(DiagramNode newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, DiagramRTPackage.DIAGRAM_NODE__INCOMING_LINKS, DiagramNode.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, DiagramRTPackage.DIAGRAM_NODE__INCOMING_LINKS, DiagramNode.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_LINK__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case DiagramRTPackage.DIAGRAM_LINK__DIAGRAM:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, DiagramRTPackage.DIAGRAM_LINK__DIAGRAM, msgs);
				case DiagramRTPackage.DIAGRAM_LINK__SOURCE:
					if (source != null)
						msgs = ((InternalEObject)source).eInverseRemove(this, DiagramRTPackage.DIAGRAM_NODE__OUTGOING_LINKS, DiagramNode.class, msgs);
					return basicSetSource((DiagramNode)otherEnd, msgs);
				case DiagramRTPackage.DIAGRAM_LINK__TARGET:
					if (target != null)
						msgs = ((InternalEObject)target).eInverseRemove(this, DiagramRTPackage.DIAGRAM_NODE__INCOMING_LINKS, DiagramNode.class, msgs);
					return basicSetTarget((DiagramNode)otherEnd, msgs);
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
				case DiagramRTPackage.DIAGRAM_LINK__RUN_TIME_PROPERTIES:
					return ((InternalEList)getRunTimeProperties()).basicRemove(otherEnd, msgs);
				case DiagramRTPackage.DIAGRAM_LINK__DIAGRAM:
					return eBasicSetContainer(null, DiagramRTPackage.DIAGRAM_LINK__DIAGRAM, msgs);
				case DiagramRTPackage.DIAGRAM_LINK__SOURCE:
					return basicSetSource(null, msgs);
				case DiagramRTPackage.DIAGRAM_LINK__TARGET:
					return basicSetTarget(null, msgs);
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
				case DiagramRTPackage.DIAGRAM_LINK__DIAGRAM:
					return eContainer.eInverseRemove(this, DiagramRTPackage.DIAGRAM_CANVAS__LINKS, DiagramCanvas.class, msgs);
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
			case DiagramRTPackage.DIAGRAM_LINK__DOMAIN_MODEL_ELEMENT:
				if (resolve) return getDomainModelElement();
				return basicGetDomainModelElement();
			case DiagramRTPackage.DIAGRAM_LINK__FOREGROUND_COLOR:
				return getForegroundColor();
			case DiagramRTPackage.DIAGRAM_LINK__UIN:
				return new Integer(getUin());
			case DiagramRTPackage.DIAGRAM_LINK__VISUAL_ID:
				return new Integer(getVisualID());
			case DiagramRTPackage.DIAGRAM_LINK__RUN_TIME_PROPERTIES:
				return getRunTimeProperties();
			case DiagramRTPackage.DIAGRAM_LINK__DIAGRAM:
				return getDiagram();
			case DiagramRTPackage.DIAGRAM_LINK__SOURCE:
				return getSource();
			case DiagramRTPackage.DIAGRAM_LINK__TARGET:
				return getTarget();
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
			case DiagramRTPackage.DIAGRAM_LINK__DOMAIN_MODEL_ELEMENT:
				setDomainModelElement((EObject)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_LINK__FOREGROUND_COLOR:
				setForegroundColor((String)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_LINK__UIN:
				setUin(((Integer)newValue).intValue());
				return;
			case DiagramRTPackage.DIAGRAM_LINK__VISUAL_ID:
				setVisualID(((Integer)newValue).intValue());
				return;
			case DiagramRTPackage.DIAGRAM_LINK__RUN_TIME_PROPERTIES:
				getRunTimeProperties().clear();
				getRunTimeProperties().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_LINK__DIAGRAM:
				setDiagram((DiagramCanvas)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_LINK__SOURCE:
				setSource((DiagramNode)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_LINK__TARGET:
				setTarget((DiagramNode)newValue);
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
			case DiagramRTPackage.DIAGRAM_LINK__DOMAIN_MODEL_ELEMENT:
				setDomainModelElement((EObject)null);
				return;
			case DiagramRTPackage.DIAGRAM_LINK__FOREGROUND_COLOR:
				setForegroundColor(FOREGROUND_COLOR_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_LINK__UIN:
				setUin(UIN_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_LINK__VISUAL_ID:
				setVisualID(VISUAL_ID_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_LINK__RUN_TIME_PROPERTIES:
				getRunTimeProperties().clear();
				return;
			case DiagramRTPackage.DIAGRAM_LINK__DIAGRAM:
				setDiagram((DiagramCanvas)null);
				return;
			case DiagramRTPackage.DIAGRAM_LINK__SOURCE:
				setSource((DiagramNode)null);
				return;
			case DiagramRTPackage.DIAGRAM_LINK__TARGET:
				setTarget((DiagramNode)null);
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
			case DiagramRTPackage.DIAGRAM_LINK__DOMAIN_MODEL_ELEMENT:
				return domainModelElement != null;
			case DiagramRTPackage.DIAGRAM_LINK__FOREGROUND_COLOR:
				return FOREGROUND_COLOR_EDEFAULT == null ? foregroundColor != null : !FOREGROUND_COLOR_EDEFAULT.equals(foregroundColor);
			case DiagramRTPackage.DIAGRAM_LINK__UIN:
				return uin != UIN_EDEFAULT;
			case DiagramRTPackage.DIAGRAM_LINK__VISUAL_ID:
				return visualID != VISUAL_ID_EDEFAULT;
			case DiagramRTPackage.DIAGRAM_LINK__RUN_TIME_PROPERTIES:
				return runTimeProperties != null && !runTimeProperties.isEmpty();
			case DiagramRTPackage.DIAGRAM_LINK__DIAGRAM:
				return getDiagram() != null;
			case DiagramRTPackage.DIAGRAM_LINK__SOURCE:
				return source != null;
			case DiagramRTPackage.DIAGRAM_LINK__TARGET:
				return target != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //DiagramLinkImpl
