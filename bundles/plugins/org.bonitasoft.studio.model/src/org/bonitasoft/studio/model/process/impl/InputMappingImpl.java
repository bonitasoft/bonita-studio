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

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.InputMappingImpl#getProcessSource <em>Process Source</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.InputMappingImpl#getSubprocessTarget <em>Subprocess Target</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.InputMappingImpl#getAssignationType <em>Assignation Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InputMappingImpl extends EObjectImpl implements InputMapping {
	/**
     * The cached value of the '{@link #getProcessSource() <em>Process Source</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getProcessSource()
     * @generated
     * @ordered
     */
	protected Expression processSource;

	/**
     * The default value of the '{@link #getSubprocessTarget() <em>Subprocess Target</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSubprocessTarget()
     * @generated
     * @ordered
     */
	protected static final String SUBPROCESS_TARGET_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getSubprocessTarget() <em>Subprocess Target</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSubprocessTarget()
     * @generated
     * @ordered
     */
	protected String subprocessTarget = SUBPROCESS_TARGET_EDEFAULT;

	/**
     * The default value of the '{@link #getAssignationType() <em>Assignation Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAssignationType()
     * @generated
     * @ordered
     */
	protected static final InputMappingAssignationType ASSIGNATION_TYPE_EDEFAULT = InputMappingAssignationType.CONTRACT_INPUT;

	/**
     * The cached value of the '{@link #getAssignationType() <em>Assignation Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAssignationType()
     * @generated
     * @ordered
     */
	protected InputMappingAssignationType assignationType = ASSIGNATION_TYPE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected InputMappingImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.INPUT_MAPPING;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getProcessSource() {
        return processSource;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetProcessSource(Expression newProcessSource, NotificationChain msgs) {
        Expression oldProcessSource = processSource;
        processSource = newProcessSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.INPUT_MAPPING__PROCESS_SOURCE, oldProcessSource, newProcessSource);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setProcessSource(Expression newProcessSource) {
        if (newProcessSource != processSource) {
            NotificationChain msgs = null;
            if (processSource != null)
                msgs = ((InternalEObject)processSource).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.INPUT_MAPPING__PROCESS_SOURCE, null, msgs);
            if (newProcessSource != null)
                msgs = ((InternalEObject)newProcessSource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.INPUT_MAPPING__PROCESS_SOURCE, null, msgs);
            msgs = basicSetProcessSource(newProcessSource, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.INPUT_MAPPING__PROCESS_SOURCE, newProcessSource, newProcessSource));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getSubprocessTarget() {
        return subprocessTarget;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setSubprocessTarget(String newSubprocessTarget) {
        String oldSubprocessTarget = subprocessTarget;
        subprocessTarget = newSubprocessTarget;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.INPUT_MAPPING__SUBPROCESS_TARGET, oldSubprocessTarget, subprocessTarget));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public InputMappingAssignationType getAssignationType() {
        return assignationType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setAssignationType(InputMappingAssignationType newAssignationType) {
        InputMappingAssignationType oldAssignationType = assignationType;
        assignationType = newAssignationType == null ? ASSIGNATION_TYPE_EDEFAULT : newAssignationType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.INPUT_MAPPING__ASSIGNATION_TYPE, oldAssignationType, assignationType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.INPUT_MAPPING__PROCESS_SOURCE:
                return basicSetProcessSource(null, msgs);
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
            case ProcessPackage.INPUT_MAPPING__PROCESS_SOURCE:
                return getProcessSource();
            case ProcessPackage.INPUT_MAPPING__SUBPROCESS_TARGET:
                return getSubprocessTarget();
            case ProcessPackage.INPUT_MAPPING__ASSIGNATION_TYPE:
                return getAssignationType();
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
            case ProcessPackage.INPUT_MAPPING__PROCESS_SOURCE:
                setProcessSource((Expression)newValue);
                return;
            case ProcessPackage.INPUT_MAPPING__SUBPROCESS_TARGET:
                setSubprocessTarget((String)newValue);
                return;
            case ProcessPackage.INPUT_MAPPING__ASSIGNATION_TYPE:
                setAssignationType((InputMappingAssignationType)newValue);
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
            case ProcessPackage.INPUT_MAPPING__PROCESS_SOURCE:
                setProcessSource((Expression)null);
                return;
            case ProcessPackage.INPUT_MAPPING__SUBPROCESS_TARGET:
                setSubprocessTarget(SUBPROCESS_TARGET_EDEFAULT);
                return;
            case ProcessPackage.INPUT_MAPPING__ASSIGNATION_TYPE:
                setAssignationType(ASSIGNATION_TYPE_EDEFAULT);
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
            case ProcessPackage.INPUT_MAPPING__PROCESS_SOURCE:
                return processSource != null;
            case ProcessPackage.INPUT_MAPPING__SUBPROCESS_TARGET:
                return SUBPROCESS_TARGET_EDEFAULT == null ? subprocessTarget != null : !SUBPROCESS_TARGET_EDEFAULT.equals(subprocessTarget);
            case ProcessPackage.INPUT_MAPPING__ASSIGNATION_TYPE:
                return assignationType != ASSIGNATION_TYPE_EDEFAULT;
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
        result.append(" (subprocessTarget: "); //$NON-NLS-1$
        result.append(subprocessTarget);
        result.append(", assignationType: "); //$NON-NLS-1$
        result.append(assignationType);
        result.append(')');
        return result.toString();
    }

} //InputMappingImpl
