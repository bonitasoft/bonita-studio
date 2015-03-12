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
package org.bonitasoft.studio.businessobject.ui.wizard.provider;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.bonitasoft.engine.bdm.model.field.SimpleField;

/**
 * @author Romain Bioteau
 *
 */
public class MultipleCheckboxLabelProviderTest extends AbstractSWTTestCase {

    private MultipleCheckboxLabelProvider labelProviderUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        createDisplayAndRealm();
        labelProviderUnderTest = new MultipleCheckboxLabelProvider();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void shouldIsEnable_ReturnsTrue() throws Exception {
        assertThat(labelProviderUnderTest.isEnabled(null)).isTrue();
        final SimpleField f = new SimpleField();
        assertThat(labelProviderUnderTest.isEnabled(f)).isTrue();
    }

    @Test
    public void shouldIsSelected_ReturnsFalse() throws Exception {
        assertThat(labelProviderUnderTest.isSelected(null)).isFalse();
        final SimpleField f = new SimpleField();
        f.setCollection(Boolean.FALSE);
        assertThat(labelProviderUnderTest.isSelected(f)).isFalse();
    }

    @Test
    public void shouldIsSelected_ReturnsTrue() throws Exception {
        final SimpleField f = new SimpleField();
        f.setCollection(Boolean.TRUE);
        assertThat(labelProviderUnderTest.isSelected(f)).isTrue();
    }

}
