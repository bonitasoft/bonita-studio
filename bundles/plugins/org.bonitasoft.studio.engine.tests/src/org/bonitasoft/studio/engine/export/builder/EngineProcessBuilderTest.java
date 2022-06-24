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
package org.bonitasoft.studio.engine.export.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.parameter.builders.ParameterBuilder.aParameter;
import static org.bonitasoft.studio.model.process.builders.ActorBuilder.anActor;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.DocumentBuilder.aDocument;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.bonitasoft.engine.bpm.process.impl.ActorDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.BusinessDataDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.FlowElementBuilder;
import org.bonitasoft.engine.bpm.process.impl.ParameterDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.engine.contribution.IEngineDefinitionBuilder;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder;
import org.bonitasoft.studio.model.process.builders.PoolBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EngineProcessBuilderTest {

    private EngineProcessBuilder engineProcessBuilder;
    @Mock
    private ProcessDefinitionBuilder processDefBuilder;
    @Mock
    private BusinessDataDefinitionBuilder bDataBuilder;
    @Mock
    private IEngineDefinitionBuilderProvider builderProvider;


    @Before
    public void setup() {
        engineProcessBuilder = spy(
                new EngineProcessBuilder(processDefBuilder, builderProvider, new ModelSearch(Collections::emptyList)));
        doReturn(bDataBuilder).when(processDefBuilder).addBusinessData(any(), any(), any());
        
    }

    @Test
    public void testAddBusinessDataInContext() {
        final Pool pool = PoolBuilder.aPool()
                .havingData(BusinessObjectDataBuilder.aBusinessData().withName("myBData").withClassname("my.classname"))
                .build();
        engineProcessBuilder.casePool(pool);

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(processDefBuilder).addContextEntry(eq("myBData_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("myBData");
        assertThat(argument.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_BUSINESS_DATA_REFERENCE.name());
    }

    @Test
    public void testAddSimpleDocumentInContext() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingDocuments(aDocument().withName("myDoc"))
                .build();
        final IEngineDefinitionBuilder engineDefinitionBuilder = mock(IEngineDefinitionBuilder.class);
        doReturn(engineDefinitionBuilder).when(builderProvider).getEngineDefinitionBuilder(eq(pool),
                any(), eq(ProcessDefinitionBuilder.class));
        engineProcessBuilder.casePool(pool);

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(processDefBuilder).addContextEntry(eq("myDoc_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("myDoc");
        assertThat(argument.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT.name());
    }

    @Test
    public void testAddSMultipleDocumentInContext() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingDocuments(aDocument().withName("myDoc").multiple())
                .build();
        final IEngineDefinitionBuilder engineDefinitionBuilder = mock(IEngineDefinitionBuilder.class);
        doReturn(engineDefinitionBuilder).when(builderProvider).getEngineDefinitionBuilder(eq(pool),
                any(), eq(ProcessDefinitionBuilder.class));
        engineProcessBuilder.casePool(pool);

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(processDefBuilder).addContextEntry(eq("myDoc_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("myDoc");
        assertThat(argument.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT_LIST.name());
    }

    @Test
    public void testAddBusinessDataAndDocumentInContext() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingData(BusinessObjectDataBuilder.aBusinessData().withName("myBData").withClassname("my.classname"))
                .havingDocuments(aDocument().withName("myDoc"))
                .build();
        final IEngineDefinitionBuilder engineDefinitionBuilder = mock(IEngineDefinitionBuilder.class);
        doReturn(engineDefinitionBuilder).when(builderProvider).getEngineDefinitionBuilder(eq(pool),
                any(), eq(ProcessDefinitionBuilder.class));
        engineProcessBuilder.casePool(pool);

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(processDefBuilder).addContextEntry(eq("myBData_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("myBData");
        assertThat(argument.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_BUSINESS_DATA_REFERENCE.name());

        final ArgumentCaptor<Expression> argumentDoc = ArgumentCaptor.forClass(Expression.class);
        verify(processDefBuilder).addContextEntry(eq("myDoc_ref"), argumentDoc.capture());
        assertThat(argumentDoc.getValue().getName()).isEqualTo("myDoc");
        assertThat(argumentDoc.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT.name());
    }

    @Test
    public void should_add_process_actors() throws Exception {
        final Pool pool = aPool()
                .havingActors(anActor().withName("employee").withDocumentation("an employee actor").initiator())
                .build();
        final ActorDefinitionBuilder actorDefinitionBuilder = mock(ActorDefinitionBuilder.class);
        doReturn(actorDefinitionBuilder).when(processDefBuilder).addActor(any(), anyBoolean());

        engineProcessBuilder.casePool(pool);

        verify(processDefBuilder).addActor("employee", true);
        verify(actorDefinitionBuilder).addDescription("an employee actor");
    }

    @Test
    public void should_add_process_parameters() throws Exception {
        final Pool pool = aPool()
                .havingParameters(aParameter().withName("myParam").withDescription("a parameter example")
                        .withType(String.class.getName()))
                .build();
        final ParameterDefinitionBuilder parameterDefinitionBuilder = mock(ParameterDefinitionBuilder.class);
        doReturn(parameterDefinitionBuilder).when(processDefBuilder).addParameter(any(), any());

        engineProcessBuilder.casePool(pool);

        verify(processDefBuilder).addParameter("myParam", String.class.getName());
        verify(parameterDefinitionBuilder).addDescription("a parameter example");
    }

    @Test
    public void should_add_process_documents() throws Exception {
        final Document myDocument = aDocument().withName("myDoc").build();
        final Pool pool = aPool()
                .havingDocuments(myDocument)
                .build();
        final IEngineDefinitionBuilder engineDefinitionBuilder = mock(IEngineDefinitionBuilder.class);
        doReturn(engineDefinitionBuilder).when(builderProvider).getEngineDefinitionBuilder(eq(pool),
                any(), eq(ProcessDefinitionBuilder.class));

        engineProcessBuilder.casePool(pool);

        verify(engineDefinitionBuilder).build(myDocument);
    }

    @Test
    public void should_add_process_contract() throws Exception {
        final Contract myContract = aContract().build();
        final Pool pool = aPool()
                .havingContract(myContract)
                .build();
        final IEngineDefinitionBuilder engineDefinitionBuilder = mock(IEngineDefinitionBuilder.class);
        doReturn(engineDefinitionBuilder).when(builderProvider).getEngineDefinitionBuilder(eq(pool),
                any(), eq(FlowElementBuilder.class));

        engineProcessBuilder.casePool(pool);

        verify(engineDefinitionBuilder).build(myContract);
    }

    @Test
    public void should_set_display_name() throws Exception {
        final Pool pool = aPool()
                .withDisplayName("my process")
                .build();

        engineProcessBuilder.casePool(pool);

        verify(processDefBuilder).addDisplayName("my process");
    }

}
