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

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.DocumentBuilder.aDocument;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.model.process.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DocumentEngineDefinitionBuilderTest {

    @Spy
    public DocumentEngineDefinitionBuilder documentEngineDefinitionBuilder;

    @Mock
    private ProcessDefinitionBuilder processDefinitionBuilder;

    @Mock
    private SingleDocumentEngineDefinitionBuilder singleDelegate;

    @Mock
    private MultipleDocumentEngineDefinitionBuilder multipleDelegate;

    @Before
    public void setUp() throws Exception {
        documentEngineDefinitionBuilder.setEngineBuilder(processDefinitionBuilder);
        doReturn(singleDelegate).when(documentEngineDefinitionBuilder).singleDocumentDelegate(notNull(Document.class));
        doReturn(multipleDelegate).when(documentEngineDefinitionBuilder).multipeDocumentDelegate(notNull(Document.class));
    }

    @Test
    public void should_appliesTo_Document_in_a_Pool() throws Exception {
        assertThat(documentEngineDefinitionBuilder.appliesTo(aPool().build(), aDocument().build())).isTrue();
    }

    @Test
    public void should_not_appliesTo_anything_else() throws Exception {
        assertThat(documentEngineDefinitionBuilder.appliesTo(aPool().build(), null)).isFalse();
        assertThat(documentEngineDefinitionBuilder.appliesTo(null, aDocument().build())).isFalse();
        assertThat(documentEngineDefinitionBuilder.appliesTo(null, null)).isFalse();
    }

    @Test
    public void should_delegate_build_to_single_document_builder() throws Exception {
        documentEngineDefinitionBuilder.build(aDocument().build());

        verify(singleDelegate).build();
    }

    @Test
    public void should_delegate_build_to_multiple_document_builder() throws Exception {
        documentEngineDefinitionBuilder.build(aDocument().multiple().build());

        verify(multipleDelegate).build();
    }
}
