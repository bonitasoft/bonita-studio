/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
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
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;


/**
 * This is a helper class that provides methods that assist in
 * creating and manipulating SQL joins.
 */
public class JoinHelper {

    public static final String copyright = "(c) Copyright IBM Corporation 2000, 2004.";

    public static final String GENERIC_CHARACTER = "Character";
    public static final String GENERIC_INTEGER = "Integer";
    public static final String GENERIC_DECIMAL = "Decimal";
    public static final String GENERIC_BINARY = "Binary";
    public static final String GENERIC_DATE = "Date";
    public static final String GENERIC_TIME = "Time";
    public static final String GENERIC_TIMESTAMP = "Timestamp";
    public static final String GENERIC_BLOB = "BLOB";
    public static final String GENERIC_OTHER = "Other";

    public static final int JOIN_COL_TYPE_MISMATCH = -1;
    public static final int JOIN_COL_USED = -2;
    public static final int JOIN_SAME_TABLE = -3;
    public static final int JOIN_VALID = 0;

    public static final String LEFT = "left_col_used";
    public static final String RIGHT = "right_col_used";
    public static final String BOTH = "both_col_used";
    public static final String NONE = "neither_col_used";

    // private static SearchConditionHelper searchConHelper = null;

    /**
     * Creates a new joined table or join condition containing the given 
     * join source and target objects, and updates the given FROM clause 
     * list. If a join does not already exist between the two tables, a
     * new joined table object is created and added to the FROM clause.  If 
     * the given tables are already joined, a new joined table is not created, 
     * but a new join condition is added to the ON clause of the join.
     * The new or modified joined table is returned.
     * 
     * @param fromClause the list of objects (tables and joins) in the FROM clause
     * @param sourceTable the source (left side) table of the join
     * @param targetTable the target (right side) table of the join
     * @param sourceColumn the source column expression of the join
     * @param targetColumn the target column expression of the join
     * @param joinType the type of join (inner, left outer, etc.)
     * @return the new or modified join object
     */
    public static TableJoined addJoin(List fromClause, TableExpression sourceTable, TableExpression targetTable, ValueExpressionColumn sourceColumn,
            ValueExpressionColumn targetColumn, int joinType) {
        TableJoined joinedTable = null;

        // Get any join that already contains the source and target tables.
        // If a table is not already contained in a join, the table itself
        // is returned.
        TableReference sourceContainer = findOutermostContainingJoin(fromClause, sourceTable);
        TableReference targetContainer = findOutermostContainingJoin(fromClause, targetTable);

        // If the two tables are contained in distinct join trees, 
        // or if one or both of them are not part of any join, then create
        // a joined table object with the source and target containers as its left 
        // and right sides.  Remove the source and target containers from 
        // the FROM clause list and add the newly created joined table object to it.
        if (sourceContainer != targetContainer) {
            joinedTable = addJoinedTable(fromClause, sourceContainer, targetContainer, joinType);
        }
        else {
            // The source and target are already contained in the same join tree,
            // so find the smallest sub tree containing the two objects.
            joinedTable = findClosestContainingJoin(sourceTable, targetTable);
        }

        // Add the join condition to the joined table object.
        if (joinedTable != null) {
            QuerySearchCondition searchCon = joinedTable.getJoinCondition();
            searchCon = buildSearchCondition(searchCon, sourceColumn, targetColumn, "=");
            joinedTable.setJoinCondition(searchCon);
        }

        return joinedTable;
    }

    /**
     * Creates a new joined table containing the given join source and
     * target objects, and adds it to the given FROM clause list. The new
     * join table is returned.  If the source and target are already part 
     * of the same joined table in the FROM clause list, then a new joined
     * table is not created, and the existing joined table is returned.
     * 
     * @param fromClause the list of objects (tables and joins) in the FROM clause
     * @param joinSource the source (left side) of the join
     * @param joinTarget the target (right side) of the join
     * @param joinType the type of join (inner, left outer, etc.)
     * @return the joined table containing the join source and target objects
     */
    public static TableJoined addJoinedTable(List fromClause, TableReference joinSource, TableReference joinTarget, int joinType) {
        // Create a new joined tables object and set its content.
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        TableJoined joinedTable = factory.createTableJoined();
        joinedTable.setTableRefLeft(joinSource);
        joinedTable.setTableRefRight(joinTarget);
        joinedTable.setJoinOperator(TableJoinedOperator.get(joinType));

        // Remove the current source and target objects from the FROM clause
        // list and replace them with the joined table we just created.
        fromClause.remove(joinSource);
        fromClause.remove(joinTarget);
        fromClause.add(joinedTable);

        return joinedTable;
    }

