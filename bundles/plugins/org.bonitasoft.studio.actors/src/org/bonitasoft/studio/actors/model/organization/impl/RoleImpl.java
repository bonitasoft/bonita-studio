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

import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.RoleImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.RoleImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.RoleImpl#getIconName <em>Icon Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.RoleImpl#getIconPath <em>Icon Path</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.RoleImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleImpl extends EObjectImpl implements Role {
	/**
     * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayName()
     * @generated
     * @ordered
     */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayName()
     * @generated
     * @ordered
     */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

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
     * The default value of the '{@link #getIconName() <em>Icon Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIconName()
     * @generated
     * @ordered
     */
	protected static final String ICON_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getIconName() <em>Icon Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIconName()
     * @generated
     * @ordered
     */
	protected String iconName = ICON_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getIconPath() <em>Icon Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIconPath()
     * @generated
     * @ordered
     */
	protected static final String ICON_PATH_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getIconPath() <em>Icon Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIconPath()
     * @generated
     * @ordered
     */
	protected String iconPath = ICON_PATH_EDEFAULT;

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
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected RoleImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OrganizationPackage.Literals.ROLE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getDisplayName() {
        return displayName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDisplayName(String newDisplayName) {
        String oldDisplayName = displayName;
        displayName = newDisplayName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ROLE__DISPLAY_NAME, oldDisplayName, displayName));
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
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ROLE__DESCRIPTION, oldDescription, description));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getIconName() {
        return iconName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setIconName(String newIconName) {
        String oldIconName = iconName;
        iconName = newIconName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ROLE__ICON_NAME, oldIconName, iconName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getIconPath() {
        return iconPath;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setIconPath(String newIconPath) {
        String oldIconPath = iconPath;
        iconPath = newIconPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ROLE__ICON_PATH, oldIconPath, iconPath));
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
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.ROLE__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OrganizationPackage.ROLE__DISPLAY_NAME:
                return getDisplayName();
            case OrganizationPackage.ROLE__DESCRIPTION:
                return getDescription();
            case OrganizationPackage.ROLE__ICON_NAME:
                return getIconName();
            case OrganizationPackage.ROLE__ICON_PATH:
                return getIconPath();
            case OrganizationPackage.ROLE__NAME:
                return getName();
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
            case OrganizationPackage.ROLE__DISPLAY_NAME:
                setDisplayName((String)newValue);
                return;
            case OrganizationPackage.ROLE__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case OrganizationPackage.ROLE__ICON_NAME:
                setIconName((String)newValue);
                return;
            case OrganizationPackage.ROLE__ICON_PATH:
                setIconPath((String)newValue);
                return;
            case OrganizationPackage.ROLE__NAME:
                setName((String)newValue);
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
            case OrganizationPackage.ROLE__DISPLAY_NAME:
                setDisplayName(DISPLAY_NAME_EDEFAULT);
                return;
            case OrganizationPackage.ROLE__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case OrganizationPackage.ROLE__ICON_NAME:
                setIconName(ICON_NAME_EDEFAULT);
                return;
            case OrganizationPackage.ROLE__ICON_PATH:
                setIconPath(ICON_PATH_EDEFAULT);
                return;
            case OrganizationPackage.ROLE__NAME:
                setName(NAME_EDEFAULT);
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
            case OrganizationPackage.ROLE__DISPLAY_NAME:
                return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
            case OrganizationPackage.ROLE__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case OrganizationPackage.ROLE__ICON_NAME:
                return ICON_NAME_EDEFAULT == null ? iconName != null : !ICON_NAME_EDEFAULT.equals(iconName);
            case OrganizationPackage.ROLE__ICON_PATH:
                return ICON_PATH_EDEFAULT == null ? iconPath != null : !ICON_PATH_EDEFAULT.equals(iconPath);
            case OrganizationPackage.ROLE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
        result.append(" (displayName: ");
        result.append(displayName);
        result.append(", description: ");
        result.append(description);
        result.append(", iconName: ");
        result.append(iconName);
        result.append(", iconPath: ");
        result.append(iconPath);
        result.append(", name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //RoleImpl
