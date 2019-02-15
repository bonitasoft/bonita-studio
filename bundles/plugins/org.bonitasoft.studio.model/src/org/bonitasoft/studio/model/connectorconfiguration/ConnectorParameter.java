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
package org.bonitasoft.studio.model.connectorconfiguration;

import org.bonitasoft.studio.model.expression.AbstractExpression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connector Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter#getKey <em>Key</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage#getConnectorParameter()
 * @model
 * @generated
 */
public interface ConnectorParameter extends EObject {
	/**
     * Returns the value of the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Key</em>' attribute.
     * @see #setKey(String)
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage#getConnectorParameter_Key()
     * @model required="true"
     * @generated
     */
	String getKey();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter#getKey <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Key</em>' attribute.
     * @see #getKey()
     * @generated
     */
	void setKey(String value);

	/**
     * Returns the value of the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Expression</em>' containment reference.
     * @see #setExpression(AbstractExpression)
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage#getConnectorParameter_Expression()
     * @model containment="true"
     * @generated
     */
	AbstractExpression getExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter#getExpression <em>Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' containment reference.
     * @see #getExpression()
     * @generated
     */
	void setExpression(AbstractExpression value);

} // ConnectorParameter
