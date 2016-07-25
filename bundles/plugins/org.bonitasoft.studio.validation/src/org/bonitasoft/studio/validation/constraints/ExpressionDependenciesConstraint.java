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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.provider.ExpressionTypeLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class ExpressionDependenciesConstraint extends AbstractLiveValidationMarkerConstraint {

    private static final String CONSTRAINT_ID = "org.bonitasoft.studio.validation.constraint.expressionDependencies";

    private final ExpressionTypeLabelProvider expressionTypeLabelProvider;
    private static final Map<EStructuralFeature, EStructuralFeature> toggableFeatures = new HashMap<EStructuralFeature, EStructuralFeature>();
    static {
        toggableFeatures.put(FormPackage.Literals.WIDGET__DISPLAY_LABEL, FormPackage.Literals.WIDGET__SHOW_DISPLAY_LABEL);
    }

    private static Set<String> supportedTypes;
    static {
        supportedTypes = new HashSet<String>();
        supportedTypes.add(ExpressionConstants.VARIABLE_TYPE);
        supportedTypes.add(ExpressionConstants.PARAMETER_TYPE);
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
                && isAnActiveExpression(expression)
                && !ModelHelper.isAnExpressionCopy(expression)
                && appliesTo(expression.getType());
    }

    private boolean isAnActiveExpression(final Expression expression) {
        return toggableFeatures.containsKey(expression.eContainingFeature()) ? isToggled(toggableFeatures.get(expression.eContainingFeature()),
                expression.eContainer()) : true;
    }

    private boolean isToggled(final EStructuralFeature featureToCheck, final EObject target) {
        return (Boolean) target.eGet(featureToCheck);
    }

    private boolean appliesTo(final String type) {
        return supportedTypes.contains(type);
    }

    private IStatus evaluateExpression(final IValidationContext context, final EObject eObj) {
        final Expression expression = (Expression) eObj;
        final String type = expression.getType();
        if (expression.getReferencedElements().isEmpty()) {
            return context.createFailureStatus(Messages.bind(Messages.unresolvedDependenciesFor, expression.getName(),
                    expressionTypeLabelProvider.getText(type)));
        }
        return context.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return CONSTRAINT_ID;
    }

}
