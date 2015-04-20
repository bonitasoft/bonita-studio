/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.core.validation;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.jface.databinding.MultiValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public class ContractInputTypeValidationRule extends MessageValidationRule implements IValidationRule {

    protected static final String TYPE_CONSTRAINT_ID = "input_type";

    private final MultiValidator inputNameValidator;

    private static Set<ContractInputType> unsupportedRuntimeTypesForBeta;
    static {
        unsupportedRuntimeTypesForBeta = new HashSet<ContractInputType>();
        unsupportedRuntimeTypesForBeta.add(ContractInputType.BOOLEAN);
        unsupportedRuntimeTypesForBeta.add(ContractInputType.INTEGER);
        unsupportedRuntimeTypesForBeta.add(ContractInputType.DECIMAL);
        unsupportedRuntimeTypesForBeta.add(ContractInputType.DATE);
    }

    public ContractInputTypeValidationRule() {
        inputNameValidator = new MultiValidator();
        inputNameValidator.addValidator(new GroovyReferenceValidator(Messages.name, true));
        inputNameValidator.addValidator(new InputLengthValidator(Messages.name, 50));
    }

    @Override
    public boolean appliesTo(final EObject element) {
        return element instanceof ContractInput;
    }

    @Override
    public IStatus validate(final EObject element) {
        final ContractInput input = (ContractInput) element;
        if (unsupportedRuntimeTypesForBeta.contains(input.getType())) {
            return ValidationStatus.warning("For 7.0 Beta, only Text and Complex can be used at runtime.");
        }
        return ValidationStatus.ok();
    }

    @Override
    public String getRuleId() {
        return TYPE_CONSTRAINT_ID;
    }

}
