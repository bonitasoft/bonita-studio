/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.fileNameValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.forbiddenCharactersValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.utf8InputValidator;

import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.studio.common.databinding.validator.MultiValidatorFactory;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * Validates the "version" attribute of an {@link AbstractProcess}
 * 
 * @author Vincent Hemery
 */
public class AbstractProcessVersionConstraint extends AbstractLiveValidationMarkerConstraint {

    private static final int MAX_VERSION_LENGTH = 50;

    /**
     * Get the multi-validator that will check the version of an {@link AbstractProcess}
     * 
     * @param elementsEClass the element's EClass (to deduce validation rules)
     * @return multi-validator to use
     */
    public static IValidator<String> versionValidator(EClass elementsEClass) {
        checkArgument(ProcessPackage.Literals.ABSTRACT_PROCESS.isSuperTypeOf(elementsEClass));
        // version validation should be the same for Diagram and Pool
        MultiValidatorFactory factory = multiValidator()
                .addValidator(mandatoryValidator(Messages.processVersion))
                .addValidator(
                        maxLengthValidator(Messages.processVersion, MAX_VERSION_LENGTH))
                .addValidator(fileNameValidator(Messages.processVersion))
                .addValidator(utf8InputValidator(Messages.processVersion))
                .addValidator(forbiddenCharactersValidator(Messages.processVersion, '#', '%', '$'));
        return factory.create();
    }

    @Override
    protected IStatus performBatchValidation(IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        checkArgument(eObj instanceof AbstractProcess);
        final AbstractProcess process = (AbstractProcess) eObj;
        final String version = process.getVersion();
        IValidator<String> validator = versionValidator(process.eClass());
        final IStatus status = validator.validate(version);
        if (!status.isOK()) {
            return ctx.createFailureStatus(status.getMessage());
        } else {
            return ctx.createSuccessStatus();
        }
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.version";
    }

}
