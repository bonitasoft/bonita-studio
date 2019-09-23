/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.validator;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class LengthValidatorTest {

    @Test
    public void should_fails_with_given_error_message_input_is_too_long() throws Exception {
        final LengthValidator validator = new LengthValidator.Builder()
                .withMessage("'%v' is too long (max = %max)")
                .maxLength(10)
                .create();

        final IStatus status = validator.validate("I'm too long man");

        StatusAssert.assertThat(status).isNotOK().hasMessage("'I'm too long man' is too long (max = 10)");
    }

    @Test
    public void should_fails_with_given_error_message_input_is_too_short() throws Exception {
        final LengthValidator validator = new LengthValidator.Builder()
                .withMessage("'%v' must have at least %min characters")
                .minLength(8)
                .create();

        final IStatus status = validator.validate("short");

        StatusAssert.assertThat(status).isNotOK().hasMessage("'short' must have at least 8 characters");
    }

}