    /**
     * Creates a PredicateBasic object using the given expressions and operator.
     * 
     * @param leftExpr the left side expression
     * @param rightExpr the right side expression
     * @param oper the operator
     * @return the new PredicateBasic object
     */
    public static PredicateBasic buildPredicateBasic(QueryValueExpression leftExpr, QueryValueExpression rightExpr, String oper) {
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        PredicateBasic pred = factory.createPredicateBasic();
        pred.setLeftValueExpr(leftExpr);
        pred.setRightValueExpr(rightExpr);
        String operLiteral = getComparisonLiteralFromSymbol(oper);
        PredicateComparisonOperator operEnum = PredicateComparisonOperator.get(operLiteral);
        pred.setComparisonOperator(operEnum);
        return pred;
    }

    /**
     * Creates a new predicate and adds it to the given search condition.
     * 
     * @param currentSearchCon the existing search condition or null 
     * @param leftExpr the left side expression of the new predicate
     * @param rightExpr the right side expression of the new predicate
     * @param oper the operator string (eg: "=" )  
     * @return the new search condition object
     */
    public static QuerySearchCondition buildSearchCondition(QuerySearchCondition currentSearchCon, QueryValueExpression leftExpr,
            QueryValueExpression rightExpr, String oper) {
        QuerySearchCondition newCondition = null;
        PredicateBasic pred = buildPredicateBasic(leftExpr, rightExpr, oper);
        if (currentSearchCon == null) {
            newCondition = pred;
        }
        else {
            SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
            SearchConditionCombined combined = factory.createSearchConditionCombined();
            combined.setLeftCondition(currentSearchCon);
            combined.setRightCondition(pred);
            combined.setCombinedOperator(SearchConditionCombinedOperator.get("AND"));
            newCondition = combined;
        }
    
        return newCondition;
    }

    /**
     * Checks whether or not a join between the given join source
     * and target objects is valid.  Returns one of the following
     * status codes:
     * <dl>
     * <dt>JOIN_VALID
     * <dd>OK to join
     * <dt>JOIN_COL_TYPE_MISMATCH
     * <dd>the source and target have incompatible datatypes
     * <dt>JOIN_SAME_TABLE
     * <dd>the source and column are in the same table
     * <dt>JOIN_COL_USED
     * <dd>one of the columns is already used in a join between the
     * same two tables
     * </dl>
     * 
     * @param fromClause the list of objects (tables and joins) in the FROM clause
     * @param sourceTable the source (left side) table of the join
     * @param targetTable the target (right side) table of the join
     * @param sourceColumn the source column of the join
     * @param targetColumn the target column of the join
     * @param isMove true when a join is being moved, false when a join
     * is being created
     * @return a result code 
     */
    public static int checkJoin(List fromClause, TableExpression sourceTable, TableExpression targetTable, ValueExpressionColumn sourceColumn,
            ValueExpressionColumn targetColumn, boolean isMove) {
        int resultCode = JOIN_VALID;

        if (checkJoinType(sourceColumn, targetColumn) == false) {
            resultCode = JOIN_COL_TYPE_MISMATCH;
        }
        else if (sourceColumn == targetColumn) {
            resultCode = JOIN_VALID;
        }
        else if (sourceTable == targetTable) {
            resultCode = JOIN_SAME_TABLE;
        }
        else {
            resultCode = JOIN_VALID;
            //      resultCode = checkJoinColumnUsage( fromClause, sourceTable, targetTable, sourceColumn, targetColumn, isMove);
        }

        return resultCode;
    }

    /**
     * Determins whether or not either of the given columns are already 
     * referenced in a join between the given tables.
     * 
     * @param fromClause the list of objects (tables and joins) in the FROM clause
     * @param sourceTable the source (left side) table of the join
     * @param targetTable the target (right side) table of the join
     * @param sourceColumn the source column of the join
     * @param targetColumn the target column of the join
     * @param isMove true when a join is being moved, false when a join
     * is being created
     */
    /*   
     public static int checkJoinColumnUsage( List fromClause, SQLFromTable sourceTable, SQLFromTable targetTable, RDBColumn sourceColumn, RDBColumn targetColumn, boolean isMove )
     {
     int resultCode = JOIN_VALID;
     
     // Check each join in the from clause list.
     //    Iterator fromClauseIter = fromClause.iterator();
     //    while (fromClauseIter.hasNext())
     //    {
     //      // Get the next table or join in the list and check it if it's
     //      // a join.
     //      SQLFromClauseContent tableRef = (SQLFromClauseContent) fromClauseIter.next();
     //      if (tableRef instanceof SQLJoinTable)
     //      {
     //        SQLJoinTable joinedTable = (SQLJoinTable) tableRef;
     //        SQLSearchCondition cond = joinedTable.getOnClause().getCondition();
     //        SQLFromClauseContent leftContent = joinedTable.getLeftContent();
     //        SQLFromClauseContent rightContent = joinedTable.getRightContent();
     //        
     //        if (leftContent instanceof SQLFromTable && rightContent instanceof SQLFromTable)
     //        {
     //        	if (leftContent == sourceTable && rightContent == targetTable) 
     //        	{
     //            String condResultCode = areColumnsUsedInCondition( cond, sourceColumn, targetColumn );
     //        		if (condResultCode != NONE)
     //        		{
     //        			if (!(isMove && (condResultCode != LEFT || condResultCode != BOTH )))
     //        			{
     //        				resultCode = JOIN_COL_USED;  
     //        			} 
     //        		}
     //        	}
     //        	else if (rightContent == sourceTable && leftContent == targetTable)
     //        	{
     //        		String condResultCode = areColumnsUsedInCondition( cond, targetColumn, sourceColumn );
     //        		if (condResultCode != NONE)
     //        		{
     //        			if (!(isMove && (condResultCode != RIGHT || condResultCode != BOTH )))
     //        			{
     //        				resultCode = JOIN_COL_USED;
     //        			}
     //        		}
     //        	}
     //        }
     //      }
     //    }
     
     return resultCode;
     }

     */
    
