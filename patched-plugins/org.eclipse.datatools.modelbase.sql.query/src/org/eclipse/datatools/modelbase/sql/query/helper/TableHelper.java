/*******************************************************************************
 * Copyright (c) 2002, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement;
import org.eclipse.datatools.modelbase.sql.query.GroupingSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeOnCondition;
import org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification;
import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateExists;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
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
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElement;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableQueryLateral;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionDefaultValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Provides utility functions related to tables and columns.
 *
 */
@SuppressWarnings("unchecked")
public class TableHelper {
     
    /** This is the default quote character to use for delimited identifiers like "Col1".  
     * When comparing with a delimited identifier, the characters inside the delimiters must 
     * match exactly.
     * <p>
     * Note: this character can vary depending on the database type, so use of this constant
     * is not recommended.  Instead get the identifier quote character from the database
     * configuration.
     */
    public static final String DELIMITED_IDENTIFIER_QUOTE = "\"";

    /**
       * Creates the From Clause with the tables used in the given SQLStatement.
       *
       * @param stmt
       *          the QueryStatement from which is used to get list of tables
       * @return the From Clause string
       */
      public static String createFromClauseForStatement(QueryStatement stmt) {
        StringBuffer sqlBuf = new StringBuffer();
    
        List tableRefList = StatementHelper.getTablesForStatement(stmt);
        TableExpression tblExpr;
    
        for (Iterator tableIt = tableRefList.iterator(); tableIt.hasNext();) {
          tblExpr = (TableExpression) tableIt.next();
          if (sqlBuf.length() > 0) {
            sqlBuf.append(", ");
          }
          //retVal = getTableForTableExpression(tblExpr).getName();
          String tblExprSQL = tblExpr.getSQL();
          sqlBuf.append(tblExprSQL);
          //TableCorrelation tblCorr = tblExpr.getTableCorrelation();
          //if (tblCorr != null) {
          //  String corrName = tblCorr.getName();
          //  retVal = retVal.concat(" as ").concat(corrName);
          //}
        }
    
        if (sqlBuf.length() > 0) {
          sqlBuf.insert(0, "From ");
        }
        
        return sqlBuf.toString();
      }
      
    /**
       * Creates the From Clause with the tables used in the given SQLStatement.
       *
       * @param stmt
       *          the QueryStatement from which is used to get list of tables
       * @return the From Clause string
       */
      public static String createFromClauseForStatement(SQLQueryObject stmt) {
        String retVal = "";
        
        if (stmt instanceof QueryStatement) {
            retVal = createFromClauseForStatement((QueryStatement)stmt);
        } else if (stmt instanceof QuerySelect) {
            StringBuffer sqlBuf = new StringBuffer();
            List tableRefList = StatementHelper.getTablesForStatement(stmt);
            TableExpression tblExpr;
            
            for (Iterator tableIt = tableRefList.iterator(); tableIt.hasNext();) {
                tblExpr = (TableExpression) tableIt.next();
                if (sqlBuf.length() > 0) {
                    sqlBuf.append(", ");
                }
                String tblExprSQL = tblExpr.getSQL(); 
                sqlBuf.append(tblExprSQL);
            }
            if (sqlBuf.length() > 0) {
                sqlBuf.insert(0, "FROM ");
            }
            retVal = sqlBuf.toString();
        }
        
        return retVal;
      }

    /**
     * Creates and returns a <code>TableInDatabase</code> object for the given 
     * <code>Table</code> object and initializes its list of SQLValueExpressColumn 
     * objects corresponding to each column of the table.
     *
     * @param table
     *          the Table for which we need a SQLRDBTable
     * @return the SQLRDBTable that is created
     */
    public static TableInDatabase createTableExpressionForTable(Table table) {
      TableInDatabase tableInDB = null;
      
      if (table != null) {
        List rdbColumnList;
        List cList;

        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        tableInDB = factory.createTableInDatabase();
        tableInDB.setDatabaseTable(table);
        String tableName = table.getName();
        tableInDB.setName( tableName );
        rdbColumnList = tableInDB.getColumnList();
        cList = table.getColumns();
        Iterator columnItr = cList.iterator();
        while (columnItr.hasNext()) {
          Column col = (Column) columnItr.next();
          ValueExpressionColumn valueExprColumn = factory.createValueExpressionColumn();
          valueExprColumn.setName(col.getName());
          valueExprColumn.setDataType(ValueExpressionHelper.copyDataType(col.getDataType()));
          rdbColumnList.add(valueExprColumn);
        }
      }
      
      return tableInDB;
    }

    /**
     * Adds <code>ValueExpressionColumn</code> objects to the column list of 
     * the given <code>QueryExpressionBody</code> for each named result column of 
     * the query.  This is useful in case the given <code>QueryExpressionBody</code> 
     * is used as a nested query, that is, as a table reference within another
     * <code>QuerySelect</code>, so that nested query can be treated like
     * any other table reference with a column list.
     * 
     * @param qryExprBody the query whose columns should be exposed
     * @return a list of already or newly exposed <code>ValueExpressionColumn</code>s
     */
    public static List exposeEffectiveResultColumns(QueryExpressionBody qryExprBody) {
        List exposedColumns = new ArrayList();
        
        if (qryExprBody instanceof QuerySelect) {
            QuerySelect select = (QuerySelect) qryExprBody;
            exposedColumns = exposeResultColumnsOfQuerySelect(select);
        }
        else if (qryExprBody instanceof QueryCombined) {
            QueryCombined combined = (QueryCombined) qryExprBody;
            exposedColumns = exposeResultColumnsOfQueryCombined(combined);
        }
        else if (qryExprBody instanceof QueryNested) {
            /* A QueryNested is a type of query expression that is a query
             * expression with parens around it. */
            QueryNested qryNested = (QueryNested) qryExprBody;
            /* Get the exposed columns of the inner (nested) query. */
            QueryExpressionBody nestedQuery = qryNested.getNestedQuery();
            exposedColumns = exposeEffectiveResultColumns(nestedQuery);
            /* Copy the exposed columns from the inner (nested) query to the outer
             * (nesting) query. */
            List qryNestedColList = qryNested.getColumnList();
            qryNestedColList.clear();
            qryNestedColList.addAll(exposedColumns);
        }
        else if (qryExprBody instanceof QueryValues) {
            QueryValues values = (QueryValues) qryExprBody;
            exposedColumns = exposeResultColumnsOfQueryValues(values);
        }
        
        return exposedColumns;
    }

    /**
     * Populates the given <code>tableExpr</code>'s <code>columnList</code> with
     * <code>ValueExpressionColumn</code>s, with <code>name</code>,
     * <code>dataType</code> and in ordering, so the given
     * <code>tableExpr</code> can be handled like a <code>TableInDatabase</code>.
     * 
     * @param tableExpr the table expression whose columns should be expose
     * @return a list of already or newly exposed <code>ValueExpressionColumn</code>s
     */
    public static List exposeEffectiveResultColumns(TableExpression tableExpr) {
        List resultColExprList = new ArrayList();
        
        if (tableExpr == null) {
            return null;
        }
        else if (tableExpr instanceof QueryExpressionBody)
        {
            QueryExpressionBody query = (QueryExpressionBody) tableExpr;
            // no column List copy, just hand it through
            resultColExprList = exposeEffectiveResultColumns(query);
        }
        else if (tableExpr instanceof TableInDatabase) 
        {
            // should already have been done! but check anyways
            TableInDatabase tableInDB = (TableInDatabase) tableExpr;
            Table dbTable = tableInDB.getDatabaseTable();
            if (dbTable != null && 
                            dbTable.getColumns().size() != 
                                tableInDB.getColumnList().size()) 
            {
                populateTableExpressionColumns(tableInDB, dbTable);
            }
            //no column List copy, just hand it through
            resultColExprList = tableInDB.getColumnList();
        } 
        else if (tableExpr instanceof WithTableReference) 
        {
            WithTableReference withTableRef = (WithTableReference) tableExpr;
            resultColExprList = exposeEffectiveResultColumns(withTableRef);
        }
        else if (tableExpr instanceof TableQueryLateral) 
        {
            TableQueryLateral queryLateral = (TableQueryLateral) tableExpr;
            QueryExpressionBody nestedQuery = queryLateral.getQuery();
            resultColExprList = exposeEffectiveResultColumns(nestedQuery);
            queryLateral.getColumnList().addAll(resultColExprList);
        }
        else if (tableExpr instanceof TableFunction) 
        {
//            StatementHelper.logError(TableHelper.class.getName()+
//                            ": implement exposeEffectiveResultColumns(TableExpression) for type"+
//                            TableFunction.class.getName());
        }
        else
        {
//            StatementHelper.logError(TableHelper.class.getName()+
//                            ": implement exposeEffectiveResultColumns(TableExpression) for type"+
//                            tableExpr.getClass().getName());
        }
        
        /* Find out if there is a column name list attached to the correlation name (if any)
         * and use those names for the exposed result columns. */
        TableCorrelation tableCorr = tableExpr.getTableCorrelation();
        if (tableCorr != null) {
            List corrColNameList = tableCorr.getColumnNameList();
            if (corrColNameList.size() > 0 && corrColNameList.size() <= resultColExprList.size()) {
                List corrColList = new ArrayList();
                for (int i=0; i<corrColNameList.size(); i++) {
                    Object obj1 = corrColNameList.get(i);
                    Object obj2 = resultColExprList.get(i);
                    if (obj1 instanceof ColumnName && obj2 instanceof ValueExpressionColumn) {
                        ColumnName corrColName = (ColumnName) obj1;
                        ValueExpressionColumn exposedCol = (ValueExpressionColumn) obj2;
                        ValueExpressionColumn exposedCorrCol = StatementHelper.createColumnExpression(corrColName.getName());
                        
                        /* Get the existing exposed column's data type and table reference
                         * set them as the datatype and table of the new exposed correlation column. */
                        DataType exposedColDataType = exposedCol.getDataType();
                        DataType exposedCorrColDataType = ValueExpressionHelper.copyDataType(exposedColDataType);
                        exposedCorrCol.setDataType(exposedCorrColDataType);
                        
                        TableInDatabase tableInDB = exposedCol.getTableInDatabase();
                        exposedCorrCol.setTableInDatabase(tableInDB);

                        /* Add the new exposed column to the list we're building. */
                        corrColList.add(exposedCorrCol);
                    }
                }
                
                /* Substitute the new list of correlation columns as the exposed result
                 * column list. */
                resultColExprList = corrColList;
            }
        }
        
        return resultColExprList;
    }

    /**
     * Populates the given <code>withTable</code>'s <code>columnList</code> with
     * <code>ValueExpressionColumn</code>s, with <code>name</code>,
     * <code>dataType</code> and in ordering, so the given
     * <code>tableExpr</code> can be handled like a <code>TableInDatabase</code>.
     * 
     * @param withTable the WITH table whose columns should be exposed
     * @return a list of already or newly exposed <code>ValueExpressionColumn</code>s
     */
    public static List exposeEffectiveResultColumns(WithTableReference withTable) {
        List resultColExprList = new ArrayList();
        
        if (withTable == null || withTable.getWithTableSpecification() == null)
        {
            return resultColExprList;
        }
        
        WithTableSpecification withTableSpec = 
            withTable.getWithTableSpecification();
        
        QueryExpressionBody withTableQuery = 
            withTableSpec.getWithTableQueryExpr();
        
        List withTableQueryResultCols = 
            getEffectiveResultColumns(withTableQuery);
        
        List withTableColNames = withTableSpec.getColumnNameList();        
        
        // if WITH table has not specified column names
        // the effective result columns (with name and type) are determined by
        // the WITH query
        if (withTableColNames == null || withTableColNames.isEmpty())
        {
            // deep copy-by-value! (EMF ownership doesn't allow us copy-by-ref)
            resultColExprList.addAll(copyColumnExprList(withTableQueryResultCols));
        }
        // does WITH table has specified column names?
        // we assume that the number of specified result column names
        // matches the number of effective result columns of the WITH-query
        else if (withTableColNames != null && !withTableColNames.isEmpty() &&
                        withTableColNames.size() <= withTableQueryResultCols.size())
        {
            for (int i = 0; i < withTableColNames.size(); i++)
            {
                ColumnName columnName = (ColumnName) withTableColNames.get(i);
                
                ValueExpressionColumn exposedCol = 
                    StatementHelper.createColumnExpression(columnName.getName());
                
                
                
                ValueExpressionColumn withQueryResultCol =
                    (ValueExpressionColumn) withTableQueryResultCols.get(i);
                
                DataType withQueryResultColDataType = 
                    withQueryResultCol.getDataType();
                
                DataType exposedColDataType = 
                    ValueExpressionHelper.copyDataType(
                                    withQueryResultColDataType);
                
                
                // we are setting the TableInDatabase that this column was
                // originally derived from, we should use a reference called
                // "derivedFromTableInDatabase"
                exposedCol.setTableInDatabase(withQueryResultCol.getTableInDatabase());

                exposedCol.setDataType(exposedColDataType);
                resultColExprList.add(exposedCol);
            }
        }
        // WITH table has specified column names and the WITH query was not yet
        // resolved - has no exposed columns
        else if (withTableColNames != null && !withTableColNames.isEmpty() &&
                        withTableQueryResultCols.isEmpty())
        {
            for (int i = 0; i < withTableColNames.size(); i++)
            {
                ColumnName columnName = (ColumnName) withTableColNames.get(i);
                
                ValueExpressionColumn exposedCol = 
                    StatementHelper.createColumnExpression(columnName.getName());
                
                resultColExprList.add(exposedCol);
            }
        }
        
        withTable.getColumnList().clear();
        withTable.getColumnList().addAll(resultColExprList);
        
        return resultColExprList;
    }
    
    /**
       * Returns a Set containing all <code>ValueExpressionColumn</code>s found
       * in the given <code>GroupingExpression</code>.
       */
        public static Set findColumnReferencesInGroupingExpression(GroupingExpression groupingExpr)
        {
            if (groupingExpr == null) { return Collections.EMPTY_SET; }
    
            HashSet columnSet = new HashSet();
            columnSet.addAll(findColumnReferencesInValueExpression(groupingExpr
                            .getValueExpr()));
    
            return columnSet;
        }

    /**
     * Returns a Set containing all <code>ValueExpressionColumn</code>s found
     * in the given <code>GroupingSpecification</code>.
     */
    public static Set findColumnReferencesInGroupingSpecification(GroupingSpecification groupingSpec)
    {
        HashSet columnSet = new HashSet();

        if (groupingSpec != null)
        {

            if (groupingSpec instanceof GroupingExpression)
            {
                GroupingExpression groupingExpr = (GroupingExpression) groupingSpec;
                columnSet
                                .addAll(findColumnReferencesInValueExpression(groupingExpr
                                                .getValueExpr()));
            }
            else if (groupingSpec instanceof SuperGroup)
            {
                Class refWanted = ValueExpressionColumn.class;
                Class[] refsToCheck = new Class[] {
                                GroupingSpecification.class,
                                SuperGroupElement.class };
                
                Set groupColumns = StatementHelper.getReferencesViaSpecificReferencePaths(groupingSpec, refWanted, refsToCheck);
                      
                columnSet.addAll(groupColumns);
                
/*                //throw new UnsupportedOperationException(
                StatementHelper.logError(
                                TableHelper.class.getName()
                                                + "#findColumnReferencesInGroupingSpecification() not implemented for "
                                                + groupingSpec.getClass()
                                                                .getName()
                                                + ".");
*/
            }
            else if (groupingSpec instanceof GroupingSets)
            {
                Class refWanted = ValueExpressionColumn.class;
                Class[] refsToCheck = new Class[] {
                                GroupingSpecification.class,
                                GroupingSetsElement.class,
                                SuperGroupElement.class };
                
                Set groupColumns = StatementHelper.getReferencesViaSpecificReferencePaths(groupingSpec, refWanted, refsToCheck);
                      
                columnSet.addAll(groupColumns);

/*                
                // throw new UnsupportedOperationException(
                StatementHelper.logError(
                                TableHelper.class.getName()
                                                + "#findColumnReferencesInGroupingSpecification() not implemented for "
                                                + groupingSpec.getClass()
                                                                .getName()
                                                + ".");
*/            
            }
            else
            {
                throw new UnsupportedOperationException(
                                TableHelper.class.getName()
                                                + "#findColumnReferencesInGroupingSpecification() not implemented for "
                                                + groupingSpec.getClass()
                                                                .getName()
                                                + ".");
            }
        }
        return columnSet;
    }

