/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.spi.migration;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Metamodel to represent models during migration
 * <!-- end-model-doc -->
 *
 * @see org.eclipse.emf.edapt.spi.migration.MigrationFactory
 * @model kind="package"
 * @generated
 */
public interface MigrationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	String eNAME = "migration"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/emf/edapt/migration/0.3"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	String eNS_PREFIX = "migration"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	MigrationPackage eINSTANCE = org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.emf.edapt.spi.migration.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.spi.migration.impl.ModelImpl
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.edapt.spi.migration.impl.MetamodelImpl <em>Metamodel</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MetamodelImpl
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getMetamodel()
	 * @generated
	 */
	int METAMODEL = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.edapt.spi.migration.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.spi.migration.impl.TypeImpl
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.edapt.spi.migration.impl.InstanceImpl <em>Instance</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.spi.migration.impl.InstanceImpl
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getInstance()
	 * @generated
	 */
	int INSTANCE = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.edapt.spi.migration.impl.SlotImpl <em>Slot</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.spi.migration.impl.SlotImpl
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getSlot()
	 * @generated
	 */
	int SLOT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.edapt.spi.migration.impl.AttributeSlotImpl
	 * <em>Attribute Slot</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.spi.migration.impl.AttributeSlotImpl
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getAttributeSlot()
	 * @generated
	 */
	int ATTRIBUTE_SLOT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.edapt.spi.migration.impl.ReferenceSlotImpl
	 * <em>Reference Slot</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.spi.migration.impl.ReferenceSlotImpl
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getReferenceSlot()
	 * @generated
	 */
	int REFERENCE_SLOT = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.edapt.spi.migration.impl.RepositoryImpl <em>Repository</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.spi.migration.impl.RepositoryImpl
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getRepository()
	 * @generated
	 */
	int REPOSITORY = 0;

	/**
	 * The feature id for the '<em><b>Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__MODEL = 0;

	/**
	 * The feature id for the '<em><b>Metamodel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__METAMODEL = 1;

	/**
	 * The number of structural features of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Metamodel</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int MODEL__METAMODEL = 0;

	/**
	 * The feature id for the '<em><b>Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int MODEL__TYPES = 1;

	/**
	 * The feature id for the '<em><b>Reflection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int MODEL__REFLECTION = 2;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int MODEL__RESOURCES = 3;

	/**
	 * The feature id for the '<em><b>Repository</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int MODEL__REPOSITORY = 4;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.edapt.spi.migration.impl.AbstractResourceImpl
	 * <em>Abstract Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.spi.migration.impl.AbstractResourceImpl
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getAbstractResource()
	 * @generated
	 */
	int ABSTRACT_RESOURCE = 10;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RESOURCE__URI = 0;

	/**
	 * The feature id for the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RESOURCE__ENCODING = 1;

	/**
	 * The number of structural features of the '<em>Abstract Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RESOURCE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.edapt.spi.migration.impl.ModelResourceImpl
	 * <em>Model Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.spi.migration.impl.ModelResourceImpl
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getModelResource()
	 * @generated
	 */
	int MODEL_RESOURCE = 2;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE__URI = ABSTRACT_RESOURCE__URI;

	/**
	 * The feature id for the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE__ENCODING = ABSTRACT_RESOURCE__ENCODING;

	/**
	 * The feature id for the '<em><b>Root Instances</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE__ROOT_INSTANCES = ABSTRACT_RESOURCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE__MODEL = ABSTRACT_RESOURCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Model Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE_FEATURE_COUNT = ABSTRACT_RESOURCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>EClass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int TYPE__ECLASS = 0;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int TYPE__INSTANCES = 1;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int TYPE__MODEL = 2;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int TYPE_FEATURE_COUNT = 3;

	/**
	 * The feature id for the '<em><b>Slots</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int INSTANCE__SLOTS = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int INSTANCE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>References</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int INSTANCE__REFERENCES = 2;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int INSTANCE__URI = 3;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int INSTANCE__UUID = 4;

	/**
	 * The number of structural features of the '<em>Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int INSTANCE_FEATURE_COUNT = 5;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int SLOT__INSTANCE = 0;

	/**
	 * The number of structural features of the '<em>Slot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int SLOT_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_SLOT__INSTANCE = SLOT__INSTANCE;

	/**
	 * The feature id for the '<em><b>EAttribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_SLOT__EATTRIBUTE = SLOT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_SLOT__VALUES = SLOT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Attribute Slot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_SLOT_FEATURE_COUNT = SLOT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int REFERENCE_SLOT__INSTANCE = SLOT__INSTANCE;

	/**
	 * The feature id for the '<em><b>EReference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int REFERENCE_SLOT__EREFERENCE = SLOT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int REFERENCE_SLOT__VALUES = SLOT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Reference Slot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int REFERENCE_SLOT_FEATURE_COUNT = SLOT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int METAMODEL__RESOURCES = 0;

	/**
	 * The feature id for the '<em><b>Repository</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int METAMODEL__REPOSITORY = 1;

	/**
	 * The feature id for the '<em><b>Default Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int METAMODEL__DEFAULT_PACKAGE = 2;

	/**
	 * The number of structural features of the '<em>Metamodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int METAMODEL_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.edapt.spi.migration.impl.MetamodelResourceImpl
	 * <em>Metamodel Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MetamodelResourceImpl
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getMetamodelResource()
	 * @generated
	 */
	int METAMODEL_RESOURCE = 9;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int METAMODEL_RESOURCE__URI = ABSTRACT_RESOURCE__URI;

	/**
	 * The feature id for the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int METAMODEL_RESOURCE__ENCODING = ABSTRACT_RESOURCE__ENCODING;

	/**
	 * The feature id for the '<em><b>Root Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int METAMODEL_RESOURCE__ROOT_PACKAGES = ABSTRACT_RESOURCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Metamodel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int METAMODEL_RESOURCE__METAMODEL = ABSTRACT_RESOURCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Metamodel Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int METAMODEL_RESOURCE_FEATURE_COUNT = ABSTRACT_RESOURCE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '<em>Set</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see java.util.Set
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getSet()
	 * @generated
	 */
	int SET = 11;

	/**
	 * The meta object id for the '<em>Diagnostic Chain</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.common.util.DiagnosticChain
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getDiagnosticChain()
	 * @generated
	 */
	int DIAGNOSTIC_CHAIN = 12;

	/**
	 * The meta object id for the '<em>URI</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.common.util.URI
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getURI()
	 * @generated
	 */
	int URI = 13;

	/**
	 * The meta object id for the '<em>Diagnostic Exception</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.internal.migration.DiagnosticException
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getDiagnosticException()
	 * @generated
	 */
	int DIAGNOSTIC_EXCEPTION = 14;

	/**
	 * The meta object id for the '<em>Exception</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.edapt.migration.MigrationException
	 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getMigrationException()
	 * @generated
	 */
	int MIGRATION_EXCEPTION = 15;

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.edapt.spi.migration.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Model</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.edapt.spi.migration.Model#getMetamodel
	 * <em>Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference '<em>Metamodel</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Model#getMetamodel()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Metamodel();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.emf.edapt.spi.migration.Model#getTypes <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference list '<em>Types</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Model#getTypes()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Types();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.edapt.spi.migration.Model#isReflection
	 * <em>Reflection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Reflection</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Model#isReflection()
	 * @see #getModel()
	 * @generated
	 */
	EAttribute getModel_Reflection();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.emf.edapt.spi.migration.Model#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference list '<em>Resources</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Model#getResources()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Resources();

	/**
	 * Returns the meta object for the container reference '
	 * {@link org.eclipse.emf.edapt.spi.migration.Model#getRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the container reference '<em>Repository</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Model#getRepository()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Repository();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.edapt.spi.migration.Metamodel <em>Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Metamodel</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Metamodel
	 * @generated
	 */
	EClass getMetamodel();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.emf.edapt.spi.migration.Metamodel#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference list '<em>Resources</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Metamodel#getResources()
	 * @see #getMetamodel()
	 * @generated
	 */
	EReference getMetamodel_Resources();

	/**
	 * Returns the meta object for the container reference '
	 * {@link org.eclipse.emf.edapt.spi.migration.Metamodel#getRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the container reference '<em>Repository</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Metamodel#getRepository()
	 * @see #getMetamodel()
	 * @generated
	 */
	EReference getMetamodel_Repository();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.emf.edapt.spi.migration.Metamodel#getDefaultPackage <em>Default Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference '<em>Default Package</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Metamodel#getDefaultPackage()
	 * @see #getMetamodel()
	 * @generated
	 */
	EReference getMetamodel_DefaultPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.edapt.spi.migration.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Type</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.edapt.spi.migration.Type#getEClass
	 * <em>EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference '<em>EClass</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Type#getEClass()
	 * @see #getType()
	 * @generated
	 */
	EReference getType_EClass();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.emf.edapt.spi.migration.Type#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference list '<em>Instances</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Type#getInstances()
	 * @see #getType()
	 * @generated
	 */
	EReference getType_Instances();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.emf.edapt.spi.migration.Type#getModel
	 * <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Type#getModel()
	 * @see #getType()
	 * @generated
	 */
	EReference getType_Model();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.edapt.spi.migration.Instance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Instance</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Instance
	 * @generated
	 */
	EClass getInstance();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.emf.edapt.spi.migration.Instance#getSlots <em>Slots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference list '<em>Slots</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Instance#getSlots()
	 * @see #getInstance()
	 * @generated
	 */
	EReference getInstance_Slots();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.emf.edapt.spi.migration.Instance#getType
	 * <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the container reference '<em>Type</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Instance#getType()
	 * @see #getInstance()
	 * @generated
	 */
	EReference getInstance_Type();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.emf.edapt.spi.migration.Instance#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference list '<em>References</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Instance#getReferences()
	 * @see #getInstance()
	 * @generated
	 */
	EReference getInstance_References();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.edapt.spi.migration.Instance#getUri
	 * <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Instance#getUri()
	 * @see #getInstance()
	 * @generated
	 */
	EAttribute getInstance_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.edapt.spi.migration.Instance#getUuid
	 * <em>Uuid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Uuid</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Instance#getUuid()
	 * @see #getInstance()
	 * @generated
	 */
	EAttribute getInstance_Uuid();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.edapt.spi.migration.Slot <em>Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Slot</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Slot
	 * @generated
	 */
	EClass getSlot();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.emf.edapt.spi.migration.Slot#getInstance
	 * <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the container reference '<em>Instance</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Slot#getInstance()
	 * @see #getSlot()
	 * @generated
	 */
	EReference getSlot_Instance();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.edapt.spi.migration.AttributeSlot
	 * <em>Attribute Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Attribute Slot</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.AttributeSlot
	 * @generated
	 */
	EClass getAttributeSlot();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.emf.edapt.spi.migration.AttributeSlot#getEAttribute <em>EAttribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference '<em>EAttribute</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.AttributeSlot#getEAttribute()
	 * @see #getAttributeSlot()
	 * @generated
	 */
	EReference getAttributeSlot_EAttribute();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.emf.edapt.spi.migration.AttributeSlot#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute list '<em>Values</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.AttributeSlot#getValues()
	 * @see #getAttributeSlot()
	 * @generated
	 */
	EAttribute getAttributeSlot_Values();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.edapt.spi.migration.ReferenceSlot
	 * <em>Reference Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Reference Slot</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.ReferenceSlot
	 * @generated
	 */
	EClass getReferenceSlot();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.emf.edapt.spi.migration.ReferenceSlot#getEReference <em>EReference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference '<em>EReference</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.ReferenceSlot#getEReference()
	 * @see #getReferenceSlot()
	 * @generated
	 */
	EReference getReferenceSlot_EReference();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.emf.edapt.spi.migration.ReferenceSlot#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference list '<em>Values</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.ReferenceSlot#getValues()
	 * @see #getReferenceSlot()
	 * @generated
	 */
	EReference getReferenceSlot_Values();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.edapt.spi.migration.Repository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Repository</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Repository
	 * @generated
	 */
	EClass getRepository();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.edapt.spi.migration.Repository#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference '<em>Model</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Repository#getModel()
	 * @see #getRepository()
	 * @generated
	 */
	EReference getRepository_Model();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.edapt.spi.migration.Repository#getMetamodel <em>Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference '<em>Metamodel</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.Repository#getMetamodel()
	 * @see #getRepository()
	 * @generated
	 */
	EReference getRepository_Metamodel();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.edapt.spi.migration.ModelResource
	 * <em>Model Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Model Resource</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.ModelResource
	 * @generated
	 */
	EClass getModelResource();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.emf.edapt.spi.migration.ModelResource#getRootInstances <em>Root Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference list '<em>Root Instances</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.ModelResource#getRootInstances()
	 * @see #getModelResource()
	 * @generated
	 */
	EReference getModelResource_RootInstances();

	/**
	 * Returns the meta object for the container reference '
	 * {@link org.eclipse.emf.edapt.spi.migration.ModelResource#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.ModelResource#getModel()
	 * @see #getModelResource()
	 * @generated
	 */
	EReference getModelResource_Model();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.edapt.spi.migration.AbstractResource
	 * <em>Abstract Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Abstract Resource</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.AbstractResource
	 * @generated
	 */
	EClass getAbstractResource();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.edapt.spi.migration.AbstractResource#getUri
	 * <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.AbstractResource#getUri()
	 * @see #getAbstractResource()
	 * @generated
	 */
	EAttribute getAbstractResource_Uri();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.emf.edapt.spi.migration.AbstractResource#getEncoding <em>Encoding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Encoding</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.AbstractResource#getEncoding()
	 * @see #getAbstractResource()
	 * @generated
	 */
	EAttribute getAbstractResource_Encoding();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.edapt.spi.migration.MetamodelResource
	 * <em>Metamodel Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Metamodel Resource</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.MetamodelResource
	 * @generated
	 */
	EClass getMetamodelResource();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.emf.edapt.spi.migration.MetamodelResource#getRootPackages <em>Root Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference list '<em>Root Packages</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.MetamodelResource#getRootPackages()
	 * @see #getMetamodelResource()
	 * @generated
	 */
	EReference getMetamodelResource_RootPackages();

	/**
	 * Returns the meta object for the container reference '
	 * {@link org.eclipse.emf.edapt.spi.migration.MetamodelResource#getMetamodel <em>Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the container reference '<em>Metamodel</em>'.
	 * @see org.eclipse.emf.edapt.spi.migration.MetamodelResource#getMetamodel()
	 * @see #getMetamodelResource()
	 * @generated
	 */
	EReference getMetamodelResource_Metamodel();

	/**
	 * Returns the meta object for data type '{@link java.util.Set <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for data type '<em>Set</em>'.
	 * @see java.util.Set
	 * @model instanceClass="java.util.Set" serializeable="false" typeParameters="E"
	 * @generated
	 */
	EDataType getSet();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.util.DiagnosticChain
	 * <em>Diagnostic Chain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for data type '<em>Diagnostic Chain</em>'.
	 * @see org.eclipse.emf.common.util.DiagnosticChain
	 * @model instanceClass="org.eclipse.emf.common.util.DiagnosticChain"
	 * @generated
	 */
	EDataType getDiagnosticChain();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.util.URI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for data type '<em>URI</em>'.
	 * @see org.eclipse.emf.common.util.URI
	 * @model instanceClass="org.eclipse.emf.common.util.URI"
	 * @generated
	 */
	EDataType getURI();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.edapt.internal.migration.DiagnosticException
	 * <em>Diagnostic Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for data type '<em>Diagnostic Exception</em>'.
	 * @see org.eclipse.emf.edapt.internal.migration.DiagnosticException
	 * @model instanceClass="org.eclipse.emf.edapt.migration.DiagnosticException"
	 * @generated
	 */
	EDataType getDiagnosticException();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.edapt.migration.MigrationException
	 * <em>Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for data type '<em>Exception</em>'.
	 * @see org.eclipse.emf.edapt.migration.MigrationException
	 * @model instanceClass="org.eclipse.emf.edapt.migration.MigrationException" serializeable="false"
	 * @generated
	 */
	EDataType getMigrationException();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MigrationFactory getMigrationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.edapt.spi.migration.impl.ModelImpl <em>Model</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.spi.migration.impl.ModelImpl
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Metamodel</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference MODEL__METAMODEL = eINSTANCE.getModel_Metamodel();

		/**
		 * The meta object literal for the '<em><b>Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference MODEL__TYPES = eINSTANCE.getModel_Types();

		/**
		 * The meta object literal for the '<em><b>Reflection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute MODEL__REFLECTION = eINSTANCE.getModel_Reflection();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference MODEL__RESOURCES = eINSTANCE.getModel_Resources();

		/**
		 * The meta object literal for the '<em><b>Repository</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference MODEL__REPOSITORY = eINSTANCE.getModel_Repository();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.edapt.spi.migration.impl.MetamodelImpl
		 * <em>Metamodel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MetamodelImpl
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getMetamodel()
		 * @generated
		 */
		EClass METAMODEL = eINSTANCE.getMetamodel();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference METAMODEL__RESOURCES = eINSTANCE.getMetamodel_Resources();

		/**
		 * The meta object literal for the '<em><b>Repository</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference METAMODEL__REPOSITORY = eINSTANCE.getMetamodel_Repository();

		/**
		 * The meta object literal for the '<em><b>Default Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference METAMODEL__DEFAULT_PACKAGE = eINSTANCE.getMetamodel_DefaultPackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.edapt.spi.migration.impl.TypeImpl <em>Type</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.spi.migration.impl.TypeImpl
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '<em><b>EClass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference TYPE__ECLASS = eINSTANCE.getType_EClass();

		/**
		 * The meta object literal for the '<em><b>Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference TYPE__INSTANCES = eINSTANCE.getType_Instances();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference TYPE__MODEL = eINSTANCE.getType_Model();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.edapt.spi.migration.impl.InstanceImpl
		 * <em>Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.spi.migration.impl.InstanceImpl
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getInstance()
		 * @generated
		 */
		EClass INSTANCE = eINSTANCE.getInstance();

		/**
		 * The meta object literal for the '<em><b>Slots</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference INSTANCE__SLOTS = eINSTANCE.getInstance_Slots();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference INSTANCE__TYPE = eINSTANCE.getInstance_Type();

		/**
		 * The meta object literal for the '<em><b>References</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference INSTANCE__REFERENCES = eINSTANCE.getInstance_References();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute INSTANCE__URI = eINSTANCE.getInstance_Uri();

		/**
		 * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute INSTANCE__UUID = eINSTANCE.getInstance_Uuid();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.edapt.spi.migration.impl.SlotImpl <em>Slot</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.spi.migration.impl.SlotImpl
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getSlot()
		 * @generated
		 */
		EClass SLOT = eINSTANCE.getSlot();

		/**
		 * The meta object literal for the '<em><b>Instance</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference SLOT__INSTANCE = eINSTANCE.getSlot_Instance();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.edapt.spi.migration.impl.AttributeSlotImpl
		 * <em>Attribute Slot</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.spi.migration.impl.AttributeSlotImpl
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getAttributeSlot()
		 * @generated
		 */
		EClass ATTRIBUTE_SLOT = eINSTANCE.getAttributeSlot();

		/**
		 * The meta object literal for the '<em><b>EAttribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference ATTRIBUTE_SLOT__EATTRIBUTE = eINSTANCE.getAttributeSlot_EAttribute();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute ATTRIBUTE_SLOT__VALUES = eINSTANCE.getAttributeSlot_Values();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.edapt.spi.migration.impl.ReferenceSlotImpl
		 * <em>Reference Slot</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.spi.migration.impl.ReferenceSlotImpl
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getReferenceSlot()
		 * @generated
		 */
		EClass REFERENCE_SLOT = eINSTANCE.getReferenceSlot();

		/**
		 * The meta object literal for the '<em><b>EReference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference REFERENCE_SLOT__EREFERENCE = eINSTANCE.getReferenceSlot_EReference();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference REFERENCE_SLOT__VALUES = eINSTANCE.getReferenceSlot_Values();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.edapt.spi.migration.impl.RepositoryImpl
		 * <em>Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.spi.migration.impl.RepositoryImpl
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getRepository()
		 * @generated
		 */
		EClass REPOSITORY = eINSTANCE.getRepository();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference REPOSITORY__MODEL = eINSTANCE.getRepository_Model();

		/**
		 * The meta object literal for the '<em><b>Metamodel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference REPOSITORY__METAMODEL = eINSTANCE.getRepository_Metamodel();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.edapt.spi.migration.impl.ModelResourceImpl
		 * <em>Model Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.spi.migration.impl.ModelResourceImpl
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getModelResource()
		 * @generated
		 */
		EClass MODEL_RESOURCE = eINSTANCE.getModelResource();

		/**
		 * The meta object literal for the '<em><b>Root Instances</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference MODEL_RESOURCE__ROOT_INSTANCES = eINSTANCE.getModelResource_RootInstances();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference MODEL_RESOURCE__MODEL = eINSTANCE.getModelResource_Model();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.edapt.spi.migration.impl.AbstractResourceImpl
		 * <em>Abstract Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.spi.migration.impl.AbstractResourceImpl
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getAbstractResource()
		 * @generated
		 */
		EClass ABSTRACT_RESOURCE = eINSTANCE.getAbstractResource();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute ABSTRACT_RESOURCE__URI = eINSTANCE.getAbstractResource_Uri();

		/**
		 * The meta object literal for the '<em><b>Encoding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute ABSTRACT_RESOURCE__ENCODING = eINSTANCE.getAbstractResource_Encoding();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.edapt.spi.migration.impl.MetamodelResourceImpl
		 * <em>Metamodel Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MetamodelResourceImpl
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getMetamodelResource()
		 * @generated
		 */
		EClass METAMODEL_RESOURCE = eINSTANCE.getMetamodelResource();

		/**
		 * The meta object literal for the '<em><b>Root Packages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference METAMODEL_RESOURCE__ROOT_PACKAGES = eINSTANCE.getMetamodelResource_RootPackages();

		/**
		 * The meta object literal for the '<em><b>Metamodel</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference METAMODEL_RESOURCE__METAMODEL = eINSTANCE.getMetamodelResource_Metamodel();

		/**
		 * The meta object literal for the '<em>Set</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see java.util.Set
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getSet()
		 * @generated
		 */
		EDataType SET = eINSTANCE.getSet();

		/**
		 * The meta object literal for the '<em>Diagnostic Chain</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.common.util.DiagnosticChain
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getDiagnosticChain()
		 * @generated
		 */
		EDataType DIAGNOSTIC_CHAIN = eINSTANCE.getDiagnosticChain();

		/**
		 * The meta object literal for the '<em>URI</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.common.util.URI
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getURI()
		 * @generated
		 */
		EDataType URI = eINSTANCE.getURI();

		/**
		 * The meta object literal for the '<em>Diagnostic Exception</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.internal.migration.DiagnosticException
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getDiagnosticException()
		 * @generated
		 */
		EDataType DIAGNOSTIC_EXCEPTION = eINSTANCE.getDiagnosticException();

		/**
		 * The meta object literal for the '<em>Exception</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.emf.edapt.migration.MigrationException
		 * @see org.eclipse.emf.edapt.spi.migration.impl.MigrationPackageImpl#getMigrationException()
		 * @generated
		 */
		EDataType MIGRATION_EXCEPTION = eINSTANCE.getMigrationException();

	}

} // MigrationPackage
