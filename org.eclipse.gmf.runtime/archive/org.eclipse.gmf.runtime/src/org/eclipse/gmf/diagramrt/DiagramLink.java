/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramLink#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramLink#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramLink#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramLink()
 * @model
 * @generated
 */
public interface DiagramLink extends DiagramBaseElement{
	/**
	 * Returns the value of the '<em><b>Diagram</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' container reference.
	 * @see #setDiagram(DiagramCanvas)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramLink_Diagram()
	 * @see org.eclipse.gmf.diagramrt.DiagramCanvas#getLinks
	 * @model opposite="links" resolveProxies="false" required="true"
	 * @generated
	 */
	DiagramCanvas getDiagram();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramLink#getDiagram <em>Diagram</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' container reference.
	 * @see #getDiagram()
	 * @generated
	 */
	void setDiagram(DiagramCanvas value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.diagramrt.DiagramNode#getOutgoingLinks <em>Outgoing Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(DiagramNode)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramLink_Source()
	 * @see org.eclipse.gmf.diagramrt.DiagramNode#getOutgoingLinks
	 * @model opposite="outgoingLinks" resolveProxies="false"
	 * @generated
	 */
	DiagramNode getSource();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramLink#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(DiagramNode value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.diagramrt.DiagramNode#getIncomingLinks <em>Incoming Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(DiagramNode)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramLink_Target()
	 * @see org.eclipse.gmf.diagramrt.DiagramNode#getIncomingLinks
	 * @model opposite="incomingLinks" resolveProxies="false"
	 * @generated
	 */
	DiagramNode getTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramLink#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(DiagramNode value);

} // DiagramLink
