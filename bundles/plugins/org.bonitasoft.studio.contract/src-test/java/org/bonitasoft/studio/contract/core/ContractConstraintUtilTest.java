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

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintUtilTest {

    private final ProcessFactory factory = ProcessFactory.eINSTANCE;
    private Contract contract;
    private ContractInput ageInput;
    private ContractInput nameInput;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contract = factory.createContract();
        ageInput = factory.createContractInput();
        ageInput.setName("age");
        ageInput.setType(ContractInputType.INTEGER);
        nameInput = factory.createContractInput();
        nameInput.setName("name");
        nameInput.setType(ContractInputType.TEXT);
        contract.getInputs().add(ageInput);
        contract.getInputs().add(nameInput);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_retrieve_constraints_for_the_given_input() throws Exception {
        final ContractConstraint c1 = ContractConstraintUtil.createConstraint("c1", "age > 18", "Must be an adult", ageInput);
        final ContractConstraint c2 = ContractConstraintUtil.createConstraint("c2", "name.lenght < 300", "Name is too long", nameInput);
        final ContractConstraint c3 = ContractConstraintUtil.createConstraint("c3", "!name.isEmpty() && age != 0", "Consistent input", nameInput, ageInput);
        contract.getConstraints().add(c1);
        contract.getConstraints().add(c2);
        contract.getConstraints().add(c3);
        assertThat(ContractConstraintUtil.getConstraintsForInput(contract, ageInput)).containsOnly(c1, c3);
        assertThat(ContractConstraintUtil.getConstraintsForInput(contract, nameInput)).containsOnly(c2, c3);
    }
}
