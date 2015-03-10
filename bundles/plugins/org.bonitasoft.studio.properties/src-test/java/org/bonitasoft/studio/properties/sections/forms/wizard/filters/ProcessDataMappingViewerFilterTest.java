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
package org.bonitasoft.studio.properties.sections.forms.wizard.filters;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class ProcessDataMappingViewerFilterTest {

    private ProcessDataMappingViewerFilter filterUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        filterUnderTest = new ProcessDataMappingViewerFilter();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldSelect_ReturnFalse() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setDataType(ProcessFactory.eINSTANCE.createStringType());
        WidgetMapping mapping = new WidgetMapping(data);
        assertThat(filterUnderTest.select(null, null, mapping)).isFalse();

        final Document document = ProcessFactory.eINSTANCE.createDocument();
        mapping = new WidgetMapping(document);
        assertThat(filterUnderTest.select(null, null, mapping)).isFalse();
    }

    @Test
    public void shouldSelect_ReturnTrue() throws Exception {
        final Data data = ProcessFactory.eINSTANCE.createBusinessObjectData();
        data.setDataType(ProcessFactory.eINSTANCE.createBusinessObjectType());
        final WidgetMapping mapping = new WidgetMapping(data);
        final SimpleField f = new SimpleField();
        f.setType(FieldType.STRING);
        final WidgetMapping childMpping = new WidgetMapping(f);
        mapping.addChild(childMpping);
        assertThat(filterUnderTest.select(null, null, childMpping)).isTrue();
    }

}
