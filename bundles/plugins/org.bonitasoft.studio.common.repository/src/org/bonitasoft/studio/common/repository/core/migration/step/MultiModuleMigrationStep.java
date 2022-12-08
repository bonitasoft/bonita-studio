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
package org.bonitasoft.studio.common.repository.core.migration.step;

import static java.util.function.Predicate.not;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.function.Consumer;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.MavenAppModuleModelBuilder;
import org.bonitasoft.studio.common.repository.core.MavenParentProjectModelBuilder;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.plugin.CreateBdmModulePlugin;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReportWriter;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.lib.Constants;
import org.osgi.framework.Version;

public class MultiModuleMigrationStep implements MigrationStep {
    
    @Override
    public boolean requireCleanImport() {
        return true;
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        var report = MigrationReport.emptyReport();
        report.updated("The project layout has been changed in favor of a multi modules maven project. It means that files location inside the project have changed.  " + 
                       "It is an internal change and will not impact the design usage in the Studio. Added maven modules and their respective `pom.xml` files are *reserved for internal use*.");
        var app = project.resolve(BonitaProject.APP_MODULE);
        try {
            if (Files.isDirectory(app)) {
                FileUtil.deleteDir(app);
            }
            Files.createDirectory(app);
            storeFolders().stream()
                    .filter(not(BonitaProject.BDM_MODULE::equals))
                    .filter(folderName -> Files.exists(project.resolve(folderName)))
                    .forEach(moveStoreFolders(project, app));
            var migrationNotes = project.resolve(MigrationReportWriter.DEFAULT_REPORT_FILE_NAME);
            if (Files.exists(migrationNotes)) {
                Files.move(migrationNotes, app.resolve(MigrationReportWriter.DEFAULT_REPORT_FILE_NAME));
            }
            var gitIgnore = project.resolve(Constants.GITIGNORE_FILENAME);
            if (Files.exists(gitIgnore)) {
                Files.move(gitIgnore, app.resolve(Constants.GITIGNORE_FILENAME));
            }
            try (var is = GitProject.getParentGitIgnoreTemplate().openStream()) {
                Files.copy(is, project.resolve(Constants.GITIGNORE_FILENAME));
            }
            var descriptor = project.resolve(IProjectDescription.DESCRIPTION_FILE_NAME);
            try (var is = Files.newInputStream(descriptor)) {
                Files.copy(is, project.resolve(BonitaProject.APP_MODULE).resolve(IProjectDescription.DESCRIPTION_FILE_NAME));
            }

            var rootModel = loadMavenModel(project);

            Path parentPomFile = project.resolve(POM_FILE_NAME);
            MavenProjectHelper.saveModel(parentPomFile, toParentModel(rootModel.clone()));
            Path appPomFile = app.resolve(POM_FILE_NAME);
            MavenProjectHelper.saveModel(appPomFile, toAppModel(rootModel.clone()));

            var bdmFolder = project.resolve(BonitaProject.BDM_MODULE);
            if (Files.exists(bdmFolder)) {
                var plugin = new CreateBdmModulePlugin(project, rootModel.getArtifactId());
                plugin.execute(new NullProgressMonitor());
                Files.deleteIfExists(bdmFolder.resolve(".artifact-descriptor.properties"));
                report.updated("The project's Business Data Model now live in its own maven module. While it does not impact the design usage, it can now be built and deployed independantly from a Studio.  " +
                               "The BDM model dependency share the same `version` and `groupId` of the parent project. It is enforced by the format of the Bonita project and must not be changed.");
            }
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed to update project layout to multi-module.", e));
        }
        return report;
    }

    private Consumer<? super String> moveStoreFolders(Path project, Path app) {
        return folderName -> {
            var sourceDirectory = project.resolve(folderName);
            try {
                FileUtil.copyDirectory(sourceDirectory, app.resolve(folderName));
                FileUtil.deleteDir(sourceDirectory);
                var readme = project.resolve("README.adoc");
                if ("documentation".equals(folderName)
                        && Files.exists(readme)) {
                    Files.move(readme, app.resolve("README.adoc"));
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    private Model toAppModel(Model model) {
        var builder = new MavenAppModuleModelBuilder();
        builder.setArtifactId(model.getArtifactId());
        builder.setVersion(model.getVersion());
        builder.setGroupId(model.getGroupId());
        builder.setDisplayName(model.getName());
        builder.setDescription(model.getDescription());

        var result = builder.toMavenModel();
        model.getProperties().clear();
        model.getBuild().setPluginManagement(null);
        result.getDependencies().clear();
        result.getDependencies().addAll(model.getDependencies());
        return result;
    }

    private Model toParentModel(Model model) {
        var builder = new MavenParentProjectModelBuilder();
        builder.setArtifactId(model.getArtifactId());
        builder.setVersion(model.getVersion());
        builder.setGroupId(model.getGroupId());
        builder.setBonitaVersion(ProductVersion.BONITA_RUNTIME_VERSION);
        return builder.toMavenModel();
    }

    private HashSet<String> storeFolders() {
        var storeFolders = new HashSet<>(AbstractRepositoryStore.REPO_STORE_ORDER.keySet());
        storeFolders.add("kpis");
        storeFolders.add("database_connectors_properties");
        storeFolders.add("documentation");
        storeFolders.add("src");
        storeFolders.add(LocalDependenciesStore.NAME);
        return storeFolders;
    }

    @Override
    public boolean appliesTo(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.16.0")) < 0;
    }
}
