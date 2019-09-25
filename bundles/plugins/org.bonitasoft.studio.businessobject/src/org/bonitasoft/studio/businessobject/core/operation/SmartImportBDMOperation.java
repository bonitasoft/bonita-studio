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
package org.bonitasoft.studio.businessobject.core.operation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.xml.sax.SAXException;

public class SmartImportBDMOperation implements IRunnableWithStatus {

    private BusinessObjectModelFileStore fileStore;
    private File fileToImport;
    private IStatus status = ValidationStatus.ok();

    public SmartImportBDMOperation(BusinessObjectModelFileStore fileStore, File fileToImport) {
        this.fileStore = fileStore;
        this.fileToImport = fileToImport;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        try {
            monitor.beginTask(Messages.importingBdm, IProgressMonitor.UNKNOWN);
            BusinessObjectModel modelToImport = ((BusinessObjectModelRepositoryStore) fileStore.getParentStore())
                    .getConverter()
                    .unmarshall(FileUtils.readFileToByteArray(fileToImport));
            BusinessObjectModel currentModel = fileStore.getContent();
            performImport(currentModel, modelToImport);
            fileStore.save(currentModel);
            monitor.done();
        } catch (JAXBException | IOException | SAXException e) {
            status = ValidationStatus.error(Messages.archiveContentInvalid, e);
        }
    }

    protected void performImport(BusinessObjectModel currentModel, BusinessObjectModel modelToImport) {
        List<BusinessObject> businessObjectsToAdd = modelToImport.getBusinessObjects().stream()
                .filter(boToAdd -> currentModel.getBusinessObjects().stream()
                        .noneMatch(existingBo -> Objects.equals(boToAdd, existingBo)))
                .collect(Collectors.toList());
        currentModel.getBusinessObjects().addAll(businessObjectsToAdd);
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
