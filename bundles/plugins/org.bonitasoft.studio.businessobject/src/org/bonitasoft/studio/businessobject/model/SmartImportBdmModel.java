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
import org.bonitasoft.studio.businessobject.core.operation.SmartImportBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
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

    public List<BusinessObject> getBusinessObjectsToImport() {
        return getSmartImportableUnits().stream()
                .map(SmartImportPackageModel.class::cast)
                .filter(packageUnit -> !Objects.equals(ConflictStatus.SAME_CONTENT, packageUnit.getConflictStatus()))
                .filter(packageUnit -> Objects.equals(ImportAction.OVERWRITE, packageUnit.getImportAction()))
                .map(SmartImportPackageModel::getBusinessObjectsToImport)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    protected BusinessObjectModel retrieveCurrentModel() {
        if (current != null) {
            BusinessObjectModelFileStore currentFileStore = current.getAdapter(BusinessObjectModelFileStore.class);
            if (currentFileStore != null) {
                return currentFileStore.getContent();
            }
        }
        return null;
    }

    private void createPackageModels(BusinessObjectModel currentModel, BusinessObjectModel modelToMerge) {
        PackageHelper packageHelper = PackageHelper.getInstance();
        packageHelper.getAllPackages(modelToMerge).stream()
                .map(packageName -> new SmartImportPackageModel(this, packageName))
                .peek(packageModel -> buildImportPackageModel(currentModel, modelToMerge, packageHelper, packageModel))
                .forEach(getSmartImportableUnits()::add);
        if (currentModel != null && includeCurrentModel) {
            packageHelper.getAllPackages(currentModel).forEach(packageName -> {
                Optional<SmartImportableUnit> packageAlreadyCreated = getSmartImportableUnits().stream()
                        .filter(unit -> Objects.equals(unit.getName(), packageName)).findFirst();
                List<BusinessObject> businessObjects = packageHelper.getAllBusinessObjects(currentModel, packageName);
                if (packageAlreadyCreated.isPresent()) {
                    completePackageWithExistingBo(businessObjects, (SmartImportPackageModel) packageAlreadyCreated.get());
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

    private void completePackageWithExistingBo(List<BusinessObject> businessObjects, SmartImportPackageModel packageModel) {
        if (packageModel.getConflictStatus() == ConflictStatus.NONE) {
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
            BusinessObjectModel modelToMerge, PackageHelper packageHelper, SmartImportPackageModel importPackageModel) {
        String packageName = importPackageModel.getName();
        List<BusinessObject> potentialConflictingBusinessObjects = retrievePotentialConflictingBusinessObjects(currentModel,
                packageHelper,
                packageName);
        List<BusinessObject> newBusinessObjects = packageHelper.getAllBusinessObjects(modelToMerge, packageName);
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
            buildImportPackageModel(currentModel, modelToMerge, PackageHelper.getInstance(), packageModel);
        } else {
            buildImportPackageModel(modelToMerge, currentModel, PackageHelper.getInstance(), packageModel);
        }
    }

    protected List<BusinessObject> retrievePotentialConflictingBusinessObjects(BusinessObjectModel currentModel,
            PackageHelper packageHelper,
            String packageName) {
        List<BusinessObject> currentBusinessObjects = currentModel != null
                ? packageHelper.getAllBusinessObjects(currentModel, packageName)
                : Collections.emptyList();
        return currentBusinessObjects;
    }

    protected SmartImportBusinessObjectModel createImportBusinessObjectModel(SmartImportPackageModel parent,
            List<BusinessObject> currentBusinessObjects,
            BusinessObject newBusinessObject) {
        SmartImportBusinessObjectModel importBusinessObjectModel = new SmartImportBusinessObjectModel(this, parent,
                newBusinessObject);
        importBusinessObjectModel.setConflictStatus(computeConflictStatus(currentBusinessObjects, newBusinessObject));
        return importBusinessObjectModel;
    }

    protected ConflictStatus computeConflictStatus(List<BusinessObject> currentBusinessObjects,
            BusinessObject newBusinessObject) {
        boolean conflicting = currentBusinessObjects.stream().anyMatch(existingBusinessObject -> {
            if (Objects.equals(existingBusinessObject.getSimpleName(), newBusinessObject.getSimpleName())) {
                return !Objects.equals(existingBusinessObject, newBusinessObject);
            }
            return false;
        });
        if (conflicting) {
            return ConflictStatus.CONFLICTING;
        }
        return currentBusinessObjects.stream()
                .anyMatch(existingBusinessObject -> Objects.equals(existingBusinessObject, newBusinessObject))
                        ? ConflictStatus.SAME_CONTENT
                        : ConflictStatus.NONE;
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

}
