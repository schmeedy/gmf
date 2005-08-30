/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2002, 2003.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.gmf.runtime.common.ui.services.action.global;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;


/**
 * Implementation class for the interface <code>IGlobalActionContext</code>
 * 
 * @author Vishy Ramaswamy
 */
public final class GlobalActionContext implements IGlobalActionContext {
    /**
     * Attribute for the action id
     */
    final private String actionId;

    /**
     * Attribute for the active part
     */
    final private IWorkbenchPart activePart;

    /**
     * Attribute for the <code>ISelection</code>
     */
    final private ISelection selection;

    /**
     * Attribute for the label of the action
     */
    final private String label;

    /**
     * Constructor for GlobalActionContext.
     * 
     * @param activePart attribute for active part
     * @param selection attribute for selection 
     * @param label attribute for label
     * @param actionId attribute for action id
     */
    public GlobalActionContext(
        IWorkbenchPart activePart,
        ISelection selection,
        String label,
        String actionId) {
        super();

        assert null != activePart : "activePart cannot be null"; //$NON-NLS-1$
        assert null != selection : "selection cannot be null"; //$NON-NLS-1$
        assert null != label : "label cannot be null"; //$NON-NLS-1$
        assert null != actionId : "actionId cannot be null"; //$NON-NLS-1$

        this.activePart = activePart;
        this.selection = selection;
        this.label = label;
        this.actionId = actionId;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext#getLabel()
     */
    public String getLabel() {
        return label;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext#getSelection()
     */
    public ISelection getSelection() {
        return selection;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext#getActionId()
     */
    public String getActionId() {
        return actionId;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext#getActivePart()
     */
    public IWorkbenchPart getActivePart() {
        return activePart;
    }
}
