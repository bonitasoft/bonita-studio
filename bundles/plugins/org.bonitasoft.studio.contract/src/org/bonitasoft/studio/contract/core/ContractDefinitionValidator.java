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

import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
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

    private final IMessageManager messageManager;

    public ContractDefinitionValidator() {
        this(null);
    }

    public ContractDefinitionValidator(final IMessageManager messageManager) {
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
            }
            return status;
        }
        return ValidationStatus.ok();
    }

    public IStatus validateInputName(final ContractInput input, final String newName) {
        final IStatus status = new GroovyReferenceValidator(Messages.name, true).validate(newName);
        if (messageManager != null) {
            updateMessage(input, status);
        }

        return status;
    }

    private void updateMessage(final Object element, final IStatus status) {
        if (!status.isOK()) {
            messageManager.addMessage(element, status.getMessage(), null, toMessageSeverity(status.getSeverity()));
        } else {
            messageManager.removeMessage(element);
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
                throw new RuntimeException("Unsupported status severity code :" + statusSeverity);
        }

    }

}
