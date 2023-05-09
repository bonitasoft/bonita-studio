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
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class MultiValidatorTest {

    @Test
    public void should_fails_validation_if_one_of_the_validator_fail() throws Exception {
        final MultiValidator multiValidator = new MultiValidator.Builder()
                .havingValidators(v -> ValidationStatus.ok(), v -> ValidationStatus.error("oups"))
                .create();

        final IStatus status = multiValidator.validate("Hello");

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_succeed_validation_if_all_of_the_validator_are_in_success() throws Exception {
        final MultiValidator multiValidator = new MultiValidator.Builder()
                .havingValidators(v -> ValidationStatus.ok(), v -> ValidationStatus.ok())
                .create();

        final IStatus status = multiValidator.validate("Hello");

        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_collect_all_statuses_in_a_MultiStatus() throws Exception {
        final MultiValidator multiValidator = new MultiValidator.Builder()
                .havingValidators(v -> ValidationStatus.ok(),
                        v -> ValidationStatus.warning("careful"),
                        v -> ValidationStatus.error("error"))
                .create();

        final IStatus status = multiValidator.validate("Hello");

        StatusAssert.assertThat(status).isEqualTo(ValidationStatus.error("error"));
    }

}
