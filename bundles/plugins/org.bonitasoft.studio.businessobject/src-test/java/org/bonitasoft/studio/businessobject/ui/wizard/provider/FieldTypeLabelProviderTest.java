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

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class FieldTypeLabelProviderTest {

    private FieldTypeLabelProvider labelProviderUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        labelProviderUnderTest = new FieldTypeLabelProvider();
    }

    @Test
    public void shouldGetText_Returns_ValidLabel_ForETypes() throws Exception {
        assertThat(labelProviderUnderTest.getText(FieldType.STRING)).isEqualTo(FieldType.STRING.name());
        final SimpleField f = new SimpleField();
        f.setType(FieldType.BOOLEAN);
        assertThat(labelProviderUnderTest.getText(f)).isEqualTo(FieldType.BOOLEAN.name());
    }

}
