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
package org.bonitasoft.studio.engine.export.switcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.BoundaryEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.CatchMessageEventTriggerDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserFilterDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.engine.contribution.IEngineDefinitionBuilder;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder;
import org.bonitasoft.studio.model.process.builders.PoolBuilder;
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
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FlowElementSwitchTest {

    private FlowElementSwitch flowElementSwitch;
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
    private IEngineDefinitionBuilder engineContractBuilder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        instance = new ProcessDefinitionBuilder().createNewInstance("test", "1.0");
        flowElementSwitch = spy(new FlowElementSwitch(instance, Collections.<EObject> emptySet()));
        doReturn(engineContractBuilder).when(flowElementSwitch).getEngineDefinitionBuilder(any(EObject.class), any(Contract.class));
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

        verify(flowElementSwitch).addMessageCorrelation(eq(startMessageEventinSubprocess), any(CatchMessageEventTriggerDefinitionBuilder.class));
    }

    @Test
    public void should_addUserFilterToTask_not_export_parameter_if_expression_is_null() throws Exception {
        final ConnectorParameter parameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        final ConnectorConfiguration connectorConfiguration = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        connectorConfiguration.getParameters().add(parameter);

        final ActorFilter filter = ProcessFactory.eINSTANCE.createActorFilter();
        filter.setConfiguration(connectorConfiguration);

        flowElementSwitch.addInputIfExpressionValid(userFilterBuilder, parameter);

        verify(userFilterBuilder, never()).addInput(any(String.class), any(Expression.class));

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
        when(boundaryEventBuilder.addMessageEventTrigger(bmEvent.getEvent())).thenReturn(catchMessageEventTriggerDefinitionBuilder);
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
    public void testAddContext(){
        final Pool pool = PoolBuilder.aPool()
                .havingElements(TaskBuilder.aTask().withName("taskName"))
                .havingData(BusinessObjectDataBuilder.aBusinessData().withName("myBData").withClassname("my.classname"))
                .build();
        flowElementSwitch.addContext(taskBuilder, (Task) pool.getElements().get(0));

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(taskBuilder).addContextEntry(eq("myBData_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("myBData");
        assertThat(argument.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_BUSINESS_DATA_REFERENCE.name());
    }

}
