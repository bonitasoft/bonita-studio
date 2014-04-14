/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.validators.descriptor.validator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorFactory
 * @model kind="package"
 * @generated
 */
public interface ValidatorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "validator";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.bonitasoft.org/ns/studio/validator";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "validator";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ValidatorPackage eINSTANCE = org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorDescriptorImpl <em>Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorDescriptorImpl
	 * @see org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorPackageImpl#getValidatorDescriptor()
	 * @generated
	 */
	int VALIDATOR_DESCRIPTOR = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_DESCRIPTOR__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_DESCRIPTOR__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_DESCRIPTOR__CLASS_NAME = 2;

	/**
	 * The feature id for the '<em><b>Has Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_DESCRIPTOR__HAS_PARAMETER = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_DESCRIPTOR__TYPE = 4;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_DESCRIPTOR__DEPENDENCIES = 5;

	/**
	 * The number of structural features of the '<em>Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_DESCRIPTOR_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorType <em>Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorType
	 * @see org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorPackageImpl#getValidatorType()
	 * @generated
	 */
	int VALIDATOR_TYPE = 1;


	/**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor <em>Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Descriptor</em>'.
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor
	 * @generated
	 */
	EClass getValidatorDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getName()
	 * @see #getValidatorDescriptor()
	 * @generated
	 */
	EAttribute getValidatorDescriptor_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getDescription()
	 * @see #getValidatorDescriptor()
	 * @generated
	 */
	EAttribute getValidatorDescriptor_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getClassName()
	 * @see #getValidatorDescriptor()
	 * @generated
	 */
	EAttribute getValidatorDescriptor_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getHasParameter <em>Has Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Parameter</em>'.
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getHasParameter()
	 * @see #getValidatorDescriptor()
	 * @generated
	 */
	EAttribute getValidatorDescriptor_HasParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getType()
	 * @see #getValidatorDescriptor()
	 * @generated
	 */
	EAttribute getValidatorDescriptor_Type();

	/**
	 * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getDependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Dependencies</em>'.
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getDependencies()
	 * @see #getValidatorDescriptor()
	 * @generated
	 */
	EAttribute getValidatorDescriptor_Dependencies();

	/**
	 * Returns the meta object for enum '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type</em>'.
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorType
	 * @generated
	 */
	EEnum getValidatorType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ValidatorFactory getValidatorFactory();

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
		 * The meta object literal for the '{@link org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorDescriptorImpl <em>Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorDescriptorImpl
		 * @see org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorPackageImpl#getValidatorDescriptor()
		 * @generated
		 */
		EClass VALIDATOR_DESCRIPTOR = eINSTANCE.getValidatorDescriptor();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATOR_DESCRIPTOR__NAME = eINSTANCE.getValidatorDescriptor_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATOR_DESCRIPTOR__DESCRIPTION = eINSTANCE.getValidatorDescriptor_Description();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATOR_DESCRIPTOR__CLASS_NAME = eINSTANCE.getValidatorDescriptor_ClassName();

		/**
		 * The meta object literal for the '<em><b>Has Parameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATOR_DESCRIPTOR__HAS_PARAMETER = eINSTANCE.getValidatorDescriptor_HasParameter();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATOR_DESCRIPTOR__TYPE = eINSTANCE.getValidatorDescriptor_Type();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALIDATOR_DESCRIPTOR__DEPENDENCIES = eINSTANCE.getValidatorDescriptor_Dependencies();

		/**
		 * The meta object literal for the '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorType <em>Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorType
		 * @see org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorPackageImpl#getValidatorType()
		 * @generated
		 */
		EEnum VALIDATOR_TYPE = eINSTANCE.getValidatorType();

	}

} //ValidatorPackage
