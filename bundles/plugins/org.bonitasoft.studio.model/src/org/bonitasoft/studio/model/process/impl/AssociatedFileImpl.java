/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process.impl;

import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Associated File</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AssociatedFileImpl#getPath <em>Path</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AssociatedFileImpl#getWarPath <em>War Path</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AssociatedFileImpl extends EObjectImpl implements AssociatedFile {

    /**
     * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPath()
     * @generated
     * @ordered
     */
    protected static final String PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPath()
     * @generated
     * @ordered
     */
    protected String path = PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getWarPath() <em>War Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getWarPath()
     * @generated
     * @ordered
     */
    protected static final String WAR_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWarPath() <em>War Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getWarPath()
     * @generated
     * @ordered
     */
    protected String warPath = WAR_PATH_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected AssociatedFileImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ProcessPackage.Literals.ASSOCIATED_FILE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getPath() {
        return path;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPath(String newPath) {
        String oldPath = path;
        path = newPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ASSOCIATED_FILE__PATH, oldPath, path));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getWarPath() {
        return warPath;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setWarPath(String newWarPath) {
        String oldWarPath = warPath;
        warPath = newWarPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ASSOCIATED_FILE__WAR_PATH, oldWarPath,
                    warPath));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProcessPackage.ASSOCIATED_FILE__PATH:
                return getPath();
            case ProcessPackage.ASSOCIATED_FILE__WAR_PATH:
                return getWarPath();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ProcessPackage.ASSOCIATED_FILE__PATH:
                setPath((String) newValue);
                return;
            case ProcessPackage.ASSOCIATED_FILE__WAR_PATH:
                setWarPath((String) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ProcessPackage.ASSOCIATED_FILE__PATH:
                setPath(PATH_EDEFAULT);
                return;
            case ProcessPackage.ASSOCIATED_FILE__WAR_PATH:
                setWarPath(WAR_PATH_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ProcessPackage.ASSOCIATED_FILE__PATH:
                return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
            case ProcessPackage.ASSOCIATED_FILE__WAR_PATH:
                return WAR_PATH_EDEFAULT == null ? warPath != null : !WAR_PATH_EDEFAULT.equals(warPath);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (path: "); //$NON-NLS-1$
        result.append(path);
        result.append(", warPath: "); //$NON-NLS-1$
        result.append(warPath);
        result.append(')');
        return result.toString();
    }

} //AssociatedFileImpl
