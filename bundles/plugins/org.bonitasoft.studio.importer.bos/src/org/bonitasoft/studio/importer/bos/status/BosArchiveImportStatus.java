/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.importer.bos.status;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;


/**
 * @author Romain Bioteau
 *
 */
public class BosArchiveImportStatus extends MultiStatus {

    public BosArchiveImportStatus() {
        super(CommonRepositoryPlugin.PLUGIN_ID, 0, null, null);
    }

    public List<AbstractProcess> getProcessesWithErrors() {
        final List<AbstractProcess> processes = new ArrayList<AbstractProcess>();
        for (final IStatus child : getChildren()) {
            if (child instanceof ProcessValidationStatus && !child.isOK()) {
                processes.add(((ProcessValidationStatus) child).getProcess());
            }
        }
        return processes;
    }

}
