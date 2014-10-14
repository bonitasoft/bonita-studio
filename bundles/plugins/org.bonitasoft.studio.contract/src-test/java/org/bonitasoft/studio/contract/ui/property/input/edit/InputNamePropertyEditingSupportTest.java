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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.contract.ui.property.FieldDecoratorProvider;
import org.bonitasoft.studio.contract.ui.property.input.edit.InputNamePropertyEditingSupport;
import org.bonitasoft.studio.contract.ui.property.input.edit.proposal.InputMappingProposal;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.ui.forms.IMessageManager;
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
    private ColumnViewer viewer;

    private InputNamePropertyEditingSupport propertyEditingSupport;

    private AdapterFactoryContentProvider propertySourceProvider;

    @Mock
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    @Mock
    private IMessageManager iMessageManager;

    @Mock
    private FieldDecoratorProvider decoratorProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        propertySourceProvider = new AdapterFactoryContentProvider(new ProcessItemProviderAdapterFactory());
        propertyEditingSupport = new InputNamePropertyEditingSupport(propertySourceProvider, viewer, adapterFactoryLabelProvider,
                new ContractDefinitionValidator(), decoratorProvider);
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
        verify(viewer).refresh(true);
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

    @Test(expected = IllegalStateException.class)
    public void should_getType_throw_RuntimeException_if_type_unknown() throws Exception {
        final JavaObjectData javaData = ProcessFactory.eINSTANCE.createJavaObjectData();
        javaData.setDataType(ProcessFactory.eINSTANCE.createJavaType());
        javaData.setClassName("com.test.Employee");
        final InputMappingProposal proposal = new InputMappingProposal(javaData, null, null);
        propertyEditingSupport.getType(proposal);
    }

    @Test
    public void should_initializeCellEditorValue_set_validator_and_add_listener() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        final CellEditor cellEditorMock = mock(CellEditor.class);
        final ViewerCell cellMock = mock(ViewerCell.class);
        when(cellMock.getElement()).thenReturn(input);
        final org.eclipse.swt.widgets.Text textMock = mock(org.eclipse.swt.widgets.Text.class);
        when(cellEditorMock.getControl()).thenReturn(textMock);


        propertyEditingSupport.initializeCellEditorValue(cellEditorMock, cellMock);
        assertThat(propertyEditingSupport.getCurrentElement()).isEqualTo(input);
        //        verify(decoratorProvider).createControlDecorator(cellEditorMock.getControl(), Messages.automaticMappingTooltip,
        //                FieldDecorationRegistry.DEC_CONTENT_PROPOSAL, SWT.TOP | SWT.LEFT);
        verify(cellEditorMock).setValidator(propertyEditingSupport);
        verify(cellEditorMock).addListener(propertyEditingSupport);

    }

}
