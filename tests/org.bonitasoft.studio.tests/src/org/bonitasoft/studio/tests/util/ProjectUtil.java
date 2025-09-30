/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.util;

import static java.util.function.Predicate.not;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.model.AppProjectConfiguration;
import org.bonitasoft.studio.common.repository.core.maven.plugin.ImportMavenModuleOperation;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.repository.EnvironmentFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.maven.ExtensionProjectFileStore;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class ProjectUtil {

    /**
     * Removes all current project user artifacts and extensions
     * 
     * @throws CoreException
     */
    public static void cleanProject() throws CoreException {
        var project = RepositoryManager.getInstance().getAccessor().getCurrentRepository().orElseThrow();
        project.getAllShareableStores().stream()
                .flatMap(s -> s.getChildren().stream())
                .filter(not(OrganizationFileStore.class::isInstance))
                .filter(not(EnvironmentFileStore.class::isInstance))
                .filter(IRepositoryFileStore::canBeDeleted)
                .forEach(IRepositoryFileStore::delete);
        removeUserExtensions();
    }

    public static void waitForProjectOperations() throws CoreException {
        try {
            // wait for any initial build operation
            Job.getJobManager().join(ResourcesPlugin.getWorkspace().getRuleFactory().buildRule(), null);
            // wait for any ongoing operation on repo manager
            Job.getJobManager().join(RepositoryManager.class, null);
            // wait for any ongoing analysis of project dependencies
            Job.getJobManager().join(ProjectDependenciesStore.ANALYZE_PPROJECT_DEPENDENCIES_FAMILY, null);
            // wait for ongoing auto build operations
            Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
            // and any post build operation added again in the process
            Job.getJobManager().join(ResourcesPlugin.getWorkspace().getRuleFactory().buildRule(), null);
        } catch (OperationCanceledException | InterruptedException e) {
            throw new CoreException(Status.error("Error while waiting for project operations", e));
        }
    }

    public static void removeUserExtensions() throws CoreException {
        waitForProjectOperations();

        BuildScheduler.callWithBuildRule(() -> {
            ensureExtensionsParentProjectIsImported();
            IProject project = RepositoryManager.getInstance().getAccessor().getCurrentRepository().orElseThrow()
                    .getProject();
            Model mavenModel = MavenProjectHelper.getMavenModel(project);
            // remove all extensions registered as dependencies
            var dependenciesToRemove = mavenModel.getDependencies()
                    .stream()
                    .filter(not(AppProjectConfiguration::isInternalDependency))
                    .collect(Collectors.toList());
            if (!dependenciesToRemove.isEmpty()) {
                new RemoveDependencyOperation(dependenciesToRemove).run(new NullProgressMonitor());
            }
            // remove all extensions as reactor modules
            var extensionsStore = RepositoryManager.getInstance().getAccessor()
                    .getRepositoryStore(ExtensionRepositoryStore.class);
            extensionsStore.getChildren().forEach(ExtensionProjectFileStore::delete);
            // and clean the extensions parent project
            var extensionsProject = RepositoryManager.getInstance().getAccessor().getCurrentProject()
                    .map(BonitaProject::getExtensionsParentProject).filter(Objects::nonNull);
            if (extensionsProject.isPresent() && extensionsProject.get().exists()) {
                var pom = extensionsProject.get().getLocation().toPath().resolve("pom.xml");
                if (!Files.exists(pom)) {
                    extensionsProject.get().delete(true, true, new NullProgressMonitor());
                } else {
                    var extensionsMavenModel = MavenProjectHelper.getMavenModel(extensionsProject.get());
                    extensionsMavenModel.getModules().clear();
                    MavenProjectHelper.saveModel(extensionsProject.get(), extensionsMavenModel,
                            new NullProgressMonitor());
                }
            }
            return true;
        });

        waitForProjectOperations();
    }

    /**
     * Make sure that the extensions parent project, if present, is imported as an Eclipse project.
     */
    private static void ensureExtensionsParentProjectIsImported() {
        var bonitaProj = RepositoryManager.getInstance().getAccessor().getCurrentProject();
        bonitaProj.ifPresent(p -> {
            var parent = p.getExtensionsParentProject();
            if (!parent.exists()) {
                var parentLoc = p.getParentProject().getLocation().toPath().resolve(BonitaProject.EXTENSIONS_MODULE);
                if (Files.exists(parentLoc) && Files.exists(parentLoc.resolve("pom.xml"))) {
                    try {
                        new ImportMavenModuleOperation(parentLoc.toFile()).run(new NullProgressMonitor());
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        });
    }

    public static DiagramFileStore importProcFile(URL procFileURL) throws IOException {
        var diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        try (var is = procFileURL.openStream()) {
            var name = new File(procFileURL.getFile()).getName();
            return diagramStore.importInputStream(name, is);
        }
    }
}
