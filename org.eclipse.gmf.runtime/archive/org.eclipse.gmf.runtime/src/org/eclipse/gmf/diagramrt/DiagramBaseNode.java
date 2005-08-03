/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Base Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramBaseNode#getBackgroundColor <em>Background Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramBaseNode()
 * @model abstract="true"
 * @generated
 */
public interface DiagramBaseNode extends DiagramBaseElement {
	/**
	 * Returns the value of the '<em><b>Background Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Background Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Background Color</em>' attribute.
	 * @see #setBackgroundColor(String)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramBaseNode_BackgroundColor()
	 * @model
	 * @generated
	 */
	String getBackgroundColor();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramBaseNode#getBackgroundColor <em>Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Background Color</em>' attribute.
	 * @see #getBackgroundColor()
	 * @generated
	 */
	void setBackgroundColor(String value);

} // DiagramBaseNode
