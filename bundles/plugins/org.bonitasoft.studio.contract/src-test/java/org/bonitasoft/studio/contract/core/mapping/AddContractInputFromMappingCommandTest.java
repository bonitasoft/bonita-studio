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
package org.bonitasoft.studio.contract.core.mapping;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Test;

/**
 * @author aurelie
 */
public class AddContractInputFromMappingCommandTest {

    @Test
    public void should_add_contract_input_to_contract_from_mapping_when_executing_command() throws Exception {
        final Contract contract = aContract().build();
        final AddContractInputFromMappingCommand command = new AddContractInputFromMappingCommand(editingDomain(),
                contract,
                mappingList(new SimpleFieldToContractInputMapping(aSimpleField("firstName", FieldType.STRING))));

        command.execute();

        assertThat(contract.getInputs()).extracting("name", "type").containsOnly(tuple("firstName", ContractInputType.TEXT));
    }

    @Test
    public void should_remove_contract_input_from_contract_when_undoing_command() throws Exception {
        final Contract contract = aContract().build();
        final AddContractInputFromMappingCommand command = new AddContractInputFromMappingCommand(editingDomain(),
                contract,
                mappingList(new SimpleFieldToContractInputMapping(aSimpleField("firstName", FieldType.STRING))));

        command.execute();
        command.undo();

        assertThat(contract.getInputs()).isEmpty();
    }

    @Test
    public void should_add_contract_input_from_contract_when_redoing_command() throws Exception {
        final Contract contract = aContract().build();
        final AddContractInputFromMappingCommand command = new AddContractInputFromMappingCommand(editingDomain(),
                contract,
                mappingList(new SimpleFieldToContractInputMapping(aSimpleField("firstName", FieldType.STRING))));

        command.execute();
        command.undo();
        command.redo();

        assertThat(contract.getInputs()).extracting("name", "type").containsOnly(tuple("firstName", ContractInputType.TEXT));
    }

    /**
     * @return
     */
    private List<FieldToContractInputMapping> mappingList(final FieldToContractInputMapping... mapping) {
        final List<FieldToContractInputMapping> mappings = new ArrayList<FieldToContractInputMapping>();
        for (final FieldToContractInputMapping m : mapping) {
            mappings.add(m);
        }
        return mappings;
    }

    private EditingDomain editingDomain() {
        return new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory());
    }

    private SimpleField aSimpleField(final String name, final FieldType type) {
        final SimpleField simpleField = new SimpleField();
        simpleField.setName(name);
        simpleField.setType(type);
        return simpleField;
    }
}
