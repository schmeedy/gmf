/******************************************************************************
 * Copyright (c) 2000, 2005  IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.common.ui.pathvariables.ide.dialogs;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.dialogs.PathVariableDialog;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * A widget group that displays path variables. Includes buttons to edit, remove
 * existing variables and create new ones.
 * <p>
 * Code taken from Eclipse while waiting for bugzilla 49149 to be resolved.
 * 
 * @since 2.1
 */
public class PathVariablesGroup {

	/**
	 * Simple data structure that holds a path variable name/value pair.
	 */
	public static class PathVariableElement {
		/** Element name. */
		String name;
		/** Element path. */
		IPath path;
	}

	// sizing constants
	private static final int SIZING_SELECTION_PANE_WIDTH = 400;

	// parent shell
	private Shell shell;

	private Label variableLabel;

	private Table variableTable;

	private Button addButton;

	private Button editButton;

	private Button removeButton;

	// used to compute layout sizes
	private FontMetrics fontMetrics;

	// create a multi select table
	private boolean multiSelect;

	// IResource.FILE and/or IResource.FOLDER
	private int variableType;

	// External listener called when the table selection changes
	private Listener selectionListener;

	// temporary collection for keeping currently defined variables
	private SortedMap tempPathVariables;

	// set of removed variables' names
	private Set removedVariableNames;

	// reference to the workspace's path variable manager
	private IPathVariableManager pathVariableManager;

	// file image
	private final Image FILE_IMG = PlatformUI.getWorkbench().getSharedImages()
		.getImage(ISharedImages.IMG_OBJ_FILE);

