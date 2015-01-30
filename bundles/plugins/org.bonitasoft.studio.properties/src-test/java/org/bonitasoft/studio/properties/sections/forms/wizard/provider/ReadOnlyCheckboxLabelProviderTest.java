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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.forms.wizard.provider;

import static org.fest.assertions.Assertions.assertThat;

import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.builders.BooleanDataTypeBuilder;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 *
 */
public class ReadOnlyCheckboxLabelProviderTest extends AbstractSWTTestCase {

    private ReadOnlyCheckboxLabelProvider readOnlyCheckboxLabelProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        createDisplayAndRealm();
        readOnlyCheckboxLabelProvider = new ReadOnlyCheckboxLabelProvider();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void shouldIsSelected_ReturnTrue() throws Exception {
        final Data data = DataBuilder.aData().havingDataType(BooleanDataTypeBuilder.create()).build();
        final WidgetMapping mapping = new WidgetMapping(data);
        mapping.setReadOnly(true);
        assertThat(readOnlyCheckboxLabelProvider.isSelected(mapping)).isTrue();
    }

    @Test
    public void shouldIsSelected_ReturnFalse() throws Exception {
        final Data data = DataBuilder.aData().havingDataType(BooleanDataTypeBuilder.create()).build();
        final WidgetMapping mapping = new WidgetMapping(data);
        mapping.setReadOnly(false);
        assertThat(readOnlyCheckboxLabelProvider.isSelected(mapping)).isFalse();
        assertThat(readOnlyCheckboxLabelProvider.isSelected(new Object())).isFalse();
        assertThat(readOnlyCheckboxLabelProvider.isSelected(null)).isFalse();
    }
}
