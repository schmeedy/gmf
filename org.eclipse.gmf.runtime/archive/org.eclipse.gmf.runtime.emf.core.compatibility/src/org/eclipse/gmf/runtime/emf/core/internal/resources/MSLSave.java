/******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core.internal.resources;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMISaveImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl;

public class MSLSave extends XMISaveImpl {
	private final static String CC_TOKEN = "xtools2_universal_type_manager"; //$NON-NLS-1$
	
	/**
	 * Discards control characters 0x00 - 0x1F except for TAB, CR, and LF, which
	 * are escaped by EMF.
	 * 
	 * @author khussey
	 * 
	 * @see "https://bugs.eclipse.org/bugs/show_bug.cgi?id=50403"
	 * @see "http://greenbytes.de/tech/webdav/rfc3470.html#binary"
	 */
	protected static class Escape
		extends XMLSaveImpl.Escape {

		private static final char[] NOTHING = {};

		public String convert(String input) {
			boolean changed = false;
			int inputLength = input.length();
			grow(inputLength);
			input.getChars(0, inputLength, value, 0);
			int pos = 0;

			while (inputLength-- > 0) {

				switch (value[pos]) {
					case '&':
						pos = replaceChars(pos, AMP, inputLength);
						changed = true;
						break;
					case '<':
						pos = replaceChars(pos, LESS, inputLength);
						changed = true;
						break;
					case '"':
						pos = replaceChars(pos, QUOTE, inputLength);
						changed = true;
						break;
					case '\n':
						pos = replaceChars(pos, LF, inputLength);
						changed = true;
						break;
					case '\r':
						pos = replaceChars(pos, CR, inputLength);
						changed = true;
						break;
					case '\t':
						pos = replaceChars(pos, TAB, inputLength);
						changed = true;
						break;
					default:
						if (value[pos] < 0x20) {
							// these characters are illegal in XML
							pos = replaceChars(pos, NOTHING, inputLength);
							changed = true;
						} else {
							pos++;
						}
						break;
				}
			}

			return changed ? new String(value, 0, pos)
				: input;
		}
	}
	
	public MSLSave(XMLHelper helper) {
		super(helper);
	}
	
	protected void init(XMLResource resource, Map options) {
		super.init(resource, options);

		if (null != escape) {
			escape = new Escape();
		}
	}
	
	/**
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl#writeTopObjects(java.util.List)
	 */
	public Object writeTopObjects(List contents) {
		writeCCToken();
		return super.writeTopObjects(contents);
	}

	/**
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl#writeTopObject(org.eclipse.emf.ecore.EObject)
	 */
	protected Object writeTopObject(EObject top) {
		writeCCToken();
		return super.writeTopObject(top);
	}
	
	/**
	 * Write ClearCase token.
	 */
	private void writeCCToken() {
		doc.add("<!--" + CC_TOKEN + "-->"); //$NON-NLS-1$ //$NON-NLS-2$
		doc.addLine();
	}
}
