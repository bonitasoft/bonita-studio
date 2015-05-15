/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.converter;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.all;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import java.util.Date;

import org.bonitasoft.studio.designer.core.converter.ToWebContract;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.web.designer.model.contract.Contract;
import org.bonitasoft.web.designer.model.contract.ContractType;
import org.bonitasoft.web.designer.model.contract.NodeContractInput;
import org.junit.Test;

public class ToWebContractTest {

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_TASK_type() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract().build());

        assertThat(contract.getContractType()).isEqualTo(ContractType.TASK);
    }

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_PROCESS_type() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract().in(aPool()).build());

        assertThat(contract.getContractType()).isEqualTo(ContractType.PROCESS);
    }

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_input_with_a_name() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract()
                .havingInput(aContractInput().withName("firstName").withType(ContractInputType.TEXT)).build());

        assertThat(contract.getInput()).extracting("name").containsExactly("firstName");
    }

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_input_with_description() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract()
                .havingInput(aContractInput().withDescription("a simple desc").withType(ContractInputType.TEXT)).build());

        assertThat(contract.getInput()).extracting("description").containsExactly("a simple desc");
    }

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_string_input() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract()
                .havingInput(aContractInput().withName("firstName").withType(ContractInputType.TEXT)).build());

        assertThat(contract.getInput()).extracting("type").containsExactly(String.class.getName());
    }

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_multiple_input() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract()
                .havingInput(aContractInput().withType(ContractInputType.TEXT).multiple()).build());

        assertThat(contract.getInput()).extracting("multiple").containsExactly(true);
    }

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_INTEGER_input() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract()
                .havingInput(aContractInput().withType(ContractInputType.INTEGER)).build());

        assertThat(contract.getInput()).extracting("type").containsExactly(Long.class.getName());
    }

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_DATE_input() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract()
                .havingInput(aContractInput().withType(ContractInputType.DATE)).build());

        assertThat(contract.getInput()).extracting("type").containsExactly(Date.class.getName());
    }

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_BOOLEAN_input() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract()
                .havingInput(aContractInput().withType(ContractInputType.BOOLEAN)).build());

        assertThat(contract.getInput()).extracting("type").containsExactly(Boolean.class.getName());
    }

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_DECIMAL_input() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract()
                .havingInput(aContractInput().withType(ContractInputType.DECIMAL)).build());

        assertThat(contract.getInput()).extracting("type").containsExactly(Double.class.getName());
    }

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_COMPLEX_input() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract()
                .havingInput(aContractInput().withType(ContractInputType.COMPLEX)).build());

        assertThat(contract.getInput()).extracting("type").containsExactly(NodeContractInput.class.getName());
        assertThat(all(contract.getInput(), instanceOf(NodeContractInput.class))).isTrue();
    }

    @Test
    public void transform_an_emf_contract_into_a_designer_contract_with_COMPLEX_input_with_input() throws Exception {
        final ToWebContract contractConverter = new ToWebContract();

        final Contract contract = contractConverter.apply(aContract()
                .havingInput(aContractInput().withName("employee").withType(ContractInputType.COMPLEX)
                        .havingInput(aContractInput().withName("firstName").withType(ContractInputType.TEXT))).build());

        assertThat(contract.getInput()).hasSize(1);
        assertThat(contract.getInput().get(0).getInput()).extracting("name").containsExactly("firstName");
    }
}
