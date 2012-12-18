/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.wfmc._2002.xpdl1.PackageHeaderType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Header Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageHeaderTypeImpl#getXPDLVersion <em>XPDL Version</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageHeaderTypeImpl#getVendor <em>Vendor</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageHeaderTypeImpl#getCreated <em>Created</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageHeaderTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageHeaderTypeImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageHeaderTypeImpl#getPriorityUnit <em>Priority Unit</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageHeaderTypeImpl#getCostUnit <em>Cost Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageHeaderTypeImpl extends EObjectImpl implements PackageHeaderType {
	/**
	 * The default value of the '{@link #getXPDLVersion() <em>XPDL Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXPDLVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String XPDL_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getXPDLVersion() <em>XPDL Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXPDLVersion()
	 * @generated
	 * @ordered
	 */
	protected String xPDLVersion = XPDL_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected static final String VENDOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected String vendor = VENDOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected String created = CREATED_EDEFAULT;

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
	 * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCUMENTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected String documentation = DOCUMENTATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPriorityUnit() <em>Priority Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriorityUnit()
	 * @generated
	 * @ordered
	 */
	protected static final String PRIORITY_UNIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPriorityUnit() <em>Priority Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriorityUnit()
	 * @generated
	 * @ordered
	 */
	protected String priorityUnit = PRIORITY_UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCostUnit() <em>Cost Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostUnit()
	 * @generated
	 * @ordered
	 */
	protected static final String COST_UNIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCostUnit() <em>Cost Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostUnit()
	 * @generated
	 * @ordered
	 */
	protected String costUnit = COST_UNIT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageHeaderTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.PACKAGE_HEADER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getXPDLVersion() {
		return xPDLVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXPDLVersion(String newXPDLVersion) {
		String oldXPDLVersion = xPDLVersion;
		xPDLVersion = newXPDLVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_HEADER_TYPE__XPDL_VERSION, oldXPDLVersion, xPDLVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVendor(String newVendor) {
		String oldVendor = vendor;
		vendor = newVendor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_HEADER_TYPE__VENDOR, oldVendor, vendor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreated(String newCreated) {
		String oldCreated = created;
		created = newCreated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_HEADER_TYPE__CREATED, oldCreated, created));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_HEADER_TYPE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocumentation(String newDocumentation) {
		String oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_HEADER_TYPE__DOCUMENTATION, oldDocumentation, documentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPriorityUnit() {
		return priorityUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriorityUnit(String newPriorityUnit) {
		String oldPriorityUnit = priorityUnit;
		priorityUnit = newPriorityUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_HEADER_TYPE__PRIORITY_UNIT, oldPriorityUnit, priorityUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCostUnit() {
		return costUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCostUnit(String newCostUnit) {
		String oldCostUnit = costUnit;
		costUnit = newCostUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_HEADER_TYPE__COST_UNIT, oldCostUnit, costUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xpdl1Package.PACKAGE_HEADER_TYPE__XPDL_VERSION:
				return getXPDLVersion();
			case Xpdl1Package.PACKAGE_HEADER_TYPE__VENDOR:
				return getVendor();
			case Xpdl1Package.PACKAGE_HEADER_TYPE__CREATED:
				return getCreated();
			case Xpdl1Package.PACKAGE_HEADER_TYPE__DESCRIPTION:
				return getDescription();
			case Xpdl1Package.PACKAGE_HEADER_TYPE__DOCUMENTATION:
				return getDocumentation();
			case Xpdl1Package.PACKAGE_HEADER_TYPE__PRIORITY_UNIT:
				return getPriorityUnit();
			case Xpdl1Package.PACKAGE_HEADER_TYPE__COST_UNIT:
				return getCostUnit();
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
			case Xpdl1Package.PACKAGE_HEADER_TYPE__XPDL_VERSION:
				setXPDLVersion((String)newValue);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__VENDOR:
				setVendor((String)newValue);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__CREATED:
				setCreated((String)newValue);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__DOCUMENTATION:
				setDocumentation((String)newValue);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__PRIORITY_UNIT:
				setPriorityUnit((String)newValue);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__COST_UNIT:
				setCostUnit((String)newValue);
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
			case Xpdl1Package.PACKAGE_HEADER_TYPE__XPDL_VERSION:
				setXPDLVersion(XPDL_VERSION_EDEFAULT);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__VENDOR:
				setVendor(VENDOR_EDEFAULT);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__CREATED:
				setCreated(CREATED_EDEFAULT);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__PRIORITY_UNIT:
				setPriorityUnit(PRIORITY_UNIT_EDEFAULT);
				return;
			case Xpdl1Package.PACKAGE_HEADER_TYPE__COST_UNIT:
				setCostUnit(COST_UNIT_EDEFAULT);
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
			case Xpdl1Package.PACKAGE_HEADER_TYPE__XPDL_VERSION:
				return XPDL_VERSION_EDEFAULT == null ? xPDLVersion != null : !XPDL_VERSION_EDEFAULT.equals(xPDLVersion);
			case Xpdl1Package.PACKAGE_HEADER_TYPE__VENDOR:
				return VENDOR_EDEFAULT == null ? vendor != null : !VENDOR_EDEFAULT.equals(vendor);
			case Xpdl1Package.PACKAGE_HEADER_TYPE__CREATED:
				return CREATED_EDEFAULT == null ? created != null : !CREATED_EDEFAULT.equals(created);
			case Xpdl1Package.PACKAGE_HEADER_TYPE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case Xpdl1Package.PACKAGE_HEADER_TYPE__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
			case Xpdl1Package.PACKAGE_HEADER_TYPE__PRIORITY_UNIT:
				return PRIORITY_UNIT_EDEFAULT == null ? priorityUnit != null : !PRIORITY_UNIT_EDEFAULT.equals(priorityUnit);
			case Xpdl1Package.PACKAGE_HEADER_TYPE__COST_UNIT:
				return COST_UNIT_EDEFAULT == null ? costUnit != null : !COST_UNIT_EDEFAULT.equals(costUnit);
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
		result.append(" (xPDLVersion: ");
		result.append(xPDLVersion);
		result.append(", vendor: ");
		result.append(vendor);
		result.append(", created: ");
		result.append(created);
		result.append(", description: ");
		result.append(description);
		result.append(", documentation: ");
		result.append(documentation);
		result.append(", priorityUnit: ");
		result.append(priorityUnit);
		result.append(", costUnit: ");
		result.append(costUnit);
		result.append(')');
		return result.toString();
	}

} //PackageHeaderTypeImpl
