/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.core.mapping.treeMaching.resolver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.contract.core.mapping.treeMaching.BusinessDataStore;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.builders.ContractInputBuilder;
import org.junit.Before;
import org.junit.Test;

public class ContractToBusinessDataResolverTest {

    private BusinessDataStore store;
    private List<BusinessObjectData> businessData = new ArrayList<>();

    @Before
    public void setUp() {
        store = mock(BusinessDataStore.class);
        businessData.add(aData("myData", "org.test.Employee"));
        when(store.getBusinessData()).thenReturn(businessData);
    }

    @Test
    public void should_find_business_data_matching_contract_input_data_reference() throws Exception {
        ContractToBusinessDataResolver resolver = new ContractToBusinessDataResolver(store);

        Optional<BusinessObjectData> businessDataFound = resolver
                .findBusinessDataFor(ContractInputBuilder.aContractInput().withDataReference("myData").withName("myDataInput").build());

        assertThat(businessDataFound).isPresent();
    }

    @Test
    public void should_not_find_any_business_data() throws Exception {
        ContractToBusinessDataResolver resolver = new ContractToBusinessDataResolver(store);

        Optional<BusinessObjectData> businessDataFound = resolver
                .findBusinessDataFor(ContractInputBuilder.aContractInput().withName("myOtherData").build());

        assertThat(businessDataFound).isNotPresent();
    }

    private BusinessObjectData aData(String name, String type) {
        BusinessObjectData data = ProcessFactory.eINSTANCE.createBusinessObjectData();
        data.setName(name);
        data.setClassName(type);
        return data;
    }

}
