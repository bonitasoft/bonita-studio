/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface.databinding.validator;

import org.eclipse.core.databinding.validation.IValidator;

/**
 * @author Romain Bioteau
 */
public class MultiValidatorFactory implements IValidatorFactory {

    public static MultiValidatorFactory multiValidator() {
        return new MultiValidatorFactory(new MultiValidator());
    }

    private final MultiValidator multiValidator;

    protected MultiValidatorFactory(final MultiValidator multiValidator) {
        this.multiValidator = multiValidator;
    }

    public MultiValidatorFactory addValidator(final IValidator validator) {
        multiValidator.addValidator(validator);
        return this;
    }

    public MultiValidatorFactory addValidator(final IValidatorFactory validatorFactory) {
        multiValidator.addValidator(validatorFactory.create());
        return this;
    }

    @Override
    public MultiValidator create() {
        return multiValidator;
    }
}
