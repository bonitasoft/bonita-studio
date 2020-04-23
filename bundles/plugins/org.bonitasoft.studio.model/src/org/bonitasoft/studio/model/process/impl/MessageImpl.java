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
import org.bonitasoft.studio.model.expression.TableExpression;

import org.bonitasoft.studio.model.process.Correlation;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.MessageImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.MessageImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.MessageImpl#getTextAnnotationAttachment <em>Text Annotation Attachment</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.MessageImpl#getThrowEvent <em>Throw Event</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.MessageImpl#getTtl <em>Ttl</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.MessageImpl#getCorrelation <em>Correlation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.MessageImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.MessageImpl#getTargetProcessExpression <em>Target Process Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.MessageImpl#getTargetElementExpression <em>Target Element Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.MessageImpl#getMessageContent <em>Message Content</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MessageImpl extends EObjectImpl implements Message {
	/**
     * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
	protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
	protected String documentation = DOCUMENTATION_EDEFAULT;

	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

	/**
     * The cached value of the '{@link #getTextAnnotationAttachment() <em>Text Annotation Attachment</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTextAnnotationAttachment()
     * @generated
     * @ordered
     */
	protected EList<TextAnnotationAttachment> textAnnotationAttachment;

	/**
     * The default value of the '{@link #getThrowEvent() <em>Throw Event</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getThrowEvent()
     * @generated
     * @ordered
     */
	protected static final String THROW_EVENT_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getThrowEvent() <em>Throw Event</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getThrowEvent()
     * @generated
     * @ordered
     */
	protected String throwEvent = THROW_EVENT_EDEFAULT;

	/**
     * The default value of the '{@link #getTtl() <em>Ttl</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTtl()
     * @generated
     * @ordered
     */
	protected static final String TTL_EDEFAULT = "31104000000"; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getTtl() <em>Ttl</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTtl()
     * @generated
     * @ordered
     */
	protected String ttl = TTL_EDEFAULT;

	/**
     * The cached value of the '{@link #getCorrelation() <em>Correlation</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCorrelation()
     * @generated
     * @ordered
     */
	protected Correlation correlation;

	/**
     * The cached value of the '{@link #getTargetProcessExpression() <em>Target Process Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTargetProcessExpression()
     * @generated
     * @ordered
     */
	protected Expression targetProcessExpression;

	/**
     * The cached value of the '{@link #getTargetElementExpression() <em>Target Element Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTargetElementExpression()
     * @generated
     * @ordered
     */
	protected Expression targetElementExpression;

	/**
     * The cached value of the '{@link #getMessageContent() <em>Message Content</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMessageContent()
     * @generated
     * @ordered
     */
	protected TableExpression messageContent;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected MessageImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.MESSAGE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getDocumentation() {
        return documentation;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__DOCUMENTATION, oldDocumentation, documentation));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<TextAnnotationAttachment> getTextAnnotationAttachment() {
        if (textAnnotationAttachment == null) {
            textAnnotationAttachment = new EObjectContainmentWithInverseEList<TextAnnotationAttachment>(TextAnnotationAttachment.class, this, ProcessPackage.MESSAGE__TEXT_ANNOTATION_ATTACHMENT, ProcessPackage.TEXT_ANNOTATION_ATTACHMENT__TARGET);
        }
        return textAnnotationAttachment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getThrowEvent() {
        return throwEvent;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setThrowEvent(String newThrowEvent) {
        String oldThrowEvent = throwEvent;
        throwEvent = newThrowEvent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__THROW_EVENT, oldThrowEvent, throwEvent));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getTtl() {
        return ttl;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setTtl(String newTtl) {
        String oldTtl = ttl;
        ttl = newTtl;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__TTL, oldTtl, ttl));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Correlation getCorrelation() {
        return correlation;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetCorrelation(Correlation newCorrelation, NotificationChain msgs) {
        Correlation oldCorrelation = correlation;
        correlation = newCorrelation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__CORRELATION, oldCorrelation, newCorrelation);
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
	public void setCorrelation(Correlation newCorrelation) {
        if (newCorrelation != correlation) {
            NotificationChain msgs = null;
            if (correlation != null)
                msgs = ((InternalEObject)correlation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.MESSAGE__CORRELATION, null, msgs);
            if (newCorrelation != null)
                msgs = ((InternalEObject)newCorrelation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.MESSAGE__CORRELATION, null, msgs);
            msgs = basicSetCorrelation(newCorrelation, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__CORRELATION, newCorrelation, newCorrelation));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ThrowMessageEvent getSource() {
        if (eContainerFeatureID() != ProcessPackage.MESSAGE__SOURCE) return null;
        return (ThrowMessageEvent)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSource(ThrowMessageEvent newSource, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newSource, ProcessPackage.MESSAGE__SOURCE, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setSource(ThrowMessageEvent newSource) {
        if (newSource != eInternalContainer() || (eContainerFeatureID() != ProcessPackage.MESSAGE__SOURCE && newSource != null)) {
            if (EcoreUtil.isAncestor(this, newSource))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSource != null)
                msgs = ((InternalEObject)newSource).eInverseAdd(this, ProcessPackage.THROW_MESSAGE_EVENT__EVENTS, ThrowMessageEvent.class, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__SOURCE, newSource, newSource));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getTargetProcessExpression() {
        return targetProcessExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTargetProcessExpression(Expression newTargetProcessExpression, NotificationChain msgs) {
        Expression oldTargetProcessExpression = targetProcessExpression;
        targetProcessExpression = newTargetProcessExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__TARGET_PROCESS_EXPRESSION, oldTargetProcessExpression, newTargetProcessExpression);
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
	public void setTargetProcessExpression(Expression newTargetProcessExpression) {
        if (newTargetProcessExpression != targetProcessExpression) {
            NotificationChain msgs = null;
            if (targetProcessExpression != null)
                msgs = ((InternalEObject)targetProcessExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.MESSAGE__TARGET_PROCESS_EXPRESSION, null, msgs);
            if (newTargetProcessExpression != null)
                msgs = ((InternalEObject)newTargetProcessExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.MESSAGE__TARGET_PROCESS_EXPRESSION, null, msgs);
            msgs = basicSetTargetProcessExpression(newTargetProcessExpression, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__TARGET_PROCESS_EXPRESSION, newTargetProcessExpression, newTargetProcessExpression));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getTargetElementExpression() {
        return targetElementExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTargetElementExpression(Expression newTargetElementExpression, NotificationChain msgs) {
        Expression oldTargetElementExpression = targetElementExpression;
        targetElementExpression = newTargetElementExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__TARGET_ELEMENT_EXPRESSION, oldTargetElementExpression, newTargetElementExpression);
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
	public void setTargetElementExpression(Expression newTargetElementExpression) {
        if (newTargetElementExpression != targetElementExpression) {
            NotificationChain msgs = null;
            if (targetElementExpression != null)
                msgs = ((InternalEObject)targetElementExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.MESSAGE__TARGET_ELEMENT_EXPRESSION, null, msgs);
            if (newTargetElementExpression != null)
                msgs = ((InternalEObject)newTargetElementExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.MESSAGE__TARGET_ELEMENT_EXPRESSION, null, msgs);
            msgs = basicSetTargetElementExpression(newTargetElementExpression, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__TARGET_ELEMENT_EXPRESSION, newTargetElementExpression, newTargetElementExpression));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public TableExpression getMessageContent() {
        return messageContent;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMessageContent(TableExpression newMessageContent, NotificationChain msgs) {
        TableExpression oldMessageContent = messageContent;
        messageContent = newMessageContent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__MESSAGE_CONTENT, oldMessageContent, newMessageContent);
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
	public void setMessageContent(TableExpression newMessageContent) {
        if (newMessageContent != messageContent) {
            NotificationChain msgs = null;
            if (messageContent != null)
                msgs = ((InternalEObject)messageContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.MESSAGE__MESSAGE_CONTENT, null, msgs);
            if (newMessageContent != null)
                msgs = ((InternalEObject)newMessageContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.MESSAGE__MESSAGE_CONTENT, null, msgs);
            msgs = basicSetMessageContent(newMessageContent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.MESSAGE__MESSAGE_CONTENT, newMessageContent, newMessageContent));
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
            case ProcessPackage.MESSAGE__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getTextAnnotationAttachment()).basicAdd(otherEnd, msgs);
            case ProcessPackage.MESSAGE__SOURCE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetSource((ThrowMessageEvent)otherEnd, msgs);
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
            case ProcessPackage.MESSAGE__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<?>)getTextAnnotationAttachment()).basicRemove(otherEnd, msgs);
            case ProcessPackage.MESSAGE__CORRELATION:
                return basicSetCorrelation(null, msgs);
            case ProcessPackage.MESSAGE__SOURCE:
                return basicSetSource(null, msgs);
            case ProcessPackage.MESSAGE__TARGET_PROCESS_EXPRESSION:
                return basicSetTargetProcessExpression(null, msgs);
            case ProcessPackage.MESSAGE__TARGET_ELEMENT_EXPRESSION:
                return basicSetTargetElementExpression(null, msgs);
            case ProcessPackage.MESSAGE__MESSAGE_CONTENT:
                return basicSetMessageContent(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case ProcessPackage.MESSAGE__SOURCE:
                return eInternalContainer().eInverseRemove(this, ProcessPackage.THROW_MESSAGE_EVENT__EVENTS, ThrowMessageEvent.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProcessPackage.MESSAGE__DOCUMENTATION:
                return getDocumentation();
            case ProcessPackage.MESSAGE__NAME:
                return getName();
            case ProcessPackage.MESSAGE__TEXT_ANNOTATION_ATTACHMENT:
                return getTextAnnotationAttachment();
            case ProcessPackage.MESSAGE__THROW_EVENT:
                return getThrowEvent();
            case ProcessPackage.MESSAGE__TTL:
                return getTtl();
            case ProcessPackage.MESSAGE__CORRELATION:
                return getCorrelation();
            case ProcessPackage.MESSAGE__SOURCE:
                return getSource();
            case ProcessPackage.MESSAGE__TARGET_PROCESS_EXPRESSION:
                return getTargetProcessExpression();
            case ProcessPackage.MESSAGE__TARGET_ELEMENT_EXPRESSION:
                return getTargetElementExpression();
            case ProcessPackage.MESSAGE__MESSAGE_CONTENT:
                return getMessageContent();
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
            case ProcessPackage.MESSAGE__DOCUMENTATION:
                setDocumentation((String)newValue);
                return;
            case ProcessPackage.MESSAGE__NAME:
                setName((String)newValue);
                return;
            case ProcessPackage.MESSAGE__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                getTextAnnotationAttachment().addAll((Collection<? extends TextAnnotationAttachment>)newValue);
                return;
            case ProcessPackage.MESSAGE__THROW_EVENT:
                setThrowEvent((String)newValue);
                return;
            case ProcessPackage.MESSAGE__TTL:
                setTtl((String)newValue);
                return;
            case ProcessPackage.MESSAGE__CORRELATION:
                setCorrelation((Correlation)newValue);
                return;
            case ProcessPackage.MESSAGE__SOURCE:
                setSource((ThrowMessageEvent)newValue);
                return;
            case ProcessPackage.MESSAGE__TARGET_PROCESS_EXPRESSION:
                setTargetProcessExpression((Expression)newValue);
                return;
            case ProcessPackage.MESSAGE__TARGET_ELEMENT_EXPRESSION:
                setTargetElementExpression((Expression)newValue);
                return;
            case ProcessPackage.MESSAGE__MESSAGE_CONTENT:
                setMessageContent((TableExpression)newValue);
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
            case ProcessPackage.MESSAGE__DOCUMENTATION:
                setDocumentation(DOCUMENTATION_EDEFAULT);
                return;
            case ProcessPackage.MESSAGE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ProcessPackage.MESSAGE__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                return;
            case ProcessPackage.MESSAGE__THROW_EVENT:
                setThrowEvent(THROW_EVENT_EDEFAULT);
                return;
            case ProcessPackage.MESSAGE__TTL:
                setTtl(TTL_EDEFAULT);
                return;
            case ProcessPackage.MESSAGE__CORRELATION:
                setCorrelation((Correlation)null);
                return;
            case ProcessPackage.MESSAGE__SOURCE:
                setSource((ThrowMessageEvent)null);
                return;
            case ProcessPackage.MESSAGE__TARGET_PROCESS_EXPRESSION:
                setTargetProcessExpression((Expression)null);
                return;
            case ProcessPackage.MESSAGE__TARGET_ELEMENT_EXPRESSION:
                setTargetElementExpression((Expression)null);
                return;
            case ProcessPackage.MESSAGE__MESSAGE_CONTENT:
                setMessageContent((TableExpression)null);
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
            case ProcessPackage.MESSAGE__DOCUMENTATION:
                return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
            case ProcessPackage.MESSAGE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ProcessPackage.MESSAGE__TEXT_ANNOTATION_ATTACHMENT:
                return textAnnotationAttachment != null && !textAnnotationAttachment.isEmpty();
            case ProcessPackage.MESSAGE__THROW_EVENT:
                return THROW_EVENT_EDEFAULT == null ? throwEvent != null : !THROW_EVENT_EDEFAULT.equals(throwEvent);
            case ProcessPackage.MESSAGE__TTL:
                return TTL_EDEFAULT == null ? ttl != null : !TTL_EDEFAULT.equals(ttl);
            case ProcessPackage.MESSAGE__CORRELATION:
                return correlation != null;
            case ProcessPackage.MESSAGE__SOURCE:
                return getSource() != null;
            case ProcessPackage.MESSAGE__TARGET_PROCESS_EXPRESSION:
                return targetProcessExpression != null;
            case ProcessPackage.MESSAGE__TARGET_ELEMENT_EXPRESSION:
                return targetElementExpression != null;
            case ProcessPackage.MESSAGE__MESSAGE_CONTENT:
                return messageContent != null;
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
        result.append(" (documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", throwEvent: "); //$NON-NLS-1$
        result.append(throwEvent);
        result.append(", ttl: "); //$NON-NLS-1$
        result.append(ttl);
        result.append(')');
        return result.toString();
    }

} //MessageImpl
