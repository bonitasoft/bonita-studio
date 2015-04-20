/**
 * t * Copyright (C) 2014 BonitaSoft S.A.
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.IMessageManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractDefinitionValidatorTest {

    private ContractDefinitionValidator validator;

    @Mock
    private IMessageManager messageManager;

    private ContractDefinitionValidator validatorWithMessageManager;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        validator = new ContractDefinitionValidator();
        validatorWithMessageManager = new ContractDefinitionValidator(messageManager);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_validate_a_contract_returns_a_valid_status_for_empty_contract() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        assertThat(validator.validate(contract)).isNotNull();
    }

    @Test
    public void should_validate_a_contract_returns_a_valid_status_for_a_null_contract() throws Exception {
        assertThat(validator.validate((Contract) null)).isNotNull();
    }

    @Test
    public void should_validate_a_contract_returns_a_valid_status_for_contract_with_valid_input() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        addInput(contract, "name", ContractInputType.TEXT, null);
        final IStatus status = validator.validate(contract);
        assertThat(status).isNotNull();
        assertThat(status.isOK()).isTrue();
    }

    @Test
    public void should_validate_a_contract_returns_an_error_status_for_contract_with_empty_input_name() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        addInput(contract, "", ContractInputType.TEXT, null);
        final IStatus status = validator.validate(contract);
        assertThat(status.isOK()).isFalse();
    }

    @Test
    public void should_validate_a_contract_returns_an_error_status_for_contract_with_duplicated_input_names() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        addInput(contract, "name", ContractInputType.TEXT, null);
        addInput(contract, "name", ContractInputType.DECIMAL, null);
        final IStatus status = validator.validate(contract);
        assertThat(status.isOK()).isFalse();
    }

    @Test
    public void should_validate_a_contract_returns_an_error_status_for_contract_with_duplicated_input_names_and_create_an_error_message() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        addInput(contract, "name", ContractInputType.TEXT, null);
        addInput(contract, "name", ContractInputType.DECIMAL, null);
        addInput(contract, "toto", ContractInputType.TEXT, null);
        addInput(contract, "toto", ContractInputType.DECIMAL, null);
        final IStatus status = validatorWithMessageManager.validate(contract);
        assertThat(status.isOK()).isFalse();
        verify(messageManager).addMessage(eq(new Pair<Object, String>(contract, ContractInputNameDuplicationValidationRule.DUPLICATED_CONSTRAINT_ID)),
                anyString(), eq(null), eq(IMessage.ERROR));
    }

    @Test
    public void should_validate_a_contract_returns_an_error_status_for_contract_with_invalid_input_and_add_an_error_message() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractInput input = addInput(contract, "", ContractInputType.TEXT, null);
        final IStatus status = validatorWithMessageManager.validate(contract);
        assertThat(status.isOK()).isFalse();
        verify(messageManager).removeAllMessages();
        final Pair<Object, String> key = new Pair<Object, String>(input, ContractInputNameValidationRule.NAME_CONSTRAINT_ID);
        verify(messageManager).addMessage(eq(key), anyString(), eq(null),
                eq(validatorWithMessageManager.toMessageSeverity(status.getSeverity())));

        input.setName("name");
        validatorWithMessageManager.validate(contract);
        verify(messageManager, times(2)).removeMessage(key);
    }

    @Test
    public void should_validate_a_contract_returns_all_errors_status_for_contract_with_multiple_invalid_input() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractInput input = addInput(contract, "", ContractInputType.TEXT, null);
        addInput(input, "", ContractInputType.TEXT, null);
        final ContractInput input3 = addInput(contract, null, ContractInputType.TEXT, null);
        addInput(input3, null, ContractInputType.TEXT, null);
        final ContractInput input2 = addInput(contract, "name", ContractInputType.TEXT, null);
        addInput(input2, "name", ContractInputType.TEXT, null);
        addInput(input2, "name", ContractInputType.TEXT, null);
        final IStatus status = validator.validate(contract);
        assertThat(status.isOK()).isFalse();
        assertThat(status).isInstanceOf(MultiStatus.class);
    }

    @Test
    public void should_toMessageSeverity_convert_a_status_severity_into_an_IMessage_severity() throws Exception {
        assertThat(validator.toMessageSeverity(IStatus.OK)).isEqualTo(IMessage.NONE);
        assertThat(validator.toMessageSeverity(IStatus.WARNING)).isEqualTo(IMessage.WARNING);
        assertThat(validator.toMessageSeverity(IStatus.ERROR)).isEqualTo(IMessage.ERROR);
        assertThat(validator.toMessageSeverity(IStatus.INFO)).isEqualTo(IMessage.INFORMATION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_toMessageSeverity_throw_a_RuntimeException_for_invalid_status_code() throws Exception {
        validator.toMessageSeverity(5);
    }

    @Test
    public void should_clearMessage_removeAllMessage_for_given_element() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractInput input = addInput(
                contract,
                "name",
                ContractInputType.TEXT,
                null);
        validatorWithMessageManager.clearMessages(input);
        verify(messageManager).removeMessage(new Pair<Object, String>(input, ContractInputNameValidationRule.NAME_CONSTRAINT_ID));
        verify(messageManager).removeMessage(new Pair<Object, String>(input, ContractInputNameDuplicationValidationRule.DUPLICATED_CONSTRAINT_ID));
        verify(messageManager).removeMessage(new Pair<Object, String>(input, ContractInputDescriptionValidationRule.DESCRIPTION_CONSTRAINT_ID));
    }

    @Test
    public void should_clearMessage_doNothing() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractInput input = addInput(
                contract,
                "name",
                ContractInputType.TEXT,
                null);
        validator.clearMessages(input);
        verifyZeroInteractions(messageManager);
    }

    private ContractInput addInput(final Contract contract, final String inputName, final ContractInputType type, final String description) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(inputName);
        contractInput.setType(type);
        contractInput.setDescription(description);
        contract.getInputs().add(contractInput);
        return contractInput;
    }

    private ContractInput addInput(final ContractInput parentInput, final String inputName, final ContractInputType type, final String description) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(inputName);
        contractInput.setType(type);
        contractInput.setDescription(description);
        parentInput.getInputs().add(contractInput);
        return contractInput;
    }

}
