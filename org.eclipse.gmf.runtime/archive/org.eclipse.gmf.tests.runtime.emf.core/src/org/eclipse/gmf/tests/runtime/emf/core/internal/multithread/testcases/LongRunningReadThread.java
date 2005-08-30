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
 * Thread representing a long running read. This thread yields for other reads.
 * @author mgoyal
 *
 */
class LongRunningReadThread extends ReadThread {
	/**
	 * Constructor
	 * @param waitObject
	 * @param notifyObject
	 */
	public LongRunningReadThread(Object waitObject, Object notifyObject) {
		super(waitObject, notifyObject);
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			OperationUtil.runAsRead(new Runnable() {
				/**
				 * @see java.lang.Runnable#run()
				 */
				public void run() {
					if(notifyObject != null) {
						synchronized(notifyObject) {
							notifyObject.notify();
						}
					}

					if(waitObject != null) {
						synchronized(waitObject) {
							try {
								waitObject.wait();
							} catch(InterruptedException e) {
								// Nothing..
							}
						}
					}
					
					startTime = System.currentTimeMillis();
					for(int i = 0; i < 10; i++) {
						try {
							sleep(Constants.SLEEP_TIME);
						} catch (InterruptedException e) {
							// ignore this.
						}
						OperationUtil.yieldForReads();
					}
					isExecuted = true;
					endTime = System.currentTimeMillis();
				}
			});
		} catch(Exception e) {
			isFailed = true;
		}
	}
}