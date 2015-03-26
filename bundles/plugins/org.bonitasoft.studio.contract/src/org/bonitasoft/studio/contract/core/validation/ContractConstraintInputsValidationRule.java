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
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintInputsValidationRule extends MessageValidationRule implements IValidationRule {

    protected static final String CONSTRAINT_INPUTS_ID = "constraint_inputs";


    @Override
    public boolean appliesTo(final EObject element) {
        return element instanceof ContractConstraint
                && ((ContractConstraint) element).getExpression() != null;
    }

    @Override
    public IStatus validate(final EObject element) {
        final ContractConstraint constraint = (ContractConstraint) element;
        final EList<String> inputNames = constraint.getInputNames();
        if (inputNames.isEmpty()) {
            return ValidationStatus.error(Messages.bind(Messages.noInputReferencedInConstraintExpression, constraint.getName()));
        }
        final Contract contract = ModelHelper.getFirstContainerOfType(constraint, Contract.class);
        final Set<String> existingInputNames = getAllInputNames(contract);
        final MultiStatus status = new MultiStatus(ContractPlugin.PLUGIN_ID, IStatus.OK, "", null);
        for (final String name : inputNames) {
            if (!existingInputNames.contains(name)) {
                status.add(ValidationStatus.error(Messages.bind(Messages.unknownInputReferencedInConstraintExpression, name, constraint.getName())));
            }
        }
        return status;
    }

    private Set<String> getAllInputNames(final Contract contract) {
        final Set<String> result = new HashSet<String>();
        final TreeIterator<EObject> treeIterator = contract.eAllContents();
        while (treeIterator.hasNext()) {
            final EObject eObject = treeIterator.next();
            if (eObject instanceof ContractInput) {
                result.add(((ContractInput) eObject).getName());
            }
        }
        return result;
    }

    @Override
    public String getMessage(final IStatus status) {
        Assert.isLegal(status != null);
        if (status.isMultiStatus() && status.getChildren().length > 0) {
            StringBuilder errorMessage = new StringBuilder();
            for (final IStatus child : status.getChildren()) {
                errorMessage.append(child.getMessage());
                errorMessage.append(SWT.CR);
            }
            errorMessage = errorMessage.delete(errorMessage.length() - 1, errorMessage.length());
            return errorMessage.toString();
        }
        return status.getMessage();
    }

    @Override
    public String getRuleId() {
        return CONSTRAINT_INPUTS_ID;
    }


}
