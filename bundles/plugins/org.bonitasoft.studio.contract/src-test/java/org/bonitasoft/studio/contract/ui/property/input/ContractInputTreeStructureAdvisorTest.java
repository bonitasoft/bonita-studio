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
package org.bonitasoft.studio.contract.ui.property.input;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.Contract;
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
public class ContractInputTreeStructureAdvisorTest {

    private ContractInputTreeStructureAdvisor contractInputTreeStructureAdvisor;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contractInputTreeStructureAdvisor = new ContractInputTreeStructureAdvisor();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getParent_return_eContainer() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        final ContractInput childContractInput = ProcessFactory.eINSTANCE.createContractInput();
        contract.getInputs().add(contractInput);
        contractInput.getInputs().add(childContractInput);
        assertThat(contractInputTreeStructureAdvisor.getParent(contractInput)).isEqualTo(contract);
        assertThat(contractInputTreeStructureAdvisor.getParent(childContractInput)).isEqualTo(contractInput);
    }

    @Test
    public void should_hasChildren_return_if_contract_input_has_children() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(ContractInputType.COMPLEX);
        final ContractInput childContractInput = ProcessFactory.eINSTANCE.createContractInput();
        contract.getInputs().add(contractInput);
        contractInput.getInputs().add(childContractInput);
        assertThat(contractInputTreeStructureAdvisor.hasChildren(contract)).isTrue();
        assertThat(contractInputTreeStructureAdvisor.hasChildren(contractInput)).isTrue();
        assertThat(contractInputTreeStructureAdvisor.hasChildren(childContractInput)).isFalse();
        assertThat(contractInputTreeStructureAdvisor.hasChildren(null)).isFalse();
        assertThat(contractInputTreeStructureAdvisor.hasChildren(ProcessFactory.eINSTANCE.createContract())).isFalse();

        contractInput.setType(ContractInputType.INTEGER);
        assertThat(contractInputTreeStructureAdvisor.hasChildren(contractInput)).isFalse();
    }
}
