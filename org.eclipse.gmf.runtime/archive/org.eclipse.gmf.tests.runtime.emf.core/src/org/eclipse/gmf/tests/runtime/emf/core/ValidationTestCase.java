/******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
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
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.gmf.runtime.emf.core.EObjectHelper;
import org.eclipse.gmf.runtime.emf.core.IValidationStatus;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLUtil;
import org.eclipse.gmf.runtime.emf.core.internal.validation.UUIDConstraint;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;
import org.eclipse.uml2.UML2Package;
import org.eclipse.uml2.VisibilityKind;

/**
 * Tests the integration of the Validation Framework into the MSL plug-in.
 * 
 * @author Christian W. Damus (cdamus)
 */
public class ValidationTestCase
	extends BaseTestCase {

	private static final String BAD_CLASS_NAME = "ChristiansFooClass"; //$NON-NLS-1$
	private static final String PLUGIN_ID = "org.eclipse.gmf.tests.runtime.emf.core";  //$NON-NLS-1$
	/**
	 * Turns the constraint enablement on or off.  Avoids interfering
	 * with other test cases that work with this metaclass. 
	 */
	static boolean validationFlag = false;
	static boolean validationParticipationFlag = true;
	
	public ValidationTestCase(String name) {
		super(name);
	}

	public static final class TestConstraint
		extends AbstractModelConstraint {

		public IStatus validate(IValidationContext ctx) {
			if (!validationFlag) {
				return ctx.createSuccessStatus();
			} else {
				return ctx.createFailureStatus(null);
			}
		}
	}
	
	public static final class TestSelector implements IClientSelector {
		public boolean selects(Object object) {
			return validationFlag;
		}
	}

	public void test_singleBatchValidation() {
		EObjectHelper helper = new EObjectHelper(MEditingDomain.INSTANCE);
		Resource resource = ResourceUtil.create(null, UML2Package.eINSTANCE
			.getModel(), 0);

		EModelElement root = (EModelElement) ResourceUtil
			.getFirstRoot(resource);

		openUndoInterval("", "");//$NON-NLS-2$//$NON-NLS-1$

		startWrite();

		EModelElement c1 = (EModelElement) EObjectUtil.create(root,
			UML2Package.eINSTANCE.getPackage_OwnedMember(),
			UML2Package.eINSTANCE.getClass_(), BAD_CLASS_NAME);

		EObjectUtil.create(c1, UML2Package.eINSTANCE
			.getStructuredClassifier_OwnedAttribute(), UML2Package.eINSTANCE
			.getProperty(), "x"); //$NON-NLS-1$

		c1.eSet(UML2Package.eINSTANCE
			.getPackageableElement_PackageableElement_visibility(),
			VisibilityKind.PUBLIC_LITERAL);

		completeWrite();
		
		closeUndoInterval();

		validationFlag = true;
		List statuses = MSLUtil.getValidationProblems(
			helper.validate(Collections.singleton(c1), null));
		validationFlag = false;

		assertNotNull(statuses);

		assertFalse(statuses.isEmpty());

		int failed = 0;
		for (Iterator iter = statuses.iterator(); iter.hasNext();) {
			IValidationStatus next = (IValidationStatus) iter.next();

			if (!next.isOK() && next.getPlugin().equals(PLUGIN_ID)) {
				failed++;
			}
		}

		// the model created by this method violates at least one constraint
		assertFalse(failed == 0);
	}

	public void test_multipleBatchValidation() {
		EObjectHelper helper = new EObjectHelper(MEditingDomain.INSTANCE);
		Resource resource = ResourceUtil.create(null, UML2Package.eINSTANCE
			.getModel(), 0);

		EModelElement root = (EModelElement) ResourceUtil
			.getFirstRoot(resource);

		openUndoInterval("", "");//$NON-NLS-2$//$NON-NLS-1$

		startWrite();

		EModelElement c1 = (EModelElement) EObjectUtil.create(root,
			UML2Package.eINSTANCE.getPackage_OwnedMember(),
			UML2Package.eINSTANCE.getClass_(), BAD_CLASS_NAME);

		EModelElement p1 = (EModelElement) EObjectUtil.create(c1,
			UML2Package.eINSTANCE.getStructuredClassifier_OwnedAttribute(),
			UML2Package.eINSTANCE.getProperty(), "x"); //$NON-NLS-1$

		c1.eSet(UML2Package.eINSTANCE
			.getPackageableElement_PackageableElement_visibility(),
			VisibilityKind.PUBLIC_LITERAL);

		completeWrite();
		closeUndoInterval();

		validationFlag = true;
		List statuses = MSLUtil.getValidationProblems(
			helper.validate(Arrays.asList(new Object[] {c1, p1}), null));
		validationFlag = false;

		assertNotNull(statuses);

		assertFalse(statuses.isEmpty());

		int failed = 0;
		for (Iterator iter = statuses.iterator(); iter.hasNext();) {
			IValidationStatus next = (IValidationStatus) iter.next();

			if (!next.isOK() && next.getPlugin().equals(PLUGIN_ID)) {
				failed++;
			}
		}

		// the model created by this method violates at least one constraint
		assertFalse(failed == 0);
	}

	public void test_liveValidation() {
		Resource resource = ResourceUtil.create(null, UML2Package.eINSTANCE
			.getModel(), 0);

		EModelElement root = (EModelElement) ResourceUtil
			.getFirstRoot(resource);

		openUndoInterval("", "");//$NON-NLS-2$//$NON-NLS-1$

		startWrite();

		EModelElement c1 = (EModelElement) EObjectUtil.create(root,
			UML2Package.eINSTANCE.getPackage_OwnedMember(),
			UML2Package.eINSTANCE.getClass_(), BAD_CLASS_NAME);

		c1.eSet(UML2Package.eINSTANCE.getNamedElement_Name(), BAD_CLASS_NAME);

		MSLActionAbandonedException e = null;

		// turn on the test live constraint
		validationFlag = true;

		try {
			completeWriteWithValidation();

			// should not get here
			fail("Exception not thrown."); //$NON-NLS-1$
		} catch (MSLActionAbandonedException ex) {
			// good!  We pass the test
			e = ex;
		} finally {
			// turn off the test live constraint
			validationFlag = false;

			closeUndoInterval();
		}

		assertNotNull(e.getStatus());
		List statuses = MSLUtil.getValidationProblems(e.getStatus());

		assertNotNull(statuses);

		assertFalse(statuses.isEmpty());

		int failed = 0;
		for (Iterator iter = statuses.iterator(); iter.hasNext();) {
			IValidationStatus next = (IValidationStatus) iter.next();

			if (!next.isOK() && next.getPlugin().equals(PLUGIN_ID)) {
				failed++;
			}
		}

		// the model created by this method violates at least one constraint:
		//   the one that asserts that classifiers not be named
		//   'ChristiansFooClass', defined by this test plug-in
		assertFalse(failed == 0);

		// check that the model was rolled back
		Collection types = (Collection) root.eGet(UML2Package.eINSTANCE
			.getPackage_OwnedType());

		assertEquals(0, types.size());
	}

	public static class TestValidationContext implements IValidationContext {
		
		private EObject target;

		public void addResult(EObject eObject) {
			// do nothing
		}

		public void addResults(Collection eObjects) {
			// do nothing
		}

		public IStatus createFailureStatus(Object[] messageArguments) {
			return new Status(IStatus.ERROR, Platform.PI_RUNTIME, 1, "", null); //$NON-NLS-1$
		}

		public IStatus createSuccessStatus() {
			return Status.OK_STATUS;
		}

		public void disableCurrentConstraint(Throwable exception) {
			// do nothing
		}

		public List getAllEvents() {
			return null;
		}

		public Object getCurrentConstraintData() {
			return null;
		}

		public String getCurrentConstraintId() {
			return null;
		}

		public EMFEventType getEventType() {
			return null;
		}

		public EStructuralFeature getFeature() {
			return null;
		}

		public Object getFeatureNewValue() {
			return null;
		}

		public Set getResultLocus() {
			return null;
		}

		public EObject getTarget() {
			return target;
		}
		
		public void setTarget(EObject eObject) {
			target = eObject;
		}

		public Object putCurrentConstraintData(Object newData) {
			return null;
		}

		public void skipCurrentConstraintFor(EObject eObject) {
			// do nothing
		}

		public void skipCurrentConstraintForAll(Collection eObjects) {
			// do nothing
		}
	};

	public void test_UUIDConstraint() {
		Resource resource = ResourceUtil.create(null, UML2Package.eINSTANCE
			.getModel(), 0);
	
		EModelElement root = (EModelElement) ResourceUtil
			.getFirstRoot(resource);
	
		openUndoInterval("", "");//$NON-NLS-2$//$NON-NLS-1$
	
		startWrite();
	
		EModelElement c1 = (EModelElement) EObjectUtil.create(root,
			UML2Package.eINSTANCE.getPackage_OwnedMember(),
			UML2Package.eINSTANCE.getClass_(), "Class1"); //$NON-NLS-1$
		
		assertTrue(resource instanceof XMLResource);
	
		XMLResource xmlResource = (XMLResource) resource;
		
		String duplicateUUID = "duplicateUUID"; //$NON-NLS-1$
		xmlResource.setID(root, duplicateUUID);
		xmlResource.setID(c1, duplicateUUID);
		
		completeWrite();
		closeUndoInterval();

		
		TestValidationContext validationContext = new TestValidationContext();
		UUIDConstraint constraint = new UUIDConstraint();
		validationContext.setTarget(root);
		IStatus status = constraint.validate(validationContext);
		
		assertNotNull(status);
		assertTrue(IStatus.ERROR == status.getSeverity());
	}
}