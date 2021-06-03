/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.maven.archetype.catalog.Archetype;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.CopyInputStream;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.PostMigrationOperationCollector;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.builder.validator.AbstractCustomPageValidator;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetype;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.builder.RestAPIBuilder;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.Groovy3MigrationStep;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.graphics.Image;

public class RestAPIExtensionRepositoryStore extends CustomPageProjectRepositoryStore<RestAPIExtensionFileStore> {

    public static final String STORE_NAME = "restAPIExtensions";

    private static final List<MavenModelMigration> MIGRATION_STEPS = List.of(new Groovy3MigrationStep());

    private MavenXpp3Reader mavenModelReader = new MavenXpp3Reader();
    private MavenXpp3Writer mavenModelWriter = new MavenXpp3Writer();

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.restApiExtensionRepositoryName;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("rest_api_icon7_16x16.png", RestAPIExtensionActivator.getDefault());
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
            try (CopyInputStream copyInputStream = new CopyInputStream(inputStream);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                Model model = mavenModelReader.read(copyInputStream.getCopy());
                boolean hasBeenMigrated = false;
                for (MavenModelMigration step : MIGRATION_STEPS) {
                    if (step.appliesTo(model)) {
                        step.migrate(model);
                        hasBeenMigrated = true;
                    }
                }
                if (hasBeenMigrated) {
                    mavenModelWriter.write(outputStream, model);
                    return new ByteArrayInputStream(outputStream.toByteArray());
                }
                return copyInputStream.getCopy();
            } catch (IOException | XmlPullParserException e) {
                throw new MigrationException(e);
            }
        }
        return super.handlePreImport(fileName, inputStream);
    }

    @Override
    public void migrate(PostMigrationOperationCollector postMigrationOperationCollector, IProgressMonitor monitor) throws CoreException, MigrationException {
        List<RestAPIExtensionFileStore> filesToMigrate = getChildren().stream()
                .filter(fs -> !fs.isReadOnly())
                .filter(IRepositoryFileStore::canBeShared)
                .collect(Collectors.toList());

        for (RestAPIExtensionFileStore fStore : filesToMigrate) {
            migrate(fStore, monitor);
        }
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
}
