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

package org.eclipse.gmf.examples.runtime.emf.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.action.CopyAction;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.examples.extlibrary.actions.EXTLibraryExtendedActionBarContributor;
import org.eclipse.emf.examples.extlibrary.presentation.EXTLibraryEditorPlugin;
import org.eclipse.gmf.examples.runtime.emf.MSLExamplePlugin;
import org.eclipse.gmf.examples.runtime.emf.constraints.ValidationDelegateClientSelector;
import org.eclipse.gmf.examples.runtime.emf.dialogs.ValidationErrorDialog;
import org.eclipse.gmf.examples.runtime.emf.editor.MSLLibraryEditor;
import org.eclipse.gmf.examples.runtime.emf.internal.l10n.MSLExampleMessages;
import org.eclipse.gmf.examples.runtime.emf.properties.PropertySheetDialog;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MFilter;
import org.eclipse.gmf.runtime.emf.core.edit.MListener;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.edit.MUndoInterval;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLActionAbandonedException;
import org.eclipse.gmf.runtime.emf.core.resources.CannotAbsorbException;
import org.eclipse.gmf.runtime.emf.core.resources.CannotSeparateException;
import org.eclipse.gmf.runtime.emf.core.resources.ILogicalResource;
import org.eclipse.gmf.runtime.emf.core.util.EObjectUtil;
import org.eclipse.gmf.runtime.emf.core.util.MetaModelUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IActionDelegate2;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;


/**
 * This action bar contributed overrides many of the default action bar contributions
 *  provided by the generated EMF reflective editor.
 * 
 * @author cmcgee
 */
