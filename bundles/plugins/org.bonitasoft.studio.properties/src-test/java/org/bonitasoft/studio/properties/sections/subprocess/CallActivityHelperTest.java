/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.subprocess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.builders.CallActivityBuilder;
import org.bonitasoft.studio.model.process.builders.ContractBuilder;
import org.bonitasoft.studio.model.process.builders.ContractInputBuilder;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.model.process.builders.InputMappingBuilder;
import org.bonitasoft.studio.model.process.builders.JavaObjectDataBuilder;
import org.bonitasoft.studio.model.process.builders.OutputMappingBuilder;
import org.bonitasoft.studio.model.process.builders.PoolBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CallActivityHelperTest {

    private CallActivityHelper callActivityHelper;
    @Mock
    private RepositoryAccessor repositoryAccessor;
    @Mock
    DiagramRepositoryStore drs;

    @Before
    public void setUp() throws Exception {
        doReturn(drs).when(repositoryAccessor).getRepositoryStore(DiagramRepositoryStore.class);
        callActivityHelper = new CallActivityHelper(repositoryAccessor);
    }

    @Test
    public void testAutoMap_ReturnEmptyWhenNoPoolConfigured() {
        final CallActivity callActivity = CallActivityBuilder
                .aCallActivity()
                .build();
        final CallActivityMapper mapper = callActivityHelper.findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(callActivity);
        assertThat(mapper.getInputMappingToCreate()).isEmpty();
        assertThat(mapper.getOutputMappingToCreate()).isEmpty();
    }

    @Test
    public void testAutoMap_basicCase_withString() {
        final Data callActivityData = DataBuilder.aData().havingDataType(ModelHelper.createStringDataType()).withName("name1").build();
        final CallActivity callActivity = CallActivityBuilder
                .aCallActivity()
                .havingCalledActivityName(ExpressionBuilder.aConstantExpression().withContent("CalledPool"))
                .havingData(callActivityData)
                .build();

        final List<AbstractProcess> pools = new ArrayList<AbstractProcess>();
        final Pool pool = PoolBuilder
                .aPool()
                .withName("CalledPool")
                .havingData(DataBuilder.aData().havingDataType(ModelHelper.createStringDataType()).withName("name1"))
                .havingContract(ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withName("name1")))
                .build();
        pools.add(pool);
        doReturn(pools).when(drs).getAllProcesses();

        final CallActivityMapper mapper = callActivityHelper.findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(callActivity);
        assertThat(mapper.getInputMappingToCreate()).containsExactly(callActivityData);
        assertThat(mapper.getOutputMappingToCreate()).containsExactly(callActivityData);
    }

    @Test
    public void testAutoMap_withAlreadyExistingInputMapping() {
        final Data callActivityData = DataBuilder.aData().havingDataType(ModelHelper.createStringDataType()).withName("name1").build();
        final InputMapping inputMapping = InputMappingBuilder
                .anInputMapping()
                .setProcessSource(ExpressionHelper.createVariableExpression(callActivityData))
                .setSubProcessTarget(InputMappingAssignationType.CONTRACT_INPUT, "name1")
                .build();
        final CallActivity callActivity = CallActivityBuilder
                .aCallActivity()
                .havingCalledActivityName(ExpressionBuilder.aConstantExpression().withContent("CalledPool"))
                .havingData(callActivityData)
                .havingInputMappings(inputMapping)
                .build();

        final List<AbstractProcess> pools = new ArrayList<AbstractProcess>();
        final Pool pool = PoolBuilder
                .aPool()
                .withName("CalledPool")
                .havingData(DataBuilder.aData().havingDataType(ModelHelper.createStringDataType()).withName("name1"))
                .build();
        pools.add(pool);
        doReturn(pools).when(drs).getAllProcesses();

        final CallActivityMapper mapper = callActivityHelper.findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(callActivity);
        assertThat(mapper.getInputMappingToCreate()).isEmpty();
        assertThat(mapper.getOutputMappingToCreate()).containsExactly(callActivityData);
    }

    @Test
    public void testAutoMap_withAlreadyExistingOutputMapping() {
        final Data callActivityData = DataBuilder.aData().havingDataType(ModelHelper.createStringDataType()).withName("name1").build();
        final OutputMapping outputMapping = OutputMappingBuilder
                .anOutputMapping()
                .setProcessTarget(callActivityData)
                .setSubProcessSource("name1")
                .build();
        final CallActivity callActivity = CallActivityBuilder
                .aCallActivity()
                .havingCalledActivityName(ExpressionBuilder.aConstantExpression().withContent("CalledPool"))
                .havingData(callActivityData)
                .havingOutputMapping(outputMapping)
                .build();

        final List<AbstractProcess> pools = new ArrayList<AbstractProcess>();
        final Pool pool = PoolBuilder
                .aPool()
                .withName("CalledPool")
                .havingData(DataBuilder.aData().havingDataType(ModelHelper.createStringDataType()).withName("name1"))
                .havingContract(ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withName("name1")))
                .build();
        pools.add(pool);
        doReturn(pools).when(drs).getAllProcesses();

        final CallActivityMapper mapper = callActivityHelper.findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(callActivity);
        assertThat(mapper.getInputMappingToCreate()).containsExactly(callActivityData);
        assertThat(mapper.getOutputMappingToCreate()).isEmpty();
    }

    @Test
    public void testAutoMap_returnEmptyWhenTypesNotMatching() {
        final Data callActivityData = DataBuilder.aData().havingDataType(ModelHelper.createIntegerDataType()).withName("name1").build();
        final CallActivity callActivity = CallActivityBuilder
                .aCallActivity()
                .havingCalledActivityName(ExpressionBuilder.aConstantExpression().withContent("CalledPool"))
                .havingData(callActivityData)
                .build();

        final List<AbstractProcess> pools = new ArrayList<AbstractProcess>();
        final Pool pool = PoolBuilder
                .aPool()
                .withName("CalledPool")
                .havingData(DataBuilder.aData().havingDataType(ModelHelper.createStringDataType()).withName("name1"))
                .havingContract(ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withName("name1")))
                .build();
        pools.add(pool);
        doReturn(pools).when(drs).getAllProcesses();

        final CallActivityMapper mapper = callActivityHelper.findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(callActivity);
        assertThat(mapper.getInputMappingToCreate()).isEmpty();
        assertThat(mapper.getOutputMappingToCreate()).isEmpty();
    }

    @Test
    public void testAutoMap_withBoolean() {
        final Data callActivityData = DataBuilder.aData().havingDataType(ModelHelper.createBooleanDataType()).withName("name1").build();
        final CallActivity callActivity = CallActivityBuilder
                .aCallActivity()
                .havingCalledActivityName(ExpressionBuilder.aConstantExpression().withContent("CalledPool"))
                .havingData(callActivityData)
                .build();

        final List<AbstractProcess> pools = new ArrayList<AbstractProcess>();
        final Pool pool = PoolBuilder
                .aPool()
                .withName("CalledPool")
                .havingData(DataBuilder.aData().havingDataType(ModelHelper.createBooleanDataType()).withName("name1"))
                .havingContract(
                        ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withType(ContractInputType.BOOLEAN).withName("name1")))
                .build();
        pools.add(pool);
        doReturn(pools).when(drs).getAllProcesses();

        final CallActivityMapper mapper = callActivityHelper.findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(callActivity);
        assertThat(mapper.getInputMappingToCreate()).containsExactly(callActivityData);
        assertThat(mapper.getOutputMappingToCreate()).containsExactly(callActivityData);
    }

    @Test
    public void testAutoMap_withMultiple() {
        final Data callActivityData = DataBuilder.aData().havingDataType(ModelHelper.createBooleanDataType()).withName("name1").multiple().build();
        final CallActivity callActivity = CallActivityBuilder
                .aCallActivity()
                .havingCalledActivityName(ExpressionBuilder.aConstantExpression().withContent("CalledPool"))
                .havingData(callActivityData)
                .build();

        final List<AbstractProcess> pools = new ArrayList<AbstractProcess>();
        final Pool pool = PoolBuilder
                .aPool()
                .withName("CalledPool")
                .havingData(DataBuilder.aData().multiple().havingDataType(ModelHelper.createBooleanDataType()).withName("name1"))
                .havingContract(
                        ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().multiple().withName("name1")))
                .build();
        pools.add(pool);
        doReturn(pools).when(drs).getAllProcesses();

        final CallActivityMapper mapper = callActivityHelper.findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(callActivity);
        assertThat(mapper.getInputMappingToCreate()).containsExactly(callActivityData);
        assertThat(mapper.getOutputMappingToCreate()).containsExactly(callActivityData);
    }

    @Test
    public void testAutoMap_withMultipleAndSimple() {
        final Data callActivityData = DataBuilder.aData().havingDataType(ModelHelper.createBooleanDataType()).withName("name1").multiple().build();
        final CallActivity callActivity = CallActivityBuilder
                .aCallActivity()
                .havingCalledActivityName(ExpressionBuilder.aConstantExpression().withContent("CalledPool"))
                .havingData(callActivityData)
                .build();

        final List<AbstractProcess> pools = new ArrayList<AbstractProcess>();
        final Pool pool = PoolBuilder
                .aPool()
                .withName("CalledPool")
                .havingData(DataBuilder.aData().havingDataType(ModelHelper.createBooleanDataType()).withName("name1"))
                .havingContract(
                        ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withName("name1")))
                .build();
        pools.add(pool);
        doReturn(pools).when(drs).getAllProcesses();

        final CallActivityMapper mapper = callActivityHelper.findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(callActivity);
        assertThat(mapper.getInputMappingToCreate()).isEmpty();
        assertThat(mapper.getOutputMappingToCreate()).isEmpty();
    }

    @Test
    public void testAutoMap_withList() {
        final Data callActivityData = JavaObjectDataBuilder.aJavaObjectData()
                .withClassname(List.class.getName())
                .havingDataType(ModelHelper.createJavaDataType())
                .withName("name1")
                .build();
        final CallActivity callActivity = CallActivityBuilder
                .aCallActivity()
                .havingCalledActivityName(ExpressionBuilder.aConstantExpression().withContent("CalledPool"))
                .havingData(callActivityData)
                .build();

        final List<AbstractProcess> pools = new ArrayList<AbstractProcess>();
        final Pool pool = PoolBuilder
                .aPool()
                .withName("CalledPool")
                .havingData(DataBuilder.aData().multiple().havingDataType(ModelHelper.createBooleanDataType()).withName("name1"))
                .havingContract(
                        ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().multiple().withName("name1")))
                .build();
        pools.add(pool);
        doReturn(pools).when(drs).getAllProcesses();

        final CallActivityMapper mapper = callActivityHelper.findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(callActivity);
        assertThat(mapper.getInputMappingToCreate()).containsExactly(callActivityData);
        assertThat(mapper.getOutputMappingToCreate()).containsExactly(callActivityData);
    }

    @Test
    public void testAutoMap_withComplex() {
        final Data callActivityData = JavaObjectDataBuilder.aJavaObjectData()
                .withClassname(Map.class.getName())
                .havingDataType(ModelHelper.createJavaDataType())
                .withName("name1")
                .build();
        final CallActivity callActivity = CallActivityBuilder
                .aCallActivity()
                .havingCalledActivityName(ExpressionBuilder.aConstantExpression().withContent("CalledPool"))
                .havingData(callActivityData)
                .build();

        final List<AbstractProcess> pools = new ArrayList<AbstractProcess>();
        final Pool pool = PoolBuilder
                .aPool()
                .withName("CalledPool")
                .havingData(JavaObjectDataBuilder.aJavaObjectData()
                        .withClassname(Map.class.getName())
                        .havingDataType(ModelHelper.createJavaDataType())
                        .withName("name1"))
                .havingContract(
                        ContractBuilder.aContract().havingInput(ContractInputBuilder.aContractInput().withType(ContractInputType.COMPLEX).withName("name1")))
                .build();
        pools.add(pool);
        doReturn(pools).when(drs).getAllProcesses();

        final CallActivityMapper mapper = callActivityHelper.findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(callActivity);
        assertThat(mapper.getInputMappingToCreate()).containsExactly(callActivityData);
        assertThat(mapper.getOutputMappingToCreate()).containsExactly(callActivityData);
    }

}
