/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.spi.migration;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A container for instances of a certain class
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Type#getEClass <em>EClass</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Type#getInstances <em>Instances</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.Type#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getType()
 * @model
 * @generated
 */
public interface Type {
	/**
	 * Returns the value of the '<em><b>EClass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EClass</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the class to which the instances of this type conform
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>EClass</em>' reference.
	 * @see #setEClass(EClass)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getType_EClass()
	 * @model required="true"
	 * @generated
	 */
	EClass getEClass();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.Type#getEClass <em>EClass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>EClass</em>' reference.
	 * @see #getEClass()
	 * @generated
	 */
	void setEClass(EClass value);

	/**
	 * Returns the value of the '<em><b>Instances</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.edapt.spi.migration.Instance}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.Instance#getType
	 * <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instances</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the instances that conform to the class
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Instances</em>' containment reference list.
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getType_Instances()
	 * @see org.eclipse.emf.edapt.spi.migration.Instance#getType
	 * @model opposite="type" containment="true"
	 * @generated
	 */
	EList<Instance> getInstances();

	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.Model#getTypes
	 * <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the model by which these instances are contained
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(Model)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getType_Model()
	 * @see org.eclipse.emf.edapt.spi.migration.Model#getTypes
	 * @model opposite="types" required="true" transient="false"
	 * @generated
	 */
	Model getModel();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.Type#getModel <em>Model</em>}' container
	 * reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(Model value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Create an instance of the type
	 * <!-- end-model-doc -->
	 *
	 * @model required="true"
	 * @generated
	 */
	Instance newInstance();

} // Type
