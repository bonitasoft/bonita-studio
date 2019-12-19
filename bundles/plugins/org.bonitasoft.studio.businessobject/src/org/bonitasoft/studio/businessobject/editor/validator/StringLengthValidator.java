/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.validator;

import java.util.Objects;

import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import com.google.common.base.Strings;

public class StringLengthValidator implements IValidator<String> {

    private IObservableValue<Field> selectedFieldObservable;

    public StringLengthValidator(IObservableValue<Field> selectedFieldObservable) {
        this.selectedFieldObservable = selectedFieldObservable;
    }

    @Override
    public IStatus validate(String length) {
        Field selectedField = selectedFieldObservable.getValue();

        if (selectedField != null && selectedField instanceof SimpleField
                && Objects.equals(((SimpleField) selectedField).getType(), FieldType.STRING)) {
            if (Strings.isNullOrEmpty(length)) {
                return ValidationStatus.error(Messages.lengthCannotBeEmpty);
            }
            try {
                if (Integer.parseInt(length) <= 0) {
                    return ValidationStatus.error(Messages.lengthIsNotAPositiveNumber);
                }
            } catch (final NumberFormatException e) {
                return ValidationStatus.error(Messages.lengthIsNotANumber);
            }
        }
        return ValidationStatus.ok();
    }

}
