/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.model.organization.impl;

import org.bonitasoft.studio.actors.model.organization.Membership;
import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Membership</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.MembershipImpl#getUserName <em>User Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.MembershipImpl#getRoleName <em>Role Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.MembershipImpl#getGroupName <em>Group Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.MembershipImpl#getGroupParentPath <em>Group Parent Path</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.MembershipImpl#getAssignedBy <em>Assigned By</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.MembershipImpl#getAssignedDate <em>Assigned Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MembershipImpl extends EObjectImpl implements Membership {
	/**
     * The default value of the '{@link #getUserName() <em>User Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUserName()
     * @generated
     * @ordered
     */
	protected static final String USER_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getUserName() <em>User Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUserName()
     * @generated
     * @ordered
     */
	protected String userName = USER_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getRoleName() <em>Role Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRoleName()
     * @generated
     * @ordered
     */
	protected static final String ROLE_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getRoleName() <em>Role Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRoleName()
     * @generated
     * @ordered
     */
	protected String roleName = ROLE_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getGroupName() <em>Group Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getGroupName()
     * @generated
     * @ordered
     */
	protected static final String GROUP_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getGroupName() <em>Group Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getGroupName()
     * @generated
     * @ordered
     */
	protected String groupName = GROUP_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getGroupParentPath() <em>Group Parent Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getGroupParentPath()
     * @generated
     * @ordered
     */
	protected static final String GROUP_PARENT_PATH_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getGroupParentPath() <em>Group Parent Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getGroupParentPath()
     * @generated
     * @ordered
     */
	protected String groupParentPath = GROUP_PARENT_PATH_EDEFAULT;

	/**
     * The default value of the '{@link #getAssignedBy() <em>Assigned By</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAssignedBy()
     * @generated
     * @ordered
     */
	protected static final String ASSIGNED_BY_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getAssignedBy() <em>Assigned By</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAssignedBy()
     * @generated
     * @ordered
     */
	protected String assignedBy = ASSIGNED_BY_EDEFAULT;

	/**
     * The default value of the '{@link #getAssignedDate() <em>Assigned Date</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAssignedDate()
     * @generated
     * @ordered
     */
	protected static final long ASSIGNED_DATE_EDEFAULT = 0L;

	/**
     * The cached value of the '{@link #getAssignedDate() <em>Assigned Date</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAssignedDate()
     * @generated
     * @ordered
     */
	protected long assignedDate = ASSIGNED_DATE_EDEFAULT;

	/**
     * This is true if the Assigned Date attribute has been set.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	protected boolean assignedDateESet;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected MembershipImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OrganizationPackage.Literals.MEMBERSHIP;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getUserName() {
        return userName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setUserName(String newUserName) {
        String oldUserName = userName;
        userName = newUserName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.MEMBERSHIP__USER_NAME, oldUserName, userName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getRoleName() {
        return roleName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setRoleName(String newRoleName) {
        String oldRoleName = roleName;
        roleName = newRoleName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.MEMBERSHIP__ROLE_NAME, oldRoleName, roleName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getGroupName() {
        return groupName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setGroupName(String newGroupName) {
        String oldGroupName = groupName;
        groupName = newGroupName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.MEMBERSHIP__GROUP_NAME, oldGroupName, groupName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getGroupParentPath() {
        return groupParentPath;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setGroupParentPath(String newGroupParentPath) {
        String oldGroupParentPath = groupParentPath;
        groupParentPath = newGroupParentPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.MEMBERSHIP__GROUP_PARENT_PATH, oldGroupParentPath, groupParentPath));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getAssignedBy() {
        return assignedBy;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAssignedBy(String newAssignedBy) {
        String oldAssignedBy = assignedBy;
        assignedBy = newAssignedBy;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.MEMBERSHIP__ASSIGNED_BY, oldAssignedBy, assignedBy));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public long getAssignedDate() {
        return assignedDate;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAssignedDate(long newAssignedDate) {
        long oldAssignedDate = assignedDate;
        assignedDate = newAssignedDate;
        boolean oldAssignedDateESet = assignedDateESet;
        assignedDateESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.MEMBERSHIP__ASSIGNED_DATE, oldAssignedDate, assignedDate, !oldAssignedDateESet));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void unsetAssignedDate() {
        long oldAssignedDate = assignedDate;
        boolean oldAssignedDateESet = assignedDateESet;
        assignedDate = ASSIGNED_DATE_EDEFAULT;
        assignedDateESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OrganizationPackage.MEMBERSHIP__ASSIGNED_DATE, oldAssignedDate, ASSIGNED_DATE_EDEFAULT, oldAssignedDateESet));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isSetAssignedDate() {
        return assignedDateESet;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OrganizationPackage.MEMBERSHIP__USER_NAME:
                return getUserName();
            case OrganizationPackage.MEMBERSHIP__ROLE_NAME:
                return getRoleName();
            case OrganizationPackage.MEMBERSHIP__GROUP_NAME:
                return getGroupName();
            case OrganizationPackage.MEMBERSHIP__GROUP_PARENT_PATH:
                return getGroupParentPath();
            case OrganizationPackage.MEMBERSHIP__ASSIGNED_BY:
                return getAssignedBy();
            case OrganizationPackage.MEMBERSHIP__ASSIGNED_DATE:
                return getAssignedDate();
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
            case OrganizationPackage.MEMBERSHIP__USER_NAME:
                setUserName((String)newValue);
                return;
            case OrganizationPackage.MEMBERSHIP__ROLE_NAME:
                setRoleName((String)newValue);
                return;
            case OrganizationPackage.MEMBERSHIP__GROUP_NAME:
                setGroupName((String)newValue);
                return;
            case OrganizationPackage.MEMBERSHIP__GROUP_PARENT_PATH:
                setGroupParentPath((String)newValue);
                return;
            case OrganizationPackage.MEMBERSHIP__ASSIGNED_BY:
                setAssignedBy((String)newValue);
                return;
            case OrganizationPackage.MEMBERSHIP__ASSIGNED_DATE:
                setAssignedDate((Long)newValue);
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
            case OrganizationPackage.MEMBERSHIP__USER_NAME:
                setUserName(USER_NAME_EDEFAULT);
                return;
            case OrganizationPackage.MEMBERSHIP__ROLE_NAME:
                setRoleName(ROLE_NAME_EDEFAULT);
                return;
            case OrganizationPackage.MEMBERSHIP__GROUP_NAME:
                setGroupName(GROUP_NAME_EDEFAULT);
                return;
            case OrganizationPackage.MEMBERSHIP__GROUP_PARENT_PATH:
                setGroupParentPath(GROUP_PARENT_PATH_EDEFAULT);
                return;
            case OrganizationPackage.MEMBERSHIP__ASSIGNED_BY:
                setAssignedBy(ASSIGNED_BY_EDEFAULT);
                return;
            case OrganizationPackage.MEMBERSHIP__ASSIGNED_DATE:
                unsetAssignedDate();
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
            case OrganizationPackage.MEMBERSHIP__USER_NAME:
                return USER_NAME_EDEFAULT == null ? userName != null : !USER_NAME_EDEFAULT.equals(userName);
            case OrganizationPackage.MEMBERSHIP__ROLE_NAME:
                return ROLE_NAME_EDEFAULT == null ? roleName != null : !ROLE_NAME_EDEFAULT.equals(roleName);
            case OrganizationPackage.MEMBERSHIP__GROUP_NAME:
                return GROUP_NAME_EDEFAULT == null ? groupName != null : !GROUP_NAME_EDEFAULT.equals(groupName);
            case OrganizationPackage.MEMBERSHIP__GROUP_PARENT_PATH:
                return GROUP_PARENT_PATH_EDEFAULT == null ? groupParentPath != null : !GROUP_PARENT_PATH_EDEFAULT.equals(groupParentPath);
            case OrganizationPackage.MEMBERSHIP__ASSIGNED_BY:
                return ASSIGNED_BY_EDEFAULT == null ? assignedBy != null : !ASSIGNED_BY_EDEFAULT.equals(assignedBy);
            case OrganizationPackage.MEMBERSHIP__ASSIGNED_DATE:
                return isSetAssignedDate();
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
        result.append(" (userName: ");
        result.append(userName);
        result.append(", roleName: ");
        result.append(roleName);
        result.append(", groupName: ");
        result.append(groupName);
        result.append(", groupParentPath: ");
        result.append(groupParentPath);
        result.append(", assignedBy: ");
        result.append(assignedBy);
        result.append(", assignedDate: ");
        if (assignedDateESet) result.append(assignedDate); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //MembershipImpl
