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

import org.bonitasoft.engine.bpm.process.impl.DocumentDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.engine.contribution.BuildProcessDefinitionException;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Document;

public class SingleDocumentEngineDefinitionBuilder implements IDefinitionBuildable {

    private final Document document;
    private final ProcessDefinitionBuilder builder;
    private final DocumentGroovyScriptExpressionFactory scriptFactory;

    public SingleDocumentEngineDefinitionBuilder(final Document document, final ProcessDefinitionBuilder builder,
            final DocumentGroovyScriptExpressionFactory scriptFactory) {
        if(document == null) {
            throw new IllegalArgumentException();
        }
        if(document.isMultiple()) {
            throw new IllegalArgumentException();
        }
        this.document = document;
        this.builder = builder;
        this.scriptFactory = scriptFactory;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.document.core.export.IDefinitionBuildable#build()
     */
    @Override
    public void build() throws BuildProcessDefinitionException {
        final DocumentDefinitionBuilder documentBuilder = builder.addDocumentDefinition(document.getName());
        documentBuilder.addDescription(document.getDocumentation());
        addDocumentMimeType(document, documentBuilder);
        addDocumentInitialContent(document, documentBuilder);
    }

    private void addDocumentMimeType(final Document document, final DocumentDefinitionBuilder documentBuilder) {
        final Expression mimeType = document.getMimeType();
        if (mimeType != null && mimeType.hasContent()) {
            documentBuilder.addMimeType(mimeType.getContent());
        }
    }

    private void addDocumentInitialContent(final Document document, final DocumentDefinitionBuilder documentBuilder) {
        switch (document.getDocumentType()) {
            case INTERNAL:
                addLocalFileContent(document, documentBuilder);
                break;
            case EXTERNAL:
                addUrlContent(document, documentBuilder);
                break;
            case CONTRACT:
                addContractInputContent(document, documentBuilder);
                break;
            case NONE:
            default:
                break;
        }
    }

    private void addContractInputContent(final Document document, final DocumentDefinitionBuilder documentBuilder) {
        if (document.getContractInput() != null) {
            documentBuilder.addInitialValue(EngineExpressionUtil.createExpression(scriptFactory.createSingleDocumentInitialContentScriptExpression(document
                    .getContractInput())));
        }
    }

    private void addUrlContent(final Document document, final DocumentDefinitionBuilder documentBuilder) {
        final Expression url = document.getUrl();
        if (url != null && url.hasContent()) {
            documentBuilder.addUrl(url.getContent());
        }
    }

    private void addLocalFileContent(final Document document, final DocumentDefinitionBuilder documentBuilder) {
        final String defaultValueIdOfDocumentStore = document.getDefaultValueIdOfDocumentStore();
        if (defaultValueIdOfDocumentStore != null && !defaultValueIdOfDocumentStore.isEmpty()) {
            documentBuilder.addFile(defaultValueIdOfDocumentStore);
            documentBuilder.addContentFileName(defaultValueIdOfDocumentStore);
        }
    }

}