    /**
     * Returns a Set containing all <code>ValueExpressionColumn</code>s found
     * in the given <code>GroupingSpecification</code> list.
     */
    public static Set findColumnReferencesInGroupingSpecificationList(List groupingSpecList)
    {
        HashSet columnSet = new HashSet();

        if (groupingSpecList != null)
        {
            for (Iterator it = groupingSpecList.iterator(); it.hasNext();)
            {
                GroupingSpecification groupingSpec = (GroupingSpecification) it
                                .next();
                columnSet
                                .addAll(findColumnReferencesInGroupingSpecification(groupingSpec));
            }
        }
        return columnSet;

    }

    /**
     * Returns a Set containing all <code>ValueExpressionColumn</code> s found
     * in the given <code>OrderBySpecification</code>.
     */
    public static Set findColumnReferencesInOrderBySpecification(OrderBySpecification orderBySpec)
    {
        if (orderBySpec == null) { return Collections.EMPTY_SET; }
        HashSet columnSet = new HashSet();
        
        if (orderBySpec instanceof OrderByValueExpression)
        {
            OrderByValueExpression orderByValue = (OrderByValueExpression) orderBySpec;
            columnSet.addAll(findColumnReferencesInValueExpression(orderByValue
                            .getValueExpr()));
        }

        // Note:
        //  column references in ResultSpecification should be found by specific
        //  method and not here via OrderByResultColumn -> ResultColumn -> ValueExpressionColumn
        //  would be very incomplete or duplicate effort
        
        return columnSet;
    }

    /**
     * Returns a Set containing all <code>ValueExpressionColumn</code> s found
     * in the given List of <code>OrderBySpecification</code>.
     */
     public static Set findColumnReferencesInOrderBySpecificationList(List orderBySpecList)
     {
         if (orderBySpecList == null) { return Collections.EMPTY_SET; }

         HashSet columnSet = new HashSet();

         for (Iterator orderIt = orderBySpecList.iterator(); orderIt.hasNext();)
         {
             OrderBySpecification orderBySpec = 
                 (OrderBySpecification) orderIt.next();

             columnSet.addAll(findColumnReferencesInOrderBySpecification(orderBySpec));
         }

         return columnSet;
     }

    /**
       * Returns a Set containing all, not necessarily distinct,
       * <code>ValueExpressionColumn</code>s found in the given
       * <code>Predicate</code>. This means you have to expect duplicate
       * <code>ValueExpressionColumn</code>s regarding their column names.
       *
       * @param predicate
       * @return
       */
      public static Set findColumnReferencesInPredicate(Predicate predicate) {
        if (predicate == null) {
          return Collections.EMPTY_SET;
        }
    
        HashSet columnSet = new HashSet();
    
        if (predicate instanceof PredicateBasic) {
          PredicateBasic basic = (PredicateBasic) predicate;
          columnSet.addAll(findColumnReferencesInValueExpression(basic.getRightValueExpr()));
          columnSet.addAll(findColumnReferencesInValueExpression(basic.getLeftValueExpr()));
          return columnSet;
        }
    
        else if (predicate instanceof PredicateBetween) {
          PredicateBetween between = (PredicateBetween) predicate;
          columnSet.addAll(findColumnReferencesInValueExpression(between.getRightValueExpr1()));
          columnSet.addAll(findColumnReferencesInValueExpression(between.getRightValueExpr2()));
          columnSet.addAll(findColumnReferencesInValueExpression(between.getLeftValueExpr()));
          return columnSet;
        }
    
        else if (predicate instanceof PredicateExists) {
          PredicateExists exists = (PredicateExists) predicate;
          columnSet.addAll(findColumnReferencesInQueryExpressionBody(exists.getQueryExpr()));
          return columnSet;
        }
    
        else if (predicate instanceof PredicateInValueList) {
          PredicateInValueList inValueList = (PredicateInValueList) predicate;
          columnSet.addAll(findColumnReferencesInValueExpression(inValueList.getValueExpr()));
          columnSet.addAll(findColumnReferencesInValueExpressionList(inValueList.getValueExprList()));
          return columnSet;
        }
    
        else if (predicate instanceof PredicateInValueRowSelect) {
          PredicateInValueRowSelect inRowSelect = (PredicateInValueRowSelect) predicate;
          columnSet.addAll(findColumnReferencesInQueryExpressionRoot(inRowSelect.getQueryExpr()));
          columnSet.addAll(findColumnReferencesInValueExpressionList(inRowSelect.getValueExprList()));
          return columnSet;
        }
    
        else if (predicate instanceof PredicateInValueSelect) {
          PredicateInValueSelect inValueSelect = (PredicateInValueSelect) predicate;
          columnSet.addAll(findColumnReferencesInQueryExpressionRoot(inValueSelect.getQueryExpr()));
          columnSet.addAll(findColumnReferencesInValueExpression(inValueSelect.getValueExpr()));
          return columnSet;
        }
    
        else if (predicate instanceof PredicateIsNull) {
          PredicateIsNull isNull = (PredicateIsNull) predicate;
          columnSet.addAll(findColumnReferencesInValueExpression(isNull.getValueExpr()));
          return columnSet;
        }
    
        else if (predicate instanceof PredicateLike) {
          PredicateLike like = (PredicateLike) predicate;
          columnSet.addAll(findColumnReferencesInValueExpression(like.getMatchingValueExpr()));
          columnSet.addAll(findColumnReferencesInValueExpression(like.getPatternValueExpr()));
          columnSet.addAll(findColumnReferencesInValueExpression(like.getEscapeValueExpr()));
          return columnSet;
        }
    
        else if (predicate instanceof PredicateQuantifiedRowSelect) {
          PredicateQuantifiedRowSelect rowSelect = (PredicateQuantifiedRowSelect) predicate;
          columnSet.addAll(findColumnReferencesInQueryExpressionRoot(rowSelect.getQueryExpr()));
          columnSet.addAll(findColumnReferencesInValueExpressionList(rowSelect.getValueExprList()));
          return columnSet;
        }
    
        else if (predicate instanceof PredicateQuantifiedValueSelect) {
          PredicateQuantifiedValueSelect inValueSelect = (PredicateQuantifiedValueSelect) predicate;
          columnSet.addAll(findColumnReferencesInQueryExpressionRoot(inValueSelect.getQueryExpr()));
          columnSet.addAll(findColumnReferencesInValueExpression(inValueSelect.getValueExpr()));
          return columnSet;
        }
    
        return columnSet;
      }

  /**
   * Returns a Set containing all <code>ValueExpressionColumn</code>s found in
   * the given <code>QueryExpressionBody</code>.
   */
   public static Set findColumnReferencesInQueryExpressionBody(QueryExpressionBody queryExprBody) {
    if (queryExprBody == null) {
      return Collections.EMPTY_SET;
    }

    HashSet columnSet = new HashSet();

    if (queryExprBody instanceof QuerySelect) {
      QuerySelect select = (QuerySelect) queryExprBody;
      columnSet.addAll(findColumnReferencesInQueryResultSpecificationList(select.getSelectClause()));
      columnSet.addAll(findColumnReferencesInTableReferenceList(select.getFromClause()));
      columnSet.addAll(findColumnReferencesInSearchCondition(select.getWhereClause()));
      columnSet.addAll(findColumnReferencesInSearchCondition(select.getHavingClause()));
      columnSet.addAll(findColumnReferencesInGroupingSpecificationList(select.getGroupByClause()));

    }
    else if (queryExprBody instanceof QueryCombined) {
      QueryCombined combined = (QueryCombined) queryExprBody;
      columnSet.addAll(findColumnReferencesInQueryExpressionBody(combined.getLeftQuery()));
      columnSet.addAll(findColumnReferencesInQueryExpressionBody(combined.getRightQuery()));
    }
    else if (queryExprBody instanceof QueryNested) {
        QueryNested qryNested = (QueryNested) queryExprBody;
        QueryExpressionBody nestedQuery = qryNested.getNestedQuery();
        columnSet.addAll(findColumnReferencesInQueryExpressionBody(nestedQuery));
    }
    else if (queryExprBody instanceof QueryValues) {
        // nothing to do here, no column references possible in VALUES clause
    }
    else {
//        throw new UnsupportedOperationException(
//            TableHelper.class.getName()
//                + "#findColumnReferencesInQueryExpressionBody( ("+queryExprBody.getClass().getName()+") QueryExpressionBody ) not implemented.");
    }

    List sortSpecList = queryExprBody.getSortSpecList();
    columnSet.addAll( findColumnReferencesInOrderBySpecificationList(sortSpecList));
    
    return columnSet;
  }

  /**
   * Returns a Set containing all <code>ValueExpressionColumn</code>s found in
   * the given <code>QueryExpressionRoot</code>.
   */
   public static Set findColumnReferencesInQueryExpressionRoot(
      QueryExpressionRoot queryExprRoot) {
    if (queryExprRoot == null) {
      return Collections.EMPTY_SET;
    }

    HashSet columnSet = new HashSet();

    columnSet.addAll(findColumnReferencesInQueryExpressionBody(queryExprRoot.getQuery()));
    
    // we don't want to find column references in TableWithSpecification
    // cause they are resolved within the context of the TableWithSpecification's query
    //columnSet.addAll(findColumnReferencesInTableWithSpecificationList(queryExprRoot.getWithClause()));

    return columnSet;
  }

   /**
    * Returns a set containing all the column value expression objects in the given statement.
    * 
    * @param mergeStmt the statement from which column expressions are wanted 
    * @return a set containing the column expressions in the statement
    */
   public static Set findColumnReferencesInQueryMergeStatement(QueryMergeStatement mergeStmt) {
       Set colSet = new HashSet();
       
       if (mergeStmt == null) {
           colSet = Collections.EMPTY_SET;
       }
       else {
           MergeOnCondition onCondition = mergeStmt.getOnCondition();
           QuerySearchCondition searchCond = onCondition.getSearchCondition();
           Set searchCondColSet = findColumnReferencesInSearchCondition(searchCond);
           colSet.addAll(searchCondColSet);
           
           List operSpecList = mergeStmt.getOperationSpecList();
           Iterator operSpecListIter = operSpecList.iterator();
           while (operSpecListIter.hasNext()) {
               MergeOperationSpecification operSpec = (MergeOperationSpecification) operSpecListIter.next();
               if (operSpec instanceof MergeUpdateSpecification) {
                   MergeUpdateSpecification updateSpec = (MergeUpdateSpecification) operSpec;
                   List assignExprList = updateSpec.getAssignementExprList();
                   Set assignExprListColSet = findColumnReferencesInUpdateAssignmentExprList(assignExprList);
                   colSet.addAll(assignExprListColSet);
               }
               else if (operSpec instanceof MergeInsertSpecification) {
                   MergeInsertSpecification insertSpec = (MergeInsertSpecification) operSpec;
                   List targetColList = insertSpec.getTargetColumnList();
                   colSet.addAll(targetColList);
                   ValuesRow valRow = insertSpec.getSourceValuesRow();
                   Set valRowColSet = findColumnReferencesInValuesRow(valRow);
                   colSet.addAll(valRowColSet);
               }
           }
       }
       
       return colSet;
   }
   
/**
 * Returns a Set containing all <code>ValueExpressionColumn</code>s found
 * in the given <code>QueryResultSpecification</code>.
 */
public static Set findColumnReferencesInQueryResultSpecification(QueryResultSpecification queryResult)
{
    if (queryResult == null) { return Collections.EMPTY_SET; }
    HashSet columnSet = new HashSet();
    if (queryResult instanceof ResultColumn)
    {
        ResultColumn resultCol = (ResultColumn) queryResult;
        columnSet.addAll(findColumnReferencesInValueExpression(resultCol
                        .getValueExpr()));
    }
    return columnSet;
}
  
  
/**
 * Returns a Set containing all <code>ValueExpressionColumn</code>s found
 * in the given <code>QueryResultSpecification</code> list.
 */
public static Set findColumnReferencesInQueryResultSpecificationList(List queryResultSpecList)
{
    if (queryResultSpecList == null) { return Collections.EMPTY_SET; }
    HashSet columnSet = new HashSet();
    for (Iterator it = queryResultSpecList.iterator(); it.hasNext();)
    {
        QueryResultSpecification resultSpec = (QueryResultSpecification) it
                        .next();
        columnSet
                        .addAll(findColumnReferencesInQueryResultSpecification(resultSpec));
    }
    return columnSet;
}

  /**
    * Returns a Set containing all <code>ValueExpressionColumn</code>s found
    * in the given <code>QuerySelectStatement</code>.
    */
    public static Set findColumnReferencesInQuerySelectStatement(QuerySelectStatement querySelect)
    {
        if (querySelect == null) { return Collections.EMPTY_SET; }

        HashSet columnSet = new HashSet();

        columnSet.addAll(findColumnReferencesInQueryExpressionRoot(querySelect
                        .getQueryExpr()));

        List orderBySpecList = querySelect.getOrderByClause();
        columnSet.addAll( findColumnReferencesInOrderBySpecificationList(orderBySpecList));

        return columnSet;
    }

  /**
   * Returns a Set containing all <code>ValueExpressionColumn</code>s found
   * in the given <code>QueryUpdateStatement</code>.
   */
   public static Set findColumnReferencesInQueryUpdateStatement(QueryUpdateStatement updateStmt)
   {
       if (updateStmt == null) { return Collections.EMPTY_SET; }

       HashSet columnSet = new HashSet();

       columnSet.addAll(findColumnReferencesInUpdateAssignmentExprList(updateStmt.getAssignmentClause()));
       columnSet.addAll(findColumnReferencesInSearchCondition(updateStmt.getWhereClause()));

       if (updateStmt.getWhereCurrentOfClause() != null) 
       {
//           StatementHelper.logError(TableHelper.class.getName()
//                           +" implement findColumnReferencesInQueryUpdateStatement(QueryUpdateStatement)" 
//                           +" for "+CursorReference.class.getName());
       }

       return columnSet;
   }

  /**
   * Returns a Set of not neccessarily distinct
   * <code>ValueExpressionColumn</code>s found in the given 
   * <code>SearchCondition</code>, means you have to expect duplicates regarding
   * column names.
   *
   * @param searchCond
   * @return
   */
  public static Set findColumnReferencesInSearchCondition(QuerySearchCondition searchCond) {
    if (searchCond == null) {
      return Collections.EMPTY_SET;
    }

    HashSet columnSet = new HashSet();

    if (searchCond instanceof Predicate) {
      Predicate pred = (Predicate) searchCond;
      return findColumnReferencesInPredicate(pred);
    }
    else if (searchCond instanceof SearchConditionCombined) {
      SearchConditionCombined combined = (SearchConditionCombined) searchCond;
      columnSet.addAll(findColumnReferencesInSearchCondition(combined.getLeftCondition()));
      columnSet.addAll(findColumnReferencesInSearchCondition(combined.getRightCondition()));
      return columnSet;
    }
    else if (searchCond instanceof SearchConditionNested) {
      SearchConditionNested nest = (SearchConditionNested) searchCond;
      return findColumnReferencesInSearchCondition(nest.getNestedCondition());
    }

    return columnSet;
  }

