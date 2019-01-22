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

import org.bonitasoft.studio.model.process.AbstractPageFlow;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Assignable;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;

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
 * An implementation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getFormMapping <em>Form Mapping</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getActor <em>Actor</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getFilters <em>Filters</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getContract <em>Contract</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#isOverrideActorsOfTheLane <em>Override Actors Of The Lane</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getExpectedDuration <em>Expected Duration</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TaskImpl extends ActivityImpl implements Task {
	/**
	 * The cached value of the '{@link #getFormMapping() <em>Form Mapping</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormMapping()
	 * @generated
	 * @ordered
	 */
	protected FormMapping formMapping;

	/**
	 * The cached value of the '{@link #getActor() <em>Actor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActor()
	 * @generated
	 * @ordered
	 */
	protected Actor actor;

	/**
	 * The cached value of the '{@link #getFilters() <em>Filters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilters()
	 * @generated
	 * @ordered
	 */
	protected EList<ActorFilter> filters;

	/**
	 * The cached value of the '{@link #getContract() <em>Contract</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContract()
	 * @generated
	 * @ordered
	 */
	protected Contract contract;

	/**
	 * The default value of the '{@link #isOverrideActorsOfTheLane() <em>Override Actors Of The Lane</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverrideActorsOfTheLane()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERRIDE_ACTORS_OF_THE_LANE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isOverrideActorsOfTheLane() <em>Override Actors Of The Lane</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverrideActorsOfTheLane()
	 * @generated
	 * @ordered
	 */
	protected boolean overrideActorsOfTheLane = OVERRIDE_ACTORS_OF_THE_LANE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final int PRIORITY_EDEFAULT = 2;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected int priority = PRIORITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExpectedDuration() <em>Expected Duration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpectedDuration()
	 * @generated
	 * @ordered
	 */
	protected Expression expectedDuration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FormMapping getFormMapping() {
		return formMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFormMapping(FormMapping newFormMapping, NotificationChain msgs) {
		FormMapping oldFormMapping = formMapping;
		formMapping = newFormMapping;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__FORM_MAPPING, oldFormMapping, newFormMapping);
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
	public void setFormMapping(FormMapping newFormMapping) {
		if (newFormMapping != formMapping) {
			NotificationChain msgs = null;
			if (formMapping != null)
				msgs = ((InternalEObject)formMapping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__FORM_MAPPING, null, msgs);
			if (newFormMapping != null)
				msgs = ((InternalEObject)newFormMapping).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__FORM_MAPPING, null, msgs);
			msgs = basicSetFormMapping(newFormMapping, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__FORM_MAPPING, newFormMapping, newFormMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Actor getActor() {
		if (actor != null && actor.eIsProxy()) {
			InternalEObject oldActor = (InternalEObject)actor;
			actor = (Actor)eResolveProxy(oldActor);
			if (actor != oldActor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessPackage.TASK__ACTOR, oldActor, actor));
			}
		}
		return actor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor basicGetActor() {
		return actor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActor(Actor newActor) {
		Actor oldActor = actor;
		actor = newActor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__ACTOR, oldActor, actor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ActorFilter> getFilters() {
		if (filters == null) {
			filters = new EObjectContainmentEList<ActorFilter>(ActorFilter.class, this, ProcessPackage.TASK__FILTERS);
		}
		return filters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Contract getContract() {
		return contract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContract(Contract newContract, NotificationChain msgs) {
		Contract oldContract = contract;
		contract = newContract;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__CONTRACT, oldContract, newContract);
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
	public void setContract(Contract newContract) {
		if (newContract != contract) {
			NotificationChain msgs = null;
			if (contract != null)
				msgs = ((InternalEObject)contract).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__CONTRACT, null, msgs);
			if (newContract != null)
				msgs = ((InternalEObject)newContract).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__CONTRACT, null, msgs);
			msgs = basicSetContract(newContract, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__CONTRACT, newContract, newContract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOverrideActorsOfTheLane() {
		return overrideActorsOfTheLane;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOverrideActorsOfTheLane(boolean newOverrideActorsOfTheLane) {
		boolean oldOverrideActorsOfTheLane = overrideActorsOfTheLane;
		overrideActorsOfTheLane = newOverrideActorsOfTheLane;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__OVERRIDE_ACTORS_OF_THE_LANE, oldOverrideActorsOfTheLane, overrideActorsOfTheLane));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPriority(int newPriority) {
		int oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__PRIORITY, oldPriority, priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getExpectedDuration() {
		return expectedDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpectedDuration(Expression newExpectedDuration, NotificationChain msgs) {
		Expression oldExpectedDuration = expectedDuration;
		expectedDuration = newExpectedDuration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__EXPECTED_DURATION, oldExpectedDuration, newExpectedDuration);
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
	public void setExpectedDuration(Expression newExpectedDuration) {
		if (newExpectedDuration != expectedDuration) {
			NotificationChain msgs = null;
			if (expectedDuration != null)
				msgs = ((InternalEObject)expectedDuration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__EXPECTED_DURATION, null, msgs);
			if (newExpectedDuration != null)
				msgs = ((InternalEObject)newExpectedDuration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__EXPECTED_DURATION, null, msgs);
			msgs = basicSetExpectedDuration(newExpectedDuration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__EXPECTED_DURATION, newExpectedDuration, newExpectedDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.TASK__FORM_MAPPING:
				return basicSetFormMapping(null, msgs);
			case ProcessPackage.TASK__FILTERS:
				return ((InternalEList<?>)getFilters()).basicRemove(otherEnd, msgs);
			case ProcessPackage.TASK__CONTRACT:
				return basicSetContract(null, msgs);
			case ProcessPackage.TASK__EXPECTED_DURATION:
				return basicSetExpectedDuration(null, msgs);
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
			case ProcessPackage.TASK__FORM_MAPPING:
				return getFormMapping();
			case ProcessPackage.TASK__ACTOR:
				if (resolve) return getActor();
				return basicGetActor();
			case ProcessPackage.TASK__FILTERS:
				return getFilters();
			case ProcessPackage.TASK__CONTRACT:
				return getContract();
			case ProcessPackage.TASK__OVERRIDE_ACTORS_OF_THE_LANE:
				return isOverrideActorsOfTheLane();
			case ProcessPackage.TASK__PRIORITY:
				return getPriority();
			case ProcessPackage.TASK__EXPECTED_DURATION:
				return getExpectedDuration();
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
			case ProcessPackage.TASK__FORM_MAPPING:
				setFormMapping((FormMapping)newValue);
				return;
			case ProcessPackage.TASK__ACTOR:
				setActor((Actor)newValue);
				return;
			case ProcessPackage.TASK__FILTERS:
				getFilters().clear();
				getFilters().addAll((Collection<? extends ActorFilter>)newValue);
				return;
			case ProcessPackage.TASK__CONTRACT:
				setContract((Contract)newValue);
				return;
			case ProcessPackage.TASK__OVERRIDE_ACTORS_OF_THE_LANE:
				setOverrideActorsOfTheLane((Boolean)newValue);
				return;
			case ProcessPackage.TASK__PRIORITY:
				setPriority((Integer)newValue);
				return;
			case ProcessPackage.TASK__EXPECTED_DURATION:
				setExpectedDuration((Expression)newValue);
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
			case ProcessPackage.TASK__FORM_MAPPING:
				setFormMapping((FormMapping)null);
				return;
			case ProcessPackage.TASK__ACTOR:
				setActor((Actor)null);
				return;
			case ProcessPackage.TASK__FILTERS:
				getFilters().clear();
				return;
			case ProcessPackage.TASK__CONTRACT:
				setContract((Contract)null);
				return;
			case ProcessPackage.TASK__OVERRIDE_ACTORS_OF_THE_LANE:
				setOverrideActorsOfTheLane(OVERRIDE_ACTORS_OF_THE_LANE_EDEFAULT);
				return;
			case ProcessPackage.TASK__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case ProcessPackage.TASK__EXPECTED_DURATION:
				setExpectedDuration((Expression)null);
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
			case ProcessPackage.TASK__FORM_MAPPING:
				return formMapping != null;
			case ProcessPackage.TASK__ACTOR:
				return actor != null;
			case ProcessPackage.TASK__FILTERS:
				return filters != null && !filters.isEmpty();
			case ProcessPackage.TASK__CONTRACT:
				return contract != null;
			case ProcessPackage.TASK__OVERRIDE_ACTORS_OF_THE_LANE:
				return overrideActorsOfTheLane != OVERRIDE_ACTORS_OF_THE_LANE_EDEFAULT;
			case ProcessPackage.TASK__PRIORITY:
				return priority != PRIORITY_EDEFAULT;
			case ProcessPackage.TASK__EXPECTED_DURATION:
				return expectedDuration != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractPageFlow.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == PageFlow.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.TASK__FORM_MAPPING: return ProcessPackage.PAGE_FLOW__FORM_MAPPING;
				default: return -1;
			}
		}
		if (baseClass == Assignable.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.TASK__ACTOR: return ProcessPackage.ASSIGNABLE__ACTOR;
				case ProcessPackage.TASK__FILTERS: return ProcessPackage.ASSIGNABLE__FILTERS;
				default: return -1;
			}
		}
		if (baseClass == ContractContainer.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.TASK__CONTRACT: return ProcessPackage.CONTRACT_CONTAINER__CONTRACT;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractPageFlow.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == PageFlow.class) {
			switch (baseFeatureID) {
				case ProcessPackage.PAGE_FLOW__FORM_MAPPING: return ProcessPackage.TASK__FORM_MAPPING;
				default: return -1;
			}
		}
		if (baseClass == Assignable.class) {
			switch (baseFeatureID) {
				case ProcessPackage.ASSIGNABLE__ACTOR: return ProcessPackage.TASK__ACTOR;
				case ProcessPackage.ASSIGNABLE__FILTERS: return ProcessPackage.TASK__FILTERS;
				default: return -1;
			}
		}
		if (baseClass == ContractContainer.class) {
			switch (baseFeatureID) {
				case ProcessPackage.CONTRACT_CONTAINER__CONTRACT: return ProcessPackage.TASK__CONTRACT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (overrideActorsOfTheLane: "); //$NON-NLS-1$
		result.append(overrideActorsOfTheLane);
		result.append(", priority: "); //$NON-NLS-1$
		result.append(priority);
		result.append(')');
		return result.toString();
	}

} //TaskImpl