    /**
     * Determines if source and target columns are of compatible type
     * for joining.
     * @param sourceColumn the source column to check
     * @param targetColumn the target column to check
     * @return true when the columns are of join compatible, otherwise false
     */
    public static boolean checkJoinType(ValueExpressionColumn sourceColumn, ValueExpressionColumn targetColumn) {
        boolean areCompatible = false;

        DataType sourceDataType = sourceColumn.getDataType();
        String sourceGenericType = getGenericType(sourceDataType);

        DataType targetDataType = targetColumn.getDataType();
        String targetGenericType = getGenericType(targetDataType);

        if (sourceGenericType.equals(targetGenericType)) {
            areCompatible = true;
        }

        return areCompatible;
    }
    
    /** 
     * Checks to see if the given SQLSearchCondition (ON clause) contains 
     * the given table.
     * 
     * @param condition the condition to search
     * @param table the table to search for
     * @return true if the condition contains the table, otherwise false
     */
    public static boolean conditionContainsTable(QuerySearchCondition condition, TableExpression table) {
        boolean tableFound = false;
        List predList = findConditionsContainingTable(condition, table);
        if (predList.size() > 0) {
            tableFound = true;
        }

        return tableFound;
    }

    /**
     * Finds and returns the closest enclosing join containing both the 
     * given source and target tables, working from the leafs of the join 
     * tree to the root.  Returns null if the tables are not contained in 
     * the same join tree. 
     * 
     * @param joinSource the source (left side) of a join
     * @param joinTarget the target (right side) of a join
     * @return closest enclosing join or null if the tables are not part of the same join 
     */
    public static TableJoined findClosestContainingJoin(TableExpression joinSource, TableExpression joinTarget) {
        TableJoined foundJoin = null;
        TableReference tempSource = joinSource;
        TableJoined container = null;

        while (foundJoin == null && tempSource != null) {
            container = tempSource.getTableJoinedLeft();
            if (container != null) {
                List tableExprList = getTablesInJoin(container);
                if (tableExprList.contains(joinTarget)) {
                    foundJoin = container;
                }
            }
            else {
                container = tempSource.getTableJoinedRight();
                if (container != null) {
                    List tableExprList = getTablesInJoin(container);
                    if (tableExprList.contains(joinTarget)) {
                        foundJoin = container;
                    }
                }
            }
            tempSource = container;
        }

        return foundJoin;
    }

    /**
     * Finds and returns the individual conditions (predicates) in the 
     * given QuerySearchCondition (ON clause) that contain references to 
     * the given table.
     * 
     * @param cond the QuerySearchCondition to search
     * @param table the TableExpression to use to search for predicates
     * @return a list of PredicateBasic objects containing references to the table
     */
    public static List findConditionsContainingTable(QuerySearchCondition cond, TableExpression table) {
        List predList = new ArrayList();

        if (cond instanceof PredicateBasic) {
            PredicateBasic predicate = (PredicateBasic) cond;
            QueryValueExpression leftExpr = predicate.getLeftValueExpr();

            if (leftExpr instanceof ValueExpressionColumn) {
                TableExpression tableExprInPred = ((ValueExpressionColumn) leftExpr).getTableExpr();
                if (tableExprInPred.equals(table)) {
                    predList.add(predicate);
                }
            }

            if (predList.size() == 0) {
                QueryValueExpression rightExpr = predicate.getRightValueExpr();
                if (rightExpr instanceof ValueExpressionColumn) {
                    TableExpression tableExprInPred = ((ValueExpressionColumn) rightExpr).getTableExpr();
                    if (tableExprInPred.equals(table)) {
                        predList.add(predicate);
                    }
                }
            }
        }
        else if (cond instanceof SearchConditionCombined) {
            SearchConditionCombined condGroup = (SearchConditionCombined) cond;
            QuerySearchCondition leftCond = condGroup.getLeftCondition();
            if (leftCond != null) {
                predList.addAll(findConditionsContainingTable(leftCond, table));
            }

            QuerySearchCondition rightCond = condGroup.getRightCondition();
            if (rightCond != null) {
                predList.addAll(findConditionsContainingTable(rightCond, table));
            }
        }

        return predList;
    }