  /**
   * Returns a Set containing all the <code>ValueExpressionColumn</code>s found
   * in the given <code>TableReference</code>.
   */
  public static Set findColumnReferencesInTableReference(TableReference tableRef) {
      Set columnSet = Collections.EMPTY_SET;
      
      if (tableRef != null) {
          columnSet = new HashSet();
          
          if (tableRef instanceof TableInDatabase) {
              // do nothing; doesn't contain query column references
          }
          // A QueryExpressionBody as a table ref as a nested query definition in the FROM clause, ie:
          //   SELECT * FROM table1, (SELECT foo, bar FROM table2)
          else if (tableRef instanceof QueryExpressionBody) {
              QueryExpressionBody queryExprBody = (QueryExpressionBody) tableRef;
              columnSet.addAll(findColumnReferencesInQueryExpressionBody(queryExprBody));
          }
          // A "lateral" table query is like the nested query definition in the FROM clause
          // as above, but the sub-query has the LATERAL (or TABLE) keyword in front of it, ie:
          //   SELECT * FROM table1, TABLE (SELECT foo, bar FROM table2)
          // In a lateral table query, the tables ahead of it in the FROM clause, (ie, table1) are
          // visible within the sub-query.
          else if (tableRef instanceof TableQueryLateral) {
              TableQueryLateral tableQueryLateral = (TableQueryLateral) tableRef;
              QueryExpressionBody queryExprBody = tableQueryLateral.getQuery();
              columnSet.addAll(findColumnReferencesInQueryExpressionBody(queryExprBody));
          }
          else if (tableRef instanceof TableFunction) {
              TableFunction tableFunc = (TableFunction) tableRef;
              columnSet.addAll(findColumnReferencesInValueExpressionList(tableFunc.getParameterList()));
          }
          else if (tableRef instanceof TableNested) {
              TableNested tableNested = (TableNested) tableRef;
              columnSet.addAll(findColumnReferencesInTableReference(tableNested.getNestedTableRef()));
          }
          else if (tableRef instanceof TableJoined) {
              TableJoined tableJoined = (TableJoined) tableRef;
              columnSet.addAll(findColumnReferencesInTableReference(tableJoined.getTableRefLeft()));
              columnSet.addAll(findColumnReferencesInTableReference(tableJoined.getTableRefRight()));
              columnSet.addAll(findColumnReferencesInSearchCondition(tableJoined.getJoinCondition()));
          }
          else if (tableRef instanceof WithTableReference) {
              WithTableReference withTableRef = (WithTableReference) tableRef;
              String withTableRefName = withTableRef.getName();
              /* Find out if this WITH reference is recursive.  If so, we don't want to dig any further. */
              boolean isRecursive = false;
              EObject parent = tableRef.eContainer();
              while (parent != null && isRecursive == false) {
                  if (parent instanceof WithTableSpecification) {
                      WithTableSpecification parentWithTableSpec = (WithTableSpecification) parent;
                      String parentName = parentWithTableSpec.getName();
                      if (StatementHelper.equalSQLIdentifiers(withTableRefName, parentName) == true) {
                          isRecursive = true;
                      }
                  }
                  parent = parent.eContainer();
              }
              
              if (isRecursive == false) {
                  columnSet.addAll(findColumnReferencesInWithTableSpecification(withTableRef.getWithTableSpecification()));
              }
          }
      }
      
      return columnSet;
  }
  
  /**
   * Returns a Set containing all the <code>ValueExpressionColumn</code>s found
   * in the given <code>TableReference</code> list.
   */
  public static Set findColumnReferencesInTableReferenceList(List tableRefList) {
      Set columnSet = Collections.EMPTY_SET;
      
      if (tableRefList != null && tableRefList.size() > 0) {
          columnSet = new HashSet();
          Iterator tableRefListIter = tableRefList.iterator();
          while (tableRefListIter.hasNext()) {
              Object listObj = tableRefListIter.next();
              if (listObj instanceof TableReference) {
                  TableReference tableRef = (TableReference) listObj;
                  columnSet.addAll(findColumnReferencesInTableReference(tableRef));
              }
          }
      }
      
      return columnSet;
  }

/**
 * Returns a Set containing all <code>ValueExpressionColumn</code>s found
 * in the given List of <code>UpdateAssignmentExpression</code>.
 * 
 * @param updateAssignmentExpr
 *            the <code>UpdateAssignmentExpression</code> to search in
 * @return Set containing all <code>ValueExpressionColumn</code>s found
 */
 public static Set findColumnReferencesInUpdateAssignmentExpr(UpdateAssignmentExpression updateAssignmentExpr)
 {
     Set columnSet = new HashSet();

     columnSet.addAll(updateAssignmentExpr.getTargetColumnList());
     
     // We only want column references in updateSourceExpressionList,
     // NOT in UpdateSourceQuery, cause the columns contained there are locally
     // resolved within the context of the source Query.
     if (updateAssignmentExpr.getUpdateSource() instanceof UpdateSourceExprList)
     {
         UpdateSourceExprList updateSrcExprList = 
             (UpdateSourceExprList) updateAssignmentExpr.getUpdateSource();
         columnSet.addAll(findColumnReferencesInUpdateSourceExprList(updateSrcExprList));
     }

     return columnSet;
 }

  /**
    * Returns a Set containing all <code>ValueExpressionColumn</code>s found
    * in the given List of <code>UpdateAssignmentExpression</code>s.
    * 
    * @param updateAssignmentExprList
    *            the list of <code>UpdateAssignmentExpression</code>s to search in
    * @return Set containing all <code>ValueExpressionColumn</code>s found
    */
    public static Set findColumnReferencesInUpdateAssignmentExprList(List updateAssignmentExprList)
    {
        Set columnSet = new HashSet();

        for (Iterator assignIt = updateAssignmentExprList.iterator(); assignIt.hasNext();)
        {
            UpdateAssignmentExpression upAssEx = 
                (UpdateAssignmentExpression) assignIt.next();

            columnSet.addAll(findColumnReferencesInUpdateAssignmentExpr(upAssEx));
        }
        

        return columnSet;
    }

  
  
  /**
 * Returns a Set containing all <code>ValueExpressionColumn</code>s found
 * in the given <code>UpdateSourceExprList</code>.
 */
public static Set findColumnReferencesInUpdateSourceExprList(UpdateSourceExprList updateSourceExprList)
{
    if (updateSourceExprList == null) { return Collections.EMPTY_SET; }
    
    HashSet columnSet = new HashSet();
    List exprList = updateSourceExprList.getValueExprList();
    columnSet.addAll(findColumnReferencesInValueExpressionList(exprList));
    return columnSet;
}

  /**
   * Returns a Set containing all <code>ValueExpressionColumn</code>s found
   * in the given <code>QueryValueExpression</code>.
   *
   * @param valueExpr
   * @return a Set of <code>ValueExpressionColumn</code>s
   */
  public static Set findColumnReferencesInValueExpression(QueryValueExpression valueExpr) {

    if (valueExpr == null)
      return Collections.EMPTY_SET;

    HashSet columnSet = new HashSet();

    if (valueExpr instanceof ValueExpressionColumn) {
      columnSet.add(valueExpr);
      return columnSet;
    }
    // catch these types for performance! are very likely to occur!
    else if (valueExpr instanceof ValueExpressionSimple) {
        return Collections.EMPTY_SET;
    }
    else if (valueExpr instanceof ValueExpressionCombined) {
        ValueExpressionCombined combi = (ValueExpressionCombined) valueExpr;
        columnSet.addAll(findColumnReferencesInValueExpression(combi.getRightValueExpr()));
        columnSet.addAll(findColumnReferencesInValueExpression(combi.getLeftValueExpr()));
        return columnSet;
    }
    // catch these types for performance! are very likely to occur!
    else if (valueExpr instanceof ValueExpressionDefaultValue
                    || valueExpr instanceof ValueExpressionNullValue
                    || valueExpr instanceof ValueExpressionVariable) {
        return Collections.EMPTY_SET;
    }
    else if (valueExpr instanceof ValueExpressionNested) {
      ValueExpressionNested nest = (ValueExpressionNested) valueExpr;
      return findColumnReferencesInValueExpression(nest.getNestedValueExpr());
    }

    else if (valueExpr instanceof ValueExpressionCaseSearch) {
      ValueExpressionCaseSearch caseSearch = (ValueExpressionCaseSearch) valueExpr;
      for (Iterator searchContentList = caseSearch.getSearchContentList()
          .iterator(); searchContentList.hasNext();) {
        ValueExpressionCaseSearchContent searchContent = (ValueExpressionCaseSearchContent) searchContentList.next();
        columnSet.addAll(findColumnReferencesInValueExpression(searchContent.getValueExpr()));
        columnSet.addAll(findColumnReferencesInSearchCondition(searchContent.getSearchCondition()));
      }
      ValueExpressionCaseElse caseElse = (ValueExpressionCaseElse) caseSearch.getCaseElse();
      if (caseElse != null) {
          columnSet.addAll(findColumnReferencesInValueExpression(caseElse.getValueExpr()));
      }
      return columnSet;
    }

    else if (valueExpr instanceof ValueExpressionCaseSimple) {
      ValueExpressionCaseSimple caseSimple = (ValueExpressionCaseSimple) valueExpr;
      
      columnSet.addAll( findColumnReferencesInValueExpression(caseSimple.getValueExpr()) );
      
      for (Iterator simpleContentList = caseSimple.getContentList().iterator(); simpleContentList.hasNext();) {
        ValueExpressionCaseSimpleContent simpleContent = (ValueExpressionCaseSimpleContent) simpleContentList.next();
        columnSet.addAll(findColumnReferencesInValueExpression(simpleContent.getResultValueExpr()));
        columnSet.addAll(findColumnReferencesInValueExpression(simpleContent.getWhenValueExpr()));
      }

      ValueExpressionCaseElse caseElse = caseSimple.getCaseElse();
      if (caseElse != null) {
          columnSet.addAll(findColumnReferencesInValueExpression(caseElse.getValueExpr()));
      }
      return columnSet;
    }

    else if (valueExpr instanceof ValueExpressionCast) {
      ValueExpressionCast cast = (ValueExpressionCast) valueExpr;
      return findColumnReferencesInValueExpression(cast.getValueExpr());
    }

    else if (valueExpr instanceof ValueExpressionFunction) {
      ValueExpressionFunction func = (ValueExpressionFunction) valueExpr;
      return findColumnReferencesInValueExpressionList(func.getParameterList());
    }

    else if (valueExpr instanceof ValueExpressionLabeledDuration) {
      ValueExpressionLabeledDuration dur = (ValueExpressionLabeledDuration) valueExpr;
      return findColumnReferencesInValueExpression(dur.getValueExpr());
    }

    else if (valueExpr instanceof ValueExpressionScalarSelect) {
        // we don't want the column references in scalar sub-select,
        // these are only valid and resolved within the context of its QueryExpressionRoot
        //**DON'T**//ValueExpressionScalarSelect scalar = (ValueExpressionScalarSelect) valueExpr;
        //**DON'T**//return findColumnReferencesInQueryExpressionRoot(scalar.getQueryExpr());
    }

    return columnSet;
  }

  /**
   * Returns a Set containing all <code>ValueExpressionColumn</code>s found
   * in the given <code>ValueExpression</code> list.
   */
    public static Set findColumnReferencesInValueExpressionList(List valueExprList)
    {
        HashSet columnSet = null;

        if (valueExprList != null)
        {
            columnSet = new HashSet();

            for (Iterator valIt = valueExprList.iterator(); valIt.hasNext();)
            {
                QueryValueExpression valueExpr = (QueryValueExpression) valIt
                                .next();
                columnSet
                                .addAll(findColumnReferencesInValueExpression(valueExpr));
            }
        }
        return columnSet;
    }

/**
 * Returns a Set containing all <code>ValueExpressionColumn</code>s found in
 * the given <code>ValuesRow</code>.
 */
public static Set findColumnReferencesInValuesRow(ValuesRow valuesRow)
{
    if (valuesRow == null) { return Collections.EMPTY_SET; }
    HashSet columnSet = new HashSet();
    columnSet.addAll(findColumnReferencesInValueExpressionList(valuesRow
                    .getExprList()));
    return columnSet;
}
  
/**
 * Returns a Set containing all the <code>ValueExpressionColumn</code>s found in
 * the given <code>WithTableSpecification</code>.
 */
public static Set findColumnReferencesInWithTableSpecification(WithTableSpecification withTableSpec) {
    Set columnSet = Collections.EMPTY_SET;
    
    if (withTableSpec != null) {
        columnSet = new HashSet();
        
        columnSet.addAll(findColumnReferencesInQueryExpressionBody(withTableSpec.getWithTableQueryExpr()));
    }
    
    return columnSet;
}

  /**
   * Finds in the given List of <code>TableExpression</code>s the one
   * <code>TableExpression</code> with a name that matches the given
   * <code>tableName</code> and, if a <code>schemaName</code> is
   * given, with a <code>Schema</code> whose name matches the
   * <code>schemaName</code>. If no <code>schemaName</code> is given,
   * the first <code>TableExpression</code> found without regard of its
   * <code>Schema</code>, will be returned.
   * 
   * @param schemaName
   *          optional the schema name of the table to find
   * @param tableName
   *          the name of the table to find
   * @param tableExprList
   *          the list of <code>TableExpression</code> s to search
   * @return the matching <code>TableExpression</code> or <code>null</code>
   */
  public static TableExpression findTableExpressionInTableExpressionList(
                                                                           String schemaName,
                                                                           String tableName,
                                                                           List tableExprList)
    {
        TableExpression foundTableExpr = null;
        SQLQuerySourceFormat sourceFormat = null;

        if (tableName != null && tableExprList != null)
        {

            for (Iterator tableIt = tableExprList.iterator(); tableIt.hasNext();)
            {
                TableExpression tableExpr = (TableExpression) tableIt.next();

                // get the delimited identifier quote from the SourceFormat
                if (sourceFormat == null
                                && tableExpr.getSourceInfo() != null
                                && tableExpr.getSourceInfo().getSqlFormat() != null)
                {
                    sourceFormat = tableExpr.getSourceInfo().getSqlFormat();
                }
                

                if (StatementHelper.equalSQLIdentifiers(tableName, tableExpr.getName()))
                {
                    String tableExprSchemaName = getSchemaNameForTableExpression(tableExpr);

                    if (schemaName == null
                     || StatementHelper.equalSQLIdentifiers(schemaName, tableExprSchemaName))
                    {
                        foundTableExpr = tableExpr;
                        break;
                    }
                }
            }

        }

        return foundTableExpr;
    }

