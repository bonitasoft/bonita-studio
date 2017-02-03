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

public class LengthValidator extends StringValidator {

    public static class Builder implements ValidatorBuilder<LengthValidator> {

        private String message;
        private int severity = IStatus.ERROR;
        private int minLength;
        private int maxLength;

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

        public Builder maxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public Builder minLength(int minLength) {
            this.minLength = minLength;
            return this;
        }

        @Override
        public LengthValidator create() {
            return new LengthValidator(message, severity, minLength, maxLength);
        }

    }

    private final int maxLength;
    private final int minLength;

    protected LengthValidator(final String message, int severity, final int minChar, final int maxChar) {
        super(message, severity);
        this.maxLength = maxChar;
        this.minLength = minChar;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.validator.SimpleValidator#isValid(java.lang.String)
     */
    @Override
    protected boolean isValid(Optional<String> value) {
        final int length = value.orElse("").trim().length();
        return length >= minLength && length <= maxLength;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.validator.SimpleValidator#formatMessage(java.lang.String)
     */
    @Override
    protected String formatMessage(Optional<String> value) {
        return super.formatMessage(value).replace("%min", String.valueOf(minLength)).replace("%max",
                String.valueOf(maxLength));
    }

}
