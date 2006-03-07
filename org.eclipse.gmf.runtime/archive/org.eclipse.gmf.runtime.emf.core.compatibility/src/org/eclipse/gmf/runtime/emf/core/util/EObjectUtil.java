/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.emf.core.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.emf.clipboard.core.ClipboardUtil;
import org.eclipse.gmf.runtime.emf.core.edit.MEditingDomain;
import org.eclipse.gmf.runtime.emf.core.edit.MObjectState;
import org.eclipse.gmf.runtime.emf.core.edit.MObjectType;
import org.eclipse.gmf.runtime.emf.core.edit.MRunnable;
import org.eclipse.gmf.runtime.emf.core.exceptions.MSLRuntimeException;
import org.eclipse.gmf.runtime.emf.core.internal.domain.MSLEditingDomain;
import org.eclipse.gmf.runtime.emf.core.internal.index.ReferenceVisitor;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLDebugOptions;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLPlugin;
import org.eclipse.gmf.runtime.emf.core.internal.plugin.MSLStatusCodes;
import org.eclipse.gmf.runtime.emf.core.internal.resources.MResource;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLConstants;
import org.eclipse.gmf.runtime.emf.core.internal.util.MSLUtil;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * This class contains a set of <code>EObject</code> related utility methods.
 * 
 * @author rafikj
 * 
 * @deprecated See individual method documentation for mappings.
 */
public class EObjectUtil {

	/**
	 * Creates an <code>EObject</code> given its <code>EClass</code>. The
	 * object is detached.
	 * 
	 * @param eClass
	 *            The <code>EClass</code>.
	 * @return The new <code>EObject</code>.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#create(EObject, EReference, EClass)}
	 *    method, instead.
	 */
	public static EObject create(EClass eClass) {
		return MSLUtil.create(MEditingDomain.INSTANCE, eClass, true);
	}

	/**
	 * Creates an object at a given containment reference whose
	 * <code>EClass</code> is eClass.
	 * 
	 * @param container
	 *            The container of the new object.
	 * @param reference
	 *            The feature containing the object.
	 * @param eClass
	 *            The <code>EClass</code>.
	 * @return The new <code>EObject</code>.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#create(EObject, EReference, EClass)}
	 *    method, instead.
	 */
	public static EObject create(EObject container, EReference reference,
			EClass eClass) {

		EObject eObject = null;

		Resource resource = container.eResource();

		TransactionalEditingDomain domain = null;

		if ((resource != null) && (resource.getResourceSet() != null)) {
			domain = TransactionUtil.getEditingDomain(resource);
		}
		
		if (domain == null) {
			domain = MEditingDomain.INSTANCE;
		}
		
		if ((domain instanceof MEditingDomain) && (resource instanceof MResource)) {

			eObject = ((MResource) resource).getHelper().create(
				(MEditingDomain) domain,
				eClass);

			MSLUtil.sendCreateEvent(domain, eObject);

		} else {
			eObject = MSLUtil.create(domain, eClass, true);
		}
		
		if (reference.isMany()) {
			((Collection) container.eGet(reference)).add(eObject);
		} else {
			container.eSet(reference, eObject);
		}
		
		return eObject;
	}

	/**
	 * Creates an object at a given containment reference whose
	 * <code>EClass</code> is eClass and assigns it a name.
	 * 
	 * @param container
	 *            The container of the new object.
	 * @param reference
	 *            The feature containing the object.
	 * @param eClass
	 *            the <code>EClass</code>.
	 * @param name
	 *            The name.
	 * @return The new <code>EObject</code>.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#create(EObject, EReference, EClass)}
	 *    method, instead, and then
	 *    {@link EMFCoreUtil#setName(EObject, String) set its name}.
	 */
	public static EObject create(EObject container, EReference reference,
			EClass eClass, String name) {

		EObject eObject = create(container, reference, eClass);

		setName(eObject, name);

		return eObject;
	}

	/**
	 * Detaches the object from its container.
	 * 
	 * @param eObject
	 *            The object to detach.
	 */
	public static void detach(EObject eObject) {

		EObject container = eObject.eContainer();

		if (container != null) {

			EReference reference = eObject.eContainmentFeature();

			if (reference.isMany())
				((Collection) container.eGet(reference)).remove(eObject);
			else
				container.eSet(reference, null);
		}
	}