    /**
     * Searches upwards in the join tree to see if the given table exists in any of 
     * the conditions in the joins. 
     * 
     * @param table the table to be searched for
     * @param join the join at which the search starts
     * @return the list of joins whose onclause condition contains the table, 
     * or an empty list if no such join is found  
     */
    public static List findJoinsWithTableInCondition(TableExpression table, TableJoined join) {
        List joinList = new ArrayList();
        TableJoined currentJoin = join;

        // Iterate our way up the join tree to the root, checking all the on clauses
        // for refernces to the target table.
        while (currentJoin != null) {
            // Search the condition of the current join.
            QuerySearchCondition onClause = currentJoin.getJoinCondition();
            if (conditionContainsTable(onClause, table) == true) {
                joinList.add(currentJoin);
            }

            // Get the next join one level up the tree, to be searched 
            // in the next iteration.
            TableJoined tempParent = currentJoin.getTableJoinedLeft();
            if (tempParent != null) {
                currentJoin = tempParent;
            }
            else {
                currentJoin = currentJoin.getTableJoinedRight();
            }
        }

        return joinList;
    }

    /**
     * Finds and returns the outermost table reference in the From clause that 
     * contains the given table.  That might be a joined table reference,
     * or the given table itself if it is not contained in any join.
     * 
     * @param fromClause the list of objects (tables and joins) in the FROM clause
     * @param targetTable the table reference to be looked for
     * @return the joined table containing the table, or the table itself
     *         if it is not part of a join
     */
    public static TableReference findOutermostContainingJoin(List fromClause, TableExpression targetTable) {
        TableReference foundTableRef = null;
        Iterator fromClauseIter = fromClause.iterator();
        while (fromClauseIter.hasNext() && foundTableRef == null) {
            TableReference tempTableRef = (TableReference) fromClauseIter.next();
            if (targetTable == tempTableRef) {
                foundTableRef = tempTableRef;
            }
            else if (tempTableRef instanceof TableNested) {
                TableNested tableNested = (TableNested) tempTableRef;
                List tableExprList = JoinHelper.getTablesInNestedTable(tableNested);
                if (tableExprList.contains(targetTable)) {
                    foundTableRef = tableNested;
                }
            }
            else if (tempTableRef instanceof TableJoined) {
                TableJoined joinedTable = (TableJoined) tempTableRef;
                List tableExprList = getTablesInJoin(joinedTable);
                if (tableExprList.contains(targetTable)) {
                    foundTableRef = joinedTable;
                }
            }
        }

        return foundTableRef;
    }

    /**
     * Returns a list of TableJoined objects of which the given TableExpression is the left side.
     * Traverses up the join tree finding the joins.
     * 
     * @param tableExpr the TableExpression for which to find the joins 
     */
    public static List getLeftJoinsForTable(TableExpression tableExpr) {
        List list = new ArrayList();
        boolean isLeft;
        TableJoined tempJoinTable = null;
        TableReference joinTable = tableExpr.getTableJoinedLeft();
        if (joinTable != null) {
            isLeft = true;
        }
        else {
            joinTable = tableExpr.getTableJoinedRight();
            isLeft = false;
        }
        
        // if this join is within a nest, get the join to be processed is the one
        // for which the nest a left or right child
        /*        TableNested nest = joinTable.getNest();
         if (nest != null) {
         TableJoined parentJoin = nest.getTableJoinedLeft();
         if (parentJoin != null) {
         isLeft = true;
         }
         else {
         parentJoin = nest.getTableJoinedRight();
         isLeft = false;
         }
         joinTable = parentJoin;
         }
         */
        while (joinTable != null) {
            if (isLeft == true) {
                list.add(joinTable);
            }
            // if this join is within a nest, the join to be processed is the one
            // for which the nest is a left or right child
            TableNested nest = joinTable.getNest();
            if (nest != null) {
                joinTable = nest;
            }
            tempJoinTable = joinTable.getTableJoinedLeft();
            if (tempJoinTable != null) {
                isLeft = true;
                joinTable = tempJoinTable;
            }
            else {
                joinTable = joinTable.getTableJoinedRight();
                isLeft = false;
            }
        }
        
        return list;
    }

