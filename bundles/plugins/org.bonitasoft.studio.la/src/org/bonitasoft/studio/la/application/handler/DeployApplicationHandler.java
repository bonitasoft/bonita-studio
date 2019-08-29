/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import org.bonitasoft.studio.application.handler.DeployArtifactsHandler;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.la.application.core.DependencyResolver;
import org.bonitasoft.studio.la.application.core.DeployApplicationAction;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class DeployApplicationHandler {

    public static final String APPLICATION_TO_DEPLOY_PARAMETER = "application";

    @Execute
    public void execute(RepositoryAccessor repositoryAccessor, Shell shell,
            @org.eclipse.e4.core.di.annotations.Optional @Named(APPLICATION_TO_DEPLOY_PARAMETER) String application, 
            DependencyResolver<ApplicationFileStore> dependencyResolver) {
        deploy(repositoryAccessor, shell, new DeployApplicationAction(repositoryAccessor), application, dependencyResolver);
    }

    protected void deploy(RepositoryAccessor repositoryAccessor, Shell shell, DeployApplicationAction action,
            String application, DependencyResolver<ApplicationFileStore> dependencyResolver) {
        Optional<ApplicationFileStore> applicationFileStore = findApplicationToDeploy(repositoryAccessor,
                application);
        if (applicationFileStore.isPresent()) {
            DeployArtifactsHandler deployArtifactsHandler = new DeployArtifactsHandler();
            List<IRepositoryFileStore> defaultSelection = new ArrayList<>();
            ApplicationFileStore fileStore = applicationFileStore.get();
            defaultSelection.add(fileStore);
            defaultSelection.addAll(dependencyResolver.findDependencies(fileStore));
            deployArtifactsHandler.setDefaultSelection(defaultSelection);
            try {
                deployArtifactsHandler.deploy(shell, repositoryAccessor, PlatformUI.getWorkbench().getProgressService());
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        } else {
            action.selectAndDeploy(shell);
        }
    }

    protected Optional<ApplicationFileStore> findApplicationToDeploy(RepositoryAccessor repositoryAccessor,
            String application) {
        if(application != null) {
            return Optional.of(repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class)
                    .getChild(application, true)); 
        }
        return Optional.empty();
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().isLoaded()
                && !repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getChildren().isEmpty();
    }

}
