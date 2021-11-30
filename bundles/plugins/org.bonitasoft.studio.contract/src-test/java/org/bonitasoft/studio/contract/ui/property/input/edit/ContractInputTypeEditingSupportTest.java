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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.contract.core.refactoring.ContractInputRefactorOperationFactory;
import org.bonitasoft.studio.contract.core.refactoring.RefactorContractInputOperation;
import org.bonitasoft.studio.contract.ui.property.input.ContractInputController;
import org.bonitasoft.studio.fakes.FakeProgressService;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
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
public class ContractInputTypeEditingSupportTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Mock
    private ColumnViewer viewer;

    private ContractInputTypeEditingSupport contractInputTypeEditingSupport;

    @Mock
    private CellEditor cellEditor;
    @Mock
    private ViewerCell cell;

    private Composite parent;

    @Mock
    private ContractInputController contractInputController;

    @Mock
    private ContractInputRefactorOperationFactory refactorOperationFactory;

    @Mock
    private RefactorContractInputOperation refactorOperation;

    private ResourceSetImpl resourceSet;

    @Mock
    private Factory transactionalEditingDomainFactory;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        parent = realm.createComposite();
        resourceSet = new ResourceSetImpl();
        when(refactorOperationFactory.createRefactorOperation(any(), any(), any())).thenReturn(refactorOperation);
        when(refactorOperation.getCompoundCommand()).thenReturn(new CompoundCommand());
        when(transactionalEditingDomainFactory.getEditingDomain(resourceSet))
                .thenReturn(mock(TransactionalEditingDomain.class));
        contractInputTypeEditingSupport = new ContractInputTypeEditingSupport(viewer,
                new AdapterFactoryContentProvider(new ProcessItemProviderAdapterFactory()), contractInputController,
                refactorOperationFactory, new FakeProgressService(), transactionalEditingDomainFactory);
    }

    @Test
    public void should_cancelEditor_and_editorValueChanged_doNothing() throws Exception {
        contractInputTypeEditingSupport.cancelEditor();
        contractInputTypeEditingSupport.editorValueChanged(true, true);
    }

    @Test
    public void should_initializeCellEditorValue_add_ICellEditorListener() throws Exception {
        viewer = new TableViewer(parent);
        contractInputTypeEditingSupport = new ContractInputTypeEditingSupport(viewer,
                new AdapterFactoryContentProvider(new ProcessItemProviderAdapterFactory()),
                new ContractInputController(new FakeProgressService()),
                refactorOperationFactory, new FakeProgressService(), transactionalEditingDomainFactory);

        when(cell.getElement()).thenReturn(ProcessFactory.eINSTANCE.createContractInput());
        contractInputTypeEditingSupport.initializeCellEditorValue(cellEditor, cell);
        verify(cellEditor).addListener(contractInputTypeEditingSupport);
    }

    @Test
    public void should_setValue_setContractInput_and_refresh_viewer() throws Exception {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        final ResourceImpl res = new ResourceImpl();
        res.getContents().add(contractInput);
        resourceSet.getResources().add(res);
        contractInputTypeEditingSupport.setValue(contractInput, ContractInputType.COMPLEX);
        assertThat(contractInputTypeEditingSupport.getContractInput()).isEqualTo(contractInput);

        verify(refactorOperation).run(any());
        verify(contractInputTypeEditingSupport.getViewer()).refresh(true);
    }

    @Test
    public void should_applyEditorValue_add_child_input_if_type_is_changed_to_complex_and_has_no_child() throws Exception {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        final ResourceImpl res = new ResourceImpl();
        res.getContents().add(contractInput);
        resourceSet.getResources().add(res);
        contractInputTypeEditingSupport.setValue(contractInput, ContractInputType.COMPLEX);
        contractInputTypeEditingSupport.applyEditorValue();

        verify(refactorOperation).run(any());
    }

    @Test
    public void should_applyEditorValue_do_nothing_input_if_type_is_changed_to_complex_and_with_child() throws Exception {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.getInputs().add(ProcessFactory.eINSTANCE.createContractInput());
        final ResourceImpl res = new ResourceImpl();
        res.getContents().add(contractInput);
        resourceSet.getResources().add(res);

        contractInputTypeEditingSupport.setValue(contractInput, ContractInputType.COMPLEX);
        contractInputTypeEditingSupport.applyEditorValue();
        assertThat(contractInput.getInputs()).hasSize(1);
    }

    @Test
    public void should_applyEditorValue_do_nothing_input_if_type_is_changed_to_primitive() throws Exception {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        final ResourceImpl res = new ResourceImpl();
        res.getContents().add(contractInput);
        resourceSet.getResources().add(res);

        contractInputTypeEditingSupport.setValue(contractInput, ContractInputType.INTEGER);
        contractInputTypeEditingSupport.applyEditorValue();
        assertThat(contractInput.getInputs()).hasSize(0);
    }

}
