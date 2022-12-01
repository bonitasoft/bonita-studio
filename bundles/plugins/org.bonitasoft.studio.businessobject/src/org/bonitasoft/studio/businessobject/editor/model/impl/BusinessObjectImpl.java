/**
 */
package org.bonitasoft.studio.businessobject.editor.model.impl;

import java.util.Collection;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;

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
 * An implementation of the model object '<em><b>Business Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl#getQualifiedName <em>Qualified Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl#getSimpleName <em>Simple Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl#getFields <em>Fields</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl#getUniqueConstraints <em>Unique Constraints</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl#getQueries <em>Queries</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.impl.BusinessObjectImpl#getDefaultQueries <em>Default Queries</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BusinessObjectImpl extends MinimalEObjectImpl.Container implements BusinessObject {
    /**
     * The default value of the '{@link #getQualifiedName() <em>Qualified Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQualifiedName()
     * @generated
     * @ordered
     */
    protected static final String QUALIFIED_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getQualifiedName() <em>Qualified Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQualifiedName()
     * @generated
     * @ordered
     */
    protected String qualifiedName = QUALIFIED_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getSimpleName() <em>Simple Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSimpleName()
     * @generated
     * @ordered
     */
    protected static final String SIMPLE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSimpleName() <em>Simple Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSimpleName()
     * @generated
     * @ordered
     */
    protected String simpleName = SIMPLE_NAME_EDEFAULT;

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
     * The cached value of the '{@link #getFields() <em>Fields</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFields()
     * @generated
     * @ordered
     */
    protected EList<Field> fields;

    /**
     * The cached value of the '{@link #getUniqueConstraints() <em>Unique Constraints</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUniqueConstraints()
     * @generated
     * @ordered
     */
    protected EList<UniqueConstraint> uniqueConstraints;

    /**
     * The cached value of the '{@link #getIndexes() <em>Indexes</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIndexes()
     * @generated
     * @ordered
     */
    protected EList<Index> indexes;

    /**
     * The cached value of the '{@link #getQueries() <em>Queries</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getQueries()
     * @generated
     * @ordered
     */
    protected EList<Query> queries;

    /**
     * The cached value of the '{@link #getDefaultQueries() <em>Default Queries</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultQueries()
     * @generated
     * @ordered
     */
    protected EList<Query> defaultQueries;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BusinessObjectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BusinessDataModelPackage.Literals.BUSINESS_OBJECT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getQualifiedName() {
        return qualifiedName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQualifiedName(String newQualifiedName) {
        String oldQualifiedName = qualifiedName;
        qualifiedName = newQualifiedName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.BUSINESS_OBJECT__QUALIFIED_NAME, oldQualifiedName, qualifiedName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSimpleName(String newSimpleName) {
        String oldSimpleName = simpleName;
        simpleName = newSimpleName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.BUSINESS_OBJECT__SIMPLE_NAME, oldSimpleName, simpleName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BusinessDataModelPackage.BUSINESS_OBJECT__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Field> getFields() {
        if (fields == null) {
            fields = new EObjectContainmentEList<Field>(Field.class, this, BusinessDataModelPackage.BUSINESS_OBJECT__FIELDS);
        }
        return fields;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<UniqueConstraint> getUniqueConstraints() {
        if (uniqueConstraints == null) {
            uniqueConstraints = new EObjectContainmentEList<UniqueConstraint>(UniqueConstraint.class, this, BusinessDataModelPackage.BUSINESS_OBJECT__UNIQUE_CONSTRAINTS);
        }
        return uniqueConstraints;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Index> getIndexes() {
        if (indexes == null) {
            indexes = new EObjectContainmentEList<Index>(Index.class, this, BusinessDataModelPackage.BUSINESS_OBJECT__INDEXES);
        }
        return indexes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Query> getQueries() {
        if (queries == null) {
            queries = new EObjectContainmentEList<Query>(Query.class, this, BusinessDataModelPackage.BUSINESS_OBJECT__QUERIES);
        }
        return queries;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Query> getDefaultQueries() {
        if (defaultQueries == null) {
            defaultQueries = new EObjectContainmentEList<Query>(Query.class, this, BusinessDataModelPackage.BUSINESS_OBJECT__DEFAULT_QUERIES);
        }
        return defaultQueries;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case BusinessDataModelPackage.BUSINESS_OBJECT__FIELDS:
                return ((InternalEList<?>)getFields()).basicRemove(otherEnd, msgs);
            case BusinessDataModelPackage.BUSINESS_OBJECT__UNIQUE_CONSTRAINTS:
                return ((InternalEList<?>)getUniqueConstraints()).basicRemove(otherEnd, msgs);
            case BusinessDataModelPackage.BUSINESS_OBJECT__INDEXES:
                return ((InternalEList<?>)getIndexes()).basicRemove(otherEnd, msgs);
            case BusinessDataModelPackage.BUSINESS_OBJECT__QUERIES:
                return ((InternalEList<?>)getQueries()).basicRemove(otherEnd, msgs);
            case BusinessDataModelPackage.BUSINESS_OBJECT__DEFAULT_QUERIES:
                return ((InternalEList<?>)getDefaultQueries()).basicRemove(otherEnd, msgs);
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
            case BusinessDataModelPackage.BUSINESS_OBJECT__QUALIFIED_NAME:
                return getQualifiedName();
            case BusinessDataModelPackage.BUSINESS_OBJECT__SIMPLE_NAME:
                return getSimpleName();
            case BusinessDataModelPackage.BUSINESS_OBJECT__DESCRIPTION:
                return getDescription();
            case BusinessDataModelPackage.BUSINESS_OBJECT__FIELDS:
                return getFields();
            case BusinessDataModelPackage.BUSINESS_OBJECT__UNIQUE_CONSTRAINTS:
                return getUniqueConstraints();
            case BusinessDataModelPackage.BUSINESS_OBJECT__INDEXES:
                return getIndexes();
            case BusinessDataModelPackage.BUSINESS_OBJECT__QUERIES:
                return getQueries();
            case BusinessDataModelPackage.BUSINESS_OBJECT__DEFAULT_QUERIES:
                return getDefaultQueries();
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
            case BusinessDataModelPackage.BUSINESS_OBJECT__QUALIFIED_NAME:
                setQualifiedName((String)newValue);
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__SIMPLE_NAME:
                setSimpleName((String)newValue);
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__FIELDS:
                getFields().clear();
                getFields().addAll((Collection<? extends Field>)newValue);
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__UNIQUE_CONSTRAINTS:
                getUniqueConstraints().clear();
                getUniqueConstraints().addAll((Collection<? extends UniqueConstraint>)newValue);
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__INDEXES:
                getIndexes().clear();
                getIndexes().addAll((Collection<? extends Index>)newValue);
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__QUERIES:
                getQueries().clear();
                getQueries().addAll((Collection<? extends Query>)newValue);
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__DEFAULT_QUERIES:
                getDefaultQueries().clear();
                getDefaultQueries().addAll((Collection<? extends Query>)newValue);
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
            case BusinessDataModelPackage.BUSINESS_OBJECT__QUALIFIED_NAME:
                setQualifiedName(QUALIFIED_NAME_EDEFAULT);
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__SIMPLE_NAME:
                setSimpleName(SIMPLE_NAME_EDEFAULT);
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__FIELDS:
                getFields().clear();
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__UNIQUE_CONSTRAINTS:
                getUniqueConstraints().clear();
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__INDEXES:
                getIndexes().clear();
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__QUERIES:
                getQueries().clear();
                return;
            case BusinessDataModelPackage.BUSINESS_OBJECT__DEFAULT_QUERIES:
                getDefaultQueries().clear();
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
            case BusinessDataModelPackage.BUSINESS_OBJECT__QUALIFIED_NAME:
                return QUALIFIED_NAME_EDEFAULT == null ? qualifiedName != null : !QUALIFIED_NAME_EDEFAULT.equals(qualifiedName);
            case BusinessDataModelPackage.BUSINESS_OBJECT__SIMPLE_NAME:
                return SIMPLE_NAME_EDEFAULT == null ? simpleName != null : !SIMPLE_NAME_EDEFAULT.equals(simpleName);
            case BusinessDataModelPackage.BUSINESS_OBJECT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case BusinessDataModelPackage.BUSINESS_OBJECT__FIELDS:
                return fields != null && !fields.isEmpty();
            case BusinessDataModelPackage.BUSINESS_OBJECT__UNIQUE_CONSTRAINTS:
                return uniqueConstraints != null && !uniqueConstraints.isEmpty();
            case BusinessDataModelPackage.BUSINESS_OBJECT__INDEXES:
                return indexes != null && !indexes.isEmpty();
            case BusinessDataModelPackage.BUSINESS_OBJECT__QUERIES:
                return queries != null && !queries.isEmpty();
            case BusinessDataModelPackage.BUSINESS_OBJECT__DEFAULT_QUERIES:
                return defaultQueries != null && !defaultQueries.isEmpty();
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
        result.append(" (qualifiedName: ");
        result.append(qualifiedName);
        result.append(", simpleName: ");
        result.append(simpleName);
        result.append(", description: ");
        result.append(description);
        result.append(')');
        return result.toString();
    }

} //BusinessObjectImpl