	/**
	 * Destroys the object by removing it from its container and severing all
	 * references from and to the object. it is also recursive, i.e., it gets
	 * called on the children. Calling destroy on an already detached object
	 * will do nothing.
	 * 
	 * @param eObject
	 *            The object to destroy.
	 *            
	 * @deprecated Use the {@link DestroyElementCommand#destroy(EObject)} API,
	 *     instead.
	 */
	public static void destroy(EObject eObject) {

		EObject container = eObject.eContainer();

		if (container != null) {

			Resource resource = container.eResource();

			TransactionalEditingDomain domain = null;

			if (resource != null) {
				domain = TransactionUtil.getEditingDomain(resource);
			}
			
			if (domain == null)
				domain = MEditingDomain.INSTANCE;

			DestroyElementRequest destroy = new DestroyElementRequest(
					domain,
					eObject,
					false);
			
			IElementType context = ElementTypeRegistry.getInstance().getElementType(
					destroy.getEditHelperContext());
			ICommand command = context.getEditCommand(destroy);
		
			if (command != null) {
				try {
					command.execute(null, null); // TODO: progress monitor?
				} catch (ExecutionException e) {
					Log.warning(MSLPlugin.getDefault(),
							MSLStatusCodes.IGNORED_EXCEPTION_WARNING,
							e.getLocalizedMessage(),
							e);
				}
			}
		}
	}

	/**
	 * Gets the object's state which could be either: <code>CLOSED</code>:
	 * the object is a proxy. <code>OPEN</code>: the object belongs to a
	 * resource. <code>DETACHED</code>: the object does not belong to a
	 * resource.
	 * 
	 * @param eObject
	 *            The object.
	 * @return The object's state.
	 * 
	 * @deprecated Determine the object's state directly as indicated in the
	 *     {@link MObjectState} documentation.
	 */
	public static MObjectState getState(EObject eObject) {

		if (eObject.eIsProxy())
			return MObjectState.CLOSED;

		else if (eObject.eResource() == null)
			return MObjectState.DETACHED;

		else
			return MObjectState.OPEN;
	}

	/**
	 * Gets the object’s type which could be either: <code>MODELING</code>:
	 * the object is a modeling object.
	 * 
	 * @param eObject
	 *            The object.
	 * @return The object's type.
	 * 
	 * @deprecated The "object type" concept is obsolete, as described in the
	 *     {@link MObjectType} documentation.
	 */
	public static MObjectType getType(EObject eObject) {

		Resource resource = eObject.eResource();

		if (resource instanceof MResource)
			return ((MResource) resource).getHelper().getType();

		return MObjectType.MODELING;
	}

	/**
	 * Gets the ID of an object. the object must be part of a
	 * <code>XMLResource</code> that supports IDs.
	 * 
	 * @param eObject
	 *            The object.
	 * @return The ID of the object.
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.ecore.xmi.XMLResource#getID(EObject)}
	 *     API, instead.
	 */
	public static String getID(EObject eObject) {

		if (eObject.eIsProxy())
			return ProxyUtil.getProxyID(eObject);

		Resource resource = eObject.eResource();

		String id = null;

		if (resource instanceof MResource)
			id = ((MResource) resource).getHelper().getID(eObject);
		else
			id = MSLUtil.getID(eObject);

		if (id != null)
			return id;
		else
			return MSLConstants.EMPTY_STRING;
	}

	/**
	 * Sets the ID of an object. Object IDs are automatically generated when
	 * objects are created. This method should be called only to modify the ID
	 * of an object. the object must be part of a <code>XMLResource</code>
	 * that supports IDs.
	 * 
	 * @param eObject
	 *            The object.
	 * @param id
	 *            The new object's ID.
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.ecore.xmi.XMLResource#setID(EObject, String)}
	 *     API, instead.
	 */
	public static void setID(EObject eObject, String id) {

		Resource resource = eObject.eResource();

		if (resource != null) {

			if (resource instanceof MResource)
				((MResource) resource).getHelper().setID(eObject, id);
			else
				MSLUtil.setID(eObject, id);
		}
	}

	/**
	 * Gets the name of an object if the object has name, returns an empty
	 * string otherwise.
	 * 
	 * @param eObject
	 *            The object.
	 * @return The object's name.
	 * 
	 * @deprecated Use {@link EMFCoreUtil#getName(EObject)}, instead.
	 */
	public static String getName(EObject eObject) {
		return EMFCoreUtil.getName(eObject);
	}

	/**
	 * Sets the name of an object if the object can be assigned a name.
	 * 
	 * @param eObject
	 *            The object.
	 * @param name
	 *            The object's new name.
	 * @return True if the name was set.
	 * 
	 * @deprecated Use {@link EMFCoreUtil#setName(EObject, String)}, instead.
	 */
	public static boolean setName(EObject eObject, String name) {
		EMFCoreUtil.setName(eObject, name);
		return true;
	}

