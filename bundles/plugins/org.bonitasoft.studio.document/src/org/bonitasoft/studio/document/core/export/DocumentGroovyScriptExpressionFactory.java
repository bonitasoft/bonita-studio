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

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.base.Predicates.not;
import static org.bonitasoft.studio.common.functions.ContractInputFunctions.toAncestorNameList;
import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withMultipleInHierarchy;

import java.util.List;
import java.util.function.Function;

import org.bonitasoft.engine.bpm.contract.FileInputValue;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Document;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.base.Joiner;

public class DocumentGroovyScriptExpressionFactory {

    public Expression createSingleDocumentInitialContentScriptExpression(final ContractInput input) {
        final Expression groovyScriptExpression = ExpressionHelper.createGroovyScriptExpression(
                fileContractInputAccessorScript(input),
                FileInputValue.class.getName());
        groovyScriptExpression.setName("Single document initial value script");
        addInputDependency(input, groovyScriptExpression);
        return groovyScriptExpression;
    }

    private void addInputDependency(final ContractInput input, final Expression groovyScriptExpression) {
        groovyScriptExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(rootInput(input)));
    }

    public Expression createMultipleDocumentInitialContentScriptExpression(final ContractInput input) {
        final Expression groovyScriptExpression = ExpressionHelper.createGroovyScriptExpression(
                fileContractInputAccessorScript(input),
                List.class.getName());
        groovyScriptExpression.setName("Multiple document initial value script");
        addInputDependency(input, groovyScriptExpression);
        return groovyScriptExpression;
    }

    private String fileContractInputAccessorScript(final ContractInput contractInput) {
        if (not(instanceOf(ContractInput.class)).apply(contractInput.eContainer())
                || not(withMultipleInHierarchy()).apply((ContractInput) contractInput.eContainer())) {
            return Joiner.on(".").join(toAncestorNameList().apply(contractInput));
        }
        final ContractInput parentInput = (ContractInput) contractInput.eContainer();
        final StringBuilder scriptBuilder = new StringBuilder(Joiner.on(".").join(toAncestorNameList().apply(parentInput)));
        scriptBuilder.append(".collect{it.");
        scriptBuilder.append(contractInput.getName());
        scriptBuilder.append("}.flatten()");
        return scriptBuilder.toString();
    }

    private ContractInput rootInput(final ContractInput contractInput) {
        ContractInput root = contractInput;
        while (root.eContainer() instanceof ContractInput) {
            root = (ContractInput) root.eContainer();
        }
        return root;
    }

    public Expression createUpdateDocumentListFromContractExpression(ContractInput input, Document document,
            Function<String, String> formater) {
        String script = createMergeDocumentScript(input, formater);
        String scriptName = String.format("update_%s", input.getDataReference());
        Expression expression = ExpressionHelper.createGroovyScriptExpression(script, List.class.getName(), scriptName);
        expression.getReferencedElements().add(EcoreUtil.copy(input));
        expression.getReferencedElements().add(EcoreUtil.copy(document));
        return expression;
    }

    private String createMergeDocumentScript(ContractInput input, Function<String, String> formater) {
        StringBuilder scriptBuilder = new StringBuilder();
        appendImport(scriptBuilder);
        initVariables(scriptBuilder);
        addExistingDocuments(scriptBuilder, input);
        addNewDocuments(scriptBuilder, input);
        addReturnStatement(scriptBuilder);
        return formater.apply(scriptBuilder.toString());
    }

    private void appendImport(StringBuilder scriptBuilder) {
        appendLine(scriptBuilder, "import org.bonitasoft.engine.bpm.document.DocumentValue");
        appendLine(scriptBuilder, "");
    }

    private void initVariables(StringBuilder scriptBuilder) {
        appendLine(scriptBuilder, "def filesOutput = []");
        appendLine(scriptBuilder, "");
    }

    private void addExistingDocuments(StringBuilder scriptBuilder, ContractInput input) {
        appendLine(scriptBuilder, String.format("if (%s) {", input.getDataReference()));
        appendLine(scriptBuilder, String.format("%s", input.getDataReference()));
        appendLine(scriptBuilder, ".collect { doc ->  new DocumentValue(doc.id) }");
        appendLine(scriptBuilder, ".each (filesOutput.&add)");
        appendLine(scriptBuilder, "}");
        appendLine(scriptBuilder, "");
    }

    private void addNewDocuments(StringBuilder scriptBuilder, ContractInput input) {
        appendLine(scriptBuilder, String.format("if (%s) {", input.getName()));
        appendLine(scriptBuilder, String.format("%s", input.getName()));
        appendLine(scriptBuilder, ".collect { doc ->  new DocumentValue(doc.content,doc.contentType, doc.fileName)}");
        appendLine(scriptBuilder, ".each (filesOutput.&add)");
        appendLine(scriptBuilder, "}");
        appendLine(scriptBuilder, "");
    }

    private void addReturnStatement(StringBuilder scriptBuilder) {
        scriptBuilder.append("filesOutput");
    }

    private void appendLine(StringBuilder scriptBuilder, String content) {
        scriptBuilder.append(content + "\n");
    }

}
