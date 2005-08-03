/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Child Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * XXX FIXME Looks like ChildNode should extend DiagramBaseNode, but for now I want to reuse NodeEditPart.javajet which heavily relies on DiagramNode
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.ChildNode#getParentNode <em>Parent Node</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.ChildNode#getGroupID <em>Group ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getChildNode()
 * @model
 * @generated
 */
public interface ChildNode extends DiagramNode{
	/**
	 * Returns the value of the '<em><b>Parent Node</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.diagramrt.DiagramNode#getChildNodes <em>Child Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Node</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Node</em>' container reference.
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getChildNode_ParentNode()
	 * @see org.eclipse.gmf.diagramrt.DiagramNode#getChildNodes
	 * @model opposite="childNodes" required="true" changeable="false"
	 * @generated
	 */
	DiagramNode getParentNode();

	/**
	 * Returns the value of the '<em><b>Group ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group ID</em>' attribute.
	 * @see #setGroupID(String)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getChildNode_GroupID()
	 * @model
	 * @generated
	 */
	String getGroupID();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.ChildNode#getGroupID <em>Group ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group ID</em>' attribute.
	 * @see #getGroupID()
	 * @generated
	 */
	void setGroupID(String value);

} // ChildNode
