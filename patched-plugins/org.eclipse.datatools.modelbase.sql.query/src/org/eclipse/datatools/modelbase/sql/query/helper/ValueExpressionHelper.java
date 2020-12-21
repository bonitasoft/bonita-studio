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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.datatools.modelbase.sql.datatypes.ApproximateNumericDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.FixedPrecisionDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesFactory;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.query.MergeSourceTable;
import org.eclipse.datatools.modelbase.sql.query.MergeTargetTable;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
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
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;



/**
 * This class provides a set of utility methods for dealing with
 * Value Expression objects.
 */
public class ValueExpressionHelper {

    
protected static HashMap FunctionReturnType = createFunctionReturnTypeMap();

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Determines whether a given value expression is already a column expression
 * and if so, adds it to the passed in list.  Otherwise, get the columns
 * and addAll to the list
 * @param List list of columns
 * @param aValueExpr QueryValueExpression whose columns will be added to the list
 */
public static void addExpressionsToList(List columnList,
                                        QueryValueExpression aValueExpr) {
  if ( aValueExpr instanceof ValueExpressionColumn ) {
       columnList.add( aValueExpr );
  } else {
       columnList.addAll(getColumnsFromValueExpression(aValueExpr));
  }
}

/**
 * Returns a copy of the given <code>valueExpr</code> using
 * {@link EcoreUtil#copy(org.eclipse.emf.ecore.EObject)}.
 * @param valueExpr
 * @return a copy of the given <code>valueExpr</code>
 */
public static QueryValueExpression cloneQueryValueExpression ( QueryValueExpression valueExpr) {

//  throw new UnsupportedOperationException(ValueExpressionHelper.class.getName()+"#cloneQueryValueExpression() not implemented and not to be used!");

    QueryValueExpression clone = null;
    
    if (valueExpr != null)
    {
        DataType dataType = valueExpr.getDataType();
        DataType dataTypeCopy = copyDataType(dataType);

        // we don't want the DataType to be copyed by reference, EMF ownership
        // would cause the DataTuype to be removed from original valueExpr when
        // the cloned value expr gets a reference to it
        valueExpr.setDataType(null);
        
        clone = (QueryValueExpression) EcoreUtil.copy(valueExpr);
        
        valueExpr.setDataType(dataType);
        clone.setDataType(dataTypeCopy);
    }
    
    return clone;
}




/**
 * Returns a copy of the given <code>datatype</code> using
 * {@link EcoreUtil#copy(org.eclipse.emf.ecore.EObject)}.
 * @param dataType
 * @return a copy of the given <code>datatype</code>
 */
public static DataType copyDataType(DataType dataType)
{
    DataType copy = null;
    
    if (dataType != null)
    {
        copy = (DataType) EcoreUtil.copy(dataType);
    }
    
    return copy;
}

/**
 * Attempts to copy the datatype from one given value expression to another
 * using {@link EcoreUtil#copy(org.eclipse.emf.ecore.EObject)}.
 * The type is not copied if the source expression does not exist or
 * its datatype is not set.
 * @param aSourceExpr the value expression whose datatype we want to copy
 * @param aTargetExpr the value expression whose datatype we want to set
 */
public static void copyDataType( QueryValueExpression aSourceExpr, QueryValueExpression aTargetExpr ) {
  if (aSourceExpr != null && aSourceExpr.getDataType() != null) {
      DataType dataType = aSourceExpr.getDataType();
      DataType dataTypeCopy = copyDataType(dataType);
      aTargetExpr.setDataType( dataTypeCopy );
  }
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Method to obtain the column expressions from a SearchCondition
 * @param aSearchCond QuerySearchCondition whose columns we need to get
 * @return List list of ValueExpressionColumns
 */
public static List getColumnsFromSearchCondition( QuerySearchCondition aSearchCond) {

    List columns = new ArrayList();
    QuerySearchCondition sc = aSearchCond;
    // Check if we have a "combined" condition (that is, two conditions combined 
    // by AND or OR).  If so, process each side of the condition separately.
    if (sc instanceof SearchConditionCombined) {
        QuerySearchCondition left = ((SearchConditionCombined)sc).getLeftCondition();
        QuerySearchCondition right = ((SearchConditionCombined)sc).getRightCondition();

        columns.addAll(getColumnsFromSearchCondition(left));
        columns.addAll(getColumnsFromSearchCondition(right));
    } 
    // [161670] bgp 19Oct2006 - begin
    // Handle a "nested" condition, that is, a condition in parens.  Get the nested
    // condition and process it.
    else if (sc instanceof SearchConditionNested) {
        QuerySearchCondition nested = ((SearchConditionNested)sc).getNestedCondition();
        columns.addAll(getColumnsFromSearchCondition(nested));
    }
    // [161670] bgp 19Oct2006 - end
    // Handle a "basic" predicate.  A basic predicate has a simple 
    // relational operator (ie, "=").
    else if (sc instanceof PredicateBasic) {
        QueryValueExpression valueExpr = ((PredicateBasic)sc).getLeftValueExpr();
        addExpressionsToList(columns, valueExpr);

        valueExpr = ((PredicateBasic)sc).getRightValueExpr();
        addExpressionsToList(columns, valueExpr);
    } 
    // Handle a BETWEEN predicate.
    else if (sc instanceof PredicateBetween) {
        // [161670] bgp 19Oct2006 - begin
        QueryValueExpression valueExpr = ((PredicateBetween)sc).getLeftValueExpr();
        addExpressionsToList(columns, valueExpr);
        // [161670] bgp 19Oct2006 - end
        QueryValueExpression lower =
            (QueryValueExpression)((PredicateBetween)sc).getRightValueExpr1();
        addExpressionsToList(columns, lower);
        QueryValueExpression upper =
            (QueryValueExpression)((PredicateBetween)sc).getRightValueExpr2();
        addExpressionsToList(columns, upper);
    } 
    // Handle a IN predicate.
    else if (sc instanceof PredicateInValueList) {
        QueryValueExpression valueExpr = ((PredicateInValueList)sc).getValueExpr();
        addExpressionsToList(columns, valueExpr);

        List inValues = ((PredicateInValueList)sc).getValueExprList();
        Iterator ivIter = inValues.iterator();
        while (ivIter.hasNext()) {
            QueryValueExpression inExpr = (QueryValueExpression)ivIter.next();
            addExpressionsToList(columns, inExpr);
        }
    }
    // Handle a LIKE predicate.
    else if (sc instanceof PredicateLike) {
        QueryValueExpression valueExpr = ((PredicateLike)sc).getMatchingValueExpr();
        addExpressionsToList(columns, valueExpr);
        valueExpr = (QueryValueExpression)((PredicateLike)sc).getPatternValueExpr();
        addExpressionsToList(columns, valueExpr);
    } 
    // Handle a IS NULL predicate.
    else if (sc instanceof PredicateIsNull) {
        QueryValueExpression valueExpr = ((PredicateIsNull)sc).getValueExpr();
        addExpressionsToList(columns, valueExpr);
    }
    
    return columns;
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Method to obtain the column expressions from a generic ValueExpression
 * Note that only the value expressions listed below can contain columns
 * within them.  All other value expressions should return an empty list.
 * @param valueExpr QueryValueExpression whose columns we need to get
 * @return List list of ValueExpressionColumns
 */
public static List getColumnsFromValueExpression( QueryValueExpression valueExpr) {

  List columns = new ArrayList();
  if (valueExpr instanceof ValueExpressionColumn) {
     columns.add(valueExpr);
  }
  else  if (valueExpr instanceof ValueExpressionFunction) {
     columns.addAll(getVEFunctionColumns( (ValueExpressionFunction)valueExpr ));
  }
  else if (valueExpr instanceof ValueExpressionCombined) {
     columns.addAll(getVECombinedColumns( (ValueExpressionCombined)valueExpr ));
  }
  else if (valueExpr instanceof ValueExpressionCast) {
     columns.addAll(getVECastColumns( (ValueExpressionCast)valueExpr ));
  }
  else if (valueExpr instanceof ValueExpressionCase) {
     columns.addAll(getVECaseColumns( (ValueExpressionCase)valueExpr ));
  }
  else if (valueExpr instanceof ValueExpressionLabeledDuration) {
     columns.addAll(getVELabeledDurationColumns( (ValueExpressionLabeledDuration)valueExpr ));
  }
  return columns;
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Method to obtain the string table name/s from a ValueExpression
 */
public static List getTableNamesFromExpression (QueryValueExpression valueExpr) {

  List tableNames = new ArrayList();

  List columnExpr = getColumnsFromValueExpression( valueExpr );
  if ( !columnExpr.isEmpty() ) {
     // now get the table names for each column expression
     Iterator cIter = columnExpr.iterator();
     while ( cIter.hasNext() ) {
        ValueExpressionColumn aColumn =
          (ValueExpressionColumn)cIter.next();
        String aTableName = ((TableInDatabase)aColumn.getTableExpr()).getName();
        if ( !tableNames.contains(aTableName) || tableNames.isEmpty() )
           tableNames.add(aTableName);
     }
  }
  return tableNames;
}
/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Method to obtain the table references from a ValueExpression
 */
public static List getTableRefsFromExpression (QueryValueExpression valueExpr) {

  List tableRefs = new ArrayList();

  List columnExpr = getColumnsFromValueExpression( valueExpr );
  if ( !columnExpr.isEmpty() ) {
     // now get the table references for each column expression
     Iterator cIter = columnExpr.iterator();
     while ( cIter.hasNext() ) {
        ValueExpressionColumn aColumn =
          (ValueExpressionColumn)cIter.next();
        TableReference aTableRef = aColumn.getTableExpr();
        if (aTableRef != null) {
           tableRefs.add(aTableRef);
        }
     }
  }
  return tableRefs;
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Extracts and returns a list of column reference objects from the given
 * ValueExpressionCase object.
 * @param aValExpr the CASE expression to process
 * @return a list of ValueExpressionColumn objects
 */
public static List getVECaseColumns( ValueExpressionCase aValExpr ) {
  List contentList = null;
  Iterator contentListIter = null;
  List columnList = new ArrayList();

  // The model structure of the "searched" case and "simple" case
  // are different, so we handle them separately.
  // @d278068 bgp 20Jan2004 - begin

  // Process a searched case expression.
  if (aValExpr instanceof ValueExpressionCaseSearch) {
    // A "searched" case looks like this:
    //  CASE
    //    WHEN EDLEVEL < 15 THEN 'Secondary'
    //    WHEN EDLEVEL < 19 THEN 'College'
    //    ELSE 'Post graduate'
    //  END

    // Process the "contents" list.  In the example above,
    // the contents list is all of the "WHEN EDLEVEL < n THEN 'Xxxxxx'"
    // clauses.
    contentList = ((ValueExpressionCaseSearch) aValExpr).getSearchContentList();
    contentListIter = contentList.iterator();
    ValueExpressionCaseSearchContent searchedWhen;
    QuerySearchCondition searchCondition;
    QueryValueExpression resultExpr;
    while (contentListIter.hasNext()) {
      // Process a single WHEN clause, ie "WHEN EDLEVEL < 15 THEN 'Secondary'"
      searchedWhen = (ValueExpressionCaseSearchContent) contentListIter.next();

      // Get the search condition from WHEN clause (ie, EDLEVEL < 15)
      // and add any columns that it contains to the column list.
      searchCondition = searchedWhen.getSearchCondition();
      if (searchCondition != null) {
        columnList.addAll( getColumnsFromSearchCondition( searchCondition ));
      }

      // Get the result expression from the WHEN clause (ie 'SECONDARY')
      // and add any columns it contains to the column list.
      resultExpr = searchedWhen.getValueExpr();
      if (resultExpr instanceof ValueExpressionColumn) {
        columnList.add( resultExpr );
      }
      else {
        columnList.addAll( getColumnsFromValueExpression( resultExpr ));
      }
    } // end while
  }
  // Process a simple case expression.
  else {
    // A "simple" case looks like this:
    //   CASE SUBSTR( WORKDEPT,1,1 )
    //     WHEN 'A' THEN 'Administration'
    //     WHEN 'H' THEN 'Human Resources'
    //      ...
    //     ELSE 'Unknown'
    //   END

    // Process the case operand expression.  In the example above,
    // the case operand expression is "SUBSTR( WORKDEPT,1,1 )".
    QueryValueExpression operandExpr = ((ValueExpressionCaseSimple) aValExpr).getValueExpr();
    if (operandExpr instanceof ValueExpressionColumn) {
      columnList.add( operandExpr );
    }
    else {
      columnList.addAll( getColumnsFromValueExpression( operandExpr ));
    }

    // Process the "contents" list.  In the example above,
    // the contents list is all of the "WHEN 'X' THEN 'Xxxxxxx'"
    // clauses.
    contentList = ((ValueExpressionCaseSimple) aValExpr).getContentList();
    contentListIter = contentList.iterator();
    ValueExpressionCaseSimpleContent simpleWhen;
    QueryValueExpression whenExpr;
    QueryValueExpression resultExpr;
    while (contentListIter.hasNext()) {
      // Process a single WHEN clause, ie "WHEN 'A' THEN 'Administration'"
      simpleWhen = (ValueExpressionCaseSimpleContent) contentListIter.next();

      // Get the WHEN expression (ie, 'A') and add any columns it
      // contains to the list.
      whenExpr = simpleWhen.getWhenValueExpr();
      if (whenExpr instanceof ValueExpressionColumn) {
        columnList.add( whenExpr );
      }
      else {
        columnList.addAll( getColumnsFromValueExpression( whenExpr ));
      }

      // Get the result expression (ie, 'Administration') and
      // add any columns it contains to the list.
      resultExpr = simpleWhen.getResultValueExpr();
      if (resultExpr instanceof ValueExpressionColumn) {
        columnList.add( resultExpr );
      }
      else {
        columnList.addAll( getColumnsFromValueExpression( resultExpr ));
      }
    } // end while
  }

  // Both the searched and simple flavors of CASE have an optional ELSE clause,
  // so scan it and add any columns it contains to the list.
  ValueExpressionCaseElse caseElse = aValExpr.getValueExprCaseElse();
  if (caseElse != null) {
    QueryValueExpression elseExpr = caseElse.getValueExpr();
    if (elseExpr instanceof ValueExpressionColumn) {
      columnList.add( elseExpr );
    }
    else {
      columnList.addAll( getColumnsFromValueExpression( elseExpr ));
    }
  }
  // @d278068 bgp 20Jan2004 - end
  return columnList;
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Method to obtain the column expressions from a ValueExpressionCast
 */
public static List getVECastColumns( ValueExpressionCast aValExpr ) {

  List columnList = new ArrayList();
  QueryValueExpression castValExpr = aValExpr.getValueExpr();
  if ( aValExpr instanceof ValueExpressionColumn ) {
     columnList.add( castValExpr );
  } else {
     columnList.addAll(getColumnsFromValueExpression(castValExpr) );
  }
  return columnList;
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Method to obtain the column expressions from a ValueExpressionCombined
 */
public static List getVECombinedColumns (ValueExpressionCombined combined) {

  List columnList = new ArrayList();
  // Handle the left side of the VECombined.  We may have to recurse.
  QueryValueExpression leftVE = combined.getLeftValueExpr();
  if ( leftVE instanceof ValueExpressionColumn ) {
     columnList.add( leftVE );
  } else {
     columnList.addAll(getColumnsFromValueExpression(leftVE) );
  }

  // Now handle the right side of the VECombined.  We may have to recurse.
  QueryValueExpression rightVE = combined.getRightValueExpr();
  if ( rightVE instanceof ValueExpressionColumn ) {
     columnList.add( rightVE );
  } else {
     columnList.addAll(getColumnsFromValueExpression(rightVE) );
  }

  return columnList;
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Method to obtain the column expressions from a ValueExpressionFunction
 */
public static List getVEFunctionColumns(ValueExpressionFunction funcExpr) {

   List valueExprCols = new ArrayList();
   List parms = funcExpr.getParameterList();
      Iterator pIter = parms.iterator();
      while ( pIter.hasNext() ) {
         Object parmx = pIter.next();
         QueryValueExpression parmExpr = (QueryValueExpression)parmx;
         if (parmx instanceof ValueExpressionColumn) {
            ValueExpressionColumn colExpr = (ValueExpressionColumn)parmx;
            valueExprCols.add(colExpr);
         }
         else {
            valueExprCols.addAll(getColumnsFromValueExpression(parmExpr) );
         }
      } // end while
   return valueExprCols;
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Method to obtain the column expressions from a ValueExpressionLabeledDuration
 */
public static List getVELabeledDurationColumns(ValueExpressionLabeledDuration labeledDurExpr) {

   List columnList = new ArrayList();
   QueryValueExpression durExpr = labeledDurExpr.getValueExpr();
   if (durExpr instanceof ValueExpressionColumn) {
      columnList.add(durExpr);
   } else {
      columnList.addAll(getColumnsFromValueExpression(durExpr) );
   }
   return columnList;
}

/**
 * Do the numeric data type promotion
 * @param left
 * @param right
 * @return
 */
public static DataType numericDataTypePromotion(DataType left, DataType right)
{
    DataType retType = doNumericDataTypePromotion(left, right);
    if(retType == null)
        retType = doNumericDataTypePromotion(right, left);
    
    if(retType == null)
    {
        if(left != null)
            retType = copyDataType(left);
        else
            retType = copyDataType(right);
    }
    
    return retType;
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Determine the Column that corresponds to this column expression if there is
 * one that the given columnExpr is derived from.
 */
public static Column resolveColumnFromValueExpression( ValueExpressionColumn aValExpr) {

  String colName = aValExpr.getName();
  Column theColumn = null;
  TableInDatabase tableRef = aValExpr.getTableInDatabase();
  if (tableRef != null) {
     List columns = tableRef.getDatabaseTable().getColumns();
     Iterator iter = columns.iterator();
     while (iter.hasNext()) {
       Column aColumn = (Column)iter.next();
       String aColumnName = aColumn.getName();
       if (StatementHelper.equalSQLIdentifiers(aColumnName, colName )) {
          theColumn = aColumn;
          break;
       }
     }
  }
  return theColumn;
}

/** CHANGE IMPLEMENTATION! CODE INCORRECT! 
 * returns the given <code>aDataType</code>, if not <code>null</code>, or
 * the given <code>anotherDataType</code>. 
 * <p>
 * Tries to determine the datatype that can contain values of both given 
 * <code>DataType</code>s <code>aDataType</code> and
 * <code>anotherDataType</code>, useful for example for the result columns
 * of a {@link com.ibm.db.models.sql.query.QueryCombined} or a CASE stmt
 * @param aDataType one <code>DataType</code>
 * @param anotherDataType another <code>DataType</code>
 * @return the inclusive <code>DataType</code>
 * 
 * Note:  complex rules for combining data types are documented in the DB2 SQL
 * Reference under "Rules for Result Data Types"
 * http://publib.boulder.ibm.com/infocenter/db2luw/v9r7/index.jsp?topic=/com.ibm.db2.luw.sql.ref.doc/doc/r0008480.html
 * 
 * To date we've only implemented the rules for combining character strings
 */
public static DataType resolveCombinedDataType( DataType aDataType,
                                            DataType anotherDataType) {
    /*
     * If we have two character strings to combine
     */
    if (aDataType != null && anotherDataType != null &&
        aDataType       instanceof CharacterStringDataType &&
        anotherDataType instanceof CharacterStringDataType)
    {
        CharacterStringDataType resolvedDataType = (CharacterStringDataType) copyDataType(aDataType);
        /*
         * set length to maximum 
         */
        resolvedDataType.setLength(
                Math.max( ((CharacterStringDataType) aDataType).getLength(), 
                          ((CharacterStringDataType) anotherDataType).getLength() )
                                  );
        PrimitiveType aPrimType = ((PredefinedDataType) aDataType).getPrimitiveType();
        PrimitiveType anotherPrimType = ((PredefinedDataType) anotherDataType).getPrimitiveType();
        if ( aPrimType == PrimitiveType.CHARACTER_LITERAL &&
             anotherPrimType == PrimitiveType.CHARACTER_VARYING_LITERAL             
           )
        {
            resolvedDataType.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL); 
        }
        else if ( (aPrimType == PrimitiveType.CHARACTER_LITERAL || 
                   aPrimType == PrimitiveType.CHARACTER_VARYING_LITERAL)
                   &&
                   anotherPrimType == PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL )
        {
            resolvedDataType.setPrimitiveType(PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL);
        }
                
        return resolvedDataType;
    }    
    else if (aDataType != null)
    {
        return aDataType;
    }
    else 
    {
        return anotherDataType;
    }
}
/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Substitutes the tables in the given table list into the given
 * Value Expression in place of the one it currently has.
 * @param valExpr the QueryValueExpression whose table need to be resolved
 * @param tables List of TableReference tables
 */
public static void resolveTablesInValueExpression(QueryValueExpression valExpr,
                                                            List tables) {

  List columnList = ValueExpressionHelper.getColumnsFromValueExpression( valExpr);
  boolean useCorrName = false;
  String searchTableName = "";
  String colCorrName = "";
  if ( columnList != null  && !columnList.isEmpty() ) {
    Iterator cIter = columnList.iterator();
    while ( cIter.hasNext() ) {
        ValueExpressionColumn colExpr = (ValueExpressionColumn)cIter.next();
      TableExpression colTable = colExpr.getTableExpr();             // @d281131 bgp 27May2003
      if (colTable != null) {                             // @d281131 bgp 27May2003
        colCorrName = colTable.getTableCorrelation().getName();
        if ((colCorrName != null) && (colCorrName.length() > 0) ) {
            searchTableName = colCorrName;
            useCorrName = true;
          } else {
            searchTableName = colExpr.getTableExpr().getName();
            useCorrName = false;
        }
      }                                                   // @d281131 bgp 27May2003
        Iterator tIter = tables.iterator();
        TableExpression foundTable = null;
        while ( tIter.hasNext() &&  foundTable == null) {
           TableExpression fcTable = (TableExpression)tIter.next();
           if (useCorrName) {
              String fcCorrName = fcTable.getTableCorrelation().getName();
              if ( fcCorrName != null
                && StatementHelper.equalSQLIdentifiers(fcCorrName, searchTableName)) {
                 foundTable = fcTable;
              }
           } else {
              String fcTableName = fcTable.getName();
              if ( fcTableName != null 
                && StatementHelper.equalSQLIdentifiers(fcTableName, searchTableName)) {
                 foundTable = fcTable;
              }
           }
        } // end while for table
        if (foundTable != null) {
           colExpr.setTableExpr(foundTable);
        }
     } // end while for columns
  }
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype of the given Case value
 * expression.
 * @param aValExpr a value expression to resolve
 * @param aTableRefList the current list of From clause table references
 * @param aDB a RDBDatabase object containing datatype information
 * @param aDBVersion an object containing database version information
 */
public static void resolveValueExpressionCaseDatatype( ValueExpressionCase aValExpr) {
/*
 * We'll set the datatype for this value expression to the datatype
 * of the combined "result expression" that is not NULL and that has a
 * datatype.
 */
    List contentList = null;
    Iterator contentListIter = null;
    DataType contentDatatype = null;
    /* 
     * The model structure of the "searched case" and
     * "simple case" are slightly different, so we handle them separately.
     */
    if (aValExpr instanceof ValueExpressionCaseSearch) {
        contentList = ((ValueExpressionCaseSearch) aValExpr).getSearchContentList();
        contentListIter = contentList.iterator();
        ValueExpressionCaseSearchContent content;
        QueryValueExpression contentValExpr;
        while ( contentListIter.hasNext() ) {
            content = (ValueExpressionCaseSearchContent) contentListIter.next();
            contentValExpr = content.getValueExpr();
            if (!(contentValExpr instanceof ValueExpressionNullValue)) {
                contentDatatype = copyDataType(
                        resolveCombinedDataType( contentDatatype, contentValExpr.getDataType() )
                      );
            }
        }
        /*
         * Process the "ELSE" value expression of the ELSE y clause, if any
         */
        ValueExpressionCaseElse elseContent = ((ValueExpressionCaseSearch) aValExpr).getCaseElse();
        if (elseContent != null) {
            contentValExpr = elseContent.getValueExpr();
            if (!(contentValExpr instanceof ValueExpressionNullValue)) {
                contentDatatype = copyDataType(
                        resolveCombinedDataType( contentDatatype, contentValExpr.getDataType() )
                        );
            }
        }
    }
    /*
     * otherwise must be ValueExpressionCaseSimple
     */
    else {
    /*
     * Step through all the "THEN" value expressions of the WHEN x THEN y clauses
     */
        contentList = ((ValueExpressionCaseSimple) aValExpr).getContentList();
        contentListIter = contentList.iterator();
        ValueExpressionCaseSimpleContent content;
        QueryValueExpression contentValExpr;
        while ( contentListIter.hasNext() ) {
            content = (ValueExpressionCaseSimpleContent) contentListIter.next();
            contentValExpr = content.getResultValueExpr();
            if (!(contentValExpr instanceof ValueExpressionNullValue)) {
                contentDatatype = copyDataType(
                        resolveCombinedDataType( contentDatatype, contentValExpr.getDataType() )
                      );
            }
        }
        /*
         * Process the "ELSE" value expression of the ELSE y clause, if any
         */
        ValueExpressionCaseElse elseContent = ((ValueExpressionCaseSimple) aValExpr).getCaseElse();
        if (elseContent != null) {
            contentValExpr = elseContent.getValueExpr();
            if (!(contentValExpr instanceof ValueExpressionNullValue)) {
                contentDatatype = copyDataType(
                        resolveCombinedDataType( contentDatatype, contentValExpr.getDataType() )
                        );
            }
        }
    }

    if (contentDatatype != null) {
        aValExpr.setDataType(contentDatatype);
    }
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype of the given column value
 * expression.
 * @param aValExpr a value expression to resolve
 */
public static void resolveValueExpressionColumnDatatype( ValueExpressionColumn aValExpr ) {
    
    //only resolve if the current DataType is null, might be called duplicately
    if (aValExpr == null || aValExpr.getDataType() != null) {return;}
    
    // Make sure that the table reference contained in the column is one
    // of the From clause tables. If it isn't, get the From clause table
    // that matches the name of the table in the column and set that as
    // the column table ref.
    String colName = aValExpr.getName();
    TableExpression tableRef = aValExpr.getTableExpr();
    if (tableRef != null)
    {
        if (tableRef instanceof TableInDatabase)
        {
            TableInDatabase tableInDB = (TableInDatabase) tableRef;
            Column rdbColumn = null;

            // Get the Column object.
            rdbColumn = TableHelper.getColumnForName(tableInDB, colName);

            // Get the datatype from the Column and attach it to the
            // value expression.
            if (rdbColumn != null)
            {
                DataType datatype = rdbColumn.getDataType();
                aValExpr.setDataType(copyDataType(datatype));
            }
        }
        // maybe more generally the tableRef is a TableExpression that has been
        // resolved already, try to use the exposed columnList
        else
        {
            ValueExpressionColumn tableColExpr =
                TableHelper.getColumnExpressionForName(tableRef, colName);
            
            if (tableColExpr != null && tableColExpr.getDataType() != null)
            {
                aValExpr.setDataType(copyDataType(tableColExpr.getDataType()));
            }
        }
    }

 }

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype of the given "Combined" value
 * expression.
 * @param aValExpr a value expression to resolve
 */
public static void resolveValueExpressionCombined( ValueExpressionCombined aValExpr) {
  // Get the left and right value expressions, and if either of them has
  // a datatype, we will use that as the datatype for this expression.
  // However we need to treat CONCAT as a special case, since the length
  // of the result of a CONCAT is the length of the left plus the length
  // of the right.  There are also special rules regarding "promoting"
  // the datatype, from CHAR to VARCHAR, for example.
  QueryValueExpression leftExpr = aValExpr.getLeftValueExpr();
  QueryValueExpression rightExpr = aValExpr.getRightValueExpr();
  int oper = aValExpr.getCombinedOperator().getValue();
  if (oper == ValueExpressionCombinedOperator.CONCATENATE) {
    resolveValueExpressionConcatDatatype( aValExpr, leftExpr, rightExpr);
  }
  else {
    DataType leftExprDatatype = leftExpr.getDataType();
    DataType rightExprDatatype = rightExpr.getDataType();
    aValExpr.setDataType(numericDataTypePromotion(leftExprDatatype, rightExprDatatype));
  }
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype (DataType) of the given
 * "concatenated" value expression.  This may either be a CONCAT function or a
 * "combined" value expression with a CONCAT operator.
 * @param aValueExpr a value expression to resolve
 * @param aLeftValueExpr the LHS value expression involved in the CONCAT
 * @param aRightValueExpr the RHS value expression involved in the CONCAT
 */
public static void resolveValueExpressionConcatDatatype( QueryValueExpression aValExpr, QueryValueExpression aLeftValExpr, QueryValueExpression aRightValExpr) {

//    throw new UnsupportedOperationException(ValueExpressionHelper.class.getName()+"#resolveValueExpressionCastDatatype() not implemented!");
  
    
  // First check if one or both of the left and right expressions are variables.
  // If so, check to see if they are part of a pattern like the following
  // (which is a pattern generated by the UI):
  //    <column> LIKE '%' CONCAT <varname>             -- 'ends with'
  // or <column> LIKE <varname> CONCAT '%'             -- 'starts with'
  // or <column> LIKE '%' CONCAT <varname> CONCAT '%'  -- 'contains'
  // If so, we want to set the var's datatype to the column's datatype.

   
  if ((aLeftValExpr instanceof ValueExpressionVariable && aLeftValExpr.getDataType() == null)
   || (aRightValExpr instanceof ValueExpressionVariable && aRightValExpr.getDataType() == null)) {
    PredicateLike likePred = null;
    // @d281231 bgp 27May2003 - begin
    ValueExpressionCombined combined = aLeftValExpr.getValueExprCombinedLeft();
    if (combined != null) {
      // Look for a nearby Like predicate.
        likePred = combined.getLikePattern();
      // Try one level higher to see if we have a Like predicate.
      if (likePred == null) {
        ValueExpressionCombined combined2 = combined.getValueExprCombinedLeft();
        if (combined2 == null) {
          combined2 = combined.getValueExprCombinedRight();
        }
        if (combined2 != null) {
          likePred = combined2.getLikePattern();
        }
        // @d281231 bgp 27May2003
      }

      // If we found the Like predicate, get the "matching" expression
      // and see if it is a column.  If it is, copy its datatype to our
      // variables.
      if (likePred != null) {
        QueryValueExpression matchExpr = likePred.getMatchingValueExpr();
        if (matchExpr != null && matchExpr instanceof ValueExpressionColumn) {
          if (aLeftValExpr instanceof ValueExpressionVariable && aLeftValExpr.getDataType() == null) {
            copyDataType( matchExpr, aLeftValExpr );
          }
          if (aRightValExpr instanceof ValueExpressionVariable && aRightValExpr.getDataType() == null) {
            copyDataType( matchExpr, aRightValExpr );
          }
        }
      }
    }
  }

  // Set this value expression's datatype based on the characteristics of
  // the left and right sides.
//  if (aValExpr.getDataType() == null) {
    DataType leftDatatype = aLeftValExpr.getDataType();
    DataType rightDatatype = aRightValExpr.getDataType();
    
    aValExpr.setDataType(concatCharStringDataTypePromotion(leftDatatype, rightDatatype));
//    if (leftDatatype != null && rightDatatype != null) {
//      // There are complicated rules for "promoting" datatypes, for
//      // example promoting CHAR to VARCHAR or VARCHAR to LONG VARCHAR.
//      // For now we will assume the datatype is VARCHAR or VARGRAPHIC
//      // depending on the type of the left side.
//      // ***TODO: handle type promotion, etc.
//      int concatLen = -1;
//      int leftDatatypeLen = 0;
//      int rightDatatypeLen = 0;
//      boolean isVarchar = true;
//
//      // Get the lengths from the left and right datatypes. The lengths
//      // are returned as strings.
//      if (leftDatatype instanceof CharacterStringDataType) {
//        leftDatatypeLen = ((CharacterStringDataType) leftDatatype).getLength();
//      }
//      
//      if (rightDatatype instanceof CharacterStringDataType) {
//        rightDatatypeLen = ((CharacterStringDataType) rightDatatype).getLength();
//      }
//
//      // Convert the strings to integers and compute the concatenated length.
//      if (leftDatatypeLen != 0 && rightDatatypeLen != 0) {
//        try {
//          concatLen = leftDatatypeLen + rightDatatypeLen;
//        }
//        catch (NumberFormatException e) {
//        }
//      }
//
//      // Create the new datatype object and set it as our datatype.
//      if (concatLen > -1) {
//        DataType datatype = null;
//          if (isVarchar == true) {
//              //TODO: supposed to be VARCHAR
//          datatype = getPredefinedDataTypeForSimpleValue( aValExpr.getSQL() , null);
//          }
//          else {
//              //TODO: supposed to be VARGRAPHIC
//            datatype = getPredefinedDataTypeForSimpleValue( aValExpr.getSQL() , null);
//          }
//
//          if (datatype != null) {
//          aValExpr.setDataType( datatype );
//          }
//      }
//    }
//  }
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype (DataType) of the given
 * value expression.  The datatype may be determined through information
 * inherent in this object (for example, by examining the content of
 * a simple value expression), or by checking the datatype of objects
 * that this object is associated with (for example, by getting the datatype
 * of a column to which the value expression is compared).
 * @param aValueExpr a value expression to resolve
 */
public static void resolveValueExpressionDatatype( QueryValueExpression valExpr) {
  // Handle each subclass of QueryValueExpression individually.

// TODO: cover all the following ValueExpressionTypes!
/*
    ValueExpressionCaseElse;
    ValueExpressionCaseSearch;
    ValueExpressionCaseSearchContent;
    ValueExpressionCaseSimple;
    ValueExpressionCaseSimpleContent;
    ValueExpressionCast;
    ValueExpressionColumn;
    ValueExpressionCombined;
    ValueExpressionDefaultValue;
    ValueExpressionFunction;
    ValueExpressionLabeledDuration;
    ValueExpressionNested;
    ValueExpressionNullValue;
    ValueExpressionScalarSelect;
    ValueExpressionSimple;
    ValueExpressionVariable;

*/        
    

  // Column value expression
  if (valExpr instanceof ValueExpressionColumn) {
    resolveValueExpressionColumnDatatype( (ValueExpressionColumn) valExpr);
  }
  // "Combined" value expression
  else if (valExpr instanceof ValueExpressionCombined) {
    resolveValueExpressionCombined( (ValueExpressionCombined) valExpr);
  }
  // "Nested" value expression
  else if (valExpr instanceof ValueExpressionNested) {
      ValueExpressionNested nest = (ValueExpressionNested) valExpr;
      QueryValueExpression nestedExpr = nest.getNestedValueExpr();
      if (nestedExpr != null && nestedExpr.getDataType() == null) {
          resolveValueExpressionDatatype( nestedExpr );
      }
      copyDataType(nestedExpr, nest);
  }
  // "Default" value expression
  else if (valExpr instanceof ValueExpressionDefaultValue) {
    resolveValueExpressionDefaultValueDatatype( (ValueExpressionDefaultValue) valExpr);
  }
  // Case value expression
  else if (valExpr instanceof ValueExpressionCase) {
    resolveValueExpressionCaseDatatype( (ValueExpressionCase) valExpr);
  }
  // Cast value expression
  else if (valExpr instanceof ValueExpressionCast) {
    // doesn't do anything here, the datatype is already there
    //resolveValueExpressionCastDatatype( (ValueExpressionCast) valExpr);
  }
  // Function value expression
  else if (valExpr instanceof ValueExpressionFunction) {
    resolveValueExpressionFunctionDatatype( (ValueExpressionFunction) valExpr);
  }
  // Labeled duration expression
  else if (valExpr instanceof ValueExpressionLabeledDuration) {
    resolveValueExpressionLabeledDurationDatatype( (ValueExpressionLabeledDuration) valExpr);
  }
  // "Null" value expression
  else if (valExpr instanceof ValueExpressionNullValue) {
    resolveValueExpressionNullValueDatatype( (ValueExpressionNullValue) valExpr);
  }
  // Simple (literal) value expression
  else if (valExpr instanceof ValueExpressionSimple) {
    resolveValueExpressionSimpleDatatype( (ValueExpressionSimple) valExpr );
  }
  // Variable value expression
  else if (valExpr instanceof ValueExpressionVariable) {
    resolveValueExpressionVariableDatatype( (ValueExpressionVariable) valExpr);
  }
  else {
//      StatementHelper.logError(ValueExpressionHelper.class.getName()+
//                      "#resolveValueExpressionDatatype(QueryValueExpression) " +
//                      "not implemented for "+valExpr.getClass().getName());
  }
}

/**
 * Tries to recursively determine and set the datatype (DataType) of the given
 * value expression.  The datatype may be determined through information
 * inherent in this object (for example, by examining the content of
 * a simple value expression), or by checking the datatype of objects
 * that this object is associated with (for example, by getting the datatype
 * of a column to which the value expression is compared).
 * @param aValueExpr a value expression to resolve
 */
public static void resolveValueExpressionDatatypeRecursively( QueryValueExpression aValueExpr ) {
    Class valueExprType = QueryValueExpression.class;
    Class[] valueExprRefType = new Class[] {valueExprType};
    Set allValueExprRefs = 
        StatementHelper.getReferencesViaSpecificReferencePaths(aValueExpr, 
                        valueExprType, valueExprRefType);
    allValueExprRefs.add(aValueExpr);
    
    int valueExprsUnresolved = 0; 

    // try to resolve at least one ValueExpression per iteration and eventually
    // resolve the whole tree of ValueExpressions up to the top (or to the root)
    do
    {
        valueExprsUnresolved = allValueExprRefs.size();
        
        for (Iterator exprIt = allValueExprRefs.iterator(); exprIt.hasNext();)
        {
            QueryValueExpression valueExpr = (QueryValueExpression) exprIt.next();
            resolveValueExpressionDatatype(valueExpr);
            
            if (valueExpr.getDataType() != null) {
                exprIt.remove();
            }
        }
    }
    while (valueExprsUnresolved > allValueExprRefs.size());
    
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype of the given "default" value
 * expression.  Note: this value expression type comes from the DEFAULT
 * keyword in , which is used in Insert and Update statements.  It's
 * not a default value expression in the usual sense.
 * @param aValExpr a value expression to resolve
 * @param aTableRefList the current list of From clause table references
 * @param aDB a RDBDatabase object containing datatype information
 * @param aDBVersion an object containing database version information
 */
public static void resolveValueExpressionDefaultValueDatatype( ValueExpressionDefaultValue aValExpr) {
 
  // A SELECT result column can't be a value expression of type DEFAULT,
  // so we won't try to set the datatype.  DEFAULT is used in Insert and
  // Update statements.
}



/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype of the given function value
 * expression.
 * @param aValExpr a value expression to resolve
 * @param aTableRefList the current list of From clause table references
 * @param aDB a RDBDatabase object containing datatype information
 * @param aDBVersion an object containing database version information
 */
public static void resolveValueExpressionFunctionDatatype(ValueExpressionFunction aValExpr)
{
    String funcName = aValExpr.getName();

    DataType dataType = aValExpr.getDataType();

    if (dataType == null) {
    
        // Get the parameter list, then get the first parameter's datatype name
        // and length, if they are known.
        List paramList = aValExpr.getParameterList();
        int paramSize = paramList.size();
        List paramDataTypeList = new ArrayList();
        if (paramSize > 0)
        {
            for (int i = 0; i < paramSize; ++i)
            {
                QueryValueExpression valExpr = (QueryValueExpression) paramList.get(i);
                DataType paramDataType = null;
                if (valExpr != null)
                {
                    paramDataType = valExpr.getDataType();
                }
                paramDataTypeList.add(paramDataType);
    
            }
        }
    
        // ***TODO: load/use the appropriate function helper for the connected
        // database.
        dataType = getFuncReturnType(funcName);
    
        // NOT a fixed return type function
        // for first parameter
        DataType param1DataType = null;
        PrimitiveType param1PrimType = null;
        if (paramDataTypeList.size() > 0)
        {
            param1DataType = (DataType) paramDataTypeList.get(0);
        }
    
        if (param1DataType != null)
        {
            if(param1DataType instanceof PredefinedDataType ){
                param1PrimType = ((PredefinedDataType) param1DataType).getPrimitiveType();    
            }
            else{
                //it is a user defined type. Can't resolve
            }
            param1DataType = copyDataType(param1DataType);
        }
    
        // for second parameter
        DataType param2DataType = null;
        PrimitiveType param2PrimType = null;
        if (paramDataTypeList.size() > 1)
            param2DataType = (DataType) paramDataTypeList.get(1);
    
        if (param2DataType != null)
        {
            param2DataType = copyDataType(param2DataType);
            if(param2DataType instanceof PredefinedDataType ){
                param2PrimType = ((PredefinedDataType) param2DataType).getPrimitiveType();
             }
             else{
                //it is a user defined type. Can't resolve
            }
        }
    
        if (dataType == null)
        {
            if (FunctionReturnType.containsKey(funcName))
    
            {
                dataType = param1DataType;
            }
            else
            {
                // Handle the special cases.
                if (funcName != null) {
                    if (funcName.equals("AVG") || funcName.equals("SUM"))
                    {
                        if (param1DataType instanceof IntegerDataType) 
                        {
                            IntegerDataType intDataType = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();
                            intDataType.setPrimitiveType(PrimitiveType.BIGINT_LITERAL);
                            dataType = intDataType;
                        }
                        else if (param1DataType instanceof ApproximateNumericDataType) 
                        {
                            ApproximateNumericDataType doubleDataType = SQLDataTypesFactory.eINSTANCE.createApproximateNumericDataType();
                            doubleDataType.setPrimitiveType(PrimitiveType.DOUBLE_PRECISION_LITERAL);
                            dataType = doubleDataType;
                        }
                        else if (param1DataType instanceof FixedPrecisionDataType) {
                            FixedPrecisionDataType decimalDataType = SQLDataTypesFactory.eINSTANCE.createFixedPrecisionDataType();
                            decimalDataType.setPrimitiveType(PrimitiveType.DECIMAL_LITERAL);
                            decimalDataType.setPrecision(31);
                            int scale = ((FixedPrecisionDataType) param1DataType).getScale();
                            decimalDataType.setScale(scale);
                            dataType = decimalDataType;
                        }
                        else {
                            dataType = copyDataType(param1DataType);
                        }
                    }
                    else if(funcName.equals("CHAR"))
                    {
                        dataType = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
                        // an arbitrary default length
                        int length = 254;
                        if (param1DataType instanceof CharacterStringDataType
                                && param1DataType != null)
                        {
                            length = ((CharacterStringDataType)param1DataType).getLength();
                        }
                        else if (param1DataType instanceof DateDataType)
                        {
                            length = 10;
                        }
                        else if (param1DataType instanceof TimeDataType)
                        {
                            if(param1PrimType == PrimitiveType.TIME_LITERAL)
                            {
                                length = 8;
                            }
                            else if(param1PrimType == PrimitiveType.TIMESTAMP_LITERAL)
                            {
                                length = 26;
                            }
                        }
                        else if (param1DataType instanceof IntegerDataType)
                        {
                            if (param1PrimType == PrimitiveType.SMALLINT_LITERAL && param2PrimType == PrimitiveType.SMALLINT_LITERAL)
                            {
                                length = 6;
                            }
                            else if (param1PrimType == PrimitiveType.BIGINT_LITERAL && param2PrimType != PrimitiveType.BIGINT_LITERAL)
                            {
                                length = 20;
                            }
                            else
                                length = 11;
                        }
                        else if (param1DataType instanceof FixedPrecisionDataType 
                                && param1DataType != null)
                        {
                            length = ((FixedPrecisionDataType)param1DataType).getPrecision() + 2;

                        }
                        else if (param1DataType instanceof ApproximateNumericDataType)
                        {
                            length = 24;
                        }
                        length = length > 254 ? 254 : length;

                        ((CharacterStringDataType)dataType).setLength(length);
                    }
                    else if (funcName.equals("CEILING") || funcName.equals("CEIL") || funcName.equals("FLOOR"))
                    {
                        if (param1DataType instanceof FixedPrecisionDataType)
                        {
                            int precision = ((FixedPrecisionDataType) param1DataType).getPrecision();
                            if (precision < 31)
                                ((FixedPrecisionDataType) param1DataType).setPrecision(precision + 1);
                            ((FixedPrecisionDataType) param1DataType).setScale(0);
                        }
                        dataType = param1DataType;
                    }
                    else if (funcName.equals("MOD"))
                    {
                        dataType = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();

                        if (param1DataType instanceof IntegerDataType || param2DataType instanceof IntegerDataType)
                        {
                            if (param1PrimType == PrimitiveType.SMALLINT_LITERAL && param2PrimType == PrimitiveType.SMALLINT_LITERAL)
                            {
                                ((IntegerDataType) dataType).setPrimitiveType(PrimitiveType.SMALLINT_LITERAL);
                            }
                            else if (param1PrimType != PrimitiveType.BIGINT_LITERAL && param2PrimType != PrimitiveType.BIGINT_LITERAL)
                            {
                                ((IntegerDataType) dataType).setPrimitiveType(PrimitiveType.INTEGER_LITERAL);
                            }
                            else
                                ((IntegerDataType) dataType).setPrimitiveType(PrimitiveType.BIGINT_LITERAL);
                        }
                    }
                    else if(funcName.equals("MULTIPLY_ALT"))
                    {
                        dataType = SQLDataTypesFactory.eINSTANCE.createFixedPrecisionDataType();
                        ((FixedPrecisionDataType) dataType).setPrimitiveType(PrimitiveType.DECIMAL_LITERAL);

                        if (param1PrimType == PrimitiveType.DECIMAL_LITERAL && param2PrimType == PrimitiveType.DECIMAL_LITERAL)
                        {
                            int p1 = ((FixedPrecisionDataType)param1DataType).getPrecision();
                            int s1 = ((FixedPrecisionDataType)param1DataType).getScale();

                            int p2 = ((FixedPrecisionDataType)param2DataType).getPrecision();
                            int s2 = ((FixedPrecisionDataType)param2DataType).getScale();

                            int p = (p1 + p2) > 31 ? 31 : (p1 + p2);
                            int s = s1 + s2;
                            if(s != 0)
                            {
                                if(p1 + p2 > 31)
                                {
                                    int t1 = (s > 3 ? 3 : s);
                                    int t2 = 31 - (p1 - s1 + p2 -s2);
                                    s = t1 > t2 ? t1 : t2;
                                }
                                else
                                {
                                    s = s > 31 ? 31 : s;
                                }
                            }
                            ((FixedPrecisionDataType) dataType).setPrecision(p);
                            ((FixedPrecisionDataType) dataType).setScale(s);
                        }
                        else
                        {
                            ((FixedPrecisionDataType)dataType).setPrecision(31);
                            ((FixedPrecisionDataType)dataType).setScale(10);
                        }                
                    }
                    else if (funcName.equals("POWER"))
                    {
                        if (param1DataType instanceof IntegerDataType || param2DataType instanceof IntegerDataType)
                        {
                            dataType = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();

                            if (param1PrimType != PrimitiveType.BIGINT_LITERAL && param2PrimType != PrimitiveType.BIGINT_LITERAL)
                            {
                                ((IntegerDataType) dataType).setPrimitiveType(PrimitiveType.INTEGER_LITERAL);
                            }
                            else
                            {
                                ((IntegerDataType) dataType).setPrimitiveType(PrimitiveType.BIGINT_LITERAL);
                            }
                        }
                        else
                        {
                            dataType = SQLDataTypesFactory.eINSTANCE.createApproximateNumericDataType();
                            ((ApproximateNumericDataType) dataType).setPrimitiveType(PrimitiveType.DOUBLE_PRECISION_LITERAL);
                        }
                    }
                    else if (funcName.equals("ROUND"))
                    {
                        if (param1DataType instanceof FixedPrecisionDataType)
                        {
                            int precision = ((FixedPrecisionDataType) param1DataType).getPrecision();
                            if (precision < 31)
                                ((FixedPrecisionDataType) param1DataType).setPrecision(precision + 1);
                        }
                        dataType = param1DataType;
                    }
                    else if (funcName.equals("SIGN"))
                    {
                        if (param1DataType instanceof IntegerDataType)
                        {
                            dataType = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();

                            ((IntegerDataType) dataType).setPrimitiveType(((IntegerDataType) param1DataType).getPrimitiveType());

                        }
                        else
                        {
                            dataType = SQLDataTypesFactory.eINSTANCE.createApproximateNumericDataType();
                            ((ApproximateNumericDataType) dataType).setPrimitiveType(PrimitiveType.DOUBLE_PRECISION_LITERAL);
                        }
                    }
                    else if (funcName.equals("CONCAT"))
                    {
                        dataType = concatCharStringDataTypePromotion(param1DataType, param2DataType);
                    }
                    else
                    {
                        dataType = param1DataType;
                    }
                }
            }
        }
    
        aValExpr.setDataType(copyDataType(dataType));
    }
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype of the given labeled duration value
 * expression.
 * @param aValExpr a value expression to resolve
 * @param aTableRefList the current list of From clause table references
 * @param aDB a RDBDatabase object containing datatype information
 * @param aDBVersion an object containing database version information
 */
public static void resolveValueExpressionLabeledDurationDatatype( ValueExpressionLabeledDuration aValExpr) {
  // The datatype of a labelled duration is always DECIMAL(15,0).
  //DataType datatype = DatabaseHelper.getDataType( Types.DECIMAL, "DECimal", "15", "0", aDB );
  //TODO:review type
  PredefinedDataType datatype = SQLDataTypesFactory.eINSTANCE.createFixedPrecisionDataType();
  datatype.setPrimitiveType(PrimitiveType.DECIMAL_LITERAL);  
  aValExpr.setDataType( datatype );
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype (DataType) of each
 * of the ValueExpressions in the given list of value expression
 * objects.  This is done by either examing the literal value of
 * the expression and determining the datatype from that (if it's
 * a literal value), finding the datatype of the associated Column
 * datatype (if it's a column expression), or locating a "neighboring"
 * value expressions that can determine the datatype of the current
 * value expression.
 * @param aValueExprList a list of value expressions to resolve
 * @param aTableRefList the current list of From clause table references
 * @param aDB a RDBDatabase object containing datatype information
 * @param aDBVersion an object containing database version information
 */
public static void resolveValueExpressionListDatatypes( List aValExprList) {
  QueryValueExpression valExpr;
  int valExprCount = aValExprList.size();
  int resolvedCount = 0;
  int prevResolvedCount = -1;
  Iterator valExprListIter;

  // Resolving all the datatypes may take more than one pass over
  // the value expression list.  We want to keep trying until we
  // have resolved all the expressions or we are no longer adding
  // to the number of resolved expressions.
  do {
    prevResolvedCount = resolvedCount;
    valExprListIter = aValExprList.iterator();
    while( valExprListIter.hasNext() ) {
      valExpr = (QueryValueExpression) valExprListIter.next();
      if (valExpr.getDataType() == null) {
        resolveValueExpressionDatatype( valExpr);

        // Check if we managed to resolve the datatype during this
        // pass.  If so, remember that we did so.
        if (valExpr.getDataType() != null) {
          resolvedCount++;
        }
      }
    }

  // Terminate our check when we have either resolved all the expression
  // datatypes or are no longer making any progress.
  } while (resolvedCount < valExprCount && resolvedCount != prevResolvedCount);

  // @d301485 bgp 06Feb2004 - begin
  // Set a default datatype for any value expressions that didn't
  // get resolved.  This prevents null pointer exceptions later.
  if (resolvedCount < valExprCount) {
      
      //TODO: what's the default type?
    DataType memberType = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    valExprListIter = aValExprList.iterator();
    while (valExprListIter.hasNext()) {
      valExpr = (QueryValueExpression) valExprListIter.next();
      if (valExpr.getDataType() == null) {
        valExpr.setDataType( memberType );
      }
    }
  }
  // @d301485 bgp 06Feb2004 - end
}


/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype of the given "null" value
 * expression.  Note: this value expression type comes from the NULL
 * keyword in , which is used in Insert and Update statements.  It's
 * not a null value expression in the usual sense.
 * @param aValExpr a value expression to resolve
 * @param aTableRefList the current list of From clause table references
 * @param aDB a RDBDatabase object containing datatype information
 * @param aDBVersion an object containing database version information
 */
public static void resolveValueExpressionNullValueDatatype( ValueExpressionNullValue aValExpr) {
  // A NULL insert/update value has no datatype.  A result column can't
  // be a value expression of type NULL.
}


/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype of the given simple value
 * expression.
 * @param aValExpr a value expression to resolve
 */
public static void resolveValueExpressionSimpleDatatype( ValueExpressionSimple aValExpr ) {
  String value = aValExpr.getValue();
  DataType datatype = null;
  if (value != null && value.length() > 0) {
      datatype = getPredefinedDataTypeForSimpleValue(value,aValExpr.getUnaryOperator());
  }

  if (datatype != null) {
    aValExpr.setDataType( datatype );
  }
}

/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Tries to determine and set the datatype of the given variable value
 * expression, using the given list of table references.
 * @param aVarExpr a value expression to resolve
 */
public static void resolveValueExpressionVariableDatatype( ValueExpressionVariable aVarExpr) {
  // @d281231 bgp 27May2003 - method rewritten to repace MOF "isSet" functions with EMF "get"
  //                          and added "resolved" logic
  boolean resolved = false;
  
  // bugfix: wsdbu00023418: "column = (:hostvar)"
  // if we have our hostvariable nested in parenthesis "(:hostVar)" we want to
  // figure out the data type of the nested expression instead and
  // set that data type to the hostVarExpr
  ValueExpressionVariable varExpr = aVarExpr; // the varExpr we gonna set the data type for
  QueryValueExpression aValExpr = aVarExpr; // the valExpr we gonna work with, could be the nesting expr of the given variable
  // bugfix: wsdbu00032486 - hostvar not resolved in: "INSERT INTO MYSCHEMA.STAFF(ID) VALUES ( (:id) )"
  // get the top nesting value expression that contains this hostvariable
  // e.g.  "column1 = ((((:hostVar))))"
  while (aValExpr.getNest() != null) {
      // if we have a nested hostVar, we work on the find out the data type of the nest
      aValExpr = aValExpr.getNest();
  }
  
  
  // A variable may appear on the left or right side of a basic
  // predicate. It's more likely to appear on the right side (for
  // example "DEPTNO = :var1") than on the left side ":var1 = DEPTNO",
  // but we need to handle both cases.  Try to get the datatype from
  // the value expresson on the other side of the predicate.

  // Case: var is left side of basic predicate
  PredicateBasic predBasic = aValExpr.getBasicLeft();
  if (predBasic != null) {
    QueryValueExpression rightExpr = predBasic.getRightValueExpr();
    copyDataType( rightExpr, varExpr );
    resolved = true;
  }

  // Case: var is right side of a basic predicate.
  else {
    predBasic = aValExpr.getBasicRight();
    if (predBasic != null) {
      QueryValueExpression leftExpr = predBasic.getLeftValueExpr();
      copyDataType( leftExpr, varExpr );
      resolved = true;
    }
  }

  if (resolved == false) {
    // A var may appear in a Between predicate, as the left side,
    // or one of the two right-hand sides.  Handle these in a similar
    // manner to the basic predicate.

    // Case: var is left side of a Between predicate
    PredicateBetween predBetween = aValExpr.getBetweenLeft();
    if (predBetween != null) {
    // Look for a column in either right 1 or right 2.
      QueryValueExpression rightExpr1 = predBetween.getRightValueExpr1();
      QueryValueExpression rightExpr2 = predBetween.getRightValueExpr2();
      copyDataType( rightExpr1, varExpr );
      if (aValExpr.getDataType() == null) {
        copyDataType( rightExpr2, varExpr );
      }
      resolved = true;
    }

    // Case: var is the first value of the right side of a Between predicate
    else {
      predBetween = aValExpr.getBetweenRight1();
      if ( predBetween != null) {
        QueryValueExpression leftExpr = predBetween.getLeftValueExpr();
        copyDataType( leftExpr, varExpr );
        resolved = true;
      }

      // Case: var is the second value of the right side of a Between predicate
      else {
        predBetween = aValExpr.getBetweenRight2();
        if (predBetween != null) {
          QueryValueExpression leftExpr = predBetween.getLeftValueExpr();
          copyDataType( leftExpr, varExpr );
          resolved = true;
        }
      }
    }
  }

  if (resolved == false) {
    // A var may appear on the left or right side of an "IN" predicate.
    // At this time the only flavor of In predicate that we support is
    // the "In Value List" type.  The right side of this kind of In is
    // a list of value expressions.

    // Case: var is the left side on an In predicate
    PredicateInValueList predIn = aValExpr.getInValueListLeft();
    if (predIn != null) {
      List rightExprList = predIn.getValueExprList();//getRightValueExpr();
      Iterator listIter = rightExprList.iterator();
      QueryValueExpression inListMember;
      while (listIter.hasNext() && aValExpr.getDataType() == null) {
        inListMember = (QueryValueExpression) listIter.next();
        copyDataType( inListMember, varExpr );
        resolved = true;
      }
    }

    // Case: var is in the list on the right side of an In predicate
    else {
      predIn = aValExpr.getInValueListRight();
      if (predIn != null) {
        QueryValueExpression leftExpr = predIn.getValueExpr();//getLeftValueExpr();
        copyDataType( leftExpr, varExpr );
        resolved = true;
      }
    }
  }

  if (resolved == false) {
    // A var may appear in a "LIKE" predicate as the left or right side.
    // Note: appearing as the right side of a Like predicate is not the
    // same as being embedded within a string expression that appears as
    // the right side of a Like predicate, which is handled elsewhere.
    // An example of the later is "COL1 LIKE '%' || CAST( :var1 AS VARCHAR(10)) || '%'".  This
    // is likely to be a much more common case than the var being the
    // only thing on the right side.

    // Case: var is the left ("matching") side of a Like predicate
    PredicateLike predLikeMatching = aValExpr.getLikeMatching();
    if (predLikeMatching != null) {
      // The datatype is set directly in this case.
      //DataType datatype = DatabaseHelper.getDataType( Types.VARCHAR, "VARCHAR", "255", "0", aDB );
      // TODO: review this type
      PredefinedDataType datatype = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
      datatype.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
      aValExpr.setDataType( datatype );
      resolved = true;
    }

    // Case: var is the right ("pattern") side of a Like predicate
    else {
      PredicateLike predLikePattern = aValExpr.getLikePattern();
      if (predLikePattern != null) {
        QueryValueExpression leftExpr = predLikePattern.getMatchingValueExpr();
        copyDataType( leftExpr, varExpr );
        resolved = true;
      }
    }
  }

  if (resolved == false) {
    // A var may appear as the left side of a IS NULL predicate, but
    // that isn't very likely.  How would one determine the datatype in this
    // context anyway?  Set the type to INTEGER just to make it non-null.

    // Case: var is the left side of an Is Null predicate
    PredicateIsNull predNull = aValExpr.getPredicateNull();
    if (predNull != null) {
      //DataType datatype = DatabaseHelper.getDataType( Types.INTEGER, "INTEGER", "10", "0", aDB );
      PredefinedDataType datatype = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();
      datatype.setPrimitiveType(PrimitiveType.INTEGER_LITERAL);
      aValExpr.setDataType( datatype );
      resolved = true;
    }
  }

  if (resolved == false) {
    // A var may appear as a value in an insert row in a Insert statement,
    // or in the assignment clause of an Update statement, or in a When Not Matched clause
    // in a Merge statement.
    // We should be able to get the datatype from the corresponding target
    // column.

    // Case: var is a value in an Values Row, which might be attached to an Insert
    // statement or a QueryValues (a kind of table reference).
    ValuesRow valRow = aValExpr.getValuesRow();
    if (valRow != null) {
      List valExprList = valRow.getExprList();
      int valExprIndex = valExprList.indexOf( aValExpr );
      
      // First check for and process the Insert statement case.
      QueryInsertStatement insertStmt = valRow.getInsertStatement();
      if (insertStmt != null) {
        List intoColList = insertStmt.getTargetColumnList();
        if (intoColList != null && intoColList.size() > valExprIndex) { 
          ValueExpressionColumn intoCol = (ValueExpressionColumn) intoColList.get( valExprIndex );
          copyDataType( intoCol, varExpr );
          resolved = true;
        }
        else {
          TableInDatabase intoTable = insertStmt.getTargetTable();
          if (intoTable != null
           && intoTable.getColumnList() != null
           && intoTable.getColumnList().size() > valExprIndex)
          {
            intoColList = intoTable.getColumnList();
            ValueExpressionColumn intoCol = (ValueExpressionColumn) intoColList.get( valExprIndex );
            copyDataType( intoCol, varExpr );
            resolved = true;
          }
        }
      }
      else {
          // Otherwise check to see if part of a VALUES table expression.  If the VALUES table expr
          // is part of a MERGE statement, then we might be able to get the column datatype from
          // the target table.
          QueryValues queryValues = valRow.getQueryValues();
          if (queryValues != null) {
              MergeSourceTable mergeSourceTable = queryValues.getMergeSourceTable();
              if (mergeSourceTable != null) {
                  QueryMergeStatement mergeStmt = mergeSourceTable.getMergeStatement();
                  if (mergeStmt != null) {
                      MergeTargetTable mergeTargetTbl = mergeStmt.getTargetTable();
                      if (mergeTargetTbl != null) {
                          TableExpression tableExpr = mergeTargetTbl.getTableExpr();
                          if (tableExpr instanceof TableInDatabase) {
                              TableInDatabase tableInDB = (TableInDatabase) tableExpr;
                              if (tableInDB.getColumnList() != null && tableInDB.getColumnList().size() > valExprIndex) {
                                List colList = tableInDB.getColumnList();
                                ValueExpressionColumn col = (ValueExpressionColumn) colList.get( valExprIndex );
                                copyDataType( col, varExpr );
                                resolved = true;
                              }
                          }
                      }
                  }
              }
          }
      }
    }

    // Case: var is in an assignment clause of an Update statement
    else {
      UpdateSourceExprList updateSrcExprList = aValExpr.getUpdateSourceExprList();
      if (updateSrcExprList != null) {
        UpdateAssignmentExpression assignExpr = updateSrcExprList.getUpdateAssignmentExpr();
        EList valExprList = updateSrcExprList.getValueExprList();
        int valExprIndex = valExprList.indexOf( aValExpr );
        EList assignColList = assignExpr.getTargetColumnList();
        ValueExpressionColumn assignCol = (ValueExpressionColumn) assignColList.get( valExprIndex );
        copyDataType( assignCol, varExpr );
        resolved = true;
      }
    }
  }

  // @d301485 bgp 06Feb2004 - begin
  if (resolved == false) {
    // A var may appear as the cast expression of a CAST function.
    ValueExpressionCast valCast = aValExpr.getValueExprCast();
    if (valCast != null) {
      copyDataType( valCast, varExpr );
      resolved = true;
    }
  }
  // @d301485 bgp 06Feb2004 - end

  if (resolved == false) {
    // A var may appear within a larger (combined) value expression.
    // This case requires further checking to properly determine the
    // datatype.

    // Case: var is part of a combined value expression
    if ( aValExpr.getValueExprCombinedLeft() != null
      || aValExpr.getValueExprCombinedRight() != null ) {
      // Get the "combined" val expr object that this expression is
      // part of, plus the val expr on the other side of the combined.
      ValueExpressionCombined valExprCombined = null;
      QueryValueExpression otherExpr = null;
      boolean isLeft = true;
      if (aValExpr.getValueExprCombinedLeft() != null) {
        valExprCombined = aValExpr.getValueExprCombinedLeft();
        otherExpr = valExprCombined.getRightValueExpr();
      }
      else if (aValExpr.getValueExprCombinedRight() != null) {
        valExprCombined = aValExpr.getValueExprCombinedRight();
        otherExpr = valExprCombined.getLeftValueExpr();
        isLeft = false;
      }

      // If we have the other elements, go on with our datatype search.
      if (valExprCombined != null && otherExpr != null) {
        // A common case is that a var appears in a string expression
        // like this:  "'%' || :var1 || '%'".  If we find we're in a
        // string expression like this, follow the tree up to see if
        // this expression is the right side of a LIKE predicate, and
        // if so, get the datatype of the expr on the other side of
        // the predicate.
        if (valExprCombined.getCombinedOperator().getValue() == ValueExpressionCombinedOperator.CONCATENATE) {
          if (isLeft == true) {
            resolveValueExpressionConcatDatatype( valExprCombined, aValExpr, otherExpr);
          }
          else {
            resolveValueExpressionConcatDatatype( valExprCombined, otherExpr, aValExpr);
          }
          resolved = true;
        }
      }
    }
  }
}


/**
 * Returns a mapping of <code>DataType</code> s (value) for SQL built-in
 * function names (key)
 * 
 * @return <code>HashMap</code> keys: <code>String</code> function name in
 *         upper case, values: {@link DataType}
 */
protected static HashMap createFunctionReturnTypeMap()
{
    HashMap functionReturnTypes = new HashMap();
    ApproximateNumericDataType doubleType = SQLDataTypesFactory.eINSTANCE.createApproximateNumericDataType();
    doubleType.setPrimitiveType(PrimitiveType.DOUBLE_PRECISION_LITERAL);

    ApproximateNumericDataType realType = SQLDataTypesFactory.eINSTANCE.createApproximateNumericDataType();
    realType.setPrimitiveType(PrimitiveType.REAL_LITERAL);

    IntegerDataType bigIntType = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();
    bigIntType.setPrimitiveType(PrimitiveType.BIGINT_LITERAL);

    IntegerDataType intType = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();
    intType.setPrimitiveType(PrimitiveType.INTEGER_LITERAL);

    IntegerDataType smallIntType = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();
    smallIntType.setPrimitiveType(PrimitiveType.SMALLINT_LITERAL);

    BinaryStringDataType blobType = SQLDataTypesFactory.eINSTANCE.createBinaryStringDataType();
    blobType.setPrimitiveType(PrimitiveType.BINARY_LARGE_OBJECT_LITERAL);

    CharacterStringDataType clobType = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    clobType.setPrimitiveType(PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL);

    DateDataType dateType = SQLDataTypesFactory.eINSTANCE.createDateDataType();
    dateType.setPrimitiveType(PrimitiveType.DATE_LITERAL);

    TimeDataType timeType = SQLDataTypesFactory.eINSTANCE.createTimeDataType();
    timeType.setPrimitiveType(PrimitiveType.TIME_LITERAL);

    TimeDataType timeStampType = SQLDataTypesFactory.eINSTANCE.createTimeDataType();
    timeStampType.setPrimitiveType(PrimitiveType.TIMESTAMP_LITERAL);

    /*
     * aggregate functions
     */
    functionReturnTypes.put("CORRELATION", doubleType);
    functionReturnTypes.put("CORR", doubleType);

    functionReturnTypes.put("COUNT", bigIntType);
    functionReturnTypes.put("COUNT_BIG", bigIntType);

    functionReturnTypes.put("COVARIANCE", doubleType);
    functionReturnTypes.put("COVAR", doubleType);

    // if and only if the return data type is the same as the argument
    // set value of the map to null
    functionReturnTypes.put("MAX", null);
    functionReturnTypes.put("MIN", null);

    functionReturnTypes.put("STDDEV", doubleType);
    functionReturnTypes.put("VARIANCE", doubleType);
    functionReturnTypes.put("VAR", doubleType);

    /*
     * scalar functions
     */
    functionReturnTypes.put("ABS", null);
    functionReturnTypes.put("ABSVAL", null);
    functionReturnTypes.put("ACOS", doubleType);

    CharacterStringDataType varchar128 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    varchar128.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
    varchar128.setLength(128);

    functionReturnTypes.put("APPLICATION_ID", varchar128);
    functionReturnTypes.put("ASCII", intType);
    functionReturnTypes.put("ASIN", doubleType);
    functionReturnTypes.put("ATAN", doubleType);
    functionReturnTypes.put("ATAN2", doubleType);
    functionReturnTypes.put("BIGINT", bigIntType);
    functionReturnTypes.put("BLOB", blobType);

    CharacterStringDataType char1 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    char1.setPrimitiveType(PrimitiveType.CHARACTER_LITERAL);
    char1.setLength(1);

    functionReturnTypes.put("CHR", char1);
    functionReturnTypes.put("CLOB", clobType);
    functionReturnTypes.put("COALESCE", char1);
    functionReturnTypes.put("COS", doubleType);
    functionReturnTypes.put("COSH", doubleType);
    functionReturnTypes.put("COT", doubleType);
    functionReturnTypes.put("DATE", dateType);
    functionReturnTypes.put("DAY", bigIntType);

    CharacterStringDataType varchar100 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    varchar100.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
    varchar100.setLength(100);

    functionReturnTypes.put("DAYNAME", varchar100);
    functionReturnTypes.put("DAYOFWEEK", intType);
    functionReturnTypes.put("DAYOFWEEK_ISO", intType);
    functionReturnTypes.put("DAYOFYEAR", intType);
    functionReturnTypes.put("DAYS", intType);
    functionReturnTypes.put("DBCLOB", clobType);
    functionReturnTypes.put("DBPARTITIONNUM", intType);
    functionReturnTypes.put("DEGREES", doubleType);
    functionReturnTypes.put("DIFFERENCE", intType);

    CharacterStringDataType varchar254 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    varchar254.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
    varchar254.setLength(254);

    CharacterStringDataType varchar4 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    varchar4.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
    varchar4.setLength(4);

    CharacterStringDataType varchar200 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    varchar200.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
    varchar200.setLength(200);

    CharacterStringDataType varchar20 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    varchar20.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
    varchar20.setLength(20);

    functionReturnTypes.put("DLCOMMENT", varchar254);
    functionReturnTypes.put("DLLINKTYPE", varchar4);
    functionReturnTypes.put("DLNEWCOPY", varchar200);
    functionReturnTypes.put("DLPREVIOUSCOPY", varchar200);
    functionReturnTypes.put("DLURLCOMPLETE", varchar4);
    functionReturnTypes.put("DLURLCOMPLETEONLY", varchar4);
    functionReturnTypes.put("DLURLCOMPLETEWRITE", varchar254);
    functionReturnTypes.put("DLURLPATH", varchar4);
    functionReturnTypes.put("DLURLPATHONLY", varchar4);
    functionReturnTypes.put("DLURLPATHWRITE", varchar4);
    functionReturnTypes.put("DLURLSCHEME", varchar20);
    functionReturnTypes.put("DLURLSERVER", varchar254);

    functionReturnTypes.put("DOUBLE", doubleType);
    functionReturnTypes.put("EVENT_MON_STATE", intType);
    functionReturnTypes.put("EXP", doubleType);
    functionReturnTypes.put("FLOAT", doubleType);

    CharacterStringDataType varchar32 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    varchar32.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
    varchar32.setLength(32);
    functionReturnTypes.put("GETHINT", varchar32);

    CharacterStringDataType varchar1024 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    varchar1024.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
    varchar1024.setLength(1024);
    functionReturnTypes.put("GET_ROUTINE_OPTS", varchar1024);

    functionReturnTypes.put("HASHEDVALUE", intType);
    functionReturnTypes.put("HOUR", bigIntType);

    FixedPrecisionDataType decimalType_31_0 = SQLDataTypesFactory.eINSTANCE.createFixedPrecisionDataType();
    decimalType_31_0.setPrimitiveType(PrimitiveType.DECIMAL_LITERAL);
    decimalType_31_0.setPrecision(31);
    decimalType_31_0.setScale(0);
    functionReturnTypes.put("IDENTITY_VAL_LOCAL", varchar1024);

    functionReturnTypes.put("INTEGER", bigIntType);
    functionReturnTypes.put("INT", bigIntType);             // fix wsdbu00045539
    functionReturnTypes.put("JULIAN_DAY", intType);

    functionReturnTypes.put("LCASE", null);
    functionReturnTypes.put("LOWER", null);

    functionReturnTypes.put("LENGTH", bigIntType);
    functionReturnTypes.put("LN", doubleType);
    functionReturnTypes.put("LOCATE", intType);
    functionReturnTypes.put("LOG", doubleType);
    functionReturnTypes.put("LOG10", doubleType);
    functionReturnTypes.put("LTRIM", null);
    functionReturnTypes.put("MICROSECOND", bigIntType);
    functionReturnTypes.put("MIDNIGHT_SECONDS", intType);
    functionReturnTypes.put("MINUTE", bigIntType);
    functionReturnTypes.put("MONTH", bigIntType);
    functionReturnTypes.put("MONTHNAME", varchar100);
    functionReturnTypes.put("NULLIF", null);
    functionReturnTypes.put("POSSTR", bigIntType);
    functionReturnTypes.put("QUARTER", intType);
    functionReturnTypes.put("RADIANS", doubleType);
    functionReturnTypes.put("RAND", doubleType);
    functionReturnTypes.put("REAL", realType);
    functionReturnTypes.put("RTRIM", null);
    functionReturnTypes.put("SECOND", bigIntType);
    functionReturnTypes.put("SIN", doubleType);
    functionReturnTypes.put("SINH", doubleType);
    functionReturnTypes.put("SMALLINT", smallIntType);

    CharacterStringDataType char4 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    char4.setPrimitiveType(PrimitiveType.CHARACTER_LITERAL);
    char4.setLength(4);
    functionReturnTypes.put("SOUNDEX", char4);

    CharacterStringDataType varchar4000 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    varchar4000.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
    varchar4000.setLength(4000);
    functionReturnTypes.put("SPACE", varchar4000);

    functionReturnTypes.put("SQRT", doubleType);
    functionReturnTypes.put("SOUNDEX", char4);
    functionReturnTypes.put("TABLE_NAME", varchar128);
    functionReturnTypes.put("TABLE_SCHEMA", varchar128);
    functionReturnTypes.put("TAN", doubleType);
    functionReturnTypes.put("TANH", doubleType);
    functionReturnTypes.put("TIME", timeType);
    functionReturnTypes.put("TIMESTAMP", timeStampType);
    functionReturnTypes.put("TIMESTAMP_FORMAT", timeStampType);
    functionReturnTypes.put("TIMESTAMP_ISO", timeStampType);
    functionReturnTypes.put("TIMESTAMPDIFF", intType);
    functionReturnTypes.put("TO_DATE", timeStampType);
    functionReturnTypes.put("TRUNCATE", null);
    functionReturnTypes.put("TRUNC", null);
    functionReturnTypes.put("TYPE_ID", intType);

    CharacterStringDataType varchar18 = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
    varchar18.setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
    varchar18.setLength(18);
    functionReturnTypes.put("TYPE_NAME", varchar18);

    functionReturnTypes.put("TYPE_SCHEMA", varchar128);
    functionReturnTypes.put("UCASE", null);
    functionReturnTypes.put("UPPER", varchar18);
    functionReturnTypes.put("VALUE", null);

    functionReturnTypes.put("WEEK", intType);
    functionReturnTypes.put("WEEK_ISO", intType);
    functionReturnTypes.put("YEAR", bigIntType);
    
    return functionReturnTypes;
}




/**
 * Get the data type of result of the string binary operation of "left" and
 * "right" 
 * ASSUMPTION: the DataType of "left" should be greater than or equal to the 
 * "right". And both of them are not null. Otherwise, null value will be returned.
 * @param left
 * @param right
 * @return
 */
private static DataType concatCharStringDataTypePromotion(DataType left, DataType right)
{
    DataType retType = null;
    
    if(left != null && right != null)
    {
        PrimitiveType leftPrim = ((PredefinedDataType)left).getPrimitiveType();
        PrimitiveType rightPrim = ((PredefinedDataType)right).getPrimitiveType();
        
        if(left instanceof CharacterStringDataType && right instanceof CharacterStringDataType)
        {
            retType = copyDataType(left);
            int len = ((CharacterStringDataType)left).getLength();
            len += ((CharacterStringDataType)right).getLength();
            ((CharacterStringDataType)retType).setLength(len);
            if(leftPrim == PrimitiveType.CHARACTER_LITERAL && rightPrim == PrimitiveType.CHARACTER_LITERAL && len < 255)
                ((CharacterStringDataType)retType).setPrimitiveType(PrimitiveType.CHARACTER_LITERAL);            
            else
                ((CharacterStringDataType)retType).setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);            
        }
    }
    if(retType == null)
    {
        retType = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
        ((CharacterStringDataType)retType).setPrimitiveType(PrimitiveType.CHARACTER_VARYING_LITERAL);
        ((CharacterStringDataType)retType).setLength(100);
    }

    return retType;
}

/**
 * Get the data type of result of the numeric binary operation of "left" and
 * "right" 
 * ASSUMPTION: the DataType of "left" should be greater than or equal to the 
 * "right". And both of them are not null. Otherwise, null value will be returned.
 * 
 * @param left a type that is "greater than or equal to" the right
 * @param right a type that is "less than or equal to" the left
 * @return
 */
private static DataType doNumericDataTypePromotion(DataType left, DataType right)
{
    DataType retType = null;
    
    if(left != null && right != null)
    {
        PrimitiveType leftPrim = ((PredefinedDataType)left).getPrimitiveType();
        PrimitiveType rightPrim = ((PredefinedDataType)right).getPrimitiveType();
        
        if(leftPrim == PrimitiveType.SMALLINT_LITERAL)
        {
            if(rightPrim == PrimitiveType.SMALLINT_LITERAL)
                retType = copyDataType(left);
        }
        else if(leftPrim == PrimitiveType.INTEGER_LITERAL)
        {
            if(rightPrim  == PrimitiveType.SMALLINT_LITERAL
                    || rightPrim  == PrimitiveType.INTEGER_LITERAL)
                retType = copyDataType(left);
        }
        else if(leftPrim == PrimitiveType.BIGINT_LITERAL)
        {
            if(rightPrim  == PrimitiveType.SMALLINT_LITERAL
                    || rightPrim  == PrimitiveType.INTEGER_LITERAL
                    || rightPrim  == PrimitiveType.BIGINT_LITERAL)
                retType = copyDataType(left);
        }
        else if(leftPrim == PrimitiveType.DECIMAL_LITERAL)
        {
            if(rightPrim  == PrimitiveType.SMALLINT_LITERAL)
            {
                retType = copyDataType(left);
                int precision = ((FixedPrecisionDataType)left).getPrecision();
                int scale = ((FixedPrecisionDataType)left).getScale();
                
                precision = scale + ((precision - scale) > 5 ? (precision - scale) : 5);
                precision = (precision > 31 ? 31 : precision);
                ((FixedPrecisionDataType)retType).setPrecision(precision);
            }
            else if(rightPrim  == PrimitiveType.INTEGER_LITERAL)
            {
                retType = copyDataType(left);
                int precision = ((FixedPrecisionDataType)left).getPrecision();
                int scale = ((FixedPrecisionDataType)left).getScale();
                
                precision = scale + ((precision - scale) > 11 ? (precision - scale) : 11);
                precision = (precision > 31 ? 31 : precision);
                ((FixedPrecisionDataType)retType).setPrecision(precision);
            }
            else if(rightPrim  == PrimitiveType.BIGINT_LITERAL)
            {
                retType = copyDataType(left);
                int precision = ((FixedPrecisionDataType)left).getPrecision();
                int scale = ((FixedPrecisionDataType)left).getScale();
                
                precision = scale + ((precision - scale) > 19 ? (precision - scale) : 19);
                precision = (precision > 31 ? 31 : precision);
                ((FixedPrecisionDataType)retType).setPrecision(precision);
                
            }
            else if(rightPrim  == PrimitiveType.DECIMAL_LITERAL)
            {
                retType = copyDataType(left);
                int pleft = ((FixedPrecisionDataType)left).getPrecision();
                int sleft = ((FixedPrecisionDataType)left).getScale();

                int pright = ((FixedPrecisionDataType)right).getPrecision();
                int sright = ((FixedPrecisionDataType)right).getScale();

                int s = (sleft > sright ? sleft : sright);
                int p = s + ((pleft - sleft) > (pright - sright) ? (pleft - sleft) : (pright - sright));

                ((FixedPrecisionDataType)retType).setPrecision(p);
                ((FixedPrecisionDataType)retType).setScale(s);
            }
        }
        else if(leftPrim == PrimitiveType.REAL_LITERAL)
        {
            if(rightPrim  == PrimitiveType.REAL_LITERAL)
            {
                retType = copyDataType(left);
            } 
            else if(rightPrim  == PrimitiveType.SMALLINT_LITERAL
                    || rightPrim  == PrimitiveType.INTEGER_LITERAL
                    || rightPrim  == PrimitiveType.BIGINT_LITERAL
                    || rightPrim  == PrimitiveType.DECIMAL_LITERAL)
            {
                retType = copyDataType(left);
                ((ApproximateNumericDataType)retType).setPrimitiveType(PrimitiveType.DOUBLE_PRECISION_LITERAL);
            }
        }
        else if(leftPrim == PrimitiveType.DOUBLE_PRECISION_LITERAL)
        {
            retType = copyDataType(left);
        }
        
    }
    return retType;
}

/**
 * For fixed return type
 * @param funcName
 * @return
 */
private static DataType getFuncReturnType(String funcName)
{
    DataType dt = (DataType)FunctionReturnType.get(funcName);
    if(dt != null)
        return (DataType)EcoreUtil.copy(dt);
    else
        return null;
}

/**
 * CHECK IMPLEMENTATION! CODE UNTESTED! TODO: implement this Proxy method.
 * Throws UnsupportedOperationException now.
 * 
 * @param funcName
 * @return
 */
private static Object[][] getParameterFormats(String funcName)
{
    // FIXME implement another method taking a ValueExpressionFunction object that has a return type
    throw new UnsupportedOperationException(
                    ValueExpressionHelper.class.getName()+
                    "#getParameterFormats(String funcName) not implemented!");
    //return FunctionHelper.getParameterFormats( funcName );
}

// TODO: to be refactored somewhere properly
/** CHECK IMPLEMENTATION! CODE UNTESTED!
 * Guesses the DataType of the given value of a ValueExpression.
 * TODO: document this properly, after review
 * 
 * @param value the value to which a DataType should be guessed
 * @return the DataType that was guessed
 */
private static PredefinedDataType getPredefinedDataTypeForSimpleValue(String value, ValueExpressionUnaryOperator op)
{

    PredefinedDataType datatype = null;

    SQLDataTypesFactory sqlDataTypesFactory = SQLDataTypesFactory.eINSTANCE;

    String lenStr = null;
    String scaleStr = null;
    if (value != null && value.length() > 0)
    {
        // Check for a string datatype by looking for enclosing quotes.
        if (value.startsWith("'") && value.endsWith("'"))
        {
            Integer len = new Integer(value.length() - 2);
            lenStr = len.toString();
            datatype = sqlDataTypesFactory.createCharacterStringDataType();
            //TODO: when is it, what about length
            //PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL;
            //PrimitiveType.CHARACTER_VARYING_LITERAL;
            datatype.setPrimitiveType(PrimitiveType.CHARACTER_LITERAL);
            //TODO: better!
            ((CharacterStringDataType) datatype).setLength(len.intValue());

            // TODO: what about date types??? or other string represented
            // types

        }

        // Check for a "graphic" (G'dddd' or N'dddd') double-byte literal.
        else if ((value.startsWith("G'") || value.startsWith("g'")
                        || value.startsWith("N'") || value.startsWith("n'"))
                        && value.endsWith("'"))
        {
            Integer len = new Integer((value.length() - 3) / 2);
            lenStr = len.toString();
            //datatype = DatabaseHelper.getDataType( Types.CHAR, "GRAPHIC",
            // lenStr, scaleStr, aDB );
            datatype = sqlDataTypesFactory.createCharacterStringDataType();
            datatype.setPrimitiveType(PrimitiveType.NATIONAL_CHARACTER_LITERAL);
        }

        // Check for a "hex" (X'cccc') literal.
        else if ((value.startsWith("X'") || value.startsWith("x'"))
                        && value.endsWith("'"))
        {
            // TODO: Now that I've found it, what do I do with it?
            datatype = sqlDataTypesFactory.createBinaryStringDataType();
            //TODO: HEX is not large per se, Hemant
            datatype.setPrimitiveType(PrimitiveType.BINARY_LARGE_OBJECT_LITERAL);
        }

        // Must not be a string. Check for a numeric value.
        else
        {
            // Since we know we don't have a string literal, we can upper
            // case the string to
            // simplify our search for special characters.
            String ucValue = value.toUpperCase();
            
            // append back the unary operator minus if present
            if (ValueExpressionUnaryOperator.MINUS_LITERAL.equals(op)) {
                ucValue = "-" + ucValue;
            }

            // We can also ignore any leading sign, since we're not really
            // interested in the value, just its datatype

            // Check for double literal. (Floating point constants are
            // always
            // assumed to be double-precision.)
            int eIndex = ucValue.indexOf('E');
            int dIndex = ucValue.lastIndexOf('.');

            // TODO: very important for range checks! unary operator
            // cascades sign!

            // INTEGER types
            if (eIndex == -1 && dIndex == -1 && datatype == null)
            {
                try
                {
                    Integer intObj = new Integer(ucValue);
                    int intValue = intObj.intValue();
                    int precision = 10;
                    PrimitiveType primitiveType = PrimitiveType.INTEGER_LITERAL;

                    if (intValue > -32769 && intValue < 32768)
                    {
                        precision = 5;
                        primitiveType = PrimitiveType.SMALLINT_LITERAL;
                    }

                    // If we reached here we know we have an integer.
                    //datatype = DatabaseHelper.getDataType( Types.INTEGER,
                    // "INTEGER", "10", "0", aDB );
                    datatype = sqlDataTypesFactory.createIntegerDataType();
                    datatype.setPrimitiveType(primitiveType);
                    ((NumericalDataType) datatype).setPrecision(precision);
                }
                catch (NumberFormatException e2)
                {
                    try
                    {
                        Long bigInt = new Long(ucValue);
                        int precision = 19;

                        // If we reached here we know we have a big integer.
                        //datatype = DatabaseHelper.getDataType(
                        // Types.INTEGER, "INTEGER", "19", "0", aDB );
                        datatype = sqlDataTypesFactory.createIntegerDataType();
                        // TODO: check that
                        datatype.setPrimitiveType(PrimitiveType.BIGINT_LITERAL);
                        ((NumericalDataType) datatype).setPrecision(precision);
                    }
                    catch (NumberFormatException e3)
                    {
                    }
                }

            }

            // DECIMAL type (fixed precision)
            if (eIndex == -1 && datatype == null)
            {
                try
                {
                    BigDecimal decimal = new BigDecimal(ucValue);

                    // If we reached here we know we have a decimal. Get
                    // it's precision
                    // and scale.
                    BigInteger bigIntVal = decimal.unscaledValue();
                    bigIntVal = bigIntVal.abs(); // eliminate the minus
                                                 // sign, if any.
                    String bigIntValStr = bigIntVal.toString();
                    int precision = bigIntValStr.length();

                    int scale = decimal.scale();
                    //datatype = DatabaseHelper.getDataType( Types.DECIMAL,
                    // "DECIMAL", "" + precision, "" + scale, aDB );
                    //TODO: check for right datatype
                    datatype = sqlDataTypesFactory.createFixedPrecisionDataType();
                    datatype.setPrimitiveType(PrimitiveType.DECIMAL_LITERAL);
                    ((FixedPrecisionDataType) datatype).setPrecision(precision);
                    ((FixedPrecisionDataType) datatype).setScale(scale);
                }
                catch (NumberFormatException e2)
                {
                }

            }

            // FLOAT types and all included
            if (datatype == null)
            {
                try
                {
                    Double doubleObject = new Double(ucValue);
                    double doubleValue = doubleObject.doubleValue();

                    //float floatMin = Float.MIN_VALUE; //("-3.402E+38");
                    //float floatMax = Float.MAX_VALUE;//("3.402E+38");

                    float floatNegMin = Float.parseFloat("-3.402E+38");
                    float floatNegMax = Float.parseFloat("-1.175E-37");
                    float floatPosMin = Float.parseFloat("1.175E-37");
                    float floatPosMax = Float.parseFloat("3.402E+38");

                    // If we reached here, we really do have a double.
                    // Get its 'precision'.
                    // (I'm assuming that the precision, like for
                    // decimal numbers, is the
                    // total number of digits, not including the 'E'
                    // part.) When
                    // computing the precision, take into account any
                    // leading sign
                    // and decimal point.
                    int valPrecision = eIndex;
                    if (ucValue.startsWith("-") || ucValue.startsWith("+"))
                    {
                        valPrecision--;
                    }
                    if (ucValue.lastIndexOf('.', eIndex) != -1)
                    {
                        valPrecision--;
                    }
                    //datatype = DatabaseHelper.getDataType(
                    // Types.DOUBLE, "DOUBLE", "" + valPrecision, "0",
                    // aDB );
                    //TODO: check the right datatype?
                    datatype = sqlDataTypesFactory.createApproximateNumericDataType();

                    //if (doubleValue < floatMin || doubleValue > floatMax) {
                    if (doubleValue < floatNegMin
                                    || doubleValue > floatPosMax
                                    || doubleValue > floatNegMax
                                    && doubleValue < floatPosMin)
                    {
                        datatype.setPrimitiveType(PrimitiveType.DOUBLE_PRECISION_LITERAL);
                    }
                    else
                    {
                        datatype.setPrimitiveType(PrimitiveType.REAL_LITERAL);
                    }
                    
                    ((ApproximateNumericDataType) datatype).setPrecision(valPrecision);
                }
                catch (NumberFormatException ed)
                {
                }

            }

        }

    }

    return datatype;

}

/**
 * CHECK IMPLEMENTATION! CODE UNTESTED! TODO: implement this Proxy method.
 * Throws UnsupportedOperationException now.
 * 
 * @param funcName
 * @param returnDatatypeName
 * @param param1DatatypeName
 * @param param1DatatypeLen
 * @return
 */
private static int getReturnDatatypeLength(String funcName, String returnDatatypeName, String param1DatatypeName, int param1DatatypeLen)
{
    // FIXME implement another method taking a ValueExpressionFunction object
    // that has a return type
    throw new UnsupportedOperationException(
    //System.err.println(
                    ValueExpressionHelper.class.getName()+"#getReturnDatatypeLength(String funcName, String returnDatatypeName, String param1DatatypeName, int param1DatatypeLen) not implemented!");
    //return FunctionHelper.getReturnDatatypeLength( funcName,
    // returnDatatypeName, param1DatatypeName, param1DatatypeLen );
}


} // end class

