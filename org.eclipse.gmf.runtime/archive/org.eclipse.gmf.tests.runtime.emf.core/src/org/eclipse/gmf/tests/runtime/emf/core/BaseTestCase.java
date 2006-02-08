/******************************************************************************
 * Copyright (c) 2002, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.tests.runtime.emf.core;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MFilter;
import org.eclipse.gmf.runtime.emf.core.edit.MListener;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;

/**
 * @author rafikj
 *
 * Base test case.
 */
public abstract class BaseTestCase
	extends TestCase {

	protected MListener listener = null;

	protected MFilter filter = null;

	public BaseTestCase(String name) {
		super(name);
	}

	protected void setUp()
		throws Exception {

		listener = new MListener() {

			public void onEvent(List events) {
				assertFalse(events.isEmpty());
				processEvents(events);
			}
		};

		filter = new MFilter() {

			public boolean matches(Notification event) {
				return true;
			}
		};

		listener.setFilter(filter);

		listener.startListening();
	}

	protected void processEvents(List events) {
		// implement in derived classes.
	}

	protected void tearDown()
		throws Exception {
		listener.stopListening();
	}
	
	protected void openUndoInterval(String label, String description) {
		MEditingDomain.INSTANCE.openUndoInterval(label, description);
	}
	
	protected void startRead() {
		MEditingDomain.INSTANCE.startRead();
	}
	
	protected void completeRead() {
		MEditingDomain.INSTANCE.complete();
	}
	
	protected void startWrite() {
		MEditingDomain.INSTANCE.startWrite();
	}
	
	protected void completeWrite() {
		try {
			completeWriteWithValidation();
		} catch (MSLActionAbandonedException e) {
			MEditingDomain.INSTANCE.closeUndoInterval();
			fail("Action abandoned: " + e.getStatus()); //$NON-NLS-1$
		}
	}
	
	protected void completeWriteWithValidation() throws MSLActionAbandonedException {
		MEditingDomain.INSTANCE.completeAndValidate();
	}
	
	protected MUndoInterval closeUndoInterval() {
		return MEditingDomain.INSTANCE.closeUndoInterval();
	}
}