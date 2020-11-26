/**
 * <copyright>
 * </copyright>
 *
 * $Id: QuerySearchConditionImpl.java,v 1.6 2008/01/31 02:57:15 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import org.eclipse.datatools.modelbase.sql.query.MergeOnCondition;
import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Search Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#isNegatedCondition <em>Negated Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getDeleteStatement <em>Delete Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getTableJoined <em>Table Joined</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getCombinedLeft <em>Combined Left</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getCombinedRight <em>Combined Right</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getQuerySelectHaving <em>Query Select Having</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getQuerySelectWhere <em>Query Select Where</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getNest <em>Nest</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl#getMergeOnCondition <em>Merge On Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class QuerySearchConditionImpl extends SQLQueryObjectImpl implements QuerySearchCondition {
	/**
     * The default value of the '{@link #isNegatedCondition() <em>Negated Condition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNegatedCondition()
     * @generated
     * @ordered
     */
    protected static final boolean NEGATED_CONDITION_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isNegatedCondition() <em>Negated Condition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNegatedCondition()
     * @generated
     * @ordered
     */
    protected boolean negatedCondition = NEGATED_CONDITION_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected QuerySearchConditionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLQueryModelPackage.Literals.QUERY_SEARCH_CONDITION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isNegatedCondition() {
        return negatedCondition;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNegatedCondition(boolean newNegatedCondition) {
        boolean oldNegatedCondition = negatedCondition;
        negatedCondition = newNegatedCondition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEGATED_CONDITION, oldNegatedCondition, negatedCondition));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryUpdateStatement getUpdateStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT) return null;
        return (QueryUpdateStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUpdateStatement(QueryUpdateStatement newUpdateStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUpdateStatement, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUpdateStatement(QueryUpdateStatement newUpdateStatement) {
        if (newUpdateStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT && newUpdateStatement != null)) {
            if (EcoreUtil.isAncestor(this, newUpdateStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUpdateStatement != null)
                msgs = ((InternalEObject)newUpdateStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__WHERE_CLAUSE, QueryUpdateStatement.class, msgs);
            msgs = basicSetUpdateStatement(newUpdateStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT, newUpdateStatement, newUpdateStatement));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryDeleteStatement getDeleteStatement() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT) return null;
        return (QueryDeleteStatement)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDeleteStatement(QueryDeleteStatement newDeleteStatement, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newDeleteStatement, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDeleteStatement(QueryDeleteStatement newDeleteStatement) {
        if (newDeleteStatement != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT && newDeleteStatement != null)) {
            if (EcoreUtil.isAncestor(this, newDeleteStatement))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newDeleteStatement != null)
                msgs = ((InternalEObject)newDeleteStatement).eInverseAdd(this, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, QueryDeleteStatement.class, msgs);
            msgs = basicSetDeleteStatement(newDeleteStatement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT, newDeleteStatement, newDeleteStatement));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableJoined getTableJoined() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED) return null;
        return (TableJoined)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTableJoined(TableJoined newTableJoined, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTableJoined, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableJoined(TableJoined newTableJoined) {
        if (newTableJoined != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED && newTableJoined != null)) {
            if (EcoreUtil.isAncestor(this, newTableJoined))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTableJoined != null)
                msgs = ((InternalEObject)newTableJoined).eInverseAdd(this, SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION, TableJoined.class, msgs);
            msgs = basicSetTableJoined(newTableJoined, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED, newTableJoined, newTableJoined));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SearchConditionCombined getCombinedLeft() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT) return null;
        return (SearchConditionCombined)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetCombinedLeft(SearchConditionCombined newCombinedLeft, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newCombinedLeft, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCombinedLeft(SearchConditionCombined newCombinedLeft) {
        if (newCombinedLeft != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT && newCombinedLeft != null)) {
            if (EcoreUtil.isAncestor(this, newCombinedLeft))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newCombinedLeft != null)
                msgs = ((InternalEObject)newCombinedLeft).eInverseAdd(this, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, SearchConditionCombined.class, msgs);
            msgs = basicSetCombinedLeft(newCombinedLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT, newCombinedLeft, newCombinedLeft));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SearchConditionCombined getCombinedRight() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT) return null;
        return (SearchConditionCombined)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetCombinedRight(SearchConditionCombined newCombinedRight, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newCombinedRight, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCombinedRight(SearchConditionCombined newCombinedRight) {
        if (newCombinedRight != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT && newCombinedRight != null)) {
            if (EcoreUtil.isAncestor(this, newCombinedRight))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newCombinedRight != null)
                msgs = ((InternalEObject)newCombinedRight).eInverseAdd(this, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, SearchConditionCombined.class, msgs);
            msgs = basicSetCombinedRight(newCombinedRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT, newCombinedRight, newCombinedRight));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySelect getQuerySelectHaving() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING) return null;
        return (QuerySelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetQuerySelectHaving(QuerySelect newQuerySelectHaving, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQuerySelectHaving, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuerySelectHaving(QuerySelect newQuerySelectHaving) {
        if (newQuerySelectHaving != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING && newQuerySelectHaving != null)) {
            if (EcoreUtil.isAncestor(this, newQuerySelectHaving))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQuerySelectHaving != null)
                msgs = ((InternalEObject)newQuerySelectHaving).eInverseAdd(this, SQLQueryModelPackage.QUERY_SELECT__HAVING_CLAUSE, QuerySelect.class, msgs);
            msgs = basicSetQuerySelectHaving(newQuerySelectHaving, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING, newQuerySelectHaving, newQuerySelectHaving));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QuerySelect getQuerySelectWhere() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE) return null;
        return (QuerySelect)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetQuerySelectWhere(QuerySelect newQuerySelectWhere, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newQuerySelectWhere, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setQuerySelectWhere(QuerySelect newQuerySelectWhere) {
        if (newQuerySelectWhere != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE && newQuerySelectWhere != null)) {
            if (EcoreUtil.isAncestor(this, newQuerySelectWhere))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newQuerySelectWhere != null)
                msgs = ((InternalEObject)newQuerySelectWhere).eInverseAdd(this, SQLQueryModelPackage.QUERY_SELECT__WHERE_CLAUSE, QuerySelect.class, msgs);
            msgs = basicSetQuerySelectWhere(newQuerySelectWhere, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE, newQuerySelectWhere, newQuerySelectWhere));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionCaseSearchContent getValueExprCaseSearchContent() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT) return null;
        return (ValueExpressionCaseSearchContent)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueExprCaseSearchContent(ValueExpressionCaseSearchContent newValueExprCaseSearchContent, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSearchContent, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueExprCaseSearchContent(ValueExpressionCaseSearchContent newValueExprCaseSearchContent) {
        if (newValueExprCaseSearchContent != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT && newValueExprCaseSearchContent != null)) {
            if (EcoreUtil.isAncestor(this, newValueExprCaseSearchContent))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newValueExprCaseSearchContent != null)
                msgs = ((InternalEObject)newValueExprCaseSearchContent).eInverseAdd(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, ValueExpressionCaseSearchContent.class, msgs);
            msgs = basicSetValueExprCaseSearchContent(newValueExprCaseSearchContent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT, newValueExprCaseSearchContent, newValueExprCaseSearchContent));
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public SearchConditionNested getNest() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST) return null;
        return (SearchConditionNested)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetNest(SearchConditionNested newNest, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newNest, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setNest(SearchConditionNested newNest) {
        if (newNest != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST && newNest != null)) {
            if (EcoreUtil.isAncestor(this, newNest))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newNest != null)
                msgs = ((InternalEObject)newNest).eInverseAdd(this, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, SearchConditionNested.class, msgs);
            msgs = basicSetNest(newNest, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST, newNest, newNest));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeOnCondition getMergeOnCondition() {
        if (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION) return null;
        return (MergeOnCondition)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMergeOnCondition(MergeOnCondition newMergeOnCondition, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newMergeOnCondition, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMergeOnCondition(MergeOnCondition newMergeOnCondition) {
        if (newMergeOnCondition != eInternalContainer() || (eContainerFeatureID() != SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION && newMergeOnCondition != null)) {
            if (EcoreUtil.isAncestor(this, newMergeOnCondition))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newMergeOnCondition != null)
                msgs = ((InternalEObject)newMergeOnCondition).eInverseAdd(this, SQLQueryModelPackage.MERGE_ON_CONDITION__SEARCH_CONDITION, MergeOnCondition.class, msgs);
            msgs = basicSetMergeOnCondition(newMergeOnCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION, newMergeOnCondition, newMergeOnCondition));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUpdateStatement((QueryUpdateStatement)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetDeleteStatement((QueryDeleteStatement)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTableJoined((TableJoined)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetCombinedLeft((SearchConditionCombined)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetCombinedRight((SearchConditionCombined)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQuerySelectHaving((QuerySelect)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetQuerySelectWhere((QuerySelect)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetValueExprCaseSearchContent((ValueExpressionCaseSearchContent)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetNest((SearchConditionNested)otherEnd, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetMergeOnCondition((MergeOnCondition)otherEnd, msgs);
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
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
                return basicSetUpdateStatement(null, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
                return basicSetDeleteStatement(null, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
                return basicSetTableJoined(null, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
                return basicSetCombinedLeft(null, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
                return basicSetCombinedRight(null, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
                return basicSetQuerySelectHaving(null, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
                return basicSetQuerySelectWhere(null, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                return basicSetValueExprCaseSearchContent(null, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST:
                return basicSetNest(null, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION:
                return basicSetMergeOnCondition(null, msgs);
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
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__WHERE_CLAUSE, QueryUpdateStatement.class, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, QueryDeleteStatement.class, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION, TableJoined.class, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, SearchConditionCombined.class, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, SearchConditionCombined.class, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__HAVING_CLAUSE, QuerySelect.class, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__WHERE_CLAUSE, QuerySelect.class, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, ValueExpressionCaseSearchContent.class, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, SearchConditionNested.class, msgs);
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION:
                return eInternalContainer().eInverseRemove(this, SQLQueryModelPackage.MERGE_ON_CONDITION__SEARCH_CONDITION, MergeOnCondition.class, msgs);
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
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEGATED_CONDITION:
                return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
                return getUpdateStatement();
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
                return getDeleteStatement();
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
                return getTableJoined();
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
                return getCombinedLeft();
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
                return getCombinedRight();
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
                return getQuerySelectHaving();
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
                return getQuerySelectWhere();
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                return getValueExprCaseSearchContent();
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST:
                return getNest();
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION:
                return getMergeOnCondition();
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
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEGATED_CONDITION:
                setNegatedCondition(((Boolean)newValue).booleanValue());
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
                setUpdateStatement((QueryUpdateStatement)newValue);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
                setDeleteStatement((QueryDeleteStatement)newValue);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
                setTableJoined((TableJoined)newValue);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
                setCombinedLeft((SearchConditionCombined)newValue);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
                setCombinedRight((SearchConditionCombined)newValue);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
                setQuerySelectHaving((QuerySelect)newValue);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
                setQuerySelectWhere((QuerySelect)newValue);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST:
                setNest((SearchConditionNested)newValue);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION:
                setMergeOnCondition((MergeOnCondition)newValue);
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
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEGATED_CONDITION:
                setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
                setUpdateStatement((QueryUpdateStatement)null);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
                setDeleteStatement((QueryDeleteStatement)null);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
                setTableJoined((TableJoined)null);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
                setCombinedLeft((SearchConditionCombined)null);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
                setCombinedRight((SearchConditionCombined)null);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
                setQuerySelectHaving((QuerySelect)null);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
                setQuerySelectWhere((QuerySelect)null);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST:
                setNest((SearchConditionNested)null);
                return;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION:
                setMergeOnCondition((MergeOnCondition)null);
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
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEGATED_CONDITION:
                return negatedCondition != NEGATED_CONDITION_EDEFAULT;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__UPDATE_STATEMENT:
                return getUpdateStatement() != null;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__DELETE_STATEMENT:
                return getDeleteStatement() != null;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__TABLE_JOINED:
                return getTableJoined() != null;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_LEFT:
                return getCombinedLeft() != null;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__COMBINED_RIGHT:
                return getCombinedRight() != null;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING:
                return getQuerySelectHaving() != null;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE:
                return getQuerySelectWhere() != null;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT:
                return getValueExprCaseSearchContent() != null;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__NEST:
                return getNest() != null;
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION:
                return getMergeOnCondition() != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getSQL() {
        return super.getSQL();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (negatedCondition: ");
        result.append(negatedCondition);
        result.append(')');
        return result.toString();
    }

} //SQLSearchConditionImpl
