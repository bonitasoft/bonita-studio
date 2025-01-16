/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.AbstractApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.validator.ModelNamespaceValidator;
import org.bonitasoft.studio.common.model.validator.XMLModelCompatibilityValidator;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ApplicationRepositoryStore extends AbstractRepositoryStore<ApplicationFileStore> {

    private static final String XML_EXTENSION = "xml";

    private static final String APPLICATION_DESCRIPTOR_NAMESPACE = ModelVersion.CURRENT_APPLICATION_DESCRIPTOR_NAMESPACE;
    private static final List<String> LEGACY_APPLICATION_DESCRIPTOR_NAMESPACES = List.of(
            ModelVersion.APPLICATION_DESCRIPTOR_NAMESPACE_PREFIX + "1.0");

    private final ApplicationNodeContainerConverter applicationNodeContainerConverter = new ApplicationNodeContainerConverter();

    public ApplicationNodeContainerConverter getConverter() {
        return applicationNodeContainerConverter;
    }

    @Override
    public String getName() {
        return "applications";
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public ApplicationFileStore createRepositoryFileStore(String fileName) {
        return new ApplicationFileStore(fileName, this);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return Set.of(XML_EXTENSION);
    }

    public Stream<AbstractApplicationNode> findByProfile(String profile) {
        return getChildren().stream()
                .map(toApplicationNodeContainer())
                .filter(Objects::nonNull)
                .flatMap(container -> container.getAllApplications().stream())
                .filter(appNode -> Objects.equals(appNode.getProfile(), profile));
    }

    private Function<? super ApplicationFileStore, ? extends ApplicationNodeContainer> toApplicationNodeContainer() {
        return t -> {
            try {
                return t.getContent();
            } catch (final ReadFileStoreException e) {
                return null;
            }
        };
    }

    @Override
    public ApplicationFileStore getChild(String fileName, boolean force) {
        if (fileName != null && fileName.endsWith(".xml")) {
            return super.getChild(fileName, force);
        }
        return null;
    }

    public Optional<ApplicationFileStore> findFileStoreByToken(String token) {
        return getChildren().stream()
                .filter(withToken(token))
                .findFirst();
    }

    private Predicate<? super ApplicationFileStore> withToken(String token) {
        return fStore -> {
            try {
                return fStore.getContent().getAllApplications().stream()
                        .anyMatch(node -> Objects.equals(node.getToken(), token));
            } catch (final ReadFileStoreException e) {
                return false;
            }
        };
    }

    @Override
    protected ApplicationFileStore doImportInputStream(String fileName, InputStream inputStream) {
        var fileStore = super.doImportInputStream(fileName, inputStream);
        if (fileStore != null) {
            var report = fileStore.getMigrationReport();
            doMigrateFileStore(fileStore, report);
        }
        return fileStore;
    }

    private void doMigrateFileStore(ApplicationFileStore fileStore, MigrationReport report) {
        try {
            BonitaStudioLog.info(String.format("Migrating %s...", fileStore.getName()));
            migrateNamespace(fileStore, report);
            var applicationNodeContainer = fileStore.getContent();
            // only legacy applications can update theme and layout
            applicationNodeContainer.getApplications().forEach(app -> updateBonitaTheme(app, report));
            applicationNodeContainer.getApplications().forEach(app -> updateBonitaLayout(app, report));
            fileStore.save(applicationNodeContainer);
            BonitaStudioLog.info(String.format("%s migration completed.", fileStore.getName()));
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    /**
     * Migrate the namespace if needed.
     * 
     * @param fileStore the file store holding the application descriptor
     * @param report the migration report
     * @throws ReadFileStoreException exception while migrating the file
     */
    private synchronized void migrateNamespace(ApplicationFileStore fileStore, MigrationReport report)
            throws ReadFileStoreException {
        var resource = fileStore.getResource();
        final Document doc;
        try (var stream = resource.getContents()) {
            // force migration to new application model version
            var builder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder();
            doc = builder.parse(stream);
        } catch (IOException | CoreException | SAXException | ParserConfigurationException e) {
            throw new ReadFileStoreException(e.getMessage(), e);
        }
        var root = doc.getDocumentElement();
        String currentNamespace = root.getAttribute("xmlns");
        if (APPLICATION_DESCRIPTOR_NAMESPACE.equals(currentNamespace)) {
            // that's already the correct namespace, no migration needed
        } else if (LEGACY_APPLICATION_DESCRIPTOR_NAMESPACES.contains(currentNamespace)) {
            // the migration is retro-compatible for now, we just need to update the namespace
            root.setAttribute("xmlns", APPLICATION_DESCRIPTOR_NAMESPACE);
            DOMSource source = new DOMSource(doc);
            try (var out = Files.newOutputStream(resource.getLocation().toFile().toPath())) {
                Transformer transformer = TransformerFactory.newDefaultInstance().newTransformer();
                StreamResult result = new StreamResult(out);
                transformer.transform(source, result);
                resource.refreshLocal(0, new NullProgressMonitor());
                BonitaStudioLog.info(String.format("%s namespace has been updated to %s", fileStore.getName(), APPLICATION_DESCRIPTOR_NAMESPACE));
            } catch (final IOException | CoreException | TransformerException e) {
                throw new ReadFileStoreException(e.getMessage(), e);
            }
            // update the migration report
            report.updated(
                    String.format("%s application descriptor has been migrated to the latest schema version.",
                            resource.getName()));
        } else {
            // incorrect namespace.
            var msg = String.format(org.bonitasoft.studio.common.Messages.incompatibleModelVersion,
                    fileStore.getName());
            throw new ReadFileStoreException(msg);
        }

    }

    @Override
    public MigrationReport migrate(IProgressMonitor monitor)
            throws CoreException, MigrationException {
        var report = super.migrate(monitor);
        for (ApplicationFileStore fileStore : getChildren()) {
            doMigrateFileStore(fileStore, report);
        }
        return report;
    }

    private void updateBonitaTheme(ApplicationNode application, MigrationReport report) {
        if (Objects.equals(application.getTheme(), "custompage_bonitadefaulttheme")
                || Objects.equals(application.getTheme(), "custompage_bootstrapdefaulttheme")) {
            application.setTheme("custompage_themeBonita");
            BonitaStudioLog.info(String.format("%s application theme has been updated to Bonita default theme.", application.getToken()));
            report.updated(String.format("%s application theme has been updated to Bonita default theme.",
                    application.getToken()));
        }
    }

    private void updateBonitaLayout(ApplicationNode application, MigrationReport report) {
        if (Objects.equals(application.getLayout(), "custompage_defaultlayout")) {
            application.setLayout("custompage_layoutBonita");
            BonitaStudioLog.info(String.format("%s application layout has been updated to Bonita default layout.", application.getToken()));
            report.updated(String.format("%s application layout has been updated to Bonita default layout.",
                    application.getToken()));
        }
    }

    @Override
    public IStatus validate(String filename, InputStream inputStream) {
        if (filename != null && filename.endsWith("." + XML_EXTENSION)) {
            return new XMLModelCompatibilityValidator(new ModelNamespaceValidator(
                    ModelVersion.CURRENT_APPLICATION_DESCRIPTOR_NAMESPACE,
                    String.format(org.bonitasoft.studio.common.Messages.incompatibleModelVersion, filename),
                    String.format(org.bonitasoft.studio.common.Messages.migrationWillBreakRetroCompatibility,
                            filename),
                    LEGACY_APPLICATION_DESCRIPTOR_NAMESPACES))
                            .validate(inputStream);
        }
        return super.validate(filename, inputStream);
    }
}
