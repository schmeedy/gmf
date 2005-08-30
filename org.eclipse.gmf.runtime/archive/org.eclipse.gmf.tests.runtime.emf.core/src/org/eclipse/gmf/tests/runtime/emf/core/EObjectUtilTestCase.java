package org.eclipse.gmf.tests.runtime.emf.core;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.uml2.Dependency;
import org.eclipse.uml2.Interface;
import org.eclipse.uml2.ParameterableElement;
import org.eclipse.uml2.TemplateParameter;
import org.eclipse.uml2.UML2Factory;
import org.eclipse.uml2.UML2Package;

import org.eclipse.emf.examples.library.Book;
import org.eclipse.emf.examples.library.Library;
import org.eclipse.emf.examples.library.RMPLibraryFactory;
import org.eclipse.gmf.runtime.emf.core.EventTypes;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MResourceOption;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;
import org.eclipse.gmf.runtime.emf.core.util.OperationUtil;
import org.eclipse.gmf.runtime.emf.core.util.ResourceUtil;


/**
 * @author cmcgee
 */
public class EObjectUtilTestCase extends BaseTestCase {

	public EObjectUtilTestCase(String name) {
		super(name);
	}
	
	public void test_metamorphose_containment_RATLC00535597() {
		ResourceSet set = ResourceUtil.getResourceSet();

		assertNotNull(set);

		final Resource resource = set.createResource(URI
			.createFileURI("\\tmp\\test5.emx")); //$NON-NLS-1$

		assertNotNull(resource);
		
		final TemplateParameter tp[] = new TemplateParameter[1];
		final ParameterableElement pe[] = new ParameterableElement[1];
		final ParameterableElement finalpe[] = new ParameterableElement[1];
		
		OperationUtil.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					OperationUtil.runAsWrite(new MRunnable() {
						public Object run() {
							tp[0] = UML2Factory.eINSTANCE.createTemplateParameter();
							pe[0] = UML2Factory.eINSTANCE.createClass();
							
							((org.eclipse.uml2.Class)pe[0]).setName("foo"); //$NON-NLS-1$
							tp[0].setOwnedParameteredElement(pe[0]);
							
							resource.getContents().add(tp[0]);
							
							EObjectUtil.metamorphose(pe[0],UML2Package.eINSTANCE.getInterface());
							
							finalpe[0] = tp[0].getOwnedParameteredElement();
							return null;
						}
					});
				} catch (MSLActionAbandonedException e) {
					assertTrue(false);
				}
			}
		});
		
		assertTrue(finalpe[0] != null);
		assertTrue(finalpe[0].eClass() == UML2Package.eINSTANCE.getInterface());
		assertTrue(((Interface)finalpe[0]).getName().equals(((org.eclipse.uml2.Class)pe[0]).getName()));
		assertTrue(pe[0].eContainer() == null);
	}
	
	public void test_metamorphose() {
		ResourceSet set = ResourceUtil.getResourceSet();

		assertNotNull(set);

		final Resource resource = set.createResource(URI
			.createFileURI("\\tmp\\test6.emx")); //$NON-NLS-1$

		assertNotNull(resource);
		
		final org.eclipse.uml2.Class cls1 = UML2Factory.eINSTANCE.createClass();
		cls1.setName("cls1"); //$NON-NLS-1$
		final org.eclipse.uml2.Class cls2 = UML2Factory.eINSTANCE.createClass();
		cls2.setName("cls2"); //$NON-NLS-1$
		final org.eclipse.uml2.Class cls3 = UML2Factory.eINSTANCE.createClass();
		cls3.setName("cls3"); //$NON-NLS-1$
		final org.eclipse.uml2.Package pkg = UML2Factory.eINSTANCE.createPackage();
		pkg.setName("pkg"); //$NON-NLS-1$
		
		final Dependency dep = UML2Factory.eINSTANCE.createDependency();
		dep.setName("dep"); //$NON-NLS-1$
		final Dependency dep2 = UML2Factory.eINSTANCE.createDependency();
		dep2.setName("dep2"); //$NON-NLS-1$
		
		final int cls2Position[] = new int[1];
		
		OperationUtil.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					OperationUtil.runAsWrite(new MRunnable() {
						public Object run() {
							resource.getContents().add(pkg);
							
							pkg.getOwnedMembers().add(cls1);
							pkg.getOwnedMembers().add(cls2);
							pkg.getOwnedMembers().add(dep);
							pkg.getOwnedMembers().add(dep2);
							
							cls2Position[0] = pkg.getOwnedMembers().indexOf(cls2);
							
							dep.getClients().add(cls1);
							dep.getSuppliers().add(cls2);
							
							dep2.getClients().add(cls2);
							dep2.getSuppliers().add(cls1);
							
							cls2.getNestedClassifiers().add(cls3);
							
							EObjectUtil.metamorphose(cls2,UML2Package.eINSTANCE.getInterface());
							return null;
						}
					});
				} catch (MSLActionAbandonedException e) {
					assertTrue(false);
				}
			}
		});
		
		// The package should still have the same number of members.
		assertTrue(pkg.getOwnedMembers().size() == 4);
		
		// Because Class cls2 was in the second position originally, its replacement
		//  should be in the second position as well.
		EObject replacement = (EObject)pkg.getOwnedMembers().get(cls2Position[0]);
		assertTrue(replacement.eClass() == UML2Package.eINSTANCE.getInterface());
		
		// The replacement should have the same string as one of the orignal's
		//  simple string EStructuralFeatures
		assertTrue(((Interface)replacement).getName().equals("cls2")); //$NON-NLS-1$
		
		// The replacement should contain anything that was contained in the original
		assertTrue(((Interface)replacement).getNestedClassifiers().contains(cls3));
		
		// The replacement should be referenced by anyone that referenced the original
		// The original shouldn't reference anything.
		assertTrue(dep.getSuppliers().contains(replacement));
		assertFalse(dep.getSuppliers().contains(cls2));
		assertTrue(dep2.getClients().contains(replacement));
		assertFalse(dep2.getClients().contains(cls2));
	}
	
	public void testPreDestroyEvents_RATLC00536942() {
		final Library mainBranch = RMPLibraryFactory.eINSTANCE.createLibrary();
		final Library l = RMPLibraryFactory.eINSTANCE.createLibrary();
		final Book b = RMPLibraryFactory.eINSTANCE.createBook();
		
		MEditingDomain domain = MEditingDomain.createNewDomain();
		final Resource r = domain.createResource("foo://",MResourceOption.URI); //$NON-NLS-1$
		
		domain.runAsUnchecked(new MRunnable() {
			public Object run() {
				l.getBooks().add(b);
				mainBranch.getBranches().add(l);
				r.getContents().add(mainBranch);
				return null;
			}
		});

		final int[] correctNotifications = new int[1];
		correctNotifications[0] = 0;
		
		EContentAdapter adapter = new EContentAdapter() {
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				
				if (notification.getEventType() == EventTypes.PRE_DESTROY) {
					Object obj = notification.getOldValue();
					
					// In each case, we will ensure that nothing has been
					//  really destroyed yet because this is a <b>pre</b> destroy
					//  event.
					if (obj instanceof Library) {
						Library aLibrary = (Library)obj;
						
						if (aLibrary.getBooks().contains(b) && aLibrary.eResource() == r) {
							correctNotifications[0]++;
						}
					} else if (obj instanceof Book) {
						Book aBook = (Book)obj;
						
						if (aBook.eContainer() == l && aBook.eResource() == r) {
							correctNotifications[0]++;
						}
					}
				}
			}
		};
		
		r.eAdapters().add(adapter);
		
		domain.runAsUnchecked(new MRunnable() {
			public Object run() {
				EObjectUtil.destroy(l);
				return null;
			}
		});
		
		assertEquals(2,correctNotifications[0]);
	}
}