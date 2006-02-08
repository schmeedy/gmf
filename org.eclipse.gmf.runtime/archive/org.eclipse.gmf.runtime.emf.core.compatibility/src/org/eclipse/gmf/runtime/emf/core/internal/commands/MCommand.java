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


package org.eclipse.gmf.runtime.emf.core.internal.commands;


/**
 * This interface consolidates all of the getter methods for supported MSL EMF
 * commands. Where a method is defined on this interface that is not applicable
 * for a given implementation class, it will return null.
 * 
 * @author nbennett
 */
public interface MCommand {

	/**
	 * Type-safe enumeration class of all EMF Command Types currently supported
	 * by the MSL command generation infrastructure.
	 */

	public static class Type {

		private static int nextOridnal = 0;

		private int ordinal = nextOridnal;

		private Type() {
			super();
			nextOridnal++;
		}

		public boolean equals(Type other) {
			return this.ordinal == other.ordinal;
		}

		public int hashCode() {
			return ordinal;
		}
	}

	/**
	 * Type-safe enumerated value representing the <code>MSLAddCommand</code>
	 */
	public static final Type ADD = new Type();

	/**
	 * Type-safe enumerated value representing the
	 * <code>TraversableCompoundCommand</code>
	 */
	public static final Type COMPOUND = new Type();

	/**
	 * Type-safe enumerated value representing the <code>MSLMoveCommand</code>
	 */
	public static final Type MOVE = new Type();

	/**
	 * Type-safe enumerated value representing the <code>MSLRemoveCommand</code>
	 */
	public static final Type REMOVE = new Type();

	/**
	 * Type-safe enumerated value representing the <code>MSLSetCommand</code>
	 */
	public static final Type SET = new Type();
}