/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withMultipleInHierarchy;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

public class DocumentWithContractInputContentConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.documentWithContract";
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        checkArgument(eObj instanceof Document);
        final Document document = (Document) eObj;
        if (document.getDocumentType().equals(org.bonitasoft.studio.model.process.DocumentType.CONTRACT)) {
            final ContractInput contractInput = document.getContractInput();
            if (contractInput == null) {
                return ctx.createFailureStatus(Messages.bind(Messages.missingFileContractInput, document.getName()));
            }
            if (contractInput.getType() != ContractInputType.FILE) {
                return ctx
                        .createFailureStatus(Messages.bind(Messages.invalidFileContractInputType, document.getName(), contractInput.getType().name()));
            }
            final boolean hasMultipleInHierachy = withMultipleInHierarchy().apply(contractInput);
            if (hasMultipleInHierachy && !document.isMultiple()) {
                return ctx.createFailureStatus(Messages.bind(Messages.invalidMultipleFileContractInput, document.getName()));
            }
            if (!hasMultipleInHierachy && document.isMultiple()) {
                return ctx.createFailureStatus(Messages.bind(Messages.invalidSingleFileContractInput, document.getName()));
            }
        }
        return ctx.createSuccessStatus();
    }
}
