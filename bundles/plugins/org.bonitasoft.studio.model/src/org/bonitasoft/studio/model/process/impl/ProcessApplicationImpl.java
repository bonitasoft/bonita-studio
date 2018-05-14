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

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.form.CSSCustomizable;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.MandatoryFieldsCustomization;

import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.ProcessApplication;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getHtmlAttributes <em>Html
 * Attributes</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getMandatorySymbol <em>Mandatory
 * Symbol</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getMandatoryLabel <em>Mandatory
 * Label</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getErrorTemplate <em>Error Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getProcessTemplate <em>Process
 * Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getPageTemplate <em>Page Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getConsultationTemplate <em>Consultation
 * Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getLogInPage <em>Log In Page</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getWelcomePage <em>Welcome Page</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getWelcomePageInternal <em>Welcome Page
 * Internal</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#isAutoLogin <em>Auto Login</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getAutoLoginId <em>Auto Login Id</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getHostPage <em>Host Page</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.impl.ProcessApplicationImpl#getBasedOnLookAndFeel <em>Based On Look And
 * Feel</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProcessApplicationImpl extends ResourceContainerImpl implements ProcessApplication {

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ProcessApplicationImpl() {
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
        return ProcessPackage.Literals.PROCESS_APPLICATION;
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
                    EStringToStringMapEntryImpl.class, this, ProcessPackage.PROCESS_APPLICATION__HTML_ATTRIBUTES);
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
                    ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL, oldMandatorySymbol, newMandatorySymbol);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL, null, msgs);
            if (newMandatorySymbol != null)
                msgs = ((InternalEObject) newMandatorySymbol).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL, null, msgs);
            msgs = basicSetMandatorySymbol(newMandatorySymbol, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL,
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
                    ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL, oldMandatoryLabel, newMandatoryLabel);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL, null, msgs);
            if (newMandatoryLabel != null)
                msgs = ((InternalEObject) newMandatoryLabel).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL, null, msgs);
            msgs = basicSetMandatoryLabel(newMandatoryLabel, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL,
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
                    ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE, oldErrorTemplate, newErrorTemplate);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE, null, msgs);
            if (newErrorTemplate != null)
                msgs = ((InternalEObject) newErrorTemplate).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE, null, msgs);
            msgs = basicSetErrorTemplate(newErrorTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE,
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
                    ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE, oldProcessTemplate, newProcessTemplate);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE, null, msgs);
            if (newProcessTemplate != null)
                msgs = ((InternalEObject) newProcessTemplate).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE, null, msgs);
            msgs = basicSetProcessTemplate(newProcessTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE,
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
                    ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE, oldPageTemplate, newPageTemplate);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE, null, msgs);
            if (newPageTemplate != null)
                msgs = ((InternalEObject) newPageTemplate).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE, null, msgs);
            msgs = basicSetPageTemplate(newPageTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE,
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
                    ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE, oldConsultationTemplate,
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE, null, msgs);
            if (newConsultationTemplate != null)
                msgs = ((InternalEObject) newConsultationTemplate).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE, null, msgs);
            msgs = basicSetConsultationTemplate(newConsultationTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE,
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
                    ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE, oldLogInPage, newLogInPage);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE, null, msgs);
            if (newLogInPage != null)
                msgs = ((InternalEObject) newLogInPage).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE, null, msgs);
            msgs = basicSetLogInPage(newLogInPage, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE,
                    newLogInPage, newLogInPage));
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
                    ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE, oldWelcomePage, newWelcomePage);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE, null, msgs);
            if (newWelcomePage != null)
                msgs = ((InternalEObject) newWelcomePage).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE, null, msgs);
            msgs = basicSetWelcomePage(newWelcomePage, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE,
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL,
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN,
                    oldAutoLogin, autoLogin));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN_ID,
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
                    ProcessPackage.PROCESS_APPLICATION__HOST_PAGE, oldHostPage, newHostPage);
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
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__HOST_PAGE, null, msgs);
            if (newHostPage != null)
                msgs = ((InternalEObject) newHostPage).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ProcessPackage.PROCESS_APPLICATION__HOST_PAGE, null, msgs);
            msgs = basicSetHostPage(newHostPage, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__HOST_PAGE, newHostPage,
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
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_APPLICATION__BASED_ON_LOOK_AND_FEEL,
                    oldBasedOnLookAndFeel, basedOnLookAndFeel));
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
            case ProcessPackage.PROCESS_APPLICATION__HTML_ATTRIBUTES:
                return ((InternalEList<?>) getHtmlAttributes()).basicRemove(otherEnd, msgs);
            case ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL:
                return basicSetMandatorySymbol(null, msgs);
            case ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL:
                return basicSetMandatoryLabel(null, msgs);
            case ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE:
                return basicSetErrorTemplate(null, msgs);
            case ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE:
                return basicSetProcessTemplate(null, msgs);
            case ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE:
                return basicSetPageTemplate(null, msgs);
            case ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE:
                return basicSetConsultationTemplate(null, msgs);
            case ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE:
                return basicSetLogInPage(null, msgs);
            case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE:
                return basicSetWelcomePage(null, msgs);
            case ProcessPackage.PROCESS_APPLICATION__HOST_PAGE:
                return basicSetHostPage(null, msgs);
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
            case ProcessPackage.PROCESS_APPLICATION__HTML_ATTRIBUTES:
                if (coreType)
                    return getHtmlAttributes();
                else
                    return getHtmlAttributes().map();
            case ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL:
                return getMandatorySymbol();
            case ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL:
                return getMandatoryLabel();
            case ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE:
                return getErrorTemplate();
            case ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE:
                return getProcessTemplate();
            case ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE:
                return getPageTemplate();
            case ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE:
                return getConsultationTemplate();
            case ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE:
                return getLogInPage();
            case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE:
                return getWelcomePage();
            case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL:
                return getWelcomePageInternal();
            case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN:
                return isAutoLogin();
            case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN_ID:
                return getAutoLoginId();
            case ProcessPackage.PROCESS_APPLICATION__HOST_PAGE:
                return getHostPage();
            case ProcessPackage.PROCESS_APPLICATION__BASED_ON_LOOK_AND_FEEL:
                return getBasedOnLookAndFeel();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ProcessPackage.PROCESS_APPLICATION__HTML_ATTRIBUTES:
                ((EStructuralFeature.Setting) getHtmlAttributes()).set(newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL:
                setMandatorySymbol((Expression) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL:
                setMandatoryLabel((Expression) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE:
                setErrorTemplate((AssociatedFile) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE:
                setProcessTemplate((AssociatedFile) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE:
                setPageTemplate((AssociatedFile) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE:
                setConsultationTemplate((AssociatedFile) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE:
                setLogInPage((AssociatedFile) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE:
                setWelcomePage((AssociatedFile) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL:
                setWelcomePageInternal((Boolean) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN:
                setAutoLogin((Boolean) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN_ID:
                setAutoLoginId((String) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__HOST_PAGE:
                setHostPage((AssociatedFile) newValue);
                return;
            case ProcessPackage.PROCESS_APPLICATION__BASED_ON_LOOK_AND_FEEL:
                setBasedOnLookAndFeel((String) newValue);
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
            case ProcessPackage.PROCESS_APPLICATION__HTML_ATTRIBUTES:
                getHtmlAttributes().clear();
                return;
            case ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL:
                setMandatorySymbol((Expression) null);
                return;
            case ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL:
                setMandatoryLabel((Expression) null);
                return;
            case ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE:
                setErrorTemplate((AssociatedFile) null);
                return;
            case ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE:
                setProcessTemplate((AssociatedFile) null);
                return;
            case ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE:
                setPageTemplate((AssociatedFile) null);
                return;
            case ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE:
                setConsultationTemplate((AssociatedFile) null);
                return;
            case ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE:
                setLogInPage((AssociatedFile) null);
                return;
            case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE:
                setWelcomePage((AssociatedFile) null);
                return;
            case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL:
                setWelcomePageInternal(WELCOME_PAGE_INTERNAL_EDEFAULT);
                return;
            case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN:
                setAutoLogin(AUTO_LOGIN_EDEFAULT);
                return;
            case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN_ID:
                setAutoLoginId(AUTO_LOGIN_ID_EDEFAULT);
                return;
            case ProcessPackage.PROCESS_APPLICATION__HOST_PAGE:
                setHostPage((AssociatedFile) null);
                return;
            case ProcessPackage.PROCESS_APPLICATION__BASED_ON_LOOK_AND_FEEL:
                setBasedOnLookAndFeel(BASED_ON_LOOK_AND_FEEL_EDEFAULT);
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
            case ProcessPackage.PROCESS_APPLICATION__HTML_ATTRIBUTES:
                return htmlAttributes != null && !htmlAttributes.isEmpty();
            case ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL:
                return mandatorySymbol != null;
            case ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL:
                return mandatoryLabel != null;
            case ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE:
                return errorTemplate != null;
            case ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE:
                return processTemplate != null;
            case ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE:
                return pageTemplate != null;
            case ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE:
                return consultationTemplate != null;
            case ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE:
                return logInPage != null;
            case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE:
                return welcomePage != null;
            case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL:
                return WELCOME_PAGE_INTERNAL_EDEFAULT == null ? welcomePageInternal != null
                        : !WELCOME_PAGE_INTERNAL_EDEFAULT.equals(welcomePageInternal);
            case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN:
                return autoLogin != AUTO_LOGIN_EDEFAULT;
            case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN_ID:
                return AUTO_LOGIN_ID_EDEFAULT == null ? autoLoginId != null : !AUTO_LOGIN_ID_EDEFAULT.equals(autoLoginId);
            case ProcessPackage.PROCESS_APPLICATION__HOST_PAGE:
                return hostPage != null;
            case ProcessPackage.PROCESS_APPLICATION__BASED_ON_LOOK_AND_FEEL:
                return BASED_ON_LOOK_AND_FEEL_EDEFAULT == null ? basedOnLookAndFeel != null
                        : !BASED_ON_LOOK_AND_FEEL_EDEFAULT.equals(basedOnLookAndFeel);
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
        if (baseClass == CSSCustomizable.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.PROCESS_APPLICATION__HTML_ATTRIBUTES:
                    return FormPackage.CSS_CUSTOMIZABLE__HTML_ATTRIBUTES;
                default:
                    return -1;
            }
        }
        if (baseClass == MandatoryFieldsCustomization.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL:
                    return FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL;
                case ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL:
                    return FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL;
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
        if (baseClass == CSSCustomizable.class) {
            switch (baseFeatureID) {
                case FormPackage.CSS_CUSTOMIZABLE__HTML_ATTRIBUTES:
                    return ProcessPackage.PROCESS_APPLICATION__HTML_ATTRIBUTES;
                default:
                    return -1;
            }
        }
        if (baseClass == MandatoryFieldsCustomization.class) {
            switch (baseFeatureID) {
                case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL:
                    return ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL;
                case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL:
                    return ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL;
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
        result.append(" (welcomePageInternal: "); //$NON-NLS-1$
        result.append(welcomePageInternal);
        result.append(", autoLogin: "); //$NON-NLS-1$
        result.append(autoLogin);
        result.append(", autoLoginId: "); //$NON-NLS-1$
        result.append(autoLoginId);
        result.append(", basedOnLookAndFeel: "); //$NON-NLS-1$
        result.append(basedOnLookAndFeel);
        result.append(')');
        return result.toString();
    }

} //ProcessApplicationImpl
