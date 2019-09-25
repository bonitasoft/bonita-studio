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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.repository.model.ISmartImportableValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.xml.sax.SAXException;

public class SmartImportBdmValidator implements ISmartImportableValidator {

    private BusinessObjectModel currentModel;
    private BusinessObjectModelConverter converter;

    public SmartImportBdmValidator(BusinessObjectModelFileStore fileStore) {
        this.currentModel = fileStore.getContent();
        converter = ((BusinessObjectModelRepositoryStore) fileStore.getParentStore()).getConverter();
    }

    // For test purpose
    protected SmartImportBdmValidator() {
    }

    @Override
    public IStatus validate(File value) {
        try {
            BusinessObjectModel modelToImport = converter.unmarshall(FileUtils.readFileToByteArray(value));
            return validateCompatibility(currentModel, modelToImport);
        } catch (JAXBException | IOException | SAXException e) {
            return ValidationStatus.error(Messages.archiveContentInvalid, e);
        }
    }

    protected IStatus validateCompatibility(BusinessObjectModel model1, BusinessObjectModel model2) {
        List<BusinessObject> duplicatedBo = model1.getBusinessObjects().stream()
                .filter(boFromModel1 -> {
                    return model2.getBusinessObjects().stream()
                            .anyMatch(boFromModel2 -> areBusinessObjectsConflicting(boFromModel1, boFromModel2));
                }).collect(Collectors.toList());
        if (!duplicatedBo.isEmpty()) {
            MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, Messages.smartImportImpossible, null);
            duplicatedBo.stream()
                    .map(BusinessObject::getSimpleName)
                    .map(name -> String.format(Messages.businessObjectNameDuplicated, name))
                    .map(ValidationStatus::error)
                    .forEach(status::add);
            return status;
        }
        return ValidationStatus.ok();
    }

    /**
     * @return true if business objects are conflicting.
     *         Business objects are conflicting if they have the same `simpleName` and if the `BusinessObject` equals method
     *         doesn't return true.
     */
    private boolean areBusinessObjectsConflicting(BusinessObject bo1, BusinessObject bo2) {
        if (Objects.equals(bo1.getSimpleName(), bo2.getSimpleName())) {
            return !Objects.equals(bo1, bo2);
        }
        return false;
    }

}
