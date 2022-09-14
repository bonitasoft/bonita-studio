/*******************************************************************************
 * Copyright (c) 2004, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.helper;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeOnCondition;
import org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeSourceTable;
import org.eclipse.datatools.modelbase.sql.query.MergeTargetTable;
import org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification;
import org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal;
import org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn;
import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
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
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableQueryLateral;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQueryLogger;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import com.ibm.icu.text.StringCharacterIterator;


public class StatementHelper {

    /**
     * TODO: make that DELIMITED_IDENTIFIER_QUOTE char flexible with Database
     * info String constant for the quote that are used for delimited
     * identifiers like "Col1" where this delimited identifier should not be
     * modified to or be compared equal to COL1 or col1, value: "\"", the
     * character "
     */
    public static char DELIMITED_IDENTIFIER_QUOTE = '"';

    public static final int STATEMENT_TYPE_DELETE = 3;
    public static final int STATEMENT_TYPE_FULLSELECT = 4;
    public static final int STATEMENT_TYPE_INSERT = 1;
    public static final int STATEMENT_TYPE_SELECT = 0;
    public static final int STATEMENT_TYPE_UPDATE = 2;
    public static final int STATEMENT_TYPE_WITH = 5;
    public static final int STATEMENT_TYPE_MERGE = 6;

    private static List templates = new ArrayList();

    Database database;
    Hashtable nameList;

    public StatementHelper() {
    }

    public StatementHelper(Database database) {
        nameList = new Hashtable();
        this.database = database;
    }

    /**
     * Gets the DELIMITED_IDENTIFIER_QUOTE char.
     */
    public static char getDELIMITED_IDENTIFIER_QUOTE()
    {
       return DELIMITED_IDENTIFIER_QUOTE;
    }

    /**
     * Sets the DELIMITED_IDENTIFIER_QUOTE char to make this class flexible with Database
     * info String constant for the quote that are used for delimited
     * identifiers like "Col1" where this delimited identifier should not be
     * modified to or be compared equal to COL1 or col1, value: "\"", the
     * character "
     */
    public static void setDELIMITED_IDENTIFIER_QUOTE(char delimited_identifier_quote)
    {
       DELIMITED_IDENTIFIER_QUOTE = delimited_identifier_quote;
    }

    /**
     * Compares the SQL source of the given SQL fragments according to their
     * syntax.
     * 
     * @param sql1
     * @param sql2
     * @return <code>0</code> if the given SQL fragments are syntactically
     *         equivalent
     */
    public static int compareSQL(String sql1, String sql2) {
        return compareSQL(sql1, sql2, '"');
    }

    /**
     * Compares the SQL source of the given SQL fragments according to their
     * syntax.
     * 
     * @param sql1
     * @param sql2
     * @param identifierDelimiterQt
     * @return <code>0</code> if the given SQL fragments are syntactically
     *         equivalent
     */
    public static int compareSQL(String sql1, String sql2, char identifierDelimiterQt) {
        sql1 = stripWhiteSpaceAndComments(sql1, identifierDelimiterQt);
        sql2 = stripWhiteSpaceAndComments(sql2, identifierDelimiterQt);
        return sql1.compareTo(sql2);
    }
    
    /**
     * Converts an identifier from catalog format to SQL format. See following
     * table for examples.
     * 
     * <table border='1' width='250'>
     * <tr>
     * <th><b>SQL format</b></th>
     * <th><b>Catalog format</b></th>
     * </tr>
     * <tr>
     * <td>schema1<br>
     * Schema1<br>
     * SCHEMA1<br>
     * &quot;SCHEMA1&quot;</td>
     * <td>SCHEMA1</td>
     * </tr>
     * <tr>
     * <td>&quot;Schema1&quot;</td>
     * <td>Schema1</td>
     * </tr>
     * <tr>
     * <td>&quot;Sch&quot;&quot;ma1&quot;</td>
     * <td>Sch&quot;ma1</td>
     * </tr>
     * <tr>
     * <td>&quot;Table A&quot;</td>
     * <td>Table A</td>
     * </tr>
     * <tr>
     * <td>Table A</td>
     * <td>Error</td>
     * </tr>
     * <tr>
     * <td>Sch&quot;ma1</td>
     * <td>Error</td>
     * </tr>
     * </table>
     * 
     * @param catalogIdentifier identifier in catalog format
     * @param idDelimiterQuote
     * @return identifier in SQL format
     */
    public static String convertCatalogIdentifierToSQLFormat(String catalogIdentifier, char idDelimiterQuote) {
        String sqlIdentifier = catalogIdentifier;

        if (catalogIdentifier != null) {
            boolean containsDelimiters = catalogIdentifier.indexOf(idDelimiterQuote) > -1;
            boolean containsSpace = catalogIdentifier.indexOf(' ') > -1;
            boolean containsDot = catalogIdentifier.indexOf('.') > -1;
            boolean isLowerOrMixedCase = !catalogIdentifier.toUpperCase().equals(catalogIdentifier);
            // QVO wsdbu00119542 
            // We need to check for cases when the identifier contains all digits (0-9)
            boolean allDigits = false;
            try
            {
                Integer.parseInt(catalogIdentifier);
                // if no exception, then it contains all digits
                allDigits = true;
            }
            catch (NumberFormatException ex)
            {
                // ignore;
            }
            // end wsdbu00119542
            
            // boolean startsWithNum = catalogIdentifier.length() > 0 &&
            // catalogIdentifier.charAt(0) < 'A';
            // ISO/IEC 9075-2:2003 (E) 5.2 <token> and <separator>
            // The Unicode General Category classes Lu, Ll, Lt,
            // Lm, Lo, and Nl
            // are assigned upper-case letters, lower-case letters, title-case
            // letters, modifier letters, other letters, and letter numbers.
            // All identifiers that have a non-word character (i.e not any of a-z A-Z _ 0-9 ) needs to be delimited
            boolean containsNonAlpha = false;
            // The following pattern is "all non-word characters (\W) except the chars $, #, and @"
            String nonAlphaRegex = "[\\W&&[^$#@]]"; // [RATLC00401002]  //$NON-NLS-1$
            Pattern patern = Pattern.compile(nonAlphaRegex);
            Matcher matcher = patern.matcher(catalogIdentifier);
            while(!containsNonAlpha &&  matcher.find()){
                containsNonAlpha = true;
            }

            // checks for leading '_' character, which was not checked by above pattern
            if ( !containsNonAlpha && catalogIdentifier.length() > 0 && catalogIdentifier.charAt(0) == '_' )
                containsNonAlpha = true;
            
            if (containsDelimiters || containsSpace || containsDot || containsNonAlpha || isLowerOrMixedCase
                    || allDigits) {
                String delimiter = String.valueOf(idDelimiterQuote);

                if (containsDelimiters) {
                    StringBuffer sqlIdentSB = new StringBuffer(sqlIdentifier);
                    for (int i = 0; i < sqlIdentSB.length(); i++) {
                        if (sqlIdentSB.charAt(i) == idDelimiterQuote) {
                            sqlIdentSB.insert(i, idDelimiterQuote);
                            i++;
                        }
                    }
                    sqlIdentifier = sqlIdentSB.toString();
                }
                sqlIdentifier = delimiter + sqlIdentifier + delimiter;
            }

        }

        return sqlIdentifier;
    }

    /**
     * Converts an identifier from SQL format to catalog format. See following
     * table for examples.
     * 
     * <table border='1' width='250'>
     * <tr>
     * <th><b>SQL format</b></th>
     * <th><b>Catalog format</b></th>
     * </tr>
     * <tr>
     * <td>schema1<br>
     * Schema1<br>
     * SCHEMA1<br>
     * &quot;SCHEMA1&quot;</td>
     * <td>SCHEMA1</td>
     * </tr>
     * <tr>
     * <td>&quot;Schema1&quot;</td>
     * <td>Schema1</td>
     * </tr>
     * <tr>
     * <td>&quot;Sch&quot;&quot;ma1&quot;</td>
     * <td>Sch&quot;ma1</td>
     * </tr>
     * <tr>
     * <td>&quot;Table A&quot;</td>
     * <td>Table A</td>
     * </tr>
     * <tr>
     * <td>Table A</td>
     * <td>Error</td>
     * </tr>
     * <tr>
     * <td>Sch&quot;ma1</td>
     * <td>Error</td>
     * </tr>
     * </table>
     * 
     * @param sqlIdentifier identifier in SQL format
     * @param idDelimiterQuote
     * @return identifier in catalog format
     */
    public static String convertSQLIdentifierToCatalogFormat(String sqlIdentifier, char idDelimiterQuote) {
        String catalogIdentifier = sqlIdentifier;

        if (sqlIdentifier != null) {
            String delimiter = String.valueOf(idDelimiterQuote);

            boolean isDelimited = sqlIdentifier.startsWith(delimiter) && sqlIdentifier.endsWith(delimiter);
            boolean containsQuotedDelimiters = sqlIdentifier.indexOf(delimiter + delimiter) > -1;

            if (isDelimited && sqlIdentifier.length() > 1) {
                catalogIdentifier = sqlIdentifier.substring(1, sqlIdentifier.length() - 1);

                if (containsQuotedDelimiters) {
                    catalogIdentifier = catalogIdentifier.replaceAll(delimiter + delimiter, delimiter);
                }
            }
            else {
                catalogIdentifier = sqlIdentifier.toUpperCase();
            }
        }

        return catalogIdentifier;
    }

    /**
     * DO NOT USE! NOT IMPLEMENTED CORRECTLY! Copies all references including
     * elements of list references from donor to recipient. Both donor and
     * recipient must be of the given type eObjectType, as only the references
     * of this type will be copied.
     * 
     * @param donor
     * @param recipient
     * @param eObjectType class or interface level on which donor's references
     *        will be copied to recipient
     * 
     * @deprecated not properly implemented
     */
    private static void copyAllDirectNonNullReferences(EObject donor, EObject recipient) {

        if (donor != null && recipient != null && donor != recipient) {

            for (Iterator refIt = donor.eClass().getEAllReferences().iterator(); refIt.hasNext();) {
                EReference ref = (EReference) refIt.next();

                // TODO: figure out how make sure you are copying EFeatures that
                // exist on both donor and recipient, same interface or
                // superclass level
                boolean isRefCopyable = recipient.eClass().getEAllReferences().contains(ref) && ref.isChangeable();

                if (isRefCopyable) {
                    if (ref.isMany()) {
                        EList donorRefList = (EList) donor.eGet(ref);
                        if (!donorRefList.isEmpty()) {
                            EList recipientRefList = (EList) recipient.eGet(ref);
                            recipientRefList.addAll(donorRefList);
                        }
                    }
                    else {
                        EObject donorReference = (EObject) donor.eGet(ref);
                        if (donorReference != null) {
                            recipient.eSet(ref, donorReference);
                        }
                    }
                }
            }
        }
    }

    /**
     * Create a new <code>ValueExpressionColumn</code> with the given name.
     * 
     * @param name optional name of the result column
     * @return new ValueExpressionColumn
     */
    public static ValueExpressionColumn createColumnExpression(String name) {
        ValueExpressionColumn colExpr = SQLQueryModelFactory.eINSTANCE.createValueExpressionColumn();
        colExpr.setName(name);
        return colExpr;
    }

    /**
     * Creates the new ColumnName object and sets the given name.
     * 
     * @param name the String name of the ColumnName.
     * @return the created ColumnName
     */
    public static ColumnName createColumnName(String name) {
        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        ColumnName newCN = factory.createColumnName();
        newCN.setName(name);
        return newCN;
    }

    public static QueryDeleteStatement createDeleteStatement(String name) {
        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        QueryDeleteStatement sqlDeleteStatement = factory.createQueryDeleteStatement();
        sqlDeleteStatement.setName(name);
        return sqlDeleteStatement;
    }

    /**
     * return false if we cannot find the input name
     */
    /*
     * private boolean findUpdate(String name) { Iterator iterator =
     * database.getStatement().iterator();
     * 
     * while (iterator.hasNext()) { Object o = iterator.next(); if (o instanceof
     * SQLUpdateStatement) { SQLUpdateStatement update = (SQLUpdateStatement) o;
     * String sname = update.getName();
     * 
     * if (sname != null && sname.equals(name)) { return true; } } } return
     * false; }
     * 
     *//**
         * return false if we cannot find the input name
         */
    /*
     * private boolean findDelete(String name) { Iterator iterator =
     * database.getStatement().iterator();
     * 
     * while (iterator.hasNext()) { Object o = iterator.next(); if (o instanceof
     * SQLDeleteStatement) { SQLDeleteStatement del = (SQLDeleteStatement) o;
     * String sname = del.getName();
     * 
     * if (sname != null && sname.equals(name)) { return true; } } }
     * 
     * return false; }
     * 
     *//**
         * return false if we cannot find the input name
         */
    /*
     * private boolean findFullSelect(String name) { Iterator iterator =
     * database.getStatement().iterator();
     * 
     * while (iterator.hasNext()) { Object o = iterator.next();
     * 
     * if (o instanceof SQLFullSelectStatement) { SQLFullSelectStatement fselect =
     * (SQLFullSelectStatement) o; String sname = fselect.getName();
     * 
     * if (sname != null && sname.equals(name)) { return true; } } } return
     * false; }
     * 
     *//**
         * return false if we cannot find the input name
         */
    /*
     * private boolean findWith(String name) { Iterator iterator =
     * database.getStatement().iterator();
     * 
     * while (iterator.hasNext()) { Object o = iterator.next(); if (o instanceof
     * SQLWithStatement) { SQLWithStatement with = (SQLWithStatement) o; String
     * sname = with.getName();
     * 
     * if (sname != null && sname.equals(name)) { return true; } } } return
     * false; }
     */

    /*
     * public SQLInsertStatement createInsertStatement() { SQLQueryModelFactory
     * factory = SQLQueryModelFactoryImpl.instance();
     * 
     * SQLInsertStatement sqlInsertStatement =
     * factory.createSQLInsertStatement();
     * sqlInsertStatement.setName(getNewName(sqlInsertStatement));
     * 
     * database.getStatement().add(sqlInsertStatement);
     * 
     * return sqlInsertStatement; }
     */

    public static QueryInsertStatement createInsertStatement(String name) {
        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        QueryInsertStatement sqlInsertStatement = factory.createQueryInsertStatement();
        sqlInsertStatement.setName(name);
        return sqlInsertStatement;
    }

    /**
     * Creates an empty MERGE statement with the given name.
     * 
     * @param name the name for the statement
     * @return the merge statement
     */
    public static QueryMergeStatement createMergeStatement(String name) {
        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        QueryMergeStatement mergeStmt = factory.createQueryMergeStatement();
        mergeStmt.setName(name);
        return mergeStmt;
    }

    /**
     * Creates a QueryCombined
     * 
     * @return the created QueryCombined
     */
    public static QueryCombined createQueryCombined() {
        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        QueryCombined queryCombined = factory.createQueryCombined();
        return queryCombined;
    }

    /**
     * Creates a QuerySelectStatement statement, with a QueryCombined in it
     * 
     * @param name the name of the statement.
     * @return the created QuerySelectStatement statement
     */
    public static QuerySelectStatement createQueryCombinedStatement(String name) {
        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        QuerySelectStatement sqlSelectStatement = factory.createQuerySelectStatement();
        sqlSelectStatement.setName(name);
        QueryExpressionRoot qroot = createQueryExpressionRoot(sqlSelectStatement);
        QueryCombined queryCombined = factory.createQueryCombined();
        queryCombined.setLeftQuery(createQuerySelect());
        queryCombined.setRightQuery(createQuerySelect());
        sqlSelectStatement.getQueryExpr().setQuery(queryCombined);
        return sqlSelectStatement;
    }

    /**
     * Creates a QueryExpressionRoot
     * 
     * @return the created QueryExpressionRoot Object
     */
    public static QueryExpressionRoot createQueryExpressionRoot() {
        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        QueryExpressionRoot expRoot = factory.createQueryExpressionRoot();
        return expRoot;
    }

    /**
     * Creates a QueryExpressionRoot and attaches it to QuerySelectStatement
     * 
     * @param aParent the parent QuerySelectStatement
     * @return the QueryExpressionRoot Object or null is aParent is null
     */
    public static QueryExpressionRoot createQueryExpressionRoot(QuerySelectStatement aParent) {
        if (aParent != null) {
            QueryExpressionRoot expRoot = createQueryExpressionRoot();
            aParent.setQueryExpr(expRoot);
            return expRoot;
        }
        return null;
    }

    /**
     * Creates a QueryNested object.
     * 
     * @return
     */
    public static QueryNested createQueryNested() {
        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        QueryNested qryNested = factory.createQueryNested();
        return qryNested;        
    }
    
    /**
     * Creates a QuerySelect Object
     * 
     * @return the created QuerySelect Object
     */
    public static QuerySelect createQuerySelect() {
        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        QuerySelect select = factory.createQuerySelect();
        return select;
    }

    /**
     * Creates a QuerySelect Object and hook it to a QueryExpressionRoot
     * 
     * @param anExpRoot the QueryExpressionRoot
     * @return the QuerySelect Object or null if anExpRoot is null
     */
    public static QuerySelect createQuerySelect(QueryExpressionRoot anExpRoot) {
        if (anExpRoot != null) {
            QuerySelect select = createQuerySelect();
            anExpRoot.setQuery(select);
            return select;
        }
        return null;
    }

    /**
     * Creates a QuerySelect Object and hook it to a QuerySelectStatement
     * 
     * @param aStatement the QuerySelectStatement
     * @return the QuerySelect Object or null if aStatement is null
     */
    public static QuerySelect createQuerySelect(QuerySelectStatement aStatement) {
        if (aStatement == null) {
            return null;
        }
        if (aStatement.getQueryExpr() == null) {
            return null;
        }
        QuerySelect select = createQuerySelect();
        aStatement.getQueryExpr().setQuery(select);
        return select;
    }

    /**
     * Creates a QuerySelectStatement
     * 
     * @param aName the name of the statement
     * @return the created QuerySelecteStatement
     */
    public static QuerySelectStatement createQuerySelectStatement(String aName) {
        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        QuerySelectStatement sqlSelectStatement = factory.createQuerySelectStatement();
        sqlSelectStatement.setName(aName);
        return sqlSelectStatement;
    }

    /**
     * Creates a statement of the given type with the given name. The valid
     * statement types are defined as constants in this class.
     * 
     * @param stmtType the requested statement type
     * @param stmtName the name for the statement
     * @return the new query statement object
     */
    public static QueryStatement createQueryStatement(int stmtType, String stmtName) {
        QueryStatement stmt = null;
        switch (stmtType) {
            case STATEMENT_TYPE_SELECT:
                stmt = createQuerySelectStatement(stmtName);
                break;
            case STATEMENT_TYPE_INSERT:
                stmt = createInsertStatement(stmtName);
                break;
            case STATEMENT_TYPE_UPDATE:
                stmt = createUpdateStatement(stmtName);
                break;
            case STATEMENT_TYPE_DELETE:
                stmt = createDeleteStatement(stmtName);
                break;
            case STATEMENT_TYPE_FULLSELECT:
                stmt = createQueryCombinedStatement(stmtName);
                break;
            case STATEMENT_TYPE_WITH:
                stmt = createWithStatement(stmtName);
                break;
            case STATEMENT_TYPE_MERGE:
                stmt = createMergeStatement(stmtName);
                break;
        }

        return stmt;
    }

    /*
     * public QueryUpdateStatement createUpdateStatement() { SQLQueryModelFactory
     * factory = SQLQueryModelFactoryImpl.eInstance;
     * 
     * QueryUpdateStatement sqlUpdateStatement =
     * factory.createSQLUpdateStatement();
     * sqlUpdateStatement.setName(getNewName(sqlUpdateStatement));
     * 
     * database.getStatement().add(sqlUpdateStatement); return
     * sqlUpdateStatement; }
     */
    public static QueryUpdateStatement createUpdateStatement(String name) {
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        QueryUpdateStatement sqlUpdateStatement = factory.createQueryUpdateStatement();
        sqlUpdateStatement.setName(name);
        return sqlUpdateStatement;
    }

    /**
     * Creates a WITH query statement.
     * 
     * @param name the String name of the statement.
     * @return the created QuerySelecteStatement with TableWithSpecification.
     */
    public static QuerySelectStatement createWithStatement(String name) {

        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        QuerySelectStatement sqlSelectStatement = factory.createQuerySelectStatement();
        sqlSelectStatement.setName(name);
        QueryExpressionRoot qroot = createQueryExpressionRoot(sqlSelectStatement);
        QuerySelect qSelectMain = createQuerySelect();
        qroot.setQuery(qSelectMain);
        WithTableSpecification withTable = factory.createWithTableSpecification();
        withTable.setName("WithTable1");
        QuerySelect qSelect = createQuerySelect();
        withTable.setWithTableQueryExpr(qSelect);
        sqlSelectStatement.getQueryExpr().getWithClause().add(withTable);
        return sqlSelectStatement;
    }

    /**
     * Creates a new <code>WithTableReference</code> for the given
     * <code>withTableSpec</code> and creates
     * <code>ValueExpressionColumn</code> objects for each of the given
     * <code>WithTableSpecification</code>'s declared columns ({@link WithTableSpecification#getColumnNameList()})
     * or, if columns are not declared, for each of the
     * <code>WithTableSpecification</code>'s query's result columns.
     * 
     * @param withTableSpec
     * @return
     */
    public static WithTableReference createWithTableReferenceForWithTable(WithTableSpecification withTableSpec) {
        WithTableReference withTableRef = null;

        if (withTableSpec != null) {
            withTableRef = SQLQueryModelFactory.eINSTANCE.createWithTableReference();
            withTableRef.setWithTableSpecification(withTableSpec);
            withTableRef.setName(withTableSpec.getName());

            // create ValueExpressionColumns for either the declare WithTable
            // columns or the implicit WithTable's query's columns

            TableHelper.exposeEffectiveResultColumns(withTableRef);
        }

        return withTableRef;
    }

    /**
     * Determines whether two SQL identifiers are equal, respecting delimited
     * identifiers using the default delimited identifier quote character.
     * (If the given SQL identifiers are delimited, they must match exactly.  
     * Otherwise equal identifiers can differ by case.) 
     * If one of the identifiers is null, they are considered not equal. If both 
     * are null, they are considered equal.
     * 
     * @param ident1 a SQL identifier
     * @param ident2 another SQL identifier
     * @return true when the identifiers are equal, otherwise false
     */
    public static boolean equalSQLIdentifiers(String ident1, String ident2) {
        return equalSQLIdentifiers(ident1, ident2, DELIMITED_IDENTIFIER_QUOTE);
    }
    
    /**
     * Determines whether two SQL identifiers are equal, respecting delimited
     * identifiers using the given quote character as delimiter.
     * (If the given SQL identifiers are delimited, they must match exactly.  
     * Otherwise equal identifiers can differ by case.)  
     * If one of the identifiers is null, they are considered not equal.  If both 
     * are null, they are considered equal.
     * 
     * @param ident1 a SQL identifier
     * @param ident2 another SQL identifier
     * @param identDelimiterQuote the delimiter char
     * @return true when the identifiers are equal, otherwise false
     */
    public static boolean equalSQLIdentifiers(String ident1, String ident2, char delimiterChar) {
        String delimStr = String.valueOf(delimiterChar);

        boolean isEqualIdentifiers = false;
        
        /* Equal when both are null. */
        if (ident1 == null && ident2 == null) {
            isEqualIdentifiers = true;
        }
        /* Not equal when one is null. */
        else if (ident1 == null || ident2 == null) {
            isEqualIdentifiers = false;
        }
        /* When one or both identifiers are delimited, must have exact match. */
        else if (ident1.startsWith(delimStr)
              || ident2.startsWith(delimStr)) {
            String tempIdent1 = ident1;
            String tempIdent2 = ident2;
            if (ident1.startsWith(delimStr)
             && ident1.endsWith(delimStr)) {
                tempIdent1 = ident1.substring(1, ident1.length()-1);
            }
            if (ident2.startsWith(delimStr)
             && ident2.endsWith(delimStr)) {
                tempIdent2 = ident2.substring(1, ident2.length()-1);
            }
            if (tempIdent1.equals(tempIdent2)) {
                isEqualIdentifiers = true;
            }
        }
        /* Otherwise compare ignoring case. */
        else if (ident1.equalsIgnoreCase(ident2)) {
            isEqualIdentifiers = true;
        }
        
        return isEqualIdentifiers;
    }

    /**
     * Returns <code>null</code> or the <code>ResultColumn</code> of the
     * given <code>QuerySelect</code> whose <code>ValueExpression</code> is
     * of type <code>ValueExpressionColumn</code> which in <code>name</code>
     * and <code>tableExpr</code> equals the given <code>columnExpr</code>.
     * 
     * @param select
     * @param columnExpr a <code>ValueExpressionColumn</code>
     * @return
     */
    public static ResultColumn findResultColumnForColumnExpression(QuerySelect select, ValueExpressionColumn columnExpr) {
        ResultColumn resultColumnFound = null;
        String colName = columnExpr.getName();

        if (colName != null && select != null) {
            // if the given columnExpr is not qualified we might instead look
            // for result column alias
            if (columnExpr.getTableExpr() == null) {
                resultColumnFound = findResultColumnForColumnNameOrAlias(select, colName);
            }
            else {
                String colTableName = columnExpr.getTableExpr().getName();

                for (Iterator resultColIt = select.getSelectClause().iterator(); resultColIt.hasNext();) {
                    QueryResultSpecification resultSpec = (QueryResultSpecification) resultColIt.next();

                    if (resultSpec instanceof ResultColumn) {
                        ResultColumn resultColumn = (ResultColumn) resultSpec;
                        ValueExpressionColumn resultColExpr = null;
                        TableExpression resultColTableExpr = null;
                        TableCorrelation resultColTableCorr = null;
                        String resultColExprName = null;
                        String resultColExprTableName = null;
                        String resultColExprTableAlias = null;

                        if (resultColumn.getValueExpr() instanceof ValueExpressionColumn) {
                            resultColExpr = (ValueExpressionColumn) resultColumn.getValueExpr();
                            resultColExprName = resultColExpr.getName();
                            resultColTableExpr = resultColExpr.getTableExpr();

                            if (resultColTableExpr != null) {
                                resultColExprTableName = resultColTableExpr.getName();
                                resultColTableCorr = resultColTableExpr.getTableCorrelation();

                                if (resultColTableCorr != null) {
                                    resultColExprTableAlias = resultColTableCorr.getName();
                                }
                            }

                            // we want to match: select t1.col1 from ... w/
                            // T1.COL1 but not with COL1 and not w/ T2.COL1
                            // we don't want to match: select col1 from ... w/
                            // T1.COL1 or T2.COL1 but with COL1 while T1 could
                            // be an alias of the table that COL1 is associated
                            // to
                            if ( equalSQLIdentifiers(colName, resultColExprName)
                              && ( equalSQLIdentifiers(colTableName, resultColExprTableName) 
                                || equalSQLIdentifiers(colTableName, resultColExprTableAlias)
                                 )
                               ) {
                                resultColumnFound = resultColumn;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return resultColumnFound;
    }

    /**
     * Returns <code>null</code> or the <code>ResultColumn</code> of the
     * given <code>QuerySelect</code> whose <code>name</code> matches the
     * given <code>columnNameOrAlias</code> or whose
     * <code>ValueExpression</code> is of type
     * <code>ValueExpressionColumn</code> which has a <code>name</code>
     * matching the given <code>columnNameOrAlias</code>.
     * 
     * @param select
     * @param columnNameOrAlias
     * @return
     */
    public static ResultColumn findResultColumnForColumnNameOrAlias(QuerySelect select, String columnNameOrAlias) {
        ResultColumn resultColumnFound = null;

        for (Iterator resultColIt = select.getSelectClause().iterator(); resultColIt.hasNext();) {
            QueryResultSpecification resultSpec = (QueryResultSpecification) resultColIt.next();

            if (resultSpec instanceof ResultColumn) {
                ResultColumn resultColumn = (ResultColumn) resultSpec;
                String resultColumnAlias = null;
                String resultColumnName = null;

                // try to match SELECT COL1 AS C1 FROM ... w/ C1
                if (resultColumn.getName() != null) {
                    resultColumnAlias = resultColumn.getName();

                    // if (equalSQLIdentifiers(resultColumnAlias,
                    // columnNameOrAlias))
                    if (equalSQLIdentifiers(resultColumnAlias, columnNameOrAlias)) {
                        resultColumnFound = resultColumn;
                        break;
                    }
                }
                else if (resultColumn.getValueExpr() instanceof ValueExpressionColumn) {
                    ValueExpressionColumn resultColExpr = (ValueExpressionColumn) resultColumn.getValueExpr();
                    resultColumnName = resultColExpr.getName();

                    // we can't match SELECT T1.COL1, T2.COL1 FROM ... w/ COL1
                    // only match SELECT COL1 FROM ... w/ COL1
                    if (resultColExpr.getTableExpr() == null || !isColumnNameAmbiguous(resultColExpr)) {
                        if (equalSQLIdentifiers(resultColumnName, columnNameOrAlias)) {
                            resultColumnFound = resultColumn;
                            break;
                        }
                    }
                }
            }
        }

        return resultColumnFound;
    }

    /**
     * @param eObject
     * @return
     */
    private static Set getAllDirectReferences(EObject eObject) {
        return getDirectReferences(eObject, null);
    }
    
    /**
     * Gets all the parameter marker-type ("?") variables in the given query
     * statement.
     * 
     * @param queryStmt 
     * @return
     */
    public static List getAllParameterMarkersInQueryStatement(QueryStatement queryStmt) {
        // [bug 223776]
        List paramMarkerList = new ArrayList(); 

        Class typeFilter = ValueExpressionVariable.class;
        Set varSet = getReferencesRecursively(queryStmt, typeFilter);

        Iterator varSetIter = varSet.iterator();
        while (varSetIter.hasNext()) {
            ValueExpressionVariable var = (ValueExpressionVariable) varSetIter.next();
            String varName = var.getName();
            if (varName == null) {
                paramMarkerList.add(var);
            }
        }

        return paramMarkerList;
    }
    
    /**
     * Returns all <code>ValueExpressionVariables</code> contained in the
     * given <code>QueryStatement</code>.  All parameter marker-type ("?")
     * variables will be modified to have names VAR0n where n is a sequence
     * number.
     * 
     * @param queryStmt the statement from which variables are wanted
     * @return List of the {@link ValueExpressionVariable}
     */
    public static List getAllVariablesInQueryStatement(QueryStatement queryStmt) {
       List vars = getAllVariablesInQueryStatement(queryStmt, true, "VAR0");
       // No sense sorting them unless parameter markers are named.
       List sortedVariables = new ArrayList(vars);
       Collections.sort(sortedVariables, new ValueExpressionComparator());
       return sortedVariables;
    }
    
    /**
     * Returns all <code>ValueExpressionVariables</code> contained in the
     * given <code>QueryStatement</code>.
     * 
     * @param queryStmt A SQL statement that might have host variables or parameter markers
     * @param varStem The stem for naming variables from parameter markers 
     * @return List of the {@link ValueExpressionVariable}
     */
    public static List getAllVariablesInQueryStatement(QueryStatement queryStmt, String varStem) {
       List vars = getAllVariablesInQueryStatement(queryStmt, true, varStem);
       // No sense sorting them unless parameter markers are named.
       List sortedVariables = new ArrayList(vars);
       Collections.sort(sortedVariables, new ValueExpressionComparator());
       return sortedVariables;
    }
    
    /**
     * Returns all <code>ValueExpressionVariables</code> contained in the
     * given <code>QueryStatement</code>.
     * 
     * @param queryStmt A SQL statement that might have host variables or parameter markers
     * @param nameParameterMarkers True if you wish to generate names for parameter markers
     * @return List of the {@link ValueExpressionVariable}
     */
    public static List getAllVariablesInQueryStatement(QueryStatement queryStmt, boolean nameParameterMarkers) {
       List vars = getAllVariablesInQueryStatement(queryStmt, nameParameterMarkers, "VAR0");
       // No sense sorting them unless parameter markers are named.
       List sortedVariables = new ArrayList(vars);
       Collections.sort(sortedVariables, new ValueExpressionComparator());
       return sortedVariables;
    }

    /**
     * Returns all <code>ValueExpressionVariables</code> contained in the
     * given <code>QueryStatement</code>.
     * 
     * @param queryStmt A SQL statement that might have host variables or parameter markers
     * @param nameParameterMarkers True if you wish to generate names for parameter markers
     * @param varStem The stem for naming variables from parameter markers 
     * @return List of the {@link ValueExpressionVariable}
     */
    public static List getAllVariablesInQueryStatement(QueryStatement queryStmt, boolean nameParameterMarkers, String varStem) {
        Class typeFilter = ValueExpressionVariable.class;
        Set hostVariableSet = getReferencesRecursively(queryStmt, typeFilter);
        ValueExpressionVariable var;
        String name, varname;
        Vector vHostVars = new Vector();
        Iterator hi = hostVariableSet.iterator();
        while (hi.hasNext())
        {
           var = (ValueExpressionVariable)hi.next();
           name = var.getName();
           if (name != null)
              vHostVars.add(name);
        }
        if (nameParameterMarkers)
        {
           // Give default names to any with null names (derived from ? markers)
           hi = hostVariableSet.iterator();
           while (hi.hasNext())
           {
              var = (ValueExpressionVariable)hi.next();
              name = var.getName();
              if (name == null)
              {
                 varname = getUniqueVarName(vHostVars, varStem);
                 vHostVars.add(varname);
                 var.setName(varname);
              }
           }
        }
        return new ArrayList(hostVariableSet);
    }
    

    
    /** Return a unique name for adding an attribute by appending a count to the
     * passed string and also checking for the existence of the name in the vector.
     *
     * @param attrNames All attribute names in the type including the
     *        inherited attribute names.
     * @param name Name that we append count to.
     *
     * @return unique name of the attribute to be added.
     */
    public static String getUniqueVarName(Vector varNames, String name) {
       String uniqueName = name;
       int count = 0;
       boolean found = true;
       if (varNames != null && varNames.size() > 0) {
          while (found) {
             uniqueName = concatName(name, ++count);
             found = !isNameUnique(varNames, uniqueName);
          }
       } else {
          uniqueName = concatName(name, ++count);
       }
       return uniqueName;
    }
    
    /** 
     * Checks the passed uniqueName is in the passed array names and returns
     * false if found, true otherwise.
     *
     * @param names Array of names
     * @param uniqueName The name to be checked.
     *
     * @return true if found  false otherwise.
     */
    public static boolean isNameUnique(Vector names, String uniqueName)  {
       int nameslength = names.size();
       for (int i = 0; i < nameslength; i++) {
          if (equalSQLIdentifiers((String) names.get(i), uniqueName, DELIMITED_IDENTIFIER_QUOTE))
             return false;
       }
       return true;
    }
    
    /**
     *  Return a new name concatenating the int (inside delimiters if need be)
     *  Assumes that the name is well-formed, ending with a delimiter if it
     *  begins with one.
     */
    public static String concatName(String name, int count)   {
       String nname = name.trim();
       int epos = name.length() - 1;
       if (nname.charAt(epos) == '"') {
          return name.substring(0, epos) + count + '"';
       } else {
          return name + count;
       }
    }

    /**
     * Returns the Database for which SQL Statement is associated with.
     * 
     * @param stmt the SQLQueryStatement for which Database is needed
     * @return the database
     */
    public static Database getDatabase(QueryStatement stmt) {
        Database db = null;
        TableExpression tableExpr = (TableExpression) getTablesForStatement(stmt).get(0);
        if (tableExpr != null) {
            Table table = TableHelper.getTableForTableExpression(tableExpr);
            Schema schema = table.getSchema();
            db = schema.getDatabase();
        }
        return db;
    }

    /**
     * Returns the <code>TableInDatabase</code> that the given
     * <code>columnExpr</code> was derived from, if the given column is not
     * the result of an expression in the <code>selectClause</code> of a
     * <code>QuerySelect</code> or the result column of a
     * <code>QueryValues</code>. The derived table of "c1" in "select t1.col1
     * as c1 from (select col1 from table1) as t1;" would be "table1".
     * 
     * @param columnExpr a <code>ValueExpressionColumn</code> that is part of
     *        a <code>TableExpression</code> s <code>columnList</code>(
     *        {@link TableExpression#getColumnList()}) as the tables exposed
     *        ordered result columns (not the <code>ResultColumn</code> of a
     *        <code>QuerySelect</code>)
     * @return the <code>TableInDatabase</code> the given column was derived
     *         from or <code>null</code> if the given column is not derived
     *         from a <code>TableInDatabase</code>
     */
    public static TableInDatabase getDerivedDatabaseTable(ValueExpressionColumn columnExpr) {
        TableInDatabase derivedTable = null;
        // we really should have a property "derivedFromTableInDatabase"
        // but for exposed result columns in a TableExpression's columnList
        // we use that property to keep th TableInDatabase that this column was
        // derived from
        // TableExpression colTable = columnExpr.getTableExpr();
        if (columnExpr.getTableInDatabase() != null) {
            derivedTable = columnExpr.getTableInDatabase();
        }
        else {
            // if columnExpr was not a exposed effective result column of a
            // TableExpression, but a normal column reference,
            // columnExpr.getTableExpr() was used to reference the table that
            // the column belongs to like "col1" in
            // "select * from table1 where col1 = 5;"
            // that is the intended use of this property!
            // we want to find the corresponding exposed column in the column's
            // referenced table
            ValueExpressionColumn exposedColumn = TableHelper.getColumnExpressionForName(columnExpr.getTableExpr(), columnExpr.getName());
            if (exposedColumn != null && exposedColumn.getTableInDatabase() != null) {
                derivedTable = exposedColumn.getTableInDatabase();
            }
        }

        return derivedTable;
    }

    /**
     * Returns the <code>TableInDatabase</code>s, that the given
     * <code>columnExpr</code>s were derived from, if the given columns are
     * not the result of an expression in the <code>selectClause</code> of a
     * <code>QuerySelect</code> or the result column of a
     * <code>QueryValues</code>. The derived tables of "select t1.col1,
     * t2.col1 from table1 t1, table2 t2" would be "table1" and "table2".
     * 
     * @param columnExprList a List of <code>ValueExpressionColumn</code>s
     *        that are part of a <code>TableExpression</code> s
     *        <code>columnList</code>(
     *        {@link TableExpression#getColumnList()}), the tables exposed
     *        ordered result columns (not the <code>ResultColumn</code> of a
     *        <code>QuerySelect</code>!)
     * @return the List of <code>TableInDatabase</code>s that the given
     *         columns were derived from, can be empty
     */
    public static List getDerivedDatabaseTables(List columnExprList) {
        Set derivedTables = new HashSet();

        for (Iterator it = columnExprList.iterator(); it.hasNext();) {
            ValueExpressionColumn colExpr = (ValueExpressionColumn) it.next();
            TableInDatabase derivedTable = getDerivedDatabaseTable(colExpr);
            if (derivedTable != null) {
                derivedTables.add(derivedTable);
            }
        }
        return new ArrayList(derivedTables);
    }

    /**
     * Returns all directly referenced Objects of the given <code>eObject</code>
     * that are of type or a subtype of the given <code>typeFilter</code>.
     * 
     * @param eObject the EObject we want to find refernces for
     * @param typeFilter optional the type of the refernced Objects to be
     *        returned, may be <code>null</code> or <code>Object.class</code>
     *        to not filter the refernces
     * @return the Set of referenced Objects
     */
    public static Set getDirectReferences(EObject eObject, Class typeFilter) {
        Set directRefs = new HashSet();

        for (Iterator refIt = eObject.eClass().getEAllReferences().iterator(); refIt.hasNext();) {
            EReference ref = (EReference) refIt.next();
            if (ref.isMany()) {
                EList refList = (EList) eObject.eGet(ref);
                for (Iterator refListIt = refList.iterator(); refListIt.hasNext();) {
                    EObject reference = (EObject) refListIt.next();
                    if (reference != null) {
                        if (typeFilter == null || typeFilter.isAssignableFrom(reference.getClass())) {
                            directRefs.add(reference);
                        }
                    }
                }
            }
            else {
                EObject reference = (EObject) eObject.eGet(ref);
                if (reference != null) {
                    if (typeFilter == null || typeFilter.isAssignableFrom(reference.getClass())) {
                        directRefs.add(reference);
                    }
                }
            }
        }

        return directRefs;
    }

    /**
     * Returns the {@link EObject#eContainer()}for the given
     * <code>eObject</code> that is assignable to the given type
     * <code>containerType</code>.
     * <p>
     * For the <code>ValueExpressionColumn</code> "col1" in the
     * <code>QuerySelect</code> that represents the query
     * <code>SELECT * FROM tbl1 WHERE col1 = 1</code>, the eConatiner would
     * be a <code>PredicateBasic</code> "col1 = 1", but the eConatiner of type
     * <code>QuerySelect</code> would be the whole <code>QuerySelect</code>,
     * that eContains the <code>PredicateBasic</code>.
     * 
     * @param eObject
     * @param containerType
     * @return <code>SQLQueryObject</code> of type <code>containerType</code>
     *         that ultimately contains the given <code>eObject</code>
     */
    public static SQLQueryObject getEContainerRecursively(EObject eObject, Class containerType) {
        SQLQueryObject container = null;

        if (eObject != null && containerType != null) {
            EObject eContainer = eObject.eContainer();
            while (eContainer instanceof SQLQueryObject) {
                if (containerType.isAssignableFrom(eContainer.getClass())) {
                    container = (SQLQueryObject) eContainer;
                    break;
                }
                eContainer = eContainer.eContainer();
            }
        }

        return container;
    }

    /**
     * @param queryExpr
     * @return List of <code>ValueExpressionColumn</code>s
     * @see #getEffectiveResultColumns(QuerySelectStatement)
     */
    private static List getEffectiveResultColumns(QueryExpressionBody queryExpr) {
        List effectiveResultColumnList = null;

        // has the queryExpr already been exposed its effective columns?
        if (queryExpr.getColumnList() != null && !queryExpr.getColumnList().isEmpty()) {
            // we assume we also have the DataTypes already
            effectiveResultColumnList = queryExpr.getColumnList();
        }
        else {
            effectiveResultColumnList = TableHelper.exposeEffectiveResultColumns(queryExpr);
        }

        return effectiveResultColumnList;
    }

    /**
     * This method compiles a list of <code>ValueExpressionColumn</code>s
     * that reflects the columns, in order and with datatype, which are the
     * result of the given <code>QuerySelectStatement</code>.
     * <p>
     * <b>Note:</b> this method will not return the <code>ResultColumn</code>
     * list of the given <code>selectStmt</code>'s <code>QuerySelect</code>,
     * because these don't necessarily reflect the effective result columns of a
     * star-query for example.
     * <p>
     * <b>Note:</b> the returned <code>ValueExpressionColumn</code>'s
     * <code>name</code> can be <code>null</code>, for example when the
     * given <code>QuerySelectStatement</code>'s query is of type
     * <code>QueryValues</code> ("VALUES (1,2),(3,4)") or if the query is a
     * <code>QuerySelect</code> with a unnamed <code>ResultColumn</code>
     * like in "SELECT COL1 + 10 FROM TABLE1" where "COL1 + 1" has no alias
     * name.
     * 
     * @param selectStmt
     * @return List of <code>ValueExpressionColumn</code>s
     */
    // retrieveEffectiveResultColumns
    public static List getEffectiveResultColumns(QuerySelectStatement selectStmt) {
        List resultValueExprList = new ArrayList();

        if (selectStmt != null && selectStmt.getQueryExpr() != null) {
            QueryExpressionBody queryExpr = selectStmt.getQueryExpr().getQuery();
            resultValueExprList = getEffectiveResultColumns(queryExpr);
        }
        return resultValueExprList;
    }

    /**
     * <b>Note:</b> to avoid too deep recursion
     * <code>referencePathFilter</code> and <code>typeFilter</code> will
     * implicitly contain <code>SQLObject.class</code>, so no references to
     * Objects of another type will be traversed.
     * 
     * give the EObject <code>referencedBy</code>, that referenced the given
     * EObject <code>p_object</code> so we can avoid traversing the
     * back-reference and don't end up in an endless loop.
     * 
     * 
     * @param eObject the object we want to find references of
     * @param referencedBy the object that referenced the given eObject so we
     *        can avoid traversing the back-reference and don't end up in an
     *        endless loop
     * @param references set of <code>EObject</code>s already referenced that
     *        that are of type or subtype of the given <code>typeFilter</code>
     * @param visited the set of <code>EObject</code>s already referenced but
     *        not necessarily in the <code>references</code> set of
     *        <code>EObject</code>s due to the given <code>typeFilter</code>
     * @param typeFilter optional the type of the referenced Objects to be
     *        returned, may be <code>null</code> or <code>Object.class</code>
     *        to not filter the references
     * @param referencePathFilter the types of references that are traversed to
     *        reach Objects indirectly referenced by the given
     *        <code>eObject</code>
     * 
     * @return Set of <code>EObject</code>s referenced
     */
    private static Set getEObjectReferencesRecursively(EObject eObject, EObject referencedBy, HashSet references, HashSet visited, Class typeFilter,
            Class[] referencePathFilter) {

        if (references == null) {
            references = new HashSet();
        }

        if (eObject == null)
            return references;

        if (visited == null) {
            visited = new HashSet();
        }

        // TODO: is there a better way to restrict reference traversion
        // we are only interested in SQL Query model objects
        if (referencePathFilter == null || referencePathFilter.length == 0) {
            referencePathFilter = new Class[] { SQLQueryObject.class };
        }

        // we might want to get all references;
        // - but then without following all it's references a second time
        // so we eliminate the loop by checking if the reference is already
        // recorded in the set of references
        // we eliminate the immediate back-flip with the comparison of
        // referencedBy
        // with eObject.eGet(Ref) so we compare our parent with our child

        // don't traverse references if we already traversed!
        if (visited.contains(eObject)) {
            return references;
        }
        else {
            if (isSubtypeOf(eObject, typeFilter)) {
                references.add(eObject);
            }
            visited.add(eObject);
        }

        // System.out.println("traversing references of: "+eObject);

        for (Iterator itCon = eObject.eClass().getEAllReferences().iterator(); itCon.hasNext();) {
            EReference ref = (EReference) itCon.next();
            if (ref.isMany()) {

                // System.out.println(" EList reference: "+ref.getName());

                EList list = (EList) eObject.eGet(ref);
                for (Iterator itChild = list.iterator(); itChild.hasNext();) {
                    EObject child = (EObject) itChild.next();
                    // don't go back where we came from
                    if (child != null && child != referencedBy && (isSubtypeOf(child, referencePathFilter) || isSubtypeOf(child, typeFilter))) {
                        getEObjectReferencesRecursively(child, eObject, references, visited, typeFilter, referencePathFilter);
                    }
                }
            }
            else {
                EObject child = (EObject) eObject.eGet(ref);
                // don't go back where we came from
                if (child != null && child != referencedBy && (isSubtypeOf(child, referencePathFilter) || isSubtypeOf(child, typeFilter))) {
                    getEObjectReferencesRecursively(child, eObject, references, visited, typeFilter, referencePathFilter);
                }
            }

        }

        return references;

    }

    /**
     * Returns "Having" clause for the given statement.
     * 
     * @param stmt the QueryStatement for which Having clause needs to be
     *        returned
     * @return the having clause
     */
    public static QuerySearchCondition getHavingCondition(QueryStatement stmt) {
        QuerySearchCondition havingCon = null;
        if (stmt instanceof QuerySelectStatement) {
            QuerySelectStatement selStmt = (QuerySelectStatement) stmt;
            QueryExpressionRoot qRoot = selStmt.getQueryExpr();
            if (qRoot != null && qRoot.getQuery() instanceof QuerySelect) {
                QuerySelect qSelect = (QuerySelect) qRoot.getQuery();
                havingCon = qSelect.getHavingClause();
            }
        }
        return havingCon;
    }

    public static QuerySearchCondition getHavingCondition(SQLQueryObject stmt) {
        QuerySearchCondition havingCon = null;
        if (stmt instanceof QueryStatement) {
            havingCon = getHavingCondition((QueryStatement) stmt);
        }
        else if (stmt instanceof QuerySelect) {
            QuerySelect qSelect = (QuerySelect) stmt;
            if (qSelect != null) {
                havingCon = qSelect.getHavingClause();
            }
        }
        return havingCon;
    }

    /**
     * Gets a list of the column value expression in the "modification operations" (Update/Insert) of the given 
     * Merge statement that are known to be references to columns in the target table of the Merge statement.
     * That is the columns on the left side of the Update Set expressions and the Insert target columns.  
     * @param mergeStmt the Merge statement for which the modification target columns are needed
     * @return the list of <code>ValueExpressionColumn</code> objects
     */
    public static List getMergeModificationTargetColumnReferences(QueryMergeStatement mergeStmt) {
        List colList = new ArrayList();
        
        List operSpecList = mergeStmt.getOperationSpecList();
        Iterator operSpecListIter = operSpecList.iterator();
        while (operSpecListIter.hasNext()) {
            MergeOperationSpecification operSpec = (MergeOperationSpecification) operSpecListIter.next();
            if (operSpec instanceof MergeUpdateSpecification) {
                MergeUpdateSpecification updateSpec = (MergeUpdateSpecification) operSpec;
                List assignExprList = updateSpec.getAssignementExprList();
                Iterator assignExprListIter = assignExprList.iterator();
                while (assignExprListIter.hasNext()) {
                    UpdateAssignmentExpression assignExpr = (UpdateAssignmentExpression) assignExprListIter.next();
                    List targetColList = assignExpr.getTargetColumnList();
                    colList.addAll(targetColList);
                }
            }
            else if (operSpec instanceof MergeInsertSpecification) {
                MergeInsertSpecification insertSpec = (MergeInsertSpecification) operSpec;
                List targetColList = insertSpec.getTargetColumnList();
                colList.addAll(targetColList);
            }
        }
        
        return colList;
    }
    
    /**
     * Returns the <code>Predicate</code> of the given <code>variable</code>
     * or <code>null</code>, if the <code>variable</code> has no reference
     * to a <code>Predicate</code>.
     * <p>
     * <b>Note:</b> If more than one reference of type <code>Predicate</code>
     * is found for the given <code>variable</code>, the first one found will
     * be returned arbitrarily.
     * </p>
     * 
     * @param variable
     * @return the <code>Predicate</code> referencing the given
     *         <code>ValueExpressionVariable</code>
     */
    public static Predicate getPredicateOfVariable(ValueExpressionVariable variable) {
        Set predicateSet = getReferencesViaSpecificReferencePaths(variable, Predicate.class, new Class[] { QueryValueExpression.class });
        Predicate pred1 = null;

        if (predicateSet != null && predicateSet.iterator().hasNext()) {
            pred1 = (Predicate) predicateSet.iterator().next();
        }

        return pred1;
    }

    /**
     * Returns the <code>QueryExpressionRoot</code> that indirectly contains
     * the given <code>tableRef</code>.
     * 
     * @param tableRef
     * @return
     */
    private static QueryExpressionRoot getQueryExpressionRootForTableReference(TableExpression tableRef) {
        QueryExpressionRoot queryExprRoot = null;
        Set oneQueryExprRootSet = null;

        Class targetType = QueryExpressionRoot.class;
        // make sure we get the local QueryExpressionRoot by only traversing the
        // tableReferences
        Class[] referenceToTraverse = new Class[] { TableReference.class };
        oneQueryExprRootSet = getReferencesViaSpecificReferencePaths(tableRef, targetType, referenceToTraverse);

        if (!oneQueryExprRootSet.isEmpty()) {
            queryExprRoot = (QueryExpressionRoot) oneQueryExprRootSet.iterator().next();
        }

        return queryExprRoot;
    }

    /**
     * Returns the <code>QuerySelect</code> that contains the given
     * <code>tableExpr</code> in its <code>fromClause</code>.
     * 
     * @param tableExpr
     * @return the containing <code>QuerySelect</code>
     */
    public static QuerySelect getQuerySelectForTableReference(TableExpression tableExpr) {
        QuerySelect querySelect = null;

        if (tableExpr != null) {
            EObject eContainer = tableExpr.eContainer();

            // the tableReference must be contained in a QuerySelect, either
            // directly or indirectly as contained in a TableJoined or
            // TableNested
            while (eContainer instanceof SQLQueryObject) {
                if (eContainer instanceof QuerySelect) {
                    querySelect = (QuerySelect) eContainer;
                    break;
                }
                eContainer = eContainer.eContainer();
            }
        }

        return querySelect;
    }

    /**
     * Returns the <code>QueryStatement</code> associated with the given
     * <code>tableRef</code>.
     * 
     * @param tableRef
     * @return
     */
    public static QueryStatement getQueryStatementForTableReference(TableReference tableRef) {
        QueryStatement queryStmt = null;

        Set oneQueryStmtSet = getReferencesRecursively(tableRef, QueryStatement.class);

        // should be only one QueryStatement associated with one table
        if (oneQueryStmtSet != null && !oneQueryStmtSet.isEmpty()) {
            queryStmt = (QueryStatement) oneQueryStmtSet.iterator().next();
        }

        return queryStmt;
    }

    /**
     * Returns all Objects recursively referenced by the given
     * <code>eObject</code>, that are of type or a subtype of the given
     * <code>typeFilter</code>.
     * 
     * @param eObject the object we want to find refernces of
     * @param typeFilter optional the type of the refernced Objects to be
     *        returned, may be <code>null</code> or <code>Object.class</code>
     *        to not filter the refernces
     * @return Set of <code>EObject</code> s referenced recursively of type
     *         assignable to <code>typeFilter</code>
     */
    public static Set getReferencesRecursively(EObject eObject, Class typeFilter) {
        return getEObjectReferencesRecursively(eObject, null, null, null, typeFilter, null);
    }

    /**
     * Returns Objects recursively referenced by the given <code>eObject</code>,
     * that are of type or a subtype of the given <code>typeFilter</code>.
     * Only such references will be traversed that are of one of the give types
     * in <code>referencePathTypes</code>.
     * 
     * @param eObject the object we want to find refernces of
     * @param typeFilter optional the type of the refernced Objects to be
     *        returned, may be <code>null</code> or <code>Object.class</code>
     *        to not filter the refernces
     * @param referencePathTypes the types of references that are traversed to
     *        reach Objects indirectly referenced by the given
     *        <code>eObject</code>
     * @return Set of <code>EObject</code>s referenced recursively
     */
    public static Set getReferencesViaSpecificReferencePaths(EObject eObject, Class typeFilter, Class[] referencePathTypes) {
        return getEObjectReferencesRecursively(eObject, null, null, null, typeFilter, referencePathTypes);
    }

    /**
     * Returns a List containing only the <code>QueryResultSpecification</code>s
     * in the given <code>queryResultSpecList</code> that are of type
     * <code>ResultTableAllColumns</code>.
     * 
     * @param queryResultSpecList a List of {@link QueryResultSpecification}
     * @return a List of {@link ResultTableAllColumns}
     */
    public static List getResultTableAllColumnsInQueryResultSpecificationList(List queryResultSpecList) {
        List resultTables = new ArrayList();

        for (Iterator resultIt = queryResultSpecList.iterator(); resultIt.hasNext();) {
            QueryResultSpecification resultSpec = (QueryResultSpecification) resultIt.next();

            if (resultSpec instanceof ResultTableAllColumns) {
                ResultTableAllColumns resultTable = (ResultTableAllColumns) resultSpec;

                resultTables.add(resultTable);
            }
        }

        return resultTables;
    }

    /**
     * Returns QuerySearchCondition for the given statement.
     * 
     * @param stmt the QueryStatement for which search condition needs to be
     *        returned
     * @return the search condition
     */
    public static QuerySearchCondition getSearchCondition(QueryStatement stmt) {
        QuerySearchCondition searchCon = null;
        if (stmt instanceof QueryUpdateStatement) {
            QueryUpdateStatement updStmt = (QueryUpdateStatement) stmt;
            searchCon = updStmt.getWhereClause();
        }
        else if (stmt instanceof QueryDeleteStatement) {
            QueryDeleteStatement delStmt = (QueryDeleteStatement) stmt;
            searchCon = delStmt.getWhereClause();
        }
        else if (stmt instanceof QuerySelectStatement) {
            QuerySelectStatement selStmt = (QuerySelectStatement) stmt;
            QueryExpressionRoot qRoot = selStmt.getQueryExpr();
            if (qRoot != null && qRoot.getQuery() instanceof QuerySelect) {
                QuerySelect qSelect = (QuerySelect) qRoot.getQuery();
                searchCon = qSelect.getWhereClause();
            }
        }
        else if (stmt instanceof QueryMergeStatement) {
            QueryMergeStatement mergeStmt = (QueryMergeStatement) stmt;
            MergeOnCondition mergeOnCond = mergeStmt.getOnCondition();
            searchCon = mergeOnCond.getSearchCondition();
        }
        
        return searchCon;
    }

    public static QuerySearchCondition getSearchCondition(SQLQueryObject stmt) {
        QuerySearchCondition searchCon = null;
        if (stmt instanceof QueryStatement) {
            searchCon = getSearchCondition((QueryStatement) stmt);
        }
        else if (stmt instanceof QuerySelect) {
            QuerySelect qSelect = (QuerySelect) stmt;
            if (qSelect != null) {
                searchCon = qSelect.getWhereClause();
            }
        }
        return searchCon;
    }

    /**
     * Generates SQL source for the given <code>queryObject</code> without any
     * comments and with the current schema qualified tables.
     * <p>
     * <b>Note: </b> this method is not <code>Thread</code> -safe, concurrent
     * calls to {@link SQLQueryObject#getSQL()} on the given
     * <code>queryObject</code> might not return the expected result.
     * 
     * @param queryObject a <code>SQLQueryObject</code> to get the SQL source
     *        for
     * @return the SQL source String of the given <code>queryObject</code>
     *         without any comments and qualified tables
     */
    public static String getSQLForExecution(SQLQueryObject queryObject) {
        SQLQuerySourceInfo sourceInfo = queryObject.getSourceInfo();
        SQLQuerySourceFormat sourceFormat = sourceInfo.getSqlFormat();
        // save the original preserveComments option and omitSchema
        boolean preserveComments = sourceFormat.isPreserveComments();
        String omitSchema = sourceFormat.getOmitSchema();
        // temporarily reset sourceFormat options
        sourceFormat.setPreserveComments(false);
        sourceFormat.setOmitSchema(null);
        String sql = queryObject.getSQL();
        // reset to the previous preserveComments option and omitSchema
        sourceFormat.setPreserveComments(preserveComments);
        sourceFormat.setOmitSchema(omitSchema);

        return sql;
    }

    /**
     * Returns the SQL source text of the given <code>SQLQueryObject</code>
     * with all identifiers (table and column references) qualified according to
     * the given <code>qualificationSpec</code>.
     * 
     * <p>
     * <b>Note: </b> this method is not <code>Thread</code> -safe, concurrent
     * calls to {@link SQLQueryObject#getSQL()} on the given
     * <code>queryObject</code> might not return the expected or even a
     * semantically invalid result.
     * 
     * @param queryObject
     * @param qualificationSpec
     *        <ul>
     *        <li> all identifiers qualified with schema names (incl. table
     *        names):
     *        {@link SQLQuerySourceFormat#QUALIFY_IDENTIFIERS_WITH_SCHEMA_NAMES}
     *        <li> all identifiers qualified with table names and without
     *        schema:
     *        {@link SQLQuerySourceFormat#QUALIFY_IDENTIFIERS_WITH_TABLE_NAMES}
     *        <li> all identifiers unqualified (column references without table
     *        names and table names without schema):
     *        {@link SQLQuerySourceFormat#QUALIFY_IDENTIFIERS_NEVER}
     *        <li> all identifiers qualified according to the context of the SQL
     *        statement:
     *        {@link SQLQuerySourceFormat#QUALIFY_IDENTIFIERS_IN_CONTEXT}
     *        </ul>
     * @return SQL source of the given <code>SQLQueryObject</code>
     */
    public static String getSQLQualified(SQLQueryObject queryObject, int qualificationSpec) {
        String sql = null;
        SQLQuerySourceInfo srcInfo = queryObject.getSourceInfo();
        SQLQuerySourceFormat sourceFormat = srcInfo.getSqlFormat();
        // preserve the original policy for qualification of identifiers
        // from now on to the reset of the original flag, calls to
        // queryObject.getSQL() may return unexpected results
        int origQualifySpec = sourceFormat.getQualifyIdentifiers();
        sourceFormat.setQualifyIdentifiers(qualificationSpec);
        sql = queryObject.getSQL();
        // reset the original policy for identifier qualification
        sourceFormat.setQualifyIdentifiers(origQualifySpec);
        return sql;
    }

    /**
     * Returns the SQL source text of the given <code>SQLQueryObject</code>
     * with all identifiers (table and column references) qualified with their
     * schema name.
     * <p>
     * <b>Note: </b> this method is not <code>Thread</code> -safe, concurrent
     * calls to {@link SQLQueryObject#getSQL()} on the given
     * <code>queryObject</code> might not return the expected result.
     * 
     * @param queryObject
     * @return SQL source of the given <code>SQLQueryObject</code>
     */
    public static String getSQLSchemaQualified(SQLQueryObject queryObject) {
        return getSQLQualified(queryObject, SQLQuerySourceFormat.QUALIFY_IDENTIFIERS_WITH_SCHEMA_NAMES);
    }

    /**
     * Returns the generated SQL source for the given <code>queryObject</code>
     * without any formatting and without comments in a single line. The
     * returned String will have no occurrence of more than one blank " " or and
     * no occurrence of a line break "\n", so that for any given
     * <code>SQLQueryObject</code> <code>queryObject</code>
     * <code>getSQLSourceUnformatted(queryObject).indexOf(" "+" ") &lt; 0</code>
     * is <code>true</code> and
     * <code>getSQLSourceUnformatted(queryObject).indexOf("\n") &lt; 0</code>
     * is <code>true</code>.
     * 
     * @param queryObject a <code>SQLQueryObject</code> to get the SQL source
     *        for
     * @return the SQL source String of the given <code>queryObject</code>
     *         without any formatting
     */
    public static String getSQLSourceUnformatted(SQLQueryObject queryObject) {
        SQLQuerySourceInfo sourceInfo = queryObject.getSourceInfo();
        SQLQuerySourceFormat sourceFormat = sourceInfo.getSqlFormat();
        // save the original preserveComments option
        boolean preserveComments = sourceFormat.isPreserveComments();
        sourceFormat.setPreserveComments(false);
        String toBeTrimmed = queryObject.getSQL();
        toBeTrimmed = stripWhiteSpace(toBeTrimmed, DELIMITED_IDENTIFIER_QUOTE);
        // reset to the previous preserveComments option
        sourceFormat.setPreserveComments(preserveComments);

        return toBeTrimmed;
    }

    /**
     * Returns the given SQL source String in a single line without any
     * formatting. The returned String will have no occurrence of more than one
     * blank " " or and no occurrence of a line break "\n", so that for any given
     * <code>QueryStatement</code> <code>queryStmt</code>
     * <code>getSQLSourceUnformatted(queryStmt).indexOf(" "+" ") &lt; 0</code>
     * is <code>true</code> and
     * <code>getSQLSourceUnformatted(queryStmt).indexOf("\n") &lt; 0</code> is
     * <code>true</code>.
     * <p>
     * <b>Note:</b> this method only works on SQL statements without
     * single-line comments! Use
     * {@link #stripWhiteSpaceAndComments(String, char)} for SQL that contains
     * comments.
     * 
     * @param queryStmt the SQL source String of a <code>QueryStatement</code>
     * @return the given <code>queryStmt</code> SQL source without any
     *         formatting
     */
    public static String getSQLSourceUnformatted(String queryStmt) {
        String toBeTrimmed = queryStmt;
        StringBuffer trimmed = new StringBuffer();
        char SPACE = ' ';
        char NEW_LINE = '\n';
        char lastChar = SPACE;
        for (int i = 0; i < toBeTrimmed.length(); i++) {
            char currentChar = toBeTrimmed.charAt(i);

            if (currentChar == NEW_LINE) {
                if (lastChar != SPACE) {
                    trimmed.append(SPACE);
                    lastChar = SPACE;
                }
            }
            else if (currentChar != SPACE || lastChar != SPACE) {
                trimmed.append(currentChar);
                lastChar = currentChar;
            }
        }
        return trimmed.toString();
    }

    /**
     * Returns the SQL source text of the given <code>SQLQueryObject</code>
     * with all column references qualified with table names, but all table
     * names unqualified (without schema name).
     * <p>
     * <b>Note: </b> this method is not <code>Thread</code> -safe, concurrent
     * calls to {@link SQLQueryObject#getSQL()} on the given
     * <code>queryObject</code> might not return the expected or even a
     * semantically invalid result.
     * 
     * @param queryObject
     * @return SQL source of the given <code>SQLQueryObject</code>
     */
    public static String getSQLTableQualified(SQLQueryObject queryObject) {
        return getSQLQualified(queryObject, SQLQuerySourceFormat.QUALIFY_IDENTIFIERS_WITH_TABLE_NAMES);
    }

    /**
     * Returns the SQL source text of the given <code>SQLQueryObject</code>
     * with all identifiers unqualified (column references without table names,
     * and all table references without schema names).
     * <p>
     * <b>Note: </b> this method is not <code>Thread</code> -safe, concurrent
     * calls to {@link SQLQueryObject#getSQL()} on the given
     * <code>queryObject</code> might not return the expected or even a
     * semantically invalid result.
     * 
     * @param queryObject
     * @return SQL source of the given <code>SQLQueryObject</code>
     */
    public static String getSQLUnqualified(SQLQueryObject queryObject) {
        return getSQLQualified(queryObject, SQLQuerySourceFormat.QUALIFY_IDENTIFIERS_NEVER);
    }

    /**
     * Returns the generated SQL source for the given <code>queryObject</code>
     * with all comments.
     * 
     * @param queryObject a <code>SQLQueryObject</code> to get the SQL source
     *        for
     * @return the SQL source String of the given <code>queryObject</code>
     *         with all comments
     */
    public static String getSQLWithComments(SQLQueryObject queryObject) {
        SQLQuerySourceInfo sourceInfo = queryObject.getSourceInfo();
        SQLQuerySourceFormat sourceFormat = sourceInfo.getSqlFormat();
        // save the original preserveComments option
        boolean preserveComments = sourceFormat.isPreserveComments();
        boolean generateComments = sourceFormat.isGenerateCommentsForStatementOnly();
        sourceFormat.setPreserveComments(true);
        sourceFormat.setGenerateCommentsForStatementOnly(false);
        String sql = queryObject.getSQL();
        // reset to the previous preserveComments option
        sourceFormat.setPreserveComments(preserveComments);
        sourceFormat.setGenerateCommentsForStatementOnly(generateComments);
        return sql;
    }

    /**
     * Returns the generated SQL source for the given <code>queryObject</code>
     * without any comments.
     * 
     * @param queryObject a <code>SQLQueryObject</code> to get the SQL source
     *        for
     * @return the SQL source String of the given <code>queryObject</code>
     *         without any comments
     */
    public static String getSQLWithoutComments(SQLQueryObject queryObject) {
        SQLQuerySourceInfo sourceInfo = queryObject.getSourceInfo();
        SQLQuerySourceFormat sourceFormat = sourceInfo.getSqlFormat();
        // save the original preserveComments option
        boolean preserveComments = sourceFormat.isPreserveComments();
        sourceFormat.setPreserveComments(false);
        String sql = queryObject.getSQL();
        // reset to the previous preserveComments option
        sourceFormat.setPreserveComments(preserveComments);

        return sql;
    }

    /**
     * Returns the integer constant defined in this class, that represents the
     * given QueryStatement
     * 
     * @param statement the QueryStatement
     * @return the integer constatnt defined in this class, that represents the
     *         given QueryStatement or -1 if no matching is found
     */
    public static int getStatementType(QueryStatement statement) {
        int type = -1;
        if (statement instanceof QuerySelectStatement) {
            type = STATEMENT_TYPE_SELECT;
            QueryExpressionRoot root = ((QuerySelectStatement) statement).getQueryExpr();
            if (root != null) {
                QueryExpressionBody qBody = root.getQuery();
                if (qBody instanceof QueryCombined) {
                    type = STATEMENT_TYPE_FULLSELECT;
                }
                List withList = root.getWithClause();
                if (!withList.isEmpty()) {
                    type = STATEMENT_TYPE_WITH;
                }
            }

        }
        else if (statement instanceof QueryInsertStatement) {
            type = STATEMENT_TYPE_INSERT;
        }
        else if (statement instanceof QueryUpdateStatement) {
            type = STATEMENT_TYPE_UPDATE;
        }
        else if (statement instanceof QueryDeleteStatement) {
            type = STATEMENT_TYPE_DELETE;
        }
        else if (statement instanceof QueryMergeStatement) {
            type = STATEMENT_TYPE_MERGE;
        }
        
        return type;
    }

    /**
     * Returns the TableExpression representing the given Table object,in the
     * given QuerySelect
     * 
     * @param table the Table object
     * @param qSelect the QuerySelect in which the TableExpression is searched
     *        for
     * @return the TableExpression object, for the Table object,or null if none
     *         is found
     */
    public static TableExpression getTableExpressionForTable(Table table, QuerySelect qSelect) {
        TableExpression tableExpr = null;
        if (qSelect != null) {
            List tableExprList = StatementHelper.getTableExpressionsInQuerySelect(qSelect);
            String name = table.getName();
            tableExpr = TableHelper.getTableExpressionFromTableExprList(name, tableExprList);
        }
        return tableExpr;
    }

    /**
     * Returns the TableExpression representing the given Table object,in the
     * given QuerySelectStatement
     * 
     * @param table the Table object
     * @param selectStmt the QuerySelectStatement in which the TableExpression
     *        is searched for
     * @return the TableExpression object, for the Table object,or null if none
     *         is found
     */
    public static TableExpression getTableExpressionForTable(Table table, QuerySelectStatement selectStmt) {
        TableExpression tableExpr = null;
        if (selectStmt != null) {
            QueryExpressionRoot root = selectStmt.getQueryExpr();
            if (root != null) {
                QueryExpressionBody query = root.getQuery();
                List tableExprList = StatementHelper.getTableExpressionsInQueryExpressionBody(query);
                String name = table.getName();
                tableExpr = TableHelper.getTableExpressionFromTableExprList(name, tableExprList);
            }
        }
        return tableExpr;
    }

    /**
     * @param query
     * @return
     */
    public static List getTableExpressionsInQueryExpressionBody(QueryExpressionBody query) {
        List tableExprList = new ArrayList();

        if (query != null) {

            if (query instanceof QueryNested) {
                QueryNested queryNest = (QueryNested) query;
                QueryExpressionBody nestedQuery = queryNest.getNestedQuery();
                tableExprList.addAll(getTableExpressionsInQueryExpressionBody(nestedQuery));
            }
            if (query instanceof QuerySelect) {
                tableExprList.addAll(getTableExpressionsInQuerySelect((QuerySelect) query));
            }
            if (query instanceof QueryCombined) {
                QueryCombined combined = (QueryCombined) query;
                tableExprList.addAll(getTableExpressionsInQueryExpressionBody(combined.getLeftQuery()));
                tableExprList.addAll(getTableExpressionsInQueryExpressionBody(combined.getRightQuery()));
            }
        }
        return tableExprList;
    }

    public static List getTableExpressionsInQuerySelect(QuerySelect querySelect) {
        List tableExprList = new ArrayList();
        if (querySelect != null) {
            List fromClause = querySelect.getFromClause();
            Iterator fromClauseItr = fromClause.iterator();
            while (fromClauseItr.hasNext()) {
                TableReference tableRef = (TableReference) fromClauseItr.next();
                if (tableRef instanceof TableExpression) {
                    tableExprList.add(tableRef);
                }
                else if (tableRef instanceof TableNested) {
                    tableExprList.addAll(JoinHelper.getTablesInNestedTable((TableNested) tableRef));
                }
                else if (tableRef instanceof TableJoined) {
                    tableExprList.addAll(JoinHelper.getTablesInJoin((TableJoined) tableRef));
                }
            }
        }
        return tableExprList;
    }

    /**
     * Returns the <code>TableExpression</code>s in the given
     * <code>QuerySelect</code>'s <code>fromClause</code> plus all the
     * <code>TableExpression</code>s that are visible in the scope of the
     * given <code>QuerySelect</code>.  If the <code>QuerySelect</code>
     * given is a nested query or sub-query, all the <code>TableExpression</code>s 
     * of the super query are appended to the returned list of
     * <code>TableExpression</code>s.
     * 
     * @param querySelect the <code>QuerySelect</code> for which the visible table expressions
     * are needed
     * @return List of visible <code>TableExpression</code>s
     */
    public static List getTableExpressionsVisibleInQuerySelect(QuerySelect querySelect) {
        List tableExprList = new ArrayList();

        if (querySelect != null) {
            /* First get the tables contained in the query select. */
            List fromClause = querySelect.getFromClause();
            tableExprList.addAll(TableHelper.getTableExpressionsInTableReferenceList(fromClause));

            /* If the QuerySelect is a nested table query then the other tables in the FROM clause of 
             * the query select that contains the nested table query are not visible in the nested table query.
             * Here's an example:
             *   select firstnme from (select e.firstnme from employee e
             *   where EMP.firstnme = 'JOHN') as e2, employee EMP
             *   SQL0206N "EMP.FIRSTNME" is not valid in the context where it is used. SQLSTATE=42703
             * And another:
             *   select deptno from department DEPT
             *   where deptno in (select workdept from (select e.workdept from employee e
             *     where e.firstnme = 'JOHN' and e.workdept = DEPT.deptno) as emp)
             *   SQL0204N "DEPT.DEPTNO" is an undefined name. SQLSTATE=42704
             *
             * That means that if the given select is part of the tables in the super
             * query's FROM-clause, we don't add add the superior queries' tables as they 
             * are not visible/allowed
             *
             * However that's not true for a "lateral" table query (TableQueryLateral). In a
             * lateral table query, table references that are to the left of the table query in 
             * the same FROM clause or in a superior query ARE visible to the table query.
             * A lateral table query is indicated by the keyword LATERAL or TABLE ahead of the
             * table query definition.  So the second example above will work if modified to 
             * look like this:
             *   select deptno from department DEPT
             *   where deptno in (select workdept from table (select e.workdept from employee e
             *     where e.firstnme = 'JOHN' and e.workdept = DEPT.deptno) as emp)
             * Note: the first example above would fail even with the TABLE keyword added because 
             * the table reference EMP is to the right of the table query.  
             */ 
            if (!isQuerySelectNestedQuery(querySelect) || isQuerySelectLateral(querySelect)) {
                /* Check if the QuerySelect is contained in a superior QuerySelect.  If it is, get the
                 * tables visible in that query. */
                EObject eContainer = querySelect.eContainer();
                while (eContainer instanceof SQLQueryObject) {
                    if (eContainer instanceof QuerySelect) {
                        QuerySelect superSelect = (QuerySelect) eContainer;
                        List superTables = getTableExpressionsVisibleInQuerySelect(superSelect);
                        tableExprList.addAll(superTables);
                        break;
                    }
                    /* If the container is a lateral query table, that changes the visibility rules. */
                    if (eContainer instanceof TableQueryLateral) {
                        TableQueryLateral queryLateral = (TableQueryLateral) eContainer;
                        /* Get the QuerySelect that encloses this lateral table query. */
                        SQLQueryObject containerQuery = getEContainerRecursively(queryLateral, QuerySelect.class);
                        if (containerQuery instanceof QuerySelect) {
                            List visibleTableExprList = getTableExpressionsVisibleLaterally(queryLateral, (QuerySelect) containerQuery);
                            tableExprList.addAll(visibleTableExprList);
                        }
                        /* Bypass the container query and continue searching up the containment hierarchy. */
                        eContainer = containerQuery;
                    }
                    /* If the container is a QueryExpressionRoot, we need to check to see if it is attached to
                     * a ValueExpressionScalarSelect.  This can occur when we have a QuerySelect in an
                     * IN clause, for example. In this case, we need to work our way up that chain rather than
                     * going to the container, since the container is null. (This is a model design bug.) */
                    else if (eContainer instanceof QueryExpressionRoot) {
                        QueryExpressionRoot queryRoot = (QueryExpressionRoot) eContainer;
                        List scalarSelectList = queryRoot.getValueExprScalarSelects();
                        if (scalarSelectList != null && scalarSelectList.size() > 0) {
                            Object listObj = scalarSelectList.get(0);
                            if (listObj instanceof ValueExpressionScalarSelect) {
                                ValueExpressionScalarSelect scalarSelect = (ValueExpressionScalarSelect) listObj;
                                eContainer = scalarSelect;
                            }
                        }
                    }
                    else if (eContainer instanceof QueryUpdateStatement) {
                        QueryUpdateStatement updateStmt = (QueryUpdateStatement) eContainer;
                        List superTables = getTablesForStatement(updateStmt);
                        tableExprList.addAll(superTables);
                    }
                    else if (eContainer instanceof QueryDeleteStatement) {
                        QueryDeleteStatement deleteStmt = (QueryDeleteStatement) eContainer;
                        List superTables = getTablesForStatement(deleteStmt);
                        tableExprList.addAll(superTables);
                    }
                    else if (eContainer instanceof QueryMergeStatement) {
                        // The source (INTO) table of the Merge statement is NOT visible to a
                        // QuerySelect in the source (USING) clause.  So don't need to add it.
                    }
                    eContainer = eContainer.eContainer();
                }
            }

        }
        return tableExprList;
    }

    /**
     * Gets a list of table expressions that are "lateral" (that is, to the left)
     * of the given lateral table query object in the same FROM clause that contains
     * the lateral table query.  
     * 
     * @param queryLateral the table query for which lateral table expressions are needed
     * @param containerQuery the query select object that contains  the lateral table query
     * @return a list of table expressions that are visible laterally
     */
    public static List getTableExpressionsVisibleLaterally(TableQueryLateral queryLateral, QuerySelect containerQuery) {
        List visibleTableExprList = new ArrayList();
        
        if (queryLateral != null && containerQuery != null) {
            /* Get a list of table expressions fromr the FROM clause of the containing query. */
            List fromClause = containerQuery.getFromClause();
            List tableExprList = TableHelper.getTableExpressionsInTableReferenceList(fromClause);
            /* Build a list of all of the table expressions ahead (to the left) of the lateral query table object. */
            if (tableExprList != null && tableExprList.size() > 0) {
                Object tableExprListObj = null;
                Iterator tableExprListIter = tableExprList.iterator();
                while (tableExprListObj != queryLateral && tableExprListIter.hasNext() ) {
                    tableExprListObj = tableExprListIter.next();
                    visibleTableExprList.add(tableExprListObj);
                }                 
            }
        }
        
        return visibleTableExprList;
    }

    /**
     * Returns List of tables (<code>TableExpression</code>) referenced in
     * the statement. For Select statement it returns the source tables of the
     * "From" clause. For Insert, Update and Delete returns the target table.
     * For Merge it returns the target (INTO) table and the source (USING) table.
     * 
     * @param stmt the SQLQueryStatement from which the table are wanted
     * @return the List of table
     */
    public static List getTablesForStatement(QueryStatement stmt) {
        List tableList = new ArrayList();

        if (stmt == null) {
            // we hate NullPointers!
            return tableList;
        }
        else if (stmt instanceof QueryInsertStatement) {
            QueryInsertStatement insertStmt = (QueryInsertStatement) stmt;
            TableExpression tableExpr = insertStmt.getTargetTable();
            if (tableExpr != null) {
                tableList.add(tableExpr);
            }
        }
        else if (stmt instanceof QueryUpdateStatement) {
            QueryUpdateStatement updateStmt = (QueryUpdateStatement) stmt;
            TableExpression tableExpr = updateStmt.getTargetTable();
            if (tableExpr != null) {
                tableList.add(tableExpr);
            }
        }
        else if (stmt instanceof QueryDeleteStatement) {
            QueryDeleteStatement deleteStmt = (QueryDeleteStatement) stmt;
            TableExpression tableExpr = deleteStmt.getTargetTable();
            if (tableExpr != null) {
                tableList.add(tableExpr);
            }
        }
        else if (stmt instanceof QuerySelectStatement) {
            QuerySelectStatement selectStmt = (QuerySelectStatement) stmt;
            QueryExpressionRoot queryExpr = selectStmt.getQueryExpr();
            if (queryExpr != null) {
                QueryExpressionBody query = queryExpr.getQuery();
                if (query != null) {
                    tableList.addAll(getTableExpressionsInQueryExpressionBody(query));
                }
            }
        }
        else if (stmt instanceof QueryMergeStatement) {
            QueryMergeStatement mergeStmt = (QueryMergeStatement) stmt;
            
             MergeTargetTable mergeTarget = mergeStmt.getTargetTable();
             TableExpression tableExpr = mergeTarget.getTableExpr();
             tableList.add(tableExpr);
             
             MergeSourceTable mergeSource = mergeStmt.getSourceTable();
             TableReference tableRef = mergeSource.getTableRef();
             tableList.add(tableRef);
        }

        return tableList;
    }

    public static List getTablesForStatement(SQLQueryObject stmt) {
        List tableList = new ArrayList();

        if (stmt == null) {
            // we hate NullPointers!
            return tableList;
        }
        else if (stmt instanceof QueryStatement) {
            tableList.addAll(getTablesForStatement((QueryStatement) stmt));
        }
        else if (stmt instanceof QuerySelect) {
            tableList.addAll(getTableExpressionsInQuerySelect((QuerySelect) stmt));
        }

        return tableList;
    }

    /**
     * Returns the default SQL template for a statement
     * 
     * @param statementType the type of the statement
     * @return the SQL template
     */
    public static String getTemplateSQL(int statementType) {
        String templateSQL = null;
        QueryStatement stmt = StatementHelper.createQueryStatement(statementType, "");
        if (stmt != null) {
            templateSQL = stmt.getSQL();
        }
        return templateSQL;
    }

    /**
     * Returns the <code>TableWithSpecification</code> with the given
     * <code>withTableName</code> from the given List of
     * <code>WithTableSpecification</code> s or <code>null</code> if the
     * given <code>withTableSpecList</code> contains no
     * <code>WithTableSpecification</code> with that <code>name</code>.
     * 
     * @param withTableName a name refering to a
     *        <code>WithTableSpecification</code>
     * @param withTableSpecList List of <code>WithTableSpecification</code>
     * @return the <code>WithTableSpecification</code> with the given
     *         <code>name</code> or <code>null</code>
     */
    private static WithTableSpecification getWithTableSpecificationForName(String withTableName, List withTableSpecList) {
        WithTableSpecification withTableFound = null;

        if (withTableName != null && withTableSpecList != null) {

            for (Iterator withIt = withTableSpecList.iterator(); withIt.hasNext();) {
                WithTableSpecification tempWithTable = (WithTableSpecification) withIt.next();

                String tempWithTableName = tempWithTable.getName();

                if (equalSQLIdentifiers(withTableName, tempWithTableName)) {
                    withTableFound = tempWithTable;
                    break;
                }

            }

        }

        return withTableFound;
    }

    /**
     * Returns the <code>TableWithSpecification</code> with the given
     * <code>withTableName</code> that is visible within the scope of the
     * given <code>querySelect</code>. If none is found <code>null</code>
     * is returned.
     * 
     * @param withTableName a name refering to a
     *        <code>WithTableSpecification</code>
     * @param querySelect <code>QuerySelect</code> in which's scope the
     *        WITH-table is referenced
     * @return the <code>WithTableSpecification</code> with the given
     *         <code>name</code> or <code>null</code>
     */
    private static WithTableSpecification getWithTableSpecificationForNameRecursively(String withTableName, QuerySelect querySelect) {
        WithTableSpecification withTableFound = null;

        if (querySelect != null) {

            EObject eContainer = querySelect.eContainer();

            // traverse the container hierarchy upwards and find the
            // QueryExpressionRoots
            // that could contain WITH-table specifications that are
            // relevant/referenced
            // w/in the scope of the given QuerySelect, don't traverse higher
            // than the
            // QuerySelectStatement, only travers SQLQueryObjects
            while (withTableFound == null && eContainer instanceof SQLQueryObject) {
                if (eContainer instanceof QueryExpressionRoot) {
                    QueryExpressionRoot queryExprRt = (QueryExpressionRoot) eContainer;
                    List withTableSpecList = queryExprRt.getWithClause();
                    withTableFound = getWithTableSpecificationForName(withTableName, withTableSpecList);

                }
                else if (eContainer instanceof QuerySelectStatement) {
                    // precaution to not traverse up higher than the
                    // QuerySelectStatement
                    break;
                }
                eContainer = eContainer.eContainer();
            }
        }

        return withTableFound;
    }

    /**
     * Returns true only if the name of the given column expression is equal 
     * to another column's name in a different table.  This check only applies
     * to columns in QuerySelect objects and in Merge statements.
     * 
     * @param colExpr the column expression to check
     * @return true when the column name is ambiguous in its context, otherwise false
     */
    public static boolean isColumnNameAmbiguous(ValueExpressionColumn colExpr) {
        boolean isAmbiguous = false;

        if (colExpr != null && colExpr.getTableExpr() != null) {
            TableExpression colTableExpr = colExpr.getTableExpr();
            String colName = colExpr.getName();
            List tableRefList = new ArrayList();
            TableExpression mergeTargetTableExpr = null;
            
            /* Get a list of the visible table references.  First assume we have a QuerySelect as 
             * the column's container, and try to get it. */
            QuerySelect querySelect = (QuerySelect) getEContainerRecursively(colExpr, QuerySelect.class);
            if (querySelect != null) {
                /* Get all the tables that are in scope.  (That is, tables of the local query and all
                 * of its super queries.) */
                tableRefList = getTableExpressionsVisibleInQuerySelect(querySelect);
            }
            /* If we don't have a QuerySelect, then see if the container is a Merge statement, and
             * get the visible table ref list from that. */
            else {
                QueryMergeStatement mergeStmt = (QueryMergeStatement) getEContainerRecursively(colExpr, QueryMergeStatement.class);
                /* When we have a Merge statement, get a list of the statement's table references. */
                if (mergeStmt != null) {
                    tableRefList = getTablesForStatement(mergeStmt);
                    MergeTargetTable mergeTargetTable = mergeStmt.getTargetTable();
                    mergeTargetTableExpr = mergeTargetTable.getTableExpr();
                }
            }

            if (tableRefList.size() > 1) {
                /* Check the column against the columns of the table in the table ref list. */
                Iterator tableRefListIter = tableRefList.iterator();
                while (isAmbiguous == false && tableRefListIter.hasNext()) {
                    TableExpression tableRef = (TableExpression) tableRefListIter.next();

                    /* Make sure we don't compare a table to itself.  Also don't compare to
                     * a table ref that is actually the QuerySelect that contains the column.
                     * For example in "select col1 from (select col1 from table0 where col1 = 5) as query0;"
                     * the nested query "select col1 from table0 where col1 = 5" has a 
                     * "result" or "exposed" column of "col1".  There is also a column "col1" in 
                     * the super query as an exposed column of the virtual table "query0".  
                     * But "query0.col1" is not visible in the nested query and is not ambiguous 
                     * to "table0.col1".     
                     * Also note that columns in the Merge target table are not ambiguous. */
                    if (tableRef != colTableExpr && tableRef != querySelect) {

                        /* Check if the table ref was already populated with its columns.  
                         * If so, see if the table ref has a column with the same name.  This can pick up
                         * ambiguities from the query definition itself, not just from database tables. */
                        if (tableRef.getColumnList() != null) {
                            ValueExpressionColumn tableRefCol = TableHelper.getColumnExpressionForName(tableRef, colName);
                            if (tableRefCol != null) {
                                isAmbiguous = true;
                            }
                        }
                        /* If we have a database table, the check its columns. */
                        if (isAmbiguous == false && tableRef instanceof TableInDatabase) {
                            Column dbTableCol = TableHelper.getColumnForName((TableInDatabase) tableRef, colName);
                            if (dbTableCol != null) {
                                isAmbiguous = true;
                            }
                        }
                        /* In the case the table expr doesn't have column info, we can try to find out if there 
                         * are other columns with the same name that reference the table. */
                        if (isAmbiguous == false && TableHelper.isTableReferencedByColumnWithName(tableRef, colName)) {
                            isAmbiguous = true;
                        }
                    }
                }
            }
        }
        
        return isAmbiguous;
    }

    /**
     * Returns <code>true</code>, if at least one
     * <code>OrderBySpecification</code> contained in the given
     * <code>orderByClause</code> list is valid.
     * 
     * @return
     */
    public static boolean isOrderByClauseContainsValidOrderBySpecification(List orderByClause) {
        boolean isOrderByClauseValid = false;

        if (orderByClause != null && !orderByClause.isEmpty()) {
            for (Iterator orderIt = orderByClause.iterator(); orderIt.hasNext();) {
                OrderBySpecification orderBy = (OrderBySpecification) orderIt.next();

                if (isOrderBySpecificationValid(orderBy)) {
                    isOrderByClauseValid = true;
                }

            }
        }

        return isOrderByClauseValid;
    }

    /**
     * @param orderBySpec
     * @return
     */
    public static boolean isOrderBySpecificationValid(OrderBySpecification orderBySpec) {
        boolean isOrderBySpecValid = false;

        if (orderBySpec != null) {
            if (orderBySpec instanceof OrderByOrdinal) {
                OrderByOrdinal orderOrdinal = (OrderByOrdinal) orderBySpec;
                isOrderBySpecValid = orderOrdinal.getOrdinalValue() > 0;
            }
            else if (orderBySpec instanceof OrderByResultColumn) {
                OrderByResultColumn orderColumn = (OrderByResultColumn) orderBySpec;
                isOrderBySpecValid = orderColumn.getResultCol() != null && orderColumn.getResultCol().getValueExpr() != null;
            }
            else if (orderBySpec instanceof OrderByValueExpression) {
                OrderByValueExpression orderExpr = (OrderByValueExpression) orderBySpec;
                isOrderBySpecValid = orderExpr.getValueExpr() != null;
            }

        }

        return isOrderBySpecValid;
    }

    /**
     * Determines whether or not the given query select object is (or is part of)
     * the query expression contained in a "lateral" query table object.
     * A "lateral" query select is a nested query introduced by the TABLE or
     * LATERAL keyword.  Here's an example:
     * 
     * SELECT d.deptname, e2.lastname 
     * FROM department d, TABLE (SELECT lastname FROM employee e where e.workdept = d.deptno) e2 
     * 
     * @param querySelect the query select object to check
     * @return true when the query select is lateral, otherwise false
     */
    private static boolean isQuerySelectLateral(QuerySelect querySelect) {
        boolean isLateral = false;
        if (querySelect != null) {
            EObject eContainer = querySelect.eContainer();
            
            while (eContainer instanceof TableReference && isLateral == false) {
                if (eContainer instanceof TableQueryLateral) {
                    isLateral = true;
                }
                eContainer = eContainer.eContainer();
            }
        }
        
        return isLateral;
    }

    /**
     * Determins whether or not the given <code>QuerySelect</code> is part of
     * the <code>fromClause</code> of another <code>QuerySelect</code>.
     * 
     * @param querySelect
     * @return <code>true</code> if the <code>querySelect</code> is nested
     *         query of another superior <code>QuerySelect</code>
     */
    private static boolean isQuerySelectNestedQuery(QuerySelect querySelect) {
        boolean isNestedQuery = false;

        if (querySelect != null) {
            EObject eContainer = querySelect.eContainer();

            // to still be part of a FROM-clause the eContainer can be any
            // table reference including a QueryCombined
            // if we reach another QuerySelect by the way of TableReferences, we
            // must be part of that Select's FROM-clause
            while (eContainer instanceof TableReference) {
                if (eContainer instanceof QuerySelect) {
                    isNestedQuery = true;
                    break;
                }
                eContainer = eContainer.eContainer();
            }
        }

        return isNestedQuery;
    }

    /**
     * Returns <code>true</code> if the given <code>eObject</code> is a
     * subclass or instance of the given <code>Class typeFilter</code> or the
     * given <code>Class typeFilter</code> is <code>null</code>.
     * 
     * @param eObject the <code>EObject</code> to test for assignability to
     *        the given <code>typeFilter Class</code>
     * @param typeFilter the <code>Class</code> tryed to assign from the given
     *        <code>eObject</code>
     * @return <code>true</code> if the given <code>eObject</code> is a
     *         subclass or instance of the given <code>Class typeFilter</code>
     *         or the given <code>Class typeFilter</code> is <code>null</code>
     */
    private static boolean isSubtypeOf(EObject eObject, Class typeFilter) {
        return typeFilter == null
        // || typeFilter.equals(Object.class)
                // || typeFilter.equals(eObject.getClass())
                || typeFilter.isAssignableFrom(eObject.getClass());
    }

    /**
     * Returns <code>true</code> if the given <code>eObject</code> is a
     * subclass or instance of one of the given <code>typeFilter Class</code>es
     * or if the given <code>typeFilter</code> array is <code>null</code> or
     * empty.
     * 
     * @param eObject the <code>EObject</code> to test for assignability to
     *        one of the given <code>typeFilter Class</code>es
     * @param typeFilter the <code>Class</code>es contained will be tested
     *        for assignability from the given <code>eObject</code>
     * @return <code>true</code> if the given <code>eObject</code> is a
     *         subclass or instance of one of the <code>Class</code>es in the
     *         given <code>typeFilter</code> array or the given
     *         <code>Class typeFilter</code> is <code>null</code> or empty
     */
    private static boolean isSubtypeOf(EObject eObject, Class[] typeFilter) {
        boolean isSubtype = false;

        // if we have no typeFilter we wanna return true, no restriction
        if (typeFilter == null || typeFilter.length == 0) {
            isSubtype = true;
        }
        else {
            for (int i = 0; i < typeFilter.length; i++) {
                if (typeFilter[i].isAssignableFrom(eObject.getClass())) {
                    isSubtype |= true;
                    break;
                }
            }
        }
        return isSubtype;
    }

    /**
     * Returns <code>true</code> if the given <code>tableExpr</code>'s
     * <code>name</code> is not unique within the <code>fromClause</code> of
     * its containing <code>QuerySelect</code> and the other
     * <code>TableExpression</code> s in the <code>fromClause</code> as well
     * as the given <code>TableExpression</code> have no
     * <code>TableCorrelation</code> to distinguish between them. That is
     * important for column references to that table, which will have to get
     * qualified with the table's <code>Schema</code> name.
     * 
     * @param tableExpr
     * @return <code>true</code> if the given <code>tableExpr</code>'s
     *         <code>name</code> is not unique within the
     *         <code>fromClause</code> of its containing
     *         <code>QuerySelect</code> and the other
     *         <code>TableExpression</code> s in the <code>fromClause</code>
     *         as well as the given <code>TableExpression</code> have no
     *         <code>TableCorrelation</code> to distinguish between them
     */
    public static boolean isTableNameAmbiguous(TableExpression tableExpr) {
        boolean isAmbiguous = false;

        // if we have have a table alias, we assume there can be no ambiguity
        if (tableExpr.getTableCorrelation() != null) {
            return false;
        }

        QuerySelect select = getQuerySelectForTableReference(tableExpr);

        if (select != null) {
            List tableExprList = getTableExpressionsInQuerySelect(select);
            for (Iterator it = tableExprList.iterator(); it.hasNext();) {
                TableExpression otherTable = (TableExpression) it.next();

                // only if table has no alias we might have ambiguity
                if (otherTable != tableExpr && otherTable.getTableCorrelation() == null && tableExpr.getName() != null
                        && equalSQLIdentifiers(tableExpr.getName(), otherTable.getName())) {
                    isAmbiguous = true;
                    break;
                }
            }
        }

        return isAmbiguous;
    }

    /**
     * Checks to see if the given string is same as the template for any of the
     * DML statements
     * 
     * @param sql the string to check if it is the template statement
     * @return true of if sql is same as a tmplate , false otherwise
     */
    public static boolean isTemplateSQL(String sql) {
        boolean isTemplate = false;
        int compares = -1;
        if (templates.isEmpty()) {
            templates.add((StatementHelper.createQueryStatement(0, "")).getSQL());
            templates.add((StatementHelper.createQueryStatement(1, "")).getSQL());
            templates.add((StatementHelper.createQueryStatement(2, "")).getSQL());
            templates.add((StatementHelper.createQueryStatement(3, "")).getSQL());
            templates.add((StatementHelper.createQueryStatement(4, "")).getSQL());
            templates.add((StatementHelper.createQueryStatement(5, "")).getSQL());
            // Merge (type 6) is not supported here
        }
        Iterator itr = templates.iterator();
        String template;
        while (compares != 0 && itr.hasNext()) {
            template = (String) itr.next();
            compares = compareSQL(template, sql);
        }
        if (compares == 0) {
            isTemplate = true;
        }
        return isTemplate;
    }

    public static void logDebug(String debugMsg) {
        // if (DEBUG) {
        SQLQueryLogger.getLogger().writeInfo(debugMsg);
        // }

    }

    public static void logError(String errorMsg) {
        // if (DEBUG) {
        SQLQueryLogger.getLogger().writeLog(errorMsg);
        // }

    }

    /**
     * Returns <code>true</code> if the given <code>tableInDB</code> does
     * not have to be qualified with its <code>Schema</code> name.
     * <p>
     * <b>Note:</b> Assumptions:
     * <ul>
     * <li> given <code>tableInDB</code> is not <code>null</code>
     * <li> given <code>tableInDB</code> has (indirect) reference to its
     * <code>QueryStatement</code>
     * <li> that <code>QueryStatement</code> has reference to its
     * <code>SQLQuerySourceInfo</code> and that way indirectly a reference to
     * a <code>SQLQuerySourceFormat</code> ({@link com.ibm.db.models.sql.query.util.SQLQuerySourceFormat#getOmitSchema()})
     * </ul>
     * 
     * @param tableInDB
     * @return <code>true</code> if table name does not have to be qualified
     */
    public static boolean omitSchema(TableInDatabase tableInDB) {
        // default: don't omit any schema!
        boolean omitSchema = false;

        if (tableInDB.getDatabaseTable() != null) {
            SQLQuerySourceFormat sourceFormat = null;

            QueryStatement queryStmt = getQueryStatementForTableReference(tableInDB);

            // don't know why this could happen, but it did!
            // get the sourceFormat from the tableInDB, sourceFormat might be
            // forgotten to be set by GUI model construction thats why we tryed
            // to get the statement as the highest level that might have been
            // through the parser, parser always sets sourceFormat if it gets it
            // provided
            if (queryStmt != null && queryStmt.getSourceInfo() != null && queryStmt.getSourceInfo().getSqlFormat() != null) {
                sourceFormat = queryStmt.getSourceInfo().getSqlFormat();
            }
            else {
                if (tableInDB.getSourceInfo() != null && tableInDB.getSourceInfo().getSqlFormat() != null) {
                    sourceFormat = tableInDB.getSourceInfo().getSqlFormat();
                }
            }

            // get the default schema name to be ommited
            String omitSchemaName = null;
            if (sourceFormat != null) {
                omitSchemaName = sourceFormat.getOmitSchema();
            }

            Schema schema = tableInDB.getDatabaseTable().getSchema();
            // String tableName = tableInDB.getDatabaseTable().getName();

            if (schema != null) {
                String schemaName = schema.getName();

                if (schemaName != null && schemaName.length() > 0 
                 && equalSQLIdentifiers(schemaName, omitSchemaName)) {
                    omitSchema = true;
                }
            }

        }

        return omitSchema;
    }

    /**
     * Removes the references of a given ValueExpressionColumn.
     * 
     * @param colExpr the ValueExpressionColumn for which all the references
     *        needs to be removed
     */
    private static void removeColumnReferences(ValueExpressionColumn colExpr) {
        Set colRefList = getAllDirectReferences(colExpr);
        for (Iterator colRefIt = colRefList.iterator(); colRefIt.hasNext();) {
            EObject reference = (EObject) colRefIt.next();
            
            if (reference instanceof GroupingSpecification) {
                removeGroupingSpecification((GroupingSpecification) reference);
            }
            else if (reference instanceof OrderBySpecification) {
                removeOrderBySpecification((OrderBySpecification) reference);
            }
            else if (reference instanceof Predicate) {
                removePredicate((Predicate) reference);
            }
            else if (reference instanceof ResultColumn) {
                removeResultColumn((ResultColumn) reference);
            }
            else if (reference instanceof QueryValueExpression) {
                removeValueExpression((QueryValueExpression) reference);
            }
        }
    }

    /**
     * Walks through the given SQL and strips out the comments that are
     * identified by two dashes in line "--" and that are not part of a
     * delimited character string (single quotes), or SQL delimited object name
     * (double quotes). <br>
     * <b>NOTE: </b> The given SQL statement string must contain the line-break
     * characters to delimit the "--" comments, otherwise all of the
     * SQL-Statement that comes after the first occurence of a "--" comment be
     * treated as part of the comment and rather be ignored, moreover the
     * resulting statement is likely to fail! <br>
     * <p>
     * SELECT EMP_NUM FROM DEPARTEMENT -- pre-accumulated number of employees
     * <br>
     * WHERE LOCATION = 'C-Tower--01' OR LOCATION = 'C-Tower-02'
     * <p>
     * In this statement the first occurence of "--" would indicate a comment
     * that would be stripped of and the second occurence would be retained, as
     * it is part of a String delimited by the enclosing single quotes.
     * 
     * 
     * @param statement A SQL statement
     * @param delimitedIdentifierQt the quote char used to prefix and suffix
     *        non-standard identifiers or identifiers that are case sensitive
     * @return The a SQL statement without "--" comments.
     */
    public static String removeCommentsInSQL(String statement, char delimitedIdentifierQt) {
        final int NO_QUOTE = 0;
        final int SINGLE_QUOTE = 1;
        final int DOUBLE_QUOTE = 2;
        final int INSIDE_COMMENT = 3;

        char lookAheadChar;
        char lastChar = ' ';
        int delimiterState = NO_QUOTE;
        StringBuffer nonCommentStmt = new StringBuffer();

        StringCharacterIterator iter = new StringCharacterIterator(statement);
        // Iterate through the string to find a comments.
        // Ignore comments that are enclosed in quotes:
        // delimited SQL object names (double quotes) or character
        // strings (single quotes)
        for (char c = iter.first(); c != CharacterIterator.DONE; c = iter.next()) {
            switch (delimiterState) {
                case INSIDE_COMMENT: // --comment end with line
                    if ((c == '\r') || (c == '\n')) // carriage return
                    {
                        delimiterState = NO_QUOTE;
                    }
                    break;
                case NO_QUOTE:
                    if (c == '\'') {
                        delimiterState = SINGLE_QUOTE;
                    }
                    else if (c == delimitedIdentifierQt) {
                        delimiterState = DOUBLE_QUOTE;
                    }
                    else if (c == '-') {
                        lookAheadChar = iter.next();
                        if (lookAheadChar == '-') {
                            delimiterState = INSIDE_COMMENT;
                        }
                        else {
                            nonCommentStmt.append(c);
                            lastChar = c;
                            c = lookAheadChar;
                        }
                    }
                    break;
                case SINGLE_QUOTE:
                    if (c == '\'') {
                        lookAheadChar = iter.next();
                        if (lookAheadChar != '\'') {
                            delimiterState = NO_QUOTE;
                            nonCommentStmt.append(c);
                            lastChar = c;
                            c = lookAheadChar;
                        }
                    }
                    break;
                case DOUBLE_QUOTE:
                    if (c == delimitedIdentifierQt) {
                        lookAheadChar = iter.next();
                        if (lookAheadChar != delimitedIdentifierQt) {
                            delimiterState = NO_QUOTE;
                            nonCommentStmt.append(c);
                            lastChar = c;
                            c = lookAheadChar;
                        }
                    }
                    break;
            }

            // if not inside a comment, append the current char to the resulting
            // StringBuffer
            if (delimiterState != INSIDE_COMMENT) {

                // filter out the line terminators
                if ((c == '\n' || c == '\r') && delimiterState == NO_QUOTE) {
                    if (lastChar != ' ') {
                        nonCommentStmt.append(' ');
                        lastChar = ' ';
                    }
                }
                else {
                    // append characters, not CharacterIterator limiter
                    if (c != CharacterIterator.DONE) {
                        // only append blanks in quotes or if the previous char
                        // was no blank
                        if (delimiterState != NO_QUOTE || lastChar != ' ' || c != ' ') {
                            nonCommentStmt.append(c);
                            lastChar = c;
                        }
                    }
                }
            }

        }

        return nonCommentStmt.toString().trim();

    }

    /**
     * Removes the given GroupingSpecification object from the group by clause
     * of the query expression that contains it.
     * 
     * @param groupingSpec the GroupingSpecification that needs to be removed
     */
    private static void removeGroupingSpecification(GroupingSpecification groupingSpec) {
        /* Get the container of the grouping spec. */
        Object container = groupingSpec.eContainer();

        /*
         * Search for the QuerySelect (query expression) object that contains
         * the grouping spec. The grouping spec may be nested inside another
         * grouping spec. Keep track of the grouping spec that is attached to
         * the query expression.
         */
        GroupingSpecification groupingSpecToRemove = groupingSpec;
        boolean foundSelect = false;
        QuerySelect qSelect = null;
        while (!foundSelect && container != null) {
            if (container instanceof QuerySelect) {
                qSelect = (QuerySelect) container;
                foundSelect = true;
            }
            else if (container instanceof SuperGroup) {
                groupingSpecToRemove = (SuperGroup) container;
            }
            else if (container instanceof GroupingSets) {
                groupingSpecToRemove = (GroupingSets) container;
            }
            container = ((EObject) container).eContainer();
        }

        /*
         * If we found the containing query expression, remove from the 
         * grouping clause of the query expression the top-most grouping 
         * spec that contains the target grouping spec.
         */
        if (qSelect != null) {
            qSelect.getGroupByClause().remove(groupingSpecToRemove);
        }
    }

    /**
     * Removes the given sort specification from the sort specification list
     * (ORDER BY clause) that contains it.
     * 
     * @param sortSpec the sort specification that needs to be removed
     */
    private static void removeOrderBySpecification(OrderBySpecification sortSpec) {
        QuerySelectStatement stmt = sortSpec.getSelectStatement();
        stmt.getOrderByClause().remove(sortSpec);
    }

    /**
     * Removes given Predicate object from the object that contains it.
     * 
     * @param pred the Predicate to remove
     */
    private static void removePredicate(Predicate pred) {
        QuerySearchCondition searchCon = null;
        Object parent = null;
        boolean isHaving = false;
        if (pred.getCombinedRight() == null && pred.getCombinedLeft() == null) {
            parent = pred.eContainer();
            if (parent instanceof QuerySelect) {
                QuerySelect query = (QuerySelect) parent;
                if (query.getHavingClause() == pred) {
                    isHaving = true;
                }
            }
        }
        else {
            if (pred.getCombinedLeft() != null) {
                searchCon = pred.getCombinedLeft();
            }
            else if (pred.getCombinedRight() != null){
                searchCon = pred.getCombinedRight();
            }
            else if (pred.getNest() != null) {
                searchCon = pred.getNest();
            }
            
            while (searchCon.getCombinedLeft() != null) {
                searchCon = searchCon.getCombinedLeft();
            }
            parent = searchCon.eContainer();
            if (parent instanceof QuerySelect) {
                QuerySelect query = (QuerySelect) parent;
                if (query.getHavingClause() == searchCon) {
                    isHaving = true;
                }
            }
        }

        // if (pred.getQuerySelectWhere() != null) {
        // QuerySelect query = pred.getQuerySelectWhere();
        // query.setWhereClause(null) ;
        // }
        if (searchCon == null) {
            // do nothing
        }
        else if (pred.getCombinedRight() != null && pred.getCombinedRight().getCombinedLeft() == null) {
            // Case : any right most (last) node then searchCon points to left
            // (previous) sibling (Combine/pred)
            searchCon = pred.getCombinedRight().getLeftCondition();
            if (searchCon != null) {
                searchCon.setCombinedLeft(null);
            }
        }
        else if (pred.getCombinedLeft() != null) {
            // Case : any left most (first) node then if more than 2 nodes then
            // parent combined replaces with right sibling
            // otherwise searchCon points to right sibling (pred)
            if (pred.getCombinedLeft().getCombinedLeft() != null) {
                pred.getCombinedLeft().getCombinedLeft().setLeftCondition(pred.getCombinedLeft().getRightCondition());
            }
            else {
                searchCon = pred.getCombinedLeft().getRightCondition();
            }
        }
        else if (pred.getCombinedRight() != null && pred.getCombinedRight().getCombinedLeft() != null) {
            // Case : any middle node then assign right (next) pred to parent
            // and assignt parent's
            // left combine to parent's parent's combine left
            // if second from right then searchCon points to parent

            SearchConditionCombined currentGroup = pred.getCombinedRight();
            pred.getCombinedRight().setRightCondition(pred.getCombinedRight().getCombinedLeft().getRightCondition());
            currentGroup.setCombinedLeft(currentGroup.getCombinedLeft().getCombinedLeft());
            if (currentGroup.getCombinedLeft() == null) {
                searchCon = currentGroup;
            }
        }

        if (parent != null) {
            updateSearchConditionParent(searchCon, parent, isHaving);
        }
    }

    /**
     * Removes the references of the given ResultColumn from the SelectClause.
     * 
     * @param resCol the ResultColumn that needs to be removed
     */
    private static void removeResultColumn(ResultColumn resCol) {
        /* Get the query expression that contains the result column, if any. */
        QuerySelect query = resCol.getQuerySelect();

        /*
         * Get the OrderByResultColumn that references the ResultColumn, if any,
         * and remove the ResultColumn from it. The OrderByResultColumn does not
         * contain (own) the ResultColumn, so we can't simply use eContainer to
         * get the referring object. Instead we need to get the order by list
         * and scan it looking for the target ResultColumn object. We assume
         * that only one OrderByResultColumn object refers to the ResultColumn.
         */
        List ordByColList = resCol.getOrderByResultCol();
        if (ordByColList != null && !ordByColList.isEmpty()) {
            Iterator orderByColListIter = ordByColList.iterator();
            boolean deleted = false;
            while (orderByColListIter.hasNext() && !deleted) {
                Object col = orderByColListIter.next();
                if (col instanceof OrderByResultColumn) {
                    OrderByResultColumn rstCol = (OrderByResultColumn) col;
                    if (rstCol.getResultCol() == resCol) {
                        orderByColListIter.remove();
                        deleted = true;
                    }
                }
            }
        }

        /*
         * Now remove the ResultColumn object from the query expression SELECT
         * clause.
         */
        if (query != null) {
            query.getSelectClause().remove(resCol);
        }
    }

    /**
     * Removes the given <code>TableExpression</code> from the given <code>SQLQueryObject</code>.
     * The SQLQueryObject should be either a <code>QueryStatement</code> or a <code>QuerySelect</code>.
     * All references to columns of the TableExpression are removed as well.
     * 
     * @param tableExpr the table expression to remove
     * @param queryObj the query object from which the table expression should
     * be removed
     */
    public static void removeTableExpressionFromQueryStatement(TableExpression tableExpr, SQLQueryObject queryObj) {
        
        /* Get a list (set) of all the objects that are directly referenced by the target
         * table expression. */
        Set directlyReferencedObjects = getAllDirectReferences(tableExpr);
        
        /* Iterate through the list of objects that reference the target table.  Remove the 
         * objects that are columns from the query. */
        for (Iterator dirRefIter = directlyReferencedObjects.iterator(); dirRefIter.hasNext();) {
            EObject reference = (EObject) dirRefIter.next();
            
            /* When the referenced object is a column, remove references to that column 
             * from any other object that references it. 
             */
            if (reference instanceof ValueExpressionColumn) {
                ValueExpressionColumn valExprCol = (ValueExpressionColumn) reference;
                removeColumnReferences(valExprCol);
            }
        }
        
        /* Remove the table expression from the query expression or statement that 
         * contains it. */
        if (queryObj instanceof QuerySelect) {
            QuerySelect querySelect = (QuerySelect) queryObj;
            List tableExprList = querySelect.getFromClause();
            if (tableExprList != null && !tableExprList.isEmpty()) {
                /* Remove any joins containing the target table. */
                JoinHelper.removeJoinsForTable(tableExprList, tableExpr);
                /* Remove the table. */
                tableExprList.remove(tableExpr);
            }
        }
        else if (queryObj instanceof QuerySelectStatement) {
            QuerySelectStatement stmt = (QuerySelectStatement) queryObj;
            QueryExpressionRoot queryExpr = stmt.getQueryExpr();
            if (queryExpr != null) {
                QueryExpressionBody query = queryExpr.getQuery();
                if (query != null) {
                    if (query instanceof QuerySelect) {
                        QuerySelect querySelect = (QuerySelect) query;
                        List tableExprList = querySelect.getFromClause();
                        if (tableExprList != null && !tableExprList.isEmpty()) {
                            /* Remove any joins containing the target table. */
                            JoinHelper.removeJoinsForTable(tableExprList, tableExpr);
                            /* Remove the table. */
                            tableExprList.remove(tableExpr);
                        }
                    }
                }
            }
        }
        else if (queryObj instanceof QueryInsertStatement) {
            QueryInsertStatement stmt = (QueryInsertStatement) queryObj;
            stmt.getTargetColumnList().clear();
            stmt.getSourceValuesRowList().clear();
            stmt.setTargetTable(null);
        }
        else if (queryObj instanceof QueryUpdateStatement) {
            QueryUpdateStatement stmt = (QueryUpdateStatement) queryObj;
            stmt.setTargetTable(null);
        }
        else if (queryObj instanceof QueryDeleteStatement) {
            QueryDeleteStatement stmt = (QueryDeleteStatement) queryObj;
            stmt.setTargetTable(null);
        }
        // QueryMergeStatement is not supported here
    }

    /**
     * Removes the given ValueExpression object from the object that
     * contains it.  If the containing object would then be invalid, remove
     * the containing object as well.
     * 
     * @param valExpr the object to remove
     */
    public static void removeValueExpression( QueryValueExpression valExpr ) {
        /* Get the container for this object. */
        Object container = valExpr.eContainer();
        if (container instanceof ValueExpressionNested) {
            ValueExpressionNested nest = (ValueExpressionNested) container;
            nest.setNestedValueExpr(null);
            removeValueExpression(nest);
        }
        else if (container instanceof ValueExpressionCombined) {
            ValueExpressionCombined combined = (ValueExpressionCombined) container;
            combined.setLeftValueExpr(null);
            combined.setRightValueExpr(null);
            removeValueExpression(combined);
        }
        else if (container instanceof GroupingExpression) {
            GroupingExpression groupingExpr = (GroupingExpression) container;
            groupingExpr.setValueExpr(null);
            removeGroupingSpecification(groupingExpr);
        }
        else if (container instanceof OrderByValueExpression) {
            OrderByValueExpression orderByVal = (OrderByValueExpression) container;
            orderByVal.setValueExpr(null);
            removeOrderBySpecification(orderByVal);
        }
        else if (container instanceof Predicate) {
            Predicate pred = (Predicate) container;
            // should determine the subclass of Predicate and remove the value expr from it
            removePredicate(pred);
        }
        else if (container instanceof ResultColumn) {
            ResultColumn resultCol = (ResultColumn) container;
            resultCol.setValueExpr(null);
            removeResultColumn(resultCol);
        }
    }
    
    /**
     * @see #resolveOrderByColumns(QuerySelect, List)
     */
    public static Set resolveOrderByColumns(QueryExpressionBody queryExpr, List orderByList) {
        Set removedColumns = new HashSet();

        if (queryExpr instanceof QuerySelect) {
            QuerySelect select = (QuerySelect) queryExpr;
            removedColumns.addAll(resolveOrderByColumns(select, orderByList));
        }
        else if (queryExpr instanceof QueryCombined) {
            QueryCombined combined = (QueryCombined) queryExpr;
            removedColumns.addAll(resolveOrderByColumns(combined.getLeftQuery(), orderByList));
            removedColumns.addAll(resolveOrderByColumns(combined.getRightQuery(), orderByList));
        }
        else if (queryExpr instanceof QueryNested) {
            QueryNested qryNested = (QueryNested) queryExpr;
            QueryExpressionBody nestedQuery = qryNested.getNestedQuery();
            removedColumns.addAll(resolveOrderByColumns(nestedQuery, orderByList));
        }
        else if (queryExpr instanceof QueryValues) {
            // nothing to resolve! no column references in VALUES clause
            // possible
        }
        else {
            throw new UnsupportedOperationException("resolveOrderByColumns(QueryExpressionBody, List) not implemented for " + queryExpr.getClass().getName()
                    + " in " + StatementHelper.class.getName());
        }

        return removedColumns;
    }

    /**
     * Every given <code>OrderBySpecification</code>s contained in the given
     * <code>orderByList</code>, that is of type
     * <code>OrderByValueExpression</code> and its owned
     * <code>ValueExpression</code> is of type
     * <code>ValueExpressionColumn</code>, will be removed from the given
     * <code>QuerySelect</code> and substituted by a new
     * <code>OrderByResultColumn</code> that will then be given the reference
     * to the <code>ValueExpressionColumn</code> formerly associated to the
     * removed <code>OrderByValueExpression</code>, if the given
     * <code>QuerySelect</code> has a <code>ResultColumn</code> that refers
     * by its <code>name</code> to the same <code>ValueExpressionColumn</code>.
     * In other words, the type of a <code>OrderByValueExpression</code>,
     * that references a <code>ValueExpressionColumn</code> also referred to
     * by one <code>ResultColumn</code> of the given <code>select</code>,
     * will be changed to <code>OrderByResultColumn</code>.
     * <p>
     * <b>Note:</b> Returns the Set of <code>ValueExpressionColumn</code>s
     * that have been removed from their <code>OrderByValueExpression</code>.
     * </p>
     * 
     * @param select
     * @param orderBySpecList List of <code>OrderBySpecification</code>s
     *        referring to the given <code>QuerySelect</code>
     * @return Set of <code>ValueExpressionColumn</code>s that have been
     *         removed from their <code>OrderByValueExpression</code>
     */
    public static Set resolveOrderByColumns(QuerySelect select, List orderBySpecList) {
        // for the ORDER BY specifications that currently are of type
        // OrderByValueExpression, check if the ValueExpression is a ColumnExpr.
        // if so resolve it with the ResultColumns of the given select and
        // change
        // the type of the OrderBySpecification from OrderByValueExpression to
        // OrderByResultColumn
        List newOrderBySpecList = new ArrayList();

        // collect the remove ValueExpressionColumns for further check or processing
        Set removedColumnExprs = new HashSet();

        for (Iterator orderIter = orderBySpecList.iterator(); orderIter.hasNext();) {
            // Get the next order by spec from the old order by list and also remove it from the list.
            // If it's an OrderByValueExpression whose value expression matches one of the
            // Result Column value expressions, then it will be transformed into a 
            // OrderByResultColumn and added to the new order by list.  Otherwise it will be
            // added to the new order by list unchanged.
            OrderBySpecification oldOrderBySpec = (OrderBySpecification) orderIter.next();
            orderIter.remove();
            OrderBySpecification newOrderBySpec = oldOrderBySpec;
            if (oldOrderBySpec instanceof OrderByValueExpression) {
                OrderByValueExpression orderByVE = (OrderByValueExpression) oldOrderBySpec;

                if (orderByVE.getValueExpr() instanceof ValueExpressionColumn) {
                    ValueExpressionColumn columnExpr = (ValueExpressionColumn) orderByVE.getValueExpr();

                    // find ResultColumns in select's resultSpecList
                    ResultColumn resultCol = null;
                    // if current order by column is just a name we want to look
                    // for result column's alias or result column's columnRef's
                    // name
                    // otherwise, if the order by column is qualified we must
                    // not
                    // look for a result column with that name but for a result
                    // column
                    // with a reference to a corresponding column expression
                    if (columnExpr.getTableExpr() == null) {
                        resultCol = findResultColumnForColumnNameOrAlias(select, columnExpr.getName());
                    }
                    else {
                        resultCol = findResultColumnForColumnExpression(select, columnExpr);
                    }

                    if (resultCol != null) {
                        OrderByResultColumn newOrderByRC = SQLQueryModelFactory.eINSTANCE.createOrderByResultColumn();
                        newOrderByRC.setResultCol(resultCol);
                        newOrderByRC.setOrderingSpecOption(orderByVE.getOrderingSpecOption());
                        // descending is replaced by the orderingspecOption.  User should use the
                        // orderingspecOption instead of descending to determine orderby type. 
                        newOrderByRC.setDescending(orderByVE.isDescending());
                        newOrderByRC.setNullOrderingOption(orderByVE.getNullOrderingOption());

                        
                        // remove the columnExpr from its table if it's a
                        // duplicate representative of a column existing,
                        // because
                        // the equivalent columnExpr will already exist because
                        // of the ResultColumn that already exists
                        orderByVE.setValueExpr(null);
                        // keep reference to it in removedColumnExprs
                        removedColumnExprs.add(columnExpr);

                        TableHelper.removeColumnExpressionFromTableIfNotReferenced(columnExpr);
                        
                        newOrderBySpec = newOrderByRC;
                    }
                }
            }
            
            // Add the transformed order by spec (or the original one, if no transform needed)
            // to the new list.
            newOrderBySpecList.add(newOrderBySpec);
        }

        // Copy the new order by spec list contents into the old
        orderBySpecList.addAll(newOrderBySpecList);

        return removedColumnExprs;
    }

    /**
     * Resolves columnExpressions and tableReferences in given
     * <code>querySelect</code>'s <code>QueryResultSpecification</code> 
     * <code>ResultColumn</code>
     * or <code>ResultTableAllColumns</code> with the columnExpressions and
     * tableExpressions in the given <code>querySelect</code>'s
     * <code>fromClause</code>.
     * 
     * @param querySelect
     */
    public static void resolveQueryResultSpecification(QuerySelect querySelect) {
        List tableRefList = querySelect.getFromClause();

        // get list of query result columns or table.*s
        List queryResultSpecList = querySelect.getSelectClause();

        // resolve column table references
        Set columnExprSet = TableHelper.findColumnReferencesInQueryResultSpecificationList(queryResultSpecList);
        TableHelper.resolveColumnTableReferences(columnExprSet, tableRefList);

        // resolve TABLE.* all column references
        // get all table expressions in given list of tableRefs
        List tableExprList = TableHelper.getTableExpressionsInTableReferenceList(tableRefList);
        List resultTableList = getResultTableAllColumnsInQueryResultSpecificationList(queryResultSpecList);

        TableHelper.resolveResultTableReferences(resultTableList, tableExprList);

    }

    /**
     * Resolves tableReferences in given <code>querySelect</code>'s
     * <code>QueryResultSpecification</code>s of type
     * <code>ResultTableAllColumns</code> with the tableExpressions in the
     * given <code>querySelect</code>'s <code>fromClause</code>.
     * 
     * @param querySelect
     */
    public static void resolveResultTableAllColumns(QuerySelect querySelect) {
        List tableRefList = querySelect.getFromClause();

        // get list of query result columns or table.*s
        List queryResultSpecList = querySelect.getSelectClause();

        // resolve TABLE.* all column references
        // get all table expressions in given list of tableRefs
        List tableExprList = TableHelper.getTableExpressionsInTableReferenceList(tableRefList);
        List resultTableList = getResultTableAllColumnsInQueryResultSpecificationList(queryResultSpecList);

        TableHelper.resolveResultTableReferences(resultTableList, tableExprList);
    }

    /**
     * Substitutes the given <code>TableReference</code> with the
     * <code>WithTableSpecification</code> that is refered by the given
     * <code>potentialTableWithRef</code>'s <code>name</code>, if a
     * <code>TableWithSpecification</code> is found in the
     * <code>QueryExpressionRoot</code> that contains the given
     * <code>TableReference</code>.
     * 
     * @param potentialTableWithRef
     * @return the substitute <code>TableWithSpecification</code> found
     */
    public static WithTableReference resolveWithTableSpecificationReference(TableExpression potentialWithTableRef) {
        WithTableReference withTableRef = null;

        if (potentialWithTableRef != null) {

            String withTableName = potentialWithTableRef.getName();

            WithTableSpecification withTableSpec = null;

            QuerySelect querySelect = getQuerySelectForTableReference(potentialWithTableRef);

            // solves the resolving problem for WithTableReferences in selects
            // that are not a nested query, like in PredicateExists
            // like in:
            // "with temp1 (col1) as (values 256),
            // temp2 (col2) as (values 'string')
            // select t1.col1 from temp1 t1
            // where exists (
            // select * from temp2 t2 where t1.col1 = t2.col2);"
            // where "t1.col1" will be attempted to resolve in the context of
            // the exist-select, but must be resolved in the context of the
            // sourrounding select and we need the WithTable.
            withTableSpec = getWithTableSpecificationForNameRecursively(withTableName, querySelect);

            if (withTableSpec != null) {
                withTableRef = createWithTableReferenceForWithTable(withTableSpec);
                substituteTableReference(potentialWithTableRef, withTableRef);
            }

        }
        return withTableRef;
    }

    /**
     * Sets the having clause in the given sql statement.
     * 
     * @param newSearchCon new QuerySearchCondition that needs to be set.
     * @param statement the SQL Statement on which new having clause needs to
     *        set.
     */
    public static void setHavingClauseForStatement(QuerySearchCondition newSearchCon, QueryStatement statement) {
        if (statement instanceof QuerySelectStatement) {
            QueryExpressionRoot qRoot = ((QuerySelectStatement) statement).getQueryExpr();
            if (qRoot != null && qRoot.getQuery() instanceof QuerySelect) {
                QuerySelect qSelect = (QuerySelect) qRoot.getQuery();
                qSelect.setHavingClause(newSearchCon);
            }
        }
    }

    /**
     * Sets the having clause in the given SQLQueryObject.
     * 
     * @param newSearchCon new QuerySearchCondition that needs to be set.
     * @param statement the SQLQueryObject on which new having clause needs to
     *        set.
     */
    public static void setHavingClauseForStatement(QuerySearchCondition newSearchCon, SQLQueryObject statement) {
        if (statement instanceof QueryStatement) {
            setHavingClauseForStatement(newSearchCon, (QueryStatement) statement);
        }
        else if (statement instanceof QuerySelect) {
            QuerySelect qSelect = (QuerySelect) statement;
            qSelect.setHavingClause(newSearchCon);
        }

    }

    /**
     * Sets the where clause in the given sql statement.
     * 
     * @param newSearchCon new QuerySearchCondition that needs to set.
     * @param statement the SQL Statement on which new where clause needs to
     *        set.
     */
    public static void setWhereClauseForStatement(QuerySearchCondition newSearchCon, QueryStatement statement) {
        if (statement instanceof QuerySelectStatement) {
            QueryExpressionRoot qRoot = ((QuerySelectStatement) statement).getQueryExpr();
            if (qRoot != null && qRoot.getQuery() instanceof QuerySelect) {
                QuerySelect qSelect = (QuerySelect) qRoot.getQuery();
                qSelect.setWhereClause(newSearchCon);
            }
        }
        else if (statement instanceof QueryUpdateStatement) {
            ((QueryUpdateStatement) statement).setWhereClause(newSearchCon);
        }
        else if (statement instanceof QueryDeleteStatement) {
            ((QueryDeleteStatement) statement).setWhereClause(newSearchCon);
        }
        // QueryMergeStatement is not supported here
    }

    /**
     * Sets the where clause in the given sqlQueryObject.
     * 
     * @param newSearchCon new QuerySearchCondition that needs to set.
     * @param statement the SQLObject on which new where clause needs to set.
     */
    public static void setWhereClauseForStatement(QuerySearchCondition newSearchCon, SQLQueryObject statement) {
        if (statement instanceof QueryStatement) {
            setWhereClauseForStatement(newSearchCon, (QueryStatement) statement);
        }
        else if (statement instanceof QuerySelect) {
            QuerySelect qSelect = (QuerySelect) statement;
            qSelect.setWhereClause(newSearchCon);
        }
    }

    /**
     * Strips of the optional whitespace from the given <code>sql</code> and
     * replaces all new lines with single spaces.
     * <p>
     * <b>Note:</b> this method does not strip comments from the given
     * <code>sql</code> and consequently makes any given SQL that contains
     * comments invalid, unless the comment is on the last line and no valid SQL
     * succeeds the comment.
     * <p>
     * <b>Note performance: </b> this method invokes
     * {@link String#replaceAll(java.lang.String, java.lang.String)} 11 times
     * after walking through the SQL source two times to remove all comments and
     * transforming the given <code>sql</code> into upper case format
     * (respecting delimited identifiers).
     * 
     * @param sql the SQL source string
     * @param delimitedIdentifierQt the String that is used to pre- and suffix
     *        delimited identifiers, commonly it is the double-quote char '"'
     * @return the given <code>sql</code> in upper case, without comments and
     *         with minimal whitespace
     */
    public static String stripWhiteSpace(String sql, char delimitedIdentifierQt) {
        String stmtTerm = ";"; //$NON-NLS-1$

        if (sql != null) {
            sql = toSQLFormatUpperCase(sql, delimitedIdentifierQt);
            sql = sql.replaceAll("\n", " "); // line breaks to space
                                                // //$NON-NLS-1$ //$NON-NLS-2$
            // sql = sql.replaceAll(" AS ", " "); // AS is optional, exept in
            // WITH table spec //$NON-NLS-1$ //$NON-NLS-2$
            sql = sql.replaceAll("\\s*,\\s*", ","); // eliminate white space
                                                    // before and after comma
                                                    // //$NON-NLS-1$
                                                    // //$NON-NLS-2$
            sql = sql.replaceAll("\\s*\\(\\s*", "("); // delete space before
                                                        // and after left paren
                                                        // //$NON-NLS-1$
                                                        // //$NON-NLS-2$
            sql = sql.replaceAll("\\s*\\)\\s*", ")"); // one space after right
                                                        // paren, no more space
                                                        // before or after
                                                        // //$NON-NLS-1$
                                                        // //$NON-NLS-2$
            sql = sql.replaceAll("\\s+", " "); // multiple space into single
                                                // space //$NON-NLS-1$
                                                // //$NON-NLS-2$
            // sql = sql.replaceAll("\\s+\\(\\+\\*-/\\)\\s+", "\\1");
            sql = sql.replaceAll("\\s*=\\s*", "="); // one space before and
                                                    // after = //$NON-NLS-1$
                                                    // //$NON-NLS-2$
            sql = sql.replaceAll("\\s*-\\s*", "-"); // one space before and
                                                    // after - //$NON-NLS-1$
                                                    // //$NON-NLS-2$
            sql = sql.replaceAll("\\s*\\+\\s*", "+"); // one space before and
                                                        // after + //$NON-NLS-1$
                                                        // //$NON-NLS-2$
            sql = sql.replaceAll("\\s*\\*\\s*", "*"); // one space before and
                                                        // after * //$NON-NLS-1$
                                                        // //$NON-NLS-2$
            sql = sql.replaceAll("\\s*/\\s*", "/"); // one space before and
                                                    // after / //$NON-NLS-1$
                                                    // //$NON-NLS-2$
            sql = sql.replaceAll(stmtTerm, " "); // eliminate optional
                                                    // trailing stmt terminator
                                                    // //$NON-NLS-1$
        }
        return sql.trim();
    }

    /**
     * Strips of the optional whitespace from the given <code>sql</code> and
     * replaces all new lines as well as comments.
     * <p>
     * <b>Note performance: </b> this method invokes
     * {@link String#replaceAll(java.lang.String, java.lang.String)} 11 times
     * after walking through the SQL source two times to remove all comments and
     * transforming the given <code>sql</code> into upper case format
     * (respecting delimited identifiers).
     * 
     * @param sql the SQL source string
     * @param delimitedIdentifierQt the String that is used to pre- and suffix
     *        delimited identifiers, commonly it is the double-quote char '"'
     * @return the given <code>sql</code> in upper case, without comments and
     *         with minimal whitespace
     */
    private static String stripWhiteSpaceAndComments(String sql, char delimitedIdentifierQt) {
        if (sql != null) {
            sql = removeCommentsInSQL(sql, delimitedIdentifierQt);
            sql = toSQLFormatUpperCase(sql, delimitedIdentifierQt);
            sql = stripWhiteSpace(sql, delimitedIdentifierQt);
        }
        return sql.trim();
    }

    /**
     * Unhooks all refernces to <code>ValueEpressionColumn</code>s and
     * <code>ResultTableAllColumns</code> of the <code>oldTableRef</code>
     * and hooks them into the
     * <code>substitute</code> <code>TableExpression</code>, removes the
     * <code>oldTableRef</code> from its container ( {@link QuerySelect},
     * {@link QueryInsertStatement}, {@link QueryDeleteStatement},
     * {@link QueryUpdateStatement}, {@link TableNested} or {@link TableJoined})
     * and hooks in the <code>substitute</code> <code>TableExpression</code>
     * instead.
     * 
     * @param oldTableRef
     * @param substitute
     */
    private static void substituteTableReference(TableExpression oldTableRef, TableExpression substitute) {
        if (oldTableRef != null && substitute != null && oldTableRef != substitute) {

            if (oldTableRef.getQuerySelect() != null) {
                List oldTableSiblings = oldTableRef.getQuerySelect().getFromClause();
                // insert substitute in same position as old one
                for (int i = 0; i < oldTableSiblings.size(); i++) {
                    TableReference oldSibling = (TableReference) oldTableSiblings.get(i);
                    if (oldSibling == oldTableRef) {
                        oldTableSiblings.set(i, substitute);
                        // oldTableRef.setQuerySelect(null); // EMF does it
                        break;
                    }
                }
            }

            copyAllDirectNonNullReferences(oldTableRef, substitute);
            // the following code is obsolete if copyAllDirectNonNullReferences
            // works correctly
            if (false) {
                substitute.getColumnList().addAll(oldTableRef.getColumnList());
                substitute.getResultTableAllColumns().addAll(oldTableRef.getResultTableAllColumns());

                if (oldTableRef.getTableCorrelation() != null) {
                    substitute.setTableCorrelation(oldTableRef.getTableCorrelation());
                    oldTableRef.setTableCorrelation(null);
                }
                if (oldTableRef.getTableJoinedLeft() != null) {
                    substitute.setTableJoinedLeft(oldTableRef.getTableJoinedLeft());
                    oldTableRef.setTableJoinedLeft(null);
                }
                if (oldTableRef.getTableJoinedRight() != null) {
                    substitute.setTableJoinedRight(oldTableRef.getTableJoinedRight());
                    oldTableRef.setTableJoinedRight(null);
                }
                if (oldTableRef.getNest() != null) {
                    substitute.setNest(oldTableRef.getNest());
                    oldTableRef.setNest(null);
                }

                if (oldTableRef instanceof TableInDatabase && substitute instanceof TableInDatabase) {
                    TableInDatabase oldTableInDB = (TableInDatabase) oldTableRef;
                    TableInDatabase substituteTableInDB = (TableInDatabase) substitute;

                    if (oldTableInDB.getInsertStatement() != null) {
                        substituteTableInDB.setInsertStatement(oldTableInDB.getInsertStatement());
                    }
                    if (oldTableInDB.getUpdateStatement() != null) {
                        substituteTableInDB.setUpdateStatement(oldTableInDB.getUpdateStatement());
                    }
                    if (oldTableInDB.getDeleteStatement() != null) {
                        substituteTableInDB.setDeleteStatement(oldTableInDB.getDeleteStatement());
                    }
                    if (oldTableInDB.getDatabaseTable() != null) {
                        substituteTableInDB.setDatabaseTable(oldTableInDB.getDatabaseTable());
                    }
                    if (oldTableInDB.getDatabaseTable() != null) {
                        substituteTableInDB.setDatabaseTable(oldTableInDB.getDatabaseTable());
                    }
                }
            }
        }
    }

    /**
     * Uppercases the characters in the given SQL exept for delimited
     * identifiers.
     * 
     * @param sql
     * @param delimitedIdentifierQt
     * @return
     */
    private static String toSQLFormatUpperCase(String sql, char delimitedIdentifierQt) {
        StringBuffer sqlUC = new StringBuffer();
        char[] sqlChar = sql.toCharArray();
        char delimiterQuote = delimitedIdentifierQt;
        boolean inDelimitedIdentifier = false;

        for (int i = 0; i < sqlChar.length; i++) {
            char c = sqlChar[i];
            if (c == delimiterQuote) {
                inDelimitedIdentifier = !inDelimitedIdentifier;
            }
            if (!inDelimitedIdentifier) {
                c = Character.toUpperCase(c);
            }
            sqlUC.append(c);
        }

        return sqlUC.toString();
    }

    /**
     * Replaces the given Search Condition in the supplied parent.
     * 
     * @param searchCon the new SearchCondition that needs to be replaced with
     * @param parent the Object that contains a SearchCondition that needs to be
     *        replaced
     * @param isHaving true if the given SearchCondition is HavingClause
     */
    private static void updateSearchConditionParent(QuerySearchCondition searchCon, Object parent, boolean isHaving) {
        if (parent instanceof QueryDeleteStatement) {
            ((QueryDeleteStatement) parent).setWhereClause(searchCon);
        }
        else if (parent instanceof QueryUpdateStatement) {
            ((QueryUpdateStatement) parent).setWhereClause(searchCon);
        }
        else if (parent instanceof TableJoined) {
            ((TableJoined) parent).setJoinCondition(searchCon);
        }
        else if (parent instanceof ValueExpressionCaseSearchContent) {
            ((ValueExpressionCaseSearchContent) parent).setSearchCondition(searchCon);
        }
        else if (parent instanceof QuerySelect && isHaving) {
            ((QuerySelect) parent).setHavingClause(searchCon);
        }
        else if (parent instanceof QuerySelect && !isHaving) {
            ((QuerySelect) parent).setWhereClause(searchCon);
        }
        // QueryMergeStatement is not supported here
    }

    /**
     * Provide the name that will be generated next this is for populating alias
     * text field with a default
     */
    public void addNewName(Table selectedTable) {
        boolean done = false;
        String token = selectedTable.getName();
        Integer value = new Integer(0);
        if (nameList == null)
           nameList = new Hashtable();
        Integer number = (Integer) nameList.get(token);

        if (number == null) {
            nameList.put(token, value);
        }
        else {
            while (!done) {
                String result = nameGenerator(selectedTable.getName());
                if (!findSelect(result))
                    break;
            }
        }
    }

    /**
     * @deprecated
     * @param name
     * @param addToDb
     * @return
     */
    public QueryInsertStatement createInsertStatement(String name, boolean addToDb) {
        SQLQueryModelFactory factory = new SQLQueryModelFactoryImpl();
        QueryInsertStatement sqlInsertStatement = factory.createQueryInsertStatement();
        sqlInsertStatement.setName(name);

        if (addToDb) {
            // database.getStatement().add(sqlInsertStatement);
        }

        return sqlInsertStatement;
    }

    public QuerySelectStatement createSelectStatement(String name) {

        SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
        QuerySelectStatement sqlSelectStatement = factory.createQuerySelectStatement();
        sqlSelectStatement.setName(name);
        return sqlSelectStatement;
    }

    /**
     * @deprecated
     * @param name
     * @param addToDb
     * @return
     */
    public QueryUpdateStatement createUpdateStatement(String name, boolean addToDb) {
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        QueryUpdateStatement sqlUpdateStatement = factory.createQueryUpdateStatement();
        sqlUpdateStatement.setName(name);
        if (addToDb) {
            // database.getStatement().add(sqlUpdateStatement);
        }

        return sqlUpdateStatement;
    }

    /**
     * return false if we cannot find the input name
     */
    private boolean findInsert(String name) {
        /*
         * Iterator iterator = database.getStatement().iterator();
         * 
         * while (iterator.hasNext()) { Object o = iterator.next();
         * 
         * if (o instanceof SQLInsertStatement) { SQLInsertStatement insert =
         * (SQLInsertStatement) o; String sname = insert.getName();
         * 
         * if (sname != null && sname.equals(name)) { return true; } } }
         */
        return false;
    }

    /**
     * return false if we cannot find the input name
     */
    private boolean findSelect(String name) {
        /*
         * Iterator iterator = database.getStatement().iterator(); while
         * (iterator.hasNext()) { Object o = iterator.next(); if (o instanceof
         * SQLSelectStatement) { SQLSelectStatement select =
         * (SQLSelectStatement) o;
         * 
         * String sname = select.getName();
         * 
         * if (sname != null && sname.equals(name)) { return true; } } }
         */
        return false;
    }

    public String getNewName(QueryInsertStatement insert) {
        boolean done = false;
        while (!done) {
            String result = nameGenerator("Insert");
            if (!findInsert(result))
                return result;
        }

        return "Insert1";
    }
    
    /**
     * Generate a name
     */
    private String nameGenerator(String token) {
       if (nameList == null)
          nameList = new Hashtable();
       Integer number = (Integer) nameList.get(token);
       int value = 1;
       if (number == null) {
          nameList.put(token, new Integer(value));
       }
       else {
          value = number.intValue();
          value++;
          nameList.remove(token);
          nameList.put(token, new Integer(value));
       }

       return token + value;
    }

    /**
     * Comparator for sorting ValueExpressionVariables.
     */
    public static class ValueExpressionComparator implements Comparator
    {
       public int compare(Object o1, Object o2) {
           int result = 0;
           ValueExpressionVariable h1 = (ValueExpressionVariable) o1;
           ValueExpressionVariable h2 = (ValueExpressionVariable) o2;
           SQLQuerySourceInfo h1si = h1.getSourceInfo();
           SQLQuerySourceInfo h2si = h2.getSourceInfo();
           if (h1si != null && h2si != null) {
               int h1Loc = h1si.getSpanStartOffset();
               int h2Loc = h2si.getSpanStartOffset();
               result = h1Loc - h2Loc;
           }
           else {
//               StatementHelper.logError(StatementHelper.class.getName() + "could not compare the location of host variables or parameter markers as no "
//                       + SQLQuerySourceInfo.class.getName() + " was associated with them. For host variables or parameter markers: " + h1 + " and " + h2);
           }
           return result;
       }
    }

}

