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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 * 
 */
public class AbstractBusinessObjectWizardTest {

    private AbstractBusinessObjectWizard abstractWizardUnderTest;

    private Pool process;

    private Data processData;

    private Task task1;

    private Data t1Data;

    private Task task2;

    private Data t2Data;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        ProcessFactory processFactory = ProcessFactory.eINSTANCE;
        process = processFactory.createPool();
        processData = processFactory.createData();
        processData.setName("processData");
        process.getData().add(processData);
        task1 = processFactory.createTask();
        t1Data = processFactory.createData();
        t1Data.setName("t1Data");
        task1.getData().add(t1Data);

        task2 = processFactory.createTask();
        t2Data = processFactory.createData();
        t2Data.setName("t2Data");
        task2.getData().add(t2Data);
        process.getElements().add(task1);
        process.getElements().add(task2);

        abstractWizardUnderTest = mock(AbstractBusinessObjectWizard.class);
        when(abstractWizardUnderTest.getAllAccessibleData(any(Element.class))).thenCallRealMethod();
        when(abstractWizardUnderTest.computeExistingNames(any(DataAware.class))).thenCallRealMethod();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldGetAllAccessibleData_ReturnEmptyListWhenNoDataAccessible() throws Exception {
        Element container = ProcessFactory.eINSTANCE.createPool();
        assertThat(abstractWizardUnderTest.getAllAccessibleData(container)).isNotNull().isEmpty();
    }

    @Test
    public void shouldGetAccessibleData_ReturnEmptyListWhenContainerIsNull() throws Exception {
        assertThat(abstractWizardUnderTest.getAllAccessibleData(null)).isNotNull().isEmpty();
    }

    @Test
    public void shouldGetAllAccessibleData_ForProcessReturnAllDataAboveProcessAndProcessData() throws Exception {
        assertThat(abstractWizardUnderTest.getAllAccessibleData(process)).isNotNull().containsOnly(processData, t1Data,
                t2Data);
    }

    @Test
    public void shouldGetAllAccessibleData_ForTask1ReturnProcessDataT1DataAndPageFlowData() throws Exception {
        assertThat(abstractWizardUnderTest.getAllAccessibleData(task1)).isNotNull().containsOnly(processData, t1Data);
    }


    @Test
    public void shouldComputeExistingNamesOnProcess_ReturnAllProcessData() throws Exception {
        assertThat(abstractWizardUnderTest.computeExistingNames(process)).isNotNull().containsOnly("processData", "t1Data",
                "t2Data");
    }

    @Test
    public void shouldComputeExistingNamesOnTask1_ReturnAllProcessDataExceptT2Data() throws Exception {
        assertThat(abstractWizardUnderTest.computeExistingNames(task1)).isNotNull().containsOnly("processData", "t1Data");
    }

}
