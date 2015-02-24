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


/**
 * @author Romain Bioteau
 *
 */
public class ForbiddenCharactersValidatorTest {

    @Test
    public void should_not_contains_a_sharp_character() throws Exception {
        assertThat(new ForbiddenCharactersValidator("name", '#').validate("hello my name is #rbioteau").isOK()).isFalse();
    }

    @Test
    public void should_not_contains_a_backslash_nor_a_dot_character() throws Exception {
        assertThat(new ForbiddenCharactersValidator("name", '\\', '.').validate("hello my name is #rbioteau").isOK()).isTrue();
        assertThat(new ForbiddenCharactersValidator("name", '\\', '.').validate("hello my name is #rbioteau.").isOK()).isFalse();
        assertThat(new ForbiddenCharactersValidator("name", '\\', '.').validate("hello \\my name is #rbioteau").isOK()).isFalse();
    }

    @Test
    public void should_accept_null_value() throws Exception {
        assertThat(new ForbiddenCharactersValidator("name", '#').validate(null).isOK()).isTrue();
    }

}
