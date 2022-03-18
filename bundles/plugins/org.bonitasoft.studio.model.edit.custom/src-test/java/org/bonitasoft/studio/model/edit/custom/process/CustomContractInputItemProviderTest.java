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
package org.bonitasoft.studio.model.edit.custom.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Map;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.junit.Test;

public class CustomContractInputItemProviderTest {

    @Test
    public void should_display_string_type_next_to_TEXT_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = aContractInput().build();
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertThat(label).contains(String.class.getName());
    }

    @Test
    public void should_display_integer_type_next_to_INTEGER_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = aContractInput().withType(ContractInputType.INTEGER).build();
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertThat(label).contains(Integer.class.getName());
    }

    @Test
    public void should_display_boolean_type_next_to_BOOLEAN_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = aContractInput().withType(ContractInputType.BOOLEAN).build();
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertThat(label).contains(Boolean.class.getName());
    }

    @Test
    public void should_display_double_type_next_to_DECIMAL_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = aContractInput().withType(ContractInputType.DECIMAL).build();
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertThat(label).contains(Double.class.getName());
    }

    @Test
    public void should_display_date_only_for_LOCALDATE_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = aContractInput().withType(ContractInputType.LOCALDATE).build();
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertThat(label).isEqualTo(String.format("DATE ONLY - %s", LocalDate.class.getName()));
    }

    @Test
    public void should_display_date_and_time_for_LOCALDATETIME_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = aContractInput().withType(ContractInputType.LOCALDATETIME).build();
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertThat(label).isEqualTo(String.format("DATE-TIME (NO TIME ZONE) - %s", LocalDateTime.class.getName()));
    }

    @Test
    public void should_display_date_and_time_for_OFFSETDATETIME_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = aContractInput().withType(ContractInputType.OFFSETDATETIME).build();
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertThat(label).isEqualTo(String.format("DATE-TIME (TIME ZONE) - %s", OffsetDateTime.class.getName()));
    }

    @Test
    public void should_display_FileInputType_type_next_to_FILE_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = aContractInput().withType(ContractInputType.FILE).build();
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertThat(label).contains("org.bonitasoft.engine.bpm.contract.FileInputValue");
    }

    @Test
    public void should_display_Map_type_next_to_COMPLEX_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = aContractInput().withType(ContractInputType.COMPLEX).build();
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertThat(label).contains(Map.class.getName());
    }

    @Test
    public void should_remove_LONG_type_if_contract_is_on_a_Task() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final Collection<ContractInputType> choiceOfValues = (Collection<ContractInputType>) itemProvider
                .getPropertyDescriptor(aContractInput().build(), ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getChoiceOfValues(aContract().in(aTask()).build());

        assertThat(choiceOfValues).doesNotContain(ContractInputType.LONG);
    }

    @Test
    public void should_haveLONG_type_if_contract_is_on_a_Pool() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final Collection<ContractInputType> choiceOfValues = (Collection<ContractInputType>) itemProvider
                .getPropertyDescriptor(aContractInput().build(), ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getChoiceOfValues(aContract().in(aPool()).build());

        assertThat(choiceOfValues).contains(ContractInputType.LONG);
    }
}
