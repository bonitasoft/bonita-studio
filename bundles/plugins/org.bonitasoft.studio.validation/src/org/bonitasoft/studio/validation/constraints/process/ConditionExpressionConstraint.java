/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.constraints.process;

import java.util.Collections;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.scoping.ConditionModelGlobalScopeProvider;
import org.bonitasoft.studio.condition.ui.expression.ComparisonExpressionLoadException;
import org.bonitasoft.studio.condition.ui.expression.ComparisonExpressionValidator;
import org.bonitasoft.studio.condition.ui.expression.ProjectXtextResourceProvider;
import org.bonitasoft.studio.condition.ui.expression.XtextComparisonExpressionLoader;
import org.bonitasoft.studio.condition.ui.internal.ConditionModelActivator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

import com.google.inject.Injector;

/**
 * @author Romain Bioteau
 */
public class ConditionExpressionConstraint extends AbstractLiveValidationMarkerConstraint {

    private ComparisonExpressionValidator comparisonExpressionValidator = new ComparisonExpressionValidator();

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.condition_expression";
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject target = ctx.getTarget();
        if (target instanceof SequenceFlow) {
            final Expression conditionExpression = ((SequenceFlow) target).getCondition();
            if (conditionExpression != null
                    && ExpressionConstants.CONDITION_TYPE.equals(conditionExpression.getType())
                    && conditionExpression.hasContent()) {
                Injector injector = getInjector();
                final Operation_Compare opCompare = getCompareOperation(injector, conditionExpression);
                if (opCompare == null || opCompare.getOp() == null) {
                    return ctx.createFailureStatus(Messages.bind(Messages.invalidConditionExpression, conditionExpression.getName()));
                }
                if(target.eResource() != null) {
                    IStatus status = comparisonExpressionValidator.validateXtextResource(injector, opCompare.eResource(), target.eResource().getResourceSet());
                    if(status.getSeverity() == IStatus.ERROR) {
                        return ctx.createFailureStatus(Messages.bind(Messages.invalidConditionExpression, conditionExpression.getName()));
                    }
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    Injector getInjector() {
        return ConditionModelActivator.getInstance()
                .getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
    }

    protected Operation_Compare getCompareOperation(Injector injector, final Expression conditionExpression) {
        final XtextComparisonExpressionLoader comparisonExpressionConverter = new XtextComparisonExpressionLoader(
                injector.getInstance(ConditionModelGlobalScopeProvider.class),
                new ModelSearch(Collections::emptyList),
                new ProjectXtextResourceProvider(injector));
        try {
            return comparisonExpressionConverter.loadConditionExpression(conditionExpression.getContent(), conditionExpression);
        } catch (final ComparisonExpressionLoadException e) {
            return null;
        }
    }

}
