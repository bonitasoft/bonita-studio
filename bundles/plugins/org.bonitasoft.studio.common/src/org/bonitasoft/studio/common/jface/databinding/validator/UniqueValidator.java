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

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Iterables.find;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 */
public class UniqueValidator implements IValidator {

    private Iterable<?> iterable;
    private String propertyName;

    public void setIterable(final Iterable<?> iterable) {
        this.iterable = iterable;
    }

    public void setUniqueProperty(final String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public IStatus validate(final Object value) {
        checkState(iterable != null);
        if (value != null) {
            return find(iterable, hasSamePropertyValue(value), null) == null ? ValidationStatus.ok() :
                    ValidationStatus.error(Messages.bind(Messages.unicityErrorMessage, value));
        }
        return ValidationStatus.ok();
    }

    private Predicate<Object> hasSamePropertyValue(final Object value) {
        return new Predicate<Object>() {

            @Override
            public boolean apply(final Object input) {
                Object currentPropertyValue = input;
                if (propertyName != null) {
                    final IObservableValue observeValue = PojoObservables.observeValue(new SimpleRealm(), input, propertyName);
                    currentPropertyValue = observeValue.getValue();
                }
                return value.equals(currentPropertyValue);
            }
        };
    }

    private class SimpleRealm extends Realm {

        @Override
        public boolean isCurrent() {
            return true;
        }

    }
}
