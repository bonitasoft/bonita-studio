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

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.Test;

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

    private Document createBasicDocument() {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName("testDocument");
        return document;
    }

}
