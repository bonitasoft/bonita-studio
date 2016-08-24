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
package org.bonitasoft.studio.contract.ui.wizard.labelProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.RelationFieldBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.junit.Test;

public class InputTypeColumnLabelProviderTest {

    @Test
    public void should_return_input_type_name() {
        final SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(SimpleFieldBuilder.aTextField("employee")
                .withName("employee").build());
        final InputTypeColumnLabelProvider provider = new InputTypeColumnLabelProvider(aContract().in(aPool()).build());
        assertThat(provider.getText(mapping)).isEqualTo(ContractInputType.TEXT.name());
    }

    @Test
    public void should_return_complex_input_type_name() {
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping(RelationFieldBuilder.aCompositionField(
                "employee",
                BusinessObjectBuilder.aBO("com.company.Manager").build()));
        final InputTypeColumnLabelProvider provider = new InputTypeColumnLabelProvider(aContract().in(aPool()).build());
        assertThat(provider.getText(mapping)).isEqualTo(ContractInputType.COMPLEX.name());
    }

}
