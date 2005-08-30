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

import org.eclipse.gmf.runtime.emf.core.util.OperationUtil;

/**
 * Thread representing write operation nested in a read operation.
 * 
 * @author mgoyal
 * 
 */
class NestedWriteInReadThread
	extends NestedOperationThread {

	/**
	 * Constructor
	 * 
	 * @param waitObject
	 * @param notifyObject
	 */
	public NestedWriteInReadThread(Object waitObject, Object notifyObject) {
		super(waitObject, notifyObject);
	}

	/**
	 * Default Constructor
	 */
	public NestedWriteInReadThread() {
		this(null, null);
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			if (notifyObject != null) {
				synchronized (notifyObject) {
					notifyObject.notify();
				}
			}
			if (waitObject != null) {
				synchronized (waitObject) {
					try {
						waitObject.wait();
					} catch (InterruptedException e) {
						// Nothing..
					}
				}
			}


			OperationUtil.runAsRead(new Runnable() {

				/**
				 * @see java.lang.Runnable#run()
				 */
				public void run() {
					startTime = System.currentTimeMillis();
					final boolean bReading = true;
					try {
						OperationUtil.runInUndoInterval(new Runnable() {

							/**
							 * @see java.lang.Runnable#run()
							 */
							public void run() {
								try {
									OperationUtil.runAsWrite(new Runnable() {

										/**
										 * @see java.lang.Runnable#run()
										 */
										public void run() {
											innerStartTime = System
												.currentTimeMillis();
											try {
												sleep(Constants.SLEEP_TIME);
											} catch (InterruptedException e) {
												// ignore this.
											}
											if (bReading && !isExecuted)
												isInnerExecuted = true;
											innerEndTime = System
												.currentTimeMillis();
										}
									});
								} catch (Exception e1) {
									isInnerFailed = true;
								}
							}
						});
					} catch (Exception e) {
						isInnerFailed = true;
					}
					try {
						sleep(Constants.SLEEP_TIME);
					} catch (InterruptedException e) {
						// ignore this.
					}
					isExecuted = true;
					endTime = System.currentTimeMillis();
				}
			});
		} catch (Exception e) {
			isFailed = true;
		}
	}
}