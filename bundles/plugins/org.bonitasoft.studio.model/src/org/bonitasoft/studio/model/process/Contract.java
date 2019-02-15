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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contract</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.Contract#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Contract#getConstraints <em>Constraints</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getContract()
 * @model
 * @generated
 */
public interface Contract extends EObject {
	/**
     * Returns the value of the '<em><b>Inputs</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.ContractInput}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Inputs</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getContract_Inputs()
     * @model containment="true"
     * @generated
     */
	EList<ContractInput> getInputs();

	/**
     * Returns the value of the '<em><b>Constraints</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.ContractConstraint}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Constraints</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getContract_Constraints()
     * @model containment="true"
     * @generated
     */
	EList<ContractConstraint> getConstraints();

} // Contract
