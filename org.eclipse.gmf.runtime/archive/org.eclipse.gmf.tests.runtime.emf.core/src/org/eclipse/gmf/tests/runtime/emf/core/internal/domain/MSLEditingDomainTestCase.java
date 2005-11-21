package org.eclipse.gmf.tests.runtime.emf.core.internal.domain;

import junit.framework.TestCase;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;


public class MSLEditingDomainTestCase
	extends TestCase {

	public void testCreateAndExecuteEMFStyleCommand() {
		MEditingDomain domain = MEditingDomain.createNewDomain();
		Resource r = domain.createResource("file://C:/temp/foo.extlibrary", EXTLibraryPackage.eINSTANCE.getLibrary()); //$NON-NLS-1$
		
		Library l = (Library)r.getContents().get(0);
		
		Command cmd = SetCommand.create(domain, l, EXTLibraryPackage.eINSTANCE.getLibrary_Name(), "foo"); //$NON-NLS-1$
		assertTrue(cmd.canExecute());
		
		domain.getCommandStack().execute(cmd);
		assertNull(l.getName());
	}
}
