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
 * @author mgoyal
 *
 */
public class Constants {
	public static final int SLEEP_TIME = 100;
	
	/**
	 * Returns true if the <code>testedTask</code> started after <code>mainTask</code>.
	 * and finished before <code>mainTask</code>.
	 *   
	 * @param mainTask Main Task
	 * @param testedTask Tested Task
	 * @return true if the condition is met.
	 */
	public static final boolean occurredDuring(SimpleOperationThread mainTask, SimpleOperationThread testedTask) {
		return occurredDuring(mainTask.getStartTime(), mainTask.getEndTime(), testedTask.getStartTime(), testedTask.getEndTime());
	}
	
	/**
	 * Returns true if the <code>testedStartTime</code> is after <code>mainStartTime</code>.
	 * and <code>testedEndTime</code> is before <code>mainEndTime</code>.
	 * 
	 * @param mainStartTime
	 * @param mainEndTime
	 * @param testedStartTime
	 * @param testedEndTime
	 * @return true if the condition is met
	 */
	public static final boolean occurredDuring(long mainStartTime, long mainEndTime, long testedStartTime, long testedEndTime) {
		return (testedStartTime - mainStartTime > 0 && testedStartTime - mainEndTime < 0) &&
			(testedEndTime - mainStartTime > 0 && testedEndTime - mainEndTime < 0);
	}
	
	/**
	 * Returns true if the <code>testedTask</code> started and finished before the <code>mainTask</code>
	 * 
	 * @param mainTask
	 * @param testedTask
	 * @return true if the condition is met.
	 */
	public static final boolean occurredBefore(SimpleOperationThread mainTask, SimpleOperationThread testedTask) {
		return testedTask.getStartTime() - mainTask.getStartTime() < 0 && testedTask.getEndTime() - mainTask.getStartTime() <= 0;
	}

	/**
	 * Returns true if the <code>testedTask</code> started and finished after the <code>mainTask</code>.
	 * 
	 * @param mainTask
	 * @param testedTask
	 * @return true if the condition is met.
	 */
	public static final boolean occurredAfter(SimpleOperationThread mainTask, SimpleOperationThread testedTask) {
		return testedTask.getStartTime() - mainTask.getEndTime() >= 0 && testedTask.getEndTime() - mainTask.getEndTime() > 0;
	}
	
	/**
	 * Returns true if the <code>testedTask</code> started before the <code>mainTask</code> and finished during the <code>mainTask</code>
	 * Also returns true if the <code>testedTask</code> started during the <code>mainTask</code> and finished after the <code>mainTask</code>
	 * 
	 * @param mainTask
	 * @param testedTask
	 * @return true if the condition is met.
	 */
	public static final boolean occurIntersect(SimpleOperationThread mainTask, SimpleOperationThread testedTask) {
		return !occurredDuring(mainTask, testedTask) && !occurredDuring(testedTask, mainTask) && !occurredBefore(mainTask, testedTask) && !occurredAfter(mainTask, testedTask);
	}
}
