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
package org.bonitasoft.studio.document.core.export;

import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.DocumentBuilder.aDocument;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.bonitasoft.engine.bpm.process.impl.DocumentListDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.assertions.EngineExpressionAssert;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.builders.ContractInputBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MultipleDocumentEngineDefinitionBuilderTest {

    @Mock
    private ProcessDefinitionBuilder processBuilder;
    @Mock
    public DocumentListDefinitionBuilder docDefinitionListBuilder;

    @Test
    public void testIntialContentForMultipleDocumentAdded() throws Exception {
        when(processBuilder.addDocumentListDefinition(anyString())).thenReturn(docDefinitionListBuilder);

        final Pool process = ProcessFactory.eINSTANCE.createPool();
        final Document document = createBasicDocument();
        document.setMultiple(true);
        final Expression initialExpression = ExpressionHelper.createEmptyListGroovyScriptExpression();
        document.setInitialMultipleContent(initialExpression);
        process.getDocuments().add(document);

        final MultipleDocumentEngineDefinitionBuilder builder = new MultipleDocumentEngineDefinitionBuilder(document, processBuilder);

        builder.build();

        verify(docDefinitionListBuilder).addInitialValue(Mockito.<org.bonitasoft.engine.expression.Expression> anyObject());
    }

    @Test
    public void should_not_add_initial_content_for_CONTRACT_type_if_contract_input_is_null() throws Exception {
        when(processBuilder.addDocumentListDefinition(anyString())).thenReturn(docDefinitionListBuilder);

        final MultipleDocumentEngineDefinitionBuilder builder = new MultipleDocumentEngineDefinitionBuilder(aDocument().multiple()
                .withDocumentType(DocumentType.CONTRACT)
                .build(), processBuilder);
        builder.build();

        verify(docDefinitionListBuilder, never()).addInitialValue(notNull(org.bonitasoft.engine.expression.Expression.class));
    }

    @Test
    public void should_add_initial_content_as_expression_for_CONTRACT_type_for_child_input() throws Exception {
        when(processBuilder.addDocumentListDefinition(anyString())).thenReturn(docDefinitionListBuilder);

        final ContractInputBuilder fileInput = aContractInput().withName("myFile").multiple().withType(ContractInputType.FILE);
        aContractInput().withName("parent").withType(ContractInputType.COMPLEX).havingInput(fileInput).build();
        final MultipleDocumentEngineDefinitionBuilder builder = new MultipleDocumentEngineDefinitionBuilder(aDocument().multiple()
                .withDocumentType(DocumentType.CONTRACT)
                .havingContractInput(fileInput).build(), processBuilder);
        builder.build();

        final ArgumentCaptor<org.bonitasoft.engine.expression.Expression> expressionCaptor = ArgumentCaptor
                .forClass(org.bonitasoft.engine.expression.Expression.class);
        verify(docDefinitionListBuilder).addInitialValue(expressionCaptor.capture());
        EngineExpressionAssert.assertThat(expressionCaptor.getValue()).hasContent("parent.myFile").hasReturnType(List.class.getName())
                .hasExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name());
    }

    @Test
    public void should_add_initial_content_as_expression_for_CONTRACT_type_for_root_input() throws Exception {
        when(processBuilder.addDocumentListDefinition(anyString())).thenReturn(docDefinitionListBuilder);

        final ContractInputBuilder fileInput = aContractInput().withName("myFile").multiple().withType(ContractInputType.FILE);
        final MultipleDocumentEngineDefinitionBuilder builder = new MultipleDocumentEngineDefinitionBuilder(aDocument().multiple()
                .withDocumentType(DocumentType.CONTRACT)
                .havingContractInput(fileInput).build(), processBuilder);
        builder.build();

        final ArgumentCaptor<org.bonitasoft.engine.expression.Expression> expressionCaptor = ArgumentCaptor
                .forClass(org.bonitasoft.engine.expression.Expression.class);
        verify(docDefinitionListBuilder).addInitialValue(expressionCaptor.capture());
        EngineExpressionAssert.assertThat(expressionCaptor.getValue()).hasContent("myFile").hasReturnType(List.class.getName())
                .hasExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name());
    }

    @Test
    public void should_add_initial_content_as_expression_for_CONTRACT_type_for_single_chile_input_contained_in_a_multiple_parent_input() throws Exception {
        when(processBuilder.addDocumentListDefinition(anyString())).thenReturn(docDefinitionListBuilder);

        final ContractInputBuilder fileInput = aContractInput().withName("myFile").withType(ContractInputType.FILE);
        aContractInput().withName("parent").withType(ContractInputType.COMPLEX).multiple().havingInput(fileInput).build();
        final MultipleDocumentEngineDefinitionBuilder builder = new MultipleDocumentEngineDefinitionBuilder(aDocument().multiple()
                .withDocumentType(DocumentType.CONTRACT)
                .havingContractInput(fileInput).build(), processBuilder);
        builder.build();

        final ArgumentCaptor<org.bonitasoft.engine.expression.Expression> expressionCaptor = ArgumentCaptor
                .forClass(org.bonitasoft.engine.expression.Expression.class);
        verify(docDefinitionListBuilder).addInitialValue(expressionCaptor.capture());
        EngineExpressionAssert.assertThat(expressionCaptor.getValue()).hasContent("parent.collect{it.myFile}.flatten()").hasReturnType(List.class.getName())
                .hasExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name());
    }

    private Document createBasicDocument() {
        return aDocument().withName("testDocument").build();
    }

}
