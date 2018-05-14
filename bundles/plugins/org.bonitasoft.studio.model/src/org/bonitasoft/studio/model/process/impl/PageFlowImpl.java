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

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;

import org.bonitasoft.studio.model.form.Form;

import org.bonitasoft.studio.model.process.AbstractPageFlow;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.EntryPageFlowType;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.PageFlowTransition;
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
 * An implementation of the model object '<em><b>Page Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getRegExpToHideDefaultField <em>Reg Exp To Hide Default
 * Field</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#isUseRegExpToHideDefaultField <em>Use Reg Exp To Hide
 * Default Field</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getByPassFormsGeneration <em>By Pass Forms
 * Generation</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getConfirmationTemplate <em>Confirmation
 * Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getTransientData <em>Transient Data</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getPageFlowConnectors <em>Page Flow Connectors</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getEntryPageFlowType <em>Entry Page Flow Type</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#isTransmitURLAsParameter <em>Transmit URL As
 * Parameter</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getPageFlowTransitions <em>Page Flow
 * Transitions</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getForm <em>Form</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getEntryRedirectionURL <em>Entry Redirection
 * URL</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getConfirmationMessage <em>Confirmation
 * Message</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getEntryRedirectionActions <em>Entry Redirection
 * Actions</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.PageFlowImpl#getFormMapping <em>Form Mapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PageFlowImpl extends ConnectableElementImpl implements PageFlow {

    /**
     * The default value of the '{@link #getRegExpToHideDefaultField() <em>Reg Exp To Hide Default Field</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRegExpToHideDefaultField()
     * @generated
     * @ordered
     */
    protected static final String REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRegExpToHideDefaultField() <em>Reg Exp To Hide Default Field</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRegExpToHideDefaultField()
     * @generated
     * @ordered
     */
    protected String regExpToHideDefaultField = REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT;

    /**
     * The default value of the '{@link #isUseRegExpToHideDefaultField() <em>Use Reg Exp To Hide Default Field</em>}'
     * attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isUseRegExpToHideDefaultField()
     * @generated
     * @ordered
     */
    protected static final boolean USE_REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isUseRegExpToHideDefaultField() <em>Use Reg Exp To Hide Default Field</em>}'
     * attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isUseRegExpToHideDefaultField()
     * @generated
     * @ordered
     */
    protected boolean useRegExpToHideDefaultField = USE_REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT;

    /**
     * The default value of the '{@link #getByPassFormsGeneration() <em>By Pass Forms Generation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getByPassFormsGeneration()
     * @generated
     * @ordered
     */
    protected static final Boolean BY_PASS_FORMS_GENERATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getByPassFormsGeneration() <em>By Pass Forms Generation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getByPassFormsGeneration()
     * @generated
     * @ordered
     */
    protected Boolean byPassFormsGeneration = BY_PASS_FORMS_GENERATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getConfirmationTemplate() <em>Confirmation Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getConfirmationTemplate()
     * @generated
     * @ordered
     */
    protected AssociatedFile confirmationTemplate;

    /**
     * The cached value of the '{@link #getTransientData() <em>Transient Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTransientData()
     * @generated
     * @ordered
     */
    protected EList<Data> transientData;

    /**
     * The cached value of the '{@link #getPageFlowConnectors() <em>Page Flow Connectors</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPageFlowConnectors()
     * @generated
     * @ordered
     */
    protected EList<Connector> pageFlowConnectors;

    /**
     * The default value of the '{@link #getEntryPageFlowType() <em>Entry Page Flow Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getEntryPageFlowType()
     * @generated
     * @ordered
     */
    protected static final EntryPageFlowType ENTRY_PAGE_FLOW_TYPE_EDEFAULT = EntryPageFlowType.PAGEFLOW;

    /**
     * The cached value of the '{@link #getEntryPageFlowType() <em>Entry Page Flow Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getEntryPageFlowType()
     * @generated
     * @ordered
     */
    protected EntryPageFlowType entryPageFlowType = ENTRY_PAGE_FLOW_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #isTransmitURLAsParameter() <em>Transmit URL As Parameter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isTransmitURLAsParameter()
     * @generated
     * @ordered
     */
    protected static final boolean TRANSMIT_URL_AS_PARAMETER_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isTransmitURLAsParameter() <em>Transmit URL As Parameter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isTransmitURLAsParameter()
     * @generated
     * @ordered
     */
    protected boolean transmitURLAsParameter = TRANSMIT_URL_AS_PARAMETER_EDEFAULT;

    /**
     * The cached value of the '{@link #getPageFlowTransitions() <em>Page Flow Transitions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPageFlowTransitions()
     * @generated
     * @ordered
     */
    protected EList<PageFlowTransition> pageFlowTransitions;

    /**
     * The cached value of the '{@link #getForm() <em>Form</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getForm()
     * @generated
     * @ordered
     */
    protected EList<Form> form;

    /**
     * The cached value of the '{@link #getEntryRedirectionURL() <em>Entry Redirection URL</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getEntryRedirectionURL()
     * @generated
     * @ordered
     */
    protected Expression entryRedirectionURL;

    /**
     * The cached value of the '{@link #getConfirmationMessage() <em>Confirmation Message</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getConfirmationMessage()
     * @generated
     * @ordered
     */
    protected Expression confirmationMessage;

    /**
     * The cached value of the '{@link #getEntryRedirectionActions() <em>Entry Redirection Actions</em>}' containment
     * reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getEntryRedirectionActions()
     * @generated
     * @ordered
     */
    protected EList<Operation> entryRedirectionActions;

    /**
     * The cached value of the '{@link #getFormMapping() <em>Form Mapping</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getFormMapping()
     * @generated
     * @ordered
     */
    protected FormMapping formMapping;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PageFlowImpl() {
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
        return ProcessPackage.Literals.PAGE_FLOW;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getRegExpToHideDefaultField() {
        return regExpToHideDefaultField;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRegExpToHideDefaultField(String newRegExpToHideDefaultField) {
        String oldRegExpToHideDefaultField = regExpToHideDefaultField;
        regExpToHideDefaultField = newRegExpToHideDefaultField;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD,
                    oldRegExpToHideDefaultField, regExpToHideDefaultField));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isUseRegExpToHideDefaultField() {
        return useRegExpToHideDefaultField;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setUseRegExpToHideDefaultField(boolean newUseRegExpToHideDefaultField) {
        boolean oldUseRegExpToHideDefaultField = useRegExpToHideDefaultField;
        useRegExpToHideDefaultField = newUseRegExpToHideDefaultField;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD, oldUseRegExpToHideDefaultField,
                    useRegExpToHideDefaultField));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Boolean getByPassFormsGeneration() {
        return byPassFormsGeneration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setByPassFormsGeneration(Boolean newByPassFormsGeneration) {
        Boolean oldByPassFormsGeneration = byPassFormsGeneration;
        byPassFormsGeneration = newByPassFormsGeneration;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PAGE_FLOW__BY_PASS_FORMS_GENERATION,
                    oldByPassFormsGeneration, byPassFormsGeneration));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssociatedFile getConfirmationTemplate() {
        return confirmationTemplate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetConfirmationTemplate(AssociatedFile newConfirmationTemplate, NotificationChain msgs) {
        AssociatedFile oldConfirmationTemplate = confirmationTemplate;
        confirmationTemplate = newConfirmationTemplate;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE, oldConfirmationTemplate, newConfirmationTemplate);
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
    public void setConfirmationTemplate(AssociatedFile newConfirmationTemplate) {
        if (newConfirmationTemplate != confirmationTemplate) {
            NotificationChain msgs = null;
            if (confirmationTemplate != null)
                msgs = ((InternalEObject) confirmationTemplate).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE, null, msgs);
            if (newConfirmationTemplate != null)
                msgs = ((InternalEObject) newConfirmationTemplate).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE, null, msgs);
            msgs = basicSetConfirmationTemplate(newConfirmationTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE,
                    newConfirmationTemplate, newConfirmationTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Data> getTransientData() {
        if (transientData == null) {
            transientData = new EObjectContainmentEList<Data>(Data.class, this, ProcessPackage.PAGE_FLOW__TRANSIENT_DATA);
        }
        return transientData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Connector> getPageFlowConnectors() {
        if (pageFlowConnectors == null) {
            pageFlowConnectors = new EObjectContainmentEList<Connector>(Connector.class, this,
                    ProcessPackage.PAGE_FLOW__PAGE_FLOW_CONNECTORS);
        }
        return pageFlowConnectors;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EntryPageFlowType getEntryPageFlowType() {
        return entryPageFlowType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEntryPageFlowType(EntryPageFlowType newEntryPageFlowType) {
        EntryPageFlowType oldEntryPageFlowType = entryPageFlowType;
        entryPageFlowType = newEntryPageFlowType == null ? ENTRY_PAGE_FLOW_TYPE_EDEFAULT : newEntryPageFlowType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE,
                    oldEntryPageFlowType, entryPageFlowType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isTransmitURLAsParameter() {
        return transmitURLAsParameter;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTransmitURLAsParameter(boolean newTransmitURLAsParameter) {
        boolean oldTransmitURLAsParameter = transmitURLAsParameter;
        transmitURLAsParameter = newTransmitURLAsParameter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PAGE_FLOW__TRANSMIT_URL_AS_PARAMETER,
                    oldTransmitURLAsParameter, transmitURLAsParameter));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<PageFlowTransition> getPageFlowTransitions() {
        if (pageFlowTransitions == null) {
            pageFlowTransitions = new EObjectContainmentEList<PageFlowTransition>(PageFlowTransition.class, this,
                    ProcessPackage.PAGE_FLOW__PAGE_FLOW_TRANSITIONS);
        }
        return pageFlowTransitions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Form> getForm() {
        if (form == null) {
            form = new EObjectContainmentEList<Form>(Form.class, this, ProcessPackage.PAGE_FLOW__FORM);
        }
        return form;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getEntryRedirectionURL() {
        return entryRedirectionURL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetEntryRedirectionURL(Expression newEntryRedirectionURL, NotificationChain msgs) {
        Expression oldEntryRedirectionURL = entryRedirectionURL;
        entryRedirectionURL = newEntryRedirectionURL;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL, oldEntryRedirectionURL, newEntryRedirectionURL);
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
    public void setEntryRedirectionURL(Expression newEntryRedirectionURL) {
        if (newEntryRedirectionURL != entryRedirectionURL) {
            NotificationChain msgs = null;
            if (entryRedirectionURL != null)
                msgs = ((InternalEObject) entryRedirectionURL).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL, null, msgs);
            if (newEntryRedirectionURL != null)
                msgs = ((InternalEObject) newEntryRedirectionURL).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL, null, msgs);
            msgs = basicSetEntryRedirectionURL(newEntryRedirectionURL, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL,
                    newEntryRedirectionURL, newEntryRedirectionURL));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getConfirmationMessage() {
        return confirmationMessage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetConfirmationMessage(Expression newConfirmationMessage, NotificationChain msgs) {
        Expression oldConfirmationMessage = confirmationMessage;
        confirmationMessage = newConfirmationMessage;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE, oldConfirmationMessage, newConfirmationMessage);
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
    public void setConfirmationMessage(Expression newConfirmationMessage) {
        if (newConfirmationMessage != confirmationMessage) {
            NotificationChain msgs = null;
            if (confirmationMessage != null)
                msgs = ((InternalEObject) confirmationMessage).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE, null, msgs);
            if (newConfirmationMessage != null)
                msgs = ((InternalEObject) newConfirmationMessage).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE, null, msgs);
            msgs = basicSetConfirmationMessage(newConfirmationMessage, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE,
                    newConfirmationMessage, newConfirmationMessage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Operation> getEntryRedirectionActions() {
        if (entryRedirectionActions == null) {
            entryRedirectionActions = new EObjectContainmentEList<Operation>(Operation.class, this,
                    ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS);
        }
        return entryRedirectionActions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public FormMapping getFormMapping() {
        return formMapping;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetFormMapping(FormMapping newFormMapping, NotificationChain msgs) {
        FormMapping oldFormMapping = formMapping;
        formMapping = newFormMapping;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.PAGE_FLOW__FORM_MAPPING, oldFormMapping, newFormMapping);
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
    public void setFormMapping(FormMapping newFormMapping) {
        if (newFormMapping != formMapping) {
            NotificationChain msgs = null;
            if (formMapping != null)
                msgs = ((InternalEObject) formMapping).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PAGE_FLOW__FORM_MAPPING, null, msgs);
            if (newFormMapping != null)
                msgs = ((InternalEObject) newFormMapping).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PAGE_FLOW__FORM_MAPPING, null, msgs);
            msgs = basicSetFormMapping(newFormMapping, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PAGE_FLOW__FORM_MAPPING, newFormMapping,
                    newFormMapping));
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
            case ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE:
                return basicSetConfirmationTemplate(null, msgs);
            case ProcessPackage.PAGE_FLOW__TRANSIENT_DATA:
                return ((InternalEList<?>) getTransientData()).basicRemove(otherEnd, msgs);
            case ProcessPackage.PAGE_FLOW__PAGE_FLOW_CONNECTORS:
                return ((InternalEList<?>) getPageFlowConnectors()).basicRemove(otherEnd, msgs);
            case ProcessPackage.PAGE_FLOW__PAGE_FLOW_TRANSITIONS:
                return ((InternalEList<?>) getPageFlowTransitions()).basicRemove(otherEnd, msgs);
            case ProcessPackage.PAGE_FLOW__FORM:
                return ((InternalEList<?>) getForm()).basicRemove(otherEnd, msgs);
            case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL:
                return basicSetEntryRedirectionURL(null, msgs);
            case ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE:
                return basicSetConfirmationMessage(null, msgs);
            case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS:
                return ((InternalEList<?>) getEntryRedirectionActions()).basicRemove(otherEnd, msgs);
            case ProcessPackage.PAGE_FLOW__FORM_MAPPING:
                return basicSetFormMapping(null, msgs);
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
            case ProcessPackage.PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return getRegExpToHideDefaultField();
            case ProcessPackage.PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return isUseRegExpToHideDefaultField();
            case ProcessPackage.PAGE_FLOW__BY_PASS_FORMS_GENERATION:
                return getByPassFormsGeneration();
            case ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE:
                return getConfirmationTemplate();
            case ProcessPackage.PAGE_FLOW__TRANSIENT_DATA:
                return getTransientData();
            case ProcessPackage.PAGE_FLOW__PAGE_FLOW_CONNECTORS:
                return getPageFlowConnectors();
            case ProcessPackage.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE:
                return getEntryPageFlowType();
            case ProcessPackage.PAGE_FLOW__TRANSMIT_URL_AS_PARAMETER:
                return isTransmitURLAsParameter();
            case ProcessPackage.PAGE_FLOW__PAGE_FLOW_TRANSITIONS:
                return getPageFlowTransitions();
            case ProcessPackage.PAGE_FLOW__FORM:
                return getForm();
            case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL:
                return getEntryRedirectionURL();
            case ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE:
                return getConfirmationMessage();
            case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS:
                return getEntryRedirectionActions();
            case ProcessPackage.PAGE_FLOW__FORM_MAPPING:
                return getFormMapping();
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
            case ProcessPackage.PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setRegExpToHideDefaultField((String) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setUseRegExpToHideDefaultField((Boolean) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__BY_PASS_FORMS_GENERATION:
                setByPassFormsGeneration((Boolean) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE:
                setConfirmationTemplate((AssociatedFile) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__TRANSIENT_DATA:
                getTransientData().clear();
                getTransientData().addAll((Collection<? extends Data>) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__PAGE_FLOW_CONNECTORS:
                getPageFlowConnectors().clear();
                getPageFlowConnectors().addAll((Collection<? extends Connector>) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE:
                setEntryPageFlowType((EntryPageFlowType) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__TRANSMIT_URL_AS_PARAMETER:
                setTransmitURLAsParameter((Boolean) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__PAGE_FLOW_TRANSITIONS:
                getPageFlowTransitions().clear();
                getPageFlowTransitions().addAll((Collection<? extends PageFlowTransition>) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__FORM:
                getForm().clear();
                getForm().addAll((Collection<? extends Form>) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL:
                setEntryRedirectionURL((Expression) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE:
                setConfirmationMessage((Expression) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS:
                getEntryRedirectionActions().clear();
                getEntryRedirectionActions().addAll((Collection<? extends Operation>) newValue);
                return;
            case ProcessPackage.PAGE_FLOW__FORM_MAPPING:
                setFormMapping((FormMapping) newValue);
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
            case ProcessPackage.PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setRegExpToHideDefaultField(REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT);
                return;
            case ProcessPackage.PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setUseRegExpToHideDefaultField(USE_REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT);
                return;
            case ProcessPackage.PAGE_FLOW__BY_PASS_FORMS_GENERATION:
                setByPassFormsGeneration(BY_PASS_FORMS_GENERATION_EDEFAULT);
                return;
            case ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE:
                setConfirmationTemplate((AssociatedFile) null);
                return;
            case ProcessPackage.PAGE_FLOW__TRANSIENT_DATA:
                getTransientData().clear();
                return;
            case ProcessPackage.PAGE_FLOW__PAGE_FLOW_CONNECTORS:
                getPageFlowConnectors().clear();
                return;
            case ProcessPackage.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE:
                setEntryPageFlowType(ENTRY_PAGE_FLOW_TYPE_EDEFAULT);
                return;
            case ProcessPackage.PAGE_FLOW__TRANSMIT_URL_AS_PARAMETER:
                setTransmitURLAsParameter(TRANSMIT_URL_AS_PARAMETER_EDEFAULT);
                return;
            case ProcessPackage.PAGE_FLOW__PAGE_FLOW_TRANSITIONS:
                getPageFlowTransitions().clear();
                return;
            case ProcessPackage.PAGE_FLOW__FORM:
                getForm().clear();
                return;
            case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL:
                setEntryRedirectionURL((Expression) null);
                return;
            case ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE:
                setConfirmationMessage((Expression) null);
                return;
            case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS:
                getEntryRedirectionActions().clear();
                return;
            case ProcessPackage.PAGE_FLOW__FORM_MAPPING:
                setFormMapping((FormMapping) null);
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
            case ProcessPackage.PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT == null ? regExpToHideDefaultField != null
                        : !REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT.equals(regExpToHideDefaultField);
            case ProcessPackage.PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return useRegExpToHideDefaultField != USE_REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT;
            case ProcessPackage.PAGE_FLOW__BY_PASS_FORMS_GENERATION:
                return BY_PASS_FORMS_GENERATION_EDEFAULT == null ? byPassFormsGeneration != null
                        : !BY_PASS_FORMS_GENERATION_EDEFAULT.equals(byPassFormsGeneration);
            case ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE:
                return confirmationTemplate != null;
            case ProcessPackage.PAGE_FLOW__TRANSIENT_DATA:
                return transientData != null && !transientData.isEmpty();
            case ProcessPackage.PAGE_FLOW__PAGE_FLOW_CONNECTORS:
                return pageFlowConnectors != null && !pageFlowConnectors.isEmpty();
            case ProcessPackage.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE:
                return entryPageFlowType != ENTRY_PAGE_FLOW_TYPE_EDEFAULT;
            case ProcessPackage.PAGE_FLOW__TRANSMIT_URL_AS_PARAMETER:
                return transmitURLAsParameter != TRANSMIT_URL_AS_PARAMETER_EDEFAULT;
            case ProcessPackage.PAGE_FLOW__PAGE_FLOW_TRANSITIONS:
                return pageFlowTransitions != null && !pageFlowTransitions.isEmpty();
            case ProcessPackage.PAGE_FLOW__FORM:
                return form != null && !form.isEmpty();
            case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL:
                return entryRedirectionURL != null;
            case ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE:
                return confirmationMessage != null;
            case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS:
                return entryRedirectionActions != null && !entryRedirectionActions.isEmpty();
            case ProcessPackage.PAGE_FLOW__FORM_MAPPING:
                return formMapping != null;
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
        if (baseClass == AbstractPageFlow.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.ABSTRACT_PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD;
                case ProcessPackage.PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.ABSTRACT_PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD;
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
        if (baseClass == AbstractPageFlow.class) {
            switch (baseFeatureID) {
                case ProcessPackage.ABSTRACT_PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD;
                case ProcessPackage.ABSTRACT_PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD;
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
        result.append(" (regExpToHideDefaultField: "); //$NON-NLS-1$
        result.append(regExpToHideDefaultField);
        result.append(", useRegExpToHideDefaultField: "); //$NON-NLS-1$
        result.append(useRegExpToHideDefaultField);
        result.append(", byPassFormsGeneration: "); //$NON-NLS-1$
        result.append(byPassFormsGeneration);
        result.append(", entryPageFlowType: "); //$NON-NLS-1$
        result.append(entryPageFlowType);
        result.append(", transmitURLAsParameter: "); //$NON-NLS-1$
        result.append(transmitURLAsParameter);
        result.append(')');
        return result.toString();
    }

} //PageFlowImpl
