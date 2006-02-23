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

package org.eclipse.gmf.examples.runtime.emf.internal.l10n;

import org.eclipse.osgi.util.NLS;

/**
 * An accessor class for externalized strings.
 * 
 * @author Christian Vogt (cvogt)
 */
public class MSLExampleMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.gmf.examples.runtime.emf.internal.l10n.MSLExampleMessages"; //$NON-NLS-1$

	public static String CreateAction_label;
	public static String CreateAction_title;
	public static String CreateAction_message;
	public static String CreateAction_undoMessage;
	public static String CreateAction_warningPositiveInteger;
	public static String EditAction_label;
	public static String EditAction_undoMessage;
	public static String PropertySheetDialog_title;
	public static String PropertySheetDialog_resetProperty;
	public static String DeleteAction_undoMessage;
	public static String PasteAction_undoMessage;
	public static String MSLLibraryActionBarContributor_liveValidationError;
	public static String BatchValidationDelegate_title;
	public static String BatchValidationDelegate_successMessage;
	public static String BatchValidationDelegate_errorPrefix;
	public static String BatchValidationDelegate_warningPrefix;
	public static String ValidationErrorDialog_errorMessage;
	public static String message_exception;
	public static String SetPathmapDelegate_pathmapNameLabel;
	public static String SetPathmapDelegate_pathmapDefaultName;
	public static String LibraryListener_title;
	public static String LibraryListener_message;
	public static String CreateResourceDelegate_title;
	public static String LoadResourceDelegate_title;
	public static String PropertySheetViewer_property;
	public static String PropertySheetViewer_value;
	public static String PropertySheetViewer_misc;
	public static String MSLLibraryEditor_menu;
	public static String Console_name;
	public static String ControlUnitAction_label;
	public static String UncontrolUnitAction_label;
	public static String Resource_name;
	
	public static String ResourceURI_label;
	public static String ContainerURI_label;
	public static String BrowseFileSystem_label;
	public static String BrowseWorkspace_label;
	public static String SelectContainer_label;
	public static String Undo_menu_item_label;
	public static String Redo_menu_item_label;
	public static String SetPathmapDialog_title;

	static {
		NLS.initializeMessages(BUNDLE_NAME, MSLExampleMessages.class);
	}
}
