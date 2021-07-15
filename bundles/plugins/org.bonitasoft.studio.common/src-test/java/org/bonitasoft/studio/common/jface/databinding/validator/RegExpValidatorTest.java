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
package org.bonitasoft.studio.common.jface.databinding.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class RegExpValidatorTest {

    @Test
    public void should_validate_not_accept_string_containing_slash() throws Exception {
        final RegExpValidator regExpValidator = new RegExpValidator("error message", "^[^/]*$");

        assertThat(regExpValidator.validate(null).isOK()).isTrue();
        assertThat(regExpValidator.validate("").isOK()).isTrue();
        assertThat(regExpValidator.validate("hello, my name is ****").isOK()).isTrue();

        assertThat(regExpValidator.validate("hello, my n/ame").isOK()).isFalse();
        assertThat(regExpValidator.validate("/hello").isOK()).isFalse();
        assertThat(regExpValidator.validate("hello/").isOK()).isFalse();
    }

}
