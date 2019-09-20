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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class MergeBdmValidator implements IValidator<BusinessObjectModel> {

    private BusinessObjectModel currentModel;

    public MergeBdmValidator(BusinessObjectModel currentModel) {
        this.currentModel = currentModel;
    }

    @Override
    public IStatus validate(BusinessObjectModel value) {
        return validateCompatibility(currentModel, value);
    }

    protected IStatus validateCompatibility(BusinessObjectModel model1, BusinessObjectModel model2) {
        List<BusinessObject> duplicatedBo = model1.getBusinessObjects().stream()
                .filter(boFromModel1 -> {
                    return model2.getBusinessObjects().stream().anyMatch(boFromModel2 -> {
                        return Objects.equals(boFromModel1.getSimpleName(), boFromModel2.getSimpleName());
                    });
                }).collect(Collectors.toList());
        if (!duplicatedBo.isEmpty()) {
            MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);
            duplicatedBo.stream()
                    .map(BusinessObject::getSimpleName)
                    .map(name -> String.format(Messages.businessObjectNameDuplicated, name))
                    .map(ValidationStatus::error)
                    .forEach(status::add);
            return status;
        }
        return ValidationStatus.ok();
    }

}
