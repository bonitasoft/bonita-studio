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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.views.properties.IPropertySource;
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
public class DescriptionPropertyEditingSupportTest {

    @Mock
    private TableViewer viewer;

    private DescriptionPropertyEditingSupport propertyEditingSupport;

    @Mock
    private AdapterFactoryContentProvider propertySourceProvider;

    @Mock
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    @Mock
    private IPropertySource propertySource;

    @Mock
    private ContractDefinitionValidator validator;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(propertySourceProvider.getPropertySource(any(ContractInput.class))).thenReturn(propertySource);
        propertyEditingSupport = new DescriptionPropertyEditingSupport(viewer, propertySourceProvider, validator);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_setValue_update_viewer() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        propertyEditingSupport.setValue(input, "a desc");
        verify(propertySource).setPropertyValue("description", "a desc");
        verify(viewer).update(input, null);
    }

    @Test
    public void should_isValid_call_contract_definition_validator() throws Exception {
        assertThat(propertyEditingSupport.isValid("a desc")).isNull();
        verify(validator).validateInputDescription(null, "a desc");
    }


}
