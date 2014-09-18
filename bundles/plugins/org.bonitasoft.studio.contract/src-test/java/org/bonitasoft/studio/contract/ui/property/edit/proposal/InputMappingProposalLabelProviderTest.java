/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property.edit.proposal;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class InputMappingProposalLabelProviderTest {

    private InputMappingProposalLabelProvider inputMappingProposalLabelProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        inputMappingProposalLabelProvider = new InputMappingProposalLabelProvider(new ProcessItemProviderAdapterFactory());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getText_returns_null_if_not_a_InputMappingProposal() throws Exception {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        assertThat(inputMappingProposalLabelProvider.getText(contractInput)).isNull();
    }

    @Test
    public void should_getImage_returns_null_if_not_a_InputMappingProposal() throws Exception {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        assertThat(inputMappingProposalLabelProvider.getImage(contractInput)).isNull();
    }

    @Test
    public void should_getText_returns_proposal_label() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final DataType dt = ProcessFactory.eINSTANCE.createStringType();
        data.setDataType(dt);
        final InputMappingProposal proposal = new InputMappingProposal(data);
        assertThat(inputMappingProposalLabelProvider.getText(proposal)).isEqualTo(proposal.getLabel());
    }

    //    @Test
    //    public void should_getImage_returns_data_image() throws Exception {
    //        final Data data = ProcessFactory.eINSTANCE.createData();
    //        data.setName("myData");
    //        final DataType dt = ProcessFactory.eINSTANCE.createStringType();
    //        data.setDataType(dt);
    //        final InputMappingProposal proposal = new InputMappingProposal(data);
    //        assertThat(inputMappingProposalLabelProvider.getImage(proposal)).isNotNull();
    //    }
}
