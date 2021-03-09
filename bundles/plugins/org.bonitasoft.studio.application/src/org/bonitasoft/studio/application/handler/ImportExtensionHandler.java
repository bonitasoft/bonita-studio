/**
 * Copyright (C) 2021 Bonitasoft S.A.
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

import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.inject.Named;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.ImportExtensionPage;
import org.bonitasoft.studio.application.ui.control.ImportExtensionPage.ImportMode;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.DependencyGetOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup.Status;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.repository.IRepository;
import org.eclipse.swt.widgets.Shell;

public class ImportExtensionHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell,
            RepositoryAccessor repositoryAccessor,
            MavenRepositoryRegistry mavenRepositoryRegistry) {
        AbstractRepository currentRepository = repositoryAccessor.getCurrentRepository();
        ImportExtensionPage importExtensionPage = new ImportExtensionPage(mavenRepositoryRegistry);

        WizardBuilder.<Boolean> newWizard()
                .withTitle(Messages.importExtensionTitle)
                .needProgress()
                .havingPage(newPage()
                        .withTitle(Messages.importExtensionTitle)
                        .withDescription(Messages.importExtension)
                        .withControl(importExtensionPage))
                .onFinish(container -> performFinish(container,
                        importExtensionPage,
                        mavenRepositoryRegistry,
                        currentRepository.getLocalDependencyStore(),
                        currentRepository.getProject()))
                .withSize(700, 650)
                .withFixedInitialSize()
                .open(activeShell, org.bonitasoft.studio.ui.i18n.Messages.importLabel);
    }

    private Optional<Boolean> performFinish(IWizardContainer container,
            ImportExtensionPage importExtensionPage,
            MavenRepositoryRegistry mavenRepositoryRegistry,
            LocalDependenciesStore localDependenciesStore,
            IProject project) {
        if (importExtensionPage.getImportMode() == ImportMode.MANUAL) {
            return manualImport(container,
                    importExtensionPage.getDependency(),
                    mavenRepositoryRegistry);
        } else if (importExtensionPage.getDependencyLookup() != null) {
            return fileImport(container,
                    importExtensionPage.getDependency(),
                    importExtensionPage.getDependencyLookup(),
                    mavenRepositoryRegistry,
                    localDependenciesStore,
                    project);
        }
        return Optional.empty();
    }

    private Optional<Boolean> fileImport(IWizardContainer container,
            Dependency dependency,
            DependencyLookup dependencyLookup,
            MavenRepositoryRegistry mavenRepositoryRegistry,
            LocalDependenciesStore localDependenciesStore,
            IProject project) {
        if (dependencyLookup.getStatus() == Status.FOUND) {
            return manualImport(container, dependency, mavenRepositoryRegistry);
        } else {
            dependencyLookup.setArtifactId(dependency.getArtifactId());
            dependencyLookup.setGroupId(dependency.getGroupId());
            dependencyLookup.setVersion(dependency.getVersion());
            dependencyLookup.setType(dependency.getType());
            dependencyLookup.setClassifier(dependency.getClassifier());
            try {
                container.run(true, false, monitor -> {
                    monitor.beginTask(Messages.installingExtensions, IProgressMonitor.UNKNOWN);
                    try {
                        localDependenciesStore.install(dependencyLookup);
                    } catch (CoreException e) {
                        throw new InvocationTargetException(e);
                    }
                    addDependency(dependency, monitor);
                    try {
                        MavenPlugin.getProjectConfigurationManager().updateProjectConfiguration(project, monitor);
                    } catch (CoreException e) {
                        throw new InvocationTargetException(e);
                    }
                    monitor.done();
                });
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
                return Optional.empty();
            }
            return Optional.of(true);
        }
    }

    private Optional<Boolean> manualImport(IWizardContainer container,
            Dependency dependency,
            MavenRepositoryRegistry mavenRepositoryRegistry) {
        try {
            GAV gav = new GAV(dependency);
            DependencyGetOperation dependencyGetOperation = new DependencyGetOperation(
                    gav);
            container.run(true, false, mavenRepositoryRegistry.updateRegistry());
            mavenRepositoryRegistry
                    .getGlobalRepositories()
                    .stream()
                    .map(IRepository::getUrl)
                    .forEach(dependencyGetOperation::addRemoteRespository);
            container.run(true, false, dependencyGetOperation);
            DependencyLookup lookupResult = dependencyGetOperation.getResult();
            if (lookupResult != null) {
                container.run(true, false, monitor -> {
                    monitor.beginTask(Messages.installingExtensions, IProgressMonitor.UNKNOWN);
                    addDependency(dependency, monitor);
                    monitor.done();
                });
            } else {
                MessageDialog.openError(container.getShell(), Messages.addDependenciesError,
                        String.format(Messages.dependencyNotFoundWhenImporting, gav));
                return Optional.empty();
            }
            return Optional.of(true);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            MessageDialog.openError(container.getShell(), Messages.addDependenciesError, e.getMessage());
            return Optional.empty();
        }
    }

    private void addDependency(Dependency dep, IProgressMonitor monitor) throws InvocationTargetException {
        try {
            AddDependencyOperation operation = new AddDependencyOperation(dep);
            operation.run(monitor);
        } catch (CoreException e) {
            throw new InvocationTargetException(e);
        }
    }

}
