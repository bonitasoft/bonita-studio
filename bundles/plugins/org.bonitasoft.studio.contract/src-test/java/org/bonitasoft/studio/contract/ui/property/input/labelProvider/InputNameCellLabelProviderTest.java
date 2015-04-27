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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.bonitasoft.studio.contract.ui.property.input.labelProvider.InputNameCellLabelProvider;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class InputNameCellLabelProviderTest {

    @Mock
    private IPropertySourceProvider propertySourceLabelProvider;

    private InputNameCellLabelProvider inputNameCellLabelProvider;

    @Mock
    private ColumnViewer viewer;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        inputNameCellLabelProvider = spy(new InputNameCellLabelProvider(propertySourceLabelProvider, new WritableSet()));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getStyledString_returns_a_StyledString_with_text() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("inputName");
        doReturn(input.getName()).when(inputNameCellLabelProvider).getText(input);
        assertThat(inputNameCellLabelProvider.getStyledText(input)).isNotNull();
        assertThat(inputNameCellLabelProvider.getStyledText(input).getString()).isEqualTo(input.getName());
    }

}
