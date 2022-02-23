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

import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.BonitaSelectGridViewer;
import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderingSpecType;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLStringHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;

/**
 * SelectTableElement is applicable for select statement
 */
public class BonitaSelectTableElement {

    protected ResultColumn resultColumn;
    protected OrderByValueExpression orderColumn;
    protected QueryValueExpression resultColExpr;
    protected SQLQueryObject selectStatement;
    protected QuerySelect qSelect;
    protected SQLDomainModel domainModel;
    protected Object column;
    protected Object target;
    protected SQLQueryObject statement;

    Object selectedColumn;
    String alias = ""; //$NON-NLS-1$
    String sortType = ""; //$NON-NLS-1$
    String sortOrder = ""; //$NON-NLS-1$

    /**
     * @param target - a select statement
     * @param insertValue - a select column
     */
    public BonitaSelectTableElement(SQLDomainModel domainModel, Object target, ResultColumn resultCol) {
        this.target = target;
        this.domainModel = domainModel;
        if (target instanceof QuerySelectStatement) {
            selectStatement = (QuerySelectStatement) target;
            this.statement = (QueryStatement) target;
        }
        else if (target instanceof QuerySelect) {
            qSelect = (QuerySelect) target;
            selectStatement = (SQLQueryObject) (target);
            statement = selectStatement;
        }
        resultColumn = resultCol;

        if (resultColumn != null) {
            resultColExpr = resultColumn.getValueExpr();
            if (resultColExpr instanceof ValueExpressionColumn) {
                final TableExpression tableExpr = ExpressionHelper.getTableExprForValueExpressionColumn((ValueExpressionColumn) resultColExpr);
                final Column tableColumn = TableHelper.getColumnForColumnExpression(tableExpr, (ValueExpressionColumn) resultColExpr);
                if (tableColumn != null)
                    column = tableColumn;
            }
        }
    }

    /**
     * @param target - a select statement
     * @param insertValue - a select column
     */
    public BonitaSelectTableElement(SQLDomainModel domainModel, Object target, OrderByValueExpression orderCol) {
        this.target = target;
        this.statement = (QueryStatement) target;
        this.domainModel = domainModel;
        selectStatement = (QuerySelectStatement) target;
        orderColumn = orderCol;

        if (orderColumn != null) {
            resultColExpr = orderColumn.getValueExpr();
            if (resultColExpr instanceof ValueExpressionColumn) {
                final TableExpression tableExpr = ExpressionHelper.getTableExprForValueExpressionColumn((ValueExpressionColumn) resultColExpr);
                final Column tableColumn = TableHelper.getColumnForColumnExpression(tableExpr, (ValueExpressionColumn) resultColExpr);
                if (tableColumn != null) {
                    column = tableColumn;
                }
            }
        }
    }

    public Object getColumn() {
        return column;
    }

    public Object getTarget() {
        return target;
    }

    /**
     * Get the QuerySelectStatement that contains this table element
     */
    public SQLQueryObject getSelectStatement() {
        return selectStatement;
    }

    /**
     * Get the QueryValueExpression that corresponds to this table element
     */
    public QueryValueExpression getSQLExpression() {
        QueryValueExpression retVal = null;
        if (resultColumn != null)
            retVal = resultColumn.getValueExpr();
        else if (orderColumn != null)
            retVal = orderColumn.getValueExpr();
        return retVal;
    }

    /**
     * Launch the expression builder
     */
    public QueryValueExpression showExpressionBuilder(Object key, boolean isColumn, String sProperty,Object oldValue) {
    	return null;
    }