  /**
 * Finds in the given List of <code>TableExpression</code>s the
 * <code>TableExpression</code> with a name that matches the given
 * <code>tableNameOrAlias</code> or a <code>tableCorrelation</code> with
 * a name that matches <code>tableNameOrAlias</code>. If multiple tables
 * are found, preference is given to the first. <b>Note: </b> if no table is
 * found by alias name but more than one table is found by name, the table
 * that is in the default Schema specified by <code>defaultSchemaName</code>
 * will be returned, or, if no <code>defaultSchemaName</code> is given the
 * table to be returned must have no <code>Schema</code> reference.
 * If you are searching for a table with name and schema, use
 * {@link #findTableExpressionInTableExpressionList(String, String, List)}
 * <b>Note:</b> If only one table is found with the given
 * <code>tableNameOrAlias</code>, but it is Schema-qualified, it will be
 * returned also.
 * 
 * Example: <br>
 * for <code>tableNameOrAlias = "T1"</code>
 * and the given <code>tableExprList</code> contains:
 * <table border='1' width='250'>
 * <tr><th><b><code>tableExprList*</code></b></th><th><b>return</b></th>
 * </tr>
 * <tr><td> T1<br> T2<br></td><td>T1</td>
 * </tr>
 * <tr><td> S1.T1<br> T1<br></td><td>T1</td>
 * </tr>
 * <tr><td> S1.T1 AS T1<br> S2.T1<br></td><td>S1.T1</td>
 * </tr>
 * <tr><td> S1.T1<br> S1.T2<br></td><td>S1.T1</td>
 * </tr>
 * <tr><td> S1.T2<br> S1.T3<br></td><td><code>null</code></td>
 * </tr>
 * <tr><td> S1.T1<br> S2.T1<br></td><td><code>null</code></td>
 * </tr>
 * </table>
 * * S1,S2 are Schema names; T1,T2,T3 are Table names
 * 
 * @param tableNameOrAlias
 *            the name of the table to find or its correlation name
 * @param tableExprList
 *            the list of <code>TableExpression</code> s to search
 * @param defaultSchemaName
 *            optional, if given and no table is found by alias name but
 *            tables, that have no alias are found by name, the table to be
 *            returned has to be in the default Schema
 * 
 * @return the matching <code>TableExpression</code> or <code>null</code>
 */
public static TableExpression findTableExpressionsByNameOrAlias(String tableNameOrAlias,
                                                     List tableExprList,
                                                     String defaultSchemaName)
{
    TableExpression tableExprFound = null;
    SQLQuerySourceFormat sourceFormat = null;
    
    // if we find tables that match the given tableNameOrAlias but are schema
    // qualified we will check in the end if only one of that was found and take it
    List matchingSchemaQualifiedTables = new ArrayList();
    
    if (tableNameOrAlias != null && tableExprList != null)
    {
        for (Iterator tableIt = tableExprList.iterator(); tableIt.hasNext();)
        {
            TableExpression tableExpr = (TableExpression) tableIt.next();
            TableCorrelation tableCorr = tableExpr.getTableCorrelation();
            
            String tableName = tableExpr.getName();
            String tableAlias = null;
            
            if (tableCorr != null) 
            {
                tableAlias = tableCorr.getName();
            }
            
            // get the omitSchema from the table
            if (tableExpr.getSourceInfo() != null
                            && tableExpr.getSourceInfo().getSqlFormat() != null)
            {
                sourceFormat = tableExpr.getSourceInfo().getSqlFormat();
                if (sourceFormat.getOmitSchema() != null
                                && sourceFormat.getOmitSchema().length()>0)
                {
                    defaultSchemaName = sourceFormat.getOmitSchema();
                }
            }
  
            boolean equalNames = StatementHelper.equalSQLIdentifiers(tableNameOrAlias, tableName);
            boolean equalAlias = StatementHelper.equalSQLIdentifiers(tableNameOrAlias, tableAlias);
            
            // the alias-name wins over the name
            if (equalAlias)
            {
                tableExprFound = tableExpr;
                break;
            }
            else if (equalNames && tableAlias == null)
            {
                // the table can only be the one, if it is in the default
                // schema or has no schemas specified
                // or else (forgiving the user his inproper input) we check
                // for schema-qualified tables in the FROM-clause and if
                // only one matches by name (no like-named tables in
                // different schemas) we return the "best match"
                String tableSchema = getSchemaName(tableExpr);
                if (tableSchema == null
                 || StatementHelper.equalSQLIdentifiers(tableSchema, defaultSchemaName))
                {
                    tableExprFound = tableExpr;
                    // don't break as we could find another table that
                    // matches by alias name, and that would be prioritized
                }
                else
                {
                    matchingSchemaQualifiedTables.add(tableExpr);
                }
            }
        }
    }

    if (tableExprFound == null && matchingSchemaQualifiedTables.size() == 1)
    {
        tableExprFound = (TableExpression) matchingSchemaQualifiedTables.get(0);
    }
    
    return tableExprFound;
}

    /**
     * Gets the SQLValueExpressionColumn corresponding to the given Column, in the
     * given table.
     *
     * @param tableExpr the table to search
     * @param column the column to be looked for
     * @return the SQLValueExpressionColumn corresponding to the given Column, or
     *         null if there is no match
     */
    public static ValueExpressionColumn getColumnExpressionForColumn(
            TableExpression tableExpr, Column column) {
        ValueExpressionColumn foundCol = null;

        if (column != null && tableExpr != null) {
            String colName = column.getName();
            foundCol = getColumnExpressionForName(tableExpr, colName);
        }

        return foundCol;
    }

    /**
     * Returns the <code>ValueExpressionColumn</code> from the given
     * <code>TableExpression</code>, if the <code>ValueExpressionColumn</code>'s
     * name matches the given column name.
     * <p>
     * <b>NOTE: </b> If there is more than one <code>ValueExpressionColumn</code> 
     * with the same name (both representing one and the same real <code>Column</code>), 
     * the first one found will be returned.
     * </p>
     * 
     * @param tableExpr the TableReference to search for the matching column name
     * @param columnName the name of the ValueExpressionColumn to search for
     * @return the found ValueExpressionColumn or <code>null</code>
     */
    static public ValueExpressionColumn getColumnExpressionForName(TableExpression tableExpr,
            String columnName) {
        ValueExpressionColumn colExprFound = null;

        // Try to find the column name in the table's list of columns.
        if (tableExpr != null && columnName != null) {
            /* Determine if the table has a correlation column name list.
             * If so, we need to use that list of names, since they hide the table's 
             * real column names. */
            boolean hasCorrColList = false;
            TableCorrelation tableCorr = tableExpr.getTableCorrelation();
            if (tableCorr != null) {
                List corrColNameList = tableCorr.getColumnNameList();
                if (corrColNameList != null && corrColNameList.size() > 0) {
                    hasCorrColList = true;
                }
            }
            
            /* If there is a correlation column name list, look through it for a match
             * and get the corresponding underlying column. */
            if (hasCorrColList == true) {
                List corrColNameList = tableCorr.getColumnNameList();
                List tableColList = tableExpr.getColumnList();
                if (corrColNameList.size() <= tableColList.size()) {
                    int i=0;
                    while (colExprFound == null && i<corrColNameList.size()) {
                        Object obj1 = corrColNameList.get(i);
                        Object obj2 = tableColList.get(i);
                        if (obj1 instanceof ColumnName && obj2 instanceof ValueExpressionColumn) {
                            ColumnName corrColName = (ColumnName) obj1;
                            String corrColNameName = corrColName.getName();
                            ValueExpressionColumn tableColExpr = (ValueExpressionColumn) obj2;
                            
                            if (StatementHelper.equalSQLIdentifiers(columnName, corrColNameName)) {
                                colExprFound = tableColExpr;
                            }
                        }
                        i++;
                    }
                }
            }
            /* Otherwise look through the table's regular column list. */
            else {
                List tableColList = tableExpr.getColumnList();
                Iterator tableColListIter = tableColList.iterator();
                while (tableColListIter.hasNext() && colExprFound == null) {
                    ValueExpressionColumn tableColExpr = (ValueExpressionColumn) tableColListIter.next();
                    String tableColName = tableColExpr.getName();
                    if (StatementHelper.equalSQLIdentifiers(columnName, tableColName)) {
                        colExprFound = tableColExpr;
                    }
                }
            }
        }

        return colExprFound;
    }

    /**
     * Returns the <code>ValueExpressionColumn</code> from the given
     * <code>TableReference</code> matching the given <code>columnName</code>.
     * If the given <code>TableReference</code> is of type
     * <code>QueryExpressionBody</code> <code>ResultColumn</code>s of the
     * <code>QuerySelect</code> statement will be regarded too.
     * <p>
     * <b>NOTE:</b>
     * If there is more than one <code>ValueExpressionColumn</code> with the same
     * name (both representing one and the same real <code>Column</code>), the
     * first one found will be returned. 
     * </p>
     *
     * @param tableRef the TableReference to search for the matching column
     * @param columnName the name of the column to search for
     * @return the found ValueExpressionColumn or <code>null</code>
     */
    static public ValueExpressionColumn getColumnExpressionForName(TableReference tableRef,
            String columnName) {
        ValueExpressionColumn colExpr = null;

        if (tableRef != null && columnName != null) {
            if (tableRef instanceof TableExpression) {
                TableExpression tableExpr = (TableExpression) tableRef;
                colExpr = getColumnExpressionForName(tableExpr, columnName);
            }
            if (tableRef instanceof TableNested) {
                TableNested nest = (TableNested) tableRef;
                TableReference nestedTableRef = nest.getNestedTableRef();
                colExpr = getColumnExpressionForName(nestedTableRef, columnName);
            }
            if (tableRef instanceof TableJoined) {
                TableJoined tableJoined = (TableJoined) tableRef;
                TableReference leftTableRef = tableJoined.getTableRefLeft();
                TableReference rightTableRef = tableJoined.getTableRefRight();

                ValueExpressionColumn leftColumn = getColumnExpressionForName(leftTableRef, columnName);

                if (leftColumn != null) {
                    colExpr = leftColumn;
                } 
                else {
                    ValueExpressionColumn rightColumn = getColumnExpressionForName(rightTableRef, columnName);
                    colExpr = rightColumn;
                }
            }
        }

        return colExpr;
    }
    

    
    /**
     * Returns the <code>ValueExpressionColumn</code> from the given
     * <code>TableExpression</code>, if the <code>ValueExpressionColumn</code>'s
     * name matches the given <code>columnName</code>.
     * If the given <code>TableExpression</code> is of type
     * <code>QueryExpressionBody</code>, this method will recursively analyze
     * its effective result columns, e.g. the <code>ResultColumn</code>s of a
     * nested <code>QuerySelect</code> statement will be considered, too.
     * <p>
     * <b>NOTE: </b> If there is more than one <code>ValueExpressionColumn</code> with 
     * the same name (both representing one and the same real <code>Column</code>), the 
     * first one found will be returned.
     * </p>
     * 
     * @param tableExpr the TableReference to search for the matching column name
     * @param columnName the name of the ValueExpressionColumn search for
     * @return column the found ValueExpressionColumn or <code>null</code>
     */
    static public ValueExpressionColumn getColumnExpressionForNameRecursively(TableExpression tableExpr,
                                                                              String columnName) {
        ValueExpressionColumn colExprFound = null;

        if (tableExpr != null && columnName != null) {
            colExprFound = getColumnExpressionForName(tableExpr, columnName);
        }

        if (colExprFound == null) {
            if (tableExpr instanceof QueryExpressionBody) {
                QueryExpressionBody tableQuery = (QueryExpressionBody) tableExpr;
                ResultColumn resultColFound = getResultColumnForAliasOrColumnName(tableQuery,columnName);
                
                if (resultColFound != null) {
                    // We have a result column for the column, but the result column
                    // doesn't have an associated ValueExpressionColumn, so create one.
                    ValueExpressionColumn resultColExpr = SQLQueryModelFactory.eINSTANCE.createValueExpressionColumn();
                    resultColExpr.setName(columnName);
                    tableQuery.getColumnList().add(resultColExpr);
                    
                    colExprFound = resultColExpr;
                }
            }
            else if (tableExpr instanceof TableInDatabase) {
                TableInDatabase tableInDB = (TableInDatabase) tableExpr;
                Column dbColumn = getColumnForName(tableInDB, columnName);
                if (dbColumn != null) {
                    colExprFound = getColumnExpressionForColumn(tableInDB, dbColumn);
                }
            }
            else if (tableExpr instanceof WithTableReference) {
//                WithTableReference withTable = (WithTableReference) tableExpr;
//                StatementHelper.logError(
//                                TableHelper.class.getName()+
//                                "#getColumnExpressionForName(TableExpression,String) not implemented for TableExpression of type "+
//                                withTable.getClass().getName());
            }
            else if (tableExpr instanceof TableFunction) {
//                TableFunction tableFunc = (TableFunction) tableExpr;
//                throw new UnsupportedOperationException(TableHelper.class.getName()+"#getColumnExpressionForName(TableExpression,String) not implemented for TableExpression of type "+
//                                tableFunc.getClass().getName());
            } 
            else
            {
//                throw new UnsupportedOperationException(TableHelper.class.getName()+"#getColumnExpressionForName(TableExpression,String) not implemented for TableExpression of type "+
//                                tableExpr.getClass().getName());
            }
        }

        return colExprFound;
    }

    /**
     * Returns the Column matching the name of the SQLValueExpressionColumn from
     * the given SQLTableExpression.
     *
     * @param tableExpr the SQLTableExpression to search for the matching
     *          SQLValueExpressionColumn
     * @param colExpr the SQLValueExpressionColumn to search for
     * @return the found column object or null if not found
     */
    static public Column getColumnForColumnExpression(TableExpression tableExpr,
            ValueExpressionColumn colExpr) {
        Column foundColumn = null;
        
        if (colExpr != null) {
            String columnName = colExpr.getName();
            if (tableExpr instanceof TableInDatabase) {
                TableInDatabase tableInDB = (TableInDatabase) tableExpr;
                foundColumn = getColumnForName(tableInDB, columnName);
            }
        }
        
        return foundColumn;
    }

/**
 * Returns the Column matching the columnName from the given TableInDatabase
 * 
 * @param tableInDB the TableInDatabase to search for the matching column name
 * @param columnName the name of the column to search for
 * @return the column object or null if not found
 */
static public Column getColumnForName(TableInDatabase tableInDB,
                                      String columnName) {
    Column columnFound = null;

    if (tableInDB != null && columnName != null) {
        Table dbTable = tableInDB.getDatabaseTable();
        if (dbTable != null) {
            List dbColList = dbTable.getColumns();
            Iterator dbColListIter = dbColList.iterator();
            while (dbColListIter.hasNext() && columnFound == null) {
                Column dbCol = (Column) dbColListIter.next();
                String dbColName = dbCol.getName();
                if (StatementHelper.equalSQLIdentifiers(columnName, dbColName)) {
                    columnFound = dbCol; 
                }
            }
        }
    }

    return columnFound;
}

/**
 * Returns the Column matching the columnName from the given TableReference.
 *
 * @param tableRef the TableReference to search for the matching column name
 * @param columnName the String name of the column to search
 * @return the found column object or null if not found
 */
static public Column getColumnForName(TableReference tableRef,
        String columnName) {
    Column foundColumn = null;
    
    if (tableRef != null) {
        if (tableRef instanceof TableInDatabase) {
            TableInDatabase tableInDB = (TableInDatabase) tableRef;
            foundColumn = getColumnForName(tableInDB, columnName);
        }
        if (tableRef instanceof TableNested) {
            TableNested nest = (TableNested) tableRef;
            TableReference nestedTable = nest.getNestedTableRef();
            foundColumn = getColumnForName(nestedTable, columnName);
        }
        if (tableRef instanceof TableJoined) {
            TableJoined tableJoined = (TableJoined) tableRef;
            TableReference leftTableRef = tableJoined.getTableRefLeft();
            TableReference rightTableRef = tableJoined.getTableRefRight();

            Column leftColumn = getColumnForName(leftTableRef, columnName);
            Column rightColumn = getColumnForName(rightTableRef, columnName);

            if (leftColumn != null) {
                foundColumn = leftColumn;
            } else {
                foundColumn = rightColumn;
            }
        }
    }

    return foundColumn;
}

/**
 * Returns the Column matching the columnName from the given <code>WithTableReference</code>.
 * 
 * @param withTableRef the <code>WithTableReference</code> to search for the
 *        matching column name
 * @param columnName the name of the column to search for
 * @return the found column object or null if not found
 */
static public Column getColumnForName(WithTableReference withTableRef,
                                      String columnName) {
    Column columnFound = null;
    
    if (withTableRef != null && columnName != null) {
        WithTableSpecification withTableSpec = withTableRef.getWithTableSpecification();
        if (withTableSpec != null) {
            // columnName matches either one of the declared columns
            //   then find the corresponding valueExpression in the query result
            // or columnName matches one of the implicit result columns of 
            //   the AS query

//            StatementHelper.logError(TableHelper.class.getName()+
//            "#getColumnForName(WithTableReference,String) is not implemented.");
            // Note: implement if it makes sense, we needed the datatype but that
            //      could be different from the original column!
        }
    }

    return columnFound;
}

