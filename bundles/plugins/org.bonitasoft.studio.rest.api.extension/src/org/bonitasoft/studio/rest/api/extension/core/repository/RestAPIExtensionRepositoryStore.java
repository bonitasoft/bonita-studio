/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.maven.archetype.catalog.Archetype;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.CopyInputStream;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.report.AsciidocMigrationReportWriter;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReportWriter;
import org.bonitasoft.studio.common.repository.core.migration.step.BdmModelArtifactMigrationStep;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.builder.validator.AbstractCustomPageValidator;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetype;
import org.bonitasoft.studio.rest.api.extension.core.builder.RestAPIBuilder;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.BonitaVersionMigrationStep;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.Groovy3MigrationStep;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.Java11MigrationStep;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.RuntimeBOMMigrationStep;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.m2e.core.MavenPlugin;

public class RestAPIExtensionRepositoryStore extends CustomPageProjectRepositoryStore<RestAPIExtensionFileStore> {

    public static final String STORE_NAME = "restAPIExtensions";

    private static final List<MavenModelMigration> MIGRATION_STEPS = List.of(new Groovy3MigrationStep(),
            new Java11MigrationStep(),
            new BonitaVersionMigrationStep(),
            new RuntimeBOMMigrationStep(),
            new BdmModelArtifactMigrationStep());

    private AsciidocMigrationReportWriter asciidocMigrationReportWriter = new AsciidocMigrationReportWriter();

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public RestAPIExtensionFileStore createRepositoryFileStore(final String restApiName) {
        return new RestAPIExtensionFileStore(restApiName, this);
    }

    @Override
    public Archetype getArchetype() {
        return RestAPIExtensionArchetype.INSTANCE;
    }

    @Override
    public List<RestAPIExtensionFileStore> getChildren() {
        List<RestAPIExtensionFileStore> extensions = super.getChildren();

        var projectDependenciesStore = getRepository().getProjectDependenciesStore();
        if (projectDependenciesStore != null) {
            projectDependenciesStore.getRestAPIExtensions().stream()
                    .map(ext -> new DependencyRestAPIExtensionFileStore(ext, this))
                    .forEach(extensions::add);
        }

        return extensions;
    }

    @Override
    public void refreshMarkers() throws CoreException {
        List<RestAPIExtensionFileStore> children = getChildren();
        RestAPIBuilder restAPIBuilder = new RestAPIBuilder();
        for (CustomPageProjectFileStore fileStore : children) {
            for (AbstractCustomPageValidator validator : restAPIBuilder.createValidators(fileStore.getProject(),
                    new NullProgressMonitor())) {
                validator.acceptAndValidate();
            }
        }
    }

    /**
     * @param resource, eg: GET|extension/resourceName
     * @return an Optional with the corresponding file store implementing the given resource
     */
    public Optional<RestAPIExtensionFileStore> findByRestResource(String resource) {
        return getChildren().stream().filter(fStore -> matchesRestResource(resource, fStore)).findFirst();
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

    @Override
    protected InputStream handlePreImport(String fileName, InputStream inputStream)
            throws MigrationException, IOException {
        if (fileName.endsWith("/.settings/org.eclipse.jdt.groovy.core.prefs")
                || fileName.endsWith("/.settings/org.eclipse.jdt.core.prefs")) {
            // Settings file recreated by eclipse
            inputStream.close();
            return null;
        }
        if (fileName.endsWith("/pom.xml")) {
            var segments = fileName.split("/");
            var projectName = segments[segments.length - 2];
            try (CopyInputStream copyInputStream = new CopyInputStream(inputStream);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                Model model = MavenPlugin.getMaven().readModel(copyInputStream.getCopy());
                boolean hasBeenMigrated = false;
                MigrationReport report = new MigrationReport();
                var metadata = ProjectMetadata.read(getRepository().getProject(), new NullProgressMonitor());
                for (MavenModelMigration step : MIGRATION_STEPS) {
                    if (step.appliesTo(model, metadata)) {
                        report = step.migrate(model, metadata).merge(report);
                        hasBeenMigrated = true;
                    }
                }
                if (hasBeenMigrated) {
                    var file = getResource().getFolder(projectName)
                            .getFile(MigrationReportWriter.DEFAULT_REPORT_FILE_NAME);
                    asciidocMigrationReportWriter.write(report, file.getLocation().toFile().toPath());
                    file.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
                    MavenProjectHelper.update(copyInputStream.getFile(), model);
                    return Files.newInputStream(copyInputStream.getFile().toPath());
                }
                return copyInputStream.getCopy();
            } catch (IOException | CoreException e) {
                throw new MigrationException(e);
            }
        }
        return super.handlePreImport(fileName, inputStream);
    }

    @Override
    public MigrationReport migrate(IProgressMonitor monitor)
            throws CoreException, MigrationException {
        List<RestAPIExtensionFileStore> filesToMigrate = getChildren().stream()
                .filter(fs -> !fs.isReadOnly())
                .filter(IRepositoryFileStore::canBeShared)
                .collect(Collectors.toList());

        for (RestAPIExtensionFileStore fStore : filesToMigrate) {
            migrate(fStore, monitor);
        }
        return MigrationReport.emptyReport();
    }

    @Override
    public void migrate(IRepositoryFileStore<?> fileStore, IProgressMonitor monitor)
            throws CoreException, MigrationException {
        RestAPIExtensionFileStore fStore = (RestAPIExtensionFileStore) fileStore;
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
            try (final InputStream is = pomFile.getContents()) {
                InputStream newIs = handlePreImport(pomFile.getLocation().toString(), is);
                if (!is.equals(newIs)) {
                    pomFile.setContents(newIs, IResource.FORCE, monitor);
                    pomFile.refreshLocal(IResource.DEPTH_ONE, monitor);
                }
            } catch (final IOException e) {
                throw new MigrationException("Cannot migrate rest api extension " + pomFile.getName(),
                        e);
            }
        }
    }

    @Override
    public IRepository getRepository() {
        return super.getRepository();
    }
}
