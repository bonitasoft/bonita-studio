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
package org.bonitasoft.studio.ui.validator;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

import org.eclipse.core.runtime.IStatus;

public class PathValidator extends StringValidator {

    public static class Builder implements ValidatorBuilder<PathValidator> {

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
        public PathValidator create() {
            return new PathValidator(message, severity);
        }

    }

    public PathValidator(final String message, int severity) {
        super(message, severity);
    }

    @Override
    protected boolean isValid(Optional<String> value) {
        return value.map(path -> Paths.get(new File(path).toURI()).toFile().exists()).orElse(false);
    }

}
