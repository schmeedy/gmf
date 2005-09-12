/******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.tests.runtime.emf.core.internal.multithread.testcases;

/**
 * Thread representing a simple model operation.
 * 
 * @author mgoyal
 */
class SimpleOperationThread
	extends Thread {

	// start time.
	protected long startTime = -1;

	// end time
	protected long endTime = -1;

	// flag to determing if execution succeeded.
	protected boolean isExecuted = false;

	// flag to determine if execution failed.
	protected boolean isFailed = false;

	// Notify Object
	Object notifyObject = null;

	// Wait Object.
	Object waitObject = null;

	/**
	 * Constructor
	 * 
	 * @param waitObject
	 *            Object to wait on
	 * @param notifyObject
	 *            Object to notify
	 */
	public SimpleOperationThread(Object waitObject, Object notifyObject) {
		this.notifyObject = notifyObject;
		this.waitObject = waitObject;
	}

	/**
	 * Returns the start time of this operation
	 * 
	 * @return startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * Returns the end time of this operation.
	 * 
	 * @return endTime
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * Returns true if the execution succeeded
	 * 
	 * @return isExecuted
	 */
	public boolean isExecuted() {
		return isExecuted;
	}

	/**
	 * Returns true if the execution failed.
	 * 
	 * @return isFailed
	 */
	public boolean isFailed() {
		return isFailed;
	}
}
