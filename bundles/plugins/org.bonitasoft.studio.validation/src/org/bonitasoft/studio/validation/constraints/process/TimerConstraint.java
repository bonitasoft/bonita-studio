/**
 * Copyright (C) 2009-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints.process;

import java.util.Date;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerScriptType;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Baptiste Mesta
 */
public class TimerConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performLiveValidation(final IValidationContext ctx) {
        final EStructuralFeature feature = ctx.getFeature();
        if (feature.equals(ProcessPackage.Literals.ABSTRACT_TIMER_EVENT__CONDITION)) {
            final AbstractTimerEvent timerEvent = (AbstractTimerEvent) ctx.getTarget();
            final Expression condition = (Expression) ctx.getFeatureNewValue();
            if (isMissingCondition(timerEvent, condition)) {
                return ctx.createFailureStatus(new Object[] { Messages.Validation_TimerEvent_MissingCondition });
            } else if (!isSupportedReturnType(condition)) {
                return ctx.createFailureStatus(new Object[] { Messages.vaidation_TimerEvent_WrongReturnType });
            }
        } else if (feature.equals(NotationPackage.Literals.VIEW__ELEMENT)) {
            final Object object = ctx.getFeatureNewValue();
            if (object instanceof AbstractTimerEvent) {
                final Expression condition = ((AbstractTimerEvent) object).getCondition();
                if (isMissingCondition((AbstractTimerEvent) object, condition)) {
                    return ctx.createFailureStatus(new Object[] { Messages.Validation_TimerEvent_MissingCondition });
                } else if (!isSupportedReturnType(condition)) {
                    return ctx.createFailureStatus(new Object[] { Messages.vaidation_TimerEvent_WrongReturnType });
                }
            }
        }

        return ctx.createSuccessStatus();
    }

    private boolean isSupportedReturnType(final Expression condition) {
        if (condition == null) {
            return false;
        }
        final String returnType = condition.getReturnType();
        return Long.class.getName().equals(returnType)
                || Date.class.getName().equals(returnType)
                || String.class.getName().equals(returnType);
    }

    private boolean isMissingCondition(final AbstractTimerEvent timerEvent, final Expression condition) {
        if (!(timerEvent instanceof StartTimerEvent && !((StartTimerEvent) timerEvent).getScriptType().equals(StartTimerScriptType.GROOVY))) {
            return condition == null || condition.getContent() == null || condition.getContent().isEmpty();
        }
        return false;
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final AbstractTimerEvent timerEvent = (AbstractTimerEvent) ctx.getTarget();
        final Expression condition = timerEvent.getCondition();
        if (isMissingCondition(timerEvent, condition)) {
            return ctx.createFailureStatus(new Object[] { Messages.Validation_TimerEvent_MissingCondition });
        } else if (!isSupportedReturnType(condition)) {
            return ctx.createFailureStatus(new Object[] { Messages.vaidation_TimerEvent_WrongReturnType });
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraint.Timer";
    }

}
