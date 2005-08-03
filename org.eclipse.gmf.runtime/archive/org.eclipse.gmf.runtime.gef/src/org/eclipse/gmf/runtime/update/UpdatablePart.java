/*
 * Copyright (c) 2005 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.runtime.update;

/**
 * Generated edit parts implementing this interface are subject 
 * to fine-grained notification from diagram change listener.
 * 
 * @author artem
 */
public interface UpdatablePart {

	void boundsChanged();
	void visualPropertiesChanged();
	void structuralPropertiesChanged();
	void incomingLinksChanged();
	void outgoingLinksChanged();
}
