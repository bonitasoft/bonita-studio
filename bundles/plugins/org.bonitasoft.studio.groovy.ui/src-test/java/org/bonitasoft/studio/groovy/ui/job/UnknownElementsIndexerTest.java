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
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.groovy.BonitaScriptGroovyCompilationUnit;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.ReturnStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.syntax.Token;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.Position;
import org.junit.Test;

public class UnknownElementsIndexerTest {

    @Test
    public void should_call_variable_visitor_on_statement_block() throws Exception {
        final BonitaScriptGroovyCompilationUnit groovyCompilationUnit = mock(BonitaScriptGroovyCompilationUnit.class, RETURNS_DEEP_STUBS);
        final BlockStatement statementBlock = mock(BlockStatement.class);
        when(groovyCompilationUnit.getModuleNode().getStatementBlock()).thenReturn(statementBlock);
        final UnknownElementsIndexer unknownElementsIndexer = new UnknownElementsIndexer(groovyCompilationUnit);

        unknownElementsIndexer.run(new NullProgressMonitor());

        verify(statementBlock).visit(notNull(VariablesVisitor.class));
    }

    @Test
    public void should_add_to_unknonwn_variables_a_variable_not_declared_and_not_in_the_process_scope() throws Exception {
        final BonitaScriptGroovyCompilationUnit groovyCompilationUnit = mock(BonitaScriptGroovyCompilationUnit.class, RETURNS_DEEP_STUBS);

        final List<Statement> statements = new ArrayList<Statement>();
        statements.add(new ReturnStatement(new VariableExpression("unkownVariable")));
        final VariableScope variableScope = new VariableScope();
        variableScope.putDeclaredVariable(new VariableExpression("unkownVariable"));
        final BlockStatement blockStatement = new BlockStatement(statements, variableScope);

        when(groovyCompilationUnit.getModuleNode().getStatementBlock()).thenReturn(blockStatement);
        final UnknownElementsIndexer unknownElementsIndexer = new UnknownElementsIndexer( groovyCompilationUnit);

        unknownElementsIndexer.run(new NullProgressMonitor());

        assertThat(unknownElementsIndexer.getUnknownVaraibles()).containsExactly("unkownVariable");
    }

    @Test
    public void should_not_add_to_unknonwn_variables_a_variable_declared_but_not_in_the_process_scope() throws Exception {
        final BonitaScriptGroovyCompilationUnit groovyCompilationUnit = mock(BonitaScriptGroovyCompilationUnit.class, RETURNS_DEEP_STUBS);

        final List<Statement> statements = new ArrayList<Statement>();
        statements.add(new ExpressionStatement(
                new DeclarationExpression(new VariableExpression("declaredVar"), Token.NULL, new VariableExpression("something"))));
        statements.add(new ReturnStatement(new VariableExpression("declaredVar")));
        final VariableScope variableScope = new VariableScope();
        variableScope.putDeclaredVariable(new VariableExpression("declaredVar"));
        final BlockStatement blockStatement = new BlockStatement(statements, variableScope);

        when(groovyCompilationUnit.getModuleNode().getStatementBlock()).thenReturn(blockStatement);
        final UnknownElementsIndexer unknownElementsIndexer = new UnknownElementsIndexer(groovyCompilationUnit);

        unknownElementsIndexer.run(new NullProgressMonitor());

        assertThat(unknownElementsIndexer.getUnknownVaraibles()).isEmpty();
    }

    @Test
    public void should_add_to_overriden_variables_a_variable_declared_and_already_in_the_process_scope() throws Exception {
        final BonitaScriptGroovyCompilationUnit groovyCompilationUnit = mock(BonitaScriptGroovyCompilationUnit.class, RETURNS_DEEP_STUBS);
        Map<String, ScriptVariable> context = new HashMap<String, ScriptVariable>();
        context.put("declaredVar", null);
        when(groovyCompilationUnit.getContext()).thenReturn(context);
        
        final List<Statement> statements = new ArrayList<Statement>();
        statements.add(new ExpressionStatement(
                new DeclarationExpression(new VariableExpression("declaredVar"), Token.NULL, new VariableExpression("something"))));
        statements.add(new ReturnStatement(new VariableExpression("declaredVar")));
        final VariableScope variableScope = new VariableScope();
        variableScope.putDeclaredVariable(new VariableExpression("declaredVar"));
        final BlockStatement blockStatement = new BlockStatement(statements, variableScope);

        when(groovyCompilationUnit.getModuleNode().getStatementBlock()).thenReturn(blockStatement);
        final UnknownElementsIndexer unknownElementsIndexer = new UnknownElementsIndexer(groovyCompilationUnit);

        unknownElementsIndexer.run(new NullProgressMonitor());

        assertThat(unknownElementsIndexer.getOverridenVariables()).containsExactly(entry("declaredVar", new Position(0)));
    }
}
