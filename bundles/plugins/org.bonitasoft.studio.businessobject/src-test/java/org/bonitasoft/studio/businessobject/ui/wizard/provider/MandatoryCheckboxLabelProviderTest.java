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
package org.bonitasoft.studio.businessobject.ui.wizard.provider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.jface.viewers.ColumnViewer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Romain Bioteau
 */
public class MandatoryCheckboxLabelProviderTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    private MandatoryCheckboxLabelProvider labelProviderUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        labelProviderUnderTest = new MandatoryCheckboxLabelProvider(viewerInShell());
    }

    private ColumnViewer viewerInShell() {
        final ColumnViewer viewer = mock(ColumnViewer.class, Mockito.RETURNS_DEEP_STUBS);
        when(viewer.getControl().getShell()).thenReturn(realm.getShell());
        return viewer;
    }

    @Test
    public void shouldIsEnable_ReturnsTrue() throws Exception {
        assertThat(labelProviderUnderTest.isEnabled(null)).isTrue();
        final SimpleField f = new SimpleField();
        assertThat(labelProviderUnderTest.isEnabled(f)).isTrue();
    }

    @Test
    public void shouldIsEnable_ReturnsFalse() throws Exception {
        assertThat(labelProviderUnderTest.isEnabled(null)).isTrue();
        final SimpleField f = new SimpleField();
        f.setCollection(true);
        assertThat(labelProviderUnderTest.isEnabled(f)).isFalse();
    }

    @Test
    public void shouldIsEnable_ReturnsTrue_WithNullCollection() throws Exception {
        assertThat(labelProviderUnderTest.isEnabled(null)).isTrue();
        final SimpleField f = new SimpleField();
        f.setCollection(null);
        assertThat(labelProviderUnderTest.isEnabled(f)).isTrue();
    }

    @Test
    public void shouldIsSelected_ReturnsFalse() throws Exception {
        assertThat(labelProviderUnderTest.isSelected(null)).isFalse();
        final SimpleField f = new SimpleField();
        f.setNullable(true);
        assertThat(labelProviderUnderTest.isSelected(f)).isFalse();
    }

    @Test
    public void shouldIsSelected_ReturnsTrue() throws Exception {
        final SimpleField f = new SimpleField();
        f.setNullable(false);
        assertThat(labelProviderUnderTest.isSelected(f)).isTrue();
    }

}
