/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.validators.descriptor.validator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getHasParameter <em>Has Parameter</em>}</li>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getDependencies <em>Dependencies</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage#getValidatorDescriptor()
 * @model
 * @generated
 */
public interface ValidatorDescriptor extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage#getValidatorDescriptor_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage#getValidatorDescriptor_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Name</em>' attribute.
	 * @see #setClassName(String)
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage#getValidatorDescriptor_ClassName()
	 * @model
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

	/**
	 * Returns the value of the '<em><b>Has Parameter</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Parameter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Parameter</em>' attribute.
	 * @see #setHasParameter(Boolean)
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage#getValidatorDescriptor_HasParameter()
	 * @model default="false"
	 * @generated
	 */
	Boolean getHasParameter();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getHasParameter <em>Has Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Parameter</em>' attribute.
	 * @see #getHasParameter()
	 * @generated
	 */
	void setHasParameter(Boolean value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * The literals are from the enumeration {@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorType
	 * @see #setType(ValidatorType)
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage#getValidatorDescriptor_Type()
	 * @model default="0"
	 * @generated
	 */
	ValidatorType getType();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorType
	 * @see #getType()
	 * @generated
	 */
	void setType(ValidatorType value);

	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependencies</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependencies</em>' attribute list.
	 * @see org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage#getValidatorDescriptor_Dependencies()
	 * @model
	 * @generated
	 */
	EList<String> getDependencies();

} // ValidatorDescriptor
