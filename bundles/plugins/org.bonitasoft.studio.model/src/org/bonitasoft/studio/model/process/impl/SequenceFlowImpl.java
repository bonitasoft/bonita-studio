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

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SequenceFlowConditionType;

import org.bonitasoft.studio.model.process.decision.DecisionTable;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequence Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.SequenceFlowImpl#isIsDefault <em>Is Default</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.SequenceFlowImpl#getConditionType <em>Condition Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.SequenceFlowImpl#getDecisionTable <em>Decision Table</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.SequenceFlowImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.SequenceFlowImpl#getPathToken <em>Path Token</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SequenceFlowImpl extends ConnectionImpl implements SequenceFlow {
	/**
	 * The default value of the '{@link #isIsDefault() <em>Is Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsDefault()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_DEFAULT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsDefault() <em>Is Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsDefault()
	 * @generated
	 * @ordered
	 */
	protected boolean isDefault = IS_DEFAULT_EDEFAULT;

	/**
	 * The default value of the '{@link #getConditionType() <em>Condition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionType()
	 * @generated
	 * @ordered
	 */
	protected static final SequenceFlowConditionType CONDITION_TYPE_EDEFAULT = SequenceFlowConditionType.EXPRESSION;

	/**
	 * The cached value of the '{@link #getConditionType() <em>Condition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionType()
	 * @generated
	 * @ordered
	 */
	protected SequenceFlowConditionType conditionType = CONDITION_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDecisionTable() <em>Decision Table</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecisionTable()
	 * @generated
	 * @ordered
	 */
	protected DecisionTable decisionTable;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected Expression condition;

	/**
	 * The default value of the '{@link #getPathToken() <em>Path Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathToken()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_TOKEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPathToken() <em>Path Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathToken()
	 * @generated
	 * @ordered
	 */
	protected String pathToken = PATH_TOKEN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SequenceFlowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.SEQUENCE_FLOW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsDefault() {
		return isDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsDefault(boolean newIsDefault) {
		boolean oldIsDefault = isDefault;
		isDefault = newIsDefault;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SEQUENCE_FLOW__IS_DEFAULT, oldIsDefault, isDefault));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SequenceFlowConditionType getConditionType() {
		return conditionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConditionType(SequenceFlowConditionType newConditionType) {
		SequenceFlowConditionType oldConditionType = conditionType;
		conditionType = newConditionType == null ? CONDITION_TYPE_EDEFAULT : newConditionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SEQUENCE_FLOW__CONDITION_TYPE, oldConditionType, conditionType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DecisionTable getDecisionTable() {
		return decisionTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDecisionTable(DecisionTable newDecisionTable, NotificationChain msgs) {
		DecisionTable oldDecisionTable = decisionTable;
		decisionTable = newDecisionTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.SEQUENCE_FLOW__DECISION_TABLE, oldDecisionTable, newDecisionTable);
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
	public void setDecisionTable(DecisionTable newDecisionTable) {
		if (newDecisionTable != decisionTable) {
			NotificationChain msgs = null;
			if (decisionTable != null)
				msgs = ((InternalEObject)decisionTable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.SEQUENCE_FLOW__DECISION_TABLE, null, msgs);
			if (newDecisionTable != null)
				msgs = ((InternalEObject)newDecisionTable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.SEQUENCE_FLOW__DECISION_TABLE, null, msgs);
			msgs = basicSetDecisionTable(newDecisionTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SEQUENCE_FLOW__DECISION_TABLE, newDecisionTable, newDecisionTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(Expression newCondition, NotificationChain msgs) {
		Expression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.SEQUENCE_FLOW__CONDITION, oldCondition, newCondition);
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
	public void setCondition(Expression newCondition) {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.SEQUENCE_FLOW__CONDITION, null, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.SEQUENCE_FLOW__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SEQUENCE_FLOW__CONDITION, newCondition, newCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPathToken() {
		return pathToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPathToken(String newPathToken) {
		String oldPathToken = pathToken;
		pathToken = newPathToken;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SEQUENCE_FLOW__PATH_TOKEN, oldPathToken, pathToken));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.SEQUENCE_FLOW__DECISION_TABLE:
				return basicSetDecisionTable(null, msgs);
			case ProcessPackage.SEQUENCE_FLOW__CONDITION:
				return basicSetCondition(null, msgs);
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
			case ProcessPackage.SEQUENCE_FLOW__IS_DEFAULT:
				return isIsDefault();
			case ProcessPackage.SEQUENCE_FLOW__CONDITION_TYPE:
				return getConditionType();
			case ProcessPackage.SEQUENCE_FLOW__DECISION_TABLE:
				return getDecisionTable();
			case ProcessPackage.SEQUENCE_FLOW__CONDITION:
				return getCondition();
			case ProcessPackage.SEQUENCE_FLOW__PATH_TOKEN:
				return getPathToken();
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
			case ProcessPackage.SEQUENCE_FLOW__IS_DEFAULT:
				setIsDefault((Boolean)newValue);
				return;
			case ProcessPackage.SEQUENCE_FLOW__CONDITION_TYPE:
				setConditionType((SequenceFlowConditionType)newValue);
				return;
			case ProcessPackage.SEQUENCE_FLOW__DECISION_TABLE:
				setDecisionTable((DecisionTable)newValue);
				return;
			case ProcessPackage.SEQUENCE_FLOW__CONDITION:
				setCondition((Expression)newValue);
				return;
			case ProcessPackage.SEQUENCE_FLOW__PATH_TOKEN:
				setPathToken((String)newValue);
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
			case ProcessPackage.SEQUENCE_FLOW__IS_DEFAULT:
				setIsDefault(IS_DEFAULT_EDEFAULT);
				return;
			case ProcessPackage.SEQUENCE_FLOW__CONDITION_TYPE:
				setConditionType(CONDITION_TYPE_EDEFAULT);
				return;
			case ProcessPackage.SEQUENCE_FLOW__DECISION_TABLE:
				setDecisionTable((DecisionTable)null);
				return;
			case ProcessPackage.SEQUENCE_FLOW__CONDITION:
				setCondition((Expression)null);
				return;
			case ProcessPackage.SEQUENCE_FLOW__PATH_TOKEN:
				setPathToken(PATH_TOKEN_EDEFAULT);
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
			case ProcessPackage.SEQUENCE_FLOW__IS_DEFAULT:
				return isDefault != IS_DEFAULT_EDEFAULT;
			case ProcessPackage.SEQUENCE_FLOW__CONDITION_TYPE:
				return conditionType != CONDITION_TYPE_EDEFAULT;
			case ProcessPackage.SEQUENCE_FLOW__DECISION_TABLE:
				return decisionTable != null;
			case ProcessPackage.SEQUENCE_FLOW__CONDITION:
				return condition != null;
			case ProcessPackage.SEQUENCE_FLOW__PATH_TOKEN:
				return PATH_TOKEN_EDEFAULT == null ? pathToken != null : !PATH_TOKEN_EDEFAULT.equals(pathToken);
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
		result.append(" (isDefault: "); //$NON-NLS-1$
		result.append(isDefault);
		result.append(", conditionType: "); //$NON-NLS-1$
		result.append(conditionType);
		result.append(", pathToken: "); //$NON-NLS-1$
		result.append(pathToken);
		result.append(')');
		return result.toString();
	}

} //SequenceFlowImpl
