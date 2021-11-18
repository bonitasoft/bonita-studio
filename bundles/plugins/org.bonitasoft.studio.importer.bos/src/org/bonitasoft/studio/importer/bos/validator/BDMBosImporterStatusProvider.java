/**
 * Copyright (C) 2018 Bonitasoft S.A.
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

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.status.ImportBosArchiveStatusBuilder;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;

public class BDMBosImporterStatusProvider implements BosImporterStatusProvider {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.bos.validator.BosImporterStatusProvider#buildStatus(org.bonitasoft.studio.importer.bos.operation.
     * ImportBosArchiveOperation, org.bonitasoft.studio.importer.bos.status.ImportBosArchiveStatusBuilder, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public ImportBosArchiveStatusBuilder buildStatus(ImportBosArchiveOperation operation,
            ImportBosArchiveStatusBuilder statusBuilder, IProgressMonitor monitor) throws ValidationException {
        operation.getImportedFileStores()
                .stream()
                .filter(BusinessObjectModelFileStore.class::isInstance)
                .findFirst()
                .map(fs -> ValidationStatus.info(Messages.bdmImportedInfo))
                .ifPresent(statusBuilder::addStatus);
        return statusBuilder;
    }

}