    /**
     * Returns a List of <code>ValueExpressionColumn</code>s,
     * with <code>name</code>, <code>dataType</code> and in ordering, 
     * so the given <code>tableRef</code> can be handled like a
     * <code>TableInDatabase</code>.
     * 
     * @param tableRef
     * @return List of exposed <code>ValueExpressionColumn</code>s
     */
    public static List getEffectiveResultColumns(TableReference tableRef) {
        
        // must be a copy of the exposed result columns of the given tableRef
        // to not allow manipulations on it to effect the underlying tableRefs
        List resultColExprList = new ArrayList();
        
        if (tableRef == null) {
            return null;
        }
        else if (tableRef instanceof TableExpression)
        {
            TableExpression tableExpr = (TableExpression) tableRef;
            // if we not already exposed the columns, expose them here
            if (tableExpr.getColumnList() == null ||
                            tableExpr.getColumnList().isEmpty()) 
            {
                exposeEffectiveResultColumns(tableExpr);
            }
            // add the exposed effective result columns from the tableExpression
            resultColExprList.addAll(tableExpr.getColumnList());
        }
        else if (tableRef instanceof TableJoined) 
        {
            // Note: naming rules for result columns in the higher star-query
            //   different Databases will have different naming schemes!!!
            //   hand-in a String pattern or a formatter object that does it
            //   e.g. assume TABLE_A (col1, col2, col3) and TABLE_B (col1,col5)
            //   duplicated column name "col1" what are the result columns of
            //   select * from TABLE_A as A join TABLE_B as B on A.col1 = B.col1
            //   -> col1, col2, col3, col1, col5
            //      ^                 ^      
            TableJoined tableJoin = (TableJoined) tableRef;
            
            List leftCols = 
                getEffectiveResultColumns(tableJoin.getTableRefLeft());
            List rightCols = 
                getEffectiveResultColumns(tableJoin.getTableRefRight());
            resultColExprList.addAll(leftCols); 
            resultColExprList.addAll(rightCols); 
        } 
        else if (tableRef instanceof TableNested)
        {
            TableNested tableNest = (TableNested) tableRef;
            resultColExprList = 
                getEffectiveResultColumns(tableNest.getNestedTableRef());
        }
        else
        {
//            StatementHelper.logError(TableHelper.class.getName()+
//                            ": implement getEffectiveResultColumns(TableReference)" +
//                            " for "+tableRef.getClass().getName());
        }
               
        return resultColExprList;
    }

    /**
       * Returns the name or the alias name of the given
       * <code>TableExpression</code> <code>tableExpr</code> depending on, whether
       * or not the <code>tableExpr</code> has a
       * <code>TableCorrelation</code>.
       * 
       * @param tableExpr
       *          the TableExpression for which the exposed name is needed
       * @return the exposed table name
       */
      public static String getExposedTableName(TableExpression tableExpr) {
        String tablename = null;
        if (tableExpr != null) {
            if (tableExpr.getTableCorrelation() != null) {
                tablename = tableExpr.getTableCorrelation().getName();
            } else {
                tablename = tableExpr.getName();
            }
        }
        return tablename;
      }




  /**
   * Returns the list of Columns that are part of the Foreign key constraint for
   * the given table.
   *
   * @param table
   *          the Table for which list of Foreign key columns needed
   * @return the list of columns
   */
  public static List getForeignKeyColumns(Table table) {
    List fkCols = new ArrayList();
    if (table instanceof BaseTable) {
      List list = ((BaseTable) table).getConstraints();
      Iterator conItr = list.iterator();
      while (conItr.hasNext()) {
        Constraint con = (Constraint) conItr.next();
        if (con instanceof ForeignKey) {
          fkCols.addAll(((ForeignKey) con).getMembers());
        }
      }
    }
    return fkCols;
  }

  /**
   * Returns fully qualified name for the given table.
   *
   * @param table
   *          the Table for which the fully qualified name is needed
   * @return the fully qualified table name
   */
  public static String getFullTableName(Table table) {
    String tablename = table.getName();
    if (table.getSchema() != null) {
      tablename = table.getSchema().getName() + "." + tablename;
    }
    return tablename;
  }

   
/**
 * Returns the <code>ValueExpressionColumn</code> with the given
 * <code>columnName</code> from the given <code>tableExpression</code>'s
 * <code>columnList</code> or, if the given <code>tableExpression</code>'s
 * <code>columnList</code> does not contain a
 * <code>ValueExpressionColumn</code> with the given <code>columnName</code>,
 * creates a new <code>ValueExpressionColumn</code> with the given
 * <code>columnName</code> and returns it.
 * 
 * @param columnName 
 * @param tableExpr optional <code>TableExpression</code>, if provided the
 *      existing <code>ValueExpressionColumn</code> with the given
 *      <code>columnName</code> will be returned from the
 *      <code>tableExpr</code>'s <code>columnList</code>
 * 
 * @return a <code>ValueExpressionColumn</code> - already existing or newly
 *      created
 * 
 */
public static ValueExpressionColumn getOrCreateColumnExpression(String columnName, TableExpression tableExpr)
{
    ValueExpressionColumn newCol = null;
    
    if (columnName != null) 
    {
        // first try to find a column (not in the depth of the database, though)
        if (tableExpr != null && tableExpr.getColumnList() != null)
        {
            newCol = getColumnExpressionForName(tableExpr, columnName);
        }
    }
    
    // if no existing column was found create one and don't attach it to the tableExpr
    if (newCol == null)
    {
        newCol = StatementHelper.createColumnExpression(columnName);
// in question if we want the references from column to table
// or the table have this column added to its exposed columnList
// we don't want to mess with the exposed columnList though!!! (ordering, names, types!
// will be added to table's exposedColumnList from caller of this method anyways, right?
//          newCol.setTableExpr(tableExpr);
    }
    
    return newCol;
}
   
  
  /**
   * Returns the list of Columns that are part of the primary key constraint for
   * the given table.
   *
   * @param table
   *          the Table for which list of primary key columns needed
   * @return the list of columns
   */
  public static List getPrimaryKeyColumns(Table table) {
    List primarykeyCols = new ArrayList();
    if (table instanceof BaseTable) {
      List list = ((BaseTable) table).getConstraints();
      Iterator conItr = list.iterator();
      while (conItr.hasNext()) {
        Constraint con = (Constraint) conItr.next();
        if (con instanceof PrimaryKey) {
          primarykeyCols.addAll(((PrimaryKey) con).getMembers());
        }
      }
    }
    return primarykeyCols;
  }
  
/**
 * Returns the <code>ResultColumn</code> contained in the
 * <code>selectClause</code> of the given <code>QueryExpressionBody</code>,
 * whose <code>name</code> matches the given <code>columnName</code>
 * or whose referenced <code>ValueExpression</code> is of type
 * <code>ValueExpressionColumn</code> and has a <code>name</code> matching
 * the given <code>columnName</code>.
 * 
 * @param tableQuery the TableReference to search for the matching column name
 * @param columnName the name of the ValueExpressionColumn to search
 * @return the found <code>ResultColumn</code> <code>null</code>
 */
public static ResultColumn getResultColumnForAliasOrColumnName(QueryExpressionBody tableQuery,
                                                               String columnName) {
    ResultColumn resultColFound = null;

    if (tableQuery != null && columnName != null) {
        List resultColList = getResultColumnsOfQueryExpression(tableQuery);
        Iterator resultColListIter = resultColList.iterator();
        while (resultColListIter.hasNext() && resultColFound == null) {
            ResultColumn resultCol = (ResultColumn) resultColListIter.next();
            String resultColName = resultCol.getName();
            ValueExpression resultColValExpr = resultCol.getValueExpr();
            
            if (StatementHelper.equalSQLIdentifiers(columnName, resultColName)) { 
                resultColFound = resultCol;
            }
            else if (resultColValExpr instanceof ValueExpressionColumn) {
                ValueExpressionColumn valExprCol = (ValueExpressionColumn) resultColValExpr;
                String valExprColName = valExprCol.getName();
                
                if (StatementHelper.equalSQLIdentifiers(columnName, valExprColName)) {
                    resultColFound = resultCol;
                }
            }
        }         
    }

    return resultColFound;
}

    /**
     * Returns the <code>ResultColumn</code> contained in the
     * <code>selectClause</code> of the given <code>QueryExpressionBody</code>,
     * whose <code>name</code> matches the given <code>columnName</code>.
     * 
     * @param tableQuery the TableReference to search for the matching column name
     * @param columnName the name of the ValueExpressionColumn to search for
     * @return the found ValueExpressionColumn or <code>null</code>
     */
    static public ResultColumn getResultColumnForName(QueryExpressionBody tableQuery,
                                                                   String columnName) {
        ResultColumn resultColFound = null;

        if (tableQuery != null && columnName != null) {
            List resultColList = getResultColumnsOfQueryExpression(tableQuery);
            Iterator resultColListIter = resultColList.iterator();
            while (resultColListIter.hasNext() && resultColFound == null) {
                ResultColumn resultCol = (ResultColumn) resultColListIter.next();
                String resultColName = resultCol.getName();
                // check with regards to delimited identifier
                if (StatementHelper.equalSQLIdentifiers(columnName, resultColName)) {
                    resultColFound = resultCol;
                }
            }         
        }

        return resultColFound;
    }
  
/**
 * Returns the <code>ResultColumn</code>s of the given
 * <code>QueryExpressionBody queryExpr</code>.
 * <p>
 * <b>NOTE:</b>
 * If the given <code>queryExpr</code>'s 
 * <code>QueryResultSpecification</code> is of type
 * <code>ResultTableAllColumns</code> ('SELECT * FROM ...') an empty List
 * will be returned.
 * </p>
 * 
 * @param queryExpr the <code>QueryExpressionBody</code> for which the
 *      <code>ResultColumn</code>s will be returned
 * @return the <code>ResultColumn</code>s of the given <code>queryExpr</code>
 */
static public List getResultColumnsOfQueryExpression(QueryExpressionBody queryExpr) {
    List resultColList = new ArrayList();

    if (queryExpr != null) {
        if (queryExpr instanceof QuerySelect) {
            QuerySelect select = (QuerySelect) queryExpr;
            List resultClause = select.getSelectClause();
            Iterator resultClauseIter  = resultClause.iterator();
            while (resultClauseIter.hasNext()) {
                QueryResultSpecification resultSpec = (QueryResultSpecification) resultClauseIter.next();
                if (resultSpec instanceof ResultColumn) {
                    ResultColumn resultCol = (ResultColumn) resultSpec;
                    resultColList.add(resultCol);
                }
            }
        } 
        else if (queryExpr instanceof QueryCombined) {
            QueryCombined combinedQ = (QueryCombined) queryExpr;                
            resultColList.addAll(getResultColumnsOfQueryExpression(combinedQ.getLeftQuery()));
            resultColList.addAll(getResultColumnsOfQueryExpression(combinedQ.getRightQuery()));
        }
        else if (queryExpr instanceof QueryNested) {
            QueryNested nest = (QueryNested) queryExpr;
            QueryExpressionBody nestedQuery = nest.getNestedQuery();
            resultColList.addAll(getResultColumnsOfQueryExpression(nestedQuery));
        }
        else {
            throw new UnsupportedOperationException(TableHelper.class.getName()+"#getColumnExpressionForName(QueryExpressionBody,String) not implemented for TableExpression of type "+
                            queryExpr.getClass().getName());
        }
    }

    return resultColList;
}

/**
 * @param tableInDB
 * @return
 */
public static Schema getSchemaForTableInDatabase(TableInDatabase tableInDB)
{
    Schema colTblRefSchema = null;

    if (tableInDB.getDatabaseTable() != null
                    && tableInDB.getDatabaseTable().getSchema() != null)
    {
        colTblRefSchema = tableInDB.getDatabaseTable().getSchema();
    }
    
    return colTblRefSchema;
}

  /**
   * Returns the name of the <code>Schema</code> that the given
   * <code>TableExpression</code> is part of or <code>null</code>, if the
   * given <code>TableExpression</code> has no reference to a
   * <code>Schema</code> or the referenced <code>Schema</code>'s name is
   * <code>null</code>.
   *
   * @param tableExpr
   * @return the <code>Schema</code> name or <code>null</code>
   */
  public static String getSchemaNameForTableExpression(TableExpression tableExpr) {
    String schemaName = null;
    if (tableExpr instanceof TableInDatabase) {
      TableInDatabase tableInDB = (TableInDatabase) tableExpr;
      Table table = tableInDB.getDatabaseTable();
      if (table != null) {
        Schema schema = table.getSchema();
        if (schema != null) {
          schemaName = schema.getName();
        }
      }
    }
    return schemaName;
  }
  
  /**
   * Gets the table object from the given list of table references that is
   * associated with (contains) a column with the given table and column name.
   * If the table name is null or blank, look through all the tables for a match
   * on the column name alone.
   *
   * @param aTableName
   *          a table name to use to search for a table reference
   * @param aColName
   *          a column name to use to search for a table reference
   * @param aTableExprList
   *          a list of table references to search
   * @return TableExpression the table expression associated with the column.
   *         Null if not found
   */
  public static TableExpression getTableExpressionForNamedColumn(
      String aTableName, String aColName, List aTableExprList) {
    TableExpression tableExpr = null;
    Column rdbColumn = null;

    // Scan the list of tables.
    boolean foundIt = false;
    Iterator tableExprListIter = aTableExprList.iterator();
    while (tableExprListIter.hasNext() && foundIt == false) {
      // See if we have a match by table name.
      tableExpr = (TableExpression) tableExprListIter.next();
      if (aTableName != null && aTableName.length() > 0) {
        String tableExprName = tableExpr.getName();
        String tableExprCorrName = null;

        if (tableExpr.getTableCorrelation() != null) {
          tableExprCorrName = tableExpr.getTableCorrelation().getName();
        }

        if (StatementHelper.equalSQLIdentifiers(aTableName, tableExprName)
         || StatementHelper.equalSQLIdentifiers(aTableName, tableExprCorrName)) {
          foundIt = true;
        }
      }
      // If no table name, use the column to try to find the table.
      else {
        if (tableExpr instanceof TableInDatabase) {
          TableInDatabase dbTable = (TableInDatabase) tableExpr;
          rdbColumn = getColumnForName(dbTable, aColName);
          if (rdbColumn != null) {
            foundIt = true;
          }
        }
      }
    }

    if (foundIt == false) {
      tableExpr = null;
    }

    return tableExpr;
  }

  /**
   * Gets the TableExpression object from the given list of table references by
   * name or correlation name ("AS"-alias). 
   * @param aTableName
   *          a table name or alias name to use to search for a table reference
   * @param aTableExprList
   *          a list of table references to search
   * @return TableExpression the table expression associated with the column.
   *         Null if not found
   */
  public static TableExpression getTableExpressionFromTableExprList(String aTableName, List aTableExprList) {
    TableExpression retTableExpr = null;

    // Scan the list of tables.
    boolean foundIt = false;
    Iterator tableExprListIter = aTableExprList.iterator();
    while (tableExprListIter.hasNext() && foundIt == false) {
      // See if we have a match by table name.
      TableExpression tableExpr = null; 
      tableExpr = (TableExpression) tableExprListIter.next();
      if (aTableName != null && aTableName.length() > 0) {
        String tableExprName = tableExpr.getName();
        String tableExprCorrName = null;

        if (tableExpr.getTableCorrelation() != null) {
          tableExprCorrName = tableExpr.getTableCorrelation().getName();
        }
        //if (aTableName.equalsIgnoreCase(tableExprName) || aTableName.equalsIgnoreCase(tableExprCorrName)) {
        if (StatementHelper.equalSQLIdentifiers(aTableName, tableExprName)
         || StatementHelper.equalSQLIdentifiers(aTableName, tableExprCorrName)) {
            retTableExpr = tableExpr ;
            foundIt = true;
        }
      }
    }
    return retTableExpr;
  }