	/**
	 * Gets the fully qualified name of an object.
	 * 
	 * @param eObject
	 *            The object.
	 * @param formatted
	 *            if True, unnamed parents will be listed using their meta-class
	 *            name.
	 * @return The object's qualified name.
	 * 
	 * @deprecated Use {@link EMFCoreUtil#getQualifiedName(EObject, boolean)}, instead.
	 */
	public static String getQName(EObject eObject, boolean formatted) {
		return EMFCoreUtil.getQualifiedName(eObject, formatted);
	}

	/**
	 * Gets the file of the eObject if any.
	 * 
	 * @param eObject
	 *            The eObject
	 * @return The file path of the eObject's resource (or null if no resource)
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.workspace.util.WorkspaceSynchronizer#getFile(Resource)}
	 *     API, instead.
	 */
	public static IFile getWorkspaceFile(EObject eObject) {

		Resource resource = eObject.eResource();

		if (resource != null)
			return ResourceUtil.getFile(resource);

		return null;
	}

	/**
	 * Checks if an object is modifiable. An object is modifiable only if its
	 * resource is modifiable or the object does not belong to any resource.
	 * 
	 * @param eObject
	 *            The object.
	 * @return True if object is modifiable.
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.workspace.util.WorkspaceSynchronizer#getFile(Resource)}
	 *      API and determine whether the file is writable
	 */
	public static boolean isModifiable(EObject eObject) {

		Resource resource = eObject.eResource();

		if (resource != null)
			return ResourceUtil.isModifiable(resource);

		return true;
	}

	/**
	 * Gets the first container with the specified EClass.
	 * 
	 * @param eObject
	 *            The <code>EObject</code>.
	 * @param eClass
	 *            The eClass.
	 * @return The container.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getContainer(EObject, EClass)} method,
	 *    instead
	 */
	public static EObject getContainer(EObject eObject, EClass eClass) {

		for (EObject parent = eObject; parent != null; parent = parent
			.eContainer())
			if (eClass.isInstance(parent))
				return parent;

		return null;
	}

	/**
	 * Checks if an object is contained recursively by another object.
	 * 
	 * @param container
	 *            The container.
	 * @param eObject
	 *            The contained.
	 * @return True if contains, false otherwise.
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.ecore.util.EcoreUtil#isAncestor(EObject, EObject)}
	 *      method, instead.
	 */
	public static boolean contains(EObject container, EObject eObject) {

		if (container == eObject)
			return true;
		else if ((container == null) || (eObject == null))
			return false;
		else
			return contains(container, eObject.eContainer());
	}

