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

package org.eclipse.gmf.examples.runtime.emf.listener;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.examples.runtime.emf.internal.l10n.MSLExampleMessages;
import org.eclipse.gmf.runtime.common.ui.util.ConsoleUtil;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MFilter;
import org.eclipse.gmf.runtime.emf.core.edit.MListener;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.part.EditorPart;

/**
 * Listener for batched changes to the library model. This listener will display
 *  a dialog with relevant information regarding the received events if the
 *  {@link #displayEvents} flag is set to true.
 * 
 * @see org.eclipse.gmf.runtime.emf.core.edit.MListener
 */
public class LibraryListener
	extends MListener {

	public static boolean displayEvents = false;
	
	// Create a name for the console
	public static final String consoleName = MSLExampleMessages.Console_name;
	private EditorPart part = null;

	public LibraryListener(MEditingDomain domain, EditorPart part) {
		super(domain, MFilter.WILDCARD_FILTER);
		this.part = part;
	}

	/*
	 * @see org.eclipse.gmf.runtime.emf.core.edit.MListener#onEvent(java.util.List)
	 */
	public void onEvent(List events) {
		if (displayEvents && part != null && part.getSite() != null) {
			// Display in the console
			MessageConsole console = ConsoleUtil.registerConsole(consoleName);
			ConsoleUtil.printInfo(consoleName, NLS.bind(MSLExampleMessages.LibraryListener_message,
				new Object[] {new Integer(events.size())}));
			for (Iterator iter = events.iterator(); iter.hasNext();) {
				Notification notification = (Notification) iter.next();
				if (null != notification) {
					ConsoleUtil.printInfo(consoleName, "        " + notification.toString());  //$NON-NLS-1$
				}
			}
			ConsolePlugin.getDefault().getConsoleManager().refresh(console);
		}
	}
}
