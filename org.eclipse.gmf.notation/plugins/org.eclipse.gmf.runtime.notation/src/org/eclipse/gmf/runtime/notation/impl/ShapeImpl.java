/******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.gmf.runtime.notation.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmf.runtime.notation.DescriptionStyle;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.LineStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.Style;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Shape</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl#getFontColor <em>Font Color</em>}</li>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl#getFontName <em>Font Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl#getFontHeight <em>Font Height</em>}</li>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl#isBold <em>Bold</em>}</li>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl#isItalic <em>Italic</em>}</li>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl#isUnderline <em>Underline</em>}</li>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl#isStrikeThrough <em>Strike Through</em>}</li>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link org.eclipse.gmf.runtime.notation.impl.ShapeImpl#getLineWidth <em>Line Width</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 1.2
 */
public class ShapeImpl extends NodeImpl implements Shape {
	/**
	 * The default value of the '{@link #getFontColor() <em>Font Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFontColor()
	 * @generated
	 * @ordered
	 */
	protected static final int FONT_COLOR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFontColor() <em>Font Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFontColor()
	 * @generated
	 * @ordered
	 */
	protected int fontColor = FONT_COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getFontName() <em>Font Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFontName()
	 * @generated
	 * @ordered
	 */
	protected static final String FONT_NAME_EDEFAULT = "Tahoma"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getFontName() <em>Font Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFontName()
	 * @generated
	 * @ordered
	 */
	protected String fontName = FONT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFontHeight() <em>Font Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFontHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int FONT_HEIGHT_EDEFAULT = 9;

	/**
	 * The cached value of the '{@link #getFontHeight() <em>Font Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFontHeight()
	 * @generated
	 * @ordered
	 */
	protected int fontHeight = FONT_HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #isBold() <em>Bold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBold()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOLD_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isBold() <em>Bold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBold()
	 * @generated
	 * @ordered
	 */
	protected static final int BOLD_EFLAG = 1 << 11;

	/**
	 * The default value of the '{@link #isItalic() <em>Italic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isItalic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ITALIC_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isItalic() <em>Italic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isItalic()
	 * @generated
	 * @ordered
	 */
	protected static final int ITALIC_EFLAG = 1 << 12;

	/**
	 * The default value of the '{@link #isUnderline() <em>Underline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnderline()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNDERLINE_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isUnderline() <em>Underline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnderline()
	 * @generated
	 * @ordered
	 */
	protected static final int UNDERLINE_EFLAG = 1 << 13;

	/**
	 * The default value of the '{@link #isStrikeThrough() <em>Strike Through</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStrikeThrough()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STRIKE_THROUGH_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isStrikeThrough() <em>Strike Through</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStrikeThrough()
	 * @generated
	 * @ordered
	 */
	protected static final int STRIKE_THROUGH_EFLAG = 1 << 14;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillColor()
	 * @generated
	 * @ordered
	 */
	protected static final int FILL_COLOR_EDEFAULT = 16777215;

	/**
	 * The cached value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillColor()
	 * @generated
	 * @ordered
	 */
	protected int fillColor = FILL_COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineColor()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_COLOR_EDEFAULT = 11579568;

