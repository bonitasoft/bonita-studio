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

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.contract.ui.property.FieldDecoratorProvider;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
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
public class ConstraintErrorMessagePropertyEditingSupportTest extends AbstractSWTTestCase {


    private ColumnViewer viewer;

    private ConstraintErrorMessagePropertyEditingSupport propertyEditingSupport;

    private AdapterFactoryContentProvider propertySourceProvider;

    @Mock
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    @Mock
    private IMessageManager iMessageManager;

    @Mock
    private FieldDecoratorProvider decoratorProvider;

    private Composite composite;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        composite = createDisplayAndRealm();
        propertySourceProvider = new AdapterFactoryContentProvider(new ProcessItemProviderAdapterFactory());
        viewer = new TableViewer(composite);
        propertyEditingSupport = new ConstraintErrorMessagePropertyEditingSupport(viewer, propertySourceProvider);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void should_getCellEditor_create_a_cell_editor_with_swtbot_id_set() throws Exception {
        final CellEditor cellEditor = propertyEditingSupport.getCellEditor(ProcessFactory.eINSTANCE.createContractConstraint());
        assertThat(cellEditor).isNotNull();
        assertThat(cellEditor.getControl()).isInstanceOf(Text.class);
        assertThat(cellEditor.getControl().getData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY)).isEqualTo(
                SWTBotConstants.SWTBOT_ID_CONSTRAINT_ERROR_MESSAGE_TEXTEDITOR);
    }

    @Test
    public void should_setValue_update_viewer() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        contract.getConstraints().add(constraint);
        propertyEditingSupport.setValue(constraint, "age is under 18");
        assertThat(constraint.getErrorMessage()).isEqualTo("age is under 18");
    }

}
