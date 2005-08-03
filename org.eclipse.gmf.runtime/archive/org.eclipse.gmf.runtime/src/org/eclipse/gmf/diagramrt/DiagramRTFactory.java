/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage
 * @generated
 */
public interface DiagramRTFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DiagramRTFactory eINSTANCE = new org.eclipse.gmf.diagramrt.impl.DiagramRTFactoryImpl();

	/**
	 * Returns a new object of class '<em>Diagram Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Diagram Node</em>'.
	 * @generated
	 */
	DiagramNode createDiagramNode();

	/**
	 * Returns a new object of class '<em>Diagram Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Diagram Link</em>'.
	 * @generated
	 */
	DiagramLink createDiagramLink();

	/**
	 * Returns a new object of class '<em>Diagram Canvas</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Diagram Canvas</em>'.
	 * @generated
	 */
	DiagramCanvas createDiagramCanvas();

	/**
	 * Returns a new object of class '<em>Child Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Child Node</em>'.
	 * @generated
	 */
	ChildNode createChildNode();

	/**
	 * Returns a new object of class '<em>Rigid Child Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rigid Child Node</em>'.
	 * @generated
	 */
	RigidChildNode createRigidChildNode();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DiagramRTPackage getDiagramRTPackage();

} //DiagramRTFactory
