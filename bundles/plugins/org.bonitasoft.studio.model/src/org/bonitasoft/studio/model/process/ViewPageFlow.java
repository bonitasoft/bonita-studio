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
import org.bonitasoft.studio.model.form.ViewForm;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>View Page Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.ViewPageFlow#getViewPageFlowTransitions <em>View Page Flow Transitions</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.ViewPageFlow#getViewTransientData <em>View Transient Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.ViewPageFlow#getViewPageFlowConnectors <em>View Page Flow Connectors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.ViewPageFlow#getViewPageFlowType <em>View Page Flow Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.ViewPageFlow#getViewForm <em>View Form</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.ViewPageFlow#getViewPageFlowRedirectionURL <em>View Page Flow Redirection URL</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getViewPageFlow()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ViewPageFlow extends AbstractPageFlow {
	/**
	 * Returns the value of the '<em><b>View Page Flow Transitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.bonitasoft.studio.model.process.PageFlowTransition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Page Flow Transitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Page Flow Transitions</em>' containment reference list.
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getViewPageFlow_ViewPageFlowTransitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<PageFlowTransition> getViewPageFlowTransitions();

	/**
	 * Returns the value of the '<em><b>View Transient Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.bonitasoft.studio.model.process.Data}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Transient Data</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Transient Data</em>' containment reference list.
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getViewPageFlow_ViewTransientData()
	 * @model containment="true"
	 * @generated
	 */
	EList<Data> getViewTransientData();

	/**
	 * Returns the value of the '<em><b>View Page Flow Connectors</b></em>' containment reference list.
	 * The list contents are of type {@link org.bonitasoft.studio.model.process.Connector}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Page Flow Connectors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Page Flow Connectors</em>' containment reference list.
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getViewPageFlow_ViewPageFlowConnectors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Connector> getViewPageFlowConnectors();

	/**
	 * Returns the value of the '<em><b>View Page Flow Type</b></em>' attribute.
	 * The default value is <code>"PAGEFLOW"</code>.
	 * The literals are from the enumeration {@link org.bonitasoft.studio.model.process.ConsultationPageFlowType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Page Flow Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Page Flow Type</em>' attribute.
	 * @see org.bonitasoft.studio.model.process.ConsultationPageFlowType
	 * @see #setViewPageFlowType(ConsultationPageFlowType)
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getViewPageFlow_ViewPageFlowType()
	 * @model default="PAGEFLOW"
	 * @generated
	 */
	ConsultationPageFlowType getViewPageFlowType();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.model.process.ViewPageFlow#getViewPageFlowType <em>View Page Flow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View Page Flow Type</em>' attribute.
	 * @see org.bonitasoft.studio.model.process.ConsultationPageFlowType
	 * @see #getViewPageFlowType()
	 * @generated
	 */
	void setViewPageFlowType(ConsultationPageFlowType value);

	/**
	 * Returns the value of the '<em><b>View Form</b></em>' containment reference list.
	 * The list contents are of type {@link org.bonitasoft.studio.model.form.ViewForm}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Form</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Form</em>' containment reference list.
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getViewPageFlow_ViewForm()
	 * @model containment="true"
	 * @generated
	 */
	EList<ViewForm> getViewForm();

	/**
	 * Returns the value of the '<em><b>View Page Flow Redirection URL</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Page Flow Redirection URL</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Page Flow Redirection URL</em>' containment reference.
	 * @see #setViewPageFlowRedirectionURL(Expression)
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getViewPageFlow_ViewPageFlowRedirectionURL()
	 * @model containment="true"
	 * @generated
	 */
	Expression getViewPageFlowRedirectionURL();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.model.process.ViewPageFlow#getViewPageFlowRedirectionURL <em>View Page Flow Redirection URL</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View Page Flow Redirection URL</em>' containment reference.
	 * @see #getViewPageFlowRedirectionURL()
	 * @generated
	 */
	void setViewPageFlowRedirectionURL(Expression value);

} // ViewPageFlow
