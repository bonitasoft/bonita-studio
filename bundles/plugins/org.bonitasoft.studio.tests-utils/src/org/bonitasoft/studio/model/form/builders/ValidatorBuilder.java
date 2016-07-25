/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.form.builders;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Validator;


public class ValidatorBuilder implements Buildable<Validator> {

    private final Validator validator;

    public ValidatorBuilder(final Validator validator) {
        this.validator = validator;
    }

    public static ValidatorBuilder aValidator() {
        return new ValidatorBuilder(FormFactory.eINSTANCE.createValidator());
    }

    public ValidatorBuilder withParameter(final Expression expression) {
        validator.setParameter(expression);
        return this;
    }

    @Override
    public Validator build() {
        return validator;
    }

}
