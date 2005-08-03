/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt.impl;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.gmf.diagramrt.ChildNode;
import org.eclipse.gmf.diagramrt.DiagramBaseElement;
import org.eclipse.gmf.diagramrt.DiagramBaseNode;
import org.eclipse.gmf.diagramrt.DiagramCanvas;
import org.eclipse.gmf.diagramrt.DiagramLink;
import org.eclipse.gmf.diagramrt.DiagramNode;
import org.eclipse.gmf.diagramrt.DiagramRTFactory;
import org.eclipse.gmf.diagramrt.DiagramRTPackage;
import org.eclipse.gmf.diagramrt.RigidChildNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DiagramRTPackageImpl extends EPackageImpl implements DiagramRTPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramCanvasEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramBaseElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramBaseNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass childNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rigidChildNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType dimensionEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType pointEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.gmf.diagramrt.DiagramRTPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DiagramRTPackageImpl() {
		super(eNS_URI, DiagramRTFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DiagramRTPackage init() {
		if (isInited) return (DiagramRTPackage)EPackage.Registry.INSTANCE.getEPackage(DiagramRTPackage.eNS_URI);

		// Obtain or create and register package
		DiagramRTPackageImpl theDiagramRTPackage = (DiagramRTPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof DiagramRTPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new DiagramRTPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theDiagramRTPackage.createPackageContents();

		// Initialize created meta-data
		theDiagramRTPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDiagramRTPackage.freeze();

		return theDiagramRTPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagramNode() {
		return diagramNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramNode_Diagram() {
		return (EReference)diagramNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramNode_IncomingLinks() {
		return (EReference)diagramNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramNode_OutgoingLinks() {
		return (EReference)diagramNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramNode_ChildNodes() {
		return (EReference)diagramNodeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramNode_Location() {
		return (EAttribute)diagramNodeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramNode_Size() {
		return (EAttribute)diagramNodeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagramLink() {
		return diagramLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramLink_Diagram() {
		return (EReference)diagramLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramLink_Source() {
		return (EReference)diagramLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramLink_Target() {
		return (EReference)diagramLinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagramCanvas() {
		return diagramCanvasEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramCanvas_Links() {
		return (EReference)diagramCanvasEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramCanvas_Nodes() {
		return (EReference)diagramCanvasEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramCanvas_DomainContainerObject() {
		return (EReference)diagramCanvasEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramCanvas_DomainResource() {
		return (EAttribute)diagramCanvasEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramCanvas_LastAssignedUin() {
		return (EAttribute)diagramCanvasEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagramBaseElement() {
		return diagramBaseElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramBaseElement_DomainModelElement() {
		return (EReference)diagramBaseElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramBaseElement_ForegroundColor() {
		return (EAttribute)diagramBaseElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramBaseElement_Uin() {
		return (EAttribute)diagramBaseElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramBaseElement_VisualID() {
		return (EAttribute)diagramBaseElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagramBaseElement_RunTimeProperties() {
		return (EReference)diagramBaseElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagramBaseNode() {
		return diagramBaseNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagramBaseNode_BackgroundColor() {
		return (EAttribute)diagramBaseNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChildNode() {
		return childNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChildNode_ParentNode() {
		return (EReference)childNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getChildNode_GroupID() {
		return (EAttribute)childNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRigidChildNode() {
		return rigidChildNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRigidChildNode_Location() {
		return (EAttribute)rigidChildNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRigidChildNode_Size() {
		return (EAttribute)rigidChildNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDimension() {
		return dimensionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPoint() {
		return pointEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramRTFactory getDiagramRTFactory() {
		return (DiagramRTFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		diagramNodeEClass = createEClass(DIAGRAM_NODE);
		createEReference(diagramNodeEClass, DIAGRAM_NODE__DIAGRAM);
		createEReference(diagramNodeEClass, DIAGRAM_NODE__INCOMING_LINKS);
		createEReference(diagramNodeEClass, DIAGRAM_NODE__OUTGOING_LINKS);
		createEReference(diagramNodeEClass, DIAGRAM_NODE__CHILD_NODES);
		createEAttribute(diagramNodeEClass, DIAGRAM_NODE__LOCATION);
		createEAttribute(diagramNodeEClass, DIAGRAM_NODE__SIZE);

		diagramLinkEClass = createEClass(DIAGRAM_LINK);
		createEReference(diagramLinkEClass, DIAGRAM_LINK__DIAGRAM);
		createEReference(diagramLinkEClass, DIAGRAM_LINK__SOURCE);
		createEReference(diagramLinkEClass, DIAGRAM_LINK__TARGET);

		diagramCanvasEClass = createEClass(DIAGRAM_CANVAS);
		createEReference(diagramCanvasEClass, DIAGRAM_CANVAS__LINKS);
		createEReference(diagramCanvasEClass, DIAGRAM_CANVAS__NODES);
		createEReference(diagramCanvasEClass, DIAGRAM_CANVAS__DOMAIN_CONTAINER_OBJECT);
		createEAttribute(diagramCanvasEClass, DIAGRAM_CANVAS__DOMAIN_RESOURCE);
		createEAttribute(diagramCanvasEClass, DIAGRAM_CANVAS__LAST_ASSIGNED_UIN);

		diagramBaseElementEClass = createEClass(DIAGRAM_BASE_ELEMENT);
		createEReference(diagramBaseElementEClass, DIAGRAM_BASE_ELEMENT__DOMAIN_MODEL_ELEMENT);
		createEAttribute(diagramBaseElementEClass, DIAGRAM_BASE_ELEMENT__FOREGROUND_COLOR);
		createEAttribute(diagramBaseElementEClass, DIAGRAM_BASE_ELEMENT__UIN);
		createEAttribute(diagramBaseElementEClass, DIAGRAM_BASE_ELEMENT__VISUAL_ID);
		createEReference(diagramBaseElementEClass, DIAGRAM_BASE_ELEMENT__RUN_TIME_PROPERTIES);

		diagramBaseNodeEClass = createEClass(DIAGRAM_BASE_NODE);
		createEAttribute(diagramBaseNodeEClass, DIAGRAM_BASE_NODE__BACKGROUND_COLOR);

		childNodeEClass = createEClass(CHILD_NODE);
		createEReference(childNodeEClass, CHILD_NODE__PARENT_NODE);
		createEAttribute(childNodeEClass, CHILD_NODE__GROUP_ID);

		rigidChildNodeEClass = createEClass(RIGID_CHILD_NODE);
		createEAttribute(rigidChildNodeEClass, RIGID_CHILD_NODE__LOCATION);
		createEAttribute(rigidChildNodeEClass, RIGID_CHILD_NODE__SIZE);

		// Create data types
		dimensionEDataType = createEDataType(DIMENSION);
		pointEDataType = createEDataType(POINT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Add supertypes to classes
		diagramNodeEClass.getESuperTypes().add(this.getDiagramBaseNode());
		diagramLinkEClass.getESuperTypes().add(this.getDiagramBaseElement());
		diagramBaseNodeEClass.getESuperTypes().add(this.getDiagramBaseElement());
		childNodeEClass.getESuperTypes().add(this.getDiagramNode());

		// Initialize classes and features; add operations and parameters
		initEClass(diagramNodeEClass, DiagramNode.class, "DiagramNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDiagramNode_Diagram(), this.getDiagramCanvas(), this.getDiagramCanvas_Nodes(), "diagram", null, 1, 1, DiagramNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagramNode_IncomingLinks(), this.getDiagramLink(), this.getDiagramLink_Target(), "incomingLinks", null, 0, -1, DiagramNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagramNode_OutgoingLinks(), this.getDiagramLink(), this.getDiagramLink_Source(), "outgoingLinks", null, 0, -1, DiagramNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagramNode_ChildNodes(), this.getChildNode(), this.getChildNode_ParentNode(), "childNodes", null, 0, -1, DiagramNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagramNode_Location(), this.getPoint(), "location", "20,20", 0, 1, DiagramNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagramNode_Size(), this.getDimension(), "size", "100, 50", 0, 1, DiagramNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(diagramLinkEClass, DiagramLink.class, "DiagramLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDiagramLink_Diagram(), this.getDiagramCanvas(), this.getDiagramCanvas_Links(), "diagram", null, 1, 1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagramLink_Source(), this.getDiagramNode(), this.getDiagramNode_OutgoingLinks(), "source", null, 0, 1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagramLink_Target(), this.getDiagramNode(), this.getDiagramNode_IncomingLinks(), "target", null, 0, 1, DiagramLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(diagramCanvasEClass, DiagramCanvas.class, "DiagramCanvas", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDiagramCanvas_Links(), this.getDiagramLink(), this.getDiagramLink_Diagram(), "links", null, 0, -1, DiagramCanvas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagramCanvas_Nodes(), this.getDiagramNode(), this.getDiagramNode_Diagram(), "nodes", null, 0, -1, DiagramCanvas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagramCanvas_DomainContainerObject(), ecorePackage.getEObject(), null, "domainContainerObject", null, 1, 1, DiagramCanvas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagramCanvas_DomainResource(), ecorePackage.getEResource(), "domainResource", null, 1, 1, DiagramCanvas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagramCanvas_LastAssignedUin(), ecorePackage.getEInt(), "lastAssignedUin", null, 0, 1, DiagramCanvas.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(diagramCanvasEClass, ecorePackage.getEInt(), "nextAvailableUin");

		initEClass(diagramBaseElementEClass, DiagramBaseElement.class, "DiagramBaseElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDiagramBaseElement_DomainModelElement(), ecorePackage.getEObject(), null, "domainModelElement", null, 0, 1, DiagramBaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagramBaseElement_ForegroundColor(), ecorePackage.getEString(), "foregroundColor", null, 0, 1, DiagramBaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagramBaseElement_Uin(), ecorePackage.getEInt(), "uin", null, 1, 1, DiagramBaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagramBaseElement_VisualID(), ecorePackage.getEInt(), "visualID", null, 1, 1, DiagramBaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagramBaseElement_RunTimeProperties(), ecorePackage.getEStringToStringMapEntry(), null, "runTimeProperties", null, 0, -1, DiagramBaseElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(diagramBaseElementEClass, this.getDiagramCanvas(), "getDiagram");

		EOperation op = addEOperation(diagramBaseElementEClass, ecorePackage.getEString(), "getProperty");
		addEParameter(op, ecorePackage.getEString(), "propertyName");

		op = addEOperation(diagramBaseElementEClass, null, "setProperty");
		addEParameter(op, ecorePackage.getEString(), "propertyName");
		addEParameter(op, ecorePackage.getEString(), "value");

		initEClass(diagramBaseNodeEClass, DiagramBaseNode.class, "DiagramBaseNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDiagramBaseNode_BackgroundColor(), ecorePackage.getEString(), "backgroundColor", null, 0, 1, DiagramBaseNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(childNodeEClass, ChildNode.class, "ChildNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChildNode_ParentNode(), this.getDiagramNode(), this.getDiagramNode_ChildNodes(), "parentNode", null, 1, 1, ChildNode.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChildNode_GroupID(), ecorePackage.getEString(), "groupID", null, 0, 1, ChildNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rigidChildNodeEClass, RigidChildNode.class, "RigidChildNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRigidChildNode_Location(), this.getPoint(), "location", "20,20", 0, 1, RigidChildNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRigidChildNode_Size(), this.getDimension(), "size", "100, 50", 0, 1, RigidChildNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(dimensionEDataType, Dimension.class, "Dimension", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(pointEDataType, Point.class, "Point", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //DiagramRTPackageImpl
