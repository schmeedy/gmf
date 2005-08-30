/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2005.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.examples.runtime.emf.listener;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.part.EditorPart;

import org.eclipse.gmf.runtime.common.ui.util.ConsoleUtil;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MFilter;
import org.eclipse.gmf.runtime.emf.core.edit.MListener;
import org.eclipse.gmf.examples.runtime.emf.MSLExamplePlugin;

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
	public static final String consoleName = MSLExamplePlugin.getResourceString("Console.name"); //$NON-NLS-1$
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
			ConsoleUtil.printInfo(consoleName, MessageFormat.format(MSLExamplePlugin.getResourceString("LibraryListener.message"), //$NON-NLS-1$
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
