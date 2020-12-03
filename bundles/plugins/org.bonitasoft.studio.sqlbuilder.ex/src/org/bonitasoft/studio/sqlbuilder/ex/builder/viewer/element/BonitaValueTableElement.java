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

import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaValueTableElement {

	ValuesRow valuesRow;
	SQLDomainModel domainModel;
	QueryValueExpression expression;

	public BonitaValueTableElement(SQLDomainModel model, ValuesRow valRow, QueryValueExpression expr) {
		domainModel = model;
		valuesRow = valRow;
		expression = expr;
	}

	public void removeExpression() {
		if (expression != null) {
			final List rowExprList = getValuesRow().getExprList();
			rowExprList.remove(expression);
		}
	}

	public QueryValueExpression getExpression() {
		return expression;
	}

	public ValuesRow getValuesRow() {
		return valuesRow;
	}

	/**
	 * Get the MOF object value
	 */
	 public String getColumnText(int columnIndex) {
		String colText = "";
		if (columnIndex == 0) {
			if (expression != null) {
				colText = expression.getSQL();
			}
		}
		return colText;
	 }

	 public void modify(Object key, Object propValue) {
		 if (key == SQLBuilderConstants.P_STATEMENT_VALUE) {
			 if (propValue instanceof String
					 && (((String) propValue).equals(SQLBuilderConstants.P_BUILD_EXPRESSION)
							 || ((String) propValue).equals(SQLBuilderConstants.P_EDIT_EXPRESSION) || ((String) propValue)
							 .equals(SQLBuilderConstants.P_REPLACE_EXPRESSION))) {
				 final QueryValueExpression expr = showExpressionBuilder(key, false, (String) propValue);
				 if (expr != null) {
					 addExpression(expr);
				 }
			 }
			 else if (propValue instanceof QueryValueExpression) {
				 addExpression((QueryValueExpression) propValue);
			 }
		 }
	 }

	 void addExpression(QueryValueExpression newExpr) {
		 if (valuesRow != null) {
			 final List rowExprList = valuesRow.getExprList();
			 if (rowExprList != null) {
				 final int position = rowExprList.indexOf(expression);
				 if (position != -1) {
					 rowExprList.add(position, newExpr);
				 }
				 rowExprList.add(newExpr);
			 }
		 }
	 }

	 /**
	  * Launch the expression builder
	  */

	  public QueryValueExpression showExpressionBuilder(Object key, boolean isColumn, String action) {
		  ExpressionBuilderWizard wizard;
		  wizard = new ExpressionBuilderWizard(domainModel, domainModel.getSQLStatement());
		  if (key == SQLBuilderConstants.P_STATEMENT_VALUE) {
			  if (action.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || action.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION))

			  {
				  wizard.setInputExpression(null);
			  }
			  else if (action.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)) {
				  if (expression != null) {
					  wizard.setInputExpression(expression);
				  }
				  else {
					  wizard.setInputExpression(null);
				  }
			  }
		  }

		  wizard.setIsColumn(isColumn);

		  final ExpressionBuilderDialog dialog = new ExpressionBuilderDialog(Display.getDefault().getActiveShell(), wizard);
		  dialog.create();
		  dialog.setBlockOnOpen(true);
		  final int result = dialog.open();

		  if (result == 0) {
			  return wizard.getSQLExpression();
		  }
		  return null;
	  }
}

