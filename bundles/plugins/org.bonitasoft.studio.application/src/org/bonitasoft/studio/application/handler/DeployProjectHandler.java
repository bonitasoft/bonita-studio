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
package org.bonitasoft.studio.application.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.swt.widgets.Shell;

public class DeployProjectHandler extends DeployArtifactsHandler {

    @Override
    public void deploy(Shell activeShell, RepositoryAccessor repositoryAccessor)
            throws InvocationTargetException, InterruptedException {
        List<IRepositoryFileStore> artifactsToDeploy = retrieveArtifactsToDeploy(activeShell, repositoryAccessor);
        deployArtifacts(activeShell, repositoryAccessor, artifactsToDeploy);
    }

    @Override
    protected List<IRepositoryFileStore> retrieveArtifactsToDeploy(Shell activeShell,
            RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().getAllStores()
                .stream()
                .map(IRepositoryStore::getChildren)
                .flatMap(Collection::stream)
                .filter(IDeployable.class::isInstance)
                .collect(Collectors.toList());
    }

}
