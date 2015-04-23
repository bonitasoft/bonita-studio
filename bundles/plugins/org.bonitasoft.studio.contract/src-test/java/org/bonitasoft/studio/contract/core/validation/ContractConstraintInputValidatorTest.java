/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import org.bonitasoft.studio.contract.core.ContractConstraintUtil;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class ContractConstraintInputValidatorTest {

    private ContractConstraintInputValidator validator;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        validator = new ContractConstraintInputValidator();
    }

    @Test
    public void should_validate_returns_a_valid_status() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractInput input = addInput(contract, "name", ContractInputType.TEXT, null);
        final ContractConstraint constraint = ContractConstraintUtil.createConstraint("name length", "", "", input);
        contract.getConstraints().add(constraint);
        assertThat(validator.validate(constraint).isOK()).isTrue();
    }

    @Test
    public void should_validate_returns_an_error_status_for_empty_inputs() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractConstraint constraint = ContractConstraintUtil.createConstraint("name length", "", "");
        contract.getConstraints().add(constraint);
        assertThat(validator.validate(constraint).isOK()).isFalse();
    }

    @Test
    public void should_validate_returns_an_error_status_for_unknown_inputs() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractConstraint constraint = ContractConstraintUtil.createConstraint("name length", "", "");
        constraint.getInputNames().add("unknownInput");
        contract.getConstraints().add(constraint);
        assertThat(validator.validate(constraint).isOK()).isFalse();
    }

    private ContractInput addInput(final Contract contract, final String inputName, final ContractInputType type, final String description) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(inputName);
        contractInput.setType(type);
        contractInput.setDescription(description);
        contract.getInputs().add(contractInput);
        return contractInput;
    }

}