    /**
     * Returns the table "nested" in the given table, if any.  If there is no
     * nested table, then the given table is returned.  Nested tables are used to
     * represent parenthesized table expressions explicitly in places where the 
     * parens are implied. For example, it's possible to say 
     * "SELECT * FROM (TABLE1 JOIN TABLE2 ON A = B)".
     *  
     * @param tableRef the table reference from which the nested table is wanted
     * @return the nested table reference
     */
    public static TableReference getNestedTable(TableReference tableRef) {
        TableReference nestedTable = tableRef;
        if (tableRef instanceof TableNested) {
            nestedTable = ((TableNested) tableRef).getNestedTableRef();
        }
        return nestedTable;
    }

    /**
     * Returns a list of TableJoined objects of which the given TableExpression is the right side.
     * Traverses up the join tree finding the joins.
     * 
     * @param tableExpr the TableExpression for which to find the joins 
     */
    public static List getRightJoinsForTable(TableExpression tableExpr) {
        List list = new ArrayList();
        boolean isRight;
        TableJoined tempJoinTable = null;
        TableReference joinTable = tableExpr.getTableJoinedRight();
        if (joinTable != null) {
            isRight = true;
        }
        else {
            joinTable = tableExpr.getTableJoinedLeft();
            isRight = false;
        }

 /*       // if this join is within a nest, get the join to be processed is the one
        // for which the nest a left or right child
        TableNested nest = joinTable.getNest();
        if (nest != null) {
            TableJoined parentJoin = nest.getTableJoinedRight();
            if (parentJoin != null) {
                isRight = true;
            }
            else {
                parentJoin = nest.getTableJoinedLeft();
                isRight = false;
            }
            joinTable = parentJoin;
        }*/

        
        while (joinTable != null) {
            if (isRight == true) {
                list.add(joinTable);
            }

            // if this join is within a nest, the join to be processed is the one
            // for which the nest is a left or right child
            TableNested nest = joinTable.getNest();
            if (nest != null) {
                joinTable = nest;
            }
            
            tempJoinTable = joinTable.getTableJoinedRight();
            if (tempJoinTable != null) {
                isRight = true;
                joinTable = tempJoinTable;
            }
            else {
                joinTable = joinTable.getTableJoinedLeft();
                isRight = false;
            }
        }

        return list;
    }

    /** 
     * Returns a string indicating the "generic" type for the given
     * datatype object.  All string types, for example, map to
     * "GENERIC_CHARACTER".  See the class static constants for the
     * list of generic types.
     * 
     * @param datatype the datatype for which a generic type is needed
     */
    public static String getGenericType(Object datatype) {
        //TODO
        return GENERIC_CHARACTER;
        /*    if (datatype instanceof SQLCharacterStringType ||
         datatype instanceof SQLNationalCharacterStringType)
         { 
         return GENERIC_CHARACTER;
         }
         
         //generic_binary
         else if (datatype instanceof SQLBoolean ||
         datatype instanceof SQLBitString)
         {
         return GENERIC_BINARY;
         }

         //generic_blob
         else if (datatype instanceof SQLBinaryLargeObject)
         {
         return GENERIC_BLOB;
         }

         //generic_decimal
         else if (datatype instanceof SQLNumeric ||
         datatype instanceof SQLFloat ||
         datatype instanceof SQLApproximateNumeric) 
         {
         return GENERIC_DECIMAL;
         }

         // generic_integer
         else if (datatype instanceof SQLExactNumeric)
         {
         return GENERIC_INTEGER;
         }

         //generic_time
         else if (datatype instanceof SQLTime)
         {
         return GENERIC_TIME;
         }

         //generic_timestamp
         else if (datatype instanceof SQLTimestamp)
         {
         return GENERIC_TIMESTAMP;
         }
         
         //generic_date
         else if (datatype instanceof SQLDate)
         {
         return GENERIC_DATE;
         }

         //generic_other
         else 
         {
         return GENERIC_OTHER;
         }
         */
    }

    /**
     * Gets a list of TableExpression objects embedded inside the given join object.
     * 
     * @param join the join from which the tables are to be obtained
     * @return the list of TableExpression objects in the given join.
     */
    public static List getTablesInJoin(TableJoined join) {
        List list = new ArrayList();
        TableReference leftContent = join.getTableRefLeft();
        TableReference rightContent = join.getTableRefRight();

        // process the right side of the join   
        if (leftContent instanceof TableExpression) {
            list.add(leftContent);
        }
        else if (leftContent instanceof TableNested) {
            TableNested tableNested = (TableNested) leftContent;
            list.addAll(JoinHelper.getTablesInNestedTable(tableNested));
        }
        else if (leftContent instanceof TableJoined) {
            TableJoined tempJoin = (TableJoined) leftContent;
            list.addAll(JoinHelper.getTablesInJoin(tempJoin));
        }

        // process the right side of the join
        if (rightContent instanceof TableExpression) {
            list.add(rightContent);
        }
        else if (rightContent instanceof TableNested) {
            TableNested tableNested = (TableNested) rightContent;
            list.addAll(JoinHelper.getTablesInNestedTable(tableNested));
        }
        else if (rightContent instanceof TableJoined) {
            TableJoined tempJoin = (TableJoined) rightContent;
            list.addAll(JoinHelper.getTablesInJoin(tempJoin));
        }
        return list;
    }