    /**
       * Retrieves a List of <code>TableExpression</code> s from the given
       * <code>TableReference</code>.
       *
       * @param tableRef
       * @return
       */
      public static List getTableExpressionsInTableReference(TableReference tableRef) {
        List tableExprList = new ArrayList();
    
        if (tableRef instanceof TableExpression) {
          TableExpression tableExpr = (TableExpression) tableRef;
          tableExprList.add(tableExpr);
        }
    
        if (tableRef instanceof TableNested) {
          TableNested nest = (TableNested) tableRef;
          tableExprList.addAll(getTableExpressionsInTableReference(nest.getNestedTableRef()));
        }
    
        if (tableRef instanceof TableJoined) {
          TableJoined join = (TableJoined) tableRef;
          tableExprList.addAll(getTableExpressionsInTableReference(join.getTableRefLeft()));
          tableExprList.addAll(getTableExpressionsInTableReference(join.getTableRefRight()));
    
        }
    
        return tableExprList;
      }
  
  /**
   * Retrieves a List of <code>TableExpression</code>s from the given List of
   * <code>TableReference</code>s.
   *
   * @param tableReferenceList
   * @return
   */
  public static List getTableExpressionsInTableReferenceList(List tableReferenceList) {
    List tableExprList = new ArrayList();

    for (Iterator tableRefIt = tableReferenceList.iterator(); tableRefIt.hasNext();) {
      TableReference tableRef = (TableReference) tableRefIt.next();

      tableExprList.addAll(getTableExpressionsInTableReference(tableRef));
    }

    return tableExprList;
  }

  /**
   * Returns the Table from the given SQLTableExpression if there is one
   * otherwise return null.
   *
   * @param tableExpr
   *          the SQLTableExpression for which the table is needed
   * @return the table
   */
  static public Table getTableForTableExpression(TableExpression tableExpr) {
    Table table = null;
    if (tableExpr != null && (tableExpr instanceof TableInDatabase)) {
      TableInDatabase tableInDB = (TableInDatabase) tableExpr;
      table = tableInDB.getDatabaseTable();
    }

    return table;
  }

  /**
   * Retrieves the <code>TableInDatabase</code> objects contained in the given
   * List of <code>TableReference</code>s. This method might be useful to
   * find all the references to database tables in the FROM-clause of a
   * <code>QuerySelect</code>.
   * 
   * @param tableReferenceList List of <code>TableReference</code> s
   * @return List of <code>TableInDatabase</code> s contained in the given
   *         <code>tableReferenceList</code>
   */
  public static List getTableInDatabaseInTableReferenceList(List tableReferenceList) {
    List tableExprList = new ArrayList();

    for (Iterator tableRefIt = tableReferenceList.iterator(); tableRefIt.hasNext();) {
      TableReference tableRef = (TableReference) tableRefIt.next();
      tableExprList.addAll(getTableExpressionsInTableReference(tableRef));
    }
    
    // filter out TableInDatabase
    for (Iterator tableExprIt = tableExprList.iterator(); tableExprIt.hasNext();) {
        if (!(tableExprIt.next() instanceof TableInDatabase)) {
            tableExprIt.remove();
        }
    }
    
    return tableExprList;
  }

  /**
   * Returns true if the given column is part of Foreign key constraint.
   *
   * @param column
   *          the Column that needs to check for the Foreign key constraint
   * @return the Boolean result
   */
  public static boolean isForeignKey(Column column) {
    boolean retValue = false;
    Table table = column.getTable();
    List fkCols = getForeignKeyColumns(table);
    Iterator fkcolItr = fkCols.iterator();
    while (fkcolItr.hasNext() && retValue == false) {
      if (column == (Column) fkcolItr.next()) {
        retValue = true;
      }
    }
    return retValue;
  }

  /**
   * Returns true if the given ValueExpressionColumn is part of Foreign key constraint.
   *
   * @param colExpr
   *          the ValueExpressionColumn that needs to check for the Foreign key constraint
   * @return the Boolean result
   */
  public static boolean isForeignKey(ValueExpressionColumn colExpr) {
    boolean retValue = false;
    if (colExpr != null && colExpr.getTableExpr() instanceof TableInDatabase) {
        Column column = getColumnForColumnExpression( colExpr.getTableExpr(), colExpr) ;
        if (column != null) {
            retValue = isForeignKey(column) ;
        }
    }
    return retValue;
  }

/**
   * Returns true if the given column is part of a primary key constraint.
   *
   * @param column
   *          the Column that needs to check for the primary key constraint
   * @return the Boolean result
   */
  public static boolean isPrimaryKey(Column column) {
    boolean retValue = false;
    Table table = column.getTable();
    List primarykeyCols = getPrimaryKeyColumns(table);
    Iterator pkcolItr = primarykeyCols.iterator();
    while (pkcolItr.hasNext() && retValue == false) {
      if (column == (Column) pkcolItr.next()) {
        retValue = true;
      }
    }
    return retValue;
  }

    /**
       * Returns true if the given ValueExpressionColumn is part of a primary key constraint.
       *
       * @param colExpr
       *          the ValueExpressionColumn that needs to check for the primary key constraint
       * @return the Boolean result
       */
      public static boolean isPrimaryKey(ValueExpressionColumn colExpr) {
        boolean retValue = false;
        if (colExpr != null && colExpr.getTableExpr() instanceof TableInDatabase) {
            Column column = getColumnForColumnExpression( colExpr.getTableExpr(), colExpr) ;
            if (column != null) {
                retValue = isPrimaryKey(column) ;
            }
        }
        return retValue;
      }

    /**
       * Returns the <code>true</code> if the given <code>tableExpr</code>
       * is referenced by another <code>ValueExpressionColumn</code>
       * with the same name as the given <code>referencedByColumnName</code>
       * 
       * 
       * @param tableExpr
       *            the TableReference searched for the matching column name reference
       * @param referencedByColumnName
       *            the String name of the ValueExpressionColumn to be searched in the
       * list of column references to the given <code>tableExpr</code>
       * @return <code>true</code>
       *   if another column was found to reference the given <code>tableExpr</code>
       * 
       */
      static public boolean isTableReferencedByColumnWithName(TableExpression tableExpr,
              String referencedByColumnName) {
          boolean colExprFound = false;
    
          // Try to find the column in the columnList of the given table.
          if (tableExpr != null && referencedByColumnName != null) {
              List tableColList = tableExpr.getValueExprColumns();
              Iterator tableColListIter = tableColList.iterator();
              while (tableColListIter.hasNext() && colExprFound == false) {
                  ValueExpressionColumn tableCol = (ValueExpressionColumn) tableColListIter.next();
                  String tableColName = tableCol.getName();
                  if (StatementHelper.equalSQLIdentifiers(referencedByColumnName, tableColName)) {
                      colExprFound = true;
                  }
              }
          }
    
          return colExprFound;
      }

    /**
       * Populates the list of ValueExpressionColumn in the given table expression
       * using the columns in the given Table object
       *
       * @param tableInDB
       *          the TableInDatabase to be populated
       * @param databaseTable
       *          the Table object used in populating the tableExpression
       */
      public static void populateTableExpressionColumns(TableInDatabase tableInDB,
          Table databaseTable) {
          
        if (databaseTable == null || tableInDB == null) { return; }
    
        List rdbColumnList = databaseTable.getColumns();
        Iterator colItr = rdbColumnList.iterator();
        List columnList = tableInDB.getColumnList();
        columnList.clear(); // clean out the previously populated columns
        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        while (colItr.hasNext()) {
          Column col = (Column) colItr.next();
          ValueExpressionColumn valueExprColumn = null;
          
          // we don't need this anymore, since we'll have a list of only the exposed
          // columns vs. the list of column references to the table
    //      ValueExpressionColumn valueExprColumn = TableHelper.getColumnExpressionForColumn(tableExpr, col);
    //      if (valueExprColumn == null) {
          
            valueExprColumn = factory.createValueExpressionColumn();
            valueExprColumn.setName(col.getName());
            DataType type = ValueExpressionHelper.copyDataType(col.getDataType());
            valueExprColumn.setDataType(type);
            
            // if this tableInDB will be used in a nested query, e.g.
            //  "select t1.col1 as c1 from (select col1 from table1) as t1;"
            // the effective result column of the outer/big select will be "c1"
            // and we want to be able to know "c1" was derived from table1
            // we are setting the TableInDatabase that this column was
            // originally derived from, this reference will be passed on through all
            // levels of nested queries. We should use a reference called
            // "derivedFromTableInDatabase"
            valueExprColumn.setTableInDatabase(tableInDB);
    
            columnList.add(valueExprColumn);
    //      }
        }
      }

    /**
       * Removes a columnExpression from its tableExpression if it has
       * no other references to objects other than its tableExpression.
       * 
       * @param columnExpr
       */
      public static void removeColumnExpressionFromTableIfNotReferenced(ValueExpressionColumn col) {
          boolean isReferenced = false;
          
          // if col has its table reference and all the columns references are only 1
          // this one ref is the table ref and will be deleted
          if (col != null && col.getTableExpr() != null) {
    
            for(Iterator refIt = col.eClass().getEAllReferences().iterator();refIt.hasNext();) {
                EReference ref = (EReference) refIt.next();
                if(ref.isMany()) {
                    EList refList = (EList)col.eGet(ref);
                    // is there a reference
                    if (refList.size() > 0) {
                        isReferenced = true;
                    }
                } else {
                    EObject reference = (EObject) col.eGet(ref);
                    // is there some reference besides its table
                    if (reference != null && reference != col.getTableExpr()
                                    && reference != col.getTableInDatabase()) {
                        isReferenced = true;
                    }
                }
                
            }
            
            if (!isReferenced) {
                col.setTableExpr(null);
            }
    
          }
      }

    /**
     * Link table references in column expressions to tables in the given
     * list of tables (that is, the From clause),
     * copies <code>DataType</code>s as well.
     * 
     * <p><b>Note:</b> the given set of <code>ValueExpressionColumn</code>s 
     * <code>aColumnSet</code> will be modified directly. The resolved
     * columns will be removed from the set.
     * </p>
     * 
     * <p><b>Note:</b> <code>TableExpression</code>s directly contained in the given
     * <code>aTableRefList</code> will be given preference before
     * <code>TableExpression</code>s found contained indeirectly within
     * <code>TableNested</code>s or <code>TableJoined</code>s, when two
     * <code>TableReference</code>s with the same <code>name</code> are found.
     * </p>
     * 
     * @param unresolvedColumns will be modified, see NOTE
     * 
     * @param aTableRefList a list of tables to use to resolve column references
     */
    public static void resolveColumnTableReferences( Collection unresolvedColumns, List aTableRefList ) {

        if (unresolvedColumns == null || unresolvedColumns.isEmpty() == true) { 
            return; 
        }
        
        // expand joined tables
        List tableExprList = getTableExpressionsInTableReferenceList(aTableRefList);
        
        ValueExpressionColumn col = null;   // column to resolve
        String colName = null; // the name of column to resolve
        TableExpression colTblRef = null;   // table referenced by the column
        TableCorrelation colTblRefCorr = null; // correlation of table referenced by the column, if column was resolved before
        String colTblRefSchemaName = null;  // the Schema that might be qualified for the column  schema1.table1.col1
        String colQualifier = null;  // the table qualifier of the column
        
        TableExpression refTableFound = null;   // table to match the column
        boolean isOffLine = getIsOffLine(tableExprList);  // find out if we're in off-line (non-connected) mode
        
        for (Iterator colIt = unresolvedColumns.iterator(); colIt.hasNext();)
        {
            col = (ValueExpressionColumn) colIt.next();
            colName = col.getName();
            colTblRef = col.getTableExpr();

            
            // first finding table candidate refTableFound
            
            if (colTblRef == null)
            {
             // If we're off-line and there is only one tableExpr, assume the columns are in it.
                if (isOffLine && tableExprList.size() == 1)
                {
                    refTableFound = (TableExpression) tableExprList.get(0);
                    // don't check here if the table found really 
                    // has a column with that name, part of semantic check
                }
                else
                {
                    // maybe one of the tables has a column with the same name
                    // only one!
                    
                    // if we have the real database columns resolved, we check
                    // for other column in the tables exposed columnList
                    // if we run without database connected, we might have no
                    // columns in the tables exposed columnList (tables not resolved)
                    // in that case we are checking for other columns that refer
                    // to that table : check disconnected mode
                    refTableFound = findTableExpressionForColumnName(tableExprList, colName, true);                    
                }
                
                // The table ref found is not valid if it is a query select that contains the column.
                if (refTableFound != null && getIsQueryParentOfColumn(refTableFound, col) == true) {
                    refTableFound = null;
                }
            }
            // was the column previously resolved?
            else if (StatementHelper.getQuerySelectForTableReference(colTblRef) != null)
            {
                // column was already resolved, we checked that the referenced
                // tableExpr is part of a fromClause of a QuerySelect
                refTableFound = colTblRef;
            }
            else
            {
                colQualifier = colTblRef.getName();
                colTblRefCorr = colTblRef.getTableCorrelation();
                
                // if the column to resolve was resolved already we must 
                // consider the alias of the columns table ref, if there are
                // two FROM-tables with the same name, but different aliases
                // select t1.col1 from table1 t1, table1 t2;
                // before first resolving:
                //   col1.getTable() == t1 and
                //   col1.getTable().getCorrelation() == null
                // after first resolving:
                //   col1.getTable() == table1 and 
                //   col1.getTable().getCorrelation() == t1
                if (colTblRefCorr != null)
                {
                    colQualifier = colTblRefCorr.getName();
                }
                
                
                // for "select table1.col1 from table1 t1, table1 t2"
                //  we want "col1" to be associated with "table1 t1"
                // for "select s2.table1.col1 from s1.table1 t1, s2.table1 t2"
                //  we want "col1" to be associated with "table1 t2"
                // for "select table1.col1 from s1.table1 t1, s2.table1 t2"
                //  where the default Schema is "s1" we want "col1" to be 
                //  associated with "table1 t1" as we assume no Schema is default schema
                // for "select t1.col1 from t2 as t1, t1 as t2"
                //  we want "col1" to be associated with "t2 as t1"
                //  where in "select s1.t1.col1 from s1.t2 as t1, s1.t1 as t2"
                //  we want "col1" associated with "s1.t1 as t2"
                

                // if the user qualified the column name with schema and table
                // "schema.table.column"
                // we assume that the table qualifier is the actual table name
                // rather than its alias/correlation name, like in  
                // "select s1.t1.col1 from s1.t2, s1.t1", and not likely
                // "select s1.t1.col1 from s1.t2 as t1, s1.t1 as t2"
                // and the column would refer to the 2nd table reference "s1.t1"
                // "select s1.t1.col1 from s2.t1, s1.t1" or if
                // default schema "s1": "select t1.col1 from s2.t1, t1" 
                colTblRefSchemaName = getSchemaName(colTblRef);
                if (colTblRefSchemaName != null)
                {
                    //colTblRefSchemaName = getDefaultSchemaName(colTblRef);
                    // col ref without schema doesn't mean the col belongs to
                    // default schema!
                    // "select t2.col1 from table1 as t1, s2.table1 as t2"  with
                    // "s1" as default schema still allows "t2.col1" to be in
                    // schema "s2"
                    
                    refTableFound = findTableExpressionInTableExpressionList(
                                    colTblRefSchemaName, colTblRef.getName(),
                                    tableExprList,
                                    getDefaultSchemaName(col));
                }
                else
                {
                    refTableFound = findTableExpressionsByNameOrAlias(
                                    colQualifier, tableExprList,
                                    getDefaultSchemaName(col));
                }
            }
            
            if (refTableFound != null)
            {
                            
                // if refTableFound has been resolved already, it has
                // a list of exposed columns and we should find
                // a correlated column with same name to retrieve its
                // DataType and the derived-from TableInDatabase
                ValueExpressionColumn corrExposedTableCol =
                    getColumnExpressionForName(refTableFound, colName);
                
                if (corrExposedTableCol != null)
                {
                    DataType dataType = 
                        corrExposedTableCol.getDataType();
                    // copy by value, cause EMF owner relationship
                    DataType colDataType = 
                        ValueExpressionHelper.copyDataType(dataType);
                
                    if (colDataType != null)
                    {
                        col.setDataType(colDataType);
                    }
                    
                    // set the table that this column is ultimately derived from
                    if (corrExposedTableCol.getTableInDatabase() != null)
                    {
                        col.setTableInDatabase(corrExposedTableCol.getTableInDatabase());
                    }
                }
                
                col.setTableExpr(refTableFound);
                // remove the column from the unresolved column set
                colIt.remove();
           }


        } // end for columnSet iteration

    }

