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
package org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.junit.Test;

public class ContractTabbedPropertyProviderTest {

    @Test
    public void should_return_execution_viewId() throws Exception {
        final ContractTabbedPropertyProvider provider = new ContractTabbedPropertyProvider();

        assertThat(provider.viewId()).isEqualTo("org.bonitasoft.studio.views.properties.process.execution");
    }

    @Test
    public void should_return_contract_tab_id() throws Exception {
        final ContractTabbedPropertyProvider provider = new ContractTabbedPropertyProvider();

        assertThat(provider.tabId(null)).isEqualTo("tab.contract");
    }

    @Test
    public void should_appliesTo_Contract() throws Exception {
        final ContractTabbedPropertyProvider provider = new ContractTabbedPropertyProvider();

        assertThat(provider.appliesTo(aContract().build(), null)).isTrue();
        assertThat(provider.appliesTo(null, null)).isFalse();
    }

    @Test
    public void should_appliesTo_Contract_child() throws Exception {
        final ContractTabbedPropertyProvider provider = new ContractTabbedPropertyProvider();

        assertThat(provider.appliesTo(aContract().havingInput(aContractInput()).build().getInputs().get(0), null)).isTrue();
    }

}
