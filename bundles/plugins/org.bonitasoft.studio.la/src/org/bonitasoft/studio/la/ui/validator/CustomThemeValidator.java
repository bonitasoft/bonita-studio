/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.ui.validator;

import org.bonitasoft.studio.la.ui.editor.theme.ThemeDescriptor;
import org.bonitasoft.studio.ui.validator.ValidatorBuilder;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class CustomThemeValidator implements IValidator {

    public static class Builder implements ValidatorBuilder<CustomThemeValidator> {

        private String message;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        @Override
        public CustomThemeValidator create() {
            return new CustomThemeValidator(message);
        }

    }

    private String message;

    public CustomThemeValidator(String message) {
        this.message = message;
    }

    @Override
    public IStatus validate(Object value) {
        return ThemeDescriptor.isDefaultTheme((String) value)
                ? ValidationStatus.ok()
                : ValidationStatus.warning(message);
    }

}
