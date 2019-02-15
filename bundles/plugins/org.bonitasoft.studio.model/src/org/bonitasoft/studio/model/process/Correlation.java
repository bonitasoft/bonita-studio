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

import org.bonitasoft.studio.model.expression.TableExpression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Correlation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.Correlation#getCorrelationType <em>Correlation Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Correlation#getCorrelationAssociation <em>Correlation Association</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getCorrelation()
 * @model
 * @generated
 */
public interface Correlation extends EObject {
	/**
     * Returns the value of the '<em><b>Correlation Type</b></em>' attribute.
     * The default value is <code>"INACTIVE"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.process.CorrelationTypeActive}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.CorrelationTypeActive
     * @see #setCorrelationType(CorrelationTypeActive)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getCorrelation_CorrelationType()
     * @model default="INACTIVE" required="true"
     * @generated
     */
	CorrelationTypeActive getCorrelationType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Correlation#getCorrelationType <em>Correlation Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.CorrelationTypeActive
     * @see #getCorrelationType()
     * @generated
     */
	void setCorrelationType(CorrelationTypeActive value);

	/**
     * Returns the value of the '<em><b>Correlation Association</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * key is the CorrelationKey
     * <!-- end-model-doc -->
     * @return the value of the '<em>Correlation Association</em>' containment reference.
     * @see #setCorrelationAssociation(TableExpression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getCorrelation_CorrelationAssociation()
     * @model containment="true" required="true"
     * @generated
     */
	TableExpression getCorrelationAssociation();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Correlation#getCorrelationAssociation <em>Correlation Association</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Association</em>' containment reference.
     * @see #getCorrelationAssociation()
     * @generated
     */
	void setCorrelationAssociation(TableExpression value);

} // Correlation
