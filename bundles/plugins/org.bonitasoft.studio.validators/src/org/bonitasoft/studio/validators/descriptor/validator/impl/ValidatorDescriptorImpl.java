/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.validators.descriptor.validator.impl;

import java.util.Collection;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorPackage;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorDescriptorImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorDescriptorImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorDescriptorImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorDescriptorImpl#getHasParameter <em>Has Parameter</em>}</li>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorDescriptorImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.validators.descriptor.validator.impl.ValidatorDescriptorImpl#getDependencies <em>Dependencies</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValidatorDescriptorImpl extends EObjectImpl implements ValidatorDescriptor {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getHasParameter() <em>Has Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasParameter()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean HAS_PARAMETER_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getHasParameter() <em>Has Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasParameter()
	 * @generated
	 * @ordered
	 */
	protected Boolean hasParameter = HAS_PARAMETER_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final ValidatorType TYPE_EDEFAULT = ValidatorType.FILED_VALIDATOR;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ValidatorType type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<String> dependencies;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValidatorDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValidatorPackage.Literals.VALIDATOR_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValidatorPackage.VALIDATOR_DESCRIPTOR__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValidatorPackage.VALIDATOR_DESCRIPTOR__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValidatorPackage.VALIDATOR_DESCRIPTOR__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getHasParameter() {
		return hasParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasParameter(Boolean newHasParameter) {
		Boolean oldHasParameter = hasParameter;
		hasParameter = newHasParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValidatorPackage.VALIDATOR_DESCRIPTOR__HAS_PARAMETER, oldHasParameter, hasParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidatorType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ValidatorType newType) {
		ValidatorType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValidatorPackage.VALIDATOR_DESCRIPTOR__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDependencies() {
		if (dependencies == null) {
			dependencies = new EDataTypeUniqueEList<String>(String.class, this, ValidatorPackage.VALIDATOR_DESCRIPTOR__DEPENDENCIES);
		}
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__NAME:
				return getName();
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__DESCRIPTION:
				return getDescription();
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__CLASS_NAME:
				return getClassName();
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__HAS_PARAMETER:
				return getHasParameter();
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__TYPE:
				return getType();
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__DEPENDENCIES:
				return getDependencies();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__NAME:
				setName((String)newValue);
				return;
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__HAS_PARAMETER:
				setHasParameter((Boolean)newValue);
				return;
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__TYPE:
				setType((ValidatorType)newValue);
				return;
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__HAS_PARAMETER:
				setHasParameter(HAS_PARAMETER_EDEFAULT);
				return;
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__DEPENDENCIES:
				getDependencies().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__HAS_PARAMETER:
				return HAS_PARAMETER_EDEFAULT == null ? hasParameter != null : !HAS_PARAMETER_EDEFAULT.equals(hasParameter);
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__TYPE:
				return type != TYPE_EDEFAULT;
			case ValidatorPackage.VALIDATOR_DESCRIPTOR__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(", className: ");
		result.append(className);
		result.append(", hasParameter: ");
		result.append(hasParameter);
		result.append(", type: ");
		result.append(type);
		result.append(", dependencies: ");
		result.append(dependencies);
		result.append(')');
		return result.toString();
	}

} //ValidatorDescriptorImpl
