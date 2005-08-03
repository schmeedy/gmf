/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.diagramrt.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage
 * @generated
 */
public class DiagramRTSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DiagramRTPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramRTSwitch() {
		if (modelPackage == null) {
			modelPackage = DiagramRTPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case DiagramRTPackage.DIAGRAM_NODE: {
				DiagramNode diagramNode = (DiagramNode)theEObject;
				Object result = caseDiagramNode(diagramNode);
				if (result == null) result = caseDiagramBaseNode(diagramNode);
				if (result == null) result = caseDiagramBaseElement(diagramNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DiagramRTPackage.DIAGRAM_LINK: {
				DiagramLink diagramLink = (DiagramLink)theEObject;
				Object result = caseDiagramLink(diagramLink);
				if (result == null) result = caseDiagramBaseElement(diagramLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DiagramRTPackage.DIAGRAM_CANVAS: {
				DiagramCanvas diagramCanvas = (DiagramCanvas)theEObject;
				Object result = caseDiagramCanvas(diagramCanvas);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DiagramRTPackage.DIAGRAM_BASE_ELEMENT: {
				DiagramBaseElement diagramBaseElement = (DiagramBaseElement)theEObject;
				Object result = caseDiagramBaseElement(diagramBaseElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DiagramRTPackage.DIAGRAM_BASE_NODE: {
				DiagramBaseNode diagramBaseNode = (DiagramBaseNode)theEObject;
				Object result = caseDiagramBaseNode(diagramBaseNode);
				if (result == null) result = caseDiagramBaseElement(diagramBaseNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DiagramRTPackage.CHILD_NODE: {
				ChildNode childNode = (ChildNode)theEObject;
				Object result = caseChildNode(childNode);
				if (result == null) result = caseDiagramNode(childNode);
				if (result == null) result = caseDiagramBaseNode(childNode);
				if (result == null) result = caseDiagramBaseElement(childNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DiagramRTPackage.RIGID_CHILD_NODE: {
				RigidChildNode rigidChildNode = (RigidChildNode)theEObject;
				Object result = caseRigidChildNode(rigidChildNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Diagram Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Diagram Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDiagramNode(DiagramNode object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Diagram Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Diagram Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDiagramLink(DiagramLink object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Diagram Canvas</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Diagram Canvas</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDiagramCanvas(DiagramCanvas object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Diagram Base Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Diagram Base Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDiagramBaseElement(DiagramBaseElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Diagram Base Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Diagram Base Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDiagramBaseNode(DiagramBaseNode object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Child Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Child Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseChildNode(ChildNode object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Rigid Child Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Rigid Child Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRigidChildNode(RigidChildNode object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //DiagramRTSwitch
