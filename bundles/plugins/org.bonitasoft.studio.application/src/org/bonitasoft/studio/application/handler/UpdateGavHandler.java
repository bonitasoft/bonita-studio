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
import java.nio.file.Path;
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
import org.bonitasoft.studio.application.ui.control.UpdateGavPage;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.swt.widgets.Shell;

public class UpdateGavHandler extends ImportExtensionHandler {

    public static final String EXTENSION_TYPE_PARAMETER = "extensionType";

    @Inject
    public UpdateGavHandler(RepositoryAccessor repositoryAccessor,
            MavenRepositoryRegistry mavenRepositoryRegistry,
            MavenProjectHelper mavenProjectHelper,
            ExceptionDialogHandler errorDialogHandler,
            CommandExecutor commandExecutor) {
        super(repositoryAccessor, mavenRepositoryRegistry, mavenProjectHelper, errorDialogHandler, commandExecutor);
    }

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell,
            @Named(EXTENSION_TYPE_PARAMETER) String extensionType,
            @org.eclipse.e4.core.di.annotations.Optional @Named("groupId") String groupId,
            @org.eclipse.e4.core.di.annotations.Optional @Named("artifactId") String artifactId,
            @org.eclipse.e4.core.di.annotations.Optional @Named("version") String version,
            @org.eclipse.e4.core.di.annotations.Optional @Named("type") String type,
            @org.eclipse.e4.core.di.annotations.Optional @Named("classifier") String classifier) {

        AbstractRepository currentRepository = repositoryAccessor.getCurrentRepository();
        Model mavenModel = loadMavenModel(mavenProjectHelper, currentRepository);
        if (mavenModel == null) {
            return;
        }

        Optional<Dependency> dependencytoUpdate = createDependency(groupId, artifactId, version, classifier, type);
        Optional<Dependency> existingDependency = mavenProjectHelper.findDependency(mavenModel,
                dependencytoUpdate.orElse(new Dependency()));

        if (existingDependency.isPresent()) {
            Path dependencyPath = currentRepository.getLocalDependencyStore().dependencyPath(existingDependency.get())
                    .resolve(LocalDependenciesStore.dependencyFileName(existingDependency.get()));
            ArtifactType artifactType = ArtifactType.valueOf(extensionType);
            var extensionTypeHandler = new ExtensionTypeHandler(artifactType);
            UpdateGavPage updateGavPage = new UpdateGavPage(mavenRepositoryRegistry,
                    mavenModel,
                    extensionTypeHandler,
                    dependencytoUpdate,
                    dependencyPath,
                    Optional.of(true));

            WizardBuilder.<Boolean> newWizard()
                    .withTitle(Messages.editMavenCoordinates)
                    .needProgress()
                    .havingPage(newPage()
                            .withTitle(Messages.editMavenCoordinates)
                            .withDescription(Messages.editMavenCoordinatesTooltip)
                            .withControl(updateGavPage))
                    .onFinish(container -> performFinish(container,
                            updateGavPage,
                            existingDependency.get(),
                            currentRepository,
                            mavenModel,
                            extensionTypeHandler))
                    .open(activeShell, org.bonitasoft.studio.ui.i18n.Messages.edit);
        }
    }

    private Optional<Boolean> performFinish(IWizardContainer container,
            UpdateGavPage updateGavPage,
            Dependency existingDependency,
            org.bonitasoft.studio.common.repository.model.IRepository currentRepository,
            Model mavenModel,
            ExtensionTypeHandler extensionTypeHandler) {

        DependencyUpdate dependencyUpdate = new DependencyUpdate(existingDependency, updateGavPage.getDependency(), true);
        var updateExtensionDecorator = new UpdateExtensionOperationDecorator(List.of(dependencyUpdate),
                currentRepository, commandExecutor);
        try {
            container.run(true, false, updateExtensionDecorator::preUpdate);
            Optional<Boolean> result = doExtensionUpdate(container, updateGavPage, currentRepository, mavenModel,
                    extensionTypeHandler, updateExtensionDecorator);
            if (result.isPresent()) {
                container.run(true, false, monitor -> {
                    updateExtensionDecorator.postUpdate(monitor);
                    try {
                        new RemoveDependencyOperation(existingDependency).run(monitor);
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

    @Override
    protected Optional<Boolean> doExtensionUpdate(IWizardContainer container,
            ImportExtensionPage importExtensionPage,
            IRepository currentRepository,
            Model mavenModel,
            ExtensionTypeHandler extensionTypeHandler,
            UpdateExtensionOperationDecorator updateExtensionDecorator) {
        return fileImport(container,
                importExtensionPage.getDependency(),
                importExtensionPage.getDependencyLookup(),
                mavenRepositoryRegistry,
                currentRepository.getLocalDependencyStore(),
                mavenModel,
                extensionTypeHandler,
                updateExtensionDecorator);
    }

}
