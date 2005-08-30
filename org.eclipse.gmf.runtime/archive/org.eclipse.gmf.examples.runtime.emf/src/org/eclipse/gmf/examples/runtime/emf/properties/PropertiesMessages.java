/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2003, 2004.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.examples.runtime.emf.properties;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Utility class which helps manage messages.
 */
class PropertiesMessages {
	private static final String RESOURCE_BUNDLE = "org.eclipse.ui.views.properties.messages"; //$NON-NLS-1$
	private static ResourceBundle bundle =
		ResourceBundle.getBundle(RESOURCE_BUNDLE);

	/**
	 * Constructor
	 */
	private PropertiesMessages() {
		super();
	}

	/**
	 * Returns the formatted message for the given key in
	 * the resource bundle. 
	 *
	 * @param key the resource name
	 * @param args the message arguments
	 * @return the string
	 */
	public static String format(String key, Object[] args) {
		return MessageFormat.format(getString(key), args);
	}
	/**
	 * Returns the resource object with the given key in
	 * the resource bundle. If there isn't any value under
	 * the given key, the key is returned.
	 *
	 * @param key the resource name
	 * @return the string
	 */
	public static String getString(String key) {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}
}