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

import org.eclipse.gmf.runtime.emf.core.util.OperationUtil;


/**
 * Thread for a simple read operation
 * 
 * @author mgoyal
 */
class ReadThread extends SimpleOperationThread {
	/**
	 * Constructor
	 * @param waitObject
	 * @param notifyObject
	 */
	public ReadThread(Object waitObject, Object notifyObject) {
		super(waitObject, notifyObject);
	}
	
	/**
	 * Default constructor 
	 */
	public ReadThread() {
		this(null, null);
	}
	
	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
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

			OperationUtil.runAsRead(new Runnable() {
				/**
				 * @see java.lang.Runnable#run()
				 */
				public void run() {
					startTime = System.currentTimeMillis();
					try {
						sleep(Constants.SLEEP_TIME);
					} catch (InterruptedException e) {
						// ignore this.
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