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

package org.eclipse.gmf.runtime.emf.core.edit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * Enumeration that describes the state of an EObject.
 * 
 * @author rafikj
 * 
 * @deprecated Object states map trivially to Ecore (see individual comments
 *      on the enumeration values).
 */
public final class MObjectState
	extends AbstractEnumerator {

	private static int nextOrdinal = 0;

	/**
	 * The object belongs to a resource.
	 * 
	 * @deprecated Simply check whether your EObject's
	 *    {@link org.eclipse.emf.ecore.EObject#eResource()} is different
	 *    from <code>null</code>
	 */
	public static final MObjectState OPEN = new MObjectState("Open"); //$NON-NLS-1$

	/**
	 * The object deoes not belong to a resource and is not a proxy.
	 * 
	 * @deprecated Simply check whether your EObject's
	 *    {@link org.eclipse.emf.ecore.EObject#eResource()} is <code>null</code>
	 *    but the object is not a {@link org.eclipse.emf.ecore.EObject#eIsProxy() proxy}
	 */
	public static final MObjectState DETACHED = new MObjectState("Detached"); //$NON-NLS-1$

	/**
	 * The object is a proxy.
	 * 
	 * @deprecated Simply check whether your EObject is a
	 *    {@link org.eclipse.emf.ecore.EObject#eIsProxy() proxy}
	 */
	public static final MObjectState CLOSED = new MObjectState("Closed"); //$NON-NLS-1$

	private static final MObjectState[] VALUES = {OPEN, DETACHED, CLOSED};

	private MObjectState(String name, int ordinal) {
		super(ordinal, name);
	}

	private MObjectState(String name) {
		this(name, nextOrdinal++);
	}

	/**
	 * Gets the list of all possible values.
	 */
	public static List getValues() {
		return Collections.unmodifiableList(Arrays.asList(VALUES));
	}
}