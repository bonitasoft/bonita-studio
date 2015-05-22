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
package org.bonitasoft.studio.contract.core.refactoring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.studio.refactoring.core.RefactorPair;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class ContractInputRefactorPairTest {

    @Test
    public void new_value_name_from_a_contract_input() throws Exception {
        final ContractInputRefactorPair refactorPair = new ContractInputRefactorPair(aContractInput().withName("firstName").build(), aContractInput().withName(
                "inputName").build());

        final String newValueName = refactorPair.getNewValueName();

        assertThat(newValueName).isEqualTo("firstName");
    }

    @Test
    public void EMPTY_VALUE_for_null_new_value() throws Exception {
        final ContractInputRefactorPair refactorPair = new ContractInputRefactorPair(null, aContractInput().withName(
                "inputName").build());

        final String newValueName = refactorPair.getNewValueName();

        assertThat(newValueName).isEqualTo(RefactorPair.EMPTY_VALUE);
    }

    @Test
    public void old_value_name_from_a_contract_input() throws Exception {
        final ContractInputRefactorPair refactorPair = new ContractInputRefactorPair(null, aContractInput().withName(
                "inputName").build());

        final String oldValueName = refactorPair.getOldValueName();

        assertThat(oldValueName).isEqualTo("inputName");
    }
}
