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

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintDuplicationValidationRuleTest {

    private ContractConstraintDuplicationValidationRule validationRule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        validationRule = new ContractConstraintDuplicationValidationRule();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_appliesTo_contract() throws Exception {
        assertThat(validationRule.appliesTo(ProcessFactory.eINSTANCE.createContractConstraint())).isFalse();
        assertThat(validationRule.appliesTo(ProcessFactory.eINSTANCE.createContract())).isTrue();
    }

    @Test
    public void should_validate_contract_return_a_valid_status_if_no_duplicated_constraint() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setName("legal age");
        contract.getConstraints().add(constraint);
        final ContractConstraint constraint2 = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint2.setName("name length");
        contract.getConstraints().add(constraint2);
        assertThat(validationRule.validate(contract).isOK()).isTrue();
    }

    @Test
    public void should_validate_contract_return_an_error_status_if_duplicated_constraint() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setName("legal age");
        contract.getConstraints().add(constraint);
        final ContractConstraint constraint2 = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint2.setName("legal age");
        contract.getConstraints().add(constraint2);
        assertThat(validationRule.validate(contract).isOK()).isFalse();
    }



}
