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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.contract.AbstractSWTTestCase;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
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
public class ContractConstraintExpressionDialogCellEditorTest extends AbstractSWTTestCase {

    private ContractConstraintExpressionDialogCellEditor cellEditor;
    private ContractConstraint constraint;
    private Composite composite;
    @Mock
    private ConstraintEditorFactory constraintEditorFactory;
    private AdapterFactoryContentProvider adapterFactoryContentProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        composite = createDisplayAndRealm();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        contract.getConstraints().add(constraint);
        adapterFactoryContentProvider = new AdapterFactoryContentProvider(
                new ProcessItemProviderAdapterFactory());
        when(constraintEditorFactory.openConstraintEditor(any(Shell.class),
                eq(constraint),
                eq(adapterFactoryContentProvider))).thenReturn(Dialog.OK);
        cellEditor = new ContractConstraintExpressionDialogCellEditor(composite,
                constraint,
                adapterFactoryContentProvider,
                constraintEditorFactory);

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void should_openDialogBox_open_constraint_editor() throws Exception {
        cellEditor.openDialogBox(composite);
        verify(constraintEditorFactory).openConstraintEditor(any(Shell.class),
                eq(constraint),
                eq(adapterFactoryContentProvider));
    }

}
