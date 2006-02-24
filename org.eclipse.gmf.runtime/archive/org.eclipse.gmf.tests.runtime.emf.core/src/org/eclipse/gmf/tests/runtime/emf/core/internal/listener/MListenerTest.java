package org.eclipse.gmf.tests.runtime.emf.core.internal.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.gmf.runtime.emf.core.edit.DemuxedMListener;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;


public class MListenerTest
	extends TestCase {
	
	public static class BrokenResourceFactory implements Resource.Factory {
		public Resource createResource(URI uri) {
			return new ResourceImpl() {
			  public void load(Map options)
					throws IOException {
					if (!isLoaded) {
						load(null, options);
					}
				}
				
				protected void doLoad(InputStream inputStream, Map options)
					throws IOException {
					
					IOException e = new IOException();
					
					getErrors().add(e);
					
					// We can never load without errors.
					throw e;
				}
			};
		}
	}
	
	public void testResourceLoadEventsWhenError() {
		MEditingDomain domain = MEditingDomain.createNewDomain();
		
		final boolean[] results = new boolean[2];
		results[0] = true;
		results[1] = true;
		
		DemuxedMListener listener = new DemuxedMListener(domain) {
			public void handleResourceLoadedEvent(Notification notification, Resource resource) {
				results[0] = false;
			}
			
			public void handleResourceUnloadedEvent(Notification notification, Resource resource, EObject modelRoot) {
				results[1] = false;
			}
		};
		
		listener.startListening();
		
		Resource r = domain.createResource("/tmp/brokenfile.broken"); //$NON-NLS-1$
		assertNotNull(r);
		
		try {
			domain.loadResource(r);
		} catch (Exception e) {
			// Ignore this exception.
		}
		
		assertFalse(results[0]);
		assertTrue(results[1]);
	}
}
