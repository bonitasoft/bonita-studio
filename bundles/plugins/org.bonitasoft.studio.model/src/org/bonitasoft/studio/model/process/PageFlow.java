/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;

import org.bonitasoft.studio.model.form.Form;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#getByPassFormsGeneration <em>By Pass Forms Generation</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#getConfirmationTemplate <em>Confirmation Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#getTransientData <em>Transient Data</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#getPageFlowConnectors <em>Page Flow Connectors</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#getEntryPageFlowType <em>Entry Page Flow Type</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#isTransmitURLAsParameter <em>Transmit URL As Parameter</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#getPageFlowTransitions <em>Page Flow Transitions</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#getForm <em>Form</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#getEntryRedirectionURL <em>Entry Redirection URL</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#getConfirmationMessage <em>Confirmation Message</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#getEntryRedirectionActions <em>Entry Redirection
 * Actions</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.PageFlow#getFormMapping <em>Form Mapping</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow()
 * @model
 * @generated
 */
public interface PageFlow extends ConnectableElement, AbstractPageFlow {

    /**
     * Returns the value of the '<em><b>By Pass Forms Generation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>By Pass Forms Generation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>By Pass Forms Generation</em>' attribute.
     * @see #setByPassFormsGeneration(Boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_ByPassFormsGeneration()
     * @model
     * @generated
     */
    Boolean getByPassFormsGeneration();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.PageFlow#getByPassFormsGeneration <em>By Pass Forms
     * Generation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>By Pass Forms Generation</em>' attribute.
     * @see #getByPassFormsGeneration()
     * @generated
     */
    void setByPassFormsGeneration(Boolean value);

    /**
     * Returns the value of the '<em><b>Confirmation Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Confirmation Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Confirmation Template</em>' containment reference.
     * @see #setConfirmationTemplate(AssociatedFile)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_ConfirmationTemplate()
     * @model containment="true"
     * @generated
     */
    AssociatedFile getConfirmationTemplate();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.PageFlow#getConfirmationTemplate <em>Confirmation
     * Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Confirmation Template</em>' containment reference.
     * @see #getConfirmationTemplate()
     * @generated
     */
    void setConfirmationTemplate(AssociatedFile value);

    /**
     * Returns the value of the '<em><b>Transient Data</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.Data}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transient Data</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Transient Data</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_TransientData()
     * @model containment="true"
     * @generated
     */
    EList<Data> getTransientData();

    /**
     * Returns the value of the '<em><b>Page Flow Connectors</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.Connector}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Page Flow Connectors</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Page Flow Connectors</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_PageFlowConnectors()
     * @model containment="true"
     * @generated
     */
    EList<Connector> getPageFlowConnectors();

    /**
     * Returns the value of the '<em><b>Entry Page Flow Type</b></em>' attribute.
     * The default value is <code>"PAGEFLOW"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.process.EntryPageFlowType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Entry Page Flow Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Entry Page Flow Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.EntryPageFlowType
     * @see #setEntryPageFlowType(EntryPageFlowType)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_EntryPageFlowType()
     * @model default="PAGEFLOW"
     * @generated
     */
    EntryPageFlowType getEntryPageFlowType();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.PageFlow#getEntryPageFlowType <em>Entry Page Flow
     * Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Entry Page Flow Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.EntryPageFlowType
     * @see #getEntryPageFlowType()
     * @generated
     */
    void setEntryPageFlowType(EntryPageFlowType value);

    /**
     * Returns the value of the '<em><b>Transmit URL As Parameter</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transmit URL As Parameter</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Transmit URL As Parameter</em>' attribute.
     * @see #setTransmitURLAsParameter(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_TransmitURLAsParameter()
     * @model default="true" required="true"
     * @generated
     */
    boolean isTransmitURLAsParameter();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.PageFlow#isTransmitURLAsParameter <em>Transmit URL
     * As Parameter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Transmit URL As Parameter</em>' attribute.
     * @see #isTransmitURLAsParameter()
     * @generated
     */
    void setTransmitURLAsParameter(boolean value);

    /**
     * Returns the value of the '<em><b>Page Flow Transitions</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.PageFlowTransition}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Page Flow Transitions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Page Flow Transitions</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_PageFlowTransitions()
     * @model containment="true"
     * @generated
     */
    EList<PageFlowTransition> getPageFlowTransitions();

    /**
     * Returns the value of the '<em><b>Form</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.form.Form}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Form</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Form</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_Form()
     * @model containment="true"
     * @generated
     */
    EList<Form> getForm();

    /**
     * Returns the value of the '<em><b>Entry Redirection URL</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Entry Redirection URL</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Entry Redirection URL</em>' containment reference.
     * @see #setEntryRedirectionURL(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_EntryRedirectionURL()
     * @model containment="true"
     * @generated
     */
    Expression getEntryRedirectionURL();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.PageFlow#getEntryRedirectionURL <em>Entry
     * Redirection URL</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Entry Redirection URL</em>' containment reference.
     * @see #getEntryRedirectionURL()
     * @generated
     */
    void setEntryRedirectionURL(Expression value);

    /**
     * Returns the value of the '<em><b>Confirmation Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Confirmation Message</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Confirmation Message</em>' containment reference.
     * @see #setConfirmationMessage(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_ConfirmationMessage()
     * @model containment="true"
     * @generated
     */
    Expression getConfirmationMessage();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.PageFlow#getConfirmationMessage <em>Confirmation
     * Message</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Confirmation Message</em>' containment reference.
     * @see #getConfirmationMessage()
     * @generated
     */
    void setConfirmationMessage(Expression value);

    /**
     * Returns the value of the '<em><b>Entry Redirection Actions</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.expression.Operation}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Entry Redirection Actions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Entry Redirection Actions</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_EntryRedirectionActions()
     * @model containment="true"
     * @generated
     */
    EList<Operation> getEntryRedirectionActions();

    /**
     * Returns the value of the '<em><b>Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Form Mapping</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Form Mapping</em>' containment reference.
     * @see #setFormMapping(FormMapping)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getPageFlow_FormMapping()
     * @model containment="true"
     * @generated
     */
    FormMapping getFormMapping();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.PageFlow#getFormMapping <em>Form Mapping</em>}'
     * containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Form Mapping</em>' containment reference.
     * @see #getFormMapping()
     * @generated
     */
    void setFormMapping(FormMapping value);

} // PageFlow
