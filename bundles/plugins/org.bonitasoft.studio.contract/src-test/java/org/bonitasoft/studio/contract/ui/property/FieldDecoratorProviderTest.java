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

import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
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
public class FieldDecoratorProviderTest extends AbstractSWTTestCase {


    private FieldDecoratorProvider fieldDecoratorProvider;

    private Control control;


    @Mock
    private ControlDecoration controlDecoration;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final Composite composite = createDisplayAndRealm();
        control = new Text(composite, SWT.NONE);
        fieldDecoratorProvider = new FieldDecoratorProvider();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void should_createControlDecorator_attach_a_ControlDecorator_to_a_Control() throws Exception {
        final ControlDecoration decorator = fieldDecoratorProvider.createControlDecorator(control, "a description",
                FieldDecorationRegistry.DEC_CONTENT_PROPOSAL, SWT.RIGHT);
        assertThat(decorator.getControl()).isEqualTo(control);
        assertThat(decorator.getDescriptionText()).isEqualTo("a description");
        assertThat(decorator.getImage()).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_getFieldDecorator_thorw_IllegalArgumentException() throws Exception {
        fieldDecoratorProvider.getDecorator("fake type");
    }
}
