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
package org.bonitasoft.studio.application.operation;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public class DeployProjectOperation implements IRunnableWithStatus {

    private IStatus status;
    private RepositoryAccessor repositoryAccessor;
    private IPath archivePath;

    public DeployProjectOperation(RepositoryAccessor repositoryAccessor, IPath archivePath) {
        this.repositoryAccessor = repositoryAccessor;
        this.archivePath = archivePath;
        status = ValidationStatus.ok();
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        deployProject();
    }

    private void deployProject() {
        BonitaStudioLog.info("Deploy not implemented yet ... ZzzZ ...", ApplicationPlugin.PLUGIN_ID);
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
