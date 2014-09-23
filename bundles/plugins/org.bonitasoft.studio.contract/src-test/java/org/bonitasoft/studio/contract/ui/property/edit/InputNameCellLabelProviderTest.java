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

import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyColumnLabelProvider;
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


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        inputNameCellLabelProvider = spy(new InputNameCellLabelProvider(propertySourceLabelProvider, new ContractDefinitionValidator()));
        doReturn(null).when(inputNameCellLabelProvider).getErrorBackgroundColor();
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
    public void should_getToolTipText_returns_null() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        assertThat(inputNameCellLabelProvider.getToolTipText(input)).isNull();
    }

    @Test
    public void should_getToolTipText_returns_error_message() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("");
        assertThat(inputNameCellLabelProvider.getToolTipText(input)).isNotEmpty().contains(org.bonitasoft.studio.common.Messages.emptyField);
    }

    @Test
    public void should_getBackgroundColor_returns_red_color() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("");
        inputNameCellLabelProvider.getBackground(input);
        verify(inputNameCellLabelProvider).getErrorBackgroundColor();
    }

    @Test
    public void should_getBackgroundColor_returns_standard_color() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        inputNameCellLabelProvider.getBackground(input);
        verify((PropertyColumnLabelProvider) inputNameCellLabelProvider).getBackground(input);
        verify(inputNameCellLabelProvider, never()).getErrorBackgroundColor();
    }
}
