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
package org.bonitasoft.studio.contract.core;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.databinding.MultiValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.IMessageManager;


/**
 * @author Romain Bioteau
 *
 */
public class ContractDefinitionValidator {

    protected static final String DESCRIPTION_CONSTRAINT_ID = "description";
    protected static final String DUPLICATED_CONSTRAINT_ID = "duplicate";
    protected static final String NAME_CONSTRAINT_ID = "name";

    private final IMessageManager messageManager;
    private final MultiValidator inputNameValidator;
    private static Set<String> inputConstraints;
    static {
        inputConstraints = new HashSet<String>();
        inputConstraints.add(NAME_CONSTRAINT_ID);
        inputConstraints.add(DUPLICATED_CONSTRAINT_ID);
        inputConstraints.add(DESCRIPTION_CONSTRAINT_ID);
    }

    public ContractDefinitionValidator() {
        this(null);
    }

    public ContractDefinitionValidator(final IMessageManager messageManager) {
        inputNameValidator = new MultiValidator();
        inputNameValidator.addValidator(new GroovyReferenceValidator(Messages.name, true));
        inputNameValidator.addValidator(new InputLengthValidator(Messages.name, 50));
        this.messageManager = messageManager;
    }

    public IStatus validate(final Contract contract) {
        if (messageManager != null) {
            messageManager.removeAllMessages();
        }
        if (contract != null) {
            final MultiStatus status = new MultiStatus(ContractPlugin.PLUGIN_ID, IStatus.OK, "", null);
            for (final ContractInput input : contract.getInputs()) {
                status.add(validateInputName(input, input.getName()));
                status.add(validateInputDescription(input, input.getDescription()));
            }
            status.addAll(validateDuplicatedInputs(contract));
            return status;
        }
        return ValidationStatus.ok();
    }

    public IStatus validateDuplicatedInputs(final Contract contract) {
        final MultiStatus status = new MultiStatus(ContractPlugin.PLUGIN_ID, IStatus.OK, "", null);
        if (contract != null) {
            final Set<String> result = new HashSet<String>();
            final Set<String> duplicated = new HashSet<String>();
            for (final ContractInput in : contract.getInputs()) {
                if (in.getName() != null && !in.getName().isEmpty() && !result.add(in.getName())) {
                    duplicated.add(in.getName());
                }
                validateDuplicatedInputsRecursively(in, duplicated, result);
            }
            for (final String dup : duplicated) {
                status.add(ValidationStatus.error(dup));
            }
            if (messageManager != null) {
                updateMessage(contract, DUPLICATED_CONSTRAINT_ID, status);
            }
        }
        return status;
    }

    private void validateDuplicatedInputsRecursively(final ContractInput in, final Set<String> duplicated, final Set<String> result) {
        for (final ContractInput child : in.getInputs()) {
            if (child.getName() != null && !child.getName().isEmpty() && !result.add(child.getName())) {
                duplicated.add(child.getName());
            }
            validateDuplicatedInputsRecursively(child, duplicated, result);
        }
    }

    public IStatus validateInputDescription(final ContractInput input, final String description) {
        final IStatus status = new InputLengthValidator(Messages.description, 255).validate(description);
        if (messageManager != null) {
            updateMessage(input, DESCRIPTION_CONSTRAINT_ID, status);
        }

        return status;
    }

    public IStatus validateInputName(final ContractInput input, final String newName) {
        final IStatus status = inputNameValidator.validate(newName);
        if (messageManager != null) {
            updateMessage(input, NAME_CONSTRAINT_ID, status);
        }

        return status;
    }

    private void updateMessage(final Object element, final String constraintID, final IStatus status) {
        final Pair<Object, String> messageKey = new org.bonitasoft.studio.common.Pair<Object, String>(element, constraintID);
        messageManager.removeMessage(messageKey);
        if (!status.isOK()) {
            if (status.isMultiStatus()) {
                final StringBuilder errorMessage = new StringBuilder(Messages.duplicatedInputNames);
                errorMessage.append(" ");
                for (final IStatus child : status.getChildren()) {
                    errorMessage.append("\"" + child.getMessage() + "\"");
                    errorMessage.append(", ");
                }
                String error = errorMessage.toString();
                if (error.endsWith(", ")) {
                    error = error.substring(0, error.length() - 2);
                }
                messageManager.addMessage(messageKey,error, null, toMessageSeverity(status.getSeverity()));

            } else {
                messageManager.addMessage(messageKey, status.getMessage(), null, toMessageSeverity(status.getSeverity()));
            }
        }
    }

    protected int toMessageSeverity(final int statusSeverity) {
        switch (statusSeverity) {
            case IStatus.OK:
                return IMessage.NONE;
            case IStatus.ERROR:
                return IMessage.ERROR;
            case IStatus.WARNING:
                return IMessage.WARNING;
            case IStatus.INFO:
                return IMessage.INFORMATION;
            default:
                throw new IllegalArgumentException("Unsupported status severity code :" + statusSeverity);
        }

    }

    public void clearMessages(final Object element) {
        if (messageManager != null) {
            for (final String constraintID : inputConstraints) {
                final Pair<Object, String> messageKey = new org.bonitasoft.studio.common.Pair<Object, String>(element, constraintID);
                messageManager.removeMessage(messageKey);
            }
        }
    }

}
