/******************************************************************************
 * Copyright (c) 2002, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.tests.runtime.emf.core;

import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLPlugin;
import org.eclipse.gmf.tests.runtime.emf.core.internal.OperationListenerTestCase;
import org.eclipse.gmf.tests.runtime.emf.core.internal.OperationUtilTestCase;
import org.eclipse.gmf.tests.runtime.emf.core.internal.commands.MSLUndoStackTestCase;
import org.eclipse.gmf.tests.runtime.emf.core.internal.domain.MSLEditingDomainTestCase;
import org.eclipse.gmf.tests.runtime.emf.core.internal.expressions.EObjectPropertyTesterTest;
import org.eclipse.gmf.tests.runtime.emf.core.internal.listener.MFilterTests;
import org.eclipse.gmf.tests.runtime.emf.core.internal.listener.MListenerTest;
import org.eclipse.gmf.tests.runtime.emf.core.internal.listener.MSemProcProviderTests;
import org.eclipse.gmf.tests.runtime.emf.core.internal.listener.ResourceContentTests;
import org.eclipse.gmf.tests.runtime.emf.core.internal.multithread.testcases.ReadOperationTest;
import org.eclipse.gmf.tests.runtime.emf.core.internal.multithread.testcases.ReadWriteOperationTest;
import org.eclipse.gmf.tests.runtime.emf.core.internal.multithread.testcases.WriteOperationTest;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * @author rafikj
 *
 * The test suite.
 */
public class AllTests
	extends TestCase
	implements IPlatformRunnable {

	public AllTests() {
		super(""); //$NON-NLS-1$
	}

	public static Test suite() {
		// Show validation problems on the console.
		Preferences mslui = Platform.getPreferencesService().getRootNode()
			.node("instance").node("org.eclipse.gmf.runtime.emf.ui"); //$NON-NLS-1$ //$NON-NLS-2$
		mslui.putInt("Validation.liveProblemsDisplay", 1); //$NON-NLS-1$
		try {
			mslui.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

		// Bugzilla 113092:
		// force initialization of the MSLPlugin now so that we do not get
		//   ClassCircularityErrors in the loading of any of its classes
		MSLPlugin.getDefault();
		
		TestSuite suite = new TestSuite("MSL Core Tests"); //$NON-NLS-1$

		suite.addTestSuite(ResourceSetTestCase.class);
		suite.addTestSuite(MetamodelProviderTestCase.class);
		suite.addTestSuite(ValidationTestCase.class);
		suite.addTestSuite(EObjectHelperTestCase.class);
		suite.addTestSuite(EObjectPropertyTesterTest.class);
		suite.addTestSuite(MSLUndoStackTestCase.class);
		suite.addTestSuite(OperationUtilTestCase.class);
		suite.addTestSuite(OperationListenerTestCase.class);
		suite.addTestSuite(EObjectContainmentUtilTest.class);
		suite.addTestSuite(EObjectUtilTestCase.class);
		suite.addTestSuite(MSemProcProviderTests.class);
		suite.addTestSuite(ResourceContentTests.class);		
		suite.addTest(RegressionTestCase.suite());
		suite.addTestSuite(SanityTestCase.class);		
		suite.addTestSuite(ReadOperationTest.class);
		suite.addTestSuite(WriteOperationTest.class);		
		suite.addTestSuite(ReadWriteOperationTest.class);
		suite.addTestSuite(EditingDomainExtensibilityTests.class);
		suite.addTestSuite(MSLEditingDomainTestCase.class);
		suite.addTestSuite(MFilterTests.class);
		suite.addTestSuite(MListenerTest.class);

		return suite;
	}

	public Object run(Object args)
		throws Exception {

		TestRunner.run(suite());
		return Arrays
			.asList(new String[] {"Please see raw test suite output for details."}); //$NON-NLS-1$
	}
}