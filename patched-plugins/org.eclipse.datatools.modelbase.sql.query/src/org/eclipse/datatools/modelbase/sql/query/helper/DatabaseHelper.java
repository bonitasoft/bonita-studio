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

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * This class provides a set of utility methods for dealing with RDBDatabase
 * objects and associated objects such as RDBTable and RDBColumn.
 */
public class DatabaseHelper {
  
  /** DO NOT USE ANYMORE IN CONNECTION WITH org.eclipse.datatools.sqltools.parsers.sql.query PARSER!
   * Resolves the table references in the From clause of the given query so that
   * each has the correct RDBTable attached to it.
   * 
   * @param aSQLStmt
   *            a SQL statement which's table refernces are to be resolved
   * @param aDatabase
   *            a Database to use to get RDBTables
   * @param aDefaultSchemaName
   *            the name of the Schema the tables will be looked up for,
   *            if the tables are not already qualified 
   * 
   * @deprecated table reference resolving is now done by post parse processer,
   * use {@link org.eclipse.datatools.modelbase.parsers.sql.query.postparse.TableReferenceResolver}
   * with {@link org.eclipse.datatools.modelbase.parsers.sql.query.SQLQueryParserManager}
   */
  public static void resolveTableReferenceRDBTables(QueryStatement aSQLStmt,
                                                    Database aDatabase,
                                                    String aDefaultSchemaName)
  {
      List tableRefList = StatementHelper.getTablesForStatement(aSQLStmt);
      resolveTableReferenceRDBTables(tableRefList, aDatabase, aDefaultSchemaName);
  }

  /** DO NOT USE ANYMORE IN CONNECTION WITH org.eclipse.datatools.sqltools.parsers.sql.query PARSER!
   * Resolves the table references in the From clause of the given query so that
   * each has the correct RDBTable attached to it.
   * 
   * @param aTableRefList
   *            a list of table references to be resolved
   * @param aDatabase
   *            an Database to use to get RDBTables
   * @param aDefaultSchemaName
   *            the name of the Schema the tables will be looked up for, if the tables 
   *            are not already qualified 
   * 
   * @deprecated table reference resolving is now done by post parse processer,
   * use {@link org.eclipse.datatools.modelbase.parsers.sql.query.postparse.TableReferenceResolver}
   * with {@link org.eclipse.datatools.modelbase.parsers.sql.query.SQLQueryParserManager}
   */
  public static void resolveTableReferenceRDBTables(List aTableRefList,
                                                    Database aDatabase,
                                                    String aDefaultSchemaName )
  {

      TableReference tblRef;
      TableInDatabase table = null;

      for (Iterator tableIt = aTableRefList.iterator(); tableIt.hasNext();)
      {
          tblRef = (TableReference) tableIt.next();

          if (tblRef instanceof TableInDatabase)
          {
              table = (TableInDatabase) tblRef;
              resolveTableReferenceRDBTable(table, aDatabase, aDefaultSchemaName);
          }
      }
  }

