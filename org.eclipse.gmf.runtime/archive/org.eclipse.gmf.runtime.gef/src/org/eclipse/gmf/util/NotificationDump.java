/*
 * Copyright (c) 2005 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.util;

import java.text.MessageFormat;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class NotificationDump {
	private static final boolean IS_FULL_DUMP = false;

	public static void print(Notification notification) {
		final String formatShort = ">>>:{0} (notifier={1}, feature={2}, newValue={4})\n";
		final String formatLong = ">>>:{0}\nNotifier:{1}\n\tfeature:{2}\n\told value: {3}\n\tnew value: {4}\n\tposition:{5}\n";
		Object[] args = {getEventTypeString(notification), getNotifierString(notification), 
				getFeatureString((EStructuralFeature) notification.getFeature()),
				getOldValueString(notification), getNewValueString(notification), new Integer(notification.getPosition())
		};

		System.err.println(MessageFormat.format(IS_FULL_DUMP ? formatLong : formatShort, args));
	}

	private static String getEventTypeString(Notification notification) {
		switch (notification.getEventType()) {
		case Notification.ADD : return "ADD"; //$NON-NLS-1$
		case Notification.ADD_MANY : return "ADD_MANY"; //$NON-NLS-1$
		case Notification.MOVE : return "MOVE"; //$NON-NLS-1$
		case Notification.REMOVE : return "REMOVE"; //$NON-NLS-1$
		case Notification.REMOVE_MANY : return "REMOVE_MANY"; //$NON-NLS-1$
		case Notification.REMOVING_ADAPTER : return "REMOVING_ADAPTER"; //$NON-NLS-1$
		case Notification.SET : return "SET"; //$NON-NLS-1$
		case Notification.RESOLVE : return "RESOLVE"; //$NON-NLS-1$
		case Notification.UNSET : return "UNSET"; //$NON-NLS-1$
		default : throw new RuntimeException();
		}
	}

	private static String getFeatureString(EStructuralFeature feature) {
		return feature.getName();
	}

	private static String getNotifierString(Notification notification) {
		return getObjectString(notification.getNotifier());
	}

	private static String getOldValueString(Notification notification) {
		return getObjectStringWithIdentity(notification.getOldValue());
	}

	private static String getNewValueString(Notification notification) {
		return getObjectStringWithIdentity(notification.getNewValue());
	}

	private static String getObjectStringWithIdentity(Object obj) {
		if (obj instanceof EObject) {
			return ((EObject) obj).eClass().getName() + ':' + obj.hashCode();
		}
		return String.valueOf(obj);
	}

	private static String getObjectString(Object obj) {
		if (obj instanceof EObject) {
			return ((EObject) obj).eClass().getName();
		}
		return String.valueOf(obj);
	}
}
