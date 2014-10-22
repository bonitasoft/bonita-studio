/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.process.builders;

import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 *
 */
public class DocumentBuilder {

    private final Document document;

    private DocumentBuilder(final Document document) {
        this.document = document;
    }

    public static DocumentBuilder create() {
        return new DocumentBuilder(ProcessFactory.eINSTANCE.createDocument());
    }

    public DocumentBuilder withName(final String name) {
        document.setName(name);
        return this;
    }

    public DocumentBuilder withDocumentType(final DocumentType type) {
        document.setDocumentType(type);
        return this;
    }

    public DocumentBuilder withDocumentation(final String documentation) {
        document.setDocumentation(documentation);
        return this;
    }

    public DocumentBuilder havingMimeType(final ExpressionBuilder mimeTypeExpression) {
        document.setMimeType(mimeTypeExpression.build());
        return this;
    }

    public DocumentBuilder havingInitialMultipleContent(final ExpressionBuilder initialMultipleContentExpression) {
        document.setInitialMultipleContent(initialMultipleContentExpression.build());
        return this;
    }

    public DocumentBuilder multiple() {
        document.setMultiple(true);
        return this;
    }

    public DocumentBuilder single() {
        document.setMultiple(false);
        return this;
    }

    public DocumentBuilder havingURL(final ExpressionBuilder urlExpression) {
        document.setUrl(urlExpression.build());
        return this;
    }

    public DocumentBuilder withDefaultValueIdOfDocumentStore(final String defaultValueIdOfDocumentStore) {
        document.setDefaultValueIdOfDocumentStore(defaultValueIdOfDocumentStore);
        return this;
    }

    public Document build() {
        return document;
    }

}
