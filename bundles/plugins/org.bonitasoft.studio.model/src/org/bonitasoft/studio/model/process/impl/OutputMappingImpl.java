/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.process.impl;

import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Output Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.OutputMappingImpl#getSubprocessSource <em>Subprocess Source</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.OutputMappingImpl#getProcessTarget <em>Process Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OutputMappingImpl extends EObjectImpl implements OutputMapping {
	/**
	 * The default value of the '{@link #getSubprocessSource() <em>Subprocess Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubprocessSource()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBPROCESS_SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubprocessSource() <em>Subprocess Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubprocessSource()
	 * @generated
	 * @ordered
	 */
	protected String subprocessSource = SUBPROCESS_SOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProcessTarget() <em>Process Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessTarget()
	 * @generated
	 * @ordered
	 */
	protected Data processTarget;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutputMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.OUTPUT_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSubprocessSource() {
		return subprocessSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSubprocessSource(String newSubprocessSource) {
		String oldSubprocessSource = subprocessSource;
		subprocessSource = newSubprocessSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.OUTPUT_MAPPING__SUBPROCESS_SOURCE, oldSubprocessSource, subprocessSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Data getProcessTarget() {
		if (processTarget != null && processTarget.eIsProxy()) {
			InternalEObject oldProcessTarget = (InternalEObject)processTarget;
			processTarget = (Data)eResolveProxy(oldProcessTarget);
			if (processTarget != oldProcessTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessPackage.OUTPUT_MAPPING__PROCESS_TARGET, oldProcessTarget, processTarget));
			}
		}
		return processTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Data basicGetProcessTarget() {
		return processTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProcessTarget(Data newProcessTarget) {
		Data oldProcessTarget = processTarget;
		processTarget = newProcessTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.OUTPUT_MAPPING__PROCESS_TARGET, oldProcessTarget, processTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProcessPackage.OUTPUT_MAPPING__SUBPROCESS_SOURCE:
				return getSubprocessSource();
			case ProcessPackage.OUTPUT_MAPPING__PROCESS_TARGET:
				if (resolve) return getProcessTarget();
				return basicGetProcessTarget();
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
			case ProcessPackage.OUTPUT_MAPPING__SUBPROCESS_SOURCE:
				setSubprocessSource((String)newValue);
				return;
			case ProcessPackage.OUTPUT_MAPPING__PROCESS_TARGET:
				setProcessTarget((Data)newValue);
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
			case ProcessPackage.OUTPUT_MAPPING__SUBPROCESS_SOURCE:
				setSubprocessSource(SUBPROCESS_SOURCE_EDEFAULT);
				return;
			case ProcessPackage.OUTPUT_MAPPING__PROCESS_TARGET:
				setProcessTarget((Data)null);
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
			case ProcessPackage.OUTPUT_MAPPING__SUBPROCESS_SOURCE:
				return SUBPROCESS_SOURCE_EDEFAULT == null ? subprocessSource != null : !SUBPROCESS_SOURCE_EDEFAULT.equals(subprocessSource);
			case ProcessPackage.OUTPUT_MAPPING__PROCESS_TARGET:
				return processTarget != null;
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
		result.append(" (subprocessSource: "); //$NON-NLS-1$
		result.append(subprocessSource);
		result.append(')');
		return result.toString();
	}

} //OutputMappingImpl
