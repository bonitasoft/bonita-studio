/**
 * Copyright (C) 2010-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.validation.constraints.process;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Objects;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class FormMappingContentConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.constraints.formMappingContent";

    @Override
    protected String getConstraintId() {
        return ID;
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        checkArgument(eObj instanceof FormMapping);
        final FormMapping formMapping = (FormMapping) eObj;
        switch (formMapping.getType()) {
            case INTERNAL:
                return doValidateInternalMapping(ctx, formMapping);
            case URL:
                return doValidateURLMapping(ctx, formMapping);
            default:
                return ctx.createSuccessStatus();
        }
    }

    private IStatus doValidateInternalMapping(final IValidationContext ctx, final FormMapping formMapping) {
        final Expression targetForm = formMapping.getTargetForm();
        if (!targetForm.hasContent()) {
            if (formMapping.eContainer() instanceof MainProcess) {
                return ctx.createFailureStatus(Messages.formMappingAtDiagramLevel_ModelInconsistency);
            } else {
                return ctx.createFailureStatus(Messages.bind(Messages.emptyFormMappingWarning, formMappingType(formMapping)));
            }
        }
        return ctx.createSuccessStatus();
    }

    private String formMappingType(final FormMapping formMapping) {
        return Objects.equals(formMapping.eContainingFeature(), ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING) ? Messages.overviewFormMapping
                : formMapping.eContainer() instanceof Task ? Messages.entryFormMapping : Messages.instantiationFormMapping;
    }

    private IStatus doValidateURLMapping(final IValidationContext ctx, final FormMapping formMapping) {
        return isNullOrEmpty(formMapping.getUrl()) ? ctx.createFailureStatus(Messages.invalidURLFormMapping) : ctx
                .createSuccessStatus();
    }

}
