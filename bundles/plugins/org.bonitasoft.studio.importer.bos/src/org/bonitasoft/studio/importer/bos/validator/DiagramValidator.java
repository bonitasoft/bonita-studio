/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.importer.bos.validator;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.status.ImportBosArchiveStatusBuilder;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.validation.common.operation.BatchValidationOperation;
import org.bonitasoft.studio.validation.common.operation.BatchValidatorFactory;
import org.bonitasoft.studio.validation.common.operation.OffscreenEditPartFactory;
import org.bonitasoft.studio.validation.common.operation.RunProcessesValidationOperation;
import org.bonitasoft.studio.validation.common.operation.ValidationMarkerProvider;
import org.eclipse.core.runtime.IProgressMonitor;

public class DiagramValidator implements BosImporterStatusProvider {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.bos.validator.BosImporterValidator#validate(org.bonitasoft.studio.importer.bos.operation.IResourceImporter)
     */
    @Override
    public ImportBosArchiveStatusBuilder buildStatus(ImportBosArchiveOperation operation,
            ImportBosArchiveStatusBuilder statusBuilder, IProgressMonitor monitor)
            throws ValidationException {
        for (final IRepositoryFileStore diagramFileStore : operation.getImportedProcesses()) {
            if(monitor.isCanceled()) {
                break;
            }
            try {
                final AbstractProcess process = (AbstractProcess) diagramFileStore.getContent();
                final RunProcessesValidationOperation validationAction = new RunProcessesValidationOperation(
                        new BatchValidationOperation(
                                new OffscreenEditPartFactory(
                                        org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory.getInstance()),
                                new ValidationMarkerProvider(),
                                new BatchValidatorFactory().create()));
                validationAction.addProcess(process);
                validationAction.run(monitor);
                if (validationAction.getStatus() != null && !validationAction.getStatus().isOK()) {
                    statusBuilder.addStatus(process, validationAction.getStatus());
                }
            } catch (final ReadFileStoreException | InvocationTargetException | InterruptedException e) {
                throw new ValidationException(e, "Failed to validate diagram content");
            }
        }
        return statusBuilder;
    }
}
