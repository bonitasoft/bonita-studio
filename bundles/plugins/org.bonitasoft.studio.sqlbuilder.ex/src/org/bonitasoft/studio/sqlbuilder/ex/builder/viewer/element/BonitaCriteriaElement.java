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

import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaGridViewer;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaCriteriaElement extends CriteriaElement {

    public BonitaCriteriaElement(SQLDomainModel domainModel, Object target, Object clause, Predicate predicate, boolean isHaving, boolean firstClause) {
		super(domainModel, target, clause, predicate, isHaving, firstClause);
	}

	@Override
    public String getOperator() {
		return super.getOperator();
	}

	@Override
	public QueryValueExpression showExpressionBuilder(boolean isColumn,String sProperty) {
		return null;
	}

	@Override
	public QueryValueExpression showExpressionBuilder(Object key,
			boolean isColumn, String sProperty) {
		return null;
	}

	public QueryValueExpression showExpressionBuilder(Object key,boolean isColumn, String sProperty, Object oldValue) {
		return null;
	}

	@Override
    public QueryValueExpression getColumn(){
		return super.getColumn() ;
	}

	public Object getObjectValue(){
		return value;

	}

	public void setElementProperty(Object key, Object propValue,Object oldValue) {
		if (propValue == null) {
			return;
		}

		boolean changed = false;

		if (key == CriteriaGridViewer.P_STATEMENT_COLUMN) {
			if (propValue instanceof String
					&& (((String) propValue).equals(SQLBuilderConstants.P_BUILD_EXPRESSION)
							|| ((String) propValue).equals(SQLBuilderConstants.P_EDIT_EXPRESSION) || ((String) propValue)
							.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION))) {
				final QueryValueExpression expr = showExpressionBuilder(key, false, (String) propValue,oldValue);
				if (expr != null) {
					column = expr;
				}
			}
			else if (propValue instanceof String && ((String) propValue).trim().equals("")) {
				column = null;
			}
			else {
				if (propValue instanceof QueryValueExpression) {
					if (propValue instanceof ValueExpressionColumn) {
						column = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn) propValue);
					}
					else {
						column = (QueryValueExpression) propValue;
					}
				}
			}

			/*            if (searchPred == null) {
	                changed = createSearchCondition();
	            }
	            else {
	                changed = updateColumn();
	            }
			 */          if(column != null) {
				 changed = updateColumn();
			 }
		}
		else if (key == CriteriaGridViewer.P_STATEMENT_OPERATOR) {
			// Update the operator only if there is a search condition
			//  created by selecting a column in the first row of the grid
			if (searchCon != null) {
				if (propValue instanceof String) {
					operator = (String) propValue;
					/*
					 * if (searchPred == null) { changed =
					 * createSearchCondition(); } else { changed =
					cascadeuser
					 * updateOperator((String) propValue); }
					 */
					changed = updateOperator((String) propValue);
				}
			}
			// if operator is EXISTS then we need to create a search condition
			// because it does not require a left predicate
			else if (propValue instanceof String && 
					(SQLBuilderConstants.P_OPERATOR_EXISTS.equals(propValue) ||
							SQLBuilderConstants.P_OPERATOR_XMLEXISTS.equals(propValue))) {        		
				createSearchCondition();       	
				changed = getSearchConditionHelper().setOperatorInPredicate(statement, StatementHelper.getSearchCondition(statement),
						searchPred, (String) propValue);        		       		
			}
		}
		else if (key == CriteriaGridViewer.P_STATEMENT_VALUE) {
			// Update the value only if there is a search condition
			//  created by selecting a column in the first row of the grid
			if(searchCon != null){
				if (propValue instanceof String
						&& (((String) propValue)
								.equals(SQLBuilderConstants.P_BUILD_EXPRESSION)
								|| ((String) propValue)
								.equals(SQLBuilderConstants.P_EDIT_EXPRESSION) || ((String) propValue)
								.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION))) {
					final QueryValueExpression expr = showExpressionBuilder(key,
							false, (String) propValue,oldValue);
					if (expr != null) {
						value = expr.getSQL(); // value should contain string
					}
				} else if (propValue instanceof String
						&& ((String) propValue).trim().equals("")) {
					value = null;
				} else {
					if (propValue instanceof QueryValueExpression) {
						value = ((QueryValueExpression) propValue).getSQL(); // value
						// should
						// contain
						// string
					}
				}

				/*
				 * if (searchPred == null) { changed = createSearchCondition(); }
				 * else { changed = updateValue(); }
				 */
				if (searchPred instanceof XMLPredicateExists)
					value = " (" + value + " )";
				changed = updateValue();
			}
		}
		else if (key == CriteriaGridViewer.P_STATEMENT_ANDOR) {
			if (propValue instanceof String) {
				if (searchCon != null) {
					if (!((String) propValue).equals("")) {
						andOr = (String) propValue;
						changed = setAndOr((String) propValue);
					} 
					else // removing AND/OR
					{

						final Vector criteriaElements = (Vector) this
						.getCriteriaElementVectorRef().get();
						final int currIdx = criteriaElements.indexOf(this);
						BonitaCriteriaElement tempCriteriaElement = null;
						if (criteriaElements.size() > (currIdx + 1)) {
							tempCriteriaElement = (BonitaCriteriaElement) criteriaElements
							.get(currIdx + 1);
						}
						if (tempCriteriaElement != null) {
							if ((tempCriteriaElement.getColumn() == null || tempCriteriaElement
									.getColumn().getSQL().equalsIgnoreCase(
									"NULL"))
									&& (tempCriteriaElement.getValue().equals(
									"") || tempCriteriaElement
									.getValue()
									.equalsIgnoreCase("NULL"))
									&& (tempCriteriaElement.getOperator()
											.equals("") || tempCriteriaElement
											.getOperator().equals("="))) {

								final Predicate nextPred = tempCriteriaElement
								.getCurrentPredicate();
								getSearchConditionHelper().removePredicateFromCondition(
										nextPred, searchCon, statement);
								changed = true;
							}
						}
					}
				}
			}
		}
		//TODO: This is a hack to get notifications to work.  Need to figure out how to remove.
		if (changed) {            
			if (statement instanceof QueryUpdateStatement) {
				((QueryUpdateStatement) statement).setWhereClause(((QueryUpdateStatement) statement).getWhereClause());
			}
			if (statement instanceof QueryDeleteStatement) {
				((QueryDeleteStatement) statement).setWhereClause(((QueryDeleteStatement) statement).getWhereClause());
			}
			if (statement instanceof QuerySelectStatement) {
				((QuerySelectStatement) statement).setQueryExpr(((QuerySelectStatement) statement).getQueryExpr());
			}
			else if (statement instanceof QuerySelect) {
				SelectHelper.refresh(statement);
			}            
		}
	}

	@Override
	  protected boolean updateValue() {        
	        boolean updated = true;
	        String newRight = "";
	        QueryValueExpression newLeft = null;
	        Predicate newPredicate = null;
	        if (value != null && !value.equals("") ) {
	            newPredicate = getSearchConditionHelper().buildNewPredicate(statement, column, value.toString(), operator);
	            if (newPredicate == null) {
	                newLeft = getSearchConditionHelper().getDefaultLeft(operator);
	                newPredicate = getSearchConditionHelper().buildNewPredicate(statement, newLeft, value.toString(), operator);
	            }
	        }
	        if (newPredicate == null) {
	            newRight = getSearchConditionHelper().getDefaultRight(operator);
	            newPredicate = getSearchConditionHelper().buildNewPredicate(statement, column, newRight, operator);
	        }
	        if (newPredicate == null) {
	            newPredicate = getSearchConditionHelper().buildNewPredicate(statement, newLeft, newRight, operator);
	        }
	        if (newPredicate != null) {
	        	getSearchConditionHelper().replacePredicate(statement, searchCon, searchPred, newPredicate);
	            searchPred = newPredicate;
	            updated = true;
	        }
	        else {
	            updated = false;
	        }
	        return updated;
	    }
}
