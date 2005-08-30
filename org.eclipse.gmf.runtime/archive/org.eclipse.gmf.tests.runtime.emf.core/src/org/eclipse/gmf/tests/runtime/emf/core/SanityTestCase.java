/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2002, 2003.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.tests.runtime.emf.core;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.UML2Package;

import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;
import org.eclipse.gmf.runtime.emf.core.util.OperationUtil;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;

/**
 * @author rafikj
 *
 * Sanity test case.
 */
public class SanityTestCase
	extends BaseTestCase {

	public SanityTestCase(String name) {
		super(name);
	}

	public void test_sanity1() {

		Resource resource = ResourceUtil.create("\\tmp\\test1.emx", //$NON-NLS-1$
			UML2Package.eINSTANCE.getModel(), 0);

		EModelElement root = (EModelElement) ResourceUtil
			.getFirstRoot(resource);

		OperationUtil.openUndoInterval(""); //$NON-NLS-1$

		OperationUtil.startWrite();

		EAnnotation a1 = EObjectUtil.createEAnnotation(root, "a1"); //$NON-NLS-1$

		EModelElement p1 = (EModelElement) EObjectUtil.create(a1,
			EcorePackage.eINSTANCE.getEAnnotation_Contents(),
			UML2Package.eINSTANCE.getPackage());

		assertNotNull(EObjectUtil.getName(p1));
		assertNotNull(EObjectUtil.getQName(p1, true));

		String n1 = (String) p1.eGet(UML2Package.eINSTANCE
			.getNamedElement_Name());

		assertNotNull(n1);

		p1.eSet(UML2Package.eINSTANCE.getNamedElement_Name(), "p1"); //$NON-NLS-1$

		String n2 = (String) p1.eGet(UML2Package.eINSTANCE
			.getNamedElement_Name());

		assertFalse(n1.equals(n2));

		EAnnotation a2 = EObjectUtil.createEAnnotation(p1, "a2"); //$NON-NLS-1$

		EObjectUtil.putDetail(a2, "k1", "v1"); //$NON-NLS-1$ //$NON-NLS-2$

		EObject f1 = EObjectUtil.create(p1, UML2Package.eINSTANCE
			.getPackage_OwnedMember(), UML2Package.eINSTANCE.getInterface());

		assertEquals(EObjectUtil.getName(f1), "Interface1"); //$NON-NLS-1$

		try {
			OperationUtil.completeAndValidate();
		} catch (MSLActionAbandonedException e) {
			OperationUtil.closeUndoInterval();
			fail("Action abandoned: " + e.getStatus()); //$NON-NLS-1$
		}

		MUndoInterval i1 = OperationUtil.closeUndoInterval();

		ResourceUtil.save(resource, 0);

		i1.undo();

		ResourceUtil.saveAs(resource, "\\tmp\\test2.emx", 0); //$NON-NLS-1$

		i1.redo();

		OperationUtil.openUndoInterval(""); //$NON-NLS-1$

		OperationUtil.startWrite();

		EObjectUtil.destroy(p1);

		try {
			OperationUtil.completeAndValidate();
		} catch (MSLActionAbandonedException e) {
			OperationUtil.closeUndoInterval();
			fail("Action abandoned: " + e.getStatus()); //$NON-NLS-1$
		}

		MUndoInterval i2 = OperationUtil.closeUndoInterval();

		i2.undo();

		ResourceUtil.saveAs(resource, "\\tmp\\test3.emx", 0); //$NON-NLS-1$
	}
}