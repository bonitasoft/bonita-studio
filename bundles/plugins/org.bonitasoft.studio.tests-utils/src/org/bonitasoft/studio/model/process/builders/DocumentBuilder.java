/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.process.builders;

import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 */
public class DocumentBuilder extends ElementBuilder<Document, DocumentBuilder> {

    public static DocumentBuilder aDocument() {
        return new DocumentBuilder();
    }

    public DocumentBuilder withDocumentType(final DocumentType type) {
        getBuiltInstance().setDocumentType(type);
        return getThis();
    }

    public DocumentBuilder havingMimeType(final ExpressionBuilder mimeTypeExpression) {
        getBuiltInstance().setMimeType(mimeTypeExpression.build());
        return getThis();
    }

    public DocumentBuilder havingInitialMultipleContent(final ExpressionBuilder initialMultipleContentExpression) {
        getBuiltInstance().setInitialMultipleContent(initialMultipleContentExpression.build());
        return getThis();
    }

    public DocumentBuilder havingContractInput(final ContractInputBuilder contractInput) {
        getBuiltInstance().setContractInput(contractInput.build());
        return getThis();
    }

    public DocumentBuilder multiple() {
        getBuiltInstance().setMultiple(true);
        return getThis();
    }

    public DocumentBuilder single() {
        getBuiltInstance().setMultiple(false);
        return getThis();
    }

    public DocumentBuilder havingURL(final ExpressionBuilder urlExpression) {
        getBuiltInstance().setUrl(urlExpression.build());
        return getThis();
    }

    public DocumentBuilder withDefaultValueIdOfDocumentStore(final String defaultValueIdOfDocumentStore) {
        getBuiltInstance().setDefaultValueIdOfDocumentStore(defaultValueIdOfDocumentStore);
        return getThis();
    }

    @Override
    protected DocumentBuilder getThis() {
        return this;
    }

    @Override
    protected Document newInstance() {
        return ProcessFactory.eINSTANCE.createDocument();
    }

    @Override
    protected Document getBuiltInstance() {
        return builtEObject;
    }

}
