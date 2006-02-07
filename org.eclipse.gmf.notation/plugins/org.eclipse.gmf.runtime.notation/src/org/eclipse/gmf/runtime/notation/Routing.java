/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.notation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Routing</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.gmf.runtime.notation.NotationPackage#getRouting()
 * @model
 * @generated
 */
/*
 * @canBeSeenBy %partners
 */
public final class Routing extends AbstractEnumerator {
    /**
     * The '<em><b>Manual</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MANUAL_LITERAL
     * @model name="Manual"
     * @generated
     * @ordered
     */
	public static final int MANUAL = 0;

    /**
     * The '<em><b>Rectilinear</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #RECTILINEAR_LITERAL
     * @model name="Rectilinear"
     * @generated
     * @ordered
     */
	public static final int RECTILINEAR = 1;

    /**
     * The '<em><b>Tree</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #TREE_LITERAL
     * @model name="Tree"
     * @generated
     * @ordered
     */
	public static final int TREE = 2;

    /**
     * The '<em><b>Manual</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MANUAL
     * @generated
     * @ordered
     */
	public static final Routing MANUAL_LITERAL = new Routing(MANUAL, "Manual", "Manual"); //$NON-NLS-2$//$NON-NLS-1$

    /**
     * The '<em><b>Rectilinear</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #RECTILINEAR
     * @generated
     * @ordered
     */
	public static final Routing RECTILINEAR_LITERAL = new Routing(RECTILINEAR, "Rectilinear", "Rectilinear"); //$NON-NLS-2$//$NON-NLS-1$

    /**
     * The '<em><b>Tree</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #TREE
     * @generated
     * @ordered
     */
	public static final Routing TREE_LITERAL = new Routing(TREE, "Tree", "Tree"); //$NON-NLS-2$//$NON-NLS-1$

    /**
     * An array of all the '<em><b>Routing</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final Routing[] VALUES_ARRAY =
        new Routing[] {
            MANUAL_LITERAL,
            RECTILINEAR_LITERAL,
            TREE_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Routing</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Routing</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static Routing get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Routing result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Routing</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Routing getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Routing result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Routing</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static Routing get(int value) {
        switch (value) {
            case MANUAL: return MANUAL_LITERAL;
            case RECTILINEAR: return RECTILINEAR_LITERAL;
            case TREE: return TREE_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private Routing(int value, String name, String literal) {
        super(value, name, literal);
    }

} //Routing
