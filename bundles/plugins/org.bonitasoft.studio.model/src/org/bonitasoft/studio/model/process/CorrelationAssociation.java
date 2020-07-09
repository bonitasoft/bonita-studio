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

import org.bonitasoft.studio.model.expression.AbstractExpression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Correlation Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.CorrelationAssociation#getCorrelationExpression <em>Correlation Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.CorrelationAssociation#getCorrelationKey <em>Correlation Key</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getCorrelationAssociation()
 * @model
 * @generated
 */
public interface CorrelationAssociation extends EObject {
	/**
     * Returns the value of the '<em><b>Correlation Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Expression</em>' containment reference.
     * @see #setCorrelationExpression(AbstractExpression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getCorrelationAssociation_CorrelationExpression()
     * @model containment="true"
     * @generated
     */
	AbstractExpression getCorrelationExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.CorrelationAssociation#getCorrelationExpression <em>Correlation Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Expression</em>' containment reference.
     * @see #getCorrelationExpression()
     * @generated
     */
	void setCorrelationExpression(AbstractExpression value);

	/**
     * Returns the value of the '<em><b>Correlation Key</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Key</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Key</em>' containment reference.
     * @see #setCorrelationKey(AbstractExpression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getCorrelationAssociation_CorrelationKey()
     * @model containment="true"
     * @generated
     */
	AbstractExpression getCorrelationKey();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.CorrelationAssociation#getCorrelationKey <em>Correlation Key</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Key</em>' containment reference.
     * @see #getCorrelationKey()
     * @generated
     */
	void setCorrelationKey(AbstractExpression value);

} // CorrelationAssociation