    /**
     * Set the model object from the grid value
     */
    public void modify(Object key, Object propValue,Object oldValue) {
        boolean changed = false;

        if (key == SQLBuilderConstants.P_STATEMENT_COLUMN) {
            column = propValue;

            if (propValue instanceof String
                    && (((String) propValue).equals(SQLBuilderConstants.P_BUILD_EXPRESSION)
                            || ((String) propValue).equals(SQLBuilderConstants.P_EDIT_EXPRESSION) || ((String) propValue)
                            .equals(SQLBuilderConstants.P_REPLACE_EXPRESSION))) {
                final QueryValueExpression expr = showExpressionBuilder(key, false, (String) propValue,oldValue);
                if (expr != null) {
                    selectedColumn = expr;
                }
            }
            else if (propValue instanceof String && ((String) propValue).trim().equals("")) { //$NON-NLS-1$
                selectedColumn = ""; //$NON-NLS-1$
            }
            else {
                selectedColumn = propValue;
            }

            if (resultColumn == null) {
                changed = createSelectDetail();
            }
            else {
                changed = updateColumn();
            }
        }
        else if (key == SQLBuilderConstants.P_STATEMENT_ALIAS) {
            changed = updateAlias((String) propValue);
        }
        else if (key == SQLBuilderConstants.P_STATEMENT_OUTPUT) //checkbox
        {
            changed = updateResultColumn((Boolean) propValue);
        }
        else if (key == SQLBuilderConstants.P_STATEMENT_SORTTYPE) {
            changed = updateSortType((String) propValue);
        }
        else if (key == SQLBuilderConstants.P_STATEMENT_SORTORDER) {
            changed = updateSortOrder((String) propValue);
        }

        //qmp-nb to trigger UI refresh
        if (changed) {
            SelectHelper.refresh(selectStatement);
        }
    }

    /**
     * Get the mof value and return it
     */
    public String getColumnText(int columnIndex) {
        if (columnIndex == 0) {
            final String result = SQLStringHelper.trimBlanks(getColumnName());
            return result;
        }
        else if (columnIndex == 1 && resultColumn != null) // alias
        {
            if (resultColumn.getName() != null)
                return resultColumn.getName();
        }
        else if (columnIndex == 2) // Output
        {
            if (resultColumn != null) {
                if (SelectHelper.isResultColumn(selectStatement, resultColumn)) {
                    return "true"; //$NON-NLS-1$
                }
                return "false"; //$NON-NLS-1$
            }
            else if (orderColumn != null) {
                if (SelectHelper.getOrderByColIndexFromValueExpr(selectStatement, orderColumn.getValueExpr()) >= 0) {
                    return "false"; //$NON-NLS-1$
                }
                return "true"; //$NON-NLS-1$
            }
            return "true"; //$NON-NLS-1$
        }
        else if (columnIndex == 3) // Sort type
        {
            if ((resultColumn == null && orderColumn == null) || selectStatement == null) {
                return ""; //$NON-NLS-1$
            }
            return getSortType();
        }
        else if (columnIndex == 4) // Sort order
        {
            if (selectStatement != null) {
                return getSortOrder();
            }
            return ""; //$NON-NLS-1$
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * Create a new QueryValueExpression object for the result column
     */
    private boolean createSelectDetail() {
        boolean created = false;
        if (resultColumn == null && selectedColumn != null) {
            if (selectedColumn instanceof ValueExpressionColumn) {
                final ValueExpressionColumn currColExpr = (ValueExpressionColumn) selectedColumn;
                ValueExpressionColumn newColExpr;
                newColExpr = ExpressionHelper.createValueExpressionColumn(currColExpr);
                if (this.qSelect != null) {
                    resultColumn = SelectHelper.appendResultColumn(qSelect, newColExpr, alias);
                }
                else {
                    resultColumn = SelectHelper.appendResultColumn(this.selectStatement, newColExpr, alias);
                }
            }
            else {
                if (this.qSelect != null) {
                    resultColumn = SelectHelper.appendResultColumn(qSelect, (QueryValueExpression) selectedColumn, alias);
                }
                else {
                    resultColumn = SelectHelper.appendResultColumn(this.selectStatement, (QueryValueExpression) selectedColumn, alias);
                }
            }
            created = true;
        }
        return created;
    }

    /**
     * Replace the QueryValueExpression object with the new choice
     */
    private boolean updateColumn() {
        boolean retVal = false;
        if (selectedColumn instanceof QueryValueExpression) {
        	QueryValueExpression valExpr = (QueryValueExpression) selectedColumn;
        	// If column, new value needs to be created, so existing value is not removed
        	// from column combobox.
        	if (selectedColumn instanceof ValueExpressionColumn) {
        		valExpr = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn)valExpr);
        	}
        	retVal = SelectHelper.replaceColumnValueExpr(selectStatement, resultColumn,  valExpr);
        	if (selectStatement instanceof QuerySelectStatement) {
        		SelectHelper.replaceColumnValueExprForOrderBy((QuerySelectStatement) selectStatement, resultColumn, valExpr);
        	}
        }
        else {
            if (selectedColumn != null && selectedColumn instanceof String && ((String) selectedColumn).equals("")) { //$NON-NLS-1$
                // just remove this expression
                SelectHelper.removeColumnFromResultColumns(selectStatement, getSQLExpression());
                SelectHelper.removeColumnFromOrderBy(selectStatement, getSQLExpression());
                retVal = true;
            }
        }
        return retVal;
    }

