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
import org.bonitasoft.studio.model.process.AdditionalResource;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import com.google.common.base.Strings;

public class AdditionalResourceBarPathValidator implements IValidator<String> {

    private Pool pool;
    private AdditionalResource originalAdditionalResource;

    public AdditionalResourceBarPathValidator(Pool pool, AdditionalResource originalAdditionalResource) {
        this.pool = pool;
        this.originalAdditionalResource = originalAdditionalResource;
    }

    @Override
    public IStatus validate(String barPath) {
        if (Strings.isNullOrEmpty(barPath)) {
            return ValidationStatus.error(Messages.additionalresourceNameIsRequired);
        }
        return pool.getAdditionalResources().stream()
                .filter(additionalResource -> !Objects.equals(additionalResource, originalAdditionalResource))
                .filter(additionalResource -> Objects.equals(additionalResource.getName(), barPath))
                .count() > 0
                        ? ValidationStatus.error(String.format(Messages.barPathUnicityError, barPath))
                        : ValidationStatus.ok();
    }

}
