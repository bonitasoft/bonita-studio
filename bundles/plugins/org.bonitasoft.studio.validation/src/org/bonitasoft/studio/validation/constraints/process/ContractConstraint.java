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

import static com.google.common.base.Preconditions.checkArgument;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.contract.core.validation.ContractConstraintInputValidator;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class ContractConstraint extends AbstractLiveValidationMarkerConstraint {

    private final ContractConstraintInputValidator validator;

    public ContractConstraint() {
        validator = new ContractConstraintInputValidator();
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        checkArgument(eObj instanceof org.bonitasoft.studio.model.process.ContractConstraint);
        final IStatus status = validator.validate(eObj);
        if (!status.isOK()) {
            return ctx.createFailureStatus(Messages.bind(Messages.invalidContractConstraintDefinition, contractContainer(eObj).getName(),
                    status.getMessage()));
        }
        return ctx.createSuccessStatus();
    }

    private Element contractContainer(final EObject constraint) {
        return (Element) ModelHelper.getFirstContainerOfType(constraint, ContractContainer.class);
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.contract";
    }

}
