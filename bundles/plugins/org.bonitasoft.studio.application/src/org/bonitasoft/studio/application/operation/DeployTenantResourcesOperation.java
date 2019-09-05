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
import java.util.Map;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.application.ui.control.model.TenantArtifact;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.ui.util.StatusCollectors;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class DeployTenantResourcesOperation implements IRunnableWithStatus {

    private Collection<TenantArtifact> artifactsToDeploy;
    private MultiStatus status;
    private APISession apiSession;
    private Map<String, Object> deployOptions = new HashMap<String, Object>();

    public DeployTenantResourcesOperation(Collection<TenantArtifact> artifactsToDeploy, APISession apiSession, Map<String, Object> deployOptions) {
        this.artifactsToDeploy = artifactsToDeploy;
        this.apiSession = apiSession;
        this.deployOptions = deployOptions;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        status = artifactsToDeploy.stream()
                .sorted()
                .map(artifact -> artifact.deploy(apiSession, deployOptions, monitor))
                .collect(StatusCollectors.toMultiStatus());
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
