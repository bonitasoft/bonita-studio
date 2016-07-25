/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.jface.databinding.validator;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ReservedRESTAPIKeywordsValidator implements IValidator {

    private static final String[] RESERVED_KEYWORDS = { "content", "API", "theme" };

    @Override
    public IStatus validate(final Object value) {
        if (value != null && value instanceof String) {
            for (final String reservedKeywords : RESERVED_KEYWORDS) {
                if (reservedKeywords.equalsIgnoreCase((String) value)) {
                    return ValidationStatus.error(Messages.validationFailureReservedKeywordsAPIREST);
                }
            }
        }
        return ValidationStatus.ok();
    }

}
