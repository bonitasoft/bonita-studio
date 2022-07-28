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

import java.io.File;
import java.nio.file.Paths;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

import com.google.common.base.Strings;

public class PathValidator extends TypedValidator<String, IStatus> {

    private final String inputName;
    private String message;

    public PathValidator(final String inputName) {
        this.inputName = inputName;
    }

    @Override
    public IStatus doValidate(final String value) {
        if (Strings.isNullOrEmpty(value)) {
            return ValidationStatus.error(message == null ? NLS.bind(Messages.invalidPath, inputName) : message);
        }
        final String path = value.toString();
        final File file = Paths.get(new File(path).toURI()).toFile();
        if (!file.exists()) {
            return ValidationStatus.error(message == null ? NLS.bind(Messages.pathDoesNotExist, inputName) : message);
        }
        return ValidationStatus.ok();
    }

    public PathValidator overrideMessage(String message) {
        this.message = message;
        return this;
    }

}
