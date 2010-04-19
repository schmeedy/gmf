/******************************************************************************
 * Copyright (c) 2004, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.notation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

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
 * @see org.eclipse.gmf.runtime.notation.NotationFactory
 * @model kind="package"
 * @generated
 */
public interface NotationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "notation"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/gmf/runtime/1.0.2/notation"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "notation"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NotationPackage eINSTANCE = org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ViewImpl <em>View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ViewImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getView()
	 * @generated
	 */
	int VIEW = 18;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__EANNOTATIONS = EcorePackage.EMODEL_ELEMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__VISIBLE = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__TYPE = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__MUTABLE = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__SOURCE_EDGES = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__TARGET_EDGES = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__PERSISTED_CHILDREN = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__STYLES = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__ELEMENT = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__DIAGRAM = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__TRANSIENT_CHILDREN = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_FEATURE_COUNT = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.EdgeImpl <em>Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.EdgeImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getEdge()
	 * @generated
	 */
	int EDGE = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__EANNOTATIONS = VIEW__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__VISIBLE = VIEW__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TYPE = VIEW__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__MUTABLE = VIEW__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__SOURCE_EDGES = VIEW__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TARGET_EDGES = VIEW__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__PERSISTED_CHILDREN = VIEW__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__STYLES = VIEW__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__ELEMENT = VIEW__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__DIAGRAM = VIEW__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TRANSIENT_CHILDREN = VIEW__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__SOURCE = VIEW_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TARGET = VIEW_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bendpoints</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__BENDPOINTS = VIEW_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__SOURCE_ANCHOR = VIEW_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TARGET_ANCHOR = VIEW_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_FEATURE_COUNT = VIEW_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.NodeImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__EANNOTATIONS = VIEW__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__VISIBLE = VIEW__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TYPE = VIEW__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__MUTABLE = VIEW__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__SOURCE_EDGES = VIEW__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TARGET_EDGES = VIEW__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__PERSISTED_CHILDREN = VIEW__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__STYLES = VIEW__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ELEMENT = VIEW__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__DIAGRAM = VIEW__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TRANSIENT_CHILDREN = VIEW__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Layout Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__LAYOUT_CONSTRAINT = VIEW_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = VIEW_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.Style <em>Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.Style
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStyle()
	 * @generated
	 */
	int STYLE = 2;

	/**
	 * The number of structural features of the '<em>Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.FillStyleImpl <em>Fill Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.FillStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getFillStyle()
	 * @generated
	 */
	int FILL_STYLE = 3;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILL_STYLE__FILL_COLOR = STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILL_STYLE__TRANSPARENCY = STYLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Gradient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILL_STYLE__GRADIENT = STYLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Fill Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILL_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.LineStyleImpl <em>Line Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.LineStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getLineStyle()
	 * @generated
	 */
	int LINE_STYLE = 4;

	/**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STYLE__LINE_COLOR = STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STYLE__LINE_WIDTH = STYLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Line Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.FontStyleImpl <em>Font Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.FontStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getFontStyle()
	 * @generated
	 */
	int FONT_STYLE = 5;

	/**
	 * The feature id for the '<em><b>Font Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT_STYLE__FONT_COLOR = STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Font Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT_STYLE__FONT_NAME = STYLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Font Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT_STYLE__FONT_HEIGHT = STYLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Bold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT_STYLE__BOLD = STYLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Italic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT_STYLE__ITALIC = STYLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Underline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT_STYLE__UNDERLINE = STYLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Strike Through</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT_STYLE__STRIKE_THROUGH = STYLE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Font Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FONT_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.TitleStyleImpl <em>Title Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.TitleStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getTitleStyle()
	 * @generated
	 */
	int TITLE_STYLE = 6;

	/**
	 * The feature id for the '<em><b>Show Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLE_STYLE__SHOW_TITLE = STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Title Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLE_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.SortingStyleImpl <em>Sorting Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.SortingStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSortingStyle()
	 * @generated
	 */
	int SORTING_STYLE = 7;

	/**
	 * The feature id for the '<em><b>Sorting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORTING_STYLE__SORTING = STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sorting Keys</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORTING_STYLE__SORTING_KEYS = STYLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sorted Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORTING_STYLE__SORTED_OBJECTS = STYLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Sorting Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORTING_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.DescriptionStyleImpl <em>Description Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.DescriptionStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDescriptionStyle()
	 * @generated
	 */
	int DESCRIPTION_STYLE = 8;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_STYLE__DESCRIPTION = STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Description Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.LayoutConstraint <em>Layout Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.LayoutConstraint
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getLayoutConstraint()
	 * @generated
	 */
	int LAYOUT_CONSTRAINT = 9;

	/**
	 * The number of structural features of the '<em>Layout Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYOUT_CONSTRAINT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.SizeImpl <em>Size</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.SizeImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSize()
	 * @generated
	 */
	int SIZE = 10;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIZE__WIDTH = LAYOUT_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIZE__HEIGHT = LAYOUT_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Size</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIZE_FEATURE_COUNT = LAYOUT_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.LocationImpl <em>Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.LocationImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getLocation()
	 * @generated
	 */
	int LOCATION = 11;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__X = LAYOUT_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__Y = LAYOUT_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_FEATURE_COUNT = LAYOUT_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.BoundsImpl <em>Bounds</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.BoundsImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBounds()
	 * @generated
	 */
	int BOUNDS = 12;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDS__X = LOCATION__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDS__Y = LOCATION__Y;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDS__WIDTH = LOCATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDS__HEIGHT = LOCATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Bounds</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDS_FEATURE_COUNT = LOCATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.RatioImpl <em>Ratio</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.RatioImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRatio()
	 * @generated
	 */
	int RATIO = 13;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATIO__VALUE = LAYOUT_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ratio</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATIO_FEATURE_COUNT = LAYOUT_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.Anchor <em>Anchor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.Anchor
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getAnchor()
	 * @generated
	 */
	int ANCHOR = 14;

	/**
	 * The number of structural features of the '<em>Anchor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANCHOR_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.Bendpoints <em>Bendpoints</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.Bendpoints
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBendpoints()
	 * @generated
	 */
	int BENDPOINTS = 15;

	/**
	 * The number of structural features of the '<em>Bendpoints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENDPOINTS_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.IdentityAnchorImpl <em>Identity Anchor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.IdentityAnchorImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getIdentityAnchor()
	 * @generated
	 */
	int IDENTITY_ANCHOR = 16;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_ANCHOR__ID = ANCHOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Identity Anchor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_ANCHOR_FEATURE_COUNT = ANCHOR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.RoundedCornersStyleImpl <em>Rounded Corners Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.RoundedCornersStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRoundedCornersStyle()
	 * @generated
	 */
	int ROUNDED_CORNERS_STYLE = 69;

	/**
	 * The feature id for the '<em><b>Rounded Bendpoints Radius</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_CORNERS_STYLE__ROUNDED_BENDPOINTS_RADIUS = STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Rounded Corners Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUNDED_CORNERS_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.RoutingStyleImpl <em>Routing Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.RoutingStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRoutingStyle()
	 * @generated
	 */
	int ROUTING_STYLE = 17;

	/**
	 * The feature id for the '<em><b>Rounded Bendpoints Radius</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * Left in for backwards compatibility
	 * @ordered
	 * @deprecated Use ROUNDED_CORNERS_STYLE__ROUNDED_BENDPOINTS_RADIUS instead
	 */
	int ROUTING_STYLE__ROUNDED_BENDPOINTS_RADIUS = ROUNDED_CORNERS_STYLE__ROUNDED_BENDPOINTS_RADIUS;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTING_STYLE__ROUTING = ROUNDED_CORNERS_STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Smoothness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTING_STYLE__SMOOTHNESS = ROUNDED_CORNERS_STYLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Avoid Obstructions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTING_STYLE__AVOID_OBSTRUCTIONS = ROUNDED_CORNERS_STYLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Closest Distance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTING_STYLE__CLOSEST_DISTANCE = ROUNDED_CORNERS_STYLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Jump Link Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTING_STYLE__JUMP_LINK_STATUS = ROUNDED_CORNERS_STYLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Jump Link Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTING_STYLE__JUMP_LINK_TYPE = ROUNDED_CORNERS_STYLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Jump Links Reverse</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTING_STYLE__JUMP_LINKS_REVERSE = ROUNDED_CORNERS_STYLE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Routing Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTING_STYLE_FEATURE_COUNT = ROUNDED_CORNERS_STYLE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.RelativeBendpointsImpl <em>Relative Bendpoints</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.RelativeBendpointsImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRelativeBendpoints()
	 * @generated
	 */
	int RELATIVE_BENDPOINTS = 19;

	/**
	 * The feature id for the '<em><b>Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_BENDPOINTS__POINTS = BENDPOINTS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Relative Bendpoints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_BENDPOINTS_FEATURE_COUNT = BENDPOINTS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.DiagramImpl <em>Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.DiagramImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDiagram()
	 * @generated
	 */
	int DIAGRAM = 20;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__EANNOTATIONS = VIEW__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__VISIBLE = VIEW__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__TYPE = VIEW__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__MUTABLE = VIEW__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__SOURCE_EDGES = VIEW__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__TARGET_EDGES = VIEW__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__PERSISTED_CHILDREN = VIEW__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__STYLES = VIEW__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__ELEMENT = VIEW__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__DIAGRAM = VIEW__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__TRANSIENT_CHILDREN = VIEW__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__NAME = VIEW_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Measurement Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__MEASUREMENT_UNIT = VIEW_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Persisted Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__PERSISTED_EDGES = VIEW_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Transient Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__TRANSIENT_EDGES = VIEW_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_FEATURE_COUNT = VIEW_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ImageImpl <em>Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ImageImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getImage()
	 * @generated
	 */
	int IMAGE = 21;

	/**
	 * The feature id for the '<em><b>Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__DATA = 0;

	/**
	 * The number of structural features of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.CanonicalStyleImpl <em>Canonical Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.CanonicalStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getCanonicalStyle()
	 * @generated
	 */
	int CANONICAL_STYLE = 22;

	/**
	 * The feature id for the '<em><b>Canonical</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANONICAL_STYLE__CANONICAL = STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Canonical Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANONICAL_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ShapeStyleImpl <em>Shape Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ShapeStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getShapeStyle()
	 * @generated
	 */
	int SHAPE_STYLE = 23;

	/**
	 * The feature id for the '<em><b>Font Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__FONT_COLOR = FONT_STYLE__FONT_COLOR;

	/**
	 * The feature id for the '<em><b>Font Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__FONT_NAME = FONT_STYLE__FONT_NAME;

	/**
	 * The feature id for the '<em><b>Font Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__FONT_HEIGHT = FONT_STYLE__FONT_HEIGHT;

	/**
	 * The feature id for the '<em><b>Bold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__BOLD = FONT_STYLE__BOLD;

	/**
	 * The feature id for the '<em><b>Italic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__ITALIC = FONT_STYLE__ITALIC;

	/**
	 * The feature id for the '<em><b>Underline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__UNDERLINE = FONT_STYLE__UNDERLINE;

	/**
	 * The feature id for the '<em><b>Strike Through</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__STRIKE_THROUGH = FONT_STYLE__STRIKE_THROUGH;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__DESCRIPTION = FONT_STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__FILL_COLOR = FONT_STYLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__TRANSPARENCY = FONT_STYLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Gradient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__GRADIENT = FONT_STYLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__LINE_COLOR = FONT_STYLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__LINE_WIDTH = FONT_STYLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Rounded Bendpoints Radius</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE__ROUNDED_BENDPOINTS_RADIUS = FONT_STYLE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Shape Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_STYLE_FEATURE_COUNT = FONT_STYLE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ConnectorStyleImpl <em>Connector Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ConnectorStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getConnectorStyle()
	 * @generated
	 */
	int CONNECTOR_STYLE = 24;

	/**
	 * The feature id for the '<em><b>Rounded Bendpoints Radius</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_STYLE__ROUNDED_BENDPOINTS_RADIUS = ROUTING_STYLE__ROUNDED_BENDPOINTS_RADIUS;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_STYLE__ROUTING = ROUTING_STYLE__ROUTING;

	/**
	 * The feature id for the '<em><b>Smoothness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_STYLE__SMOOTHNESS = ROUTING_STYLE__SMOOTHNESS;

	/**
	 * The feature id for the '<em><b>Avoid Obstructions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_STYLE__AVOID_OBSTRUCTIONS = ROUTING_STYLE__AVOID_OBSTRUCTIONS;

	/**
	 * The feature id for the '<em><b>Closest Distance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_STYLE__CLOSEST_DISTANCE = ROUTING_STYLE__CLOSEST_DISTANCE;

	/**
	 * The feature id for the '<em><b>Jump Link Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_STYLE__JUMP_LINK_STATUS = ROUTING_STYLE__JUMP_LINK_STATUS;

	/**
	 * The feature id for the '<em><b>Jump Link Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_STYLE__JUMP_LINK_TYPE = ROUTING_STYLE__JUMP_LINK_TYPE;

	/**
	 * The feature id for the '<em><b>Jump Links Reverse</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_STYLE__JUMP_LINKS_REVERSE = ROUTING_STYLE__JUMP_LINKS_REVERSE;

	/**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_STYLE__LINE_COLOR = ROUTING_STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_STYLE__LINE_WIDTH = ROUTING_STYLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Connector Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_STYLE_FEATURE_COUNT = ROUTING_STYLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.PageStyleImpl <em>Page Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.PageStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getPageStyle()
	 * @generated
	 */
	int PAGE_STYLE = 25;

	/**
	 * The feature id for the '<em><b>Page X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_STYLE__PAGE_X = STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Page Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_STYLE__PAGE_Y = STYLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Page Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_STYLE__PAGE_WIDTH = STYLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Page Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_STYLE__PAGE_HEIGHT = STYLE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Page Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.DrawerStyleImpl <em>Drawer Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.DrawerStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDrawerStyle()
	 * @generated
	 */
	int DRAWER_STYLE = 26;

	/**
	 * The feature id for the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWER_STYLE__COLLAPSED = STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Drawer Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRAWER_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.GuideStyleImpl <em>Guide Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.GuideStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getGuideStyle()
	 * @generated
	 */
	int GUIDE_STYLE = 27;

	/**
	 * The feature id for the '<em><b>Horizontal Guides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDE_STYLE__HORIZONTAL_GUIDES = STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Vertical Guides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDE_STYLE__VERTICAL_GUIDES = STYLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Guide Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDE_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.GuideImpl <em>Guide</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.GuideImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getGuide()
	 * @generated
	 */
	int GUIDE = 28;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDE__POSITION = 0;

	/**
	 * The feature id for the '<em><b>Node Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDE__NODE_MAP = 1;

	/**
	 * The number of structural features of the '<em>Guide</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUIDE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.NodeEntryImpl <em>Node Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.NodeEntryImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getNodeEntry()
	 * @generated
	 */
	int NODE_ENTRY = 29;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_ENTRY__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_ENTRY__KEY = 1;

	/**
	 * The number of structural features of the '<em>Node Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.FilteringStyleImpl <em>Filtering Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.FilteringStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getFilteringStyle()
	 * @generated
	 */
	int FILTERING_STYLE = 30;

	/**
	 * The feature id for the '<em><b>Filtering</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_STYLE__FILTERING = STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Filtering Keys</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_STYLE__FILTERING_KEYS = STYLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Filtered Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_STYLE__FILTERED_OBJECTS = STYLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Filtering Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.DiagramStyleImpl <em>Diagram Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.DiagramStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDiagramStyle()
	 * @generated
	 */
	int DIAGRAM_STYLE = 31;

	/**
	 * The feature id for the '<em><b>Page X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_STYLE__PAGE_X = PAGE_STYLE__PAGE_X;

	/**
	 * The feature id for the '<em><b>Page Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_STYLE__PAGE_Y = PAGE_STYLE__PAGE_Y;

	/**
	 * The feature id for the '<em><b>Page Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_STYLE__PAGE_WIDTH = PAGE_STYLE__PAGE_WIDTH;

	/**
	 * The feature id for the '<em><b>Page Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_STYLE__PAGE_HEIGHT = PAGE_STYLE__PAGE_HEIGHT;

	/**
	 * The feature id for the '<em><b>Horizontal Guides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_STYLE__HORIZONTAL_GUIDES = PAGE_STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Vertical Guides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_STYLE__VERTICAL_GUIDES = PAGE_STYLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_STYLE__DESCRIPTION = PAGE_STYLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Diagram Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_STYLE_FEATURE_COUNT = PAGE_STYLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ImageStyleImpl <em>Image Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ImageStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getImageStyle()
	 * @generated
	 */
	int IMAGE_STYLE = 32;

	/**
	 * The feature id for the '<em><b>Anti Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_STYLE__ANTI_ALIAS = STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Maintain Aspect Ratio</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_STYLE__MAINTAIN_ASPECT_RATIO = STYLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Crop Bound</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_STYLE__CROP_BOUND = STYLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Image Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ImageBufferStyleImpl <em>Image Buffer Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ImageBufferStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getImageBufferStyle()
	 * @generated
	 */
	int IMAGE_BUFFER_STYLE = 33;

	/**
	 * The feature id for the '<em><b>Anti Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUFFER_STYLE__ANTI_ALIAS = IMAGE_STYLE__ANTI_ALIAS;

	/**
	 * The feature id for the '<em><b>Maintain Aspect Ratio</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUFFER_STYLE__MAINTAIN_ASPECT_RATIO = IMAGE_STYLE__MAINTAIN_ASPECT_RATIO;

	/**
	 * The feature id for the '<em><b>Crop Bound</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUFFER_STYLE__CROP_BOUND = IMAGE_STYLE__CROP_BOUND;

	/**
	 * The feature id for the '<em><b>Image Buffer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUFFER_STYLE__IMAGE_BUFFER = IMAGE_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Image Buffer Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUFFER_STYLE_FEATURE_COUNT = IMAGE_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.NamedStyleImpl <em>Named Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.NamedStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getNamedStyle()
	 * @generated
	 */
	int NAMED_STYLE = 39;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_STYLE__NAME = STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Named Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.PropertiesSetStyleImpl <em>Properties Set Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.PropertiesSetStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getPropertiesSetStyle()
	 * @generated
	 */
	int PROPERTIES_SET_STYLE = 34;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_SET_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>Properties Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_SET_STYLE__PROPERTIES_MAP = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Properties Set Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_SET_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.StringToPropertyValueMapEntryImpl <em>String To Property Value Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.StringToPropertyValueMapEntryImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStringToPropertyValueMapEntry()
	 * @generated
	 */
	int STRING_TO_PROPERTY_VALUE_MAP_ENTRY = 35;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_PROPERTY_VALUE_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_PROPERTY_VALUE_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Property Value Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_PROPERTY_VALUE_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.StringObjectConverter <em>String Object Converter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.StringObjectConverter
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStringObjectConverter()
	 * @generated
	 */
	int STRING_OBJECT_CONVERTER = 40;

	/**
	 * The number of structural features of the '<em>String Object Converter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_OBJECT_CONVERTER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.PropertyValueImpl <em>Property Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.PropertyValueImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getPropertyValue()
	 * @generated
	 */
	int PROPERTY_VALUE = 36;

	/**
	 * The feature id for the '<em><b>Raw Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_VALUE__RAW_VALUE = STRING_OBJECT_CONVERTER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instance Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_VALUE__INSTANCE_TYPE = STRING_OBJECT_CONVERTER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Property Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_VALUE_FEATURE_COUNT = STRING_OBJECT_CONVERTER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.DataTypeStyleImpl <em>Data Type Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.DataTypeStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDataTypeStyle()
	 * @generated
	 */
	int DATA_TYPE_STYLE = 41;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>Instance Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_STYLE__INSTANCE_TYPE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Type Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.SingleValueStyleImpl <em>Single Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.SingleValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSingleValueStyle()
	 * @generated
	 */
	int SINGLE_VALUE_STYLE = 37;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VALUE_STYLE__NAME = DATA_TYPE_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>Instance Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VALUE_STYLE__INSTANCE_TYPE = DATA_TYPE_STYLE__INSTANCE_TYPE;

	/**
	 * The feature id for the '<em><b>Raw Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VALUE_STYLE__RAW_VALUE = DATA_TYPE_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Single Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VALUE_STYLE_FEATURE_COUNT = DATA_TYPE_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ListValueStyleImpl <em>List Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ListValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getListValueStyle()
	 * @generated
	 */
	int LIST_VALUE_STYLE = 38;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_VALUE_STYLE__NAME = DATA_TYPE_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>Instance Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_VALUE_STYLE__INSTANCE_TYPE = DATA_TYPE_STYLE__INSTANCE_TYPE;

	/**
	 * The feature id for the '<em><b>Raw Values List</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_VALUE_STYLE__RAW_VALUES_LIST = DATA_TYPE_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>List Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_VALUE_STYLE_FEATURE_COUNT = DATA_TYPE_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.IntValueStyleImpl <em>Int Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.IntValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getIntValueStyle()
	 * @generated
	 */
	int INT_VALUE_STYLE = 42;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_VALUE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>Int Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_VALUE_STYLE__INT_VALUE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Int Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_VALUE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.IntListValueStyleImpl <em>Int List Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.IntListValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getIntListValueStyle()
	 * @generated
	 */
	int INT_LIST_VALUE_STYLE = 43;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_LIST_VALUE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>Int List Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_LIST_VALUE_STYLE__INT_LIST_VALUE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Int List Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_LIST_VALUE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.BooleanValueStyleImpl <em>Boolean Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.BooleanValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBooleanValueStyle()
	 * @generated
	 */
	int BOOLEAN_VALUE_STYLE = 44;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>Boolean Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_STYLE__BOOLEAN_VALUE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.DoubleValueStyleImpl <em>Double Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.DoubleValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDoubleValueStyle()
	 * @generated
	 */
	int DOUBLE_VALUE_STYLE = 45;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VALUE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>Double Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VALUE_STYLE__DOUBLE_VALUE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VALUE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.DoubleListValueStyleImpl <em>Double List Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.DoubleListValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDoubleListValueStyle()
	 * @generated
	 */
	int DOUBLE_LIST_VALUE_STYLE = 46;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LIST_VALUE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>Double List Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LIST_VALUE_STYLE__DOUBLE_LIST_VALUE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double List Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LIST_VALUE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.StringValueStyleImpl <em>String Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.StringValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStringValueStyle()
	 * @generated
	 */
	int STRING_VALUE_STYLE = 47;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>String Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_STYLE__STRING_VALUE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.StringListValueStyleImpl <em>String List Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.StringListValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStringListValueStyle()
	 * @generated
	 */
	int STRING_LIST_VALUE_STYLE = 48;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LIST_VALUE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>String List Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LIST_VALUE_STYLE__STRING_LIST_VALUE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String List Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LIST_VALUE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.EObjectValueStyleImpl <em>EObject Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.EObjectValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getEObjectValueStyle()
	 * @generated
	 */
	int EOBJECT_VALUE_STYLE = 49;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_VALUE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>EObject Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_VALUE_STYLE__EOBJECT_VALUE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EObject Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_VALUE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.EObjectListValueStyleImpl <em>EObject List Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.EObjectListValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getEObjectListValueStyle()
	 * @generated
	 */
	int EOBJECT_LIST_VALUE_STYLE = 50;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LIST_VALUE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>EObject List Value</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LIST_VALUE_STYLE__EOBJECT_LIST_VALUE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EObject List Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_LIST_VALUE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ByteArrayValueStyleImpl <em>Byte Array Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ByteArrayValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getByteArrayValueStyle()
	 * @generated
	 */
	int BYTE_ARRAY_VALUE_STYLE = 51;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BYTE_ARRAY_VALUE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>Byte Array Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BYTE_ARRAY_VALUE_STYLE__BYTE_ARRAY_VALUE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Byte Array Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BYTE_ARRAY_VALUE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.BooleanListValueStyleImpl <em>Boolean List Value Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.BooleanListValueStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBooleanListValueStyle()
	 * @generated
	 */
	int BOOLEAN_LIST_VALUE_STYLE = 52;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LIST_VALUE_STYLE__NAME = NAMED_STYLE__NAME;

	/**
	 * The feature id for the '<em><b>Boolean List Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LIST_VALUE_STYLE__BOOLEAN_LIST_VALUE = NAMED_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean List Value Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LIST_VALUE_STYLE_FEATURE_COUNT = NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.DiagramLinkStyleImpl <em>Diagram Link Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.DiagramLinkStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDiagramLinkStyle()
	 * @generated
	 */
	int DIAGRAM_LINK_STYLE = 54;

	/**
	 * The feature id for the '<em><b>Diagram Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK_STYLE__DIAGRAM_LINK = STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Diagram Link Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_LINK_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.HintedDiagramLinkStyleImpl <em>Hinted Diagram Link Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.HintedDiagramLinkStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getHintedDiagramLinkStyle()
	 * @generated
	 */
	int HINTED_DIAGRAM_LINK_STYLE = 53;

	/**
	 * The feature id for the '<em><b>Diagram Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HINTED_DIAGRAM_LINK_STYLE__DIAGRAM_LINK = DIAGRAM_LINK_STYLE__DIAGRAM_LINK;

	/**
	 * The feature id for the '<em><b>Hint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HINTED_DIAGRAM_LINK_STYLE__HINT = DIAGRAM_LINK_STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Hinted Diagram Link Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HINTED_DIAGRAM_LINK_STYLE_FEATURE_COUNT = DIAGRAM_LINK_STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.MultiDiagramLinkStyleImpl <em>Multi Diagram Link Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.MultiDiagramLinkStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getMultiDiagramLinkStyle()
	 * @generated
	 */
	int MULTI_DIAGRAM_LINK_STYLE = 55;

	/**
	 * The feature id for the '<em><b>Diagram Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_DIAGRAM_LINK_STYLE__DIAGRAM_LINKS = STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Multi Diagram Link Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_DIAGRAM_LINK_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.TextStyleImpl <em>Text Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.TextStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getTextStyle()
	 * @generated
	 */
	int TEXT_STYLE = 56;

	/**
	 * The feature id for the '<em><b>Text Alignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_STYLE__TEXT_ALIGNMENT = STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.LineTypeStyleImpl <em>Line Type Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.LineTypeStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getLineTypeStyle()
	 * @generated
	 */
	int LINE_TYPE_STYLE = 57;

	/**
	 * The feature id for the '<em><b>Line Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_TYPE_STYLE__LINE_TYPE = STYLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Line Type Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_TYPE_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ArrowStyleImpl <em>Arrow Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ArrowStyleImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getArrowStyle()
	 * @generated
	 */
	int ARROW_STYLE = 58;

	/**
	 * The feature id for the '<em><b>Arrow Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_STYLE__ARROW_SOURCE = STYLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Arrow Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_STYLE__ARROW_TARGET = STYLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Arrow Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARROW_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl <em>Shape</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ShapeImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getShape()
	 * @generated
	 */
	int SHAPE = 59;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__EANNOTATIONS = NODE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__VISIBLE = NODE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__TYPE = NODE__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__MUTABLE = NODE__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__SOURCE_EDGES = NODE__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__TARGET_EDGES = NODE__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__PERSISTED_CHILDREN = NODE__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__STYLES = NODE__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__ELEMENT = NODE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__DIAGRAM = NODE__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__TRANSIENT_CHILDREN = NODE__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Layout Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__LAYOUT_CONSTRAINT = NODE__LAYOUT_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Font Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__FONT_COLOR = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Font Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__FONT_NAME = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Font Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__FONT_HEIGHT = NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Bold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__BOLD = NODE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Italic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__ITALIC = NODE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Underline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__UNDERLINE = NODE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Strike Through</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__STRIKE_THROUGH = NODE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__DESCRIPTION = NODE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__FILL_COLOR = NODE_FEATURE_COUNT + 8;
	/**
	 * The feature id for the '<em><b>Transparency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__TRANSPARENCY = NODE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Gradient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__GRADIENT = NODE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__LINE_COLOR = NODE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__LINE_WIDTH = NODE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Rounded Bendpoints Radius</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__ROUNDED_BENDPOINTS_RADIUS = NODE_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Shape</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_FEATURE_COUNT = NODE_FEATURE_COUNT + 14;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.CompartmentImpl <em>Compartment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.CompartmentImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getCompartment()
	 * @generated
	 */
	int COMPARTMENT = 60;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ListCompartmentImpl <em>List Compartment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ListCompartmentImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getListCompartment()
	 * @generated
	 */
	int LIST_COMPARTMENT = 61;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.ConnectorImpl <em>Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.ConnectorImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getConnector()
	 * @generated
	 */
	int CONNECTOR = 62;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.StandardDiagramImpl <em>Standard Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.StandardDiagramImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStandardDiagram()
	 * @generated
	 */
	int STANDARD_DIAGRAM = 63;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.BasicDecorationNodeImpl <em>Basic Decoration Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.BasicDecorationNodeImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBasicDecorationNode()
	 * @generated
	 */
	int BASIC_DECORATION_NODE = 65;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__EANNOTATIONS = NODE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__VISIBLE = NODE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__TYPE = NODE__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__MUTABLE = NODE__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__SOURCE_EDGES = NODE__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__TARGET_EDGES = NODE__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__PERSISTED_CHILDREN = NODE__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__STYLES = NODE__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__ELEMENT = NODE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__DIAGRAM = NODE__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__TRANSIENT_CHILDREN = NODE__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Layout Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE__LAYOUT_CONSTRAINT = NODE__LAYOUT_CONSTRAINT;

	/**
	 * The number of structural features of the '<em>Basic Decoration Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_DECORATION_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.DecorationNodeImpl <em>Decoration Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.DecorationNodeImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDecorationNode()
	 * @generated
	 */
	int DECORATION_NODE = 64;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__EANNOTATIONS = BASIC_DECORATION_NODE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__VISIBLE = BASIC_DECORATION_NODE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__TYPE = BASIC_DECORATION_NODE__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__MUTABLE = BASIC_DECORATION_NODE__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__SOURCE_EDGES = BASIC_DECORATION_NODE__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__TARGET_EDGES = BASIC_DECORATION_NODE__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__PERSISTED_CHILDREN = BASIC_DECORATION_NODE__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__STYLES = BASIC_DECORATION_NODE__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__ELEMENT = BASIC_DECORATION_NODE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__DIAGRAM = BASIC_DECORATION_NODE__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__TRANSIENT_CHILDREN = BASIC_DECORATION_NODE__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Layout Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE__LAYOUT_CONSTRAINT = BASIC_DECORATION_NODE__LAYOUT_CONSTRAINT;

	/**
	 * The number of structural features of the '<em>Decoration Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECORATION_NODE_FEATURE_COUNT = BASIC_DECORATION_NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.BasicCompartmentImpl <em>Basic Compartment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.BasicCompartmentImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBasicCompartment()
	 * @generated
	 */
	int BASIC_COMPARTMENT = 66;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__EANNOTATIONS = DECORATION_NODE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__VISIBLE = DECORATION_NODE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__TYPE = DECORATION_NODE__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__MUTABLE = DECORATION_NODE__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__SOURCE_EDGES = DECORATION_NODE__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__TARGET_EDGES = DECORATION_NODE__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__PERSISTED_CHILDREN = DECORATION_NODE__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__STYLES = DECORATION_NODE__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__ELEMENT = DECORATION_NODE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__DIAGRAM = DECORATION_NODE__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__TRANSIENT_CHILDREN = DECORATION_NODE__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Layout Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__LAYOUT_CONSTRAINT = DECORATION_NODE__LAYOUT_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT__COLLAPSED = DECORATION_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Basic Compartment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_COMPARTMENT_FEATURE_COUNT = DECORATION_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__EANNOTATIONS = BASIC_COMPARTMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__VISIBLE = BASIC_COMPARTMENT__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__TYPE = BASIC_COMPARTMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__MUTABLE = BASIC_COMPARTMENT__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__SOURCE_EDGES = BASIC_COMPARTMENT__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__TARGET_EDGES = BASIC_COMPARTMENT__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__PERSISTED_CHILDREN = BASIC_COMPARTMENT__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__STYLES = BASIC_COMPARTMENT__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__ELEMENT = BASIC_COMPARTMENT__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__DIAGRAM = BASIC_COMPARTMENT__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__TRANSIENT_CHILDREN = BASIC_COMPARTMENT__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Layout Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__LAYOUT_CONSTRAINT = BASIC_COMPARTMENT__LAYOUT_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__COLLAPSED = BASIC_COMPARTMENT__COLLAPSED;

	/**
	 * The feature id for the '<em><b>Canonical</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__CANONICAL = BASIC_COMPARTMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Show Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT__SHOW_TITLE = BASIC_COMPARTMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Compartment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT_FEATURE_COUNT = BASIC_COMPARTMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__EANNOTATIONS = BASIC_COMPARTMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__VISIBLE = BASIC_COMPARTMENT__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__TYPE = BASIC_COMPARTMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__MUTABLE = BASIC_COMPARTMENT__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__SOURCE_EDGES = BASIC_COMPARTMENT__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__TARGET_EDGES = BASIC_COMPARTMENT__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__PERSISTED_CHILDREN = BASIC_COMPARTMENT__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__STYLES = BASIC_COMPARTMENT__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__ELEMENT = BASIC_COMPARTMENT__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__DIAGRAM = BASIC_COMPARTMENT__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__TRANSIENT_CHILDREN = BASIC_COMPARTMENT__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Layout Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__LAYOUT_CONSTRAINT = BASIC_COMPARTMENT__LAYOUT_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__COLLAPSED = BASIC_COMPARTMENT__COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sorting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__SORTING = BASIC_COMPARTMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sorting Keys</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__SORTING_KEYS = BASIC_COMPARTMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sorted Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__SORTED_OBJECTS = BASIC_COMPARTMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Filtering</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__FILTERING = BASIC_COMPARTMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Filtering Keys</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__FILTERING_KEYS = BASIC_COMPARTMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Filtered Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__FILTERED_OBJECTS = BASIC_COMPARTMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Show Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT__SHOW_TITLE = BASIC_COMPARTMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>List Compartment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_COMPARTMENT_FEATURE_COUNT = BASIC_COMPARTMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__EANNOTATIONS = EDGE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__VISIBLE = EDGE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__TYPE = EDGE__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__MUTABLE = EDGE__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__SOURCE_EDGES = EDGE__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__TARGET_EDGES = EDGE__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__PERSISTED_CHILDREN = EDGE__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__STYLES = EDGE__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__ELEMENT = EDGE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__DIAGRAM = EDGE__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__TRANSIENT_CHILDREN = EDGE__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__SOURCE = EDGE__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__TARGET = EDGE__TARGET;

	/**
	 * The feature id for the '<em><b>Bendpoints</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__BENDPOINTS = EDGE__BENDPOINTS;

	/**
	 * The feature id for the '<em><b>Source Anchor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__SOURCE_ANCHOR = EDGE__SOURCE_ANCHOR;

	/**
	 * The feature id for the '<em><b>Target Anchor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__TARGET_ANCHOR = EDGE__TARGET_ANCHOR;

	/**
	 * The feature id for the '<em><b>Rounded Bendpoints Radius</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__ROUNDED_BENDPOINTS_RADIUS = EDGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Routing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__ROUTING = EDGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Smoothness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__SMOOTHNESS = EDGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Avoid Obstructions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__AVOID_OBSTRUCTIONS = EDGE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Closest Distance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__CLOSEST_DISTANCE = EDGE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Jump Link Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__JUMP_LINK_STATUS = EDGE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Jump Link Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__JUMP_LINK_TYPE = EDGE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Jump Links Reverse</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__JUMP_LINKS_REVERSE = EDGE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__LINE_COLOR = EDGE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__LINE_WIDTH = EDGE_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_FEATURE_COUNT = EDGE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__EANNOTATIONS = DIAGRAM__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__VISIBLE = DIAGRAM__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__TYPE = DIAGRAM__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__MUTABLE = DIAGRAM__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__SOURCE_EDGES = DIAGRAM__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__TARGET_EDGES = DIAGRAM__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__PERSISTED_CHILDREN = DIAGRAM__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__STYLES = DIAGRAM__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__ELEMENT = DIAGRAM__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__DIAGRAM = DIAGRAM__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__TRANSIENT_CHILDREN = DIAGRAM__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__NAME = DIAGRAM__NAME;

	/**
	 * The feature id for the '<em><b>Measurement Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__MEASUREMENT_UNIT = DIAGRAM__MEASUREMENT_UNIT;

	/**
	 * The feature id for the '<em><b>Persisted Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__PERSISTED_EDGES = DIAGRAM__PERSISTED_EDGES;

	/**
	 * The feature id for the '<em><b>Transient Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__TRANSIENT_EDGES = DIAGRAM__TRANSIENT_EDGES;

	/**
	 * The feature id for the '<em><b>Page X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__PAGE_X = DIAGRAM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Page Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__PAGE_Y = DIAGRAM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Page Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__PAGE_WIDTH = DIAGRAM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Page Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__PAGE_HEIGHT = DIAGRAM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Horizontal Guides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__HORIZONTAL_GUIDES = DIAGRAM_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Vertical Guides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__VERTICAL_GUIDES = DIAGRAM_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM__DESCRIPTION = DIAGRAM_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Standard Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_DIAGRAM_FEATURE_COUNT = DIAGRAM_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.BasicSemanticCompartmentImpl <em>Basic Semantic Compartment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.BasicSemanticCompartmentImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBasicSemanticCompartment()
	 * @generated
	 */
	int BASIC_SEMANTIC_COMPARTMENT = 67;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__EANNOTATIONS = BASIC_DECORATION_NODE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__VISIBLE = BASIC_DECORATION_NODE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__TYPE = BASIC_DECORATION_NODE__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__MUTABLE = BASIC_DECORATION_NODE__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__SOURCE_EDGES = BASIC_DECORATION_NODE__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__TARGET_EDGES = BASIC_DECORATION_NODE__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__PERSISTED_CHILDREN = BASIC_DECORATION_NODE__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__STYLES = BASIC_DECORATION_NODE__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__ELEMENT = BASIC_DECORATION_NODE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__DIAGRAM = BASIC_DECORATION_NODE__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__TRANSIENT_CHILDREN = BASIC_DECORATION_NODE__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Layout Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__LAYOUT_CONSTRAINT = BASIC_DECORATION_NODE__LAYOUT_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT__COLLAPSED = BASIC_DECORATION_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Basic Semantic Compartment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_SEMANTIC_COMPARTMENT_FEATURE_COUNT = BASIC_DECORATION_NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.impl.SemanticListCompartmentImpl <em>Semantic List Compartment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.impl.SemanticListCompartmentImpl
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSemanticListCompartment()
	 * @generated
	 */
	int SEMANTIC_LIST_COMPARTMENT = 68;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__EANNOTATIONS = BASIC_SEMANTIC_COMPARTMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__VISIBLE = BASIC_SEMANTIC_COMPARTMENT__VISIBLE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__TYPE = BASIC_SEMANTIC_COMPARTMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__MUTABLE = BASIC_SEMANTIC_COMPARTMENT__MUTABLE;

	/**
	 * The feature id for the '<em><b>Source Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__SOURCE_EDGES = BASIC_SEMANTIC_COMPARTMENT__SOURCE_EDGES;

	/**
	 * The feature id for the '<em><b>Target Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__TARGET_EDGES = BASIC_SEMANTIC_COMPARTMENT__TARGET_EDGES;

	/**
	 * The feature id for the '<em><b>Persisted Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__PERSISTED_CHILDREN = BASIC_SEMANTIC_COMPARTMENT__PERSISTED_CHILDREN;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__STYLES = BASIC_SEMANTIC_COMPARTMENT__STYLES;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__ELEMENT = BASIC_SEMANTIC_COMPARTMENT__ELEMENT;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__DIAGRAM = BASIC_SEMANTIC_COMPARTMENT__DIAGRAM;

	/**
	 * The feature id for the '<em><b>Transient Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__TRANSIENT_CHILDREN = BASIC_SEMANTIC_COMPARTMENT__TRANSIENT_CHILDREN;

	/**
	 * The feature id for the '<em><b>Layout Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__LAYOUT_CONSTRAINT = BASIC_SEMANTIC_COMPARTMENT__LAYOUT_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__COLLAPSED = BASIC_SEMANTIC_COMPARTMENT__COLLAPSED;

	/**
	 * The feature id for the '<em><b>Sorting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__SORTING = BASIC_SEMANTIC_COMPARTMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sorting Keys</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__SORTING_KEYS = BASIC_SEMANTIC_COMPARTMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sorted Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__SORTED_OBJECTS = BASIC_SEMANTIC_COMPARTMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Filtering</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__FILTERING = BASIC_SEMANTIC_COMPARTMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Filtering Keys</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__FILTERING_KEYS = BASIC_SEMANTIC_COMPARTMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Filtered Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__FILTERED_OBJECTS = BASIC_SEMANTIC_COMPARTMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Show Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT__SHOW_TITLE = BASIC_SEMANTIC_COMPARTMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Semantic List Compartment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_LIST_COMPARTMENT_FEATURE_COUNT = BASIC_SEMANTIC_COMPARTMENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.Sorting <em>Sorting</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.Sorting
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSorting()
	 * @generated
	 */
	int SORTING = 70;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.Filtering <em>Filtering</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.Filtering
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getFiltering()
	 * @generated
	 */
	int FILTERING = 71;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.Routing <em>Routing</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.Routing
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRouting()
	 * @generated
	 */
	int ROUTING = 72;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.Smoothness <em>Smoothness</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.Smoothness
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSmoothness()
	 * @generated
	 */
	int SMOOTHNESS = 73;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.JumpLinkStatus <em>Jump Link Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.JumpLinkStatus
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getJumpLinkStatus()
	 * @generated
	 */
	int JUMP_LINK_STATUS = 74;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.JumpLinkType <em>Jump Link Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.JumpLinkType
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getJumpLinkType()
	 * @generated
	 */
	int JUMP_LINK_TYPE = 75;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.Alignment <em>Alignment</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.Alignment
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getAlignment()
	 * @generated
	 */
	int ALIGNMENT = 76;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.SortingDirection <em>Sorting Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.SortingDirection
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSortingDirection()
	 * @generated
	 */
	int SORTING_DIRECTION = 77;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.MeasurementUnit <em>Measurement Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.MeasurementUnit
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getMeasurementUnit()
	 * @generated
	 */
	int MEASUREMENT_UNIT = 78;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.TextAlignment <em>Text Alignment</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.TextAlignment
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getTextAlignment()
	 * @generated
	 */
	int TEXT_ALIGNMENT = 79;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.LineType <em>Line Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.LineType
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getLineType()
	 * @generated
	 */
	int LINE_TYPE = 80;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.ArrowType <em>Arrow Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.ArrowType
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getArrowType()
	 * @generated
	 */
	int ARROW_TYPE = 81;

	/**
	 * The meta object id for the '{@link org.eclipse.gmf.runtime.notation.GradientStyle <em>Gradient Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.GradientStyle
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getGradientStyle()
	 * @generated
	 */
	int GRADIENT_STYLE = 82;

	/**
	 * The meta object id for the '<em>Relative Bendpoint List</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRelativeBendpointList()
	 * @generated
	 */
	int RELATIVE_BENDPOINT_LIST = 83;

	/**
	 * The meta object id for the '<em>Filter Key List</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getFilterKeyList()
	 * @generated
	 */
	int FILTER_KEY_LIST = 84;

	/**
	 * The meta object id for the '<em>Sort Key Map</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Map
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSortKeyMap()
	 * @generated
	 */
	int SORT_KEY_MAP = 85;


	/**
	 * The meta object id for the '<em>Gradient Data</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gmf.runtime.notation.datatype.GradientData
	 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getGradientData()
	 * @generated
	 */
	int GRADIENT_DATA = 86;


	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Edge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Edge
	 * @generated
	 */
	EClass getEdge();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.runtime.notation.Edge#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Edge#getSource()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_Source();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.runtime.notation.Edge#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Edge#getTarget()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_Target();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.gmf.runtime.notation.Edge#getBendpoints <em>Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Bendpoints</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Edge#getBendpoints()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_Bendpoints();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.gmf.runtime.notation.Edge#getSourceAnchor <em>Source Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source Anchor</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Edge#getSourceAnchor()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_SourceAnchor();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.gmf.runtime.notation.Edge#getTargetAnchor <em>Target Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target Anchor</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Edge#getTargetAnchor()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_TargetAnchor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.gmf.runtime.notation.Node#getLayoutConstraint <em>Layout Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Layout Constraint</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Node#getLayoutConstraint()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_LayoutConstraint();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Style <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Style
	 * @generated
	 */
	EClass getStyle();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.FillStyle <em>Fill Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fill Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FillStyle
	 * @generated
	 */
	EClass getFillStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FillStyle#getFillColor <em>Fill Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Color</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FillStyle#getFillColor()
	 * @see #getFillStyle()
	 * @generated
	 */
	EAttribute getFillStyle_FillColor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FillStyle#getTransparency <em>Transparency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transparency</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FillStyle#getTransparency()
	 * @see #getFillStyle()
	 * @generated
	 */
	EAttribute getFillStyle_Transparency();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FillStyle#getGradient <em>Gradient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gradient</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FillStyle#getGradient()
	 * @see #getFillStyle()
	 * @generated
	 */
	EAttribute getFillStyle_Gradient();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.LineStyle <em>Line Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Line Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.LineStyle
	 * @generated
	 */
	EClass getLineStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.LineStyle#getLineColor <em>Line Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Color</em>'.
	 * @see org.eclipse.gmf.runtime.notation.LineStyle#getLineColor()
	 * @see #getLineStyle()
	 * @generated
	 */
	EAttribute getLineStyle_LineColor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.LineStyle#getLineWidth <em>Line Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Width</em>'.
	 * @see org.eclipse.gmf.runtime.notation.LineStyle#getLineWidth()
	 * @see #getLineStyle()
	 * @generated
	 */
	EAttribute getLineStyle_LineWidth();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.FontStyle <em>Font Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Font Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FontStyle
	 * @generated
	 */
	EClass getFontStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FontStyle#getFontColor <em>Font Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Font Color</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FontStyle#getFontColor()
	 * @see #getFontStyle()
	 * @generated
	 */
	EAttribute getFontStyle_FontColor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FontStyle#getFontName <em>Font Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Font Name</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FontStyle#getFontName()
	 * @see #getFontStyle()
	 * @generated
	 */
	EAttribute getFontStyle_FontName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FontStyle#getFontHeight <em>Font Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Font Height</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FontStyle#getFontHeight()
	 * @see #getFontStyle()
	 * @generated
	 */
	EAttribute getFontStyle_FontHeight();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FontStyle#isBold <em>Bold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bold</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FontStyle#isBold()
	 * @see #getFontStyle()
	 * @generated
	 */
	EAttribute getFontStyle_Bold();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FontStyle#isItalic <em>Italic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Italic</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FontStyle#isItalic()
	 * @see #getFontStyle()
	 * @generated
	 */
	EAttribute getFontStyle_Italic();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FontStyle#isUnderline <em>Underline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Underline</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FontStyle#isUnderline()
	 * @see #getFontStyle()
	 * @generated
	 */
	EAttribute getFontStyle_Underline();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FontStyle#isStrikeThrough <em>Strike Through</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Strike Through</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FontStyle#isStrikeThrough()
	 * @see #getFontStyle()
	 * @generated
	 */
	EAttribute getFontStyle_StrikeThrough();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.TitleStyle <em>Title Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Title Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.TitleStyle
	 * @generated
	 */
	EClass getTitleStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.TitleStyle#isShowTitle <em>Show Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Title</em>'.
	 * @see org.eclipse.gmf.runtime.notation.TitleStyle#isShowTitle()
	 * @see #getTitleStyle()
	 * @generated
	 */
	EAttribute getTitleStyle_ShowTitle();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.SortingStyle <em>Sorting Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sorting Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.SortingStyle
	 * @generated
	 */
	EClass getSortingStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.SortingStyle#getSorting <em>Sorting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sorting</em>'.
	 * @see org.eclipse.gmf.runtime.notation.SortingStyle#getSorting()
	 * @see #getSortingStyle()
	 * @generated
	 */
	EAttribute getSortingStyle_Sorting();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.SortingStyle#getSortingKeys <em>Sorting Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sorting Keys</em>'.
	 * @see org.eclipse.gmf.runtime.notation.SortingStyle#getSortingKeys()
	 * @see #getSortingStyle()
	 * @generated
	 */
	EAttribute getSortingStyle_SortingKeys();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gmf.runtime.notation.SortingStyle#getSortedObjects <em>Sorted Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sorted Objects</em>'.
	 * @see org.eclipse.gmf.runtime.notation.SortingStyle#getSortedObjects()
	 * @see #getSortingStyle()
	 * @generated
	 */
	EReference getSortingStyle_SortedObjects();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.DescriptionStyle <em>Description Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Description Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DescriptionStyle
	 * @generated
	 */
	EClass getDescriptionStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.DescriptionStyle#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DescriptionStyle#getDescription()
	 * @see #getDescriptionStyle()
	 * @generated
	 */
	EAttribute getDescriptionStyle_Description();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.LayoutConstraint <em>Layout Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Layout Constraint</em>'.
	 * @see org.eclipse.gmf.runtime.notation.LayoutConstraint
	 * @generated
	 */
	EClass getLayoutConstraint();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Size <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Size</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Size
	 * @generated
	 */
	EClass getSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.Size#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Size#getWidth()
	 * @see #getSize()
	 * @generated
	 */
	EAttribute getSize_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.Size#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Size#getHeight()
	 * @see #getSize()
	 * @generated
	 */
	EAttribute getSize_Height();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Location <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Location</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Location
	 * @generated
	 */
	EClass getLocation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.Location#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Location#getX()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_X();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.Location#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Location#getY()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_Y();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Bounds <em>Bounds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bounds</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Bounds
	 * @generated
	 */
	EClass getBounds();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Ratio <em>Ratio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ratio</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Ratio
	 * @generated
	 */
	EClass getRatio();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.Ratio#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Ratio#getValue()
	 * @see #getRatio()
	 * @generated
	 */
	EAttribute getRatio_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Anchor <em>Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Anchor</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Anchor
	 * @generated
	 */
	EClass getAnchor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Bendpoints <em>Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bendpoints</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Bendpoints
	 * @generated
	 */
	EClass getBendpoints();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.IdentityAnchor <em>Identity Anchor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identity Anchor</em>'.
	 * @see org.eclipse.gmf.runtime.notation.IdentityAnchor
	 * @generated
	 */
	EClass getIdentityAnchor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.IdentityAnchor#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.gmf.runtime.notation.IdentityAnchor#getId()
	 * @see #getIdentityAnchor()
	 * @generated
	 */
	EAttribute getIdentityAnchor_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.RoutingStyle <em>Routing Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Routing Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RoutingStyle
	 * @generated
	 */
	EClass getRoutingStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.RoutingStyle#getRouting <em>Routing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Routing</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RoutingStyle#getRouting()
	 * @see #getRoutingStyle()
	 * @generated
	 */
	EAttribute getRoutingStyle_Routing();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.RoutingStyle#getSmoothness <em>Smoothness</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Smoothness</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RoutingStyle#getSmoothness()
	 * @see #getRoutingStyle()
	 * @generated
	 */
	EAttribute getRoutingStyle_Smoothness();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.RoutingStyle#isAvoidObstructions <em>Avoid Obstructions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Avoid Obstructions</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RoutingStyle#isAvoidObstructions()
	 * @see #getRoutingStyle()
	 * @generated
	 */
	EAttribute getRoutingStyle_AvoidObstructions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.RoutingStyle#isClosestDistance <em>Closest Distance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Closest Distance</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RoutingStyle#isClosestDistance()
	 * @see #getRoutingStyle()
	 * @generated
	 */
	EAttribute getRoutingStyle_ClosestDistance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.RoutingStyle#getJumpLinkStatus <em>Jump Link Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jump Link Status</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RoutingStyle#getJumpLinkStatus()
	 * @see #getRoutingStyle()
	 * @generated
	 */
	EAttribute getRoutingStyle_JumpLinkStatus();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.RoutingStyle#getJumpLinkType <em>Jump Link Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jump Link Type</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RoutingStyle#getJumpLinkType()
	 * @see #getRoutingStyle()
	 * @generated
	 */
	EAttribute getRoutingStyle_JumpLinkType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.RoutingStyle#isJumpLinksReverse <em>Jump Links Reverse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jump Links Reverse</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RoutingStyle#isJumpLinksReverse()
	 * @see #getRoutingStyle()
	 * @generated
	 */
	EAttribute getRoutingStyle_JumpLinksReverse();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.RoutingStyle#getRoundedBendpointsRadius <em>Rounded Bendpoints Radius</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rounded Bendpoints Radius</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RoutingStyle#getRoundedBendpointsRadius()
	 * @see #getRoutingStyle()
	 * Left in for backwards compatibility
	 * @deprecated Use getRoundedCornersStyle_RoundedBendpointsRadius() instead
	 */
	EAttribute getRoutingStyle_RoundedBendpointsRadius();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.View <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View</em>'.
	 * @see org.eclipse.gmf.runtime.notation.View
	 * @generated
	 */
	EClass getView();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.View#isVisible <em>Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visible</em>'.
	 * @see org.eclipse.gmf.runtime.notation.View#isVisible()
	 * @see #getView()
	 * @generated
	 */
	EAttribute getView_Visible();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.View#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.gmf.runtime.notation.View#getType()
	 * @see #getView()
	 * @generated
	 */
	EAttribute getView_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.View#isMutable <em>Mutable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mutable</em>'.
	 * @see org.eclipse.gmf.runtime.notation.View#isMutable()
	 * @see #getView()
	 * @generated
	 */
	EAttribute getView_Mutable();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gmf.runtime.notation.View#getSourceEdges <em>Source Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Source Edges</em>'.
	 * @see org.eclipse.gmf.runtime.notation.View#getSourceEdges()
	 * @see #getView()
	 * @generated
	 */
	EReference getView_SourceEdges();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gmf.runtime.notation.View#getTargetEdges <em>Target Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Target Edges</em>'.
	 * @see org.eclipse.gmf.runtime.notation.View#getTargetEdges()
	 * @see #getView()
	 * @generated
	 */
	EReference getView_TargetEdges();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gmf.runtime.notation.View#getPersistedChildren <em>Persisted Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persisted Children</em>'.
	 * @see org.eclipse.gmf.runtime.notation.View#getPersistedChildren()
	 * @see #getView()
	 * @generated
	 */
	EReference getView_PersistedChildren();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gmf.runtime.notation.View#getStyles <em>Styles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Styles</em>'.
	 * @see org.eclipse.gmf.runtime.notation.View#getStyles()
	 * @see #getView()
	 * @generated
	 */
	EReference getView_Styles();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.runtime.notation.View#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see org.eclipse.gmf.runtime.notation.View#getElement()
	 * @see #getView()
	 * @generated
	 */
	EReference getView_Element();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.runtime.notation.View#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Diagram</em>'.
	 * @see org.eclipse.gmf.runtime.notation.View#getDiagram()
	 * @see #getView()
	 * @generated
	 */
	EReference getView_Diagram();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gmf.runtime.notation.View#getTransientChildren <em>Transient Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transient Children</em>'.
	 * @see org.eclipse.gmf.runtime.notation.View#getTransientChildren()
	 * @see #getView()
	 * @generated
	 */
	EReference getView_TransientChildren();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.RelativeBendpoints <em>Relative Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relative Bendpoints</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RelativeBendpoints
	 * @generated
	 */
	EClass getRelativeBendpoints();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.RelativeBendpoints#getPoints <em>Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Points</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RelativeBendpoints#getPoints()
	 * @see #getRelativeBendpoints()
	 * @generated
	 */
	EAttribute getRelativeBendpoints_Points();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Diagram
	 * @generated
	 */
	EClass getDiagram();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.Diagram#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Diagram#getName()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.Diagram#getMeasurementUnit <em>Measurement Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Measurement Unit</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Diagram#getMeasurementUnit()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_MeasurementUnit();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gmf.runtime.notation.Diagram#getPersistedEdges <em>Persisted Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persisted Edges</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Diagram#getPersistedEdges()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_PersistedEdges();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gmf.runtime.notation.Diagram#getTransientEdges <em>Transient Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transient Edges</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Diagram#getTransientEdges()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_TransientEdges();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Image
	 * @generated
	 */
	EClass getImage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.Image#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Image#getData()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Data();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.CanonicalStyle <em>Canonical Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Canonical Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.CanonicalStyle
	 * @generated
	 */
	EClass getCanonicalStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.CanonicalStyle#isCanonical <em>Canonical</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Canonical</em>'.
	 * @see org.eclipse.gmf.runtime.notation.CanonicalStyle#isCanonical()
	 * @see #getCanonicalStyle()
	 * @generated
	 */
	EAttribute getCanonicalStyle_Canonical();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.ShapeStyle <em>Shape Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shape Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ShapeStyle
	 * @generated
	 */
	EClass getShapeStyle();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.ConnectorStyle <em>Connector Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ConnectorStyle
	 * @generated
	 */
	EClass getConnectorStyle();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.PageStyle <em>Page Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.PageStyle
	 * @generated
	 */
	EClass getPageStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.PageStyle#getPageX <em>Page X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page X</em>'.
	 * @see org.eclipse.gmf.runtime.notation.PageStyle#getPageX()
	 * @see #getPageStyle()
	 * @generated
	 */
	EAttribute getPageStyle_PageX();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.PageStyle#getPageY <em>Page Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Y</em>'.
	 * @see org.eclipse.gmf.runtime.notation.PageStyle#getPageY()
	 * @see #getPageStyle()
	 * @generated
	 */
	EAttribute getPageStyle_PageY();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.PageStyle#getPageWidth <em>Page Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Width</em>'.
	 * @see org.eclipse.gmf.runtime.notation.PageStyle#getPageWidth()
	 * @see #getPageStyle()
	 * @generated
	 */
	EAttribute getPageStyle_PageWidth();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.PageStyle#getPageHeight <em>Page Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Height</em>'.
	 * @see org.eclipse.gmf.runtime.notation.PageStyle#getPageHeight()
	 * @see #getPageStyle()
	 * @generated
	 */
	EAttribute getPageStyle_PageHeight();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.DrawerStyle <em>Drawer Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Drawer Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DrawerStyle
	 * @generated
	 */
	EClass getDrawerStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.DrawerStyle#isCollapsed <em>Collapsed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Collapsed</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DrawerStyle#isCollapsed()
	 * @see #getDrawerStyle()
	 * @generated
	 */
	EAttribute getDrawerStyle_Collapsed();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.GuideStyle <em>Guide Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guide Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.GuideStyle
	 * @generated
	 */
	EClass getGuideStyle();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gmf.runtime.notation.GuideStyle#getHorizontalGuides <em>Horizontal Guides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Horizontal Guides</em>'.
	 * @see org.eclipse.gmf.runtime.notation.GuideStyle#getHorizontalGuides()
	 * @see #getGuideStyle()
	 * @generated
	 */
	EReference getGuideStyle_HorizontalGuides();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gmf.runtime.notation.GuideStyle#getVerticalGuides <em>Vertical Guides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Vertical Guides</em>'.
	 * @see org.eclipse.gmf.runtime.notation.GuideStyle#getVerticalGuides()
	 * @see #getGuideStyle()
	 * @generated
	 */
	EReference getGuideStyle_VerticalGuides();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Guide <em>Guide</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guide</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Guide
	 * @generated
	 */
	EClass getGuide();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.Guide#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Guide#getPosition()
	 * @see #getGuide()
	 * @generated
	 */
	EAttribute getGuide_Position();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.gmf.runtime.notation.Guide#getNodeMap <em>Node Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Node Map</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Guide#getNodeMap()
	 * @see #getGuide()
	 * @generated
	 */
	EReference getGuide_NodeMap();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Node Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model features="value key" 
	 *        valueDefault="Center" valueDataType="org.eclipse.gmf.runtime.notation.Alignment"
	 *        keyType="org.eclipse.gmf.runtime.notation.Node" keyRequired="true"
	 * @generated
	 */
	EClass getNodeEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getNodeEntry()
	 * @generated
	 */
	EAttribute getNodeEntry_Value();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getNodeEntry()
	 * @generated
	 */
	EReference getNodeEntry_Key();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.FilteringStyle <em>Filtering Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filtering Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FilteringStyle
	 * @generated
	 */
	EClass getFilteringStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FilteringStyle#getFiltering <em>Filtering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filtering</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FilteringStyle#getFiltering()
	 * @see #getFilteringStyle()
	 * @generated
	 */
	EAttribute getFilteringStyle_Filtering();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.FilteringStyle#getFilteringKeys <em>Filtering Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filtering Keys</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FilteringStyle#getFilteringKeys()
	 * @see #getFilteringStyle()
	 * @generated
	 */
	EAttribute getFilteringStyle_FilteringKeys();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gmf.runtime.notation.FilteringStyle#getFilteredObjects <em>Filtered Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Filtered Objects</em>'.
	 * @see org.eclipse.gmf.runtime.notation.FilteringStyle#getFilteredObjects()
	 * @see #getFilteringStyle()
	 * @generated
	 */
	EReference getFilteringStyle_FilteredObjects();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.DiagramStyle <em>Diagram Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DiagramStyle
	 * @generated
	 */
	EClass getDiagramStyle();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.ImageStyle <em>Image Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ImageStyle
	 * @generated
	 */
	EClass getImageStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.ImageStyle#getAntiAlias <em>Anti Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Anti Alias</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ImageStyle#getAntiAlias()
	 * @see #getImageStyle()
	 * @generated
	 */
	EAttribute getImageStyle_AntiAlias();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.ImageStyle#getMaintainAspectRatio <em>Maintain Aspect Ratio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maintain Aspect Ratio</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ImageStyle#getMaintainAspectRatio()
	 * @see #getImageStyle()
	 * @generated
	 */
	EAttribute getImageStyle_MaintainAspectRatio();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.gmf.runtime.notation.ImageStyle#getCropBound <em>Crop Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Crop Bound</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ImageStyle#getCropBound()
	 * @see #getImageStyle()
	 * @generated
	 */
	EReference getImageStyle_CropBound();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.ImageBufferStyle <em>Image Buffer Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image Buffer Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ImageBufferStyle
	 * @generated
	 */
	EClass getImageBufferStyle();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.gmf.runtime.notation.ImageBufferStyle#getImageBuffer <em>Image Buffer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Image Buffer</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ImageBufferStyle#getImageBuffer()
	 * @see #getImageBufferStyle()
	 * @generated
	 */
	EReference getImageBufferStyle_ImageBuffer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.PropertiesSetStyle <em>Properties Set Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Properties Set Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.PropertiesSetStyle
	 * @generated
	 */
	EClass getPropertiesSetStyle();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.gmf.runtime.notation.PropertiesSetStyle#getPropertiesMap <em>Properties Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Properties Map</em>'.
	 * @see org.eclipse.gmf.runtime.notation.PropertiesSetStyle#getPropertiesMap()
	 * @see #getPropertiesSetStyle()
	 * @generated
	 */
	EReference getPropertiesSetStyle_PropertiesMap();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Property Value Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Property Value Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="org.eclipse.gmf.runtime.notation.PropertyValue" valueContainment="true"
	 * @generated
	 */
	EClass getStringToPropertyValueMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToPropertyValueMapEntry()
	 * @generated
	 */
	EAttribute getStringToPropertyValueMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToPropertyValueMapEntry()
	 * @generated
	 */
	EReference getStringToPropertyValueMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.PropertyValue <em>Property Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.PropertyValue
	 * @generated
	 */
	EClass getPropertyValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.PropertyValue#getRawValue <em>Raw Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Raw Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.PropertyValue#getRawValue()
	 * @see #getPropertyValue()
	 * @generated
	 */
	EAttribute getPropertyValue_RawValue();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.runtime.notation.PropertyValue#getInstanceType <em>Instance Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Instance Type</em>'.
	 * @see org.eclipse.gmf.runtime.notation.PropertyValue#getInstanceType()
	 * @see #getPropertyValue()
	 * @generated
	 */
	EReference getPropertyValue_InstanceType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.SingleValueStyle <em>Single Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.SingleValueStyle
	 * @generated
	 */
	EClass getSingleValueStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.SingleValueStyle#getRawValue <em>Raw Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Raw Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.SingleValueStyle#getRawValue()
	 * @see #getSingleValueStyle()
	 * @generated
	 */
	EAttribute getSingleValueStyle_RawValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.ListValueStyle <em>List Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ListValueStyle
	 * @generated
	 */
	EClass getListValueStyle();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gmf.runtime.notation.ListValueStyle#getRawValuesList <em>Raw Values List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Raw Values List</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ListValueStyle#getRawValuesList()
	 * @see #getListValueStyle()
	 * @generated
	 */
	EAttribute getListValueStyle_RawValuesList();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.NamedStyle <em>Named Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.NamedStyle
	 * @generated
	 */
	EClass getNamedStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.NamedStyle#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.gmf.runtime.notation.NamedStyle#getName()
	 * @see #getNamedStyle()
	 * @generated
	 */
	EAttribute getNamedStyle_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.StringObjectConverter <em>String Object Converter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Object Converter</em>'.
	 * @see org.eclipse.gmf.runtime.notation.StringObjectConverter
	 * @generated
	 */
	EClass getStringObjectConverter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.DataTypeStyle <em>Data Type Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Type Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DataTypeStyle
	 * @generated
	 */
	EClass getDataTypeStyle();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.runtime.notation.DataTypeStyle#getInstanceType <em>Instance Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Instance Type</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DataTypeStyle#getInstanceType()
	 * @see #getDataTypeStyle()
	 * @generated
	 */
	EReference getDataTypeStyle_InstanceType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.IntValueStyle <em>Int Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.IntValueStyle
	 * @generated
	 */
	EClass getIntValueStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.IntValueStyle#getIntValue <em>Int Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Int Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.IntValueStyle#getIntValue()
	 * @see #getIntValueStyle()
	 * @generated
	 */
	EAttribute getIntValueStyle_IntValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.IntListValueStyle <em>Int List Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int List Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.IntListValueStyle
	 * @generated
	 */
	EClass getIntListValueStyle();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gmf.runtime.notation.IntListValueStyle#getIntListValue <em>Int List Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Int List Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.IntListValueStyle#getIntListValue()
	 * @see #getIntListValueStyle()
	 * @generated
	 */
	EAttribute getIntListValueStyle_IntListValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.BooleanValueStyle <em>Boolean Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.BooleanValueStyle
	 * @generated
	 */
	EClass getBooleanValueStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.BooleanValueStyle#isBooleanValue <em>Boolean Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.BooleanValueStyle#isBooleanValue()
	 * @see #getBooleanValueStyle()
	 * @generated
	 */
	EAttribute getBooleanValueStyle_BooleanValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.DoubleValueStyle <em>Double Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DoubleValueStyle
	 * @generated
	 */
	EClass getDoubleValueStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.DoubleValueStyle#getDoubleValue <em>Double Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Double Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DoubleValueStyle#getDoubleValue()
	 * @see #getDoubleValueStyle()
	 * @generated
	 */
	EAttribute getDoubleValueStyle_DoubleValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.DoubleListValueStyle <em>Double List Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double List Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DoubleListValueStyle
	 * @generated
	 */
	EClass getDoubleListValueStyle();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gmf.runtime.notation.DoubleListValueStyle#getDoubleListValue <em>Double List Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Double List Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DoubleListValueStyle#getDoubleListValue()
	 * @see #getDoubleListValueStyle()
	 * @generated
	 */
	EAttribute getDoubleListValueStyle_DoubleListValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.StringValueStyle <em>String Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.StringValueStyle
	 * @generated
	 */
	EClass getStringValueStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.StringValueStyle#getStringValue <em>String Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.StringValueStyle#getStringValue()
	 * @see #getStringValueStyle()
	 * @generated
	 */
	EAttribute getStringValueStyle_StringValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.StringListValueStyle <em>String List Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String List Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.StringListValueStyle
	 * @generated
	 */
	EClass getStringListValueStyle();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gmf.runtime.notation.StringListValueStyle#getStringListValue <em>String List Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>String List Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.StringListValueStyle#getStringListValue()
	 * @see #getStringListValueStyle()
	 * @generated
	 */
	EAttribute getStringListValueStyle_StringListValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.EObjectValueStyle <em>EObject Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EObject Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.EObjectValueStyle
	 * @generated
	 */
	EClass getEObjectValueStyle();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.runtime.notation.EObjectValueStyle#getEObjectValue <em>EObject Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EObject Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.EObjectValueStyle#getEObjectValue()
	 * @see #getEObjectValueStyle()
	 * @generated
	 */
	EReference getEObjectValueStyle_EObjectValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.EObjectListValueStyle <em>EObject List Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EObject List Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.EObjectListValueStyle
	 * @generated
	 */
	EClass getEObjectListValueStyle();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gmf.runtime.notation.EObjectListValueStyle#getEObjectListValue <em>EObject List Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>EObject List Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.EObjectListValueStyle#getEObjectListValue()
	 * @see #getEObjectListValueStyle()
	 * @generated
	 */
	EReference getEObjectListValueStyle_EObjectListValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.ByteArrayValueStyle <em>Byte Array Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Byte Array Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ByteArrayValueStyle
	 * @generated
	 */
	EClass getByteArrayValueStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.ByteArrayValueStyle#getByteArrayValue <em>Byte Array Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Byte Array Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ByteArrayValueStyle#getByteArrayValue()
	 * @see #getByteArrayValueStyle()
	 * @generated
	 */
	EAttribute getByteArrayValueStyle_ByteArrayValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.BooleanListValueStyle <em>Boolean List Value Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean List Value Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.BooleanListValueStyle
	 * @generated
	 */
	EClass getBooleanListValueStyle();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gmf.runtime.notation.BooleanListValueStyle#getBooleanListValue <em>Boolean List Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Boolean List Value</em>'.
	 * @see org.eclipse.gmf.runtime.notation.BooleanListValueStyle#getBooleanListValue()
	 * @see #getBooleanListValueStyle()
	 * @generated
	 */
	EAttribute getBooleanListValueStyle_BooleanListValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.HintedDiagramLinkStyle <em>Hinted Diagram Link Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hinted Diagram Link Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.HintedDiagramLinkStyle
	 * @generated
	 */
	EClass getHintedDiagramLinkStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.HintedDiagramLinkStyle#getHint <em>Hint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hint</em>'.
	 * @see org.eclipse.gmf.runtime.notation.HintedDiagramLinkStyle#getHint()
	 * @see #getHintedDiagramLinkStyle()
	 * @generated
	 */
	EAttribute getHintedDiagramLinkStyle_Hint();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.DiagramLinkStyle <em>Diagram Link Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram Link Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DiagramLinkStyle
	 * @generated
	 */
	EClass getDiagramLinkStyle();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gmf.runtime.notation.DiagramLinkStyle#getDiagramLink <em>Diagram Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Diagram Link</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DiagramLinkStyle#getDiagramLink()
	 * @see #getDiagramLinkStyle()
	 * @generated
	 */
	EReference getDiagramLinkStyle_DiagramLink();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.MultiDiagramLinkStyle <em>Multi Diagram Link Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Diagram Link Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.MultiDiagramLinkStyle
	 * @generated
	 */
	EClass getMultiDiagramLinkStyle();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gmf.runtime.notation.MultiDiagramLinkStyle#getDiagramLinks <em>Diagram Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Diagram Links</em>'.
	 * @see org.eclipse.gmf.runtime.notation.MultiDiagramLinkStyle#getDiagramLinks()
	 * @see #getMultiDiagramLinkStyle()
	 * @generated
	 */
	EReference getMultiDiagramLinkStyle_DiagramLinks();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.TextStyle <em>Text Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.TextStyle
	 * @generated
	 */
	EClass getTextStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.TextStyle#getTextAlignment <em>Text Alignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Alignment</em>'.
	 * @see org.eclipse.gmf.runtime.notation.TextStyle#getTextAlignment()
	 * @see #getTextStyle()
	 * @generated
	 */
	EAttribute getTextStyle_TextAlignment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.LineTypeStyle <em>Line Type Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Line Type Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.LineTypeStyle
	 * @generated
	 */
	EClass getLineTypeStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.LineTypeStyle#getLineType <em>Line Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Type</em>'.
	 * @see org.eclipse.gmf.runtime.notation.LineTypeStyle#getLineType()
	 * @see #getLineTypeStyle()
	 * @generated
	 */
	EAttribute getLineTypeStyle_LineType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.ArrowStyle <em>Arrow Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arrow Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ArrowStyle
	 * @generated
	 */
	EClass getArrowStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.ArrowStyle#getArrowSource <em>Arrow Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Arrow Source</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ArrowStyle#getArrowSource()
	 * @see #getArrowStyle()
	 * @generated
	 */
	EAttribute getArrowStyle_ArrowSource();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.ArrowStyle#getArrowTarget <em>Arrow Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Arrow Target</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ArrowStyle#getArrowTarget()
	 * @see #getArrowStyle()
	 * @generated
	 */
	EAttribute getArrowStyle_ArrowTarget();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Shape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shape</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Shape
	 * @generated
	 */
	EClass getShape();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Compartment <em>Compartment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compartment</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Compartment
	 * @generated
	 */
	EClass getCompartment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.ListCompartment <em>List Compartment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Compartment</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ListCompartment
	 * @generated
	 */
	EClass getListCompartment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.Connector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Connector
	 * @generated
	 */
	EClass getConnector();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.StandardDiagram <em>Standard Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Diagram</em>'.
	 * @see org.eclipse.gmf.runtime.notation.StandardDiagram
	 * @generated
	 */
	EClass getStandardDiagram();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.DecorationNode <em>Decoration Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decoration Node</em>'.
	 * @see org.eclipse.gmf.runtime.notation.DecorationNode
	 * @generated
	 */
	EClass getDecorationNode();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.BasicDecorationNode <em>Basic Decoration Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Basic Decoration Node</em>'.
	 * @see org.eclipse.gmf.runtime.notation.BasicDecorationNode
	 * @generated
	 */
	EClass getBasicDecorationNode();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.BasicCompartment <em>Basic Compartment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Basic Compartment</em>'.
	 * @see org.eclipse.gmf.runtime.notation.BasicCompartment
	 * @generated
	 */
	EClass getBasicCompartment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.BasicSemanticCompartment <em>Basic Semantic Compartment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Basic Semantic Compartment</em>'.
	 * @see org.eclipse.gmf.runtime.notation.BasicSemanticCompartment
	 * @generated
	 */
	EClass getBasicSemanticCompartment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.SemanticListCompartment <em>Semantic List Compartment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Semantic List Compartment</em>'.
	 * @see org.eclipse.gmf.runtime.notation.SemanticListCompartment
	 * @generated
	 */
	EClass getSemanticListCompartment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gmf.runtime.notation.RoundedCornersStyle <em>Rounded Corners Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rounded Corners Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RoundedCornersStyle
	 * @generated
	 */
	EClass getRoundedCornersStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gmf.runtime.notation.RoundedCornersStyle#getRoundedBendpointsRadius <em>Rounded Bendpoints Radius</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rounded Bendpoints Radius</em>'.
	 * @see org.eclipse.gmf.runtime.notation.RoundedCornersStyle#getRoundedBendpointsRadius()
	 * @see #getRoundedCornersStyle()
	 * @generated
	 */
	EAttribute getRoundedCornersStyle_RoundedBendpointsRadius();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.Sorting <em>Sorting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Sorting</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Sorting
	 * @generated
	 */
	EEnum getSorting();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.Filtering <em>Filtering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Filtering</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Filtering
	 * @generated
	 */
	EEnum getFiltering();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.Routing <em>Routing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Routing</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Routing
	 * @generated
	 */
	EEnum getRouting();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.Smoothness <em>Smoothness</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Smoothness</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Smoothness
	 * @generated
	 */
	EEnum getSmoothness();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.JumpLinkStatus <em>Jump Link Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Jump Link Status</em>'.
	 * @see org.eclipse.gmf.runtime.notation.JumpLinkStatus
	 * @generated
	 */
	EEnum getJumpLinkStatus();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.JumpLinkType <em>Jump Link Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Jump Link Type</em>'.
	 * @see org.eclipse.gmf.runtime.notation.JumpLinkType
	 * @generated
	 */
	EEnum getJumpLinkType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.Alignment <em>Alignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Alignment</em>'.
	 * @see org.eclipse.gmf.runtime.notation.Alignment
	 * @generated
	 */
	EEnum getAlignment();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.SortingDirection <em>Sorting Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Sorting Direction</em>'.
	 * @see org.eclipse.gmf.runtime.notation.SortingDirection
	 * @generated
	 */
	EEnum getSortingDirection();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.MeasurementUnit <em>Measurement Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Measurement Unit</em>'.
	 * @see org.eclipse.gmf.runtime.notation.MeasurementUnit
	 * @generated
	 */
	EEnum getMeasurementUnit();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.TextAlignment <em>Text Alignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Text Alignment</em>'.
	 * @see org.eclipse.gmf.runtime.notation.TextAlignment
	 * @generated
	 */
	EEnum getTextAlignment();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.LineType <em>Line Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Line Type</em>'.
	 * @see org.eclipse.gmf.runtime.notation.LineType
	 * @generated
	 */
	EEnum getLineType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.ArrowType <em>Arrow Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Arrow Type</em>'.
	 * @see org.eclipse.gmf.runtime.notation.ArrowType
	 * @generated
	 */
	EEnum getArrowType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gmf.runtime.notation.GradientStyle <em>Gradient Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Gradient Style</em>'.
	 * @see org.eclipse.gmf.runtime.notation.GradientStyle
	 * @generated
	 */
	EEnum getGradientStyle();

	/**
	 * Returns the meta object for data type '{@link java.util.List <em>Relative Bendpoint List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Relative Bendpoint List</em>'.
	 * @see java.util.List
	 * @model instanceClass="java.util.List"
	 * @generated
	 */
	EDataType getRelativeBendpointList();

	/**
	 * Returns the meta object for data type '{@link java.util.List <em>Filter Key List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Filter Key List</em>'.
	 * @see java.util.List
	 * @model instanceClass="java.util.List"
	 * @generated
	 */
	EDataType getFilterKeyList();

	/**
	 * Returns the meta object for data type '{@link java.util.Map <em>Sort Key Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Sort Key Map</em>'.
	 * @see java.util.Map
	 * @model instanceClass="java.util.Map"
	 * @generated
	 */
	EDataType getSortKeyMap();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.gmf.runtime.notation.datatype.GradientData <em>Gradient Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Gradient Data</em>'.
	 * @see org.eclipse.gmf.runtime.notation.datatype.GradientData
	 * @model instanceClass="org.eclipse.gmf.runtime.notation.datatype.GradientData"
	 * @generated
	 */
	EDataType getGradientData();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	NotationFactory getNotationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.EdgeImpl <em>Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.EdgeImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getEdge()
		 * @generated
		 */
		EClass EDGE = eINSTANCE.getEdge();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__SOURCE = eINSTANCE.getEdge_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__TARGET = eINSTANCE.getEdge_Target();

		/**
		 * The meta object literal for the '<em><b>Bendpoints</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__BENDPOINTS = eINSTANCE.getEdge_Bendpoints();

		/**
		 * The meta object literal for the '<em><b>Source Anchor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__SOURCE_ANCHOR = eINSTANCE.getEdge_SourceAnchor();

		/**
		 * The meta object literal for the '<em><b>Target Anchor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__TARGET_ANCHOR = eINSTANCE.getEdge_TargetAnchor();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.NodeImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Layout Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__LAYOUT_CONSTRAINT = eINSTANCE.getNode_LayoutConstraint();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.Style <em>Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.Style
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStyle()
		 * @generated
		 */
		EClass STYLE = eINSTANCE.getStyle();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.FillStyleImpl <em>Fill Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.FillStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getFillStyle()
		 * @generated
		 */
		EClass FILL_STYLE = eINSTANCE.getFillStyle();

		/**
		 * The meta object literal for the '<em><b>Fill Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILL_STYLE__FILL_COLOR = eINSTANCE.getFillStyle_FillColor();

		/**
		 * The meta object literal for the '<em><b>Transparency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILL_STYLE__TRANSPARENCY = eINSTANCE.getFillStyle_Transparency();

		/**
		 * The meta object literal for the '<em><b>Gradient</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILL_STYLE__GRADIENT = eINSTANCE.getFillStyle_Gradient();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.LineStyleImpl <em>Line Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.LineStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getLineStyle()
		 * @generated
		 */
		EClass LINE_STYLE = eINSTANCE.getLineStyle();

		/**
		 * The meta object literal for the '<em><b>Line Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_STYLE__LINE_COLOR = eINSTANCE.getLineStyle_LineColor();

		/**
		 * The meta object literal for the '<em><b>Line Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_STYLE__LINE_WIDTH = eINSTANCE.getLineStyle_LineWidth();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.FontStyleImpl <em>Font Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.FontStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getFontStyle()
		 * @generated
		 */
		EClass FONT_STYLE = eINSTANCE.getFontStyle();

		/**
		 * The meta object literal for the '<em><b>Font Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT_STYLE__FONT_COLOR = eINSTANCE.getFontStyle_FontColor();

		/**
		 * The meta object literal for the '<em><b>Font Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT_STYLE__FONT_NAME = eINSTANCE.getFontStyle_FontName();

		/**
		 * The meta object literal for the '<em><b>Font Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT_STYLE__FONT_HEIGHT = eINSTANCE.getFontStyle_FontHeight();

		/**
		 * The meta object literal for the '<em><b>Bold</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT_STYLE__BOLD = eINSTANCE.getFontStyle_Bold();

		/**
		 * The meta object literal for the '<em><b>Italic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT_STYLE__ITALIC = eINSTANCE.getFontStyle_Italic();

		/**
		 * The meta object literal for the '<em><b>Underline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT_STYLE__UNDERLINE = eINSTANCE.getFontStyle_Underline();

		/**
		 * The meta object literal for the '<em><b>Strike Through</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FONT_STYLE__STRIKE_THROUGH = eINSTANCE.getFontStyle_StrikeThrough();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.TitleStyleImpl <em>Title Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.TitleStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getTitleStyle()
		 * @generated
		 */
		EClass TITLE_STYLE = eINSTANCE.getTitleStyle();

		/**
		 * The meta object literal for the '<em><b>Show Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TITLE_STYLE__SHOW_TITLE = eINSTANCE.getTitleStyle_ShowTitle();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.SortingStyleImpl <em>Sorting Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.SortingStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSortingStyle()
		 * @generated
		 */
		EClass SORTING_STYLE = eINSTANCE.getSortingStyle();

		/**
		 * The meta object literal for the '<em><b>Sorting</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SORTING_STYLE__SORTING = eINSTANCE.getSortingStyle_Sorting();

		/**
		 * The meta object literal for the '<em><b>Sorting Keys</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SORTING_STYLE__SORTING_KEYS = eINSTANCE.getSortingStyle_SortingKeys();

		/**
		 * The meta object literal for the '<em><b>Sorted Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SORTING_STYLE__SORTED_OBJECTS = eINSTANCE.getSortingStyle_SortedObjects();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.DescriptionStyleImpl <em>Description Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.DescriptionStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDescriptionStyle()
		 * @generated
		 */
		EClass DESCRIPTION_STYLE = eINSTANCE.getDescriptionStyle();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTION_STYLE__DESCRIPTION = eINSTANCE.getDescriptionStyle_Description();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.LayoutConstraint <em>Layout Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.LayoutConstraint
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getLayoutConstraint()
		 * @generated
		 */
		EClass LAYOUT_CONSTRAINT = eINSTANCE.getLayoutConstraint();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.SizeImpl <em>Size</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.SizeImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSize()
		 * @generated
		 */
		EClass SIZE = eINSTANCE.getSize();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIZE__WIDTH = eINSTANCE.getSize_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIZE__HEIGHT = eINSTANCE.getSize_Height();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.LocationImpl <em>Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.LocationImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getLocation()
		 * @generated
		 */
		EClass LOCATION = eINSTANCE.getLocation();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATION__X = eINSTANCE.getLocation_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATION__Y = eINSTANCE.getLocation_Y();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.BoundsImpl <em>Bounds</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.BoundsImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBounds()
		 * @generated
		 */
		EClass BOUNDS = eINSTANCE.getBounds();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.RatioImpl <em>Ratio</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.RatioImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRatio()
		 * @generated
		 */
		EClass RATIO = eINSTANCE.getRatio();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RATIO__VALUE = eINSTANCE.getRatio_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.Anchor <em>Anchor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.Anchor
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getAnchor()
		 * @generated
		 */
		EClass ANCHOR = eINSTANCE.getAnchor();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.Bendpoints <em>Bendpoints</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.Bendpoints
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBendpoints()
		 * @generated
		 */
		EClass BENDPOINTS = eINSTANCE.getBendpoints();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.IdentityAnchorImpl <em>Identity Anchor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.IdentityAnchorImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getIdentityAnchor()
		 * @generated
		 */
		EClass IDENTITY_ANCHOR = eINSTANCE.getIdentityAnchor();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTITY_ANCHOR__ID = eINSTANCE.getIdentityAnchor_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.RoutingStyleImpl <em>Routing Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.RoutingStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRoutingStyle()
		 * @generated
		 */
		EClass ROUTING_STYLE = eINSTANCE.getRoutingStyle();

		/**
		 * The meta object literal for the '<em><b>Routing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTING_STYLE__ROUTING = eINSTANCE.getRoutingStyle_Routing();

		/**
		 * The meta object literal for the '<em><b>Smoothness</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTING_STYLE__SMOOTHNESS = eINSTANCE.getRoutingStyle_Smoothness();

		/**
		 * The meta object literal for the '<em><b>Avoid Obstructions</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTING_STYLE__AVOID_OBSTRUCTIONS = eINSTANCE.getRoutingStyle_AvoidObstructions();

		/**
		 * The meta object literal for the '<em><b>Closest Distance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTING_STYLE__CLOSEST_DISTANCE = eINSTANCE.getRoutingStyle_ClosestDistance();

		/**
		 * The meta object literal for the '<em><b>Jump Link Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTING_STYLE__JUMP_LINK_STATUS = eINSTANCE.getRoutingStyle_JumpLinkStatus();

		/**
		 * The meta object literal for the '<em><b>Jump Link Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTING_STYLE__JUMP_LINK_TYPE = eINSTANCE.getRoutingStyle_JumpLinkType();

		/**
		 * The meta object literal for the '<em><b>Jump Links Reverse</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTING_STYLE__JUMP_LINKS_REVERSE = eINSTANCE.getRoutingStyle_JumpLinksReverse();

		/**

		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ViewImpl <em>View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ViewImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getView()
		 * @generated
		 */
		EClass VIEW = eINSTANCE.getView();

		/**
		 * The meta object literal for the '<em><b>Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW__VISIBLE = eINSTANCE.getView_Visible();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW__TYPE = eINSTANCE.getView_Type();

		/**
		 * The meta object literal for the '<em><b>Mutable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW__MUTABLE = eINSTANCE.getView_Mutable();

		/**
		 * The meta object literal for the '<em><b>Source Edges</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIEW__SOURCE_EDGES = eINSTANCE.getView_SourceEdges();

		/**
		 * The meta object literal for the '<em><b>Target Edges</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIEW__TARGET_EDGES = eINSTANCE.getView_TargetEdges();

		/**
		 * The meta object literal for the '<em><b>Persisted Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIEW__PERSISTED_CHILDREN = eINSTANCE.getView_PersistedChildren();

		/**
		 * The meta object literal for the '<em><b>Styles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIEW__STYLES = eINSTANCE.getView_Styles();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIEW__ELEMENT = eINSTANCE.getView_Element();

		/**
		 * The meta object literal for the '<em><b>Diagram</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIEW__DIAGRAM = eINSTANCE.getView_Diagram();

		/**
		 * The meta object literal for the '<em><b>Transient Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIEW__TRANSIENT_CHILDREN = eINSTANCE.getView_TransientChildren();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.RelativeBendpointsImpl <em>Relative Bendpoints</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.RelativeBendpointsImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRelativeBendpoints()
		 * @generated
		 */
		EClass RELATIVE_BENDPOINTS = eINSTANCE.getRelativeBendpoints();

		/**
		 * The meta object literal for the '<em><b>Points</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIVE_BENDPOINTS__POINTS = eINSTANCE.getRelativeBendpoints_Points();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.DiagramImpl <em>Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.DiagramImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDiagram()
		 * @generated
		 */
		EClass DIAGRAM = eINSTANCE.getDiagram();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__NAME = eINSTANCE.getDiagram_Name();

		/**
		 * The meta object literal for the '<em><b>Measurement Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__MEASUREMENT_UNIT = eINSTANCE.getDiagram_MeasurementUnit();

		/**
		 * The meta object literal for the '<em><b>Persisted Edges</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__PERSISTED_EDGES = eINSTANCE.getDiagram_PersistedEdges();

		/**
		 * The meta object literal for the '<em><b>Transient Edges</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__TRANSIENT_EDGES = eINSTANCE.getDiagram_TransientEdges();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ImageImpl <em>Image</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ImageImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getImage()
		 * @generated
		 */
		EClass IMAGE = eINSTANCE.getImage();

		/**
		 * The meta object literal for the '<em><b>Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__DATA = eINSTANCE.getImage_Data();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.CanonicalStyleImpl <em>Canonical Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.CanonicalStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getCanonicalStyle()
		 * @generated
		 */
		EClass CANONICAL_STYLE = eINSTANCE.getCanonicalStyle();

		/**
		 * The meta object literal for the '<em><b>Canonical</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CANONICAL_STYLE__CANONICAL = eINSTANCE.getCanonicalStyle_Canonical();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ShapeStyleImpl <em>Shape Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ShapeStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getShapeStyle()
		 * @generated
		 */
		EClass SHAPE_STYLE = eINSTANCE.getShapeStyle();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ConnectorStyleImpl <em>Connector Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ConnectorStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getConnectorStyle()
		 * @generated
		 */
		EClass CONNECTOR_STYLE = eINSTANCE.getConnectorStyle();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.PageStyleImpl <em>Page Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.PageStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getPageStyle()
		 * @generated
		 */
		EClass PAGE_STYLE = eINSTANCE.getPageStyle();

		/**
		 * The meta object literal for the '<em><b>Page X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_STYLE__PAGE_X = eINSTANCE.getPageStyle_PageX();

		/**
		 * The meta object literal for the '<em><b>Page Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_STYLE__PAGE_Y = eINSTANCE.getPageStyle_PageY();

		/**
		 * The meta object literal for the '<em><b>Page Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_STYLE__PAGE_WIDTH = eINSTANCE.getPageStyle_PageWidth();

		/**
		 * The meta object literal for the '<em><b>Page Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_STYLE__PAGE_HEIGHT = eINSTANCE.getPageStyle_PageHeight();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.DrawerStyleImpl <em>Drawer Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.DrawerStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDrawerStyle()
		 * @generated
		 */
		EClass DRAWER_STYLE = eINSTANCE.getDrawerStyle();

		/**
		 * The meta object literal for the '<em><b>Collapsed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRAWER_STYLE__COLLAPSED = eINSTANCE.getDrawerStyle_Collapsed();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.GuideStyleImpl <em>Guide Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.GuideStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getGuideStyle()
		 * @generated
		 */
		EClass GUIDE_STYLE = eINSTANCE.getGuideStyle();

		/**
		 * The meta object literal for the '<em><b>Horizontal Guides</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GUIDE_STYLE__HORIZONTAL_GUIDES = eINSTANCE.getGuideStyle_HorizontalGuides();

		/**
		 * The meta object literal for the '<em><b>Vertical Guides</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GUIDE_STYLE__VERTICAL_GUIDES = eINSTANCE.getGuideStyle_VerticalGuides();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.GuideImpl <em>Guide</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.GuideImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getGuide()
		 * @generated
		 */
		EClass GUIDE = eINSTANCE.getGuide();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GUIDE__POSITION = eINSTANCE.getGuide_Position();

		/**
		 * The meta object literal for the '<em><b>Node Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GUIDE__NODE_MAP = eINSTANCE.getGuide_NodeMap();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.NodeEntryImpl <em>Node Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.NodeEntryImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getNodeEntry()
		 * @generated
		 */
		EClass NODE_ENTRY = eINSTANCE.getNodeEntry();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_ENTRY__VALUE = eINSTANCE.getNodeEntry_Value();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_ENTRY__KEY = eINSTANCE.getNodeEntry_Key();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.FilteringStyleImpl <em>Filtering Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.FilteringStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getFilteringStyle()
		 * @generated
		 */
		EClass FILTERING_STYLE = eINSTANCE.getFilteringStyle();

		/**
		 * The meta object literal for the '<em><b>Filtering</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTERING_STYLE__FILTERING = eINSTANCE.getFilteringStyle_Filtering();

		/**
		 * The meta object literal for the '<em><b>Filtering Keys</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTERING_STYLE__FILTERING_KEYS = eINSTANCE.getFilteringStyle_FilteringKeys();

		/**
		 * The meta object literal for the '<em><b>Filtered Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTERING_STYLE__FILTERED_OBJECTS = eINSTANCE.getFilteringStyle_FilteredObjects();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.DiagramStyleImpl <em>Diagram Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.DiagramStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDiagramStyle()
		 * @generated
		 */
		EClass DIAGRAM_STYLE = eINSTANCE.getDiagramStyle();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ImageStyleImpl <em>Image Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ImageStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getImageStyle()
		 * @generated
		 */
		EClass IMAGE_STYLE = eINSTANCE.getImageStyle();

		/**
		 * The meta object literal for the '<em><b>Anti Alias</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE_STYLE__ANTI_ALIAS = eINSTANCE.getImageStyle_AntiAlias();

		/**
		 * The meta object literal for the '<em><b>Maintain Aspect Ratio</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE_STYLE__MAINTAIN_ASPECT_RATIO = eINSTANCE.getImageStyle_MaintainAspectRatio();

		/**
		 * The meta object literal for the '<em><b>Crop Bound</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMAGE_STYLE__CROP_BOUND = eINSTANCE.getImageStyle_CropBound();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ImageBufferStyleImpl <em>Image Buffer Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ImageBufferStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getImageBufferStyle()
		 * @generated
		 */
		EClass IMAGE_BUFFER_STYLE = eINSTANCE.getImageBufferStyle();

		/**
		 * The meta object literal for the '<em><b>Image Buffer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMAGE_BUFFER_STYLE__IMAGE_BUFFER = eINSTANCE.getImageBufferStyle_ImageBuffer();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.PropertiesSetStyleImpl <em>Properties Set Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.PropertiesSetStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getPropertiesSetStyle()
		 * @generated
		 */
		EClass PROPERTIES_SET_STYLE = eINSTANCE.getPropertiesSetStyle();

		/**
		 * The meta object literal for the '<em><b>Properties Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTIES_SET_STYLE__PROPERTIES_MAP = eINSTANCE.getPropertiesSetStyle_PropertiesMap();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.StringToPropertyValueMapEntryImpl <em>String To Property Value Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.StringToPropertyValueMapEntryImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStringToPropertyValueMapEntry()
		 * @generated
		 */
		EClass STRING_TO_PROPERTY_VALUE_MAP_ENTRY = eINSTANCE.getStringToPropertyValueMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_PROPERTY_VALUE_MAP_ENTRY__KEY = eINSTANCE.getStringToPropertyValueMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_TO_PROPERTY_VALUE_MAP_ENTRY__VALUE = eINSTANCE.getStringToPropertyValueMapEntry_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.PropertyValueImpl <em>Property Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.PropertyValueImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getPropertyValue()
		 * @generated
		 */
		EClass PROPERTY_VALUE = eINSTANCE.getPropertyValue();

		/**
		 * The meta object literal for the '<em><b>Raw Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY_VALUE__RAW_VALUE = eINSTANCE.getPropertyValue_RawValue();

		/**
		 * The meta object literal for the '<em><b>Instance Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_VALUE__INSTANCE_TYPE = eINSTANCE.getPropertyValue_InstanceType();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.SingleValueStyleImpl <em>Single Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.SingleValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSingleValueStyle()
		 * @generated
		 */
		EClass SINGLE_VALUE_STYLE = eINSTANCE.getSingleValueStyle();

		/**
		 * The meta object literal for the '<em><b>Raw Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SINGLE_VALUE_STYLE__RAW_VALUE = eINSTANCE.getSingleValueStyle_RawValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ListValueStyleImpl <em>List Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ListValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getListValueStyle()
		 * @generated
		 */
		EClass LIST_VALUE_STYLE = eINSTANCE.getListValueStyle();

		/**
		 * The meta object literal for the '<em><b>Raw Values List</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIST_VALUE_STYLE__RAW_VALUES_LIST = eINSTANCE.getListValueStyle_RawValuesList();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.NamedStyleImpl <em>Named Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.NamedStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getNamedStyle()
		 * @generated
		 */
		EClass NAMED_STYLE = eINSTANCE.getNamedStyle();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_STYLE__NAME = eINSTANCE.getNamedStyle_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.StringObjectConverter <em>String Object Converter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.StringObjectConverter
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStringObjectConverter()
		 * @generated
		 */
		EClass STRING_OBJECT_CONVERTER = eINSTANCE.getStringObjectConverter();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.DataTypeStyleImpl <em>Data Type Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.DataTypeStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDataTypeStyle()
		 * @generated
		 */
		EClass DATA_TYPE_STYLE = eINSTANCE.getDataTypeStyle();

		/**
		 * The meta object literal for the '<em><b>Instance Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TYPE_STYLE__INSTANCE_TYPE = eINSTANCE.getDataTypeStyle_InstanceType();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.IntValueStyleImpl <em>Int Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.IntValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getIntValueStyle()
		 * @generated
		 */
		EClass INT_VALUE_STYLE = eINSTANCE.getIntValueStyle();

		/**
		 * The meta object literal for the '<em><b>Int Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INT_VALUE_STYLE__INT_VALUE = eINSTANCE.getIntValueStyle_IntValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.IntListValueStyleImpl <em>Int List Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.IntListValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getIntListValueStyle()
		 * @generated
		 */
		EClass INT_LIST_VALUE_STYLE = eINSTANCE.getIntListValueStyle();

		/**
		 * The meta object literal for the '<em><b>Int List Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INT_LIST_VALUE_STYLE__INT_LIST_VALUE = eINSTANCE.getIntListValueStyle_IntListValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.BooleanValueStyleImpl <em>Boolean Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.BooleanValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBooleanValueStyle()
		 * @generated
		 */
		EClass BOOLEAN_VALUE_STYLE = eINSTANCE.getBooleanValueStyle();

		/**
		 * The meta object literal for the '<em><b>Boolean Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_VALUE_STYLE__BOOLEAN_VALUE = eINSTANCE.getBooleanValueStyle_BooleanValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.DoubleValueStyleImpl <em>Double Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.DoubleValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDoubleValueStyle()
		 * @generated
		 */
		EClass DOUBLE_VALUE_STYLE = eINSTANCE.getDoubleValueStyle();

		/**
		 * The meta object literal for the '<em><b>Double Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_VALUE_STYLE__DOUBLE_VALUE = eINSTANCE.getDoubleValueStyle_DoubleValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.DoubleListValueStyleImpl <em>Double List Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.DoubleListValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDoubleListValueStyle()
		 * @generated
		 */
		EClass DOUBLE_LIST_VALUE_STYLE = eINSTANCE.getDoubleListValueStyle();

		/**
		 * The meta object literal for the '<em><b>Double List Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_LIST_VALUE_STYLE__DOUBLE_LIST_VALUE = eINSTANCE.getDoubleListValueStyle_DoubleListValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.StringValueStyleImpl <em>String Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.StringValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStringValueStyle()
		 * @generated
		 */
		EClass STRING_VALUE_STYLE = eINSTANCE.getStringValueStyle();

		/**
		 * The meta object literal for the '<em><b>String Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_VALUE_STYLE__STRING_VALUE = eINSTANCE.getStringValueStyle_StringValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.StringListValueStyleImpl <em>String List Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.StringListValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStringListValueStyle()
		 * @generated
		 */
		EClass STRING_LIST_VALUE_STYLE = eINSTANCE.getStringListValueStyle();

		/**
		 * The meta object literal for the '<em><b>String List Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_LIST_VALUE_STYLE__STRING_LIST_VALUE = eINSTANCE.getStringListValueStyle_StringListValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.EObjectValueStyleImpl <em>EObject Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.EObjectValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getEObjectValueStyle()
		 * @generated
		 */
		EClass EOBJECT_VALUE_STYLE = eINSTANCE.getEObjectValueStyle();

		/**
		 * The meta object literal for the '<em><b>EObject Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EOBJECT_VALUE_STYLE__EOBJECT_VALUE = eINSTANCE.getEObjectValueStyle_EObjectValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.EObjectListValueStyleImpl <em>EObject List Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.EObjectListValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getEObjectListValueStyle()
		 * @generated
		 */
		EClass EOBJECT_LIST_VALUE_STYLE = eINSTANCE.getEObjectListValueStyle();

		/**
		 * The meta object literal for the '<em><b>EObject List Value</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EOBJECT_LIST_VALUE_STYLE__EOBJECT_LIST_VALUE = eINSTANCE.getEObjectListValueStyle_EObjectListValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ByteArrayValueStyleImpl <em>Byte Array Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ByteArrayValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getByteArrayValueStyle()
		 * @generated
		 */
		EClass BYTE_ARRAY_VALUE_STYLE = eINSTANCE.getByteArrayValueStyle();

		/**
		 * The meta object literal for the '<em><b>Byte Array Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BYTE_ARRAY_VALUE_STYLE__BYTE_ARRAY_VALUE = eINSTANCE.getByteArrayValueStyle_ByteArrayValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.BooleanListValueStyleImpl <em>Boolean List Value Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.BooleanListValueStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBooleanListValueStyle()
		 * @generated
		 */
		EClass BOOLEAN_LIST_VALUE_STYLE = eINSTANCE.getBooleanListValueStyle();

		/**
		 * The meta object literal for the '<em><b>Boolean List Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_LIST_VALUE_STYLE__BOOLEAN_LIST_VALUE = eINSTANCE.getBooleanListValueStyle_BooleanListValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.HintedDiagramLinkStyleImpl <em>Hinted Diagram Link Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.HintedDiagramLinkStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getHintedDiagramLinkStyle()
		 * @generated
		 */
		EClass HINTED_DIAGRAM_LINK_STYLE = eINSTANCE.getHintedDiagramLinkStyle();

		/**
		 * The meta object literal for the '<em><b>Hint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HINTED_DIAGRAM_LINK_STYLE__HINT = eINSTANCE.getHintedDiagramLinkStyle_Hint();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.DiagramLinkStyleImpl <em>Diagram Link Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.DiagramLinkStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDiagramLinkStyle()
		 * @generated
		 */
		EClass DIAGRAM_LINK_STYLE = eINSTANCE.getDiagramLinkStyle();

		/**
		 * The meta object literal for the '<em><b>Diagram Link</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM_LINK_STYLE__DIAGRAM_LINK = eINSTANCE.getDiagramLinkStyle_DiagramLink();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.MultiDiagramLinkStyleImpl <em>Multi Diagram Link Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.MultiDiagramLinkStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getMultiDiagramLinkStyle()
		 * @generated
		 */
		EClass MULTI_DIAGRAM_LINK_STYLE = eINSTANCE.getMultiDiagramLinkStyle();

		/**
		 * The meta object literal for the '<em><b>Diagram Links</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MULTI_DIAGRAM_LINK_STYLE__DIAGRAM_LINKS = eINSTANCE.getMultiDiagramLinkStyle_DiagramLinks();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.TextStyleImpl <em>Text Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.TextStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getTextStyle()
		 * @generated
		 */
		EClass TEXT_STYLE = eINSTANCE.getTextStyle();

		/**
		 * The meta object literal for the '<em><b>Text Alignment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_STYLE__TEXT_ALIGNMENT = eINSTANCE.getTextStyle_TextAlignment();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.LineTypeStyleImpl <em>Line Type Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.LineTypeStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getLineTypeStyle()
		 * @generated
		 */
		EClass LINE_TYPE_STYLE = eINSTANCE.getLineTypeStyle();

		/**
		 * The meta object literal for the '<em><b>Line Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE_TYPE_STYLE__LINE_TYPE = eINSTANCE.getLineTypeStyle_LineType();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ArrowStyleImpl <em>Arrow Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ArrowStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getArrowStyle()
		 * @generated
		 */
		EClass ARROW_STYLE = eINSTANCE.getArrowStyle();

		/**
		 * The meta object literal for the '<em><b>Arrow Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARROW_STYLE__ARROW_SOURCE = eINSTANCE.getArrowStyle_ArrowSource();

		/**
		 * The meta object literal for the '<em><b>Arrow Target</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARROW_STYLE__ARROW_TARGET = eINSTANCE.getArrowStyle_ArrowTarget();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl <em>Shape</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ShapeImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getShape()
		 * @generated
		 */
		EClass SHAPE = eINSTANCE.getShape();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.CompartmentImpl <em>Compartment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.CompartmentImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getCompartment()
		 * @generated
		 */
		EClass COMPARTMENT = eINSTANCE.getCompartment();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ListCompartmentImpl <em>List Compartment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ListCompartmentImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getListCompartment()
		 * @generated
		 */
		EClass LIST_COMPARTMENT = eINSTANCE.getListCompartment();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.ConnectorImpl <em>Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.ConnectorImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getConnector()
		 * @generated
		 */
		EClass CONNECTOR = eINSTANCE.getConnector();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.StandardDiagramImpl <em>Standard Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.StandardDiagramImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getStandardDiagram()
		 * @generated
		 */
		EClass STANDARD_DIAGRAM = eINSTANCE.getStandardDiagram();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.DecorationNodeImpl <em>Decoration Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.DecorationNodeImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getDecorationNode()
		 * @generated
		 */
		EClass DECORATION_NODE = eINSTANCE.getDecorationNode();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.BasicDecorationNodeImpl <em>Basic Decoration Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.BasicDecorationNodeImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBasicDecorationNode()
		 * @generated
		 */
		EClass BASIC_DECORATION_NODE = eINSTANCE.getBasicDecorationNode();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.BasicCompartmentImpl <em>Basic Compartment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.BasicCompartmentImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBasicCompartment()
		 * @generated
		 */
		EClass BASIC_COMPARTMENT = eINSTANCE.getBasicCompartment();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.BasicSemanticCompartmentImpl <em>Basic Semantic Compartment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.BasicSemanticCompartmentImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getBasicSemanticCompartment()
		 * @generated
		 */
		EClass BASIC_SEMANTIC_COMPARTMENT = eINSTANCE.getBasicSemanticCompartment();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.SemanticListCompartmentImpl <em>Semantic List Compartment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.SemanticListCompartmentImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSemanticListCompartment()
		 * @generated
		 */
		EClass SEMANTIC_LIST_COMPARTMENT = eINSTANCE.getSemanticListCompartment();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.impl.RoundedCornersStyleImpl <em>Rounded Corners Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.impl.RoundedCornersStyleImpl
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRoundedCornersStyle()
		 * @generated
		 */
		EClass ROUNDED_CORNERS_STYLE = eINSTANCE.getRoundedCornersStyle();

		/**
		 * The meta object literal for the '<em><b>Rounded Bendpoints Radius</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUNDED_CORNERS_STYLE__ROUNDED_BENDPOINTS_RADIUS = eINSTANCE.getRoundedCornersStyle_RoundedBendpointsRadius();
		
		
		/**
		 * @generated not For backwards compatibility 
		 */
		EAttribute ROUTING_STYLE__ROUNDED_BENDPOINTS_RADIUS = ROUNDED_CORNERS_STYLE__ROUNDED_BENDPOINTS_RADIUS;

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.Sorting <em>Sorting</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.Sorting
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSorting()
		 * @generated
		 */
		EEnum SORTING = eINSTANCE.getSorting();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.Filtering <em>Filtering</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.Filtering
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getFiltering()
		 * @generated
		 */
		EEnum FILTERING = eINSTANCE.getFiltering();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.Routing <em>Routing</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.Routing
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRouting()
		 * @generated
		 */
		EEnum ROUTING = eINSTANCE.getRouting();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.Smoothness <em>Smoothness</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.Smoothness
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSmoothness()
		 * @generated
		 */
		EEnum SMOOTHNESS = eINSTANCE.getSmoothness();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.JumpLinkStatus <em>Jump Link Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.JumpLinkStatus
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getJumpLinkStatus()
		 * @generated
		 */
		EEnum JUMP_LINK_STATUS = eINSTANCE.getJumpLinkStatus();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.JumpLinkType <em>Jump Link Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.JumpLinkType
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getJumpLinkType()
		 * @generated
		 */
		EEnum JUMP_LINK_TYPE = eINSTANCE.getJumpLinkType();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.Alignment <em>Alignment</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.Alignment
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getAlignment()
		 * @generated
		 */
		EEnum ALIGNMENT = eINSTANCE.getAlignment();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.SortingDirection <em>Sorting Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.SortingDirection
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSortingDirection()
		 * @generated
		 */
		EEnum SORTING_DIRECTION = eINSTANCE.getSortingDirection();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.MeasurementUnit <em>Measurement Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.MeasurementUnit
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getMeasurementUnit()
		 * @generated
		 */
		EEnum MEASUREMENT_UNIT = eINSTANCE.getMeasurementUnit();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.TextAlignment <em>Text Alignment</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.TextAlignment
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getTextAlignment()
		 * @generated
		 */
		EEnum TEXT_ALIGNMENT = eINSTANCE.getTextAlignment();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.LineType <em>Line Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.LineType
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getLineType()
		 * @generated
		 */
		EEnum LINE_TYPE = eINSTANCE.getLineType();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.ArrowType <em>Arrow Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.ArrowType
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getArrowType()
		 * @generated
		 */
		EEnum ARROW_TYPE = eINSTANCE.getArrowType();

		/**
		 * The meta object literal for the '{@link org.eclipse.gmf.runtime.notation.GradientStyle <em>Gradient Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.GradientStyle
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getGradientStyle()
		 * @generated
		 */
		EEnum GRADIENT_STYLE = eINSTANCE.getGradientStyle();

		/**
		 * The meta object literal for the '<em>Relative Bendpoint List</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.List
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getRelativeBendpointList()
		 * @generated
		 */
		EDataType RELATIVE_BENDPOINT_LIST = eINSTANCE.getRelativeBendpointList();

		/**
		 * The meta object literal for the '<em>Filter Key List</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.List
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getFilterKeyList()
		 * @generated
		 */
		EDataType FILTER_KEY_LIST = eINSTANCE.getFilterKeyList();

		/**
		 * The meta object literal for the '<em>Sort Key Map</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Map
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getSortKeyMap()
		 * @generated
		 */
		EDataType SORT_KEY_MAP = eINSTANCE.getSortKeyMap();

		/**
		 * The meta object literal for the '<em>Gradient Data</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gmf.runtime.notation.datatype.GradientData
		 * @see org.eclipse.gmf.runtime.notation.impl.NotationPackageImpl#getGradientData()
		 * @generated
		 */
		EDataType GRADIENT_DATA = eINSTANCE.getGradientData();

	}

} //NotationPackage