    /**
     * Update the alias (e.g. Select Dept As "D")
     */
    private boolean updateAlias(String value) {
        if (resultColumn == null) {
            // Only update the alias if the column is already created
            return false;
        }
        SelectHelper.setResultColumnAlias(resultColumn, value);
        return true;
    }

    /**
     * Indicate if this column is to be included in the select clause
     */
    private boolean updateResultColumn(Boolean value) {
        if (resultColumn == null && orderColumn == null) {
            return false;
        }
        if (value.booleanValue()) {
            if (orderColumn != null) {
                resultColumn = SelectHelper.appendResultColumn(selectStatement, orderColumn.getValueExpr(), alias);
                orderColumn = null;
            }
            if (selectStatement instanceof QuerySelectStatement) {
                if (sortType.trim().length() > 0) {
                    SelectHelper.appendOrderByColumn((QuerySelectStatement) selectStatement, resultColumn, alias, sortType);
                }
            }
        }
        else {
            updateAlias(""); //$NON-NLS-1$
            SelectHelper.removeColumnFromResultColumns(selectStatement, getSQLExpression());

            if (selectStatement instanceof QuerySelectStatement) {
                if (sortType.trim().length() > 0) {
                    orderColumn = SelectHelper.appendOrderByColumn((QuerySelectStatement) selectStatement, getSQLExpression(), sortType);
                }
                else {
                    orderColumn = SelectHelper.appendOrderByColumn((QuerySelectStatement) selectStatement, getSQLExpression(), "ASC"); //$NON-NLS-1$
                }
            }
            resultColumn = null;
        }
        return true;
    }

    /**
     * Update the sort type - ascending, descending or none
     */
    private boolean updateSortType(String value) {
        if ((resultColumn == null && orderColumn == null) || selectStatement instanceof QuerySelect) {
            return false;
        }
        sortType = ""; //$NON-NLS-1$
        if (value.equalsIgnoreCase(BonitaSelectGridViewer.P_ASCENDING)) {
            sortType = "ASC"; //$NON-NLS-1$
        }
        else if (value.equalsIgnoreCase(BonitaSelectGridViewer.P_DESCENDING)) {
            sortType = "DESC"; //$NON-NLS-1$
        }
        if (sortType.length() > 0) {
            if (resultColumn != null) {
                SelectHelper.appendOrderByColumn((QuerySelectStatement) selectStatement, resultColumn, alias, sortType);
            }
            else {
                SelectHelper.appendOrderByColumn((QuerySelectStatement) selectStatement, getSQLExpression(), sortType);
            }
        }
        else {
            SelectHelper.removeColumnFromOrderBy(selectStatement, getSQLExpression());
        }
        return true;
    }

