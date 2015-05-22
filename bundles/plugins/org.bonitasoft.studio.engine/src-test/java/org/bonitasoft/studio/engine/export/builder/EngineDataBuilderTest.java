/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.engine.export.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;

import java.util.List;

import org.bonitasoft.engine.bpm.businessdata.BusinessDataDefinition;
import org.bonitasoft.engine.bpm.flownode.FlowElementContainerDefinition;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.engine.export.builder.EngineDataBuilder;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.junit.Before;
import org.junit.Test;

public class EngineDataBuilderTest {

    private ProcessDefinitionBuilder builder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        builder = new ProcessDefinitionBuilder().createNewInstance("p1", "1.0");

    }

    /**
     * Test method for
     * {@link org.bonitasoft.studio.engine.ex.exporter.switcher.DataSwitchEx#caseBusinessObjectData(org.bonitasoft.studio.model.process.BusinessObjectData)}.
     */
    @Test
    public void shouldCaseBusinessObjecType_addBusinessObjectDataInProcessDefinition() throws Exception {
        final BusinessObjectData data = aBusinessData()
                .withName("myLeaveRequest")
                .withClassname("org.bonitasoft.hr.LeaveRequest")
                .withDocumentation("Some doc")
                .havingDefaultValue(
                        aGroovyScriptExpression().withName("init").withContent("new LeaveRequest()").withReturnType("org.bonitasoft.hr.LeaveRequest")).build();
        final EngineDataBuilder switchUnderTest = new EngineDataBuilder(data, builder);

        switchUnderTest.doSwitch(data.getDataType());

        final FlowElementContainerDefinition flowElementContainerDefinition = builder.getProcess().getFlowElementContainer();
        final List<BusinessDataDefinition> businessDataDefinitions = flowElementContainerDefinition.getBusinessDataDefinitions();
        assertThat(businessDataDefinitions).hasSize(1);
        assertThat(businessDataDefinitions).extracting("name", "className", "description", "multiple").contains(
                tuple("myLeaveRequest", "org.bonitasoft.hr.LeaveRequest", "Some doc", false));
        assertThat(businessDataDefinitions.get(0).getDefaultValueExpression())
                .isEqualToIgnoringGivenFields(EngineExpressionUtil.createExpression(data.getDefaultValue()), "id");
    }

    @Test
    public void shouldCaseBusinessObjecType_addMultipleBusinessObjectDataInProcessDefinition() throws Exception {
        final BusinessObjectData data = aBusinessData().withName("myLeaveRequest").withClassname("org.bonitasoft.hr.LeaveRequest").multiple().build();
        final EngineDataBuilder switchUnderTest = new EngineDataBuilder(data, builder);

        switchUnderTest.doSwitch(data.getDataType());

        final List<BusinessDataDefinition> businessDataDefinitions = builder.getProcess().getFlowElementContainer()
                .getBusinessDataDefinitions();
        assertThat(businessDataDefinitions).hasSize(1);
        assertThat(businessDataDefinitions).extracting("multiple").contains(true);
    }

}
