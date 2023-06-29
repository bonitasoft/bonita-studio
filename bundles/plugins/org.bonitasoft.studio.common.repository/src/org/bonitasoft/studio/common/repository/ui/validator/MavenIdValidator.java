/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.ui.validator;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.databinding.validator.RegExpValidator;
import org.bonitasoft.studio.common.repository.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class MavenIdValidator implements IValidator<String> {

    private IValidator<String> emptyFieldValidator;
    private boolean mandatory;
    private RegExpValidator regExpValidator;
    private Set<String> reserved = new HashSet<>();

    public MavenIdValidator(String fieldName) {
        this(fieldName, true);
    }
    
    public void addReservedId(String id) {
        reserved.add(id.toLowerCase());
    }

    public MavenIdValidator(String fieldName, boolean mandatory) {
        this.emptyFieldValidator = new EmptyInputValidator(fieldName);
        this.regExpValidator = new RegExpValidator(Messages.invalidFormat, "[A-Za-z0-9_\\-.]+");
        this.mandatory = mandatory;
    }

    @Override
    public IStatus validate(String value) {
        if (mandatory) {
            var status = emptyFieldValidator.validate(value);
            if (status.isOK()) {
                if(reserved.contains(value.toLowerCase())) {
                    return ValidationStatus.error(Messages.reservedId);
                }
                return regExpValidator.validate(value);
            }
        } else if (Strings.isNullOrEmpty(value)) {
            return ValidationStatus.ok();
        }
        return regExpValidator.validate(value);
    }

}
