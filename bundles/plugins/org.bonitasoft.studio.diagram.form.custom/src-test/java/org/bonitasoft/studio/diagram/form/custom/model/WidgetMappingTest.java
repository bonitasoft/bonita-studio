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

import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.RadioFormField;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class WidgetMappingTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldConstructor_InitializeWidgetTypeForData() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setDataType(ProcessFactory.eINSTANCE.createStringType());
        assertThat(new WidgetMapping(data).getWidgetType()).isInstanceOf(TextFormField.class);

        data.setDataType(ProcessFactory.eINSTANCE.createDateType());
        assertThat(new WidgetMapping(data).getWidgetType()).isInstanceOf(DateFormField.class);

        data.setDataType(ProcessFactory.eINSTANCE.createBooleanType());
        assertThat(new WidgetMapping(data).getWidgetType()).isInstanceOf(CheckBoxSingleFormField.class);

        data.setDataType(ProcessFactory.eINSTANCE.createEnumType());
        assertThat(new WidgetMapping(data).getWidgetType()).isInstanceOf(RadioFormField.class);

        data.setDataType(ProcessFactory.eINSTANCE.createStringType());
        data.setMultiple(true);
        assertThat(new WidgetMapping(data).getWidgetType()).isInstanceOf(ListFormField.class);

        data.setDataType(ProcessFactory.eINSTANCE.createBooleanType());
        data.setMultiple(true);
        assertThat(new WidgetMapping(data).getWidgetType()).isInstanceOf(CheckBoxMultipleFormField.class);
    }

    @Test
    public void shouldConstructor_InitializeWidgetTypeForDocument() throws Exception {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        assertThat(new WidgetMapping(document).getWidgetType()).isInstanceOf(FileWidget.class);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldConstructor_ThrowIllegalStateExceptionIfDataTypeIsNull() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        new WidgetMapping(data);
    }

}
