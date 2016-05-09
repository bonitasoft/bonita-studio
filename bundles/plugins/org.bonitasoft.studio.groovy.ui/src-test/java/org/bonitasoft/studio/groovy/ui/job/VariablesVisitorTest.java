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
package org.bonitasoft.studio.groovy.ui.job;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.syntax.Token;
import org.eclipse.jface.text.Position;
import org.junit.Test;

public class VariablesVisitorTest {

    @Test
    public void should_add_a_variable_present_as_a_decalred_variable_in_the_scope() throws Exception {
        final VariableScope variableScope = new VariableScope();
        variableScope.putDeclaredVariable(new VariableExpression("aData"));

        final VariablesVisitor variablesVisitor = new VariablesVisitor(variableScope);
        variablesVisitor.visitVariableExpression(new VariableExpression("aData"));

        assertThat(variablesVisitor.getVariableExpressions()).containsExactly("aData");
    }

    @Test
    public void should_add_a_variable_present_as_a_referenced_calss_variable_in_the_scope() throws Exception {
        final VariableScope variableScope = new VariableScope();
        variableScope.putReferencedClassVariable(new VariableExpression("aData"));

        final VariablesVisitor variablesVisitor = new VariablesVisitor(variableScope);
        variablesVisitor.visitVariableExpression(new VariableExpression("aData"));

        assertThat(variablesVisitor.getVariableExpressions()).containsExactly("aData");
    }

    @Test
    public void should_not_add_a_variable_not_in_the_scope() throws Exception {
        final VariableScope variableScope = new VariableScope();
        variableScope.putDeclaredVariable(new VariableExpression("aData"));

        final VariablesVisitor variablesVisitor = new VariablesVisitor(variableScope);
        variablesVisitor.visitVariableExpression(new VariableExpression("varaibleNotInScope"));

        assertThat(variablesVisitor.getVariableExpressions()).isEmpty();
    }

    @Test
    public void should_add_decalred_expression_with_position() throws Exception {
        final VariablesVisitor variablesVisitor = new VariablesVisitor(new VariableScope());

        final DeclarationExpression declarationExpression = new DeclarationExpression(new VariableExpression("myVar"), Token.NULL,
                new VariableExpression("anotherVar"));
        declarationExpression.setStart(42);
        variablesVisitor.visitDeclarationExpression(declarationExpression);

        assertThat(variablesVisitor.getDeclaredExpressions()).containsExactly(entry("myVar", new Position(42)));
    }

}
