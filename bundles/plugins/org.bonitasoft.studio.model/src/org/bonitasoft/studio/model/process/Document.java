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
package org.bonitasoft.studio.model.process;

import org.bonitasoft.studio.model.expression.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.Document#getDefaultValueIdOfDocumentStore <em>Default Value Id Of Document Store</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Document#getMimeType <em>Mime Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Document#getUrl <em>Url</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Document#getDocumentType <em>Document Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Document#getInitialMultipleContent <em>Initial Multiple Content</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Document#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Document#getContractInput <em>Contract Input</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getDocument()
 * @model
 * @generated
 */
public interface Document extends Element {
	/**
     * Returns the value of the '<em><b>Default Value Id Of Document Store</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value Id Of Document Store</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default Value Id Of Document Store</em>' attribute.
     * @see #setDefaultValueIdOfDocumentStore(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getDocument_DefaultValueIdOfDocumentStore()
     * @model
     * @generated
     */
	String getDefaultValueIdOfDocumentStore();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Document#getDefaultValueIdOfDocumentStore <em>Default Value Id Of Document Store</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Value Id Of Document Store</em>' attribute.
     * @see #getDefaultValueIdOfDocumentStore()
     * @generated
     */
	void setDefaultValueIdOfDocumentStore(String value);

	/**
     * Returns the value of the '<em><b>Mime Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mime Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Mime Type</em>' containment reference.
     * @see #setMimeType(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getDocument_MimeType()
     * @model containment="true"
     * @generated
     */
	Expression getMimeType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Document#getMimeType <em>Mime Type</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mime Type</em>' containment reference.
     * @see #getMimeType()
     * @generated
     */
	void setMimeType(Expression value);

	/**
     * Returns the value of the '<em><b>Url</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Url</em>' containment reference.
     * @see #setUrl(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getDocument_Url()
     * @model containment="true"
     * @generated
     */
	Expression getUrl();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Document#getUrl <em>Url</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Url</em>' containment reference.
     * @see #getUrl()
     * @generated
     */
	void setUrl(Expression value);

	/**
     * Returns the value of the '<em><b>Document Type</b></em>' attribute.
     * The default value is <code>"NONE"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.process.DocumentType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Document Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Document Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.DocumentType
     * @see #setDocumentType(DocumentType)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getDocument_DocumentType()
     * @model default="NONE" required="true"
     * @generated
     */
	DocumentType getDocumentType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Document#getDocumentType <em>Document Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Document Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.DocumentType
     * @see #getDocumentType()
     * @generated
     */
	void setDocumentType(DocumentType value);

	/**
     * Returns the value of the '<em><b>Initial Multiple Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Multiple Content</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Initial Multiple Content</em>' containment reference.
     * @see #setInitialMultipleContent(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getDocument_InitialMultipleContent()
     * @model containment="true"
     * @generated
     */
	Expression getInitialMultipleContent();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Document#getInitialMultipleContent <em>Initial Multiple Content</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Initial Multiple Content</em>' containment reference.
     * @see #getInitialMultipleContent()
     * @generated
     */
	void setInitialMultipleContent(Expression value);

	/**
     * Returns the value of the '<em><b>Multiple</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiple</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Multiple</em>' attribute.
     * @see #setMultiple(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getDocument_Multiple()
     * @model
     * @generated
     */
	boolean isMultiple();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Document#isMultiple <em>Multiple</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Multiple</em>' attribute.
     * @see #isMultiple()
     * @generated
     */
	void setMultiple(boolean value);

	/**
     * Returns the value of the '<em><b>Contract Input</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contract Input</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Contract Input</em>' reference.
     * @see #setContractInput(ContractInput)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getDocument_ContractInput()
     * @model
     * @generated
     */
	ContractInput getContractInput();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Document#getContractInput <em>Contract Input</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Contract Input</em>' reference.
     * @see #getContractInput()
     * @generated
     */
	void setContractInput(ContractInput value);

} // Document
