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
package org.bonitasoft.studio.contract.ui.property.edit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.contract.ui.property.edit.proposal.InputMappingProposal;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class InputNamePropertyEditingSupportTest {

    @Mock
    private TableViewer viewer;

    private InputNamePropertyEditingSupport propertyEditingSupport;

    private AdapterFactoryContentProvider propertySourceProvider;

    @Mock
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        propertySourceProvider = new AdapterFactoryContentProvider(new ProcessItemProviderAdapterFactory());
        propertyEditingSupport = new InputNamePropertyEditingSupport(propertySourceProvider, viewer, adapterFactoryLabelProvider);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_setValue_refresh_viewer() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        propertyEditingSupport.setValue(input, "age");
        assertThat(input.getName()).isEqualTo("age");
        verify(viewer).update(input, null);
    }

    @Test
    public void should_getType_return_input_type_for_data_type() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setDataType(ProcessFactory.eINSTANCE.createStringType());
        InputMappingProposal proposal = new InputMappingProposal(data, null, null);
        assertThat(propertyEditingSupport.getType(proposal)).isEqualTo(ContractInputType.TEXT);

        data.setDataType(ProcessFactory.eINSTANCE.createBooleanType());
        proposal = new InputMappingProposal(data, null, null);
        assertThat(propertyEditingSupport.getType(proposal)).isEqualTo(ContractInputType.BOOLEAN);

        data.setDataType(ProcessFactory.eINSTANCE.createLongType());
        proposal = new InputMappingProposal(data, null, null);
        assertThat(propertyEditingSupport.getType(proposal)).isEqualTo(ContractInputType.INTEGER);

        data.setDataType(ProcessFactory.eINSTANCE.createIntegerType());
        proposal = new InputMappingProposal(data, null, null);
        assertThat(propertyEditingSupport.getType(proposal)).isEqualTo(ContractInputType.INTEGER);

        data.setDataType(ProcessFactory.eINSTANCE.createDateType());
        proposal = new InputMappingProposal(data, null, null);
        assertThat(propertyEditingSupport.getType(proposal)).isEqualTo(ContractInputType.DATE);

        data.setDataType(ProcessFactory.eINSTANCE.createDoubleType());
        proposal = new InputMappingProposal(data, null, null);
        assertThat(propertyEditingSupport.getType(proposal)).isEqualTo(ContractInputType.DECIMAL);

        data.setDataType(ProcessFactory.eINSTANCE.createFloatType());
        proposal = new InputMappingProposal(data, null, null);
        assertThat(propertyEditingSupport.getType(proposal)).isEqualTo(ContractInputType.DECIMAL);

        data.setDataType(ProcessFactory.eINSTANCE.createFloatType());
        data.setMultiple(true);
        proposal = new InputMappingProposal(data, null, null);
        assertThat(propertyEditingSupport.getType(proposal)).isEqualTo(ContractInputType.DECIMAL);
        data.setMultiple(false);

        final JavaObjectData javaData = ProcessFactory.eINSTANCE.createJavaObjectData();
        javaData.setDataType(ProcessFactory.eINSTANCE.createJavaType());
        javaData.setClassName("com.test.Employee");
        proposal = new InputMappingProposal(javaData, "setShort", Short.class.getName());
        assertThat(propertyEditingSupport.getType(proposal)).isEqualTo(ContractInputType.INTEGER);
    }

    @Test(expected = RuntimeException.class)
    public void should_getType_throw_RuntimeException_if_type_unknown() throws Exception {
        final JavaObjectData javaData = ProcessFactory.eINSTANCE.createJavaObjectData();
        javaData.setDataType(ProcessFactory.eINSTANCE.createJavaType());
        javaData.setClassName("com.test.Employee");
        final InputMappingProposal proposal = new InputMappingProposal(javaData, null, null);
        propertyEditingSupport.getType(proposal);
    }

}
