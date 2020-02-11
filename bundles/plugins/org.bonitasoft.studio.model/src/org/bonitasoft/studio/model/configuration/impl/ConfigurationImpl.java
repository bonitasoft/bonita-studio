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
package org.bonitasoft.studio.model.configuration.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.actormapping.ActorMappingsType;

import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.FragmentContainer;

import org.bonitasoft.studio.model.configuration.Resource;
import org.bonitasoft.studio.model.parameter.Parameter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getActorMappings <em>Actor Mappings</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getAnonymousUserName <em>Anonymous User Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getAnonymousPassword <em>Anonymous Password</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getDefinitionMappings <em>Definition Mappings</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getProcessDependencies <em>Process Dependencies</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getApplicationDependencies <em>Application Dependencies</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getUsername <em>Username</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl#getAdditionalResources <em>Additional Resources</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfigurationImpl extends EObjectImpl implements Configuration {
	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = null;

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
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
     * The cached value of the '{@link #getActorMappings() <em>Actor Mappings</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getActorMappings()
     * @generated
     * @ordered
     */
	protected ActorMappingsType actorMappings;

	/**
     * The default value of the '{@link #getAnonymousUserName() <em>Anonymous User Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAnonymousUserName()
     * @generated
     * @ordered
     */
	protected static final String ANONYMOUS_USER_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getAnonymousUserName() <em>Anonymous User Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAnonymousUserName()
     * @generated
     * @ordered
     */
	protected String anonymousUserName = ANONYMOUS_USER_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getAnonymousPassword() <em>Anonymous Password</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAnonymousPassword()
     * @generated
     * @ordered
     */
	protected static final String ANONYMOUS_PASSWORD_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getAnonymousPassword() <em>Anonymous Password</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAnonymousPassword()
     * @generated
     * @ordered
     */
	protected String anonymousPassword = ANONYMOUS_PASSWORD_EDEFAULT;

	/**
     * The cached value of the '{@link #getDefinitionMappings() <em>Definition Mappings</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefinitionMappings()
     * @generated
     * @ordered
     */
	protected EList<DefinitionMapping> definitionMappings;

	/**
     * The cached value of the '{@link #getProcessDependencies() <em>Process Dependencies</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getProcessDependencies()
     * @generated
     * @ordered
     */
	protected EList<FragmentContainer> processDependencies;

	/**
     * The cached value of the '{@link #getApplicationDependencies() <em>Application Dependencies</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getApplicationDependencies()
     * @generated
     * @ordered
     */
	protected EList<FragmentContainer> applicationDependencies;

	/**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getParameters()
     * @generated
     * @ordered
     */
	protected EList<Parameter> parameters;

	/**
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
	protected static final String VERSION_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
	protected String version = VERSION_EDEFAULT;

	/**
     * The default value of the '{@link #getUsername() <em>Username</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUsername()
     * @generated
     * @ordered
     */
	protected static final String USERNAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getUsername() <em>Username</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUsername()
     * @generated
     * @ordered
     */
	protected String username = USERNAME_EDEFAULT;

	/**
     * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPassword()
     * @generated
     * @ordered
     */
	protected static final String PASSWORD_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPassword()
     * @generated
     * @ordered
     */
	protected String password = PASSWORD_EDEFAULT;

	/**
     * The cached value of the '{@link #getAdditionalResources() <em>Additional Resources</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAdditionalResources()
     * @generated
     * @ordered
     */
    protected EList<Resource> additionalResources;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ConfigurationImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ConfigurationPackage.Literals.CONFIGURATION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.CONFIGURATION__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getDescription() {
        return description;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.CONFIGURATION__DESCRIPTION, oldDescription, description));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ActorMappingsType getActorMappings() {
        return actorMappings;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetActorMappings(ActorMappingsType newActorMappings, NotificationChain msgs) {
        ActorMappingsType oldActorMappings = actorMappings;
        actorMappings = newActorMappings;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConfigurationPackage.CONFIGURATION__ACTOR_MAPPINGS, oldActorMappings, newActorMappings);
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
	public void setActorMappings(ActorMappingsType newActorMappings) {
        if (newActorMappings != actorMappings) {
            NotificationChain msgs = null;
            if (actorMappings != null)
                msgs = ((InternalEObject)actorMappings).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConfigurationPackage.CONFIGURATION__ACTOR_MAPPINGS, null, msgs);
            if (newActorMappings != null)
                msgs = ((InternalEObject)newActorMappings).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConfigurationPackage.CONFIGURATION__ACTOR_MAPPINGS, null, msgs);
            msgs = basicSetActorMappings(newActorMappings, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.CONFIGURATION__ACTOR_MAPPINGS, newActorMappings, newActorMappings));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getAnonymousUserName() {
        return anonymousUserName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setAnonymousUserName(String newAnonymousUserName) {
        String oldAnonymousUserName = anonymousUserName;
        anonymousUserName = newAnonymousUserName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.CONFIGURATION__ANONYMOUS_USER_NAME, oldAnonymousUserName, anonymousUserName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getAnonymousPassword() {
        return anonymousPassword;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setAnonymousPassword(String newAnonymousPassword) {
        String oldAnonymousPassword = anonymousPassword;
        anonymousPassword = newAnonymousPassword;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.CONFIGURATION__ANONYMOUS_PASSWORD, oldAnonymousPassword, anonymousPassword));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<DefinitionMapping> getDefinitionMappings() {
        if (definitionMappings == null) {
            definitionMappings = new EObjectContainmentEList<DefinitionMapping>(DefinitionMapping.class, this, ConfigurationPackage.CONFIGURATION__DEFINITION_MAPPINGS);
        }
        return definitionMappings;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<FragmentContainer> getProcessDependencies() {
        if (processDependencies == null) {
            processDependencies = new EObjectContainmentEList<FragmentContainer>(FragmentContainer.class, this, ConfigurationPackage.CONFIGURATION__PROCESS_DEPENDENCIES);
        }
        return processDependencies;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<FragmentContainer> getApplicationDependencies() {
        if (applicationDependencies == null) {
            applicationDependencies = new EObjectContainmentEList<FragmentContainer>(FragmentContainer.class, this, ConfigurationPackage.CONFIGURATION__APPLICATION_DEPENDENCIES);
        }
        return applicationDependencies;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Parameter> getParameters() {
        if (parameters == null) {
            parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, ConfigurationPackage.CONFIGURATION__PARAMETERS);
        }
        return parameters;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getVersion() {
        return version;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.CONFIGURATION__VERSION, oldVersion, version));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getUsername() {
        return username;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setUsername(String newUsername) {
        String oldUsername = username;
        username = newUsername;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.CONFIGURATION__USERNAME, oldUsername, username));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getPassword() {
        return password;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setPassword(String newPassword) {
        String oldPassword = password;
        password = newPassword;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.CONFIGURATION__PASSWORD, oldPassword, password));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<Resource> getAdditionalResources() {
        if (additionalResources == null) {
            additionalResources = new EObjectContainmentEList<Resource>(Resource.class, this, ConfigurationPackage.CONFIGURATION__ADDITIONAL_RESOURCES);
        }
        return additionalResources;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConfigurationPackage.CONFIGURATION__ACTOR_MAPPINGS:
                return basicSetActorMappings(null, msgs);
            case ConfigurationPackage.CONFIGURATION__DEFINITION_MAPPINGS:
                return ((InternalEList<?>)getDefinitionMappings()).basicRemove(otherEnd, msgs);
            case ConfigurationPackage.CONFIGURATION__PROCESS_DEPENDENCIES:
                return ((InternalEList<?>)getProcessDependencies()).basicRemove(otherEnd, msgs);
            case ConfigurationPackage.CONFIGURATION__APPLICATION_DEPENDENCIES:
                return ((InternalEList<?>)getApplicationDependencies()).basicRemove(otherEnd, msgs);
            case ConfigurationPackage.CONFIGURATION__PARAMETERS:
                return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
            case ConfigurationPackage.CONFIGURATION__ADDITIONAL_RESOURCES:
                return ((InternalEList<?>)getAdditionalResources()).basicRemove(otherEnd, msgs);
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
            case ConfigurationPackage.CONFIGURATION__NAME:
                return getName();
            case ConfigurationPackage.CONFIGURATION__DESCRIPTION:
                return getDescription();
            case ConfigurationPackage.CONFIGURATION__ACTOR_MAPPINGS:
                return getActorMappings();
            case ConfigurationPackage.CONFIGURATION__ANONYMOUS_USER_NAME:
                return getAnonymousUserName();
            case ConfigurationPackage.CONFIGURATION__ANONYMOUS_PASSWORD:
                return getAnonymousPassword();
            case ConfigurationPackage.CONFIGURATION__DEFINITION_MAPPINGS:
                return getDefinitionMappings();
            case ConfigurationPackage.CONFIGURATION__PROCESS_DEPENDENCIES:
                return getProcessDependencies();
            case ConfigurationPackage.CONFIGURATION__APPLICATION_DEPENDENCIES:
                return getApplicationDependencies();
            case ConfigurationPackage.CONFIGURATION__PARAMETERS:
                return getParameters();
            case ConfigurationPackage.CONFIGURATION__VERSION:
                return getVersion();
            case ConfigurationPackage.CONFIGURATION__USERNAME:
                return getUsername();
            case ConfigurationPackage.CONFIGURATION__PASSWORD:
                return getPassword();
            case ConfigurationPackage.CONFIGURATION__ADDITIONAL_RESOURCES:
                return getAdditionalResources();
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
            case ConfigurationPackage.CONFIGURATION__NAME:
                setName((String)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__ACTOR_MAPPINGS:
                setActorMappings((ActorMappingsType)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__ANONYMOUS_USER_NAME:
                setAnonymousUserName((String)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__ANONYMOUS_PASSWORD:
                setAnonymousPassword((String)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__DEFINITION_MAPPINGS:
                getDefinitionMappings().clear();
                getDefinitionMappings().addAll((Collection<? extends DefinitionMapping>)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__PROCESS_DEPENDENCIES:
                getProcessDependencies().clear();
                getProcessDependencies().addAll((Collection<? extends FragmentContainer>)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__APPLICATION_DEPENDENCIES:
                getApplicationDependencies().clear();
                getApplicationDependencies().addAll((Collection<? extends FragmentContainer>)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__PARAMETERS:
                getParameters().clear();
                getParameters().addAll((Collection<? extends Parameter>)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__VERSION:
                setVersion((String)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__USERNAME:
                setUsername((String)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__PASSWORD:
                setPassword((String)newValue);
                return;
            case ConfigurationPackage.CONFIGURATION__ADDITIONAL_RESOURCES:
                getAdditionalResources().clear();
                getAdditionalResources().addAll((Collection<? extends Resource>)newValue);
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
            case ConfigurationPackage.CONFIGURATION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ConfigurationPackage.CONFIGURATION__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ConfigurationPackage.CONFIGURATION__ACTOR_MAPPINGS:
                setActorMappings((ActorMappingsType)null);
                return;
            case ConfigurationPackage.CONFIGURATION__ANONYMOUS_USER_NAME:
                setAnonymousUserName(ANONYMOUS_USER_NAME_EDEFAULT);
                return;
            case ConfigurationPackage.CONFIGURATION__ANONYMOUS_PASSWORD:
                setAnonymousPassword(ANONYMOUS_PASSWORD_EDEFAULT);
                return;
            case ConfigurationPackage.CONFIGURATION__DEFINITION_MAPPINGS:
                getDefinitionMappings().clear();
                return;
            case ConfigurationPackage.CONFIGURATION__PROCESS_DEPENDENCIES:
                getProcessDependencies().clear();
                return;
            case ConfigurationPackage.CONFIGURATION__APPLICATION_DEPENDENCIES:
                getApplicationDependencies().clear();
                return;
            case ConfigurationPackage.CONFIGURATION__PARAMETERS:
                getParameters().clear();
                return;
            case ConfigurationPackage.CONFIGURATION__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case ConfigurationPackage.CONFIGURATION__USERNAME:
                setUsername(USERNAME_EDEFAULT);
                return;
            case ConfigurationPackage.CONFIGURATION__PASSWORD:
                setPassword(PASSWORD_EDEFAULT);
                return;
            case ConfigurationPackage.CONFIGURATION__ADDITIONAL_RESOURCES:
                getAdditionalResources().clear();
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
            case ConfigurationPackage.CONFIGURATION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ConfigurationPackage.CONFIGURATION__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case ConfigurationPackage.CONFIGURATION__ACTOR_MAPPINGS:
                return actorMappings != null;
            case ConfigurationPackage.CONFIGURATION__ANONYMOUS_USER_NAME:
                return ANONYMOUS_USER_NAME_EDEFAULT == null ? anonymousUserName != null : !ANONYMOUS_USER_NAME_EDEFAULT.equals(anonymousUserName);
            case ConfigurationPackage.CONFIGURATION__ANONYMOUS_PASSWORD:
                return ANONYMOUS_PASSWORD_EDEFAULT == null ? anonymousPassword != null : !ANONYMOUS_PASSWORD_EDEFAULT.equals(anonymousPassword);
            case ConfigurationPackage.CONFIGURATION__DEFINITION_MAPPINGS:
                return definitionMappings != null && !definitionMappings.isEmpty();
            case ConfigurationPackage.CONFIGURATION__PROCESS_DEPENDENCIES:
                return processDependencies != null && !processDependencies.isEmpty();
            case ConfigurationPackage.CONFIGURATION__APPLICATION_DEPENDENCIES:
                return applicationDependencies != null && !applicationDependencies.isEmpty();
            case ConfigurationPackage.CONFIGURATION__PARAMETERS:
                return parameters != null && !parameters.isEmpty();
            case ConfigurationPackage.CONFIGURATION__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case ConfigurationPackage.CONFIGURATION__USERNAME:
                return USERNAME_EDEFAULT == null ? username != null : !USERNAME_EDEFAULT.equals(username);
            case ConfigurationPackage.CONFIGURATION__PASSWORD:
                return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
            case ConfigurationPackage.CONFIGURATION__ADDITIONAL_RESOURCES:
                return additionalResources != null && !additionalResources.isEmpty();
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", description: "); //$NON-NLS-1$
        result.append(description);
        result.append(", anonymousUserName: "); //$NON-NLS-1$
        result.append(anonymousUserName);
        result.append(", anonymousPassword: "); //$NON-NLS-1$
        result.append(anonymousPassword);
        result.append(", version: "); //$NON-NLS-1$
        result.append(version);
        result.append(", username: "); //$NON-NLS-1$
        result.append(username);
        result.append(", password: "); //$NON-NLS-1$
        result.append(password);
        result.append(')');
        return result.toString();
    }

} //ConfigurationImpl
