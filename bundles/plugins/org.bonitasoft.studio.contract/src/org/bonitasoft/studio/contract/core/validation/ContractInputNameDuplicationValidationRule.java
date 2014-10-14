/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.core.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.ecore.EObject;


/**
 * @author Romain Bioteau
 *
 */
public class ContractInputNameDuplicationValidationRule implements IValidationRule {

    protected static final String DUPLICATED_CONSTRAINT_ID = "duplicate";

    public ContractInputNameDuplicationValidationRule() {

    }

    @Override
    public boolean appliesTo(final EObject element) {
        return element instanceof Contract;
    }

    @Override
    public IStatus validate(final EObject element) {
        final Contract contract = (Contract) element;
        final MultiStatus status = new MultiStatus(ContractPlugin.PLUGIN_ID, IStatus.OK, "", null);

        final Set<String> result = new HashSet<String>();
        final Set<String> duplicated = new HashSet<String>();
        validateDuplicatedInputsRecursively(contract.getInputs(), duplicated, result);
        for (final String dup : duplicated) {
            status.add(ValidationStatus.error(dup));
        }
        return status;
    }

    private void validateDuplicatedInputsRecursively(final List<ContractInput> inputs, final Set<String> duplicated, final Set<String> result) {
        for (final ContractInput child : inputs) {
            if (child.getName() != null
                    && !child.getName().isEmpty()
                    && !result.add(child.getName())) {
                duplicated.add(child.getName());
            }
            validateDuplicatedInputsRecursively(child.getInputs(), duplicated, result);
        }
    }

    @Override
    public String getRuleId() {
        return DUPLICATED_CONSTRAINT_ID;
    }

}
