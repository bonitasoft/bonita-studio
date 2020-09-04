/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.jface.databinding.validator;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;

import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class EmptyInputValidatorTest {

    @Test
    public void should_fail_for_null_input() throws Exception {
        final EmptyInputValidator emptyInputValidator = new EmptyInputValidator("name");

        final IStatus status = emptyInputValidator.validate(null);

        assertThat(status).isNotOK();
    }

    @Test
    public void should_fail_for_empty_string_input() throws Exception {
        final EmptyInputValidator emptyInputValidator = new EmptyInputValidator("name");

        final IStatus status = emptyInputValidator.validate("");

        assertThat(status).isNotOK();
    }

    @Test
    public void should_not_fail_for_not_empty_string_input() throws Exception {
        final EmptyInputValidator emptyInputValidator = new EmptyInputValidator("name");

        final IStatus status = emptyInputValidator.validate("a");

        assertThat(status).isOK();
    }

}
