/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
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
