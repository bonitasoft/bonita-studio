/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.validator;

import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.identity.actors.validator.ValidatorConstants;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;

public class DisplayNameValidator implements IValidator<String> {

    private InputLengthValidator inputLengthValidator;

    public DisplayNameValidator() {
        inputLengthValidator = new InputLengthValidator(Messages.name, ValidatorConstants.LONG_FIELD_MAX_LENGTH);
    }

    @Override
    public IStatus validate(String value) {
        return inputLengthValidator.validate(value);
    }

}
