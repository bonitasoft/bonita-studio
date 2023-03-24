/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.validator;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ProfileValidator implements IValidator<String> {

    @Override
    public IStatus validate(String value) {
        if (value == null || value.isEmpty()) {
            return ValidationStatus.warning(Messages.emptyProfile);
        }
        return validateProfile(value);
    }

    protected IStatus validateProfile(String value) {
        List<String> knownprofiles = getKnownprofiles();
        return knownprofiles.contains(value)
                ? ValidationStatus.ok()
                : ValidationStatus.error(String.format(Messages.unknownProfile, value));
    }

    protected List<String> getKnownprofiles() {
        return Arrays.asList(ApplicationFormPage.DEFAULT_USER_ID, ApplicationFormPage.DEFAULT_ADMINISTRATOR_ID);
    }

}
