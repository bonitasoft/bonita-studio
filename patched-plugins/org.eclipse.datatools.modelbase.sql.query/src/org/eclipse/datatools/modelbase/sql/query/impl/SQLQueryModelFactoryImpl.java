/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLQueryModelFactoryImpl.java,v 1.5 2010/02/25 01:57:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import org.eclipse.datatools.modelbase.sql.query.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SQLQueryModelFactoryImpl extends EFactoryImpl implements SQLQueryModelFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SQLQueryModelFactory init() {
        try {
            SQLQueryModelFactory theSQLQueryModelFactory = (SQLQueryModelFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/datatools/modelbase/sql/query/SQLQueryModel.ecore"); 
            if (theSQLQueryModelFactory != null) {
                return theSQLQueryModelFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SQLQueryModelFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SQLQueryModelFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case SQLQueryModelPackage.QUERY_DELETE_STATEMENT: return createQueryDeleteStatement();
            case SQLQueryModelPackage.QUERY_INSERT_STATEMENT: return createQueryInsertStatement();
            case SQLQueryModelPackage.QUERY_SELECT_STATEMENT: return createQuerySelectStatement();
            case SQLQueryModelPackage.QUERY_UPDATE_STATEMENT: return createQueryUpdateStatement();
            case SQLQueryModelPackage.UPDATE_ASSIGNMENT_EXPRESSION: return createUpdateAssignmentExpression();
            case SQLQueryModelPackage.CURSOR_REFERENCE: return createCursorReference();
            case SQLQueryModelPackage.QUERY_EXPRESSION_ROOT: return createQueryExpressionRoot();
            case SQLQueryModelPackage.VALUES_ROW: return createValuesRow();
            case SQLQueryModelPackage.QUERY_VALUES: return createQueryValues();
            case SQLQueryModelPackage.TABLE_JOINED: return createTableJoined();
            case SQLQueryModelPackage.WITH_TABLE_SPECIFICATION: return createWithTableSpecification();
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED: return createSearchConditionCombined();
            case SQLQueryModelPackage.ORDER_BY_VALUE_EXPRESSION: return createOrderByValueExpression();
            case SQLQueryModelPackage.QUERY_COMBINED: return createQueryCombined();
            case SQLQueryModelPackage.QUERY_SELECT: return createQuerySelect();
            case SQLQueryModelPackage.RESULT_TABLE_ALL_COLUMNS: return createResultTableAllColumns();
            case SQLQueryModelPackage.RESULT_COLUMN: return createResultColumn();
            case SQLQueryModelPackage.PREDICATE_BASIC: return createPredicateBasic();
            case SQLQueryModelPackage.PREDICATE_BETWEEN: return createPredicateBetween();
            case SQLQueryModelPackage.PREDICATE_EXISTS: return createPredicateExists();
            case SQLQueryModelPackage.PREDICATE_LIKE: return createPredicateLike();
            case SQLQueryModelPackage.PREDICATE_IS_NULL: return createPredicateIsNull();
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT: return createPredicateQuantifiedValueSelect();
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_ROW_SELECT: return createPredicateQuantifiedRowSelect();
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_SELECT: return createPredicateInValueSelect();
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_LIST: return createPredicateInValueList();
            case SQLQueryModelPackage.PREDICATE_IN_VALUE_ROW_SELECT: return createPredicateInValueRowSelect();
            case SQLQueryModelPackage.VALUE_EXPRESSION_SIMPLE: return createValueExpressionSimple();
            case SQLQueryModelPackage.VALUE_EXPRESSION_COLUMN: return createValueExpressionColumn();
            case SQLQueryModelPackage.VALUE_EXPRESSION_VARIABLE: return createValueExpressionVariable();
            case SQLQueryModelPackage.VALUE_EXPRESSION_SCALAR_SELECT: return createValueExpressionScalarSelect();
            case SQLQueryModelPackage.VALUE_EXPRESSION_LABELED_DURATION: return createValueExpressionLabeledDuration();
            case SQLQueryModelPackage.VALUE_EXPRESSION_CAST: return createValueExpressionCast();
            case SQLQueryModelPackage.VALUE_EXPRESSION_NULL_VALUE: return createValueExpressionNullValue();
            case SQLQueryModelPackage.VALUE_EXPRESSION_DEFAULT_VALUE: return createValueExpressionDefaultValue();
            case SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION: return createValueExpressionFunction();
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED: return createValueExpressionCombined();
            case SQLQueryModelPackage.GROUPING_SETS: return createGroupingSets();
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_SUBLIST: return createGroupingSetsElementSublist();
            case SQLQueryModelPackage.GROUPING_SETS_ELEMENT_EXPRESSION: return createGroupingSetsElementExpression();
            case SQLQueryModelPackage.SUPER_GROUP: return createSuperGroup();
            case SQLQueryModelPackage.GROUPING_EXPRESSION: return createGroupingExpression();
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_SUBLIST: return createSuperGroupElementSublist();
            case SQLQueryModelPackage.SUPER_GROUP_ELEMENT_EXPRESSION: return createSuperGroupElementExpression();
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH: return createValueExpressionCaseSearch();
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE: return createValueExpressionCaseSimple();
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_ELSE: return createValueExpressionCaseElse();
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT: return createValueExpressionCaseSearchContent();
            case SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SIMPLE_CONTENT: return createValueExpressionCaseSimpleContent();
            case SQLQueryModelPackage.TABLE_IN_DATABASE: return createTableInDatabase();
            case SQLQueryModelPackage.TABLE_FUNCTION: return createTableFunction();
            case SQLQueryModelPackage.COLUMN_NAME: return createColumnName();
            case SQLQueryModelPackage.TABLE_NESTED: return createTableNested();
            case SQLQueryModelPackage.QUERY_MERGE_STATEMENT: return createQueryMergeStatement();
            case SQLQueryModelPackage.SEARCH_CONDITION_NESTED: return createSearchConditionNested();
            case SQLQueryModelPackage.VALUE_EXPRESSION_NESTED: return createValueExpressionNested();
            case SQLQueryModelPackage.ORDER_BY_ORDINAL: return createOrderByOrdinal();
            case SQLQueryModelPackage.TABLE_CORRELATION: return createTableCorrelation();
            case SQLQueryModelPackage.UPDATE_SOURCE: return createUpdateSource();
            case SQLQueryModelPackage.UPDATE_SOURCE_EXPR_LIST: return createUpdateSourceExprList();
            case SQLQueryModelPackage.UPDATE_SOURCE_QUERY: return createUpdateSourceQuery();
            case SQLQueryModelPackage.ORDER_BY_RESULT_COLUMN: return createOrderByResultColumn();
            case SQLQueryModelPackage.WITH_TABLE_REFERENCE: return createWithTableReference();
            case SQLQueryModelPackage.QUERY_NESTED: return createQueryNested();
            case SQLQueryModelPackage.VALUE_EXPRESSION_ROW: return createValueExpressionRow();
            case SQLQueryModelPackage.MERGE_TARGET_TABLE: return createMergeTargetTable();
            case SQLQueryModelPackage.MERGE_SOURCE_TABLE: return createMergeSourceTable();
            case SQLQueryModelPackage.MERGE_ON_CONDITION: return createMergeOnCondition();
            case SQLQueryModelPackage.MERGE_UPDATE_SPECIFICATION: return createMergeUpdateSpecification();
            case SQLQueryModelPackage.MERGE_INSERT_SPECIFICATION: return createMergeInsertSpecification();
            case SQLQueryModelPackage.MERGE_OPERATION_SPECIFICATION: return createMergeOperationSpecification();
            case SQLQueryModelPackage.UPDATE_OF_COLUMN: return createUpdateOfColumn();
            case SQLQueryModelPackage.UPDATABILITY_EXPRESSION: return createUpdatabilityExpression();
            case SQLQueryModelPackage.CALL_STATEMENT: return createCallStatement();
            case SQLQueryModelPackage.PROCEDURE_REFERENCE: return createProcedureReference();
            case SQLQueryModelPackage.TABLE_QUERY_LATERAL: return createTableQueryLateral();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case SQLQueryModelPackage.SUPER_GROUP_TYPE:
                return createSuperGroupTypeFromString(eDataType, initialValue);
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_TYPE:
                return createPredicateQuantifiedTypeFromString(eDataType, initialValue);
            case SQLQueryModelPackage.PREDICATE_COMPARISON_OPERATOR:
                return createPredicateComparisonOperatorFromString(eDataType, initialValue);
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED_OPERATOR:
                return createSearchConditionCombinedOperatorFromString(eDataType, initialValue);
            case SQLQueryModelPackage.TABLE_JOINED_OPERATOR:
                return createTableJoinedOperatorFromString(eDataType, initialValue);
            case SQLQueryModelPackage.QUERY_COMBINED_OPERATOR:
                return createQueryCombinedOperatorFromString(eDataType, initialValue);
            case SQLQueryModelPackage.VALUE_EXPRESSION_UNARY_OPERATOR:
                return createValueExpressionUnaryOperatorFromString(eDataType, initialValue);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED_OPERATOR:
                return createValueExpressionCombinedOperatorFromString(eDataType, initialValue);
            case SQLQueryModelPackage.VALUE_EXPRESSION_LABELED_DURATION_TYPE:
                return createValueExpressionLabeledDurationTypeFromString(eDataType, initialValue);
            case SQLQueryModelPackage.NULL_ORDERING_TYPE:
                return createNullOrderingTypeFromString(eDataType, initialValue);
            case SQLQueryModelPackage.ORDERING_SPEC_TYPE:
                return createOrderingSpecTypeFromString(eDataType, initialValue);
            case SQLQueryModelPackage.UPDATABILITY_TYPE:
                return createUpdatabilityTypeFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case SQLQueryModelPackage.SUPER_GROUP_TYPE:
                return convertSuperGroupTypeToString(eDataType, instanceValue);
            case SQLQueryModelPackage.PREDICATE_QUANTIFIED_TYPE:
                return convertPredicateQuantifiedTypeToString(eDataType, instanceValue);
            case SQLQueryModelPackage.PREDICATE_COMPARISON_OPERATOR:
                return convertPredicateComparisonOperatorToString(eDataType, instanceValue);
            case SQLQueryModelPackage.SEARCH_CONDITION_COMBINED_OPERATOR:
                return convertSearchConditionCombinedOperatorToString(eDataType, instanceValue);
            case SQLQueryModelPackage.TABLE_JOINED_OPERATOR:
                return convertTableJoinedOperatorToString(eDataType, instanceValue);
            case SQLQueryModelPackage.QUERY_COMBINED_OPERATOR:
                return convertQueryCombinedOperatorToString(eDataType, instanceValue);
            case SQLQueryModelPackage.VALUE_EXPRESSION_UNARY_OPERATOR:
                return convertValueExpressionUnaryOperatorToString(eDataType, instanceValue);
            case SQLQueryModelPackage.VALUE_EXPRESSION_COMBINED_OPERATOR:
                return convertValueExpressionCombinedOperatorToString(eDataType, instanceValue);
            case SQLQueryModelPackage.VALUE_EXPRESSION_LABELED_DURATION_TYPE:
                return convertValueExpressionLabeledDurationTypeToString(eDataType, instanceValue);
            case SQLQueryModelPackage.NULL_ORDERING_TYPE:
                return convertNullOrderingTypeToString(eDataType, instanceValue);
            case SQLQueryModelPackage.ORDERING_SPEC_TYPE:
                return convertOrderingSpecTypeToString(eDataType, instanceValue);
            case SQLQueryModelPackage.UPDATABILITY_TYPE:
                return convertUpdatabilityTypeToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QueryDeleteStatement createQueryDeleteStatement() {
        QueryDeleteStatementImpl queryDeleteStatement = new QueryDeleteStatementImpl();
        return queryDeleteStatement;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QueryInsertStatement createQueryInsertStatement() {
        QueryInsertStatementImpl queryInsertStatement = new QueryInsertStatementImpl();
        return queryInsertStatement;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QuerySelectStatement createQuerySelectStatement() {
        QuerySelectStatementImpl querySelectStatement = new QuerySelectStatementImpl();
        return querySelectStatement;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QueryUpdateStatement createQueryUpdateStatement() {
        QueryUpdateStatementImpl queryUpdateStatement = new QueryUpdateStatementImpl();
        return queryUpdateStatement;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public UpdateAssignmentExpression createUpdateAssignmentExpression() {
        UpdateAssignmentExpressionImpl updateAssignmentExpression = new UpdateAssignmentExpressionImpl();
        return updateAssignmentExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CursorReference createCursorReference() {
        CursorReferenceImpl cursorReference = new CursorReferenceImpl();
        return cursorReference;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QueryExpressionRoot createQueryExpressionRoot() {
        QueryExpressionRootImpl queryExpressionRoot = new QueryExpressionRootImpl();
        return queryExpressionRoot;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValuesRow createValuesRow() {
        ValuesRowImpl valuesRow = new ValuesRowImpl();
        return valuesRow;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QueryValues createQueryValues() {
        QueryValuesImpl queryValues = new QueryValuesImpl();
        return queryValues;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TableJoined createTableJoined() {
        TableJoinedImpl tableJoined = new TableJoinedImpl();
        return tableJoined;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public WithTableSpecification createWithTableSpecification() {
        WithTableSpecificationImpl withTableSpecification = new WithTableSpecificationImpl();
        return withTableSpecification;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SearchConditionCombined createSearchConditionCombined() {
        SearchConditionCombinedImpl searchConditionCombined = new SearchConditionCombinedImpl();
        return searchConditionCombined;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OrderByValueExpression createOrderByValueExpression() {
        OrderByValueExpressionImpl orderByValueExpression = new OrderByValueExpressionImpl();
        return orderByValueExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QueryCombined createQueryCombined() {
        QueryCombinedImpl queryCombined = new QueryCombinedImpl();
        return queryCombined;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QuerySelect createQuerySelect() {
        QuerySelectImpl querySelect = new QuerySelectImpl();
        return querySelect;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ResultTableAllColumns createResultTableAllColumns() {
        ResultTableAllColumnsImpl resultTableAllColumns = new ResultTableAllColumnsImpl();
        return resultTableAllColumns;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ResultColumn createResultColumn() {
        ResultColumnImpl resultColumn = new ResultColumnImpl();
        return resultColumn;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateBasic createPredicateBasic() {
        PredicateBasicImpl predicateBasic = new PredicateBasicImpl();
        return predicateBasic;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateBetween createPredicateBetween() {
        PredicateBetweenImpl predicateBetween = new PredicateBetweenImpl();
        return predicateBetween;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateExists createPredicateExists() {
        PredicateExistsImpl predicateExists = new PredicateExistsImpl();
        return predicateExists;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateLike createPredicateLike() {
        PredicateLikeImpl predicateLike = new PredicateLikeImpl();
        return predicateLike;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateIsNull createPredicateIsNull() {
        PredicateIsNullImpl predicateIsNull = new PredicateIsNullImpl();
        return predicateIsNull;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateQuantifiedValueSelect createPredicateQuantifiedValueSelect() {
        PredicateQuantifiedValueSelectImpl predicateQuantifiedValueSelect = new PredicateQuantifiedValueSelectImpl();
        return predicateQuantifiedValueSelect;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateQuantifiedRowSelect createPredicateQuantifiedRowSelect() {
        PredicateQuantifiedRowSelectImpl predicateQuantifiedRowSelect = new PredicateQuantifiedRowSelectImpl();
        return predicateQuantifiedRowSelect;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateInValueSelect createPredicateInValueSelect() {
        PredicateInValueSelectImpl predicateInValueSelect = new PredicateInValueSelectImpl();
        return predicateInValueSelect;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateInValueList createPredicateInValueList() {
        PredicateInValueListImpl predicateInValueList = new PredicateInValueListImpl();
        return predicateInValueList;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateInValueRowSelect createPredicateInValueRowSelect() {
        PredicateInValueRowSelectImpl predicateInValueRowSelect = new PredicateInValueRowSelectImpl();
        return predicateInValueRowSelect;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionSimple createValueExpressionSimple() {
        ValueExpressionSimpleImpl valueExpressionSimple = new ValueExpressionSimpleImpl();
        return valueExpressionSimple;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionColumn createValueExpressionColumn() {
        ValueExpressionColumnImpl valueExpressionColumn = new ValueExpressionColumnImpl();
        return valueExpressionColumn;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionVariable createValueExpressionVariable() {
        ValueExpressionVariableImpl valueExpressionVariable = new ValueExpressionVariableImpl();
        return valueExpressionVariable;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionScalarSelect createValueExpressionScalarSelect() {
        ValueExpressionScalarSelectImpl valueExpressionScalarSelect = new ValueExpressionScalarSelectImpl();
        return valueExpressionScalarSelect;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionLabeledDuration createValueExpressionLabeledDuration() {
        ValueExpressionLabeledDurationImpl valueExpressionLabeledDuration = new ValueExpressionLabeledDurationImpl();
        return valueExpressionLabeledDuration;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionCast createValueExpressionCast() {
        ValueExpressionCastImpl valueExpressionCast = new ValueExpressionCastImpl();
        return valueExpressionCast;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionNullValue createValueExpressionNullValue() {
        ValueExpressionNullValueImpl valueExpressionNullValue = new ValueExpressionNullValueImpl();
        return valueExpressionNullValue;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionDefaultValue createValueExpressionDefaultValue() {
        ValueExpressionDefaultValueImpl valueExpressionDefaultValue = new ValueExpressionDefaultValueImpl();
        return valueExpressionDefaultValue;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionFunction createValueExpressionFunction() {
        ValueExpressionFunctionImpl valueExpressionFunction = new ValueExpressionFunctionImpl();
        return valueExpressionFunction;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionCombined createValueExpressionCombined() {
        ValueExpressionCombinedImpl valueExpressionCombined = new ValueExpressionCombinedImpl();
        return valueExpressionCombined;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public GroupingSets createGroupingSets() {
        GroupingSetsImpl groupingSets = new GroupingSetsImpl();
        return groupingSets;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public GroupingSetsElementSublist createGroupingSetsElementSublist() {
        GroupingSetsElementSublistImpl groupingSetsElementSublist = new GroupingSetsElementSublistImpl();
        return groupingSetsElementSublist;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public GroupingSetsElementExpression createGroupingSetsElementExpression() {
        GroupingSetsElementExpressionImpl groupingSetsElementExpression = new GroupingSetsElementExpressionImpl();
        return groupingSetsElementExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SuperGroup createSuperGroup() {
        SuperGroupImpl superGroup = new SuperGroupImpl();
        return superGroup;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public GroupingExpression createGroupingExpression() {
        GroupingExpressionImpl groupingExpression = new GroupingExpressionImpl();
        return groupingExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SuperGroupElementSublist createSuperGroupElementSublist() {
        SuperGroupElementSublistImpl superGroupElementSublist = new SuperGroupElementSublistImpl();
        return superGroupElementSublist;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SuperGroupElementExpression createSuperGroupElementExpression() {
        SuperGroupElementExpressionImpl superGroupElementExpression = new SuperGroupElementExpressionImpl();
        return superGroupElementExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionCaseSearch createValueExpressionCaseSearch() {
        ValueExpressionCaseSearchImpl valueExpressionCaseSearch = new ValueExpressionCaseSearchImpl();
        return valueExpressionCaseSearch;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionCaseSimple createValueExpressionCaseSimple() {
        ValueExpressionCaseSimpleImpl valueExpressionCaseSimple = new ValueExpressionCaseSimpleImpl();
        return valueExpressionCaseSimple;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionCaseElse createValueExpressionCaseElse() {
        ValueExpressionCaseElseImpl valueExpressionCaseElse = new ValueExpressionCaseElseImpl();
        return valueExpressionCaseElse;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionCaseSearchContent createValueExpressionCaseSearchContent() {
        ValueExpressionCaseSearchContentImpl valueExpressionCaseSearchContent = new ValueExpressionCaseSearchContentImpl();
        return valueExpressionCaseSearchContent;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionCaseSimpleContent createValueExpressionCaseSimpleContent() {
        ValueExpressionCaseSimpleContentImpl valueExpressionCaseSimpleContent = new ValueExpressionCaseSimpleContentImpl();
        return valueExpressionCaseSimpleContent;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TableInDatabase createTableInDatabase() {
        TableInDatabaseImpl tableInDatabase = new TableInDatabaseImpl();
        return tableInDatabase;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TableFunction createTableFunction() {
        TableFunctionImpl tableFunction = new TableFunctionImpl();
        return tableFunction;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ColumnName createColumnName() {
        ColumnNameImpl columnName = new ColumnNameImpl();
        return columnName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TableNested createTableNested() {
        TableNestedImpl tableNested = new TableNestedImpl();
        return tableNested;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QueryMergeStatement createQueryMergeStatement() {
        QueryMergeStatementImpl queryMergeStatement = new QueryMergeStatementImpl();
        return queryMergeStatement;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SearchConditionNested createSearchConditionNested() {
        SearchConditionNestedImpl searchConditionNested = new SearchConditionNestedImpl();
        return searchConditionNested;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionNested createValueExpressionNested() {
        ValueExpressionNestedImpl valueExpressionNested = new ValueExpressionNestedImpl();
        return valueExpressionNested;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OrderByOrdinal createOrderByOrdinal() {
        OrderByOrdinalImpl orderByOrdinal = new OrderByOrdinalImpl();
        return orderByOrdinal;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TableCorrelation createTableCorrelation() {
        TableCorrelationImpl tableCorrelation = new TableCorrelationImpl();
        return tableCorrelation;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public UpdateSource createUpdateSource() {
        UpdateSourceImpl updateSource = new UpdateSourceImpl();
        return updateSource;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public UpdateSourceExprList createUpdateSourceExprList() {
        UpdateSourceExprListImpl updateSourceExprList = new UpdateSourceExprListImpl();
        return updateSourceExprList;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public UpdateSourceQuery createUpdateSourceQuery() {
        UpdateSourceQueryImpl updateSourceQuery = new UpdateSourceQueryImpl();
        return updateSourceQuery;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OrderByResultColumn createOrderByResultColumn() {
        OrderByResultColumnImpl orderByResultColumn = new OrderByResultColumnImpl();
        return orderByResultColumn;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public WithTableReference createWithTableReference() {
        WithTableReferenceImpl withTableReference = new WithTableReferenceImpl();
        return withTableReference;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QueryNested createQueryNested() {
        QueryNestedImpl queryNested = new QueryNestedImpl();
        return queryNested;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueExpressionRow createValueExpressionRow() {
        ValueExpressionRowImpl valueExpressionRow = new ValueExpressionRowImpl();
        return valueExpressionRow;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeTargetTable createMergeTargetTable() {
        MergeTargetTableImpl mergeTargetTable = new MergeTargetTableImpl();
        return mergeTargetTable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeSourceTable createMergeSourceTable() {
        MergeSourceTableImpl mergeSourceTable = new MergeSourceTableImpl();
        return mergeSourceTable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeOnCondition createMergeOnCondition() {
        MergeOnConditionImpl mergeOnCondition = new MergeOnConditionImpl();
        return mergeOnCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeUpdateSpecification createMergeUpdateSpecification() {
        MergeUpdateSpecificationImpl mergeUpdateSpecification = new MergeUpdateSpecificationImpl();
        return mergeUpdateSpecification;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeInsertSpecification createMergeInsertSpecification() {
        MergeInsertSpecificationImpl mergeInsertSpecification = new MergeInsertSpecificationImpl();
        return mergeInsertSpecification;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MergeOperationSpecification createMergeOperationSpecification() {
        MergeOperationSpecificationImpl mergeOperationSpecification = new MergeOperationSpecificationImpl();
        return mergeOperationSpecification;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UpdateOfColumn createUpdateOfColumn() {
        UpdateOfColumnImpl updateOfColumn = new UpdateOfColumnImpl();
        return updateOfColumn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UpdatabilityExpression createUpdatabilityExpression() {
        UpdatabilityExpressionImpl updatabilityExpression = new UpdatabilityExpressionImpl();
        return updatabilityExpression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CallStatement createCallStatement() {
        CallStatementImpl callStatement = new CallStatementImpl();
        return callStatement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProcedureReference createProcedureReference() {
        ProcedureReferenceImpl procedureReference = new ProcedureReferenceImpl();
        return procedureReference;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableQueryLateral createTableQueryLateral() {
        TableQueryLateralImpl tableQueryLateral = new TableQueryLateralImpl();
        return tableQueryLateral;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SuperGroupType createSuperGroupTypeFromString(EDataType eDataType, String initialValue) {
        SuperGroupType result = SuperGroupType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertSuperGroupTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateQuantifiedType createPredicateQuantifiedTypeFromString(EDataType eDataType, String initialValue) {
        PredicateQuantifiedType result = PredicateQuantifiedType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertPredicateQuantifiedTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PredicateComparisonOperator createPredicateComparisonOperatorFromString(EDataType eDataType, String initialValue) {
        PredicateComparisonOperator result = PredicateComparisonOperator.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertPredicateComparisonOperatorToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SearchConditionCombinedOperator createSearchConditionCombinedOperatorFromString(EDataType eDataType, String initialValue) {
        SearchConditionCombinedOperator result = SearchConditionCombinedOperator.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertSearchConditionCombinedOperatorToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TableJoinedOperator createTableJoinedOperatorFromString(EDataType eDataType, String initialValue) {
        TableJoinedOperator result = TableJoinedOperator.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertTableJoinedOperatorToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QueryCombinedOperator createQueryCombinedOperatorFromString(EDataType eDataType, String initialValue) {
        QueryCombinedOperator result = QueryCombinedOperator.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertQueryCombinedOperatorToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionUnaryOperator createValueExpressionUnaryOperatorFromString(EDataType eDataType, String initialValue) {
        ValueExpressionUnaryOperator result = ValueExpressionUnaryOperator.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertValueExpressionUnaryOperatorToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionCombinedOperator createValueExpressionCombinedOperatorFromString(EDataType eDataType, String initialValue) {
        ValueExpressionCombinedOperator result = ValueExpressionCombinedOperator.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertValueExpressionCombinedOperatorToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValueExpressionLabeledDurationType createValueExpressionLabeledDurationTypeFromString(EDataType eDataType, String initialValue) {
        ValueExpressionLabeledDurationType result = ValueExpressionLabeledDurationType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertValueExpressionLabeledDurationTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NullOrderingType createNullOrderingTypeFromString(EDataType eDataType, String initialValue) {
        NullOrderingType result = NullOrderingType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertNullOrderingTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OrderingSpecType createOrderingSpecTypeFromString(EDataType eDataType, String initialValue) {
        OrderingSpecType result = OrderingSpecType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertOrderingSpecTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UpdatabilityType createUpdatabilityTypeFromString(EDataType eDataType, String initialValue) {
        UpdatabilityType result = UpdatabilityType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertUpdatabilityTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SQLQueryModelPackage getSQLQueryModelPackage() {
        return (SQLQueryModelPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	public static SQLQueryModelPackage getPackage() {
        return SQLQueryModelPackage.eINSTANCE;
    }

} //SQLQueryModelFactoryImpl
