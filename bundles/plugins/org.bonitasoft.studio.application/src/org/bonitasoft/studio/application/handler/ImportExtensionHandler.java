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
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.UpdateExtensionOperationDecorator;
import org.bonitasoft.studio.application.ui.control.ExtensionTypeHandler;
import org.bonitasoft.studio.application.ui.control.ImportExtensionPage;
import org.bonitasoft.studio.application.ui.control.ImportExtensionPage.ImportMode;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.DependencyGetOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.bonitasoft.studio.common.repository.core.maven.UpdateDependencyVersionOperation;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup.Status;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.repository.IRepository;
import org.eclipse.swt.widgets.Shell;

public class ImportExtensionHandler {

    public static final String EXTENSION_TYPE_PARAMETER = "extensionType";
    protected MavenProjectHelper mavenProjectHelper;
    protected RepositoryAccessor repositoryAccessor;
    protected MavenRepositoryRegistry mavenRepositoryRegistry;
    protected ExceptionDialogHandler errorDialogHandler;
    protected CommandExecutor commandExecutor;

    @Inject
    public ImportExtensionHandler(RepositoryAccessor repositoryAccessor,
            MavenRepositoryRegistry mavenRepositoryRegistry,
            MavenProjectHelper mavenProjectHelper,
            ExceptionDialogHandler errorDialogHandler,
            CommandExecutor commandExecutor) {
        this.mavenProjectHelper = mavenProjectHelper;
        this.repositoryAccessor = repositoryAccessor;
        this.mavenRepositoryRegistry = mavenRepositoryRegistry;
        this.errorDialogHandler = errorDialogHandler;
        this.commandExecutor = commandExecutor;
    }

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell,
            @Named(EXTENSION_TYPE_PARAMETER) String extensionType,
            @org.eclipse.e4.core.di.annotations.Optional @Named("groupId") String groupId,
            @org.eclipse.e4.core.di.annotations.Optional @Named("artifactId") String artifactId,
            @org.eclipse.e4.core.di.annotations.Optional @Named("version") String version,
            @org.eclipse.e4.core.di.annotations.Optional @Named("type") String type,
            @org.eclipse.e4.core.di.annotations.Optional @Named("classifier") String classifier,
            @org.eclipse.e4.core.di.annotations.Optional @Named("isLocal") String isLocal) {

        AbstractRepository currentRepository = repositoryAccessor.getCurrentRepository();
        Model mavenModel = loadMavenModel(mavenProjectHelper, currentRepository);
        if (mavenModel == null) {
            return;
        }
        boolean localExtension = Boolean.parseBoolean(isLocal);

        Optional<Dependency> dependencytoUpdate = createDependency(groupId, artifactId, version, classifier, type);

        ArtifactType artifactType = ArtifactType.valueOf(extensionType);
        var extensionTypeHandler = new ExtensionTypeHandler(artifactType);
        ImportExtensionPage importExtensionPage = new ImportExtensionPage(mavenRepositoryRegistry,
                mavenModel,
                extensionTypeHandler,
                dependencytoUpdate,
                Optional.ofNullable(localExtension));

        String titleType = extensionTypeHandler.getExtensionLabel();

        WizardBuilder.<Boolean> newWizard()
                .withTitle(String.format(Messages.importExtensionTitle, titleType))
                .needProgress()
                .havingPage(newPage()
                        .withTitle(String.format(Messages.importExtensionTitle, titleType))
                        .withDescription(String.format(Messages.importExtension, titleType))
                        .withControl(importExtensionPage))
                .onFinish(container -> performFinish(container,
                        importExtensionPage,
                        currentRepository,
                        mavenModel,
                        extensionTypeHandler))
                .open(activeShell, org.bonitasoft.studio.ui.i18n.Messages.importLabel);
    }

    protected Optional<Dependency> createDependency(String groupId, String artifactId, String version,
            String classifier,
            String type) {
        if (groupId == null) {
            return Optional.empty();
        }
        Dependency dependency = new Dependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        dependency.setClassifier(classifier);
        dependency.setType(type);
        return Optional.of(dependency);
    }

    protected Model loadMavenModel(MavenProjectHelper mavenProjectHelper, AbstractRepository currentRepository) {
        try {
            return mavenProjectHelper.getMavenModel(currentRepository.getProject());
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    private Optional<Boolean> performFinish(IWizardContainer container,
            ImportExtensionPage importExtensionPage,
            org.bonitasoft.studio.common.repository.model.IRepository currentRepository,
            Model mavenModel,
            ExtensionTypeHandler extensionTypeHandler) {
        var dependencytoUpdate = mavenProjectHelper.findDependencyInAnyVersion(mavenModel,
                importExtensionPage.getDependency());

        List<DependencyUpdate> dependenciesUpdate = dependencytoUpdate
                .map(dep -> new DependencyUpdate(dep, importExtensionPage.getDependency().getVersion()))
                .map(List::of)
                .orElseGet(List::of);

        var updateExtensionDecorator = new UpdateExtensionOperationDecorator(dependenciesUpdate,
                currentRepository, commandExecutor);
        try {
            Optional<Boolean> result = doExtensionUpdate(container, importExtensionPage, currentRepository, mavenModel,
                    extensionTypeHandler, updateExtensionDecorator);
            if (result.isPresent()) {
                container.run(true, false, monitor -> {
                    try {
                        MavenPlugin.getProjectConfigurationManager()
                                .updateProjectConfiguration(currentRepository.getProject(), monitor);
                    } catch (CoreException e) {
                        throw new InvocationTargetException(e);
                    }
                });
                if (updateExtensionDecorator.shouldValidateProject()) {
                    updateExtensionDecorator.validateDependenciesConstraints();
                }
            }
            return result;
        } catch (InvocationTargetException | InterruptedException e) {
            errorDialogHandler.openErrorDialog(container.getShell(), e.getMessage(), e);
            return Optional.empty();
        }
    }

    protected Optional<Boolean> doExtensionUpdate(IWizardContainer container,
            ImportExtensionPage importExtensionPage,
            org.bonitasoft.studio.common.repository.model.IRepository currentRepository,
            Model mavenModel,
            ExtensionTypeHandler extensionTypeHandler,
            UpdateExtensionOperationDecorator updateExtensionDecorator) {
        Optional<Boolean> result = Optional.empty();
        if (importExtensionPage.getImportMode() == ImportMode.MANUAL) {
            result = manualImport(container,
                    importExtensionPage.getDependency(),
                    mavenRepositoryRegistry,
                    mavenModel,
                    extensionTypeHandler,
                    updateExtensionDecorator);
        } else if (importExtensionPage.getDependencyLookup() != null) {
            result = fileImport(container,
                    importExtensionPage.getDependency(),
                    importExtensionPage.getDependencyLookup(),
                    mavenRepositoryRegistry,
                    currentRepository.getLocalDependencyStore(),
                    mavenModel,
                    extensionTypeHandler,
                    updateExtensionDecorator);
        }
        return result;
    }

    protected Optional<Boolean> fileImport(IWizardContainer container,
            Dependency dependency,
            DependencyLookup dependencyLookup,
            MavenRepositoryRegistry mavenRepositoryRegistry,
            LocalDependenciesStore localDependenciesStore,
            Model mavenModel,
            ExtensionTypeHandler extensionTypeHandler,
            UpdateExtensionOperationDecorator updateExtensionDecorator) {
        if (dependencyLookup.getStatus() == Status.FOUND) {
            return manualImport(container, dependency, mavenRepositoryRegistry, mavenModel, extensionTypeHandler,
                    updateExtensionDecorator);
        }
        dependencyLookup.setArtifactId(dependency.getArtifactId());
        dependencyLookup.setGroupId(dependency.getGroupId());
        dependencyLookup.setVersion(dependency.getVersion());
        dependencyLookup.setType(dependency.getType());
        dependencyLookup.setClassifier(dependency.getClassifier());
        try {
            container.run(true, false, monitor -> {
                try {
                    monitor.beginTask(Messages.installingExtensions, IProgressMonitor.UNKNOWN);
                    updateExtensionDecorator.preUpdate(monitor);
                    localDependenciesStore.install(dependencyLookup);
                    addDependency(mavenModel, dependency, monitor);
                    updateExtensionDecorator.postUpdate(monitor);
                    monitor.done();
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return Optional.empty();
        }
        return Optional.of(true);
    }

    private Optional<Boolean> manualImport(IWizardContainer container,
            Dependency dependency,
            MavenRepositoryRegistry mavenRepositoryRegistry,
            Model mavenModel,
            ExtensionTypeHandler extensionTypeHandler,
            UpdateExtensionOperationDecorator updateExtensionDecorator) {
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
                IStatus status = extensionTypeHandler.getExtensionValidator()
                        .validate(lookupResult.getArtifact().getFile());
                if (!status.isOK()) {
                    MessageDialog.openError(container.getShell(), Messages.addDependenciesError,
                            status.getMessage());
                    return Optional.empty();
                }
                container.run(true, false, monitor -> {
                    monitor.beginTask(Messages.installingExtensions, IProgressMonitor.UNKNOWN);
                    updateExtensionDecorator.preUpdate(monitor);
                    addDependency(mavenModel, dependency, monitor);
                    updateExtensionDecorator.postUpdate(monitor);
                });
                if (updateExtensionDecorator.shouldValidateProject()) {
                    updateExtensionDecorator.validateDependenciesConstraints();
                }
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

    private void addDependency(Model mavenModel, Dependency dep, IProgressMonitor monitor)
            throws InvocationTargetException {
        try {
            var operation = isUpdate(mavenModel, dep)
                    ? new UpdateDependencyVersionOperation(dep)
                    : new AddDependencyOperation(dep);
            operation.run(monitor);
        } catch (CoreException e) {
            throw new InvocationTargetException(e);
        }
    }

    protected boolean isUpdate(Model mavenModel, Dependency dep) {
        return mavenProjectHelper.findDependencyInAnyVersion(mavenModel, dep).isPresent();
    }

}