    /**
     * Resolves the <code>TableReference</code>s of the
     * <code>ResultTableAllColumns</code> in the given
     * <code>resultTableList</code> with the <code>TableExpression</code>s
     * in the given <code>tableExprList</code>.
     * 
     * @param resultTableList
     *            a List of {@link ResultTableAllColumns}
     * @param tableExprList
     *            a List of {@link TableExpression}s
     */
    public static void resolveResultTableReferences(List resultTableList, List tableExprList)
    {
        if (resultTableList == null || resultTableList.isEmpty())
        {
            return;
        }
        
        for (Iterator resultTableIt = resultTableList.iterator(); resultTableIt.hasNext();)
        {
            ResultTableAllColumns resultTable = (ResultTableAllColumns) resultTableIt.next();
                
            if (resultTable != null && resultTable.getName() != null) 
            {
                String resultTableName = resultTable.getName();           
                TableExpression tableExpr = getTableExpressionFromTableExprList(resultTableName, tableExprList);
                
                if (tableExpr != null) {
                    resultTable.setTableExpr(tableExpr);
                }
            }
        }
    }

    /**
     * Assigns the alias to the given TableExpression. 
     * @param tableExpr the TableExpression for which alias needs to be set
     * @param alias the String value for the alias
     */
    public static void setTableAliasInTableExpression(TableExpression tableExpr, String alias) {
        if (alias.trim().length() > 0) {
            TableCorrelation tableCorr = SQLQueryModelFactory.eINSTANCE.createTableCorrelation() ;
            tableCorr.setName(alias.trim().toUpperCase());
            tableExpr.setTableCorrelation(tableCorr) ;
        }
    }
    
    /**
     * Returns a new List with new <code>ValueExpressionColumn</code>s
     * reflecting the given <code>origColExprList</code> of
     * <code>ValueExpressionColumn</code>s. This method returns a result List
     * different from the List that would result of
     * <code>
     *   List newList = ArrayList(); // could be any List implementation
     *   newList.addAll(origColExprList);
     * </code>
     * 
     * @param origColExprList List of <code>ValueExpressionColumn</code>s to be
     *  copied by value
     * @return a new List containing new copied <code>ValueExpressionColumn</code>
     *  objects
     */
    private static List copyColumnExprList(List origColExprList)
    {
        //return (List)EcoreUtil.copyAll(origColExprList); // didn't copy the 
        // reference to the derived TableInDatabase
        List result;
        if(origColExprList == null){
            result = new ArrayList();            
        } else {
            result = new ArrayList(origColExprList.size());
            for (Iterator it = origColExprList.iterator(); it.hasNext();)
            {
                ValueExpressionColumn original = (ValueExpressionColumn) it.next();
                ValueExpressionColumn copy = 
                    (ValueExpressionColumn)EcoreUtil.copy(original);
                
                // copy the reference to the derived TableInDatabase
                // we should have a reference called "derivedFromTableInDatabase"
                copy.setTableInDatabase(original.getTableInDatabase());
                
                result.add(copy);
            }
        }
        return result;
   }

    /**
     * Adds <code>ValueExpressionColumn</code>s to the given
     * <code>queryCombined</code> for each of its queries' exposed columns, see
     * {@link #exposeEffectiveResultColumns(QueryExpressionBody)}, if both
     * combined queries have the same number of exposed columns (explicitly by
     * <code>ResultColumn</code>s or implicit by
     * <code>ResultTableAllColumns</code> or no
     * <code>QueryResultSpecification</code> at all - "select * from ...")
     * If the related <code>ValueExpressionColumn</code> names of both combined
     * queries don't match, the exposed <code>ValueExpressionColumn</code> will
     * get the <code>leftQuery</code>'s name.
     * That methods is used for resolving purposes and useful in case the given
     * <code>queryCombined</code> is used as a nested query as a
     * <code>TableReference</code> within another <code>QuerySelect</code>.
     * 
     * @param queryCombined
     * @return List of already or newly exposed <code>ValueExpressionColumn</code>s
     */
    private static List exposeResultColumnsOfQueryCombined(QueryCombined queryCombined)
    {
        List exposedColumns = new ArrayList();
        
        if (queryCombined == null) {return exposedColumns;}
        
        List exposedColListLeft = getEffectiveResultColumns(queryCombined.getLeftQuery());
        List exposedColListRight = getEffectiveResultColumns(queryCombined.getRightQuery());
        
        // only if the two combined queries have the same number of columns
        if (exposedColListLeft != null && exposedColListRight != null
                        && exposedColListLeft.size() == exposedColListRight.size())
        {
            for (int i = 0; i < exposedColListLeft.size(); i++)
            {
                ValueExpressionColumn resultColExprLeft = 
                    (ValueExpressionColumn) exposedColListLeft.get(i);
                ValueExpressionColumn resultColExprRight = 
                    (ValueExpressionColumn) exposedColListRight.get(i);
                
                String leftName = resultColExprLeft.getName();
                String rightName = resultColExprRight.getName();
                
                
                String exposedColumnName = null;
                
                // the result name is always(?) the left name
                // except we have a QueryValues on the left side (has no names)
                if (queryCombined.getLeftQuery() instanceof QueryValues &&
                               !(queryCombined.getRightQuery() instanceof QueryValues))
                {
                    exposedColumnName = rightName;
                }
                else
                {
                    exposedColumnName = leftName;
                }
                
                // get previously exposed columns or create new one
                ValueExpressionColumn exposedColumn = 
                    getOrCreateColumnExpression(exposedColumnName, queryCombined);
                
                // get the best fitting inclusive DataType
                DataType exposedColumnDataType = 
                    ValueExpressionHelper.resolveCombinedDataType(
                                    resultColExprLeft.getDataType(),
                                    resultColExprRight.getDataType());
                
                exposedColumn.setDataType(exposedColumnDataType);
                
                // there is likely to be no common derived tableInDB for the 
                // here exposed column, but check if both exposed columns of
                // the underlying combined query are derived from the same
                // database table
                if (resultColExprLeft.getTableInDatabase() 
                                == resultColExprRight.getTableInDatabase())
                {
                    exposedColumn.setTableInDatabase(resultColExprLeft.getTableInDatabase());
                }
                
                exposedColumns.add(exposedColumn);
            }
            
        }
        // all the previously associated columns (if previously exposed)
        // are in exposedColumns @see #getOrCreateColumnExpression()
        //so clear the old columnList to not duplicate and preserve ordering
        queryCombined.getColumnList().clear();
        queryCombined.getColumnList().addAll(exposedColumns);
        
        return exposedColumns;
    }

    /**
     * Adds a <code>ValueExpressionColumn</code> to the given
     * <code>querySelect</code> for each of its <code>ResultColumn</code>s that
     * have either a <code>name</code> or reference a
     * <code>ValueExpressionColumn</code> that has a <code>name</code>. That is
     * useful in case the given <code>querySelect</code> is used as a nested
     * query as a <code>TableReference</code> within another
     * <code>QuerySelect</code>.
     * If the given <code>querySelect</code> already exposes some or all the
     * columns of its <code>selectClause</code>, these columns will not be
     * duplicated.
     * <p><b>Note:</b> 
     * If the <code>querySelect</code>'s <code>selectClause</code> does include
     * <code>ResultColumn</code>s without a <code>name</code> a
     * <code>ValueExpressionColumn</code> without a <code>name</code> will be
     * exposed, in order to correctly reflect order and number of resulting
     * columns for the given <code>QuerySelect</code>.
     * If the <code>querySelect</code>'s <code>selectClause</code> does include
     * a <code>ResultTableAllColumns</code>, or no <code>ResultColumn</code>s at
     * all, the number of <code>ValueExpressionColumn</code>s to be exposed is
     * determined by the total number of columns in the
     * <code>querySelect</code>'s <code>fromClause</code>.
     * </p>
     * 
     * @param querySelect
     * @return List of already or newly exposed <code>ValueExpressionColumn</code>s
     */
    private static List exposeResultColumnsOfQuerySelect(QuerySelect querySelect)
    {
        List exposedColumns = new ArrayList();
        
        if (querySelect == null) { return exposedColumns; }
        
        List resultSpecList = querySelect.getSelectClause();
        
        // do we have a "SELECT * FROM ...", star query
        if (resultSpecList == null || resultSpecList.isEmpty())
        {
            //StatementHelper.logError(TableHelper.class.getName()
            //                +" implement exposeResultColumnsOfQuerySelect(QuerySelect)" 
            //                +" for star queries (\"SELECT * FROM ...\")");
            
            // Determine the number of columns implied by table, tables or joins
            // in the select's from-clause, expose as many columnExpressions with
            // name = null to make it correct for further resolving
            for (Iterator it = querySelect.getFromClause().iterator(); it.hasNext();)
            {
                TableReference tableRef = (TableReference) it.next();
                
                List tableRefCols = getEffectiveResultColumns(tableRef);
                // Not: exposedColumns.addAll(tableRefCols);
                // deep copy by value the returned exposed columnList
                //(EMF ownership doesn't allow shallow copy by reference)
                exposedColumns.addAll(copyColumnExprList(tableRefCols));                    
            }
        }
        else 
        {
            for (Iterator resultIt = resultSpecList.iterator(); resultIt.hasNext();)
            {
                
                QueryResultSpecification resultSpec = (QueryResultSpecification) resultIt.next();
                
                // do we have a "SELECT t1.*, ... FROM table AS t1, ..."
                if (resultSpec instanceof ResultTableAllColumns)
                {
                    //StatementHelper.logError(TableHelper.class.getName()
                    //        +" implement exposeResultColumnsOfQuerySelect(QuerySelect)" 
                    //        +" for table-star queries (\"SELECT t1.* ... FROM table1 AS t1...\")");
                    
                    // Determine the number of columns implied by table, tables or joins
                    // in the select's from-clause, expose as many columnExpressions with
                    // name = null to make it correct for further resolving
                    
                    ResultTableAllColumns tableAll = (ResultTableAllColumns) resultSpec;
                    TableExpression tableExpr = tableAll.getTableExpr();
                    
                    // has the tableExpr already been resolved? check 
                    // if tableAll.getTableExpr() only returns a place holder
                    // we assume a resolved tableExpression would have a exposed
                    // columnList, therefore if columnList is empty we only have
                    // a place holder table
                    if (tableExpr.getColumnList().isEmpty())
                    {
                        String tableNameOrAlias = tableAll.getName();
                        List tableExprList = getTableExpressionsInTableReferenceList(querySelect.getFromClause());
                        tableExpr = getTableExpressionFromTableExprList(tableNameOrAlias, tableExprList);
                    }
                    
                    List tableExprCols = getEffectiveResultColumns(tableExpr);
                    // Not: exposedColumns.addAll(tableExprCols);
                    // deep copy by value the returned exposed columnList
                    //(EMF ownership doesn't allow shallow copy by reference)
                    exposedColumns.addAll(copyColumnExprList(tableExprCols));                   

                }
                else if (resultSpec instanceof ResultColumn) 
                {

                
                    ResultColumn resultCol = (ResultColumn) resultSpec;
                    QueryValueExpression resultColExpr = resultCol.getValueExpr();
                    String exposedColumnName = null;
                    TableInDatabase derivedFromTableInDB = null;
                    
                    // try to get the alias name of the result column
                    if (resultCol.getName() != null)
                    {
                        exposedColumnName = resultCol.getName();
                    }
                    // if it doesn't have an alias, it might have a columnExpr with a name
                    else if (resultColExpr != null 
                                    && resultColExpr instanceof ValueExpressionColumn)
                    {
                        ValueExpressionColumn colExpr = 
                            (ValueExpressionColumn) resultColExpr;
                        //exposedColumnName = colExpr.getName();
                        // what if "select t1.col1, t2.col1 from table1 t1, table2 t2;"
                        // then we expose not only COL1 but T1_COL1 and T2_COL1 or so?
                        // what if we have column name duplications
                        // we don't do anything about duplicate column names
                        // that will be different from application to application
                        // and can't be referenced by name anyway
                        exposedColumnName = colExpr.getName();
                    }

                    
                    // if we found no name, we still must expose a new columnExpr
                    // how do we generate a column name, ...we don't that's not
                    // specified by database either, names will be generated
                    // by application and can therefore not be referenced too
//                  if (exposedColumnName == null)
//                  {
//                      exposedColumnName = "_"+String.valueOf(exposedColumns.size() + 1);
//                  }                    
                    
                    // check if column table reference was resolved already
                    if (resultColExpr != null 
                                    && resultColExpr instanceof ValueExpressionColumn)
                    {
                        ValueExpressionColumn colExpr = 
                            (ValueExpressionColumn) resultColExpr;
                        // was column resolved already?
                        if (colExpr.getDataType() == null)
                        {
                            // if not resolve column reference
                            HashSet unresolvedColumns = new HashSet();
                            unresolvedColumns.add(colExpr);
                            List tableRefList = querySelect.getFromClause();
                            resolveColumnTableReferences(unresolvedColumns, tableRefList);
                        }
                    }               
                    
                    // if the result column was derived from a TableInDB column
                    // keep this information
                    if (resultColExpr != null 
                                    && resultColExpr instanceof ValueExpressionColumn)
                    {
                        ValueExpressionColumn colExpr = 
                            (ValueExpressionColumn) resultColExpr;
                        
                        // we use the columnExpr's tableRefernce, not parentTable!!!
                        // tableRef is just an reference, the column's parentTable
                        // is always just one owner tableExpression that will have
                        // the column in its columnList, this columnList is not
                        // a random collection of columns that refer to that table
                        // but it reflects the exact list of columns in order like
                        // in the database below
                        // here: we should have a special reference to a TableInDB
                        // with name "derivedFromTableInDatabase"
                        
                        // get corresponding exposed column of the colExpr's table
                        ValueExpressionColumn colExprsTableExposedColumn =
                            getColumnExpressionForName(colExpr.getTableExpr(), colExpr.getName());
                        // and the tableInDB that it was derived from
                        if (colExprsTableExposedColumn != null
                                        && colExprsTableExposedColumn
                                                        .getTableInDatabase() != null)
                        {
                            derivedFromTableInDB = colExprsTableExposedColumn
                                            .getTableInDatabase();
                        }
                        
                    }
                    
                    // create a new ValueExpressionColumn and associate with this
                    // querySelect or find a already exposed column
                    ValueExpressionColumn exposedColumn = 
                        getOrCreateColumnExpression(exposedColumnName, querySelect);
                                      
                    // check for datatype
                    if (resultColExpr != null)
                    {
                        DataType resultColDataType = resultColExpr.getDataType();
                        //was the datatype resolved already?
                        if (resultColDataType == null) {
                            ValueExpressionHelper.resolveValueExpressionDatatypeRecursively(resultColExpr);
                            resultColDataType = resultColExpr.getDataType();
                        }
                        DataType exposedColumnDataType = 
                            ValueExpressionHelper.copyDataType(resultColDataType);
                        exposedColumn.setDataType(exposedColumnDataType); 
                    }

                    // we are setting the TableInDatabase that this column was
                    // originally derived from, we should use a reference called
                    // "derivedFromTableInDatabase"
                    exposedColumn.setTableInDatabase(derivedFromTableInDB);
                            
                    exposedColumns.add(exposedColumn);
                }
            }
        }
        
        // all the previously associated columns (if previously exposed)
        // are in exposedColumns @see #getOrCreateColumnExpression()
        // so clear the old columnList to not duplicate and preserve ordering
        querySelect.getColumnList().clear();
        querySelect.getColumnList().addAll(exposedColumns);
        
        return exposedColumns;
    }

