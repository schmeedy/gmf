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
package org.eclipse.gmf.runtime.gef;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;

public class CompartmentsContentPane extends Figure {
	private final Map myId2Figure;

	public CompartmentsContentPane() {
		myId2Figure = new HashMap();
	}

	/**
	 * Adds compartmentFigure as own child (with no constraints) unless it was already added (perhaps,
	 * using some constrains).
	 * @param id
	 * @param compartmentFigure
	 */
	public void register(String id, IFigure compartmentFigure) {
		assert id != null;
		assert compartmentFigure != null;
		if (!getChildren().contains(compartmentFigure)) {
			add(compartmentFigure);
		}
		myId2Figure.put(id, compartmentFigure);
	}

	public void addChildVisual(String id, IFigure childFigure, int index) {
		IFigure compartmentFigure = (IFigure) myId2Figure.get(id);
		if (compartmentFigure != null) {
			// FIXME index is within parent figure, not relative to compartment, so needs adjusting
			// XXX just don't use it for now
			compartmentFigure.add(childFigure, -1);
		} else {
			System.err.println("no compartment with id " + id);
		}
	}

	public void removeChildVisual(String id, IFigure childFigure) {
		IFigure compartmentFigure = (IFigure) myId2Figure.get(id);
		if (compartmentFigure != null) {
			compartmentFigure.remove(childFigure);
		} else {
			System.err.println("no compartment with id " + id);
		}
	}
}
