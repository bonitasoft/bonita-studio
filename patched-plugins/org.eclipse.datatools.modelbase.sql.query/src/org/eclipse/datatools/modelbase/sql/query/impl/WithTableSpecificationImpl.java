/**
 * <copyright>
 * </copyright>
 *
 * $Id: WithTableSpecificationImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>With Table Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl#getQueryExpressionRoot <em>Query Expression Root</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl#getWithTableQueryExpr <em>With Table Query Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl#getWithTableReferences <em>With Table References</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl#getColumnNameList <em>Column Name List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WithTableSpecificationImpl extends SQLQueryObjectImpl implements WithTableSpecification {
	/**
     * The cached value of the '{@link #getWithTableQueryExpr() <em>With Table Query Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWithTableQueryExpr()
     * @generated
     * @ordered
     */
    protected QueryExpressionBody withTableQueryExpr;

	/**
     * The cached value of the '{@link #getWithTableReferences() <em>With Table References</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWithTableReferences()
     * @generated
     * @ordered
     */
    protected EList withTableReferences;

	/**
     * The cached value of the '{@link #getColumnNameList() <em>Column Name List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColumnNameList()
     * @generated
     * @ordered
     */
    protected EList columnNameList;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected WithTableSpecificationImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.WITH_TABLE_SPECIFICATION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryExpressionRoot getQueryExpressionRoot() {
        if (eContainerFeatureID() != SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT) return null;
        return (QueryExpressionRoot)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetQueryExpressionRoot(QueryExpressionRoot newQueryExpressionRoot, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQueryExpressionRoot, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQueryExpressionRoot(QueryExpressionRoot newQueryExpressionRoot) {
        if (newQueryExpressionRoot != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT && newQueryExpressionRoot != null)) {
            if (EcoreUtil.isAncestor(this, newQueryExpressionRoot))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQueryExpressionRoot != null)
                msgs = ((InternalEObject)newQueryExpressionRoot).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE, QueryExpressionRoot.class, msgs);
            msgs = basicSetQueryExpressionRoot(newQueryExpressionRoot, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT, newQueryExpressionRoot, newQueryExpressionRoot));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryExpressionBody getWithTableQueryExpr() {
        return withTableQueryExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetWithTableQueryExpr(QueryExpressionBody newWithTableQueryExpr, NotificationChain msgs) {
        QueryExpressionBody oldWithTableQueryExpr = withTableQueryExpr;
        withTableQueryExpr = newWithTableQueryExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR, oldWithTableQueryExpr, newWithTableQueryExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWithTableQueryExpr(QueryExpressionBody newWithTableQueryExpr) {
        if (newWithTableQueryExpr != withTableQueryExpr) {
            NotificationChain msgs = null;
            if (withTableQueryExpr != null)
                msgs = ((InternalEObject)withTableQueryExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION, QueryExpressionBody.class, msgs);
            if (newWithTableQueryExpr != null)
                msgs = ((InternalEObject)newWithTableQueryExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION, QueryExpressionBody.class, msgs);
            msgs = basicSetWithTableQueryExpr(newWithTableQueryExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR, newWithTableQueryExpr, newWithTableQueryExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getWithTableReferences() {
        if (withTableReferences == null) {
            withTableReferences = new EObjectWithInverseResolvingEList(WithTableReference.class, this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES, SQLQueryModelPackage.WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION);
        }
        return withTableReferences;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getColumnNameList() {
        if (columnNameList == null) {
            columnNameList = new EObjectContainmentWithInverseEList(ColumnName.class, this, SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST, SQLQueryModelPackage.COLUMN_NAME__WITH_TABLE_SPECIFICATION);
        }
        return columnNameList;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQueryExpressionRoot((QueryExpressionRoot)otherEnd, msgs);
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
                if (withTableQueryExpr != null)
                    msgs = ((InternalEObject)withTableQueryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR, null, msgs);
                return basicSetWithTableQueryExpr((QueryExpressionBody)otherEnd, msgs);
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
                return ((InternalEList)getWithTableReferences()).basicAdd(otherEnd, msgs);
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
                return ((InternalEList)getColumnNameList()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
                return basicSetQueryExpressionRoot(null, msgs);
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
                return basicSetWithTableQueryExpr(null, msgs);
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
                return ((InternalEList)getWithTableReferences()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
                return ((InternalEList)getColumnNameList()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__WITH_CLAUSE, QueryExpressionRoot.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
                return getQueryExpressionRoot();
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
                return getWithTableQueryExpr();
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
                return getWithTableReferences();
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
                return getColumnNameList();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
                setQueryExpressionRoot((QueryExpressionRoot)newValue);
                return;
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
                setWithTableQueryExpr((QueryExpressionBody)newValue);
                return;
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
                getWithTableReferences().clear();
                getWithTableReferences().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
                getColumnNameList().clear();
                getColumnNameList().addAll((Collection)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(int featureID) {
        switch (featureID) {
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
                setQueryExpressionRoot((QueryExpressionRoot)null);
                return;
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
                setWithTableQueryExpr((QueryExpressionBody)null);
                return;
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
                getWithTableReferences().clear();
                return;
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
                getColumnNameList().clear();
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT:
                return getQueryExpressionRoot() != null;
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR:
                return withTableQueryExpr != null;
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES:
                return withTableReferences != null && !withTableReferences.isEmpty();
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST:
                return columnNameList != null && !columnNameList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //WithTableSpecificationImpl
