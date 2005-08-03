/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramNode#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramNode#getIncomingLinks <em>Incoming Links</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramNode#getOutgoingLinks <em>Outgoing Links</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramNode#getChildNodes <em>Child Nodes</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramNode#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.gmf.diagramrt.DiagramNode#getSize <em>Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramNode()
 * @model
 * @generated
 */
public interface DiagramNode extends DiagramBaseNode {
	/**
	 * Returns the value of the '<em><b>Diagram</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' container reference.
	 * @see #setDiagram(DiagramCanvas)
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramNode_Diagram()
	 * @see org.eclipse.gmf.diagramrt.DiagramCanvas#getNodes
	 * @model opposite="nodes" resolveProxies="false" required="true"
	 * @generated
	 */
	DiagramCanvas getDiagram();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramNode#getDiagram <em>Diagram</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' container reference.
	 * @see #getDiagram()
	 * @generated
	 */
	void setDiagram(DiagramCanvas value);

	/**
	 * Returns the value of the '<em><b>Incoming Links</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmf.diagramrt.DiagramLink}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.diagramrt.DiagramLink#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Links</em>' reference list.
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramNode_IncomingLinks()
	 * @see org.eclipse.gmf.diagramrt.DiagramLink#getTarget
	 * @model type="org.eclipse.gmf.diagramrt.DiagramLink" opposite="target" resolveProxies="false"
	 * @generated
	 */
	EList getIncomingLinks();

	/**
	 * Returns the value of the '<em><b>Outgoing Links</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmf.diagramrt.DiagramLink}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.diagramrt.DiagramLink#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Links</em>' reference list.
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramNode_OutgoingLinks()
	 * @see org.eclipse.gmf.diagramrt.DiagramLink#getSource
	 * @model type="org.eclipse.gmf.diagramrt.DiagramLink" opposite="source" resolveProxies="false"
	 * @generated
	 */
	EList getOutgoingLinks();

	/**
	 * Returns the value of the '<em><b>Child Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gmf.diagramrt.ChildNode}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmf.diagramrt.ChildNode#getParentNode <em>Parent Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Nodes</em>' containment reference list.
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramNode_ChildNodes()
	 * @see org.eclipse.gmf.diagramrt.ChildNode#getParentNode
	 * @model type="org.eclipse.gmf.diagramrt.ChildNode" opposite="parentNode" containment="true"
	 * @generated
	 */
	EList getChildNodes();

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
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramNode_Location()
	 * @model default="20,20" dataType="org.eclipse.gmf.diagramrt.Point"
	 * @generated
	 */
	Point getLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramNode#getLocation <em>Location</em>}' attribute.
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
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#getDiagramNode_Size()
	 * @model default="100, 50" dataType="org.eclipse.gmf.diagramrt.Dimension"
	 * @generated
	 */
	Dimension getSize();

	/**
	 * Sets the value of the '{@link org.eclipse.gmf.diagramrt.DiagramNode#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(Dimension value);

} // DiagramNode
