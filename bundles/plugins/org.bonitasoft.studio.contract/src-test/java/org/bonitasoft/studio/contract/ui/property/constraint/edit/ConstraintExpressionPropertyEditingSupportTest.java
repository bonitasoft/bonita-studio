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
package org.bonitasoft.studio.contract.ui.property.constraint.edit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.contract.ui.property.FieldDecoratorProvider;
import org.bonitasoft.studio.contract.ui.property.constraint.edit.editor.ContractConstraintExpressionDialogCellEditor;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IMessageManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ConstraintExpressionPropertyEditingSupportTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Mock
    private ColumnViewer viewer;

    private ConstraintExpressionPropertyEditingSupport propertyEditingSupport;

    private AdapterFactoryContentProvider propertySourceProvider;

    @Mock
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    @Mock
    private IMessageManager iMessageManager;

    @Mock
    private FieldDecoratorProvider decoratorProvider;

    private Composite container;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        container = realm.createComposite();
        propertySourceProvider = new AdapterFactoryContentProvider(new ProcessItemProviderAdapterFactory());
        propertyEditingSupport = new ConstraintExpressionPropertyEditingSupport(viewer, propertySourceProvider);
    }

    @Test
    public void should_setValue_refresh_viewer() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        contract.getConstraints().add(constraint);
        propertyEditingSupport.setValue(constraint, "name.length > 50");
        assertThat(constraint.getExpression()).isEqualTo("name.length > 50");
        verify(viewer).update(constraint, null);
    }

    @Test
    public void should_canEdit_ContractConstraint() throws Exception {
        assertThat(propertyEditingSupport.canEdit(ProcessFactory.eINSTANCE.createContractConstraint())).isTrue();
        assertThat(propertyEditingSupport.canEdit(ProcessFactory.eINSTANCE.createContractInput())).isFalse();
    }

    @Test
    public void should_getCellEditor_return() throws Exception {
        propertyEditingSupport = new ConstraintExpressionPropertyEditingSupport(new TableViewer(container), propertySourceProvider);
        final CellEditor cellEditor = propertyEditingSupport.getCellEditor(ProcessFactory.eINSTANCE.createContractConstraint());
        assertThat(cellEditor).isNotNull().isInstanceOf(ContractConstraintExpressionDialogCellEditor.class);
    }
}
