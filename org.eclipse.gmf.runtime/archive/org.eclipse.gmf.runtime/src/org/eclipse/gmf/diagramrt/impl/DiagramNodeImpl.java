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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.gmf.diagramrt.ChildNode;
import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramLink;
import org.eclipse.gmf.diagramrt.DiagramNode;
import org.eclipse.gmf.diagramrt.DiagramRTFactory;
import org.eclipse.gmf.diagramrt.DiagramRTPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramNodeImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramNodeImpl#getIncomingLinks <em>Incoming Links</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramNodeImpl#getOutgoingLinks <em>Outgoing Links</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramNodeImpl#getChildNodes <em>Child Nodes</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramNodeImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.impl.DiagramNodeImpl#getSize <em>Size</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DiagramNodeImpl extends DiagramBaseNodeImpl implements DiagramNode {
	/**
	 * The cached value of the '{@link #getIncomingLinks() <em>Incoming Links</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncomingLinks()
	 * @generated
	 * @ordered
	 */
	protected EList incomingLinks = null;

	/**
	 * The cached value of the '{@link #getOutgoingLinks() <em>Outgoing Links</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoingLinks()
	 * @generated
	 * @ordered
	 */
	protected EList outgoingLinks = null;

	/**
	 * The cached value of the '{@link #getChildNodes() <em>Child Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildNodes()
	 * @generated
	 * @ordered
	 */
	protected EList childNodes = null;

	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final Point LOCATION_EDEFAULT = (Point)DiagramRTFactory.eINSTANCE.createFromString(DiagramRTPackage.eINSTANCE.getPoint(), "20,20");

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected Point location = LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final Dimension SIZE_EDEFAULT = (Dimension)DiagramRTFactory.eINSTANCE.createFromString(DiagramRTPackage.eINSTANCE.getDimension(), "100, 50");

	/**
	 * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected Dimension size = SIZE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DiagramRTPackage.eINSTANCE.getDiagramNode();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramCanvas getDiagram() {
		if (eContainerFeatureID != DiagramRTPackage.DIAGRAM_NODE__DIAGRAM) return null;
		return (DiagramCanvas)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagram(DiagramCanvas newDiagram) {
		if (newDiagram != eContainer || (eContainerFeatureID != DiagramRTPackage.DIAGRAM_NODE__DIAGRAM && newDiagram != null)) {
			if (EcoreUtil.isAncestor(this, newDiagram))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDiagram != null)
				msgs = ((InternalEObject)newDiagram).eInverseAdd(this, DiagramRTPackage.DIAGRAM_CANVAS__NODES, DiagramCanvas.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newDiagram, DiagramRTPackage.DIAGRAM_NODE__DIAGRAM, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_NODE__DIAGRAM, newDiagram, newDiagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIncomingLinks() {
		if (incomingLinks == null) {
			incomingLinks = new EObjectWithInverseEList(DiagramLink.class, this, DiagramRTPackage.DIAGRAM_NODE__INCOMING_LINKS, DiagramRTPackage.DIAGRAM_LINK__TARGET);
		}
		return incomingLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOutgoingLinks() {
		if (outgoingLinks == null) {
			outgoingLinks = new EObjectWithInverseEList(DiagramLink.class, this, DiagramRTPackage.DIAGRAM_NODE__OUTGOING_LINKS, DiagramRTPackage.DIAGRAM_LINK__SOURCE);
		}
		return outgoingLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getChildNodes() {
		if (childNodes == null) {
			childNodes = new EObjectContainmentWithInverseEList(ChildNode.class, this, DiagramRTPackage.DIAGRAM_NODE__CHILD_NODES, DiagramRTPackage.CHILD_NODE__PARENT_NODE);
		}
		return childNodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocation(Point newLocation) {
		Point oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_NODE__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dimension getSize() {
		return size;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSize(Dimension newSize) {
		Dimension oldSize = size;
		size = newSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiagramRTPackage.DIAGRAM_NODE__SIZE, oldSize, size));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case DiagramRTPackage.DIAGRAM_NODE__DIAGRAM:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, DiagramRTPackage.DIAGRAM_NODE__DIAGRAM, msgs);
				case DiagramRTPackage.DIAGRAM_NODE__INCOMING_LINKS:
					return ((InternalEList)getIncomingLinks()).basicAdd(otherEnd, msgs);
				case DiagramRTPackage.DIAGRAM_NODE__OUTGOING_LINKS:
					return ((InternalEList)getOutgoingLinks()).basicAdd(otherEnd, msgs);
				case DiagramRTPackage.DIAGRAM_NODE__CHILD_NODES:
					return ((InternalEList)getChildNodes()).basicAdd(otherEnd, msgs);
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
				case DiagramRTPackage.DIAGRAM_NODE__RUN_TIME_PROPERTIES:
					return ((InternalEList)getRunTimeProperties()).basicRemove(otherEnd, msgs);
				case DiagramRTPackage.DIAGRAM_NODE__DIAGRAM:
					return eBasicSetContainer(null, DiagramRTPackage.DIAGRAM_NODE__DIAGRAM, msgs);
				case DiagramRTPackage.DIAGRAM_NODE__INCOMING_LINKS:
					return ((InternalEList)getIncomingLinks()).basicRemove(otherEnd, msgs);
				case DiagramRTPackage.DIAGRAM_NODE__OUTGOING_LINKS:
					return ((InternalEList)getOutgoingLinks()).basicRemove(otherEnd, msgs);
				case DiagramRTPackage.DIAGRAM_NODE__CHILD_NODES:
					return ((InternalEList)getChildNodes()).basicRemove(otherEnd, msgs);
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
				case DiagramRTPackage.DIAGRAM_NODE__DIAGRAM:
					return eContainer.eInverseRemove(this, DiagramRTPackage.DIAGRAM_CANVAS__NODES, DiagramCanvas.class, msgs);
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
			case DiagramRTPackage.DIAGRAM_NODE__DOMAIN_MODEL_ELEMENT:
				if (resolve) return getDomainModelElement();
				return basicGetDomainModelElement();
			case DiagramRTPackage.DIAGRAM_NODE__FOREGROUND_COLOR:
				return getForegroundColor();
			case DiagramRTPackage.DIAGRAM_NODE__UIN:
				return new Integer(getUin());
			case DiagramRTPackage.DIAGRAM_NODE__VISUAL_ID:
				return new Integer(getVisualID());
			case DiagramRTPackage.DIAGRAM_NODE__RUN_TIME_PROPERTIES:
				return getRunTimeProperties();
			case DiagramRTPackage.DIAGRAM_NODE__BACKGROUND_COLOR:
				return getBackgroundColor();
			case DiagramRTPackage.DIAGRAM_NODE__DIAGRAM:
				return getDiagram();
			case DiagramRTPackage.DIAGRAM_NODE__INCOMING_LINKS:
				return getIncomingLinks();
			case DiagramRTPackage.DIAGRAM_NODE__OUTGOING_LINKS:
				return getOutgoingLinks();
			case DiagramRTPackage.DIAGRAM_NODE__CHILD_NODES:
				return getChildNodes();
			case DiagramRTPackage.DIAGRAM_NODE__LOCATION:
				return getLocation();
			case DiagramRTPackage.DIAGRAM_NODE__SIZE:
				return getSize();
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
			case DiagramRTPackage.DIAGRAM_NODE__DOMAIN_MODEL_ELEMENT:
				setDomainModelElement((EObject)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__FOREGROUND_COLOR:
				setForegroundColor((String)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__UIN:
				setUin(((Integer)newValue).intValue());
				return;
			case DiagramRTPackage.DIAGRAM_NODE__VISUAL_ID:
				setVisualID(((Integer)newValue).intValue());
				return;
			case DiagramRTPackage.DIAGRAM_NODE__RUN_TIME_PROPERTIES:
				getRunTimeProperties().clear();
				getRunTimeProperties().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__BACKGROUND_COLOR:
				setBackgroundColor((String)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__DIAGRAM:
				setDiagram((DiagramCanvas)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__INCOMING_LINKS:
				getIncomingLinks().clear();
				getIncomingLinks().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__OUTGOING_LINKS:
				getOutgoingLinks().clear();
				getOutgoingLinks().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__CHILD_NODES:
				getChildNodes().clear();
				getChildNodes().addAll((Collection)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__LOCATION:
				setLocation((Point)newValue);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__SIZE:
				setSize((Dimension)newValue);
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
			case DiagramRTPackage.DIAGRAM_NODE__DOMAIN_MODEL_ELEMENT:
				setDomainModelElement((EObject)null);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__FOREGROUND_COLOR:
				setForegroundColor(FOREGROUND_COLOR_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__UIN:
				setUin(UIN_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__VISUAL_ID:
				setVisualID(VISUAL_ID_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__RUN_TIME_PROPERTIES:
				getRunTimeProperties().clear();
				return;
			case DiagramRTPackage.DIAGRAM_NODE__BACKGROUND_COLOR:
				setBackgroundColor(BACKGROUND_COLOR_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__DIAGRAM:
				setDiagram((DiagramCanvas)null);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__INCOMING_LINKS:
				getIncomingLinks().clear();
				return;
			case DiagramRTPackage.DIAGRAM_NODE__OUTGOING_LINKS:
				getOutgoingLinks().clear();
				return;
			case DiagramRTPackage.DIAGRAM_NODE__CHILD_NODES:
				getChildNodes().clear();
				return;
			case DiagramRTPackage.DIAGRAM_NODE__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case DiagramRTPackage.DIAGRAM_NODE__SIZE:
				setSize(SIZE_EDEFAULT);
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
			case DiagramRTPackage.DIAGRAM_NODE__DOMAIN_MODEL_ELEMENT:
				return domainModelElement != null;
			case DiagramRTPackage.DIAGRAM_NODE__FOREGROUND_COLOR:
				return FOREGROUND_COLOR_EDEFAULT == null ? foregroundColor != null : !FOREGROUND_COLOR_EDEFAULT.equals(foregroundColor);
			case DiagramRTPackage.DIAGRAM_NODE__UIN:
				return uin != UIN_EDEFAULT;
			case DiagramRTPackage.DIAGRAM_NODE__VISUAL_ID:
				return visualID != VISUAL_ID_EDEFAULT;
			case DiagramRTPackage.DIAGRAM_NODE__RUN_TIME_PROPERTIES:
				return runTimeProperties != null && !runTimeProperties.isEmpty();
			case DiagramRTPackage.DIAGRAM_NODE__BACKGROUND_COLOR:
				return BACKGROUND_COLOR_EDEFAULT == null ? backgroundColor != null : !BACKGROUND_COLOR_EDEFAULT.equals(backgroundColor);
			case DiagramRTPackage.DIAGRAM_NODE__DIAGRAM:
				return getDiagram() != null;
			case DiagramRTPackage.DIAGRAM_NODE__INCOMING_LINKS:
				return incomingLinks != null && !incomingLinks.isEmpty();
			case DiagramRTPackage.DIAGRAM_NODE__OUTGOING_LINKS:
				return outgoingLinks != null && !outgoingLinks.isEmpty();
			case DiagramRTPackage.DIAGRAM_NODE__CHILD_NODES:
				return childNodes != null && !childNodes.isEmpty();
			case DiagramRTPackage.DIAGRAM_NODE__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case DiagramRTPackage.DIAGRAM_NODE__SIZE:
				return SIZE_EDEFAULT == null ? size != null : !SIZE_EDEFAULT.equals(size);
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
		result.append(" (location: ");
		result.append(location);
		result.append(", size: ");
		result.append(size);
		result.append(')');
		return result.toString();
	}

} //DiagramNodeImpl
