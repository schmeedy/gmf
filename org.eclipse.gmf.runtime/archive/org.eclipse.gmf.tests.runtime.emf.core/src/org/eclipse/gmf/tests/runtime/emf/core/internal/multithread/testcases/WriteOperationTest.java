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

import junit.framework.TestCase;

/**
 * Testcase for testing scheduling of Write Operation scenarios
 * @author mgoyal
 *  
 */
public class WriteOperationTest
	extends TestCase {

	/**
	 *  Tests scheduling of simple write operation.
	 */
	public void testWriteOperation() {
		WriteThread writeThread1 = new WriteThread();

		writeThread1.start();
		boolean done = false;
		while (!done) {
			try {
				Thread.sleep(Constants.SLEEP_TIME);
			} catch (InterruptedException e) {
				// ignore this exception
			}
			if (!writeThread1.isAlive())
				done = true;
		}

		assertFalse(writeThread1.isFailed());
		assertTrue(writeThread1.isExecuted());
	}

	/**
	 *  Tests scheduling of two simultaneous write operations.
	 */
	public void testSimultaneousWrites() {
		Object notifier = new Object();
		WriteThread writeThread1 = new WriteThread(null, notifier);
		WriteThread writeThread2 = new WriteThread(null, notifier);

		synchronized (notifier) {
			try {
				writeThread1.start();
				notifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}
		synchronized (notifier) {
			try {
				writeThread2.start();
				notifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}

		boolean done = false;
		while (!done) {
			try {
				Thread.sleep(Constants.SLEEP_TIME);
			} catch (InterruptedException e) {
				// ignore this exception
			}
			if (!writeThread1.isAlive() && !writeThread2.isAlive())
				done = true;
		}

		assertFalse(writeThread1.isFailed());
		assertFalse(writeThread2.isFailed());
		assertTrue(writeThread1.isExecuted());
		assertTrue(writeThread2.isExecuted());
		assertTrue(Constants.occurredAfter(writeThread1, writeThread2)
			|| Constants.occurredBefore(writeThread1, writeThread2));
	}

	/**
	 * Tests scheduling of Nested Write Operations.
	 */
	public void testNestedWrites() {
		NestedWriteThread writeThread1 = new NestedWriteThread();
		writeThread1.start();
		boolean done = false;
		while (!done) {
			try {
				Thread.sleep(Constants.SLEEP_TIME);
			} catch (InterruptedException e) {
				// ignore this exception
			}
			if (!writeThread1.isAlive())
				done = true;
		}

		assertFalse(writeThread1.isInnerFailed());
		assertFalse(writeThread1.isFailed());
		assertTrue(writeThread1.isInnerExecuted());
		assertTrue(writeThread1.isExecuted());
		assertTrue(Constants.occurredDuring(writeThread1.getStartTime(),
			writeThread1.getEndTime(), writeThread1.getInnerStartTime(),
			writeThread1.getInnerEndTime()));
	}

}
