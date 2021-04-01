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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.process.decision.DecisionFactory
 * @model kind="package"
 * @generated
 */
public interface DecisionPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "decision"; //$NON-NLS-1$

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.bonitasoft.org/ns/studio/process/decision"; //$NON-NLS-1$

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "decision"; //$NON-NLS-1$

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	DecisionPackage eINSTANCE = org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.decision.impl.DecisionTableImpl <em>Table</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.decision.impl.DecisionTableImpl
     * @see org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl#getDecisionTable()
     * @generated
     */
	int DECISION_TABLE = 0;

	/**
     * The feature id for the '<em><b>Lines</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DECISION_TABLE__LINES = 0;

	/**
     * The feature id for the '<em><b>Default Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DECISION_TABLE__DEFAULT_ACTION = 1;

	/**
     * The number of structural features of the '<em>Table</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DECISION_TABLE_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.decision.impl.DecisionTableLineImpl <em>Table Line</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.decision.impl.DecisionTableLineImpl
     * @see org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl#getDecisionTableLine()
     * @generated
     */
	int DECISION_TABLE_LINE = 1;

	/**
     * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DECISION_TABLE_LINE__CONDITIONS = 0;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DECISION_TABLE_LINE__ACTION = 1;

	/**
     * The number of structural features of the '<em>Table Line</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DECISION_TABLE_LINE_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.decision.impl.DecisionTableActionImpl <em>Table Action</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.decision.impl.DecisionTableActionImpl
     * @see org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl#getDecisionTableAction()
     * @generated
     */
	int DECISION_TABLE_ACTION = 2;

	/**
     * The number of structural features of the '<em>Table Action</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DECISION_TABLE_ACTION_FEATURE_COUNT = 0;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.decision.DecisionTable <em>Table</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table</em>'.
     * @see org.bonitasoft.studio.model.process.decision.DecisionTable
     * @generated
     */
	EClass getDecisionTable();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.decision.DecisionTable#getLines <em>Lines</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Lines</em>'.
     * @see org.bonitasoft.studio.model.process.decision.DecisionTable#getLines()
     * @see #getDecisionTable()
     * @generated
     */
	EReference getDecisionTable_Lines();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.decision.DecisionTable#getDefaultAction <em>Default Action</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default Action</em>'.
     * @see org.bonitasoft.studio.model.process.decision.DecisionTable#getDefaultAction()
     * @see #getDecisionTable()
     * @generated
     */
	EReference getDecisionTable_DefaultAction();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.decision.DecisionTableLine <em>Table Line</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Line</em>'.
     * @see org.bonitasoft.studio.model.process.decision.DecisionTableLine
     * @generated
     */
	EClass getDecisionTableLine();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.process.decision.DecisionTableLine#getConditions <em>Conditions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Conditions</em>'.
     * @see org.bonitasoft.studio.model.process.decision.DecisionTableLine#getConditions()
     * @see #getDecisionTableLine()
     * @generated
     */
	EReference getDecisionTableLine_Conditions();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.process.decision.DecisionTableLine#getAction <em>Action</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Action</em>'.
     * @see org.bonitasoft.studio.model.process.decision.DecisionTableLine#getAction()
     * @see #getDecisionTableLine()
     * @generated
     */
	EReference getDecisionTableLine_Action();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.decision.DecisionTableAction <em>Table Action</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Action</em>'.
     * @see org.bonitasoft.studio.model.process.decision.DecisionTableAction
     * @generated
     */
	EClass getDecisionTableAction();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	DecisionFactory getDecisionFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.decision.impl.DecisionTableImpl <em>Table</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.decision.impl.DecisionTableImpl
         * @see org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl#getDecisionTable()
         * @generated
         */
		EClass DECISION_TABLE = eINSTANCE.getDecisionTable();

		/**
         * The meta object literal for the '<em><b>Lines</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DECISION_TABLE__LINES = eINSTANCE.getDecisionTable_Lines();

		/**
         * The meta object literal for the '<em><b>Default Action</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DECISION_TABLE__DEFAULT_ACTION = eINSTANCE.getDecisionTable_DefaultAction();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.decision.impl.DecisionTableLineImpl <em>Table Line</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.decision.impl.DecisionTableLineImpl
         * @see org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl#getDecisionTableLine()
         * @generated
         */
		EClass DECISION_TABLE_LINE = eINSTANCE.getDecisionTableLine();

		/**
         * The meta object literal for the '<em><b>Conditions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DECISION_TABLE_LINE__CONDITIONS = eINSTANCE.getDecisionTableLine_Conditions();

		/**
         * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DECISION_TABLE_LINE__ACTION = eINSTANCE.getDecisionTableLine_Action();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.decision.impl.DecisionTableActionImpl <em>Table Action</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.decision.impl.DecisionTableActionImpl
         * @see org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl#getDecisionTableAction()
         * @generated
         */
		EClass DECISION_TABLE_ACTION = eINSTANCE.getDecisionTableAction();

	}

} //DecisionPackage
