/**
 * Copyright (C) 2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.uniqueValidator;

import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class UniqueValidatorTest {

    @Test
    public void should_return_a_valid_status_for_unique_string_value_in_name_list() throws Exception {
        //Given
        final UniqueValidator validator = uniqueValidator().in(newArrayList("Romain", "Colin", "Vincent")).create();

        //When
        final IStatus status = validator.validate("Robert");

        //Then
        assertThat(status).isOK();
    }

    @Test
    public void should_return_a_error_status_for_duplicated_string_value_in_name_list() throws Exception {
        //Given
        final UniqueValidator validator = uniqueValidator().in(newArrayList("Romain", "Colin", "Vincent")).create();

        //When
        final IStatus status = validator.validate("Colin");

        //Then
        assertThat(status).isNotOK();
    }

    @Test
    public void should_return_a_valid_status_for_unique_int_value_in_int_list() throws Exception {
        //Given
        final UniqueValidator validator = uniqueValidator().in(newArrayList(1, 2, 3)).create();

        //When
        final IStatus status = validator.validate(4);

        //Then
        assertThat(status).isOK();
    }

    @Test
    public void should_return_a_error_status_for_duplicated_int_value_in_int_list() throws Exception {
        //Given
        final UniqueValidator validator = uniqueValidator().in(newArrayList(1, 2, 3)).create();

        //When
        final IStatus status = validator.validate(3);

        //Then
        assertThat(status).isNotOK();
    }

    @Test
    public void should_return_a_error_status_for_duplicated_lastName_in_person_list() throws Exception {
        //Given
        final UniqueValidator validator = uniqueValidator().in(newArrayList(new Person("Lebron", "James"),
                new Person("Kobe", "Bryant"),
                new Person("Derrick", "Rose"))).onProperty("lastName").create();

        //When
        final IStatus status = validator.validate("Bryant");

        //Then
        assertThat(status).isNotOK();
    }

    @Test
    public void should_return_a_valid_status_for_unique_firstName_in_person_list() throws Exception {
        //Given
        final UniqueValidator validator = uniqueValidator().in(newArrayList(new Person("Lebron", "James"),
                new Person("Kobe", "Bryant"),
                new Person("Derrick", "Rose"))).onProperty("lastName").create();

        //When
        final IStatus status = validator.validate("Dwayne");

        //Then
        assertThat(status).isOK();
    }

    @SuppressWarnings("unused")
    private class Person {

        private String firstName;
        private String lastName;

        Person(final String firstName, final String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(final String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(final String lastName) {
            this.lastName = lastName;
        }
    }
}
