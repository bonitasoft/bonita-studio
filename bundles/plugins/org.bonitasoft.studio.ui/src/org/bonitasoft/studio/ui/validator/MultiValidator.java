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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.runtime.LocalizationUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

/**
 * A multi validator wraps a list of IValidator.
 * Each validator is evaluated when validation is triggered.
 * A {@link MultiStatus} is returned by the {@link IValidator#validate(Object) validate(Object value)} method.
 */
public class MultiValidator implements IValidator {

    private static final String UNKNOWN_ID = "unknown"; //$NON-NLS-1$

    public static class Builder implements ValidatorBuilder<MultiValidator> {

        private final List<IValidator> validators = new ArrayList<>();

        public Builder havingValidators(IValidator... validators) {
            this.validators.addAll(Stream.of(validators).collect(Collectors.toList()));
            return this;
        }

        public Builder havingValidators(ValidatorBuilder<?>... validators) {
            this.validators.addAll(Stream.of(validators).map(ValidatorBuilder::create).collect(Collectors.toList()));
            return this;
        }

        @Override
        public MultiValidator create() {
            return new MultiValidator(validators);
        }

    }

    private final List<IValidator> validators;

    protected MultiValidator(final List<IValidator> validators) {
        this.validators = validators;
    }

    @Override
    public IStatus validate(final Object value) {
        final MultiStatus multiStatus = new MultiStatus(UNKNOWN_ID, IStatus.OK, LocalizationUtils.safeLocalize("ok"), null);
        validators.stream()
                .map(validator -> validator.validate(value))
                .forEach(status -> multiStatus.add(status));
        return maxSeverityStatus(multiStatus);
    }

    protected IStatus maxSeverityStatus(MultiStatus multiStatus) {
        return Stream.of(multiStatus.getChildren()).collect(Collectors.<IStatus> maxBy(
                (s1, s2) -> Integer.valueOf(s1.getSeverity()).compareTo(Integer.valueOf(s2.getSeverity()))))
                .orElse(ValidationStatus.ok());
    }

}