  /**
   * Resolves the given table references so that it has the correct RDBTable attached to it.
   * 
   * @param aTableInDB a table references to be resolved
   * @param aDatabase a Database object to use to get RDBTables
   * @param aDefaultSchemaName the default schema name to use to look up
   * tables in the database for tables that are not already qualified 
   */
  public static void resolveTableReferenceRDBTable( TableInDatabase aTableInDB,
                                                    Database aDatabase,
                                                    String aDefaultSchemaName )
  {

      Table rdbTable = null;
      Schema rdbSchema = null;
      String rdbSchemaName = null;
      String searchTableName = null;
      
      searchTableName = aTableInDB.getName();
      Table tempRdbTbl = aTableInDB.getDatabaseTable();
      Schema tempRdbSch = null;

      /* If the table doesn't have a schema associated with it use the default schema
       * that was passed in.
       */
      if (tempRdbTbl != null) {
          tempRdbSch = tempRdbTbl.getSchema();
          if (tempRdbSch != null 
           && tempRdbSch.getName() != null 
           && tempRdbSch.getName().length() > 0){
              rdbSchemaName = tempRdbSch.getName();
          }
          else {
              rdbSchemaName = aDefaultSchemaName;
          }
      }

      /* Get the RDBTable object associated with the RDBDatabase that has the 
       * table name we're looking for.
       */
      // Note: we've set up the RDBDatabase so that tables and views are both
      // treated as tables. If this changes, we will need to call "findAbstractTable" 
      // here instead of "findTable".
      rdbSchema = findSchema(aDatabase, rdbSchemaName);
      if (rdbSchema != null) {
          rdbTable = findTable(rdbSchema, searchTableName); 
      }

      /* Associate the RDBTable we found with our query table. */
      if (rdbTable != null) {
          aTableInDB.setDatabaseTable(rdbTable);
          
          /* Populate the column list attached to the TableExpression object in 
           * the query model.
           */
          TableHelper.populateTableExpressionColumns(aTableInDB, rdbTable);
      }
  }
  
  /**
   * Finds and returns a Procedure object with the given name associated with the given Schema object.
   * 
   * @param schema the Schema object to use to find the Procedure object
   * @param procName the name of the Procedure object to find
   * @return the found Procedure object, or null if not found
   */
  public static Procedure findProcedure(Schema schema, String procName) {
      Procedure returnProc = null;
      
      if (schema != null && procName != null) {
          /* Get a list of Procedure objects from the given Schema object. */
          List dbProcList = schema.getProcedures();
          
          /* Try to find a match for the given proc name in the list of
           * procedures.  The names we're comparing are in catalog format, 
           * which means they are not delimited. Try first for a direct
           * match on the name, respecting case. */
          Iterator dbProcListIter = dbProcList.iterator();
          while (dbProcListIter.hasNext() && returnProc == null) {
              Procedure dbProc = (Procedure) dbProcListIter.next();
              String dbProcName = dbProc.getName();           
              if (procName.equals(dbProcName)) {
                  returnProc = dbProc;
              }
          }
          
          /* If we didn't find a match, try again without regard to case. */
          if (returnProc == null) {
              dbProcListIter = dbProcList.iterator();
              while (dbProcListIter.hasNext() && returnProc == null) {
                  Procedure dbProc = (Procedure) dbProcListIter.next();
                  String dbProcName = dbProc.getName();           
                  if (procName.equalsIgnoreCase(dbProcName)) {
                      returnProc = dbProc;
                  }
              }
          }
      }

      return returnProc;
  }
  
