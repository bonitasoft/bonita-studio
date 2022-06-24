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

public class RegExpValidatorTest {

    @Test
    public void should_fails_with_given_error_message_when_input_does_not_match_regExp() throws Exception {
        final RegExpValidator validator = new RegExpValidator.Builder()
                .withMessage("'%v' cannot contains whitespace")
                .matches("^[^\\d\\s]*$")
                .create();

        final IStatus status = validator.validate("I've got white space");

        StatusAssert.assertThat(status).isNotOK().hasMessage("'I've got white space' cannot contains whitespace");
    }

    @Test
    public void should_not_fail_when_input_matches_regExp() throws Exception {
        final RegExpValidator validator = new RegExpValidator.Builder()
                .withMessage("'%v' cannot contains whitespace")
                .matches("^[^\\d\\s]*$")
                .create();

        final IStatus status = validator.validate("I'veGotAnyNospace");

        StatusAssert.assertThat(status).isOK();
    }

}
