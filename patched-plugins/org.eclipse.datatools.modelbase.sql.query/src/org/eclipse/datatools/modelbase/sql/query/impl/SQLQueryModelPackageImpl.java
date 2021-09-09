/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLQueryModelPackageImpl.java,v 1.5 2010/02/25 01:57:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;

import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;

import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl;

import org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl;

import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;

import org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl;

import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;

import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl;

import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.CursorReference;
import org.eclipse.datatools.modelbase.sql.query.Grouping;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist;
import org.eclipse.datatools.modelbase.sql.query.GroupingSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeOnCondition;
import org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeSourceTable;
import org.eclipse.datatools.modelbase.sql.query.MergeTargetTable;
import org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification;
import org.eclipse.datatools.modelbase.sql.query.NullOrderingType;
import org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal;
import org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn;
import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderingSpecType;
import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator;
import org.eclipse.datatools.modelbase.sql.query.PredicateExists;
import org.eclipse.datatools.modelbase.sql.query.PredicateIn;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantified;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.ProcedureReference;
import org.eclipse.datatools.modelbase.sql.query.QueryChangeStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryNested;
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElement;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupType;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableQueryLateral;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdatabilityType;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionDefaultValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;

import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;

import org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl;

import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage;

import org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl;

import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;

