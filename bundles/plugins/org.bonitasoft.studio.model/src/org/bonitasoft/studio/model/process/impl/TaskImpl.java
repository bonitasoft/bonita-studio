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
import org.bonitasoft.studio.model.expression.Operation;

import org.bonitasoft.studio.model.form.Form;

import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Assignable;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.EntryPageFlowType;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.PageFlowTransition;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ResourceContainer;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.bonitasoft.studio.model.process.Task;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
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
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getByPassFormsGeneration <em>By Pass Forms Generation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getConfirmationTemplate <em>Confirmation Template</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getTransientData <em>Transient Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getPageFlowConnectors <em>Page Flow Connectors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getEntryPageFlowType <em>Entry Page Flow Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#isTransmitURLAsParameter <em>Transmit URL As Parameter</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getPageFlowTransitions <em>Page Flow Transitions</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getForm <em>Form</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getEntryRedirectionURL <em>Entry Redirection URL</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getConfirmationMessage <em>Confirmation Message</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getEntryRedirectionActions <em>Entry Redirection Actions</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getFormMapping <em>Form Mapping</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getHtmlTemplate <em>Html Template</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getResourceJars <em>Resource Jars</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getResourceValidators <em>Resource Validators</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getResourceFiles <em>Resource Files</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.TaskImpl#getResourceFolders <em>Resource Folders</em>}</li>
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
	 * The default value of the '{@link #getByPassFormsGeneration() <em>By Pass Forms Generation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getByPassFormsGeneration()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean BY_PASS_FORMS_GENERATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getByPassFormsGeneration() <em>By Pass Forms Generation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getByPassFormsGeneration()
	 * @generated
	 * @ordered
	 */
	protected Boolean byPassFormsGeneration = BY_PASS_FORMS_GENERATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConfirmationTemplate() <em>Confirmation Template</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfirmationTemplate()
	 * @generated
	 * @ordered
	 */
	protected AssociatedFile confirmationTemplate;

	/**
	 * The cached value of the '{@link #getTransientData() <em>Transient Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransientData()
	 * @generated
	 * @ordered
	 */
	protected EList<Data> transientData;

	/**
	 * The cached value of the '{@link #getPageFlowConnectors() <em>Page Flow Connectors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageFlowConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<Connector> pageFlowConnectors;

	/**
	 * The default value of the '{@link #getEntryPageFlowType() <em>Entry Page Flow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntryPageFlowType()
	 * @generated
	 * @ordered
	 */
	protected static final EntryPageFlowType ENTRY_PAGE_FLOW_TYPE_EDEFAULT = EntryPageFlowType.PAGEFLOW;

	/**
	 * The cached value of the '{@link #getEntryPageFlowType() <em>Entry Page Flow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntryPageFlowType()
	 * @generated
	 * @ordered
	 */
	protected EntryPageFlowType entryPageFlowType = ENTRY_PAGE_FLOW_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isTransmitURLAsParameter() <em>Transmit URL As Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransmitURLAsParameter()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRANSMIT_URL_AS_PARAMETER_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isTransmitURLAsParameter() <em>Transmit URL As Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransmitURLAsParameter()
	 * @generated
	 * @ordered
	 */
	protected boolean transmitURLAsParameter = TRANSMIT_URL_AS_PARAMETER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPageFlowTransitions() <em>Page Flow Transitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageFlowTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<PageFlowTransition> pageFlowTransitions;

	/**
	 * The cached value of the '{@link #getForm() <em>Form</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForm()
	 * @generated
	 * @ordered
	 */
	protected EList<Form> form;

	/**
	 * The cached value of the '{@link #getEntryRedirectionURL() <em>Entry Redirection URL</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntryRedirectionURL()
	 * @generated
	 * @ordered
	 */
	protected Expression entryRedirectionURL;

	/**
	 * The cached value of the '{@link #getConfirmationMessage() <em>Confirmation Message</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfirmationMessage()
	 * @generated
	 * @ordered
	 */
	protected Expression confirmationMessage;

	/**
	 * The cached value of the '{@link #getEntryRedirectionActions() <em>Entry Redirection Actions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntryRedirectionActions()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> entryRedirectionActions;

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
	 * The cached value of the '{@link #getHtmlTemplate() <em>Html Template</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHtmlTemplate()
	 * @generated
	 * @ordered
	 */
	protected AssociatedFile htmlTemplate;

	/**
	 * The cached value of the '{@link #getResourceJars() <em>Resource Jars</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceJars()
	 * @generated
	 * @ordered
	 */
	protected EList<String> resourceJars;

	/**
	 * The cached value of the '{@link #getResourceValidators() <em>Resource Validators</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceValidators()
	 * @generated
	 * @ordered
	 */
	protected EList<String> resourceValidators;

	/**
	 * The cached value of the '{@link #getResourceFiles() <em>Resource Files</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceFiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceFile> resourceFiles;

	/**
	 * The cached value of the '{@link #getResourceFolders() <em>Resource Folders</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceFolders()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceFolder> resourceFolders;

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
	public Boolean getByPassFormsGeneration() {
		return byPassFormsGeneration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setByPassFormsGeneration(Boolean newByPassFormsGeneration) {
		Boolean oldByPassFormsGeneration = byPassFormsGeneration;
		byPassFormsGeneration = newByPassFormsGeneration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__BY_PASS_FORMS_GENERATION, oldByPassFormsGeneration, byPassFormsGeneration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociatedFile getConfirmationTemplate() {
		return confirmationTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConfirmationTemplate(AssociatedFile newConfirmationTemplate, NotificationChain msgs) {
		AssociatedFile oldConfirmationTemplate = confirmationTemplate;
		confirmationTemplate = newConfirmationTemplate;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__CONFIRMATION_TEMPLATE, oldConfirmationTemplate, newConfirmationTemplate);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfirmationTemplate(AssociatedFile newConfirmationTemplate) {
		if (newConfirmationTemplate != confirmationTemplate) {
			NotificationChain msgs = null;
			if (confirmationTemplate != null)
				msgs = ((InternalEObject)confirmationTemplate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__CONFIRMATION_TEMPLATE, null, msgs);
			if (newConfirmationTemplate != null)
				msgs = ((InternalEObject)newConfirmationTemplate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__CONFIRMATION_TEMPLATE, null, msgs);
			msgs = basicSetConfirmationTemplate(newConfirmationTemplate, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__CONFIRMATION_TEMPLATE, newConfirmationTemplate, newConfirmationTemplate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Data> getTransientData() {
		if (transientData == null) {
			transientData = new EObjectContainmentEList<Data>(Data.class, this, ProcessPackage.TASK__TRANSIENT_DATA);
		}
		return transientData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Connector> getPageFlowConnectors() {
		if (pageFlowConnectors == null) {
			pageFlowConnectors = new EObjectContainmentEList<Connector>(Connector.class, this, ProcessPackage.TASK__PAGE_FLOW_CONNECTORS);
		}
		return pageFlowConnectors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntryPageFlowType getEntryPageFlowType() {
		return entryPageFlowType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntryPageFlowType(EntryPageFlowType newEntryPageFlowType) {
		EntryPageFlowType oldEntryPageFlowType = entryPageFlowType;
		entryPageFlowType = newEntryPageFlowType == null ? ENTRY_PAGE_FLOW_TYPE_EDEFAULT : newEntryPageFlowType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__ENTRY_PAGE_FLOW_TYPE, oldEntryPageFlowType, entryPageFlowType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTransmitURLAsParameter() {
		return transmitURLAsParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransmitURLAsParameter(boolean newTransmitURLAsParameter) {
		boolean oldTransmitURLAsParameter = transmitURLAsParameter;
		transmitURLAsParameter = newTransmitURLAsParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__TRANSMIT_URL_AS_PARAMETER, oldTransmitURLAsParameter, transmitURLAsParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PageFlowTransition> getPageFlowTransitions() {
		if (pageFlowTransitions == null) {
			pageFlowTransitions = new EObjectContainmentEList<PageFlowTransition>(PageFlowTransition.class, this, ProcessPackage.TASK__PAGE_FLOW_TRANSITIONS);
		}
		return pageFlowTransitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Form> getForm() {
		if (form == null) {
			form = new EObjectContainmentEList<Form>(Form.class, this, ProcessPackage.TASK__FORM);
		}
		return form;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getEntryRedirectionURL() {
		return entryRedirectionURL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEntryRedirectionURL(Expression newEntryRedirectionURL, NotificationChain msgs) {
		Expression oldEntryRedirectionURL = entryRedirectionURL;
		entryRedirectionURL = newEntryRedirectionURL;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__ENTRY_REDIRECTION_URL, oldEntryRedirectionURL, newEntryRedirectionURL);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntryRedirectionURL(Expression newEntryRedirectionURL) {
		if (newEntryRedirectionURL != entryRedirectionURL) {
			NotificationChain msgs = null;
			if (entryRedirectionURL != null)
				msgs = ((InternalEObject)entryRedirectionURL).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__ENTRY_REDIRECTION_URL, null, msgs);
			if (newEntryRedirectionURL != null)
				msgs = ((InternalEObject)newEntryRedirectionURL).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__ENTRY_REDIRECTION_URL, null, msgs);
			msgs = basicSetEntryRedirectionURL(newEntryRedirectionURL, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__ENTRY_REDIRECTION_URL, newEntryRedirectionURL, newEntryRedirectionURL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getConfirmationMessage() {
		return confirmationMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConfirmationMessage(Expression newConfirmationMessage, NotificationChain msgs) {
		Expression oldConfirmationMessage = confirmationMessage;
		confirmationMessage = newConfirmationMessage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__CONFIRMATION_MESSAGE, oldConfirmationMessage, newConfirmationMessage);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfirmationMessage(Expression newConfirmationMessage) {
		if (newConfirmationMessage != confirmationMessage) {
			NotificationChain msgs = null;
			if (confirmationMessage != null)
				msgs = ((InternalEObject)confirmationMessage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__CONFIRMATION_MESSAGE, null, msgs);
			if (newConfirmationMessage != null)
				msgs = ((InternalEObject)newConfirmationMessage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__CONFIRMATION_MESSAGE, null, msgs);
			msgs = basicSetConfirmationMessage(newConfirmationMessage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__CONFIRMATION_MESSAGE, newConfirmationMessage, newConfirmationMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Operation> getEntryRedirectionActions() {
		if (entryRedirectionActions == null) {
			entryRedirectionActions = new EObjectContainmentEList<Operation>(Operation.class, this, ProcessPackage.TASK__ENTRY_REDIRECTION_ACTIONS);
		}
		return entryRedirectionActions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public AssociatedFile getHtmlTemplate() {
		return htmlTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHtmlTemplate(AssociatedFile newHtmlTemplate, NotificationChain msgs) {
		AssociatedFile oldHtmlTemplate = htmlTemplate;
		htmlTemplate = newHtmlTemplate;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__HTML_TEMPLATE, oldHtmlTemplate, newHtmlTemplate);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHtmlTemplate(AssociatedFile newHtmlTemplate) {
		if (newHtmlTemplate != htmlTemplate) {
			NotificationChain msgs = null;
			if (htmlTemplate != null)
				msgs = ((InternalEObject)htmlTemplate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__HTML_TEMPLATE, null, msgs);
			if (newHtmlTemplate != null)
				msgs = ((InternalEObject)newHtmlTemplate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.TASK__HTML_TEMPLATE, null, msgs);
			msgs = basicSetHtmlTemplate(newHtmlTemplate, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TASK__HTML_TEMPLATE, newHtmlTemplate, newHtmlTemplate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getResourceJars() {
		if (resourceJars == null) {
			resourceJars = new EDataTypeUniqueEList<String>(String.class, this, ProcessPackage.TASK__RESOURCE_JARS);
		}
		return resourceJars;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getResourceValidators() {
		if (resourceValidators == null) {
			resourceValidators = new EDataTypeUniqueEList<String>(String.class, this, ProcessPackage.TASK__RESOURCE_VALIDATORS);
		}
		return resourceValidators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceFile> getResourceFiles() {
		if (resourceFiles == null) {
			resourceFiles = new EObjectContainmentEList<ResourceFile>(ResourceFile.class, this, ProcessPackage.TASK__RESOURCE_FILES);
		}
		return resourceFiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceFolder> getResourceFolders() {
		if (resourceFolders == null) {
			resourceFolders = new EObjectContainmentEList<ResourceFolder>(ResourceFolder.class, this, ProcessPackage.TASK__RESOURCE_FOLDERS);
		}
		return resourceFolders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public boolean isOverrideActorsOfTheLane() {
		return overrideActorsOfTheLane;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public int getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
			case ProcessPackage.TASK__CONFIRMATION_TEMPLATE:
				return basicSetConfirmationTemplate(null, msgs);
			case ProcessPackage.TASK__TRANSIENT_DATA:
				return ((InternalEList<?>)getTransientData()).basicRemove(otherEnd, msgs);
			case ProcessPackage.TASK__PAGE_FLOW_CONNECTORS:
				return ((InternalEList<?>)getPageFlowConnectors()).basicRemove(otherEnd, msgs);
			case ProcessPackage.TASK__PAGE_FLOW_TRANSITIONS:
				return ((InternalEList<?>)getPageFlowTransitions()).basicRemove(otherEnd, msgs);
			case ProcessPackage.TASK__FORM:
				return ((InternalEList<?>)getForm()).basicRemove(otherEnd, msgs);
			case ProcessPackage.TASK__ENTRY_REDIRECTION_URL:
				return basicSetEntryRedirectionURL(null, msgs);
			case ProcessPackage.TASK__CONFIRMATION_MESSAGE:
				return basicSetConfirmationMessage(null, msgs);
			case ProcessPackage.TASK__ENTRY_REDIRECTION_ACTIONS:
				return ((InternalEList<?>)getEntryRedirectionActions()).basicRemove(otherEnd, msgs);
			case ProcessPackage.TASK__FORM_MAPPING:
				return basicSetFormMapping(null, msgs);
			case ProcessPackage.TASK__HTML_TEMPLATE:
				return basicSetHtmlTemplate(null, msgs);
			case ProcessPackage.TASK__RESOURCE_FILES:
				return ((InternalEList<?>)getResourceFiles()).basicRemove(otherEnd, msgs);
			case ProcessPackage.TASK__RESOURCE_FOLDERS:
				return ((InternalEList<?>)getResourceFolders()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.TASK__BY_PASS_FORMS_GENERATION:
				return getByPassFormsGeneration();
			case ProcessPackage.TASK__CONFIRMATION_TEMPLATE:
				return getConfirmationTemplate();
			case ProcessPackage.TASK__TRANSIENT_DATA:
				return getTransientData();
			case ProcessPackage.TASK__PAGE_FLOW_CONNECTORS:
				return getPageFlowConnectors();
			case ProcessPackage.TASK__ENTRY_PAGE_FLOW_TYPE:
				return getEntryPageFlowType();
			case ProcessPackage.TASK__TRANSMIT_URL_AS_PARAMETER:
				return isTransmitURLAsParameter();
			case ProcessPackage.TASK__PAGE_FLOW_TRANSITIONS:
				return getPageFlowTransitions();
			case ProcessPackage.TASK__FORM:
				return getForm();
			case ProcessPackage.TASK__ENTRY_REDIRECTION_URL:
				return getEntryRedirectionURL();
			case ProcessPackage.TASK__CONFIRMATION_MESSAGE:
				return getConfirmationMessage();
			case ProcessPackage.TASK__ENTRY_REDIRECTION_ACTIONS:
				return getEntryRedirectionActions();
			case ProcessPackage.TASK__FORM_MAPPING:
				return getFormMapping();
			case ProcessPackage.TASK__HTML_TEMPLATE:
				return getHtmlTemplate();
			case ProcessPackage.TASK__RESOURCE_JARS:
				return getResourceJars();
			case ProcessPackage.TASK__RESOURCE_VALIDATORS:
				return getResourceValidators();
			case ProcessPackage.TASK__RESOURCE_FILES:
				return getResourceFiles();
			case ProcessPackage.TASK__RESOURCE_FOLDERS:
				return getResourceFolders();
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
			case ProcessPackage.TASK__BY_PASS_FORMS_GENERATION:
				setByPassFormsGeneration((Boolean)newValue);
				return;
			case ProcessPackage.TASK__CONFIRMATION_TEMPLATE:
				setConfirmationTemplate((AssociatedFile)newValue);
				return;
			case ProcessPackage.TASK__TRANSIENT_DATA:
				getTransientData().clear();
				getTransientData().addAll((Collection<? extends Data>)newValue);
				return;
			case ProcessPackage.TASK__PAGE_FLOW_CONNECTORS:
				getPageFlowConnectors().clear();
				getPageFlowConnectors().addAll((Collection<? extends Connector>)newValue);
				return;
			case ProcessPackage.TASK__ENTRY_PAGE_FLOW_TYPE:
				setEntryPageFlowType((EntryPageFlowType)newValue);
				return;
			case ProcessPackage.TASK__TRANSMIT_URL_AS_PARAMETER:
				setTransmitURLAsParameter((Boolean)newValue);
				return;
			case ProcessPackage.TASK__PAGE_FLOW_TRANSITIONS:
				getPageFlowTransitions().clear();
				getPageFlowTransitions().addAll((Collection<? extends PageFlowTransition>)newValue);
				return;
			case ProcessPackage.TASK__FORM:
				getForm().clear();
				getForm().addAll((Collection<? extends Form>)newValue);
				return;
			case ProcessPackage.TASK__ENTRY_REDIRECTION_URL:
				setEntryRedirectionURL((Expression)newValue);
				return;
			case ProcessPackage.TASK__CONFIRMATION_MESSAGE:
				setConfirmationMessage((Expression)newValue);
				return;
			case ProcessPackage.TASK__ENTRY_REDIRECTION_ACTIONS:
				getEntryRedirectionActions().clear();
				getEntryRedirectionActions().addAll((Collection<? extends Operation>)newValue);
				return;
			case ProcessPackage.TASK__FORM_MAPPING:
				setFormMapping((FormMapping)newValue);
				return;
			case ProcessPackage.TASK__HTML_TEMPLATE:
				setHtmlTemplate((AssociatedFile)newValue);
				return;
			case ProcessPackage.TASK__RESOURCE_JARS:
				getResourceJars().clear();
				getResourceJars().addAll((Collection<? extends String>)newValue);
				return;
			case ProcessPackage.TASK__RESOURCE_VALIDATORS:
				getResourceValidators().clear();
				getResourceValidators().addAll((Collection<? extends String>)newValue);
				return;
			case ProcessPackage.TASK__RESOURCE_FILES:
				getResourceFiles().clear();
				getResourceFiles().addAll((Collection<? extends ResourceFile>)newValue);
				return;
			case ProcessPackage.TASK__RESOURCE_FOLDERS:
				getResourceFolders().clear();
				getResourceFolders().addAll((Collection<? extends ResourceFolder>)newValue);
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
			case ProcessPackage.TASK__BY_PASS_FORMS_GENERATION:
				setByPassFormsGeneration(BY_PASS_FORMS_GENERATION_EDEFAULT);
				return;
			case ProcessPackage.TASK__CONFIRMATION_TEMPLATE:
				setConfirmationTemplate((AssociatedFile)null);
				return;
			case ProcessPackage.TASK__TRANSIENT_DATA:
				getTransientData().clear();
				return;
			case ProcessPackage.TASK__PAGE_FLOW_CONNECTORS:
				getPageFlowConnectors().clear();
				return;
			case ProcessPackage.TASK__ENTRY_PAGE_FLOW_TYPE:
				setEntryPageFlowType(ENTRY_PAGE_FLOW_TYPE_EDEFAULT);
				return;
			case ProcessPackage.TASK__TRANSMIT_URL_AS_PARAMETER:
				setTransmitURLAsParameter(TRANSMIT_URL_AS_PARAMETER_EDEFAULT);
				return;
			case ProcessPackage.TASK__PAGE_FLOW_TRANSITIONS:
				getPageFlowTransitions().clear();
				return;
			case ProcessPackage.TASK__FORM:
				getForm().clear();
				return;
			case ProcessPackage.TASK__ENTRY_REDIRECTION_URL:
				setEntryRedirectionURL((Expression)null);
				return;
			case ProcessPackage.TASK__CONFIRMATION_MESSAGE:
				setConfirmationMessage((Expression)null);
				return;
			case ProcessPackage.TASK__ENTRY_REDIRECTION_ACTIONS:
				getEntryRedirectionActions().clear();
				return;
			case ProcessPackage.TASK__FORM_MAPPING:
				setFormMapping((FormMapping)null);
				return;
			case ProcessPackage.TASK__HTML_TEMPLATE:
				setHtmlTemplate((AssociatedFile)null);
				return;
			case ProcessPackage.TASK__RESOURCE_JARS:
				getResourceJars().clear();
				return;
			case ProcessPackage.TASK__RESOURCE_VALIDATORS:
				getResourceValidators().clear();
				return;
			case ProcessPackage.TASK__RESOURCE_FILES:
				getResourceFiles().clear();
				return;
			case ProcessPackage.TASK__RESOURCE_FOLDERS:
				getResourceFolders().clear();
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
			case ProcessPackage.TASK__BY_PASS_FORMS_GENERATION:
				return BY_PASS_FORMS_GENERATION_EDEFAULT == null ? byPassFormsGeneration != null : !BY_PASS_FORMS_GENERATION_EDEFAULT.equals(byPassFormsGeneration);
			case ProcessPackage.TASK__CONFIRMATION_TEMPLATE:
				return confirmationTemplate != null;
			case ProcessPackage.TASK__TRANSIENT_DATA:
				return transientData != null && !transientData.isEmpty();
			case ProcessPackage.TASK__PAGE_FLOW_CONNECTORS:
				return pageFlowConnectors != null && !pageFlowConnectors.isEmpty();
			case ProcessPackage.TASK__ENTRY_PAGE_FLOW_TYPE:
				return entryPageFlowType != ENTRY_PAGE_FLOW_TYPE_EDEFAULT;
			case ProcessPackage.TASK__TRANSMIT_URL_AS_PARAMETER:
				return transmitURLAsParameter != TRANSMIT_URL_AS_PARAMETER_EDEFAULT;
			case ProcessPackage.TASK__PAGE_FLOW_TRANSITIONS:
				return pageFlowTransitions != null && !pageFlowTransitions.isEmpty();
			case ProcessPackage.TASK__FORM:
				return form != null && !form.isEmpty();
			case ProcessPackage.TASK__ENTRY_REDIRECTION_URL:
				return entryRedirectionURL != null;
			case ProcessPackage.TASK__CONFIRMATION_MESSAGE:
				return confirmationMessage != null;
			case ProcessPackage.TASK__ENTRY_REDIRECTION_ACTIONS:
				return entryRedirectionActions != null && !entryRedirectionActions.isEmpty();
			case ProcessPackage.TASK__FORM_MAPPING:
				return formMapping != null;
			case ProcessPackage.TASK__HTML_TEMPLATE:
				return htmlTemplate != null;
			case ProcessPackage.TASK__RESOURCE_JARS:
				return resourceJars != null && !resourceJars.isEmpty();
			case ProcessPackage.TASK__RESOURCE_VALIDATORS:
				return resourceValidators != null && !resourceValidators.isEmpty();
			case ProcessPackage.TASK__RESOURCE_FILES:
				return resourceFiles != null && !resourceFiles.isEmpty();
			case ProcessPackage.TASK__RESOURCE_FOLDERS:
				return resourceFolders != null && !resourceFolders.isEmpty();
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
		if (baseClass == PageFlow.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.TASK__BY_PASS_FORMS_GENERATION: return ProcessPackage.PAGE_FLOW__BY_PASS_FORMS_GENERATION;
				case ProcessPackage.TASK__CONFIRMATION_TEMPLATE: return ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE;
				case ProcessPackage.TASK__TRANSIENT_DATA: return ProcessPackage.PAGE_FLOW__TRANSIENT_DATA;
				case ProcessPackage.TASK__PAGE_FLOW_CONNECTORS: return ProcessPackage.PAGE_FLOW__PAGE_FLOW_CONNECTORS;
				case ProcessPackage.TASK__ENTRY_PAGE_FLOW_TYPE: return ProcessPackage.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE;
				case ProcessPackage.TASK__TRANSMIT_URL_AS_PARAMETER: return ProcessPackage.PAGE_FLOW__TRANSMIT_URL_AS_PARAMETER;
				case ProcessPackage.TASK__PAGE_FLOW_TRANSITIONS: return ProcessPackage.PAGE_FLOW__PAGE_FLOW_TRANSITIONS;
				case ProcessPackage.TASK__FORM: return ProcessPackage.PAGE_FLOW__FORM;
				case ProcessPackage.TASK__ENTRY_REDIRECTION_URL: return ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL;
				case ProcessPackage.TASK__CONFIRMATION_MESSAGE: return ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE;
				case ProcessPackage.TASK__ENTRY_REDIRECTION_ACTIONS: return ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS;
				case ProcessPackage.TASK__FORM_MAPPING: return ProcessPackage.PAGE_FLOW__FORM_MAPPING;
				default: return -1;
			}
		}
		if (baseClass == ResourceContainer.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.TASK__HTML_TEMPLATE: return ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE;
				case ProcessPackage.TASK__RESOURCE_JARS: return ProcessPackage.RESOURCE_CONTAINER__RESOURCE_JARS;
				case ProcessPackage.TASK__RESOURCE_VALIDATORS: return ProcessPackage.RESOURCE_CONTAINER__RESOURCE_VALIDATORS;
				case ProcessPackage.TASK__RESOURCE_FILES: return ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FILES;
				case ProcessPackage.TASK__RESOURCE_FOLDERS: return ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FOLDERS;
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
		if (baseClass == PageFlow.class) {
			switch (baseFeatureID) {
				case ProcessPackage.PAGE_FLOW__BY_PASS_FORMS_GENERATION: return ProcessPackage.TASK__BY_PASS_FORMS_GENERATION;
				case ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE: return ProcessPackage.TASK__CONFIRMATION_TEMPLATE;
				case ProcessPackage.PAGE_FLOW__TRANSIENT_DATA: return ProcessPackage.TASK__TRANSIENT_DATA;
				case ProcessPackage.PAGE_FLOW__PAGE_FLOW_CONNECTORS: return ProcessPackage.TASK__PAGE_FLOW_CONNECTORS;
				case ProcessPackage.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE: return ProcessPackage.TASK__ENTRY_PAGE_FLOW_TYPE;
				case ProcessPackage.PAGE_FLOW__TRANSMIT_URL_AS_PARAMETER: return ProcessPackage.TASK__TRANSMIT_URL_AS_PARAMETER;
				case ProcessPackage.PAGE_FLOW__PAGE_FLOW_TRANSITIONS: return ProcessPackage.TASK__PAGE_FLOW_TRANSITIONS;
				case ProcessPackage.PAGE_FLOW__FORM: return ProcessPackage.TASK__FORM;
				case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL: return ProcessPackage.TASK__ENTRY_REDIRECTION_URL;
				case ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE: return ProcessPackage.TASK__CONFIRMATION_MESSAGE;
				case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS: return ProcessPackage.TASK__ENTRY_REDIRECTION_ACTIONS;
				case ProcessPackage.PAGE_FLOW__FORM_MAPPING: return ProcessPackage.TASK__FORM_MAPPING;
				default: return -1;
			}
		}
		if (baseClass == ResourceContainer.class) {
			switch (baseFeatureID) {
				case ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE: return ProcessPackage.TASK__HTML_TEMPLATE;
				case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_JARS: return ProcessPackage.TASK__RESOURCE_JARS;
				case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_VALIDATORS: return ProcessPackage.TASK__RESOURCE_VALIDATORS;
				case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FILES: return ProcessPackage.TASK__RESOURCE_FILES;
				case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FOLDERS: return ProcessPackage.TASK__RESOURCE_FOLDERS;
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (byPassFormsGeneration: "); //$NON-NLS-1$
		result.append(byPassFormsGeneration);
		result.append(", entryPageFlowType: "); //$NON-NLS-1$
		result.append(entryPageFlowType);
		result.append(", transmitURLAsParameter: "); //$NON-NLS-1$
		result.append(transmitURLAsParameter);
		result.append(", resourceJars: "); //$NON-NLS-1$
		result.append(resourceJars);
		result.append(", resourceValidators: "); //$NON-NLS-1$
		result.append(resourceValidators);
		result.append(", overrideActorsOfTheLane: "); //$NON-NLS-1$
		result.append(overrideActorsOfTheLane);
		result.append(", priority: "); //$NON-NLS-1$
		result.append(priority);
		result.append(')');
		return result.toString();
	}

} //TaskImpl