    /**
     * Returns a list of TableExpressions contained in the given TableNested object.
     * 
     * @param tableNested the TableNested object from which the TableExpressions are to be obtained
     * @return the list of TableExpressions
     */
    public static List getTablesInNestedTable(TableNested tableNested) {
        List list = new ArrayList();
        TableReference tableRef = tableNested.getNestedTableRef();
        if (tableRef instanceof TableExpression) {
            list.add(tableRef);
        }
        else if (tableRef instanceof TableNested) {
            list.addAll(JoinHelper.getTablesInNestedTable((TableNested) tableRef));
        }
        else if (tableRef instanceof TableJoined) {
            list.addAll(JoinHelper.getTablesInJoin((TableJoined) tableRef));
        }
        return list;
    }

    /**
     * Removes the given joined table from the given FROM clause list.
     * Depending on the structure of the joined table, one or both the 
     * left and right side of the join may be removed from the join tree 
     * that contains it and added directly to the FROM clause list.
     * 
     * @param fromClause the list of objects (tables and joins) in the FROM clause
     * @param the join to be removed
     */
    public static void removeJoin(List fromClause, TableJoined joinedTable) {
        List leftTableList = new ArrayList();
        List rightTableList = new ArrayList();
    
        // Get the children (the objects on the left and right sides) of the join.
        TableReference leftChild = joinedTable.getTableRefLeft();
        TableReference rightChild = joinedTable.getTableRefRight();
    
        // Get a list of tables in the left side of the join.
        if (leftChild instanceof TableExpression) {
            leftTableList.add(leftChild);
        }
        else if (leftChild instanceof TableNested) {
            TableNested tableNested = (TableNested) leftChild;
            leftTableList = JoinHelper.getTablesInNestedTable(tableNested);
        }
        else if (leftChild instanceof TableJoined) {
            TableJoined tempJoin = (TableJoined) leftChild;
            leftTableList = JoinHelper.getTablesInJoin(tempJoin);
        }
    
        // Get a list of tables in the righ side of the join.
        if (rightChild instanceof TableExpression) {
            rightTableList.add(rightChild);
        }
        else if (rightChild instanceof TableNested) {
            TableNested tableNested = (TableNested) rightChild;
            rightTableList = JoinHelper.getTablesInNestedTable(tableNested);
        }
        else if (rightChild instanceof TableJoined) {
            TableJoined tempJoin = (TableJoined) rightChild;
            rightTableList = JoinHelper.getTablesInJoin(tempJoin);
        }
    
        // Get the parent of the join, if any. If this join is a "nested" join, 
        // then get the parent of the join (the nest). 
        TableJoined parentJoin = null;
        TableReference nestTable = joinedTable.getNest();
        if(nestTable != null){
            parentJoin = nestTable.getTableJoinedLeft();
        }
        else{
            parentJoin = joinedTable.getTableJoinedLeft();    
        }
        
        boolean isRightChild = false;
        if (parentJoin == null) {
            if(nestTable != null){
                parentJoin = nestTable.getTableJoinedRight();
            }
            else {
                parentJoin = joinedTable.getTableJoinedRight();
            }
            isRightChild = true;
        }
    
        // Get a list of joins which refer to the tables in the left side of this join
        // Do the same for the right side.
        List leftJoinList = new ArrayList();
        List rightJoinList = new ArrayList();
        Iterator itr = leftTableList.iterator();
        TableExpression table;
        if (parentJoin != null) {
            while (itr.hasNext()) {
                table = (TableExpression) itr.next();
                leftJoinList.addAll(findJoinsWithTableInCondition(table, parentJoin));
            }
    
            itr = rightTableList.iterator();
            while (itr.hasNext()) {
                table = (TableExpression) itr.next();
                rightJoinList.addAll(findJoinsWithTableInCondition(table, parentJoin));
            }
        }
    
        // Determine what to do with the children.  They should be either
        // "promoted" to take the place of the join being removed, or "bumped"
        // out of the join tree entirely and placed directly in the From clause.
        boolean promoteLeftChild = false;
        boolean bumpLeftChild = false;
        boolean promoteRightChild = false;
        boolean bumpRightChild = false;
        
        // If there are no other joins that refer to the tables in the
        // left side, then bump it to the From clause. 
        if (leftJoinList.isEmpty() && parentJoin != null) {
            bumpLeftChild = true;
        }
        // Otherwise mark it for promotion. 
        else {
            promoteLeftChild = true;
        }
    
        // Do the same for the right side.
        if (rightJoinList.isEmpty() && parentJoin != null) {
            bumpRightChild = true;
        }
        else {
            promoteRightChild = true;
        }
    
        // We're going to be moving the children, so remove them from
        // the join.
        joinedTable.setTableRefLeft(null);
        joinedTable.setTableRefRight(null);
    
        // We can only promote one child.  If both are eligible for
        // promotion, we need to figure out which to promote, and which
        // to bump.  To resolve this, we determine which child has the fewest
        // references to it from other joins.  We'll remove the join 
        // conditions further up the join tree that refer to it and bump it 
        // out of the join. For a tie, we arbitrarily choose to bump the right side.
        TableReference childToPromote = null;
        if (!(promoteLeftChild == true && promoteRightChild == true)) {
            // The easy case: only one child was marked for promotion.
            if (promoteLeftChild == true) {
                childToPromote = leftChild;
            }
            else if (promoteRightChild == true) {
                childToPromote = rightChild;
            }
        }
        else {
            // Both children are eligible for promotion.  Have to make
            // Sophie's Choice.
            if (leftJoinList.size() >= rightJoinList.size()) {
                bumpRightChild = true;
                childToPromote = leftChild;
            }
            else {
                bumpLeftChild = true;
                childToPromote = rightChild;
            }
        }
    
        // If there is a parent join, promote the child we determined above 
        // (if any) to take place of the join.
        if (parentJoin != null) {
            if (childToPromote != null) {
                if (isRightChild == false) {
                    parentJoin.setTableRefLeft(childToPromote);
                }
                else {
                    parentJoin.setTableRefRight(childToPromote);
                }
            }
        }
        // Otherwise we must be the root join.  Remove the join from the
        // From clause list.  Add the promoted child to the From clause list.
        else {
            // If the join is within a nest, remove the nest from the fromclause
            TableNested nest = joinedTable.getNest();
            if(nest != null){
                fromClause.remove(nest);
            }
            // otherwise remove the join from the fromclause
            else {
                fromClause.remove(joinedTable);    
            }
            if (childToPromote != null) {
                fromClause.add(childToPromote);
            }
        }
    
        // If we have to bump a child out of the join, remove all the 
        // ON clause predicates that refer to it and add it directly to
        // the From clause.
        // Note: we process the right side first so that if it turns out 
        // that we bump both the left and right children, when we're done
        // the left child will appear first in the From clause, which should 
        // appear a little more natural to the user.
        if (bumpRightChild == true) {
            // Remove join predicates (and possibly joins) that refer to 
            // the tables in the child further up the join tree. 
            removeJoinConditionsForTables(fromClause, rightTableList, rightJoinList);
    
            // Add the child to the end of the From clause.
            fromClause.add(rightChild);
        }
        else if (bumpLeftChild == true) {
            removeJoinConditionsForTables(fromClause, leftTableList, leftJoinList);
            fromClause.add(0, leftChild);
        }
    }

