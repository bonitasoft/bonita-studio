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
package org.bonitasoft.studio.model.parameter;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see org.bonitasoft.studio.model.parameter.ParameterFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/edapt historyURI='process.history'"
 * @generated
 */
public interface ParameterPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "parameter"; //$NON-NLS-1$

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.bonitasoft.org/ns/studio/parameter"; //$NON-NLS-1$

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "parameter"; //$NON-NLS-1$

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ParameterPackage eINSTANCE = org.bonitasoft.studio.model.parameter.impl.ParameterPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.parameter.impl.ParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.parameter.impl.ParameterImpl
     * @see org.bonitasoft.studio.model.parameter.impl.ParameterPackageImpl#getParameter()
     * @generated
     */
	int PARAMETER = 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PARAMETER__NAME = 0;

	/**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PARAMETER__VALUE = 1;

	/**
     * The feature id for the '<em><b>Type Classname</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PARAMETER__TYPE_CLASSNAME = 2;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PARAMETER__DESCRIPTION = 3;

	/**
     * The number of structural features of the '<em>Parameter</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PARAMETER_FEATURE_COUNT = 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.parameter.impl.ParameterContextImpl <em>Context</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.parameter.impl.ParameterContextImpl
     * @see org.bonitasoft.studio.model.parameter.impl.ParameterPackageImpl#getParameterContext()
     * @generated
     */
	int PARAMETER_CONTEXT = 1;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PARAMETER_CONTEXT__NAME = 0;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PARAMETER_CONTEXT__DESCRIPTION = 1;

	/**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PARAMETER_CONTEXT__PARAMETERS = 2;

	/**
     * The feature id for the '<em><b>Default Context</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PARAMETER_CONTEXT__DEFAULT_CONTEXT = 3;

	/**
     * The number of structural features of the '<em>Context</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PARAMETER_CONTEXT_FEATURE_COUNT = 4;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.parameter.Parameter <em>Parameter</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Parameter</em>'.
     * @see org.bonitasoft.studio.model.parameter.Parameter
     * @generated
     */
	EClass getParameter();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.parameter.Parameter#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.parameter.Parameter#getName()
     * @see #getParameter()
     * @generated
     */
	EAttribute getParameter_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.parameter.Parameter#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.bonitasoft.studio.model.parameter.Parameter#getValue()
     * @see #getParameter()
     * @generated
     */
	EAttribute getParameter_Value();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.parameter.Parameter#getTypeClassname <em>Type Classname</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type Classname</em>'.
     * @see org.bonitasoft.studio.model.parameter.Parameter#getTypeClassname()
     * @see #getParameter()
     * @generated
     */
	EAttribute getParameter_TypeClassname();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.parameter.Parameter#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.model.parameter.Parameter#getDescription()
     * @see #getParameter()
     * @generated
     */
	EAttribute getParameter_Description();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.parameter.ParameterContext <em>Context</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Context</em>'.
     * @see org.bonitasoft.studio.model.parameter.ParameterContext
     * @generated
     */
	EClass getParameterContext();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.parameter.ParameterContext#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.parameter.ParameterContext#getName()
     * @see #getParameterContext()
     * @generated
     */
	EAttribute getParameterContext_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.parameter.ParameterContext#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.model.parameter.ParameterContext#getDescription()
     * @see #getParameterContext()
     * @generated
     */
	EAttribute getParameterContext_Description();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.parameter.ParameterContext#getParameters <em>Parameters</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Parameters</em>'.
     * @see org.bonitasoft.studio.model.parameter.ParameterContext#getParameters()
     * @see #getParameterContext()
     * @generated
     */
	EReference getParameterContext_Parameters();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.parameter.ParameterContext#isDefaultContext <em>Default Context</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default Context</em>'.
     * @see org.bonitasoft.studio.model.parameter.ParameterContext#isDefaultContext()
     * @see #getParameterContext()
     * @generated
     */
	EAttribute getParameterContext_DefaultContext();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	ParameterFactory getParameterFactory();

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
         * The meta object literal for the '{@link org.bonitasoft.studio.model.parameter.impl.ParameterImpl <em>Parameter</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.parameter.impl.ParameterImpl
         * @see org.bonitasoft.studio.model.parameter.impl.ParameterPackageImpl#getParameter()
         * @generated
         */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PARAMETER__NAME = eINSTANCE.getParameter_Name();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PARAMETER__VALUE = eINSTANCE.getParameter_Value();

		/**
         * The meta object literal for the '<em><b>Type Classname</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PARAMETER__TYPE_CLASSNAME = eINSTANCE.getParameter_TypeClassname();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PARAMETER__DESCRIPTION = eINSTANCE.getParameter_Description();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.parameter.impl.ParameterContextImpl <em>Context</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.parameter.impl.ParameterContextImpl
         * @see org.bonitasoft.studio.model.parameter.impl.ParameterPackageImpl#getParameterContext()
         * @generated
         */
		EClass PARAMETER_CONTEXT = eINSTANCE.getParameterContext();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PARAMETER_CONTEXT__NAME = eINSTANCE.getParameterContext_Name();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PARAMETER_CONTEXT__DESCRIPTION = eINSTANCE.getParameterContext_Description();

		/**
         * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PARAMETER_CONTEXT__PARAMETERS = eINSTANCE.getParameterContext_Parameters();

		/**
         * The meta object literal for the '<em><b>Default Context</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PARAMETER_CONTEXT__DEFAULT_CONTEXT = eINSTANCE.getParameterContext_DefaultContext();

	}

} //ParameterPackage
