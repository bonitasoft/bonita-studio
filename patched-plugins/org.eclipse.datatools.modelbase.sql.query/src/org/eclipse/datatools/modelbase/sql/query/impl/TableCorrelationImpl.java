/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableCorrelationImpl.java,v 1.5 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Table Correlation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableCorrelationImpl#getTableExpr <em>Table Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableCorrelationImpl#getColumnNameList <em>Column Name List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableCorrelationImpl extends SQLQueryObjectImpl implements TableCorrelation {
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
    protected TableCorrelationImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.TABLE_CORRELATION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableExpression getTableExpr() {
        if (eContainerFeatureID() != SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR) return null;
        return (TableExpression)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTableExpr(TableExpression newTableExpr, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTableExpr, SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableExpr(TableExpression newTableExpr) {
        if (newTableExpr != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR && newTableExpr != null)) {
            if (EcoreUtil.isAncestor(this, newTableExpr))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTableExpr != null)
                msgs = ((InternalEObject)newTableExpr).eInverseAdd(this, SQLQueryModelPackage.TABLE_EXPRESSION__TABLE_CORRELATION, TableExpression.class, msgs);
            msgs = basicSetTableExpr(newTableExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR, newTableExpr, newTableExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getColumnNameList() {
        if (columnNameList == null) {
            columnNameList = new EObjectContainmentWithInverseEList(ColumnName.class, this, SQLQueryModelPackage.TABLE_CORRELATION__COLUMN_NAME_LIST, SQLQueryModelPackage.COLUMN_NAME__TABLE_CORRELATION);
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
            case SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTableExpr((TableExpression)otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
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
            case SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR:
                return basicSetTableExpr(null, msgs);
            case SQLQueryModelPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
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
            case SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.TABLE_EXPRESSION__TABLE_CORRELATION, TableExpression.class, msgs);
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
            case SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR:
                return getTableExpr();
            case SQLQueryModelPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
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
            case SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR:
                setTableExpr((TableExpression)newValue);
                return;
            case SQLQueryModelPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
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
            case SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR:
                setTableExpr((TableExpression)null);
                return;
            case SQLQueryModelPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
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
            case SQLQueryModelPackage.TABLE_CORRELATION__TABLE_EXPR:
                return getTableExpr() != null;
            case SQLQueryModelPackage.TABLE_CORRELATION__COLUMN_NAME_LIST:
                return columnNameList != null && !columnNameList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SQLTableCorrelationImpl
