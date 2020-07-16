/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.export.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aConstantExpression;
import static org.bonitasoft.studio.model.process.builders.StringDataTypeBuilder.aStringDataType;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.BoundaryEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.CatchMessageEventTriggerDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.FlowElementBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserFilterDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.engine.operation.LeftOperand;
import org.bonitasoft.engine.operation.Operation;
import org.bonitasoft.engine.operation.OperatorType;
import org.bonitasoft.studio.assertions.EngineExpressionAssert;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.engine.contribution.IEngineDefinitionBuilder;
import org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorConfigurationBuilder;
import org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorParameterBuilder;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.builders.ActivityBuilder;
import org.bonitasoft.studio.model.process.builders.ActorFilterBuilder;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataTypeBuilder;
import org.bonitasoft.studio.model.process.builders.CallActivityBuilder;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.model.process.builders.InputMappingBuilder;
import org.bonitasoft.studio.model.process.builders.MainProcessBuilder;
import org.bonitasoft.studio.model.process.builders.PoolBuilder;
import org.bonitasoft.studio.model.process.builders.StringDataTypeBuilder;
import org.bonitasoft.studio.model.process.builders.TaskBuilder;
import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class EngineFlowElementBuilderTest {

    private EngineFlowElementBuilder flowElementSwitch;
    private ProcessDefinitionBuilder instance;
    @Mock
    ActivityDefinitionBuilder activityDefinitionBuilder;

    @Mock
    BoundaryEventDefinitionBuilder boundaryEventBuilder;
    @Mock
    CatchMessageEventTriggerDefinitionBuilder catchMessageEventTriggerDefinitionBuilder;

    @Mock
    private UserFilterDefinitionBuilder userFilterBuilder;

    @Mock
    private UserTaskDefinitionBuilder taskBuilder;
    @Mock
    private org.bonitasoft.engine.bpm.process.impl.CallActivityBuilder callActivityBuilder;

    @Mock
    private IEngineDefinitionBuilder<FlowElementBuilder> engineContractBuilder;

    @Mock
    private IEngineDefinitionBuilderProvider builderProvider;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        instance = new ProcessDefinitionBuilder().createNewInstance("test", "1.0");
        flowElementSwitch = spy(
                new EngineFlowElementBuilder(instance, builderProvider, new ModelSearch(Collections::emptyList)));
        doReturn(engineContractBuilder).when(builderProvider).getEngineDefinitionBuilder(any(EObject.class),
                any(Contract.class), eq(FlowElementBuilder.class));
        doReturn(userFilterBuilder).when(taskBuilder).addUserFilter(anyString(), anyString(), anyString());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_add_message_correlation_if_start_message_in_event_subprocess() throws Exception {
        final StartMessageEvent startMessageEventinSubprocess = ProcessFactory.eINSTANCE.createStartMessageEvent();
        startMessageEventinSubprocess.setEvent("message");

        final SubProcessEvent subProcessEvent = ProcessFactory.eINSTANCE.createSubProcessEvent();
        subProcessEvent.getElements().add(startMessageEventinSubprocess);
        flowElementSwitch.caseStartMessageEvent(startMessageEventinSubprocess);

        verify(flowElementSwitch).addMessageCorrelation(eq(startMessageEventinSubprocess),
                any(CatchMessageEventTriggerDefinitionBuilder.class));
    }

    @Test
    public void should_addCorrelations_whenActivity_isBoundaryMessageEvent() {
        final BoundaryMessageEvent bmEvent = ProcessFactory.eINSTANCE.createBoundaryMessageEvent();
        final TableExpression correlations = ExpressionFactory.eINSTANCE.createTableExpression();
        bmEvent.setEvent("myEvent");
        bmEvent.setName("bmEvent");
        bmEvent.setCorrelation(correlations);
        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.getBoundaryIntermediateEvents().add(bmEvent);
        when(activityDefinitionBuilder.addBoundaryEvent(bmEvent.getName(), true)).thenReturn(boundaryEventBuilder);
        when(boundaryEventBuilder.addMessageEventTrigger(bmEvent.getEvent()))
                .thenReturn(catchMessageEventTriggerDefinitionBuilder);
        flowElementSwitch.addBoundaryEvents(activityDefinitionBuilder, activity);
        verify(flowElementSwitch).addMessageCorrelation(eq(bmEvent), any(CatchMessageEventTriggerDefinitionBuilder.class));
    }

    @Test
    public void should_addContract_build_an_engine_contract() throws Exception {
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        pool.setContract(contract);
        flowElementSwitch.addContract(taskBuilder, pool);
        verify(engineContractBuilder).setEngineBuilder(taskBuilder);
        verify(engineContractBuilder).build(contract);
    }

    @Test
    public void should_not_addContract_build_an_engine_contract() throws Exception {
        flowElementSwitch.addContract(taskBuilder, null);
        verify(engineContractBuilder, never()).build(any(Contract.class));
    }

    @Test
    public void testAddContext() {
        final Data collectionDataToMultiInstantiate = DataBuilder.aData().withName("pData")
                .havingDataType(StringDataTypeBuilder.aStringDataType()).build();

        final TaskBuilder taskB = TaskBuilder.aTask()
                .havingCollectionDataToMultiInstantiate(collectionDataToMultiInstantiate)
                .havingIteratorExpression(ExpressionBuilder.anExpression()
                        .withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE)
                        .withReturnType(String.class.getName())
                        .withName("pData"))
                .havingData(collectionDataToMultiInstantiate);

        final Pool pool = PoolBuilder.aPool()
                .havingElements(taskB)
                .havingData(BusinessObjectDataBuilder.aBusinessData().withName("myBData").withClassname("my.classname"))
                .build();
        final MainProcess mainProcess = MainProcessBuilder.aMainProcess().build();
        mainProcess.getElements().add(pool);
        mainProcess.getDatatypes().add(StringDataTypeBuilder.aStringDataType().build());
        flowElementSwitch.addContext(taskBuilder, (Task) pool.getElements().get(0));

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(taskBuilder).addContextEntry(eq("myBData_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("myBData");
        assertThat(argument.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_BUSINESS_DATA_REFERENCE.name());
    }

    @Test
    public void testAddIteratorToContext() {
        final Data collectionDataToMultiInstantiate = BusinessObjectDataBuilder.aBusinessData().withName("bData")
                .withClassname("classname").build();

        final TaskBuilder taskB = TaskBuilder.aTask()
                .havingCollectionDataToMultiInstantiate(collectionDataToMultiInstantiate)
                .havingIteratorExpression(ExpressionBuilder.anExpression()
                        .withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE)
                        .withReturnType(String.class.getName())
                        .withName("pData"))
                .havingData(collectionDataToMultiInstantiate);

        final Pool pool = PoolBuilder.aPool().havingElements(taskB).build();
        final MainProcess mainProcess = MainProcessBuilder.aMainProcess().build();
        mainProcess.getElements().add(pool);
        mainProcess.getDatatypes()
                .add(BusinessObjectDataTypeBuilder.aBusinessObjectDataType().withName("classname").build());
        flowElementSwitch.addContext(taskBuilder, (Task) pool.getElements().get(0));

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(taskBuilder).addContextEntry(eq("pData_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("pData");
        assertThat(argument.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_BUSINESS_DATA_REFERENCE.name());
    }

    @Test
    public void testAddIteratorToContext_robustnessWithNullValue() {
        final Data collectionDataToMultiInstantiate = BusinessObjectDataBuilder.aBusinessData().withName("bData")
                .withClassname("classname").build();

        final TaskBuilder taskB = TaskBuilder.aTask()
                .havingCollectionDataToMultiInstantiate(collectionDataToMultiInstantiate)
                .havingData(collectionDataToMultiInstantiate);

        final Pool pool = PoolBuilder.aPool().havingElements(taskB).build();
        final MainProcess mainProcess = MainProcessBuilder.aMainProcess().build();
        mainProcess.getElements().add(pool);
        mainProcess.getDatatypes()
                .add(BusinessObjectDataTypeBuilder.aBusinessObjectDataType().withName("classname").build());
        flowElementSwitch.addContext(taskBuilder, (Task) pool.getElements().get(0));

        verify(taskBuilder, times(0)).addContextEntry(anyString(), any(Expression.class));
    }

    @Test
    public void testAddInputMappingAssignedToData() {
        final Pool pool = PoolBuilder.aPool()
                .havingElements(
                        CallActivityBuilder
                                .aCallActivity()
                                .withName("Call Activity")
                                .havingCalledActivityName(aConstantExpression().withContent("Pool1"))
                                .havingInputMappings(
                                        InputMappingBuilder
                                                .anInputMapping()
                                                .setSubProcessTarget(InputMappingAssignationType.DATA, "subProcessData")
                                                .setProcessSource(ExpressionBuilder.aVariableExpression()
                                                        .withContent("processData").build())
                                                .build()))
                .havingData(DataBuilder.aData().havingDataType(aStringDataType()).withName("processData"))
                .build();

        flowElementSwitch.exportInputMappingsForCallActivity((CallActivity) pool.getElements().get(0), callActivityBuilder);

        final ArgumentCaptor<Operation> argument = ArgumentCaptor.forClass(Operation.class);
        verify(callActivityBuilder).addDataInputOperation(argument.capture());
        assertThat(argument.getValue().getType()).isEqualTo(OperatorType.ASSIGNMENT);
        assertThat(argument.getValue().getLeftOperand().getName()).isEqualTo("subProcessData");
        assertThat(argument.getValue().getLeftOperand().getType()).isEqualTo(LeftOperand.TYPE_DATA);
        assertThat(argument.getValue().getRightOperand().getContent()).isEqualTo("processData");
    }

    @Test
    public void testAddInputMappingAssignedToContractInput() {
        final Pool pool = PoolBuilder.aPool()
                .havingElements(
                        CallActivityBuilder
                                .aCallActivity()
                                .withName("Call Activity")
                                .havingInputMappings(
                                        InputMappingBuilder
                                                .anInputMapping()
                                                .setSubProcessTarget(InputMappingAssignationType.CONTRACT_INPUT,
                                                        "contractInput")
                                                .setProcessSource(ExpressionBuilder.aVariableExpression()
                                                        .withContent("processData").build())
                                                .build()))
                .havingData(DataBuilder.aData().havingDataType(aStringDataType()).withName("processData"))
                .build();

        flowElementSwitch.exportInputMappingsForCallActivity((CallActivity) pool.getElements().get(0), callActivityBuilder);

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(callActivityBuilder).addProcessStartContractInput(eq("contractInput"), argument.capture());
        EngineExpressionAssert.assertThat(argument.getValue())
                .hasContent("processData")
                .hasExpressionType(ExpressionType.TYPE_VARIABLE.name());
    }

    @Test
    public void testAddUserFilterToTask_withTableExpression() {
        final TableExpression tableExpression = ExpressionFactory.eINSTANCE.createTableExpression();
        final ListExpression listExpression = ExpressionFactory.eINSTANCE.createListExpression();
        listExpression.getExpressions().add(ExpressionBuilder.aConstantExpression().withName("test").build());
        tableExpression.getExpressions().add(listExpression);
        final ActorFilter actorFilter = ActorFilterBuilder.anActorFilter().havingConfiguration(
                ConnectorConfigurationBuilder.aConnectorConfiguration().havingParameters(
                        ConnectorParameterBuilder.aConnectorParameter().withKey("plop").havingExpression(tableExpression)))
                .build();
        flowElementSwitch.addUserFilterToTask(taskBuilder, "actor", actorFilter);
        verify(userFilterBuilder).addInput(anyString(), any(Expression.class));
    }

    @Test
    public void testAddUserFilterToTask_withEmptyOptionalInputs() {
        final ActorFilter actorFilter = ActorFilterBuilder.anActorFilter().havingConfiguration(
                ConnectorConfigurationBuilder.aConnectorConfiguration().havingParameters(
                        ConnectorParameterBuilder.aConnectorParameter().withKey("plop")))
                .build();
        flowElementSwitch.addUserFilterToTask(taskBuilder, "actor", actorFilter);
        verify(userFilterBuilder, never()).addInput(anyString(), any(Expression.class));
    }

    @Test
    public void testAddDataForMultiInstanceIterator_WithBusinessData() {
        final Data collectionDataToMultiInstantiate = BusinessObjectDataBuilder.aBusinessData().withName("bData")
                .withClassname("classname").build();
        final Pool pool = PoolBuilder.aPool()
                .havingElements(
                        ActivityBuilder.anActivity()
                                .havingCollectionDataToMultiInstantiate(collectionDataToMultiInstantiate)
                                .havingIteratorExpression(ExpressionBuilder.anExpression()
                                        .withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE)
                                        .withReturnType(String.class.getName())
                                        .withName("bData")))
                .havingData(collectionDataToMultiInstantiate)
                .build();

        flowElementSwitch.addDataForMultiInstanceIterator(activityDefinitionBuilder,
                ((Activity) pool.getElements().get(0)).getIteratorExpression(),
                collectionDataToMultiInstantiate);

        verify(activityDefinitionBuilder).addBusinessData(anyString(), anyString());
    }

    @Test
    public void testAddDataForMultiInstanceIterator_WithProcessData() {
        final Data collectionDataToMultiInstantiate = DataBuilder.aData().withName("pData")
                .havingDataType(StringDataTypeBuilder.aStringDataType()).build();
        final Pool pool = PoolBuilder.aPool()
                .havingElements(
                        ActivityBuilder.anActivity()
                                .havingCollectionDataToMultiInstantiate(collectionDataToMultiInstantiate)
                                .havingIteratorExpression(ExpressionBuilder.anExpression()
                                        .withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE)
                                        .withReturnType(String.class.getName())
                                        .withName("pData")))
                .havingData(collectionDataToMultiInstantiate)
                .build();

        flowElementSwitch.addDataForMultiInstanceIterator(activityDefinitionBuilder,
                ((Activity) pool.getElements().get(0)).getIteratorExpression(),
                collectionDataToMultiInstantiate);

        verify(activityDefinitionBuilder).addData(anyString(), anyString(), any(Expression.class));
    }
}
