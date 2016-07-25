/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard.page.sqlutil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.AbstractTable;
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;
import org.bonitasoft.studio.model.form.SingleValuatedFormField;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 *
 */
public class SQLQueryUtil {

	private static final String FROM_CLAUSE = " from ";
	private static final String SELECT_QUERY_PREFIX = "select ";

	public static boolean isGraphicalModeSupportedFor(Expression sqlQuery){
		String query = sqlQuery.getContent();
		String expressionType = sqlQuery.getType();
		if(ExpressionConstants.CONSTANT_TYPE.equals(expressionType) ){
			if(query != null && !query.isEmpty()){
				if(query.trim().toLowerCase().startsWith(SELECT_QUERY_PREFIX)){
					return true;
				}
			}
		}else if(ExpressionConstants.PATTERN_TYPE.equals(expressionType)){
			if(query != null && !query.isEmpty()){
				if(query.trim().toLowerCase().startsWith(SELECT_QUERY_PREFIX)){
					int indexOfFromClause = query.toLowerCase().indexOf(FROM_CLAUSE);
					if(indexOfFromClause != -1){
						String columns = query.trim().toLowerCase().substring(SELECT_QUERY_PREFIX.length(),indexOfFromClause);
						if(!columns.contains("${")){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean useWildcard(Expression sqlQuery) {
		return getSelectedColumns(sqlQuery).contains("*");
	}

	public static String getSelectedColumn(Expression sqlQuery) {
		String query = sqlQuery.getContent();
		if(query != null && !query.isEmpty()){
			if(query.trim().toLowerCase().startsWith(SELECT_QUERY_PREFIX)){
				int indexOfFromClause = query.toLowerCase().indexOf(FROM_CLAUSE);
				if(indexOfFromClause != -1){
					String columns = query.trim().toLowerCase().substring(SELECT_QUERY_PREFIX.length(),indexOfFromClause);
					return columns.trim();
				}
			}
		}
		return null;
	}

	public static List<String> getSelectedColumns(Expression sqlQuery) {
		String columns = getSelectedColumn(sqlQuery);
		final List<String> result = new ArrayList<String>();
		if(columns != null){
			if(columns.indexOf(",") != -1){
				String[] cols = columns.split(",");
				for(String col : cols){
					result.add(col.trim());
				}
			}else{
				result.add(columns);
			}
		}
		return result;
	}

	public static boolean isGraphicalModeSupportedFor(
			Expression scriptExpression, EObject container) {
		if(isSingleOutput(container)){
			if(isGraphicalModeSupportedFor(scriptExpression)){
				return getSelectedColumns(scriptExpression).size() == 1 && !useWildcard(scriptExpression);
			}
		}else if(isListOutput(container)){
			if(isGraphicalModeSupportedFor(scriptExpression)){
				return !useWildcard(scriptExpression);
			}
		}else if(isTableOutput(container)){
			return isGraphicalModeSupportedFor(scriptExpression);
		}
		return false;
	}
	
	public static boolean isTableOutput(EObject container) {
		Widget widget =  ModelHelper.getParentWidget(container);
		if(container instanceof Expression){
			String returnType = ((Expression) container).getReturnType();
			if(returnType != null){
				try{
					Class<?> returnTypeClass = Class.forName(returnType);
					return Collection.class.isAssignableFrom(returnTypeClass) && (widget instanceof AbstractTable);
				}catch(Exception e){
					
				}
			}
		}
	
		return  widget instanceof MultipleValuatedFormField && (widget instanceof AbstractTable);
	}
	
	public static boolean isListOutput(EObject container) {
		Widget widget =  ModelHelper.getParentWidget(container);
		if(container instanceof Expression){
			String returnType = ((Expression) container).getReturnType();
			if(returnType != null){
				try{
					Class<?> returnTypeClass = Class.forName(returnType);
					return Collection.class.isAssignableFrom(returnTypeClass) && !(widget instanceof AbstractTable);
				}catch(Exception e){
					
				}
			}
		}
	
		return  widget instanceof MultipleValuatedFormField && !(widget instanceof AbstractTable);
	}

	public static boolean isSingleOutput(EObject container) {
		if(container instanceof Expression){
			String returnType = ((Expression) container).getReturnType();
			if(returnType != null){
				if (returnType.equals(String.class.getName())
						  || returnType.equals(Byte.class.getName())
				          || returnType.equals(Short.class.getName())
				          || returnType.equals(Integer.class.getName())
				          || returnType.equals(Long.class.getName())
				          || returnType.equals(Float.class.getName())
				          || returnType.equals(Double.class.getName())
				          || returnType.equals(Boolean.class.getName())
				          || returnType.equals(Character.class.getName())){
					return true;
				}
			}
		}
		Widget widget = ModelHelper.getParentWidget(container);
		return widget instanceof SingleValuatedFormField;
	}


}
