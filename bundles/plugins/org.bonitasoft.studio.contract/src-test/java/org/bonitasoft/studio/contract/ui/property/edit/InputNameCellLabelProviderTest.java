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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
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
public class InputNameCellLabelProviderTest {

    @Mock
    private IPropertySourceProvider propertySourceLabelProvider;

    private InputNameCellLabelProvider inputNameCellLabelProvider;

    @Mock
    private TableViewer viewer;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        inputNameCellLabelProvider = spy(new InputNameCellLabelProvider(viewer, propertySourceLabelProvider));
        doReturn(null).when(inputNameCellLabelProvider).getErrorImage();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getImage_returns_null() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        assertThat(inputNameCellLabelProvider.getImage(input)).isNull();
    }

    @Test
    public void should_getToolTipText_returns_null_if_no_error() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        assertThat(inputNameCellLabelProvider.getToolTipText(input)).isNull();
    }

    @Test
    public void should_getToolTipText_returns_null_if_error_but_activeEditor() throws Exception {
        when(viewer.isCellEditorActive()).thenReturn(true);
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("");
        assertThat(inputNameCellLabelProvider.getToolTipText(input)).isNull();
    }

    @Test
    public void should_getToolTipText_returns_error_message_for_empty_name() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("");
        assertThat(inputNameCellLabelProvider.getToolTipText(input)).isNotEmpty();
    }

    @Test
    public void should_getImage_returns_error_image_for_duplicated_name() throws Exception {
        final Contract c = ProcessFactory.eINSTANCE.createContract();
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("nameInput");
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        input2.setName("nameInput");
        c.getInputs().add(input);
        c.getInputs().add(input2);
        inputNameCellLabelProvider.getImage(input);
        verify(inputNameCellLabelProvider).getErrorImage();
    }

    @Test
    public void should_getImage_returns_null_if_other_duplicated_name() throws Exception {
        final Contract c = ProcessFactory.eINSTANCE.createContract();
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("nameInput");
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        input2.setName("nameInput");
        final ContractInput input3 = ProcessFactory.eINSTANCE.createContractInput();
        input3.setName("otherInput");
        c.getInputs().add(input);
        c.getInputs().add(input2);
        c.getInputs().add(input3);
        inputNameCellLabelProvider.getImage(input3);
        verify(inputNameCellLabelProvider, never()).getErrorImage();
    }

    @Test
    public void should_getImage_returns_error_image_for_empty_name() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("");
        inputNameCellLabelProvider.getImage(input);
        verify(inputNameCellLabelProvider).getErrorImage();
    }

    @Test
    public void should_getImage_returns_null_for_valid_input_name() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        inputNameCellLabelProvider.getImage(input);
        verify(inputNameCellLabelProvider, never()).getErrorImage();
    }
}
