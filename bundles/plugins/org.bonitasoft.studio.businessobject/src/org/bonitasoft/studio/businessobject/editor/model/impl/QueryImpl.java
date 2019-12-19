/**
 */
package org.bonitasoft.studio.businessobject.editor.model.impl;

import java.util.Collection;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.QueryParameter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.QueryImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.QueryImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.QueryImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.QueryImpl#getQueryParameters <em>Query Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class QueryImpl extends MinimalEObjectImpl.Container implements Query {
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
     * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContent()
     * @generated
     * @ordered
     */
    protected static final String CONTENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContent()
     * @generated
     * @ordered
     */
    protected String content = CONTENT_EDEFAULT;

    /**
     * The default value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReturnType()
     * @generated
     * @ordered
     */
    protected static final String RETURN_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReturnType()
     * @generated
     * @ordered
     */
    protected String returnType = RETURN_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getQueryParameters() <em>Query Parameters</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQueryParameters()
     * @generated
     * @ordered
     */
    protected EList<QueryParameter> queryParameters;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QueryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BusinessDataModelPackage.Literals.QUERY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.QUERY__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getContent() {
        return content;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContent(String newContent) {
        String oldContent = content;
        content = newContent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.QUERY__CONTENT, oldContent, content));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReturnType(String newReturnType) {
        String oldReturnType = returnType;
        returnType = newReturnType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.QUERY__RETURN_TYPE, oldReturnType, returnType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<QueryParameter> getQueryParameters() {
        if (queryParameters == null) {
            queryParameters = new EObjectContainmentEList<QueryParameter>(QueryParameter.class, this, BusinessDataModelPackage.QUERY__QUERY_PARAMETERS);
        }
        return queryParameters;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case BusinessDataModelPackage.QUERY__QUERY_PARAMETERS:
                return ((InternalEList<?>)getQueryParameters()).basicRemove(otherEnd, msgs);
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
            case BusinessDataModelPackage.QUERY__NAME:
                return getName();
            case BusinessDataModelPackage.QUERY__CONTENT:
                return getContent();
            case BusinessDataModelPackage.QUERY__RETURN_TYPE:
                return getReturnType();
            case BusinessDataModelPackage.QUERY__QUERY_PARAMETERS:
                return getQueryParameters();
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
            case BusinessDataModelPackage.QUERY__NAME:
                setName((String)newValue);
                return;
            case BusinessDataModelPackage.QUERY__CONTENT:
                setContent((String)newValue);
                return;
            case BusinessDataModelPackage.QUERY__RETURN_TYPE:
                setReturnType((String)newValue);
                return;
            case BusinessDataModelPackage.QUERY__QUERY_PARAMETERS:
                getQueryParameters().clear();
                getQueryParameters().addAll((Collection<? extends QueryParameter>)newValue);
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
            case BusinessDataModelPackage.QUERY__NAME:
                setName(NAME_EDEFAULT);
                return;
            case BusinessDataModelPackage.QUERY__CONTENT:
                setContent(CONTENT_EDEFAULT);
                return;
            case BusinessDataModelPackage.QUERY__RETURN_TYPE:
                setReturnType(RETURN_TYPE_EDEFAULT);
                return;
            case BusinessDataModelPackage.QUERY__QUERY_PARAMETERS:
                getQueryParameters().clear();
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
            case BusinessDataModelPackage.QUERY__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case BusinessDataModelPackage.QUERY__CONTENT:
                return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
            case BusinessDataModelPackage.QUERY__RETURN_TYPE:
                return RETURN_TYPE_EDEFAULT == null ? returnType != null : !RETURN_TYPE_EDEFAULT.equals(returnType);
            case BusinessDataModelPackage.QUERY__QUERY_PARAMETERS:
                return queryParameters != null && !queryParameters.isEmpty();
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
        result.append(" (name: ");
        result.append(name);
        result.append(", content: ");
        result.append(content);
        result.append(", returnType: ");
        result.append(returnType);
        result.append(')');
        return result.toString();
    }

} //QueryImpl
