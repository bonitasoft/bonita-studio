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
package org.bonitasoft.studio.contract.ui.property.constraint.edit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.contract.core.validation.ContractDefinitionValidator;
import org.bonitasoft.studio.contract.ui.property.FieldDecoratorProvider;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
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
public class ConstraintNamePropertyEditingSupportTest {

    @Mock
    private ColumnViewer viewer;

    private ConstraintNamePropertyEditingSupport propertyEditingSupport;

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
        propertyEditingSupport = new ConstraintNamePropertyEditingSupport(propertySourceProvider, viewer, adapterFactoryLabelProvider,
                new ContractDefinitionValidator());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_setValue_refresh_viewer() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        contract.getConstraints().add(constraint);
        propertyEditingSupport.setValue(constraint, "legal age");
        assertThat(constraint.getName()).isEqualTo("legal age");
        verify(viewer).refresh(true);
    }

    @Test
    public void should_initializeCellEditorValue_set_swtbot_id_key() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        final CellEditor cellEditorMock = mock(CellEditor.class);
        final ViewerCell cellMock = mock(ViewerCell.class);
        when(cellMock.getElement()).thenReturn(input);
        final org.eclipse.swt.widgets.Text textMock = mock(org.eclipse.swt.widgets.Text.class);
        when(cellEditorMock.getControl()).thenReturn(textMock);

        propertyEditingSupport.initializeCellEditorValue(cellEditorMock, cellMock);
        verify(textMock).setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_CONSTRAINT_NAME_TEXTEDITOR);
    }

}
