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

import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import java.util.List;

import org.bonitasoft.engine.bpm.contract.FileInputValue;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.bonitasoft.studio.model.process.builders.ContractInputBuilder;
import org.junit.Test;

public class DocumentGroovyScriptExpressionFactoryTest {

    @Test
    public void should_create_an_expression_for_a_multiple_child_input() throws Exception {
        final DocumentGroovyScriptExpressionFactory scriptExpressionFactory = new DocumentGroovyScriptExpressionFactory();
        final ContractInputBuilder fileInput = aContractInput().withName("myFile").multiple().withType(ContractInputType.FILE);
        aContractInput().withName("parent").withType(ContractInputType.COMPLEX).havingInput(fileInput).build();

        final Expression expression = scriptExpressionFactory.createMultipleDocumentInitialContentScriptExpression(fileInput.build());

        ExpressionAssert.assertThat(expression).hasContent("parent.myFile").hasReturnType(List.class.getName())
                .hasType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name());
        ContractInputAssert.assertThat((ContractInput) expression.getReferencedElements().get(0)).hasName("parent");
    }

    @Test
    public void should_create_an_expression_for_a_multiple_root_input() throws Exception {
        final DocumentGroovyScriptExpressionFactory scriptExpressionFactory = new DocumentGroovyScriptExpressionFactory();
        final ContractInputBuilder fileInput = aContractInput().withName("myFile").multiple().withType(ContractInputType.FILE);

        final Expression expression = scriptExpressionFactory.createMultipleDocumentInitialContentScriptExpression(fileInput.build());

        ExpressionAssert.assertThat(expression).hasContent("myFile").hasReturnType(List.class.getName())
                .hasType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name());
        ContractInputAssert.assertThat((ContractInput) expression.getReferencedElements().get(0)).hasName("myFile");
    }

    @Test
    public void should_create_an_expression_for_for_single_child_input_contained_in_a_multiple_parent_input() throws Exception {
        final DocumentGroovyScriptExpressionFactory scriptExpressionFactory = new DocumentGroovyScriptExpressionFactory();
        final ContractInputBuilder fileInput = aContractInput().withName("myFile").withType(ContractInputType.FILE);
        aContractInput().withName("parent").withType(ContractInputType.COMPLEX).multiple().havingInput(fileInput).build();

        final Expression expression = scriptExpressionFactory.createMultipleDocumentInitialContentScriptExpression(fileInput.build());

        ExpressionAssert.assertThat(expression).hasContent("parent.collect{it.myFile}.flatten()").hasReturnType(List.class.getName())
                .hasType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name());
        ContractInputAssert.assertThat((ContractInput) expression.getReferencedElements().get(0)).hasName("parent");
    }

    @Test
    public void should_create_an_expression_for_root_single_input() throws Exception {
        final DocumentGroovyScriptExpressionFactory scriptExpressionFactory = new DocumentGroovyScriptExpressionFactory();
        final ContractInputBuilder fileInput = aContractInput().withName("myFile").withType(ContractInputType.FILE);

        final Expression expression = scriptExpressionFactory.createSingleDocumentInitialContentScriptExpression(fileInput.build());

        ExpressionAssert.assertThat(expression).hasContent("myFile").hasReturnType(FileInputValue.class.getName())
                .hasType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name());
        ContractInputAssert.assertThat((ContractInput) expression.getReferencedElements().get(0)).hasName("myFile");
    }

    @Test
    public void should_create_an_expression_for_child_single_input_contained_in_a_single_input() throws Exception {
        final DocumentGroovyScriptExpressionFactory scriptExpressionFactory = new DocumentGroovyScriptExpressionFactory();
        final ContractInputBuilder fileInput = aContractInput().withName("myFile").withType(ContractInputType.FILE);
        aContractInput().withName("parent").withType(ContractInputType.COMPLEX).havingInput(fileInput).build();
        final Expression expression = scriptExpressionFactory.createSingleDocumentInitialContentScriptExpression(fileInput.build());

        ExpressionAssert.assertThat(expression).hasContent("parent.myFile").hasReturnType(FileInputValue.class.getName())
                .hasType(ExpressionType.TYPE_READ_ONLY_SCRIPT.name());
        ContractInputAssert.assertThat((ContractInput) expression.getReferencedElements().get(0)).hasName("parent");
    }

}
