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
package org.bonitasoft.studio.businessobject.model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.operation.SmartImportBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.model.smartImport.ISmartImportable;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableModel;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Display;
import org.xml.sax.SAXException;

public class SmartImportBdmModel extends SmartImportableModel {

    protected BusinessObjectModelConverter converter;
    private boolean includeCurrentModel;
    private BusinessObjectModel modelToMerge;

    public SmartImportBdmModel(ISmartImportable current, BusinessObjectModelConverter converter,
            IValidator<File> validator) {
        this(current, converter, validator, true);
    }

    public SmartImportBdmModel(ISmartImportable current, BusinessObjectModelConverter converter,
            IValidator<File> validator, boolean includeCurrentModel) {
        super(current, validator);
        this.converter = converter;
        this.includeCurrentModel = includeCurrentModel;
    }

    @Override
    public void buildSmartImportModel(File fileToImport) {
        try {
            modelToMerge = converter.unmarshall(FileUtils.readFileToByteArray(fileToImport));
            createPackageModels(retrieveCurrentModel(), modelToMerge);
        } catch (JAXBException | IOException | SAXException e) {
            new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
        }
    }

    public List<BusinessObject> getBusinessObjectsToImport(List<String> overwrittenPackages) {
        return getSmartImportableUnits().stream()
                .map(SmartImportPackageModel.class::cast)
                .filter(packageUnit -> !Objects.equals(ConflictStatus.SAME_CONTENT, packageUnit.getConflictStatus()))
                .filter(packageUnit -> Objects.equals(ImportAction.OVERWRITE, packageUnit.getImportAction()))
                .map(packageModel -> packageModel
                        .getBusinessObjectsToImport(overwrittenPackages.contains(packageModel.getName())))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    protected BusinessObjectModel retrieveCurrentModel() {
        if (current != null) {
            BusinessObjectModelFileStore currentFileStore = current.getAdapter(BusinessObjectModelFileStore.class);
            if (currentFileStore != null) {
                try {
                    return currentFileStore.getContent();
                } catch (ReadFileStoreException e) {
                    BonitaStudioLog.warning(e.getMessage(), BusinessObjectPlugin.PLUGIN_ID);
                  return null;
                }
            }
        }
        return null;
    }

    private void createPackageModels(BusinessObjectModel currentModel, BusinessObjectModel modelToMerge) {
        PackageHelper.getAllPackages(modelToMerge).stream()
                .map(packageName -> new SmartImportPackageModel(this, packageName))
                .peek(packageModel -> buildImportPackageModel(currentModel, modelToMerge, packageModel))
                .forEach(getSmartImportableUnits()::add);
        if (currentModel != null && includeCurrentModel) {
            PackageHelper.getAllPackages(currentModel).forEach(packageName -> {
                Optional<SmartImportableUnit> packageAlreadyCreated = getSmartImportableUnits().stream()
                        .filter(unit -> Objects.equals(unit.getName(), packageName)).findFirst();
                List<BusinessObject> businessObjects = PackageHelper.getAllBusinessObjects(currentModel, packageName);
                if (packageAlreadyCreated.isPresent()) {
                    completePackageWithExistingBo(businessObjects,
                            (SmartImportPackageModel) packageAlreadyCreated.get());
                } else {
                    createExistingPackage(businessObjects, packageName);
                }
            });
        }
    }

    private void createExistingPackage(List<BusinessObject> businessObjects, String packageName) {
        SmartImportPackageModel importPackageModel = new SmartImportPackageModel(this, packageName);
        completePackageWithExistingBo(businessObjects, importPackageModel);
        getSmartImportableUnits().add(importPackageModel);
    }

    private void completePackageWithExistingBo(List<BusinessObject> businessObjects,
            SmartImportPackageModel packageModel) {
        if (packageModel.getConflictStatus() != ConflictStatus.CONFLICTING) {
            businessObjects.stream()
                    .filter(bo -> packageModel.getSmartImportableUnits().stream()
                            .map(SmartImportableUnit::getName)
                            .noneMatch(bo.getSimpleName()::equals))
                    .map(bo -> new SmartImportBusinessObjectModel(this, packageModel, bo))
                    .peek(boModel -> boModel.setConflictStatus(ConflictStatus.SAME_CONTENT))
                    .forEach(packageModel.getSmartImportableUnits()::add);
        }
    }

    protected void buildImportPackageModel(BusinessObjectModel currentModel,
            BusinessObjectModel modelToMerge, SmartImportPackageModel importPackageModel) {
        String packageName = importPackageModel.getName();
        List<BusinessObject> potentialConflictingBusinessObjects = retrievePotentialConflictingBusinessObjects(
                currentModel,
                packageName);
        List<BusinessObject> newBusinessObjects = PackageHelper.getAllBusinessObjects(modelToMerge, packageName);
        newBusinessObjects.stream()
                .map(newBusinessObject -> createImportBusinessObjectModel(importPackageModel,
                        potentialConflictingBusinessObjects,
                        newBusinessObject))
                .forEach(importPackageModel.getSmartImportableUnits()::add);
    }

    /**
     * Depending on the ImportAction, the SmartImporPackageModel in input will contain the objects from the current model or
     * from the imported one.
     */
    public void updateSmartImportPackageModel(SmartImportPackageModel packageModel) {
        BusinessObjectModel currentModel = retrieveCurrentModel();
        packageModel.getSmartImportableUnits().clear();
        if (Objects.equals(packageModel.getImportAction(), ImportAction.OVERWRITE)) {
            buildImportPackageModel(currentModel, modelToMerge, packageModel);
        } else {
            buildImportPackageModel(modelToMerge, currentModel, packageModel);
            packageModel.getSmartImportableUnits().stream()
                    .filter(objectModel -> Objects.equals(objectModel.getConflictStatus(), ConflictStatus.NONE))
                    .forEach(objectModel -> objectModel.setConflictStatus(ConflictStatus.SAME_CONTENT));
        }
    }

    protected List<BusinessObject> retrievePotentialConflictingBusinessObjects(BusinessObjectModel currentModel,
            String packageName) {
        List<BusinessObject> currentBusinessObjects = currentModel != null
                ? PackageHelper.getAllBusinessObjects(currentModel, packageName)
                : Collections.emptyList();
        return currentBusinessObjects;
    }

    protected SmartImportBusinessObjectModel createImportBusinessObjectModel(SmartImportPackageModel parent,
            List<BusinessObject> currentBusinessObjects,
            BusinessObject newBusinessObject) {
        SmartImportBusinessObjectModel importBusinessObjectModel = new SmartImportBusinessObjectModel(this, parent,
                newBusinessObject);
        ConflictResult conflictResult = computeConflictStatus(currentBusinessObjects, newBusinessObject);
        importBusinessObjectModel.setConflictStatus(conflictResult.getStatus());
        importBusinessObjectModel.setConflictingObjectName(conflictResult.getConflictingObjectName());
        return importBusinessObjectModel;
    }

    protected ConflictResult computeConflictStatus(List<BusinessObject> currentBusinessObjects,
            BusinessObject newBusinessObject) {
        Optional<String> conflictingObjectName = currentBusinessObjects.stream().map(existingBusinessObject -> {
            if (Objects.equals(existingBusinessObject.getSimpleName(), newBusinessObject.getSimpleName())) {
                if (!Objects.equals(existingBusinessObject, newBusinessObject)) {
                    return existingBusinessObject.getQualifiedName();
                }
            }
            return null;
        })
                .filter(Objects::nonNull)
                .findFirst();
        if (conflictingObjectName.isPresent()) {
            return new ConflictResult(ConflictStatus.CONFLICTING, conflictingObjectName.get());
        }
        return currentBusinessObjects.stream()
                .anyMatch(existingBusinessObject -> Objects.equals(existingBusinessObject, newBusinessObject))
                        ? new ConflictResult(ConflictStatus.SAME_CONTENT)
                        : new ConflictResult(ConflictStatus.NONE);
    }

    @Override
    public IStatus performSmartImport(IProgressMonitor monitor) {
        try {
            SmartImportBDMOperation operation = new SmartImportBDMOperation(
                    current.getAdapter(BusinessObjectModelFileStore.class), this);
            operation.run(monitor);
            return operation.getStatus();
        } catch (InvocationTargetException | InterruptedException e) {
            return ValidationStatus.error(e.getMessage(), e.getCause());
        }
    }

    class ConflictResult {

        private ConflictStatus status;
        private String conflictingObjectName;

        ConflictResult(ConflictStatus status, String conflictingObjectName) {
            this.status = status;
            this.conflictingObjectName = conflictingObjectName;
        }

        ConflictResult(ConflictStatus status) {
            this.status = status;
        }

        public ConflictStatus getStatus() {
            return status;
        }

        public String getConflictingObjectName() {
            return conflictingObjectName;
        }
    }

}
