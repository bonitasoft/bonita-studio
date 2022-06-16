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

import static com.google.common.collect.Iterators.find;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.codehaus.groovy.ast.CodeVisitorSupport;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.eclipse.jface.text.Position;

import com.google.common.base.Predicate;

public class VariablesVisitor extends CodeVisitorSupport {

    private final Set<String> variableExpressions = new HashSet<String>();
    private final Map<String, Position> declaredExpressions = new HashMap<String, Position>();
    private final VariableScope variableScope;

    public VariablesVisitor(final VariableScope variableScope) {
        this.variableScope = variableScope;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.ast.CodeVisitorSupport#visitVariableExpression(org.codehaus.groovy.ast.expr.VariableExpression)
     */
    @Override
    public void visitVariableExpression(final VariableExpression expression) {
        if (inVariableScope(expression)) {
            variableExpressions.add(expression.getName());
        }
        super.visitVariableExpression(expression);
    }

    private boolean inVariableScope(final VariableExpression expression) {
        final Variable matchInDeclaredVariables = find(variableScope
                .getDeclaredVariablesIterator(),
                variableWithName(expression.getName()), null);
        final Variable matchInReferencedClassVariables = find(variableScope
                .getReferencedClassVariablesIterator(),
                variableWithName(expression.getName()), null);
        return matchInDeclaredVariables != null || matchInReferencedClassVariables != null;
    }

    private Predicate<Variable> variableWithName(final String name) {
        return new Predicate<Variable>() {

            @Override
            public boolean apply(final Variable input) {
                return Objects.equals(name, input.getName());
            }
        };
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.ast.CodeVisitorSupport#visitDeclarationExpression(org.codehaus.groovy.ast.expr.DeclarationExpression)
     */
    @Override
    public void visitDeclarationExpression(final DeclarationExpression expression) {
        declaredExpressions.put(expression.getLeftExpression().getText(), new Position(expression.getStart()));
        super.visitDeclarationExpression(expression);
    }

    public Set<String> getVariableExpressions() {
        return variableExpressions;
    }

    public Map<String, Position> getDeclaredExpressions() {
        return declaredExpressions;
    }
}
