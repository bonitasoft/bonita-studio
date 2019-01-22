/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.form.impl;

import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Widget Layout Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.WidgetLayoutInfoImpl#getLine <em>Line</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.WidgetLayoutInfoImpl#getColumn <em>Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.WidgetLayoutInfoImpl#getVerticalSpan <em>Vertical Span</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.WidgetLayoutInfoImpl#getHorizontalSpan <em>Horizontal Span</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WidgetLayoutInfoImpl extends EObjectImpl implements WidgetLayoutInfo {
	/**
	 * The default value of the '{@link #getLine() <em>Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLine()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLine() <em>Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLine()
	 * @generated
	 * @ordered
	 */
	protected int line = LINE_EDEFAULT;

	/**
	 * The default value of the '{@link #getColumn() <em>Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumn()
	 * @generated
	 * @ordered
	 */
	protected static final int COLUMN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getColumn() <em>Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumn()
	 * @generated
	 * @ordered
	 */
	protected int column = COLUMN_EDEFAULT;

	/**
	 * The default value of the '{@link #getVerticalSpan() <em>Vertical Span</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerticalSpan()
	 * @generated
	 * @ordered
	 */
	protected static final int VERTICAL_SPAN_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getVerticalSpan() <em>Vertical Span</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerticalSpan()
	 * @generated
	 * @ordered
	 */
	protected int verticalSpan = VERTICAL_SPAN_EDEFAULT;

	/**
	 * The default value of the '{@link #getHorizontalSpan() <em>Horizontal Span</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHorizontalSpan()
	 * @generated
	 * @ordered
	 */
	protected static final int HORIZONTAL_SPAN_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getHorizontalSpan() <em>Horizontal Span</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHorizontalSpan()
	 * @generated
	 * @ordered
	 */
	protected int horizontalSpan = HORIZONTAL_SPAN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WidgetLayoutInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.WIDGET_LAYOUT_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getLine() {
		return line;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLine(int newLine) {
		int oldLine = line;
		line = newLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.WIDGET_LAYOUT_INFO__LINE, oldLine, line));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getColumn() {
		return column;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColumn(int newColumn) {
		int oldColumn = column;
		column = newColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.WIDGET_LAYOUT_INFO__COLUMN, oldColumn, column));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getVerticalSpan() {
		return verticalSpan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVerticalSpan(int newVerticalSpan) {
		int oldVerticalSpan = verticalSpan;
		verticalSpan = newVerticalSpan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.WIDGET_LAYOUT_INFO__VERTICAL_SPAN, oldVerticalSpan, verticalSpan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getHorizontalSpan() {
		return horizontalSpan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHorizontalSpan(int newHorizontalSpan) {
		int oldHorizontalSpan = horizontalSpan;
		horizontalSpan = newHorizontalSpan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.WIDGET_LAYOUT_INFO__HORIZONTAL_SPAN, oldHorizontalSpan, horizontalSpan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.WIDGET_LAYOUT_INFO__LINE:
				return getLine();
			case FormPackage.WIDGET_LAYOUT_INFO__COLUMN:
				return getColumn();
			case FormPackage.WIDGET_LAYOUT_INFO__VERTICAL_SPAN:
				return getVerticalSpan();
			case FormPackage.WIDGET_LAYOUT_INFO__HORIZONTAL_SPAN:
				return getHorizontalSpan();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FormPackage.WIDGET_LAYOUT_INFO__LINE:
				setLine((Integer)newValue);
				return;
			case FormPackage.WIDGET_LAYOUT_INFO__COLUMN:
				setColumn((Integer)newValue);
				return;
			case FormPackage.WIDGET_LAYOUT_INFO__VERTICAL_SPAN:
				setVerticalSpan((Integer)newValue);
				return;
			case FormPackage.WIDGET_LAYOUT_INFO__HORIZONTAL_SPAN:
				setHorizontalSpan((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case FormPackage.WIDGET_LAYOUT_INFO__LINE:
				setLine(LINE_EDEFAULT);
				return;
			case FormPackage.WIDGET_LAYOUT_INFO__COLUMN:
				setColumn(COLUMN_EDEFAULT);
				return;
			case FormPackage.WIDGET_LAYOUT_INFO__VERTICAL_SPAN:
				setVerticalSpan(VERTICAL_SPAN_EDEFAULT);
				return;
			case FormPackage.WIDGET_LAYOUT_INFO__HORIZONTAL_SPAN:
				setHorizontalSpan(HORIZONTAL_SPAN_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FormPackage.WIDGET_LAYOUT_INFO__LINE:
				return line != LINE_EDEFAULT;
			case FormPackage.WIDGET_LAYOUT_INFO__COLUMN:
				return column != COLUMN_EDEFAULT;
			case FormPackage.WIDGET_LAYOUT_INFO__VERTICAL_SPAN:
				return verticalSpan != VERTICAL_SPAN_EDEFAULT;
			case FormPackage.WIDGET_LAYOUT_INFO__HORIZONTAL_SPAN:
				return horizontalSpan != HORIZONTAL_SPAN_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (line: "); //$NON-NLS-1$
		result.append(line);
		result.append(", column: "); //$NON-NLS-1$
		result.append(column);
		result.append(", verticalSpan: "); //$NON-NLS-1$
		result.append(verticalSpan);
		result.append(", horizontalSpan: "); //$NON-NLS-1$
		result.append(horizontalSpan);
		result.append(')');
		return result.toString();
	}

} //WidgetLayoutInfoImpl
