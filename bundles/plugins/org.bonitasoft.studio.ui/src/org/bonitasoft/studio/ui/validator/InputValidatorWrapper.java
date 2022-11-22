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

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IInputValidator;

public class InputValidatorWrapper implements IInputValidator {

    private final IValidator validator;

    public InputValidatorWrapper(IValidator validator) {
        this.validator = validator;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IInputValidator#isValid(java.lang.String)
     */
    @Override
    public String isValid(String newText) {
        final IStatus status = validator.validate(newText);
        return status.isOK() ? null : status.getMessage();
    }

}
