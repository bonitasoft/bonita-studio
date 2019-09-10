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

import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.application.handler.DeployArtifactsHandler;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.core.DependencyResolver;
import org.bonitasoft.studio.la.application.core.DeployApplicationAction;
import org.bonitasoft.studio.la.application.core.DeployApplicationDescriptorOperation;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class DeployApplicationHandler {

    public static final String APPLICATION_TO_DEPLOY_PARAMETER = "application";
    public static final String DEPLOY_WIZARD_PARAMETER = "useWizard";

    @Execute
    public IStatus execute(RepositoryAccessor repositoryAccessor, 
            @Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
            @org.eclipse.e4.core.di.annotations.Optional @Named(APPLICATION_TO_DEPLOY_PARAMETER) String application, 
            @org.eclipse.e4.core.di.annotations.Optional @Named(DEPLOY_WIZARD_PARAMETER) String useWizard,
            DependencyResolver<ApplicationFileStore> dependencyResolver) {
       return useWizard == null || Boolean.valueOf(useWizard) ? 
               openDeployWizard(repositoryAccessor, shell, new DeployApplicationAction(repositoryAccessor), application, dependencyResolver) : 
               deploy(repositoryAccessor, application);
    }
    
    protected IStatus deploy(RepositoryAccessor repositoryAccessor, String application) {
        Optional<ApplicationFileStore> applicationFileStore = findApplicationToDeploy(repositoryAccessor,
                application);
        if (applicationFileStore.isPresent()) {
            APISession apiSession = null;
            try {
                apiSession = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
                DeployApplicationDescriptorOperation deployAppDescriptorOperation = getDeployAppDescriptorOperation(apiSession, repositoryAccessor, applicationFileStore);
                deployAppDescriptorOperation.run(Repository.NULL_PROGRESS_MONITOR);
                return deployAppDescriptorOperation.getStatus();
            } catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException
                    | ReadFileStoreException | InvocationTargetException | InterruptedException | LoginException e) {
               BonitaStudioLog.error(e);
               return new Status(IStatus.ERROR, LivingApplicationPlugin.PLUGIN_ID, e.getMessage(),e);
            }finally {
                if(apiSession != null) {
                    BOSEngineManager.getInstance().logoutDefaultTenant(apiSession);
                }
            }
        } 
        return new Status(IStatus.ERROR, LivingApplicationPlugin.PLUGIN_ID, String.format("Application '%s' not found.",application));
    }

    protected DeployApplicationDescriptorOperation getDeployAppDescriptorOperation(APISession apiSession, RepositoryAccessor repositoryAccessor,
            Optional<ApplicationFileStore> applicationFileStore)
            throws BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException, ReadFileStoreException {
       return new DeployApplicationDescriptorOperation(BOSEngineManager.getInstance().getApplicationAPI(apiSession),
                applicationFileStore.get().getContent(),
                repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class).getConverter());
    }

    protected IStatus openDeployWizard(RepositoryAccessor repositoryAccessor, Shell shell, DeployApplicationAction action,
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
        return ValidationStatus.ok();
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
