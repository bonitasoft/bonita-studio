/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Merge Insert Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeInsertSpecificationImpl#getTargetColumnList <em>Target Column List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeInsertSpecificationImpl#getSourceValuesRow <em>Source Values Row</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MergeInsertSpecificationImpl extends MergeOperationSpecificationImpl implements MergeInsertSpecification {
    /**
     * The cached value of the '{@link #getTargetColumnList() <em>Target Column List</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetColumnList()
     * @generated
     * @ordered
     */
    protected EList targetColumnList;

    /**
     * The cached value of the '{@link #getSourceValuesRow() <em>Source Values Row</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceValuesRow()
     * @generated
     * @ordered
     */
    protected ValuesRow sourceValuesRow;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MergeInsertSpecificationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.MERGE_INSERT_SPECIFICATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTargetColumnList() {
        if (targetColumnList == null) {
            targetColumnList = new EObjectWithInverseResolvingEList.ManyInverse(ValueExpressionColumn.class, this, SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__TARGET_COLUMN_LIST, SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC);
        }
        return targetColumnList;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValuesRow getSourceValuesRow() {
        return sourceValuesRow;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSourceValuesRow(ValuesRow newSourceValuesRow, NotificationChain msgs) {
        ValuesRow oldSourceValuesRow = sourceValuesRow;
        sourceValuesRow = newSourceValuesRow;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW, oldSourceValuesRow, newSourceValuesRow);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceValuesRow(ValuesRow newSourceValuesRow) {
        if (newSourceValuesRow != sourceValuesRow) {
            NotificationChain msgs = null;
            if (sourceValuesRow != null)
                msgs = ((InternalEObject)sourceValuesRow).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW, null, msgs);
            if (newSourceValuesRow != null)
                msgs = ((InternalEObject)newSourceValuesRow).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW, null, msgs);
            msgs = basicSetSourceValuesRow(newSourceValuesRow, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW, newSourceValuesRow, newSourceValuesRow));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__TARGET_COLUMN_LIST:
                return ((InternalEList)getTargetColumnList()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__TARGET_COLUMN_LIST:
                return ((InternalEList)getTargetColumnList()).basicRemove(otherEnd, msgs);
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW:
                return basicSetSourceValuesRow(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__TARGET_COLUMN_LIST:
                return getTargetColumnList();
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW:
                return getSourceValuesRow();
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
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__TARGET_COLUMN_LIST:
                getTargetColumnList().clear();
                getTargetColumnList().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW:
                setSourceValuesRow((ValuesRow)newValue);
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
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__TARGET_COLUMN_LIST:
                getTargetColumnList().clear();
                return;
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW:
                setSourceValuesRow((ValuesRow)null);
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
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__TARGET_COLUMN_LIST:
                return targetColumnList != null && !targetColumnList.isEmpty();
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW:
                return sourceValuesRow != null;
        }
        return super.eIsSet(featureID);
    }

} //MergeInsertSpecificationImpl
