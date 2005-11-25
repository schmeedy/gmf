package org.eclipse.gmf.tests.runtime.emf.core.internal.domain;

import junit.framework.TestCase;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MResourceOption;
import org.eclipse.gmf.tests.runtime.emf.core.BaseCoreTests;


public class MSLEditingDomainTestCase
	extends TestCase {

	public void testCreateAndExecuteEMFStyleCommand() {
		MEditingDomain domain = MEditingDomain.createNewDomain();
		Resource r = domain.createResource("file:///tmp/foo.extlibrary", EXTLibraryPackage.eINSTANCE.getLibrary()); //$NON-NLS-1$
		
		Library l = (Library)r.getContents().get(0);
		
		Command cmd = SetCommand.create(domain, l, EXTLibraryPackage.eINSTANCE.getLibrary_Name(), "foo"); //$NON-NLS-1$
		assertTrue(cmd.canExecute());
		
		domain.getCommandStack().execute(cmd);
		assertNull(l.getName());
	}
	
	public void testResourceCreationAndLoading() {
		MEditingDomain domain = MEditingDomain.createNewDomain();
		
		Resource r = domain.createResource("/tmp/foo.extlibrary"); //$NON-NLS-1$
		assertNotNull(r);
		
		// Resource should not yet be loaded
		assertFalse(r.isLoaded());
		
		Resource r2 = domain.createResource("/tmp/foo.extlibrary"); //$NON-NLS-1$
		assertNotNull(r2);
		assertFalse(r2.isLoaded());
		assertSame(r,r2);
		
		r = domain.createResource(URI
			.createURI(BaseCoreTests.MslCoreTestsBundle.getEntry(
				"/test_models/test_model.extlibrary") //$NON-NLS-1$
				.toString()).toString(), MResourceOption.URI);
		
		assertNotNull(r);
		assertFalse(r.isLoaded());
		
		domain.loadResource(r);
		assertTrue(r.isLoaded());
		
		try {
			r = domain.createResource(URI
				.createURI(BaseCoreTests.MslCoreTestsBundle.getEntry(
					"/test_models/test_model.extlibrary") //$NON-NLS-1$
					.toString()).toString(), MResourceOption.URI);
			fail("We should not be allowed to create a resource that is already loaded."); //$NON-NLS-1$
		} catch (IllegalStateException e) {
			// SUCCESS
		}
		
		r2 = domain.findResource(URI
			.createURI(BaseCoreTests.MslCoreTestsBundle.getEntry(
			"/test_models/test_model.extlibrary") //$NON-NLS-1$
			.toString()).toString(), MResourceOption.URI);
		
		// We should find that we couldn't create this resource because it
		//  was already in the resource set and loaded.
		assertSame(r, r2);
		
		r = domain.createResource(URI
			.createURI(BaseCoreTests.MslCoreTestsBundle.getEntry(
			"/test_models/test_corrupted_model.extlibrary") //$NON-NLS-1$
			.toString()).toString(), MResourceOption.URI);
		
		assertNotNull(r);
		assertFalse(r.isLoaded());
		
		try {
			domain.loadResource(r);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		// TODO Change this line when we turn off automatic unloading of resources during load.
		assertFalse(r.isLoaded());
	}
}
