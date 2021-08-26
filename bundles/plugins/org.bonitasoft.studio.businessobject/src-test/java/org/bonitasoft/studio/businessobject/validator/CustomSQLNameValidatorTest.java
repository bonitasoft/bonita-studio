/**
 * Copyright (C) 2020 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.validator;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;

import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class CustomSQLNameValidatorTest {
    
    @Test
    public void should_validate_db_specific_keyword() throws Exception {
        CustomSQLNameValidator customSQLNameValidator = new CustomSQLNameValidator(20);
        
        IStatus status = customSQLNameValidator.validate("Index");
        
        assertThat(status).isWarning();
    }
    
    @Test
    public void should_validate_sql_keyword() throws Exception {
        CustomSQLNameValidator customSQLNameValidator = new CustomSQLNameValidator(20);
        
        IStatus status = customSQLNameValidator.validate("Table");
        
        assertThat(status).isError();
    }

}
