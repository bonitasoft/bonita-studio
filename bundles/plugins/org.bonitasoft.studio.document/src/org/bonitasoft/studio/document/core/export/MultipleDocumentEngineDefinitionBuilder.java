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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.base.Predicates.not;
import static org.bonitasoft.studio.common.functions.ContractInputFunctions.toAncestorNameList;
import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withMultipleInHierarchy;

import java.util.List;

import org.bonitasoft.engine.bpm.process.impl.DocumentListDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.engine.contribution.BuildProcessDefinitionException;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Document;

import com.google.common.base.Joiner;

public class MultipleDocumentEngineDefinitionBuilder implements IDefinitionBuildable {

    private final Document document;
    private final ProcessDefinitionBuilder builder;

    public MultipleDocumentEngineDefinitionBuilder(final Document document, final ProcessDefinitionBuilder builder) {
        checkArgument(document != null);
        checkArgument(document.isMultiple());
        this.document = document;
        this.builder = builder;
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
            default:
                addScriptInitialContent(documentListBuilder);
                break;
        }
    }

    private void addContractInputInitialContent(final DocumentListDefinitionBuilder documentListBuilder) {
        if (document.getContractInput() != null) {
            final Expression groovyScriptExpression = ExpressionHelper.createGroovyScriptExpression(
                    fileContractInputAccessorScript(document.getContractInput()),
                    List.class.getName());
            groovyScriptExpression.setName("Initial document value script");
            documentListBuilder.addInitialValue(EngineExpressionUtil.createExpression(groovyScriptExpression));
        }
    }

    private void addScriptInitialContent(final DocumentListDefinitionBuilder documentListBuilder) {
        final Expression initialMultipleContent = document.getInitialMultipleContent();
        if (initialMultipleContent != null && initialMultipleContent.hasContent()) {
            documentListBuilder.addInitialValue(EngineExpressionUtil.createExpression(initialMultipleContent));
        }
    }

    private String fileContractInputAccessorScript(final ContractInput contractInput) {
        if (contractInput.isMultiple()) {
            if (not(instanceOf(ContractInput.class)).apply(contractInput.eContainer())
                    || not(withMultipleInHierarchy()).apply((ContractInput) contractInput.eContainer())) {
                return Joiner.on(".").join(toAncestorNameList().apply(contractInput));
            }
        }
        //Here a complicated script ?
        return "";
    }
}