	/**
	 * Metamorphoses an object. The object will be replaced by the newly created
	 * object of the given type. The contents of the object will moved to the
	 * new object and all references to the object will redirected to the new
	 * object.
	 * 
	 * @param eObject
	 *            The <code>EObject</code>.
	 * @param newClass
	 *            The new <code>EClass</code>.
	 * @return The new metamorphosed object.
	 */
	public static EObject metamorphose(EObject eObject, final EClass newClass) {

		EObject eContainer = eObject.eContainer();
		EClass eClass = eObject.eClass();

		EReference containmentFeature = eObject.eContainmentFeature();
		int containmentPosition = -1;
		List containmentList = null;
		if (containmentFeature != null && containmentFeature.isMany()) {

			containmentList = (List) eContainer.eGet(containmentFeature);
			containmentPosition = containmentList.indexOf(eObject);
		}
		
		Resource resource = eObject.eResource();

		MSLEditingDomain domain = null;

		if (resource != null)
			domain = (MSLEditingDomain) MEditingDomain
				.getEditingDomain(resource);

		if (domain == null)
			domain = (MSLEditingDomain) MEditingDomain.INSTANCE;

		final EObject newObject;

		if (resource instanceof MResource)
			newObject = ((MResource) resource).getHelper().create(domain,
				newClass);
		else
			newObject = domain.create(newClass);

		// The object we will copy from.
		EObject copyObject = ((eContainer != null) && (canContain(eContainer,
			newClass, false))) ? eObject
			: EcoreUtil.copy(eObject);

		// Clone the features.
		{
			List allFeatures = eClass.getEAllStructuralFeatures();
			List allNewFeatures = newClass.getEAllStructuralFeatures();

			Iterator i = allFeatures.iterator();

			while (i.hasNext()) {

				EStructuralFeature feature = (EStructuralFeature) i.next();

				if ((!feature.isChangeable()) || (feature.isDerived()))
					continue;

				if ((feature instanceof EReference)
					&& (((EReference) feature).isContainer()))
					continue;

				// Find the target feature.
				EStructuralFeature newFeature = null;

				if (allNewFeatures.contains(feature))
					newFeature = feature;

				else {

					newFeature = newClass.getEStructuralFeature(feature
						.getName());

					if ((newFeature != null)
						&& (newFeature.getEType() != feature.getEType()))
						newFeature = null;
				}

				// Copy the feature value.
				if (newFeature != null) {

					if (newFeature.isMany()) {

						Object[] list = ((Collection) copyObject.eGet(feature))
							.toArray();

						Collection newList = (Collection) newObject
							.eGet(newFeature);

						for (int j = 0; j < list.length; j++) {

							Object object = list[j];

							newList.add(object);
						}

					} else {

						Object object = copyObject.eGet(feature);

						if (object != null)
							newObject.eSet(newFeature, object);
					}
				}
			}
		}

		// Replace the references to eObject with newObject.
		{
			if (copyObject == eObject) {

				ReferenceVisitor visitor = new ReferenceVisitor(eObject) {

					protected void visitedReferencer(EReference reference,
							EObject referencer) {

						MSLUtil.fixReferences(referencedObject, newClass,
							newObject, reference,
							(EClass) reference.getEType(), referencer);
					}
				};

				visitor.visitReferencers();

				// Replace old object with new object in the container.
				if (containmentFeature != null && containmentFeature.isMany()) {
					destroy(eObject);
					containmentList.add(containmentPosition, newObject);
				} else if (containmentFeature != null) {
					eContainer.eSet(containmentFeature, newObject);
				}
			}
		}

		return newObject;
	}

	/**
	 * Uses a reverse reference map that is maintained by the MSL service to
	 * find all referencers of a particular element. The search can be narrowed
	 * down by passing the list of Reference features to match.
	 * <code>features</code> can be null.
	 * 
	 * @param eObject
	 *            The referenced object.
	 * @param features
	 *            The reference features.
	 * @return The collection of referencers.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getReferencers(EObject, EReference[])}
	 *     method, instead.
	 */
	public static Collection getReferencers(EObject eObject,
			EReference[] features) {

		return EMFCoreUtil.getReferencers(eObject, features);
	}

	/**
	 * Replaces all of the references to <code>referencedElement</code> with
	 * <code>replacement</code>.
	 * 
	 * @param referenced
	 *            the referenced element to be replaced
	 * @param replacement
	 *            the replacement element
	 * @param ignoredObjects
	 *            ignore references in these elements and their children. Can be
	 *            an empty list.
	 */
	public static void replaceReferencesTo(final EObject referenced,
			final EObject replacement, final List ignoredObjects) {

		Resource resource = referenced.eResource();

		MSLEditingDomain domain = null;

		if (resource != null)
			domain = (MSLEditingDomain) MEditingDomain
				.getEditingDomain(resource);

		if (domain == null)
			domain = (MSLEditingDomain) MEditingDomain.INSTANCE;

		ReferenceVisitor visitor = new ReferenceVisitor(referenced) {

			protected void visitedReferencer(EReference reference,
					EObject referencer) {

				MSLUtil.replaceReferences(referenced, replacement,
					ignoredObjects, reference, referencer);
			}
		};

		visitor.visitReferencers();
	}

	/**
	 * Checks if the object can contain an object of type eClass. The check can
	 * be recursive.
	 * 
	 * @param container
	 *            The container.
	 * @param eClass
	 *            The <code>EClass</code>.
	 * @param recursive
	 *            True if recursive.
	 * @return True if an object can contain other objects of a given type.
	 * 
	 * @deprecated Use the {@link MetaModelUtil#canContain(EClass, EClass, boolean)}
	 *     method, instead
	 */
	public static boolean canContain(EObject container, EClass eClass,
			boolean recursive) {
		return MetaModelUtil.canContain(container.eClass(), eClass, recursive);
	}

	/**
	 * Checks if the object can contain an object of type eClass at a given
	 * containment reference. The check can be recursive.
	 * 
	 * @param container
	 *            The container.
	 * @param reference
	 *            The feature.
	 * @param eClass
	 *            The <code>EClass</code>.
	 * @param recursive
	 *            True if recursive.
	 * @return True if an object can contain other objects of a given type.
	 */
	public static boolean canContain(EObject container, EReference reference,
			EClass eClass, boolean recursive) {
		return MetaModelUtil.canContain(container.eClass(), reference, eClass,
			recursive);
	}

