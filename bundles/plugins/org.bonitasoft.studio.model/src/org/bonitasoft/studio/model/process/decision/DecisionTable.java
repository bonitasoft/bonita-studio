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
package org.bonitasoft.studio.model.process.decision;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.decision.DecisionTable#getLines <em>Lines</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.decision.DecisionTable#getDefaultAction <em>Default Action</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.decision.DecisionPackage#getDecisionTable()
 * @model
 * @generated
 */
public interface DecisionTable extends EObject {
	/**
     * Returns the value of the '<em><b>Lines</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.decision.DecisionTableLine}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lines</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Lines</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.decision.DecisionPackage#getDecisionTable_Lines()
     * @model containment="true"
     * @generated
     */
	EList<DecisionTableLine> getLines();

	/**
     * Returns the value of the '<em><b>Default Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default Action</em>' containment reference.
     * @see #setDefaultAction(DecisionTableAction)
     * @see org.bonitasoft.studio.model.process.decision.DecisionPackage#getDecisionTable_DefaultAction()
     * @model containment="true"
     * @generated
     */
	DecisionTableAction getDefaultAction();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.decision.DecisionTable#getDefaultAction <em>Default Action</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Action</em>' containment reference.
     * @see #getDefaultAction()
     * @generated
     */
	void setDefaultAction(DecisionTableAction value);

} // DecisionTable
