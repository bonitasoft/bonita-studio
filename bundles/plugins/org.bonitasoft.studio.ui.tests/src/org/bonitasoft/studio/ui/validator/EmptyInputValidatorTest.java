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

public class EmptyInputValidatorTest {

    @Test
    public void should_fails_with_given_error_message_input_is_null() throws Exception {
        final EmptyInputValidator validator = new EmptyInputValidator.Builder()
                .withMessage("null value")
                .create();

        final IStatus status = validator.validate(null);

        StatusAssert.assertThat(status).isNotOK().hasMessage("null value");
    }

    @Test
    public void should_fails_with_given_error_message_input_is_empty() throws Exception {
        final EmptyInputValidator validator = new EmptyInputValidator.Builder()
                .withMessage("empty value")
                .create();

        final IStatus status = validator.validate("");

        StatusAssert.assertThat(status).isNotOK().hasMessage("empty value");
    }

    @Test
    public void should_fails_with_given_error_message_input_is_whitespace_only() throws Exception {
        final EmptyInputValidator validator = new EmptyInputValidator.Builder()
                .withMessage("empty value")
                .create();

        final IStatus status = validator.validate(" ");

        StatusAssert.assertThat(status).isNotOK().hasMessage("empty value");
    }

    @Test
    public void should_fails_with_given_severity() throws Exception {
        final EmptyInputValidator validator = new EmptyInputValidator.Builder()
                .withMessage("empty value")
                .warningLevel()
                .create();

        final IStatus status = validator.validate(" ");

        StatusAssert.assertThat(status).isNotOK().hasSeverity(IStatus.WARNING).hasMessage("empty value");
    }

}
