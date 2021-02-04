/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bos.status;

import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.ui.util.ProcessValidationStatus;
import org.eclipse.core.runtime.IStatus;

/**
 * @author Romain Bioteau
 */
public class ImportBosArchiveStatusBuilder {

    private final BosArchiveImportStatus status;

    public ImportBosArchiveStatusBuilder() {
        status = new BosArchiveImportStatus();
    }

    public ImportBosArchiveStatusBuilder addStatus(final AbstractProcess process, final IStatus status) {
        this.status.add(new ProcessValidationStatus(process, status));
        return this;
    }

    public BosArchiveImportStatus done() {
        return new BonitaStatusCodeAggregator().aggregateStatus(status);
    }

    public ImportBosArchiveStatusBuilder addStatus(final IStatus status) {
        this.status.add(status);
        return this;
    }

}
