/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.wfmc._2002.xpdl1.ExtendedAttributeType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extended Attribute Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ExtendedAttributeTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ExtendedAttributeTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ExtendedAttributeTypeImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ExtendedAttributeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ExtendedAttributeTypeImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtendedAttributeTypeImpl extends EObjectImpl implements ExtendedAttributeType {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

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
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtendedAttributeTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.EXTENDED_ATTRIBUTE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		return (FeatureMap)getMixed().<FeatureMap.Entry>list(Xpdl1Package.Literals.EXTENDED_ATTRIBUTE_TYPE__GROUP);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAny() {
		return (FeatureMap)getGroup().<FeatureMap.Entry>list(Xpdl1Package.Literals.EXTENDED_ATTRIBUTE_TYPE__ANY);
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
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__ANY:
				return ((InternalEList<?>)getAny()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__ANY:
				if (coreType) return getAny();
				return ((FeatureMap.Internal)getAny()).getWrapper();
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__NAME:
				return getName();
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__VALUE:
				return getValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__ANY:
				((FeatureMap.Internal)getAny()).set(newValue);
				return;
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__NAME:
				setName((String)newValue);
				return;
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__VALUE:
				setValue((String)newValue);
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
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__MIXED:
				getMixed().clear();
				return;
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__GROUP:
				getGroup().clear();
				return;
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__ANY:
				getAny().clear();
				return;
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__VALUE:
				setValue(VALUE_EDEFAULT);
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
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__MIXED:
				return mixed != null && !mixed.isEmpty();
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__GROUP:
				return !getGroup().isEmpty();
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__ANY:
				return !getAny().isEmpty();
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Xpdl1Package.EXTENDED_ATTRIBUTE_TYPE__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
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
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(", name: ");
		result.append(name);
		result.append(", value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

} //ExtendedAttributeTypeImpl
