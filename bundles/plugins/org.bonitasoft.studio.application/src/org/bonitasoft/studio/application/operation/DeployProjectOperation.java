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
import java.util.Collection;
import java.util.HashMap;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.FileStoreArtifact;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.ui.util.StatusCollectors;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class DeployProjectOperation implements IRunnableWithStatus {

    private IStatus status;
    private APISession session;
    private Collection<FileStoreArtifact> artifactsToDeploy;

    public DeployProjectOperation(APISession session, Collection<FileStoreArtifact> artifactsToDeploy) {
        this.session = session;
        this.artifactsToDeploy = artifactsToDeploy;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        status = artifactsToDeploy.stream()
                .peek(artifact -> monitor.setTaskName(String.format(Messages.deploying, artifact.getName())))
                .map(artifact -> !monitor.isCanceled()
                        ? artifact.deploy(session, new HashMap<>(), Repository.NULL_PROGRESS_MONITOR)
                        : Status.CANCEL_STATUS)
                .peek(artifact -> monitor.worked(1))
                .collect(StatusCollectors.toMultiStatus());
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
