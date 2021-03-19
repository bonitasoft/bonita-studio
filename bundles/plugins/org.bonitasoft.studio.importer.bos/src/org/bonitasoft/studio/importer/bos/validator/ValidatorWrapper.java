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
package org.bonitasoft.studio.importer.bos.validator;

import java.util.function.Supplier;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ValidatorWrapper<T> implements IValidator<T> {

    private IValidator<T> validatorDelegate;
    private Supplier<Boolean> applyValidator;

    public ValidatorWrapper(IValidator<T> validator, Supplier<Boolean> applyValidator) {
        this.validatorDelegate = validator;
        this.applyValidator = applyValidator;
    }

    @Override
    public IStatus validate(T value) {
        if (applyValidator.get()) {
            return validatorDelegate.validate(value);
        }
        return ValidationStatus.ok();
    }
}
