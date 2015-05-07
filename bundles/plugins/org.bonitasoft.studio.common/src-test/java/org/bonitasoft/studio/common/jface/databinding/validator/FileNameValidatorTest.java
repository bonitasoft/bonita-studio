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

import org.junit.Test;

public class FileNameValidatorTest {

    @Test
    public void should_fails_for_value_containing_a_backslash_character() throws Exception {
        assertThat(new FileNameValidator("").validate("My\\Diagram")).isNotOK();
    }

    @Test
    public void should_fails_for_value_containing_a_slash_character() throws Exception {
        assertThat(new FileNameValidator("").validate("My/Diagram")).isNotOK();
    }

    @Test
    public void should_fails_for_value_containing_a_column_character() throws Exception {
        assertThat(new FileNameValidator("").validate("My:Diagram")).isNotOK();
    }

    @Test
    public void should_fails_for_value_containing_a_largerThan_character() throws Exception {
        assertThat(new FileNameValidator("").validate("My>Diagram")).isNotOK();
    }

    @Test
    public void should_fails_for_value_containing_a_lowerThan_character() throws Exception {
        assertThat(new FileNameValidator("").validate("My<Diagram")).isNotOK();
    }

    @Test
    public void should_fails_for_value_containing_a_pipe_character() throws Exception {
        assertThat(new FileNameValidator("").validate("My|Diagram")).isNotOK();
    }

    @Test
    public void should_fails_for_value_containing_a_questionMark_character() throws Exception {
        assertThat(new FileNameValidator("").validate("My?Diagram")).isNotOK();
    }

    @Test
    public void should_fails_for_value_containing_a_percent_character() throws Exception {
        assertThat(new FileNameValidator("").validate("My%Diagram")).isNotOK();
    }

    @Test
    public void should_fails_for_value_containing_a_sharp_character() throws Exception {
        assertThat(new FileNameValidator("").validate("My#Diagram")).isNotOK();
    }

    @Test
    public void should_fails_for_value_containing_an_asterix_character() throws Exception {
        assertThat(new FileNameValidator("").validate("My*Diagram")).isNotOK();
    }

    @Test
    public void should_fails_for_value_containing_a_dollar_character() throws Exception {
        assertThat(new FileNameValidator("").validate("My$Diagram")).isNotOK();
    }

    @Test
    public void should_not_fail_for_other_values() throws Exception {
        assertThat(new FileNameValidator("").validate("My Diagram")).isOK();
    }
}
