/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableJoinedImpl.java,v 1.5 2007/02/08 17:00:31 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Table Joined</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl#getJoinOperator <em>Join Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl#getJoinCondition <em>Join Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl#getTableRefRight <em>Table Ref Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl#getTableRefLeft <em>Table Ref Left</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableJoinedImpl extends TableReferenceImpl implements TableJoined {
	/**
     * The default value of the '{@link #getJoinOperator() <em>Join Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJoinOperator()
     * @generated
     * @ordered
     */
    protected static final TableJoinedOperator JOIN_OPERATOR_EDEFAULT = TableJoinedOperator.DEFAULT_INNER_LITERAL;

	/**
     * The cached value of the '{@link #getJoinOperator() <em>Join Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJoinOperator()
     * @generated
     * @ordered
     */
    protected TableJoinedOperator joinOperator = JOIN_OPERATOR_EDEFAULT;

	/**
     * The cached value of the '{@link #getJoinCondition() <em>Join Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJoinCondition()
     * @generated
     * @ordered
     */
    protected QuerySearchCondition joinCondition;

	/**
     * The cached value of the '{@link #getTableRefRight() <em>Table Ref Right</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTableRefRight()
     * @generated
     * @ordered
     */
    protected TableReference tableRefRight;

	/**
     * The cached value of the '{@link #getTableRefLeft() <em>Table Ref Left</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTableRefLeft()
     * @generated
     * @ordered
     */
    protected TableReference tableRefLeft;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TableJoinedImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.TABLE_JOINED;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableJoinedOperator getJoinOperator() {
        return joinOperator;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setJoinOperator(TableJoinedOperator newJoinOperator) {
        TableJoinedOperator oldJoinOperator = joinOperator;
        joinOperator = newJoinOperator == null ? JOIN_OPERATOR_EDEFAULT : newJoinOperator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_JOINED__JOIN_OPERATOR, oldJoinOperator, joinOperator));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySearchCondition getJoinCondition() {
        return joinCondition;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetJoinCondition(QuerySearchCondition newJoinCondition, NotificationChain msgs) {
        QuerySearchCondition oldJoinCondition = joinCondition;
        joinCondition = newJoinCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION, oldJoinCondition, newJoinCondition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setJoinCondition(QuerySearchCondition newJoinCondition) {
        if (newJoinCondition != joinCondition) {
            NotificationChain msgs = null;
            if (joinCondition != null)
                msgs = ((InternalEObject)joinCondition).eInverseRemove(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED, QuerySearchCondition.class, msgs);
            if (newJoinCondition != null)
                msgs = ((InternalEObject)newJoinCondition).eInverseAdd(this, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED, QuerySearchCondition.class, msgs);
            msgs = basicSetJoinCondition(newJoinCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION, newJoinCondition, newJoinCondition));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableReference getTableRefRight() {
        return tableRefRight;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTableRefRight(TableReference newTableRefRight, NotificationChain msgs) {
        TableReference oldTableRefRight = tableRefRight;
        tableRefRight = newTableRefRight;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT, oldTableRefRight, newTableRefRight);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableRefRight(TableReference newTableRefRight) {
        if (newTableRefRight != tableRefRight) {
            NotificationChain msgs = null;
            if (tableRefRight != null)
                msgs = ((InternalEObject)tableRefRight).eInverseRemove(this, SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT, TableReference.class, msgs);
            if (newTableRefRight != null)
                msgs = ((InternalEObject)newTableRefRight).eInverseAdd(this, SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_RIGHT, TableReference.class, msgs);
            msgs = basicSetTableRefRight(newTableRefRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT, newTableRefRight, newTableRefRight));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableReference getTableRefLeft() {
        return tableRefLeft;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTableRefLeft(TableReference newTableRefLeft, NotificationChain msgs) {
        TableReference oldTableRefLeft = tableRefLeft;
        tableRefLeft = newTableRefLeft;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT, oldTableRefLeft, newTableRefLeft);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableRefLeft(TableReference newTableRefLeft) {
        if (newTableRefLeft != tableRefLeft) {
            NotificationChain msgs = null;
            if (tableRefLeft != null)
                msgs = ((InternalEObject)tableRefLeft).eInverseRemove(this, SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT, TableReference.class, msgs);
            if (newTableRefLeft != null)
                msgs = ((InternalEObject)newTableRefLeft).eInverseAdd(this, SQLQueryModelPackage.TABLE_REFERENCE__TABLE_JOINED_LEFT, TableReference.class, msgs);
            msgs = basicSetTableRefLeft(newTableRefLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT, newTableRefLeft, newTableRefLeft));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION:
                if (joinCondition != null)
                    msgs = ((InternalEObject)joinCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION, null, msgs);
                return basicSetJoinCondition((QuerySearchCondition)otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT:
                if (tableRefRight != null)
                    msgs = ((InternalEObject)tableRefRight).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT, null, msgs);
                return basicSetTableRefRight((TableReference)otherEnd, msgs);
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT:
                if (tableRefLeft != null)
                    msgs = ((InternalEObject)tableRefLeft).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT, null, msgs);
                return basicSetTableRefLeft((TableReference)otherEnd, msgs);
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
            case SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION:
                return basicSetJoinCondition(null, msgs);
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT:
                return basicSetTableRefRight(null, msgs);
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT:
                return basicSetTableRefLeft(null, msgs);
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
            case SQLQueryModelPackage.TABLE_JOINED__JOIN_OPERATOR:
                return getJoinOperator();
            case SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION:
                return getJoinCondition();
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT:
                return getTableRefRight();
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT:
                return getTableRefLeft();
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
            case SQLQueryModelPackage.TABLE_JOINED__JOIN_OPERATOR:
                setJoinOperator((TableJoinedOperator)newValue);
                return;
            case SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION:
                setJoinCondition((QuerySearchCondition)newValue);
                return;
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT:
                setTableRefRight((TableReference)newValue);
                return;
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT:
                setTableRefLeft((TableReference)newValue);
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
            case SQLQueryModelPackage.TABLE_JOINED__JOIN_OPERATOR:
                setJoinOperator(JOIN_OPERATOR_EDEFAULT);
                return;
            case SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION:
                setJoinCondition((QuerySearchCondition)null);
                return;
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT:
                setTableRefRight((TableReference)null);
                return;
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT:
                setTableRefLeft((TableReference)null);
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
            case SQLQueryModelPackage.TABLE_JOINED__JOIN_OPERATOR:
                return joinOperator != JOIN_OPERATOR_EDEFAULT;
            case SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION:
                return joinCondition != null;
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_RIGHT:
                return tableRefRight != null;
            case SQLQueryModelPackage.TABLE_JOINED__TABLE_REF_LEFT:
                return tableRefLeft != null;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (joinOperator: ");
        result.append(joinOperator);
        result.append(')');
        return result.toString();
    }

} //SQLTableJoinedImpl
