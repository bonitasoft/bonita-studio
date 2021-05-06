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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Widget</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.FileWidget#isDownloadOnly <em>Download Only</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.FileWidget#isUsePreview <em>Use Preview</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.FileWidget#getDocument <em>Document</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.FileWidget#getInitialResourcePath <em>Initial Resource Path</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.FileWidget#getOutputDocumentName <em>Output Document Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.FileWidget#isUpdateDocument <em>Update Document</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.FileWidget#getIntialResourceList <em>Intial Resource List</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.FileWidget#getInputType <em>Input Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.FileWidget#getOutputDocumentListExpression <em>Output Document List Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.FileWidget#getDownloadType <em>Download Type</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getFileWidget()
 * @model
 * @generated
 */
public interface FileWidget extends SingleValuatedFormField, Duplicable {
	/**
     * Returns the value of the '<em><b>Download Only</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Download Only</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Download Only</em>' attribute.
     * @see #setDownloadOnly(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getFileWidget_DownloadOnly()
     * @model default="false"
     * @generated
     */
	boolean isDownloadOnly();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FileWidget#isDownloadOnly <em>Download Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Download Only</em>' attribute.
     * @see #isDownloadOnly()
     * @generated
     */
	void setDownloadOnly(boolean value);

	/**
     * Returns the value of the '<em><b>Use Preview</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Preview</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Use Preview</em>' attribute.
     * @see #setUsePreview(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getFileWidget_UsePreview()
     * @model default="false"
     * @generated
     */
	boolean isUsePreview();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FileWidget#isUsePreview <em>Use Preview</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Use Preview</em>' attribute.
     * @see #isUsePreview()
     * @generated
     */
	void setUsePreview(boolean value);

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
     * @see org.bonitasoft.studio.model.form.FormPackage#getFileWidget_Document()
     * @model
     * @generated
     */
	Document getDocument();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FileWidget#getDocument <em>Document</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Document</em>' reference.
     * @see #getDocument()
     * @generated
     */
	void setDocument(Document value);

	/**
     * Returns the value of the '<em><b>Initial Resource Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Resource Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Initial Resource Path</em>' attribute.
     * @see #setInitialResourcePath(String)
     * @see org.bonitasoft.studio.model.form.FormPackage#getFileWidget_InitialResourcePath()
     * @model
     * @generated
     */
	String getInitialResourcePath();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FileWidget#getInitialResourcePath <em>Initial Resource Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Initial Resource Path</em>' attribute.
     * @see #getInitialResourcePath()
     * @generated
     */
	void setInitialResourcePath(String value);

	/**
     * Returns the value of the '<em><b>Output Document Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Document Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Output Document Name</em>' attribute.
     * @see #setOutputDocumentName(String)
     * @see org.bonitasoft.studio.model.form.FormPackage#getFileWidget_OutputDocumentName()
     * @model
     * @generated
     */
	String getOutputDocumentName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FileWidget#getOutputDocumentName <em>Output Document Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Document Name</em>' attribute.
     * @see #getOutputDocumentName()
     * @generated
     */
	void setOutputDocumentName(String value);

	/**
     * Returns the value of the '<em><b>Update Document</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Update Document</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Update Document</em>' attribute.
     * @see #setUpdateDocument(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getFileWidget_UpdateDocument()
     * @model default="true"
     * @generated
     */
	boolean isUpdateDocument();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FileWidget#isUpdateDocument <em>Update Document</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Update Document</em>' attribute.
     * @see #isUpdateDocument()
     * @generated
     */
	void setUpdateDocument(boolean value);

	/**
     * Returns the value of the '<em><b>Intial Resource List</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intial Resource List</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Intial Resource List</em>' attribute list.
     * @see org.bonitasoft.studio.model.form.FormPackage#getFileWidget_IntialResourceList()
     * @model
     * @generated
     */
	EList<String> getIntialResourceList();

	/**
     * Returns the value of the '<em><b>Input Type</b></em>' attribute.
     * The default value is <code>"URL"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.form.FileWidgetInputType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Input Type</em>' attribute.
     * @see org.bonitasoft.studio.model.form.FileWidgetInputType
     * @see #setInputType(FileWidgetInputType)
     * @see org.bonitasoft.studio.model.form.FormPackage#getFileWidget_InputType()
     * @model default="URL"
     * @generated
     */
	FileWidgetInputType getInputType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FileWidget#getInputType <em>Input Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Type</em>' attribute.
     * @see org.bonitasoft.studio.model.form.FileWidgetInputType
     * @see #getInputType()
     * @generated
     */
	void setInputType(FileWidgetInputType value);

	/**
     * Returns the value of the '<em><b>Output Document List Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Document List Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Output Document List Expression</em>' containment reference.
     * @see #setOutputDocumentListExpression(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getFileWidget_OutputDocumentListExpression()
     * @model containment="true"
     * @generated
     */
	Expression getOutputDocumentListExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FileWidget#getOutputDocumentListExpression <em>Output Document List Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Document List Expression</em>' containment reference.
     * @see #getOutputDocumentListExpression()
     * @generated
     */
	void setOutputDocumentListExpression(Expression value);

	/**
     * Returns the value of the '<em><b>Download Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.form.FileWidgetDownloadType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Download Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Download Type</em>' attribute.
     * @see org.bonitasoft.studio.model.form.FileWidgetDownloadType
     * @see #setDownloadType(FileWidgetDownloadType)
     * @see org.bonitasoft.studio.model.form.FormPackage#getFileWidget_DownloadType()
     * @model
     * @generated
     */
	FileWidgetDownloadType getDownloadType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.FileWidget#getDownloadType <em>Download Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Download Type</em>' attribute.
     * @see org.bonitasoft.studio.model.form.FileWidgetDownloadType
     * @see #getDownloadType()
     * @generated
     */
	void setDownloadType(FileWidgetDownloadType value);

} // FileWidget
