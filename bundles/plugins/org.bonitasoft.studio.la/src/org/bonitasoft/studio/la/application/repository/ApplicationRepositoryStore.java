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

import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
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
import org.eclipse.emf.edapt.migration.MigrationException;

public class ApplicationRepositoryStore extends AbstractRepositoryStore<ApplicationFileStore> {

    private static final String XML_EXTENSION = "xml";

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

    public Stream<ApplicationNode> findByProfile(String profile) {
        return getChildren().stream()
                .map(toApplicationNodeContainer())
                .filter(Objects::nonNull)
                .flatMap(container -> container.getApplications().stream())
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
        if(fileName != null && fileName.endsWith(".xml")) {
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
                return fStore.getContent().getApplications().stream()
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
            var report = MigrationReport.emptyReport();
            doMigrateFileStore(fileStore, report);
            fileStore.setMigrationReport(report);
        }
        return fileStore;
    }

    private void doMigrateFileStore(ApplicationFileStore fileStore, MigrationReport report) {
        try {
            var applicationNodeContainer = fileStore.getContent();
            applicationNodeContainer.getApplications().forEach(app -> updateBonitaTheme(app, report));
            applicationNodeContainer.getApplications().forEach(app -> updateBonitaLayout(app, report));
            fileStore.save(applicationNodeContainer);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(e);
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
            report.updated(String.format("%s application theme has been updated to Bonita default theme.",
                    application.getToken()));
        }
    }

    private void updateBonitaLayout(ApplicationNode application, MigrationReport report) {
        if (Objects.equals(application.getLayout(), "custompage_defaultlayout")) {
            application.setLayout("custompage_layoutBonita");
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
                            filename)))
                                    .validate(inputStream);
        }
        return super.validate(filename, inputStream);
    }
}
