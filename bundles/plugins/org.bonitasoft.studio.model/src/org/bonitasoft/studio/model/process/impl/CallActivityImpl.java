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

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.CallActivityImpl#getInputMappings <em>Input Mappings</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.CallActivityImpl#getOutputMappings <em>Output Mappings</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.CallActivityImpl#getCalledActivityName <em>Called Activity Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.CallActivityImpl#getCalledActivityVersion <em>Called Activity Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CallActivityImpl extends ActivityImpl implements CallActivity {
	/**
	 * The cached value of the '{@link #getInputMappings() <em>Input Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<InputMapping> inputMappings;

	/**
	 * The cached value of the '{@link #getOutputMappings() <em>Output Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<OutputMapping> outputMappings;

	/**
	 * The cached value of the '{@link #getCalledActivityName() <em>Called Activity Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledActivityName()
	 * @generated
	 * @ordered
	 */
	protected Expression calledActivityName;

	/**
	 * The cached value of the '{@link #getCalledActivityVersion() <em>Called Activity Version</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledActivityVersion()
	 * @generated
	 * @ordered
	 */
	protected Expression calledActivityVersion;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.CALL_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<InputMapping> getInputMappings() {
		if (inputMappings == null) {
			inputMappings = new EObjectContainmentEList<InputMapping>(InputMapping.class, this, ProcessPackage.CALL_ACTIVITY__INPUT_MAPPINGS);
		}
		return inputMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<OutputMapping> getOutputMappings() {
		if (outputMappings == null) {
			outputMappings = new EObjectContainmentEList<OutputMapping>(OutputMapping.class, this, ProcessPackage.CALL_ACTIVITY__OUTPUT_MAPPINGS);
		}
		return outputMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getCalledActivityName() {
		return calledActivityName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCalledActivityName(Expression newCalledActivityName, NotificationChain msgs) {
		Expression oldCalledActivityName = calledActivityName;
		calledActivityName = newCalledActivityName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_NAME, oldCalledActivityName, newCalledActivityName);
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
	public void setCalledActivityName(Expression newCalledActivityName) {
		if (newCalledActivityName != calledActivityName) {
			NotificationChain msgs = null;
			if (calledActivityName != null)
				msgs = ((InternalEObject)calledActivityName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_NAME, null, msgs);
			if (newCalledActivityName != null)
				msgs = ((InternalEObject)newCalledActivityName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_NAME, null, msgs);
			msgs = basicSetCalledActivityName(newCalledActivityName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_NAME, newCalledActivityName, newCalledActivityName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getCalledActivityVersion() {
		return calledActivityVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCalledActivityVersion(Expression newCalledActivityVersion, NotificationChain msgs) {
		Expression oldCalledActivityVersion = calledActivityVersion;
		calledActivityVersion = newCalledActivityVersion;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION, oldCalledActivityVersion, newCalledActivityVersion);
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
	public void setCalledActivityVersion(Expression newCalledActivityVersion) {
		if (newCalledActivityVersion != calledActivityVersion) {
			NotificationChain msgs = null;
			if (calledActivityVersion != null)
				msgs = ((InternalEObject)calledActivityVersion).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION, null, msgs);
			if (newCalledActivityVersion != null)
				msgs = ((InternalEObject)newCalledActivityVersion).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION, null, msgs);
			msgs = basicSetCalledActivityVersion(newCalledActivityVersion, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION, newCalledActivityVersion, newCalledActivityVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.CALL_ACTIVITY__INPUT_MAPPINGS:
				return ((InternalEList<?>)getInputMappings()).basicRemove(otherEnd, msgs);
			case ProcessPackage.CALL_ACTIVITY__OUTPUT_MAPPINGS:
				return ((InternalEList<?>)getOutputMappings()).basicRemove(otherEnd, msgs);
			case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_NAME:
				return basicSetCalledActivityName(null, msgs);
			case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION:
				return basicSetCalledActivityVersion(null, msgs);
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
			case ProcessPackage.CALL_ACTIVITY__INPUT_MAPPINGS:
				return getInputMappings();
			case ProcessPackage.CALL_ACTIVITY__OUTPUT_MAPPINGS:
				return getOutputMappings();
			case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_NAME:
				return getCalledActivityName();
			case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION:
				return getCalledActivityVersion();
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
			case ProcessPackage.CALL_ACTIVITY__INPUT_MAPPINGS:
				getInputMappings().clear();
				getInputMappings().addAll((Collection<? extends InputMapping>)newValue);
				return;
			case ProcessPackage.CALL_ACTIVITY__OUTPUT_MAPPINGS:
				getOutputMappings().clear();
				getOutputMappings().addAll((Collection<? extends OutputMapping>)newValue);
				return;
			case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_NAME:
				setCalledActivityName((Expression)newValue);
				return;
			case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION:
				setCalledActivityVersion((Expression)newValue);
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
			case ProcessPackage.CALL_ACTIVITY__INPUT_MAPPINGS:
				getInputMappings().clear();
				return;
			case ProcessPackage.CALL_ACTIVITY__OUTPUT_MAPPINGS:
				getOutputMappings().clear();
				return;
			case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_NAME:
				setCalledActivityName((Expression)null);
				return;
			case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION:
				setCalledActivityVersion((Expression)null);
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
			case ProcessPackage.CALL_ACTIVITY__INPUT_MAPPINGS:
				return inputMappings != null && !inputMappings.isEmpty();
			case ProcessPackage.CALL_ACTIVITY__OUTPUT_MAPPINGS:
				return outputMappings != null && !outputMappings.isEmpty();
			case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_NAME:
				return calledActivityName != null;
			case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION:
				return calledActivityVersion != null;
		}
		return super.eIsSet(featureID);
	}

} //CallActivityImpl