    /**
     * Adds <code>ValueExpressionColumn</code>s to the given
     * <code>queryValues</code> for each of its values in the first
     * <code>ValuesRow</code>.
     * @see #exposeEffectiveResultColumns(QueryExpressionBody)
     * 
     * That methods is used for resolving purposes and useful in case the given
     * <code>queryValues</code> is used as a nested query as a
     * <code>TableReference</code> within another <code>QuerySelect</code> or as
     * part of a <code>QueryCombined</code>.
     * 
     * @param queryValues
     * @return List of already or newly exposed <code>ValueExpressionColumn</code>s
     */
    private static List exposeResultColumnsOfQueryValues(QueryValues queryValues)
    {
        List exposedColumns = new ArrayList();

        if (queryValues == null || queryValues.getValuesRowList().isEmpty()) {
            return exposedColumns;
        }
        
        ValuesRow firstRow = (ValuesRow) queryValues.getValuesRowList().get(0);
        List firstRowValues = firstRow.getExprList();
        
        for (int i = 0; i < firstRowValues.size(); i++)
        {
            QueryValueExpression valueExpr = 
                (QueryValueExpression) firstRowValues.get(i);
            
            // figure out the naming of unnamed columns in QueryValues
            // if we found no name, we still must expose a new columnExpr
            // how do we generate a column name, ...we don't that's not
            // specified by database either, names will be generated
            // by application and can therefore not be referenced too
//            ValueExpressionColumn exposedCol = 
//                StatementHelper.createColumnExpression("_"+(i+1));
            ValueExpressionColumn exposedCol = 
                StatementHelper.createColumnExpression(null);
            
            // was the DataType resolved already?
            if (valueExpr.getDataType() == null)
            {
                ValueExpressionHelper.resolveValueExpressionDatatypeRecursively(valueExpr);
            }
            ValueExpressionHelper.copyDataType(valueExpr, exposedCol);
            
            exposedColumns.add(exposedCol);
        }
        queryValues.getColumnList().clear();
        queryValues.getColumnList().addAll(exposedColumns);
        
        return exposedColumns;
    }

    /**
     * Returns the one <code>TableExpression</code> from the given
     * <code>tableExprList</code>, that has a
     * <code>ValueExpressionColumn</code> in its <code>columnList</code>
     * whose name matches the given <code>columnName</code>. If there is more
     * than one <code>TableExpression</code> found with a matching column,
     * <code>null</code> will be returned.
     * @param tableExprList
     *            the List of tables to check for the specified column
     * @param columnName
     *            the name of the column that a <code>TableExpression</code>
     *            is searched for
     * 
     * @return the <code>TableExpression</code> that has a column with the
     *         given <code>columnName</code>,<code>null</code> if more
     *         than one or no such table was found in the given
     *         <code>tableExprList</code>
     */
    private static TableExpression findTableExpressionForColumnName(List tableExprList, String columnName)
    {
        return findTableExpressionForColumnName(tableExprList, columnName, false);
    }

    /**
     * Returns the one <code>TableExpression</code> from the given
     * <code>tableExprList</code>, that has a
     * <code>ValueExpressionColumn</code> in its <code>columnList</code>
     * whose name matches the given <code>columnName</code>. If there is more
     * than one <code>TableExpression</code> found with a matching column,
     * <code>null</code> will be returned.
     * 
     * @param tableExprList
     *            the List of tables to check for the specified column
     * @param columnName
     *            the name of the column that a <code>TableExpression</code>
     *            is searched for
     * @param checkDisconnected
     *            if <code>true</code> a <code>TableExpression</code> will
     *            be returned, if it is referenced by another column with the
     *            same name as the given <code>columnName</code>, that is
     *            useful if the given <code>tableExprList</code>'s
     *            <code>TableExpression</code> s have not been populated with
     *            column in their exposed <code>columnList</code> (if no
     *            database was connected this information can be missing)
     * 
     * @return the <code>TableExpression</code> that has a column with the
     *         given <code>columnName</code>,<code>null</code> if more
     *         than one or no such table was found in the given
     *         <code>tableExprList</code>
     */
    private static TableExpression findTableExpressionForColumnName(List tableExprList, String columnName, boolean checkDisconnected)
    {
        TableExpression tableFound = null;
        
        List tablesReferencedByColumn = null;
        
        if (checkDisconnected)
        {
            tablesReferencedByColumn = new ArrayList();
        }

        for (Iterator it = tableExprList.iterator(); it.hasNext();)
        {
            TableExpression tableExpr = (TableExpression) it.next();
            
            if (checkDisconnected
                            && isTableReferencedByColumnWithName(tableExpr,
                                            columnName))
            {
                tablesReferencedByColumn.add(tableExpr);
            }
            
            ValueExpressionColumn columnFound = 
                getColumnExpressionForName(tableExpr, columnName);
            
            if (columnFound != null)
            {
                // if we did not find one other table already, we found it
                // if we found one already, but it was the same real table like in
                // select col1 from table1, table1; we keep the one previously found
                // but if we found one already and the one we found now are not
                // representing the same real table, then delete the previously found
                // and break
                if (tableFound == null)
                {
                    tableFound = tableExpr;
                }
                else 
                {
                    String tableFoundName = tableFound.getName();
                    String tableExprName = tableExpr.getName();
                    if (StatementHelper.equalSQLIdentifiers(tableFoundName, tableExprName) )
                    {
                        // we have the same column name in two different tables
                        // that means we can't tell what table the column is of
                        tableFound = null;
                        break;
                    }
                    else
                    {
                        // check if we have the same table in the same schema
                        // twice, in that case we keep the first table as
                        // the one found
                        // select col1 from table1, table1; or
                        // select col1 from s1.table1 t1, s1.table1 t2;
                        // NOT select col1 from s1.table1, s2.table1;
                        
                        String tableExprSchema = getSchemaName(tableExpr);
                        String tableFoundSchema = getSchemaName(tableFound);
                        String defaultSchema = getDefaultSchemaName(tableExpr);
                        
                        if (tableExprSchema == null)
                        {
                            tableExprSchema = defaultSchema;
                        }
                        if (tableFoundSchema == null)
                        {
                            tableFoundSchema = defaultSchema;
                        }
                        
                        if (StatementHelper.equalSQLIdentifiers(tableExprSchema,tableFoundSchema))
                        {
                            // we have the same column name in two tables with
                            // the same name but in different schemas
                            // that means we can't tell what table the column is of
                            tableFound = null;
                            break;
                        }
                        
                    }
                }
            }
        }
        
        // if we didn't find a table maybe the reason is that all the
        // TableInDatabases were not populated because no database is connected
        // in that case we want to be smart enough to find out about other
        // columns referring to the table
        // e.g. "select col1, col2 from tbl1 JOIN tbl2 ON tbl1.col1 = tbl2.col2"
        // we know that "col1" belongs to "tbl1" and "col2" to "tbl2", right?
        if (tableFound == null && checkDisconnected)
        {
            if (tablesReferencedByColumn != null
                            && tablesReferencedByColumn.size() == 1)
            {
                tableFound = (TableExpression) tablesReferencedByColumn.get(0);
            }
        }
        
        return tableFound;
    }

    /**
       * Finds in the given List of <code>TableExpression</code> s the one
       * <code>TableExpression</code> with a name that matches the given
       * <code>tableName</code> and, if given a <code>schemaName</code> is
       * given, a <code>Schema</code> that name matches the
       * <code>schemaName</code>. If no <code>schemaName</code> is given, only
       * a <code>TableExpression</code> without a schema name or with the
       * current default <code>Schema</code> can match.
       * (see {@link SQLQueryObject#getSourceInfo()},
       * {@link SQLQuerySourceInfo#getSqlFormat()},
       * {@link SQLQuerySourceFormat#getOmitSchema()})
       * <b>Note:</b> a table can only match if it has no alias, which would cascade
       * the table name and schema.
       *
       * @param schemaName
       *          optional the schema name of the table to find
       * @param tableName
       *          the name of the table to find
       * @param tableExprList
       *          the list of <code>TableExpression</code> s to search
       * @param defaultSchemaName
       * @return the matching <code>TableExpression</code> or <code>null</code>
       */
      private static TableExpression findTableExpressionInTableExpressionList(
                                                                               String schemaName,
                                                                               String tableName,
                                                                               List tableExprList,
                                                                               String defaultSchemaName)
        {
            TableExpression foundTableExpr = null;
            SQLQuerySourceFormat sourceFormat = null;
    
            if (tableName != null && tableExprList != null)
            {
    
                for (Iterator tableIt = tableExprList.iterator(); tableIt.hasNext();)
                {
                    TableExpression tableExpr = (TableExpression) tableIt.next();
                    String tableExprName = tableExpr.getName();
    
                    // get the omitSchema from the table
                    if (tableExpr.getSourceInfo() != null
                                    && tableExpr.getSourceInfo().getSqlFormat() != null)
                    {
                        sourceFormat = tableExpr.getSourceInfo().getSqlFormat();
                        if (sourceFormat.getOmitSchema() != null
                                        && sourceFormat.getOmitSchema().length() > 0)
                        {
                            defaultSchemaName = sourceFormat.getOmitSchema();
                        }
                    }
    
                    if (StatementHelper.equalSQLIdentifiers(tableName, tableExprName))
                    {
    
                        // attention: no schemaName given can match any schema name of
                        // the tables here (doesn't mean the defaultSchema as the column
                        // trying to be resolved doesn't have to be qualified with the
                        // schema), but if the schemaName was given it is binding and
                        // we assume the given
    
                        if (schemaName != null)
                        {
                            String tableExprSchemaName = getSchemaName(tableExpr);
    
                            if (tableExprSchemaName == null)
                            {
                                tableExprSchemaName = defaultSchemaName;
                            }
    
                            // How to generate the SQL for column?
                            // First find out if column is in more than one table.
                            // If the column is unique, don't qualify. 
                            // If the column's table has an alias, use the alias as the 
                            // qualifier, ie t1.col1
                            // If there are two or more tables with the same name,
                            // qualify with the schema if that's different.
    
                            // table alias must be null
                            if (StatementHelper.equalSQLIdentifiers(schemaName, tableExprSchemaName)
                             && tableExpr.getTableCorrelation() == null)
                            {
                                foundTableExpr = tableExpr;
                                break;
                            }
                        }
                        else
                        // no schema to compare
                        {
                            // table alias must be null
                            if (tableExpr.getTableCorrelation() == null)
                            {
                                // give priority to previously found
                                //if (foundTableExpr == null)
                                //    foundTableExpr = tableExpr;
                                
                                // the schema name has to match exactly or has to be
                                // absent
                                String tableSchema = getSchemaName(tableExpr);
                                if (tableSchema == null
                                 || StatementHelper.equalSQLIdentifiers(tableSchema, defaultSchemaName))
                                {
                                    // we have the table in the default schema or in
                                    // a schema not specified (implicit default)
                                    // that's the one, don't look further
                                    foundTableExpr = tableExpr;
                                    break;
                                }
    
                            }
    
                        }
    
                    }
                }
            }
    
            return foundTableExpr;
        }

    
    /**
     * Returns the default <code>Schema</code> valid for the context of the
     * <code>QueryStatement</code> that the given <code>queryObject</code> is
     * part of.
     * 
     * @param queryObject
     * @return the default <code>Schema</code> name that might be omitted
     */
    private static String getDefaultSchemaName(SQLQueryObject queryObject)
    {
        String defaultSchema = null;
        
        if (queryObject.getSourceInfo() != null &&
                        queryObject.getSourceInfo().getSqlFormat() != null)
        {
            defaultSchema =
                queryObject.getSourceInfo().getSqlFormat().getOmitSchema();
        }
        
        return defaultSchema;
    }
    
    /**
     * Tries to determines whether or not we are operating in off-line mode by examining the
     * given list of table expression objects. Off-line mode means that there isn't an instance
     * of the SQL model reachable from the table objects.  (That is, there is no database 
     * connection available.)
     *  
     * @param aTableExprList the table expression list to examine
     * @return true when the tables appear to be off-line, otherwise false
     */
    private static boolean getIsOffLine(List aTableExprList) {
        boolean isOffLine = true;
        
        if (aTableExprList != null && aTableExprList.size() > 0) {
            // Look for a TableInDatabase in the list.  If find one, see if the Table
            // object attached to it is (indirectly) attached to a Database object.
            // If so, we assume we are on-line.
            Iterator tableExprListIter = aTableExprList.iterator();
            while (tableExprListIter.hasNext() && isOffLine == true) {
                TableExpression tableExpr = (TableExpression) tableExprListIter.next();
                if (tableExpr instanceof TableInDatabase) {
                    TableInDatabase tableInDB = (TableInDatabase) tableExpr;
                    Table table = tableInDB.getDatabaseTable();
                    if (table != null) {
                        Schema schema = table.getSchema();
                        if (schema != null) {
                            if (schema.getDatabase() != null 
                              || (schema.getCatalog() != null && schema.getCatalog().getDatabase() != null)
                               ) {
                                isOffLine = false;
                            }
                        }
                    }
                }
            }
        }
        
        return isOffLine;
    }

    /**
     * Gets whether or not the given table reference is a query select that contains the given column.
     * 
     * @param tblRef the table reference to check
     * @param col the column to check
     * @return true when the table reference is a query select that contains the column otherwise false
     */
    private static boolean getIsQueryParentOfColumn(TableReference tblRef, ValueExpressionColumn col) {
        boolean isQueryParent = false;
        
        if (tblRef instanceof QuerySelect) {
            EObject colContainerObj = col.eContainer();
            while (colContainerObj != null && !(colContainerObj instanceof QuerySelect)) {
                colContainerObj = colContainerObj.eContainer();
            } 
            
            if (colContainerObj == tblRef) {
                isQueryParent = true;
            }
        }
        
        return isQueryParent;
    }
    
    /**
     * Convenience method:
     * Returns the Schema name of the given <code>TableExpression</code> if the
     * given <code>tableExpr</code> is of type {@link TableInDatabase}, or
     * <code>null</code> if the given <code>tableExpr</code> has no
     * <code>Schema</code> (or no reference to a real <code>Table</code>)
     * or the given <code>tableExpr</code> was not of type
     * <code>TableInDatabase</code>.
     * 
     * @param tableExpr
     * @return the <code>tableExpr</code>s <code>Schema</code> name or <code>null</code>
     */
    private static String getSchemaName(TableExpression tableExpr)
    {
        String schemaName = null;

        if (tableExpr instanceof TableInDatabase)
        {
            TableInDatabase tableInDB = (TableInDatabase) tableExpr;
            Table dbTable = tableInDB.getDatabaseTable();

            if (dbTable != null)
            {
                Schema schema = dbTable.getSchema();
                if (schema != null)
                {
                    schemaName = schema.getName();
                }
            }
        }
        return schemaName;
    }

} // End Class
