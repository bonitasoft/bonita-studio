/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * 
 * @author Romain Bioteau
 *
 */
public class ConditionExpressionConstraint extends AbstractLiveValidationMarkerConstraint {


    @Override
    protected IStatus performLiveValidation(IValidationContext ctx) {
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getMarkerType(DiagramEditor editor) {
        return ProcessMarkerNavigationProvider.MARKER_TYPE;
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.condition_expression";
    }

    @Override
    protected IStatus performBatchValidation(IValidationContext ctx) {
        EObject target = ctx.getTarget();
        if(target instanceof SequenceFlow){
            Expression conditionExpression = ((SequenceFlow) target).getCondition();
            if(conditionExpression != null 
                    && ExpressionConstants.CONDITION_TYPE.equals(conditionExpression.getType())
                    && conditionExpression.getContent() != null 
                    && !conditionExpression.getContent().isEmpty()){
                Operation_Compare opCompare = getCompareOperation(conditionExpression);
                if(opCompare == null || opCompare.getOp() == null){
                    return ctx.createFailureStatus(Messages.bind(Messages.invalidConditionExpression,conditionExpression.getName()));
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    protected Operation_Compare getCompareOperation(Expression conditionExpression) {
        return EngineExpressionUtil.parseConditionExpression(conditionExpression.getContent(), conditionExpression);
    }

}
