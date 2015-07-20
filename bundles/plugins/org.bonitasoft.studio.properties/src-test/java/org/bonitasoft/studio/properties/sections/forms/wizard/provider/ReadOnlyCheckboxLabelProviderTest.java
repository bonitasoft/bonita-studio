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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.builders.BooleanDataTypeBuilder;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.jface.viewers.ColumnViewer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Romain Bioteau
 */
public class ReadOnlyCheckboxLabelProviderTest {

    private ReadOnlyCheckboxLabelProvider readOnlyCheckboxLabelProvider;

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        readOnlyCheckboxLabelProvider = new ReadOnlyCheckboxLabelProvider(viewerInShell());
    }

    private ColumnViewer viewerInShell() {
        final ColumnViewer viewer = mock(ColumnViewer.class, Mockito.RETURNS_DEEP_STUBS);
        when(viewer.getControl().getShell()).thenReturn(realm.getShell());
        return viewer;
    }

    @Test
    public void shouldIsSelected_ReturnTrue() throws Exception {
        final Data data = DataBuilder.aData().havingDataType(BooleanDataTypeBuilder.aBooleanDataType()).build();
        final WidgetMapping mapping = new WidgetMapping(data);
        mapping.setReadOnly(true);
        assertThat(readOnlyCheckboxLabelProvider.isSelected(mapping)).isTrue();
    }

    @Test
    public void shouldIsSelected_ReturnFalse() throws Exception {
        final Data data = DataBuilder.aData().havingDataType(BooleanDataTypeBuilder.aBooleanDataType()).build();
        final WidgetMapping mapping = new WidgetMapping(data);
        mapping.setReadOnly(false);
        assertThat(readOnlyCheckboxLabelProvider.isSelected(mapping)).isFalse();
        assertThat(readOnlyCheckboxLabelProvider.isSelected(new Object())).isFalse();
        assertThat(readOnlyCheckboxLabelProvider.isSelected(null)).isFalse();
    }
}
