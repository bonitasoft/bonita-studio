/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints.form;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Baptiste Mesta
 */
public class ValidatorErrorMessageConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performLiveValidation(final IValidationContext ctx) {
        final EStructuralFeature feature = ctx.getFeature();
        if (feature.equals(FormPackage.Literals.VALIDATOR__DISPLAY_NAME)) {
            final Expression errorMessage = (Expression) ctx.getFeatureNewValue();
            if (!ModelHelper.isAnExpressionCopy(errorMessage)) {
                if (errorMessage == null || errorMessage.getContent() == null || errorMessage.getContent().isEmpty()) {
                    return ctx.createFailureStatus(new Object[] { Messages.Validation_Validator_EmptyErrorMessage });
                }
            }
        } else if (feature.equals(FormPackage.Literals.VALIDABLE__VALIDATORS) && ctx.getEventType().equals(EMFEventType.ADD)) {
            final Object validator = ctx.getFeatureNewValue();
            if (validator instanceof Validator) {
                final Validator v = (Validator) validator;
                if (v.getDisplayName() == null || v.getDisplayName().getContent() == null || v.getDisplayName().getContent().isEmpty()) {
                    return ctx.createFailureStatus(new Object[] { Messages.Validation_Validator_EmptyErrorMessage });
                }
            }

        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject target = ctx.getTarget();
        if (target instanceof Validator) {
            final Validator v = (Validator) target;
            if (v.getDisplayName() == null || v.getDisplayName().getContent() == null || v.getDisplayName().getContent().isEmpty()) {
                return ctx.createFailureStatus(new Object[] { Messages.Validation_Validator_EmptyErrorMessage + " " + v.getName() });
            }
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraint.ValidatorErrorMessage";
    }

}
