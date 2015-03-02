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
package org.bonitasoft.studio.properties.sections.forms.wizard.provider;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class WidgetMappingTreeContentProviderTest {

    private WidgetMappingTreeContentProvider contentProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contentProvider = new WidgetMappingTreeContentProvider();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link org.bonitasoft.studio.properties.sections.forms.wizard.provider.WidgetMappingTreeContentProvider#getElements(java.lang.Object)}.
     */
    @Test
    public void shouldGetElements_ReturnInputAsArray() throws Exception {
        final List<WidgetMapping> input = new ArrayList<WidgetMapping>();
        final Data myData = ProcessFactory.eINSTANCE.createData();
        myData.setDataType(ProcessFactory.eINSTANCE.createStringType());
        final WidgetMapping mapping = new WidgetMapping(myData);

        input.add(mapping);
        assertThat(contentProvider.getElements(input)).isNotNull().containsOnly(mapping);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetElements_ThrowAnIllegalArgumentExceptionIfInputIsNotACollectionOfEObject() throws Exception {
        final List<String> input = new ArrayList<String>();
        input.add("toto");
        contentProvider.getElements(input);
    }

    public void shouldHasChildren_ReturnAlwaysReturnsFalse() throws Exception {
        final Data myData = ProcessFactory.eINSTANCE.createData();
        final WidgetMapping mapping = new WidgetMapping(myData);
        assertThat(contentProvider.hasChildren(mapping)).isFalse();
        assertThat(contentProvider.hasChildren(null)).isFalse();
    }

}
