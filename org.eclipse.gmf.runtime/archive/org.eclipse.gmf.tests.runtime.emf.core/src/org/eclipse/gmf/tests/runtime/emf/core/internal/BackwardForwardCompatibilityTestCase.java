/******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.gmf.tests.runtime.emf.core.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;

import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;

import junit.framework.TestCase;

/**
 * Tests some of the basic low-level MSL functionality for backward/forward
 * compatibility.
 * 
 * @author cmcgee
 */
public class BackwardForwardCompatibilityTestCase extends TestCase {
	public void testEPackageVersionMistmatchLookup() {
		// Grab the namespace URI of the EXTLibrary EPackage
		String nsURI = EXTLibraryPackage.eINSTANCE.getNsURI();
		
		// Grab the version major, middle and minor numbers.
		String pattern = "(\\d+)\\.(\\d+)\\.(\\d+)"; //$NON-NLS-1$
		int major=0,middle=0,minor=0;
		Matcher matcher = Pattern.compile(pattern).matcher(nsURI);
		assertTrue(matcher.find());
		major = Integer.parseInt(matcher.group(1));
		middle = Integer.parseInt(matcher.group(2));
		minor = Integer.parseInt(matcher.group(3));
		
		// Now, we will alter the version number of the URI to ensure that the
		//  extended metadata matcher will give us back the original EPackage. Note there 
		//  should only be one version of the EXT Library metamodel in the registry for this
		//  test case to work.
		
		String newVersionNsURI = nsURI.replaceFirst(pattern,(major+1)+"."+middle+"."+minor);  //$NON-NLS-1$ //$NON-NLS-2$
		
		assertFalse(nsURI.equals(newVersionNsURI));
		
		MSLEditingDomain editingDomain = (MSLEditingDomain)MEditingDomain.createNewDomain();
		ExtendedMetaData domainExtendedMetaData = editingDomain.getExtendedMetaData();
		
		assertSame(domainExtendedMetaData.getPackage(newVersionNsURI),
				EXTLibraryPackage.eINSTANCE);
		
		String olderVersionNsURI = nsURI.replaceFirst(pattern,(major-1)+"."+middle+"."+minor); //$NON-NLS-1$ //$NON-NLS-2$
		
		assertFalse(nsURI.equals(olderVersionNsURI));
		
		assertSame(domainExtendedMetaData.getPackage(olderVersionNsURI),
				EXTLibraryPackage.eINSTANCE);
	}
}
