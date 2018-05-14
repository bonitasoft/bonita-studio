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
package org.bonitasoft.studio.model.process;

import org.bonitasoft.studio.model.form.MandatoryFieldsCustomization;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.bonitasoft.studio.model.process.ProcessApplication#getErrorTemplate <em>Error Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ProcessApplication#getProcessTemplate <em>Process Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ProcessApplication#getPageTemplate <em>Page Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ProcessApplication#getConsultationTemplate <em>Consultation
 * Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ProcessApplication#getLogInPage <em>Log In Page</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ProcessApplication#getWelcomePage <em>Welcome Page</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ProcessApplication#getWelcomePageInternal <em>Welcome Page
 * Internal</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ProcessApplication#isAutoLogin <em>Auto Login</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ProcessApplication#getAutoLoginId <em>Auto Login Id</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ProcessApplication#getHostPage <em>Host Page</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ProcessApplication#getBasedOnLookAndFeel <em>Based On Look And
 * Feel</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication()
 * @model
 * @generated
 */
public interface ProcessApplication extends ResourceContainer, MandatoryFieldsCustomization {

    /**
     * Returns the value of the '<em><b>Error Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Error Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Error Template</em>' containment reference.
     * @see #setErrorTemplate(AssociatedFile)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication_ErrorTemplate()
     * @model containment="true"
     * @generated
     */
    AssociatedFile getErrorTemplate();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ProcessApplication#getErrorTemplate <em>Error
     * Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Error Template</em>' containment reference.
     * @see #getErrorTemplate()
     * @generated
     */
    void setErrorTemplate(AssociatedFile value);

    /**
     * Returns the value of the '<em><b>Process Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Process Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Process Template</em>' containment reference.
     * @see #setProcessTemplate(AssociatedFile)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication_ProcessTemplate()
     * @model containment="true"
     * @generated
     */
    AssociatedFile getProcessTemplate();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ProcessApplication#getProcessTemplate <em>Process
     * Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Process Template</em>' containment reference.
     * @see #getProcessTemplate()
     * @generated
     */
    void setProcessTemplate(AssociatedFile value);

    /**
     * Returns the value of the '<em><b>Page Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Page Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Page Template</em>' containment reference.
     * @see #setPageTemplate(AssociatedFile)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication_PageTemplate()
     * @model containment="true"
     * @generated
     */
    AssociatedFile getPageTemplate();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ProcessApplication#getPageTemplate <em>Page
     * Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Page Template</em>' containment reference.
     * @see #getPageTemplate()
     * @generated
     */
    void setPageTemplate(AssociatedFile value);

    /**
     * Returns the value of the '<em><b>Consultation Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Consultation Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Consultation Template</em>' containment reference.
     * @see #setConsultationTemplate(AssociatedFile)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication_ConsultationTemplate()
     * @model containment="true"
     * @generated
     */
    AssociatedFile getConsultationTemplate();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ProcessApplication#getConsultationTemplate
     * <em>Consultation Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Consultation Template</em>' containment reference.
     * @see #getConsultationTemplate()
     * @generated
     */
    void setConsultationTemplate(AssociatedFile value);

    /**
     * Returns the value of the '<em><b>Log In Page</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Log In Page</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Log In Page</em>' containment reference.
     * @see #setLogInPage(AssociatedFile)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication_LogInPage()
     * @model containment="true"
     * @generated
     */
    AssociatedFile getLogInPage();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ProcessApplication#getLogInPage <em>Log In
     * Page</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Log In Page</em>' containment reference.
     * @see #getLogInPage()
     * @generated
     */
    void setLogInPage(AssociatedFile value);

    /**
     * Returns the value of the '<em><b>Welcome Page</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Welcome Page</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Welcome Page</em>' containment reference.
     * @see #setWelcomePage(AssociatedFile)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication_WelcomePage()
     * @model containment="true"
     * @generated
     */
    AssociatedFile getWelcomePage();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ProcessApplication#getWelcomePage <em>Welcome
     * Page</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Welcome Page</em>' containment reference.
     * @see #getWelcomePage()
     * @generated
     */
    void setWelcomePage(AssociatedFile value);

    /**
     * Returns the value of the '<em><b>Welcome Page Internal</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Welcome Page Internal</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Welcome Page Internal</em>' attribute.
     * @see #setWelcomePageInternal(Boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication_WelcomePageInternal()
     * @model default="true"
     * @generated
     */
    Boolean getWelcomePageInternal();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ProcessApplication#getWelcomePageInternal
     * <em>Welcome Page Internal</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Welcome Page Internal</em>' attribute.
     * @see #getWelcomePageInternal()
     * @generated
     */
    void setWelcomePageInternal(Boolean value);

    /**
     * Returns the value of the '<em><b>Auto Login</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Auto Login</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Auto Login</em>' attribute.
     * @see #setAutoLogin(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication_AutoLogin()
     * @model
     * @generated
     */
    boolean isAutoLogin();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ProcessApplication#isAutoLogin <em>Auto Login</em>}'
     * attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Auto Login</em>' attribute.
     * @see #isAutoLogin()
     * @generated
     */
    void setAutoLogin(boolean value);

    /**
     * Returns the value of the '<em><b>Auto Login Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Auto Login Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Auto Login Id</em>' attribute.
     * @see #setAutoLoginId(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication_AutoLoginId()
     * @model
     * @generated
     */
    String getAutoLoginId();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ProcessApplication#getAutoLoginId <em>Auto Login
     * Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Auto Login Id</em>' attribute.
     * @see #getAutoLoginId()
     * @generated
     */
    void setAutoLoginId(String value);

    /**
     * Returns the value of the '<em><b>Host Page</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Host Page</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Host Page</em>' containment reference.
     * @see #setHostPage(AssociatedFile)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication_HostPage()
     * @model containment="true"
     * @generated
     */
    AssociatedFile getHostPage();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ProcessApplication#getHostPage <em>Host Page</em>}'
     * containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Host Page</em>' containment reference.
     * @see #getHostPage()
     * @generated
     */
    void setHostPage(AssociatedFile value);

    /**
     * Returns the value of the '<em><b>Based On Look And Feel</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Based On Look And Feel</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Based On Look And Feel</em>' attribute.
     * @see #setBasedOnLookAndFeel(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getProcessApplication_BasedOnLookAndFeel()
     * @model
     * @generated
     */
    String getBasedOnLookAndFeel();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ProcessApplication#getBasedOnLookAndFeel <em>Based
     * On Look And Feel</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Based On Look And Feel</em>' attribute.
     * @see #getBasedOnLookAndFeel()
     * @generated
     */
    void setBasedOnLookAndFeel(String value);

} // ProcessApplication
