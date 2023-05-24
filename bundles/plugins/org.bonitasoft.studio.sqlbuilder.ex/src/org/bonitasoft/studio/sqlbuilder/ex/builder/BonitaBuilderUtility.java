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
package org.bonitasoft.studio.sqlbuilder.ex.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.EditComboBoxCellEditor;
import org.eclipse.jface.viewers.CellEditor;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaBuilderUtility {

	public static int fillColumnComboBox(CellEditor cellEditor, SQLQueryObject sqlStatement, 
			boolean enableExprBuilder, boolean existsExpression) {
		List tables = new ArrayList();
		if (sqlStatement instanceof QueryStatement) {
			tables = StatementHelper.getTablesForStatement((QueryStatement) sqlStatement);
		}
		else if (sqlStatement instanceof QuerySelect) {
			tables = StatementHelper.getTableExpressionsInQuerySelect((QuerySelect) sqlStatement);
		}
		int size = getTotalColumns(tables);

		if (enableExprBuilder && !existsExpression) {
			size++; // for build expression... option
		}
		else if (enableExprBuilder && existsExpression) {
			size += 2; // for edit and replace options
		}

		if (cellEditor != null) {
			LabelValuePair[] items = (LabelValuePair[]) getDistinctColumnItems(sqlStatement, enableExprBuilder, existsExpression);
			if (cellEditor instanceof ComboBoxCellEditor) {
				((ComboBoxCellEditor) cellEditor).createItems(items);
			}
			else if (cellEditor instanceof EditComboBoxCellEditor) {
				((EditComboBoxCellEditor) cellEditor).createItems(items);
			}
		}

		return getTotalColumns(tables);
	}

	public static Object getDistinctColumnItems(SQLQueryObject sqlStatement, boolean enableExprBuilder, boolean existsExpression) {

		List colList = new ArrayList();
		List tables = new ArrayList();
		if (sqlStatement instanceof QueryStatement) {
			tables = StatementHelper.getTablesForStatement((QueryStatement) sqlStatement);
		}
		else if (sqlStatement instanceof QuerySelect) {
			tables = StatementHelper.getTableExpressionsInQuerySelect((QuerySelect) sqlStatement);
		}

		boolean schemaNeeded = false;
		for (int i = 0; i < tables.size() && !schemaNeeded; i++) {
			Object item = tables.get(i);
			TableExpression table = null;
			String tableName = "";
			if (item instanceof TableExpression) {
				table = (TableExpression) item;
				tableName = TableHelper.getExposedTableName(table);
				for (int j = i + 1; j < tables.size() && !schemaNeeded; j++) {
					Object item2 = tables.get(j);
					TableExpression table2 = null;
					String tableName2 = "";
					if (item2 instanceof TableExpression) {
						table2 = (TableExpression) item2;
						tableName2 = TableHelper.getExposedTableName(table2);
					}
					if (tableName.equalsIgnoreCase(tableName2)) {
						schemaNeeded = true;
					}
				}
			}
		}

		for (int i = 0; i < tables.size(); i++) {
			Object item = tables.get(i);
			TableExpression table = null;
			String tableName = "";
			String schemaName = "";
			if (item instanceof TableExpression) {
				table = (TableExpression) item;
				if (schemaNeeded && table instanceof TableInDatabase) {
					schemaName = TableHelper.getSchemaNameForTableExpression(table);
				}
				tableName = TableHelper.getExposedTableName((TableExpression) item);
				if (tableName == null || tableName.equals("")) {
					tableName = "";
				}
				if (tableName.length() > 0 & schemaName.length() > 0) {
					tableName = schemaName + "." + tableName;
				}
			}
			// Construct combo box item. Each combo box item is backed by a Rdb Column.
			Iterator iterator = table.getColumnList().iterator();

			while (iterator.hasNext()) {
				boolean found = false;
				ValueExpressionColumn rdbColumn = (ValueExpressionColumn) iterator.next();

				if (sqlStatement instanceof QueryInsertStatement)
				{
					// do not add items that are already on the target columns
					QueryInsertStatement insert = (QueryInsertStatement)sqlStatement;
					if (isExpressionInTargetList(rdbColumn, insert))
					{
						continue;
					}

				}

				TableExpression expr = rdbColumn.getTableExpr();
				if (expr == null) {
					// Assign parent expression to table expression because
					// the table expression may be null.  As a result, the table name
					// is replace with <null> when a column is modified
					expr = rdbColumn.getParentTableExpr();
					rdbColumn.setTableExpr(expr);
				}                

				String displayName = "";
				if (tableName.trim().length() > 0)
					displayName = tableName + "." + rdbColumn.getName();
				else
					displayName = rdbColumn.getName();

				Iterator colItr = colList.iterator();
				while (colItr.hasNext()) {
					LabelValuePair label = (LabelValuePair) colItr.next();
					if (label != null && label.fLabel.equals(displayName)) {
						found = true;
					}
				}
				if (!found) {
					colList.add(new LabelValuePair(displayName, rdbColumn));
				}
			}
		}

		if (enableExprBuilder && !existsExpression) {
			//colList.add(new LabelValuePair(SQLBuilderConstants.P_EDIT_EXPRESSION, SQLBuilderConstants.P_BUILD_EXPRESSION));
		}
		else if (enableExprBuilder && existsExpression) {
			//colList.add(new LabelValuePair(SQLBuilderConstants.P_EDIT_EXPRESSION, SQLBuilderConstants.P_EDIT_EXPRESSION));
			colList.add(new LabelValuePair(SQLBuilderConstants.P_REPLACE_EXPRESSION, SQLBuilderConstants.P_REPLACE_EXPRESSION));
		}

		colList.add(new LabelValuePair(" ", " "));
		LabelValuePair[] items = new LabelValuePair[colList.size()];
		Iterator colItr = colList.iterator();
		int ctr = 0;
		while (colItr.hasNext()) {
			LabelValuePair pair = (LabelValuePair) colItr.next();
			items[ctr] = pair;
			ctr++;
		}

		return items;
	}

	/**
	 * Determines whether or not a ValueExpressionColumn already exists in the
	 * target column list
	 * @param exprColumn the expression column
	 * @param insert the QueryInsertStatement containing the target column list
	 * @return true if exists, false if not
	 */
	private static boolean isExpressionInTargetList(ValueExpressionColumn exprColumn,
			QueryInsertStatement insert)
	{
		if (exprColumn == null || insert == null)
		{
			return false;
		}    	
		List targetList = insert.getTargetColumnList();
		for (int i=0;i<targetList.size();i++)
		{
			ValueExpressionColumn column = (ValueExpressionColumn)targetList.get(i);
			if (column.getName().equalsIgnoreCase(exprColumn.getName()) &&
					column.getTableExpr().getName().equalsIgnoreCase(insert.getTargetTable().getName()))
			{
				return true;
			}
		}
		return false;
	}

	public static Object getColumnItems(QueryStatement sqlStatement, boolean enableExprBuilder, boolean existsExpression) {
		List tables = StatementHelper.getTablesForStatement(sqlStatement);

		int size = getTotalColumns(tables);
		if (enableExprBuilder && !existsExpression) {
			size++; // for build expression... option
		}
		else if (enableExprBuilder && existsExpression) {
			size += 2; // for edit and replace options
		}

		LabelValuePair[] items = new LabelValuePair[size];

		int columnIndex = 0;

		for (int i = 0; i < tables.size(); i++) {
			Object item = tables.get(i);
			TableExpression table = null;
			String tableName = "";

			if (item instanceof TableExpression) {
				table = (TableExpression) item;
				tableName = TableHelper.getExposedTableName((TableExpression) item);

				if (tableName == null || tableName.equals("")) {
					tableName = "";
				}
			}            
			// Construct combo box item. Each combo box item is backed by a Rdb Column.
			Iterator iterator = table.getColumnList().iterator();
			while (iterator.hasNext()) {
				boolean found = false;
				ValueExpressionColumn rdbColumn = (ValueExpressionColumn) iterator.next();

				String displayName = "";
				if (tableName.trim().length() > 0)
					displayName = tableName + "." + rdbColumn.getName();
				else
					displayName = rdbColumn.getName();

				for (int newIdx = 0; newIdx < items.length; newIdx++) {
					LabelValuePair label = items[newIdx];
					if (label != null && label.fLabel.equals(displayName)) {
						found = true;
					}
				}
				if (!found) {
					items[columnIndex++] = new LabelValuePair(displayName, rdbColumn);
				}
			}
		}

		if (enableExprBuilder && !existsExpression) {
			items[columnIndex++] = new LabelValuePair("Edit Expression", "Edit Expression");
		}
		else if (enableExprBuilder && existsExpression) {
			items[columnIndex++] = new LabelValuePair("Edit Expression", "Edit Expression");
			items[columnIndex++] = new LabelValuePair(SQLBuilderConstants.P_REPLACE_EXPRESSION, SQLBuilderConstants.P_REPLACE_EXPRESSION);

		}

		items[columnIndex] = new LabelValuePair(" ", " ");
		return items;
	}

	public static int getTotalColumns(List tables) {
		int size = 1;

		for (int i = 0; i < tables.size(); i++) {
			Object item = tables.get(i);
			TableExpression table = null;
			if (item instanceof TableExpression) {
				table = (TableExpression) item;
			}
			size = size + table.getColumnList().size();
		}

		return size;
	}

	public static List getColumnVector(SQLQueryObject sqlStatement) {
		List columnVector = new ArrayList();
		List tables = new ArrayList();
		if (sqlStatement instanceof QueryStatement) {
			tables = StatementHelper.getTablesForStatement((QueryStatement) sqlStatement);
		}
		else if (sqlStatement instanceof QuerySelect) {
			tables = StatementHelper.getTableExpressionsInQuerySelect((QuerySelect) sqlStatement);
		}

		for (int i = 0; i < tables.size(); i++) {
			Object item = tables.get(i);
			TableExpression table = null;
			if (item instanceof TableExpression) {
				table = (TableExpression) item;
			}
			List colList = table.getColumnList();
			if (colList != null) {
				Iterator iterator = colList.iterator();
				while (iterator.hasNext()) {
					ValueExpressionColumn colExpr = (ValueExpressionColumn) iterator.next();
					columnVector.add(colExpr);
				}
			}
		}
		return columnVector;
	}
}
