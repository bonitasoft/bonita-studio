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

import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageEvent;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Send Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.SendTaskImpl#getEvents <em>Events</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.SendTaskImpl#getOutgoingMessages <em>Outgoing Messages</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SendTaskImpl extends ActivityImpl implements SendTask {
	/**
     * The cached value of the '{@link #getEvents() <em>Events</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getEvents()
     * @generated
     * @ordered
     */
	protected EList<Message> events;

	/**
     * The cached value of the '{@link #getOutgoingMessages() <em>Outgoing Messages</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOutgoingMessages()
     * @generated
     * @ordered
     */
	protected EList<MessageFlow> outgoingMessages;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SendTaskImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.SEND_TASK;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Message> getEvents() {
        if (events == null) {
            events = new EObjectContainmentWithInverseEList<Message>(Message.class, this, ProcessPackage.SEND_TASK__EVENTS, ProcessPackage.MESSAGE__SOURCE);
        }
        return events;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<MessageFlow> getOutgoingMessages() {
        if (outgoingMessages == null) {
            outgoingMessages = new EObjectWithInverseResolvingEList<MessageFlow>(MessageFlow.class, this, ProcessPackage.SEND_TASK__OUTGOING_MESSAGES, ProcessPackage.MESSAGE_FLOW__SOURCE);
        }
        return outgoingMessages;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.SEND_TASK__EVENTS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getEvents()).basicAdd(otherEnd, msgs);
            case ProcessPackage.SEND_TASK__OUTGOING_MESSAGES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingMessages()).basicAdd(otherEnd, msgs);
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
            case ProcessPackage.SEND_TASK__EVENTS:
                return ((InternalEList<?>)getEvents()).basicRemove(otherEnd, msgs);
            case ProcessPackage.SEND_TASK__OUTGOING_MESSAGES:
                return ((InternalEList<?>)getOutgoingMessages()).basicRemove(otherEnd, msgs);
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
            case ProcessPackage.SEND_TASK__EVENTS:
                return getEvents();
            case ProcessPackage.SEND_TASK__OUTGOING_MESSAGES:
                return getOutgoingMessages();
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
            case ProcessPackage.SEND_TASK__EVENTS:
                getEvents().clear();
                getEvents().addAll((Collection<? extends Message>)newValue);
                return;
            case ProcessPackage.SEND_TASK__OUTGOING_MESSAGES:
                getOutgoingMessages().clear();
                getOutgoingMessages().addAll((Collection<? extends MessageFlow>)newValue);
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
            case ProcessPackage.SEND_TASK__EVENTS:
                getEvents().clear();
                return;
            case ProcessPackage.SEND_TASK__OUTGOING_MESSAGES:
                getOutgoingMessages().clear();
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
            case ProcessPackage.SEND_TASK__EVENTS:
                return events != null && !events.isEmpty();
            case ProcessPackage.SEND_TASK__OUTGOING_MESSAGES:
                return outgoingMessages != null && !outgoingMessages.isEmpty();
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
        if (baseClass == Event.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == MessageEvent.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == ThrowMessageEvent.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.SEND_TASK__EVENTS: return ProcessPackage.THROW_MESSAGE_EVENT__EVENTS;
                case ProcessPackage.SEND_TASK__OUTGOING_MESSAGES: return ProcessPackage.THROW_MESSAGE_EVENT__OUTGOING_MESSAGES;
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
        if (baseClass == Event.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == MessageEvent.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == ThrowMessageEvent.class) {
            switch (baseFeatureID) {
                case ProcessPackage.THROW_MESSAGE_EVENT__EVENTS: return ProcessPackage.SEND_TASK__EVENTS;
                case ProcessPackage.THROW_MESSAGE_EVENT__OUTGOING_MESSAGES: return ProcessPackage.SEND_TASK__OUTGOING_MESSAGES;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //SendTaskImpl
