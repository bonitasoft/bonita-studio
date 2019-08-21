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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.api.result.ExecutionResult;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.DeployerException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.ui.dialog.EngineStatusMapper;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

public class DeployProjectOperation implements IRunnableWithStatus {

    private MultiStatus status = new MultiStatus(ApplicationPlugin.PLUGIN_ID, -1, null, null);
    private IPath archivePath;
    private APISession session;

    public DeployProjectOperation(APISession session, IPath archivePath) {
        this.session = session;
        this.archivePath = archivePath;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.deployingApplicationArtifacts, IProgressMonitor.UNKNOWN);
        try {
            ApplicationAPI applicationAPI = BOSEngineManager.getInstance().getApplicationAPI(session);
            ExecutionResult result = applicationAPI
                    .deployApplication(Files.readAllBytes(archivePath.toFile().toPath()));
            result.getAllStatus().stream()
                    .map(new EngineStatusMapper())
                    .forEach(status::add);
            if(status.isOK() && status.getChildren().length == 0) {
                status.add(ValidationStatus.info("Application artifacts deployed successfully."));
            }
        } catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException | DeployerException
                | IOException e) {
            status.add(new Status(IStatus.ERROR, ApplicationPlugin.PLUGIN_ID, e.getMessage(), e));
        }finally {
            monitor.done();
        }
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
