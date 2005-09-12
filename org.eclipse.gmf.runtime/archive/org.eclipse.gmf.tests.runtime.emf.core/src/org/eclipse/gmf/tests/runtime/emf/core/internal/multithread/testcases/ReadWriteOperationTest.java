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
 * Testcase for testing read and write operation scheduling scenarios
 * 
 * @author mgoyal
 */
public class ReadWriteOperationTest
	extends TestCase {

	/**
	 *  Tests scheduling of complex Read write scenarios.
	 */
	public void testComplexSimultaneousReadsWrites() {
		Object notifier = new Object();
		NestedReadInWriteThread readInWriteThread1 = new NestedReadInWriteThread(
			null, notifier);
		NestedWriteInReadThread writeInReadThread1 = new NestedWriteInReadThread(
			null, notifier);
		NestedReadInWriteThread readInWriteThread2 = new NestedReadInWriteThread(
			null, notifier);
		NestedWriteInReadThread writeInReadThread2 = new NestedWriteInReadThread(
			null, notifier);

		synchronized (notifier) {
			try {
				readInWriteThread1.start();
				notifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}
		synchronized (notifier) {
			try {
				writeInReadThread1.start();
				notifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}
		synchronized (notifier) {
			try {
				readInWriteThread2.start();
				notifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}
		synchronized (notifier) {
			try {
				writeInReadThread2.start();
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
			if (!readInWriteThread1.isAlive() && !writeInReadThread1.isAlive()
				&& !readInWriteThread2.isAlive()
				&& !writeInReadThread2.isAlive())
				done = true;
		}

		assertFalse(readInWriteThread1.isFailed());
		assertFalse(writeInReadThread1.isFailed());
		assertFalse(readInWriteThread2.isFailed());
		assertFalse(writeInReadThread2.isFailed());
		assertTrue(readInWriteThread1.isExecuted());
		assertTrue(writeInReadThread1.isExecuted());
		assertTrue(readInWriteThread2.isExecuted());
		assertTrue(writeInReadThread2.isExecuted());
		assertFalse(readInWriteThread1.isInnerFailed());
		assertFalse(writeInReadThread1.isInnerFailed());
		assertFalse(readInWriteThread2.isInnerFailed());
		assertFalse(writeInReadThread2.isInnerFailed());
		assertTrue(readInWriteThread1.isInnerExecuted());
		assertTrue(writeInReadThread1.isInnerExecuted());
		assertTrue(readInWriteThread2.isInnerExecuted());
		assertTrue(writeInReadThread2.isInnerExecuted());
	}

	/**
	 * Tests scheduling of simultaneous read and write operation  
	 */
	public void testSimultaneousReadsWrites() {
		Object notifier = new Object();
		WriteThread writeThread1 = new WriteThread(null, notifier);
		WriteThread writeThread2 = new WriteThread(null, notifier);
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
				writeThread1.start();
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
			if (!writeThread1.isAlive() && !writeThread2.isAlive()
				&& !readThread1.isAlive() && !readThread2.isAlive())
				done = true;
		}

		assertFalse(readThread1.isFailed());
		assertFalse(readThread2.isFailed());
		assertFalse(writeThread1.isFailed());
		assertFalse(writeThread2.isFailed());
		assertTrue(readThread1.isExecuted());
		assertTrue(readThread2.isExecuted());
		assertTrue(writeThread1.isExecuted());
		assertTrue(writeThread2.isExecuted());
		assertTrue(Constants.occurredBefore(writeThread2, writeThread1)
			|| Constants.occurredAfter(writeThread2, writeThread1));
		assertTrue(Constants.occurredBefore(writeThread2, readThread2)
			|| Constants.occurredAfter(writeThread2, readThread2));
		assertTrue(Constants.occurredBefore(writeThread2, readThread1)
			|| Constants.occurredAfter(writeThread2, readThread1));
		assertTrue(Constants.occurredBefore(readThread2, readThread1)
			|| Constants.occurredAfter(readThread2, readThread1));
		assertTrue(Constants.occurredBefore(readThread2, writeThread1)
			|| Constants.occurredAfter(readThread2, writeThread1));
		assertTrue(Constants.occurredBefore(readThread1, writeThread1)
			|| Constants.occurredAfter(readThread1, writeThread1));
	}

	/**
	 *  Tests scheduling of Nested Write operation in Read operation
	 */
	public void testNestedWriteInRead() {
		NestedWriteInReadThread writeInReadThd = new NestedWriteInReadThread();
		writeInReadThd.start();

		boolean done = false;
		while (!done) {
			try {
				Thread.sleep(Constants.SLEEP_TIME);
			} catch (InterruptedException e) {
				// ignore this exception
			}
			if (!writeInReadThd.isAlive())
				done = true;
		}
		assertFalse(writeInReadThd.isInnerFailed());
		assertFalse(writeInReadThd.isFailed());
		assertTrue(writeInReadThd.isInnerExecuted());
		assertTrue(writeInReadThd.isExecuted());
		
		// CWD: Cannot assert that the elapsed time was >= Constants.SLEEP_TIME
		//    because the J9 VM always sleeps to short when SLEEP_TIME < 500
		assertTrue((writeInReadThd.getEndTime() - writeInReadThd
			.getInnerEndTime()) > 0);
	}

	/**
	 *  Tests Scheduling of Nested Read in Write Operation
	 */
	public void testNestedReadInWrite() {
		NestedReadInWriteThread readInWriteThd = new NestedReadInWriteThread();
		readInWriteThd.start();

		boolean done = false;
		while (!done) {
			try {
				Thread.sleep(Constants.SLEEP_TIME);
			} catch (InterruptedException e) {
				// ignore this exception
			}
			if (!readInWriteThd.isAlive())
				done = true;
		}

		assertFalse(readInWriteThd.isInnerFailed());
		assertFalse(readInWriteThd.isFailed());
		assertTrue(readInWriteThd.isInnerExecuted());
		assertTrue(readInWriteThd.isExecuted());
		
		// CWD: Cannot assert that the elapsed time was >= Constants.SLEEP_TIME
		//    because the J9 VM always sleeps to short when SLEEP_TIME < 500
		assertTrue((readInWriteThd.getEndTime() - readInWriteThd
			.getInnerEndTime()) > 0);
	}

	/**
	 *  Tests scheduling of long running read with write operation.
	 */
	public void testLongRunningReadWithWrites() {
		Object notifier = new Object();
		LongRunningReadThread longReadThread = new LongRunningReadThread(null,
			notifier);
		ReadThread readThd1 = new ReadThread(null, notifier);
		ReadThread readThd2 = new ReadThread(null, notifier);
		WriteThread writeThd1 = new WriteThread(null, notifier);
		ReadThread readThd3 = new ReadThread(null, notifier);

		synchronized (notifier) {
			try {
				longReadThread.start();
				notifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}

		synchronized (notifier) {
			try {
				readThd1.start();
				notifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}

		synchronized (notifier) {
			try {
				readThd2.start();
				notifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}

		synchronized (notifier) {
			try {
				writeThd1.start();
				notifier.wait();
			} catch (InterruptedException e) {
				// nothing
			}
		}

		synchronized (notifier) {
			try {
				readThd3.start();
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
			if (!longReadThread.isAlive() && !readThd1.isAlive()
				&& !readThd2.isAlive() && !readThd3.isAlive()
				&& !writeThd1.isAlive())
				done = true;
		}

		assertFalse(longReadThread.isFailed());
		assertFalse(readThd1.isFailed());
		assertFalse(readThd2.isFailed());
		assertFalse(readThd3.isFailed());
		assertFalse(writeThd1.isFailed());
		assertTrue(longReadThread.isExecuted());
		assertTrue(readThd1.isExecuted());
		assertTrue(readThd2.isExecuted());
		assertTrue(readThd3.isExecuted());
		assertTrue(writeThd1.isExecuted());
		// The start time of long running thread is before the start time of
		// readThd1 and readThd2
		// The end time of long running thread is before the start time of
		// writeThd1 and readThd3
		// The end time of readThd1 and readThd2 is before the end time of long
		// running thread.
		// Verify readThd1 and readThd2 didn't execute simultaneously
		// Verify writeThd1 and readThd3 didn't execute simulatenously
		//		System.out.println((readThd1.getStartTime() -
		// longReadThread.getStartTime()));
		//		System.out.println(readThd2.getStartTime() -
		// longReadThread.getStartTime());
		//		System.out.println(longReadThread.getEndTime() -
		// readThd1.getEndTime());
		//		System.out.println(longReadThread.getEndTime() -
		// readThd2.getEndTime());
		//		System.out.println(readThd1.getStartTime() -
		// readThd2.getStartTime());
		//		System.out.println(writeThd1.getStartTime() -
		// longReadThread.getEndTime());
		//		System.out.println(readThd3.getStartTime() -
		// longReadThread.getEndTime());
		//		System.out.println(readThd3.getStartTime() -
		// writeThd1.getStartTime());
		assertTrue(!Constants.occurIntersect(longReadThread, readThd1));
		assertTrue(!Constants.occurIntersect(longReadThread, readThd2));
		assertTrue(Constants.occurredAfter(readThd1, readThd2)
			|| Constants.occurredBefore(readThd1, readThd2));
		assertTrue((Constants.occurredBefore(longReadThread, writeThd1) || Constants
			.occurredAfter(longReadThread, writeThd1))
			&& !Constants.occurredDuring(longReadThread, writeThd1));
		assertTrue(!Constants.occurIntersect(longReadThread, readThd3));
	}
}