    // UI shows position starting from 1. Model uses position starting from 0.
    private boolean updateSortOrder(String value) {
        if ((resultColumn == null && orderColumn == null) || selectStatement instanceof QuerySelect || selectStatement == null) {
            return false;
        }

        if (((QuerySelectStatement) selectStatement).getOrderByClause() != null) {
        	final int position = Integer.parseInt(value) - 1;
        	final boolean moved =  SelectHelper.repositionColumnInOrderBy(selectStatement, getSQLExpression(), position);       
        	if (!moved) {
        		// Add new orderby specification since it doesn't already exist
        		OrderBySpecification orderBy;
        		 if (resultColumn != null) {
                 	 orderBy = SelectHelper.appendOrderByColumn((QuerySelectStatement)selectStatement,  resultColumn, null, "ASC");
                 } else {
                	 orderBy = SelectHelper.appendOrderByColumn((QuerySelectStatement)selectStatement, getSQLExpression(), "ASC");	 
                 }
        		 SelectHelper.moveOrderByToPosition(orderBy, ((QuerySelectStatement) selectStatement).getOrderByClause(), position);
        		 
        	}
        	return true;
        } // end of if ()
        else
        	return false;
    }

    /**
     * Get the column name.
     * For table without alias, it will be tableName.columnName
     * For table with alias, it will be tableAliasName.columnName
     */
    String getColumnName() {
        String retVal = ""; //$NON-NLS-1$
        if (resultColumn == null && orderColumn == null) {
            retVal = ""; //$NON-NLS-1$
        }
        else {
            final QueryValueExpression valExpr = getSQLExpression();
            if (valExpr != null) {
                if (valExpr instanceof ValueExpressionColumn) {
                    retVal = TableHelper.getExposedTableName(ExpressionHelper.getTableExprForValueExpressionColumn((ValueExpressionColumn) valExpr)) + "." //$NON-NLS-1$
                            + valExpr.getName();
                    final String sqlVal = valExpr.getSQL();
                    if (sqlVal.length() > retVal.length()) {
                        retVal = sqlVal;
                    }
                }
                else {
                    retVal = valExpr.getSQL();
                }
            }
        }
        return retVal;
    }

    String getSortType() {
        String result = ""; //$NON-NLS-1$
        if (selectStatement instanceof QuerySelectStatement) {
            final List orderByClause = ((QuerySelectStatement) selectStatement).getOrderByClause();
            if (orderByClause != null && orderByClause.size() > 0) {
                final int currIdx = SelectHelper.getOrderByColIndexFromValueExpr(selectStatement, getSQLExpression());
                if (currIdx >= 0) {
                    final OrderBySpecification obSpec = (OrderBySpecification) orderByClause.get(currIdx);
                    // use the OrderingSpecType instead of isDescending to determine sort type
                    if (obSpec.getOrderingSpecOption().getValue() == OrderingSpecType.DESC) {
                    	result = Messages._UI_COMBO_SORT_DESCENDING;
                    }
                    else {
                        result = Messages._UI_COMBO_SORT_ASCENDING;
                    }
                }
            }
        }
        return result;
    }

    public String getSortOrder() {
        sortOrder = ""; //$NON-NLS-1$
        if (selectStatement instanceof QuerySelectStatement) {
            final List orderByClause = ((QuerySelectStatement) selectStatement).getOrderByClause();
            if (orderByClause != null && orderByClause.size() > 0 && (resultColumn != null || orderColumn != null)) {
                final int location = SelectHelper.getOrderByColIndexFromValueExpr(selectStatement, getSQLExpression());
                if (location != -1)
                    sortOrder = Integer.valueOf(location + 1).toString();
            }
        }
        return sortOrder;
    }
    
    public boolean hasColumn(){
        if (resultColumn != null || orderColumn != null || resultColExpr != null){
        	return true;
        }
        else {
        	return false;
        }
    }
}

