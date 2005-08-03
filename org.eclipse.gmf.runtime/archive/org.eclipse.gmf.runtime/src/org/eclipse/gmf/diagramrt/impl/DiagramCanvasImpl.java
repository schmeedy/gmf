/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramLink;
import org.eclipse.gmf.diagramrt.DiagramNode;
import org.eclipse.gmf.diagramrt.DiagramRTPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram Canvas</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramCanvasImpl#getLinks <em>Links</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramCanvasImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramCanvasImpl#getDomainContainerObject <em>Domain Container Object</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramCanvasImpl#getDomainResource <em>Domain Resource</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramCanvasImpl#getLastAssignedUin <em>Last Assigned Uin</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DiagramCanvasImpl extends EObjectImpl implements DiagramCanvas {
	/**
	 * The cached value of the '{@link #getLinks() <em>Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinks()
	 * @generated
	 * @ordered
	 */
	protected EList links = null;

	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList nodes = null;

	/**
	 * The cached value of the '{@link #getDomainContainerObject() <em>Domain Container Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainContainerObject()
	 * @generated
	 * @ordered
	 */
	protected EObject domainContainerObject = null;

	/**
	 * The default value of the '{@link #getDomainResource() <em>Domain Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainResource()
	 * @generated
	 * @ordered
	 */
	protected static final Resource DOMAIN_RESOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDomainResource() <em>Domain Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainResource()
	 * @generated
	 * @ordered
	 */
	protected Resource domainResource = DOMAIN_RESOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastAssignedUin() <em>Last Assigned Uin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastAssignedUin()
	 * @generated
	 * @ordered
	 */
	protected static final int LAST_ASSIGNED_UIN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLastAssignedUin() <em>Last Assigned Uin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastAssignedUin()
	 * @generated
	 * @ordered
	 */
	protected int lastAssignedUin = LAST_ASSIGNED_UIN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramCanvasImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DiagramRTPackage.eINSTANCE.getDiagramCanvas();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLinks() {
		if (links == null) {
			links = new EObjectContainmentWithInverseEList(DiagramLink.class, this, DiagramRTPackage.DIAGRAM_CANVAS__LINKS, DiagramRTPackage.DIAGRAM_LINK__DIAGRAM);
		}
		return links;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentWithInverseEList(DiagramNode.class, this, DiagramRTPackage.DIAGRAM_CANVAS__NODES, DiagramRTPackage.DIAGRAM_NODE__DIAGRAM);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getDomainContainerObject() {
		if (domainContainerObject != null && domainContainerObject.eIsProxy()) {
			EObject oldDomainContainerObject = domainContainerObject;
			domainContainerObject = (EObject)eResolveProxy((InternalEObject)domainContainerObject);
			if (domainContainerObject != oldDomainContainerObject) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramRTPackage.DIAGRAM_CANVAS__DOMAIN_CONTAINER_OBJECT, oldDomainContainerObject, domainContainerObject));
			}
		}
		return domainContainerObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetDomainContainerObject() {
		return domainContainerObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomainContainerObject(EObject newDomainContainerObject) {
		EObject oldDomainContainerObject = domainContainerObject;
		domainContainerObject = newDomainContainerObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_CANVAS__DOMAIN_CONTAINER_OBJECT, oldDomainContainerObject, domainContainerObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resource getDomainResource() {
		return domainResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomainResource(Resource newDomainResource) {
		Resource oldDomainResource = domainResource;
		domainResource = newDomainResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_CANVAS__DOMAIN_RESOURCE, oldDomainResource, domainResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLastAssignedUin() {
		return lastAssignedUin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastAssignedUin(int newLastAssignedUin) {
		int oldLastAssignedUin = lastAssignedUin;
		lastAssignedUin = newLastAssignedUin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_CANVAS__LAST_ASSIGNED_UIN, oldLastAssignedUin, lastAssignedUin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int nextAvailableUin() {
		int rv = getLastAssignedUin() + 1;
		while (isUinInUse(rv)) {
			rv += Math.round(Math.random() * 10);
		}
		setLastAssignedUin(rv);
		return rv;
	}

	/**
	 * consider caching all current ids to reduce check time
	 */
	private boolean isUinInUse(int uinCandidate) {
		for (Iterator it = getNodes().iterator(); it.hasNext();) {
			if (((DiagramNode) it.next()).getUin() == uinCandidate) {
				return true;
			}
		}
		for (Iterator it = getLinks().iterator(); it.hasNext();) {
			if (((DiagramLink) it.next()).getUin() == uinCandidate) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case DiagramRTPackage.DIAGRAM_CANVAS__LINKS:
					return ((InternalEList)getLinks()).basicAdd(otherEnd, msgs);
				case DiagramRTPackage.DIAGRAM_CANVAS__NODES:
					return ((InternalEList)getNodes()).basicAdd(otherEnd, msgs);
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
				case DiagramRTPackage.DIAGRAM_CANVAS__LINKS:
					return ((InternalEList)getLinks()).basicRemove(otherEnd, msgs);
				case DiagramRTPackage.DIAGRAM_CANVAS__NODES:
					return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
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
			case DiagramRTPackage.DIAGRAM_CANVAS__LINKS:
				return getLinks();
			case DiagramRTPackage.DIAGRAM_CANVAS__NODES:
				return getNodes();
			case DiagramRTPackage.DIAGRAM_CANVAS__DOMAIN_CONTAINER_OBJECT:
				if (resolve) return getDomainContainerObject();
				return basicGetDomainContainerObject();
			case DiagramRTPackage.DIAGRAM_CANVAS__DOMAIN_RESOURCE:
				return getDomainResource();
			case DiagramRTPackage.DIAGRAM_CANVAS__LAST_ASSIGNED_UIN:
				return new Integer(getLastAssignedUin());
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
			case DiagramRTPackage.DIAGRAM_CANVAS__LINKS:
				getLinks().clear();
				getLinks().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_CANVAS__NODES:
				getNodes().clear();
				getNodes().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_CANVAS__DOMAIN_CONTAINER_OBJECT:
				setDomainContainerObject((EObject)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_CANVAS__DOMAIN_RESOURCE:
				setDomainResource((Resource)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_CANVAS__LAST_ASSIGNED_UIN:
				setLastAssignedUin(((Integer)newValue).intValue());
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
			case DiagramRTPackage.DIAGRAM_CANVAS__LINKS:
				getLinks().clear();
				return;
			case DiagramRTPackage.DIAGRAM_CANVAS__NODES:
				getNodes().clear();
				return;
			case DiagramRTPackage.DIAGRAM_CANVAS__DOMAIN_CONTAINER_OBJECT:
				setDomainContainerObject((EObject)null);
				return;
			case DiagramRTPackage.DIAGRAM_CANVAS__DOMAIN_RESOURCE:
				setDomainResource(DOMAIN_RESOURCE_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_CANVAS__LAST_ASSIGNED_UIN:
				setLastAssignedUin(LAST_ASSIGNED_UIN_EDEFAULT);
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
			case DiagramRTPackage.DIAGRAM_CANVAS__LINKS:
				return links != null && !links.isEmpty();
			case DiagramRTPackage.DIAGRAM_CANVAS__NODES:
				return nodes != null && !nodes.isEmpty();
			case DiagramRTPackage.DIAGRAM_CANVAS__DOMAIN_CONTAINER_OBJECT:
				return domainContainerObject != null;
			case DiagramRTPackage.DIAGRAM_CANVAS__DOMAIN_RESOURCE:
				return DOMAIN_RESOURCE_EDEFAULT == null ? domainResource != null : !DOMAIN_RESOURCE_EDEFAULT.equals(domainResource);
			case DiagramRTPackage.DIAGRAM_CANVAS__LAST_ASSIGNED_UIN:
				return lastAssignedUin != LAST_ASSIGNED_UIN_EDEFAULT;
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
		result.append(" (domainResource: ");
		result.append(domainResource);
		result.append(", lastAssignedUin: ");
		result.append(lastAssignedUin);
		result.append(')');
		return result.toString();
	}

} //DiagramCanvasImpl
