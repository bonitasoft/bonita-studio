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
package org.bonitasoft.studio.contract.core.mapping.operation.initializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.anAggregationField;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.junit.Test;

public class AggregationReferencePropertyInitializerTest {

    @Test
    public void should_call_query_in_a_closure() throws Exception {
        final InitializerContext context = new InitializerContext();
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping(
                anAggregationField("country",
                        aBO("country").build()));
        context.setMapping(mapping);
        context.setContractInput(
                aContractInput().withName("persistenceId_string").in(aContractInput().withName("country")).build());
        context.setLocalVariableName("countryVar");

        final String initialValue = new AggregationReferencePropertyInitializer(null, context).getInitialValue();

        assertThat(initialValue).isEqualTo("{\n"
                + "//Retrieve aggregated country using its DAO and persistenceId\n"
                + "def countryVar = countryDAO.findByPersistenceId(country?.persistenceId_string?.find()?.toLong())\n"
                + "if (!countryVar) {\n"
                + "if (country?.persistenceId_string?.find()?.toLong()) {\n"
                + "throw new IllegalArgumentException(\"The aggregated reference of type `country`  with the persistence id \" + country?.persistenceId_string?.find()?.toLong() + \" has not been found.\")\n"
                + "}\n"
                + "return null\n"
                + "}\n"
                + "return countryVar}()");
    }
}
