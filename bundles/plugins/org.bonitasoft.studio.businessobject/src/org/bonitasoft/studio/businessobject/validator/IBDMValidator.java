/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.validator;

import java.util.Arrays;
import java.util.Optional;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

public interface IBDMValidator<T> extends IValidator<T> {

    public String getValidatorType();

    /**
     * The status messages are prefixed with the validator type, in order to be displayed in a high level
     */
    public default IStatus validateWithType(T value) {
        return validate(Optional.empty(), value);
    }

    /**
     * The status messages are prefixed with the business object name and the validator type, in order to be displayed in a
     * high level
     */
    public default IStatus validateWithNameAndType(String name, T value) {
        return validate(Optional.ofNullable(name), value);
    }

    default IStatus validate(Optional<String> name, T value) {
        IStatus status = validate(value);
        if (!status.isOK()) {
            if (status instanceof MultiStatus) {
                MultiStatus statusWithType = new MultiStatus(status.getPlugin(), status.getCode(), "", null);
                Arrays.asList(status.getChildren())
                        .stream()
                        .filter(s -> !s.isOK())
                        .map(s -> createStatus(s, name))
                        .forEach(statusWithType::add);
                return statusWithType;
            }
            return createStatus(status, name);
        }
        return status;
    }

    default Status createStatus(IStatus status, Optional<String> name) {
        return name.isPresent()
                ? new Status(status.getSeverity(), status.getPlugin(),
                        messageWithNameAndType(name.get(), status.getMessage()))
                : new Status(status.getSeverity(), status.getPlugin(), messageWithType(status.getMessage()));
    }

    default String messageWithNameAndType(String name, String message) {
        return String.format("[%s] [%s] %s", name, getValidatorType(), message);
    };

    default String messageWithType(String message) {
        return String.format("[%s] %s", getValidatorType(), message);
    };

}
