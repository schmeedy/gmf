/******************************************************************************
 * Copyright (c) 2002, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.tests.runtime.emf.core;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.internal.commands.MSLUndoStack.ActionLockMode;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;
import org.eclipse.uml2.UML2Package;

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

		((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().openUndoInterval("", "");//$NON-NLS-2$//$NON-NLS-1$

		((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().startAction(ActionLockMode.WRITE);

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

		EObjectUtil.putDetail(a2, "k1", "v1"); //$NON-NLS-2$//$NON-NLS-1$

		EObject f1 = EObjectUtil.create(p1, UML2Package.eINSTANCE
			.getPackage_OwnedMember(), UML2Package.eINSTANCE.getInterface());

		assertNotNull(f1);
		
		// This has been commented out because we cannot assume that there are
		//  any semantic procedures at this layer. ChrisM
		//assertEquals(EObjectUtil.getName(f1), "Interface1"); //$NON-NLS-1$

		try {
			((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().completeAndValidateAction();
		} catch (MSLActionAbandonedException e) {
			((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().closeUndoInterval();
			fail("Action abandoned: " + e.getStatus()); //$NON-NLS-1$
		}

		MUndoInterval i1 = ((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().closeUndoInterval();

		ResourceUtil.save(resource, 0);

		i1.undo();

		ResourceUtil.saveAs(resource, "\\tmp\\test2.emx", 0); //$NON-NLS-1$

		i1.redo();

		((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().openUndoInterval("", "");//$NON-NLS-2$//$NON-NLS-1$

		((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().startAction(ActionLockMode.WRITE);

		EObjectUtil.destroy(p1);

		try {
			((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().completeAndValidateAction();
		} catch (MSLActionAbandonedException e) {
			((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().closeUndoInterval();
			fail("Action abandoned: " + e.getStatus()); //$NON-NLS-1$
		}

		MUndoInterval i2 = ((MSLEditingDomain) MEditingDomain.INSTANCE).getUndoStack().closeUndoInterval();

		i2.undo();

		ResourceUtil.saveAs(resource, "\\tmp\\test3.emx", 0); //$NON-NLS-1$
	}
}