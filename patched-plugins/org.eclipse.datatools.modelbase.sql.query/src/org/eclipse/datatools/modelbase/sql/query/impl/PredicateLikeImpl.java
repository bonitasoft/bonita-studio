/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateLikeImpl.java,v 1.5 2007/02/08 17:00:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Predicate Like</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl#isNotLike <em>Not Like</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl#getPatternValueExpr <em>Pattern Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl#getMatchingValueExpr <em>Matching Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl#getEscapeValueExpr <em>Escape Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredicateLikeImpl extends PredicateImpl implements PredicateLike {
	/**
     * The default value of the '{@link #isNotLike() <em>Not Like</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNotLike()
     * @generated
     * @ordered
     */
    protected static final boolean NOT_LIKE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isNotLike() <em>Not Like</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNotLike()
     * @generated
     * @ordered
     */
    protected boolean notLike = NOT_LIKE_EDEFAULT;

	/**
     * The cached value of the '{@link #getPatternValueExpr() <em>Pattern Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPatternValueExpr()
     * @generated
     * @ordered
     */
    protected QueryValueExpression patternValueExpr;

	/**
     * The cached value of the '{@link #getMatchingValueExpr() <em>Matching Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMatchingValueExpr()
     * @generated
     * @ordered
     */
    protected QueryValueExpression matchingValueExpr;

	/**
     * The cached value of the '{@link #getEscapeValueExpr() <em>Escape Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEscapeValueExpr()
     * @generated
     * @ordered
     */
    protected QueryValueExpression escapeValueExpr;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PredicateLikeImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.PREDICATE_LIKE;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isNotLike() {
        return notLike;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNotLike(boolean newNotLike) {
        boolean oldNotLike = notLike;
        notLike = newNotLike;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_LIKE__NOT_LIKE, oldNotLike, notLike));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValueExpression getPatternValueExpr() {
        return patternValueExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPatternValueExpr(QueryValueExpression newPatternValueExpr, NotificationChain msgs) {
        QueryValueExpression oldPatternValueExpr = patternValueExpr;
        patternValueExpr = newPatternValueExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR, oldPatternValueExpr, newPatternValueExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPatternValueExpr(QueryValueExpression newPatternValueExpr) {
        if (newPatternValueExpr != patternValueExpr) {
            NotificationChain msgs = null;
            if (patternValueExpr != null)
                msgs = ((InternalEObject)patternValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN, QueryValueExpression.class, msgs);
            if (newPatternValueExpr != null)
                msgs = ((InternalEObject)newPatternValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN, QueryValueExpression.class, msgs);
            msgs = basicSetPatternValueExpr(newPatternValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR, newPatternValueExpr, newPatternValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValueExpression getMatchingValueExpr() {
        return matchingValueExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMatchingValueExpr(QueryValueExpression newMatchingValueExpr, NotificationChain msgs) {
        QueryValueExpression oldMatchingValueExpr = matchingValueExpr;
        matchingValueExpr = newMatchingValueExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR, oldMatchingValueExpr, newMatchingValueExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMatchingValueExpr(QueryValueExpression newMatchingValueExpr) {
        if (newMatchingValueExpr != matchingValueExpr) {
            NotificationChain msgs = null;
            if (matchingValueExpr != null)
                msgs = ((InternalEObject)matchingValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING, QueryValueExpression.class, msgs);
            if (newMatchingValueExpr != null)
                msgs = ((InternalEObject)newMatchingValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING, QueryValueExpression.class, msgs);
            msgs = basicSetMatchingValueExpr(newMatchingValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR, newMatchingValueExpr, newMatchingValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryValueExpression getEscapeValueExpr() {
        return escapeValueExpr;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEscapeValueExpr(QueryValueExpression newEscapeValueExpr, NotificationChain msgs) {
        QueryValueExpression oldEscapeValueExpr = escapeValueExpr;
        escapeValueExpr = newEscapeValueExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR, oldEscapeValueExpr, newEscapeValueExpr);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEscapeValueExpr(QueryValueExpression newEscapeValueExpr) {
        if (newEscapeValueExpr != escapeValueExpr) {
            NotificationChain msgs = null;
            if (escapeValueExpr != null)
                msgs = ((InternalEObject)escapeValueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE, QueryValueExpression.class, msgs);
            if (newEscapeValueExpr != null)
                msgs = ((InternalEObject)newEscapeValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE, QueryValueExpression.class, msgs);
            msgs = basicSetEscapeValueExpr(newEscapeValueExpr, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR, newEscapeValueExpr, newEscapeValueExpr));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
                if (patternValueExpr != null)
                    msgs = ((InternalEObject)patternValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR, null, msgs);
                return basicSetPatternValueExpr((QueryValueExpression)otherEnd, msgs);
            case SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
                if (matchingValueExpr != null)
                    msgs = ((InternalEObject)matchingValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR, null, msgs);
                return basicSetMatchingValueExpr((QueryValueExpression)otherEnd, msgs);
            case SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
                if (escapeValueExpr != null)
                    msgs = ((InternalEObject)escapeValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR, null, msgs);
                return basicSetEscapeValueExpr((QueryValueExpression)otherEnd, msgs);
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
            case SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
                return basicSetPatternValueExpr(null, msgs);
            case SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
                return basicSetMatchingValueExpr(null, msgs);
            case SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
                return basicSetEscapeValueExpr(null, msgs);
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
            case SQLQueryModelPackage.PREDICATE_LIKE__NOT_LIKE:
                return isNotLike() ? Boolean.TRUE : Boolean.FALSE;
            case SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
                return getPatternValueExpr();
            case SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
                return getMatchingValueExpr();
            case SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
                return getEscapeValueExpr();
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
            case SQLQueryModelPackage.PREDICATE_LIKE__NOT_LIKE:
                setNotLike(((Boolean)newValue).booleanValue());
                return;
            case SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
                setPatternValueExpr((QueryValueExpression)newValue);
                return;
            case SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
                setMatchingValueExpr((QueryValueExpression)newValue);
                return;
            case SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
                setEscapeValueExpr((QueryValueExpression)newValue);
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
            case SQLQueryModelPackage.PREDICATE_LIKE__NOT_LIKE:
                setNotLike(NOT_LIKE_EDEFAULT);
                return;
            case SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
                setPatternValueExpr((QueryValueExpression)null);
                return;
            case SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
                setMatchingValueExpr((QueryValueExpression)null);
                return;
            case SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
                setEscapeValueExpr((QueryValueExpression)null);
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
            case SQLQueryModelPackage.PREDICATE_LIKE__NOT_LIKE:
                return notLike != NOT_LIKE_EDEFAULT;
            case SQLQueryModelPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
                return patternValueExpr != null;
            case SQLQueryModelPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
                return matchingValueExpr != null;
            case SQLQueryModelPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
                return escapeValueExpr != null;
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
        result.append(" (notLike: ");
        result.append(notLike);
        result.append(')');
        return result.toString();
    }

} //SQLPredicateLikeImpl