    /**
     * Removes the given join condition (PredicateBasic) from the given
     * joined table.  If removing the condition leaves the ON clause empty,
     * then the join itself is removed from the join tree.
     * 
     * @param fromClause the list of objects (tables and joins) in the FROM clause
     * @param joinedTable the join from which the join condition is to be removed
     * @param joinCond the join condition to be removed
     */
    public static void removeJoinCondition(List fromClause, TableJoined joinedTable, PredicateBasic joinCond) {
        // Remove the condition from the ON clause.
        QuerySearchCondition onClause = joinedTable.getJoinCondition();
        if (onClause != null && joinCond != null) {
            QuerySearchCondition newCondition = removePredicateFromCondition(joinCond, onClause);
            joinedTable.setJoinCondition(newCondition);
        }

        // If the ON clause is now empty, remove the join itself from the
        // join tree.  (Can't have a join without an ON condition.)
        // if (onClause == null )
        if (joinedTable.getJoinCondition() == null) {
            removeJoin(fromClause, joinedTable);
        }
    }

    /**
     * Unravels the content (left or right) of a join by removing all
     * ON clause conditions in the given join list that refer to the tables
     * in the given table list.  Joins who's ON clause become empty are
     * removed from the join tree.
     * 
     * @param fromClause the list of objects (tables and joins) in the FROM clause
     * @param tableList a list of tables for which joins are to be removed
     * @param joinList a list of joins to be searched for conditions involving
     * the tables and updated or removed
     */
    public static void removeJoinConditionsForTables(List fromClause, List tableList, List joinList) {
        if (tableList != null && joinList != null) {
            Iterator tableListIter = tableList.iterator();
            while (tableListIter.hasNext()) {
                TableExpression table = (TableExpression) tableListIter.next();
                Iterator joinListIter = joinList.iterator();
                while (joinListIter.hasNext()) {
                    TableJoined join = (TableJoined) joinListIter.next();
                    QuerySearchCondition cond = join.getJoinCondition();
                    List predList = findConditionsContainingTable(cond, table);
                    Iterator predListIter = predList.iterator();
                    while (predListIter.hasNext()) {
                        PredicateBasic pred = (PredicateBasic) predListIter.next();
                        removeJoinCondition(fromClause, join, pred);
                    }
                }
            }
        }
    }

