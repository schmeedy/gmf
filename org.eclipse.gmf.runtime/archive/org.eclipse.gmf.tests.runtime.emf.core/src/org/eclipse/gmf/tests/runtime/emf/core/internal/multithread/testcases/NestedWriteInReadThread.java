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


			OperationUtil.runAsRead(new MRunnable() {

				/**
				 * @see org.eclipse.gmf.runtime.emf.core.edit.MRunnable#run()
				 */
				public Object run() {
					startTime = System.currentTimeMillis();
					final boolean bReading = true;
					try {
						OperationUtil.runInUndoInterval(new Runnable() {

							/**
							 * @see java.lang.Runnable#run()
							 */
							public void run() {
								try {
									OperationUtil.runAsWrite(new MRunnable() {

										/**
										 * @see java.lang.Runnable#run()
										 */
										public Object run() {
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
											return null;
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
					return null;
				}
			});
		} catch (Exception e) {
			isFailed = true;
		}
	}
}