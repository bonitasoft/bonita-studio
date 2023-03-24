/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.input.labelProvider;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.builders.ContractInputBuilder;
import org.junit.Before;
import org.junit.Test;

public class ContractInputTypeCellLabelProviderTest {

    private ContractInputTypeCellLabelProvider contractInputTypeCellLabelProvider;

    @Before
    public void setUp() throws Exception {
        contractInputTypeCellLabelProvider = new ContractInputTypeCellLabelProvider(null);
    }

    @Test
    public void should_getImage_return_null() throws Exception {
        assertThat(contractInputTypeCellLabelProvider.getImage(ProcessFactory.eINSTANCE.createContractInput())).isNull();
    }

    @Test
    public void should_return_type_name() throws Exception {
        assertThat(contractInputTypeCellLabelProvider
                .getText(ContractInputBuilder.aContractInput().withType(ContractInputType.TEXT).build())).isEqualTo("TEXT");
    }

    @Test
    public void should_return_date_only() throws Exception {
        assertThat(contractInputTypeCellLabelProvider
                .getText(ContractInputBuilder.aContractInput().withType(ContractInputType.LOCALDATE).build()))
                        .isEqualTo(DateTypeLabels.DATE_ONLY);
    }

    @Test
    public void should_return_dateand_time() throws Exception {
        assertThat(contractInputTypeCellLabelProvider
                .getText(ContractInputBuilder.aContractInput().withType(ContractInputType.LOCALDATETIME).build()))
                        .isEqualTo(DateTypeLabels.DATE_AND_TIME);
    }

    @Test
    public void should_return_date_and_time_with_timezone() throws Exception {
        assertThat(contractInputTypeCellLabelProvider
                .getText(ContractInputBuilder.aContractInput().withType(ContractInputType.OFFSETDATETIME).build()))
                        .isEqualTo(DateTypeLabels.DATE_TIME_WITH_TIMEZONE);
    }
}
