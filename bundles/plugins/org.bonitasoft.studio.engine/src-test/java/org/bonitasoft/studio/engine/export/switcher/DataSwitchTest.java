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
package org.bonitasoft.studio.engine.export.switcher;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.bonitasoft.engine.bpm.businessdata.BusinessDataDefinition;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class DataSwitchTest {

    private DataSwitch switchUnderTest;
    private BusinessObjectData data;
    private ProcessDefinitionBuilder designProcessDefinitionBuilderEx;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        designProcessDefinitionBuilderEx = new ProcessDefinitionBuilder().createNewInstance("p1", "1.0");
        data = ProcessFactory.eINSTANCE.createBusinessObjectData();
        data.setName("myLeaveRequest");
        data.setClassName("org.bonitasoft.hr.LeaveRequest");
        data.setDataType(ProcessFactory.eINSTANCE.createBusinessObjectType());
        data.setDocumentation("Some doc");
        data.setMultiple(false);
        switchUnderTest = new DataSwitch(data, null, designProcessDefinitionBuilderEx);

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link org.bonitasoft.studio.engine.ex.exporter.switcher.DataSwitchEx#caseBusinessObjectData(org.bonitasoft.studio.model.process.BusinessObjectData)}.
     */
    @Test
    public void shouldCaseBusinessObjecType_addBusinessObjectDataInProcessDefinition() throws Exception {
        switchUnderTest.doSwitch(data.getDataType());
        final List<BusinessDataDefinition> businessDataDefinitions = designProcessDefinitionBuilderEx.getProcess().getProcessContainer()
                .getBusinessDataDefinitions();
        assertThat(businessDataDefinitions).hasSize(1).extracting("name").contains("myLeaveRequest");
        assertThat(designProcessDefinitionBuilderEx.getProcess().getProcessContainer().getBusinessDataDefinitions()).extracting("className").contains(
                "org.bonitasoft.hr.LeaveRequest");
        assertThat(designProcessDefinitionBuilderEx.getProcess().getProcessContainer().getBusinessDataDefinitions()).extracting("description").contains(
                "Some doc");
        assertThat(designProcessDefinitionBuilderEx.getProcess().getProcessContainer().getBusinessDataDefinitions()).extracting("multiple").contains(false);
    }

    @Test
    public void shouldCaseBusinessObjecType_addMultipleBusinessObjectDataInProcessDefinition() throws Exception {
        data.setMultiple(true);
        switchUnderTest.doSwitch(data.getDataType());
        final List<BusinessDataDefinition> businessDataDefinitions = designProcessDefinitionBuilderEx.getProcess().getProcessContainer()
                .getBusinessDataDefinitions();
        assertThat(businessDataDefinitions).hasSize(1);
        assertThat(designProcessDefinitionBuilderEx.getProcess().getProcessContainer().getBusinessDataDefinitions()).extracting("multiple").contains(true);
    }
}
