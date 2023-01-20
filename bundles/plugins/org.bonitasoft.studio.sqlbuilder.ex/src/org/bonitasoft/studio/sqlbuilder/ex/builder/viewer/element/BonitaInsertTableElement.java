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
package org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element;

import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.MultilineInputDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.InsertHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaInsertTableElement {

	protected QueryInsertStatement insertStatement;
	protected ValueExpressionColumn insertColumn;
	protected QueryValueExpression valueExpr;
	protected SQLDomainModel domainModel;

    public BonitaInsertTableElement(SQLDomainModel domainModel, QueryInsertStatement insertStmt,
			ValueExpressionColumn column, QueryValueExpression expr) {
		this.domainModel = domainModel;
		this.insertStatement = insertStmt;
		this.insertColumn = column;
		this.valueExpr = expr;
	}

	public QueryValueExpression showExpressionBuilder(Object obj,boolean isColumn, String action, Object oldValue) {
		return null;
	}

	public void modify(Object key, Object value ,Object oldValue) {
		Object newValue = null;
		if (key == SQLBuilderConstants.P_STATEMENT_VALUE) {

			if (value instanceof String) {
				final String strVal = (String) value;
				if (strVal.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || strVal.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)
						|| strVal.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {

					newValue = showExpressionBuilder(getExpression(), false, (String) value, oldValue);
				}
				else if (strVal.equals(SQLBuilderConstants.P_VALUE_DEFAULT)) {
					newValue = ExpressionHelper.createValueExpressionDefaultValue();
				}
				else if (strVal.equals(SQLBuilderConstants.P_VALUE_NULL)) {
					newValue = ExpressionHelper.createValueExpressionNullValue();
				}
				else if (strVal.equals(SQLBuilderConstants.P_EDIT_INPUT_VALUE)) {
					newValue = showInputEditor(valueExpr.getSQL());                      
				}

			}
			else if (value instanceof QueryValueExpression) {
				newValue = value;
			}

			if (valueExpr == null) {
				if (newValue == null) {
					newValue = ExpressionHelper.createExpression(getColumnText(1));
				}
				InsertHelper.addInsertColumnValuePair(insertStatement, insertColumn, (QueryValueExpression) newValue);
			}
			else {
				InsertHelper.updateInsertValueForColumn(insertStatement, insertColumn, (QueryValueExpression) newValue);
			}
		}
		//insert values is setup in the UI 
		else if (key == SQLBuilderConstants.P_STATEMENT_COLUMN) {
			if (insertColumn != null) { //a column is already selected in the grid
				InsertHelper.replaceColumn(insertStatement, insertColumn, (ValueExpressionColumn) value);
			}
			else {//a column is being added from the grid
				final EditingDomain editDomain = domainModel.getEditingDomain();
				final Column col = TableHelper.getColumnForColumnExpression(insertStatement.getTargetTable(), (ValueExpressionColumn) value);
				InsertHelper.addColumn(editDomain, insertStatement, col);
			}
		}
	}

	public QueryInsertStatement getInsertStatement() {
		return insertStatement;
	}

	public ValueExpressionColumn getColumn() {
		return insertColumn;
	}

	public QueryValueExpression getExpression() {
		return valueExpr;
	}

	/**
	 * Get the mof value and return it
	 */
	public String getColumnText(int columnIndex) {
		String columnText = ""; //$NON-NLS-1$
		if (columnIndex == 0) {
			if (insertColumn != null) {
				columnText = insertColumn.getName();
			}
		}
		else if (columnIndex == 1) {
			if (valueExpr != null) {
				columnText = valueExpr.getSQL();
			}
		}
		return columnText;
	}
	/**
	 * Launches the input editor to optain user value
	 * @param initialText the initial text to show in the editor
	 * @return the input from the editor as a QueryValueExpression
	 */
	protected QueryValueExpression showInputEditor(String initialText) {

		final MultilineInputDialog inputDialog = new MultilineInputDialog(Display.getDefault().getActiveShell(), 
				Messages._UI_SPECIFY_VALUE_TITLE, insertColumn.getName());
		inputDialog.setText(initialText);
		inputDialog.open();
		final String text = inputDialog.getText();
		final QueryValueExpression expression = ExpressionHelper.createExpression(text);
		return expression;
	}



}
