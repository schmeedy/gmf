/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */

package org.eclipse.gmf.tests.runtime.emf.core;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.services.metamodel.GetMetamodelSupportOperation;
import org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupport;
import org.eclipse.gmf.runtime.emf.core.services.metamodel.IMetamodelSupportProvider;
import org.eclipse.gmf.runtime.emf.core.util.OperationUtil;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;

/**
 * Unit tests for the interaction with metamodel providers.
 * 
 * @author Christian W. Damus (cdamus)
 */
public class MetamodelProviderTestCase
	extends BaseTestCase {

	static boolean wasNotified = false;

	static EPackage epackage;

	static EClass eclass;

	static EReference ereference;

	private Resource resource;

	public MetamodelProviderTestCase(String name) {
		super(name);
	}

	protected void setUp()
		throws Exception {

		super.setUp();

		epackage = EcoreFactory.eINSTANCE.createEPackage();
		epackage.setName("_testpackage"); //$NON-NLS-1$

		eclass = EcoreFactory.eINSTANCE.createEClass();
		eclass.setName("_testclass"); //$NON-NLS-1$
		epackage.getEClassifiers().add(eclass);

		ereference = EcoreFactory.eINSTANCE.createEReference();
		ereference.setName("_testreference"); //$NON-NLS-1$
		ereference.setChangeable(true);
		ereference.setContainment(true);
		ereference.setEType(eclass);
		eclass.getEStructuralFeatures().add(ereference);

		resource = ResourceUtil.create(null, eclass);
	}

	protected void tearDown()
		throws Exception {

		ResourceSet rset = resource.getResourceSet();
		ResourceUtil.unload(resource);
		rset.getResources().remove(resource);

		epackage = null;
		eclass = null;
		ereference = null;

		super.tearDown();
	}

	public void test_metamodelNotifiedOnDo() {
		final boolean[] notificationHappened = new boolean[1];

		OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				OperationUtil.runAsUnchecked(new MRunnable() {

					public Object run() {
						EObject root = (EObject) resource.getContents().get(0);

						// turn off the flag and make a notifying change
						wasNotified = false;
						root.eSet(ereference, epackage.getEFactoryInstance()
							.create(eclass));

						notificationHappened[0] = wasNotified;
						return null;
					}
				});
			}
		});

		assertTrue(
			"Notification did not occur as expected", notificationHappened[0]); //$NON-NLS-1$
	}

	public void test_metamodelNotNotifiedOnUndo() {
		MUndoInterval undo = OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				OperationUtil.runAsUnchecked(new MRunnable() {

					public Object run() {
						EObject root = (EObject) resource.getContents().get(0);

						// make a notifying change
						root.eSet(ereference, epackage.getEFactoryInstance()
							.create(eclass));

						return null;
					}
				});
			}
		});

		wasNotified = false;
		undo.undo();

		assertFalse("Notification occured on undo", wasNotified); //$NON-NLS-1$
	}

	public void test_metamodelNotNotifiedOnRedo() {
		MUndoInterval undo = OperationUtil.runInUndoInterval(new Runnable() {

			public void run() {
				OperationUtil.runAsUnchecked(new MRunnable() {

					public Object run() {
						EObject root = (EObject) resource.getContents().get(0);

						// make a notifying change
						root.eSet(ereference, epackage.getEFactoryInstance()
							.create(eclass));

						return null;
					}
				});
			}
		});

		undo.undo();

		wasNotified = false;
		undo.redo();

		assertFalse("Notification occured on redo", wasNotified); //$NON-NLS-1$
	}

	public static class Provider
		extends AbstractProvider
		implements IMetamodelSupportProvider {

		private final IMetamodelSupport metamodel = new MyMetamodel();

		public IMetamodelSupport getMetamodelSupport(EPackage ePackage) {
			IMetamodelSupport result = null;

			if (ePackage == epackage) {
				result = metamodel;
			}

			return result;
		}

		public boolean provides(IOperation operation) {
			boolean result = false;

			if (operation instanceof GetMetamodelSupportOperation) {
				GetMetamodelSupportOperation gmmop = (GetMetamodelSupportOperation) operation;

				result = gmmop.getEPackage() == epackage;
			}

			return result;
		}

	}

	private static class MyMetamodel
		implements IMetamodelSupport {

		public void handleEvent(Notification event) {
			wasNotified = true;
		}

		public boolean canDestroy(EObject eObject) {
			return true;
		}

		public boolean canContain(EClass eContainer, EReference eReference,
				EClass eClass) {
			return false;
		}

		public void postProcess(EObject root) {
			// nothing to do
		}
	}
}