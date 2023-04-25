/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLQueryModelSwitch.java,v 1.5 2010/02/25 01:57:25 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.util;


import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLDataChangeStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLDataStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage
 * @generated
 */
public class SQLQueryModelSwitch {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static SQLQueryModelPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SQLQueryModelSwitch() {
        if (modelPackage == null) {
            modelPackage = SQLQueryModelPackage.eINSTANCE;
        }
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public Object doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch((EClass)eSuperTypes.get(0), theEObject);
        }
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case SQLQueryModelPackage.QUERY_STATEMENT: {
                QueryStatement queryStatement = (QueryStatement)theEObject;
                Object result = caseQueryStatement(queryStatement);
                if (result == null) result = caseSQLQueryObject(queryStatement);
                if (result == null) result = caseSQLDataStatement(queryStatement);
                if (result == null) result = caseSQLObject(queryStatement);
                if (result == null) result = caseSQLStatement(queryStatement);
                if (result == null) result = caseENamedElement(queryStatement);
                if (result == null) result = caseEModelElement(queryStatement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT: {
                QueryDeleteStatement queryDeleteStatement = (QueryDeleteStatement)theEObject;
                Object result = caseQueryDeleteStatement(queryDeleteStatement);
                if (result == null) result = caseQueryChangeStatement(queryDeleteStatement);
                if (result == null) result = caseQueryStatement(queryDeleteStatement);
                if (result == null) result = caseSQLDataChangeStatement(queryDeleteStatement);
                if (result == null) result = caseSQLQueryObject(queryDeleteStatement);
                if (result == null) result = caseSQLDataStatement(queryDeleteStatement);
                if (result == null) result = caseSQLObject(queryDeleteStatement);
                if (result == null) result = caseSQLStatement(queryDeleteStatement);
                if (result == null) result = caseENamedElement(queryDeleteStatement);
                if (result == null) result = caseEModelElement(queryDeleteStatement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT: {
                QueryInsertStatement queryInsertStatement = (QueryInsertStatement)theEObject;
                Object result = caseQueryInsertStatement(queryInsertStatement);
                if (result == null) result = caseQueryChangeStatement(queryInsertStatement);
                if (result == null) result = caseQueryStatement(queryInsertStatement);
                if (result == null) result = caseSQLDataChangeStatement(queryInsertStatement);
                if (result == null) result = caseSQLQueryObject(queryInsertStatement);
                if (result == null) result = caseSQLDataStatement(queryInsertStatement);
                if (result == null) result = caseSQLObject(queryInsertStatement);
                if (result == null) result = caseSQLStatement(queryInsertStatement);
                if (result == null) result = caseENamedElement(queryInsertStatement);
                if (result == null) result = caseEModelElement(queryInsertStatement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT: {
                QuerySelectStatement querySelectStatement = (QuerySelectStatement)theEObject;
                Object result = caseQuerySelectStatement(querySelectStatement);
                if (result == null) result = caseQueryStatement(querySelectStatement);
                if (result == null) result = caseSQLQueryObject(querySelectStatement);
                if (result == null) result = caseSQLDataStatement(querySelectStatement);
                if (result == null) result = caseSQLObject(querySelectStatement);
                if (result == null) result = caseSQLStatement(querySelectStatement);
                if (result == null) result = caseENamedElement(querySelectStatement);
                if (result == null) result = caseEModelElement(querySelectStatement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_UPDATE_STATEMENT: {
                QueryUpdateStatement queryUpdateStatement = (QueryUpdateStatement)theEObject;
                Object result = caseQueryUpdateStatement(queryUpdateStatement);
                if (result == null) result = caseQueryChangeStatement(queryUpdateStatement);
                if (result == null) result = caseQueryStatement(queryUpdateStatement);
                if (result == null) result = caseSQLDataChangeStatement(queryUpdateStatement);
                if (result == null) result = caseSQLQueryObject(queryUpdateStatement);
                if (result == null) result = caseSQLDataStatement(queryUpdateStatement);
                if (result == null) result = caseSQLObject(queryUpdateStatement);
                if (result == null) result = caseSQLStatement(queryUpdateStatement);
                if (result == null) result = caseENamedElement(queryUpdateStatement);
                if (result == null) result = caseEModelElement(queryUpdateStatement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION: {
                UpdateAssignmentExpression updateAssignmentExpression = (UpdateAssignmentExpression)theEObject;
                Object result = caseUpdateAssignmentExpression(updateAssignmentExpression);
                if (result == null) result = caseSQLQueryObject(updateAssignmentExpression);
                if (result == null) result = caseSQLObject(updateAssignmentExpression);
                if (result == null) result = caseENamedElement(updateAssignmentExpression);
                if (result == null) result = caseEModelElement(updateAssignmentExpression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.CURSOR_REFERENCE: {
                CursorReference cursorReference = (CursorReference)theEObject;
                Object result = caseCursorReference(cursorReference);
                if (result == null) result = caseSQLQueryObject(cursorReference);
                if (result == null) result = caseSQLObject(cursorReference);
                if (result == null) result = caseENamedElement(cursorReference);
                if (result == null) result = caseEModelElement(cursorReference);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_SEARCH_CONDITION: {
                QuerySearchCondition querySearchCondition = (QuerySearchCondition)theEObject;
                Object result = caseQuerySearchCondition(querySearchCondition);
                if (result == null) result = caseSQLQueryObject(querySearchCondition);
                if (result == null) result = caseSearchCondition(querySearchCondition);
                if (result == null) result = caseSQLObject(querySearchCondition);
                if (result == null) result = caseENamedElement(querySearchCondition);
                if (result == null) result = caseEModelElement(querySearchCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_EXPRESSION_BODY: {
                QueryExpressionBody queryExpressionBody = (QueryExpressionBody)theEObject;
                Object result = caseQueryExpressionBody(queryExpressionBody);
                if (result == null) result = caseTableExpression(queryExpressionBody);
                if (result == null) result = caseTableReference(queryExpressionBody);
                if (result == null) result = caseSQLQueryObject(queryExpressionBody);
                if (result == null) result = caseSQLObject(queryExpressionBody);
                if (result == null) result = caseENamedElement(queryExpressionBody);
                if (result == null) result = caseEModelElement(queryExpressionBody);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_VALUE_EXPRESSION: {
                QueryValueExpression queryValueExpression = (QueryValueExpression)theEObject;
                Object result = caseQueryValueExpression(queryValueExpression);
                if (result == null) result = caseSQLQueryObject(queryValueExpression);
                if (result == null) result = caseValueExpression(queryValueExpression);
                if (result == null) result = caseSQLObject(queryValueExpression);
                if (result == null) result = caseENamedElement(queryValueExpression);
                if (result == null) result = caseEModelElement(queryValueExpression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT: {
                QueryExpressionRoot queryExpressionRoot = (QueryExpressionRoot)theEObject;
                Object result = caseQueryExpressionRoot(queryExpressionRoot);
                if (result == null) result = caseSQLQueryObject(queryExpressionRoot);
                if (result == null) result = caseQueryExpression(queryExpressionRoot);
                if (result == null) result = caseSQLObject(queryExpressionRoot);
                if (result == null) result = caseENamedElement(queryExpressionRoot);
                if (result == null) result = caseEModelElement(queryExpressionRoot);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUES_ROW: {
                ValuesRow valuesRow = (ValuesRow)theEObject;
                Object result = caseValuesRow(valuesRow);
                if (result == null) result = caseSQLQueryObject(valuesRow);
                if (result == null) result = caseSQLObject(valuesRow);
                if (result == null) result = caseENamedElement(valuesRow);
                if (result == null) result = caseEModelElement(valuesRow);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_VALUES: {
                QueryValues queryValues = (QueryValues)theEObject;
                Object result = caseQueryValues(queryValues);
                if (result == null) result = caseQueryExpressionBody(queryValues);
                if (result == null) result = caseTableExpression(queryValues);
                if (result == null) result = caseTableReference(queryValues);
                if (result == null) result = caseSQLQueryObject(queryValues);
                if (result == null) result = caseSQLObject(queryValues);
                if (result == null) result = caseENamedElement(queryValues);
                if (result == null) result = caseEModelElement(queryValues);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.TABLE_REFERENCE: {
                TableReference tableReference = (TableReference)theEObject;
                Object result = caseTableReference(tableReference);
                if (result == null) result = caseSQLQueryObject(tableReference);
                if (result == null) result = caseSQLObject(tableReference);
                if (result == null) result = caseENamedElement(tableReference);
                if (result == null) result = caseEModelElement(tableReference);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.TABLE_EXPRESSION: {
                TableExpression tableExpression = (TableExpression)theEObject;
                Object result = caseTableExpression(tableExpression);
                if (result == null) result = caseTableReference(tableExpression);
                if (result == null) result = caseSQLQueryObject(tableExpression);
                if (result == null) result = caseSQLObject(tableExpression);
                if (result == null) result = caseENamedElement(tableExpression);
                if (result == null) result = caseEModelElement(tableExpression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.TABLE_JOINED: {
                TableJoined tableJoined = (TableJoined)theEObject;
                Object result = caseTableJoined(tableJoined);
                if (result == null) result = caseTableReference(tableJoined);
                if (result == null) result = caseSQLQueryObject(tableJoined);
                if (result == null) result = caseSQLObject(tableJoined);
                if (result == null) result = caseENamedElement(tableJoined);
                if (result == null) result = caseEModelElement(tableJoined);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION: {
                WithTableSpecification withTableSpecification = (WithTableSpecification)theEObject;
                Object result = caseWithTableSpecification(withTableSpecification);
                if (result == null) result = caseSQLQueryObject(withTableSpecification);
                if (result == null) result = caseSQLObject(withTableSpecification);
                if (result == null) result = caseENamedElement(withTableSpecification);
                if (result == null) result = caseEModelElement(withTableSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE: {
                Predicate predicate = (Predicate)theEObject;
                Object result = casePredicate(predicate);
                if (result == null) result = caseQuerySearchCondition(predicate);
                if (result == null) result = caseSQLQueryObject(predicate);
                if (result == null) result = caseSearchCondition(predicate);
                if (result == null) result = caseSQLObject(predicate);
                if (result == null) result = caseENamedElement(predicate);
                if (result == null) result = caseEModelElement(predicate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED: {
                SearchConditionCombined searchConditionCombined = (SearchConditionCombined)theEObject;
                Object result = caseSearchConditionCombined(searchConditionCombined);
                if (result == null) result = caseQuerySearchCondition(searchConditionCombined);
                if (result == null) result = caseSQLQueryObject(searchConditionCombined);
                if (result == null) result = caseSearchCondition(searchConditionCombined);
                if (result == null) result = caseSQLObject(searchConditionCombined);
                if (result == null) result = caseENamedElement(searchConditionCombined);
                if (result == null) result = caseEModelElement(searchConditionCombined);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.ORDER_BY_VALUE_EXPRESSION: {
                OrderByValueExpression orderByValueExpression = (OrderByValueExpression)theEObject;
                Object result = caseOrderByValueExpression(orderByValueExpression);
                if (result == null) result = caseOrderBySpecification(orderByValueExpression);
                if (result == null) result = caseSQLQueryObject(orderByValueExpression);
                if (result == null) result = caseSQLObject(orderByValueExpression);
                if (result == null) result = caseENamedElement(orderByValueExpression);
                if (result == null) result = caseEModelElement(orderByValueExpression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_COMBINED: {
                QueryCombined queryCombined = (QueryCombined)theEObject;
                Object result = caseQueryCombined(queryCombined);
                if (result == null) result = caseQueryExpressionBody(queryCombined);
                if (result == null) result = caseTableExpression(queryCombined);
                if (result == null) result = caseTableReference(queryCombined);
                if (result == null) result = caseSQLQueryObject(queryCombined);
                if (result == null) result = caseSQLObject(queryCombined);
                if (result == null) result = caseENamedElement(queryCombined);
                if (result == null) result = caseEModelElement(queryCombined);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_SELECT: {
                QuerySelect querySelect = (QuerySelect)theEObject;
                Object result = caseQuerySelect(querySelect);
                if (result == null) result = caseQueryExpressionBody(querySelect);
                if (result == null) result = caseTableExpression(querySelect);
                if (result == null) result = caseTableReference(querySelect);
                if (result == null) result = caseSQLQueryObject(querySelect);
                if (result == null) result = caseSQLObject(querySelect);
                if (result == null) result = caseENamedElement(querySelect);
                if (result == null) result = caseEModelElement(querySelect);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.GROUPING_SPECIFICATION: {
                GroupingSpecification groupingSpecification = (GroupingSpecification)theEObject;
                Object result = caseGroupingSpecification(groupingSpecification);
                if (result == null) result = caseSQLQueryObject(groupingSpecification);
                if (result == null) result = caseSQLObject(groupingSpecification);
                if (result == null) result = caseENamedElement(groupingSpecification);
                if (result == null) result = caseEModelElement(groupingSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_RESULT_SPECIFICATION: {
                QueryResultSpecification queryResultSpecification = (QueryResultSpecification)theEObject;
                Object result = caseQueryResultSpecification(queryResultSpecification);
                if (result == null) result = caseSQLQueryObject(queryResultSpecification);
                if (result == null) result = caseSQLObject(queryResultSpecification);
                if (result == null) result = caseENamedElement(queryResultSpecification);
                if (result == null) result = caseEModelElement(queryResultSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS: {
                ResultTableAllColumns resultTableAllColumns = (ResultTableAllColumns)theEObject;
                Object result = caseResultTableAllColumns(resultTableAllColumns);
                if (result == null) result = caseQueryResultSpecification(resultTableAllColumns);
                if (result == null) result = caseSQLQueryObject(resultTableAllColumns);
                if (result == null) result = caseSQLObject(resultTableAllColumns);
                if (result == null) result = caseENamedElement(resultTableAllColumns);
                if (result == null) result = caseEModelElement(resultTableAllColumns);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.RESULT_COLUMN: {
                ResultColumn resultColumn = (ResultColumn)theEObject;
                Object result = caseResultColumn(resultColumn);
                if (result == null) result = caseQueryResultSpecification(resultColumn);
                if (result == null) result = caseSQLQueryObject(resultColumn);
                if (result == null) result = caseSQLObject(resultColumn);
                if (result == null) result = caseENamedElement(resultColumn);
                if (result == null) result = caseEModelElement(resultColumn);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_BASIC: {
                PredicateBasic predicateBasic = (PredicateBasic)theEObject;
                Object result = casePredicateBasic(predicateBasic);
                if (result == null) result = casePredicate(predicateBasic);
                if (result == null) result = caseQuerySearchCondition(predicateBasic);
                if (result == null) result = caseSQLQueryObject(predicateBasic);
                if (result == null) result = caseSearchCondition(predicateBasic);
                if (result == null) result = caseSQLObject(predicateBasic);
                if (result == null) result = caseENamedElement(predicateBasic);
                if (result == null) result = caseEModelElement(predicateBasic);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED: {
                PredicateQuantified predicateQuantified = (PredicateQuantified)theEObject;
                Object result = casePredicateQuantified(predicateQuantified);
                if (result == null) result = casePredicate(predicateQuantified);
                if (result == null) result = caseQuerySearchCondition(predicateQuantified);
                if (result == null) result = caseSQLQueryObject(predicateQuantified);
                if (result == null) result = caseSearchCondition(predicateQuantified);
                if (result == null) result = caseSQLObject(predicateQuantified);
                if (result == null) result = caseENamedElement(predicateQuantified);
                if (result == null) result = caseEModelElement(predicateQuantified);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_BETWEEN: {
                PredicateBetween predicateBetween = (PredicateBetween)theEObject;
                Object result = casePredicateBetween(predicateBetween);
                if (result == null) result = casePredicate(predicateBetween);
                if (result == null) result = caseQuerySearchCondition(predicateBetween);
                if (result == null) result = caseSQLQueryObject(predicateBetween);
                if (result == null) result = caseSearchCondition(predicateBetween);
                if (result == null) result = caseSQLObject(predicateBetween);
                if (result == null) result = caseENamedElement(predicateBetween);
                if (result == null) result = caseEModelElement(predicateBetween);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_EXISTS: {
                PredicateExists predicateExists = (PredicateExists)theEObject;
                Object result = casePredicateExists(predicateExists);
                if (result == null) result = casePredicate(predicateExists);
                if (result == null) result = caseQuerySearchCondition(predicateExists);
                if (result == null) result = caseSQLQueryObject(predicateExists);
                if (result == null) result = caseSearchCondition(predicateExists);
                if (result == null) result = caseSQLObject(predicateExists);
                if (result == null) result = caseENamedElement(predicateExists);
                if (result == null) result = caseEModelElement(predicateExists);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_IN: {
                PredicateIn predicateIn = (PredicateIn)theEObject;
                Object result = casePredicateIn(predicateIn);
                if (result == null) result = casePredicate(predicateIn);
                if (result == null) result = caseQuerySearchCondition(predicateIn);
                if (result == null) result = caseSQLQueryObject(predicateIn);
                if (result == null) result = caseSearchCondition(predicateIn);
                if (result == null) result = caseSQLObject(predicateIn);
                if (result == null) result = caseENamedElement(predicateIn);
                if (result == null) result = caseEModelElement(predicateIn);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_LIKE: {
                PredicateLike predicateLike = (PredicateLike)theEObject;
                Object result = casePredicateLike(predicateLike);
                if (result == null) result = casePredicate(predicateLike);
                if (result == null) result = caseQuerySearchCondition(predicateLike);
                if (result == null) result = caseSQLQueryObject(predicateLike);
                if (result == null) result = caseSearchCondition(predicateLike);
                if (result == null) result = caseSQLObject(predicateLike);
                if (result == null) result = caseENamedElement(predicateLike);
                if (result == null) result = caseEModelElement(predicateLike);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_IS_NULL: {
                PredicateIsNull predicateIsNull = (PredicateIsNull)theEObject;
                Object result = casePredicateIsNull(predicateIsNull);
                if (result == null) result = casePredicate(predicateIsNull);
                if (result == null) result = caseQuerySearchCondition(predicateIsNull);
                if (result == null) result = caseSQLQueryObject(predicateIsNull);
                if (result == null) result = caseSearchCondition(predicateIsNull);
                if (result == null) result = caseSQLObject(predicateIsNull);
                if (result == null) result = caseENamedElement(predicateIsNull);
                if (result == null) result = caseEModelElement(predicateIsNull);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT: {
                PredicateQuantifiedValueSelect predicateQuantifiedValueSelect = (PredicateQuantifiedValueSelect)theEObject;
                Object result = casePredicateQuantifiedValueSelect(predicateQuantifiedValueSelect);
                if (result == null) result = casePredicateQuantified(predicateQuantifiedValueSelect);
                if (result == null) result = casePredicate(predicateQuantifiedValueSelect);
                if (result == null) result = caseQuerySearchCondition(predicateQuantifiedValueSelect);
                if (result == null) result = caseSQLQueryObject(predicateQuantifiedValueSelect);
                if (result == null) result = caseSearchCondition(predicateQuantifiedValueSelect);
                if (result == null) result = caseSQLObject(predicateQuantifiedValueSelect);
                if (result == null) result = caseENamedElement(predicateQuantifiedValueSelect);
                if (result == null) result = caseEModelElement(predicateQuantifiedValueSelect);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_ROW_SELECT: {
                PredicateQuantifiedRowSelect predicateQuantifiedRowSelect = (PredicateQuantifiedRowSelect)theEObject;
                Object result = casePredicateQuantifiedRowSelect(predicateQuantifiedRowSelect);
                if (result == null) result = casePredicateQuantified(predicateQuantifiedRowSelect);
                if (result == null) result = casePredicate(predicateQuantifiedRowSelect);
                if (result == null) result = caseQuerySearchCondition(predicateQuantifiedRowSelect);
                if (result == null) result = caseSQLQueryObject(predicateQuantifiedRowSelect);
                if (result == null) result = caseSearchCondition(predicateQuantifiedRowSelect);
                if (result == null) result = caseSQLObject(predicateQuantifiedRowSelect);
                if (result == null) result = caseENamedElement(predicateQuantifiedRowSelect);
                if (result == null) result = caseEModelElement(predicateQuantifiedRowSelect);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_SELECT: {
                PredicateInValueSelect predicateInValueSelect = (PredicateInValueSelect)theEObject;
                Object result = casePredicateInValueSelect(predicateInValueSelect);
                if (result == null) result = casePredicateIn(predicateInValueSelect);
                if (result == null) result = casePredicate(predicateInValueSelect);
                if (result == null) result = caseQuerySearchCondition(predicateInValueSelect);
                if (result == null) result = caseSQLQueryObject(predicateInValueSelect);
                if (result == null) result = caseSearchCondition(predicateInValueSelect);
                if (result == null) result = caseSQLObject(predicateInValueSelect);
                if (result == null) result = caseENamedElement(predicateInValueSelect);
                if (result == null) result = caseEModelElement(predicateInValueSelect);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_LIST: {
                PredicateInValueList predicateInValueList = (PredicateInValueList)theEObject;
                Object result = casePredicateInValueList(predicateInValueList);
                if (result == null) result = casePredicateIn(predicateInValueList);
                if (result == null) result = casePredicate(predicateInValueList);
                if (result == null) result = caseQuerySearchCondition(predicateInValueList);
                if (result == null) result = caseSQLQueryObject(predicateInValueList);
                if (result == null) result = caseSearchCondition(predicateInValueList);
                if (result == null) result = caseSQLObject(predicateInValueList);
                if (result == null) result = caseENamedElement(predicateInValueList);
                if (result == null) result = caseEModelElement(predicateInValueList);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT: {
                PredicateInValueRowSelect predicateInValueRowSelect = (PredicateInValueRowSelect)theEObject;
                Object result = casePredicateInValueRowSelect(predicateInValueRowSelect);
                if (result == null) result = casePredicateIn(predicateInValueRowSelect);
                if (result == null) result = casePredicate(predicateInValueRowSelect);
                if (result == null) result = caseQuerySearchCondition(predicateInValueRowSelect);
                if (result == null) result = caseSQLQueryObject(predicateInValueRowSelect);
                if (result == null) result = caseSearchCondition(predicateInValueRowSelect);
                if (result == null) result = caseSQLObject(predicateInValueRowSelect);
                if (result == null) result = caseENamedElement(predicateInValueRowSelect);
                if (result == null) result = caseEModelElement(predicateInValueRowSelect);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_SIMPLE: {
                ValueExpressionSimple valueExpressionSimple = (ValueExpressionSimple)theEObject;
                Object result = caseValueExpressionSimple(valueExpressionSimple);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionSimple);
                if (result == null) result = caseQueryValueExpression(valueExpressionSimple);
                if (result == null) result = caseSQLQueryObject(valueExpressionSimple);
                if (result == null) result = caseValueExpression(valueExpressionSimple);
                if (result == null) result = caseSQLObject(valueExpressionSimple);
                if (result == null) result = caseENamedElement(valueExpressionSimple);
                if (result == null) result = caseEModelElement(valueExpressionSimple);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN: {
                ValueExpressionColumn valueExpressionColumn = (ValueExpressionColumn)theEObject;
                Object result = caseValueExpressionColumn(valueExpressionColumn);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionColumn);
                if (result == null) result = caseQueryValueExpression(valueExpressionColumn);
                if (result == null) result = caseSQLQueryObject(valueExpressionColumn);
                if (result == null) result = caseValueExpression(valueExpressionColumn);
                if (result == null) result = caseSQLObject(valueExpressionColumn);
                if (result == null) result = caseENamedElement(valueExpressionColumn);
                if (result == null) result = caseEModelElement(valueExpressionColumn);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE: {
                ValueExpressionVariable valueExpressionVariable = (ValueExpressionVariable)theEObject;
                Object result = caseValueExpressionVariable(valueExpressionVariable);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionVariable);
                if (result == null) result = caseQueryValueExpression(valueExpressionVariable);
                if (result == null) result = caseSQLQueryObject(valueExpressionVariable);
                if (result == null) result = caseValueExpression(valueExpressionVariable);
                if (result == null) result = caseSQLObject(valueExpressionVariable);
                if (result == null) result = caseENamedElement(valueExpressionVariable);
                if (result == null) result = caseEModelElement(valueExpressionVariable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_SCALAR_SELECT: {
                ValueExpressionScalarSelect valueExpressionScalarSelect = (ValueExpressionScalarSelect)theEObject;
                Object result = caseValueExpressionScalarSelect(valueExpressionScalarSelect);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionScalarSelect);
                if (result == null) result = caseQueryValueExpression(valueExpressionScalarSelect);
                if (result == null) result = caseSQLQueryObject(valueExpressionScalarSelect);
                if (result == null) result = caseValueExpression(valueExpressionScalarSelect);
                if (result == null) result = caseSQLObject(valueExpressionScalarSelect);
                if (result == null) result = caseENamedElement(valueExpressionScalarSelect);
                if (result == null) result = caseEModelElement(valueExpressionScalarSelect);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_LABELED_DURATION: {
                ValueExpressionLabeledDuration valueExpressionLabeledDuration = (ValueExpressionLabeledDuration)theEObject;
                Object result = caseValueExpressionLabeledDuration(valueExpressionLabeledDuration);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionLabeledDuration);
                if (result == null) result = caseQueryValueExpression(valueExpressionLabeledDuration);
                if (result == null) result = caseSQLQueryObject(valueExpressionLabeledDuration);
                if (result == null) result = caseValueExpression(valueExpressionLabeledDuration);
                if (result == null) result = caseSQLObject(valueExpressionLabeledDuration);
                if (result == null) result = caseENamedElement(valueExpressionLabeledDuration);
                if (result == null) result = caseEModelElement(valueExpressionLabeledDuration);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE: {
                ValueExpressionCase valueExpressionCase = (ValueExpressionCase)theEObject;
                Object result = caseValueExpressionCase(valueExpressionCase);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionCase);
                if (result == null) result = caseQueryValueExpression(valueExpressionCase);
                if (result == null) result = caseSQLQueryObject(valueExpressionCase);
                if (result == null) result = caseValueExpression(valueExpressionCase);
                if (result == null) result = caseSQLObject(valueExpressionCase);
                if (result == null) result = caseENamedElement(valueExpressionCase);
                if (result == null) result = caseEModelElement(valueExpressionCase);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_CAST: {
                ValueExpressionCast valueExpressionCast = (ValueExpressionCast)theEObject;
                Object result = caseValueExpressionCast(valueExpressionCast);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionCast);
                if (result == null) result = caseQueryValueExpression(valueExpressionCast);
                if (result == null) result = caseSQLQueryObject(valueExpressionCast);
                if (result == null) result = caseValueExpression(valueExpressionCast);
                if (result == null) result = caseSQLObject(valueExpressionCast);
                if (result == null) result = caseENamedElement(valueExpressionCast);
                if (result == null) result = caseEModelElement(valueExpressionCast);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_NULL_VALUE: {
                ValueExpressionNullValue valueExpressionNullValue = (ValueExpressionNullValue)theEObject;
                Object result = caseValueExpressionNullValue(valueExpressionNullValue);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionNullValue);
                if (result == null) result = caseQueryValueExpression(valueExpressionNullValue);
                if (result == null) result = caseSQLQueryObject(valueExpressionNullValue);
                if (result == null) result = caseValueExpression(valueExpressionNullValue);
                if (result == null) result = caseSQLObject(valueExpressionNullValue);
                if (result == null) result = caseENamedElement(valueExpressionNullValue);
                if (result == null) result = caseEModelElement(valueExpressionNullValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_DEFAULT_VALUE: {
                ValueExpressionDefaultValue valueExpressionDefaultValue = (ValueExpressionDefaultValue)theEObject;
                Object result = caseValueExpressionDefaultValue(valueExpressionDefaultValue);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionDefaultValue);
                if (result == null) result = caseQueryValueExpression(valueExpressionDefaultValue);
                if (result == null) result = caseSQLQueryObject(valueExpressionDefaultValue);
                if (result == null) result = caseValueExpression(valueExpressionDefaultValue);
                if (result == null) result = caseSQLObject(valueExpressionDefaultValue);
                if (result == null) result = caseENamedElement(valueExpressionDefaultValue);
                if (result == null) result = caseEModelElement(valueExpressionDefaultValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION: {
                ValueExpressionFunction valueExpressionFunction = (ValueExpressionFunction)theEObject;
                Object result = caseValueExpressionFunction(valueExpressionFunction);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionFunction);
                if (result == null) result = caseQueryValueExpression(valueExpressionFunction);
                if (result == null) result = caseSQLQueryObject(valueExpressionFunction);
                if (result == null) result = caseValueExpression(valueExpressionFunction);
                if (result == null) result = caseSQLObject(valueExpressionFunction);
                if (result == null) result = caseENamedElement(valueExpressionFunction);
                if (result == null) result = caseEModelElement(valueExpressionFunction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED: {
                ValueExpressionCombined valueExpressionCombined = (ValueExpressionCombined)theEObject;
                Object result = caseValueExpressionCombined(valueExpressionCombined);
                if (result == null) result = caseQueryValueExpression(valueExpressionCombined);
                if (result == null) result = caseSQLQueryObject(valueExpressionCombined);
                if (result == null) result = caseValueExpression(valueExpressionCombined);
                if (result == null) result = caseSQLObject(valueExpressionCombined);
                if (result == null) result = caseENamedElement(valueExpressionCombined);
                if (result == null) result = caseEModelElement(valueExpressionCombined);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.GROUPING_SETS: {
                GroupingSets groupingSets = (GroupingSets)theEObject;
                Object result = caseGroupingSets(groupingSets);
                if (result == null) result = caseGroupingSpecification(groupingSets);
                if (result == null) result = caseSQLQueryObject(groupingSets);
                if (result == null) result = caseSQLObject(groupingSets);
                if (result == null) result = caseENamedElement(groupingSets);
                if (result == null) result = caseEModelElement(groupingSets);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.GROUPING: {
                Grouping grouping = (Grouping)theEObject;
                Object result = caseGrouping(grouping);
                if (result == null) result = caseGroupingSpecification(grouping);
                if (result == null) result = caseSQLQueryObject(grouping);
                if (result == null) result = caseSQLObject(grouping);
                if (result == null) result = caseENamedElement(grouping);
                if (result == null) result = caseEModelElement(grouping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT: {
                GroupingSetsElement groupingSetsElement = (GroupingSetsElement)theEObject;
                Object result = caseGroupingSetsElement(groupingSetsElement);
                if (result == null) result = caseSQLQueryObject(groupingSetsElement);
                if (result == null) result = caseSQLObject(groupingSetsElement);
                if (result == null) result = caseENamedElement(groupingSetsElement);
                if (result == null) result = caseEModelElement(groupingSetsElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST: {
                GroupingSetsElementSublist groupingSetsElementSublist = (GroupingSetsElementSublist)theEObject;
                Object result = caseGroupingSetsElementSublist(groupingSetsElementSublist);
                if (result == null) result = caseGroupingSetsElement(groupingSetsElementSublist);
                if (result == null) result = caseSQLQueryObject(groupingSetsElementSublist);
                if (result == null) result = caseSQLObject(groupingSetsElementSublist);
                if (result == null) result = caseENamedElement(groupingSetsElementSublist);
                if (result == null) result = caseEModelElement(groupingSetsElementSublist);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION: {
                GroupingSetsElementExpression groupingSetsElementExpression = (GroupingSetsElementExpression)theEObject;
                Object result = caseGroupingSetsElementExpression(groupingSetsElementExpression);
                if (result == null) result = caseGroupingSetsElement(groupingSetsElementExpression);
                if (result == null) result = caseSQLQueryObject(groupingSetsElementExpression);
                if (result == null) result = caseSQLObject(groupingSetsElementExpression);
                if (result == null) result = caseENamedElement(groupingSetsElementExpression);
                if (result == null) result = caseEModelElement(groupingSetsElementExpression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.SUPER_GROUP: {
                SuperGroup superGroup = (SuperGroup)theEObject;
                Object result = caseSuperGroup(superGroup);
                if (result == null) result = caseGrouping(superGroup);
                if (result == null) result = caseGroupingSpecification(superGroup);
                if (result == null) result = caseSQLQueryObject(superGroup);
                if (result == null) result = caseSQLObject(superGroup);
                if (result == null) result = caseENamedElement(superGroup);
                if (result == null) result = caseEModelElement(superGroup);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.GROUPING_EXPRESSION: {
                GroupingExpression groupingExpression = (GroupingExpression)theEObject;
                Object result = caseGroupingExpression(groupingExpression);
                if (result == null) result = caseGrouping(groupingExpression);
                if (result == null) result = caseGroupingSpecification(groupingExpression);
                if (result == null) result = caseSQLQueryObject(groupingExpression);
                if (result == null) result = caseSQLObject(groupingExpression);
                if (result == null) result = caseENamedElement(groupingExpression);
                if (result == null) result = caseEModelElement(groupingExpression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT: {
                SuperGroupElement superGroupElement = (SuperGroupElement)theEObject;
                Object result = caseSuperGroupElement(superGroupElement);
                if (result == null) result = caseSQLQueryObject(superGroupElement);
                if (result == null) result = caseSQLObject(superGroupElement);
                if (result == null) result = caseENamedElement(superGroupElement);
                if (result == null) result = caseEModelElement(superGroupElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_SUBLIST: {
                SuperGroupElementSublist superGroupElementSublist = (SuperGroupElementSublist)theEObject;
                Object result = caseSuperGroupElementSublist(superGroupElementSublist);
                if (result == null) result = caseSuperGroupElement(superGroupElementSublist);
                if (result == null) result = caseSQLQueryObject(superGroupElementSublist);
                if (result == null) result = caseSQLObject(superGroupElementSublist);
                if (result == null) result = caseENamedElement(superGroupElementSublist);
                if (result == null) result = caseEModelElement(superGroupElementSublist);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION: {
                SuperGroupElementExpression superGroupElementExpression = (SuperGroupElementExpression)theEObject;
                Object result = caseSuperGroupElementExpression(superGroupElementExpression);
                if (result == null) result = caseSuperGroupElement(superGroupElementExpression);
                if (result == null) result = caseSQLQueryObject(superGroupElementExpression);
                if (result == null) result = caseSQLObject(superGroupElementExpression);
                if (result == null) result = caseENamedElement(superGroupElementExpression);
                if (result == null) result = caseEModelElement(superGroupElementExpression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH: {
                ValueExpressionCaseSearch valueExpressionCaseSearch = (ValueExpressionCaseSearch)theEObject;
                Object result = caseValueExpressionCaseSearch(valueExpressionCaseSearch);
                if (result == null) result = caseValueExpressionCase(valueExpressionCaseSearch);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionCaseSearch);
                if (result == null) result = caseQueryValueExpression(valueExpressionCaseSearch);
                if (result == null) result = caseSQLQueryObject(valueExpressionCaseSearch);
                if (result == null) result = caseValueExpression(valueExpressionCaseSearch);
                if (result == null) result = caseSQLObject(valueExpressionCaseSearch);
                if (result == null) result = caseENamedElement(valueExpressionCaseSearch);
                if (result == null) result = caseEModelElement(valueExpressionCaseSearch);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE: {
                ValueExpressionCaseSimple valueExpressionCaseSimple = (ValueExpressionCaseSimple)theEObject;
                Object result = caseValueExpressionCaseSimple(valueExpressionCaseSimple);
                if (result == null) result = caseValueExpressionCase(valueExpressionCaseSimple);
                if (result == null) result = caseValueExpressionAtomic(valueExpressionCaseSimple);
                if (result == null) result = caseQueryValueExpression(valueExpressionCaseSimple);
                if (result == null) result = caseSQLQueryObject(valueExpressionCaseSimple);
                if (result == null) result = caseValueExpression(valueExpressionCaseSimple);
                if (result == null) result = caseSQLObject(valueExpressionCaseSimple);
                if (result == null) result = caseENamedElement(valueExpressionCaseSimple);
                if (result == null) result = caseEModelElement(valueExpressionCaseSimple);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE: {
                ValueExpressionCaseElse valueExpressionCaseElse = (ValueExpressionCaseElse)theEObject;
                Object result = caseValueExpressionCaseElse(valueExpressionCaseElse);
                if (result == null) result = caseSQLQueryObject(valueExpressionCaseElse);
                if (result == null) result = caseSQLObject(valueExpressionCaseElse);
                if (result == null) result = caseENamedElement(valueExpressionCaseElse);
                if (result == null) result = caseEModelElement(valueExpressionCaseElse);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT: {
                ValueExpressionCaseSearchContent valueExpressionCaseSearchContent = (ValueExpressionCaseSearchContent)theEObject;
                Object result = caseValueExpressionCaseSearchContent(valueExpressionCaseSearchContent);
                if (result == null) result = caseSQLQueryObject(valueExpressionCaseSearchContent);
                if (result == null) result = caseSQLObject(valueExpressionCaseSearchContent);
                if (result == null) result = caseENamedElement(valueExpressionCaseSearchContent);
                if (result == null) result = caseEModelElement(valueExpressionCaseSearchContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT: {
                ValueExpressionCaseSimpleContent valueExpressionCaseSimpleContent = (ValueExpressionCaseSimpleContent)theEObject;
                Object result = caseValueExpressionCaseSimpleContent(valueExpressionCaseSimpleContent);
                if (result == null) result = caseSQLQueryObject(valueExpressionCaseSimpleContent);
                if (result == null) result = caseSQLObject(valueExpressionCaseSimpleContent);
                if (result == null) result = caseENamedElement(valueExpressionCaseSimpleContent);
                if (result == null) result = caseEModelElement(valueExpressionCaseSimpleContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.TABLE_IN_DATABASE: {
                TableInDatabase tableInDatabase = (TableInDatabase)theEObject;
                Object result = caseTableInDatabase(tableInDatabase);
                if (result == null) result = caseTableExpression(tableInDatabase);
                if (result == null) result = caseTableReference(tableInDatabase);
                if (result == null) result = caseSQLQueryObject(tableInDatabase);
                if (result == null) result = caseSQLObject(tableInDatabase);
                if (result == null) result = caseENamedElement(tableInDatabase);
                if (result == null) result = caseEModelElement(tableInDatabase);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.TABLE_FUNCTION: {
                TableFunction tableFunction = (TableFunction)theEObject;
                Object result = caseTableFunction(tableFunction);
                if (result == null) result = caseTableExpression(tableFunction);
                if (result == null) result = caseTableReference(tableFunction);
                if (result == null) result = caseSQLQueryObject(tableFunction);
                if (result == null) result = caseSQLObject(tableFunction);
                if (result == null) result = caseENamedElement(tableFunction);
                if (result == null) result = caseEModelElement(tableFunction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.SQL_QUERY_OBJECT: {
                SQLQueryObject sqlQueryObject = (SQLQueryObject)theEObject;
                Object result = caseSQLQueryObject(sqlQueryObject);
                if (result == null) result = caseSQLObject(sqlQueryObject);
                if (result == null) result = caseENamedElement(sqlQueryObject);
                if (result == null) result = caseEModelElement(sqlQueryObject);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_CHANGE_STATEMENT: {
                QueryChangeStatement queryChangeStatement = (QueryChangeStatement)theEObject;
                Object result = caseQueryChangeStatement(queryChangeStatement);
                if (result == null) result = caseQueryStatement(queryChangeStatement);
                if (result == null) result = caseSQLDataChangeStatement(queryChangeStatement);
                if (result == null) result = caseSQLQueryObject(queryChangeStatement);
                if (result == null) result = caseSQLDataStatement(queryChangeStatement);
                if (result == null) result = caseSQLObject(queryChangeStatement);
                if (result == null) result = caseSQLStatement(queryChangeStatement);
                if (result == null) result = caseENamedElement(queryChangeStatement);
                if (result == null) result = caseEModelElement(queryChangeStatement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.COLUMN_NAME: {
                ColumnName columnName = (ColumnName)theEObject;
                Object result = caseColumnName(columnName);
                if (result == null) result = caseSQLQueryObject(columnName);
                if (result == null) result = caseSQLObject(columnName);
                if (result == null) result = caseENamedElement(columnName);
                if (result == null) result = caseEModelElement(columnName);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.TABLE_NESTED: {
                TableNested tableNested = (TableNested)theEObject;
                Object result = caseTableNested(tableNested);
                if (result == null) result = caseTableReference(tableNested);
                if (result == null) result = caseSQLQueryObject(tableNested);
                if (result == null) result = caseSQLObject(tableNested);
                if (result == null) result = caseENamedElement(tableNested);
                if (result == null) result = caseEModelElement(tableNested);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT: {
                QueryMergeStatement queryMergeStatement = (QueryMergeStatement)theEObject;
                Object result = caseQueryMergeStatement(queryMergeStatement);
                if (result == null) result = caseQueryChangeStatement(queryMergeStatement);
                if (result == null) result = caseQueryStatement(queryMergeStatement);
                if (result == null) result = caseSQLDataChangeStatement(queryMergeStatement);
                if (result == null) result = caseSQLQueryObject(queryMergeStatement);
                if (result == null) result = caseSQLDataStatement(queryMergeStatement);
                if (result == null) result = caseSQLObject(queryMergeStatement);
                if (result == null) result = caseSQLStatement(queryMergeStatement);
                if (result == null) result = caseENamedElement(queryMergeStatement);
                if (result == null) result = caseEModelElement(queryMergeStatement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.SEARCH_CONDITION_NESTED: {
                SearchConditionNested searchConditionNested = (SearchConditionNested)theEObject;
                Object result = caseSearchConditionNested(searchConditionNested);
                if (result == null) result = caseQuerySearchCondition(searchConditionNested);
                if (result == null) result = caseSQLQueryObject(searchConditionNested);
                if (result == null) result = caseSearchCondition(searchConditionNested);
                if (result == null) result = caseSQLObject(searchConditionNested);
                if (result == null) result = caseENamedElement(searchConditionNested);
                if (result == null) result = caseEModelElement(searchConditionNested);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED: {
                ValueExpressionNested valueExpressionNested = (ValueExpressionNested)theEObject;
                Object result = caseValueExpressionNested(valueExpressionNested);
                if (result == null) result = caseQueryValueExpression(valueExpressionNested);
                if (result == null) result = caseSQLQueryObject(valueExpressionNested);
                if (result == null) result = caseValueExpression(valueExpressionNested);
                if (result == null) result = caseSQLObject(valueExpressionNested);
                if (result == null) result = caseENamedElement(valueExpressionNested);
                if (result == null) result = caseEModelElement(valueExpressionNested);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_ATOMIC: {
                ValueExpressionAtomic valueExpressionAtomic = (ValueExpressionAtomic)theEObject;
                Object result = caseValueExpressionAtomic(valueExpressionAtomic);
                if (result == null) result = caseQueryValueExpression(valueExpressionAtomic);
                if (result == null) result = caseSQLQueryObject(valueExpressionAtomic);
                if (result == null) result = caseValueExpression(valueExpressionAtomic);
                if (result == null) result = caseSQLObject(valueExpressionAtomic);
                if (result == null) result = caseENamedElement(valueExpressionAtomic);
                if (result == null) result = caseEModelElement(valueExpressionAtomic);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.ORDER_BY_SPECIFICATION: {
                OrderBySpecification orderBySpecification = (OrderBySpecification)theEObject;
                Object result = caseOrderBySpecification(orderBySpecification);
                if (result == null) result = caseSQLQueryObject(orderBySpecification);
                if (result == null) result = caseSQLObject(orderBySpecification);
                if (result == null) result = caseENamedElement(orderBySpecification);
                if (result == null) result = caseEModelElement(orderBySpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.ORDER_BY_ORDINAL: {
                OrderByOrdinal orderByOrdinal = (OrderByOrdinal)theEObject;
                Object result = caseOrderByOrdinal(orderByOrdinal);
                if (result == null) result = caseOrderBySpecification(orderByOrdinal);
                if (result == null) result = caseSQLQueryObject(orderByOrdinal);
                if (result == null) result = caseSQLObject(orderByOrdinal);
                if (result == null) result = caseENamedElement(orderByOrdinal);
                if (result == null) result = caseEModelElement(orderByOrdinal);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.TABLE_CORRELATION: {
                TableCorrelation tableCorrelation = (TableCorrelation)theEObject;
                Object result = caseTableCorrelation(tableCorrelation);
                if (result == null) result = caseSQLQueryObject(tableCorrelation);
                if (result == null) result = caseSQLObject(tableCorrelation);
                if (result == null) result = caseENamedElement(tableCorrelation);
                if (result == null) result = caseEModelElement(tableCorrelation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.UPDATE_SOURCE: {
                UpdateSource updateSource = (UpdateSource)theEObject;
                Object result = caseUpdateSource(updateSource);
                if (result == null) result = caseSQLQueryObject(updateSource);
                if (result == null) result = caseSQLObject(updateSource);
                if (result == null) result = caseENamedElement(updateSource);
                if (result == null) result = caseEModelElement(updateSource);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.UPDATE_SOURCE_EXPR_LIST: {
                UpdateSourceExprList updateSourceExprList = (UpdateSourceExprList)theEObject;
                Object result = caseUpdateSourceExprList(updateSourceExprList);
                if (result == null) result = caseUpdateSource(updateSourceExprList);
                if (result == null) result = caseSQLQueryObject(updateSourceExprList);
                if (result == null) result = caseSQLObject(updateSourceExprList);
                if (result == null) result = caseENamedElement(updateSourceExprList);
                if (result == null) result = caseEModelElement(updateSourceExprList);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.UPDATE_SOURCE_QUERY: {
                UpdateSourceQuery updateSourceQuery = (UpdateSourceQuery)theEObject;
                Object result = caseUpdateSourceQuery(updateSourceQuery);
                if (result == null) result = caseUpdateSource(updateSourceQuery);
                if (result == null) result = caseSQLQueryObject(updateSourceQuery);
                if (result == null) result = caseSQLObject(updateSourceQuery);
                if (result == null) result = caseENamedElement(updateSourceQuery);
                if (result == null) result = caseEModelElement(updateSourceQuery);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN: {
                OrderByResultColumn orderByResultColumn = (OrderByResultColumn)theEObject;
                Object result = caseOrderByResultColumn(orderByResultColumn);
                if (result == null) result = caseOrderBySpecification(orderByResultColumn);
                if (result == null) result = caseSQLQueryObject(orderByResultColumn);
                if (result == null) result = caseSQLObject(orderByResultColumn);
                if (result == null) result = caseENamedElement(orderByResultColumn);
                if (result == null) result = caseEModelElement(orderByResultColumn);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.WITH_TABLE_REFERENCE: {
                WithTableReference withTableReference = (WithTableReference)theEObject;
                Object result = caseWithTableReference(withTableReference);
                if (result == null) result = caseTableExpression(withTableReference);
                if (result == null) result = caseTableReference(withTableReference);
                if (result == null) result = caseSQLQueryObject(withTableReference);
                if (result == null) result = caseSQLObject(withTableReference);
                if (result == null) result = caseENamedElement(withTableReference);
                if (result == null) result = caseEModelElement(withTableReference);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.QUERY_NESTED: {
                QueryNested queryNested = (QueryNested)theEObject;
                Object result = caseQueryNested(queryNested);
                if (result == null) result = caseQueryExpressionBody(queryNested);
                if (result == null) result = caseTableExpression(queryNested);
                if (result == null) result = caseTableReference(queryNested);
                if (result == null) result = caseSQLQueryObject(queryNested);
                if (result == null) result = caseSQLObject(queryNested);
                if (result == null) result = caseENamedElement(queryNested);
                if (result == null) result = caseEModelElement(queryNested);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.VALUE_EXPRESSION_ROW: {
                ValueExpressionRow valueExpressionRow = (ValueExpressionRow)theEObject;
                Object result = caseValueExpressionRow(valueExpressionRow);
                if (result == null) result = caseQueryValueExpression(valueExpressionRow);
                if (result == null) result = caseSQLQueryObject(valueExpressionRow);
                if (result == null) result = caseValueExpression(valueExpressionRow);
                if (result == null) result = caseSQLObject(valueExpressionRow);
                if (result == null) result = caseENamedElement(valueExpressionRow);
                if (result == null) result = caseEModelElement(valueExpressionRow);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.MERGE_TARGET_TABLE: {
                MergeTargetTable mergeTargetTable = (MergeTargetTable)theEObject;
                Object result = caseMergeTargetTable(mergeTargetTable);
                if (result == null) result = caseSQLQueryObject(mergeTargetTable);
                if (result == null) result = caseSQLObject(mergeTargetTable);
                if (result == null) result = caseENamedElement(mergeTargetTable);
                if (result == null) result = caseEModelElement(mergeTargetTable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE: {
                MergeSourceTable mergeSourceTable = (MergeSourceTable)theEObject;
                Object result = caseMergeSourceTable(mergeSourceTable);
                if (result == null) result = caseSQLQueryObject(mergeSourceTable);
                if (result == null) result = caseSQLObject(mergeSourceTable);
                if (result == null) result = caseENamedElement(mergeSourceTable);
                if (result == null) result = caseEModelElement(mergeSourceTable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.MERGE_ON_CONDITION: {
                MergeOnCondition mergeOnCondition = (MergeOnCondition)theEObject;
                Object result = caseMergeOnCondition(mergeOnCondition);
                if (result == null) result = caseSQLQueryObject(mergeOnCondition);
                if (result == null) result = caseSQLObject(mergeOnCondition);
                if (result == null) result = caseENamedElement(mergeOnCondition);
                if (result == null) result = caseEModelElement(mergeOnCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.MERGE_UPDATE_SPECIFICATION: {
                MergeUpdateSpecification mergeUpdateSpecification = (MergeUpdateSpecification)theEObject;
                Object result = caseMergeUpdateSpecification(mergeUpdateSpecification);
                if (result == null) result = caseMergeOperationSpecification(mergeUpdateSpecification);
                if (result == null) result = caseSQLQueryObject(mergeUpdateSpecification);
                if (result == null) result = caseSQLObject(mergeUpdateSpecification);
                if (result == null) result = caseENamedElement(mergeUpdateSpecification);
                if (result == null) result = caseEModelElement(mergeUpdateSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION: {
                MergeInsertSpecification mergeInsertSpecification = (MergeInsertSpecification)theEObject;
                Object result = caseMergeInsertSpecification(mergeInsertSpecification);
                if (result == null) result = caseMergeOperationSpecification(mergeInsertSpecification);
                if (result == null) result = caseSQLQueryObject(mergeInsertSpecification);
                if (result == null) result = caseSQLObject(mergeInsertSpecification);
                if (result == null) result = caseENamedElement(mergeInsertSpecification);
                if (result == null) result = caseEModelElement(mergeInsertSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION: {
                MergeOperationSpecification mergeOperationSpecification = (MergeOperationSpecification)theEObject;
                Object result = caseMergeOperationSpecification(mergeOperationSpecification);
                if (result == null) result = caseSQLQueryObject(mergeOperationSpecification);
                if (result == null) result = caseSQLObject(mergeOperationSpecification);
                if (result == null) result = caseENamedElement(mergeOperationSpecification);
                if (result == null) result = caseEModelElement(mergeOperationSpecification);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.UPDATE_OF_COLUMN: {
                UpdateOfColumn updateOfColumn = (UpdateOfColumn)theEObject;
                Object result = caseUpdateOfColumn(updateOfColumn);
                if (result == null) result = caseSQLQueryObject(updateOfColumn);
                if (result == null) result = caseSQLObject(updateOfColumn);
                if (result == null) result = caseENamedElement(updateOfColumn);
                if (result == null) result = caseEModelElement(updateOfColumn);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION: {
                UpdatabilityExpression updatabilityExpression = (UpdatabilityExpression)theEObject;
                Object result = caseUpdatabilityExpression(updatabilityExpression);
                if (result == null) result = caseSQLQueryObject(updatabilityExpression);
                if (result == null) result = caseSQLObject(updatabilityExpression);
                if (result == null) result = caseENamedElement(updatabilityExpression);
                if (result == null) result = caseEModelElement(updatabilityExpression);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.CALL_STATEMENT: {
                CallStatement callStatement = (CallStatement)theEObject;
                Object result = caseCallStatement(callStatement);
                if (result == null) result = caseSQLQueryObject(callStatement);
                if (result == null) result = caseSQLControlStatement(callStatement);
                if (result == null) result = caseSQLObject(callStatement);
                if (result == null) result = caseSQLStatement(callStatement);
                if (result == null) result = caseENamedElement(callStatement);
                if (result == null) result = caseEModelElement(callStatement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.PROCEDURE_REFERENCE: {
                ProcedureReference procedureReference = (ProcedureReference)theEObject;
                Object result = caseProcedureReference(procedureReference);
                if (result == null) result = caseSQLQueryObject(procedureReference);
                if (result == null) result = caseSQLObject(procedureReference);
                if (result == null) result = caseENamedElement(procedureReference);
                if (result == null) result = caseEModelElement(procedureReference);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SQLQueryModelPackage.TABLE_QUERY_LATERAL: {
                TableQueryLateral tableQueryLateral = (TableQueryLateral)theEObject;
                Object result = caseTableQueryLateral(tableQueryLateral);
                if (result == null) result = caseTableExpression(tableQueryLateral);
                if (result == null) result = caseTableReference(tableQueryLateral);
                if (result == null) result = caseSQLQueryObject(tableQueryLateral);
                if (result == null) result = caseSQLObject(tableQueryLateral);
                if (result == null) result = caseENamedElement(tableQueryLateral);
                if (result == null) result = caseEModelElement(tableQueryLateral);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryStatement(QueryStatement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Delete Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Delete Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryDeleteStatement(QueryDeleteStatement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Insert Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Insert Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryInsertStatement(QueryInsertStatement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Select Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Select Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQuerySelectStatement(QuerySelectStatement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Update Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Update Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryUpdateStatement(QueryUpdateStatement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Update Assignment Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Update Assignment Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUpdateAssignmentExpression(UpdateAssignmentExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Cursor Reference</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Cursor Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCursorReference(CursorReference object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Search Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Search Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQuerySearchCondition(QuerySearchCondition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Expression Body</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Expression Body</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryExpressionBody(QueryExpressionBody object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Value Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Value Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryValueExpression(QueryValueExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Expression Root</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Expression Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryExpressionRoot(QueryExpressionRoot object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Values Row</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Values Row</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValuesRow(ValuesRow object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Values</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Values</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryValues(QueryValues object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table Reference</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTableReference(TableReference object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTableExpression(TableExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table Joined</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Joined</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTableJoined(TableJoined object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>With Table Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>With Table Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseWithTableSpecification(WithTableSpecification object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicate(Predicate object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Search Condition Combined</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Search Condition Combined</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSearchConditionCombined(SearchConditionCombined object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Order By Value Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Order By Value Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseOrderByValueExpression(OrderByValueExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Combined</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Combined</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryCombined(QueryCombined object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Select</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Select</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQuerySelect(QuerySelect object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Grouping Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Grouping Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGroupingSpecification(GroupingSpecification object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Result Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Result Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryResultSpecification(QueryResultSpecification object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Result Table All Columns</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Result Table All Columns</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseResultTableAllColumns(ResultTableAllColumns object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Result Column</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Result Column</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseResultColumn(ResultColumn object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate Basic</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate Basic</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateBasic(PredicateBasic object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate Quantified</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate Quantified</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateQuantified(PredicateQuantified object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate Between</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate Between</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateBetween(PredicateBetween object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate Exists</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate Exists</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateExists(PredicateExists object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate In</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate In</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateIn(PredicateIn object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate Like</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate Like</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateLike(PredicateLike object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate Is Null</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate Is Null</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateIsNull(PredicateIsNull object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate Quantified Value Select</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate Quantified Value Select</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateQuantifiedValueSelect(PredicateQuantifiedValueSelect object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate Quantified Row Select</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate Quantified Row Select</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateQuantifiedRowSelect(PredicateQuantifiedRowSelect object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate In Value Select</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate In Value Select</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateInValueSelect(PredicateInValueSelect object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate In Value List</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate In Value List</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateInValueList(PredicateInValueList object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Predicate In Value Row Select</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predicate In Value Row Select</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePredicateInValueRowSelect(PredicateInValueRowSelect object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Simple</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Simple</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionSimple(ValueExpressionSimple object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Column</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Column</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionColumn(ValueExpressionColumn object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Variable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionVariable(ValueExpressionVariable object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Scalar Select</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Scalar Select</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionScalarSelect(ValueExpressionScalarSelect object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Labeled Duration</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Labeled Duration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionLabeledDuration(ValueExpressionLabeledDuration object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Case</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Case</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionCase(ValueExpressionCase object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Cast</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Cast</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionCast(ValueExpressionCast object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Null Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Null Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionNullValue(ValueExpressionNullValue object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Default Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Default Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionDefaultValue(ValueExpressionDefaultValue object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Function</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Function</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionFunction(ValueExpressionFunction object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Combined</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Combined</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionCombined(ValueExpressionCombined object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Grouping Sets</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Grouping Sets</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGroupingSets(GroupingSets object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Grouping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Grouping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGrouping(Grouping object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Grouping Sets Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Grouping Sets Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGroupingSetsElement(GroupingSetsElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Grouping Sets Element Sublist</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Grouping Sets Element Sublist</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGroupingSetsElementSublist(GroupingSetsElementSublist object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Grouping Sets Element Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Grouping Sets Element Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGroupingSetsElementExpression(GroupingSetsElementExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Super Group</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Super Group</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSuperGroup(SuperGroup object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Grouping Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Grouping Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGroupingExpression(GroupingExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Super Group Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Super Group Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSuperGroupElement(SuperGroupElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Super Group Element Sublist</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Super Group Element Sublist</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSuperGroupElementSublist(SuperGroupElementSublist object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Super Group Element Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Super Group Element Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSuperGroupElementExpression(SuperGroupElementExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Case Search</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Case Search</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionCaseSearch(ValueExpressionCaseSearch object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Case Simple</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Case Simple</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionCaseSimple(ValueExpressionCaseSimple object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Case Else</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Case Else</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionCaseElse(ValueExpressionCaseElse object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Case Search Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Case Search Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionCaseSearchContent(ValueExpressionCaseSearchContent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Case Simple Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Case Simple Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionCaseSimpleContent(ValueExpressionCaseSimpleContent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table In Database</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table In Database</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTableInDatabase(TableInDatabase object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table Function</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Function</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTableFunction(TableFunction object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>SQL Query Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SQL Query Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSQLQueryObject(SQLQueryObject object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Change Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Change Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryChangeStatement(QueryChangeStatement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Column Name</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Column Name</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseColumnName(ColumnName object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table Nested</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Nested</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTableNested(TableNested object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Merge Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Merge Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryMergeStatement(QueryMergeStatement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Search Condition Nested</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Search Condition Nested</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSearchConditionNested(SearchConditionNested object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Nested</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Nested</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionNested(ValueExpressionNested object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Atomic</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Atomic</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionAtomic(ValueExpressionAtomic object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Order By Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Order By Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseOrderBySpecification(OrderBySpecification object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Order By Ordinal</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Order By Ordinal</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseOrderByOrdinal(OrderByOrdinal object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table Correlation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Correlation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTableCorrelation(TableCorrelation object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Update Source</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Update Source</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUpdateSource(UpdateSource object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Update Source Expr List</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Update Source Expr List</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUpdateSourceExprList(UpdateSourceExprList object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Update Source Query</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Update Source Query</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUpdateSourceQuery(UpdateSourceQuery object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Order By Result Column</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Order By Result Column</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseOrderByResultColumn(OrderByResultColumn object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>With Table Reference</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>With Table Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseWithTableReference(WithTableReference object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Nested</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Nested</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryNested(QueryNested object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression Row</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression Row</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpressionRow(ValueExpressionRow object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Merge Target Table</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Merge Target Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMergeTargetTable(MergeTargetTable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Merge Source Table</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Merge Source Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMergeSourceTable(MergeSourceTable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Merge On Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Merge On Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMergeOnCondition(MergeOnCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Merge Update Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Merge Update Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMergeUpdateSpecification(MergeUpdateSpecification object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Merge Insert Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Merge Insert Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMergeInsertSpecification(MergeInsertSpecification object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Merge Operation Specification</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Merge Operation Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseMergeOperationSpecification(MergeOperationSpecification object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Update Of Column</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Update Of Column</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUpdateOfColumn(UpdateOfColumn object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Updatability Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Updatability Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUpdatabilityExpression(UpdatabilityExpression object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Call Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Call Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseCallStatement(CallStatement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Procedure Reference</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Procedure Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseProcedureReference(ProcedureReference object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Table Query Lateral</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Query Lateral</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTableQueryLateral(TableQueryLateral object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EModel Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EModel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseEModelElement(EModelElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseENamedElement(ENamedElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>SQL Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SQL Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSQLObject(SQLObject object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>SQL Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SQL Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSQLStatement(SQLStatement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>SQL Data Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SQL Data Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSQLDataStatement(SQLDataStatement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>SQL Data Change Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SQL Data Change Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSQLDataChangeStatement(SQLDataChangeStatement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Search Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Search Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSearchCondition(SearchCondition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Value Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseValueExpression(ValueExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Query Expression</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseQueryExpression(QueryExpression object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>SQL Control Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SQL Control Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSQLControlStatement(SQLControlStatement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public Object defaultCase(EObject object) {
        return null;
    }

} //SQLQuerySwitch
