/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Base Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getDomainModelElement <em>Domain Model Element</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getForegroundColor <em>Foreground Color</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getUin <em>Uin</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getVisualID <em>Visual ID</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getRunTimeProperties <em>Run Time Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramBaseElement()
 * @model abstract="true"
 * @generated
 */
public interface DiagramBaseElement extends EObject{
	/**
	 * Returns the value of the '<em><b>Domain Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Model Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Model Element</em>' reference.
	 * @see #setDomainModelElement(EObject)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramBaseElement_DomainModelElement()
	 * @model
	 * @generated
	 */
	EObject getDomainModelElement();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getDomainModelElement <em>Domain Model Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Model Element</em>' reference.
	 * @see #getDomainModelElement()
	 * @generated
	 */
	void setDomainModelElement(EObject value);

	/**
	 * Returns the value of the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * String, not datatype(org.eclipse.swt.graphics.Color) solves next issues: 1) show it's possible to be independent from GEF, 2) easy to serialize
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Foreground Color</em>' attribute.
	 * @see #setForegroundColor(String)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramBaseElement_ForegroundColor()
	 * @model
	 * @generated
	 */
	String getForegroundColor();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getForegroundColor <em>Foreground Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Foreground Color</em>' attribute.
	 * @see #getForegroundColor()
	 * @generated
	 */
	void setForegroundColor(String value);

	/**
	 * Returns the value of the '<em><b>Uin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uin</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uin</em>' attribute.
	 * @see #setUin(int)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramBaseElement_Uin()
	 * @model id="true" required="true"
	 * @generated
	 */
	int getUin();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getUin <em>Uin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uin</em>' attribute.
	 * @see #getUin()
	 * @generated
	 */
	void setUin(int value);

	/**
	 * Returns the value of the '<em><b>Visual ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visual ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visual ID</em>' attribute.
	 * @see #setVisualID(int)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramBaseElement_VisualID()
	 * @model required="true"
	 * @generated
	 */
	int getVisualID();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getVisualID <em>Visual ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visual ID</em>' attribute.
	 * @see #getVisualID()
	 * @generated
	 */
	void setVisualID(int value);

	/**
	 * Returns the value of the '<em><b>Run Time Properties</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Run Time Properties</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Run Time Properties</em>' map.
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramBaseElement_RunTimeProperties()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry" keyType="java.lang.String" valueType="java.lang.String"
	 * @generated
	 */
	EMap getRunTimeProperties();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	DiagramCanvas getDiagram();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model propertyNameRequired="true"
	 * @generated
	 */
	String getProperty(String propertyName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model propertyNameRequired="true"
	 * @generated
	 */
	void setProperty(String propertyName, String value);

} // DiagramBaseElement
