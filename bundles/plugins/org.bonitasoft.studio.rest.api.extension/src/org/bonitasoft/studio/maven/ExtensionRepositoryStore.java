/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.maven;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.model.Model;
import org.bonitasoft.plugin.analyze.report.model.CustomPage;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.IProjectContainer;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.maven.plugin.CreateExtensionsModulePlugin;
import org.bonitasoft.studio.common.repository.core.maven.plugin.ImportMavenModuleOperation;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.report.AsciidocMigrationReportWriter;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReportWriter;
import org.bonitasoft.studio.common.repository.core.migration.step.BdmModelArtifactMigrationStep;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractFolderRepositoryStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.DependencyRestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.PathTemplate;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.BonitaVersionMigrationStep;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.Groovy3MigrationStep;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.GroovyCompilerPluginStep;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.Java11MigrationStep;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.ParentMigrationStep;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.RuntimeBOMMigrationStep;
import org.bonitasoft.studio.theme.DependencyThemeFileStore;
import org.bonitasoft.studio.theme.ThemeFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;

public class ExtensionRepositoryStore
        extends AbstractFolderRepositoryStore<ExtensionProjectFileStore>
        implements IProjectContainer {

    public static final String API_EXTENSION_CONTENT_TYPE = "apiExtension";
    public static final String THEME_CONTENT_TYPE = "theme";

    public static final String STORE_NAME = "extensions";

    private static final List<MavenModelMigration> REST_API_EXTENSIONS_MIGRATION_STEPS = List.of(
            new Groovy3MigrationStep(),
            new Java11MigrationStep(),
            new BonitaVersionMigrationStep(),
            new RuntimeBOMMigrationStep(),
            new BdmModelArtifactMigrationStep(),
            new GroovyCompilerPluginStep());

    private static final List<MavenModelMigration> MIGRATION_STEPS = List.of(
            new ParentMigrationStep());

    private AsciidocMigrationReportWriter asciidocMigrationReportWriter = new AsciidocMigrationReportWriter();

    @Override
    public void createRepositoryStore(IRepository repository) {
        this.repository = repository;
        if (getResource() != null && getResource().exists()) {
            importProjects();
        }
    }

    @Override
    public IFolder getResource() {
        var project = getBonitaProject();
        if (project != null && project.exists()) {
            return project.getAppProject().getFolder(STORE_NAME);
        }
        return super.getResource();
    }

    private BonitaProject getBonitaProject() {
        return Adapters.adapt(getRepository(), BonitaProject.class);
    }

    @Override
    public String getName() {
        return STORE_NAME;
    }

    private void importProjects() {
        getChildren().stream()
                .filter(c -> c.getProject() != null && !c.getProject().exists())
                .forEach(c -> {
                    try {
                        c.importProject();
                    } catch (ImportProjectException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public ExtensionProjectFileStore importArchiveData(String folderName, List<ImportArchiveData> importArchiveData,
            IProgressMonitor monitor) throws CoreException {
        if (!getResource().exists() || !getResource().getFile("pom.xml").exists()) {
            createExtensionModule(getBonitaProject(), new NullProgressMonitor());
        }

        importArchiveData
                .sort((d1, d2) -> d1.getName().contains(MigrationReportWriter.DEFAULT_REPORT_FILE_NAME) ? -1 : 1);
        final ExtensionProjectFileStore fileStore = super.importArchiveData(folderName, importArchiveData, monitor);
        try {
            migrate(fileStore, monitor);
        } catch (MigrationException e) {
            throw new CoreException(Status.error("Failed to migrate " + folderName, e));
        }
        try {
            if (!fileStore.getProject().exists()) {
                fileStore.importProject();
            }
        } catch (final ImportProjectException e) {
            BonitaStudioLog.error(e);
        }
        return fileStore;
    }

    @Override
    public void close() {
        for (var child : getChildren()) {
            //Remove project from workspace root to avoid name collisions with other Bonita Project extension projects
            child.removeProject();
        }
    }

    @Override
    public void repositoryUpdated() {
        importProjects();
    }

    @Override
    public void refresh() {
        refreshResources();

        BonitaProject bonitaProject = getBonitaProject();
        var parentProject = bonitaProject.getExtensionsParentProject();
        // Remove all existing project before clean re import
        if (parentProject.exists() && !parentProject.isOpen()) {
            try {
                parentProject.delete(!parentProject.getFile("pom.xml").exists(), false, new NullProgressMonitor());
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }

        Stream.of(ResourcesPlugin.getWorkspace().getRoot().getProjects())
                .filter(Predicate.not(IProject::isOpen))
                .filter(project -> project.getRawLocation() != null)
                .filter(project -> Objects.equals(project.getRawLocation().toFile().getParentFile(),
                        bonitaProject.getParentProject().getLocation().toFile().toPath()
                                .resolve(BonitaProject.EXTENSIONS_MODULE).toFile()))
                .forEach(project -> {
                    try {
                        project.delete(true, false, new NullProgressMonitor());
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                });

        final IFolder folder = getResource();
        File parentFolder = folder.getLocation().toFile();
        var projectFolders = parentFolder.listFiles(file -> file.isDirectory() && new File(file, "pom.xml").exists());
        if (projectFolders != null) {
            var existingProjects = getBonitaProject().getExtensionsProjects().stream()
                    .map(IProject::getName)
                    .collect(Collectors.toList());
            if (Stream.of(projectFolders)
                    .map(parent -> new File(parent, "pom.xml"))
                    .map(pomFile -> {
                        try {
                            return MavenProjectHelper.readModel(pomFile).getArtifactId();
                        } catch (CoreException e) {
                            BonitaStudioLog.error(e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .anyMatch(artifactId -> !existingProjects.contains(artifactId))) {
                try {
                    parentProject.delete(false, false, new NullProgressMonitor());
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
                var importBdmModules = new ImportMavenModuleOperation(parentFolder);
                try {
                    importBdmModules.run(new NullProgressMonitor());
                    if(bonitaProject.getGitDir().exists()) {
                        var connectProviderOperation = bonitaProject.newConnectProviderOperation();
                        connectProviderOperation.run(new NullProgressMonitor());
                    }
                } catch (CoreException | InvocationTargetException | InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }

        createExtensionFolderLinkInAppProject(getBonitaProject());
    }

    @Override
    protected InputStream handlePreImport(String fileName, InputStream inputStream)
            throws MigrationException, IOException {
        if (fileName.endsWith("/.settings/org.eclipse.jdt.groovy.core.prefs")
                || fileName.endsWith("/.settings/org.eclipse.jdt.core.prefs")) {
            // Settings file recreated by eclipse
            inputStream.close();
            return null;
        }
        return super.handlePreImport(fileName, inputStream);
    }

    @Override
    public List<ExtensionProjectFileStore> getChildren() {
        // Refresh children resources
        refreshResources();

        final List<ExtensionProjectFileStore> result = new ArrayList<>();
        final IFolder folder = getResource();
        try {
            if (folder.isAccessible()) {
                for (final IResource r : folder.members()) {
                    if (r instanceof IFolder && !r.isHidden()
                            && ((IFolder) r).getFile(IMavenConstants.POM_FILE_NAME).exists()) {
                        result.add(createRepositoryFileStore(r.getName()));
                    }
                }
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        var projectDependenciesStore = getRepository().getProjectDependenciesStore();
        if (projectDependenciesStore != null) {
            projectDependenciesStore.getRestAPIExtensions().stream()
                    .filter(ext -> !hasSourceProject(ext))
                    .map(ext -> new DependencyRestAPIExtensionFileStore(ext, this))
                    .forEach(result::add);
            projectDependenciesStore.getThemes().stream()
                    .filter(ext -> !hasSourceProject(ext))
                    .map(ext -> new DependencyThemeFileStore(ext, this))
                    .forEach(result::add);
        }

        return result;
    }

    private void refreshResources() {
        final IFolder folder = getResource();
        if (folder != null && !folder.isSynchronized(IResource.DEPTH_ONE)) {
            try {
                folder.refreshLocal(IResource.DEPTH_ONE, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    private boolean hasSourceProject(CustomPage ext) {
        var artifact = ext.getArtifact();
        return MavenPlugin.getMavenProjectRegistry().getMavenProject(artifact.getGroupId(), artifact.getArtifactId(),
                artifact.getVersion()) != null;
    }

    public <V extends ExtensionProjectFileStore> List<V> getChildren(Class<V> contenType) {
        if (RestAPIExtensionFileStore.class.equals(contenType)) {
            return getChildren().stream()
                    .filter(fStore -> Objects.equals(fStore.getContentType(), API_EXTENSION_CONTENT_TYPE))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    protected List<IResource> listChildren() throws CoreException {
        return super.listChildren().stream()
                .filter(IFolder.class::isInstance)
                .collect(Collectors.toList());
    }

    @Override
    public MigrationReport migrate(IProgressMonitor monitor)
            throws CoreException, MigrationException {

        var project = getBonitaProject();
        if (project != null) {
            createExtensionFolderLinkInAppProject(project);
        }

        var filesToMigrate = getChildren().stream()
                .filter(fs -> !fs.isReadOnly())
                .filter(IRepositoryFileStore::canBeShared)
                .collect(Collectors.toList());

        for (ExtensionProjectFileStore fStore : filesToMigrate) {
            migrate(fStore, monitor);
        }

        BuildScheduler.scheduleJobWithBuildRule(
                BonitaProject.updateMavenProjectsJob(getBonitaProject().getExtensionsProjects(), true));
        return MigrationReport.emptyReport();
    }

    @Override
    public void migrate(IRepositoryFileStore<?> fileStore, IProgressMonitor monitor)
            throws CoreException, MigrationException {
        var fStore = (ExtensionProjectFileStore) fileStore;
        IFolder projectFolder = fStore.getResource();
        IFile groovyPrefs = projectFolder.getFile(".settings/org.eclipse.jdt.groovy.core.prefs");
        if (groovyPrefs.exists()) {
            groovyPrefs.delete(true, monitor);
        }
        IFile jdtPrefs = projectFolder.getFile(".settings/org.eclipse.jdt.core.prefs");
        if (jdtPrefs.exists()) {
            jdtPrefs.delete(true, monitor);
        }
        IFile pomFile = projectFolder.getFile("pom.xml");
        if (pomFile.exists()) {
            try {
                migratePom(fStore, pomFile, monitor);
            } catch (final IOException e) {
                throw new MigrationException("Cannot migrate extension " + pomFile.getName(),
                        e);
            }
        }
    }

    private void migratePom(ExtensionProjectFileStore<?> fileStore, IFile pomFile, IProgressMonitor monitor)
            throws IOException, CoreException, MigrationException {
        try (var is = pomFile.getContents()) {
            Model model = MavenPlugin.getMaven().readModel(is);
            boolean hasBeenMigrated = false;
            MigrationReport report = new MigrationReport();
            var metadata = ProjectMetadata.read(getRepository().getProject(), monitor);
            if (API_EXTENSION_CONTENT_TYPE.equals(fileStore.getContentType())) {
                for (MavenModelMigration step : REST_API_EXTENSIONS_MIGRATION_STEPS) {
                    if (step.appliesTo(model, metadata)) {
                        report = step.migrate(model, metadata).merge(report);
                        hasBeenMigrated = true;
                    }
                }
            }
            for (MavenModelMigration step : MIGRATION_STEPS) {
                if (step.appliesTo(model, metadata)) {
                    report = step.migrate(model, metadata).merge(report);
                    hasBeenMigrated = true;
                }
            }
            String parentArtifactId = String.format("%s-extensions", metadata.getProjectId());
            if (!Objects.equals(model.getParent().getArtifactId(), parentArtifactId)) {
                model.getParent().setArtifactId(parentArtifactId);
                MavenProjectHelper.update(pomFile.getLocation().toFile(), model);
            }
            if (hasBeenMigrated) {
                var file = ((IFolder) pomFile.getParent())
                        .getFile(MigrationReportWriter.DEFAULT_REPORT_FILE_NAME);
                asciidocMigrationReportWriter.write(report, file.getLocation().toFile().toPath());
                file.refreshLocal(IResource.DEPTH_ONE, monitor);
                MavenProjectHelper.update(pomFile.getLocation().toFile(), model);
                pomFile.refreshLocal(IResource.DEPTH_ONE, monitor);
            }
        } catch (IOException | CoreException e) {
            throw new MigrationException(e);
        }
    }

    public Optional<ExtensionProjectFileStore> findByCustomPageId(String pageId) {
        return getChildren().stream()
                .filter(project -> Objects.equals(project.getPageId(), pageId))
                .findFirst();
    }

    /**
     * @param resource, eg: GET|extension/resourceName
     * @return an Optional with the corresponding file store implementing the given resource
     */
    public Optional<RestAPIExtensionFileStore> findByRestResource(String resource) {
        return getChildren().stream()
                .filter(fStore -> Objects.equals(fStore.getContentType(), API_EXTENSION_CONTENT_TYPE))
                .map(fStore -> new RestAPIExtensionFileStore(fStore.getName(), this))
                .filter(fStore -> matchesRestResource(resource, fStore))
                .findFirst();
    }

    private boolean matchesRestResource(String restResource, RestAPIExtensionFileStore fStore) {
        String[] splitted = restResource.split("\\|");
        PathTemplate pathTemplate = new PathTemplate(splitted[1].substring("extension/".length()),
                splitted[0]);
        try {
            return fStore.getContent().getPathTemplates().stream().anyMatch(pathTemplate::equals);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(e);
            return false;
        }
    }

    public Optional<ThemeFileStore> findTheme(String customPageName) {
        return getChildren().stream()
                .filter(fStore -> Objects.equals(customPageName, fStore.getPageId()))
                .filter(fStore -> Objects.equals(fStore.getContentType(), THEME_CONTENT_TYPE))
                .map(fStore -> new ThemeFileStore(fStore.getName(), this))
                .findFirst();
    }

    @Override
    public ExtensionProjectFileStore<?> createRepositoryFileStore(String fileName) {
        var fStore = new ExtensionProjectFileStore<>(fileName, this);
        if (!fileName.contains("/") && Objects.equals(fStore.getContentType(), API_EXTENSION_CONTENT_TYPE)) {
            return new RestAPIExtensionFileStore(fStore.getName(), this);
        }
        if (!fileName.contains("/") && Objects.equals(fStore.getContentType(), THEME_CONTENT_TYPE)) {
            return new ThemeFileStore(fStore.getName(), this);
        }
        return fStore;
    }

    public IFolder createExtensionModule(BonitaProject project, IProgressMonitor monitor) throws CoreException {
        var parentProject = project.getParentProject();
        var parentProjectPath = parentProject.getLocation().toFile().toPath();
        var plugin = new CreateExtensionsModulePlugin(parentProjectPath, project.getId());
        plugin.execute(new NullProgressMonitor());
        var importBdmModules = new ImportMavenModuleOperation(parentProjectPath.resolve(STORE_NAME).toFile());
        importBdmModules.run(monitor);
        project.getParentProject().refreshLocal(IResource.DEPTH_ONE, monitor);
        project.getExtensionsParentProject().refreshLocal(IResource.DEPTH_ONE, monitor);
        return createExtensionFolderLinkInAppProject(project);
    }

    public IFolder createExtensionFolderLinkInAppProject(BonitaProject project) {
        var extensionFolder = project.getParentProject().getFolder(STORE_NAME);
        var extensionLinkedFolder = project.getAppProject().getFolder(STORE_NAME);
        try {
            if (!extensionFolder.exists()) {
                extensionFolder.create(false, true, new NullProgressMonitor());
            }
            if (!extensionLinkedFolder.exists()) {
                extensionLinkedFolder.createLink(Path.fromOSString("PARENT-1-PROJECT_LOC/" + STORE_NAME),
                        IResource.REPLACE | IResource.ALLOW_MISSING_LOCAL,
                        new NullProgressMonitor());
                // Persist workspace state
                ResourcesPlugin.getWorkspace().save(false, new NullProgressMonitor());
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        return extensionLinkedFolder;
    }

    public Optional<ExtensionProjectFileStore> findByGAV(GAV gav) {
        return getChildren().stream()
                .filter(fStore -> gav.equals(fStore.getGAV()))
                .findFirst();
    }

    @Override
    public Collection<IProject> getChildrenProjects() {
        var bonitaProject = getBonitaProject();
        if (bonitaProject != null) {
            return bonitaProject.getExtensionsProjects();
        }
        return List.of();
    }

}
