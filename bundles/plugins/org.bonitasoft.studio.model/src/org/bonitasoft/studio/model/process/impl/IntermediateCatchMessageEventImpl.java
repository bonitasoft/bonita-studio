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

import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.TableExpression;

import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.IntermediateCatchMessageEvent;
import org.bonitasoft.studio.model.process.MessageFlow;
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
 * An implementation of the model object '<em><b>Intermediate Catch Message Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchMessageEventImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchMessageEventImpl#getIncomingMessag <em>Incoming Messag</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchMessageEventImpl#getCorrelation <em>Correlation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchMessageEventImpl#getMessageContent <em>Message Content</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.IntermediateCatchMessageEventImpl#getData <em>Data</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IntermediateCatchMessageEventImpl extends MessageEventImpl implements IntermediateCatchMessageEvent {
	/**
	 * The default value of the '{@link #getEvent() <em>Event</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvent()
	 * @generated
	 * @ordered
	 */
	protected static final String EVENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEvent() <em>Event</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvent()
	 * @generated
	 * @ordered
	 */
	protected String event = EVENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIncomingMessag() <em>Incoming Messag</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncomingMessag()
	 * @generated
	 * @ordered
	 */
	protected MessageFlow incomingMessag;

	/**
	 * The cached value of the '{@link #getCorrelation() <em>Correlation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelation()
	 * @generated
	 * @ordered
	 */
	protected TableExpression correlation;

	/**
	 * The cached value of the '{@link #getMessageContent() <em>Message Content</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageContent()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> messageContent;

	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected EList<Data> data;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntermediateCatchMessageEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.INTERMEDIATE_CATCH_MESSAGE_EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEvent() {
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEvent(String newEvent) {
		String oldEvent = event;
		event = newEvent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__EVENT, oldEvent, event));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageFlow getIncomingMessag() {
		if (incomingMessag != null && incomingMessag.eIsProxy()) {
			InternalEObject oldIncomingMessag = (InternalEObject)incomingMessag;
			incomingMessag = (MessageFlow)eResolveProxy(oldIncomingMessag);
			if (incomingMessag != oldIncomingMessag) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG, oldIncomingMessag, incomingMessag));
			}
		}
		return incomingMessag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageFlow basicGetIncomingMessag() {
		return incomingMessag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIncomingMessag(MessageFlow newIncomingMessag, NotificationChain msgs) {
		MessageFlow oldIncomingMessag = incomingMessag;
		incomingMessag = newIncomingMessag;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG, oldIncomingMessag, newIncomingMessag);
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
	public void setIncomingMessag(MessageFlow newIncomingMessag) {
		if (newIncomingMessag != incomingMessag) {
			NotificationChain msgs = null;
			if (incomingMessag != null)
				msgs = ((InternalEObject)incomingMessag).eInverseRemove(this, ProcessPackage.MESSAGE_FLOW__TARGET, MessageFlow.class, msgs);
			if (newIncomingMessag != null)
				msgs = ((InternalEObject)newIncomingMessag).eInverseAdd(this, ProcessPackage.MESSAGE_FLOW__TARGET, MessageFlow.class, msgs);
			msgs = basicSetIncomingMessag(newIncomingMessag, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG, newIncomingMessag, newIncomingMessag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TableExpression getCorrelation() {
		return correlation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCorrelation(TableExpression newCorrelation, NotificationChain msgs) {
		TableExpression oldCorrelation = correlation;
		correlation = newCorrelation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION, oldCorrelation, newCorrelation);
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
	public void setCorrelation(TableExpression newCorrelation) {
		if (newCorrelation != correlation) {
			NotificationChain msgs = null;
			if (correlation != null)
				msgs = ((InternalEObject)correlation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION, null, msgs);
			if (newCorrelation != null)
				msgs = ((InternalEObject)newCorrelation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION, null, msgs);
			msgs = basicSetCorrelation(newCorrelation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION, newCorrelation, newCorrelation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Operation> getMessageContent() {
		if (messageContent == null) {
			messageContent = new EObjectContainmentEList<Operation>(Operation.class, this, ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT);
		}
		return messageContent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Data> getData() {
		if (data == null) {
			data = new EObjectContainmentEList<Data>(Data.class, this, ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__DATA);
		}
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG:
				if (incomingMessag != null)
					msgs = ((InternalEObject)incomingMessag).eInverseRemove(this, ProcessPackage.MESSAGE_FLOW__TARGET, MessageFlow.class, msgs);
				return basicSetIncomingMessag((MessageFlow)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG:
				return basicSetIncomingMessag(null, msgs);
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION:
				return basicSetCorrelation(null, msgs);
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT:
				return ((InternalEList<?>)getMessageContent()).basicRemove(otherEnd, msgs);
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__EVENT:
				return getEvent();
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG:
				if (resolve) return getIncomingMessag();
				return basicGetIncomingMessag();
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION:
				return getCorrelation();
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT:
				return getMessageContent();
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__DATA:
				return getData();
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
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__EVENT:
				setEvent((String)newValue);
				return;
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG:
				setIncomingMessag((MessageFlow)newValue);
				return;
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION:
				setCorrelation((TableExpression)newValue);
				return;
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT:
				getMessageContent().clear();
				getMessageContent().addAll((Collection<? extends Operation>)newValue);
				return;
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__DATA:
				getData().clear();
				getData().addAll((Collection<? extends Data>)newValue);
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
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__EVENT:
				setEvent(EVENT_EDEFAULT);
				return;
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG:
				setIncomingMessag((MessageFlow)null);
				return;
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION:
				setCorrelation((TableExpression)null);
				return;
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT:
				getMessageContent().clear();
				return;
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__DATA:
				getData().clear();
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
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__EVENT:
				return EVENT_EDEFAULT == null ? event != null : !EVENT_EDEFAULT.equals(event);
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG:
				return incomingMessag != null;
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION:
				return correlation != null;
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT:
				return messageContent != null && !messageContent.isEmpty();
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__DATA:
				return data != null && !data.isEmpty();
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
		if (baseClass == AbstractCatchMessageEvent.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__EVENT: return ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT;
				case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG: return ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__INCOMING_MESSAG;
				case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION: return ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__CORRELATION;
				case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT: return ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT;
				default: return -1;
			}
		}
		if (baseClass == DataAware.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__DATA: return ProcessPackage.DATA_AWARE__DATA;
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
		if (baseClass == AbstractCatchMessageEvent.class) {
			switch (baseFeatureID) {
				case ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT: return ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__EVENT;
				case ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__INCOMING_MESSAG: return ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__INCOMING_MESSAG;
				case ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__CORRELATION: return ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__CORRELATION;
				case ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT: return ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT;
				default: return -1;
			}
		}
		if (baseClass == DataAware.class) {
			switch (baseFeatureID) {
				case ProcessPackage.DATA_AWARE__DATA: return ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT__DATA;
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
		result.append(" (event: "); //$NON-NLS-1$
		result.append(event);
		result.append(')');
		return result.toString();
	}

} //IntermediateCatchMessageEventImpl
