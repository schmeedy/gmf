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

import junit.framework.TestCase;

/**
 * Testcase for testing scheduling of Read operation scenarios
 * @author mgoyal
 */
public class ReadOperationTest
	extends TestCase {

	/**
	 *  Tests scheduling of simple read operation
	 */
	public void testReadOperation() {
		ReadThread readThread1 = new ReadThread();

		readThread1.start();

		boolean done = false;
		while (!done) {
			try {
				Thread.sleep(Constants.SLEEP_TIME);
			} catch (InterruptedException e) {
				// ignore this exception
			}
			if (!readThread1.isAlive())
				done = true;
		}

		assertFalse(readThread1.isFailed());
		assertTrue(readThread1.isExecuted());
	}

	/**
	 *  Tests scheduling of simultaneous read operations.
	 */
	public void testSimultaneousRead() {
		Object notifier = new Object();
		ReadThread readThread1 = new ReadThread(null, notifier);
		ReadThread readThread2 = new ReadThread(null, notifier);

		synchronized (notifier) {
			try {
				readThread1.start();
				notifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}
		synchronized (notifier) {
			try {
				readThread2.start();
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
			if (!readThread1.isAlive() && !readThread2.isAlive())
				done = true;
		}

		assertFalse(readThread1.isFailed());
		assertFalse(readThread2.isFailed());
		assertTrue(readThread1.isExecuted());
		assertTrue(readThread2.isExecuted());
		assertTrue(Constants.occurredBefore(readThread2, readThread1)
			|| Constants.occurredAfter(readThread2, readThread1));
	}

	/**
	 *  Tests scheduling of nested read operations.
	 */
	public void testNestedReads() {
		NestedReadThread readThread1 = new NestedReadThread();
		readThread1.start();
		boolean done = false;
		while (!done) {
			try {
				Thread.sleep(Constants.SLEEP_TIME);
			} catch (InterruptedException e) {
				// ignore this exception
			}
			if (!readThread1.isAlive())
				done = true;
		}

		assertFalse(readThread1.isInnerFailed());
		assertFalse(readThread1.isFailed());
		assertTrue(readThread1.isInnerExecuted());
		assertTrue(readThread1.isExecuted());
		assertTrue(Constants.occurredDuring(readThread1.getStartTime(),
			readThread1.getEndTime(), readThread1.getInnerStartTime(),
			readThread1.getInnerEndTime()));
	}

	/**
	 *  Tests scheduling of yielding read with other simultaneous read operations.
	 */
	public void testLongRunningYieldingRead() {
		Object runNotifier = new Object();
		LongRunningReadThread longReadThread = new LongRunningReadThread(null,
			runNotifier);
		ReadThread readThd1 = new ReadThread(null, runNotifier);
		ReadThread readThd2 = new ReadThread(null, runNotifier);
		ReadThread readThd3 = new ReadThread(null, runNotifier);

		synchronized (runNotifier) {
			try {
				longReadThread.start();
				runNotifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}
		synchronized (runNotifier) {
			try {
				readThd1.start();
				runNotifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}
		synchronized (runNotifier) {
			try {
				readThd2.start();
				runNotifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}
		synchronized (runNotifier) {
			try {
				readThd3.start();
				runNotifier.wait();
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
			if (!longReadThread.isAlive() && !readThd1.isAlive()
				&& !readThd2.isAlive() && !readThd3.isAlive())
				done = true;
		}

		assertFalse(longReadThread.isFailed());
		assertFalse(readThd1.isFailed());
		assertFalse(readThd2.isFailed());
		assertFalse(readThd3.isFailed());
		assertTrue(longReadThread.isExecuted());
		assertTrue(readThd1.isExecuted());
		assertTrue(readThd2.isExecuted());
		assertTrue(readThd3.isExecuted());
		// Verify that the start time of long running thread is before the start
		// time of other threads
		// Verify that the end time of other threads is before the end time of
		// long running thread.
		// Also verify that other threads didn't run simulatenously.
		assertTrue(Constants.occurredDuring(longReadThread, readThd1));
		assertTrue(Constants.occurredDuring(longReadThread, readThd2));
		assertTrue(Constants.occurredDuring(longReadThread, readThd3));
		assertTrue(!Constants.occurIntersect(readThd1, readThd2));
		assertTrue(!Constants.occurIntersect(readThd1, readThd3));
		assertTrue(!Constants.occurIntersect(readThd2, readThd3));
	}
}
