/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BMW Car IT - Initial API and implementation
 *     Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.spi.migration.impl;

import java.util.Set;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.edapt.internal.migration.DiagnosticException;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.AbstractResource;
import org.eclipse.emf.edapt.spi.migration.AttributeSlot;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.MetamodelResource;
import org.eclipse.emf.edapt.spi.migration.MigrationFactory;
import org.eclipse.emf.edapt.spi.migration.MigrationPackage;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.emf.edapt.spi.migration.ModelResource;
import org.eclipse.emf.edapt.spi.migration.ReferenceSlot;
import org.eclipse.emf.edapt.spi.migration.Repository;
import org.eclipse.emf.edapt.spi.migration.Slot;
import org.eclipse.emf.edapt.spi.migration.Type;
import org.eclipse.emf.edapt.spi.migration.util.MigrationValidator;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MigrationPackageImpl extends EPackageImpl implements MigrationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metamodelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass slotEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeSlotEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceSlotEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass repositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metamodelResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType setEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType diagnosticChainEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType uriEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType diagnosticExceptionEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType migrationExceptionEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.emf.edapt.spi.migration.MigrationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MigrationPackageImpl() {
		super(eNS_URI, MigrationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MigrationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MigrationPackage init() {
		if (isInited) return (MigrationPackage)EPackage.Registry.INSTANCE.getEPackage(MigrationPackage.eNS_URI);

		// Obtain or create and register package
		MigrationPackageImpl theMigrationPackage = (MigrationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MigrationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MigrationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMigrationPackage.createPackageContents();

		// Initialize created meta-data
		theMigrationPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theMigrationPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return MigrationValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theMigrationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MigrationPackage.eNS_URI, theMigrationPackage);
		return theMigrationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModel() {
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Metamodel() {
		return (EReference)modelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Types() {
		return (EReference)modelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModel_Reflection() {
		return (EAttribute)modelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Resources() {
		return (EReference)modelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Repository() {
		return (EReference)modelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetamodel() {
		return metamodelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetamodel_Resources() {
		return (EReference)metamodelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetamodel_Repository() {
		return (EReference)metamodelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetamodel_DefaultPackage() {
		return (EReference)metamodelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getType() {
		return typeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getType_EClass() {
		return (EReference)typeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getType_Instances() {
		return (EReference)typeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getType_Model() {
		return (EReference)typeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstance() {
		return instanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstance_Slots() {
		return (EReference)instanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstance_Type() {
		return (EReference)instanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstance_References() {
		return (EReference)instanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstance_Uri() {
		return (EAttribute)instanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstance_Uuid() {
		return (EAttribute)instanceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSlot() {
		return slotEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSlot_Instance() {
		return (EReference)slotEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeSlot() {
		return attributeSlotEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttributeSlot_EAttribute() {
		return (EReference)attributeSlotEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttributeSlot_Values() {
		return (EAttribute)attributeSlotEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceSlot() {
		return referenceSlotEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceSlot_EReference() {
		return (EReference)referenceSlotEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceSlot_Values() {
		return (EReference)referenceSlotEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRepository() {
		return repositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRepository_Model() {
		return (EReference)repositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRepository_Metamodel() {
		return (EReference)repositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelResource() {
		return modelResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelResource_RootInstances() {
		return (EReference)modelResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelResource_Model() {
		return (EReference)modelResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractResource() {
		return abstractResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractResource_Uri() {
		return (EAttribute)abstractResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractResource_Encoding() {
		return (EAttribute)abstractResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetamodelResource() {
		return metamodelResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetamodelResource_RootPackages() {
		return (EReference)metamodelResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetamodelResource_Metamodel() {
		return (EReference)metamodelResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSet() {
		return setEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDiagnosticChain() {
		return diagnosticChainEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getURI() {
		return uriEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDiagnosticException() {
		return diagnosticExceptionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getMigrationException() {
		return migrationExceptionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MigrationFactory getMigrationFactory() {
		return (MigrationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		repositoryEClass = createEClass(REPOSITORY);
		createEReference(repositoryEClass, REPOSITORY__MODEL);
		createEReference(repositoryEClass, REPOSITORY__METAMODEL);

		modelEClass = createEClass(MODEL);
		createEReference(modelEClass, MODEL__METAMODEL);
		createEReference(modelEClass, MODEL__TYPES);
		createEAttribute(modelEClass, MODEL__REFLECTION);
		createEReference(modelEClass, MODEL__RESOURCES);
		createEReference(modelEClass, MODEL__REPOSITORY);

		modelResourceEClass = createEClass(MODEL_RESOURCE);
		createEReference(modelResourceEClass, MODEL_RESOURCE__ROOT_INSTANCES);
		createEReference(modelResourceEClass, MODEL_RESOURCE__MODEL);

		typeEClass = createEClass(TYPE);
		createEReference(typeEClass, TYPE__ECLASS);
		createEReference(typeEClass, TYPE__INSTANCES);
		createEReference(typeEClass, TYPE__MODEL);

		instanceEClass = createEClass(INSTANCE);
		createEReference(instanceEClass, INSTANCE__SLOTS);
		createEReference(instanceEClass, INSTANCE__TYPE);
		createEReference(instanceEClass, INSTANCE__REFERENCES);
		createEAttribute(instanceEClass, INSTANCE__URI);
		createEAttribute(instanceEClass, INSTANCE__UUID);

		slotEClass = createEClass(SLOT);
		createEReference(slotEClass, SLOT__INSTANCE);

		attributeSlotEClass = createEClass(ATTRIBUTE_SLOT);
		createEReference(attributeSlotEClass, ATTRIBUTE_SLOT__EATTRIBUTE);
		createEAttribute(attributeSlotEClass, ATTRIBUTE_SLOT__VALUES);

		referenceSlotEClass = createEClass(REFERENCE_SLOT);
		createEReference(referenceSlotEClass, REFERENCE_SLOT__EREFERENCE);
		createEReference(referenceSlotEClass, REFERENCE_SLOT__VALUES);

		metamodelEClass = createEClass(METAMODEL);
		createEReference(metamodelEClass, METAMODEL__RESOURCES);
		createEReference(metamodelEClass, METAMODEL__REPOSITORY);
		createEReference(metamodelEClass, METAMODEL__DEFAULT_PACKAGE);

		metamodelResourceEClass = createEClass(METAMODEL_RESOURCE);
		createEReference(metamodelResourceEClass, METAMODEL_RESOURCE__ROOT_PACKAGES);
		createEReference(metamodelResourceEClass, METAMODEL_RESOURCE__METAMODEL);

		abstractResourceEClass = createEClass(ABSTRACT_RESOURCE);
		createEAttribute(abstractResourceEClass, ABSTRACT_RESOURCE__URI);
		createEAttribute(abstractResourceEClass, ABSTRACT_RESOURCE__ENCODING);

		// Create data types
		setEDataType = createEDataType(SET);
		diagnosticChainEDataType = createEDataType(DIAGNOSTIC_CHAIN);
		uriEDataType = createEDataType(URI);
		diagnosticExceptionEDataType = createEDataType(DIAGNOSTIC_EXCEPTION);
		migrationExceptionEDataType = createEDataType(MIGRATION_EXCEPTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters
		addETypeParameter(setEDataType, "E");

		// Set bounds for type parameters

		// Add supertypes to classes
		modelResourceEClass.getESuperTypes().add(this.getAbstractResource());
		attributeSlotEClass.getESuperTypes().add(this.getSlot());
		referenceSlotEClass.getESuperTypes().add(this.getSlot());
		metamodelResourceEClass.getESuperTypes().add(this.getAbstractResource());

		// Initialize classes and features; add operations and parameters
		initEClass(repositoryEClass, Repository.class, "Repository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRepository_Model(), this.getModel(), this.getModel_Repository(), "model", null, 1, 1, Repository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRepository_Metamodel(), this.getMetamodel(), this.getMetamodel_Repository(), "metamodel", null, 1, 1, Repository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModel_Metamodel(), this.getMetamodel(), null, "metamodel", null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Types(), this.getType(), this.getType_Model(), "types", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModel_Reflection(), ecorePackage.getEBoolean(), "reflection", null, 1, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Resources(), this.getModelResource(), this.getModelResource_Model(), "resources", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Repository(), this.getRepository(), this.getRepository_Model(), "repository", null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(modelEClass, this.getInstance(), "getAllInstances", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEClass(), "eClass", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelEClass, this.getInstance(), "getInstances", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEClass(), "eClass", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelEClass, this.getType(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEClass(), "eClass", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelEClass, null, "createExtentMap", 1, 1, IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEClass());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(this.getSet());
		g1.getETypeArguments().add(g2);
		EGenericType g3 = createEGenericType(this.getInstance());
		g2.getETypeArguments().add(g3);
		initEOperation(op, g1);

		op = addEOperation(modelEClass, this.getInstance(), "newInstance", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEClass(), "eClass", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getInstance(), "instance", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelEClass, null, "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getMigrationException());

		op = addEOperation(modelEClass, null, "checkConformance", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getMigrationException());

		op = addEOperation(modelEClass, null, "commit", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getMigrationException());

		op = addEOperation(modelEClass, this.getInstance(), "getAllInstances", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "className", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelEClass, this.getInstance(), "getInstances", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "className", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelEClass, this.getInstance(), "newInstance", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "className", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelEClass, this.getModelResource(), "newResource", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getURI(), "uri", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(modelResourceEClass, ModelResource.class, "ModelResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModelResource_RootInstances(), this.getInstance(), null, "rootInstances", null, 0, -1, ModelResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelResource_Model(), this.getModel(), this.getModel_Resources(), "model", null, 0, 1, ModelResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeEClass, Type.class, "Type", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getType_EClass(), ecorePackage.getEClass(), null, "eClass", null, 1, 1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getType_Instances(), this.getInstance(), this.getInstance_Type(), "instances", null, 0, -1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getType_Model(), this.getModel(), this.getModel_Types(), "model", null, 1, 1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(typeEClass, this.getInstance(), "newInstance", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(instanceEClass, Instance.class, "Instance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstance_Slots(), this.getSlot(), this.getSlot_Instance(), "slots", null, 0, -1, Instance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstance_Type(), this.getType(), this.getType_Instances(), "type", null, 1, 1, Instance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstance_References(), this.getReferenceSlot(), this.getReferenceSlot_Values(), "references", null, 0, -1, Instance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInstance_Uri(), this.getURI(), "uri", null, 0, 1, Instance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInstance_Uuid(), ecorePackage.getEString(), "uuid", null, 0, 1, Instance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "get", 0, 1, IS_UNIQUE, IS_ORDERED);
		ETypeParameter t1 = addETypeParameter(op, "V");
		addEParameter(op, ecorePackage.getEStructuralFeature(), "feature", 1, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = addEOperation(instanceEClass, null, "get", 0, 1, IS_UNIQUE, IS_ORDERED);
		t1 = addETypeParameter(op, "V");
		addEParameter(op, ecorePackage.getEString(), "featureName", 1, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = addEOperation(instanceEClass, this.getInstance(), "getInverse", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "reference", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, this.getSlot(), "getSlot", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEStructuralFeature(), "feature", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(instanceEClass, ecorePackage.getEClass(), "getEClass", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "set", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEStructuralFeature(), "feature", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "set", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "featureName", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, ecorePackage.getEBoolean(), "isSet", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEStructuralFeature(), "feature", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "unset", 0, 1, IS_UNIQUE, IS_ORDERED);
		t1 = addETypeParameter(op, "V");
		addEParameter(op, ecorePackage.getEStructuralFeature(), "feature", 1, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = addEOperation(instanceEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEStructuralFeature(), "feature", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "index", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "value", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEStructuralFeature(), "feature", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "value", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "remove", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEStructuralFeature(), "feature", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "value", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "remove", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEStructuralFeature(), "feature", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "index", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "migrate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEClass(), "eClass", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, ecorePackage.getEBoolean(), "instanceOf", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEClass(), "eClass", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(instanceEClass, this.getInstance(), "getContainer", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(instanceEClass, this.getInstance(), "getContents", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(instanceEClass, null, "validate", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, ecorePackage.getEBoolean(), "validate", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDiagnosticChain(), "chain", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "evaluate", 0, 1, IS_UNIQUE, IS_ORDERED);
		t1 = addETypeParameter(op, "V");
		addEParameter(op, ecorePackage.getEString(), "expression", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getMigrationException());
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		addEOperation(instanceEClass, ecorePackage.getEReference(), "getContainerReference", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(instanceEClass, this.getModelResource(), "getResource", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(instanceEClass, ecorePackage.getEBoolean(), "isProxy", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "migrate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "className", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, this.getInstance(), "getInverse", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "referenceName", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, this.getInstance(), "getLink", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "referenceName", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, this.getInstance(), "getLinks", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "referenceName", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, ecorePackage.getEBoolean(), "instanceOf", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "className", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "featureName", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "value", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "remove", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "featureName", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "value", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "featureName", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "index", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "value", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, this.getInstance(), "getLink", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "reference", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(instanceEClass, this.getInstance(), "getLinks", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "reference", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(instanceEClass, this.getInstance(), "copy", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(slotEClass, Slot.class, "Slot", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSlot_Instance(), this.getInstance(), this.getInstance_Slots(), "instance", null, 1, 1, Slot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(slotEClass, ecorePackage.getEStructuralFeature(), "getEFeature", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(attributeSlotEClass, AttributeSlot.class, "AttributeSlot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttributeSlot_EAttribute(), ecorePackage.getEAttribute(), null, "eAttribute", null, 1, 1, AttributeSlot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributeSlot_Values(), ecorePackage.getEJavaObject(), "values", null, 0, -1, AttributeSlot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceSlotEClass, ReferenceSlot.class, "ReferenceSlot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferenceSlot_EReference(), ecorePackage.getEReference(), null, "eReference", null, 1, 1, ReferenceSlot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReferenceSlot_Values(), this.getInstance(), this.getInstance_References(), "values", null, 0, -1, ReferenceSlot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metamodelEClass, Metamodel.class, "Metamodel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetamodel_Resources(), this.getMetamodelResource(), this.getMetamodelResource_Metamodel(), "resources", null, 0, -1, Metamodel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetamodel_Repository(), this.getRepository(), this.getRepository_Metamodel(), "repository", null, 0, 1, Metamodel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetamodel_DefaultPackage(), ecorePackage.getEPackage(), null, "defaultPackage", null, 0, 1, Metamodel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(metamodelEClass, ecorePackage.getEPackage(), "getEPackage", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, ecorePackage.getEClassifier(), "getEClassifier", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, ecorePackage.getEStructuralFeature(), "getEFeature", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, ecorePackage.getEClass(), "getEClass", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, ecorePackage.getEReference(), "getEReference", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, ecorePackage.getEAttribute(), "getEAttribute", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, ecorePackage.getEDataType(), "getEDataType", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, ecorePackage.getEModelElement(), "getElement", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, null, "validate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getMigrationException());

		addEOperation(metamodelEClass, ecorePackage.getEPackage(), "getEPackages", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, null, "setDefaultPackage", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "packageName", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, ecorePackage.getEEnum(), "getEEnum", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, ecorePackage.getEEnumLiteral(), "getEEnumLiteral", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEModelElement(), "metamodelElement", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, null, "getInverse", 0, -1, IS_UNIQUE, IS_ORDERED);
		t1 = addETypeParameter(op, "V");
		addEParameter(op, ecorePackage.getEModelElement(), "metamodelElement", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "reference", 1, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = addEOperation(metamodelEClass, ecorePackage.getEClass(), "getESubTypes", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEClass(), "eClass", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, ecorePackage.getEClass(), "getEAllSubTypes", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEClass(), "eClass", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(metamodelEClass, null, "setEOpposite", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "reference", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "opposite", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(metamodelResourceEClass, MetamodelResource.class, "MetamodelResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetamodelResource_RootPackages(), ecorePackage.getEPackage(), null, "rootPackages", null, 0, -1, MetamodelResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetamodelResource_Metamodel(), this.getMetamodel(), this.getMetamodel_Resources(), "metamodel", null, 0, 1, MetamodelResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractResourceEClass, AbstractResource.class, "AbstractResource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractResource_Uri(), this.getURI(), "uri", null, 1, 1, AbstractResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractResource_Encoding(), ecorePackage.getEString(), "encoding", null, 0, 1, AbstractResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(setEDataType, Set.class, "Set", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(diagnosticChainEDataType, DiagnosticChain.class, "DiagnosticChain", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(uriEDataType, org.eclipse.emf.common.util.URI.class, "URI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(diagnosticExceptionEDataType, DiagnosticException.class, "DiagnosticException", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(migrationExceptionEDataType, MigrationException.class, "MigrationException", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";																																
		addAnnotation
		  (instanceEClass, 
		   source, 
		   new String[] {
			 "constraints", "validContainment validType"
		   });																																											
		addAnnotation
		  (slotEClass, 
		   source, 
		   new String[] {
			 "constraints", "validFeature validMultiplicity"
		   });								
		addAnnotation
		  (referenceSlotEClass, 
		   source, 
		   new String[] {
			 "constraints", "validType noDanglingReference validOpposite"
		   });																																		
	}

} //MigrationPackageImpl
