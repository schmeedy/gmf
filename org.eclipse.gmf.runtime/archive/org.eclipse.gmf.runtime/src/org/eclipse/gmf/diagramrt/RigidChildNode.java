/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rigid Child Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * FIXME Should extend ChildNode once it doesn't extend DiagramNode (duplicating attributes)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.RigidChildNode#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.RigidChildNode#getSize <em>Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getRigidChildNode()
 * @model
 * @generated
 */
public interface RigidChildNode extends EObject {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * The default value is <code>"20,20"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(Point)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getRigidChildNode_Location()
	 * @model default="20,20" dataType="org.eclipse.gmf.diagramrt.Point"
	 * @generated
	 */
	Point getLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.RigidChildNode#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(Point value);

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * The default value is <code>"100, 50"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(Dimension)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getRigidChildNode_Size()
	 * @model default="100, 50" dataType="org.eclipse.gmf.diagramrt.Dimension"
	 * @generated
	 */
	Dimension getSize();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.RigidChildNode#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(Dimension value);

} // RigidChildNode