	/**
	 * Checks if the object can reference an object of type eClass.
	 * 
	 * @param container
	 *            The reference container.
	 * @param eClass
	 *            The <code>EClass</code>.
	 * @return True if an object can reference other objects of a given type.
	 * 
	 * @deprecated Use the {@link MetaModelUtil#canReference(EClass, EClass)}
	 *     method, instead
	 */
	public static boolean canReference(EObject container, EClass eClass) {
		return MetaModelUtil.canReference(container.eClass(), eClass);
	}

	/**
	 * Checks if the object can reference an object of type eClass at a given
	 * non containment reference.
	 * 
	 * @param container
	 *            The reference container.
	 * @param reference
	 *            The feature.
	 * @param eClass
	 *            The <code>EClass</code>.
	 * @return True if an object can reference other objects of a given type.
	 * 
	 * @deprecated Use the {@link MetaModelUtil#canReference(EClass, EReference, EClass)}
	 *     method, instead
	 */
	public static boolean canReference(EObject container, EReference reference,
			EClass eClass) {
		return MetaModelUtil
			.canReference(container.eClass(), reference, eClass);
	}

	/**
	 * Creates an annotation.
	 * 
	 * @param container
	 *            The annotation container.
	 * @param source
	 *            The annotation source.
	 * @return The new annotation.
	 */
	public static EAnnotation createEAnnotation(EModelElement container,
			String source) {

		EAnnotation annotation = (EAnnotation) create(container,
			EcorePackage.eINSTANCE.getEModelElement_EAnnotations(),
			EcorePackage.eINSTANCE.getEAnnotation());

		annotation.setSource(source);

		return annotation;
	}

	/**
	 * Adds an annotation.
	 * 
	 * @param container
	 *            The annotation container.
	 * @param annotation
	 *            The annotation to add.
	 */
	public static void addEAnnotation(EModelElement container,
			EAnnotation annotation) {
		container.getEAnnotations().add(annotation);
	}

	/**
	 * Removes an annotation.
	 * 
	 * @param container
	 *            The annotation container.
	 * @param source
	 *            The annotation source.
	 * @return The removed annotation.
	 */
	public static EAnnotation detachEAnnotation(EModelElement container,
			String source) {

		EAnnotation annotation = container.getEAnnotation(source);

		if (annotation != null)
			detach(annotation);

		return annotation;
	}

	/**
	 * Destroys an annotation.
	 * 
	 * @param container
	 *            The annotation container.
	 * @param source
	 *            The annotation source.
	 * @return True if an annotation was destroyed..
	 */
	public static boolean destroyEAnnotation(EModelElement container,
			String source) {

		EAnnotation annotation = container.getEAnnotation(source);

		if (annotation != null)
			destroy(annotation);

		return (annotation != null);
	}

	/**
	 * Adds or sets a detail entry given a key/value pair. It returns the
	 * previous value if any.
	 * 
	 * @param annotation
	 *            The annotation.
	 * @param key
	 *            The key.
	 * @param value
	 *            The value.
	 * @return The previous value.
	 */
	public static String putDetail(EAnnotation annotation, String key,
			String value) {

		EMap details = annotation.getDetails();

		String oldValue = (String) details.get(key);

		details.put(key, value);

		return oldValue;
	}

	/**
	 * Gets the detail value given a key.
	 * 
	 * @param annotation
	 *            The annotation.
	 * @param key
	 *            The key.
	 * @return The value.
	 */
	public static String getDetail(EAnnotation annotation, String key) {
		return (String) annotation.getDetails().get(key);
	}

	/**
	 * Copies a collection of objects to a string. Hints are used to customize
	 * the operation.
	 * 
	 * @param objects
	 *            The objects to copy.
	 * @param hints
	 *            Hints used during copy. This an object to string map.
	 * @return The serialized output.
	 * 
	 * @deprecated Use the
	 *     {@link ClipboardUtil#copyElementsToString(Collection, Map, org.eclipse.core.runtime.IProgressMonitor)}
	 *     API, instead.
	 */
	public static String serialize(Collection objects, Map hints) {
		try {
			return ClipboardUtil.copyElementsToString(
				objects, hints, new NullProgressMonitor());
		} catch (Exception ex) {
			handleException(ex, "copySelectionToString"); //$NON-NLS-1$
		}
		return null;
	}

