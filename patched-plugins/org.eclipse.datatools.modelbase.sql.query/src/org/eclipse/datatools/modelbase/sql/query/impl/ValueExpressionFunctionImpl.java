/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionFunctionImpl.java,v 1.5 2007/02/08 17:00:27 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Value Expression Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionFunctionImpl#isSpecialRegister <em>Special Register</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionFunctionImpl#isDistinct <em>Distinct</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionFunctionImpl#isColumnFunction <em>Column Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionFunctionImpl#getParameterList <em>Parameter List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionFunctionImpl#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueExpressionFunctionImpl extends ValueExpressionAtomicImpl implements ValueExpressionFunction {
	/**
     * The default value of the '{@link #isSpecialRegister() <em>Special Register</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSpecialRegister()
     * @generated
     * @ordered
     */
    protected static final boolean SPECIAL_REGISTER_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isSpecialRegister() <em>Special Register</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSpecialRegister()
     * @generated
     * @ordered
     */
    protected boolean specialRegister = SPECIAL_REGISTER_EDEFAULT;

	/**
     * The default value of the '{@link #isDistinct() <em>Distinct</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDistinct()
     * @generated
     * @ordered
     */
    protected static final boolean DISTINCT_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isDistinct() <em>Distinct</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDistinct()
     * @generated
     * @ordered
     */
    protected boolean distinct = DISTINCT_EDEFAULT;

	/**
     * The default value of the '{@link #isColumnFunction() <em>Column Function</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isColumnFunction()
     * @generated
     * @ordered
     */
    protected static final boolean COLUMN_FUNCTION_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isColumnFunction() <em>Column Function</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isColumnFunction()
     * @generated
     * @ordered
     */
    protected boolean columnFunction = COLUMN_FUNCTION_EDEFAULT;

	/**
     * The cached value of the '{@link #getParameterList() <em>Parameter List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParameterList()
     * @generated
     * @ordered
     */
    protected EList parameterList;

	/**
     * The cached value of the '{@link #getFunction() <em>Function</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFunction()
     * @generated
     * @ordered
     */
    protected Function function;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ValueExpressionFunctionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.VALUE_EXPRESSION_FUNCTION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSpecialRegister() {
        return specialRegister;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSpecialRegister(boolean newSpecialRegister) {
        boolean oldSpecialRegister = specialRegister;
        specialRegister = newSpecialRegister;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER, oldSpecialRegister, specialRegister));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDistinct() {
        return distinct;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDistinct(boolean newDistinct) {
        boolean oldDistinct = distinct;
        distinct = newDistinct;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DISTINCT, oldDistinct, distinct));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isColumnFunction() {
        return columnFunction;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setColumnFunction(boolean newColumnFunction) {
        boolean oldColumnFunction = columnFunction;
        columnFunction = newColumnFunction;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION, oldColumnFunction, columnFunction));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getParameterList() {
        if (parameterList == null) {
            parameterList = new EObjectContainmentWithInverseEList(QueryValueExpression.class, this, SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION);
        }
        return parameterList;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Function getFunction() {
        if (function != null && function.eIsProxy()) {
            InternalEObject oldFunction = (InternalEObject)function;
            function = (Function)eResolveProxy(oldFunction);
            if (function != oldFunction) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__FUNCTION, oldFunction, function));
            }
        }
        return function;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Function basicGetFunction() {
        return function;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFunction(Function newFunction) {
        Function oldFunction = function;
        function = newFunction;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__FUNCTION, oldFunction, function));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST:
                return ((InternalEList)getParameterList()).basicAdd(otherEnd, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST:
                return ((InternalEList)getParameterList()).basicRemove(otherEnd, msgs);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER:
                return isSpecialRegister() ? Boolean.TRUE : Boolean.FALSE;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DISTINCT:
                return isDistinct() ? Boolean.TRUE : Boolean.FALSE;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION:
                return isColumnFunction() ? Boolean.TRUE : Boolean.FALSE;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST:
                return getParameterList();
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__FUNCTION:
                if (resolve) return getFunction();
                return basicGetFunction();
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER:
                setSpecialRegister(((Boolean)newValue).booleanValue());
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DISTINCT:
                setDistinct(((Boolean)newValue).booleanValue());
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION:
                setColumnFunction(((Boolean)newValue).booleanValue());
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST:
                getParameterList().clear();
                getParameterList().addAll((Collection)newValue);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__FUNCTION:
                setFunction((Function)newValue);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER:
                setSpecialRegister(SPECIAL_REGISTER_EDEFAULT);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DISTINCT:
                setDistinct(DISTINCT_EDEFAULT);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION:
                setColumnFunction(COLUMN_FUNCTION_EDEFAULT);
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST:
                getParameterList().clear();
                return;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__FUNCTION:
                setFunction((Function)null);
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
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER:
                return specialRegister != SPECIAL_REGISTER_EDEFAULT;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DISTINCT:
                return distinct != DISTINCT_EDEFAULT;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION:
                return columnFunction != COLUMN_FUNCTION_EDEFAULT;
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST:
                return parameterList != null && !parameterList.isEmpty();
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__FUNCTION:
                return function != null;
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
        result.append(" (specialRegister: ");
        result.append(specialRegister);
        result.append(", distinct: ");
        result.append(distinct);
        result.append(", columnFunction: ");
        result.append(columnFunction);
        result.append(')');
        return result.toString();
    }

} //SQLValueExpressionFunctionImpl
