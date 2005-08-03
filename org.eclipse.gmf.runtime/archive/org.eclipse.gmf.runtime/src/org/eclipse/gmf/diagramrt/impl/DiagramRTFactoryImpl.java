/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.diagramrt.impl;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.gmf.diagramrt.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DiagramRTFactoryImpl extends EFactoryImpl implements DiagramRTFactory {
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramRTFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DiagramRTPackage.DIAGRAM_NODE: return createDiagramNode();
			case DiagramRTPackage.DIAGRAM_LINK: return createDiagramLink();
			case DiagramRTPackage.DIAGRAM_CANVAS: return createDiagramCanvas();
			case DiagramRTPackage.CHILD_NODE: return createChildNode();
			case DiagramRTPackage.RIGID_CHILD_NODE: return createRigidChildNode();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case DiagramRTPackage.DIMENSION:
				return createDimensionFromString(eDataType, initialValue);
			case DiagramRTPackage.POINT:
				return createPointFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case DiagramRTPackage.DIMENSION:
				return convertDimensionToString(eDataType, instanceValue);
			case DiagramRTPackage.POINT:
				return convertPointToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramNode createDiagramNode() {
		DiagramNodeImpl diagramNode = new DiagramNodeImpl();
		return diagramNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramLink createDiagramLink() {
		DiagramLinkImpl diagramLink = new DiagramLinkImpl();
		return diagramLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramCanvas createDiagramCanvas() {
		DiagramCanvasImpl diagramCanvas = new DiagramCanvasImpl();
		return diagramCanvas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChildNode createChildNode() {
		ChildNodeImpl childNode = new ChildNodeImpl();
		return childNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RigidChildNode createRigidChildNode() {
		RigidChildNodeImpl rigidChildNode = new RigidChildNodeImpl();
		return rigidChildNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Dimension createDimensionFromString(EDataType eDataType, String initialValue) {
		int sep = initialValue.indexOf(',');
		if (sep == -1) {
			return new Dimension();
		}
		try {
			int w = Integer.parseInt(initialValue.substring(0, sep).trim());
			int h = Integer.parseInt(initialValue.substring(sep+1).trim());
			return new Dimension(w, h);
		} catch (NumberFormatException ex) {
			return new Dimension();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertDimensionToString(EDataType eDataType, Object instanceValue) {
		Dimension d = (Dimension) instanceValue;
		return new StringBuffer().append(d.width).append(',').append(d.height).toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Point createPointFromString(EDataType eDataType, String initialValue) {
		int sep = initialValue.indexOf(',');
		if (sep == -1) {
			return new Point();
		}
		try {
			int x = Integer.parseInt(initialValue.substring(0, sep).trim());
			int y = Integer.parseInt(initialValue.substring(sep+1).trim());
			return new Point(x, y);
		} catch (NumberFormatException ex) {
			return new Point();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertPointToString(EDataType eDataType, Object instanceValue) {
		Point p = (Point) instanceValue;
		return new StringBuffer().append(p.x).append(',').append(p.y).toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramRTPackage getDiagramRTPackage() {
		return (DiagramRTPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static DiagramRTPackage getPackage() {
		return DiagramRTPackage.eINSTANCE;
	}

} //DiagramRTFactoryImpl
