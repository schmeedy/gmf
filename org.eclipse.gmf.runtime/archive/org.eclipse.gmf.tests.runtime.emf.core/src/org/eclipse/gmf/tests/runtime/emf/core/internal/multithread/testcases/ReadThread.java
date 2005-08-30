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