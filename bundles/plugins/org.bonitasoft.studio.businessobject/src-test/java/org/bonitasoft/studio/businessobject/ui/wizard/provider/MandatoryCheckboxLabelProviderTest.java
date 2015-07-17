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

import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
        labelProviderUnderTest = new MandatoryCheckboxLabelProvider();
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
