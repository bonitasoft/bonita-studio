/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.validators.descriptor.validator.impl;

import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorFactory;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorType;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ValidatorPackageImpl extends EPackageImpl implements ValidatorPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validatorDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum validatorTypeEEnum = null;

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
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ValidatorPackageImpl() {
		super(eNS_URI, ValidatorFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ValidatorPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ValidatorPackage init() {
		if (isInited) return (ValidatorPackage)EPackage.Registry.INSTANCE.getEPackage(ValidatorPackage.eNS_URI);

		// Obtain or create and register package
		ValidatorPackageImpl theValidatorPackage = (ValidatorPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ValidatorPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ValidatorPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theValidatorPackage.createPackageContents();

		// Initialize created meta-data
		theValidatorPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theValidatorPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ValidatorPackage.eNS_URI, theValidatorPackage);
		return theValidatorPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValidatorDescriptor() {
		return validatorDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValidatorDescriptor_Name() {
		return (EAttribute)validatorDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValidatorDescriptor_Description() {
		return (EAttribute)validatorDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValidatorDescriptor_ClassName() {
		return (EAttribute)validatorDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValidatorDescriptor_HasParameter() {
		return (EAttribute)validatorDescriptorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValidatorDescriptor_Type() {
		return (EAttribute)validatorDescriptorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValidatorDescriptor_Dependencies() {
		return (EAttribute)validatorDescriptorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getValidatorType() {
		return validatorTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidatorFactory getValidatorFactory() {
		return (ValidatorFactory)getEFactoryInstance();
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
		validatorDescriptorEClass = createEClass(VALIDATOR_DESCRIPTOR);
		createEAttribute(validatorDescriptorEClass, VALIDATOR_DESCRIPTOR__NAME);
		createEAttribute(validatorDescriptorEClass, VALIDATOR_DESCRIPTOR__DESCRIPTION);
		createEAttribute(validatorDescriptorEClass, VALIDATOR_DESCRIPTOR__CLASS_NAME);
		createEAttribute(validatorDescriptorEClass, VALIDATOR_DESCRIPTOR__HAS_PARAMETER);
		createEAttribute(validatorDescriptorEClass, VALIDATOR_DESCRIPTOR__TYPE);
		createEAttribute(validatorDescriptorEClass, VALIDATOR_DESCRIPTOR__DEPENDENCIES);

		// Create enums
		validatorTypeEEnum = createEEnum(VALIDATOR_TYPE);
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

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(validatorDescriptorEClass, ValidatorDescriptor.class, "ValidatorDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValidatorDescriptor_Name(), ecorePackage.getEString(), "name", null, 0, 1, ValidatorDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidatorDescriptor_Description(), ecorePackage.getEString(), "description", null, 0, 1, ValidatorDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidatorDescriptor_ClassName(), ecorePackage.getEString(), "className", null, 0, 1, ValidatorDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidatorDescriptor_HasParameter(), ecorePackage.getEBooleanObject(), "hasParameter", "false", 0, 1, ValidatorDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidatorDescriptor_Type(), this.getValidatorType(), "type", "0", 0, 1, ValidatorDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValidatorDescriptor_Dependencies(), ecorePackage.getEString(), "dependencies", null, 0, -1, ValidatorDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(validatorTypeEEnum, ValidatorType.class, "ValidatorType");
		addEEnumLiteral(validatorTypeEEnum, ValidatorType.FILED_VALIDATOR);
		addEEnumLiteral(validatorTypeEEnum, ValidatorType.PAGE_VALIDATOR);

		// Create resource
		createResource(eNS_URI);
	}

} //ValidatorPackageImpl
