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

import java.util.Optional;

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public abstract class StringValidator extends TypedValidator<String, IStatus> {

    protected final Optional<String> message;
    private final int severity;

    public StringValidator(final String message) {
        this(message, IStatus.ERROR);
    }

    public StringValidator(String message, int severity) {
        this.message = Optional.ofNullable(message);
        this.severity = severity;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.validator.TypedValidator#doValidate(java.lang.Object)
     */
    @Override
    protected final IStatus doValidate(Optional<String> value) {
        return isValid(value) ? ValidationStatus.ok() : createFailureStatus(formatMessage(value));
    }

    protected String formatMessage(Optional<String> value) {
        return message.orElse("").replace("%v", value.orElse("null"));
    }

    /**
     * @param the value to validate
     * @return true if the value is valid, false otherwise
     */
    protected abstract boolean isValid(Optional<String> value);

    protected IStatus createFailureStatus(String message) {
        switch (severity) {
            case IStatus.WARNING:
                return ValidationStatus.warning(message);
            case IStatus.CANCEL:
                return ValidationStatus.cancel(message);
            case IStatus.INFO:
                return ValidationStatus.info(message);
            case IStatus.ERROR:
            default:
                return ValidationStatus.error(message);
        }
    }

}
