/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.wizard.sforce.querys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxence Raoux
 * 
 */
public class QueryHelper {

	public static final String[] operands = { "=", "!=", "<", "<=", ">", ">=",
			"LIKE", "IN", "NOT IN" };
	public static final String[] boolOperands = { "AND", "OR" };

	private String from;
	private List<String> fields;
	private List<QueryCondition> conditions;

	public QueryHelper() {
		from = "";
		fields = new ArrayList<String>();
		conditions = new ArrayList<QueryCondition>();
	}

	public String generateQuery() {
		StringBuilder query = new StringBuilder();
		if (!fields.isEmpty() && !from.isEmpty()) {
			query.append("SELECT");
			for (String field : fields) {
				query.append(" " + field);
				if (fields.indexOf(field) != fields.size() - 1) {
					query.append(",");
				}
			}
			query.append(" FROM " + from);
			if (!conditions.isEmpty()) {
				query.append(" WHERE");
				for (QueryCondition condition : conditions) {
					query.append(" " + condition.getResultString());
				}
			}
		}
		return query.toString();
	}

	public void setFrom(String from) {
		this.from = from;
		fields = new ArrayList<String>();
		conditions = new ArrayList<QueryCondition>();
	}

	public void addField(String field) {
		if (!field.isEmpty() && !fields.contains(field)) {
			fields.add(field);
		}
	}

	public void removeField(String field) {
		if (fields.contains(field)) {
			fields.remove(field);
		}
	}

	public void addCondition(QueryCondition condition) {
		if (condition != null) {
			conditions.add(condition);
		}
	}

	public void clean() {
		from = "";
		fields = new ArrayList<String>();
		conditions = new ArrayList<QueryCondition>();
	}

}
