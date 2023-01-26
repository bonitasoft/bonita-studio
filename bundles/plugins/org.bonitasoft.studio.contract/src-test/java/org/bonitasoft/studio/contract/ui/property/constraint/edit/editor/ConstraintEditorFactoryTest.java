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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ConstraintEditorFactoryTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Spy
    private ConstraintEditorFactory factory;

    @Mock
    private ContractConstraintExpressionWizard wizard;

    private Composite composite;

    @Mock
    private ContractConstraint constraint;

    @Mock
    private IPropertySourceProvider propertySourceProvider;

    @Before
    public void setUp() throws Exception {
        composite = realm.createComposite();
        doReturn(wizard).when(factory).createWizard(any(ContractConstraint.class), any(IPropertySourceProvider.class));
        doReturn(Dialog.OK).when(factory).openDialog(any(WizardDialog.class));
    }

    @Test
    public void should_openConstraintEditor_open_a_WizardDialog_with_ContractConstraintExpressionWizard() throws Exception {
        factory.openConstraintEditor(composite.getShell(), constraint, propertySourceProvider);
        verify(factory).createWizard(constraint, propertySourceProvider);
        verify(factory).openDialog(any(WizardDialog.class));
    }
}
