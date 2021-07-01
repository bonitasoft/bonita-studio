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

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.process.Document;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Image Widget</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.ImageWidget#isIsADocument <em>Is ADocument</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.ImageWidget#getDocument <em>Document</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.ImageWidget#getImgPath <em>Img Path</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getImageWidget()
 * @model
 * @generated
 */
public interface ImageWidget extends Widget, Duplicable {
	/**
     * Returns the value of the '<em><b>Is ADocument</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is ADocument</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Is ADocument</em>' attribute.
     * @see #setIsADocument(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getImageWidget_IsADocument()
     * @model default="false"
     * @generated
     */
	boolean isIsADocument();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.ImageWidget#isIsADocument <em>Is ADocument</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is ADocument</em>' attribute.
     * @see #isIsADocument()
     * @generated
     */
	void setIsADocument(boolean value);

	/**
     * Returns the value of the '<em><b>Document</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Document</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Document</em>' reference.
     * @see #setDocument(Document)
     * @see org.bonitasoft.studio.model.form.FormPackage#getImageWidget_Document()
     * @model
     * @generated
     */
	Document getDocument();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.ImageWidget#getDocument <em>Document</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Document</em>' reference.
     * @see #getDocument()
     * @generated
     */
	void setDocument(Document value);

	/**
     * Returns the value of the '<em><b>Img Path</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Img Path</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Img Path</em>' containment reference.
     * @see #setImgPath(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getImageWidget_ImgPath()
     * @model containment="true"
     * @generated
     */
	Expression getImgPath();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.ImageWidget#getImgPath <em>Img Path</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Img Path</em>' containment reference.
     * @see #getImgPath()
     * @generated
     */
	void setImgPath(Expression value);

} // ImageWidget
