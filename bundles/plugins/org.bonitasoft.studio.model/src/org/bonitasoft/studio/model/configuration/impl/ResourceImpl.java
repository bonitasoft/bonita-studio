/**
 * Copyright (C) 2009-2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.configuration.impl;

import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Resource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ResourceImpl#getBarPath <em>Bar Path</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ResourceImpl#getProjectPath <em>Project Path</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceImpl extends EObjectImpl implements Resource {
    /**
     * The default value of the '{@link #getBarPath() <em>Bar Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBarPath()
     * @generated
     * @ordered
     */
    protected static final String BAR_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBarPath() <em>Bar Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBarPath()
     * @generated
     * @ordered
     */
    protected String barPath = BAR_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getProjectPath() <em>Project Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProjectPath()
     * @generated
     * @ordered
     */
    protected static final String PROJECT_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getProjectPath() <em>Project Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProjectPath()
     * @generated
     * @ordered
     */
    protected String projectPath = PROJECT_PATH_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResourceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ConfigurationPackage.Literals.RESOURCE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBarPath() {
        return barPath;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBarPath(String newBarPath) {
        String oldBarPath = barPath;
        barPath = newBarPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.RESOURCE__BAR_PATH, oldBarPath, barPath));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getProjectPath() {
        return projectPath;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setProjectPath(String newProjectPath) {
        String oldProjectPath = projectPath;
        projectPath = newProjectPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.RESOURCE__PROJECT_PATH, oldProjectPath, projectPath));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConfigurationPackage.RESOURCE__BAR_PATH:
                return getBarPath();
            case ConfigurationPackage.RESOURCE__PROJECT_PATH:
                return getProjectPath();
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
            case ConfigurationPackage.RESOURCE__BAR_PATH:
                setBarPath((String)newValue);
                return;
            case ConfigurationPackage.RESOURCE__PROJECT_PATH:
                setProjectPath((String)newValue);
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
            case ConfigurationPackage.RESOURCE__BAR_PATH:
                setBarPath(BAR_PATH_EDEFAULT);
                return;
            case ConfigurationPackage.RESOURCE__PROJECT_PATH:
                setProjectPath(PROJECT_PATH_EDEFAULT);
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
            case ConfigurationPackage.RESOURCE__BAR_PATH:
                return BAR_PATH_EDEFAULT == null ? barPath != null : !BAR_PATH_EDEFAULT.equals(barPath);
            case ConfigurationPackage.RESOURCE__PROJECT_PATH:
                return PROJECT_PATH_EDEFAULT == null ? projectPath != null : !PROJECT_PATH_EDEFAULT.equals(projectPath);
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (barPath: "); //$NON-NLS-1$
        result.append(barPath);
        result.append(", projectPath: "); //$NON-NLS-1$
        result.append(projectPath);
        result.append(')');
        return result.toString();
    }

} //ResourceImpl
