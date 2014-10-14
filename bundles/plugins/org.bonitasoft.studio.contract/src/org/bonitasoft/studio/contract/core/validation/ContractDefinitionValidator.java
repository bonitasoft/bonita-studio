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

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.IMessageManager;


/**
 * @author Romain Bioteau
 *
 */
public class ContractDefinitionValidator {




    private final IMessageManager messageManager;
    private final Set<IValidationRule> validationRules;

    public ContractDefinitionValidator() {
        this(null);
    }

    public ContractDefinitionValidator(final IMessageManager messageManager) {
        this.messageManager = messageManager;
        validationRules = new HashSet<IValidationRule>();
        validationRules.add(new ContractInputNameValidationRule());
        validationRules.add(new ContractInputDescriptionValidationRule());
        validationRules.add(new ContractInputNameDuplicationValidationRule());
        validationRules.add(new ContractConstraintNameValidationRule());
        validationRules.add(new ContractConstraintDuplicationValidationRule());
        validationRules.add(new ContractConstraintInputsValidationRule());
    }

    public IStatus validate(final Contract contract) {
        if (messageManager != null) {
            messageManager.removeAllMessages();
        }
        if (contract != null) {
            final MultiStatus status = new MultiStatus(ContractPlugin.PLUGIN_ID, IStatus.OK, "", null);
            final TreeIterator<EObject> iterator = contract.eAllContents();
            while (iterator.hasNext()) {
                final EObject eObject = iterator.next();
                for (final IValidationRule rule : validationRules) {
                    if (rule.appliesTo(eObject)) {
                        final IStatus iStatus = rule.validate(eObject);
                        if (messageManager != null) {
                            updateMessage(eObject, rule.getRuleId(), iStatus);
                        }
                        status.add(iStatus);
                    }
                }
            }
            for (final IValidationRule rule : validationRules) {
                if (rule.appliesTo(contract)) {
                    final IStatus iStatus = rule.validate(contract);
                    if (messageManager != null) {
                        updateMessage(contract, rule.getRuleId(), iStatus);
                    }
                    status.add(iStatus);
                }
            }
            return status;
        }
        return ValidationStatus.ok();
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
                error = error.substring(0, error.length() - 2);
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
            for (final IValidationRule rule : validationRules) {
                final Pair<Object, String> messageKey = new org.bonitasoft.studio.common.Pair<Object, String>(element, rule.getRuleId());
                messageManager.removeMessage(messageKey);
            }
        }
    }


}
