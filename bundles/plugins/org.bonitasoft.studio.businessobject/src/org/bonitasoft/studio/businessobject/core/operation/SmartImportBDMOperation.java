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

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.model.SmartImportBdmModel;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public class SmartImportBDMOperation implements IRunnableWithStatus {

    private BusinessObjectModelFileStore fileStore;
    private SmartImportBdmModel modelToImport;
    private IStatus status = ValidationStatus.ok();
    private PackageHelper packageHelper = PackageHelper.getInstance();

    public SmartImportBDMOperation(BusinessObjectModelFileStore fileStore, SmartImportBdmModel modelToImport) {
        this.fileStore = fileStore;
        this.modelToImport = modelToImport;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.importingBdm, IProgressMonitor.UNKNOWN);
        BusinessObjectModel currentModel = fileStore.getContent();
        performImport(currentModel);
        fileStore.save(currentModel);
        monitor.done();
    }

    protected void performImport(BusinessObjectModel currentModel) {
        List<String> packageToOverwrite = modelToImport.getSmartImportableUnits().stream()
                .filter(unit -> Objects.equals(ConflictStatus.CONFLICTING, unit.getConflictStatus()))
                .filter(unit -> Objects.equals(unit.getImportAction(), ImportAction.OVERWRITE))
                .map(SmartImportableUnit::getName)
                .collect(Collectors.toList());
        currentModel.getBusinessObjects()
                .removeIf(existingBo -> packageToOverwrite.contains(packageHelper.getPackageName(existingBo)));
        currentModel.getBusinessObjects().addAll(modelToImport.getBusinessObjectsToImport());
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
