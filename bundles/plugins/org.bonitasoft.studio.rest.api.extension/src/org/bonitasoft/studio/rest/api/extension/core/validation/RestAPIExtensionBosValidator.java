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
package org.bonitasoft.studio.rest.api.extension.core.validation;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.filter;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.status.ImportBosArchiveStatusBuilder;
import org.bonitasoft.studio.importer.bos.validator.BosImporterStatusProvider;
import org.bonitasoft.studio.importer.bos.validator.ValidationException;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public class RestAPIExtensionBosValidator implements BosImporterStatusProvider {

    @Override
    public ImportBosArchiveStatusBuilder buildStatus(ImportBosArchiveOperation operation,
            ImportBosArchiveStatusBuilder statusBuilder, IProgressMonitor monitor)
            throws ValidationException {
        for (IRepositoryFileStore fs : filter(operation.getImportedFileStores(),
                instanceOf(RestAPIExtensionFileStore.class))) {
            if (!monitor.isCanceled()) {
                RestAPIExtensionFileStore restApiFileStore = (RestAPIExtensionFileStore) fs;
                monitor.beginTask(String.format(Messages.validatingRestAPIExtension, restApiFileStore.getDisplayName()),
                        IProgressMonitor.UNKNOWN);
                try {
                    for (IStatus status : new RestAPIExtensionPomValidator().validate(restApiFileStore)) {
                        statusBuilder.addStatus(status);
                    }
                } catch (CoreException e) {
                    throw new ValidationException(e, "Failed to read pom file.");
                }
            }
        }
        return statusBuilder;
    }

}
