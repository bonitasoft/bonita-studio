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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.junit.After;
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
public class ConstraintEditorWizardDialogTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Mock
    private IWizard newWizard;
    private ConstraintEditorWizardDialog dialog;
    private Composite composite;
    private Image image;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        composite = realm.createComposite();
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        dialog = spy(new ConstraintEditorWizardDialog(composite.getShell(), newWizard));
        image = realm.createImage();
        doReturn(image).when(dialog).getHelpImage();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        image.dispose();
    }

    @Test
    public void should_createHelpControl_create_a_toolbar_with_help_button() throws Exception {
        final Control helpControl = dialog.createHelpControl(composite);
        assertThat(helpControl).isInstanceOf(ToolBar.class);
        assertThat(((ToolBar) helpControl).getItems()).hasSize(1);
        final ToolItem item = ((ToolBar) helpControl).getItem(0);
        assertThat(item.getImage()).isEqualTo(image);
    }

}
