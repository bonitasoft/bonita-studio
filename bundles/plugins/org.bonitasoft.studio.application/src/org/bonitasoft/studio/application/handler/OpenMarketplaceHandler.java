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
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.UpdateExtensionOperationDecorator;
import org.bonitasoft.studio.application.ui.control.BonitaMarketplacePage;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenModelOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.UpdateDependencyVersionOperation;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Shell;

public class OpenMarketplaceHandler {

    private static final String ADD_DRIVER_COMMAND_ID = "org.bonitasoft.studio.connectors.database.driver.add.command";
    private static final Object BONITA_CONNECTOR_GROUP_ID = "org.bonitasoft.connectors";
    private static final Object BONITA_CONNECTOR_DATABASE_ARTIFACT_ID = "bonita-connector-database";
    private MavenProjectHelper mavenProjectHelper;
    private RepositoryAccessor repositoryAccessor;
    private CommandExecutor commandExecutor;

    @Inject
    public OpenMarketplaceHandler(MavenProjectHelper mavenProjectHelper,
            RepositoryAccessor repositoryAccessor,
            CommandExecutor commandExecutor) {
        this.mavenProjectHelper = mavenProjectHelper;
        this.repositoryAccessor = repositoryAccessor;
        this.commandExecutor = commandExecutor;
    }

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell,
            @org.eclipse.e4.core.di.annotations.Optional @Named("types") String types) {
        String[] extensionTypes = types != null
                ? types.split(":")
                : new String[] {};
        BonitaMarketplacePage bonitaMarketplacePage = createBonitaMarketPlacePage(repositoryAccessor, extensionTypes);
        WizardBuilder.<Boolean> newWizard()
                .withTitle(getWizardTitle())
                .needProgress()
                .havingPage(newPage()
                        .withTitle(getWizardTitle())
                        .withDescription(getPageDescription())
                        .withControl(bonitaMarketplacePage))
                .onFinish(container -> performFinish(container, bonitaMarketplacePage))
                .withSize(800, 800)
                .withFixedInitialSize()
                .open(activeShell, Messages.install);

        if (databaseConnectorAdded(bonitaMarketplacePage.getDependenciesToAdd())
                && MessageDialog.openQuestion(activeShell, Messages.addDatabaseDriverTitle,
                        Messages.addDatabaseDriverQuestion)) {
            new CommandExecutor().executeCommand(ADD_DRIVER_COMMAND_ID, Collections.emptyMap());
        }
    }

    protected String getPageDescription() {
        return Messages.extendProjectDescription;
    }

    protected String getWizardTitle() {
        return Messages.bonitaMarketplace;
    }

    protected BonitaMarketplacePage createBonitaMarketPlacePage(RepositoryAccessor repositoryAccessor,
            String... types) {
        return types.length == 0
                ? new BonitaMarketplacePage(repositoryAccessor.getCurrentRepository().getProject())
                : new BonitaMarketplacePage(repositoryAccessor.getCurrentRepository().getProject(), types);
    }

    private Optional<Boolean> performFinish(IWizardContainer container, BonitaMarketplacePage extendProjectPage) {
        List<DependencyUpdate> dependenciesUpdates = extendProjectPage.getDependenciesToUpdate().stream()
                .map(bad -> new DependencyUpdate(findCurrentDependency(bad), bad.getBestVersion()))
                .collect(Collectors.toList());
        var updateExtensionDecorator = new UpdateExtensionOperationDecorator(dependenciesUpdates,
                repositoryAccessor.getCurrentRepository(), commandExecutor);
        try {
            container.run(true, false, monitor -> {
                updateExtensionDecorator.preUpdate(monitor);
                installDependencies(extendProjectPage, repositoryAccessor, monitor);
                updateExtensionDecorator.postUpdate(monitor);
            });
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            MessageDialog.openError(container.getShell(), Messages.addDependenciesError, e.getMessage());
        }
        return Optional.of(true);
    }

    protected void installDependencies(BonitaMarketplacePage extendProjectPage, RepositoryAccessor repositoryAccessor,
            IProgressMonitor monitor)
            throws InvocationTargetException {
        monitor.beginTask(Messages.installingExtensions, IProgressMonitor.UNKNOWN);
        updateDependency(extendProjectPage.getDependenciesToUpdate(), monitor);
        addDependency(extendProjectPage.getDependenciesToAdd(), monitor);
        if (extendProjectPage.getDependenciesToUpdate().isEmpty()) {
            MavenModelOperation.scheduleAnalyzeProjectDependenciesJob(repositoryAccessor);
        } else {
            repositoryAccessor.getCurrentRepository().getProjectDependenciesStore().analyze(monitor);
        }
    }

    private boolean databaseConnectorAdded(List<BonitaArtifactDependency> dependenciesToAdd) {
        return dependenciesToAdd.stream().anyMatch(isDatabaseConnector());
    }

    private Predicate<? super BonitaArtifactDependency> isDatabaseConnector() {
        return dep -> Objects.equals(dep.getGroupId(), BONITA_CONNECTOR_GROUP_ID)
                && Objects.equals(dep.getArtifactId(), BONITA_CONNECTOR_DATABASE_ARTIFACT_ID);
    }

    private void updateDependency(List<BonitaArtifactDependency> deps, IProgressMonitor monitor)
            throws InvocationTargetException {
        try {
            new UpdateDependencyVersionOperation(deps.stream()
                    .map(BonitaArtifactDependency::toMavenDependency)
                    .collect(Collectors.toList()))
                            .disableAnalyze()
                            .run(monitor);
        } catch (CoreException e) {
            throw new InvocationTargetException(e);
        }
    }

    private Dependency findCurrentDependency(BonitaArtifactDependency bad) {
        try {
            return mavenProjectHelper.getMavenModel(repositoryAccessor.getCurrentRepository().getProject())
                    .getDependencies().stream()
                    .filter(dep -> dep.getGroupId().equals(bad.getGroupId())
                            && dep.getArtifactId().equals(bad.getArtifactId()))
                    .findFirst()
                    .orElseThrow();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            throw new NoSuchElementException(e.getMessage());
        }
    }

    private void addDependency(List<BonitaArtifactDependency> deps, IProgressMonitor monitor)
            throws InvocationTargetException {
        try {
            new AddDependencyOperation(deps.stream()
                    .map(BonitaArtifactDependency::toMavenDependency)
                    .collect(Collectors.toList()))
                            .disableAnalyze()
                            .run(monitor);
        } catch (CoreException e) {
            throw new InvocationTargetException(e);
        }
    }

}
