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
package org.bonitasoft.studio.document.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.studio.model.process.Contract;
import org.junit.Test;

public class ContractInputLabelProviderTest {

    @Test
    public void should_return_contract_input_path_as_text() throws Exception {
        final ContractInputLabelProvider contractInputLabelProvider = new ContractInputLabelProvider();
        final Contract contract = aContract()
                .havingInput(aContractInput().withName("employee")
                        .havingInput(aContractInput().withName("firstName"))).build();

        final String text = contractInputLabelProvider.getText(contract.getInputs().get(0).getInputs().get(0));

        assertThat(text).isEqualTo("employee.firstName");
    }
}
