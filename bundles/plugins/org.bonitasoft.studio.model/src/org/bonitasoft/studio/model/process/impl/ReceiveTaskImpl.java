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

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.TableExpression;

import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.CatchMessageEvent;
import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.MessageEvent;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ReceiveTask;

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
 * An implementation of the model object '<em><b>Receive Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ReceiveTaskImpl#getEvent <em>Event</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ReceiveTaskImpl#getIncomingMessag <em>Incoming Messag</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ReceiveTaskImpl#getCorrelation <em>Correlation</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ReceiveTaskImpl#getMessageContent <em>Message Content</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReceiveTaskImpl extends ActivityImpl implements ReceiveTask {

    /**
     * The default value of the '{@link #getEvent() <em>Event</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getEvent()
     * @generated
     * @ordered
     */
    protected static final String EVENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEvent() <em>Event</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getEvent()
     * @generated
     * @ordered
     */
    protected String event = EVENT_EDEFAULT;

    /**
     * The cached value of the '{@link #getIncomingMessag() <em>Incoming Messag</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getIncomingMessag()
     * @generated
     * @ordered
     */
    protected MessageFlow incomingMessag;

    /**
     * The cached value of the '{@link #getCorrelation() <em>Correlation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getCorrelation()
     * @generated
     * @ordered
     */
    protected TableExpression correlation;

    /**
     * The cached value of the '{@link #getMessageContent() <em>Message Content</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMessageContent()
     * @generated
     * @ordered
     */
    protected EList<Operation> messageContent;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ReceiveTaskImpl() {
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
        return ProcessPackage.Literals.RECEIVE_TASK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getEvent() {
        return event;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEvent(String newEvent) {
        String oldEvent = event;
        event = newEvent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.RECEIVE_TASK__EVENT, oldEvent, event));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public MessageFlow getIncomingMessag() {
        if (incomingMessag != null && incomingMessag.eIsProxy()) {
            InternalEObject oldIncomingMessag = (InternalEObject) incomingMessag;
            incomingMessag = (MessageFlow) eResolveProxy(oldIncomingMessag);
            if (incomingMessag != oldIncomingMessag) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessPackage.RECEIVE_TASK__INCOMING_MESSAG,
                            oldIncomingMessag, incomingMessag));
            }
        }
        return incomingMessag;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public MessageFlow basicGetIncomingMessag() {
        return incomingMessag;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetIncomingMessag(MessageFlow newIncomingMessag, NotificationChain msgs) {
        MessageFlow oldIncomingMessag = incomingMessag;
        incomingMessag = newIncomingMessag;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.RECEIVE_TASK__INCOMING_MESSAG, oldIncomingMessag, newIncomingMessag);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setIncomingMessag(MessageFlow newIncomingMessag) {
        if (newIncomingMessag != incomingMessag) {
            NotificationChain msgs = null;
            if (incomingMessag != null)
                msgs = ((InternalEObject) incomingMessag).eInverseRemove(this, ProcessPackage.MESSAGE_FLOW__TARGET,
                        MessageFlow.class, msgs);
            if (newIncomingMessag != null)
                msgs = ((InternalEObject) newIncomingMessag).eInverseAdd(this, ProcessPackage.MESSAGE_FLOW__TARGET,
                        MessageFlow.class, msgs);
            msgs = basicSetIncomingMessag(newIncomingMessag, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.RECEIVE_TASK__INCOMING_MESSAG,
                    newIncomingMessag, newIncomingMessag));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TableExpression getCorrelation() {
        return correlation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetCorrelation(TableExpression newCorrelation, NotificationChain msgs) {
        TableExpression oldCorrelation = correlation;
        correlation = newCorrelation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.RECEIVE_TASK__CORRELATION, oldCorrelation, newCorrelation);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setCorrelation(TableExpression newCorrelation) {
        if (newCorrelation != correlation) {
            NotificationChain msgs = null;
            if (correlation != null)
                msgs = ((InternalEObject) correlation).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.RECEIVE_TASK__CORRELATION, null, msgs);
            if (newCorrelation != null)
                msgs = ((InternalEObject) newCorrelation).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.RECEIVE_TASK__CORRELATION, null, msgs);
            msgs = basicSetCorrelation(newCorrelation, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.RECEIVE_TASK__CORRELATION, newCorrelation,
                    newCorrelation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Operation> getMessageContent() {
        if (messageContent == null) {
            messageContent = new EObjectContainmentEList<Operation>(Operation.class, this,
                    ProcessPackage.RECEIVE_TASK__MESSAGE_CONTENT);
        }
        return messageContent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.RECEIVE_TASK__INCOMING_MESSAG:
                if (incomingMessag != null)
                    msgs = ((InternalEObject) incomingMessag).eInverseRemove(this, ProcessPackage.MESSAGE_FLOW__TARGET,
                            MessageFlow.class, msgs);
                return basicSetIncomingMessag((MessageFlow) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.RECEIVE_TASK__INCOMING_MESSAG:
                return basicSetIncomingMessag(null, msgs);
            case ProcessPackage.RECEIVE_TASK__CORRELATION:
                return basicSetCorrelation(null, msgs);
            case ProcessPackage.RECEIVE_TASK__MESSAGE_CONTENT:
                return ((InternalEList<?>) getMessageContent()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
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
            case ProcessPackage.RECEIVE_TASK__EVENT:
                return getEvent();
            case ProcessPackage.RECEIVE_TASK__INCOMING_MESSAG:
                if (resolve)
                    return getIncomingMessag();
                return basicGetIncomingMessag();
            case ProcessPackage.RECEIVE_TASK__CORRELATION:
                return getCorrelation();
            case ProcessPackage.RECEIVE_TASK__MESSAGE_CONTENT:
                return getMessageContent();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ProcessPackage.RECEIVE_TASK__EVENT:
                setEvent((String) newValue);
                return;
            case ProcessPackage.RECEIVE_TASK__INCOMING_MESSAG:
                setIncomingMessag((MessageFlow) newValue);
                return;
            case ProcessPackage.RECEIVE_TASK__CORRELATION:
                setCorrelation((TableExpression) newValue);
                return;
            case ProcessPackage.RECEIVE_TASK__MESSAGE_CONTENT:
                getMessageContent().clear();
                getMessageContent().addAll((Collection<? extends Operation>) newValue);
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
            case ProcessPackage.RECEIVE_TASK__EVENT:
                setEvent(EVENT_EDEFAULT);
                return;
            case ProcessPackage.RECEIVE_TASK__INCOMING_MESSAG:
                setIncomingMessag((MessageFlow) null);
                return;
            case ProcessPackage.RECEIVE_TASK__CORRELATION:
                setCorrelation((TableExpression) null);
                return;
            case ProcessPackage.RECEIVE_TASK__MESSAGE_CONTENT:
                getMessageContent().clear();
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
            case ProcessPackage.RECEIVE_TASK__EVENT:
                return EVENT_EDEFAULT == null ? event != null : !EVENT_EDEFAULT.equals(event);
            case ProcessPackage.RECEIVE_TASK__INCOMING_MESSAG:
                return incomingMessag != null;
            case ProcessPackage.RECEIVE_TASK__CORRELATION:
                return correlation != null;
            case ProcessPackage.RECEIVE_TASK__MESSAGE_CONTENT:
                return messageContent != null && !messageContent.isEmpty();
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
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == Event.class) {
            switch (derivedFeatureID) {
                default:
                    return -1;
            }
        }
        if (baseClass == MessageEvent.class) {
            switch (derivedFeatureID) {
                default:
                    return -1;
            }
        }
        if (baseClass == AbstractCatchMessageEvent.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.RECEIVE_TASK__EVENT:
                    return ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT;
                case ProcessPackage.RECEIVE_TASK__INCOMING_MESSAG:
                    return ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__INCOMING_MESSAG;
                case ProcessPackage.RECEIVE_TASK__CORRELATION:
                    return ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__CORRELATION;
                case ProcessPackage.RECEIVE_TASK__MESSAGE_CONTENT:
                    return ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT;
                default:
                    return -1;
            }
        }
        if (baseClass == CatchMessageEvent.class) {
            switch (derivedFeatureID) {
                default:
                    return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == Event.class) {
            switch (baseFeatureID) {
                default:
                    return -1;
            }
        }
        if (baseClass == MessageEvent.class) {
            switch (baseFeatureID) {
                default:
                    return -1;
            }
        }
        if (baseClass == AbstractCatchMessageEvent.class) {
            switch (baseFeatureID) {
                case ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT:
                    return ProcessPackage.RECEIVE_TASK__EVENT;
                case ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__INCOMING_MESSAG:
                    return ProcessPackage.RECEIVE_TASK__INCOMING_MESSAG;
                case ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__CORRELATION:
                    return ProcessPackage.RECEIVE_TASK__CORRELATION;
                case ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT:
                    return ProcessPackage.RECEIVE_TASK__MESSAGE_CONTENT;
                default:
                    return -1;
            }
        }
        if (baseClass == CatchMessageEvent.class) {
            switch (baseFeatureID) {
                default:
                    return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (event: "); //$NON-NLS-1$
        result.append(event);
        result.append(')');
        return result.toString();
    }

} //ReceiveTaskImpl
