/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.export;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.bpm.process.impl.DocumentDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.DocumentListDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DesignProcessDefinitionBuilderTest {

    @Test
    public void testHasADefaultValueReturnFalseWithNone() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.NONE);
        assertThat(builder.hasADefaultValue(document))
        .as("Document type None can't have Initial content")
        .isFalse();
    }

    @Test
    public void testHasADefaultValueReturnTrueWithFromInternal() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.INTERNAL);
        document.setDefaultValueIdOfDocumentStore("internalId");
        assertThat(builder.hasADefaultValue(document))
        .as("Document type Internal with id set should have a defautl value")
        .isTrue();
    }

    @Test
    public void testHasADefaultValueReturnFalseWithFromInternalAndNoDocumentSToreId() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.INTERNAL);
        assertThat(builder.hasADefaultValue(document))
        .as("Document type Internal without id set should not have a default value")
        .isFalse();
    }

    @Test
    public void testHasADefaultValueReturnTrueWithFromExternal() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.EXTERNAL);
        final Expression urlExpression = ExpressionFactory.eINSTANCE.createExpression();
        urlExpression.setName("testName");
        urlExpression.setContent("testContent");
        document.setUrl(urlExpression);
        assertThat(builder.hasADefaultValue(document))
        .as("Document type External with id set should have a default value")
        .isTrue();
    }

    @Test
    public void testHasADefaultValueReturnFalseWithFromExternalAndEmptyUrlExpression() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.EXTERNAL);
        final Expression urlExpression = ExpressionFactory.eINSTANCE.createExpression();
        document.setUrl(urlExpression);
        assertThat(builder.hasADefaultValue(document))
        .as("Document type External with id but no url set should not have a default value")
        .isFalse();
    }

    @Test
    public void testHasADefaultValueReturnFalseForDocumentList() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.EXTERNAL);
        document.setMultiple(true);
        final Expression urlExpression = ExpressionFactory.eINSTANCE.createExpression();
        urlExpression.setName("testName");
        urlExpression.setContent("testContent");
        document.setUrl(urlExpression);
        assertThat(builder.hasADefaultValue(document))
        .as("Mulitple Document with type External set with id set without default value should not be called")
        .isFalse();
    }

    @Test
    public void testHasADefaultValueReturnTrueForDocumentList() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        final Document document = createBasicDocument();
        document.setMultiple(true);
        final Expression urlExpression = ExpressionFactory.eINSTANCE.createExpression();
        urlExpression.setName("testName");
        urlExpression.setContent("testContent");
        document.setInitialMultipleContent(urlExpression);
        assertThat(builder.hasADefaultValue(document))
        .as("Mulitple Document with initial multiple content set without should be called")
        .isTrue();
    }

    @Mock
    private ProcessDefinitionBuilder processDefinitionBuilder;

    @Mock
    private DocumentDefinitionBuilder docDefinitionBuilder;

    @Test
    public void testMimeTypeAddedIfThereIsADefaultValue() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        when(processDefinitionBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final Pool process = ProcessFactory.eINSTANCE.createPool();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.EXTERNAL);
        final Expression urlExpression = ExpressionFactory.eINSTANCE.createExpression();
        document.setUrl(urlExpression);
        urlExpression.setName("testName");
        urlExpression.setContent("testContent");
        document.setUrl(urlExpression);
        final Expression mimeType = ExpressionFactory.eINSTANCE.createExpression();
        mimeType.setName("mimeTypeTest");
        mimeType.setName("octet/stream");
        document.setMimeType(mimeType);
        process.getDocuments().add(document);

        builder.processDocuments(process, processDefinitionBuilder);

        verify(docDefinitionBuilder).addMimeType(anyString());
    }

    @Test
    public void testMimeTypeAddedEvenIfThereIsNoDefaultValue() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        when(processDefinitionBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        // Create a process with a document with a not valid Initial Content
        final Pool process = ProcessFactory.eINSTANCE.createPool();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.EXTERNAL);
        final Expression urlExpression = ExpressionFactory.eINSTANCE.createExpression();
        document.setUrl(urlExpression);
        final Expression mimeType = ExpressionFactory.eINSTANCE.createExpression();
        mimeType.setName("mimeTypeTest");
        mimeType.setName("octet/stream");
        document.setMimeType(mimeType);
        process.getDocuments().add(document);

        builder.processDocuments(process, processDefinitionBuilder);

        verify(docDefinitionBuilder).addMimeType(anyString());
    }

    @Mock
    public DocumentListDefinitionBuilder docDefinitionListBuilder;

    @Test
    public void testIntialContentForMultipleDocumentAdded() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        when(processDefinitionBuilder.addDocumentListDefinition(anyString())).thenReturn(docDefinitionListBuilder);

        final Pool process = ProcessFactory.eINSTANCE.createPool();
        final Document document = createBasicDocument();
        document.setMultiple(true);
        final Expression initialExpression = ExpressionHelper.createEmptyListGroovyScriptExpression();
        document.setInitialMultipleContent(initialExpression);
        process.getDocuments().add(document);

        builder.processDocuments(process, processDefinitionBuilder);

        verify(docDefinitionListBuilder).addInitialValue(Mockito.<org.bonitasoft.engine.expression.Expression> anyObject());
    }

    @Test
    public void testIntialContentForExternalSimpleDocumentAdded() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        when(processDefinitionBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final Pool process = ProcessFactory.eINSTANCE.createPool();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.EXTERNAL);
        final Expression initialExpression = ExpressionHelper.createEmptyListGroovyScriptExpression();
        initialExpression.setContent("http://testUrl");
        document.setUrl(initialExpression);
        process.getDocuments().add(document);

        builder.processDocuments(process, processDefinitionBuilder);

        verify(docDefinitionBuilder).addUrl(Mockito.anyString());
    }

    @Test
    public void testIntialContentForExternalSimpleDocumentNotAddedIfUrlNotSpecified() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        when(processDefinitionBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final Pool process = ProcessFactory.eINSTANCE.createPool();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.EXTERNAL);
        process.getDocuments().add(document);

        builder.processDocuments(process, processDefinitionBuilder);

        verify(docDefinitionBuilder, Mockito.never()).addUrl(Mockito.anyString());
    }

    @Test
    public void testIntialContentForInteralSimpleDocumentAdded() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        when(processDefinitionBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final Pool process = ProcessFactory.eINSTANCE.createPool();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.INTERNAL);
        document.setDefaultValueIdOfDocumentStore("idTest");
        process.getDocuments().add(document);

        builder.processDocuments(process, processDefinitionBuilder);

        verify(docDefinitionBuilder).addFile(Mockito.anyString());
        verify(docDefinitionBuilder).addContentFileName(Mockito.anyString());
    }

    @Test
    public void testIntialContentNotAddedForInternalSimpleDocumentAddedIfStoreIdNotSpecified() {
        final DesignProcessDefinitionBuilder builder = new DesignProcessDefinitionBuilder();
        when(processDefinitionBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final Pool process = ProcessFactory.eINSTANCE.createPool();
        final Document document = createBasicDocument();
        document.setDocumentType(DocumentType.INTERNAL);
        process.getDocuments().add(document);

        builder.processDocuments(process, processDefinitionBuilder);

        verify(docDefinitionBuilder, Mockito.never()).addFile(Mockito.anyString());
        verify(docDefinitionBuilder, Mockito.never()).addContentFileName(Mockito.anyString());
    }

    private Document createBasicDocument() {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("testDocument");
        return document;
    }

}
