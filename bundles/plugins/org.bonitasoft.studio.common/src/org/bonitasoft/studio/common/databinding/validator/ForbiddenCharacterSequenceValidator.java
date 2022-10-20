/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.databinding.validator;

import java.util.Optional;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

/**
 * Validates that the input value does not contain a particular character sequence.<br/>
 * This could be performed with {@link RegExpValidator} and a negative lookaround,
 * but this specialized implementation has better performances.
 * 
 * @author Vincent Hemery
 */
public class ForbiddenCharacterSequenceValidator implements IValidator {

    private final String errorMessage;
    private final String forbiddenSequence;

    /**
     * Create a new validator.
     * 
     * @param errorMessage the error message in case of failure
     * @param forbiddenSequence the character sequence that will make validator fail when found in input
     */
    public ForbiddenCharacterSequenceValidator(final String errorMessage, final String forbiddenSequence) {
        this.errorMessage = errorMessage;
        this.forbiddenSequence = forbiddenSequence;
    }

    @Override
    public IStatus validate(final Object input) {
        if (Optional.ofNullable(input).map(Object::toString).filter(s -> s.contains(forbiddenSequence)).isPresent()) {
            return ValidationStatus.error(errorMessage);
        } else {
            return ValidationStatus.ok();
        }
    }

}