	// folder image
	private final Image FOLDER_IMG = PlatformUI.getWorkbench()
		.getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);

	// unknown (non-existent) image. created locally, dispose locally
	private Image imageUnkown;

	/**
	 * Creates a new PathVariablesGroup.
	 * 
	 * @param multiSelect
	 *            create a multi select tree
	 * @param variableType
	 *            the type of variables that are displayed in the widget group.
	 *            <code>IResource.FILE</code> and/or
	 *            <code>IResource.FOLDER</code> logically ORed together.
	 */
	public PathVariablesGroup(boolean multiSelect, int variableType) {
		this.multiSelect = multiSelect;
		this.variableType = variableType;
		pathVariableManager = ResourcesPlugin.getWorkspace()
			.getPathVariableManager();
		removedVariableNames = new HashSet();
		tempPathVariables = new TreeMap();
		// initialize internal model
		initTemporaryState();
	}

	/**
	 * Creates a new PathVariablesGroup.
	 * 
	 * @param multiSelect
	 *            create a multi select tree
	 * @param variableType
	 *            the type of variables that are displayed in the widget group.
	 *            <code>IResource.FILE</code> and/or
	 *            <code>IResource.FOLDER</code> logically ORed together.
	 * @param pathVariableManager
	 *            Path Variable Manager that uses this group
	 */
	public PathVariablesGroup(boolean multiSelect, int variableType,
			IPathVariableManager pathVariableManager) {
		this.multiSelect = multiSelect;
		this.variableType = variableType;
		this.pathVariableManager = pathVariableManager;
		removedVariableNames = new HashSet();
		tempPathVariables = new TreeMap();
		// initialize internal model
		initTemporaryState();
	}

	/**
	 * Creates a new PathVariablesGroup.
	 * 
	 * @param multiSelect
	 *            create a multi select tree
	 * @param variableType
	 *            the type of variables that are displayed in the widget group.
	 *            <code>IResource.FILE</code> and/or
	 *            <code>IResource.FOLDER</code> logically ORed together.
	 * @param selectionListener
	 *            listener notified when the selection changes in the variables
	 *            list.
	 */
	public PathVariablesGroup(boolean multiSelect, int variableType,
			Listener selectionListener) {
		this(multiSelect, variableType);
		this.selectionListener = selectionListener;
	}

	/**
	 * Opens a dialog for creating a new variable.
	 */
	private void addNewVariable() {
		// constructs a dialog for editing the new variable's current name and
		// value
		PathVariableDialog dialog = new PathVariableDialog(shell,
			PathVariableDialog.NEW_VARIABLE, variableType, pathVariableManager,
			tempPathVariables.keySet());

		// opens the dialog - just returns if the user cancels it
		if (dialog.open() == Window.CANCEL)
			return;

		// otherwise, adds the new variable (or updates an existing one) in the
		// temporary collection of currently defined variables
		String newVariableName = dialog.getVariableName();
		IPath newVariableValue = new Path(dialog.getVariableValue());
		tempPathVariables.put(newVariableName, newVariableValue);

		// the UI must be updated
		updateWidgetState(newVariableName);
	}

	/**
	 * Creates the widget group. Callers must call <code>dispose</code> when
	 * the group is no longer needed.
	 * 
	 * @param parent
	 *            the widget parent
	 * @return container of the widgets
	 */
	public Control createContents(Composite parent) {
		Font font = parent.getFont();

		if (imageUnkown == null) {
			ImageDescriptor descriptor = AbstractUIPlugin
				.imageDescriptorFromPlugin(IDEWorkbenchPlugin.IDE_WORKBENCH,
					"icons/full/obj16/warning.gif"); //$NON-NLS-1$
			imageUnkown = descriptor.createImage();
		}
		initializeDialogUnits(parent);
		shell = parent.getShell();

		// define container & its layout
		Composite pageComponent = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		pageComponent.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = SIZING_SELECTION_PANE_WIDTH;
		pageComponent.setLayoutData(data);
		pageComponent.setFont(font);

		// layout the table & its buttons
		variableLabel = new Label(pageComponent, SWT.LEFT);
		variableLabel.setText(IDEWorkbenchMessages.PathVariablesBlock_variablesLabel);

		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.horizontalSpan = 2;
		variableLabel.setLayoutData(data);
		variableLabel.setFont(font);

		int tableStyle = SWT.BORDER | SWT.FULL_SELECTION;
		if (multiSelect) {
			tableStyle |= SWT.MULTI;
		}
		variableTable = new Table(pageComponent, tableStyle);
		variableTable.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				updateEnabledState();
				if (selectionListener != null)
					selectionListener.handleEvent(new Event());
			}
		});
		data = new GridData(GridData.FILL_BOTH);
		data.heightHint = variableTable.getItemHeight() * 7;
		variableTable.setLayoutData(data);
		variableTable.setFont(font);

		createButtonGroup(pageComponent);
		// populate table with current internal state and set buttons' initial
		// state
		updateWidgetState(null);

		return pageComponent;
	}

	/**
	 * Disposes the group's resources.
	 */
	public void dispose() {
		if (imageUnkown != null) {
			imageUnkown.dispose();
			imageUnkown = null;
		}
	}

	/**
	 * Opens a dialog for editing an existing variable.
	 * 
	 * @see PathVariableDialog
	 */
	private void editSelectedVariable() {
		// retrieves the name and value for the currently selected variable
		TableItem item = variableTable.getItem(variableTable
			.getSelectionIndex());
		String variableName = (String) item.getData();
		IPath variableValue = (IPath) tempPathVariables.get(variableName);

		// constructs a dialog for editing the variable's current name and value
		PathVariableDialog dialog = new PathVariableDialog(shell,
			PathVariableDialog.EXISTING_VARIABLE, variableType,
			pathVariableManager, tempPathVariables.keySet());
		dialog.setVariableName(variableName);
		dialog.setVariableValue(variableValue.toOSString());

		// opens the dialog - just returns if the user cancels it
		if (dialog.open() == Window.CANCEL)
			return;

		// the name can be changed, so we remove the current variable
		// definition...
		removedVariableNames.add(variableName);
		tempPathVariables.remove(variableName);

		String newVariableName = dialog.getVariableName();
		IPath newVariableValue = new Path(dialog.getVariableValue());

		// and add it again (maybe with a different name)
		tempPathVariables.put(newVariableName, newVariableValue);

		// now we must refresh the UI state
		updateWidgetState(newVariableName);

	}

	/**
	 * Returns the enabled state of the group's widgets. Returns
	 * <code>true</code> if called prior to calling
	 * <code>createContents</code>.
	 * 
	 * @return boolean the enabled state of the group's widgets.
	 *         <code>true</code> if called prior to calling
	 *         <code>createContents</code>.
	 */
	public boolean getEnabled() {
		if (variableTable != null && !variableTable.isDisposed()) {
			return variableTable.getEnabled();
		}
		return true;
	}

	/**
	 * Returns the selected variables.
	 * 
	 * @return the selected variables. Returns an empty array if the widget
	 *         group has not been created yet by calling
	 *         <code>createContents</code>
	 */
	public PathVariableElement[] getSelection() {
		if (variableTable == null) {
			return new PathVariableElement[0];
		}
		TableItem[] items = variableTable.getSelection();
		PathVariableElement[] selection = new PathVariableElement[items.length];

		for (int i = 0; i < items.length; i++) {
			String name = (String) items[i].getData();
			selection[i] = new PathVariableElement();
			selection[i].name = name;
			selection[i].path = (IPath) tempPathVariables.get(name);
		}
		return selection;
	}

	/**
	 * Creates the add/edit/remove buttons
	 * 
	 * @param parent
	 *            the widget parent
	 */
	private void createButtonGroup(Composite parent) {
		Font font = parent.getFont();
		Composite groupComponent = new Composite(parent, SWT.NULL);
		GridLayout groupLayout = new GridLayout();
		groupLayout.marginWidth = 0;
		groupLayout.marginHeight = 0;
		groupComponent.setLayout(groupLayout);
		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		groupComponent.setLayoutData(data);
		groupComponent.setFont(font);

		addButton = new Button(groupComponent, SWT.PUSH);
		addButton.setText(IDEWorkbenchMessages.PathVariablesBlock_addVariableButton);

		addButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				addNewVariable();
			}
		});
		addButton.setFont(font);
		setButtonLayoutData(addButton);

		editButton = new Button(groupComponent, SWT.PUSH);
		editButton.setText(IDEWorkbenchMessages.PathVariablesBlock_editVariableButton);
		
		editButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				editSelectedVariable();
			}
		});
		editButton.setFont(font);
		setButtonLayoutData(editButton);

		removeButton = new Button(groupComponent, SWT.PUSH);
		removeButton.setText(IDEWorkbenchMessages.PathVariablesBlock_removeVariableButton);
		
		removeButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				removeSelectedVariables();
			}
		});
		removeButton.setFont(font);
		setButtonLayoutData(removeButton);
	}

	/**
	 * Initializes the computation of horizontal and vertical dialog units based
	 * on the size of current font.
	 * <p>
	 * This method must be called before <code>setButtonLayoutData</code> is
	 * called.
	 * </p>
	 * 
	 * @param control
	 *            a control from which to obtain the current font
	 */
	protected void initializeDialogUnits(Control control) {
		// Compute and store a font metric
		GC gc = new GC(control);
		gc.setFont(control.getFont());
		fontMetrics = gc.getFontMetrics();
		gc.dispose();
	}

	/**
	 * (Re-)Initialize collections used to mantain temporary variable state.
	 */
	private void initTemporaryState() {
		String[] varNames = pathVariableManager.getPathVariableNames();

		tempPathVariables.clear();
		for (int i = 0; i < varNames.length; i++) {
			IPath value = pathVariableManager.getValue(varNames[i]);

			// the value may not exist any more
			if (value != null) {
				boolean isFile = value.toFile().isFile();
				if ((isFile && (variableType & IResource.FILE) != 0)
					|| (isFile == false && (variableType & IResource.FOLDER) != 0)) {

					tempPathVariables.put(varNames[i], value);
				}
			}
		}
		removedVariableNames.clear();
	}

	/**
	 * Updates button enabled state, depending on the number of currently
	 * selected variables in the table.
	 */
	private void updateEnabledState() {
		int itemsSelectedCount = variableTable.getSelectionCount();
		editButton.setEnabled(itemsSelectedCount == 1);
		removeButton.setEnabled(itemsSelectedCount > 0);
	}

	/**
	 * Rebuilds table widget state with the current list of variables
	 * (reflecting any changes, additions and removals), and selects the item
	 * corresponding to the given variable name. If the variable name is
	 * <code>null</code>, the first item (if any) will be selected.
	 * 
	 * @param selectedVarName
	 *            the name for the variable to be selected (may be
	 *            <code>null</code>)
	 * @see IPathVariableManager#getPathVariableNames()
	 * @see IPathVariableManager#getValue(String)
	 */
	private void updateVariableTable(String selectedVarName) {
		variableTable.removeAll();
		int selectedVarIndex = 0;
		for (Iterator varNames = tempPathVariables.keySet().iterator(); varNames
			.hasNext();) {
			TableItem item = new TableItem(variableTable, SWT.NONE);
			String varName = (String) varNames.next();
			IPath value = (IPath) tempPathVariables.get(varName);
			File file = value.toFile();

			item.setText(varName + " - " + value.toOSString()); //$NON-NLS-1$ 
			// the corresponding variable name is stored in each table widget
			// item
			item.setData(varName);
			item.setImage(file.exists() ? (file.isFile() ? FILE_IMG
				: FOLDER_IMG)
				: imageUnkown);
			if (varName.equals(selectedVarName))
				selectedVarIndex = variableTable.getItemCount() - 1;
		}
		if (variableTable.getItemCount() > selectedVarIndex) {
			variableTable.setSelection(selectedVarIndex);
			if (selectionListener != null)
				selectionListener.handleEvent(new Event());
		} else if (variableTable.getItemCount() == 0
			&& selectionListener != null)
			selectionListener.handleEvent(new Event());
	}

	/**
	 * Commits the temporary state to the path variable manager in response to
	 * user confirmation.
	 * 
	 * @return <code>true</code> if resource was committed succesfully, <code>false</code> otherwise
	 * @see IPathVariableManager#setValue(String, IPath)
	 */
	public boolean performOk() {
		try {
			// first process removed variables
			for (Iterator removed = removedVariableNames.iterator(); removed
				.hasNext();) {
				String removedVariableName = (String) removed.next();
				// only removes variables that have not been added again
				if (!tempPathVariables.containsKey(removedVariableName))
					pathVariableManager.setValue(removedVariableName, null);
			}

			// then process the current collection of variables, adding/updating
			// them
			for (Iterator current = tempPathVariables.entrySet().iterator(); current
				.hasNext();) {
				Map.Entry entry = (Map.Entry) current.next();
				String variableName = (String) entry.getKey();
				IPath variableValue = (IPath) entry.getValue();
				pathVariableManager.setValue(variableName, variableValue);
			}
			// re-initialize temporary state
			initTemporaryState();

			// performOk accepted
			return true;
		} catch (CoreException ce) {
			ErrorDialog.openError(shell, null, null, ce.getStatus());
		}
		return false;
	}

	/**
	 * Removes the currently selected variables.
	 */
	private void removeSelectedVariables() {
		// remove each selected element
		int[] selectedIndices = variableTable.getSelectionIndices();
		for (int i = 0; i < selectedIndices.length; i++) {
			TableItem selectedItem = variableTable.getItem(selectedIndices[i]);
			String varName = (String) selectedItem.getData();
			removedVariableNames.add(varName);
			tempPathVariables.remove(varName);
		}
		updateWidgetState(null);
	}

	/**
	 * Sets the <code>GridData</code> on the specified button to be one that
	 * is spaced for the current dialog page units. The method
	 * <code>initializeDialogUnits</code> must be called once before calling
	 * this method for the first time.
	 * 
	 * @param button
	 *            the button to set the <code>GridData</code>
	 * @return the <code>GridData</code> set on the specified button
	 */
	private GridData setButtonLayoutData(Button button) {
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		int widthHint = Dialog.convertHorizontalDLUsToPixels(fontMetrics,
			IDialogConstants.BUTTON_WIDTH);
		data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT,
			SWT.DEFAULT, true).x);
		button.setLayoutData(data);
		return data;
	}

	/**
	 * Sets the enabled state of the group's widgets. Does nothing if called
	 * prior to calling <code>createContents</code>.
	 * 
	 * @param enabled
	 *            the new enabled state of the group's widgets
	 */
	public void setEnabled(boolean enabled) {
		if (variableTable != null && !variableTable.isDisposed()) {
			variableLabel.setEnabled(enabled);
			variableTable.setEnabled(enabled);
			addButton.setEnabled(enabled);
			if (enabled)
				updateEnabledState();
			else {
				editButton.setEnabled(enabled);
				removeButton.setEnabled(enabled);
			}
		}
	}

	/**
	 * Updates the widget's current state: refreshes the table with the current
	 * defined variables, selects the item corresponding to the given variable
	 * (selects the first item if <code>null</code> is provided) and updates
	 * the enabled state for the Add/Remove/Edit buttons.
	 * 
	 * @param selectedVarName
	 *            the name of the variable to be selected (may be null)
	 */
	private void updateWidgetState(String selectedVarName) {
		updateVariableTable(selectedVarName);
		updateEnabledState();
	}
}