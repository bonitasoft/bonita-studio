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
 * A representation of the model object '<em><b>Recap Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.RecapFlow#getRecapPageFlowTransitions <em>Recap Page Flow Transitions</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.RecapFlow#getRecapTransientData <em>Recap Transient Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.RecapFlow#getRecapFlowConnectors <em>Recap Flow Connectors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.RecapFlow#getRecapPageFlowType <em>Recap Page Flow Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.RecapFlow#getRecapForms <em>Recap Forms</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.RecapFlow#getRecapPageFlowRedirectionURL <em>Recap Page Flow Redirection URL</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.RecapFlow#getOverviewFormMapping <em>Overview Form Mapping</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getRecapFlow()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface RecapFlow extends AbstractPageFlow {
	/**
	 * Returns the value of the '<em><b>Recap Page Flow Transitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.bonitasoft.studio.model.process.PageFlowTransition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recap Page Flow Transitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recap Page Flow Transitions</em>' containment reference list.
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getRecapFlow_RecapPageFlowTransitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<PageFlowTransition> getRecapPageFlowTransitions();

	/**
	 * Returns the value of the '<em><b>Recap Transient Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.bonitasoft.studio.model.process.Data}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recap Transient Data</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recap Transient Data</em>' containment reference list.
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getRecapFlow_RecapTransientData()
	 * @model containment="true"
	 * @generated
	 */
	EList<Data> getRecapTransientData();

	/**
	 * Returns the value of the '<em><b>Recap Flow Connectors</b></em>' containment reference list.
	 * The list contents are of type {@link org.bonitasoft.studio.model.process.Connector}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recap Flow Connectors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recap Flow Connectors</em>' containment reference list.
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getRecapFlow_RecapFlowConnectors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Connector> getRecapFlowConnectors();

	/**
	 * Returns the value of the '<em><b>Recap Page Flow Type</b></em>' attribute.
	 * The default value is <code>"PAGEFLOW"</code>.
	 * The literals are from the enumeration {@link org.bonitasoft.studio.model.process.ConsultationPageFlowType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recap Page Flow Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recap Page Flow Type</em>' attribute.
	 * @see org.bonitasoft.studio.model.process.ConsultationPageFlowType
	 * @see #setRecapPageFlowType(ConsultationPageFlowType)
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getRecapFlow_RecapPageFlowType()
	 * @model default="PAGEFLOW"
	 * @generated
	 */
	ConsultationPageFlowType getRecapPageFlowType();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.model.process.RecapFlow#getRecapPageFlowType <em>Recap Page Flow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recap Page Flow Type</em>' attribute.
	 * @see org.bonitasoft.studio.model.process.ConsultationPageFlowType
	 * @see #getRecapPageFlowType()
	 * @generated
	 */
	void setRecapPageFlowType(ConsultationPageFlowType value);

	/**
	 * Returns the value of the '<em><b>Recap Forms</b></em>' containment reference list.
	 * The list contents are of type {@link org.bonitasoft.studio.model.form.ViewForm}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recap Forms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recap Forms</em>' containment reference list.
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getRecapFlow_RecapForms()
	 * @model containment="true"
	 * @generated
	 */
	EList<ViewForm> getRecapForms();

	/**
	 * Returns the value of the '<em><b>Recap Page Flow Redirection URL</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recap Page Flow Redirection URL</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recap Page Flow Redirection URL</em>' containment reference.
	 * @see #setRecapPageFlowRedirectionURL(Expression)
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getRecapFlow_RecapPageFlowRedirectionURL()
	 * @model containment="true"
	 * @generated
	 */
	Expression getRecapPageFlowRedirectionURL();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.model.process.RecapFlow#getRecapPageFlowRedirectionURL <em>Recap Page Flow Redirection URL</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recap Page Flow Redirection URL</em>' containment reference.
	 * @see #getRecapPageFlowRedirectionURL()
	 * @generated
	 */
	void setRecapPageFlowRedirectionURL(Expression value);

	/**
	 * Returns the value of the '<em><b>Overview Form Mapping</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overview Form Mapping</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overview Form Mapping</em>' containment reference.
	 * @see #setOverviewFormMapping(FormMapping)
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getRecapFlow_OverviewFormMapping()
	 * @model containment="true"
	 * @generated
	 */
	FormMapping getOverviewFormMapping();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.model.process.RecapFlow#getOverviewFormMapping <em>Overview Form Mapping</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overview Form Mapping</em>' containment reference.
	 * @see #getOverviewFormMapping()
	 * @generated
	 */
	void setOverviewFormMapping(FormMapping value);

} // RecapFlow