	/**
	 * The cached value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineColor()
	 * @generated
	 * @ordered
	 */
	protected int lineColor = LINE_COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getLineWidth() <em>Line Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_WIDTH_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getLineWidth() <em>Line Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineWidth()
	 * @generated
	 * @ordered
	 */
	protected int lineWidth = LINE_WIDTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShapeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return NotationPackage.Literals.SHAPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFontColor() {
		return fontColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFontColor(int newFontColor) {
		int oldFontColor = fontColor;
		fontColor = newFontColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.SHAPE__FONT_COLOR, oldFontColor, fontColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFontName() {
		return fontName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setFontName(String newFontName) {
		String oldFontName = fontName;
		fontName = newFontName == null ? null : newFontName.intern();
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.SHAPE__FONT_NAME, oldFontName, fontName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFontHeight() {
		return fontHeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFontHeight(int newFontHeight) {
		int oldFontHeight = fontHeight;
		fontHeight = newFontHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.SHAPE__FONT_HEIGHT, oldFontHeight, fontHeight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBold() {
		return (eFlags & BOLD_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBold(boolean newBold) {
		boolean oldBold = (eFlags & BOLD_EFLAG) != 0;
		if (newBold) eFlags |= BOLD_EFLAG; else eFlags &= ~BOLD_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.SHAPE__BOLD, oldBold, newBold));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isItalic() {
		return (eFlags & ITALIC_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setItalic(boolean newItalic) {
		boolean oldItalic = (eFlags & ITALIC_EFLAG) != 0;
		if (newItalic) eFlags |= ITALIC_EFLAG; else eFlags &= ~ITALIC_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.SHAPE__ITALIC, oldItalic, newItalic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnderline() {
		return (eFlags & UNDERLINE_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnderline(boolean newUnderline) {
		boolean oldUnderline = (eFlags & UNDERLINE_EFLAG) != 0;
		if (newUnderline) eFlags |= UNDERLINE_EFLAG; else eFlags &= ~UNDERLINE_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.SHAPE__UNDERLINE, oldUnderline, newUnderline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStrikeThrough() {
		return (eFlags & STRIKE_THROUGH_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrikeThrough(boolean newStrikeThrough) {
		boolean oldStrikeThrough = (eFlags & STRIKE_THROUGH_EFLAG) != 0;
		if (newStrikeThrough) eFlags |= STRIKE_THROUGH_EFLAG; else eFlags &= ~STRIKE_THROUGH_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.SHAPE__STRIKE_THROUGH, oldStrikeThrough, newStrikeThrough));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.SHAPE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFillColor() {
		return fillColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillColor(int newFillColor) {
		int oldFillColor = fillColor;
		fillColor = newFillColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.SHAPE__FILL_COLOR, oldFillColor, fillColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLineColor() {
		return lineColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineColor(int newLineColor) {
		int oldLineColor = lineColor;
		lineColor = newLineColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.SHAPE__LINE_COLOR, oldLineColor, lineColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLineWidth() {
		return lineWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineWidth(int newLineWidth) {
		int oldLineWidth = lineWidth;
		lineWidth = newLineWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.SHAPE__LINE_WIDTH, oldLineWidth, lineWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NotationPackage.SHAPE__EANNOTATIONS:
				return getEAnnotations();
			case NotationPackage.SHAPE__VISIBLE:
				return isVisible() ? Boolean.TRUE : Boolean.FALSE;
			case NotationPackage.SHAPE__TYPE:
				return getType();
			case NotationPackage.SHAPE__MUTABLE:
				return isMutable() ? Boolean.TRUE : Boolean.FALSE;
			case NotationPackage.SHAPE__SOURCE_EDGES:
				return getSourceEdges();
			case NotationPackage.SHAPE__TARGET_EDGES:
				return getTargetEdges();
			case NotationPackage.SHAPE__PERSISTED_CHILDREN:
				return getPersistedChildren();
			case NotationPackage.SHAPE__STYLES:
				return getStyles();
			case NotationPackage.SHAPE__ELEMENT:
				if (resolve) return getElement();
				return basicGetElement();
			case NotationPackage.SHAPE__DIAGRAM:
				if (resolve) return getDiagram();
				return basicGetDiagram();
			case NotationPackage.SHAPE__TRANSIENT_CHILDREN:
				return getTransientChildren();
			case NotationPackage.SHAPE__LAYOUT_CONSTRAINT:
				return getLayoutConstraint();
			case NotationPackage.SHAPE__FONT_COLOR:
				return new Integer(getFontColor());
			case NotationPackage.SHAPE__FONT_NAME:
				return getFontName();
			case NotationPackage.SHAPE__FONT_HEIGHT:
				return new Integer(getFontHeight());
			case NotationPackage.SHAPE__BOLD:
				return isBold() ? Boolean.TRUE : Boolean.FALSE;
			case NotationPackage.SHAPE__ITALIC:
				return isItalic() ? Boolean.TRUE : Boolean.FALSE;
			case NotationPackage.SHAPE__UNDERLINE:
				return isUnderline() ? Boolean.TRUE : Boolean.FALSE;
			case NotationPackage.SHAPE__STRIKE_THROUGH:
				return isStrikeThrough() ? Boolean.TRUE : Boolean.FALSE;
			case NotationPackage.SHAPE__DESCRIPTION:
				return getDescription();
			case NotationPackage.SHAPE__FILL_COLOR:
				return new Integer(getFillColor());
			case NotationPackage.SHAPE__LINE_COLOR:
				return new Integer(getLineColor());
			case NotationPackage.SHAPE__LINE_WIDTH:
				return new Integer(getLineWidth());
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
			case NotationPackage.SHAPE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case NotationPackage.SHAPE__VISIBLE:
				setVisible(((Boolean)newValue).booleanValue());
				return;
			case NotationPackage.SHAPE__TYPE:
				setType((String)newValue);
				return;
			case NotationPackage.SHAPE__MUTABLE:
				setMutable(((Boolean)newValue).booleanValue());
				return;
			case NotationPackage.SHAPE__SOURCE_EDGES:
				getSourceEdges().clear();
				getSourceEdges().addAll((Collection)newValue);
				return;
			case NotationPackage.SHAPE__TARGET_EDGES:
				getTargetEdges().clear();
				getTargetEdges().addAll((Collection)newValue);
				return;
			case NotationPackage.SHAPE__PERSISTED_CHILDREN:
				getPersistedChildren().clear();
				getPersistedChildren().addAll((Collection)newValue);
				return;
			case NotationPackage.SHAPE__STYLES:
				getStyles().clear();
				getStyles().addAll((Collection)newValue);
				return;
			case NotationPackage.SHAPE__ELEMENT:
				setElement((EObject)newValue);
				return;
			case NotationPackage.SHAPE__TRANSIENT_CHILDREN:
				getTransientChildren().clear();
				getTransientChildren().addAll((Collection)newValue);
				return;
			case NotationPackage.SHAPE__LAYOUT_CONSTRAINT:
				setLayoutConstraint((LayoutConstraint)newValue);
				return;
			case NotationPackage.SHAPE__FONT_COLOR:
				setFontColor(((Integer)newValue).intValue());
				return;
			case NotationPackage.SHAPE__FONT_NAME:
				setFontName((String)newValue);
				return;
			case NotationPackage.SHAPE__FONT_HEIGHT:
				setFontHeight(((Integer)newValue).intValue());
				return;
			case NotationPackage.SHAPE__BOLD:
				setBold(((Boolean)newValue).booleanValue());
				return;
			case NotationPackage.SHAPE__ITALIC:
				setItalic(((Boolean)newValue).booleanValue());
				return;
			case NotationPackage.SHAPE__UNDERLINE:
				setUnderline(((Boolean)newValue).booleanValue());
				return;
			case NotationPackage.SHAPE__STRIKE_THROUGH:
				setStrikeThrough(((Boolean)newValue).booleanValue());
				return;
			case NotationPackage.SHAPE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case NotationPackage.SHAPE__FILL_COLOR:
				setFillColor(((Integer)newValue).intValue());
				return;
			case NotationPackage.SHAPE__LINE_COLOR:
				setLineColor(((Integer)newValue).intValue());
				return;
			case NotationPackage.SHAPE__LINE_WIDTH:
				setLineWidth(((Integer)newValue).intValue());
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
			case NotationPackage.SHAPE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case NotationPackage.SHAPE__VISIBLE:
				setVisible(VISIBLE_EDEFAULT);
				return;
			case NotationPackage.SHAPE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case NotationPackage.SHAPE__MUTABLE:
				setMutable(MUTABLE_EDEFAULT);
				return;
			case NotationPackage.SHAPE__SOURCE_EDGES:
				getSourceEdges().clear();
				return;
			case NotationPackage.SHAPE__TARGET_EDGES:
				getTargetEdges().clear();
				return;
			case NotationPackage.SHAPE__PERSISTED_CHILDREN:
				getPersistedChildren().clear();
				return;
			case NotationPackage.SHAPE__STYLES:
				getStyles().clear();
				return;
			case NotationPackage.SHAPE__ELEMENT:
				unsetElement();
				return;
			case NotationPackage.SHAPE__TRANSIENT_CHILDREN:
				getTransientChildren().clear();
				return;
			case NotationPackage.SHAPE__LAYOUT_CONSTRAINT:
				setLayoutConstraint((LayoutConstraint)null);
				return;
			case NotationPackage.SHAPE__FONT_COLOR:
				setFontColor(FONT_COLOR_EDEFAULT);
				return;
			case NotationPackage.SHAPE__FONT_NAME:
				setFontName(FONT_NAME_EDEFAULT);
				return;
			case NotationPackage.SHAPE__FONT_HEIGHT:
				setFontHeight(FONT_HEIGHT_EDEFAULT);
				return;
			case NotationPackage.SHAPE__BOLD:
				setBold(BOLD_EDEFAULT);
				return;
			case NotationPackage.SHAPE__ITALIC:
				setItalic(ITALIC_EDEFAULT);
				return;
			case NotationPackage.SHAPE__UNDERLINE:
				setUnderline(UNDERLINE_EDEFAULT);
				return;
			case NotationPackage.SHAPE__STRIKE_THROUGH:
				setStrikeThrough(STRIKE_THROUGH_EDEFAULT);
				return;
			case NotationPackage.SHAPE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case NotationPackage.SHAPE__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case NotationPackage.SHAPE__LINE_COLOR:
				setLineColor(LINE_COLOR_EDEFAULT);
				return;
			case NotationPackage.SHAPE__LINE_WIDTH:
				setLineWidth(LINE_WIDTH_EDEFAULT);
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
			case NotationPackage.SHAPE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case NotationPackage.SHAPE__VISIBLE:
				return ((eFlags & VISIBLE_EFLAG) != 0) != VISIBLE_EDEFAULT;
			case NotationPackage.SHAPE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case NotationPackage.SHAPE__MUTABLE:
				return ((eFlags & MUTABLE_EFLAG) != 0) != MUTABLE_EDEFAULT;
			case NotationPackage.SHAPE__SOURCE_EDGES:
				return sourceEdges != null && !sourceEdges.isEmpty();
			case NotationPackage.SHAPE__TARGET_EDGES:
				return targetEdges != null && !targetEdges.isEmpty();
			case NotationPackage.SHAPE__PERSISTED_CHILDREN:
				return persistedChildren != null && !persistedChildren.isEmpty();
			case NotationPackage.SHAPE__STYLES:
				return styles != null && !styles.isEmpty();
			case NotationPackage.SHAPE__ELEMENT:
				return isSetElement();
			case NotationPackage.SHAPE__DIAGRAM:
				return basicGetDiagram() != null;
			case NotationPackage.SHAPE__TRANSIENT_CHILDREN:
				return transientChildren != null && !transientChildren.isEmpty();
			case NotationPackage.SHAPE__LAYOUT_CONSTRAINT:
				return layoutConstraint != null;
			case NotationPackage.SHAPE__FONT_COLOR:
				return fontColor != FONT_COLOR_EDEFAULT;
			case NotationPackage.SHAPE__FONT_NAME:
				return FONT_NAME_EDEFAULT == null ? fontName != null : !FONT_NAME_EDEFAULT.equals(fontName);
			case NotationPackage.SHAPE__FONT_HEIGHT:
				return fontHeight != FONT_HEIGHT_EDEFAULT;
			case NotationPackage.SHAPE__BOLD:
				return ((eFlags & BOLD_EFLAG) != 0) != BOLD_EDEFAULT;
			case NotationPackage.SHAPE__ITALIC:
				return ((eFlags & ITALIC_EFLAG) != 0) != ITALIC_EDEFAULT;
			case NotationPackage.SHAPE__UNDERLINE:
				return ((eFlags & UNDERLINE_EFLAG) != 0) != UNDERLINE_EDEFAULT;
			case NotationPackage.SHAPE__STRIKE_THROUGH:
				return ((eFlags & STRIKE_THROUGH_EFLAG) != 0) != STRIKE_THROUGH_EDEFAULT;
			case NotationPackage.SHAPE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case NotationPackage.SHAPE__FILL_COLOR:
				return fillColor != FILL_COLOR_EDEFAULT;
			case NotationPackage.SHAPE__LINE_COLOR:
				return lineColor != LINE_COLOR_EDEFAULT;
			case NotationPackage.SHAPE__LINE_WIDTH:
				return lineWidth != LINE_WIDTH_EDEFAULT;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == Style.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == FontStyle.class) {
			switch (derivedFeatureID) {
				case NotationPackage.SHAPE__FONT_COLOR: return NotationPackage.FONT_STYLE__FONT_COLOR;
				case NotationPackage.SHAPE__FONT_NAME: return NotationPackage.FONT_STYLE__FONT_NAME;
				case NotationPackage.SHAPE__FONT_HEIGHT: return NotationPackage.FONT_STYLE__FONT_HEIGHT;
				case NotationPackage.SHAPE__BOLD: return NotationPackage.FONT_STYLE__BOLD;
				case NotationPackage.SHAPE__ITALIC: return NotationPackage.FONT_STYLE__ITALIC;
				case NotationPackage.SHAPE__UNDERLINE: return NotationPackage.FONT_STYLE__UNDERLINE;
				case NotationPackage.SHAPE__STRIKE_THROUGH: return NotationPackage.FONT_STYLE__STRIKE_THROUGH;
				default: return -1;
			}
		}
		if (baseClass == DescriptionStyle.class) {
			switch (derivedFeatureID) {
				case NotationPackage.SHAPE__DESCRIPTION: return NotationPackage.DESCRIPTION_STYLE__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == FillStyle.class) {
			switch (derivedFeatureID) {
				case NotationPackage.SHAPE__FILL_COLOR: return NotationPackage.FILL_STYLE__FILL_COLOR;
				default: return -1;
			}
		}
		if (baseClass == LineStyle.class) {
			switch (derivedFeatureID) {
				case NotationPackage.SHAPE__LINE_COLOR: return NotationPackage.LINE_STYLE__LINE_COLOR;
				case NotationPackage.SHAPE__LINE_WIDTH: return NotationPackage.LINE_STYLE__LINE_WIDTH;
				default: return -1;
			}
		}
		if (baseClass == ShapeStyle.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == Style.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == FontStyle.class) {
			switch (baseFeatureID) {
				case NotationPackage.FONT_STYLE__FONT_COLOR: return NotationPackage.SHAPE__FONT_COLOR;
				case NotationPackage.FONT_STYLE__FONT_NAME: return NotationPackage.SHAPE__FONT_NAME;
				case NotationPackage.FONT_STYLE__FONT_HEIGHT: return NotationPackage.SHAPE__FONT_HEIGHT;
				case NotationPackage.FONT_STYLE__BOLD: return NotationPackage.SHAPE__BOLD;
				case NotationPackage.FONT_STYLE__ITALIC: return NotationPackage.SHAPE__ITALIC;
				case NotationPackage.FONT_STYLE__UNDERLINE: return NotationPackage.SHAPE__UNDERLINE;
				case NotationPackage.FONT_STYLE__STRIKE_THROUGH: return NotationPackage.SHAPE__STRIKE_THROUGH;
				default: return -1;
			}
		}
		if (baseClass == DescriptionStyle.class) {
			switch (baseFeatureID) {
				case NotationPackage.DESCRIPTION_STYLE__DESCRIPTION: return NotationPackage.SHAPE__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == FillStyle.class) {
			switch (baseFeatureID) {
				case NotationPackage.FILL_STYLE__FILL_COLOR: return NotationPackage.SHAPE__FILL_COLOR;
				default: return -1;
			}
		}
		if (baseClass == LineStyle.class) {
			switch (baseFeatureID) {
				case NotationPackage.LINE_STYLE__LINE_COLOR: return NotationPackage.SHAPE__LINE_COLOR;
				case NotationPackage.LINE_STYLE__LINE_WIDTH: return NotationPackage.SHAPE__LINE_WIDTH;
				default: return -1;
			}
		}
		if (baseClass == ShapeStyle.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (fontColor: "); //$NON-NLS-1$
		result.append(fontColor);
		result.append(", fontName: "); //$NON-NLS-1$
		result.append(fontName);
		result.append(", fontHeight: "); //$NON-NLS-1$
		result.append(fontHeight);
		result.append(", bold: "); //$NON-NLS-1$
		result.append((eFlags & BOLD_EFLAG) != 0);
		result.append(", italic: "); //$NON-NLS-1$
		result.append((eFlags & ITALIC_EFLAG) != 0);
		result.append(", underline: "); //$NON-NLS-1$
		result.append((eFlags & UNDERLINE_EFLAG) != 0);
		result.append(", strikeThrough: "); //$NON-NLS-1$
		result.append((eFlags & STRIKE_THROUGH_EFLAG) != 0);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", fillColor: "); //$NON-NLS-1$
		result.append(fillColor);
		result.append(", lineColor: "); //$NON-NLS-1$
		result.append(lineColor);
		result.append(", lineWidth: "); //$NON-NLS-1$
		result.append(lineWidth);
		result.append(')');
		return result.toString();
	}

} //ShapeImpl
