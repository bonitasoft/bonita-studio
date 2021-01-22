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

/**
 * @author Maxence Raoux
 * 
 */
public class QueryCondition {

	private String boolOperand;
	private String field1;
	private String operands;
	private String field2;

	public QueryCondition(String boolOperand, String field1, String operands,
			String field2) {
		super();
		this.boolOperand = boolOperand;
		this.field1 = field1;
		this.operands = operands;
		this.field2 = field2;
	}

	public QueryCondition(String field1, String operands, String field2) {
		super();
		boolOperand = null;
		this.field1 = field1;
		this.operands = operands;
		this.field2 = field2;
	}

	public String getResultString() {
		String conditionS = "";
		if (boolOperand != null)
			conditionS += boolOperand;
		conditionS += " " + field1;
		conditionS += " " + operands;
		conditionS += " " + field2;
		return conditionS;
	}
}
