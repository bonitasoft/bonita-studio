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

import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;

/**
 * @author Romain Bioteau
 * 
 */
public class RelationKindLabelProviderTest {

    private RelationKindLabelProvider labelProviderUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        labelProviderUnderTest = new RelationKindLabelProvider();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldGetText_Returns_ValidLabel_For_RelationField_Kind() throws Exception {
        assertThat(labelProviderUnderTest.getText(Type.COMPOSITION)).isEqualTo(Messages.composition);
        assertThat(labelProviderUnderTest.getText(Type.AGGREGATION)).isEqualTo(Messages.aggregation);
        RelationField f = new RelationField();
        f.setType(Type.COMPOSITION);
        assertThat(labelProviderUnderTest.getText(f)).isEqualTo(Messages.composition);
        f.setType(Type.AGGREGATION);
        assertThat(labelProviderUnderTest.getText(f)).isEqualTo(Messages.aggregation);

        SimpleField sf = new SimpleField();
        assertThat(labelProviderUnderTest.getText(sf)).isEqualTo("");
    }
}
