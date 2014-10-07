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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
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
public class FieldDecoratorProviderTest {

    @Spy
    private FieldDecoratorProvider fieldDecoratorProvider;

    @Mock
    private Control control;

    @Mock
    private FieldDecoration fieldDecoration;

    @Mock
    private ControlDecoration controlDecoration;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn(fieldDecoration).when(fieldDecoratorProvider).getDecorator(FieldDecorationRegistry.DEC_CONTENT_PROPOSAL);
        doReturn(controlDecoration).when(fieldDecoratorProvider).newControlDecoration(any(Control.class), anyInt());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_createControlDecorator_attach_a_ControlDecorator_to_a_Control() throws Exception {
        final ControlDecoration decorator = fieldDecoratorProvider.createControlDecorator(control, "a description",
                FieldDecorationRegistry.DEC_CONTENT_PROPOSAL, SWT.RIGHT);
        assertThat(decorator).isEqualTo(controlDecoration);
        verify(decorator).setDescriptionText("a description");
        verify(decorator).setImage(any(Image.class));
        verify(control).addControlListener(any(CellEditorControlAdapter.class));
    }
}
