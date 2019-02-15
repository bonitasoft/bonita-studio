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
package org.bonitasoft.studio.model.form;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Widget Layout Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Information of the position on the widget
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getLine <em>Line</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getColumn <em>Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getVerticalSpan <em>Vertical Span</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getHorizontalSpan <em>Horizontal Span</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getWidgetLayoutInfo()
 * @model
 * @generated
 */
public interface WidgetLayoutInfo extends EObject {
	/**
     * Returns the value of the '<em><b>Line</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Line</em>' attribute.
     * @see #setLine(int)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidgetLayoutInfo_Line()
     * @model
     * @generated
     */
	int getLine();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getLine <em>Line</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line</em>' attribute.
     * @see #getLine()
     * @generated
     */
	void setLine(int value);

	/**
     * Returns the value of the '<em><b>Column</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Column</em>' attribute.
     * @see #setColumn(int)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidgetLayoutInfo_Column()
     * @model
     * @generated
     */
	int getColumn();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getColumn <em>Column</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column</em>' attribute.
     * @see #getColumn()
     * @generated
     */
	void setColumn(int value);

	/**
     * Returns the value of the '<em><b>Vertical Span</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vertical Span</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Vertical Span</em>' attribute.
     * @see #setVerticalSpan(int)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidgetLayoutInfo_VerticalSpan()
     * @model default="1"
     * @generated
     */
	int getVerticalSpan();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getVerticalSpan <em>Vertical Span</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vertical Span</em>' attribute.
     * @see #getVerticalSpan()
     * @generated
     */
	void setVerticalSpan(int value);

	/**
     * Returns the value of the '<em><b>Horizontal Span</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Horizontal Span</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Horizontal Span</em>' attribute.
     * @see #setHorizontalSpan(int)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidgetLayoutInfo_HorizontalSpan()
     * @model default="1"
     * @generated
     */
	int getHorizontalSpan();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getHorizontalSpan <em>Horizontal Span</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Horizontal Span</em>' attribute.
     * @see #getHorizontalSpan()
     * @generated
     */
	void setHorizontalSpan(int value);

} // WidgetLayoutInfo
