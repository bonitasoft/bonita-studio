/**
 * Copyright (C) 2015 BonitaSoft S.A.
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

/**
 * @author Romain Bioteau
 */
public class GroovyReferenceValidatorFactory implements IValidatorFactory {

    public static GroovyReferenceValidatorFactory groovyReferenceValidator(final String fieldName) {
        return new GroovyReferenceValidatorFactory(new GroovyReferenceValidator(fieldName));
    }

    private final GroovyReferenceValidator groovyReferenceValidator;

    GroovyReferenceValidatorFactory(final GroovyReferenceValidator groovyReferenceValidator) {
        this.groovyReferenceValidator = groovyReferenceValidator;
    }

    public GroovyReferenceValidatorFactory allowEmptyValue() {
        groovyReferenceValidator.setCheckEmptyField(false);
        return this;
    }

    public GroovyReferenceValidatorFactory startsWithLowerCase() {
        groovyReferenceValidator.setForceLowerCaseFirst(true);
        return this;
    }

    public GroovyReferenceValidatorFactory startsWithAnyCase() {
        groovyReferenceValidator.setForceLowerCaseFirst(false);
        return this;
    }

    @Override
    public GroovyReferenceValidator create() {
        return groovyReferenceValidator;
    }
}
