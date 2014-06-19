/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.validator;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;


/**
 * @author Romain Bioteau
 *
 */
public class UserEmptyInputValidator implements IValidator {

    private final String inputName;

    private final IObservableValue userSelection;

    public UserEmptyInputValidator(final String inputName, final IObservableValue userSelection) {
        this.inputName = inputName ;
        this.userSelection = userSelection;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(final Object input) {
        if (isUserNotSelected()) {
            return ValidationStatus.ok();
        }

        if(input == null || input.toString().isEmpty()){
            return ValidationStatus.error(Messages.bind(Messages.emptyField,inputName)) ;
        }
        return ValidationStatus.ok() ;
    }

    private boolean isUserNotSelected() {
        return userSelection == null || userSelection.getValue() == null;
    }
}
