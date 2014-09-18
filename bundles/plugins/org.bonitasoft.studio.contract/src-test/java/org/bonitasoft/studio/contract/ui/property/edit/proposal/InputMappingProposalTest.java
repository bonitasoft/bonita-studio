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

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class InputMappingProposalTest {

    private InputMappingProposal proposal;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getLabel_returns_dataName_with_type_for_simple_data() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final DataType dt = ProcessFactory.eINSTANCE.createStringType();
        dt.setName(Messages.StringType);
        data.setDataType(dt);
        proposal = new InputMappingProposal(data);
        assertThat(proposal.getLabel()).isEqualTo("myData -- " + Messages.StringType);
    }

    @Test
    public void should_getLabel_returns_dataName_and_field_with_type_for_java_data() throws Exception {
        final JavaObjectData data = ProcessFactory.eINSTANCE.createJavaObjectData();
        data.setName("myData");
        final DataType dt = ProcessFactory.eINSTANCE.createStringType();
        dt.setName(Messages.StringType);
        data.setDataType(dt);
        proposal = new InputMappingProposal(data, "setName", String.class.getName());
        assertThat(proposal.getLabel()).isEqualTo("myData.name -- " + String.class.getName());
    }

    @Test
    public void should_getLabel_returns_dataName_with_type_for_business_object_data() throws Exception {
        final JavaObjectData data = ProcessFactory.eINSTANCE.createBusinessObjectData();
        data.setName("myData");
        final DataType dt = ProcessFactory.eINSTANCE.createBusinessObjectType();
        data.setClassName("com.test.Employee");
        data.setDataType(dt);
        proposal = new InputMappingProposal(data, null, null);
        assertThat(proposal.getLabel()).isEqualTo("myData -- com.test.Employee");
    }


    @Test
    public void should_get_label_returns_null() throws Exception {
        proposal = new InputMappingProposal((Data) null);
        assertThat(proposal.getLabel()).isNull();
    }

    @Test
    public void should_getContent_returns_dataName_for_simple_data() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        final DataType dt = ProcessFactory.eINSTANCE.createStringType();
        dt.setName(Messages.StringType);
        data.setDataType(dt);
        proposal = new InputMappingProposal(data);
        assertThat(proposal.getContent()).isEqualTo("myData");
    }

    @Test
    public void should_getContent_returns_dataName_and_field_for_java_data() throws Exception {
        final JavaObjectData data = ProcessFactory.eINSTANCE.createJavaObjectData();
        data.setName("myData");
        final DataType dt = ProcessFactory.eINSTANCE.createStringType();
        dt.setName(Messages.StringType);
        data.setDataType(dt);
        proposal = new InputMappingProposal(data, "setName", String.class.getName());
        assertThat(proposal.getContent()).isEqualTo("myData.name");
    }

    @Test
    public void should_getContent_returns_dataName_for_business_object_data() throws Exception {
        final JavaObjectData data = ProcessFactory.eINSTANCE.createBusinessObjectData();
        data.setName("myData");
        final DataType dt = ProcessFactory.eINSTANCE.createBusinessObjectType();
        data.setClassName("com.test.Employee");
        data.setDataType(dt);
        proposal = new InputMappingProposal(data, null, null);
        assertThat(proposal.getContent()).isEqualTo("myData");
    }

    @Test
    public void should_get_content_returns_null() throws Exception {
        proposal = new InputMappingProposal((Data) null);
        assertThat(proposal.getContent()).isNull();
    }

    @Test
    public void should_mapping_constructor_set_proposal_property() throws Exception {
        final ContractInputMapping inputMapping = ProcessFactory.eINSTANCE.createContractInputMapping();
        final JavaObjectData data = ProcessFactory.eINSTANCE.createJavaObjectData();
        data.setName("myData");
        final DataType dt = ProcessFactory.eINSTANCE.createStringType();
        dt.setName(Messages.StringType);
        data.setDataType(dt);
        proposal = new InputMappingProposal(data, "setName", String.class.getName());
        inputMapping.setData(data);
        inputMapping.setSetterName("setName");
        inputMapping.setSetterParamType(String.class.getName());
        proposal = new InputMappingProposal(inputMapping);
        assertThat(proposal.getData()).isEqualTo(data);
        assertThat(proposal.getSetterName()).isEqualTo("setName");
        assertThat(proposal.getSetterParamType()).isEqualTo(String.class.getName());
        assertThat(proposal.getDescription()).isNull();
        assertThat(proposal.getCursorPosition()).isEqualTo(proposal.getContent().length());
    }

}
