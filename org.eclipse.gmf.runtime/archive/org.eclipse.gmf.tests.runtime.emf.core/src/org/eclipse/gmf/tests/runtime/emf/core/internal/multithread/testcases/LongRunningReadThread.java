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

import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
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
			OperationUtil.runAsRead(new MRunnable() {
				/**
				 * @see org.eclipse.gmf.runtime.emf.core.edit.MRunnable#run()
				 */
				public Object run() {
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
					return null;
				}
			});
		} catch(Exception e) {
			isFailed = true;
		}
	}
}