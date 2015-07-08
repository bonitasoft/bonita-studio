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
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aStringField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.anAggregationField;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.studio.model.process.ContractInputType;
import org.junit.Test;

public class BusinessObjectQueryInitializerTest {

    @Test
    public void should_generate_a_script_using_query_to_retrieve_a_business_object_in_initial_value() throws Exception {
        final BusinessObjectQueryInitializer initializer = new BusinessObjectQueryInitializer(null, anAggregationField("country", aBO("org.test.Country")
                .build()),
                aContractInput().withName("pId").in(aContractInput().withName("countryInput").withType(ContractInputType.COMPLEX)).build(),
                "myCountry");

        initializer.addPropertyInitializer(new SimpleFieldPropertyInitializer(null, aStringField("persistenceId").build(),
                aContractInput().withName("persistenceId").in(aContractInput().withName("countryInput").withType(ContractInputType.COMPLEX)).build()));
        initializer.addPropertyInitializer(new SimpleFieldPropertyInitializer(null, aStringField("name").build(),
                aContractInput().withName("name").in(aContractInput().withName("countryInput").withType(ContractInputType.COMPLEX)).build()));

        final String initialValue = initializer.getInitialValue();

        assertThat(initialValue).isEqualTo(
                "def countryVar = countryDAO.findByPersistenceId(countryInput.pId.toLong())" + System.lineSeparator()
                        + "countryVar.name = countryInput.name" + System.lineSeparator()
                        + "return countryVar");
    }
}