  // RATLC0391894 bgp 06July2007 -- method adapted from wtp.rdb DatabaseHelper class
  /**
   * Finds a <code>Schema</code> by its name in a given <code>Database</code>.
   * If the given <code>schemaName</code> is not delimited, the search is not case 
   * sensitive.  
   * 
   * @param database the <code>Database</code> to find <code>Schema</code>s in
   * @param schemaName the String name of the <code>Schema</code> to find
   * @return the <code>Schema</code> found or <code>null</code> if no 
   *      corresponding <code>Schema</code> was found
   */
  public static Schema findSchema(Database database, String schemaName) {
      Schema returnSchema = null;
      
      if (schemaName != null) {
          /* Get the list of schemas from the database. */
          List dbSchemaList = database.getSchemas();
          
          /* If we don't have any schemas, then check whether there
           * are Catalog objects that contain the schemas. */
          if (dbSchemaList == null || dbSchemaList.isEmpty()) {
              List catalogList = database.getCatalogs();
              if (!catalogList.isEmpty()) {
                  dbSchemaList = new ArrayList();
                  Iterator catalogIter = catalogList.iterator();
                  while(catalogIter.hasNext()) {
                      Catalog currentCatalog = (Catalog)catalogIter.next();
                      dbSchemaList.addAll(currentCatalog.getSchemas());
                  }
              }
          }

          /* Try to match the given schema name with one of the schemas
           * from the database. The names are in catalog format, which means
           * they are not delimited. Try first for a direct match on the
           * name, respecting case. (This helps handle the case where there are 
           * two schemas in the DB that differ only by case.) */
          Iterator dbSchemaListIter = dbSchemaList.iterator();
          while (dbSchemaListIter.hasNext() && returnSchema == null) {
              Schema dbSchema = (Schema) dbSchemaListIter.next();
              String dbSchemaName = dbSchema.getName();
              if (schemaName.equals(dbSchemaName)) {
                  returnSchema = dbSchema;
              }
          }    
          
          /* If we didn't find a match, try again without regard to case. */
          if (returnSchema == null) {
              dbSchemaListIter = dbSchemaList.iterator();
              while (dbSchemaListIter.hasNext() && returnSchema == null) {
                  Schema dbSchema = (Schema) dbSchemaListIter.next();
                  String dbSchemaName = dbSchema.getName();
                  if (schemaName.equalsIgnoreCase(dbSchemaName)) {
                      returnSchema = dbSchema;
                  }
              }    
          }
          
          /* If we still didn't find a match, trim *trailing* blanks from the 
           * schema names and try again.  Some databases (such as DB2 for LUW)
           * when given a new schema named "  schema  ", will create the schema
           * in the catalog as "  schema". */
          if (returnSchema == null) {
              String trimmedSchemaName = trimTrailing(schemaName);
              dbSchemaListIter = dbSchemaList.iterator();
              while (dbSchemaListIter.hasNext() && returnSchema == null) {
                  Schema dbSchema = (Schema) dbSchemaListIter.next();
                  String trimmedDBSchemaName = trimTrailing(dbSchema.getName());
                  if (trimmedSchemaName.equals(trimmedDBSchemaName)) {
                      returnSchema = dbSchema;
                  }
              }     
          }
      }
  
      return returnSchema;
  }

  // RATLC0391894 bgp 15May2007 -- method adapted from wtp.rdb SchemaHelper class  
  /**
   * Finds a <code>Table</code> by its name in a given <code>Schema</code>,
   * the given <code>tableName</code> is not case sensitive.  
   * Look at {@link DatabaseHelper#findSchema(Database, String)} to see how 
   * the <code>Schema</code> can be found in a <code>Database</code>.
   * 
   * @param schema the <code>Schema</code> to find a <code>Table</code> in
   * @param tableName the String name of the <code>Table</code> to find 
   * @return the <code>Table</code> found or null
   */
  public static Table findTable(Schema schema, String tableName) {
      Table returnTable = null;
      
      if (tableName != null) {
          /* Get a list of tables from the given schema. */
          List dbTableList = schema.getTables();
          
          /* Try to find a match for the given table name in the list of
           * tables.  The table names we're comparing are in catalog format, 
           * which means they are not delimited. Try first for a direct
           * match on the name, respecting case. */
          Iterator dbTableListIter = dbTableList.iterator();
          while (dbTableListIter.hasNext() && returnTable == null) {
              Table dbTable = (Table) dbTableListIter.next();
              String dbTableName = dbTable.getName();           
              if (tableName.equals(dbTableName)) {
                  returnTable = dbTable;
              }
          }
          
          /* If we didn't find a match, try again without regard to case. */
          if (returnTable == null) {
              dbTableListIter = dbTableList.iterator();
              while (dbTableListIter.hasNext() && returnTable == null) {
                  Table dbTable = (Table) dbTableListIter.next();
                  String dbTableName = dbTable.getName();           
                  if (tableName.equalsIgnoreCase(dbTableName)) {
                      returnTable = dbTable;
                  }
              }
          }
      }
      
      return returnTable;
  }
  
  /**
   * Returns a copy of the given string with whitespace characters removed from the end of the string.
   * 
   * @return the trimmed string
   */
  protected static String trimTrailing(String str) { 
      return str.replaceAll("\\s+$", "");
  }

} // end class DatabaseHelper