	/**
	 * Pastes a collection of objects from a string. Hints are used to customize
	 * the operation
	 * 
	 * @param target
	 *            The target of the copy.
	 * @param str
	 *            The string to deserialize.
	 * @param hints
	 *            Hints used during paste.
	 * @return The collection of pasted objects.
	 * 
	 * @deprecated Use the
	 *     {@link ClipboardUtil#pasteElementsFromString(String, EObject, Map, org.eclipse.core.runtime.IProgressMonitor)}
	 *     API, instead.
	 */
	public static Collection deserialize(final EObject target, final String str, final Map hints) {
		return (Collection) OperationUtil.runWithNoSemProcs(new MRunnable() {
			public Object run() {
				try {
					return ClipboardUtil.pasteElementsFromString(
						str, target, hints, new NullProgressMonitor());
				} catch (Exception ex) {
					handleException(ex, "deserialize"); //$NON-NLS-1$
				}
				return null;
			}
		});
	}

	/**
	 * Pastes a collection of objects from a string. Hints are used to customize
	 * the operation
	 * 
	 * @param target
	 *            The target of the copy.
	 * @param str
	 *            The string to deserialize.
	 * @param hints
	 *            Hints used during paste.
	 * @return The collection of pasted objects.
	 * 
	 * @deprecated Use the
	 *     {@link ClipboardUtil#pasteElementsFromString(String, Resource, Map, org.eclipse.core.runtime.IProgressMonitor)}
	 *     API, instead.
	 */
	public static Collection deserialize(final Resource target, final String str, final Map hints) {
		return (Collection) OperationUtil.runWithNoSemProcs(new MRunnable() {
			public Object run() {
				try {
					return ClipboardUtil.pasteElementsFromString(
						str, target, hints, new NullProgressMonitor());
				} catch (Exception ex) {
					handleException(ex, "deserialize"); //$NON-NLS-1$
				}
				return null;
			}
		});
	}

	/**
	 * Finds the first common container of a collection of objects.
	 * 
	 * @param objects
	 *            The <code>EObject</code>s.
	 * @param desiredContainerType
	 *            The desired <code>EClass</code> of the container.
	 * @return The least common container.
	 * 
	 * @deprecated Use the {@link EMFCoreUtil#getLeastCommonContainer(Collection, EClass)}
	 *     API, instead.
	 */
	public static EObject getLeastCommonContainer(Collection objects,
			EClass desiredContainerType) {
		return EMFCoreUtil.getLeastCommonContainer(objects, desiredContainerType);
	}

	/**
	 * Gets the string representing the full path for the model for an element.
	 * 
	 * @param eObject
	 *            the element.
	 * @return the model path name.
	 * 
	 * @deprecated Obtain the file path from the <code>eObject</code>'s
	 *      resource's URI, {@link org.eclipse.emf.ecore.resource.URIConverter converting}
	 *      it if necessary
	 */
	public static String getFileString(EObject eObject) {
		return ResourceUtil.getFileString(eObject.eResource());
	}

	/**
	 * Return the file in the workspace for a element.
	 * 
	 * @param eObject
	 *            the element.
	 * @return the file in the workspace for a element.
	 * 
	 * @deprecated Use the {@link org.eclipse.emf.workspace.util.WorkspaceSynchronizer#getFile(Resource)}
	 *      API to get the <code>eObject</code>'s resource's file.
	 */
	public static IFile getFile(EObject eObject) {
		if (eObject.eResource() == null) {
			return null;
		} else {
			return ResourceUtil.getFile(eObject.eResource());
		}
	}

	private EObjectUtil() {
		// private
	}
	
	/**
	 * handles the exception, does tracing ...etc.
	 * 
	 * @param ex
	 *            the exception to hanlde
	 * @param methodname
	 *            the calling method
	 */
	private static void handleException(Exception ex, String methodname) {
		if (ex instanceof OperationCanceledException) {
			Trace.catching(MSLPlugin.getDefault(),
				MSLDebugOptions.EXCEPTIONS_CATCHING, EObjectUtil.class,
				methodname, ex);
		} else {
			Trace.throwing(MSLPlugin.getDefault(),
				MSLDebugOptions.EXCEPTIONS_THROWING, EObjectUtil.class,
				methodname, ex);
			throw (ex instanceof MSLRuntimeException) ? (MSLRuntimeException) ex
				: new MSLRuntimeException("EObjectUtil General Error", ex);//$NON-NLS-1$
		}
	}
}