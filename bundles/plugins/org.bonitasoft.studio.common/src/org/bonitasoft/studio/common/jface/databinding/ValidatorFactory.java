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
package org.bonitasoft.studio.common.jface.databinding;

import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.URLEncodableInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.UTF8InputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.UniqueValidator;
import org.eclipse.core.databinding.validation.IValidator;
// github.com/bonitasoft/bonita-studio.git

/**
 * @author Romain Bioteau
 */
public class ValidatorFactory {

    public static MultiValidatorFactory multiValidator() {
        return new MultiValidatorFactory(new MultiValidator());
    }

    public static IValidator maxLengthValidator(final String inputName, final int maxLength) {
        return new InputLengthValidator(inputName, maxLength);
    }

    public static IValidator minMaxLengthValidator(final String inputName, final int minLength, final int maxLength) {
        return new InputLengthValidator(inputName, minLength, maxLength);
    }

    public static IValidator mandatoryValidator(final String inputName) {
        return new EmptyInputValidator(inputName);
    }

    public static IValidator urlEncodableInputValidator(final String inputName) {
        return new URLEncodableInputValidator(inputName);
    }

    public static IValidator utf8InputValidator(final String inputName) {
        return new UTF8InputValidator(inputName);
    }

    public static IValidator groovyReferenceValidator(final String inputName, final boolean checkEmptyField, final boolean checkLowerCaseForFirstChar) {
        return new GroovyReferenceValidator(inputName, checkEmptyField, checkLowerCaseForFirstChar);
    }

    public static UniqueValidatorFactory uniqueValidator() {
        return new UniqueValidatorFactory(new UniqueValidator());
    }

}
