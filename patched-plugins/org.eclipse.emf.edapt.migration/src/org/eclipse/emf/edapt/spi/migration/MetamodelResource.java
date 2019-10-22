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
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Metamodel Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Resource for metamodel parts
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.MetamodelResource#getRootPackages <em>Root Packages</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.MetamodelResource#getMetamodel <em>Metamodel</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getMetamodelResource()
 * @model
 * @generated
 */
public interface MetamodelResource extends AbstractResource {
	/**
	 * Returns the value of the '<em><b>Root Packages</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Packages</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Packages that constitute the root of a metamodel resource
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Root Packages</em>' reference list.
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getMetamodelResource_RootPackages()
	 * @model
	 * @generated
	 */
	EList<EPackage> getRootPackages();

	/**
	 * Returns the value of the '<em><b>Metamodel</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.edapt.spi.migration.Metamodel#getResources
	 * <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metamodel</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Metamodel</em>' container reference.
	 * @see #setMetamodel(Metamodel)
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#getMetamodelResource_Metamodel()
	 * @see org.eclipse.emf.edapt.spi.migration.Metamodel#getResources
	 * @model opposite="resources" transient="false"
	 * @generated
	 */
	Metamodel getMetamodel();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.edapt.spi.migration.MetamodelResource#getMetamodel
	 * <em>Metamodel</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value the new value of the '<em>Metamodel</em>' container reference.
	 * @see #getMetamodel()
	 * @generated
	 */
	void setMetamodel(Metamodel value);

} // MetamodelResource
