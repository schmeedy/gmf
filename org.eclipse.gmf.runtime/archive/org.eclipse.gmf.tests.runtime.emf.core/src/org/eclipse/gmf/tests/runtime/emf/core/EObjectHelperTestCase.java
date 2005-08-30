/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2004.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */package org.eclipse.gmf.tests.runtime.emf.core;

import java.io.IOException;
import java.util.HashMap;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.Generalization;
import org.eclipse.uml2.Model;
import org.eclipse.uml2.UML2Package;

import org.eclipse.gmf.runtime.emf.core.EObjectHelper;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.util.OperationUtil;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;

/**
 * @author fplante
 * 
 * Test cases fopr EObjectHelper functionality not covered elsewhere
 */
public class EObjectHelperTestCase
	extends TestCase {

	private UML2Package uml2 = UML2Package.eINSTANCE;

	private EObjectHelper eObjectHelper = new EObjectHelper(
		MEditingDomain.INSTANCE);

	private Resource resource1;

	private Resource resource2;

	private Model model1;

	private Model model2;

	private org.eclipse.uml2.Class class1;

	private org.eclipse.uml2.Class class2;

	private Generalization generalization;

	protected void setUp()
		throws Exception {

		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				try {
					OperationUtil.runAsWrite(new Runnable() {

						public void run() {

							/* Created a model with a class */
							resource1 = ResourceUtil.create(
								"\\tmp\\resource1.emx", //$NON-NLS-1$
								uml2.getModel(), 0);
							assertNotNull(resource1);

							model1 = (Model) ResourceUtil
								.getFirstRoot(resource1);
							assertNotNull(model1);

							class1 = (org.eclipse.uml2.Class) model1
								.createOwnedMember(uml2.getClass_());
							assertNotNull(class1);

							/* Created a second model with a class */
							resource2 = ResourceUtil.create(
								"\\tmp\\resource2.emx", //$NON-NLS-1$
								uml2.getModel(), 0);
							assertNotNull(resource2);

							model2 = (Model) ResourceUtil
								.getFirstRoot(resource2);
							assertNotNull(model2);

							class2 = (org.eclipse.uml2.Class) model2
								.createOwnedMember(uml2.getClass_());
							assertNotNull(class2);

							/* Created a generalization between the classes */
							generalization = class2
								.createGeneralization(class1);
							assertNotNull(generalization);

							/* Save models */
							try {
								model1.eResource().save(new HashMap());
								model2.eResource().save(new HashMap());
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
						}
					});
				} catch (Exception e) {
					if (null != resource1)
						resource1.unload();
					if (null != resource2)
						resource2.unload();
					throw new RuntimeException(e);
				}
			}
		});
	}

	protected void tearDown()
		throws Exception {

		/* Unload models */
		if (null != resource1) {
			resource1.unload();
			resource1 = null;
		}
		if (null != resource2) {
			resource2.unload();
			resource2 = null;
		}
	}

	public void test_destroy() {

		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				try {
					OperationUtil.runAsWrite(new Runnable() {

						public void run() {
							/* Delete first model's class */
							eObjectHelper.destroy(class1);

							/* Make sure Generalization object is gone */
							assertTrue(class2.getGeneralizations().size() == 0);
						}
					});
				} catch (MSLActionAbandonedException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

}