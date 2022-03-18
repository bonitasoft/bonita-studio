/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLQueryModelFactory.java,v 1.5 2010/02/25 01:57:23 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage
 * @generated
 */
public interface SQLQueryModelFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	SQLQueryModelFactory eINSTANCE = org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Query Delete Statement</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Query Delete Statement</em>'.
     * @generated
     */
	QueryDeleteStatement createQueryDeleteStatement();

	/**
     * Returns a new object of class '<em>Query Insert Statement</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Query Insert Statement</em>'.
     * @generated
     */
	QueryInsertStatement createQueryInsertStatement();

	/**
     * Returns a new object of class '<em>Query Select Statement</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Query Select Statement</em>'.
     * @generated
     */
	QuerySelectStatement createQuerySelectStatement();

	/**
     * Returns a new object of class '<em>Query Update Statement</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Query Update Statement</em>'.
     * @generated
     */
	QueryUpdateStatement createQueryUpdateStatement();

	/**
     * Returns a new object of class '<em>Update Assignment Expression</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Update Assignment Expression</em>'.
     * @generated
     */
	UpdateAssignmentExpression createUpdateAssignmentExpression();

	/**
     * Returns a new object of class '<em>Cursor Reference</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Cursor Reference</em>'.
     * @generated
     */
	CursorReference createCursorReference();

	/**
     * Returns a new object of class '<em>Query Expression Root</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Query Expression Root</em>'.
     * @generated
     */
	QueryExpressionRoot createQueryExpressionRoot();

	/**
     * Returns a new object of class '<em>Values Row</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Values Row</em>'.
     * @generated
     */
	ValuesRow createValuesRow();

	/**
     * Returns a new object of class '<em>Query Values</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Query Values</em>'.
     * @generated
     */
	QueryValues createQueryValues();

	/**
     * Returns a new object of class '<em>Table Joined</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Table Joined</em>'.
     * @generated
     */
	TableJoined createTableJoined();

	/**
     * Returns a new object of class '<em>With Table Specification</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>With Table Specification</em>'.
     * @generated
     */
	WithTableSpecification createWithTableSpecification();

	/**
     * Returns a new object of class '<em>Search Condition Combined</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Search Condition Combined</em>'.
     * @generated
     */
	SearchConditionCombined createSearchConditionCombined();

	/**
     * Returns a new object of class '<em>Order By Value Expression</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Order By Value Expression</em>'.
     * @generated
     */
	OrderByValueExpression createOrderByValueExpression();

	/**
     * Returns a new object of class '<em>Query Combined</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Query Combined</em>'.
     * @generated
     */
	QueryCombined createQueryCombined();

	/**
     * Returns a new object of class '<em>Query Select</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Query Select</em>'.
     * @generated
     */
	QuerySelect createQuerySelect();

	/**
     * Returns a new object of class '<em>Result Table All Columns</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Result Table All Columns</em>'.
     * @generated
     */
	ResultTableAllColumns createResultTableAllColumns();

	/**
     * Returns a new object of class '<em>Result Column</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Result Column</em>'.
     * @generated
     */
	ResultColumn createResultColumn();

	/**
     * Returns a new object of class '<em>Predicate Basic</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Predicate Basic</em>'.
     * @generated
     */
	PredicateBasic createPredicateBasic();

	/**
     * Returns a new object of class '<em>Predicate Between</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Predicate Between</em>'.
     * @generated
     */
	PredicateBetween createPredicateBetween();

	/**
     * Returns a new object of class '<em>Predicate Exists</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Predicate Exists</em>'.
     * @generated
     */
	PredicateExists createPredicateExists();

	/**
     * Returns a new object of class '<em>Predicate Like</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Predicate Like</em>'.
     * @generated
     */
	PredicateLike createPredicateLike();

	/**
     * Returns a new object of class '<em>Predicate Is Null</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Predicate Is Null</em>'.
     * @generated
     */
	PredicateIsNull createPredicateIsNull();

	/**
     * Returns a new object of class '<em>Predicate Quantified Value Select</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Predicate Quantified Value Select</em>'.
     * @generated
     */
	PredicateQuantifiedValueSelect createPredicateQuantifiedValueSelect();

	/**
     * Returns a new object of class '<em>Predicate Quantified Row Select</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Predicate Quantified Row Select</em>'.
     * @generated
     */
	PredicateQuantifiedRowSelect createPredicateQuantifiedRowSelect();

	/**
     * Returns a new object of class '<em>Predicate In Value Select</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Predicate In Value Select</em>'.
     * @generated
     */
	PredicateInValueSelect createPredicateInValueSelect();

	/**
     * Returns a new object of class '<em>Predicate In Value List</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Predicate In Value List</em>'.
     * @generated
     */
	PredicateInValueList createPredicateInValueList();

	/**
     * Returns a new object of class '<em>Predicate In Value Row Select</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Predicate In Value Row Select</em>'.
     * @generated
     */
	PredicateInValueRowSelect createPredicateInValueRowSelect();

	/**
     * Returns a new object of class '<em>Value Expression Simple</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Simple</em>'.
     * @generated
     */
	ValueExpressionSimple createValueExpressionSimple();

	/**
     * Returns a new object of class '<em>Value Expression Column</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Column</em>'.
     * @generated
     */
	ValueExpressionColumn createValueExpressionColumn();

	/**
     * Returns a new object of class '<em>Value Expression Variable</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Variable</em>'.
     * @generated
     */
	ValueExpressionVariable createValueExpressionVariable();

	/**
     * Returns a new object of class '<em>Value Expression Scalar Select</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Scalar Select</em>'.
     * @generated
     */
	ValueExpressionScalarSelect createValueExpressionScalarSelect();

	/**
     * Returns a new object of class '<em>Value Expression Labeled Duration</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Labeled Duration</em>'.
     * @generated
     */
	ValueExpressionLabeledDuration createValueExpressionLabeledDuration();

	/**
     * Returns a new object of class '<em>Value Expression Cast</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Cast</em>'.
     * @generated
     */
	ValueExpressionCast createValueExpressionCast();

	/**
     * Returns a new object of class '<em>Value Expression Null Value</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Null Value</em>'.
     * @generated
     */
	ValueExpressionNullValue createValueExpressionNullValue();

	/**
     * Returns a new object of class '<em>Value Expression Default Value</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Default Value</em>'.
     * @generated
     */
	ValueExpressionDefaultValue createValueExpressionDefaultValue();

	/**
     * Returns a new object of class '<em>Value Expression Function</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Function</em>'.
     * @generated
     */
	ValueExpressionFunction createValueExpressionFunction();

	/**
     * Returns a new object of class '<em>Value Expression Combined</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Combined</em>'.
     * @generated
     */
	ValueExpressionCombined createValueExpressionCombined();

	/**
     * Returns a new object of class '<em>Grouping Sets</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Grouping Sets</em>'.
     * @generated
     */
	GroupingSets createGroupingSets();

	/**
     * Returns a new object of class '<em>Grouping Sets Element Sublist</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Grouping Sets Element Sublist</em>'.
     * @generated
     */
	GroupingSetsElementSublist createGroupingSetsElementSublist();

	/**
     * Returns a new object of class '<em>Grouping Sets Element Expression</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Grouping Sets Element Expression</em>'.
     * @generated
     */
	GroupingSetsElementExpression createGroupingSetsElementExpression();

	/**
     * Returns a new object of class '<em>Super Group</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Super Group</em>'.
     * @generated
     */
	SuperGroup createSuperGroup();

	/**
     * Returns a new object of class '<em>Grouping Expression</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Grouping Expression</em>'.
     * @generated
     */
	GroupingExpression createGroupingExpression();

	/**
     * Returns a new object of class '<em>Super Group Element Sublist</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Super Group Element Sublist</em>'.
     * @generated
     */
	SuperGroupElementSublist createSuperGroupElementSublist();

	/**
     * Returns a new object of class '<em>Super Group Element Expression</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Super Group Element Expression</em>'.
     * @generated
     */
	SuperGroupElementExpression createSuperGroupElementExpression();

	/**
     * Returns a new object of class '<em>Value Expression Case Search</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Case Search</em>'.
     * @generated
     */
	ValueExpressionCaseSearch createValueExpressionCaseSearch();

	/**
     * Returns a new object of class '<em>Value Expression Case Simple</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Case Simple</em>'.
     * @generated
     */
	ValueExpressionCaseSimple createValueExpressionCaseSimple();

	/**
     * Returns a new object of class '<em>Value Expression Case Else</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Case Else</em>'.
     * @generated
     */
	ValueExpressionCaseElse createValueExpressionCaseElse();

	/**
     * Returns a new object of class '<em>Value Expression Case Search Content</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Case Search Content</em>'.
     * @generated
     */
	ValueExpressionCaseSearchContent createValueExpressionCaseSearchContent();

	/**
     * Returns a new object of class '<em>Value Expression Case Simple Content</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Case Simple Content</em>'.
     * @generated
     */
	ValueExpressionCaseSimpleContent createValueExpressionCaseSimpleContent();

	/**
     * Returns a new object of class '<em>Table In Database</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Table In Database</em>'.
     * @generated
     */
	TableInDatabase createTableInDatabase();

	/**
     * Returns a new object of class '<em>Table Function</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Table Function</em>'.
     * @generated
     */
	TableFunction createTableFunction();

	/**
     * Returns a new object of class '<em>Column Name</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Column Name</em>'.
     * @generated
     */
	ColumnName createColumnName();

	/**
     * Returns a new object of class '<em>Table Nested</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Table Nested</em>'.
     * @generated
     */
	TableNested createTableNested();

	/**
     * Returns a new object of class '<em>Query Merge Statement</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Query Merge Statement</em>'.
     * @generated
     */
	QueryMergeStatement createQueryMergeStatement();

	/**
     * Returns a new object of class '<em>Search Condition Nested</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Search Condition Nested</em>'.
     * @generated
     */
	SearchConditionNested createSearchConditionNested();

	/**
     * Returns a new object of class '<em>Value Expression Nested</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Nested</em>'.
     * @generated
     */
	ValueExpressionNested createValueExpressionNested();

	/**
     * Returns a new object of class '<em>Order By Ordinal</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Order By Ordinal</em>'.
     * @generated
     */
	OrderByOrdinal createOrderByOrdinal();

	/**
     * Returns a new object of class '<em>Table Correlation</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Table Correlation</em>'.
     * @generated
     */
	TableCorrelation createTableCorrelation();

	/**
     * Returns a new object of class '<em>Update Source</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Update Source</em>'.
     * @generated
     */
	UpdateSource createUpdateSource();

	/**
     * Returns a new object of class '<em>Update Source Expr List</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Update Source Expr List</em>'.
     * @generated
     */
	UpdateSourceExprList createUpdateSourceExprList();

	/**
     * Returns a new object of class '<em>Update Source Query</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Update Source Query</em>'.
     * @generated
     */
	UpdateSourceQuery createUpdateSourceQuery();

	/**
     * Returns a new object of class '<em>Order By Result Column</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Order By Result Column</em>'.
     * @generated
     */
	OrderByResultColumn createOrderByResultColumn();

	/**
     * Returns a new object of class '<em>With Table Reference</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>With Table Reference</em>'.
     * @generated
     */
	WithTableReference createWithTableReference();

	/**
     * Returns a new object of class '<em>Query Nested</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Query Nested</em>'.
     * @generated
     */
    QueryNested createQueryNested();

    /**
     * Returns a new object of class '<em>Value Expression Row</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Value Expression Row</em>'.
     * @generated
     */
    ValueExpressionRow createValueExpressionRow();

    /**
     * Returns a new object of class '<em>Merge Target Table</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Merge Target Table</em>'.
     * @generated
     */
    MergeTargetTable createMergeTargetTable();

    /**
     * Returns a new object of class '<em>Merge Source Table</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Merge Source Table</em>'.
     * @generated
     */
    MergeSourceTable createMergeSourceTable();

    /**
     * Returns a new object of class '<em>Merge On Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Merge On Condition</em>'.
     * @generated
     */
    MergeOnCondition createMergeOnCondition();

    /**
     * Returns a new object of class '<em>Merge Update Specification</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Merge Update Specification</em>'.
     * @generated
     */
    MergeUpdateSpecification createMergeUpdateSpecification();

    /**
     * Returns a new object of class '<em>Merge Insert Specification</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Merge Insert Specification</em>'.
     * @generated
     */
    MergeInsertSpecification createMergeInsertSpecification();

    /**
     * Returns a new object of class '<em>Merge Operation Specification</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Merge Operation Specification</em>'.
     * @generated
     */
    MergeOperationSpecification createMergeOperationSpecification();

    /**
     * Returns a new object of class '<em>Update Of Column</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Update Of Column</em>'.
     * @generated
     */
    UpdateOfColumn createUpdateOfColumn();

    /**
     * Returns a new object of class '<em>Updatability Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Updatability Expression</em>'.
     * @generated
     */
    UpdatabilityExpression createUpdatabilityExpression();

    /**
     * Returns a new object of class '<em>Call Statement</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Call Statement</em>'.
     * @generated
     */
    CallStatement createCallStatement();

    /**
     * Returns a new object of class '<em>Procedure Reference</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Procedure Reference</em>'.
     * @generated
     */
    ProcedureReference createProcedureReference();

    /**
     * Returns a new object of class '<em>Table Query Lateral</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Table Query Lateral</em>'.
     * @generated
     */
    TableQueryLateral createTableQueryLateral();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	SQLQueryModelPackage getSQLQueryModelPackage();

} //SQLQueryModelFactory
