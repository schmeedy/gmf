/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.gmf.diagramrt.DiagramRTFactory
 * @model kind="package"
 * @generated
 */
public interface DiagramRTPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "diagramrt";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/gmf/2005/diagramrt";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "diagramrt";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DiagramRTPackage eINSTANCE = org.eclipse.gmf.diagramrt.impl.DiagramRTPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.diagramrt.impl.DiagramBaseElementImpl <em>Diagram Base Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramBaseElementImpl
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramRTPackageImpl#getDiagramBaseElement()
	 * @generated
	 */
	int DIAGRAM_BASE_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Domain Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_ELEMENT__DOMAIN_MODEL_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_ELEMENT__FOREGROUND_COLOR = 1;

	/**
	 * The feature id for the '<em><b>Uin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_ELEMENT__UIN = 2;

	/**
	 * The feature id for the '<em><b>Visual ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_ELEMENT__VISUAL_ID = 3;

	/**
	 * The feature id for the '<em><b>Run Time Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_ELEMENT__RUN_TIME_PROPERTIES = 4;

	/**
	 * The number of structural features of the the '<em>Diagram Base Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_ELEMENT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.diagramrt.impl.DiagramBaseNodeImpl <em>Diagram Base Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramBaseNodeImpl
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramRTPackageImpl#getDiagramBaseNode()
	 * @generated
	 */
	int DIAGRAM_BASE_NODE = 4;

	/**
	 * The feature id for the '<em><b>Domain Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_NODE__DOMAIN_MODEL_ELEMENT = DIAGRAM_BASE_ELEMENT__DOMAIN_MODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_NODE__FOREGROUND_COLOR = DIAGRAM_BASE_ELEMENT__FOREGROUND_COLOR;

	/**
	 * The feature id for the '<em><b>Uin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_NODE__UIN = DIAGRAM_BASE_ELEMENT__UIN;

	/**
	 * The feature id for the '<em><b>Visual ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_NODE__VISUAL_ID = DIAGRAM_BASE_ELEMENT__VISUAL_ID;

	/**
	 * The feature id for the '<em><b>Run Time Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_NODE__RUN_TIME_PROPERTIES = DIAGRAM_BASE_ELEMENT__RUN_TIME_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_NODE__BACKGROUND_COLOR = DIAGRAM_BASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Diagram Base Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_BASE_NODE_FEATURE_COUNT = DIAGRAM_BASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.diagramrt.impl.DiagramNodeImpl <em>Diagram Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramNodeImpl
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramRTPackageImpl#getDiagramNode()
	 * @generated
	 */
	int DIAGRAM_NODE = 0;

	/**
	 * The feature id for the '<em><b>Domain Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__DOMAIN_MODEL_ELEMENT = DIAGRAM_BASE_NODE__DOMAIN_MODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__FOREGROUND_COLOR = DIAGRAM_BASE_NODE__FOREGROUND_COLOR;

	/**
	 * The feature id for the '<em><b>Uin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__UIN = DIAGRAM_BASE_NODE__UIN;

	/**
	 * The feature id for the '<em><b>Visual ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__VISUAL_ID = DIAGRAM_BASE_NODE__VISUAL_ID;

	/**
	 * The feature id for the '<em><b>Run Time Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__RUN_TIME_PROPERTIES = DIAGRAM_BASE_NODE__RUN_TIME_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__BACKGROUND_COLOR = DIAGRAM_BASE_NODE__BACKGROUND_COLOR;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__DIAGRAM = DIAGRAM_BASE_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Incoming Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__INCOMING_LINKS = DIAGRAM_BASE_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Outgoing Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__OUTGOING_LINKS = DIAGRAM_BASE_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__CHILD_NODES = DIAGRAM_BASE_NODE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__LOCATION = DIAGRAM_BASE_NODE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE__SIZE = DIAGRAM_BASE_NODE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the the '<em>Diagram Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_NODE_FEATURE_COUNT = DIAGRAM_BASE_NODE_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.diagramrt.impl.DiagramLinkImpl <em>Diagram Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramLinkImpl
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramRTPackageImpl#getDiagramLink()
	 * @generated
	 */
	int DIAGRAM_LINK = 1;

	/**
	 * The feature id for the '<em><b>Domain Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__DOMAIN_MODEL_ELEMENT = DIAGRAM_BASE_ELEMENT__DOMAIN_MODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__FOREGROUND_COLOR = DIAGRAM_BASE_ELEMENT__FOREGROUND_COLOR;

	/**
	 * The feature id for the '<em><b>Uin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__UIN = DIAGRAM_BASE_ELEMENT__UIN;

	/**
	 * The feature id for the '<em><b>Visual ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__VISUAL_ID = DIAGRAM_BASE_ELEMENT__VISUAL_ID;

	/**
	 * The feature id for the '<em><b>Run Time Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__RUN_TIME_PROPERTIES = DIAGRAM_BASE_ELEMENT__RUN_TIME_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__DIAGRAM = DIAGRAM_BASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__SOURCE = DIAGRAM_BASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK__TARGET = DIAGRAM_BASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>Diagram Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK_FEATURE_COUNT = DIAGRAM_BASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.diagramrt.impl.DiagramCanvasImpl <em>Diagram Canvas</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramCanvasImpl
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramRTPackageImpl#getDiagramCanvas()
	 * @generated
	 */
	int DIAGRAM_CANVAS = 2;

	/**
	 * The feature id for the '<em><b>Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_CANVAS__LINKS = 0;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_CANVAS__NODES = 1;

	/**
	 * The feature id for the '<em><b>Domain Container Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_CANVAS__DOMAIN_CONTAINER_OBJECT = 2;

	/**
	 * The feature id for the '<em><b>Domain Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_CANVAS__DOMAIN_RESOURCE = 3;

	/**
	 * The feature id for the '<em><b>Last Assigned Uin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_CANVAS__LAST_ASSIGNED_UIN = 4;

	/**
	 * The number of structural features of the the '<em>Diagram Canvas</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_CANVAS_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.diagramrt.impl.ChildNodeImpl <em>Child Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.diagramrt.impl.ChildNodeImpl
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramRTPackageImpl#getChildNode()
	 * @generated
	 */
	int CHILD_NODE = 5;

	/**
	 * The feature id for the '<em><b>Domain Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__DOMAIN_MODEL_ELEMENT = DIAGRAM_NODE__DOMAIN_MODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__FOREGROUND_COLOR = DIAGRAM_NODE__FOREGROUND_COLOR;

	/**
	 * The feature id for the '<em><b>Uin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__UIN = DIAGRAM_NODE__UIN;

	/**
	 * The feature id for the '<em><b>Visual ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__VISUAL_ID = DIAGRAM_NODE__VISUAL_ID;

	/**
	 * The feature id for the '<em><b>Run Time Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__RUN_TIME_PROPERTIES = DIAGRAM_NODE__RUN_TIME_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Background Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__BACKGROUND_COLOR = DIAGRAM_NODE__BACKGROUND_COLOR;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__DIAGRAM = DIAGRAM_NODE__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Incoming Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__INCOMING_LINKS = DIAGRAM_NODE__INCOMING_LINKS;

	/**
	 * The feature id for the '<em><b>Outgoing Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__OUTGOING_LINKS = DIAGRAM_NODE__OUTGOING_LINKS;

	/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__CHILD_NODES = DIAGRAM_NODE__CHILD_NODES;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__LOCATION = DIAGRAM_NODE__LOCATION;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__SIZE = DIAGRAM_NODE__SIZE;

	/**
	 * The feature id for the '<em><b>Parent Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__PARENT_NODE = DIAGRAM_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Group ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE__GROUP_ID = DIAGRAM_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Child Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_NODE_FEATURE_COUNT = DIAGRAM_NODE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.diagramrt.impl.RigidChildNodeImpl <em>Rigid Child Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.diagramrt.impl.RigidChildNodeImpl
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramRTPackageImpl#getRigidChildNode()
	 * @generated
	 */
	int RIGID_CHILD_NODE = 6;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RIGID_CHILD_NODE__LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RIGID_CHILD_NODE__SIZE = 1;

	/**
	 * The number of structural features of the the '<em>Rigid Child Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RIGID_CHILD_NODE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '<em>Dimension</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.draw2d.geometry.Dimension
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramRTPackageImpl#getDimension()
	 * @generated
	 */
	int DIMENSION = 7;

	/**
	 * The meta object id for the '<em>Point</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.draw2d.geometry.Point
	 * @see org.eclipse.gmf.diagramrt.impl.DiagramRTPackageImpl#getPoint()
	 * @generated
	 */
	int POINT = 8;


	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.diagramrt.DiagramNode <em>Diagram Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram Node</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramNode
	 * @generated
	 */
	EClass getDiagramNode();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.gmf.diagramrt.DiagramNode#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Diagram</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramNode#getDiagram()
	 * @see #getDiagramNode()
	 * @generated
	 */
	EReference getDiagramNode_Diagram();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gmf.diagramrt.DiagramNode#getIncomingLinks <em>Incoming Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Links</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramNode#getIncomingLinks()
	 * @see #getDiagramNode()
	 * @generated
	 */
	EReference getDiagramNode_IncomingLinks();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gmf.diagramrt.DiagramNode#getOutgoingLinks <em>Outgoing Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing Links</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramNode#getOutgoingLinks()
	 * @see #getDiagramNode()
	 * @generated
	 */
	EReference getDiagramNode_OutgoingLinks();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gmf.diagramrt.DiagramNode#getChildNodes <em>Child Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Child Nodes</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramNode#getChildNodes()
	 * @see #getDiagramNode()
	 * @generated
	 */
	EReference getDiagramNode_ChildNodes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.diagramrt.DiagramNode#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramNode#getLocation()
	 * @see #getDiagramNode()
	 * @generated
	 */
	EAttribute getDiagramNode_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.diagramrt.DiagramNode#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramNode#getSize()
	 * @see #getDiagramNode()
	 * @generated
	 */
	EAttribute getDiagramNode_Size();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.diagramrt.DiagramLink <em>Diagram Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram Link</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramLink
	 * @generated
	 */
	EClass getDiagramLink();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.gmf.diagramrt.DiagramLink#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Diagram</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramLink#getDiagram()
	 * @see #getDiagramLink()
	 * @generated
	 */
	EReference getDiagramLink_Diagram();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.diagramrt.DiagramLink#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramLink#getSource()
	 * @see #getDiagramLink()
	 * @generated
	 */
	EReference getDiagramLink_Source();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.diagramrt.DiagramLink#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramLink#getTarget()
	 * @see #getDiagramLink()
	 * @generated
	 */
	EReference getDiagramLink_Target();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.diagramrt.DiagramCanvas <em>Diagram Canvas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram Canvas</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramCanvas
	 * @generated
	 */
	EClass getDiagramCanvas();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Links</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramCanvas#getLinks()
	 * @see #getDiagramCanvas()
	 * @generated
	 */
	EReference getDiagramCanvas_Links();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramCanvas#getNodes()
	 * @see #getDiagramCanvas()
	 * @generated
	 */
	EReference getDiagramCanvas_Nodes();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getDomainContainerObject <em>Domain Container Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Domain Container Object</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramCanvas#getDomainContainerObject()
	 * @see #getDiagramCanvas()
	 * @generated
	 */
	EReference getDiagramCanvas_DomainContainerObject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getDomainResource <em>Domain Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Domain Resource</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramCanvas#getDomainResource()
	 * @see #getDiagramCanvas()
	 * @generated
	 */
	EAttribute getDiagramCanvas_DomainResource();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.diagramrt.DiagramCanvas#getLastAssignedUin <em>Last Assigned Uin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Assigned Uin</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramCanvas#getLastAssignedUin()
	 * @see #getDiagramCanvas()
	 * @generated
	 */
	EAttribute getDiagramCanvas_LastAssignedUin();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.diagramrt.DiagramBaseElement <em>Diagram Base Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram Base Element</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramBaseElement
	 * @generated
	 */
	EClass getDiagramBaseElement();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getDomainModelElement <em>Domain Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Domain Model Element</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramBaseElement#getDomainModelElement()
	 * @see #getDiagramBaseElement()
	 * @generated
	 */
	EReference getDiagramBaseElement_DomainModelElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getForegroundColor <em>Foreground Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Foreground Color</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramBaseElement#getForegroundColor()
	 * @see #getDiagramBaseElement()
	 * @generated
	 */
	EAttribute getDiagramBaseElement_ForegroundColor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getUin <em>Uin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uin</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramBaseElement#getUin()
	 * @see #getDiagramBaseElement()
	 * @generated
	 */
	EAttribute getDiagramBaseElement_Uin();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getVisualID <em>Visual ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visual ID</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramBaseElement#getVisualID()
	 * @see #getDiagramBaseElement()
	 * @generated
	 */
	EAttribute getDiagramBaseElement_VisualID();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.gmf.diagramrt.DiagramBaseElement#getRunTimeProperties <em>Run Time Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Run Time Properties</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramBaseElement#getRunTimeProperties()
	 * @see #getDiagramBaseElement()
	 * @generated
	 */
	EReference getDiagramBaseElement_RunTimeProperties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.diagramrt.DiagramBaseNode <em>Diagram Base Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram Base Node</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramBaseNode
	 * @generated
	 */
	EClass getDiagramBaseNode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.diagramrt.DiagramBaseNode#getBackgroundColor <em>Background Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Background Color</em>'.
	 * @see org.eclipse.gmf.diagramrt.DiagramBaseNode#getBackgroundColor()
	 * @see #getDiagramBaseNode()
	 * @generated
	 */
	EAttribute getDiagramBaseNode_BackgroundColor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.diagramrt.ChildNode <em>Child Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Child Node</em>'.
	 * @see org.eclipse.gmf.diagramrt.ChildNode
	 * @generated
	 */
	EClass getChildNode();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.gmf.diagramrt.ChildNode#getParentNode <em>Parent Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent Node</em>'.
	 * @see org.eclipse.gmf.diagramrt.ChildNode#getParentNode()
	 * @see #getChildNode()
	 * @generated
	 */
	EReference getChildNode_ParentNode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.diagramrt.ChildNode#getGroupID <em>Group ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group ID</em>'.
	 * @see org.eclipse.gmf.diagramrt.ChildNode#getGroupID()
	 * @see #getChildNode()
	 * @generated
	 */
	EAttribute getChildNode_GroupID();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.diagramrt.RigidChildNode <em>Rigid Child Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rigid Child Node</em>'.
	 * @see org.eclipse.gmf.diagramrt.RigidChildNode
	 * @generated
	 */
	EClass getRigidChildNode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.diagramrt.RigidChildNode#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.gmf.diagramrt.RigidChildNode#getLocation()
	 * @see #getRigidChildNode()
	 * @generated
	 */
	EAttribute getRigidChildNode_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.diagramrt.RigidChildNode#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.gmf.diagramrt.RigidChildNode#getSize()
	 * @see #getRigidChildNode()
	 * @generated
	 */
	EAttribute getRigidChildNode_Size();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.draw2d.geometry.Dimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Dimension</em>'.
	 * @see org.eclipse.draw2d.geometry.Dimension
	 * @model instanceClass="org.eclipse.draw2d.geometry.Dimension"
	 * @generated
	 */
	EDataType getDimension();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.draw2d.geometry.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Point</em>'.
	 * @see org.eclipse.draw2d.geometry.Point
	 * @model instanceClass="org.eclipse.draw2d.geometry.Point"
	 * @generated
	 */
	EDataType getPoint();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DiagramRTFactory getDiagramRTFactory();

} //DiagramRTPackage
