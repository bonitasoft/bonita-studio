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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Iterables.transform;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getAllElementOfTypeIn;

import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

import com.google.common.base.Function;
import com.google.common.collect.Sets;

/**
 * @author Romain Bioteau
 */
public class ContractConstraintInputValidator implements IValidator {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(final Object value) {
        checkArgument(value instanceof ContractConstraint);
        final ContractConstraint constraint = (ContractConstraint) value;
        final List<String> constraintInputNames = constraint.getInputNames();
        if (constraintInputNames.isEmpty()) {
            return ValidationStatus.error(Messages.bind(Messages.noInputReferencedInConstraintExpression, constraint.getName()));
        }
        final Set<String> existingInputNames = allContractInputNames(ModelHelper.getFirstContainerOfType(constraint, Contract.class));
        final MultiStatus status = new MultiStatus(ContractPlugin.PLUGIN_ID, IStatus.OK, "", null);
        for (final String name : constraintInputNames) {
            if (!existingInputNames.contains(name)) {
                status.add(ValidationStatus.error(Messages.bind(Messages.unknownInputReferencedInConstraintExpression, name, constraint.getName())));
            }
        }
        return status;
    }

    private Iterable<ContractInput> allContractInput(final Contract contract) {
        return getAllElementOfTypeIn(contract, ContractInput.class);
    }

    private Set<String> allContractInputNames(final Contract contract) {
        return Sets.newHashSet(transform(allContractInput(contract), toInputName()));
    }

    private Function<ContractInput, String> toInputName() {
        return new Function<ContractInput, String>() {

            @Override
            public String apply(final ContractInput input) {
                return input.getName();
            }
        };
    }

}
