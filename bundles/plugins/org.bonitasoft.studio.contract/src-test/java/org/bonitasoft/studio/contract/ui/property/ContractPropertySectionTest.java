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
package org.bonitasoft.studio.contract.ui.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.contract.AbstractSWTTestCase;
import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractPropertySectionTest extends AbstractSWTTestCase {

    @Spy
    private ContractPropertySection section;

    @Mock
    private TabbedPropertySheetPage tabbedPropertySheetPage;

    private Composite parent;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        parent = createDisplayAndRealm();
        when(tabbedPropertySheetPage.getWidgetFactory()).thenReturn(new TabbedPropertySheetWidgetFactory());
    }


    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        section.dispose();
        dispose();
    }

    @Test
    public void section_description_isNotEmpty() throws Exception {
        assertThat(section.getSectionDescription()).isNotEmpty();
    }

    @Test
    public void section_title_isNotEmpty() throws Exception {
        assertThat(section.getSectionTitle()).isNotEmpty();
    }

    @Test
    public void should_createControls_create_section_content() throws Exception {
        section.createControls(parent, tabbedPropertySheetPage);
    }

    @Test
    public void should_validate_call_validate_a_contract() throws Exception {
        final ContractDefinitionValidator contractValidator = mock(ContractDefinitionValidator.class);
        doReturn(contractValidator).when(section).getContractDefinitionValidator();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        section.validate(contract);
        verify(contractValidator).validate(contract);
    }

    @Test
    public void should_bindRemoveButtonEnablement_convert_boolean_value() throws Exception {
        section.init();
        final Button removeButton = new Button(parent, SWT.PUSH);
        final TableViewer inputsTableViewer = new TableViewer(parent);
        inputsTableViewer.setLabelProvider(new LabelProvider());
        inputsTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        inputsTableViewer.setInput(Arrays.asList("item"));
        section.bindRemoveButtonEnablement(removeButton, inputsTableViewer);
        assertThat(removeButton.isEnabled()).isFalse();
        assertThat(inputsTableViewer.getSelection().isEmpty()).isTrue();
        inputsTableViewer.setSelection(new StructuredSelection("item"));
        assertThat(removeButton.isEnabled()).isTrue();
        assertThat(inputsTableViewer.getSelection().isEmpty()).isFalse();
    }

    @Test
    public void should_bindAddChildButtonEnablement_convert_boolean_value() throws Exception {
        section.init();
        final Button button = new Button(parent, SWT.PUSH);
        final TableViewer inputsTableViewer = new TableViewer(parent);
        inputsTableViewer.setLabelProvider(new LabelProvider());
        inputsTableViewer.setContentProvider(ArrayContentProvider.getInstance());

        final ContractInput textParentInput = ProcessFactory.eINSTANCE.createContractInput();
        textParentInput.setType(ContractInputType.TEXT);
        final ContractInput complexParentInput = ProcessFactory.eINSTANCE.createContractInput();
        complexParentInput.setType(ContractInputType.COMPLEX);

        inputsTableViewer.setInput(Arrays.asList(textParentInput, complexParentInput));
        section.bindAddChildButtonEnablement(button, inputsTableViewer);
        assertThat(button.isEnabled()).isFalse();
        assertThat(inputsTableViewer.getSelection().isEmpty()).isTrue();
        inputsTableViewer.setSelection(new StructuredSelection(textParentInput));
        assertThat(button.isEnabled()).isFalse();
        assertThat(inputsTableViewer.getSelection().isEmpty()).isFalse();

        inputsTableViewer.setSelection(new StructuredSelection(complexParentInput));
        assertThat(button.isEnabled()).isTrue();
    }

}
