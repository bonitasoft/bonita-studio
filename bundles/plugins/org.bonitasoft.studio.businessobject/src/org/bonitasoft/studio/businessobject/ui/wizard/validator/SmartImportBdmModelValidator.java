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
package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import java.util.Objects;

import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.model.SmartImportBdmModel;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class SmartImportBdmModelValidator implements IValidator<SmartImportBdmModel> {

    @Override
    public IStatus validate(SmartImportBdmModel model) {
        if (model == null || model.getSmartImportableUnits().isEmpty()) {
            return ValidationStatus.error(Messages.nothingToImport);
        }
        if (Objects.equals(model.getConflictStatus(), ConflictStatus.SAME_CONTENT)) {
            return ValidationStatus.error(Messages.globalSkipped);
        }
        return ValidationStatus.ok();
    }

}
