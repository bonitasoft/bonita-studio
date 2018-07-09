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
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;

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

	

}
