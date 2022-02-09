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

import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.DocumentBuilder.aDocument;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.notNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.bonitasoft.engine.bpm.process.impl.DocumentDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.builders.ContractInputBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SingleDocumentEngineDefinitionBuilderTest {

    @Mock
    private ProcessDefinitionBuilder processBuilder;

    @Mock
    private DocumentDefinitionBuilder docDefinitionBuilder;

    @Mock
    private DocumentGroovyScriptExpressionFactory scriptFactory;

    @Test
    public void testMimeTypeAddedIfThereIsADefaultValue() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.EXTERNAL)
                .havingURL(anExpression().withContent("http://test.com")).havingMimeType(anExpression().withContent("octet/stream")).build(), processBuilder,
                scriptFactory);
        builder.build();

        verify(docDefinitionBuilder).addMimeType(anyString());
    }

    @Test
    public void testMimeTypeAddedEvenIfThereIsNoDefaultValue() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.EXTERNAL)
                .havingURL(anExpression()).havingMimeType(anExpression().withContent("octet/stream")).build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder).addMimeType(anyString());
    }

    @Test
    public void testIntialContentForExternalSimpleDocumentAdded() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.EXTERNAL)
                .havingURL(aGroovyScriptExpression().withReturnType(List.class.getName()).withContent("http://test.com")).build(), processBuilder,
                scriptFactory);
        builder.build();

        verify(docDefinitionBuilder).addUrl(Mockito.anyString());
    }

    @Test
    public void testIntialContentForExternalSimpleDocumentNotAddedIfUrlNotSpecified() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.EXTERNAL)
                .build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder, Mockito.never()).addUrl(Mockito.anyString());
    }

    @Test
    public void testIntialContentForInteralSimpleDocumentAdded() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.INTERNAL)
                .withDefaultValueIdOfDocumentStore("idTest").build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder).addFile(Mockito.anyString());
        verify(docDefinitionBuilder).addContentFileName(Mockito.anyString());
    }

    @Test
    public void testIntialContentNotAddedForInternalSimpleDocumentAddedIfStoreIdNotSpecified() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.INTERNAL)
                .build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder, Mockito.never()).addFile(Mockito.anyString());
        verify(docDefinitionBuilder, Mockito.never()).addContentFileName(Mockito.anyString());
    }

    @Test
    public void should_not_add_initial_content_for_CONTRACT_type_if_contract_input_is_null() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.CONTRACT)
                .build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder, never()).addInitialValue(notNull());
    }

    @Test
    public void should_add_initial_content_as_expression_for_CONTRACT_type() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);
        when(scriptFactory.createSingleDocumentInitialContentScriptExpression(any(ContractInput.class))).thenReturn(
                aGroovyScriptExpression().withContent("script content").build());

        final ContractInputBuilder fileInput = aContractInput().withName("myFile").withType(ContractInputType.FILE);
        aContractInput().withName("parent").withType(ContractInputType.COMPLEX).havingInput(fileInput).build();
        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.CONTRACT)
                .havingContractInput(fileInput).build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder).addInitialValue(notNull());
    }
}
