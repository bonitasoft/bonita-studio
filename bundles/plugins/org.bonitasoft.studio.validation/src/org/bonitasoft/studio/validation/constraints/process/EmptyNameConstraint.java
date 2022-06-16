/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
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
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.forbiddenCharactersValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;

import org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class EmptyNameConstraint extends AbstractLiveValidationMarkerConstraint {

    private static final int MAX_NAME_LENGTH = 255;

    @Override
    protected IStatus performLiveValidation(final IValidationContext ctx) {
        return doValidate(ctx);
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.nonemptynames";
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        return doValidate(ctx);
    }

    protected IStatus doValidate(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        checkArgument(eObj instanceof Element);
        final Element element = (Element) eObj;
        final String name = element.getName();
        if (isBlank(name)) {
            return elementCanHaveEmptyName(eObj) ? ctx.createSuccessStatus() : ctx.createFailureStatus(Messages.bind(Messages.emptynameMessage,
                    eClassName(eObj)));
        }
        return elementMustHaveARestrictedName(eObj) ? doValidateNameRestriction((Element) eObj, ctx) : ctx.createSuccessStatus();
    }

    private String eClassName(final EObject eObj) {
        return eObj
                .eClass().getName();
    }

    private IStatus doValidateNameRestriction(final Element element, final IValidationContext ctx) {
        final String inputName = String.format("%s %s", eClassName(element), Messages.elementName);
        final IStatus status = multiValidator()
                .addValidator(forbiddenCharactersValidator(inputName, '#', '%', '$'))
                .addValidator(maxLengthValidator(inputName, MAX_NAME_LENGTH))
                .addValidator(ValidatorFactory.reservedRESTAPIKeywordsValidator()).create().validate(element.getName());
        if (!status.isOK()) {
            return ctx.createFailureStatus(status.getMessage());
        }
        if (element instanceof Task && element.getName().contains(" :")) {
            return ctx.createFailureStatus(Messages.invalidColumnUsageInTaskName);
        }
        return ctx.createSuccessStatus();
    }

    private boolean isBlank(final String name) {
        return name == null || name.trim().isEmpty();
    }

    private boolean elementMustHaveARestrictedName(final EObject eObj) {
        return eObj instanceof SequenceFlow
                || eObj instanceof FlowElement;
    }

    private boolean elementCanHaveEmptyName(final EObject eObj) {
        return eObj instanceof SequenceFlow
                || eObj instanceof TextAnnotation;
    }


}
