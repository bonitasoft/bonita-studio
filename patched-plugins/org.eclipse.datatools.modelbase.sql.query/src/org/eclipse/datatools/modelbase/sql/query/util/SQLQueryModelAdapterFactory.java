/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLQueryModelAdapterFactory.java,v 1.5 2010/02/25 01:57:25 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.util;

import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;


import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLDataChangeStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLDataStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;

import org.eclipse.datatools.modelbase.sql.query.*;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage
 * @generated
 */
public class SQLQueryModelAdapterFactory extends AdapterFactoryImpl {
	/**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static SQLQueryModelPackage modelPackage;

	/**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SQLQueryModelAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = SQLQueryModelPackage.eINSTANCE;
        }
    }

	/**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

	/**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SQLQueryModelSwitch modelSwitch =
		new SQLQueryModelSwitch() {
            public Object caseQueryStatement(QueryStatement object) {
                return createQueryStatementAdapter();
            }
            public Object caseQueryDeleteStatement(QueryDeleteStatement object) {
                return createQueryDeleteStatementAdapter();
            }
            public Object caseQueryInsertStatement(QueryInsertStatement object) {
                return createQueryInsertStatementAdapter();
            }
            public Object caseQuerySelectStatement(QuerySelectStatement object) {
                return createQuerySelectStatementAdapter();
            }
            public Object caseQueryUpdateStatement(QueryUpdateStatement object) {
                return createQueryUpdateStatementAdapter();
            }
            public Object caseUpdateAssignmentExpression(UpdateAssignmentExpression object) {
                return createUpdateAssignmentExpressionAdapter();
            }
            public Object caseCursorReference(CursorReference object) {
                return createCursorReferenceAdapter();
            }
            public Object caseQuerySearchCondition(QuerySearchCondition object) {
                return createQuerySearchConditionAdapter();
            }
            public Object caseQueryExpressionBody(QueryExpressionBody object) {
                return createQueryExpressionBodyAdapter();
            }
            public Object caseQueryValueExpression(QueryValueExpression object) {
                return createQueryValueExpressionAdapter();
            }
            public Object caseQueryExpressionRoot(QueryExpressionRoot object) {
                return createQueryExpressionRootAdapter();
            }
            public Object caseValuesRow(ValuesRow object) {
                return createValuesRowAdapter();
            }
            public Object caseQueryValues(QueryValues object) {
                return createQueryValuesAdapter();
            }
            public Object caseTableReference(TableReference object) {
                return createTableReferenceAdapter();
            }
            public Object caseTableExpression(TableExpression object) {
                return createTableExpressionAdapter();
            }
            public Object caseTableJoined(TableJoined object) {
                return createTableJoinedAdapter();
            }
            public Object caseWithTableSpecification(WithTableSpecification object) {
                return createWithTableSpecificationAdapter();
            }
            public Object casePredicate(Predicate object) {
                return createPredicateAdapter();
            }
            public Object caseSearchConditionCombined(SearchConditionCombined object) {
                return createSearchConditionCombinedAdapter();
            }
            public Object caseOrderByValueExpression(OrderByValueExpression object) {
                return createOrderByValueExpressionAdapter();
            }
            public Object caseQueryCombined(QueryCombined object) {
                return createQueryCombinedAdapter();
            }
            public Object caseQuerySelect(QuerySelect object) {
                return createQuerySelectAdapter();
            }
            public Object caseGroupingSpecification(GroupingSpecification object) {
                return createGroupingSpecificationAdapter();
            }
            public Object caseQueryResultSpecification(QueryResultSpecification object) {
                return createQueryResultSpecificationAdapter();
            }
            public Object caseResultTableAllColumns(ResultTableAllColumns object) {
                return createResultTableAllColumnsAdapter();
            }
            public Object caseResultColumn(ResultColumn object) {
                return createResultColumnAdapter();
            }
            public Object casePredicateBasic(PredicateBasic object) {
                return createPredicateBasicAdapter();
            }
            public Object casePredicateQuantified(PredicateQuantified object) {
                return createPredicateQuantifiedAdapter();
            }
            public Object casePredicateBetween(PredicateBetween object) {
                return createPredicateBetweenAdapter();
            }
            public Object casePredicateExists(PredicateExists object) {
                return createPredicateExistsAdapter();
            }
            public Object casePredicateIn(PredicateIn object) {
                return createPredicateInAdapter();
            }
            public Object casePredicateLike(PredicateLike object) {
                return createPredicateLikeAdapter();
            }
            public Object casePredicateIsNull(PredicateIsNull object) {
                return createPredicateIsNullAdapter();
            }
            public Object casePredicateQuantifiedValueSelect(PredicateQuantifiedValueSelect object) {
                return createPredicateQuantifiedValueSelectAdapter();
            }
            public Object casePredicateQuantifiedRowSelect(PredicateQuantifiedRowSelect object) {
                return createPredicateQuantifiedRowSelectAdapter();
            }
            public Object casePredicateInValueSelect(PredicateInValueSelect object) {
                return createPredicateInValueSelectAdapter();
            }
            public Object casePredicateInValueList(PredicateInValueList object) {
                return createPredicateInValueListAdapter();
            }
            public Object casePredicateInValueRowSelect(PredicateInValueRowSelect object) {
                return createPredicateInValueRowSelectAdapter();
            }
            public Object caseValueExpressionSimple(ValueExpressionSimple object) {
                return createValueExpressionSimpleAdapter();
            }
            public Object caseValueExpressionColumn(ValueExpressionColumn object) {
                return createValueExpressionColumnAdapter();
            }
            public Object caseValueExpressionVariable(ValueExpressionVariable object) {
                return createValueExpressionVariableAdapter();
            }
            public Object caseValueExpressionScalarSelect(ValueExpressionScalarSelect object) {
                return createValueExpressionScalarSelectAdapter();
            }
            public Object caseValueExpressionLabeledDuration(ValueExpressionLabeledDuration object) {
                return createValueExpressionLabeledDurationAdapter();
            }
            public Object caseValueExpressionCase(ValueExpressionCase object) {
                return createValueExpressionCaseAdapter();
            }
            public Object caseValueExpressionCast(ValueExpressionCast object) {
                return createValueExpressionCastAdapter();
            }
            public Object caseValueExpressionNullValue(ValueExpressionNullValue object) {
                return createValueExpressionNullValueAdapter();
            }
            public Object caseValueExpressionDefaultValue(ValueExpressionDefaultValue object) {
                return createValueExpressionDefaultValueAdapter();
            }
            public Object caseValueExpressionFunction(ValueExpressionFunction object) {
                return createValueExpressionFunctionAdapter();
            }
            public Object caseValueExpressionCombined(ValueExpressionCombined object) {
                return createValueExpressionCombinedAdapter();
            }
            public Object caseGroupingSets(GroupingSets object) {
                return createGroupingSetsAdapter();
            }
            public Object caseGrouping(Grouping object) {
                return createGroupingAdapter();
            }
            public Object caseGroupingSetsElement(GroupingSetsElement object) {
                return createGroupingSetsElementAdapter();
            }
            public Object caseGroupingSetsElementSublist(GroupingSetsElementSublist object) {
                return createGroupingSetsElementSublistAdapter();
            }
            public Object caseGroupingSetsElementExpression(GroupingSetsElementExpression object) {
                return createGroupingSetsElementExpressionAdapter();
            }
            public Object caseSuperGroup(SuperGroup object) {
                return createSuperGroupAdapter();
            }
            public Object caseGroupingExpression(GroupingExpression object) {
                return createGroupingExpressionAdapter();
            }
            public Object caseSuperGroupElement(SuperGroupElement object) {
                return createSuperGroupElementAdapter();
            }
            public Object caseSuperGroupElementSublist(SuperGroupElementSublist object) {
                return createSuperGroupElementSublistAdapter();
            }
            public Object caseSuperGroupElementExpression(SuperGroupElementExpression object) {
                return createSuperGroupElementExpressionAdapter();
            }
            public Object caseValueExpressionCaseSearch(ValueExpressionCaseSearch object) {
                return createValueExpressionCaseSearchAdapter();
            }
            public Object caseValueExpressionCaseSimple(ValueExpressionCaseSimple object) {
                return createValueExpressionCaseSimpleAdapter();
            }
            public Object caseValueExpressionCaseElse(ValueExpressionCaseElse object) {
                return createValueExpressionCaseElseAdapter();
            }
            public Object caseValueExpressionCaseSearchContent(ValueExpressionCaseSearchContent object) {
                return createValueExpressionCaseSearchContentAdapter();
            }
            public Object caseValueExpressionCaseSimpleContent(ValueExpressionCaseSimpleContent object) {
                return createValueExpressionCaseSimpleContentAdapter();
            }
            public Object caseTableInDatabase(TableInDatabase object) {
                return createTableInDatabaseAdapter();
            }
            public Object caseTableFunction(TableFunction object) {
                return createTableFunctionAdapter();
            }
            public Object caseSQLQueryObject(SQLQueryObject object) {
                return createSQLQueryObjectAdapter();
            }
            public Object caseQueryChangeStatement(QueryChangeStatement object) {
                return createQueryChangeStatementAdapter();
            }
            public Object caseColumnName(ColumnName object) {
                return createColumnNameAdapter();
            }
            public Object caseTableNested(TableNested object) {
                return createTableNestedAdapter();
            }
            public Object caseQueryMergeStatement(QueryMergeStatement object) {
                return createQueryMergeStatementAdapter();
            }
            public Object caseSearchConditionNested(SearchConditionNested object) {
                return createSearchConditionNestedAdapter();
            }
            public Object caseValueExpressionNested(ValueExpressionNested object) {
                return createValueExpressionNestedAdapter();
            }
            public Object caseValueExpressionAtomic(ValueExpressionAtomic object) {
                return createValueExpressionAtomicAdapter();
            }
            public Object caseOrderBySpecification(OrderBySpecification object) {
                return createOrderBySpecificationAdapter();
            }
            public Object caseOrderByOrdinal(OrderByOrdinal object) {
                return createOrderByOrdinalAdapter();
            }
            public Object caseTableCorrelation(TableCorrelation object) {
                return createTableCorrelationAdapter();
            }
            public Object caseUpdateSource(UpdateSource object) {
                return createUpdateSourceAdapter();
            }
            public Object caseUpdateSourceExprList(UpdateSourceExprList object) {
                return createUpdateSourceExprListAdapter();
            }
            public Object caseUpdateSourceQuery(UpdateSourceQuery object) {
                return createUpdateSourceQueryAdapter();
            }
            public Object caseOrderByResultColumn(OrderByResultColumn object) {
                return createOrderByResultColumnAdapter();
            }
            public Object caseWithTableReference(WithTableReference object) {
                return createWithTableReferenceAdapter();
            }
            public Object caseQueryNested(QueryNested object) {
                return createQueryNestedAdapter();
            }
            public Object caseValueExpressionRow(ValueExpressionRow object) {
                return createValueExpressionRowAdapter();
            }
            public Object caseMergeTargetTable(MergeTargetTable object) {
                return createMergeTargetTableAdapter();
            }
            public Object caseMergeSourceTable(MergeSourceTable object) {
                return createMergeSourceTableAdapter();
            }
            public Object caseMergeOnCondition(MergeOnCondition object) {
                return createMergeOnConditionAdapter();
            }
            public Object caseMergeUpdateSpecification(MergeUpdateSpecification object) {
                return createMergeUpdateSpecificationAdapter();
            }
            public Object caseMergeInsertSpecification(MergeInsertSpecification object) {
                return createMergeInsertSpecificationAdapter();
            }
            public Object caseMergeOperationSpecification(MergeOperationSpecification object) {
                return createMergeOperationSpecificationAdapter();
            }
            public Object caseUpdateOfColumn(UpdateOfColumn object) {
                return createUpdateOfColumnAdapter();
            }
            public Object caseUpdatabilityExpression(UpdatabilityExpression object) {
                return createUpdatabilityExpressionAdapter();
            }
            public Object caseCallStatement(CallStatement object) {
                return createCallStatementAdapter();
            }
            public Object caseProcedureReference(ProcedureReference object) {
                return createProcedureReferenceAdapter();
            }
            public Object caseTableQueryLateral(TableQueryLateral object) {
                return createTableQueryLateralAdapter();
            }
            public Object caseEModelElement(EModelElement object) {
                return createEModelElementAdapter();
            }
            public Object caseENamedElement(ENamedElement object) {
                return createENamedElementAdapter();
            }
            public Object caseSQLObject(SQLObject object) {
                return createSQLObjectAdapter();
            }
            public Object caseSQLStatement(SQLStatement object) {
                return createSQLStatementAdapter();
            }
            public Object caseSQLDataStatement(SQLDataStatement object) {
                return createSQLDataStatementAdapter();
            }
            public Object caseSQLDataChangeStatement(SQLDataChangeStatement object) {
                return createSQLDataChangeStatementAdapter();
            }
            public Object caseSearchCondition(SearchCondition object) {
                return createSearchConditionAdapter();
            }
            public Object caseValueExpression(ValueExpression object) {
                return createValueExpressionAdapter();
            }
            public Object caseQueryExpression(QueryExpression object) {
                return createQueryExpressionAdapter();
            }
            public Object caseSQLControlStatement(SQLControlStatement object) {
                return createSQLControlStatementAdapter();
            }
            public Object defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

	/**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    public Adapter createAdapter(Notifier target) {
        return (Adapter)modelSwitch.doSwitch((EObject)target);
    }


	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryStatement <em>Query Statement</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryStatement
     * @generated
     */
  public Adapter createQueryStatementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement <em>Query Delete Statement</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement
     * @generated
     */
  public Adapter createQueryDeleteStatementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement <em>Query Insert Statement</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement
     * @generated
     */
  public Adapter createQueryInsertStatementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement <em>Query Select Statement</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement
     * @generated
     */
  public Adapter createQuerySelectStatementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement <em>Query Update Statement</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement
     * @generated
     */
  public Adapter createQueryUpdateStatementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression <em>Update Assignment Expression</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression
     * @generated
     */
  public Adapter createUpdateAssignmentExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.CursorReference <em>Cursor Reference</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.CursorReference
     * @generated
     */
  public Adapter createCursorReferenceAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition <em>Query Search Condition</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition
     * @generated
     */
  public Adapter createQuerySearchConditionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody <em>Query Expression Body</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody
     * @generated
     */
  public Adapter createQueryExpressionBodyAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression <em>Query Value Expression</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression
     * @generated
     */
  public Adapter createQueryValueExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot <em>Query Expression Root</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot
     * @generated
     */
  public Adapter createQueryExpressionRootAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow <em>Values Row</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValuesRow
     * @generated
     */
  public Adapter createValuesRowAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryValues <em>Query Values</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValues
     * @generated
     */
  public Adapter createQueryValuesAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.TableReference <em>Table Reference</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference
     * @generated
     */
  public Adapter createTableReferenceAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression <em>Table Expression</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.TableExpression
     * @generated
     */
  public Adapter createTableExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined <em>Table Joined</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoined
     * @generated
     */
  public Adapter createTableJoinedAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification <em>With Table Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableSpecification
     * @generated
     */
    public Adapter createWithTableSpecificationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.Predicate <em>Predicate</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.Predicate
     * @generated
     */
  public Adapter createPredicateAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined <em>Search Condition Combined</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined
     * @generated
     */
  public Adapter createSearchConditionCombinedAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression <em>Order By Value Expression</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression
     * @generated
     */
  public Adapter createOrderByValueExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined <em>Query Combined</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombined
     * @generated
     */
  public Adapter createQueryCombinedAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect <em>Query Select</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect
     * @generated
     */
  public Adapter createQuerySelectAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSpecification <em>Grouping Specification</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSpecification
     * @generated
     */
  public Adapter createGroupingSpecificationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification <em>Query Result Specification</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification
     * @generated
     */
  public Adapter createQueryResultSpecificationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns <em>Result Table All Columns</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns
     * @generated
     */
  public Adapter createResultTableAllColumnsAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ResultColumn <em>Result Column</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ResultColumn
     * @generated
     */
  public Adapter createResultColumnAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic <em>Predicate Basic</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBasic
     * @generated
     */
  public Adapter createPredicateBasicAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantified <em>Predicate Quantified</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantified
     * @generated
     */
  public Adapter createPredicateQuantifiedAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween <em>Predicate Between</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBetween
     * @generated
     */
  public Adapter createPredicateBetweenAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateExists <em>Predicate Exists</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateExists
     * @generated
     */
  public Adapter createPredicateExistsAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateIn <em>Predicate In</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateIn
     * @generated
     */
  public Adapter createPredicateInAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike <em>Predicate Like</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateLike
     * @generated
     */
  public Adapter createPredicateLikeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateIsNull <em>Predicate Is Null</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateIsNull
     * @generated
     */
  public Adapter createPredicateIsNullAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect <em>Predicate Quantified Value Select</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect
     * @generated
     */
  public Adapter createPredicateQuantifiedValueSelectAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect <em>Predicate Quantified Row Select</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect
     * @generated
     */
  public Adapter createPredicateQuantifiedRowSelectAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect <em>Predicate In Value Select</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect
     * @generated
     */
  public Adapter createPredicateInValueSelectAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueList <em>Predicate In Value List</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueList
     * @generated
     */
  public Adapter createPredicateInValueListAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect <em>Predicate In Value Row Select</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect
     * @generated
     */
  public Adapter createPredicateInValueRowSelectAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple <em>Value Expression Simple</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple
     * @generated
     */
  public Adapter createValueExpressionSimpleAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn <em>Value Expression Column</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn
     * @generated
     */
  public Adapter createValueExpressionColumnAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable <em>Value Expression Variable</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable
     * @generated
     */
  public Adapter createValueExpressionVariableAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect <em>Value Expression Scalar Select</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect
     * @generated
     */
  public Adapter createValueExpressionScalarSelectAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration <em>Value Expression Labeled Duration</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration
     * @generated
     */
  public Adapter createValueExpressionLabeledDurationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase <em>Value Expression Case</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase
     * @generated
     */
  public Adapter createValueExpressionCaseAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast <em>Value Expression Cast</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast
     * @generated
     */
  public Adapter createValueExpressionCastAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue <em>Value Expression Null Value</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue
     * @generated
     */
  public Adapter createValueExpressionNullValueAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionDefaultValue <em>Value Expression Default Value</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionDefaultValue
     * @generated
     */
  public Adapter createValueExpressionDefaultValueAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction <em>Value Expression Function</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction
     * @generated
     */
  public Adapter createValueExpressionFunctionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined <em>Value Expression Combined</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined
     * @generated
     */
  public Adapter createValueExpressionCombinedAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSets <em>Grouping Sets</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSets
     * @generated
     */
  public Adapter createGroupingSetsAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.Grouping <em>Grouping</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.Grouping
     * @generated
     */
  public Adapter createGroupingAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement <em>Grouping Sets Element</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement
     * @generated
     */
  public Adapter createGroupingSetsElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist <em>Grouping Sets Element Sublist</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist
     * @generated
     */
  public Adapter createGroupingSetsElementSublistAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression <em>Grouping Sets Element Expression</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression
     * @generated
     */
  public Adapter createGroupingSetsElementExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroup <em>Super Group</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroup
     * @generated
     */
  public Adapter createSuperGroupAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingExpression <em>Grouping Expression</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingExpression
     * @generated
     */
  public Adapter createGroupingExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElement <em>Super Group Element</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElement
     * @generated
     */
  public Adapter createSuperGroupElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist <em>Super Group Element Sublist</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist
     * @generated
     */
  public Adapter createSuperGroupElementSublistAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression <em>Super Group Element Expression</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression
     * @generated
     */
  public Adapter createSuperGroupElementExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch <em>Value Expression Case Search</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch
     * @generated
     */
  public Adapter createValueExpressionCaseSearchAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple <em>Value Expression Case Simple</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple
     * @generated
     */
  public Adapter createValueExpressionCaseSimpleAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse <em>Value Expression Case Else</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse
     * @generated
     */
  public Adapter createValueExpressionCaseElseAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent <em>Value Expression Case Search Content</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent
     * @generated
     */
  public Adapter createValueExpressionCaseSearchContentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent <em>Value Expression Case Simple Content</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent
     * @generated
     */
  public Adapter createValueExpressionCaseSimpleContentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase <em>Table In Database</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.TableInDatabase
     * @generated
     */
  public Adapter createTableInDatabaseAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.TableFunction <em>Table Function</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.TableFunction
     * @generated
     */
  public Adapter createTableFunctionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.SQLQueryObject <em>SQL Query Object</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryObject
     * @generated
     */
    public Adapter createSQLQueryObjectAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryChangeStatement <em>Query Change Statement</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryChangeStatement
     * @generated
     */
  public Adapter createQueryChangeStatementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ColumnName <em>Column Name</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ColumnName
     * @generated
     */
  public Adapter createColumnNameAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.TableNested <em>Table Nested</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.TableNested
     * @generated
     */
  public Adapter createTableNestedAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement <em>Query Merge Statement</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement
     * @generated
     */
  public Adapter createQueryMergeStatementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionNested <em>Search Condition Nested</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionNested
     * @generated
     */
  public Adapter createSearchConditionNestedAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested <em>Value Expression Nested</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested
     * @generated
     */
  public Adapter createValueExpressionNestedAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic <em>Value Expression Atomic</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic
     * @generated
     */
  public Adapter createValueExpressionAtomicAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification <em>Order By Specification</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderBySpecification
     * @generated
     */
  public Adapter createOrderBySpecificationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal <em>Order By Ordinal</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal
     * @generated
     */
  public Adapter createOrderByOrdinalAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.TableCorrelation <em>Table Correlation</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.TableCorrelation
     * @generated
     */
  public Adapter createTableCorrelationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSource <em>Update Source</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSource
     * @generated
     */
  public Adapter createUpdateSourceAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList <em>Update Source Expr List</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList
     * @generated
     */
  public Adapter createUpdateSourceExprListAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery <em>Update Source Query</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery
     * @generated
     */
  public Adapter createUpdateSourceQueryAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn <em>Order By Result Column</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn
     * @generated
     */
    public Adapter createOrderByResultColumnAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.WithTableReference <em>With Table Reference</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableReference
     * @generated
     */
    public Adapter createWithTableReferenceAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.QueryNested <em>Query Nested</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryNested
     * @generated
     */
    public Adapter createQueryNestedAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow <em>Value Expression Row</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow
     * @generated
     */
    public Adapter createValueExpressionRowAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.MergeTargetTable <em>Merge Target Table</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeTargetTable
     * @generated
     */
    public Adapter createMergeTargetTableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable <em>Merge Source Table</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeSourceTable
     * @generated
     */
    public Adapter createMergeSourceTableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.MergeOnCondition <em>Merge On Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeOnCondition
     * @generated
     */
    public Adapter createMergeOnConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification <em>Merge Update Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification
     * @generated
     */
    public Adapter createMergeUpdateSpecificationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification <em>Merge Insert Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification
     * @generated
     */
    public Adapter createMergeInsertSpecificationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification <em>Merge Operation Specification</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification
     * @generated
     */
    public Adapter createMergeOperationSpecificationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn <em>Update Of Column</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn
     * @generated
     */
    public Adapter createUpdateOfColumnAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression <em>Updatability Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression
     * @generated
     */
    public Adapter createUpdatabilityExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.CallStatement <em>Call Statement</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.CallStatement
     * @generated
     */
    public Adapter createCallStatementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.ProcedureReference <em>Procedure Reference</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.ProcedureReference
     * @generated
     */
    public Adapter createProcedureReferenceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.query.TableQueryLateral <em>Table Query Lateral</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.query.TableQueryLateral
     * @generated
     */
    public Adapter createTableQueryLateralAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.emf.ecore.EModelElement
     * @generated
     */
    public Adapter createEModelElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.ENamedElement <em>ENamed Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.emf.ecore.ENamedElement
     * @generated
     */
    public Adapter createENamedElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject <em>SQL Object</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject
     * @generated
     */
    public Adapter createSQLObjectAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLStatement <em>SQL Statement</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.statements.SQLStatement
     * @generated
     */
    public Adapter createSQLStatementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDataStatement <em>SQL Data Statement</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.statements.SQLDataStatement
     * @generated
     */
    public Adapter createSQLDataStatementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDataChangeStatement <em>SQL Data Change Statement</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.statements.SQLDataChangeStatement
     * @generated
     */
    public Adapter createSQLDataChangeStatementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.expressions.SearchCondition <em>Search Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.expressions.SearchCondition
     * @generated
     */
    public Adapter createSearchConditionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.expressions.ValueExpression <em>Value Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.expressions.ValueExpression
     * @generated
     */
    public Adapter createValueExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.expressions.QueryExpression <em>Query Expression</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.expressions.QueryExpression
     * @generated
     */
    public Adapter createQueryExpressionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement <em>SQL Control Statement</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement
     * @generated
     */
    public Adapter createSQLControlStatementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //SQLQueryAdapterFactory
