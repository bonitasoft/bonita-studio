/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.validators.descriptor.validator.impl;

import org.bonitasoft.studio.validators.descriptor.validator.*;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorFactory;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorType;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ValidatorFactoryImpl extends EFactoryImpl implements ValidatorFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ValidatorFactory init() {
		try {
			ValidatorFactory theValidatorFactory = (ValidatorFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.bonitasoft.org/ns/studio/validator"); 
			if (theValidatorFactory != null) {
				return theValidatorFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ValidatorFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidatorFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ValidatorPackage.VALIDATOR_DESCRIPTOR: return createValidatorDescriptor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ValidatorPackage.VALIDATOR_TYPE:
				return createValidatorTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ValidatorPackage.VALIDATOR_TYPE:
				return convertValidatorTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidatorDescriptor createValidatorDescriptor() {
		ValidatorDescriptorImpl validatorDescriptor = new ValidatorDescriptorImpl();
		return validatorDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidatorType createValidatorTypeFromString(EDataType eDataType, String initialValue) {
		ValidatorType result = ValidatorType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertValidatorTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidatorPackage getValidatorPackage() {
		return (ValidatorPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ValidatorPackage getPackage() {
		return ValidatorPackage.eINSTANCE;
	}

} //ValidatorFactoryImpl
