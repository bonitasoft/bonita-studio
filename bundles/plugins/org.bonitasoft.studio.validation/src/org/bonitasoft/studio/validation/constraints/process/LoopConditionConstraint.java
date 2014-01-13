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

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * 
 * @author Romain Bioteau
 *
 */
public class LoopConditionConstraint extends AbstractLiveValidationMarkerConstraint {


    @Override
    protected IStatus performLiveValidation(IValidationContext ctx) {
        final EStructuralFeature featureTriggered = ctx.getFeature();
        if(featureTriggered.equals(ExpressionPackage.Literals.EXPRESSION__CONTENT)){
            final Expression expression = (Expression) ctx.getTarget();
            if(expression.eContainingFeature() != null && expression.eContainingFeature().equals(ProcessPackage.Literals.ACTIVITY__LOOP_CONDITION)){
                final Expression loopConditionExpr = expression;
                String condition = null;
                if(loopConditionExpr != null){
                    condition = loopConditionExpr.getContent();
                }
                Activity activity =  (Activity) expression.eContainer() ;
                if (activity.getIsLoop() && (condition == null || condition.trim().isEmpty())) {
                    return ctx.createFailureStatus(new Object[] { activity.getName() });
                }
            }
        }else if(featureTriggered.equals(ProcessPackage.Literals.ACTIVITY__IS_LOOP) || featureTriggered.equals(ProcessPackage.Literals.ACTIVITY__TEST_BEFORE)){
            final EObject eobject = ctx.getTarget();
            if(eobject instanceof Activity){
                final Expression loopConditionExpr = ((Activity) eobject).getLoopCondition();
                String condition = null;
                if(loopConditionExpr != null){
                    condition = loopConditionExpr.getContent();
                }
                if (((Activity) eobject).getIsLoop() && (condition == null || condition.trim().isEmpty())) {
                    return ctx.createFailureStatus(new Object[] { ((Activity) eobject).getName() });
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getMarkerType(DiagramEditor editor) {
        return ProcessMarkerNavigationProvider.MARKER_TYPE;
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.nonemptyloopcondition";
    }

    @Override
    protected IStatus performBatchValidation(IValidationContext ctx) {
        if(ctx.getTarget() instanceof Activity){
            final Activity activity = (Activity) ctx.getTarget();
            if(activity.getIsLoop()){
                if(activity.getLoopCondition() == null || activity.getLoopCondition().getContent() == null || activity.getLoopCondition().getContent().isEmpty()){
                    return ctx.createFailureStatus(new Object[] { activity.getName()});
                }
            }
        }
        return ctx.createSuccessStatus();
    }

}
