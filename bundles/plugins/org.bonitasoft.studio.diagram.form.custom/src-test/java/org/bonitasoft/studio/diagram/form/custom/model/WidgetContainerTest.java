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
package org.bonitasoft.studio.diagram.form.custom.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.TextFormField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class WidgetContainerTest {

    private Form form;
    private Group group;
    private TextFormField w1;
    private CheckBoxSingleFormField w2;
    private TextFormField w3;
    private CheckBoxSingleFormField w4;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        form = FormFactory.eINSTANCE.createForm();
        group = FormFactory.eINSTANCE.createGroup();
        w1 = FormFactory.eINSTANCE.createTextFormField();
        w2 = FormFactory.eINSTANCE.createCheckBoxSingleFormField();
        w3 = FormFactory.eINSTANCE.createTextFormField();
        w4 = FormFactory.eINSTANCE.createCheckBoxSingleFormField();
        form.getWidgets().add(w1);
        form.getWidgets().add(w2);
        group.getWidgets().add(w3);
        group.getWidgets().add(w4);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldGetWidgets_ReturnContainedWidgetForForm() throws Exception {
        final WidgetContainer container = new WidgetContainer(form);
        assertThat(container.getWidgets()).containsExactly(w1, w2);
    }

    @Test
    public void shouldGetWidgets_ReturnContainedWidgetForGroup() throws Exception {
        final WidgetContainer container = new WidgetContainer(group);
        assertThat(container.getWidgets()).containsExactly(w3, w4);
    }

    @Test
    public void shouldIsGroup_ReturnFalse() throws Exception {
        final WidgetContainer container = new WidgetContainer(form);
        assertThat(container.isGroup()).isFalse();
    }

    @Test
    public void shouldIsGroup_ReturnTrue() throws Exception {
        final WidgetContainer container = new WidgetContainer(group);
        assertThat(container.isGroup()).isTrue();
    }
}
