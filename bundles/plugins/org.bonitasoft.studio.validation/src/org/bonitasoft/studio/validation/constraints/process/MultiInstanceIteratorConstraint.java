/**
 * Copyright (C) 2013 BonitaSoft S.A.
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

import static com.google.common.base.Preconditions.checkArgument;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.groovyReferenceValidator;

import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class MultiInstanceIteratorConstraint extends AbstractLiveValidationMarkerConstraint {

    private static final String CONSTRAINT_ID = "org.bonitasoft.studio.validation.multiInstanceIterator";

    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        return doValidate(context);
    }

    protected IStatus doValidate(final IValidationContext context) {
        final EObject eObj = context.getTarget();
        checkArgument(eObj instanceof MultiInstantiable);

        final MultiInstantiable multiInstantiable = (MultiInstantiable) eObj;
        if (isMultiInstantied(multiInstantiable)
                && !multiInstantiable.isUseCardinality()) {
            final IStatus status = groovyReferenceValidator("").startsWithLowerCase().create().validate(multiInstantiable.getIteratorExpression().getName());
            return status.isOK() ? context.createSuccessStatus() :
                    context.createFailureStatus(status.getMessage());
        }
        return context.createSuccessStatus();
    }

    private boolean isMultiInstantied(final MultiInstantiable multiInstantiable) {
        return multiInstantiable.getType() == MultiInstanceType.PARALLEL
                || multiInstantiable.getType() == MultiInstanceType.SEQUENTIAL;
    }

    @Override
    protected String getConstraintId() {
        return CONSTRAINT_ID;
    }

}
