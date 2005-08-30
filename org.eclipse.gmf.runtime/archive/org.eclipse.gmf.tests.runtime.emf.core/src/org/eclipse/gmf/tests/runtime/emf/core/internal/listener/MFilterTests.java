/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.tests.runtime.emf.core.internal.listener;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.emf.core.edit.MFilter;
import org.eclipse.gmf.runtime.emf.core.edit.MListener;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.tests.runtime.emf.core.BaseCoreTests;


/**
 * Tests certain MFilter implementations.
 */
public class MFilterTests
	extends BaseCoreTests {

	private static final String RMPLIBRARY_CONTENTTYPE = "org.eclipse.emf.examples.library.rationalModelingPlatformLibrary"; //$NON-NLS-1$

	public MFilterTests() {
		super();
	}
	
	public void testResourceContentTypeFilter() {
		/* Unload the resource */
		assertTrue(testResource != null && testResource.isLoaded());
		testResource.unload();
		
		final int[] eventsCounter = {0};
		MListener regularListener = new MListener(domain) {
			public void onEvent(List events) {
				eventsCounter[0]++;
			}
		};
		
		/* Set the filter */
		regularListener.setFilter(new MFilter.ResourceContentType(Collections.singleton(RMPLIBRARY_CONTENTTYPE)));
		regularListener.startListening();

		/* Load the resource */
		domain.loadResource(testResource);
		domain.unloadResource(testResource);
		regularListener.stopListening();
		
		assertTrue(eventsCounter[0] > 0);
		
		eventsCounter[0] = 0;
		
		regularListener = new MListener(domain) {
			public void onEvent(List events) {
				eventsCounter[0]++;
			}
		};
		
		/* Set the filter */
		regularListener.setFilter(new MFilter.ResourceContentType(Collections.singleton("org.foo.somecontenttype"))); //$NON-NLS-1$
		regularListener.startListening();

		/* Load the resource */
		domain.loadResource(testResource);
		domain.unloadResource(testResource);
		regularListener.stopListening();
		
		assertTrue(eventsCounter[0] == 0);
		
		eventsCounter[0] = 0;
		
		regularListener = new MListener(domain) {
			public void onEvent(List events) {
				eventsCounter[0]++;
			}
		};
		
		/* Set the filter */
		regularListener.setFilter(new MFilter.ResourceContentType(Collections.singleton(RMPLIBRARY_CONTENTTYPE))); //$NON-NLS-1$

		/* Load the resource */
		domain.loadResource(testResource);
		// Mark the resource as modified so that we won't get any events from the resource itself.
		testResource.setModified(true);
		regularListener.startListening();
		
		domain.runInUndoInterval(new Runnable() {
			public void run() {
				try {
					domain.runAsWrite(new MRunnable() {
						public Object run() {
							// We will modify something inside the resource but
							//  we will not touch the resource itself so that we do
							//  not generate any resource level events.
							EObject aRoot = (EObject)testResource.getContents().get(0);
							EObject element = (EObject)aRoot.eContents().get(0);
							
							List l = (List)aRoot.eGet(element.eContainingFeature());
							l.remove(element);
							return null;
						}
					});
				} catch (MSLActionAbandonedException e) {
					fail();
				}
			}
		});
		
		regularListener.stopListening();
		domain.unloadResource(testResource);
		
		// We shouldn't get any events because we are only interested in resource-level
		//  events.
		assertTrue(eventsCounter[0] == 0);
	}
}