public class MSLLibraryActionBarContributor
	extends EXTLibraryExtendedActionBarContributor {

	private static final String CREATE_ACTION_LABEL = MSLExampleMessages.CreateAction_label;
	private static final String CREATE_ACTION_TITLE = MSLExampleMessages.CreateAction_title;
	private static final String CREATE_ACTION_MESSAGE = MSLExampleMessages.CreateAction_message;
	private static final String CREATE_ACTION_UNDO_MESSAGE = MSLExampleMessages.CreateAction_undoMessage;
	private static final String CREATE_ACTION_WARNING_POSITIVE_INTEGER = MSLExampleMessages.CreateAction_warningPositiveInteger;
	
	private static final String EDIT_ACTION_UNDO_MESSAGE = MSLExampleMessages.EditAction_undoMessage;
	
	private static final String ERROR_LIVE_VALIDATION_TITLE = MSLExampleMessages.MSLLibraryActionBarContributor_liveValidationError;
	
	private static final String DELETE_ACTION_UNDO_MESSAGE = MSLExampleMessages.DeleteAction_undoMessage;
	
	private static final String PASTE_ACTION_UNDO_MESSAGE = MSLExampleMessages.PasteAction_undoMessage;
	
	private MListener resourceLoadListener;
	private List undoIntervals = new ArrayList();
	private int pointer = -1;
	
	private MEditingDomain editingDomain = null;
	
	private String clipboardString = null;
	
	/**
	 * An action class used to create a number of EObjects of a specific
	 *  type (EClass) in a specific containment EReference.
	 * 
	 * @author cmcgee
	 */
	private class MSLCreateAction extends Action {
		private EObject container;
		private EReference containmentFeature;
		private EClass newObjectType;
		
		public MSLCreateAction(EObject container, EReference feature, EClass type) {
			super(NLS.bind(CREATE_ACTION_LABEL, new Object[] {type
				.getName()}));
			this.container = container;
			this.containmentFeature = feature;
			this.newObjectType = type;
		}

		public void run() {
			super.run();
			
			InputDialog dialog = new InputDialog(getShell(),
				CREATE_ACTION_TITLE, NLS.bind(
					CREATE_ACTION_MESSAGE, new Object[] {newObjectType
						.getName()}), "1", new IInputValidator() { //$NON-NLS-1$

					public String isValid(String newText) {
						try {
							if (Integer.parseInt(newText) < 1) {
								return CREATE_ACTION_WARNING_POSITIVE_INTEGER;
							}
						} catch (NumberFormatException e) {
							return CREATE_ACTION_WARNING_POSITIVE_INTEGER;
						}

						return null;
					}
				});
			
			if (dialog.open() == InputDialog.OK) {
				final int number = Integer.parseInt(dialog.getValue());
				MUndoInterval undoInterval = execute(NLS.bind(
					CREATE_ACTION_UNDO_MESSAGE, new Object[] {
						new Integer(number), newObjectType.getName()}),
					new Runnable() {

						public void run() {
							for (int i = 0; i < number; i++) {
								EObjectUtil.create(container,
									containmentFeature, newObjectType);
							}
						}
					});

				addUndoInterval(undoInterval);
			}
		}
	}
	
	/**
	 * This action provides is used to allow modification of a specific EObject by
	 *  bringing up a properties dialog. The appropriate MSL write action is opened
	 *  to allow the modifications to be propagated to the resource(s).
	 * 
	 * @author cmcgee
	 */
	public static class MSLEditAction implements IEditorActionDelegate, IActionDelegate2 {
		EObject eObj = null;
		MSLLibraryActionBarContributor actionBarContrib;
		
		public void setSelectedObject(EObject eObject) {
			this.eObj = eObject;
		}

		public void setActiveEditor(IAction action, IEditorPart targetEditor) {
			if (targetEditor != null) {
				actionBarContrib = (MSLLibraryActionBarContributor)((MSLLibraryEditor)targetEditor).getActionBarContributor();
			}
		}

		public void run(IAction action) {
			if (eObj == null) {
				return;
			}
			
			final PropertySheetDialog dialog = new PropertySheetDialog(actionBarContrib.getShell(),actionBarContrib.getContentAdapter(),eObj);
			
			MUndoInterval undoInterval = actionBarContrib.execute(NLS.bind(
				EDIT_ACTION_UNDO_MESSAGE, new Object[] {EObjectUtil
					.getName(eObj)}), new Runnable() {

				public void run() {
					dialog.open();
				}
			});
			
			if (dialog.getReturnCode() == PropertySheetDialog.CANCEL) {
				undoInterval.undo();
			} else {
				actionBarContrib.addUndoInterval(undoInterval);
			}
		}

		public void selectionChanged(IAction action, ISelection selection) {
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structSel = (IStructuredSelection)selection;
				
				if (structSel.size() == 1) {
					Object o = structSel.getFirstElement();
					
					if (o instanceof EObject) {
						setSelectedObject((EObject)o);
						action.setEnabled(true);
						return;
					}
				}
			}
			
			action.setEnabled(false);
		}

		public void init(IAction action) {
			// No-op
		}

		public void dispose() {
			// No-op
		}

		public void runWithEvent(IAction action, Event event) {
			run(action);
		}
  	}
	
	/**
	 * This action delegate refactors the selected element into a seaparate
	 * controlled unit.
	 */
	public static class ControlUnitDelegate
		implements IEditorActionDelegate, IActionDelegate2 {

		/**
		 * Error message to display when an exception occured
		 */
		protected static final String MESSAGE_EXCEPTION = MSLExampleMessages.message_exception;

		/**
		 * The shell this action is hosted in
		 */
		protected Shell shell = null;

		/**
		 * The active editor
		 */
		protected MSLLibraryEditor editor = null;

		/**
		 * Selected EObjects
		 */
		protected List selectedEObjects = null;

		/**
		 * The title for the action.
		 */
		private String title = MSLExampleMessages.ControlUnitAction_label;
		
		/** Action bar contributor of the MSL Library example editor. */
		private MSLLibraryActionBarContributor actionBarContrib;
		
		public void selectionChanged(IAction action, final ISelection selection) {
			this.selectedEObjects = null;
			try {
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
					this.selectedEObjects = structuredSelection.toList();
				}
			} catch (Exception e) {
				// Exceptions are not expected
				MessageDialog.openInformation(shell, title, MESSAGE_EXCEPTION);
				throw new RuntimeException(e);
			} finally {
				if ((null != selectedEObjects) && (selectedEObjects.size() == 1)
						&& (selectedEObjects.get(0) instanceof EObject)) {
					EObject selected = (EObject) selectedEObjects.get(0);
					final MEditingDomain domain = MEditingDomain.getEditingDomain(
						selected.eResource());
					ILogicalResource res = domain.asLogicalResource(
						selected.eResource());
					
					action.setEnabled(res.canSeparate(selected));
				} else {
					action.setEnabled(false);
				}
			}
		}

		public void dispose() {
			//No-op
		}

		public void setActiveEditor(IAction action, IEditorPart targetEditor) {
			this.editor = (MSLLibraryEditor) targetEditor;
			if ( targetEditor != null ) {
				this.shell = targetEditor.getSite().getShell();
				actionBarContrib = (MSLLibraryActionBarContributor)((MSLLibraryEditor)targetEditor).getActionBarContributor();
			} else {
				actionBarContrib = null;
			}
		}

		public void init(IAction action) {
			// No-op
		}

		public void runWithEvent(IAction action, Event event) {
			run(action);
		}

		public void run(final IAction action) {
			final EObject selected = (EObject) selectedEObjects.get(0);
			final MEditingDomain domain = MEditingDomain.getEditingDomain(
				selected.eResource());
			final ILogicalResource res = domain.asLogicalResource(
				selected.eResource());
			
			final Exception[] caught = new Exception[1];
			
			MUndoInterval undo = domain.runInUndoInterval(title, new Runnable() {
				public void run() {
					try {
						domain.runAsWrite(new MRunnable() {
							
							public Object run() {
								try {
									res.separate(selected, null);
									action.setEnabled(false);
								} catch (CannotSeparateException e) {
									caught[0] = e;
									MessageDialog.openError(
										shell,
										title,
										e.getLocalizedMessage());
								}
								return null;
							}});
					} catch (MSLActionAbandonedException e) {
						caught[0] = e;
						Log.log(MSLExamplePlugin.getDefault(), e.getStatus());
					}
				}});
			
			if (caught[0] != null) {
				undo.undo();
			} else {
				actionBarContrib.addUndoInterval(undo);
			}
		}
	}
	
	/**
	 * This action delegate refactors the selected element into a seaparate
	 * controlled unit.
	 */
	public static class UncontrolUnitDelegate
		implements IEditorActionDelegate, IActionDelegate2 {

		/**
		 * Error message to display when an exception occured
		 */
		protected static final String MESSAGE_EXCEPTION = MSLExampleMessages.message_exception;

		/**
		 * The shell this action is hosted in
		 */
		protected Shell shell = null;

		/**
		 * The active editor
		 */
		protected MSLLibraryEditor editor = null;

		/**
		 * Selected EObjects
		 */
		protected List selectedEObjects = null;

		/**
		 * The title for the action.
		 */
		private String title = MSLExampleMessages.UncontrolUnitAction_label;
		
		/** Action bar contributor of the MSL Library example editor. */
		private MSLLibraryActionBarContributor actionBarContrib;
		
		public void selectionChanged(IAction action, final ISelection selection) {
			this.selectedEObjects = null;
			try {
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
					this.selectedEObjects = structuredSelection.toList();
				}
			} catch (Exception e) {
				// Exceptions are not expected
				MessageDialog.openInformation(shell, title, MESSAGE_EXCEPTION);
				throw new RuntimeException(e);
			} finally {
				if ((null != selectedEObjects) && (selectedEObjects.size() == 1)
						&& (selectedEObjects.get(0) instanceof EObject)) {
					EObject selected = (EObject) selectedEObjects.get(0);
					final MEditingDomain domain = MEditingDomain.getEditingDomain(
						selected.eResource());
					ILogicalResource res = domain.asLogicalResource(
						selected.eResource());
					
					action.setEnabled(res.isSeparate(selected));
				} else {
					action.setEnabled(false);
				}
			}
		}

		public void dispose() {
			//No-op
		}

		public void setActiveEditor(IAction action, IEditorPart targetEditor) {
			this.editor = (MSLLibraryEditor) targetEditor;
			if ( targetEditor != null ) {
				this.shell = targetEditor.getSite().getShell();
				actionBarContrib = (MSLLibraryActionBarContributor)((MSLLibraryEditor)targetEditor).getActionBarContributor();
			} else {
				actionBarContrib = null;
			}
		}

		public void init(IAction action) {
			// No-op
		}

		public void runWithEvent(IAction action, Event event) {
			run(action);
		}

		public void run(final IAction action) {
			final EObject selected = (EObject) selectedEObjects.get(0);
			final MEditingDomain domain = MEditingDomain.getEditingDomain(
				selected.eResource());
			final ILogicalResource res = domain.asLogicalResource(
				selected.eResource());
			
			final Exception[] caught = new Exception[1];
			
			MUndoInterval undo = domain.runInUndoInterval(title, new Runnable() {
				public void run() {
					try {
						domain.runAsWrite(new MRunnable() {
							
							public Object run() {
								try {
									res.absorb(selected);
									action.setEnabled(false);
								} catch (CannotAbsorbException e) {
									caught[0] = e;
									MessageDialog.openError(
										shell,
										title,
										e.getLocalizedMessage());
								}
								return null;
							}});
					} catch (MSLActionAbandonedException e) {
						caught[0] = e;
						Log.log(MSLExamplePlugin.getDefault(), e.getStatus());
					}
				}});
			
			if (caught[0] != null) {
				undo.undo();
			} else {
				actionBarContrib.addUndoInterval(undo);
			}
		}
	}
	
	private class MSLDeleteAction extends DeleteAction {
		private Collection objects;
		
		public Command createCommand(Collection selection) {
			return UnexecutableCommand.INSTANCE;
		}

		public boolean updateSelection(IStructuredSelection selection) {
			objects = selection.toList();
			
			return true;
		}

		public void run() {
			addUndoInterval(execute(DELETE_ACTION_UNDO_MESSAGE,new Runnable() {
				public void run() {
					for (Iterator i = objects.iterator(); i.hasNext();) {
						Object o = i.next();
						if (o instanceof EObject) {
							EObjectUtil.destroy((EObject)o);
						}
					}
				}
			}));
		}
	}
	
	private class MSLCutAction extends CutAction {
		private Collection objects;
		public Command createCommand(Collection selection) {
			return UnexecutableCommand.INSTANCE;
		}

		public boolean updateSelection(IStructuredSelection selection) {
			objects = selection.toList();
			
			return true;
		}

		public void run() {
			Set objectsToCut = new HashSet();
			for (Iterator i = objects.iterator(); i.hasNext();) {
				Object o = i.next();
				if (o instanceof EObject) {
					objectsToCut.add(o);
				}
			}
			
			clipboardString = EObjectUtil.serialize(objectsToCut,Collections.EMPTY_MAP);
			deleteAction.run();
		}
	}
	
	private class MSLCopyAction extends CopyAction {
		private Collection objects;
		public Command createCommand(Collection selection) {
			return UnexecutableCommand.INSTANCE;
		}

		public boolean updateSelection(IStructuredSelection selection) {
			objects = selection.toList();
			
			return true;
		}

		public void run() {
			Set objectsToCopy = new HashSet();
			for (Iterator i = objects.iterator(); i.hasNext();) {
				Object o = i.next();
				if (o instanceof EObject) {
					objectsToCopy.add(o);
				}
			}
			
			clipboardString = EObjectUtil.serialize(objectsToCopy,Collections.EMPTY_MAP);
		}
	}
	
	private class MSLPasteAction extends PasteAction {
		private EObject eObj;
		
		public Command createCommand(Collection selection) {
			return UnexecutableCommand.INSTANCE;
		}

		public boolean updateSelection(IStructuredSelection selection) {
			eObj = extractEObjectFromSelection(selection);
			
			return eObj != null && clipboardString != null;
		}

		public void run() {
			addUndoInterval(execute(NLS.bind(PASTE_ACTION_UNDO_MESSAGE, new Object[] {EObjectUtil.getName(eObj)}),new Runnable() {
				public void run() {
					EObjectUtil.deserialize(eObj,clipboardString,Collections.EMPTY_MAP);
				}
			}));
		}
	}
	
	private class MSLUndoAction extends UndoAction {

		public Command createCommand(Collection selection) {
			return UnexecutableCommand.INSTANCE;
		}

		public void update() {
			if (pointer > -1) {
				MUndoInterval interval = (MUndoInterval)undoIntervals.get(pointer);
				setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Undo_menu_item", new Object [] { interval.getLabel() })); //$NON-NLS-1$
				setEnabled(interval.canUndo());
			} else {
				setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Undo_menu_item", new Object [] { "" })); //$NON-NLS-1$ //$NON-NLS-2$
				setEnabled(false);
			}
		}

		public void run() {
			if (pointer > -1) {
				MUndoInterval interval = (MUndoInterval)undoIntervals.get(pointer);
				interval.undo();
				pointer--;
				MSLLibraryActionBarContributor.this.update();
			}
		}
	}
	
	private class MSLRedoAction extends RedoAction {
		public Command createCommand(Collection selection) {
			return UnexecutableCommand.INSTANCE;
		}

		public void update() {
			if (pointer >= -1 && undoIntervals.size() > pointer+1) {
				MUndoInterval interval = (MUndoInterval)undoIntervals.get(pointer+1);
				setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item", new Object [] { interval.getLabel() })); //$NON-NLS-1$
				setEnabled(interval.canRedo());
			} else {
				setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Redo_menu_item", new Object [] { "" }));  //$NON-NLS-1$//$NON-NLS-2$
				setEnabled(false);
			}
		}

		public void run() {
			if (pointer >= -1 && undoIntervals.size() > pointer+1) {
				MUndoInterval interval = (MUndoInterval)undoIntervals.get(pointer+1);
				interval.redo();
				pointer++;
				MSLLibraryActionBarContributor.this.update();
			}
		}
	}
	
	/**
	 * Executes the given runnable in an undo interval with a write action.
	 *  The provided label is used to apply to the undo interval. The undo
	 *  interval is returned after the runnable has been executed.
	 * 
	 * @param label The user-visible label for the undo interval.
	 * @param r The code to execute in the undo interval.
	 * 
	 * @return The undo interval that the code executed in.
	 */
	private MUndoInterval execute(String label, final Runnable r) {
		ValidationDelegateClientSelector.running = true;
		try {
			return getEditingDomain().runInUndoInterval(label ,new Runnable() {
				public void run() {
					try {
						getEditingDomain().runAsWrite(new MRunnable() {
							public Object run() {
								r.run();
								return null;
							}
						});
					} catch (MSLActionAbandonedException e) {
						Dialog dialog = new ValidationErrorDialog(getShell(),ERROR_LIVE_VALIDATION_TITLE,e.getStatus());
						dialog.open();
					}
				}
			});
		} finally {
			ValidationDelegateClientSelector.running = false;
		}
	}

	private AdapterFactoryContentProvider getContentAdapter() {
		MSLLibraryEditor editor = (MSLLibraryEditor)getActiveEditor();
		return new AdapterFactoryContentProvider(editor.getAdapterFactory());
	}
	
	private MEditingDomain getEditingDomain() {
		if (editingDomain == null) {
			editingDomain = (MEditingDomain)((IEditingDomainProvider)getActiveEditor()).getEditingDomain();
		}
		
		return editingDomain;
	}

	private Shell getShell() {
		return MSLLibraryActionBarContributor.this.getPage().getActivePart().getSite().getShell();
	}
	
	/**
	 * Adds the given undo interval to our internal undo stack if the interval is
	 *  actually undoable. If the undo interval is not undoable then the stack is
	 *  cleared.
	 * 
	 * @param undoInterval The most recently executed undo interval.
	 */
	private void addUndoInterval(MUndoInterval undoInterval) {
		if (undoInterval.canUndo() && !undoInterval.isEmpty()) {
			while (pointer != undoIntervals.size() -1) {
				undoIntervals.remove(pointer+1);
			}
			undoIntervals.add(undoInterval);
			pointer++;
		} else {
			undoIntervals.clear();
			pointer = -1;
		}
		
		// We need to update some of the actions whenever an undo interval has closed.
		update();
	}
	
	// TODO Remove this!
	/*public void menuAboutToShow(IMenuManager menuManager) {
		super.menuAboutToShow(menuManager);

		menuManager.insertAfter("edit", new ActionContributionItem(editAction)); //$NON-NLS-1$
		
		menuManager.
		
		IMenuManager mslMenu = menuManager.findMenuUsingPath("org.eclipse.gmf.examples.runtime.emf.mslMenu");
		mslMenu.insertAfter("additions",new ActionContributionItem(editAction));
	}*/
	
	public void selectionChanged(SelectionChangedEvent event) {
		if (resourceLoadListener == null) {
			resourceLoadListener = new MListener(getEditingDomain(), MFilter.RESOURCE_UNLOADED_FILTER) {
				public void onEvent(List events) {
					// We need to clear out the undo intervals after resources
					//  are unloaded because they will all be invalidated and made
					//  non-undoable.
					undoIntervals.clear();
					pointer = -1;
					update();
				}
			};
			resourceLoadListener.startListening();
		}
		
		// Remove any menu items for old selection.
		//
		if (createChildMenuManager != null) {
			depopulateManager(createChildMenuManager, createChildActions);
		}
		if (createSiblingMenuManager != null) {
			depopulateManager(createSiblingMenuManager, createSiblingActions);
		}

		ISelection selection = event.getSelection();
		EObject eObj = extractEObjectFromSelection(selection);
		
		// Gather the appropriate actions to include in the
		//  Create Child and Create Sibling menus.
		if (eObj != null) {
			createChildActions = generateCreateChildActions(eObj);
			createSiblingActions = generateCreateSiblingActions(eObj);
			
			// TODO REMOVE!
			//editAction.setSelectedObject(eObj);
			//editAction.setEnabled(true);
		} else {
			createChildActions = Collections.EMPTY_SET;
			createSiblingActions = Collections.EMPTY_SET;
			// TODO REMOVE!
			//editAction.setEnabled(false);
		}

		if (createChildMenuManager != null) {
			populateManager(createChildMenuManager, createChildActions, null);
			createChildMenuManager.update(true);
		}
		if (createSiblingMenuManager != null) {
			populateManager(createSiblingMenuManager, createSiblingActions,
				null);
			createSiblingMenuManager.update(true);
		}
	}

	private EObject extractEObjectFromSelection(ISelection selection) {
		if ((selection instanceof IStructuredSelection) &&
				((IStructuredSelection)selection).size() == 1) {
			
			Object o = ((IStructuredSelection)selection).getFirstElement();

			if (!(o instanceof Resource) &&
					!(o instanceof ResourceSet)) {
				EObject eObj = (EObject)o;
				
				return eObj;
			}
		}
		
		return null;
	}

	/**
	 * Generates a collection of actions that can be used to create children
	 *  of different types (EClasses) in the provided EObject.
	 * 
	 * @param eObj A parent EObject.
	 * @return A collection of Action objects to create children of that object.
	 */
	protected Collection generateCreateChildActions(EObject eObj) {
		Collection actions = new ArrayList();
		EClass containerEClass = eObj.eClass();
		
		for (Iterator i = containerEClass.getEPackage().getEClassifiers()
			.iterator(); i.hasNext();) {
			EClassifier classifier = (EClassifier)i.next();
			
			if (classifier instanceof EClass) {
				EClass eClass = (EClass)classifier;
				EReference feature = MetaModelUtil.findFeature(containerEClass,eClass);
				
				if (!eClass.isAbstract() &&
						feature != null) {
					actions.add(new MSLCreateAction(eObj,feature,(EClass)classifier));
				}
			}
		}
		return actions;
	}

	/**
	 * Generates a collection of actions that can be used to create siblings
	 *  of different types (EClasses) in the provided EObject.
	 * 
	 * @param eObj A parent EObject.
	 * @return A collection of Action objects to create siblings of that object.
	 */
	protected Collection generateCreateSiblingActions(EObject eObj) {
		Collection actions = new ArrayList();
		
		if (eObj.eContainer() == null) {
			return actions;
		}
		
		EClass containerEClass = eObj.eContainer().eClass();
		
		for (Iterator i = containerEClass.getEPackage().getEClassifiers()
				.iterator(); i.hasNext();) {
			EClassifier classifier = (EClassifier)i.next();
			
			if (classifier instanceof EClass) {
				EClass eClass = (EClass)classifier;
				EReference feature = MetaModelUtil.findFeature(containerEClass,eClass);
				
				if (!eClass.isAbstract() &&
						feature != null) {
					actions.add(new MSLCreateAction(eObj.eContainer(),feature,(EClass)classifier));
				}
			}
		}
		return actions;
	}

	public void init(IActionBars actionBars) {
		super.init(actionBars);

		ISharedImages sharedImages = PlatformUI.getWorkbench()
			.getSharedImages();

		// We will override all of these default actions provided
		//  by the EMF reflective editor with MSL versions.
		deleteAction = new MSLDeleteAction();
		deleteAction.setImageDescriptor(sharedImages
			.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
			deleteAction);

		cutAction = new MSLCutAction() ;
		cutAction.setImageDescriptor(sharedImages
			.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);

		copyAction = new MSLCopyAction();
		copyAction.setImageDescriptor(sharedImages
			.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
			copyAction);

		pasteAction = new MSLPasteAction();
		pasteAction.setImageDescriptor(sharedImages
			.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
			pasteAction);

		undoAction = new MSLUndoAction();
		undoAction.setImageDescriptor(sharedImages
			.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
			undoAction);

		redoAction = new MSLRedoAction() ;
		redoAction.setImageDescriptor(sharedImages
			.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
			redoAction);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorActionBarContributor#dispose()
	 */
	public void dispose() {
		super.dispose();
		if (resourceLoadListener != null) {
			resourceLoadListener.stopListening();
			resourceLoadListener = null;
		}
	}

	/*
	 * @see org.eclipse.emf.examples.extlibrary.presentation.EXTLibraryActionBarContributor#contributeToMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void contributeToMenu(IMenuManager menuManager) {
		IMenuManager submenuManager = new MenuManager(MSLExampleMessages.MSLLibraryEditor_menu, "org.eclipse.gmf.examples.runtime.emf.extlibraryMenuID"); //$NON-NLS-1$
		menuManager.insertAfter("additions", submenuManager); //$NON-NLS-1$
		submenuManager.add(new Separator("settings")); //$NON-NLS-1$
		submenuManager.add(new Separator("actions")); //$NON-NLS-1$
		submenuManager.add(new Separator("additions")); //$NON-NLS-1$
		submenuManager.add(new Separator("additions-end")); //$NON-NLS-1$

		// Prepare for CreateChild item addition or removal.
		//
		createChildMenuManager = new MenuManager(EXTLibraryEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item")); //$NON-NLS-1$
		submenuManager.insertBefore("additions", createChildMenuManager); //$NON-NLS-1$

		// Prepare for CreateSibling item addition or removal.
		//
		createSiblingMenuManager = new MenuManager(EXTLibraryEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item")); //$NON-NLS-1$
		submenuManager.insertBefore("additions", createSiblingMenuManager); //$NON-NLS-1$

		// Force an update because Eclipse hides empty menus now.
		//
		submenuManager.addMenuListener
			(new IMenuListener() {
				 public void menuAboutToShow(IMenuManager inputMenuManager) {
				 	inputMenuManager.updateAll(true);
				 }
			 });

		addGlobalActions(submenuManager);
	}
}