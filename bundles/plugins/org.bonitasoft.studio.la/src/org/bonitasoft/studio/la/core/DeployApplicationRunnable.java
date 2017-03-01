/**
 * Copyright (C) 2017 BonitaSoft S.A.
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
package org.bonitasoft.studio.la.core;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.ApplicationImportPolicy;
import org.bonitasoft.engine.exception.AlreadyExistsException;
import org.bonitasoft.engine.exception.ImportException;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class DeployApplicationRunnable implements IRunnableWithProgress {

    private final ApplicationAPI applicationAPI;
    private final ApplicationFileStore appFileStore;
    private IStatus status = Status.OK_STATUS;

    public DeployApplicationRunnable(ApplicationAPI applicationAPI, ApplicationFileStore appFileStore) {
        this.applicationAPI = requireNonNull(applicationAPI);
        this.appFileStore = requireNonNull(appFileStore);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.deployingLivingApplication, IProgressMonitor.UNKNOWN);
        status = deleteBeforeDeploy(monitor);
        if (status.isOK()) {
            try {
                applicationAPI.importApplications(appFileStore.toByteArray(), ApplicationImportPolicy.FAIL_ON_DUPLICATES);
            } catch (AlreadyExistsException | ImportException | IOException e) {
                status = new Status(IStatus.ERROR, LivingApplicationPlugin.PLUGIN_ID,
                        String.format("Failed to import '%s' application descriptor.", appFileStore.getDisplayName()), e);
            } finally {
                monitor.done();
            }
        }
    }

    protected IStatus deleteBeforeDeploy(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        final DeleteApplicationRunnable deleteApplicationRunnable = new DeleteApplicationRunnable(applicationAPI,
                appFileStore).ignoreErrors();
        deleteApplicationRunnable.run(monitor);
        return deleteApplicationRunnable.getStatus();
    }

    public IStatus getStatus() {
        return status;
    }

}
