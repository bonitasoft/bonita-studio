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
package org.bonitasoft.studio.engine.export;

import static org.fest.assertions.Assertions.assertThat;

import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 *
 */
public class EngineExpressionUtilTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void shouldCreateVariableExpression_SetVariableExpressionType() throws Exception {
		Data textData = ProcessFactory.eINSTANCE.createData();
		textData.setDataType(ProcessFactory.eINSTANCE.createStringType());
		textData.setName("textData");
		assertThat(EngineExpressionUtil.createVariableExpression(textData).getExpressionType()).isEqualTo(ExpressionType.TYPE_VARIABLE.name());
	}
	
	@Test
	public void shouldCreateVariableExpression_SetBusinessObjectExpressionType() throws Exception {
		BusinessObjectData businessObjectData = ProcessFactory.eINSTANCE.createBusinessObjectData();
		businessObjectData.setDataType(ProcessFactory.eINSTANCE.createBusinessObjectType());
		businessObjectData.setName("leaveRequest");
		businessObjectData.setClassName("org.bonita.business.LeaveRequest");
		Expression createVariableExpression = EngineExpressionUtil.createVariableExpression(businessObjectData);
		assertThat(createVariableExpression.getExpressionType()).isEqualTo("TYPE_BUSINESS_DATA");
		assertThat(createVariableExpression.getReturnType()).isEqualTo("org.bonita.business.LeaveRequest");
	}
}
