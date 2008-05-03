/******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.gmf.runtime.notation.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.PropertiesSetStyle;
import org.eclipse.gmf.runtime.notation.PropertyValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Properties Set Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.PropertiesSetStyleImpl#getPropertiesMap <em>Properties Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertiesSetStyleImpl extends NamedStyleImpl implements PropertiesSetStyle {
	/**
	 * The cached value of the '{@link #getPropertiesMap() <em>Properties Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertiesMap()
	 * @generated
	 * @ordered
	 */
	protected EMap propertiesMap;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertiesSetStyleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return NotationPackage.Literals.PROPERTIES_SET_STYLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap getPropertiesMap() {
		if (propertiesMap == null) {
			propertiesMap = new EcoreEMap(NotationPackage.Literals.STRING_TO_PROPERTY_VALUE_MAP_ENTRY, StringToPropertyValueMapEntryImpl.class, this, NotationPackage.PROPERTIES_SET_STYLE__PROPERTIES_MAP);
		}
		return propertiesMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Object getProperty(String propertyName) {
		if (hasProperty(propertyName)) {
			return ((PropertyValue)getPropertiesMap().get(propertyName)).getValue();
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean setProperty(String propertyName, Object newValue) {
		if (hasProperty(propertyName)) {
			((PropertyValue)getPropertiesMap().get(propertyName)).setValue(newValue);
			return true;
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean createProperty(String propertyName, EDataType instanceType, Object initialValue) {
		if (propertyName != null && !hasProperty(propertyName)) {
			PropertyValue value = NotationFactory.eINSTANCE.createPropertyValue();
			if (instanceType == null) {
				if (initialValue instanceof String)
					value.setRawValue((String)initialValue);
				else
					return false;
			} else {
				value.setInstanceType(instanceType);
				value.setValue(initialValue);
			}
			return getPropertiesMap().put(propertyName, value) == null;
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean removeProperty(String propertyName) {
		return getPropertiesMap().removeKey(propertyName) != null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean hasProperty(String propertyName) {
		return getPropertiesMap().containsKey(propertyName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean createProperty(String propertyName, String initialValue) {
		if (!hasProperty(propertyName)) {
			PropertyValue value = NotationFactory.eINSTANCE.createPropertyValue();
			value.setRawValue(initialValue);
			return getPropertiesMap().put(propertyName, value) == null;
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NotationPackage.PROPERTIES_SET_STYLE__PROPERTIES_MAP:
				return ((InternalEList)getPropertiesMap()).basicRemove(otherEnd, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NotationPackage.PROPERTIES_SET_STYLE__UUID:
				return getUUID();
			case NotationPackage.PROPERTIES_SET_STYLE__NAME:
				return getName();
			case NotationPackage.PROPERTIES_SET_STYLE__PROPERTIES_MAP:
				if (coreType) return getPropertiesMap();
				else return getPropertiesMap().map();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NotationPackage.PROPERTIES_SET_STYLE__UUID:
				setUUID((byte[])newValue);
				return;
			case NotationPackage.PROPERTIES_SET_STYLE__NAME:
				setName((String)newValue);
				return;
			case NotationPackage.PROPERTIES_SET_STYLE__PROPERTIES_MAP:
				((EStructuralFeature.Setting)getPropertiesMap()).set(newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case NotationPackage.PROPERTIES_SET_STYLE__UUID:
				setUUID(UUID_EDEFAULT);
				return;
			case NotationPackage.PROPERTIES_SET_STYLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case NotationPackage.PROPERTIES_SET_STYLE__PROPERTIES_MAP:
				getPropertiesMap().clear();
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NotationPackage.PROPERTIES_SET_STYLE__UUID:
				return UUID_EDEFAULT == null ? uUID != null : !UUID_EDEFAULT.equals(uUID);
			case NotationPackage.PROPERTIES_SET_STYLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case NotationPackage.PROPERTIES_SET_STYLE__PROPERTIES_MAP:
				return propertiesMap != null && !propertiesMap.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

} //PropertiesSetStyleImpl
