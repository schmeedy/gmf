/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Canvas</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getLinks <em>Links</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getDomainContainerObject <em>Domain Container Object</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getDomainResource <em>Domain Resource</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getLastAssignedUin <em>Last Assigned Uin</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramCanvas()
 * @model
 * @generated
 */
public interface DiagramCanvas extends EObject{
	/**
	 * Returns the value of the '<em><b>Links</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmf.diagramrt.DiagramLink}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.diagramrt.DiagramLink#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Links</em>' containment reference list.
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramCanvas_Links()
	 * @see org.eclipse.gmf.diagramrt.DiagramLink#getDiagram
	 * @model type="org.eclipse.gmf.diagramrt.DiagramLink" opposite="diagram" containment="true" resolveProxies="false"
	 * @generated
	 */
	EList getLinks();

	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmf.diagramrt.DiagramNode}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.diagramrt.DiagramNode#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramCanvas_Nodes()
	 * @see org.eclipse.gmf.diagramrt.DiagramNode#getDiagram
	 * @model type="org.eclipse.gmf.diagramrt.DiagramNode" opposite="diagram" containment="true" resolveProxies="false"
	 * @generated
	 */
	EList getNodes();

	/**
	 * Returns the value of the '<em><b>Domain Container Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Container Object</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Container Object</em>' reference.
	 * @see #setDomainContainerObject(EObject)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramCanvas_DomainContainerObject()
	 * @model required="true"
	 * @generated
	 */
	EObject getDomainContainerObject();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getDomainContainerObject <em>Domain Container Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Container Object</em>' reference.
	 * @see #getDomainContainerObject()
	 * @generated
	 */
	void setDomainContainerObject(EObject value);

	/**
	 * Returns the value of the '<em><b>Domain Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Resource</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Resource</em>' attribute.
	 * @see #setDomainResource(Resource)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramCanvas_DomainResource()
	 * @model required="true"
	 * @generated
	 */
	Resource getDomainResource();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getDomainResource <em>Domain Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Resource</em>' attribute.
	 * @see #getDomainResource()
	 * @generated
	 */
	void setDomainResource(Resource value);

	/**
	 * Returns the value of the '<em><b>Last Assigned Uin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Assigned Uin</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Assigned Uin</em>' attribute.
	 * @see #setLastAssignedUin(int)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramCanvas_LastAssignedUin()
	 * @model
	 * @generated
	 */
	int getLastAssignedUin();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getLastAssignedUin <em>Last Assigned Uin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Assigned Uin</em>' attribute.
	 * @see #getLastAssignedUin()
	 * @generated
	 */
	void setLastAssignedUin(int value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int nextAvailableUin();

} // DiagramCanvas
