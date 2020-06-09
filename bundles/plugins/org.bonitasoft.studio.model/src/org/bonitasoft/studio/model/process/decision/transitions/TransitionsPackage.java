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
package org.bonitasoft.studio.model.process.decision.transitions;

import org.bonitasoft.studio.model.process.decision.DecisionPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see org.bonitasoft.studio.model.process.decision.transitions.TransitionsFactory
 * @model kind="package"
 * @generated
 */
public interface TransitionsPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "transitions"; //$NON-NLS-1$

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.bonitasoft.org/ns/studio/process/decision/transitions"; //$NON-NLS-1$

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "transitions"; //$NON-NLS-1$

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	TransitionsPackage eINSTANCE = org.bonitasoft.studio.model.process.decision.transitions.impl.TransitionsPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.process.decision.transitions.impl.TakeTransitionActionImpl <em>Take Transition Action</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.process.decision.transitions.impl.TakeTransitionActionImpl
     * @see org.bonitasoft.studio.model.process.decision.transitions.impl.TransitionsPackageImpl#getTakeTransitionAction()
     * @generated
     */
	int TAKE_TRANSITION_ACTION = 0;

	/**
     * The feature id for the '<em><b>Take Transition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TAKE_TRANSITION_ACTION__TAKE_TRANSITION = DecisionPackage.DECISION_TABLE_ACTION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Take Transition Action</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TAKE_TRANSITION_ACTION_FEATURE_COUNT = DecisionPackage.DECISION_TABLE_ACTION_FEATURE_COUNT + 1;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.process.decision.transitions.TakeTransitionAction <em>Take Transition Action</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Take Transition Action</em>'.
     * @see org.bonitasoft.studio.model.process.decision.transitions.TakeTransitionAction
     * @generated
     */
	EClass getTakeTransitionAction();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.process.decision.transitions.TakeTransitionAction#isTakeTransition <em>Take Transition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Take Transition</em>'.
     * @see org.bonitasoft.studio.model.process.decision.transitions.TakeTransitionAction#isTakeTransition()
     * @see #getTakeTransitionAction()
     * @generated
     */
	EAttribute getTakeTransitionAction_TakeTransition();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	TransitionsFactory getTransitionsFactory();

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
         * The meta object literal for the '{@link org.bonitasoft.studio.model.process.decision.transitions.impl.TakeTransitionActionImpl <em>Take Transition Action</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.process.decision.transitions.impl.TakeTransitionActionImpl
         * @see org.bonitasoft.studio.model.process.decision.transitions.impl.TransitionsPackageImpl#getTakeTransitionAction()
         * @generated
         */
		EClass TAKE_TRANSITION_ACTION = eINSTANCE.getTakeTransitionAction();

		/**
         * The meta object literal for the '<em><b>Take Transition</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TAKE_TRANSITION_ACTION__TAKE_TRANSITION = eINSTANCE.getTakeTransitionAction_TakeTransition();

	}

} //TransitionsPackage
