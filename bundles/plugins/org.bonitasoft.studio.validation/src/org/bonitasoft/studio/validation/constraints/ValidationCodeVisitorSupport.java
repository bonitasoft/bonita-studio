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
package org.bonitasoft.studio.validation.constraints;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.validation.ValidationPlugin;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.codehaus.groovy.ast.CodeVisitorSupport;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.syntax.Types;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class ValidationCodeVisitorSupport extends CodeVisitorSupport {

    private final Set<String> dependenciesName;
    private final IStatus okStatus;
    private final MultiStatus errorStatus;
    private final IValidationContext context;
    private final Set<MethodSignature> declaredMethodsSignature = new HashSet<>();

    public ValidationCodeVisitorSupport(final IValidationContext context, final Expression expression, final ModuleNode node) {
        dependenciesName = retrieveDependenciesList(expression);
        okStatus = context.createSuccessStatus();
        errorStatus = new MultiStatus(ValidationPlugin.PLUGIN_ID, IStatus.OK, "", null);
        this.context = context;
        for (final MethodNode method : node.getMethods()) {
            declaredMethodsSignature.add(new MethodSignature(method.getName(), method.getParameters().length));
        }
    }

    @Override
    public void visitBinaryExpression(final BinaryExpression expression) {
        if (expression.getOperation() != null && expression.getOperation().isA(Types.ASSIGNMENT_OPERATOR)) {
            final org.codehaus.groovy.ast.expr.Expression leftExpression = expression.getLeftExpression();
            if (dependenciesName.contains(leftExpression.getText())) {
                errorStatus.add(context.createFailureStatus(Messages.bind(
                        Messages.invalidDependencyAssignement, leftExpression.getText())));

            }
        }
        super.visitBinaryExpression(expression);
    }

    private Set<String> retrieveDependenciesList(final Expression expression) {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil.createExpression(expression);
        final Set<String> result = new HashSet<>();
        if (engineExpression != null) {
            for (final org.bonitasoft.engine.expression.Expression dep : engineExpression.getDependencies()) {
                result.add(dep.getName());
            }
        }
        return result;
    }

    public IStatus getStatus() {
        if (!errorStatus.isOK()) {
            return errorStatus;
        }
        return okStatus;
    }
}