    /**
     * Removes any joins from the FROM clause list that contain or reference 
     * the given table.
     * 
     * @param fromClause the list of objects (tables and joins) in the FROM clause
     * @param table the table for which joins should be removed
     */
    public static void removeJoinsForTable(List fromClause, TableExpression table) {
        // Get the join, if any, containing the table and remove it.
        // When we find and remove a join, the result may be that the
        // table we're interested in gets "promoted" into a join further
        // up the join tree.  So we need to keep removing joins until
        // there are no more joins involving the table.
        TableJoined joinedTable = table.getTableJoinedLeft();
        if (joinedTable == null) {
            joinedTable = table.getTableJoinedRight();
        }
        while (joinedTable != null) {
            removeJoin(fromClause, joinedTable);
            joinedTable = table.getTableJoinedLeft();
            if (joinedTable == null) {
                joinedTable = table.getTableJoinedRight();
            }
        }
    }

    /**
     * Removes the given predicate from the given search condition.
     * 
     * @param pred the Predicate which needs to be removed from the condition
     * @param searchCon the SearchCondition from which predicate needs to be removed
     * @return the modified searchCondition after removing the predicate
     */
    public static QuerySearchCondition removePredicateFromCondition(Predicate pred, QuerySearchCondition searchCon) {
        // Case : if only node  then searchCon = new pred
        if (pred.getCombinedRight() == null && pred.getCombinedLeft() == null) {
            searchCon = null;
        }
        else if (pred.getCombinedRight() != null && pred.getCombinedRight().getCombinedLeft() == null) {
            // Case : any right most (last) node then searchCon points to left (previous) sibling (Combine/pred) 
            searchCon = pred.getCombinedRight().getLeftCondition();
            searchCon.setCombinedLeft(null);
        }
        else if (pred.getCombinedLeft() != null) {
            // Case : any left most (first) node then if more than 2 nodes then parent combined replaces with right sibling  
            //                           otherwise  searchCon points to right sibling (pred)
            if (pred.getCombinedLeft().getCombinedLeft() != null) {
                pred.getCombinedLeft().getCombinedLeft().setLeftCondition(pred.getCombinedLeft().getRightCondition());
            }
            else {
                searchCon = pred.getCombinedLeft().getRightCondition();
            }
        }
        else if (pred.getCombinedRight() != null && pred.getCombinedRight().getCombinedLeft() != null) {
            // Case : any middle node then assign right (next) pred to parent and assignt parent's
            //                     left combine to parent's parent's combine left
            //                     if second from right then searchCon points to parent

            SearchConditionCombined currentGroup = pred.getCombinedRight();
            pred.getCombinedRight().setRightCondition(pred.getCombinedRight().getCombinedLeft().getRightCondition());
            currentGroup.setCombinedLeft(currentGroup.getCombinedLeft().getCombinedLeft());
            if (currentGroup.getCombinedLeft() == null) {
                searchCon = currentGroup;
            }
        }
        return searchCon;
    }

    /**
     * Returns the ComparisonOperator literal from the given symbol.
     * 
     * @param compKind the String symbol for which Comparison Literal is needed 
     * @return string literal name
     */
    private static String getComparisonLiteralFromSymbol(String compKind) {
        String retVal = "";
        if (compKind.equalsIgnoreCase("=")) {
            retVal = "EQUAL";
        }
        else if (compKind.equalsIgnoreCase("<")) {
            retVal = "LESS_THAN";
        }
        else if (compKind.equalsIgnoreCase("<=")) {
            retVal = "LESS_THAN_OR_EQUAL";
        }
        else if (compKind.equalsIgnoreCase(">")) {
            retVal = "GREATER_THAN";
        }
        else if (compKind.equalsIgnoreCase(">=")) {
            retVal = "GREATER_THAN_OR_EQUAL";
        }
        else if (compKind.equalsIgnoreCase("<>")) {
            retVal = "NOT_EQUAL";
        }
        return retVal;
    }

} // end class
