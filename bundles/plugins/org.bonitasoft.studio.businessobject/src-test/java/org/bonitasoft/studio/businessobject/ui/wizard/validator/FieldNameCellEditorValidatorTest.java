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
package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.SimpleField;

/**
 * @author Romain Bioteau
 * 
 */
public class FieldNameCellEditorValidatorTest {

    private FieldNameCellEditorValidator validatorUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        BusinessObject bo = new BusinessObject();
        SimpleField attr = new SimpleField();
        attr.setName("existingAttribute");
        bo.getFields().add(attr);
        SimpleField currentAttr = new SimpleField();
        validatorUnderTest = new FieldNameCellEditorValidator(bo, currentAttr);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldIsValid_ReturnNull() throws Exception {
        assertThat(validatorUnderTest.isValid("name")).isNull();
        assertThat(validatorUnderTest.isValid("employeeRelation")).isNull();
    }

    @Test
    public void shouldIsValid_ReturnAnErrorMessage() throws Exception {
        assertThat(validatorUnderTest.isValid(Field.PERSISTENCE_ID)).isNotNull().isNotEmpty();
        assertThat(validatorUnderTest.isValid(Field.PERSISTENCE_ID.toLowerCase())).isNotNull().isNotEmpty();
        assertThat(validatorUnderTest.isValid(Field.PERSISTENCE_VERSION)).isNotNull().isNotEmpty();
        assertThat(validatorUnderTest.isValid(Field.PERSISTENCE_VERSION.toLowerCase())).isNotNull().isNotEmpty();
        assertThat(validatorUnderTest.isValid("my Name")).isNotNull().isNotEmpty();
        assertThat(validatorUnderTest.isValid("my#Name")).isNotNull().isNotEmpty();
        assertThat(validatorUnderTest.isValid("")).isNotNull().isNotEmpty();
        assertThat(validatorUnderTest.isValid(null)).isNotNull().isNotEmpty();
        assertThat(validatorUnderTest.isValid("existingAttribute")).isNotNull().isNotEmpty();
    }

}
