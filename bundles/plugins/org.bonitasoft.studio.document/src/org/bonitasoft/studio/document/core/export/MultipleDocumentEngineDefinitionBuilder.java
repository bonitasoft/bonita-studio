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

import org.bonitasoft.engine.bpm.process.impl.DocumentListDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.engine.contribution.BuildProcessDefinitionException;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Document;

public class MultipleDocumentEngineDefinitionBuilder implements IDefinitionBuildable {

    private final Document document;
    private final ProcessDefinitionBuilder builder;
    private final DocumentGroovyScriptExpressionFactory scriptFactory;

    public MultipleDocumentEngineDefinitionBuilder(final Document document, final ProcessDefinitionBuilder builder,
            final DocumentGroovyScriptExpressionFactory scriptFactory) {
        if (document == null) {
            throw new IllegalArgumentException();
        }
        if (!document.isMultiple()) {
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
        final DocumentListDefinitionBuilder documentListBuilder = builder.addDocumentListDefinition(document.getName());
        documentListBuilder.addDescription(document.getDocumentation());
        switch (document.getDocumentType()) {
            case CONTRACT:
                addContractInputInitialContent(documentListBuilder);
                break;
            case EXTERNAL:
            case INTERNAL:
                addScriptInitialContent(documentListBuilder);
                break;
            case NONE:
            default:
                break;
        }
    }

    private void addContractInputInitialContent(final DocumentListDefinitionBuilder documentListBuilder) {
        if (document.getContractInput() != null) {
            documentListBuilder.addInitialValue(EngineExpressionUtil.createExpression(scriptFactory
                    .createMultipleDocumentInitialContentScriptExpression(document.getContractInput())));
        }
    }

    private void addScriptInitialContent(final DocumentListDefinitionBuilder documentListBuilder) {
        final Expression initialMultipleContent = document.getInitialMultipleContent();
        if (initialMultipleContent != null && initialMultipleContent.hasContent()) {
            documentListBuilder.addInitialValue(EngineExpressionUtil.createExpression(initialMultipleContent));
        }
    }

}
