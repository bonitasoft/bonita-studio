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

import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintNameValidationRuleTest {

    private ContractConstraintNameValidationRule validationRule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        validationRule = new ContractConstraintNameValidationRule();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_appliesTo_contract_constraint() throws Exception {
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        assertThat(validationRule.appliesTo(constraint)).isTrue();
        assertThat(validationRule.appliesTo(ProcessFactory.eINSTANCE.createContractInput())).isFalse();
    }

    @Test
    public void should_validate_contract_constraint_name_return_a_valid_status() throws Exception {
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setName("legal age");
        assertThat(validationRule.validate(constraint).isOK()).isTrue();
    }

    @Test
    public void should_validate_contract_constraint_name_return_an_error_status_when_name_is_too_long() throws Exception {
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setName("legal age jqsdhkjqs dkjqsdh kjqshkjqsdhkjqsdhkjqsdh kjqsdhqkjhkjqsdhkjqh");
        assertThat(validationRule.validate(constraint).isOK()).isFalse();
    }

    @Test
    public void should_validate_contract_constraint_name_return_an_error_status_when_name_is_empty() throws Exception {
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setName("");
        assertThat(validationRule.validate(constraint).isOK()).isFalse();
    }

}
