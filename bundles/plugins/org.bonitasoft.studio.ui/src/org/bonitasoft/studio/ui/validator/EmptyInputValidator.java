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

import org.eclipse.core.runtime.IStatus;

public class EmptyInputValidator extends StringValidator {

    public static class Builder implements ValidatorBuilder<EmptyInputValidator> {

        private String message;
        private int severity = IStatus.ERROR;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder warningLevel() {
            this.severity = IStatus.WARNING;
            return this;
        }

        public Builder cancelLevel() {
            this.severity = IStatus.CANCEL;
            return this;
        }

        public Builder infoLevel() {
            this.severity = IStatus.INFO;
            return this;
        }

        @Override
        public EmptyInputValidator create() {
            return new EmptyInputValidator(message, severity);
        }

    }

    protected EmptyInputValidator(String errorMessage, int severity) {
        super(errorMessage, severity);
    }

    protected EmptyInputValidator(String errorMessage) {
        super(errorMessage);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.validator.SimpleValidator#validate(java.lang.String)
     */
    @Override
    protected boolean isValid(Optional<String> value) {
        return !value.orElse("").trim().isEmpty();
    }

}
