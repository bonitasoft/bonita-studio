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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.codehaus.groovy.ast.CodeVisitorSupport;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.eclipse.jface.text.Position;

public class VariablesVisitor extends CodeVisitorSupport {

    private final Set<String> variableExpressions = new HashSet<String>();
    private final Map<String, Position> declaredExpressions = new HashMap<String, Position>();

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.ast.CodeVisitorSupport#visitVariableExpression(org.codehaus.groovy.ast.expr.VariableExpression)
     */
    @Override
    public void visitVariableExpression(final VariableExpression expression) {
        variableExpressions.add(expression.getName());
        super.visitVariableExpression(expression);
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
        variableExpressions.remove("this");
        return variableExpressions;
    }

    public Map<String, Position> getDeclaredExpressions() {
        return declaredExpressions;
    }
}
