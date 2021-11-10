/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.sqlbuilder.ex.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.AddTableDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.model.DeleteHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.InsertHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.UpdateHelper;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaAddTableAction extends Action {

    Object object;
    SQLDomainModel domainModel;
    Object selectTableToReplace = null;
    boolean replace = false; // true = replace table, false = add table

    public BonitaAddTableAction(SQLDomainModel domainModel) {
        super(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_ADD_TABLE);
        this.domainModel = domainModel;
    }

    public void setTable(Object table) {
        this.selectTableToReplace = table;
        if (table != null) {
            replace = true;
            setText(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_ADD_TABLE_REPLACE);
        }
        else {
            replace = false;
            setText(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_ADD_TABLE);
        }
    }

    public void setElement(Object obj) {
        object = obj;

        if (selectTableToReplace == null) {

            replace = false;
            if (object instanceof QueryInsertStatement) {
                replace = (((QueryInsertStatement) object).getTargetTable() == null) ? false : true;

            }
            else if (object instanceof QueryUpdateStatement) {
                replace = (((QueryUpdateStatement) object).getTargetTable() == null) ? false : true;
            }
            else if (object instanceof QueryDeleteStatement) {
                replace = (((QueryDeleteStatement) object).getTargetTable() == null) ? false : true;
            }

            if (replace) {
                setText(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_ADD_TABLE_REPLACE);
            }
            else {
                setText(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_ADD_TABLE);
            }
        }
    }

    Shell getShell() {
        return org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow().getShell();
    }

    @Override
    public void run() {
        SQLObject table = null;
        String tableAlias = null;

        // Retrieve referenced table names
        if (object != null) {
            List tableList = new ArrayList();
            if (object instanceof SQLQueryObject) {
                tableList = StatementHelper.getTablesForStatement((SQLQueryObject) object);
            }

            final Vector tableNames = new Vector();

            for (int i = 0; i < tableList.size(); i++) {
                final Object item = tableList.get(i);

                if (item instanceof TableExpression) {
                    // For those statements where that a correlation object
                    final TableExpression tableExpr = (TableExpression) item;
                    tableNames.addElement(tableExpr.getName());
                }
                else if (item instanceof Table) {
                    tableNames.addElement(((Table) item).getName());
                }
            }
        }

        Object action = null;
        if (replace) {
            action = AddTableDialog.REPLACE_TABLE;
        }
        else {
            action = AddTableDialog.ADD_TABLE;
        }

        final BonitaAddTableDialog dialog = new BonitaAddTableDialog(getShell(), domainModel, object, null);
        dialog.setAction(action);
        String tableName = ""; //$NON-NLS-1$
        if (selectTableToReplace != null) {
            if (selectTableToReplace instanceof TableExpression) {
                tableName = ((TableExpression) selectTableToReplace).getName();
            }
            else {
                tableName = ((Table) selectTableToReplace).getName();
            }
        }
        dialog.setReplaceTitle(tableName);
        dialog.create();

        dialog.setBlockOnOpen(true);
        final int value = dialog.open();
        if (value == Window.CANCEL)
            return;
        final List tableList = dialog.getTablesList();
        //table = dialog.getTableValue();
        if (tableList != null && tableList.size() > 0) {
        	table = (SQLObject) tableList.get(0);
        }
        
        tableAlias = dialog.getTableAlias();

        if (table != null) {
            if (object instanceof QuerySelectStatement || object instanceof QuerySelect) {
                final SQLQueryObject stmt = (SQLQueryObject) object;
                final String name = stmt.getName();
                final String label = stmt.getLabel();
                TableExpression tableExpr = null;
                int replacePosition = 0;

                if (replace) {
                    if (selectTableToReplace instanceof Table) {
                        final List tblExprList = StatementHelper.getTablesForStatement(stmt);
                        final TableExpression oldTableExpr = TableHelper.getTableExpressionFromTableExprList(((Table) selectTableToReplace).getName(), tblExprList);
                        replacePosition = tblExprList.indexOf(oldTableExpr);
                        if (oldTableExpr != null) {
                            //SelectHelper.removeTableFromStatement(stmt, oldTableExpr);
                            StatementHelper.removeTableExpressionFromQueryStatement(oldTableExpr, stmt);
                        }
                    }
                    else if (selectTableToReplace instanceof WithTableReference) {
                        StatementHelper.removeTableExpressionFromQueryStatement((WithTableReference) selectTableToReplace, stmt);
                    }
                }

                final Iterator itr = tableList.iterator();
                while (itr.hasNext()) {
                    table = (SQLObject) itr.next();
                    if (table instanceof Table) {
                        tableExpr = TableHelper.createTableExpressionForTable((Table) table);
                    }
                    else if (table instanceof WithTableSpecification) {
                        tableExpr = StatementHelper.createWithTableReferenceForWithTable((WithTableSpecification) table);
                    }
                    if (tableAlias.trim().length() > 0) {
                        TableHelper.setTableAliasInTableExpression(tableExpr, tableAlias);
                    }
                    if (stmt instanceof QuerySelectStatement) {
                    	if(replace){
                    		SelectHelper.addTableToStatementAtPosition(stmt, tableExpr, replacePosition);
                    	}
                    	else {
                    		SelectHelper.addTableToStatement((QuerySelectStatement) stmt, tableExpr);
                    	}
                    }
                    else if (stmt instanceof QuerySelect) {
                    	if(replace){
                    		SelectHelper.addTableToStatementAtPosition(stmt, tableExpr, replacePosition);
                    	}
                    	else {
                    		SelectHelper.addTableToStatement((QuerySelect) stmt, tableExpr);
                    	}
                    }
                }
                stmt.setName(name);
                stmt.setLabel(label);
            }
            else if (object instanceof QueryInsertStatement) {
                final QueryInsertStatement stmt = (QueryInsertStatement) object;
                final String name = stmt.getName();
                final String label = stmt.getLabel();
                final TableInDatabase tableExpr = TableHelper.createTableExpressionForTable((Table) table);
                InsertHelper.clearStatementContents(stmt);
                InsertHelper.setTargetTable(stmt, tableExpr);
                stmt.setName(name);
                stmt.setLabel(label);
            }
            else if (object instanceof QueryUpdateStatement) {
                final QueryUpdateStatement stmt = (QueryUpdateStatement) object;
                final String name = stmt.getName();
                final String label = stmt.getLabel();
                final TableInDatabase tableExpr = TableHelper.createTableExpressionForTable((Table) table);
                if (tableAlias.trim().length() > 0) {
                    TableHelper.setTableAliasInTableExpression(tableExpr, tableAlias);
                }
                UpdateHelper.clearStatementContents(stmt);
                UpdateHelper.setTargetTable(stmt, tableExpr);
                stmt.setName(name);
                stmt.setLabel(label);
            }
            else if (object instanceof QueryDeleteStatement) {
                final QueryDeleteStatement stmt = (QueryDeleteStatement) object;
                final String name = stmt.getName();
                final String label = stmt.getLabel();
                final TableInDatabase tableExpr = TableHelper.createTableExpressionForTable((Table) table);
                if (tableAlias.trim().length() > 0) {
                    TableHelper.setTableAliasInTableExpression(tableExpr, tableAlias);
                }
                DeleteHelper.clearStatementContents(stmt);
                DeleteHelper.setTargetTable(stmt, tableExpr);
                stmt.setName(name);
                stmt.setLabel(label);

            }
        }
    }

}
