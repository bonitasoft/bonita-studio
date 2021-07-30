/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.provider.ExpressionTypeLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;

public class ExpressionDependenciesConstraint extends AbstractLiveValidationMarkerConstraint {

    private static final String CONSTRAINT_ID = "org.bonitasoft.studio.validation.constraint.expressionDependencies";

    private final ExpressionTypeLabelProvider expressionTypeLabelProvider;

    private static Set<String> supportedTypes;
    static {
        supportedTypes = new HashSet<>();
        supportedTypes.add(ExpressionConstants.VARIABLE_TYPE);
        supportedTypes.add(ExpressionConstants.PARAMETER_TYPE);
        supportedTypes.add(ExpressionConstants.PATTERN_TYPE);
    }

    public ExpressionDependenciesConstraint() {
        expressionTypeLabelProvider = new ExpressionTypeLabelProvider();
    }

    ExpressionDependenciesConstraint(final ExpressionTypeLabelProvider expressionTypeLabelProvider) {
        this.expressionTypeLabelProvider = expressionTypeLabelProvider;
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final Expression expression = (Expression) context.getTarget();
        if (shouldValidateExpression(expression)) {
            return evaluateExpression(context, expression);
        }
        return context.createSuccessStatus();
    }

    private boolean shouldValidateExpression(final Expression expression) {
        return expression.hasContent()
                && !ModelHelper.isAnExpressionCopy(expression)
                && appliesTo(expression.getType());
    }

    private boolean appliesTo(final String type) {
        return supportedTypes.contains(type);
    }

    private IStatus evaluateExpression(final IValidationContext context, final EObject eObj) {
        final Expression expression = (Expression) eObj;
        final String type = expression.getType();
        if (ExpressionConstants.PATTERN_TYPE.equals(type)) {
            for (EObject dep : expression.getReferencedElements()) {
                if (dep instanceof Expression && ((Expression) dep).getReferencedElements().stream()
                        .filter(Expression.class::isInstance)
                        .map(Expression.class::cast)
                        .anyMatch(exp -> !exp.hasContent() || !exp.hasName())) {
                    return context.createFailureStatus(NLS.bind(Messages.unresolvedDependenciesFor,
                            expression.getContent().replace("\n", "\\n").replace("\r", "\\r"),
                            expressionTypeLabelProvider.getText(type)));
                }
            }
        } else if (expression.getReferencedElements().isEmpty()) {
            return context.createFailureStatus(NLS.bind(Messages.unresolvedDependenciesFor,
                    expression.getName(),
                    expressionTypeLabelProvider.getText(type)));
        }
        return context.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return CONSTRAINT_ID;
    }

}