import org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SQLQueryModelPackageImpl extends EPackageImpl implements SQLQueryModelPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryStatementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryDeleteStatementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryInsertStatementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass querySelectStatementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryUpdateStatementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass updateAssignmentExpressionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass cursorReferenceEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass querySearchConditionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryExpressionBodyEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryValueExpressionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryExpressionRootEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valuesRowEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryValuesEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass tableReferenceEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass tableExpressionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass tableJoinedEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass withTableSpecificationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass searchConditionCombinedEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass orderByValueExpressionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryCombinedEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass querySelectEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass groupingSpecificationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryResultSpecificationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass resultTableAllColumnsEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass resultColumnEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateBasicEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateQuantifiedEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateBetweenEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateExistsEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateInEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateLikeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateIsNullEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateQuantifiedValueSelectEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateQuantifiedRowSelectEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateInValueSelectEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateInValueListEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass predicateInValueRowSelectEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionSimpleEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionColumnEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionVariableEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionScalarSelectEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionLabeledDurationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionCaseEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionCastEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionNullValueEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionDefaultValueEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionFunctionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionCombinedEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass groupingSetsEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass groupingEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass groupingSetsElementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass groupingSetsElementSublistEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass groupingSetsElementExpressionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass superGroupEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass groupingExpressionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass superGroupElementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass superGroupElementSublistEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass superGroupElementExpressionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionCaseSearchEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionCaseSimpleEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionCaseElseEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionCaseSearchContentEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionCaseSimpleContentEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass tableInDatabaseEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass tableFunctionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass sqlQueryObjectEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryChangeStatementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass columnNameEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass tableNestedEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass queryMergeStatementEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass searchConditionNestedEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionNestedEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueExpressionAtomicEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass orderBySpecificationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass orderByOrdinalEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass tableCorrelationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass updateSourceEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass updateSourceExprListEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass updateSourceQueryEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass orderByResultColumnEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass withTableReferenceEClass = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass queryNestedEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass valueExpressionRowEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass mergeTargetTableEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass mergeSourceTableEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass mergeOnConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass mergeUpdateSpecificationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass mergeInsertSpecificationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass mergeOperationSpecificationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass updateOfColumnEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass updatabilityExpressionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass callStatementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass procedureReferenceEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass tableQueryLateralEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum superGroupTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum predicateQuantifiedTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum predicateComparisonOperatorEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum searchConditionCombinedOperatorEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum tableJoinedOperatorEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum queryCombinedOperatorEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum valueExpressionUnaryOperatorEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum valueExpressionCombinedOperatorEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum valueExpressionLabeledDurationTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum nullOrderingTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EEnum orderingSpecTypeEEnum = null;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum updatabilityTypeEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private SQLQueryModelPackageImpl() {
        super(eNS_URI, SQLQueryModelFactory.eINSTANCE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static boolean isInited = false;

	/**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link SQLQueryModelPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static SQLQueryModelPackage init() {
        if (isInited) return (SQLQueryModelPackage)EPackage.Registry.INSTANCE.getEPackage(SQLQueryModelPackage.eNS_URI);

        // Obtain or create and register package
        SQLQueryModelPackageImpl theSQLQueryModelPackage = (SQLQueryModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SQLQueryModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SQLQueryModelPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        SQLSchemaPackage.eINSTANCE.eClass();
        SQLConstraintsPackage.eINSTANCE.eClass();
        SQLDataTypesPackage.eINSTANCE.eClass();
        SQLExpressionsPackage.eINSTANCE.eClass();
        SQLRoutinesPackage.eINSTANCE.eClass();
        SQLStatementsPackage.eINSTANCE.eClass();
        SQLTablesPackage.eINSTANCE.eClass();
        SQLAccessControlPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theSQLQueryModelPackage.createPackageContents();

        // Initialize created meta-data
        theSQLQueryModelPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theSQLQueryModelPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(SQLQueryModelPackage.eNS_URI, theSQLQueryModelPackage);
        return theSQLQueryModelPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryStatement() {
        return queryStatementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryDeleteStatement() {
        return queryDeleteStatementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryDeleteStatement_WhereCurrentOfClause() {
        return (EReference)queryDeleteStatementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryDeleteStatement_WhereClause() {
        return (EReference)queryDeleteStatementEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryDeleteStatement_TargetTable() {
        return (EReference)queryDeleteStatementEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryInsertStatement() {
        return queryInsertStatementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryInsertStatement_SourceQuery() {
        return (EReference)queryInsertStatementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryInsertStatement_SourceValuesRowList() {
        return (EReference)queryInsertStatementEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryInsertStatement_TargetTable() {
        return (EReference)queryInsertStatementEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryInsertStatement_TargetColumnList() {
        return (EReference)queryInsertStatementEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQuerySelectStatement() {
        return querySelectStatementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySelectStatement_QueryExpr() {
        return (EReference)querySelectStatementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySelectStatement_OrderByClause() {
        return (EReference)querySelectStatementEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQuerySelectStatement_UpdatabilityExpr() {
        return (EReference)querySelectStatementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryUpdateStatement() {
        return queryUpdateStatementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryUpdateStatement_AssignmentClause() {
        return (EReference)queryUpdateStatementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryUpdateStatement_WhereCurrentOfClause() {
        return (EReference)queryUpdateStatementEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryUpdateStatement_WhereClause() {
        return (EReference)queryUpdateStatementEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryUpdateStatement_TargetTable() {
        return (EReference)queryUpdateStatementEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getUpdateAssignmentExpression() {
        return updateAssignmentExpressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getUpdateAssignmentExpression_UpdateStatement() {
        return (EReference)updateAssignmentExpressionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getUpdateAssignmentExpression_TargetColumnList() {
        return (EReference)updateAssignmentExpressionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getUpdateAssignmentExpression_UpdateSource() {
        return (EReference)updateAssignmentExpressionEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUpdateAssignmentExpression_MergeUpdateSpec() {
        return (EReference)updateAssignmentExpressionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getCursorReference() {
        return cursorReferenceEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getCursorReference_UpdateStatement() {
        return (EReference)cursorReferenceEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getCursorReference_DeleteStatement() {
        return (EReference)cursorReferenceEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQuerySearchCondition() {
        return querySearchConditionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getQuerySearchCondition_NegatedCondition() {
        return (EAttribute)querySearchConditionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySearchCondition_UpdateStatement() {
        return (EReference)querySearchConditionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySearchCondition_DeleteStatement() {
        return (EReference)querySearchConditionEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySearchCondition_TableJoined() {
        return (EReference)querySearchConditionEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySearchCondition_CombinedLeft() {
        return (EReference)querySearchConditionEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySearchCondition_CombinedRight() {
        return (EReference)querySearchConditionEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySearchCondition_QuerySelectHaving() {
        return (EReference)querySearchConditionEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySearchCondition_QuerySelectWhere() {
        return (EReference)querySearchConditionEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySearchCondition_ValueExprCaseSearchContent() {
        return (EReference)querySearchConditionEClass.getEStructuralFeatures().get(8);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySearchCondition_Nest() {
        return (EReference)querySearchConditionEClass.getEStructuralFeatures().get(9);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQuerySearchCondition_MergeOnCondition() {
        return (EReference)querySearchConditionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryExpressionBody() {
        return queryExpressionBodyEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getQueryExpressionBody_RowFetchLimit() {
        return (EAttribute)queryExpressionBodyEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionBody_QueryExpression() {
        return (EReference)queryExpressionBodyEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionBody_CombinedLeft() {
        return (EReference)queryExpressionBodyEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionBody_CombinedRight() {
        return (EReference)queryExpressionBodyEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionBody_PredicateExists() {
        return (EReference)queryExpressionBodyEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionBody_UpdateSourceQuery() {
        return (EReference)queryExpressionBodyEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionBody_WithTableSpecification() {
        return (EReference)queryExpressionBodyEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueryExpressionBody_QueryNest() {
        return (EReference)queryExpressionBodyEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueryExpressionBody_SortSpecList() {
        return (EReference)queryExpressionBodyEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryValueExpression() {
        return queryValueExpressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getQueryValueExpression_UnaryOperator() {
        return (EAttribute)queryValueExpressionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_DataType() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ValuesRow() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_OrderByValueExpr() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ResultColumn() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_BasicRight() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_BasicLeft() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_LikePattern() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_LikeMatching() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(8);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_PredicateNull() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(9);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_InValueListRight() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(10);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_InValueListLeft() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(11);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_InValueRowSelectLeft() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(12);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_InValueSelectLeft() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(13);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_QuantifiedRowSelectLeft() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(14);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_QuantifiedValueSelectLeft() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(15);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_BetweenLeft() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(16);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_BetweenRight1() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(17);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_BetweenRight2() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(18);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ValueExprCast() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(19);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ValueExprFunction() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(20);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ValueExprCombinedLeft() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(21);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ValueExprCombinedRight() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(22);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_GroupingExpr() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(23);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ValueExprCaseElse() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(24);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ValueExprCaseSimple() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(25);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ValueExprCaseSimpleContentWhen() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(26);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ValueExprCaseSimpleContentResult() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(27);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ValueExprCaseSearchContent() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(28);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_LikeEscape() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(29);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_ValueExprLabeledDuration() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(30);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_Nest() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(31);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValueExpression_UpdateSourceExprList() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(32);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueryValueExpression_TableFunction() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(33);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueryValueExpression_ValueExprRow() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(34);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueryValueExpression_CallStatement() {
        return (EReference)queryValueExpressionEClass.getEStructuralFeatures().get(35);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryExpressionRoot() {
        return queryExpressionRootEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionRoot_InsertStatement() {
        return (EReference)queryExpressionRootEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionRoot_SelectStatement() {
        return (EReference)queryExpressionRootEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionRoot_WithClause() {
        return (EReference)queryExpressionRootEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionRoot_Query() {
        return (EReference)queryExpressionRootEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionRoot_InValueRowSelectRight() {
        return (EReference)queryExpressionRootEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionRoot_InValueSelectRight() {
        return (EReference)queryExpressionRootEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionRoot_QuantifiedRowSelectRight() {
        return (EReference)queryExpressionRootEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryExpressionRoot_QuantifiedValueSelectRight() {
        return (EReference)queryExpressionRootEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueryExpressionRoot_ValExprScalarSelect() {
        return (EReference)queryExpressionRootEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValuesRow() {
        return valuesRowEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValuesRow_InsertStatement() {
        return (EReference)valuesRowEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValuesRow_ExprList() {
        return (EReference)valuesRowEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValuesRow_QueryValues() {
        return (EReference)valuesRowEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryValues() {
        return queryValuesEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryValues_ValuesRowList() {
        return (EReference)queryValuesEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getTableReference() {
        return tableReferenceEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableReference_TableJoinedRight() {
        return (EReference)tableReferenceEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableReference_TableJoinedLeft() {
        return (EReference)tableReferenceEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableReference_QuerySelect() {
        return (EReference)tableReferenceEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableReference_Nest() {
        return (EReference)tableReferenceEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTableReference_MergeSourceTable() {
        return (EReference)tableReferenceEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getTableExpression() {
        return tableExpressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableExpression_ColumnList() {
        return (EReference)tableExpressionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableExpression_TableCorrelation() {
        return (EReference)tableExpressionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableExpression_ResultTableAllColumns() {
        return (EReference)tableExpressionEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableExpression_ValueExprColumns() {
        return (EReference)tableExpressionEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTableExpression_MergeTargetTable() {
        return (EReference)tableExpressionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getTableJoined() {
        return tableJoinedEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getTableJoined_JoinOperator() {
        return (EAttribute)tableJoinedEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableJoined_JoinCondition() {
        return (EReference)tableJoinedEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableJoined_TableRefRight() {
        return (EReference)tableJoinedEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableJoined_TableRefLeft() {
        return (EReference)tableJoinedEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getWithTableSpecification() {
        return withTableSpecificationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getWithTableSpecification_QueryExpressionRoot() {
        return (EReference)withTableSpecificationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getWithTableSpecification_WithTableQueryExpr() {
        return (EReference)withTableSpecificationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getWithTableSpecification_WithTableReferences() {
        return (EReference)withTableSpecificationEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getWithTableSpecification_ColumnNameList() {
        return (EReference)withTableSpecificationEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicate() {
        return predicateEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPredicate_NegatedPredicate() {
        return (EAttribute)predicateEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPredicate_HasSelectivity() {
        return (EAttribute)predicateEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPredicate_SelectivityValue() {
        return (EAttribute)predicateEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getSearchConditionCombined() {
        return searchConditionCombinedEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSearchConditionCombined_CombinedOperator() {
        return (EAttribute)searchConditionCombinedEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getSearchConditionCombined_LeftCondition() {
        return (EReference)searchConditionCombinedEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getSearchConditionCombined_RightCondition() {
        return (EReference)searchConditionCombinedEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getOrderByValueExpression() {
        return orderByValueExpressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getOrderByValueExpression_ValueExpr() {
        return (EReference)orderByValueExpressionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryCombined() {
        return queryCombinedEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getQueryCombined_CombinedOperator() {
        return (EAttribute)queryCombinedEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryCombined_LeftQuery() {
        return (EReference)queryCombinedEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryCombined_RightQuery() {
        return (EReference)queryCombinedEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQuerySelect() {
        return querySelectEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getQuerySelect_Distinct() {
        return (EAttribute)querySelectEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySelect_HavingClause() {
        return (EReference)querySelectEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySelect_WhereClause() {
        return (EReference)querySelectEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySelect_GroupByClause() {
        return (EReference)querySelectEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySelect_SelectClause() {
        return (EReference)querySelectEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySelect_FromClause() {
        return (EReference)querySelectEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQuerySelect_IntoClause() {
        return (EReference)querySelectEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getGroupingSpecification() {
        return groupingSpecificationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getGroupingSpecification_QuerySelect() {
        return (EReference)groupingSpecificationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryResultSpecification() {
        return queryResultSpecificationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getQueryResultSpecification_QuerySelect() {
        return (EReference)queryResultSpecificationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getResultTableAllColumns() {
        return resultTableAllColumnsEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getResultTableAllColumns_TableExpr() {
        return (EReference)resultTableAllColumnsEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getResultColumn() {
        return resultColumnEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getResultColumn_ValueExpr() {
        return (EReference)resultColumnEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getResultColumn_OrderByResultCol() {
        return (EReference)resultColumnEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateBasic() {
        return predicateBasicEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPredicateBasic_ComparisonOperator() {
        return (EAttribute)predicateBasicEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateBasic_RightValueExpr() {
        return (EReference)predicateBasicEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateBasic_LeftValueExpr() {
        return (EReference)predicateBasicEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateQuantified() {
        return predicateQuantifiedEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateBetween() {
        return predicateBetweenEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPredicateBetween_NotBetween() {
        return (EAttribute)predicateBetweenEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateBetween_LeftValueExpr() {
        return (EReference)predicateBetweenEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateBetween_RightValueExpr1() {
        return (EReference)predicateBetweenEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateBetween_RightValueExpr2() {
        return (EReference)predicateBetweenEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateExists() {
        return predicateExistsEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateExists_QueryExpr() {
        return (EReference)predicateExistsEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateIn() {
        return predicateInEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPredicateIn_NotIn() {
        return (EAttribute)predicateInEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateLike() {
        return predicateLikeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPredicateLike_NotLike() {
        return (EAttribute)predicateLikeEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateLike_PatternValueExpr() {
        return (EReference)predicateLikeEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateLike_MatchingValueExpr() {
        return (EReference)predicateLikeEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateLike_EscapeValueExpr() {
        return (EReference)predicateLikeEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateIsNull() {
        return predicateIsNullEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPredicateIsNull_NotNull() {
        return (EAttribute)predicateIsNullEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateIsNull_ValueExpr() {
        return (EReference)predicateIsNullEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateQuantifiedValueSelect() {
        return predicateQuantifiedValueSelectEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPredicateQuantifiedValueSelect_QuantifiedType() {
        return (EAttribute)predicateQuantifiedValueSelectEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPredicateQuantifiedValueSelect_ComparisonOperator() {
        return (EAttribute)predicateQuantifiedValueSelectEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateQuantifiedValueSelect_QueryExpr() {
        return (EReference)predicateQuantifiedValueSelectEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateQuantifiedValueSelect_ValueExpr() {
        return (EReference)predicateQuantifiedValueSelectEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateQuantifiedRowSelect() {
        return predicateQuantifiedRowSelectEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPredicateQuantifiedRowSelect_QuantifiedType() {
        return (EAttribute)predicateQuantifiedRowSelectEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateQuantifiedRowSelect_QueryExpr() {
        return (EReference)predicateQuantifiedRowSelectEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateQuantifiedRowSelect_ValueExprList() {
        return (EReference)predicateQuantifiedRowSelectEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateInValueSelect() {
        return predicateInValueSelectEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateInValueSelect_QueryExpr() {
        return (EReference)predicateInValueSelectEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateInValueSelect_ValueExpr() {
        return (EReference)predicateInValueSelectEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateInValueList() {
        return predicateInValueListEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateInValueList_ValueExprList() {
        return (EReference)predicateInValueListEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateInValueList_ValueExpr() {
        return (EReference)predicateInValueListEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPredicateInValueRowSelect() {
        return predicateInValueRowSelectEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateInValueRowSelect_ValueExprList() {
        return (EReference)predicateInValueRowSelectEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPredicateInValueRowSelect_QueryExpr() {
        return (EReference)predicateInValueRowSelectEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionSimple() {
        return valueExpressionSimpleEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValueExpressionSimple_Value() {
        return (EAttribute)valueExpressionSimpleEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionColumn() {
        return valueExpressionColumnEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionColumn_AssignmentExprTarget() {
        return (EReference)valueExpressionColumnEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionColumn_ParentTableExpr() {
        return (EReference)valueExpressionColumnEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionColumn_InsertStatement() {
        return (EReference)valueExpressionColumnEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionColumn_TableExpr() {
        return (EReference)valueExpressionColumnEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionColumn_TableInDatabase() {
        return (EReference)valueExpressionColumnEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getValueExpressionColumn_MergeInsertSpec() {
        return (EReference)valueExpressionColumnEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionVariable() {
        return valueExpressionVariableEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionVariable_QuerySelect() {
        return (EReference)valueExpressionVariableEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionScalarSelect() {
        return valueExpressionScalarSelectEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionScalarSelect_QueryExpr() {
        return (EReference)valueExpressionScalarSelectEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionLabeledDuration() {
        return valueExpressionLabeledDurationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValueExpressionLabeledDuration_LabeledDurationType() {
        return (EAttribute)valueExpressionLabeledDurationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionLabeledDuration_ValueExpr() {
        return (EReference)valueExpressionLabeledDurationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionCase() {
        return valueExpressionCaseEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCase_CaseElse() {
        return (EReference)valueExpressionCaseEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionCast() {
        return valueExpressionCastEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCast_ValueExpr() {
        return (EReference)valueExpressionCastEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionNullValue() {
        return valueExpressionNullValueEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionDefaultValue() {
        return valueExpressionDefaultValueEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionFunction() {
        return valueExpressionFunctionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValueExpressionFunction_SpecialRegister() {
        return (EAttribute)valueExpressionFunctionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValueExpressionFunction_Distinct() {
        return (EAttribute)valueExpressionFunctionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValueExpressionFunction_ColumnFunction() {
        return (EAttribute)valueExpressionFunctionEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionFunction_ParameterList() {
        return (EReference)valueExpressionFunctionEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionFunction_Function() {
        return (EReference)valueExpressionFunctionEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionCombined() {
        return valueExpressionCombinedEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValueExpressionCombined_CombinedOperator() {
        return (EAttribute)valueExpressionCombinedEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCombined_LeftValueExpr() {
        return (EReference)valueExpressionCombinedEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCombined_RightValueExpr() {
        return (EReference)valueExpressionCombinedEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getGroupingSets() {
        return groupingSetsEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getGroupingSets_GroupingSetsElementList() {
        return (EReference)groupingSetsEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getGrouping() {
        return groupingEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getGrouping_GroupingSetsElementExpr() {
        return (EReference)groupingEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getGroupingSetsElement() {
        return groupingSetsElementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getGroupingSetsElement_GroupingSets() {
        return (EReference)groupingSetsElementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getGroupingSetsElementSublist() {
        return groupingSetsElementSublistEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getGroupingSetsElementSublist_GroupingSetsElementExprList() {
        return (EReference)groupingSetsElementSublistEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getGroupingSetsElementExpression() {
        return groupingSetsElementExpressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getGroupingSetsElementExpression_GroupingSetsElementSublist() {
        return (EReference)groupingSetsElementExpressionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getGroupingSetsElementExpression_Grouping() {
        return (EReference)groupingSetsElementExpressionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getSuperGroup() {
        return superGroupEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSuperGroup_SuperGroupType() {
        return (EAttribute)superGroupEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getSuperGroup_SuperGroupElementList() {
        return (EReference)superGroupEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getGroupingExpression() {
        return groupingExpressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getGroupingExpression_ValueExpr() {
        return (EReference)groupingExpressionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getGroupingExpression_SuperGroupElementExpr() {
        return (EReference)groupingExpressionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getSuperGroupElement() {
        return superGroupElementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getSuperGroupElement_SuperGroup() {
        return (EReference)superGroupElementEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getSuperGroupElementSublist() {
        return superGroupElementSublistEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getSuperGroupElementSublist_SuperGroupElementExprList() {
        return (EReference)superGroupElementSublistEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getSuperGroupElementExpression() {
        return superGroupElementExpressionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getSuperGroupElementExpression_SuperGroupElementSublist() {
        return (EReference)superGroupElementExpressionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getSuperGroupElementExpression_GroupingExpr() {
        return (EReference)superGroupElementExpressionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionCaseSearch() {
        return valueExpressionCaseSearchEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCaseSearch_SearchContentList() {
        return (EReference)valueExpressionCaseSearchEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionCaseSimple() {
        return valueExpressionCaseSimpleEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCaseSimple_ContentList() {
        return (EReference)valueExpressionCaseSimpleEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCaseSimple_ValueExpr() {
        return (EReference)valueExpressionCaseSimpleEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionCaseElse() {
        return valueExpressionCaseElseEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCaseElse_ValueExprCase() {
        return (EReference)valueExpressionCaseElseEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCaseElse_ValueExpr() {
        return (EReference)valueExpressionCaseElseEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionCaseSearchContent() {
        return valueExpressionCaseSearchContentEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCaseSearchContent_ValueExpr() {
        return (EReference)valueExpressionCaseSearchContentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCaseSearchContent_SearchCondition() {
        return (EReference)valueExpressionCaseSearchContentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCaseSearchContent_ValueExprCaseSearch() {
        return (EReference)valueExpressionCaseSearchContentEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionCaseSimpleContent() {
        return valueExpressionCaseSimpleContentEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCaseSimpleContent_ValueExprCaseSimple() {
        return (EReference)valueExpressionCaseSimpleContentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCaseSimpleContent_WhenValueExpr() {
        return (EReference)valueExpressionCaseSimpleContentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionCaseSimpleContent_ResultValueExpr() {
        return (EReference)valueExpressionCaseSimpleContentEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getTableInDatabase() {
        return tableInDatabaseEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableInDatabase_UpdateStatement() {
        return (EReference)tableInDatabaseEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableInDatabase_DeleteStatement() {
        return (EReference)tableInDatabaseEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableInDatabase_InsertStatement() {
        return (EReference)tableInDatabaseEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableInDatabase_DatabaseTable() {
        return (EReference)tableInDatabaseEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableInDatabase_DerivedColumnList() {
        return (EReference)tableInDatabaseEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getTableFunction() {
        return tableFunctionEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTableFunction_Function() {
        return (EReference)tableFunctionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTableFunction_ParameterList() {
        return (EReference)tableFunctionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getSQLQueryObject() {
        return sqlQueryObjectEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryChangeStatement() {
        return queryChangeStatementEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getColumnName() {
        return columnNameEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getColumnName_TableCorrelation() {
        return (EReference)columnNameEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getColumnName_WithTableSpecification() {
        return (EReference)columnNameEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getTableNested() {
        return tableNestedEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableNested_NestedTableRef() {
        return (EReference)tableNestedEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getQueryMergeStatement() {
        return queryMergeStatementEClass;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueryMergeStatement_TargetTable() {
        return (EReference)queryMergeStatementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueryMergeStatement_SourceTable() {
        return (EReference)queryMergeStatementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueryMergeStatement_OnCondition() {
        return (EReference)queryMergeStatementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueryMergeStatement_OperationSpecList() {
        return (EReference)queryMergeStatementEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getSearchConditionNested() {
        return searchConditionNestedEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getSearchConditionNested_NestedCondition() {
        return (EReference)searchConditionNestedEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionNested() {
        return valueExpressionNestedEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValueExpressionNested_NestedValueExpr() {
        return (EReference)valueExpressionNestedEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueExpressionAtomic() {
        return valueExpressionAtomicEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getOrderBySpecification() {
        return orderBySpecificationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getOrderBySpecification_Descending() {
        return (EAttribute)orderBySpecificationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getOrderBySpecification_OrderingSpecOption() {
        return (EAttribute)orderBySpecificationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getOrderBySpecification_NullOrderingOption() {
        return (EAttribute)orderBySpecificationEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getOrderBySpecification_SelectStatement() {
        return (EReference)orderBySpecificationEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOrderBySpecification_Query() {
        return (EReference)orderBySpecificationEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getOrderByOrdinal() {
        return orderByOrdinalEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getOrderByOrdinal_OrdinalValue() {
        return (EAttribute)orderByOrdinalEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getTableCorrelation() {
        return tableCorrelationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableCorrelation_TableExpr() {
        return (EReference)tableCorrelationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getTableCorrelation_ColumnNameList() {
        return (EReference)tableCorrelationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getUpdateSource() {
        return updateSourceEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getUpdateSource_UpdateAssignmentExpr() {
        return (EReference)updateSourceEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getUpdateSourceExprList() {
        return updateSourceExprListEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getUpdateSourceExprList_ValueExprList() {
        return (EReference)updateSourceExprListEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getUpdateSourceQuery() {
        return updateSourceQueryEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getUpdateSourceQuery_QueryExpr() {
        return (EReference)updateSourceQueryEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getOrderByResultColumn() {
        return orderByResultColumnEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getOrderByResultColumn_ResultCol() {
        return (EReference)orderByResultColumnEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getWithTableReference() {
        return withTableReferenceEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getWithTableReference_WithTableSpecification() {
        return (EReference)withTableReferenceEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getQueryNested() {
        return queryNestedEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getQueryNested_NestedQuery() {
        return (EReference)queryNestedEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getValueExpressionRow() {
        return valueExpressionRowEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getValueExpressionRow_ValueExprList() {
        return (EReference)valueExpressionRowEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMergeTargetTable() {
        return mergeTargetTableEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMergeTargetTable_MergeStatement() {
        return (EReference)mergeTargetTableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMergeTargetTable_TableExpr() {
        return (EReference)mergeTargetTableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMergeSourceTable() {
        return mergeSourceTableEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMergeSourceTable_QueryMergeStatement() {
        return (EReference)mergeSourceTableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMergeSourceTable_MergeStatement() {
        return (EReference)mergeSourceTableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMergeSourceTable_TableRef() {
        return (EReference)mergeSourceTableEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMergeOnCondition() {
        return mergeOnConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMergeOnCondition_MergeStatement() {
        return (EReference)mergeOnConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMergeOnCondition_SearchCondition() {
        return (EReference)mergeOnConditionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMergeUpdateSpecification() {
        return mergeUpdateSpecificationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMergeUpdateSpecification_AssignementExprList() {
        return (EReference)mergeUpdateSpecificationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMergeInsertSpecification() {
        return mergeInsertSpecificationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMergeInsertSpecification_TargetColumnList() {
        return (EReference)mergeInsertSpecificationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMergeInsertSpecification_SourceValuesRow() {
        return (EReference)mergeInsertSpecificationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMergeOperationSpecification() {
        return mergeOperationSpecificationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMergeOperationSpecification_MergeStatement() {
        return (EReference)mergeOperationSpecificationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUpdateOfColumn() {
        return updateOfColumnEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUpdateOfColumn_UpdatabilityExpr() {
        return (EReference)updateOfColumnEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUpdatabilityExpression() {
        return updatabilityExpressionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUpdatabilityExpression_UpdatabilityType() {
        return (EAttribute)updatabilityExpressionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUpdatabilityExpression_UpdateOfColumnList() {
        return (EReference)updatabilityExpressionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUpdatabilityExpression_SelectStatement() {
        return (EReference)updatabilityExpressionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCallStatement() {
        return callStatementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCallStatement_ArgumentList() {
        return (EReference)callStatementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCallStatement_ProcedureRef() {
        return (EReference)callStatementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProcedureReference() {
        return procedureReferenceEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProcedureReference_CallStatement() {
        return (EReference)procedureReferenceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProcedureReference_Procedure() {
        return (EReference)procedureReferenceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTableQueryLateral() {
        return tableQueryLateralEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTableQueryLateral_Query() {
        return (EReference)tableQueryLateralEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getSuperGroupType() {
        return superGroupTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getPredicateQuantifiedType() {
        return predicateQuantifiedTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getPredicateComparisonOperator() {
        return predicateComparisonOperatorEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getSearchConditionCombinedOperator() {
        return searchConditionCombinedOperatorEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getTableJoinedOperator() {
        return tableJoinedOperatorEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getQueryCombinedOperator() {
        return queryCombinedOperatorEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getValueExpressionUnaryOperator() {
        return valueExpressionUnaryOperatorEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getValueExpressionCombinedOperator() {
        return valueExpressionCombinedOperatorEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getValueExpressionLabeledDurationType() {
        return valueExpressionLabeledDurationTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getNullOrderingType() {
        return nullOrderingTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EEnum getOrderingSpecType() {
        return orderingSpecTypeEEnum;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getUpdatabilityType() {
        return updatabilityTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SQLQueryModelFactory getSQLQueryModelFactory() {
        return (SQLQueryModelFactory)getEFactoryInstance();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isCreated = false;

	/**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        queryStatementEClass = createEClass(QUERY_STATEMENT);

        queryDeleteStatementEClass = createEClass(QUERY_DELETE_STATEMENT);
        createEReference(queryDeleteStatementEClass, QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE);
        createEReference(queryDeleteStatementEClass, QUERY_DELETE_STATEMENT__WHERE_CLAUSE);
        createEReference(queryDeleteStatementEClass, QUERY_DELETE_STATEMENT__TARGET_TABLE);

        queryInsertStatementEClass = createEClass(QUERY_INSERT_STATEMENT);
        createEReference(queryInsertStatementEClass, QUERY_INSERT_STATEMENT__SOURCE_QUERY);
        createEReference(queryInsertStatementEClass, QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST);
        createEReference(queryInsertStatementEClass, QUERY_INSERT_STATEMENT__TARGET_TABLE);
        createEReference(queryInsertStatementEClass, QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST);

        querySelectStatementEClass = createEClass(QUERY_SELECT_STATEMENT);
        createEReference(querySelectStatementEClass, QUERY_SELECT_STATEMENT__QUERY_EXPR);
        createEReference(querySelectStatementEClass, QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE);
        createEReference(querySelectStatementEClass, QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR);

        queryUpdateStatementEClass = createEClass(QUERY_UPDATE_STATEMENT);
        createEReference(queryUpdateStatementEClass, QUERY_UPDATE_STATEMENT__ASSIGNMENT_CLAUSE);
        createEReference(queryUpdateStatementEClass, QUERY_UPDATE_STATEMENT__WHERE_CURRENT_OF_CLAUSE);
        createEReference(queryUpdateStatementEClass, QUERY_UPDATE_STATEMENT__WHERE_CLAUSE);
        createEReference(queryUpdateStatementEClass, QUERY_UPDATE_STATEMENT__TARGET_TABLE);

        updateAssignmentExpressionEClass = createEClass(UPDATE_ASSIGNMENT_EXPRESSION);
        createEReference(updateAssignmentExpressionEClass, UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT);
        createEReference(updateAssignmentExpressionEClass, UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST);
        createEReference(updateAssignmentExpressionEClass, UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE);
        createEReference(updateAssignmentExpressionEClass, UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC);

        cursorReferenceEClass = createEClass(CURSOR_REFERENCE);
        createEReference(cursorReferenceEClass, CURSOR_REFERENCE__UPDATE_STATEMENT);
        createEReference(cursorReferenceEClass, CURSOR_REFERENCE__DELETE_STATEMENT);

        querySearchConditionEClass = createEClass(QUERY_SEARCH_CONDITION);
        createEAttribute(querySearchConditionEClass, QUERY_SEARCH_CONDITION__NEGATED_CONDITION);
        createEReference(querySearchConditionEClass, QUERY_SEARCH_CONDITION__UPDATE_STATEMENT);
        createEReference(querySearchConditionEClass, QUERY_SEARCH_CONDITION__DELETE_STATEMENT);
        createEReference(querySearchConditionEClass, QUERY_SEARCH_CONDITION__TABLE_JOINED);
        createEReference(querySearchConditionEClass, QUERY_SEARCH_CONDITION__COMBINED_LEFT);
        createEReference(querySearchConditionEClass, QUERY_SEARCH_CONDITION__COMBINED_RIGHT);
        createEReference(querySearchConditionEClass, QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING);
        createEReference(querySearchConditionEClass, QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE);
        createEReference(querySearchConditionEClass, QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT);
        createEReference(querySearchConditionEClass, QUERY_SEARCH_CONDITION__NEST);
        createEReference(querySearchConditionEClass, QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION);

        queryExpressionBodyEClass = createEClass(QUERY_EXPRESSION_BODY);
        createEAttribute(queryExpressionBodyEClass, QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT);
        createEReference(queryExpressionBodyEClass, QUERY_EXPRESSION_BODY__QUERY_EXPRESSION);
        createEReference(queryExpressionBodyEClass, QUERY_EXPRESSION_BODY__COMBINED_LEFT);
        createEReference(queryExpressionBodyEClass, QUERY_EXPRESSION_BODY__COMBINED_RIGHT);
        createEReference(queryExpressionBodyEClass, QUERY_EXPRESSION_BODY__PREDICATE_EXISTS);
        createEReference(queryExpressionBodyEClass, QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY);
        createEReference(queryExpressionBodyEClass, QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION);
        createEReference(queryExpressionBodyEClass, QUERY_EXPRESSION_BODY__QUERY_NEST);
        createEReference(queryExpressionBodyEClass, QUERY_EXPRESSION_BODY__SORT_SPEC_LIST);

        queryValueExpressionEClass = createEClass(QUERY_VALUE_EXPRESSION);
        createEAttribute(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__UNARY_OPERATOR);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__DATA_TYPE);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUES_ROW);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__RESULT_COLUMN);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__BASIC_RIGHT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__BASIC_LEFT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__LIKE_PATTERN);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__LIKE_MATCHING);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__PREDICATE_NULL);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__BETWEEN_LEFT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__GROUPING_EXPR);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__LIKE_ESCAPE);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__NEST);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__TABLE_FUNCTION);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW);
        createEReference(queryValueExpressionEClass, QUERY_VALUE_EXPRESSION__CALL_STATEMENT);

        queryExpressionRootEClass = createEClass(QUERY_EXPRESSION_ROOT);
        createEReference(queryExpressionRootEClass, QUERY_EXPRESSION_ROOT__INSERT_STATEMENT);
        createEReference(queryExpressionRootEClass, QUERY_EXPRESSION_ROOT__SELECT_STATEMENT);
        createEReference(queryExpressionRootEClass, QUERY_EXPRESSION_ROOT__WITH_CLAUSE);
        createEReference(queryExpressionRootEClass, QUERY_EXPRESSION_ROOT__QUERY);
        createEReference(queryExpressionRootEClass, QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT);
        createEReference(queryExpressionRootEClass, QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT);
        createEReference(queryExpressionRootEClass, QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT);
        createEReference(queryExpressionRootEClass, QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT);
        createEReference(queryExpressionRootEClass, QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT);

        valuesRowEClass = createEClass(VALUES_ROW);
        createEReference(valuesRowEClass, VALUES_ROW__INSERT_STATEMENT);
        createEReference(valuesRowEClass, VALUES_ROW__EXPR_LIST);
        createEReference(valuesRowEClass, VALUES_ROW__QUERY_VALUES);

        queryValuesEClass = createEClass(QUERY_VALUES);
        createEReference(queryValuesEClass, QUERY_VALUES__VALUES_ROW_LIST);

        tableReferenceEClass = createEClass(TABLE_REFERENCE);
        createEReference(tableReferenceEClass, TABLE_REFERENCE__TABLE_JOINED_RIGHT);
        createEReference(tableReferenceEClass, TABLE_REFERENCE__TABLE_JOINED_LEFT);
        createEReference(tableReferenceEClass, TABLE_REFERENCE__QUERY_SELECT);
        createEReference(tableReferenceEClass, TABLE_REFERENCE__NEST);
        createEReference(tableReferenceEClass, TABLE_REFERENCE__MERGE_SOURCE_TABLE);

        tableExpressionEClass = createEClass(TABLE_EXPRESSION);
        createEReference(tableExpressionEClass, TABLE_EXPRESSION__COLUMN_LIST);
        createEReference(tableExpressionEClass, TABLE_EXPRESSION__TABLE_CORRELATION);
        createEReference(tableExpressionEClass, TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS);
        createEReference(tableExpressionEClass, TABLE_EXPRESSION__VALUE_EXPR_COLUMNS);
        createEReference(tableExpressionEClass, TABLE_EXPRESSION__MERGE_TARGET_TABLE);

        tableJoinedEClass = createEClass(TABLE_JOINED);
        createEAttribute(tableJoinedEClass, TABLE_JOINED__JOIN_OPERATOR);
        createEReference(tableJoinedEClass, TABLE_JOINED__JOIN_CONDITION);
        createEReference(tableJoinedEClass, TABLE_JOINED__TABLE_REF_RIGHT);
        createEReference(tableJoinedEClass, TABLE_JOINED__TABLE_REF_LEFT);

        withTableSpecificationEClass = createEClass(WITH_TABLE_SPECIFICATION);
        createEReference(withTableSpecificationEClass, WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT);
        createEReference(withTableSpecificationEClass, WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR);
        createEReference(withTableSpecificationEClass, WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES);
        createEReference(withTableSpecificationEClass, WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST);

        predicateEClass = createEClass(PREDICATE);
        createEAttribute(predicateEClass, PREDICATE__NEGATED_PREDICATE);
        createEAttribute(predicateEClass, PREDICATE__HAS_SELECTIVITY);
        createEAttribute(predicateEClass, PREDICATE__SELECTIVITY_VALUE);

        searchConditionCombinedEClass = createEClass(SEARCH_CONDITION_COMBINED);
        createEAttribute(searchConditionCombinedEClass, SEARCH_CONDITION_COMBINED__COMBINED_OPERATOR);
        createEReference(searchConditionCombinedEClass, SEARCH_CONDITION_COMBINED__LEFT_CONDITION);
        createEReference(searchConditionCombinedEClass, SEARCH_CONDITION_COMBINED__RIGHT_CONDITION);

        orderByValueExpressionEClass = createEClass(ORDER_BY_VALUE_EXPRESSION);
        createEReference(orderByValueExpressionEClass, ORDER_BY_VALUE_EXPRESSION__VALUE_EXPR);

        queryCombinedEClass = createEClass(QUERY_COMBINED);
        createEAttribute(queryCombinedEClass, QUERY_COMBINED__COMBINED_OPERATOR);
        createEReference(queryCombinedEClass, QUERY_COMBINED__LEFT_QUERY);
        createEReference(queryCombinedEClass, QUERY_COMBINED__RIGHT_QUERY);

        querySelectEClass = createEClass(QUERY_SELECT);
        createEAttribute(querySelectEClass, QUERY_SELECT__DISTINCT);
        createEReference(querySelectEClass, QUERY_SELECT__HAVING_CLAUSE);
        createEReference(querySelectEClass, QUERY_SELECT__WHERE_CLAUSE);
        createEReference(querySelectEClass, QUERY_SELECT__GROUP_BY_CLAUSE);
        createEReference(querySelectEClass, QUERY_SELECT__SELECT_CLAUSE);
        createEReference(querySelectEClass, QUERY_SELECT__FROM_CLAUSE);
        createEReference(querySelectEClass, QUERY_SELECT__INTO_CLAUSE);

        groupingSpecificationEClass = createEClass(GROUPING_SPECIFICATION);
        createEReference(groupingSpecificationEClass, GROUPING_SPECIFICATION__QUERY_SELECT);

        queryResultSpecificationEClass = createEClass(QUERY_RESULT_SPECIFICATION);
        createEReference(queryResultSpecificationEClass, QUERY_RESULT_SPECIFICATION__QUERY_SELECT);

        resultTableAllColumnsEClass = createEClass(RESULT_TABLE_ALL_COLUMNS);
        createEReference(resultTableAllColumnsEClass, RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR);

        resultColumnEClass = createEClass(RESULT_COLUMN);
        createEReference(resultColumnEClass, RESULT_COLUMN__VALUE_EXPR);
        createEReference(resultColumnEClass, RESULT_COLUMN__ORDER_BY_RESULT_COL);

        predicateBasicEClass = createEClass(PREDICATE_BASIC);
        createEAttribute(predicateBasicEClass, PREDICATE_BASIC__COMPARISON_OPERATOR);
        createEReference(predicateBasicEClass, PREDICATE_BASIC__RIGHT_VALUE_EXPR);
        createEReference(predicateBasicEClass, PREDICATE_BASIC__LEFT_VALUE_EXPR);

        predicateQuantifiedEClass = createEClass(PREDICATE_QUANTIFIED);

        predicateBetweenEClass = createEClass(PREDICATE_BETWEEN);
        createEAttribute(predicateBetweenEClass, PREDICATE_BETWEEN__NOT_BETWEEN);
        createEReference(predicateBetweenEClass, PREDICATE_BETWEEN__LEFT_VALUE_EXPR);
        createEReference(predicateBetweenEClass, PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1);
        createEReference(predicateBetweenEClass, PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2);

        predicateExistsEClass = createEClass(PREDICATE_EXISTS);
        createEReference(predicateExistsEClass, PREDICATE_EXISTS__QUERY_EXPR);

        predicateInEClass = createEClass(PREDICATE_IN);
        createEAttribute(predicateInEClass, PREDICATE_IN__NOT_IN);

        predicateLikeEClass = createEClass(PREDICATE_LIKE);
        createEAttribute(predicateLikeEClass, PREDICATE_LIKE__NOT_LIKE);
        createEReference(predicateLikeEClass, PREDICATE_LIKE__PATTERN_VALUE_EXPR);
        createEReference(predicateLikeEClass, PREDICATE_LIKE__MATCHING_VALUE_EXPR);
        createEReference(predicateLikeEClass, PREDICATE_LIKE__ESCAPE_VALUE_EXPR);

        predicateIsNullEClass = createEClass(PREDICATE_IS_NULL);
        createEAttribute(predicateIsNullEClass, PREDICATE_IS_NULL__NOT_NULL);
        createEReference(predicateIsNullEClass, PREDICATE_IS_NULL__VALUE_EXPR);

        predicateQuantifiedValueSelectEClass = createEClass(PREDICATE_QUANTIFIED_VALUE_SELECT);
        createEAttribute(predicateQuantifiedValueSelectEClass, PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE);
        createEAttribute(predicateQuantifiedValueSelectEClass, PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR);
        createEReference(predicateQuantifiedValueSelectEClass, PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR);
        createEReference(predicateQuantifiedValueSelectEClass, PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR);

        predicateQuantifiedRowSelectEClass = createEClass(PREDICATE_QUANTIFIED_ROW_SELECT);
        createEAttribute(predicateQuantifiedRowSelectEClass, PREDICATE_QUANTIFIED_ROW_SELECT__QUANTIFIED_TYPE);
        createEReference(predicateQuantifiedRowSelectEClass, PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR);
        createEReference(predicateQuantifiedRowSelectEClass, PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST);

        predicateInValueSelectEClass = createEClass(PREDICATE_IN_VALUE_SELECT);
        createEReference(predicateInValueSelectEClass, PREDICATE_IN_VALUE_SELECT__QUERY_EXPR);
        createEReference(predicateInValueSelectEClass, PREDICATE_IN_VALUE_SELECT__VALUE_EXPR);

        predicateInValueListEClass = createEClass(PREDICATE_IN_VALUE_LIST);
        createEReference(predicateInValueListEClass, PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST);
        createEReference(predicateInValueListEClass, PREDICATE_IN_VALUE_LIST__VALUE_EXPR);

        predicateInValueRowSelectEClass = createEClass(PREDICATE_IN_VALUE_ROW_SELECT);
        createEReference(predicateInValueRowSelectEClass, PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST);
        createEReference(predicateInValueRowSelectEClass, PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR);

        valueExpressionSimpleEClass = createEClass(VALUE_EXPRESSION_SIMPLE);
        createEAttribute(valueExpressionSimpleEClass, VALUE_EXPRESSION_SIMPLE__VALUE);

        valueExpressionColumnEClass = createEClass(VALUE_EXPRESSION_COLUMN);
        createEReference(valueExpressionColumnEClass, VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET);
        createEReference(valueExpressionColumnEClass, VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR);
        createEReference(valueExpressionColumnEClass, VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT);
        createEReference(valueExpressionColumnEClass, VALUE_EXPRESSION_COLUMN__TABLE_EXPR);
        createEReference(valueExpressionColumnEClass, VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE);
        createEReference(valueExpressionColumnEClass, VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC);

        valueExpressionVariableEClass = createEClass(VALUE_EXPRESSION_VARIABLE);
        createEReference(valueExpressionVariableEClass, VALUE_EXPRESSION_VARIABLE__QUERY_SELECT);

        valueExpressionScalarSelectEClass = createEClass(VALUE_EXPRESSION_SCALAR_SELECT);
        createEReference(valueExpressionScalarSelectEClass, VALUE_EXPRESSION_SCALAR_SELECT__QUERY_EXPR);

        valueExpressionLabeledDurationEClass = createEClass(VALUE_EXPRESSION_LABELED_DURATION);
        createEAttribute(valueExpressionLabeledDurationEClass, VALUE_EXPRESSION_LABELED_DURATION__LABELED_DURATION_TYPE);
        createEReference(valueExpressionLabeledDurationEClass, VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR);

        valueExpressionCaseEClass = createEClass(VALUE_EXPRESSION_CASE);
        createEReference(valueExpressionCaseEClass, VALUE_EXPRESSION_CASE__CASE_ELSE);

        valueExpressionCastEClass = createEClass(VALUE_EXPRESSION_CAST);
        createEReference(valueExpressionCastEClass, VALUE_EXPRESSION_CAST__VALUE_EXPR);

        valueExpressionNullValueEClass = createEClass(VALUE_EXPRESSION_NULL_VALUE);

        valueExpressionDefaultValueEClass = createEClass(VALUE_EXPRESSION_DEFAULT_VALUE);

        valueExpressionFunctionEClass = createEClass(VALUE_EXPRESSION_FUNCTION);
        createEAttribute(valueExpressionFunctionEClass, VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER);
        createEAttribute(valueExpressionFunctionEClass, VALUE_EXPRESSION_FUNCTION__DISTINCT);
        createEAttribute(valueExpressionFunctionEClass, VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION);
        createEReference(valueExpressionFunctionEClass, VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST);
        createEReference(valueExpressionFunctionEClass, VALUE_EXPRESSION_FUNCTION__FUNCTION);

        valueExpressionCombinedEClass = createEClass(VALUE_EXPRESSION_COMBINED);
        createEAttribute(valueExpressionCombinedEClass, VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR);
        createEReference(valueExpressionCombinedEClass, VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR);
        createEReference(valueExpressionCombinedEClass, VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR);

        groupingSetsEClass = createEClass(GROUPING_SETS);
        createEReference(groupingSetsEClass, GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST);

        groupingEClass = createEClass(GROUPING);
        createEReference(groupingEClass, GROUPING__GROUPING_SETS_ELEMENT_EXPR);

        groupingSetsElementEClass = createEClass(GROUPING_SETS_ELEMENT);
        createEReference(groupingSetsElementEClass, GROUPING_SETS_ELEMENT__GROUPING_SETS);

        groupingSetsElementSublistEClass = createEClass(GROUPING_SETS_ELEMENT_SUBLIST);
        createEReference(groupingSetsElementSublistEClass, GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST);

        groupingSetsElementExpressionEClass = createEClass(GROUPING_SETS_ELEMENT_EXPRESSION);
        createEReference(groupingSetsElementExpressionEClass, GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST);
        createEReference(groupingSetsElementExpressionEClass, GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING);

        superGroupEClass = createEClass(SUPER_GROUP);
        createEAttribute(superGroupEClass, SUPER_GROUP__SUPER_GROUP_TYPE);
        createEReference(superGroupEClass, SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST);

        groupingExpressionEClass = createEClass(GROUPING_EXPRESSION);
        createEReference(groupingExpressionEClass, GROUPING_EXPRESSION__VALUE_EXPR);
        createEReference(groupingExpressionEClass, GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR);

        superGroupElementEClass = createEClass(SUPER_GROUP_ELEMENT);
        createEReference(superGroupElementEClass, SUPER_GROUP_ELEMENT__SUPER_GROUP);

        superGroupElementSublistEClass = createEClass(SUPER_GROUP_ELEMENT_SUBLIST);
        createEReference(superGroupElementSublistEClass, SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST);

        superGroupElementExpressionEClass = createEClass(SUPER_GROUP_ELEMENT_EXPRESSION);
        createEReference(superGroupElementExpressionEClass, SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST);
        createEReference(superGroupElementExpressionEClass, SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR);

        valueExpressionCaseSearchEClass = createEClass(VALUE_EXPRESSION_CASE_SEARCH);
        createEReference(valueExpressionCaseSearchEClass, VALUE_EXPRESSION_CASE_SEARCH__SEARCH_CONTENT_LIST);

        valueExpressionCaseSimpleEClass = createEClass(VALUE_EXPRESSION_CASE_SIMPLE);
        createEReference(valueExpressionCaseSimpleEClass, VALUE_EXPRESSION_CASE_SIMPLE__CONTENT_LIST);
        createEReference(valueExpressionCaseSimpleEClass, VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR);

        valueExpressionCaseElseEClass = createEClass(VALUE_EXPRESSION_CASE_ELSE);
        createEReference(valueExpressionCaseElseEClass, VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE);
        createEReference(valueExpressionCaseElseEClass, VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR);

        valueExpressionCaseSearchContentEClass = createEClass(VALUE_EXPRESSION_CASE_SEARCH_CONTENT);
        createEReference(valueExpressionCaseSearchContentEClass, VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR);
        createEReference(valueExpressionCaseSearchContentEClass, VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION);
        createEReference(valueExpressionCaseSearchContentEClass, VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH);

        valueExpressionCaseSimpleContentEClass = createEClass(VALUE_EXPRESSION_CASE_SIMPLE_CONTENT);
        createEReference(valueExpressionCaseSimpleContentEClass, VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE);
        createEReference(valueExpressionCaseSimpleContentEClass, VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR);
        createEReference(valueExpressionCaseSimpleContentEClass, VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR);

        tableInDatabaseEClass = createEClass(TABLE_IN_DATABASE);
        createEReference(tableInDatabaseEClass, TABLE_IN_DATABASE__UPDATE_STATEMENT);
        createEReference(tableInDatabaseEClass, TABLE_IN_DATABASE__DELETE_STATEMENT);
        createEReference(tableInDatabaseEClass, TABLE_IN_DATABASE__INSERT_STATEMENT);
        createEReference(tableInDatabaseEClass, TABLE_IN_DATABASE__DATABASE_TABLE);
        createEReference(tableInDatabaseEClass, TABLE_IN_DATABASE__DERIVED_COLUMN_LIST);

        tableFunctionEClass = createEClass(TABLE_FUNCTION);
        createEReference(tableFunctionEClass, TABLE_FUNCTION__FUNCTION);
        createEReference(tableFunctionEClass, TABLE_FUNCTION__PARAMETER_LIST);

        sqlQueryObjectEClass = createEClass(SQL_QUERY_OBJECT);

        queryChangeStatementEClass = createEClass(QUERY_CHANGE_STATEMENT);

        columnNameEClass = createEClass(COLUMN_NAME);
        createEReference(columnNameEClass, COLUMN_NAME__TABLE_CORRELATION);
        createEReference(columnNameEClass, COLUMN_NAME__WITH_TABLE_SPECIFICATION);

        tableNestedEClass = createEClass(TABLE_NESTED);
        createEReference(tableNestedEClass, TABLE_NESTED__NESTED_TABLE_REF);

        queryMergeStatementEClass = createEClass(QUERY_MERGE_STATEMENT);
        createEReference(queryMergeStatementEClass, QUERY_MERGE_STATEMENT__TARGET_TABLE);
        createEReference(queryMergeStatementEClass, QUERY_MERGE_STATEMENT__SOURCE_TABLE);
        createEReference(queryMergeStatementEClass, QUERY_MERGE_STATEMENT__ON_CONDITION);
        createEReference(queryMergeStatementEClass, QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST);

        searchConditionNestedEClass = createEClass(SEARCH_CONDITION_NESTED);
        createEReference(searchConditionNestedEClass, SEARCH_CONDITION_NESTED__NESTED_CONDITION);

        valueExpressionNestedEClass = createEClass(VALUE_EXPRESSION_NESTED);
        createEReference(valueExpressionNestedEClass, VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR);

        valueExpressionAtomicEClass = createEClass(VALUE_EXPRESSION_ATOMIC);

        orderBySpecificationEClass = createEClass(ORDER_BY_SPECIFICATION);
        createEAttribute(orderBySpecificationEClass, ORDER_BY_SPECIFICATION__DESCENDING);
        createEAttribute(orderBySpecificationEClass, ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION);
        createEAttribute(orderBySpecificationEClass, ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION);
        createEReference(orderBySpecificationEClass, ORDER_BY_SPECIFICATION__SELECT_STATEMENT);
        createEReference(orderBySpecificationEClass, ORDER_BY_SPECIFICATION__QUERY);

        orderByOrdinalEClass = createEClass(ORDER_BY_ORDINAL);
        createEAttribute(orderByOrdinalEClass, ORDER_BY_ORDINAL__ORDINAL_VALUE);

        tableCorrelationEClass = createEClass(TABLE_CORRELATION);
        createEReference(tableCorrelationEClass, TABLE_CORRELATION__TABLE_EXPR);
        createEReference(tableCorrelationEClass, TABLE_CORRELATION__COLUMN_NAME_LIST);

        updateSourceEClass = createEClass(UPDATE_SOURCE);
        createEReference(updateSourceEClass, UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR);

        updateSourceExprListEClass = createEClass(UPDATE_SOURCE_EXPR_LIST);
        createEReference(updateSourceExprListEClass, UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST);

        updateSourceQueryEClass = createEClass(UPDATE_SOURCE_QUERY);
        createEReference(updateSourceQueryEClass, UPDATE_SOURCE_QUERY__QUERY_EXPR);

        orderByResultColumnEClass = createEClass(ORDER_BY_RESULT_COLUMN);
        createEReference(orderByResultColumnEClass, ORDER_BY_RESULT_COLUMN__RESULT_COL);

        withTableReferenceEClass = createEClass(WITH_TABLE_REFERENCE);
        createEReference(withTableReferenceEClass, WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION);

        queryNestedEClass = createEClass(QUERY_NESTED);
        createEReference(queryNestedEClass, QUERY_NESTED__NESTED_QUERY);

        valueExpressionRowEClass = createEClass(VALUE_EXPRESSION_ROW);
        createEReference(valueExpressionRowEClass, VALUE_EXPRESSION_ROW__VALUE_EXPR_LIST);

        mergeTargetTableEClass = createEClass(MERGE_TARGET_TABLE);
        createEReference(mergeTargetTableEClass, MERGE_TARGET_TABLE__MERGE_STATEMENT);
        createEReference(mergeTargetTableEClass, MERGE_TARGET_TABLE__TABLE_EXPR);

        mergeSourceTableEClass = createEClass(MERGE_SOURCE_TABLE);
        createEReference(mergeSourceTableEClass, MERGE_SOURCE_TABLE__QUERY_MERGE_STATEMENT);
        createEReference(mergeSourceTableEClass, MERGE_SOURCE_TABLE__MERGE_STATEMENT);
        createEReference(mergeSourceTableEClass, MERGE_SOURCE_TABLE__TABLE_REF);

        mergeOnConditionEClass = createEClass(MERGE_ON_CONDITION);
        createEReference(mergeOnConditionEClass, MERGE_ON_CONDITION__MERGE_STATEMENT);
        createEReference(mergeOnConditionEClass, MERGE_ON_CONDITION__SEARCH_CONDITION);

        mergeUpdateSpecificationEClass = createEClass(MERGE_UPDATE_SPECIFICATION);
        createEReference(mergeUpdateSpecificationEClass, MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST);

        mergeInsertSpecificationEClass = createEClass(MERGE_INSERT_SPECIFICATION);
        createEReference(mergeInsertSpecificationEClass, MERGE_INSERT_SPECIFICATION__TARGET_COLUMN_LIST);
        createEReference(mergeInsertSpecificationEClass, MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW);

        mergeOperationSpecificationEClass = createEClass(MERGE_OPERATION_SPECIFICATION);
        createEReference(mergeOperationSpecificationEClass, MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT);

        updateOfColumnEClass = createEClass(UPDATE_OF_COLUMN);
        createEReference(updateOfColumnEClass, UPDATE_OF_COLUMN__UPDATABILITY_EXPR);

        updatabilityExpressionEClass = createEClass(UPDATABILITY_EXPRESSION);
        createEAttribute(updatabilityExpressionEClass, UPDATABILITY_EXPRESSION__UPDATABILITY_TYPE);
        createEReference(updatabilityExpressionEClass, UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST);
        createEReference(updatabilityExpressionEClass, UPDATABILITY_EXPRESSION__SELECT_STATEMENT);

        callStatementEClass = createEClass(CALL_STATEMENT);
        createEReference(callStatementEClass, CALL_STATEMENT__ARGUMENT_LIST);
        createEReference(callStatementEClass, CALL_STATEMENT__PROCEDURE_REF);

        procedureReferenceEClass = createEClass(PROCEDURE_REFERENCE);
        createEReference(procedureReferenceEClass, PROCEDURE_REFERENCE__CALL_STATEMENT);
        createEReference(procedureReferenceEClass, PROCEDURE_REFERENCE__PROCEDURE);

        tableQueryLateralEClass = createEClass(TABLE_QUERY_LATERAL);
        createEReference(tableQueryLateralEClass, TABLE_QUERY_LATERAL__QUERY);

        // Create enums
        superGroupTypeEEnum = createEEnum(SUPER_GROUP_TYPE);
        predicateQuantifiedTypeEEnum = createEEnum(PREDICATE_QUANTIFIED_TYPE);
        predicateComparisonOperatorEEnum = createEEnum(PREDICATE_COMPARISON_OPERATOR);
        searchConditionCombinedOperatorEEnum = createEEnum(SEARCH_CONDITION_COMBINED_OPERATOR);
        tableJoinedOperatorEEnum = createEEnum(TABLE_JOINED_OPERATOR);
        queryCombinedOperatorEEnum = createEEnum(QUERY_COMBINED_OPERATOR);
        valueExpressionUnaryOperatorEEnum = createEEnum(VALUE_EXPRESSION_UNARY_OPERATOR);
        valueExpressionCombinedOperatorEEnum = createEEnum(VALUE_EXPRESSION_COMBINED_OPERATOR);
        valueExpressionLabeledDurationTypeEEnum = createEEnum(VALUE_EXPRESSION_LABELED_DURATION_TYPE);
        nullOrderingTypeEEnum = createEEnum(NULL_ORDERING_TYPE);
        orderingSpecTypeEEnum = createEEnum(ORDERING_SPEC_TYPE);
        updatabilityTypeEEnum = createEEnum(UPDATABILITY_TYPE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isInitialized = false;

	/**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        SQLStatementsPackage theSQLStatementsPackage = (SQLStatementsPackage)EPackage.Registry.INSTANCE.getEPackage(SQLStatementsPackage.eNS_URI);
        SQLExpressionsPackage theSQLExpressionsPackage = (SQLExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage(SQLExpressionsPackage.eNS_URI);
        SQLDataTypesPackage theSQLDataTypesPackage = (SQLDataTypesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLDataTypesPackage.eNS_URI);
        SQLRoutinesPackage theSQLRoutinesPackage = (SQLRoutinesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLRoutinesPackage.eNS_URI);
        SQLTablesPackage theSQLTablesPackage = (SQLTablesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLTablesPackage.eNS_URI);
        SQLSchemaPackage theSQLSchemaPackage = (SQLSchemaPackage)EPackage.Registry.INSTANCE.getEPackage(SQLSchemaPackage.eNS_URI);

        // Add supertypes to classes
        queryStatementEClass.getESuperTypes().add(this.getSQLQueryObject());
        queryStatementEClass.getESuperTypes().add(theSQLStatementsPackage.getSQLDataStatement());
        queryDeleteStatementEClass.getESuperTypes().add(this.getQueryChangeStatement());
        queryInsertStatementEClass.getESuperTypes().add(this.getQueryChangeStatement());
        querySelectStatementEClass.getESuperTypes().add(this.getQueryStatement());
        queryUpdateStatementEClass.getESuperTypes().add(this.getQueryChangeStatement());
        updateAssignmentExpressionEClass.getESuperTypes().add(this.getSQLQueryObject());
        cursorReferenceEClass.getESuperTypes().add(this.getSQLQueryObject());
        querySearchConditionEClass.getESuperTypes().add(this.getSQLQueryObject());
        querySearchConditionEClass.getESuperTypes().add(theSQLExpressionsPackage.getSearchCondition());
        queryExpressionBodyEClass.getESuperTypes().add(this.getTableExpression());
        queryValueExpressionEClass.getESuperTypes().add(this.getSQLQueryObject());
        queryValueExpressionEClass.getESuperTypes().add(theSQLExpressionsPackage.getValueExpression());
        queryExpressionRootEClass.getESuperTypes().add(this.getSQLQueryObject());
        queryExpressionRootEClass.getESuperTypes().add(theSQLExpressionsPackage.getQueryExpression());
        valuesRowEClass.getESuperTypes().add(this.getSQLQueryObject());
        queryValuesEClass.getESuperTypes().add(this.getQueryExpressionBody());
        tableReferenceEClass.getESuperTypes().add(this.getSQLQueryObject());
        tableExpressionEClass.getESuperTypes().add(this.getTableReference());
        tableJoinedEClass.getESuperTypes().add(this.getTableReference());
        withTableSpecificationEClass.getESuperTypes().add(this.getSQLQueryObject());
        predicateEClass.getESuperTypes().add(this.getQuerySearchCondition());
        searchConditionCombinedEClass.getESuperTypes().add(this.getQuerySearchCondition());
        orderByValueExpressionEClass.getESuperTypes().add(this.getOrderBySpecification());
        queryCombinedEClass.getESuperTypes().add(this.getQueryExpressionBody());
        querySelectEClass.getESuperTypes().add(this.getQueryExpressionBody());
        groupingSpecificationEClass.getESuperTypes().add(this.getSQLQueryObject());
        queryResultSpecificationEClass.getESuperTypes().add(this.getSQLQueryObject());
        resultTableAllColumnsEClass.getESuperTypes().add(this.getQueryResultSpecification());
        resultColumnEClass.getESuperTypes().add(this.getQueryResultSpecification());
        predicateBasicEClass.getESuperTypes().add(this.getPredicate());
        predicateQuantifiedEClass.getESuperTypes().add(this.getPredicate());
        predicateBetweenEClass.getESuperTypes().add(this.getPredicate());
        predicateExistsEClass.getESuperTypes().add(this.getPredicate());
        predicateInEClass.getESuperTypes().add(this.getPredicate());
        predicateLikeEClass.getESuperTypes().add(this.getPredicate());
        predicateIsNullEClass.getESuperTypes().add(this.getPredicate());
        predicateQuantifiedValueSelectEClass.getESuperTypes().add(this.getPredicateQuantified());
        predicateQuantifiedRowSelectEClass.getESuperTypes().add(this.getPredicateQuantified());
        predicateInValueSelectEClass.getESuperTypes().add(this.getPredicateIn());
        predicateInValueListEClass.getESuperTypes().add(this.getPredicateIn());
        predicateInValueRowSelectEClass.getESuperTypes().add(this.getPredicateIn());
        valueExpressionSimpleEClass.getESuperTypes().add(this.getValueExpressionAtomic());
        valueExpressionColumnEClass.getESuperTypes().add(this.getValueExpressionAtomic());
        valueExpressionVariableEClass.getESuperTypes().add(this.getValueExpressionAtomic());
        valueExpressionScalarSelectEClass.getESuperTypes().add(this.getValueExpressionAtomic());
        valueExpressionLabeledDurationEClass.getESuperTypes().add(this.getValueExpressionAtomic());
        valueExpressionCaseEClass.getESuperTypes().add(this.getValueExpressionAtomic());
        valueExpressionCastEClass.getESuperTypes().add(this.getValueExpressionAtomic());
        valueExpressionNullValueEClass.getESuperTypes().add(this.getValueExpressionAtomic());
        valueExpressionDefaultValueEClass.getESuperTypes().add(this.getValueExpressionAtomic());
        valueExpressionFunctionEClass.getESuperTypes().add(this.getValueExpressionAtomic());
        valueExpressionCombinedEClass.getESuperTypes().add(this.getQueryValueExpression());
        groupingSetsEClass.getESuperTypes().add(this.getGroupingSpecification());
        groupingEClass.getESuperTypes().add(this.getGroupingSpecification());
        groupingSetsElementEClass.getESuperTypes().add(this.getSQLQueryObject());
        groupingSetsElementSublistEClass.getESuperTypes().add(this.getGroupingSetsElement());
        groupingSetsElementExpressionEClass.getESuperTypes().add(this.getGroupingSetsElement());
        superGroupEClass.getESuperTypes().add(this.getGrouping());
        groupingExpressionEClass.getESuperTypes().add(this.getGrouping());
        superGroupElementEClass.getESuperTypes().add(this.getSQLQueryObject());
        superGroupElementSublistEClass.getESuperTypes().add(this.getSuperGroupElement());
        superGroupElementExpressionEClass.getESuperTypes().add(this.getSuperGroupElement());
        valueExpressionCaseSearchEClass.getESuperTypes().add(this.getValueExpressionCase());
        valueExpressionCaseSimpleEClass.getESuperTypes().add(this.getValueExpressionCase());
        valueExpressionCaseElseEClass.getESuperTypes().add(this.getSQLQueryObject());
        valueExpressionCaseSearchContentEClass.getESuperTypes().add(this.getSQLQueryObject());
        valueExpressionCaseSimpleContentEClass.getESuperTypes().add(this.getSQLQueryObject());
        tableInDatabaseEClass.getESuperTypes().add(this.getTableExpression());
        tableFunctionEClass.getESuperTypes().add(this.getTableExpression());
        sqlQueryObjectEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
        queryChangeStatementEClass.getESuperTypes().add(this.getQueryStatement());
        queryChangeStatementEClass.getESuperTypes().add(theSQLStatementsPackage.getSQLDataChangeStatement());
        columnNameEClass.getESuperTypes().add(this.getSQLQueryObject());
        tableNestedEClass.getESuperTypes().add(this.getTableReference());
        queryMergeStatementEClass.getESuperTypes().add(this.getQueryChangeStatement());
        searchConditionNestedEClass.getESuperTypes().add(this.getQuerySearchCondition());
        valueExpressionNestedEClass.getESuperTypes().add(this.getQueryValueExpression());
        valueExpressionAtomicEClass.getESuperTypes().add(this.getQueryValueExpression());
        orderBySpecificationEClass.getESuperTypes().add(this.getSQLQueryObject());
        orderByOrdinalEClass.getESuperTypes().add(this.getOrderBySpecification());
        tableCorrelationEClass.getESuperTypes().add(this.getSQLQueryObject());
        updateSourceEClass.getESuperTypes().add(this.getSQLQueryObject());
        updateSourceExprListEClass.getESuperTypes().add(this.getUpdateSource());
        updateSourceQueryEClass.getESuperTypes().add(this.getUpdateSource());
        orderByResultColumnEClass.getESuperTypes().add(this.getOrderBySpecification());
        withTableReferenceEClass.getESuperTypes().add(this.getTableExpression());
        queryNestedEClass.getESuperTypes().add(this.getQueryExpressionBody());
        valueExpressionRowEClass.getESuperTypes().add(this.getQueryValueExpression());
        mergeTargetTableEClass.getESuperTypes().add(this.getSQLQueryObject());
        mergeSourceTableEClass.getESuperTypes().add(this.getSQLQueryObject());
        mergeOnConditionEClass.getESuperTypes().add(this.getSQLQueryObject());
        mergeUpdateSpecificationEClass.getESuperTypes().add(this.getMergeOperationSpecification());
        mergeInsertSpecificationEClass.getESuperTypes().add(this.getMergeOperationSpecification());
        mergeOperationSpecificationEClass.getESuperTypes().add(this.getSQLQueryObject());
        updateOfColumnEClass.getESuperTypes().add(this.getSQLQueryObject());
        updatabilityExpressionEClass.getESuperTypes().add(this.getSQLQueryObject());
        callStatementEClass.getESuperTypes().add(this.getSQLQueryObject());
        callStatementEClass.getESuperTypes().add(theSQLStatementsPackage.getSQLControlStatement());
        procedureReferenceEClass.getESuperTypes().add(this.getSQLQueryObject());
        tableQueryLateralEClass.getESuperTypes().add(this.getTableExpression());

        // Initialize classes and features; add operations and parameters
        initEClass(queryStatementEClass, QueryStatement.class, "QueryStatement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(queryDeleteStatementEClass, QueryDeleteStatement.class, "QueryDeleteStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQueryDeleteStatement_WhereCurrentOfClause(), this.getCursorReference(), this.getCursorReference_DeleteStatement(), "whereCurrentOfClause", null, 0, 1, QueryDeleteStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryDeleteStatement_WhereClause(), this.getQuerySearchCondition(), this.getQuerySearchCondition_DeleteStatement(), "whereClause", null, 0, 1, QueryDeleteStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryDeleteStatement_TargetTable(), this.getTableInDatabase(), this.getTableInDatabase_DeleteStatement(), "targetTable", null, 1, 1, QueryDeleteStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryInsertStatementEClass, QueryInsertStatement.class, "QueryInsertStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQueryInsertStatement_SourceQuery(), this.getQueryExpressionRoot(), this.getQueryExpressionRoot_InsertStatement(), "sourceQuery", null, 0, 1, QueryInsertStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryInsertStatement_SourceValuesRowList(), this.getValuesRow(), this.getValuesRow_InsertStatement(), "sourceValuesRowList", null, 0, -1, QueryInsertStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryInsertStatement_TargetTable(), this.getTableInDatabase(), this.getTableInDatabase_InsertStatement(), "targetTable", null, 1, 1, QueryInsertStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryInsertStatement_TargetColumnList(), this.getValueExpressionColumn(), this.getValueExpressionColumn_InsertStatement(), "targetColumnList", null, 0, -1, QueryInsertStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(querySelectStatementEClass, QuerySelectStatement.class, "QuerySelectStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQuerySelectStatement_QueryExpr(), this.getQueryExpressionRoot(), this.getQueryExpressionRoot_SelectStatement(), "queryExpr", null, 1, 1, QuerySelectStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySelectStatement_OrderByClause(), this.getOrderBySpecification(), this.getOrderBySpecification_SelectStatement(), "orderByClause", null, 0, -1, QuerySelectStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySelectStatement_UpdatabilityExpr(), this.getUpdatabilityExpression(), this.getUpdatabilityExpression_SelectStatement(), "updatabilityExpr", null, 0, 1, QuerySelectStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryUpdateStatementEClass, QueryUpdateStatement.class, "QueryUpdateStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQueryUpdateStatement_AssignmentClause(), this.getUpdateAssignmentExpression(), this.getUpdateAssignmentExpression_UpdateStatement(), "assignmentClause", null, 1, -1, QueryUpdateStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryUpdateStatement_WhereCurrentOfClause(), this.getCursorReference(), this.getCursorReference_UpdateStatement(), "whereCurrentOfClause", null, 0, 1, QueryUpdateStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryUpdateStatement_WhereClause(), this.getQuerySearchCondition(), this.getQuerySearchCondition_UpdateStatement(), "whereClause", null, 0, 1, QueryUpdateStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryUpdateStatement_TargetTable(), this.getTableInDatabase(), this.getTableInDatabase_UpdateStatement(), "targetTable", null, 1, 1, QueryUpdateStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(updateAssignmentExpressionEClass, UpdateAssignmentExpression.class, "UpdateAssignmentExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getUpdateAssignmentExpression_UpdateStatement(), this.getQueryUpdateStatement(), this.getQueryUpdateStatement_AssignmentClause(), "updateStatement", null, 0, 1, UpdateAssignmentExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUpdateAssignmentExpression_TargetColumnList(), this.getValueExpressionColumn(), this.getValueExpressionColumn_AssignmentExprTarget(), "targetColumnList", null, 1, -1, UpdateAssignmentExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUpdateAssignmentExpression_UpdateSource(), this.getUpdateSource(), this.getUpdateSource_UpdateAssignmentExpr(), "updateSource", null, 1, 1, UpdateAssignmentExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUpdateAssignmentExpression_MergeUpdateSpec(), this.getMergeUpdateSpecification(), this.getMergeUpdateSpecification_AssignementExprList(), "mergeUpdateSpec", null, 0, 1, UpdateAssignmentExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(cursorReferenceEClass, CursorReference.class, "CursorReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCursorReference_UpdateStatement(), this.getQueryUpdateStatement(), this.getQueryUpdateStatement_WhereCurrentOfClause(), "updateStatement", null, 0, 1, CursorReference.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCursorReference_DeleteStatement(), this.getQueryDeleteStatement(), this.getQueryDeleteStatement_WhereCurrentOfClause(), "deleteStatement", null, 0, 1, CursorReference.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(querySearchConditionEClass, QuerySearchCondition.class, "QuerySearchCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQuerySearchCondition_NegatedCondition(), ecorePackage.getEBoolean(), "negatedCondition", null, 0, 1, QuerySearchCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySearchCondition_UpdateStatement(), this.getQueryUpdateStatement(), this.getQueryUpdateStatement_WhereClause(), "updateStatement", null, 0, 1, QuerySearchCondition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySearchCondition_DeleteStatement(), this.getQueryDeleteStatement(), this.getQueryDeleteStatement_WhereClause(), "deleteStatement", null, 0, 1, QuerySearchCondition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySearchCondition_TableJoined(), this.getTableJoined(), this.getTableJoined_JoinCondition(), "tableJoined", null, 0, 1, QuerySearchCondition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySearchCondition_CombinedLeft(), this.getSearchConditionCombined(), this.getSearchConditionCombined_LeftCondition(), "combinedLeft", null, 0, 1, QuerySearchCondition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySearchCondition_CombinedRight(), this.getSearchConditionCombined(), this.getSearchConditionCombined_RightCondition(), "combinedRight", null, 0, 1, QuerySearchCondition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySearchCondition_QuerySelectHaving(), this.getQuerySelect(), this.getQuerySelect_HavingClause(), "querySelectHaving", null, 0, 1, QuerySearchCondition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySearchCondition_QuerySelectWhere(), this.getQuerySelect(), this.getQuerySelect_WhereClause(), "querySelectWhere", null, 0, 1, QuerySearchCondition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySearchCondition_ValueExprCaseSearchContent(), this.getValueExpressionCaseSearchContent(), this.getValueExpressionCaseSearchContent_SearchCondition(), "valueExprCaseSearchContent", null, 0, 1, QuerySearchCondition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySearchCondition_Nest(), this.getSearchConditionNested(), this.getSearchConditionNested_NestedCondition(), "nest", null, 0, 1, QuerySearchCondition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySearchCondition_MergeOnCondition(), this.getMergeOnCondition(), this.getMergeOnCondition_SearchCondition(), "mergeOnCondition", null, 0, 1, QuerySearchCondition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryExpressionBodyEClass, QueryExpressionBody.class, "QueryExpressionBody", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQueryExpressionBody_RowFetchLimit(), ecorePackage.getEInt(), "rowFetchLimit", null, 0, 1, QueryExpressionBody.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionBody_QueryExpression(), this.getQueryExpressionRoot(), this.getQueryExpressionRoot_Query(), "queryExpression", null, 0, 1, QueryExpressionBody.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionBody_CombinedLeft(), this.getQueryCombined(), this.getQueryCombined_LeftQuery(), "combinedLeft", null, 0, 1, QueryExpressionBody.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionBody_CombinedRight(), this.getQueryCombined(), this.getQueryCombined_RightQuery(), "combinedRight", null, 0, 1, QueryExpressionBody.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionBody_PredicateExists(), this.getPredicateExists(), this.getPredicateExists_QueryExpr(), "predicateExists", null, 0, 1, QueryExpressionBody.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionBody_UpdateSourceQuery(), this.getUpdateSourceQuery(), this.getUpdateSourceQuery_QueryExpr(), "updateSourceQuery", null, 1, 1, QueryExpressionBody.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionBody_WithTableSpecification(), this.getWithTableSpecification(), this.getWithTableSpecification_WithTableQueryExpr(), "withTableSpecification", null, 1, 1, QueryExpressionBody.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionBody_QueryNest(), this.getQueryNested(), this.getQueryNested_NestedQuery(), "queryNest", null, 0, 1, QueryExpressionBody.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionBody_SortSpecList(), this.getOrderBySpecification(), this.getOrderBySpecification_Query(), "sortSpecList", null, 0, -1, QueryExpressionBody.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryValueExpressionEClass, QueryValueExpression.class, "QueryValueExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQueryValueExpression_UnaryOperator(), this.getValueExpressionUnaryOperator(), "unaryOperator", null, 0, 1, QueryValueExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_DataType(), theSQLDataTypesPackage.getDataType(), null, "dataType", null, 0, 1, QueryValueExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValuesRow(), this.getValuesRow(), this.getValuesRow_ExprList(), "valuesRow", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_OrderByValueExpr(), this.getOrderByValueExpression(), this.getOrderByValueExpression_ValueExpr(), "orderByValueExpr", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ResultColumn(), this.getResultColumn(), this.getResultColumn_ValueExpr(), "resultColumn", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_BasicRight(), this.getPredicateBasic(), this.getPredicateBasic_RightValueExpr(), "basicRight", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_BasicLeft(), this.getPredicateBasic(), this.getPredicateBasic_LeftValueExpr(), "basicLeft", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_LikePattern(), this.getPredicateLike(), this.getPredicateLike_PatternValueExpr(), "likePattern", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_LikeMatching(), this.getPredicateLike(), this.getPredicateLike_MatchingValueExpr(), "likeMatching", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_PredicateNull(), this.getPredicateIsNull(), this.getPredicateIsNull_ValueExpr(), "predicateNull", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_InValueListRight(), this.getPredicateInValueList(), this.getPredicateInValueList_ValueExprList(), "inValueListRight", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_InValueListLeft(), this.getPredicateInValueList(), this.getPredicateInValueList_ValueExpr(), "inValueListLeft", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_InValueRowSelectLeft(), this.getPredicateInValueRowSelect(), this.getPredicateInValueRowSelect_ValueExprList(), "inValueRowSelectLeft", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_InValueSelectLeft(), this.getPredicateInValueSelect(), this.getPredicateInValueSelect_ValueExpr(), "inValueSelectLeft", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_QuantifiedRowSelectLeft(), this.getPredicateQuantifiedRowSelect(), this.getPredicateQuantifiedRowSelect_ValueExprList(), "quantifiedRowSelectLeft", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_QuantifiedValueSelectLeft(), this.getPredicateQuantifiedValueSelect(), this.getPredicateQuantifiedValueSelect_ValueExpr(), "quantifiedValueSelectLeft", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_BetweenLeft(), this.getPredicateBetween(), this.getPredicateBetween_LeftValueExpr(), "betweenLeft", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_BetweenRight1(), this.getPredicateBetween(), this.getPredicateBetween_RightValueExpr1(), "betweenRight1", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_BetweenRight2(), this.getPredicateBetween(), this.getPredicateBetween_RightValueExpr2(), "betweenRight2", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValueExprCast(), this.getValueExpressionCast(), this.getValueExpressionCast_ValueExpr(), "valueExprCast", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValueExprFunction(), this.getValueExpressionFunction(), this.getValueExpressionFunction_ParameterList(), "valueExprFunction", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValueExprCombinedLeft(), this.getValueExpressionCombined(), this.getValueExpressionCombined_LeftValueExpr(), "valueExprCombinedLeft", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValueExprCombinedRight(), this.getValueExpressionCombined(), this.getValueExpressionCombined_RightValueExpr(), "valueExprCombinedRight", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_GroupingExpr(), this.getGroupingExpression(), this.getGroupingExpression_ValueExpr(), "groupingExpr", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValueExprCaseElse(), this.getValueExpressionCaseElse(), this.getValueExpressionCaseElse_ValueExpr(), "valueExprCaseElse", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValueExprCaseSimple(), this.getValueExpressionCaseSimple(), this.getValueExpressionCaseSimple_ValueExpr(), "valueExprCaseSimple", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValueExprCaseSimpleContentWhen(), this.getValueExpressionCaseSimpleContent(), this.getValueExpressionCaseSimpleContent_WhenValueExpr(), "valueExprCaseSimpleContentWhen", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValueExprCaseSimpleContentResult(), this.getValueExpressionCaseSimpleContent(), this.getValueExpressionCaseSimpleContent_ResultValueExpr(), "valueExprCaseSimpleContentResult", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValueExprCaseSearchContent(), this.getValueExpressionCaseSearchContent(), this.getValueExpressionCaseSearchContent_ValueExpr(), "valueExprCaseSearchContent", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_LikeEscape(), this.getPredicateLike(), this.getPredicateLike_EscapeValueExpr(), "likeEscape", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValueExprLabeledDuration(), this.getValueExpressionLabeledDuration(), this.getValueExpressionLabeledDuration_ValueExpr(), "valueExprLabeledDuration", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_Nest(), this.getValueExpressionNested(), this.getValueExpressionNested_NestedValueExpr(), "nest", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_UpdateSourceExprList(), this.getUpdateSourceExprList(), this.getUpdateSourceExprList_ValueExprList(), "updateSourceExprList", null, 0, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_TableFunction(), this.getTableFunction(), this.getTableFunction_ParameterList(), "tableFunction", null, 1, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_ValueExprRow(), this.getValueExpressionRow(), this.getValueExpressionRow_ValueExprList(), "valueExprRow", null, 1, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryValueExpression_CallStatement(), this.getCallStatement(), this.getCallStatement_ArgumentList(), "callStatement", null, 1, 1, QueryValueExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryExpressionRootEClass, QueryExpressionRoot.class, "QueryExpressionRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQueryExpressionRoot_InsertStatement(), this.getQueryInsertStatement(), this.getQueryInsertStatement_SourceQuery(), "insertStatement", null, 0, 1, QueryExpressionRoot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionRoot_SelectStatement(), this.getQuerySelectStatement(), this.getQuerySelectStatement_QueryExpr(), "selectStatement", null, 0, 1, QueryExpressionRoot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionRoot_WithClause(), this.getWithTableSpecification(), this.getWithTableSpecification_QueryExpressionRoot(), "withClause", null, 0, -1, QueryExpressionRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionRoot_Query(), this.getQueryExpressionBody(), this.getQueryExpressionBody_QueryExpression(), "query", null, 1, 1, QueryExpressionRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionRoot_InValueRowSelectRight(), this.getPredicateInValueRowSelect(), this.getPredicateInValueRowSelect_QueryExpr(), "inValueRowSelectRight", null, 0, 1, QueryExpressionRoot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionRoot_InValueSelectRight(), this.getPredicateInValueSelect(), this.getPredicateInValueSelect_QueryExpr(), "inValueSelectRight", null, 0, 1, QueryExpressionRoot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionRoot_QuantifiedRowSelectRight(), this.getPredicateQuantifiedRowSelect(), this.getPredicateQuantifiedRowSelect_QueryExpr(), "quantifiedRowSelectRight", null, 0, 1, QueryExpressionRoot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionRoot_QuantifiedValueSelectRight(), this.getPredicateQuantifiedValueSelect(), this.getPredicateQuantifiedValueSelect_QueryExpr(), "quantifiedValueSelectRight", null, 0, 1, QueryExpressionRoot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryExpressionRoot_ValExprScalarSelect(), this.getValueExpressionScalarSelect(), this.getValueExpressionScalarSelect_QueryExpr(), "valExprScalarSelect", null, 0, 1, QueryExpressionRoot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valuesRowEClass, ValuesRow.class, "ValuesRow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValuesRow_InsertStatement(), this.getQueryInsertStatement(), this.getQueryInsertStatement_SourceValuesRowList(), "insertStatement", null, 0, 1, ValuesRow.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValuesRow_ExprList(), this.getQueryValueExpression(), this.getQueryValueExpression_ValuesRow(), "exprList", null, 1, -1, ValuesRow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValuesRow_QueryValues(), this.getQueryValues(), this.getQueryValues_ValuesRowList(), "queryValues", null, 1, 1, ValuesRow.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryValuesEClass, QueryValues.class, "QueryValues", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQueryValues_ValuesRowList(), this.getValuesRow(), this.getValuesRow_QueryValues(), "valuesRowList", null, 1, -1, QueryValues.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableReferenceEClass, TableReference.class, "TableReference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTableReference_TableJoinedRight(), this.getTableJoined(), this.getTableJoined_TableRefRight(), "tableJoinedRight", null, 1, 1, TableReference.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableReference_TableJoinedLeft(), this.getTableJoined(), this.getTableJoined_TableRefLeft(), "tableJoinedLeft", null, 1, 1, TableReference.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableReference_QuerySelect(), this.getQuerySelect(), this.getQuerySelect_FromClause(), "querySelect", null, 0, 1, TableReference.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableReference_Nest(), this.getTableNested(), this.getTableNested_NestedTableRef(), "nest", null, 0, 1, TableReference.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableReference_MergeSourceTable(), this.getMergeSourceTable(), this.getMergeSourceTable_TableRef(), "mergeSourceTable", null, 0, 1, TableReference.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableExpressionEClass, TableExpression.class, "TableExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTableExpression_ColumnList(), this.getValueExpressionColumn(), this.getValueExpressionColumn_ParentTableExpr(), "columnList", null, 0, -1, TableExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableExpression_TableCorrelation(), this.getTableCorrelation(), this.getTableCorrelation_TableExpr(), "tableCorrelation", null, 0, 1, TableExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableExpression_ResultTableAllColumns(), this.getResultTableAllColumns(), this.getResultTableAllColumns_TableExpr(), "resultTableAllColumns", null, 0, -1, TableExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableExpression_ValueExprColumns(), this.getValueExpressionColumn(), this.getValueExpressionColumn_TableExpr(), "valueExprColumns", null, 0, -1, TableExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableExpression_MergeTargetTable(), this.getMergeTargetTable(), this.getMergeTargetTable_TableExpr(), "mergeTargetTable", null, 0, 1, TableExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableJoinedEClass, TableJoined.class, "TableJoined", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTableJoined_JoinOperator(), this.getTableJoinedOperator(), "joinOperator", null, 0, 1, TableJoined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableJoined_JoinCondition(), this.getQuerySearchCondition(), this.getQuerySearchCondition_TableJoined(), "joinCondition", null, 0, 1, TableJoined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableJoined_TableRefRight(), this.getTableReference(), this.getTableReference_TableJoinedRight(), "tableRefRight", null, 1, 1, TableJoined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableJoined_TableRefLeft(), this.getTableReference(), this.getTableReference_TableJoinedLeft(), "tableRefLeft", null, 1, 1, TableJoined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(withTableSpecificationEClass, WithTableSpecification.class, "WithTableSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getWithTableSpecification_QueryExpressionRoot(), this.getQueryExpressionRoot(), this.getQueryExpressionRoot_WithClause(), "queryExpressionRoot", null, 0, 1, WithTableSpecification.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getWithTableSpecification_WithTableQueryExpr(), this.getQueryExpressionBody(), this.getQueryExpressionBody_WithTableSpecification(), "withTableQueryExpr", null, 1, 1, WithTableSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getWithTableSpecification_WithTableReferences(), this.getWithTableReference(), this.getWithTableReference_WithTableSpecification(), "withTableReferences", null, 0, -1, WithTableSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getWithTableSpecification_ColumnNameList(), this.getColumnName(), this.getColumnName_WithTableSpecification(), "columnNameList", null, 0, -1, WithTableSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateEClass, Predicate.class, "Predicate", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPredicate_NegatedPredicate(), ecorePackage.getEBoolean(), "negatedPredicate", null, 0, 1, Predicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPredicate_HasSelectivity(), ecorePackage.getEBoolean(), "hasSelectivity", null, 0, 1, Predicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPredicate_SelectivityValue(), ecorePackage.getEIntegerObject(), "selectivityValue", null, 0, 1, Predicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(searchConditionCombinedEClass, SearchConditionCombined.class, "SearchConditionCombined", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSearchConditionCombined_CombinedOperator(), this.getSearchConditionCombinedOperator(), "combinedOperator", null, 0, 1, SearchConditionCombined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSearchConditionCombined_LeftCondition(), this.getQuerySearchCondition(), this.getQuerySearchCondition_CombinedLeft(), "leftCondition", null, 1, 1, SearchConditionCombined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSearchConditionCombined_RightCondition(), this.getQuerySearchCondition(), this.getQuerySearchCondition_CombinedRight(), "rightCondition", null, 1, 1, SearchConditionCombined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(orderByValueExpressionEClass, OrderByValueExpression.class, "OrderByValueExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getOrderByValueExpression_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_OrderByValueExpr(), "valueExpr", null, 1, 1, OrderByValueExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryCombinedEClass, QueryCombined.class, "QueryCombined", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQueryCombined_CombinedOperator(), this.getQueryCombinedOperator(), "combinedOperator", null, 0, 1, QueryCombined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryCombined_LeftQuery(), this.getQueryExpressionBody(), this.getQueryExpressionBody_CombinedLeft(), "leftQuery", null, 1, 1, QueryCombined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryCombined_RightQuery(), this.getQueryExpressionBody(), this.getQueryExpressionBody_CombinedRight(), "rightQuery", null, 1, 1, QueryCombined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(querySelectEClass, QuerySelect.class, "QuerySelect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQuerySelect_Distinct(), ecorePackage.getEBoolean(), "distinct", null, 0, 1, QuerySelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySelect_HavingClause(), this.getQuerySearchCondition(), this.getQuerySearchCondition_QuerySelectHaving(), "havingClause", null, 0, 1, QuerySelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySelect_WhereClause(), this.getQuerySearchCondition(), this.getQuerySearchCondition_QuerySelectWhere(), "whereClause", null, 0, 1, QuerySelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySelect_GroupByClause(), this.getGroupingSpecification(), this.getGroupingSpecification_QuerySelect(), "groupByClause", null, 0, -1, QuerySelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySelect_SelectClause(), this.getQueryResultSpecification(), this.getQueryResultSpecification_QuerySelect(), "selectClause", null, 0, -1, QuerySelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySelect_FromClause(), this.getTableReference(), this.getTableReference_QuerySelect(), "fromClause", null, 1, -1, QuerySelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQuerySelect_IntoClause(), this.getValueExpressionVariable(), this.getValueExpressionVariable_QuerySelect(), "intoClause", null, 0, -1, QuerySelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(groupingSpecificationEClass, GroupingSpecification.class, "GroupingSpecification", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGroupingSpecification_QuerySelect(), this.getQuerySelect(), this.getQuerySelect_GroupByClause(), "querySelect", null, 0, 1, GroupingSpecification.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryResultSpecificationEClass, QueryResultSpecification.class, "QueryResultSpecification", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQueryResultSpecification_QuerySelect(), this.getQuerySelect(), this.getQuerySelect_SelectClause(), "querySelect", null, 0, 1, QueryResultSpecification.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(resultTableAllColumnsEClass, ResultTableAllColumns.class, "ResultTableAllColumns", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getResultTableAllColumns_TableExpr(), this.getTableExpression(), this.getTableExpression_ResultTableAllColumns(), "tableExpr", null, 1, 1, ResultTableAllColumns.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(resultColumnEClass, ResultColumn.class, "ResultColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getResultColumn_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_ResultColumn(), "valueExpr", null, 1, 1, ResultColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getResultColumn_OrderByResultCol(), this.getOrderByResultColumn(), this.getOrderByResultColumn_ResultCol(), "orderByResultCol", null, 0, -1, ResultColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateBasicEClass, PredicateBasic.class, "PredicateBasic", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPredicateBasic_ComparisonOperator(), this.getPredicateComparisonOperator(), "comparisonOperator", null, 0, 1, PredicateBasic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateBasic_RightValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_BasicRight(), "rightValueExpr", null, 1, 1, PredicateBasic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateBasic_LeftValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_BasicLeft(), "leftValueExpr", null, 1, 1, PredicateBasic.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateQuantifiedEClass, PredicateQuantified.class, "PredicateQuantified", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(predicateBetweenEClass, PredicateBetween.class, "PredicateBetween", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPredicateBetween_NotBetween(), ecorePackage.getEBoolean(), "notBetween", null, 0, 1, PredicateBetween.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateBetween_LeftValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_BetweenLeft(), "leftValueExpr", null, 1, 1, PredicateBetween.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateBetween_RightValueExpr1(), this.getQueryValueExpression(), this.getQueryValueExpression_BetweenRight1(), "rightValueExpr1", null, 1, 1, PredicateBetween.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateBetween_RightValueExpr2(), this.getQueryValueExpression(), this.getQueryValueExpression_BetweenRight2(), "rightValueExpr2", null, 1, 1, PredicateBetween.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateExistsEClass, PredicateExists.class, "PredicateExists", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPredicateExists_QueryExpr(), this.getQueryExpressionBody(), this.getQueryExpressionBody_PredicateExists(), "queryExpr", null, 1, 1, PredicateExists.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateInEClass, PredicateIn.class, "PredicateIn", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPredicateIn_NotIn(), ecorePackage.getEBoolean(), "notIn", null, 0, 1, PredicateIn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateLikeEClass, PredicateLike.class, "PredicateLike", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPredicateLike_NotLike(), ecorePackage.getEBoolean(), "notLike", null, 0, 1, PredicateLike.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateLike_PatternValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_LikePattern(), "patternValueExpr", null, 1, 1, PredicateLike.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateLike_MatchingValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_LikeMatching(), "matchingValueExpr", null, 1, 1, PredicateLike.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateLike_EscapeValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_LikeEscape(), "escapeValueExpr", null, 0, 1, PredicateLike.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateIsNullEClass, PredicateIsNull.class, "PredicateIsNull", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPredicateIsNull_NotNull(), ecorePackage.getEBoolean(), "notNull", null, 0, 1, PredicateIsNull.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateIsNull_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_PredicateNull(), "valueExpr", null, 1, 1, PredicateIsNull.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateQuantifiedValueSelectEClass, PredicateQuantifiedValueSelect.class, "PredicateQuantifiedValueSelect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPredicateQuantifiedValueSelect_QuantifiedType(), this.getPredicateQuantifiedType(), "quantifiedType", null, 0, 1, PredicateQuantifiedValueSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPredicateQuantifiedValueSelect_ComparisonOperator(), this.getPredicateComparisonOperator(), "comparisonOperator", null, 0, 1, PredicateQuantifiedValueSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateQuantifiedValueSelect_QueryExpr(), this.getQueryExpressionRoot(), this.getQueryExpressionRoot_QuantifiedValueSelectRight(), "queryExpr", null, 1, 1, PredicateQuantifiedValueSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateQuantifiedValueSelect_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_QuantifiedValueSelectLeft(), "valueExpr", null, 1, 1, PredicateQuantifiedValueSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateQuantifiedRowSelectEClass, PredicateQuantifiedRowSelect.class, "PredicateQuantifiedRowSelect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPredicateQuantifiedRowSelect_QuantifiedType(), this.getPredicateQuantifiedType(), "quantifiedType", null, 0, 1, PredicateQuantifiedRowSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateQuantifiedRowSelect_QueryExpr(), this.getQueryExpressionRoot(), this.getQueryExpressionRoot_QuantifiedRowSelectRight(), "queryExpr", null, 1, 1, PredicateQuantifiedRowSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateQuantifiedRowSelect_ValueExprList(), this.getQueryValueExpression(), this.getQueryValueExpression_QuantifiedRowSelectLeft(), "valueExprList", null, 1, -1, PredicateQuantifiedRowSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateInValueSelectEClass, PredicateInValueSelect.class, "PredicateInValueSelect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPredicateInValueSelect_QueryExpr(), this.getQueryExpressionRoot(), this.getQueryExpressionRoot_InValueSelectRight(), "queryExpr", null, 1, 1, PredicateInValueSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateInValueSelect_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_InValueSelectLeft(), "valueExpr", null, 1, 1, PredicateInValueSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateInValueListEClass, PredicateInValueList.class, "PredicateInValueList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPredicateInValueList_ValueExprList(), this.getQueryValueExpression(), this.getQueryValueExpression_InValueListRight(), "valueExprList", null, 1, -1, PredicateInValueList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateInValueList_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_InValueListLeft(), "valueExpr", null, 1, 1, PredicateInValueList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(predicateInValueRowSelectEClass, PredicateInValueRowSelect.class, "PredicateInValueRowSelect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPredicateInValueRowSelect_ValueExprList(), this.getQueryValueExpression(), this.getQueryValueExpression_InValueRowSelectLeft(), "valueExprList", null, 1, -1, PredicateInValueRowSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPredicateInValueRowSelect_QueryExpr(), this.getQueryExpressionRoot(), this.getQueryExpressionRoot_InValueRowSelectRight(), "queryExpr", null, 1, 1, PredicateInValueRowSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionSimpleEClass, ValueExpressionSimple.class, "ValueExpressionSimple", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getValueExpressionSimple_Value(), ecorePackage.getEString(), "value", null, 0, 1, ValueExpressionSimple.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionColumnEClass, ValueExpressionColumn.class, "ValueExpressionColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionColumn_AssignmentExprTarget(), this.getUpdateAssignmentExpression(), this.getUpdateAssignmentExpression_TargetColumnList(), "assignmentExprTarget", null, 0, -1, ValueExpressionColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionColumn_ParentTableExpr(), this.getTableExpression(), this.getTableExpression_ColumnList(), "parentTableExpr", null, 1, 1, ValueExpressionColumn.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionColumn_InsertStatement(), this.getQueryInsertStatement(), this.getQueryInsertStatement_TargetColumnList(), "insertStatement", null, 0, -1, ValueExpressionColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionColumn_TableExpr(), this.getTableExpression(), this.getTableExpression_ValueExprColumns(), "tableExpr", null, 1, 1, ValueExpressionColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionColumn_TableInDatabase(), this.getTableInDatabase(), this.getTableInDatabase_DerivedColumnList(), "tableInDatabase", null, 0, 1, ValueExpressionColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionColumn_MergeInsertSpec(), this.getMergeInsertSpecification(), this.getMergeInsertSpecification_TargetColumnList(), "mergeInsertSpec", null, 0, -1, ValueExpressionColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionVariableEClass, ValueExpressionVariable.class, "ValueExpressionVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionVariable_QuerySelect(), this.getQuerySelect(), this.getQuerySelect_IntoClause(), "querySelect", null, 0, 1, ValueExpressionVariable.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionScalarSelectEClass, ValueExpressionScalarSelect.class, "ValueExpressionScalarSelect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionScalarSelect_QueryExpr(), this.getQueryExpressionRoot(), this.getQueryExpressionRoot_ValExprScalarSelect(), "queryExpr", null, 1, 1, ValueExpressionScalarSelect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionLabeledDurationEClass, ValueExpressionLabeledDuration.class, "ValueExpressionLabeledDuration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getValueExpressionLabeledDuration_LabeledDurationType(), this.getValueExpressionLabeledDurationType(), "labeledDurationType", null, 0, 1, ValueExpressionLabeledDuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionLabeledDuration_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_ValueExprLabeledDuration(), "valueExpr", null, 1, 1, ValueExpressionLabeledDuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionCaseEClass, ValueExpressionCase.class, "ValueExpressionCase", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionCase_CaseElse(), this.getValueExpressionCaseElse(), this.getValueExpressionCaseElse_ValueExprCase(), "caseElse", null, 0, 1, ValueExpressionCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionCastEClass, ValueExpressionCast.class, "ValueExpressionCast", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionCast_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_ValueExprCast(), "valueExpr", null, 1, 1, ValueExpressionCast.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionNullValueEClass, ValueExpressionNullValue.class, "ValueExpressionNullValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(valueExpressionDefaultValueEClass, ValueExpressionDefaultValue.class, "ValueExpressionDefaultValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(valueExpressionFunctionEClass, ValueExpressionFunction.class, "ValueExpressionFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getValueExpressionFunction_SpecialRegister(), ecorePackage.getEBoolean(), "specialRegister", null, 0, 1, ValueExpressionFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getValueExpressionFunction_Distinct(), ecorePackage.getEBoolean(), "distinct", null, 0, 1, ValueExpressionFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getValueExpressionFunction_ColumnFunction(), ecorePackage.getEBoolean(), "columnFunction", null, 0, 1, ValueExpressionFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionFunction_ParameterList(), this.getQueryValueExpression(), this.getQueryValueExpression_ValueExprFunction(), "parameterList", null, 0, -1, ValueExpressionFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionFunction_Function(), theSQLRoutinesPackage.getFunction(), null, "function", null, 1, 1, ValueExpressionFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionCombinedEClass, ValueExpressionCombined.class, "ValueExpressionCombined", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getValueExpressionCombined_CombinedOperator(), this.getValueExpressionCombinedOperator(), "combinedOperator", null, 0, 1, ValueExpressionCombined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionCombined_LeftValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_ValueExprCombinedLeft(), "leftValueExpr", null, 1, 1, ValueExpressionCombined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionCombined_RightValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_ValueExprCombinedRight(), "rightValueExpr", null, 1, 1, ValueExpressionCombined.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(groupingSetsEClass, GroupingSets.class, "GroupingSets", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGroupingSets_GroupingSetsElementList(), this.getGroupingSetsElement(), this.getGroupingSetsElement_GroupingSets(), "groupingSetsElementList", null, 1, -1, GroupingSets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(groupingEClass, Grouping.class, "Grouping", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGrouping_GroupingSetsElementExpr(), this.getGroupingSetsElementExpression(), this.getGroupingSetsElementExpression_Grouping(), "groupingSetsElementExpr", null, 0, 1, Grouping.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(groupingSetsElementEClass, GroupingSetsElement.class, "GroupingSetsElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGroupingSetsElement_GroupingSets(), this.getGroupingSets(), this.getGroupingSets_GroupingSetsElementList(), "groupingSets", null, 0, 1, GroupingSetsElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(groupingSetsElementSublistEClass, GroupingSetsElementSublist.class, "GroupingSetsElementSublist", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGroupingSetsElementSublist_GroupingSetsElementExprList(), this.getGroupingSetsElementExpression(), this.getGroupingSetsElementExpression_GroupingSetsElementSublist(), "groupingSetsElementExprList", null, 1, -1, GroupingSetsElementSublist.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(groupingSetsElementExpressionEClass, GroupingSetsElementExpression.class, "GroupingSetsElementExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGroupingSetsElementExpression_GroupingSetsElementSublist(), this.getGroupingSetsElementSublist(), this.getGroupingSetsElementSublist_GroupingSetsElementExprList(), "groupingSetsElementSublist", null, 0, 1, GroupingSetsElementExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGroupingSetsElementExpression_Grouping(), this.getGrouping(), this.getGrouping_GroupingSetsElementExpr(), "grouping", null, 1, 1, GroupingSetsElementExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(superGroupEClass, SuperGroup.class, "SuperGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSuperGroup_SuperGroupType(), this.getSuperGroupType(), "superGroupType", null, 0, 1, SuperGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSuperGroup_SuperGroupElementList(), this.getSuperGroupElement(), this.getSuperGroupElement_SuperGroup(), "superGroupElementList", null, 0, -1, SuperGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(groupingExpressionEClass, GroupingExpression.class, "GroupingExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGroupingExpression_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_GroupingExpr(), "valueExpr", null, 1, 1, GroupingExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGroupingExpression_SuperGroupElementExpr(), this.getSuperGroupElementExpression(), this.getSuperGroupElementExpression_GroupingExpr(), "superGroupElementExpr", null, 0, 1, GroupingExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(superGroupElementEClass, SuperGroupElement.class, "SuperGroupElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSuperGroupElement_SuperGroup(), this.getSuperGroup(), this.getSuperGroup_SuperGroupElementList(), "superGroup", null, 0, 1, SuperGroupElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(superGroupElementSublistEClass, SuperGroupElementSublist.class, "SuperGroupElementSublist", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSuperGroupElementSublist_SuperGroupElementExprList(), this.getSuperGroupElementExpression(), this.getSuperGroupElementExpression_SuperGroupElementSublist(), "superGroupElementExprList", null, 1, -1, SuperGroupElementSublist.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(superGroupElementExpressionEClass, SuperGroupElementExpression.class, "SuperGroupElementExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSuperGroupElementExpression_SuperGroupElementSublist(), this.getSuperGroupElementSublist(), this.getSuperGroupElementSublist_SuperGroupElementExprList(), "superGroupElementSublist", null, 0, 1, SuperGroupElementExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSuperGroupElementExpression_GroupingExpr(), this.getGroupingExpression(), this.getGroupingExpression_SuperGroupElementExpr(), "groupingExpr", null, 1, 1, SuperGroupElementExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionCaseSearchEClass, ValueExpressionCaseSearch.class, "ValueExpressionCaseSearch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionCaseSearch_SearchContentList(), this.getValueExpressionCaseSearchContent(), this.getValueExpressionCaseSearchContent_ValueExprCaseSearch(), "searchContentList", null, 1, -1, ValueExpressionCaseSearch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionCaseSimpleEClass, ValueExpressionCaseSimple.class, "ValueExpressionCaseSimple", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionCaseSimple_ContentList(), this.getValueExpressionCaseSimpleContent(), this.getValueExpressionCaseSimpleContent_ValueExprCaseSimple(), "contentList", null, 1, -1, ValueExpressionCaseSimple.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionCaseSimple_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_ValueExprCaseSimple(), "valueExpr", null, 1, 1, ValueExpressionCaseSimple.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionCaseElseEClass, ValueExpressionCaseElse.class, "ValueExpressionCaseElse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionCaseElse_ValueExprCase(), this.getValueExpressionCase(), this.getValueExpressionCase_CaseElse(), "valueExprCase", null, 0, 1, ValueExpressionCaseElse.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionCaseElse_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_ValueExprCaseElse(), "valueExpr", null, 0, 1, ValueExpressionCaseElse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionCaseSearchContentEClass, ValueExpressionCaseSearchContent.class, "ValueExpressionCaseSearchContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionCaseSearchContent_ValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_ValueExprCaseSearchContent(), "valueExpr", null, 1, 1, ValueExpressionCaseSearchContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionCaseSearchContent_SearchCondition(), this.getQuerySearchCondition(), this.getQuerySearchCondition_ValueExprCaseSearchContent(), "searchCondition", null, 1, 1, ValueExpressionCaseSearchContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionCaseSearchContent_ValueExprCaseSearch(), this.getValueExpressionCaseSearch(), this.getValueExpressionCaseSearch_SearchContentList(), "valueExprCaseSearch", null, 0, 1, ValueExpressionCaseSearchContent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionCaseSimpleContentEClass, ValueExpressionCaseSimpleContent.class, "ValueExpressionCaseSimpleContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionCaseSimpleContent_ValueExprCaseSimple(), this.getValueExpressionCaseSimple(), this.getValueExpressionCaseSimple_ContentList(), "valueExprCaseSimple", null, 0, 1, ValueExpressionCaseSimpleContent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionCaseSimpleContent_WhenValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_ValueExprCaseSimpleContentWhen(), "whenValueExpr", null, 1, 1, ValueExpressionCaseSimpleContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueExpressionCaseSimpleContent_ResultValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_ValueExprCaseSimpleContentResult(), "resultValueExpr", null, 1, 1, ValueExpressionCaseSimpleContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableInDatabaseEClass, TableInDatabase.class, "TableInDatabase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTableInDatabase_UpdateStatement(), this.getQueryUpdateStatement(), this.getQueryUpdateStatement_TargetTable(), "updateStatement", null, 0, 1, TableInDatabase.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableInDatabase_DeleteStatement(), this.getQueryDeleteStatement(), this.getQueryDeleteStatement_TargetTable(), "deleteStatement", null, 0, 1, TableInDatabase.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableInDatabase_InsertStatement(), this.getQueryInsertStatement(), this.getQueryInsertStatement_TargetTable(), "insertStatement", null, 0, 1, TableInDatabase.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableInDatabase_DatabaseTable(), theSQLTablesPackage.getTable(), null, "databaseTable", null, 1, 1, TableInDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableInDatabase_DerivedColumnList(), this.getValueExpressionColumn(), this.getValueExpressionColumn_TableInDatabase(), "derivedColumnList", null, 0, -1, TableInDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableFunctionEClass, TableFunction.class, "TableFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTableFunction_Function(), theSQLRoutinesPackage.getFunction(), null, "function", null, 1, 1, TableFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableFunction_ParameterList(), this.getQueryValueExpression(), this.getQueryValueExpression_TableFunction(), "parameterList", null, 0, -1, TableFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(sqlQueryObjectEClass, SQLQueryObject.class, "SQLQueryObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(sqlQueryObjectEClass, ecorePackage.getEString(), "getSQL", 0, 1);

        EOperation op = addEOperation(sqlQueryObjectEClass, null, "setSQL");
        addEParameter(op, ecorePackage.getEString(), "sqlText", 0, 1);

        initEClass(queryChangeStatementEClass, QueryChangeStatement.class, "QueryChangeStatement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(columnNameEClass, ColumnName.class, "ColumnName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getColumnName_TableCorrelation(), this.getTableCorrelation(), this.getTableCorrelation_ColumnNameList(), "tableCorrelation", null, 0, 1, ColumnName.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getColumnName_WithTableSpecification(), this.getWithTableSpecification(), this.getWithTableSpecification_ColumnNameList(), "withTableSpecification", null, 1, 1, ColumnName.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableNestedEClass, TableNested.class, "TableNested", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTableNested_NestedTableRef(), this.getTableReference(), this.getTableReference_Nest(), "nestedTableRef", null, 1, 1, TableNested.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryMergeStatementEClass, QueryMergeStatement.class, "QueryMergeStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQueryMergeStatement_TargetTable(), this.getMergeTargetTable(), this.getMergeTargetTable_MergeStatement(), "targetTable", null, 1, 1, QueryMergeStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryMergeStatement_SourceTable(), this.getMergeSourceTable(), this.getMergeSourceTable_MergeStatement(), "sourceTable", null, 1, 1, QueryMergeStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryMergeStatement_OnCondition(), this.getMergeOnCondition(), this.getMergeOnCondition_MergeStatement(), "onCondition", null, 1, 1, QueryMergeStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getQueryMergeStatement_OperationSpecList(), this.getMergeOperationSpecification(), this.getMergeOperationSpecification_MergeStatement(), "operationSpecList", null, 1, -1, QueryMergeStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(searchConditionNestedEClass, SearchConditionNested.class, "SearchConditionNested", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSearchConditionNested_NestedCondition(), this.getQuerySearchCondition(), this.getQuerySearchCondition_Nest(), "nestedCondition", null, 1, 1, SearchConditionNested.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionNestedEClass, ValueExpressionNested.class, "ValueExpressionNested", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionNested_NestedValueExpr(), this.getQueryValueExpression(), this.getQueryValueExpression_Nest(), "nestedValueExpr", null, 1, 1, ValueExpressionNested.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionAtomicEClass, ValueExpressionAtomic.class, "ValueExpressionAtomic", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(orderBySpecificationEClass, OrderBySpecification.class, "OrderBySpecification", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getOrderBySpecification_Descending(), ecorePackage.getEBoolean(), "descending", null, 0, 1, OrderBySpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getOrderBySpecification_OrderingSpecOption(), this.getOrderingSpecType(), "OrderingSpecOption", null, 0, 1, OrderBySpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getOrderBySpecification_NullOrderingOption(), this.getNullOrderingType(), "NullOrderingOption", null, 0, 1, OrderBySpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getOrderBySpecification_SelectStatement(), this.getQuerySelectStatement(), this.getQuerySelectStatement_OrderByClause(), "selectStatement", null, 0, 1, OrderBySpecification.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getOrderBySpecification_Query(), this.getQueryExpressionBody(), this.getQueryExpressionBody_SortSpecList(), "query", null, 0, 1, OrderBySpecification.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(orderByOrdinalEClass, OrderByOrdinal.class, "OrderByOrdinal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getOrderByOrdinal_OrdinalValue(), ecorePackage.getEInt(), "ordinalValue", null, 0, 1, OrderByOrdinal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableCorrelationEClass, TableCorrelation.class, "TableCorrelation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTableCorrelation_TableExpr(), this.getTableExpression(), this.getTableExpression_TableCorrelation(), "tableExpr", null, 0, 1, TableCorrelation.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTableCorrelation_ColumnNameList(), this.getColumnName(), this.getColumnName_TableCorrelation(), "columnNameList", null, 0, -1, TableCorrelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(updateSourceEClass, UpdateSource.class, "UpdateSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getUpdateSource_UpdateAssignmentExpr(), this.getUpdateAssignmentExpression(), this.getUpdateAssignmentExpression_UpdateSource(), "updateAssignmentExpr", null, 0, 1, UpdateSource.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(updateSourceExprListEClass, UpdateSourceExprList.class, "UpdateSourceExprList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getUpdateSourceExprList_ValueExprList(), this.getQueryValueExpression(), this.getQueryValueExpression_UpdateSourceExprList(), "valueExprList", null, 1, -1, UpdateSourceExprList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(updateSourceQueryEClass, UpdateSourceQuery.class, "UpdateSourceQuery", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getUpdateSourceQuery_QueryExpr(), this.getQueryExpressionBody(), this.getQueryExpressionBody_UpdateSourceQuery(), "queryExpr", null, 1, 1, UpdateSourceQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(orderByResultColumnEClass, OrderByResultColumn.class, "OrderByResultColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getOrderByResultColumn_ResultCol(), this.getResultColumn(), this.getResultColumn_OrderByResultCol(), "resultCol", null, 1, 1, OrderByResultColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(withTableReferenceEClass, WithTableReference.class, "WithTableReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getWithTableReference_WithTableSpecification(), this.getWithTableSpecification(), this.getWithTableSpecification_WithTableReferences(), "withTableSpecification", null, 1, 1, WithTableReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryNestedEClass, QueryNested.class, "QueryNested", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getQueryNested_NestedQuery(), this.getQueryExpressionBody(), this.getQueryExpressionBody_QueryNest(), "nestedQuery", null, 1, 1, QueryNested.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueExpressionRowEClass, ValueExpressionRow.class, "ValueExpressionRow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueExpressionRow_ValueExprList(), this.getQueryValueExpression(), this.getQueryValueExpression_ValueExprRow(), "valueExprList", null, 0, -1, ValueExpressionRow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mergeTargetTableEClass, MergeTargetTable.class, "MergeTargetTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMergeTargetTable_MergeStatement(), this.getQueryMergeStatement(), this.getQueryMergeStatement_TargetTable(), "mergeStatement", null, 0, 1, MergeTargetTable.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMergeTargetTable_TableExpr(), this.getTableExpression(), this.getTableExpression_MergeTargetTable(), "tableExpr", null, 1, 1, MergeTargetTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mergeSourceTableEClass, MergeSourceTable.class, "MergeSourceTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMergeSourceTable_QueryMergeStatement(), this.getQueryMergeStatement(), null, "QueryMergeStatement", null, 0, -1, MergeSourceTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMergeSourceTable_MergeStatement(), this.getQueryMergeStatement(), this.getQueryMergeStatement_SourceTable(), "mergeStatement", null, 0, 1, MergeSourceTable.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMergeSourceTable_TableRef(), this.getTableReference(), this.getTableReference_MergeSourceTable(), "tableRef", null, 1, 1, MergeSourceTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mergeOnConditionEClass, MergeOnCondition.class, "MergeOnCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMergeOnCondition_MergeStatement(), this.getQueryMergeStatement(), this.getQueryMergeStatement_OnCondition(), "mergeStatement", null, 0, 1, MergeOnCondition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMergeOnCondition_SearchCondition(), this.getQuerySearchCondition(), this.getQuerySearchCondition_MergeOnCondition(), "searchCondition", null, 1, 1, MergeOnCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mergeUpdateSpecificationEClass, MergeUpdateSpecification.class, "MergeUpdateSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMergeUpdateSpecification_AssignementExprList(), this.getUpdateAssignmentExpression(), this.getUpdateAssignmentExpression_MergeUpdateSpec(), "assignementExprList", null, 1, -1, MergeUpdateSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mergeInsertSpecificationEClass, MergeInsertSpecification.class, "MergeInsertSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMergeInsertSpecification_TargetColumnList(), this.getValueExpressionColumn(), this.getValueExpressionColumn_MergeInsertSpec(), "targetColumnList", null, 0, -1, MergeInsertSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMergeInsertSpecification_SourceValuesRow(), this.getValuesRow(), null, "sourceValuesRow", null, 1, 1, MergeInsertSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mergeOperationSpecificationEClass, MergeOperationSpecification.class, "MergeOperationSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMergeOperationSpecification_MergeStatement(), this.getQueryMergeStatement(), this.getQueryMergeStatement_OperationSpecList(), "mergeStatement", null, 0, 1, MergeOperationSpecification.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(updateOfColumnEClass, UpdateOfColumn.class, "UpdateOfColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getUpdateOfColumn_UpdatabilityExpr(), this.getUpdatabilityExpression(), this.getUpdatabilityExpression_UpdateOfColumnList(), "updatabilityExpr", null, 0, 1, UpdateOfColumn.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(updatabilityExpressionEClass, UpdatabilityExpression.class, "UpdatabilityExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUpdatabilityExpression_UpdatabilityType(), this.getUpdatabilityType(), "updatabilityType", null, 0, 1, UpdatabilityExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUpdatabilityExpression_UpdateOfColumnList(), this.getUpdateOfColumn(), this.getUpdateOfColumn_UpdatabilityExpr(), "updateOfColumnList", null, 0, -1, UpdatabilityExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUpdatabilityExpression_SelectStatement(), this.getQuerySelectStatement(), this.getQuerySelectStatement_UpdatabilityExpr(), "selectStatement", null, 0, 1, UpdatabilityExpression.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(callStatementEClass, CallStatement.class, "CallStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCallStatement_ArgumentList(), this.getQueryValueExpression(), this.getQueryValueExpression_CallStatement(), "argumentList", null, 0, -1, CallStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCallStatement_ProcedureRef(), this.getProcedureReference(), this.getProcedureReference_CallStatement(), "procedureRef", null, 1, 1, CallStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(procedureReferenceEClass, ProcedureReference.class, "ProcedureReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getProcedureReference_CallStatement(), this.getCallStatement(), this.getCallStatement_ProcedureRef(), "callStatement", null, 1, 1, ProcedureReference.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getProcedureReference_Procedure(), theSQLRoutinesPackage.getProcedure(), null, "procedure", null, 1, 1, ProcedureReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableQueryLateralEClass, TableQueryLateral.class, "TableQueryLateral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTableQueryLateral_Query(), this.getQueryExpressionBody(), null, "query", null, 1, 1, TableQueryLateral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(superGroupTypeEEnum, SuperGroupType.class, "SuperGroupType");
        addEEnumLiteral(superGroupTypeEEnum, SuperGroupType.CUBE_LITERAL);
        addEEnumLiteral(superGroupTypeEEnum, SuperGroupType.GRANDTOTAL_LITERAL);
        addEEnumLiteral(superGroupTypeEEnum, SuperGroupType.ROLLUP_LITERAL);

        initEEnum(predicateQuantifiedTypeEEnum, PredicateQuantifiedType.class, "PredicateQuantifiedType");
        addEEnumLiteral(predicateQuantifiedTypeEEnum, PredicateQuantifiedType.SOME_LITERAL);
        addEEnumLiteral(predicateQuantifiedTypeEEnum, PredicateQuantifiedType.ANY_LITERAL);
        addEEnumLiteral(predicateQuantifiedTypeEEnum, PredicateQuantifiedType.ALL_LITERAL);

        initEEnum(predicateComparisonOperatorEEnum, PredicateComparisonOperator.class, "PredicateComparisonOperator");
        addEEnumLiteral(predicateComparisonOperatorEEnum, PredicateComparisonOperator.EQUAL_LITERAL);
        addEEnumLiteral(predicateComparisonOperatorEEnum, PredicateComparisonOperator.NOT_EQUAL_LITERAL);
        addEEnumLiteral(predicateComparisonOperatorEEnum, PredicateComparisonOperator.LESS_THAN_LITERAL);
        addEEnumLiteral(predicateComparisonOperatorEEnum, PredicateComparisonOperator.GREATER_THAN_LITERAL);
        addEEnumLiteral(predicateComparisonOperatorEEnum, PredicateComparisonOperator.LESS_THAN_OR_EQUAL_LITERAL);
        addEEnumLiteral(predicateComparisonOperatorEEnum, PredicateComparisonOperator.GREATER_THAN_OR_EQUAL_LITERAL);

        initEEnum(searchConditionCombinedOperatorEEnum, SearchConditionCombinedOperator.class, "SearchConditionCombinedOperator");
        addEEnumLiteral(searchConditionCombinedOperatorEEnum, SearchConditionCombinedOperator.AND_LITERAL);
        addEEnumLiteral(searchConditionCombinedOperatorEEnum, SearchConditionCombinedOperator.OR_LITERAL);

        initEEnum(tableJoinedOperatorEEnum, TableJoinedOperator.class, "TableJoinedOperator");
        addEEnumLiteral(tableJoinedOperatorEEnum, TableJoinedOperator.DEFAULT_INNER_LITERAL);
        addEEnumLiteral(tableJoinedOperatorEEnum, TableJoinedOperator.EXPLICIT_INNER_LITERAL);
        addEEnumLiteral(tableJoinedOperatorEEnum, TableJoinedOperator.LEFT_OUTER_LITERAL);
        addEEnumLiteral(tableJoinedOperatorEEnum, TableJoinedOperator.RIGHT_OUTER_LITERAL);
        addEEnumLiteral(tableJoinedOperatorEEnum, TableJoinedOperator.FULL_OUTER_LITERAL);

        initEEnum(queryCombinedOperatorEEnum, QueryCombinedOperator.class, "QueryCombinedOperator");
        addEEnumLiteral(queryCombinedOperatorEEnum, QueryCombinedOperator.UNION_LITERAL);
        addEEnumLiteral(queryCombinedOperatorEEnum, QueryCombinedOperator.UNION_ALL_LITERAL);
        addEEnumLiteral(queryCombinedOperatorEEnum, QueryCombinedOperator.INTERSECT_LITERAL);
        addEEnumLiteral(queryCombinedOperatorEEnum, QueryCombinedOperator.INTERSECT_ALL_LITERAL);
        addEEnumLiteral(queryCombinedOperatorEEnum, QueryCombinedOperator.EXCEPT_LITERAL);
        addEEnumLiteral(queryCombinedOperatorEEnum, QueryCombinedOperator.EXCEPT_ALL_LITERAL);

        initEEnum(valueExpressionUnaryOperatorEEnum, ValueExpressionUnaryOperator.class, "ValueExpressionUnaryOperator");
        addEEnumLiteral(valueExpressionUnaryOperatorEEnum, ValueExpressionUnaryOperator.NONE_LITERAL);
        addEEnumLiteral(valueExpressionUnaryOperatorEEnum, ValueExpressionUnaryOperator.PLUS_LITERAL);
        addEEnumLiteral(valueExpressionUnaryOperatorEEnum, ValueExpressionUnaryOperator.MINUS_LITERAL);

        initEEnum(valueExpressionCombinedOperatorEEnum, ValueExpressionCombinedOperator.class, "ValueExpressionCombinedOperator");
        addEEnumLiteral(valueExpressionCombinedOperatorEEnum, ValueExpressionCombinedOperator.ADD_LITERAL);
        addEEnumLiteral(valueExpressionCombinedOperatorEEnum, ValueExpressionCombinedOperator.SUBTRACT_LITERAL);
        addEEnumLiteral(valueExpressionCombinedOperatorEEnum, ValueExpressionCombinedOperator.MULTIPLY_LITERAL);
        addEEnumLiteral(valueExpressionCombinedOperatorEEnum, ValueExpressionCombinedOperator.DIVIDE_LITERAL);
        addEEnumLiteral(valueExpressionCombinedOperatorEEnum, ValueExpressionCombinedOperator.CONCATENATE_LITERAL);

        initEEnum(valueExpressionLabeledDurationTypeEEnum, ValueExpressionLabeledDurationType.class, "ValueExpressionLabeledDurationType");
        addEEnumLiteral(valueExpressionLabeledDurationTypeEEnum, ValueExpressionLabeledDurationType.YEARS_LITERAL);
        addEEnumLiteral(valueExpressionLabeledDurationTypeEEnum, ValueExpressionLabeledDurationType.MONTHS_LITERAL);
        addEEnumLiteral(valueExpressionLabeledDurationTypeEEnum, ValueExpressionLabeledDurationType.DAYS_LITERAL);
        addEEnumLiteral(valueExpressionLabeledDurationTypeEEnum, ValueExpressionLabeledDurationType.HOURS_LITERAL);
        addEEnumLiteral(valueExpressionLabeledDurationTypeEEnum, ValueExpressionLabeledDurationType.MINUTES_LITERAL);
        addEEnumLiteral(valueExpressionLabeledDurationTypeEEnum, ValueExpressionLabeledDurationType.SECONDS_LITERAL);
        addEEnumLiteral(valueExpressionLabeledDurationTypeEEnum, ValueExpressionLabeledDurationType.MICROSECONDS_LITERAL);

        initEEnum(nullOrderingTypeEEnum, NullOrderingType.class, "NullOrderingType");
        addEEnumLiteral(nullOrderingTypeEEnum, NullOrderingType.NONE_LITERAL);
        addEEnumLiteral(nullOrderingTypeEEnum, NullOrderingType.NULLS_FIRST_LITERAL);
        addEEnumLiteral(nullOrderingTypeEEnum, NullOrderingType.NULLS_LAST_LITERAL);

        initEEnum(orderingSpecTypeEEnum, OrderingSpecType.class, "OrderingSpecType");
        addEEnumLiteral(orderingSpecTypeEEnum, OrderingSpecType.NONE_LITERAL);
        addEEnumLiteral(orderingSpecTypeEEnum, OrderingSpecType.ASC_LITERAL);
        addEEnumLiteral(orderingSpecTypeEEnum, OrderingSpecType.DESC_LITERAL);

        initEEnum(updatabilityTypeEEnum, UpdatabilityType.class, "UpdatabilityType");
        addEEnumLiteral(updatabilityTypeEEnum, UpdatabilityType.READ_ONLY_LITERAL);
        addEEnumLiteral(updatabilityTypeEEnum, UpdatabilityType.UPDATE_LITERAL);

        // Create resource
        createResource(eNS_URI);
    }

} //SQLQueryModelPackageImpl
