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
import java.util.Date;

import org.bonitasoft.studio.model.configuration.Configuration;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;

import org.bonitasoft.studio.model.form.CSSCustomizable;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.MandatoryFieldsCustomization;
import org.bonitasoft.studio.model.form.ViewForm;

import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;

import org.bonitasoft.studio.model.parameter.Parameter;

import org.bonitasoft.studio.model.process.AbstractPageFlow;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ConsultationPageFlowType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.EntryPageFlowType;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.PageFlowTransition;
import org.bonitasoft.studio.model.process.ProcessApplication;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.RecapFlow;
import org.bonitasoft.studio.model.process.ResourceContainer;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.bonitasoft.studio.model.process.ViewPageFlow;

import org.bonitasoft.studio.model.simulation.SimulationAbstractProcess;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.model.simulation.SimulationDataContainer;
import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getHtmlTemplate <em>Html Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getResourceJars <em>Resource Jars</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getResourceValidators <em>Resource
 * Validators</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getResourceFiles <em>Resource Files</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getResourceFolders <em>Resource Folders</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getHtmlAttributes <em>Html Attributes</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getMandatorySymbol <em>Mandatory Symbol</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getMandatoryLabel <em>Mandatory Label</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getErrorTemplate <em>Error Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getProcessTemplate <em>Process Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getPageTemplate <em>Page Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getConsultationTemplate <em>Consultation
 * Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getLogInPage <em>Log In Page</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getWelcomePage <em>Welcome Page</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getWelcomePageInternal <em>Welcome Page
 * Internal</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#isAutoLogin <em>Auto Login</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getAutoLoginId <em>Auto Login Id</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getHostPage <em>Host Page</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getBasedOnLookAndFeel <em>Based On Look And
 * Feel</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getData <em>Data</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getConnectors <em>Connectors</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getKpis <em>Kpis</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getRegExpToHideDefaultField <em>Reg Exp To Hide
 * Default Field</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#isUseRegExpToHideDefaultField <em>Use Reg Exp To
 * Hide Default Field</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getByPassFormsGeneration <em>By Pass Forms
 * Generation</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getConfirmationTemplate <em>Confirmation
 * Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getTransientData <em>Transient Data</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getPageFlowConnectors <em>Page Flow
 * Connectors</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getEntryPageFlowType <em>Entry Page Flow
 * Type</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#isTransmitURLAsParameter <em>Transmit URL As
 * Parameter</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getPageFlowTransitions <em>Page Flow
 * Transitions</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getForm <em>Form</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getEntryRedirectionURL <em>Entry Redirection
 * URL</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getConfirmationMessage <em>Confirmation
 * Message</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getEntryRedirectionActions <em>Entry Redirection
 * Actions</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getFormMapping <em>Form Mapping</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getSimulationData <em>Simulation Data</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getLoadProfileID <em>Load Profile ID</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getMaximumTime <em>Maximum Time</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getRecapPageFlowTransitions <em>Recap Page Flow
 * Transitions</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getRecapTransientData <em>Recap Transient
 * Data</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getRecapFlowConnectors <em>Recap Flow
 * Connectors</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getRecapPageFlowType <em>Recap Page Flow
 * Type</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getRecapForms <em>Recap Forms</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getRecapPageFlowRedirectionURL <em>Recap Page Flow
 * Redirection URL</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getOverviewFormMapping <em>Overview Form
 * Mapping</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getViewPageFlowTransitions <em>View Page Flow
 * Transitions</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getViewTransientData <em>View Transient
 * Data</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getViewPageFlowConnectors <em>View Page Flow
 * Connectors</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getViewPageFlowType <em>View Page Flow
 * Type</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getViewForm <em>View Form</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getViewPageFlowRedirectionURL <em>View Page Flow
 * Redirection URL</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getAuthor <em>Author</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getCreationDate <em>Creation Date</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getModificationDate <em>Modification
 * Date</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getDatatypes <em>Datatypes</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getConnections <em>Connections</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getCategories <em>Categories</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getActors <em>Actors</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getConfigurations <em>Configurations</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.AbstractProcessImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractProcessImpl extends ContainerImpl implements AbstractProcess {

    /**
     * The cached value of the '{@link #getHtmlTemplate() <em>Html Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getHtmlTemplate()
     * @generated
     * @ordered
     */
    protected AssociatedFile htmlTemplate;

    /**
     * The cached value of the '{@link #getResourceJars() <em>Resource Jars</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getResourceJars()
     * @generated
     * @ordered
     */
    protected EList<String> resourceJars;

    /**
     * The cached value of the '{@link #getResourceValidators() <em>Resource Validators</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getResourceValidators()
     * @generated
     * @ordered
     */
    protected EList<String> resourceValidators;

    /**
     * The cached value of the '{@link #getResourceFiles() <em>Resource Files</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getResourceFiles()
     * @generated
     * @ordered
     */
    protected EList<ResourceFile> resourceFiles;

    /**
     * The cached value of the '{@link #getResourceFolders() <em>Resource Folders</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getResourceFolders()
     * @generated
     * @ordered
     */
    protected EList<ResourceFolder> resourceFolders;

    /**
     * The cached value of the '{@link #getHtmlAttributes() <em>Html Attributes</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getHtmlAttributes()
     * @generated
     * @ordered
     */
    protected EMap<String, String> htmlAttributes;

    /**
     * The cached value of the '{@link #getMandatorySymbol() <em>Mandatory Symbol</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMandatorySymbol()
     * @generated
     * @ordered
     */
    protected Expression mandatorySymbol;

    /**
     * The cached value of the '{@link #getMandatoryLabel() <em>Mandatory Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMandatoryLabel()
     * @generated
     * @ordered
     */
    protected Expression mandatoryLabel;

    /**
     * The cached value of the '{@link #getErrorTemplate() <em>Error Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getErrorTemplate()
     * @generated
     * @ordered
     */
    protected AssociatedFile errorTemplate;

    /**
     * The cached value of the '{@link #getProcessTemplate() <em>Process Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getProcessTemplate()
     * @generated
     * @ordered
     */
    protected AssociatedFile processTemplate;

    /**
     * The cached value of the '{@link #getPageTemplate() <em>Page Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPageTemplate()
     * @generated
     * @ordered
     */
    protected AssociatedFile pageTemplate;

    /**
     * The cached value of the '{@link #getConsultationTemplate() <em>Consultation Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getConsultationTemplate()
     * @generated
     * @ordered
     */
    protected AssociatedFile consultationTemplate;

    /**
     * The cached value of the '{@link #getLogInPage() <em>Log In Page</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getLogInPage()
     * @generated
     * @ordered
     */
    protected AssociatedFile logInPage;

    /**
     * The cached value of the '{@link #getWelcomePage() <em>Welcome Page</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getWelcomePage()
     * @generated
     * @ordered
     */
    protected AssociatedFile welcomePage;

    /**
     * The default value of the '{@link #getWelcomePageInternal() <em>Welcome Page Internal</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getWelcomePageInternal()
     * @generated
     * @ordered
     */
    protected static final Boolean WELCOME_PAGE_INTERNAL_EDEFAULT = Boolean.TRUE;

    /**
     * The cached value of the '{@link #getWelcomePageInternal() <em>Welcome Page Internal</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getWelcomePageInternal()
     * @generated
     * @ordered
     */
    protected Boolean welcomePageInternal = WELCOME_PAGE_INTERNAL_EDEFAULT;

    /**
     * The default value of the '{@link #isAutoLogin() <em>Auto Login</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isAutoLogin()
     * @generated
     * @ordered
     */
    protected static final boolean AUTO_LOGIN_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isAutoLogin() <em>Auto Login</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isAutoLogin()
     * @generated
     * @ordered
     */
    protected boolean autoLogin = AUTO_LOGIN_EDEFAULT;

    /**
     * The default value of the '{@link #getAutoLoginId() <em>Auto Login Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getAutoLoginId()
     * @generated
     * @ordered
     */
    protected static final String AUTO_LOGIN_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAutoLoginId() <em>Auto Login Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getAutoLoginId()
     * @generated
     * @ordered
     */
    protected String autoLoginId = AUTO_LOGIN_ID_EDEFAULT;

    /**
     * The cached value of the '{@link #getHostPage() <em>Host Page</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getHostPage()
     * @generated
     * @ordered
     */
    protected AssociatedFile hostPage;

    /**
     * The default value of the '{@link #getBasedOnLookAndFeel() <em>Based On Look And Feel</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getBasedOnLookAndFeel()
     * @generated
     * @ordered
     */
    protected static final String BASED_ON_LOOK_AND_FEEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBasedOnLookAndFeel() <em>Based On Look And Feel</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getBasedOnLookAndFeel()
     * @generated
     * @ordered
     */
    protected String basedOnLookAndFeel = BASED_ON_LOOK_AND_FEEL_EDEFAULT;

    /**
     * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getData()
     * @generated
     * @ordered
     */
    protected EList<Data> data;

    /**
     * The cached value of the '{@link #getConnectors() <em>Connectors</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getConnectors()
     * @generated
     * @ordered
     */
    protected EList<Connector> connectors;

    /**
     * The cached value of the '{@link #getKpis() <em>Kpis</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getKpis()
     * @generated
     * @ordered
     */
    protected EList<AbstractKPIBinding> kpis;

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
     * The cached value of the '{@link #getSimulationData() <em>Simulation Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSimulationData()
     * @generated
     * @ordered
     */
    protected EList<SimulationData> simulationData;

    /**
     * The default value of the '{@link #getLoadProfileID() <em>Load Profile ID</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getLoadProfileID()
     * @generated
     * @ordered
     */
    protected static final String LOAD_PROFILE_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLoadProfileID() <em>Load Profile ID</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getLoadProfileID()
     * @generated
     * @ordered
     */
    protected String loadProfileID = LOAD_PROFILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getMaximumTime() <em>Maximum Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMaximumTime()
     * @generated
     * @ordered
     */
    protected static final long MAXIMUM_TIME_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getMaximumTime() <em>Maximum Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMaximumTime()
     * @generated
     * @ordered
     */
    protected long maximumTime = MAXIMUM_TIME_EDEFAULT;

    /**
     * The cached value of the '{@link #getRecapPageFlowTransitions() <em>Recap Page Flow Transitions</em>}' containment
     * reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRecapPageFlowTransitions()
     * @generated
     * @ordered
     */
    protected EList<PageFlowTransition> recapPageFlowTransitions;

    /**
     * The cached value of the '{@link #getRecapTransientData() <em>Recap Transient Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRecapTransientData()
     * @generated
     * @ordered
     */
    protected EList<Data> recapTransientData;

    /**
     * The cached value of the '{@link #getRecapFlowConnectors() <em>Recap Flow Connectors</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRecapFlowConnectors()
     * @generated
     * @ordered
     */
    protected EList<Connector> recapFlowConnectors;

    /**
     * The default value of the '{@link #getRecapPageFlowType() <em>Recap Page Flow Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRecapPageFlowType()
     * @generated
     * @ordered
     */
    protected static final ConsultationPageFlowType RECAP_PAGE_FLOW_TYPE_EDEFAULT = ConsultationPageFlowType.PAGEFLOW;

    /**
     * The cached value of the '{@link #getRecapPageFlowType() <em>Recap Page Flow Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRecapPageFlowType()
     * @generated
     * @ordered
     */
    protected ConsultationPageFlowType recapPageFlowType = RECAP_PAGE_FLOW_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getRecapForms() <em>Recap Forms</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRecapForms()
     * @generated
     * @ordered
     */
    protected EList<ViewForm> recapForms;

    /**
     * The cached value of the '{@link #getRecapPageFlowRedirectionURL() <em>Recap Page Flow Redirection URL</em>}'
     * containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRecapPageFlowRedirectionURL()
     * @generated
     * @ordered
     */
    protected Expression recapPageFlowRedirectionURL;

    /**
     * The cached value of the '{@link #getOverviewFormMapping() <em>Overview Form Mapping</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getOverviewFormMapping()
     * @generated
     * @ordered
     */
    protected FormMapping overviewFormMapping;

    /**
     * The cached value of the '{@link #getViewPageFlowTransitions() <em>View Page Flow Transitions</em>}' containment
     * reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewPageFlowTransitions()
     * @generated
     * @ordered
     */
    protected EList<PageFlowTransition> viewPageFlowTransitions;

    /**
     * The cached value of the '{@link #getViewTransientData() <em>View Transient Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewTransientData()
     * @generated
     * @ordered
     */
    protected EList<Data> viewTransientData;

    /**
     * The cached value of the '{@link #getViewPageFlowConnectors() <em>View Page Flow Connectors</em>}' containment
     * reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewPageFlowConnectors()
     * @generated
     * @ordered
     */
    protected EList<Connector> viewPageFlowConnectors;

    /**
     * The default value of the '{@link #getViewPageFlowType() <em>View Page Flow Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewPageFlowType()
     * @generated
     * @ordered
     */
    protected static final ConsultationPageFlowType VIEW_PAGE_FLOW_TYPE_EDEFAULT = ConsultationPageFlowType.PAGEFLOW;

    /**
     * The cached value of the '{@link #getViewPageFlowType() <em>View Page Flow Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewPageFlowType()
     * @generated
     * @ordered
     */
    protected ConsultationPageFlowType viewPageFlowType = VIEW_PAGE_FLOW_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getViewForm() <em>View Form</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewForm()
     * @generated
     * @ordered
     */
    protected EList<ViewForm> viewForm;

    /**
     * The cached value of the '{@link #getViewPageFlowRedirectionURL() <em>View Page Flow Redirection URL</em>}' containment
     * reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getViewPageFlowRedirectionURL()
     * @generated
     * @ordered
     */
    protected Expression viewPageFlowRedirectionURL;

    /**
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected static final String VERSION_EDEFAULT = "1.0"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected String version = VERSION_EDEFAULT;

    /**
     * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getAuthor()
     * @generated
     * @ordered
     */
    protected static final String AUTHOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getAuthor()
     * @generated
     * @ordered
     */
    protected String author = AUTHOR_EDEFAULT;

    /**
     * The default value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getCreationDate()
     * @generated
     * @ordered
     */
    protected static final Date CREATION_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getCreationDate()
     * @generated
     * @ordered
     */
    protected Date creationDate = CREATION_DATE_EDEFAULT;

    /**
     * The default value of the '{@link #getModificationDate() <em>Modification Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getModificationDate()
     * @generated
     * @ordered
     */
    protected static final Date MODIFICATION_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getModificationDate() <em>Modification Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getModificationDate()
     * @generated
     * @ordered
     */
    protected Date modificationDate = MODIFICATION_DATE_EDEFAULT;

    /**
     * The cached value of the '{@link #getDatatypes() <em>Datatypes</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDatatypes()
     * @generated
     * @ordered
     */
    protected EList<DataType> datatypes;

    /**
     * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getConnections()
     * @generated
     * @ordered
     */
    protected EList<Connection> connections;

    /**
     * The cached value of the '{@link #getCategories() <em>Categories</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getCategories()
     * @generated
     * @ordered
     */
    protected EList<String> categories;

    /**
     * The cached value of the '{@link #getActors() <em>Actors</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getActors()
     * @generated
     * @ordered
     */
    protected EList<Actor> actors;

    /**
     * The cached value of the '{@link #getConfigurations() <em>Configurations</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getConfigurations()
     * @generated
     * @ordered
     */
    protected EList<Configuration> configurations;

    /**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected EList<Parameter> parameters;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected AbstractProcessImpl() {
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
        return ProcessPackage.Literals.ABSTRACT_PROCESS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssociatedFile getHtmlTemplate() {
        return htmlTemplate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetHtmlTemplate(AssociatedFile newHtmlTemplate, NotificationChain msgs) {
        AssociatedFile oldHtmlTemplate = htmlTemplate;
        htmlTemplate = newHtmlTemplate;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE, oldHtmlTemplate, newHtmlTemplate);
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
    public void setHtmlTemplate(AssociatedFile newHtmlTemplate) {
        if (newHtmlTemplate != htmlTemplate) {
            NotificationChain msgs = null;
            if (htmlTemplate != null)
                msgs = ((InternalEObject) htmlTemplate).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE, null, msgs);
            if (newHtmlTemplate != null)
                msgs = ((InternalEObject) newHtmlTemplate).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE, null, msgs);
            msgs = basicSetHtmlTemplate(newHtmlTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE,
                    newHtmlTemplate, newHtmlTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<String> getResourceJars() {
        if (resourceJars == null) {
            resourceJars = new EDataTypeUniqueEList<String>(String.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__RESOURCE_JARS);
        }
        return resourceJars;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<String> getResourceValidators() {
        if (resourceValidators == null) {
            resourceValidators = new EDataTypeUniqueEList<String>(String.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__RESOURCE_VALIDATORS);
        }
        return resourceValidators;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ResourceFile> getResourceFiles() {
        if (resourceFiles == null) {
            resourceFiles = new EObjectContainmentEList<ResourceFile>(ResourceFile.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FILES);
        }
        return resourceFiles;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ResourceFolder> getResourceFolders() {
        if (resourceFolders == null) {
            resourceFolders = new EObjectContainmentEList<ResourceFolder>(ResourceFolder.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FOLDERS);
        }
        return resourceFolders;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EMap<String, String> getHtmlAttributes() {
        if (htmlAttributes == null) {
            htmlAttributes = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY,
                    EStringToStringMapEntryImpl.class, this, ProcessPackage.ABSTRACT_PROCESS__HTML_ATTRIBUTES);
        }
        return htmlAttributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getMandatorySymbol() {
        return mandatorySymbol;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetMandatorySymbol(Expression newMandatorySymbol, NotificationChain msgs) {
        Expression oldMandatorySymbol = mandatorySymbol;
        mandatorySymbol = newMandatorySymbol;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL, oldMandatorySymbol, newMandatorySymbol);
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
    public void setMandatorySymbol(Expression newMandatorySymbol) {
        if (newMandatorySymbol != mandatorySymbol) {
            NotificationChain msgs = null;
            if (mandatorySymbol != null)
                msgs = ((InternalEObject) mandatorySymbol).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL, null, msgs);
            if (newMandatorySymbol != null)
                msgs = ((InternalEObject) newMandatorySymbol).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL, null, msgs);
            msgs = basicSetMandatorySymbol(newMandatorySymbol, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL,
                    newMandatorySymbol, newMandatorySymbol));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getMandatoryLabel() {
        return mandatoryLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetMandatoryLabel(Expression newMandatoryLabel, NotificationChain msgs) {
        Expression oldMandatoryLabel = mandatoryLabel;
        mandatoryLabel = newMandatoryLabel;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL, oldMandatoryLabel, newMandatoryLabel);
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
    public void setMandatoryLabel(Expression newMandatoryLabel) {
        if (newMandatoryLabel != mandatoryLabel) {
            NotificationChain msgs = null;
            if (mandatoryLabel != null)
                msgs = ((InternalEObject) mandatoryLabel).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL, null, msgs);
            if (newMandatoryLabel != null)
                msgs = ((InternalEObject) newMandatoryLabel).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL, null, msgs);
            msgs = basicSetMandatoryLabel(newMandatoryLabel, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL,
                    newMandatoryLabel, newMandatoryLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssociatedFile getErrorTemplate() {
        return errorTemplate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetErrorTemplate(AssociatedFile newErrorTemplate, NotificationChain msgs) {
        AssociatedFile oldErrorTemplate = errorTemplate;
        errorTemplate = newErrorTemplate;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE, oldErrorTemplate, newErrorTemplate);
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
    public void setErrorTemplate(AssociatedFile newErrorTemplate) {
        if (newErrorTemplate != errorTemplate) {
            NotificationChain msgs = null;
            if (errorTemplate != null)
                msgs = ((InternalEObject) errorTemplate).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE, null, msgs);
            if (newErrorTemplate != null)
                msgs = ((InternalEObject) newErrorTemplate).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE, null, msgs);
            msgs = basicSetErrorTemplate(newErrorTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE,
                    newErrorTemplate, newErrorTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssociatedFile getProcessTemplate() {
        return processTemplate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetProcessTemplate(AssociatedFile newProcessTemplate, NotificationChain msgs) {
        AssociatedFile oldProcessTemplate = processTemplate;
        processTemplate = newProcessTemplate;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE, oldProcessTemplate, newProcessTemplate);
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
    public void setProcessTemplate(AssociatedFile newProcessTemplate) {
        if (newProcessTemplate != processTemplate) {
            NotificationChain msgs = null;
            if (processTemplate != null)
                msgs = ((InternalEObject) processTemplate).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE, null, msgs);
            if (newProcessTemplate != null)
                msgs = ((InternalEObject) newProcessTemplate).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE, null, msgs);
            msgs = basicSetProcessTemplate(newProcessTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE,
                    newProcessTemplate, newProcessTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssociatedFile getPageTemplate() {
        return pageTemplate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetPageTemplate(AssociatedFile newPageTemplate, NotificationChain msgs) {
        AssociatedFile oldPageTemplate = pageTemplate;
        pageTemplate = newPageTemplate;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE, oldPageTemplate, newPageTemplate);
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
    public void setPageTemplate(AssociatedFile newPageTemplate) {
        if (newPageTemplate != pageTemplate) {
            NotificationChain msgs = null;
            if (pageTemplate != null)
                msgs = ((InternalEObject) pageTemplate).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE, null, msgs);
            if (newPageTemplate != null)
                msgs = ((InternalEObject) newPageTemplate).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE, null, msgs);
            msgs = basicSetPageTemplate(newPageTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE,
                    newPageTemplate, newPageTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssociatedFile getConsultationTemplate() {
        return consultationTemplate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetConsultationTemplate(AssociatedFile newConsultationTemplate, NotificationChain msgs) {
        AssociatedFile oldConsultationTemplate = consultationTemplate;
        consultationTemplate = newConsultationTemplate;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE, oldConsultationTemplate,
                    newConsultationTemplate);
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
    public void setConsultationTemplate(AssociatedFile newConsultationTemplate) {
        if (newConsultationTemplate != consultationTemplate) {
            NotificationChain msgs = null;
            if (consultationTemplate != null)
                msgs = ((InternalEObject) consultationTemplate).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE, null, msgs);
            if (newConsultationTemplate != null)
                msgs = ((InternalEObject) newConsultationTemplate).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE, null, msgs);
            msgs = basicSetConsultationTemplate(newConsultationTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE,
                    newConsultationTemplate, newConsultationTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssociatedFile getLogInPage() {
        return logInPage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetLogInPage(AssociatedFile newLogInPage, NotificationChain msgs) {
        AssociatedFile oldLogInPage = logInPage;
        logInPage = newLogInPage;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE, oldLogInPage, newLogInPage);
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
    public void setLogInPage(AssociatedFile newLogInPage) {
        if (newLogInPage != logInPage) {
            NotificationChain msgs = null;
            if (logInPage != null)
                msgs = ((InternalEObject) logInPage).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE, null, msgs);
            if (newLogInPage != null)
                msgs = ((InternalEObject) newLogInPage).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE, null, msgs);
            msgs = basicSetLogInPage(newLogInPage, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE, newLogInPage,
                    newLogInPage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssociatedFile getWelcomePage() {
        return welcomePage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetWelcomePage(AssociatedFile newWelcomePage, NotificationChain msgs) {
        AssociatedFile oldWelcomePage = welcomePage;
        welcomePage = newWelcomePage;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE, oldWelcomePage, newWelcomePage);
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
    public void setWelcomePage(AssociatedFile newWelcomePage) {
        if (newWelcomePage != welcomePage) {
            NotificationChain msgs = null;
            if (welcomePage != null)
                msgs = ((InternalEObject) welcomePage).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE, null, msgs);
            if (newWelcomePage != null)
                msgs = ((InternalEObject) newWelcomePage).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE, null, msgs);
            msgs = basicSetWelcomePage(newWelcomePage, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE,
                    newWelcomePage, newWelcomePage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Boolean getWelcomePageInternal() {
        return welcomePageInternal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setWelcomePageInternal(Boolean newWelcomePageInternal) {
        Boolean oldWelcomePageInternal = welcomePageInternal;
        welcomePageInternal = newWelcomePageInternal;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE_INTERNAL,
                    oldWelcomePageInternal, welcomePageInternal));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isAutoLogin() {
        return autoLogin;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setAutoLogin(boolean newAutoLogin) {
        boolean oldAutoLogin = autoLogin;
        autoLogin = newAutoLogin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN, oldAutoLogin,
                    autoLogin));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getAutoLoginId() {
        return autoLoginId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setAutoLoginId(String newAutoLoginId) {
        String oldAutoLoginId = autoLoginId;
        autoLoginId = newAutoLoginId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN_ID,
                    oldAutoLoginId, autoLoginId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public AssociatedFile getHostPage() {
        return hostPage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetHostPage(AssociatedFile newHostPage, NotificationChain msgs) {
        AssociatedFile oldHostPage = hostPage;
        hostPage = newHostPage;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE, oldHostPage, newHostPage);
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
    public void setHostPage(AssociatedFile newHostPage) {
        if (newHostPage != hostPage) {
            NotificationChain msgs = null;
            if (hostPage != null)
                msgs = ((InternalEObject) hostPage).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE, null, msgs);
            if (newHostPage != null)
                msgs = ((InternalEObject) newHostPage).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE, null, msgs);
            msgs = basicSetHostPage(newHostPage, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE, newHostPage,
                    newHostPage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getBasedOnLookAndFeel() {
        return basedOnLookAndFeel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBasedOnLookAndFeel(String newBasedOnLookAndFeel) {
        String oldBasedOnLookAndFeel = basedOnLookAndFeel;
        basedOnLookAndFeel = newBasedOnLookAndFeel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__BASED_ON_LOOK_AND_FEEL,
                    oldBasedOnLookAndFeel, basedOnLookAndFeel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Data> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<Data>(Data.class, this, ProcessPackage.ABSTRACT_PROCESS__DATA);
        }
        return data;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Connector> getConnectors() {
        if (connectors == null) {
            connectors = new EObjectContainmentEList<Connector>(Connector.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__CONNECTORS);
        }
        return connectors;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<AbstractKPIBinding> getKpis() {
        if (kpis == null) {
            kpis = new EObjectContainmentEList<AbstractKPIBinding>(AbstractKPIBinding.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__KPIS);
        }
        return kpis;
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
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__REG_EXP_TO_HIDE_DEFAULT_FIELD, oldRegExpToHideDefaultField,
                    regExpToHideDefaultField));
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
                    ProcessPackage.ABSTRACT_PROCESS__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD, oldUseRegExpToHideDefaultField,
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__BY_PASS_FORMS_GENERATION,
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
                    ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE, oldConfirmationTemplate,
                    newConfirmationTemplate);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE, null, msgs);
            if (newConfirmationTemplate != null)
                msgs = ((InternalEObject) newConfirmationTemplate).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE, null, msgs);
            msgs = basicSetConfirmationTemplate(newConfirmationTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE,
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
            transientData = new EObjectContainmentEList<Data>(Data.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__TRANSIENT_DATA);
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
                    ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_CONNECTORS);
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__ENTRY_PAGE_FLOW_TYPE,
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__TRANSMIT_URL_AS_PARAMETER,
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
                    ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_TRANSITIONS);
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
            form = new EObjectContainmentEList<Form>(Form.class, this, ProcessPackage.ABSTRACT_PROCESS__FORM);
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
                    ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL, oldEntryRedirectionURL, newEntryRedirectionURL);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL, null, msgs);
            if (newEntryRedirectionURL != null)
                msgs = ((InternalEObject) newEntryRedirectionURL).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL, null, msgs);
            msgs = basicSetEntryRedirectionURL(newEntryRedirectionURL, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL,
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
                    ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE, oldConfirmationMessage, newConfirmationMessage);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE, null, msgs);
            if (newConfirmationMessage != null)
                msgs = ((InternalEObject) newConfirmationMessage).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE, null, msgs);
            msgs = basicSetConfirmationMessage(newConfirmationMessage, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE,
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
                    ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_ACTIONS);
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
                    ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING, oldFormMapping, newFormMapping);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING, null, msgs);
            if (newFormMapping != null)
                msgs = ((InternalEObject) newFormMapping).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING, null, msgs);
            msgs = basicSetFormMapping(newFormMapping, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING,
                    newFormMapping, newFormMapping));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<SimulationData> getSimulationData() {
        if (simulationData == null) {
            simulationData = new EObjectContainmentEList<SimulationData>(SimulationData.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__SIMULATION_DATA);
        }
        return simulationData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLoadProfileID() {
        return loadProfileID;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLoadProfileID(String newLoadProfileID) {
        String oldLoadProfileID = loadProfileID;
        loadProfileID = newLoadProfileID;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__LOAD_PROFILE_ID,
                    oldLoadProfileID, loadProfileID));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public long getMaximumTime() {
        return maximumTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMaximumTime(long newMaximumTime) {
        long oldMaximumTime = maximumTime;
        maximumTime = newMaximumTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__MAXIMUM_TIME,
                    oldMaximumTime, maximumTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<PageFlowTransition> getRecapPageFlowTransitions() {
        if (recapPageFlowTransitions == null) {
            recapPageFlowTransitions = new EObjectContainmentEList<PageFlowTransition>(PageFlowTransition.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TRANSITIONS);
        }
        return recapPageFlowTransitions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Data> getRecapTransientData() {
        if (recapTransientData == null) {
            recapTransientData = new EObjectContainmentEList<Data>(Data.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__RECAP_TRANSIENT_DATA);
        }
        return recapTransientData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Connector> getRecapFlowConnectors() {
        if (recapFlowConnectors == null) {
            recapFlowConnectors = new EObjectContainmentEList<Connector>(Connector.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__RECAP_FLOW_CONNECTORS);
        }
        return recapFlowConnectors;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public ConsultationPageFlowType getRecapPageFlowType() {
        return recapPageFlowType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRecapPageFlowType(ConsultationPageFlowType newRecapPageFlowType) {
        ConsultationPageFlowType oldRecapPageFlowType = recapPageFlowType;
        recapPageFlowType = newRecapPageFlowType == null ? RECAP_PAGE_FLOW_TYPE_EDEFAULT : newRecapPageFlowType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TYPE,
                    oldRecapPageFlowType, recapPageFlowType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ViewForm> getRecapForms() {
        if (recapForms == null) {
            recapForms = new EObjectContainmentEList<ViewForm>(ViewForm.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__RECAP_FORMS);
        }
        return recapForms;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getRecapPageFlowRedirectionURL() {
        return recapPageFlowRedirectionURL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetRecapPageFlowRedirectionURL(Expression newRecapPageFlowRedirectionURL,
            NotificationChain msgs) {
        Expression oldRecapPageFlowRedirectionURL = recapPageFlowRedirectionURL;
        recapPageFlowRedirectionURL = newRecapPageFlowRedirectionURL;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL, oldRecapPageFlowRedirectionURL,
                    newRecapPageFlowRedirectionURL);
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
    public void setRecapPageFlowRedirectionURL(Expression newRecapPageFlowRedirectionURL) {
        if (newRecapPageFlowRedirectionURL != recapPageFlowRedirectionURL) {
            NotificationChain msgs = null;
            if (recapPageFlowRedirectionURL != null)
                msgs = ((InternalEObject) recapPageFlowRedirectionURL).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL, null,
                        msgs);
            if (newRecapPageFlowRedirectionURL != null)
                msgs = ((InternalEObject) newRecapPageFlowRedirectionURL).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL, null,
                        msgs);
            msgs = basicSetRecapPageFlowRedirectionURL(newRecapPageFlowRedirectionURL, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL, newRecapPageFlowRedirectionURL,
                    newRecapPageFlowRedirectionURL));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public FormMapping getOverviewFormMapping() {
        return overviewFormMapping;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetOverviewFormMapping(FormMapping newOverviewFormMapping, NotificationChain msgs) {
        FormMapping oldOverviewFormMapping = overviewFormMapping;
        overviewFormMapping = newOverviewFormMapping;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING, oldOverviewFormMapping, newOverviewFormMapping);
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
    public void setOverviewFormMapping(FormMapping newOverviewFormMapping) {
        if (newOverviewFormMapping != overviewFormMapping) {
            NotificationChain msgs = null;
            if (overviewFormMapping != null)
                msgs = ((InternalEObject) overviewFormMapping).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING, null, msgs);
            if (newOverviewFormMapping != null)
                msgs = ((InternalEObject) newOverviewFormMapping).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING, null, msgs);
            msgs = basicSetOverviewFormMapping(newOverviewFormMapping, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING,
                    newOverviewFormMapping, newOverviewFormMapping));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<PageFlowTransition> getViewPageFlowTransitions() {
        if (viewPageFlowTransitions == null) {
            viewPageFlowTransitions = new EObjectContainmentEList<PageFlowTransition>(PageFlowTransition.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TRANSITIONS);
        }
        return viewPageFlowTransitions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Data> getViewTransientData() {
        if (viewTransientData == null) {
            viewTransientData = new EObjectContainmentEList<Data>(Data.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__VIEW_TRANSIENT_DATA);
        }
        return viewTransientData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Connector> getViewPageFlowConnectors() {
        if (viewPageFlowConnectors == null) {
            viewPageFlowConnectors = new EObjectContainmentEList<Connector>(Connector.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_CONNECTORS);
        }
        return viewPageFlowConnectors;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public ConsultationPageFlowType getViewPageFlowType() {
        return viewPageFlowType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setViewPageFlowType(ConsultationPageFlowType newViewPageFlowType) {
        ConsultationPageFlowType oldViewPageFlowType = viewPageFlowType;
        viewPageFlowType = newViewPageFlowType == null ? VIEW_PAGE_FLOW_TYPE_EDEFAULT : newViewPageFlowType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TYPE,
                    oldViewPageFlowType, viewPageFlowType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ViewForm> getViewForm() {
        if (viewForm == null) {
            viewForm = new EObjectContainmentEList<ViewForm>(ViewForm.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__VIEW_FORM);
        }
        return viewForm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Expression getViewPageFlowRedirectionURL() {
        return viewPageFlowRedirectionURL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetViewPageFlowRedirectionURL(Expression newViewPageFlowRedirectionURL,
            NotificationChain msgs) {
        Expression oldViewPageFlowRedirectionURL = viewPageFlowRedirectionURL;
        viewPageFlowRedirectionURL = newViewPageFlowRedirectionURL;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL, oldViewPageFlowRedirectionURL,
                    newViewPageFlowRedirectionURL);
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
    public void setViewPageFlowRedirectionURL(Expression newViewPageFlowRedirectionURL) {
        if (newViewPageFlowRedirectionURL != viewPageFlowRedirectionURL) {
            NotificationChain msgs = null;
            if (viewPageFlowRedirectionURL != null)
                msgs = ((InternalEObject) viewPageFlowRedirectionURL).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL, null,
                        msgs);
            if (newViewPageFlowRedirectionURL != null)
                msgs = ((InternalEObject) newViewPageFlowRedirectionURL).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL, null,
                        msgs);
            msgs = basicSetViewPageFlowRedirectionURL(newViewPageFlowRedirectionURL, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL, newViewPageFlowRedirectionURL,
                    newViewPageFlowRedirectionURL));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getVersion() {
        return version;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__VERSION, oldVersion,
                    version));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getAuthor() {
        return author;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setAuthor(String newAuthor) {
        String oldAuthor = author;
        author = newAuthor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__AUTHOR, oldAuthor,
                    author));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setCreationDate(Date newCreationDate) {
        Date oldCreationDate = creationDate;
        creationDate = newCreationDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE,
                    oldCreationDate, creationDate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Date getModificationDate() {
        return modificationDate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setModificationDate(Date newModificationDate) {
        Date oldModificationDate = modificationDate;
        modificationDate = newModificationDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE,
                    oldModificationDate, modificationDate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<DataType> getDatatypes() {
        if (datatypes == null) {
            datatypes = new EObjectContainmentEList<DataType>(DataType.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__DATATYPES);
        }
        return datatypes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Connection> getConnections() {
        if (connections == null) {
            connections = new EObjectContainmentEList<Connection>(Connection.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS);
        }
        return connections;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<String> getCategories() {
        if (categories == null) {
            categories = new EDataTypeUniqueEList<String>(String.class, this, ProcessPackage.ABSTRACT_PROCESS__CATEGORIES);
        }
        return categories;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Actor> getActors() {
        if (actors == null) {
            actors = new EObjectContainmentEList<Actor>(Actor.class, this, ProcessPackage.ABSTRACT_PROCESS__ACTORS);
        }
        return actors;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Configuration> getConfigurations() {
        if (configurations == null) {
            configurations = new EObjectContainmentEList<Configuration>(Configuration.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS);
        }
        return configurations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Parameter> getParameters() {
        if (parameters == null) {
            parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this,
                    ProcessPackage.ABSTRACT_PROCESS__PARAMETERS);
        }
        return parameters;
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
            case ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE:
                return basicSetHtmlTemplate(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FILES:
                return ((InternalEList<?>) getResourceFiles()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FOLDERS:
                return ((InternalEList<?>) getResourceFolders()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__HTML_ATTRIBUTES:
                return ((InternalEList<?>) getHtmlAttributes()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL:
                return basicSetMandatorySymbol(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL:
                return basicSetMandatoryLabel(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE:
                return basicSetErrorTemplate(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE:
                return basicSetProcessTemplate(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE:
                return basicSetPageTemplate(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE:
                return basicSetConsultationTemplate(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE:
                return basicSetLogInPage(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE:
                return basicSetWelcomePage(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE:
                return basicSetHostPage(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__DATA:
                return ((InternalEList<?>) getData()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
                return ((InternalEList<?>) getConnectors()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__KPIS:
                return ((InternalEList<?>) getKpis()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE:
                return basicSetConfirmationTemplate(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__TRANSIENT_DATA:
                return ((InternalEList<?>) getTransientData()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_CONNECTORS:
                return ((InternalEList<?>) getPageFlowConnectors()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_TRANSITIONS:
                return ((InternalEList<?>) getPageFlowTransitions()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__FORM:
                return ((InternalEList<?>) getForm()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL:
                return basicSetEntryRedirectionURL(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE:
                return basicSetConfirmationMessage(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_ACTIONS:
                return ((InternalEList<?>) getEntryRedirectionActions()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
                return basicSetFormMapping(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__SIMULATION_DATA:
                return ((InternalEList<?>) getSimulationData()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TRANSITIONS:
                return ((InternalEList<?>) getRecapPageFlowTransitions()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_TRANSIENT_DATA:
                return ((InternalEList<?>) getRecapTransientData()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_FLOW_CONNECTORS:
                return ((InternalEList<?>) getRecapFlowConnectors()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_FORMS:
                return ((InternalEList<?>) getRecapForms()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL:
                return basicSetRecapPageFlowRedirectionURL(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
                return basicSetOverviewFormMapping(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TRANSITIONS:
                return ((InternalEList<?>) getViewPageFlowTransitions()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_TRANSIENT_DATA:
                return ((InternalEList<?>) getViewTransientData()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_CONNECTORS:
                return ((InternalEList<?>) getViewPageFlowConnectors()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_FORM:
                return ((InternalEList<?>) getViewForm()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL:
                return basicSetViewPageFlowRedirectionURL(null, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
                return ((InternalEList<?>) getDatatypes()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
                return ((InternalEList<?>) getConnections()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
                return ((InternalEList<?>) getActors()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS:
                return ((InternalEList<?>) getConfigurations()).basicRemove(otherEnd, msgs);
            case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
                return ((InternalEList<?>) getParameters()).basicRemove(otherEnd, msgs);
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
            case ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE:
                return getHtmlTemplate();
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_JARS:
                return getResourceJars();
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_VALIDATORS:
                return getResourceValidators();
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FILES:
                return getResourceFiles();
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FOLDERS:
                return getResourceFolders();
            case ProcessPackage.ABSTRACT_PROCESS__HTML_ATTRIBUTES:
                if (coreType)
                    return getHtmlAttributes();
                else
                    return getHtmlAttributes().map();
            case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL:
                return getMandatorySymbol();
            case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL:
                return getMandatoryLabel();
            case ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE:
                return getErrorTemplate();
            case ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE:
                return getProcessTemplate();
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE:
                return getPageTemplate();
            case ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE:
                return getConsultationTemplate();
            case ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE:
                return getLogInPage();
            case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE:
                return getWelcomePage();
            case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE_INTERNAL:
                return getWelcomePageInternal();
            case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN:
                return isAutoLogin();
            case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN_ID:
                return getAutoLoginId();
            case ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE:
                return getHostPage();
            case ProcessPackage.ABSTRACT_PROCESS__BASED_ON_LOOK_AND_FEEL:
                return getBasedOnLookAndFeel();
            case ProcessPackage.ABSTRACT_PROCESS__DATA:
                return getData();
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
                return getConnectors();
            case ProcessPackage.ABSTRACT_PROCESS__KPIS:
                return getKpis();
            case ProcessPackage.ABSTRACT_PROCESS__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return getRegExpToHideDefaultField();
            case ProcessPackage.ABSTRACT_PROCESS__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return isUseRegExpToHideDefaultField();
            case ProcessPackage.ABSTRACT_PROCESS__BY_PASS_FORMS_GENERATION:
                return getByPassFormsGeneration();
            case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE:
                return getConfirmationTemplate();
            case ProcessPackage.ABSTRACT_PROCESS__TRANSIENT_DATA:
                return getTransientData();
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_CONNECTORS:
                return getPageFlowConnectors();
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_PAGE_FLOW_TYPE:
                return getEntryPageFlowType();
            case ProcessPackage.ABSTRACT_PROCESS__TRANSMIT_URL_AS_PARAMETER:
                return isTransmitURLAsParameter();
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_TRANSITIONS:
                return getPageFlowTransitions();
            case ProcessPackage.ABSTRACT_PROCESS__FORM:
                return getForm();
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL:
                return getEntryRedirectionURL();
            case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE:
                return getConfirmationMessage();
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_ACTIONS:
                return getEntryRedirectionActions();
            case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
                return getFormMapping();
            case ProcessPackage.ABSTRACT_PROCESS__SIMULATION_DATA:
                return getSimulationData();
            case ProcessPackage.ABSTRACT_PROCESS__LOAD_PROFILE_ID:
                return getLoadProfileID();
            case ProcessPackage.ABSTRACT_PROCESS__MAXIMUM_TIME:
                return getMaximumTime();
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TRANSITIONS:
                return getRecapPageFlowTransitions();
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_TRANSIENT_DATA:
                return getRecapTransientData();
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_FLOW_CONNECTORS:
                return getRecapFlowConnectors();
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TYPE:
                return getRecapPageFlowType();
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_FORMS:
                return getRecapForms();
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL:
                return getRecapPageFlowRedirectionURL();
            case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
                return getOverviewFormMapping();
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TRANSITIONS:
                return getViewPageFlowTransitions();
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_TRANSIENT_DATA:
                return getViewTransientData();
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_CONNECTORS:
                return getViewPageFlowConnectors();
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TYPE:
                return getViewPageFlowType();
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_FORM:
                return getViewForm();
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL:
                return getViewPageFlowRedirectionURL();
            case ProcessPackage.ABSTRACT_PROCESS__VERSION:
                return getVersion();
            case ProcessPackage.ABSTRACT_PROCESS__AUTHOR:
                return getAuthor();
            case ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE:
                return getCreationDate();
            case ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE:
                return getModificationDate();
            case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
                return getDatatypes();
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
                return getConnections();
            case ProcessPackage.ABSTRACT_PROCESS__CATEGORIES:
                return getCategories();
            case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
                return getActors();
            case ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS:
                return getConfigurations();
            case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
                return getParameters();
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
            case ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE:
                setHtmlTemplate((AssociatedFile) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_JARS:
                getResourceJars().clear();
                getResourceJars().addAll((Collection<? extends String>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_VALIDATORS:
                getResourceValidators().clear();
                getResourceValidators().addAll((Collection<? extends String>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FILES:
                getResourceFiles().clear();
                getResourceFiles().addAll((Collection<? extends ResourceFile>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FOLDERS:
                getResourceFolders().clear();
                getResourceFolders().addAll((Collection<? extends ResourceFolder>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__HTML_ATTRIBUTES:
                ((EStructuralFeature.Setting) getHtmlAttributes()).set(newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL:
                setMandatorySymbol((Expression) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL:
                setMandatoryLabel((Expression) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE:
                setErrorTemplate((AssociatedFile) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE:
                setProcessTemplate((AssociatedFile) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE:
                setPageTemplate((AssociatedFile) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE:
                setConsultationTemplate((AssociatedFile) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE:
                setLogInPage((AssociatedFile) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE:
                setWelcomePage((AssociatedFile) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE_INTERNAL:
                setWelcomePageInternal((Boolean) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN:
                setAutoLogin((Boolean) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN_ID:
                setAutoLoginId((String) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE:
                setHostPage((AssociatedFile) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__BASED_ON_LOOK_AND_FEEL:
                setBasedOnLookAndFeel((String) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__DATA:
                getData().clear();
                getData().addAll((Collection<? extends Data>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
                getConnectors().clear();
                getConnectors().addAll((Collection<? extends Connector>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__KPIS:
                getKpis().clear();
                getKpis().addAll((Collection<? extends AbstractKPIBinding>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setRegExpToHideDefaultField((String) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setUseRegExpToHideDefaultField((Boolean) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__BY_PASS_FORMS_GENERATION:
                setByPassFormsGeneration((Boolean) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE:
                setConfirmationTemplate((AssociatedFile) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__TRANSIENT_DATA:
                getTransientData().clear();
                getTransientData().addAll((Collection<? extends Data>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_CONNECTORS:
                getPageFlowConnectors().clear();
                getPageFlowConnectors().addAll((Collection<? extends Connector>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_PAGE_FLOW_TYPE:
                setEntryPageFlowType((EntryPageFlowType) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__TRANSMIT_URL_AS_PARAMETER:
                setTransmitURLAsParameter((Boolean) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_TRANSITIONS:
                getPageFlowTransitions().clear();
                getPageFlowTransitions().addAll((Collection<? extends PageFlowTransition>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__FORM:
                getForm().clear();
                getForm().addAll((Collection<? extends Form>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL:
                setEntryRedirectionURL((Expression) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE:
                setConfirmationMessage((Expression) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_ACTIONS:
                getEntryRedirectionActions().clear();
                getEntryRedirectionActions().addAll((Collection<? extends Operation>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
                setFormMapping((FormMapping) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__SIMULATION_DATA:
                getSimulationData().clear();
                getSimulationData().addAll((Collection<? extends SimulationData>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__LOAD_PROFILE_ID:
                setLoadProfileID((String) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__MAXIMUM_TIME:
                setMaximumTime((Long) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TRANSITIONS:
                getRecapPageFlowTransitions().clear();
                getRecapPageFlowTransitions().addAll((Collection<? extends PageFlowTransition>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_TRANSIENT_DATA:
                getRecapTransientData().clear();
                getRecapTransientData().addAll((Collection<? extends Data>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_FLOW_CONNECTORS:
                getRecapFlowConnectors().clear();
                getRecapFlowConnectors().addAll((Collection<? extends Connector>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TYPE:
                setRecapPageFlowType((ConsultationPageFlowType) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_FORMS:
                getRecapForms().clear();
                getRecapForms().addAll((Collection<? extends ViewForm>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL:
                setRecapPageFlowRedirectionURL((Expression) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
                setOverviewFormMapping((FormMapping) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TRANSITIONS:
                getViewPageFlowTransitions().clear();
                getViewPageFlowTransitions().addAll((Collection<? extends PageFlowTransition>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_TRANSIENT_DATA:
                getViewTransientData().clear();
                getViewTransientData().addAll((Collection<? extends Data>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_CONNECTORS:
                getViewPageFlowConnectors().clear();
                getViewPageFlowConnectors().addAll((Collection<? extends Connector>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TYPE:
                setViewPageFlowType((ConsultationPageFlowType) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_FORM:
                getViewForm().clear();
                getViewForm().addAll((Collection<? extends ViewForm>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL:
                setViewPageFlowRedirectionURL((Expression) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VERSION:
                setVersion((String) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__AUTHOR:
                setAuthor((String) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE:
                setCreationDate((Date) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE:
                setModificationDate((Date) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
                getDatatypes().clear();
                getDatatypes().addAll((Collection<? extends DataType>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
                getConnections().clear();
                getConnections().addAll((Collection<? extends Connection>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CATEGORIES:
                getCategories().clear();
                getCategories().addAll((Collection<? extends String>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
                getActors().clear();
                getActors().addAll((Collection<? extends Actor>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS:
                getConfigurations().clear();
                getConfigurations().addAll((Collection<? extends Configuration>) newValue);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
                getParameters().clear();
                getParameters().addAll((Collection<? extends Parameter>) newValue);
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
            case ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE:
                setHtmlTemplate((AssociatedFile) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_JARS:
                getResourceJars().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_VALIDATORS:
                getResourceValidators().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FILES:
                getResourceFiles().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FOLDERS:
                getResourceFolders().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__HTML_ATTRIBUTES:
                getHtmlAttributes().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL:
                setMandatorySymbol((Expression) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL:
                setMandatoryLabel((Expression) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE:
                setErrorTemplate((AssociatedFile) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE:
                setProcessTemplate((AssociatedFile) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE:
                setPageTemplate((AssociatedFile) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE:
                setConsultationTemplate((AssociatedFile) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE:
                setLogInPage((AssociatedFile) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE:
                setWelcomePage((AssociatedFile) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE_INTERNAL:
                setWelcomePageInternal(WELCOME_PAGE_INTERNAL_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN:
                setAutoLogin(AUTO_LOGIN_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN_ID:
                setAutoLoginId(AUTO_LOGIN_ID_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE:
                setHostPage((AssociatedFile) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__BASED_ON_LOOK_AND_FEEL:
                setBasedOnLookAndFeel(BASED_ON_LOOK_AND_FEEL_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__DATA:
                getData().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
                getConnectors().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__KPIS:
                getKpis().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setRegExpToHideDefaultField(REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                setUseRegExpToHideDefaultField(USE_REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__BY_PASS_FORMS_GENERATION:
                setByPassFormsGeneration(BY_PASS_FORMS_GENERATION_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE:
                setConfirmationTemplate((AssociatedFile) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__TRANSIENT_DATA:
                getTransientData().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_CONNECTORS:
                getPageFlowConnectors().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_PAGE_FLOW_TYPE:
                setEntryPageFlowType(ENTRY_PAGE_FLOW_TYPE_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__TRANSMIT_URL_AS_PARAMETER:
                setTransmitURLAsParameter(TRANSMIT_URL_AS_PARAMETER_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_TRANSITIONS:
                getPageFlowTransitions().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__FORM:
                getForm().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL:
                setEntryRedirectionURL((Expression) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE:
                setConfirmationMessage((Expression) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_ACTIONS:
                getEntryRedirectionActions().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
                setFormMapping((FormMapping) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__SIMULATION_DATA:
                getSimulationData().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__LOAD_PROFILE_ID:
                setLoadProfileID(LOAD_PROFILE_ID_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__MAXIMUM_TIME:
                setMaximumTime(MAXIMUM_TIME_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TRANSITIONS:
                getRecapPageFlowTransitions().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_TRANSIENT_DATA:
                getRecapTransientData().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_FLOW_CONNECTORS:
                getRecapFlowConnectors().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TYPE:
                setRecapPageFlowType(RECAP_PAGE_FLOW_TYPE_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_FORMS:
                getRecapForms().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL:
                setRecapPageFlowRedirectionURL((Expression) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
                setOverviewFormMapping((FormMapping) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TRANSITIONS:
                getViewPageFlowTransitions().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_TRANSIENT_DATA:
                getViewTransientData().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_CONNECTORS:
                getViewPageFlowConnectors().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TYPE:
                setViewPageFlowType(VIEW_PAGE_FLOW_TYPE_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_FORM:
                getViewForm().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL:
                setViewPageFlowRedirectionURL((Expression) null);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__AUTHOR:
                setAuthor(AUTHOR_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE:
                setCreationDate(CREATION_DATE_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE:
                setModificationDate(MODIFICATION_DATE_EDEFAULT);
                return;
            case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
                getDatatypes().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
                getConnections().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CATEGORIES:
                getCategories().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
                getActors().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS:
                getConfigurations().clear();
                return;
            case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
                getParameters().clear();
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
            case ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE:
                return htmlTemplate != null;
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_JARS:
                return resourceJars != null && !resourceJars.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_VALIDATORS:
                return resourceValidators != null && !resourceValidators.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FILES:
                return resourceFiles != null && !resourceFiles.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FOLDERS:
                return resourceFolders != null && !resourceFolders.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__HTML_ATTRIBUTES:
                return htmlAttributes != null && !htmlAttributes.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL:
                return mandatorySymbol != null;
            case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL:
                return mandatoryLabel != null;
            case ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE:
                return errorTemplate != null;
            case ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE:
                return processTemplate != null;
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE:
                return pageTemplate != null;
            case ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE:
                return consultationTemplate != null;
            case ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE:
                return logInPage != null;
            case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE:
                return welcomePage != null;
            case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE_INTERNAL:
                return WELCOME_PAGE_INTERNAL_EDEFAULT == null ? welcomePageInternal != null
                        : !WELCOME_PAGE_INTERNAL_EDEFAULT.equals(welcomePageInternal);
            case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN:
                return autoLogin != AUTO_LOGIN_EDEFAULT;
            case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN_ID:
                return AUTO_LOGIN_ID_EDEFAULT == null ? autoLoginId != null : !AUTO_LOGIN_ID_EDEFAULT.equals(autoLoginId);
            case ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE:
                return hostPage != null;
            case ProcessPackage.ABSTRACT_PROCESS__BASED_ON_LOOK_AND_FEEL:
                return BASED_ON_LOOK_AND_FEEL_EDEFAULT == null ? basedOnLookAndFeel != null
                        : !BASED_ON_LOOK_AND_FEEL_EDEFAULT.equals(basedOnLookAndFeel);
            case ProcessPackage.ABSTRACT_PROCESS__DATA:
                return data != null && !data.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
                return connectors != null && !connectors.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__KPIS:
                return kpis != null && !kpis.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT == null ? regExpToHideDefaultField != null
                        : !REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT.equals(regExpToHideDefaultField);
            case ProcessPackage.ABSTRACT_PROCESS__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                return useRegExpToHideDefaultField != USE_REG_EXP_TO_HIDE_DEFAULT_FIELD_EDEFAULT;
            case ProcessPackage.ABSTRACT_PROCESS__BY_PASS_FORMS_GENERATION:
                return BY_PASS_FORMS_GENERATION_EDEFAULT == null ? byPassFormsGeneration != null
                        : !BY_PASS_FORMS_GENERATION_EDEFAULT.equals(byPassFormsGeneration);
            case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE:
                return confirmationTemplate != null;
            case ProcessPackage.ABSTRACT_PROCESS__TRANSIENT_DATA:
                return transientData != null && !transientData.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_CONNECTORS:
                return pageFlowConnectors != null && !pageFlowConnectors.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_PAGE_FLOW_TYPE:
                return entryPageFlowType != ENTRY_PAGE_FLOW_TYPE_EDEFAULT;
            case ProcessPackage.ABSTRACT_PROCESS__TRANSMIT_URL_AS_PARAMETER:
                return transmitURLAsParameter != TRANSMIT_URL_AS_PARAMETER_EDEFAULT;
            case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_TRANSITIONS:
                return pageFlowTransitions != null && !pageFlowTransitions.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__FORM:
                return form != null && !form.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL:
                return entryRedirectionURL != null;
            case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE:
                return confirmationMessage != null;
            case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_ACTIONS:
                return entryRedirectionActions != null && !entryRedirectionActions.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
                return formMapping != null;
            case ProcessPackage.ABSTRACT_PROCESS__SIMULATION_DATA:
                return simulationData != null && !simulationData.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__LOAD_PROFILE_ID:
                return LOAD_PROFILE_ID_EDEFAULT == null ? loadProfileID != null
                        : !LOAD_PROFILE_ID_EDEFAULT.equals(loadProfileID);
            case ProcessPackage.ABSTRACT_PROCESS__MAXIMUM_TIME:
                return maximumTime != MAXIMUM_TIME_EDEFAULT;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TRANSITIONS:
                return recapPageFlowTransitions != null && !recapPageFlowTransitions.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_TRANSIENT_DATA:
                return recapTransientData != null && !recapTransientData.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_FLOW_CONNECTORS:
                return recapFlowConnectors != null && !recapFlowConnectors.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TYPE:
                return recapPageFlowType != RECAP_PAGE_FLOW_TYPE_EDEFAULT;
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_FORMS:
                return recapForms != null && !recapForms.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL:
                return recapPageFlowRedirectionURL != null;
            case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
                return overviewFormMapping != null;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TRANSITIONS:
                return viewPageFlowTransitions != null && !viewPageFlowTransitions.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_TRANSIENT_DATA:
                return viewTransientData != null && !viewTransientData.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_CONNECTORS:
                return viewPageFlowConnectors != null && !viewPageFlowConnectors.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TYPE:
                return viewPageFlowType != VIEW_PAGE_FLOW_TYPE_EDEFAULT;
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_FORM:
                return viewForm != null && !viewForm.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL:
                return viewPageFlowRedirectionURL != null;
            case ProcessPackage.ABSTRACT_PROCESS__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case ProcessPackage.ABSTRACT_PROCESS__AUTHOR:
                return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
            case ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE:
                return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals(creationDate);
            case ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE:
                return MODIFICATION_DATE_EDEFAULT == null ? modificationDate != null
                        : !MODIFICATION_DATE_EDEFAULT.equals(modificationDate);
            case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
                return datatypes != null && !datatypes.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
                return connections != null && !connections.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__CATEGORIES:
                return categories != null && !categories.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
                return actors != null && !actors.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__CONFIGURATIONS:
                return configurations != null && !configurations.isEmpty();
            case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
                return parameters != null && !parameters.isEmpty();
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
        if (baseClass == ResourceContainer.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE:
                    return ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE;
                case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_JARS:
                    return ProcessPackage.RESOURCE_CONTAINER__RESOURCE_JARS;
                case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_VALIDATORS:
                    return ProcessPackage.RESOURCE_CONTAINER__RESOURCE_VALIDATORS;
                case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FILES:
                    return ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FILES;
                case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FOLDERS:
                    return ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FOLDERS;
                default:
                    return -1;
            }
        }
        if (baseClass == CSSCustomizable.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__HTML_ATTRIBUTES:
                    return FormPackage.CSS_CUSTOMIZABLE__HTML_ATTRIBUTES;
                default:
                    return -1;
            }
        }
        if (baseClass == MandatoryFieldsCustomization.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL:
                    return FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL;
                case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL:
                    return FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL;
                default:
                    return -1;
            }
        }
        if (baseClass == ProcessApplication.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE:
                    return ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE;
                case ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE:
                    return ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE;
                case ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE:
                    return ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE;
                case ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE:
                    return ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE;
                case ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE:
                    return ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE;
                case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE:
                    return ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE;
                case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE_INTERNAL:
                    return ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL;
                case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN:
                    return ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN;
                case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN_ID:
                    return ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN_ID;
                case ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE:
                    return ProcessPackage.PROCESS_APPLICATION__HOST_PAGE;
                case ProcessPackage.ABSTRACT_PROCESS__BASED_ON_LOOK_AND_FEEL:
                    return ProcessPackage.PROCESS_APPLICATION__BASED_ON_LOOK_AND_FEEL;
                default:
                    return -1;
            }
        }
        if (baseClass == DataAware.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__DATA:
                    return ProcessPackage.DATA_AWARE__DATA;
                default:
                    return -1;
            }
        }
        if (baseClass == ConnectableElement.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
                    return ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS;
                case ProcessPackage.ABSTRACT_PROCESS__KPIS:
                    return ProcessPackage.CONNECTABLE_ELEMENT__KPIS;
                default:
                    return -1;
            }
        }
        if (baseClass == AbstractPageFlow.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.ABSTRACT_PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD;
                case ProcessPackage.ABSTRACT_PROCESS__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.ABSTRACT_PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD;
                default:
                    return -1;
            }
        }
        if (baseClass == PageFlow.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__BY_PASS_FORMS_GENERATION:
                    return ProcessPackage.PAGE_FLOW__BY_PASS_FORMS_GENERATION;
                case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE:
                    return ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE;
                case ProcessPackage.ABSTRACT_PROCESS__TRANSIENT_DATA:
                    return ProcessPackage.PAGE_FLOW__TRANSIENT_DATA;
                case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_CONNECTORS:
                    return ProcessPackage.PAGE_FLOW__PAGE_FLOW_CONNECTORS;
                case ProcessPackage.ABSTRACT_PROCESS__ENTRY_PAGE_FLOW_TYPE:
                    return ProcessPackage.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE;
                case ProcessPackage.ABSTRACT_PROCESS__TRANSMIT_URL_AS_PARAMETER:
                    return ProcessPackage.PAGE_FLOW__TRANSMIT_URL_AS_PARAMETER;
                case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_TRANSITIONS:
                    return ProcessPackage.PAGE_FLOW__PAGE_FLOW_TRANSITIONS;
                case ProcessPackage.ABSTRACT_PROCESS__FORM:
                    return ProcessPackage.PAGE_FLOW__FORM;
                case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL:
                    return ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL;
                case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE:
                    return ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE;
                case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_ACTIONS:
                    return ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS;
                case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
                    return ProcessPackage.PAGE_FLOW__FORM_MAPPING;
                default:
                    return -1;
            }
        }
        if (baseClass == SimulationDataContainer.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__SIMULATION_DATA:
                    return SimulationPackage.SIMULATION_DATA_CONTAINER__SIMULATION_DATA;
                default:
                    return -1;
            }
        }
        if (baseClass == SimulationAbstractProcess.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__LOAD_PROFILE_ID:
                    return SimulationPackage.SIMULATION_ABSTRACT_PROCESS__LOAD_PROFILE_ID;
                case ProcessPackage.ABSTRACT_PROCESS__MAXIMUM_TIME:
                    return SimulationPackage.SIMULATION_ABSTRACT_PROCESS__MAXIMUM_TIME;
                default:
                    return -1;
            }
        }
        if (baseClass == RecapFlow.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TRANSITIONS:
                    return ProcessPackage.RECAP_FLOW__RECAP_PAGE_FLOW_TRANSITIONS;
                case ProcessPackage.ABSTRACT_PROCESS__RECAP_TRANSIENT_DATA:
                    return ProcessPackage.RECAP_FLOW__RECAP_TRANSIENT_DATA;
                case ProcessPackage.ABSTRACT_PROCESS__RECAP_FLOW_CONNECTORS:
                    return ProcessPackage.RECAP_FLOW__RECAP_FLOW_CONNECTORS;
                case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TYPE:
                    return ProcessPackage.RECAP_FLOW__RECAP_PAGE_FLOW_TYPE;
                case ProcessPackage.ABSTRACT_PROCESS__RECAP_FORMS:
                    return ProcessPackage.RECAP_FLOW__RECAP_FORMS;
                case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL:
                    return ProcessPackage.RECAP_FLOW__RECAP_PAGE_FLOW_REDIRECTION_URL;
                case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
                    return ProcessPackage.RECAP_FLOW__OVERVIEW_FORM_MAPPING;
                default:
                    return -1;
            }
        }
        if (baseClass == ViewPageFlow.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TRANSITIONS:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TRANSITIONS;
                case ProcessPackage.ABSTRACT_PROCESS__VIEW_TRANSIENT_DATA:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA;
                case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_CONNECTORS:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS;
                case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TYPE:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TYPE;
                case ProcessPackage.ABSTRACT_PROCESS__VIEW_FORM:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_FORM;
                case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL:
                    return ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_REDIRECTION_URL;
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
        if (baseClass == ResourceContainer.class) {
            switch (baseFeatureID) {
                case ProcessPackage.RESOURCE_CONTAINER__HTML_TEMPLATE:
                    return ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE;
                case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_JARS:
                    return ProcessPackage.ABSTRACT_PROCESS__RESOURCE_JARS;
                case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_VALIDATORS:
                    return ProcessPackage.ABSTRACT_PROCESS__RESOURCE_VALIDATORS;
                case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FILES:
                    return ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FILES;
                case ProcessPackage.RESOURCE_CONTAINER__RESOURCE_FOLDERS:
                    return ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FOLDERS;
                default:
                    return -1;
            }
        }
        if (baseClass == CSSCustomizable.class) {
            switch (baseFeatureID) {
                case FormPackage.CSS_CUSTOMIZABLE__HTML_ATTRIBUTES:
                    return ProcessPackage.ABSTRACT_PROCESS__HTML_ATTRIBUTES;
                default:
                    return -1;
            }
        }
        if (baseClass == MandatoryFieldsCustomization.class) {
            switch (baseFeatureID) {
                case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL:
                    return ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL;
                case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL:
                    return ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL;
                default:
                    return -1;
            }
        }
        if (baseClass == ProcessApplication.class) {
            switch (baseFeatureID) {
                case ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE:
                    return ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE;
                case ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE:
                    return ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE;
                case ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE:
                    return ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE;
                case ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE:
                    return ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE;
                case ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE:
                    return ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE;
                case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE:
                    return ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE;
                case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL:
                    return ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE_INTERNAL;
                case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN:
                    return ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN;
                case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN_ID:
                    return ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN_ID;
                case ProcessPackage.PROCESS_APPLICATION__HOST_PAGE:
                    return ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE;
                case ProcessPackage.PROCESS_APPLICATION__BASED_ON_LOOK_AND_FEEL:
                    return ProcessPackage.ABSTRACT_PROCESS__BASED_ON_LOOK_AND_FEEL;
                default:
                    return -1;
            }
        }
        if (baseClass == DataAware.class) {
            switch (baseFeatureID) {
                case ProcessPackage.DATA_AWARE__DATA:
                    return ProcessPackage.ABSTRACT_PROCESS__DATA;
                default:
                    return -1;
            }
        }
        if (baseClass == ConnectableElement.class) {
            switch (baseFeatureID) {
                case ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS:
                    return ProcessPackage.ABSTRACT_PROCESS__CONNECTORS;
                case ProcessPackage.CONNECTABLE_ELEMENT__KPIS:
                    return ProcessPackage.ABSTRACT_PROCESS__KPIS;
                default:
                    return -1;
            }
        }
        if (baseClass == AbstractPageFlow.class) {
            switch (baseFeatureID) {
                case ProcessPackage.ABSTRACT_PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.ABSTRACT_PROCESS__REG_EXP_TO_HIDE_DEFAULT_FIELD;
                case ProcessPackage.ABSTRACT_PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
                    return ProcessPackage.ABSTRACT_PROCESS__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD;
                default:
                    return -1;
            }
        }
        if (baseClass == PageFlow.class) {
            switch (baseFeatureID) {
                case ProcessPackage.PAGE_FLOW__BY_PASS_FORMS_GENERATION:
                    return ProcessPackage.ABSTRACT_PROCESS__BY_PASS_FORMS_GENERATION;
                case ProcessPackage.PAGE_FLOW__CONFIRMATION_TEMPLATE:
                    return ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE;
                case ProcessPackage.PAGE_FLOW__TRANSIENT_DATA:
                    return ProcessPackage.ABSTRACT_PROCESS__TRANSIENT_DATA;
                case ProcessPackage.PAGE_FLOW__PAGE_FLOW_CONNECTORS:
                    return ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_CONNECTORS;
                case ProcessPackage.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE:
                    return ProcessPackage.ABSTRACT_PROCESS__ENTRY_PAGE_FLOW_TYPE;
                case ProcessPackage.PAGE_FLOW__TRANSMIT_URL_AS_PARAMETER:
                    return ProcessPackage.ABSTRACT_PROCESS__TRANSMIT_URL_AS_PARAMETER;
                case ProcessPackage.PAGE_FLOW__PAGE_FLOW_TRANSITIONS:
                    return ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_TRANSITIONS;
                case ProcessPackage.PAGE_FLOW__FORM:
                    return ProcessPackage.ABSTRACT_PROCESS__FORM;
                case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_URL:
                    return ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL;
                case ProcessPackage.PAGE_FLOW__CONFIRMATION_MESSAGE:
                    return ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE;
                case ProcessPackage.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS:
                    return ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_ACTIONS;
                case ProcessPackage.PAGE_FLOW__FORM_MAPPING:
                    return ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING;
                default:
                    return -1;
            }
        }
        if (baseClass == SimulationDataContainer.class) {
            switch (baseFeatureID) {
                case SimulationPackage.SIMULATION_DATA_CONTAINER__SIMULATION_DATA:
                    return ProcessPackage.ABSTRACT_PROCESS__SIMULATION_DATA;
                default:
                    return -1;
            }
        }
        if (baseClass == SimulationAbstractProcess.class) {
            switch (baseFeatureID) {
                case SimulationPackage.SIMULATION_ABSTRACT_PROCESS__LOAD_PROFILE_ID:
                    return ProcessPackage.ABSTRACT_PROCESS__LOAD_PROFILE_ID;
                case SimulationPackage.SIMULATION_ABSTRACT_PROCESS__MAXIMUM_TIME:
                    return ProcessPackage.ABSTRACT_PROCESS__MAXIMUM_TIME;
                default:
                    return -1;
            }
        }
        if (baseClass == RecapFlow.class) {
            switch (baseFeatureID) {
                case ProcessPackage.RECAP_FLOW__RECAP_PAGE_FLOW_TRANSITIONS:
                    return ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TRANSITIONS;
                case ProcessPackage.RECAP_FLOW__RECAP_TRANSIENT_DATA:
                    return ProcessPackage.ABSTRACT_PROCESS__RECAP_TRANSIENT_DATA;
                case ProcessPackage.RECAP_FLOW__RECAP_FLOW_CONNECTORS:
                    return ProcessPackage.ABSTRACT_PROCESS__RECAP_FLOW_CONNECTORS;
                case ProcessPackage.RECAP_FLOW__RECAP_PAGE_FLOW_TYPE:
                    return ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TYPE;
                case ProcessPackage.RECAP_FLOW__RECAP_FORMS:
                    return ProcessPackage.ABSTRACT_PROCESS__RECAP_FORMS;
                case ProcessPackage.RECAP_FLOW__RECAP_PAGE_FLOW_REDIRECTION_URL:
                    return ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL;
                case ProcessPackage.RECAP_FLOW__OVERVIEW_FORM_MAPPING:
                    return ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING;
                default:
                    return -1;
            }
        }
        if (baseClass == ViewPageFlow.class) {
            switch (baseFeatureID) {
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TRANSITIONS:
                    return ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TRANSITIONS;
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA:
                    return ProcessPackage.ABSTRACT_PROCESS__VIEW_TRANSIENT_DATA;
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS:
                    return ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_CONNECTORS;
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TYPE:
                    return ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TYPE;
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_FORM:
                    return ProcessPackage.ABSTRACT_PROCESS__VIEW_FORM;
                case ProcessPackage.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_REDIRECTION_URL:
                    return ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL;
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
        result.append(" (resourceJars: "); //$NON-NLS-1$
        result.append(resourceJars);
        result.append(", resourceValidators: "); //$NON-NLS-1$
        result.append(resourceValidators);
        result.append(", welcomePageInternal: "); //$NON-NLS-1$
        result.append(welcomePageInternal);
        result.append(", autoLogin: "); //$NON-NLS-1$
        result.append(autoLogin);
        result.append(", autoLoginId: "); //$NON-NLS-1$
        result.append(autoLoginId);
        result.append(", basedOnLookAndFeel: "); //$NON-NLS-1$
        result.append(basedOnLookAndFeel);
        result.append(", regExpToHideDefaultField: "); //$NON-NLS-1$
        result.append(regExpToHideDefaultField);
        result.append(", useRegExpToHideDefaultField: "); //$NON-NLS-1$
        result.append(useRegExpToHideDefaultField);
        result.append(", byPassFormsGeneration: "); //$NON-NLS-1$
        result.append(byPassFormsGeneration);
        result.append(", entryPageFlowType: "); //$NON-NLS-1$
        result.append(entryPageFlowType);
        result.append(", transmitURLAsParameter: "); //$NON-NLS-1$
        result.append(transmitURLAsParameter);
        result.append(", loadProfileID: "); //$NON-NLS-1$
        result.append(loadProfileID);
        result.append(", maximumTime: "); //$NON-NLS-1$
        result.append(maximumTime);
        result.append(", recapPageFlowType: "); //$NON-NLS-1$
        result.append(recapPageFlowType);
        result.append(", viewPageFlowType: "); //$NON-NLS-1$
        result.append(viewPageFlowType);
        result.append(", version: "); //$NON-NLS-1$
        result.append(version);
        result.append(", author: "); //$NON-NLS-1$
        result.append(author);
        result.append(", creationDate: "); //$NON-NLS-1$
        result.append(creationDate);
        result.append(", modificationDate: "); //$NON-NLS-1$
        result.append(modificationDate);
        result.append(", categories: "); //$NON-NLS-1$
        result.append(categories);
        result.append(')');
        return result.toString();
    }

} //AbstractProcessImpl
