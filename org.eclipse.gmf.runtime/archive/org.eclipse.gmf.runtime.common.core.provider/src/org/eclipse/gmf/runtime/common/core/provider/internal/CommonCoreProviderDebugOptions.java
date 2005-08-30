/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2002, 2003.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.common.core.provider.internal;

/**
 * A list of debug options for this plug-in.
 * 
 * @author khussey
 *
 */
public final class CommonCoreProviderDebugOptions {

	/**
	 * This class should not be instantiated since it is a static constant
	 * class.
	 * 
	 */
	private CommonCoreProviderDebugOptions() {
		/* private constructor */
	}

	/** Debug option. */
	public static final String DEBUG = CommonCoreProviderPlugin.getPluginId() + "/debug"; //$NON-NLS-1$
	
	/** Debug option for tracing exception catching. */
	public static final String EXCEPTIONS_CATCHING = DEBUG + "/exceptions/catching"; //$NON-NLS-1$
	
	/** Debug option for tracing exception throwing. */
	public static final String EXCEPTIONS_THROWING = DEBUG + "/exceptions/throwing"; //$NON-NLS-1$

	/** Debug option for tracing method entering. */
	public static final String METHODS_ENTERING = DEBUG + "/methods/entering"; //$NON-NLS-1$
	
	/** Debug option for tracing method exiting. */
	public static final String METHODS_EXITING = DEBUG + "/methods/exiting"; //$NON-NLS-1$
	
	/** Debug option for tracing action filter providers. */
	public static final String ELEMENT_ACTION_FILTER_PROVIDER = DEBUG + "ElementActionFilterProvider/tracing"; //$NON-NLS-1$ 
	
	/** Debug option for tracing storage unit action filter providers. */
	public static final String STORAGE_UNIT_ACTION_FILTER_PROVIDER = DEBUG + "StorageUnitActionFilterProvider/tracing"; //$NON-NLS-1$
}
