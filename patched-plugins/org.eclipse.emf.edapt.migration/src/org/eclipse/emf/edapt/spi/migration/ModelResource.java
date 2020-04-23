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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Resource for model parts
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.ModelResource#getRootInstances <em>Root Instances</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.ModelResource#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getModelResource()
 * @model
 * @generated
 */
public interface ModelResource extends AbstractResource {
	/**
	 * Returns the value of the '<em><b>Root Instances</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.edapt.spi.migration.Instance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Instances</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The instances which are the root elements of the resource
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Root Instances</em>' reference list.
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getModelResource_RootInstances()
	 * @model
	 * @generated
	 */
	EList<Instance> getRootInstances();

	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.Model#getResources
	 * <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(Model)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getModelResource_Model()
	 * @see org.eclipse.emf.edapt.spi.migration.Model#getResources
	 * @model opposite="resources" transient="false"
	 * @generated
	 */
	Model getModel();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.ModelResource#getModel <em>Model</em>}'
	 * container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(Model value);

} // ModelResource
