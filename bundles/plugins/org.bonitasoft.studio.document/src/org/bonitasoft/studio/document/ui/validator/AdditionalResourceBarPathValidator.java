/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.document.ui.validator;

import java.util.Objects;

import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.Resource;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import com.google.common.base.Strings;

public class AdditionalResourceBarPathValidator implements IValidator<Resource> {

    private Configuration configuration;

    @Override
    public IStatus validate(Resource resource) {
        String barPath = resource.getBarPath();
        if (Strings.isNullOrEmpty(barPath)) {
            return ValidationStatus.error(org.bonitasoft.studio.ui.i18n.Messages.required);
        }
        if (configuration != null) {
            return configuration.getAdditionalResources().stream()
                    .filter(rsrc -> Objects.equals(rsrc.getBarPath(), barPath))
                    .count() > 1
                            ? ValidationStatus.error(Messages.barPathUnicityError)
                            : ValidationStatus.ok();
        }
        return ValidationStatus.ok();

    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

}
