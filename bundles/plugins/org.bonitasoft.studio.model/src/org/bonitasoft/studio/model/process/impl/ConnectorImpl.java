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

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;

import org.bonitasoft.studio.model.expression.Operation;

import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl#getTextAnnotationAttachment <em>Text Annotation Attachment</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl#getDefinitionId <em>Definition Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl#isIgnoreErrors <em>Ignore Errors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl#isThrowErrorEvent <em>Throw Error Event</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl#getNamedError <em>Named Error</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl#getDefinitionVersion <em>Definition Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl#getConfiguration <em>Configuration</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectorImpl#getOutputs <em>Outputs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectorImpl extends EObjectImpl implements Connector {
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
     * The default value of the '{@link #getDefinitionId() <em>Definition Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefinitionId()
     * @generated
     * @ordered
     */
	protected static final String DEFINITION_ID_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getDefinitionId() <em>Definition Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefinitionId()
     * @generated
     * @ordered
     */
	protected String definitionId = DEFINITION_ID_EDEFAULT;

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
     * The default value of the '{@link #isIgnoreErrors() <em>Ignore Errors</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isIgnoreErrors()
     * @generated
     * @ordered
     */
	protected static final boolean IGNORE_ERRORS_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isIgnoreErrors() <em>Ignore Errors</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isIgnoreErrors()
     * @generated
     * @ordered
     */
	protected boolean ignoreErrors = IGNORE_ERRORS_EDEFAULT;

	/**
     * The default value of the '{@link #isThrowErrorEvent() <em>Throw Error Event</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isThrowErrorEvent()
     * @generated
     * @ordered
     */
	protected static final boolean THROW_ERROR_EVENT_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isThrowErrorEvent() <em>Throw Error Event</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isThrowErrorEvent()
     * @generated
     * @ordered
     */
	protected boolean throwErrorEvent = THROW_ERROR_EVENT_EDEFAULT;

	/**
     * The default value of the '{@link #getNamedError() <em>Named Error</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getNamedError()
     * @generated
     * @ordered
     */
	protected static final String NAMED_ERROR_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getNamedError() <em>Named Error</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getNamedError()
     * @generated
     * @ordered
     */
	protected String namedError = NAMED_ERROR_EDEFAULT;

	/**
     * The default value of the '{@link #getDefinitionVersion() <em>Definition Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefinitionVersion()
     * @generated
     * @ordered
     */
	protected static final String DEFINITION_VERSION_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getDefinitionVersion() <em>Definition Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefinitionVersion()
     * @generated
     * @ordered
     */
	protected String definitionVersion = DEFINITION_VERSION_EDEFAULT;

	/**
     * The cached value of the '{@link #getConfiguration() <em>Configuration</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConfiguration()
     * @generated
     * @ordered
     */
	protected ConnectorConfiguration configuration;

	/**
     * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOutputs()
     * @generated
     * @ordered
     */
	protected EList<Operation> outputs;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ConnectorImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.CONNECTOR;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTOR__DOCUMENTATION, oldDocumentation, documentation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTOR__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<TextAnnotationAttachment> getTextAnnotationAttachment() {
        if (textAnnotationAttachment == null) {
            textAnnotationAttachment = new EObjectContainmentWithInverseEList<TextAnnotationAttachment>(TextAnnotationAttachment.class, this, ProcessPackage.CONNECTOR__TEXT_ANNOTATION_ATTACHMENT, ProcessPackage.TEXT_ANNOTATION_ATTACHMENT__TARGET);
        }
        return textAnnotationAttachment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getDefinitionId() {
        return definitionId;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDefinitionId(String newDefinitionId) {
        String oldDefinitionId = definitionId;
        definitionId = newDefinitionId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTOR__DEFINITION_ID, oldDefinitionId, definitionId));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTOR__EVENT, oldEvent, event));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isIgnoreErrors() {
        return ignoreErrors;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setIgnoreErrors(boolean newIgnoreErrors) {
        boolean oldIgnoreErrors = ignoreErrors;
        ignoreErrors = newIgnoreErrors;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTOR__IGNORE_ERRORS, oldIgnoreErrors, ignoreErrors));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isThrowErrorEvent() {
        return throwErrorEvent;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setThrowErrorEvent(boolean newThrowErrorEvent) {
        boolean oldThrowErrorEvent = throwErrorEvent;
        throwErrorEvent = newThrowErrorEvent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTOR__THROW_ERROR_EVENT, oldThrowErrorEvent, throwErrorEvent));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getNamedError() {
        return namedError;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setNamedError(String newNamedError) {
        String oldNamedError = namedError;
        namedError = newNamedError;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTOR__NAMED_ERROR, oldNamedError, namedError));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getDefinitionVersion() {
        return definitionVersion;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDefinitionVersion(String newDefinitionVersion) {
        String oldDefinitionVersion = definitionVersion;
        definitionVersion = newDefinitionVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTOR__DEFINITION_VERSION, oldDefinitionVersion, definitionVersion));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ConnectorConfiguration getConfiguration() {
        return configuration;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetConfiguration(ConnectorConfiguration newConfiguration, NotificationChain msgs) {
        ConnectorConfiguration oldConfiguration = configuration;
        configuration = newConfiguration;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTOR__CONFIGURATION, oldConfiguration, newConfiguration);
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
	public void setConfiguration(ConnectorConfiguration newConfiguration) {
        if (newConfiguration != configuration) {
            NotificationChain msgs = null;
            if (configuration != null)
                msgs = ((InternalEObject)configuration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CONNECTOR__CONFIGURATION, null, msgs);
            if (newConfiguration != null)
                msgs = ((InternalEObject)newConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CONNECTOR__CONFIGURATION, null, msgs);
            msgs = basicSetConfiguration(newConfiguration, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTOR__CONFIGURATION, newConfiguration, newConfiguration));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Operation> getOutputs() {
        if (outputs == null) {
            outputs = new EObjectContainmentEList<Operation>(Operation.class, this, ProcessPackage.CONNECTOR__OUTPUTS);
        }
        return outputs;
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
            case ProcessPackage.CONNECTOR__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getTextAnnotationAttachment()).basicAdd(otherEnd, msgs);
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
            case ProcessPackage.CONNECTOR__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<?>)getTextAnnotationAttachment()).basicRemove(otherEnd, msgs);
            case ProcessPackage.CONNECTOR__CONFIGURATION:
                return basicSetConfiguration(null, msgs);
            case ProcessPackage.CONNECTOR__OUTPUTS:
                return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
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
            case ProcessPackage.CONNECTOR__DOCUMENTATION:
                return getDocumentation();
            case ProcessPackage.CONNECTOR__NAME:
                return getName();
            case ProcessPackage.CONNECTOR__TEXT_ANNOTATION_ATTACHMENT:
                return getTextAnnotationAttachment();
            case ProcessPackage.CONNECTOR__DEFINITION_ID:
                return getDefinitionId();
            case ProcessPackage.CONNECTOR__EVENT:
                return getEvent();
            case ProcessPackage.CONNECTOR__IGNORE_ERRORS:
                return isIgnoreErrors();
            case ProcessPackage.CONNECTOR__THROW_ERROR_EVENT:
                return isThrowErrorEvent();
            case ProcessPackage.CONNECTOR__NAMED_ERROR:
                return getNamedError();
            case ProcessPackage.CONNECTOR__DEFINITION_VERSION:
                return getDefinitionVersion();
            case ProcessPackage.CONNECTOR__CONFIGURATION:
                return getConfiguration();
            case ProcessPackage.CONNECTOR__OUTPUTS:
                return getOutputs();
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
            case ProcessPackage.CONNECTOR__DOCUMENTATION:
                setDocumentation((String)newValue);
                return;
            case ProcessPackage.CONNECTOR__NAME:
                setName((String)newValue);
                return;
            case ProcessPackage.CONNECTOR__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                getTextAnnotationAttachment().addAll((Collection<? extends TextAnnotationAttachment>)newValue);
                return;
            case ProcessPackage.CONNECTOR__DEFINITION_ID:
                setDefinitionId((String)newValue);
                return;
            case ProcessPackage.CONNECTOR__EVENT:
                setEvent((String)newValue);
                return;
            case ProcessPackage.CONNECTOR__IGNORE_ERRORS:
                setIgnoreErrors((Boolean)newValue);
                return;
            case ProcessPackage.CONNECTOR__THROW_ERROR_EVENT:
                setThrowErrorEvent((Boolean)newValue);
                return;
            case ProcessPackage.CONNECTOR__NAMED_ERROR:
                setNamedError((String)newValue);
                return;
            case ProcessPackage.CONNECTOR__DEFINITION_VERSION:
                setDefinitionVersion((String)newValue);
                return;
            case ProcessPackage.CONNECTOR__CONFIGURATION:
                setConfiguration((ConnectorConfiguration)newValue);
                return;
            case ProcessPackage.CONNECTOR__OUTPUTS:
                getOutputs().clear();
                getOutputs().addAll((Collection<? extends Operation>)newValue);
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
            case ProcessPackage.CONNECTOR__DOCUMENTATION:
                setDocumentation(DOCUMENTATION_EDEFAULT);
                return;
            case ProcessPackage.CONNECTOR__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ProcessPackage.CONNECTOR__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                return;
            case ProcessPackage.CONNECTOR__DEFINITION_ID:
                setDefinitionId(DEFINITION_ID_EDEFAULT);
                return;
            case ProcessPackage.CONNECTOR__EVENT:
                setEvent(EVENT_EDEFAULT);
                return;
            case ProcessPackage.CONNECTOR__IGNORE_ERRORS:
                setIgnoreErrors(IGNORE_ERRORS_EDEFAULT);
                return;
            case ProcessPackage.CONNECTOR__THROW_ERROR_EVENT:
                setThrowErrorEvent(THROW_ERROR_EVENT_EDEFAULT);
                return;
            case ProcessPackage.CONNECTOR__NAMED_ERROR:
                setNamedError(NAMED_ERROR_EDEFAULT);
                return;
            case ProcessPackage.CONNECTOR__DEFINITION_VERSION:
                setDefinitionVersion(DEFINITION_VERSION_EDEFAULT);
                return;
            case ProcessPackage.CONNECTOR__CONFIGURATION:
                setConfiguration((ConnectorConfiguration)null);
                return;
            case ProcessPackage.CONNECTOR__OUTPUTS:
                getOutputs().clear();
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
            case ProcessPackage.CONNECTOR__DOCUMENTATION:
                return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
            case ProcessPackage.CONNECTOR__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ProcessPackage.CONNECTOR__TEXT_ANNOTATION_ATTACHMENT:
                return textAnnotationAttachment != null && !textAnnotationAttachment.isEmpty();
            case ProcessPackage.CONNECTOR__DEFINITION_ID:
                return DEFINITION_ID_EDEFAULT == null ? definitionId != null : !DEFINITION_ID_EDEFAULT.equals(definitionId);
            case ProcessPackage.CONNECTOR__EVENT:
                return EVENT_EDEFAULT == null ? event != null : !EVENT_EDEFAULT.equals(event);
            case ProcessPackage.CONNECTOR__IGNORE_ERRORS:
                return ignoreErrors != IGNORE_ERRORS_EDEFAULT;
            case ProcessPackage.CONNECTOR__THROW_ERROR_EVENT:
                return throwErrorEvent != THROW_ERROR_EVENT_EDEFAULT;
            case ProcessPackage.CONNECTOR__NAMED_ERROR:
                return NAMED_ERROR_EDEFAULT == null ? namedError != null : !NAMED_ERROR_EDEFAULT.equals(namedError);
            case ProcessPackage.CONNECTOR__DEFINITION_VERSION:
                return DEFINITION_VERSION_EDEFAULT == null ? definitionVersion != null : !DEFINITION_VERSION_EDEFAULT.equals(definitionVersion);
            case ProcessPackage.CONNECTOR__CONFIGURATION:
                return configuration != null;
            case ProcessPackage.CONNECTOR__OUTPUTS:
                return outputs != null && !outputs.isEmpty();
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
        result.append(", definitionId: "); //$NON-NLS-1$
        result.append(definitionId);
        result.append(", event: "); //$NON-NLS-1$
        result.append(event);
        result.append(", ignoreErrors: "); //$NON-NLS-1$
        result.append(ignoreErrors);
        result.append(", throwErrorEvent: "); //$NON-NLS-1$
        result.append(throwErrorEvent);
        result.append(", namedError: "); //$NON-NLS-1$
        result.append(namedError);
        result.append(", definitionVersion: "); //$NON-NLS-1$
        result.append(definitionVersion);
        result.append(')');
        return result.toString();
    }

} //ConnectorImpl
