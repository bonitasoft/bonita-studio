/**
 * Copyright (C) 2014-2015 BonitaSoft S.A.
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

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.contract.ContractPlugin;
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
 */
public class ContractDefinitionValidator {

    private final IMessageManager messageManager;
    private final Set<IValidationRule> validationRules;

    public ContractDefinitionValidator() {
        this(new DefaultMessageManager());
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
        validationRules.add(new ContractConstraintExpressionValidationRule());
        validationRules.add(new ContractInputTypeValidationRule());
    }

    public IStatus validate(final Contract contract) {
        messageManager.removeAllMessages();
        if (contract != null) {
            final MultiStatus status = new MultiStatus(ContractPlugin.PLUGIN_ID, IStatus.OK, "", null);
            validateApllicableRulesOnChildrenOf(contract, status);
            validateApplicableRuleOn(status, contract);
            return status;
        }
        return ValidationStatus.ok();
    }

    protected void validateApllicableRulesOnChildrenOf(final Contract contract, final MultiStatus status) {
        final TreeIterator<EObject> iterator = contract.eAllContents();
        while (iterator.hasNext()) {
            final EObject eObject = iterator.next();
            validateApplicableRuleOn(status, eObject);
        }
    }

    protected void validateApplicableRuleOn(final MultiStatus status, final EObject eObject) {
        for (final IValidationRule rule : validationRules) {
            if (rule.appliesTo(eObject)) {
                final IStatus iStatus = rule.validate(eObject);
                updateMessage(eObject, rule, iStatus);
                status.add(iStatus);
            }
        }
    }

    private void updateMessage(final Object element, final IValidationRule rule, final IStatus status) {
        final Pair<Object, String> messageKey = new org.bonitasoft.studio.common.Pair<Object, String>(element, rule.getRuleId());
        messageManager.removeMessage(messageKey);
        if (!status.isOK()) {
            messageManager.addMessage(messageKey, rule.getMessage(status), null, toMessageSeverity(status.getSeverity()));
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
        for (final IValidationRule rule : validationRules) {
            final Pair<Object, String> messageKey = new org.bonitasoft.studio.common.Pair<Object, String>(element, rule.getRuleId());
            messageManager.removeMessage(messageKey);
        }
    }

}
