/**
 * Copyright (C) 2015 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author Romain Bioteau
 */
public class MultiValidator implements IValidator {

    private final List<IValidator> validators;

    public MultiValidator(final List<IValidator> validators) {
        this.validators = validators;
    }

    public MultiValidator() {
        validators = new ArrayList<IValidator>();
    }

    public void addValidator(final IValidator validator) {
        if (!validators.contains(validator)) {
            validators.add(validator);
        }
    }

    @Override
    public IStatus validate(final Object value) {
        for (final IValidator validator : validators) {
            final IStatus status = validator.validate(value);
            if (!status.isOK()) {
                return status;
            }
        }
        return Status.OK_STATUS;
    }

}
