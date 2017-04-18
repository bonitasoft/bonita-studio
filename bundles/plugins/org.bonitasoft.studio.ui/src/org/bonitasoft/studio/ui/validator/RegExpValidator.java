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
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;

public class RegExpValidator extends StringValidator {

    public static class Builder implements ValidatorBuilder<RegExpValidator> {

        private String message;
        private int severity = IStatus.ERROR;
        private String regExp;

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

        public Builder matches(String regExp) {
            this.regExp = regExp;
            return this;
        }

        @Override
        public RegExpValidator create() {
            return new RegExpValidator(regExp, message, severity);
        }

    }

    private final String regex;

    protected RegExpValidator(final String regex, final String message, final int severity) {
        super(message, severity);
        this.regex = regex;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.validator.SimpleValidator#isValid(java.lang.String)
     */
    @Override
    protected boolean isValid(Optional<String> value) {
        return Pattern.matches(regex, value.orElse(""));
    }

}
