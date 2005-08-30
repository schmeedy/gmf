/**
 * 
 */
package org.eclipse.gmf.tests.runtime.emf.core.internal.listener;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.examples.library.Library;
import org.eclipse.emf.examples.library.RMPLibraryFactory;
import org.eclipse.gmf.runtime.emf.core.edit.DemuxedMListener;
import org.eclipse.gmf.runtime.emf.core.edit.MFilter;
import org.eclipse.gmf.runtime.emf.core.edit.MListener;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.tests.runtime.emf.core.BaseCoreTests;


/**
 * Tests related to resource content modifications and resource load and unload
 * notifications
 * 
 * @author vramaswa
 */
public class ResourceContentTests
	extends BaseCoreTests {

	/**
	 * Constructor
	 */
	public ResourceContentTests() {
		super();

	}
	
	/**
	 * Tests whether the framework fires fires only one notification
	 * at the end of a resource load and one for a resource unload
	 */
	public void testResourceLoadAndUnLoadNotification_RATLC00537431() {
		/* Unload the resource */
		assertTrue(testResource != null && testResource.isLoaded());
		testResource.unload();
		
		final int[] eventsCounter = {0};
		MListener regularListener = new MListener(domain) {
			public void onEvent(List events) {
				// only set true if the events size is one
				eventsCounter[0] = eventsCounter[0] + events.size();
			}
		};
		
		/* Set the filter */
		regularListener.setFilter(new MFilter.Or(MFilter.RESOURCE_LOADED_FILTER, MFilter.RESOURCE_UNLOADED_FILTER));
		regularListener.startListening();

		/* Load the resource */
		domain.loadResource(testResource);
		domain.unloadResource(testResource);
		regularListener.stopListening();
		
		assertTrue(eventsCounter[0] == 2);		
	}

	/**
	 * Tests whether the framework fires fires notifications for modifications
	 * to the root contents of the resource
	 */
	public void testRootContentsAddAndRemove_RATLC00536781() {
		/* Unload the resource */
		assertTrue(testResource != null && testResource.isLoaded());
		
		final Library tempLib = RMPLibraryFactory.eINSTANCE.createLibrary();
		final int[] eventsCounter1 = {0};
		MListener regularListener = new MListener(domain) {
			public void onEvent(List events) {
				// only set true if the events size is one
				eventsCounter1[0] = eventsCounter1[0] + events.size();
			}
		};
		final int[] eventsCounter2 = {0};
		DemuxedMListener demuxedListener = new DemuxedMListener(domain) {
			public void handleRootAddedEvent(org.eclipse.emf.common.notify.Notification notification, org.eclipse.emf.ecore.resource.Resource resource, org.eclipse.emf.ecore.EObject eObject) {
				eventsCounter2[0] = eventsCounter2[0] + 1;
				if ( eObject != tempLib ) {
					eventsCounter2[0] = 0;
				}
			};
			
			public void handleRootRemovedEvent(org.eclipse.emf.common.notify.Notification notification, org.eclipse.emf.ecore.resource.Resource resource, org.eclipse.emf.ecore.EObject eObject) {
				eventsCounter2[0] = eventsCounter2[0] + 1;
				if ( eObject != tempLib ) {
					eventsCounter2[0] = 0;
				}				
			};
			
			public MFilter getFilter() {				
				return new MFilter.Or(MFilter.RESOURCE_ROOT_ADDED_FILTER, MFilter.RESOURCE_ROOT_REMOVED_FILTER);
			}
		};
		
		/* Set the filter */
		regularListener.setFilter(new MFilter.Or(MFilter.RESOURCE_ROOT_ADDED_FILTER, MFilter.RESOURCE_ROOT_REMOVED_FILTER));
		regularListener.startListening();
		demuxedListener.startListening();

		/* Add a new library to the root contents and then remove it */
		
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
						public Object run() {
							try {
								EList contents = testResource.getContents();
								contents.add(tempLib);
								contents.remove(tempLib);
							} catch (Exception e) {
								fail("Failed to modify the root contents: " + e.getLocalizedMessage()); //$NON-NLS-1$								
							}
							
							return tempLib;
						}
					});
				} catch (MSLActionAbandonedException e) {
					fail("Failed to load test model: " + e.getLocalizedMessage()); //$NON-NLS-1$
				}				
			}
		});
		
		regularListener.stopListening();
		demuxedListener.stopListening();
		
		assertTrue(eventsCounter1[0] == 2 && eventsCounter2[0] == 2);				
	}

	/**
	 * Tests whether the framework fires fires only one notification
	 * at the end of a resource unload and has the root object passed as
	 * part of the notification
	 */
	public void testResourceUnLoadNotification_RATLC00537431() {
		/* Unload the resource */
		assertTrue(testResource != null && testResource.isLoaded());
	
		final int[] eventsCounter = {0};
		DemuxedMListener demuxedListener = new DemuxedMListener(domain) {
			public MFilter getFilter() {				
				return MFilter.RESOURCE_UNLOADED_FILTER;
			}
			public void handleResourceUnloadedEvent(Notification notification, Resource resource, EObject modelRoot) {
				eventsCounter[0] = eventsCounter[0] + 1;
				
				// if model root fails then set the counter to 0 to detect failure
				if ( modelRoot != root ) {
					eventsCounter[0] = 0;
				}
			}
		};
		
		/* start listening */
		demuxedListener.startListening();

		/* UnLoad the resource */
		domain.unloadResource(testResource);
		demuxedListener.stopListening();
		
		assertTrue(eventsCounter[0] == 1);				
	}	
}
