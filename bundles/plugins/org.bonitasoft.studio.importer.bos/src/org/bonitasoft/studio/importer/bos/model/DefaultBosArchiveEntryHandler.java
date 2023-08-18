/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bos.model;

import static com.google.common.collect.Iterables.concat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.migration.step.RemoveLegacyFolderStep;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.smartImport.ISmartImportable;
import org.bonitasoft.studio.diagram.custom.repository.DiagramLegacyFormsValidator;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class DefaultBosArchiveEntryHandler implements BosArchiveEntryHandler {

    private File archiveFile;
    private final DiagramLegacyFormsValidator legacyFormsValidator = new DiagramLegacyFormsValidator();
    private ObjectMapper objectMapper = new ObjectMapper();

    public DefaultBosArchiveEntryHandler(File archiveFile) {
        this.archiveFile = archiveFile;
    }

    @Override
    public void parseEntry(ZipEntry entry, ImportArchiveModel archiveModel, IRepository repository,
            Set<String> resourcesToOpen) {
        final List<String> segments = splitEntry(entry);
        segments.stream()
                .filter(segment -> selectSegment(repository, segment))
                .forEach(segment -> handleSegment(archiveModel, segment, segments, repository, resourcesToOpen));
    }

    protected List<String> splitEntry(ZipEntry entry) {
        final String entryName = entry.getName();
        return Stream.of(entryName.split("/")).collect(Collectors.toList());
    }

    protected String toEntryPath(final Iterable<String> parentSegments) {
        return Joiner.on('/').join(parentSegments);
    }

    private boolean selectSegment(IRepository repository, String segment) {
        return isRepositoryStoreParent(repository, segment)
                || isLegacyFormRepo(segment)
                || isLegacyRestAPIRepo(segment)
                || isLegacyThemeRepo(segment)
                || isReadme(segment)
                || isSrc(segment);
    }

    private boolean isLegacyThemeRepo(String segment) {
        return "themes".equals(segment);
    }

    private boolean isLegacyRestAPIRepo(String segment) {
        return "restAPIExtensions".equals(segment);
    }

    private boolean isSrc(String segment) {
        return "src".equals(segment);
    }

    private boolean isReadme(String segment) {
        return "README.adoc".equals(segment);
    }

    private boolean isRepositoryStoreParent(IRepository repository, String segment) {
        return repository.getRepositoryStoreByName(segment).isPresent();
    }

    private boolean isLegacyFormRepo(String segment) {
        return RemoveLegacyFolderStep.legacyRepositories().contains(segment);
    }

    private void handleSegment(ImportArchiveModel archiveModel, String segment, final List<String> segments,
            IRepository repository, Set<String> resourcesToOpen) {
        int storeDepth = getStoreDepth(segments);
        final List<String> parentSegments = segments.subList(0, storeDepth);
        Optional<IRepositoryStore<? extends IRepositoryFileStore>> repositoryStoreByName;
        if(isLegacyThemeRepo(segment) || isLegacyRestAPIRepo(segment)) {
            repositoryStoreByName = repository.getRepositoryStoreByName("extensions");
        }else {
            repositoryStoreByName = repository.getRepositoryStoreByName(segment);
        }
        if (repositoryStoreByName.isPresent()) {
            var store = new ImportStoreModel(toEntryPath(parentSegments),
                    (IRepositoryStore<IRepositoryFileStore>) repositoryStoreByName.get());
            if(isLegacyThemeRepo(segment)) {
                store = new LegacyThemesImportStoreModel(toEntryPath(parentSegments), (IRepositoryStore<IRepositoryFileStore>) repositoryStoreByName.get());
            }
            if(isLegacyRestAPIRepo(segment)) {
                store = new LegacyRestAPIExtensionsImportStoreModel(toEntryPath(parentSegments), (IRepositoryStore<IRepositoryFileStore>) repositoryStoreByName.get());
            }
           

            parseFolder(archiveModel.addStore(store), segments.subList(storeDepth, segments.size()), parentSegments,
                    resourcesToOpen,
                    true, archiveModel, repository);
            if (store.getChildren().length == 0) {
                archiveModel.removeStore(store);
            }
        } else if (segments.size() >= storeDepth && segments.get(1).equals("src")) {
            parseFolder(archiveModel.addStore(new SourceFolderStoreModel(toEntryPath(parentSegments))),
                    segments.subList(2, segments.size()),
                    parentSegments,
                    resourcesToOpen,
                    true,
                    archiveModel,
                    repository);
        } else if (RemoveLegacyFolderStep.legacyRepositories().contains(segment)) {
            archiveModel.addStore(new LegacyStoreModel(toEntryPath(parentSegments)));
        } else if (segments.size() == storeDepth) { // Only root files should be added as RootFileModel
            archiveModel.addStore(new RootFileModel(segment, toEntryPath(parentSegments)));
        }
    }

    protected int getStoreDepth(List<String> segments) {
        return 2;
    }

    private void parseFolder(AbstractFolderModel store, List<String> segments, List<String> parentSegments,
            Set<String> resourcesToOpen, boolean directStoreChild,
            ImportArchiveModel archiveModel,
            IRepository repository) {
        if (segments.size() == 1
                && directStoreChild) { // File
            String filePath = toEntryPath(concat(parentSegments, segments));

            ImportFileStoreModel file = createImportModelFileStore(store, segments.get(0), filePath);
            if (!isALegacyProfile(store, file) && !isLegacySoapXSD(store, file)) {
                if (store.getFolderName().equals(DiagramRepositoryStore.STORE_NAME)
                        && !file.getFileName().endsWith(".proc")) {
                    return;
                }
                file.setToOpen(resourcesToOpen.contains(file.getFileName()));
                file.getParentRepositoryStore().ifPresent(repositoryStore -> {
                    IStatus validationStatus = validateFile(file, repositoryStore);
                    if (isDiagram(file)) {
                        MultiStatus multiStatus = new MultiStatus(BosArchive.class, 0, null);
                        multiStatus.add(validationStatus);
                        try (ZipFile archive = getZipFile();
                                InputStream is = archive.getInputStream(archive.getEntry(file.getPath()));) {
                            multiStatus.add(legacyFormsValidator.validate(is));
                        } catch (IOException e) {
                            ((MultiStatus) validationStatus).add(
                                    ValidationStatus.error(String.format("Failed to read %s", file.getFileName())));
                        }
                        validationStatus = multiStatus;
                    }
                    file.setValidationStatus(validationStatus);
                    if (validationStatus.getSeverity() == IStatus.ERROR) {
                        archiveModel.setValidationStatus(validationStatus);
                    }
                });
                store.addFile(file);
            }
        } else if (segments.size() == 1 && !directStoreChild) {
            final ImportFileModel file = createFileModel(store, segments, parentSegments, repository);
            if (shouldReadNameInJSON(store, file)) {
                final String name = extractNameFromJSON(file);
                file.setDisplayName(String.format("%s (%s)", name, file.getFileName()));
                file.getParent().ifPresent(parent -> parent
                        .setDisplayName(String.format("%s (%s)", name, parent.getFolderName())));
                file.getParentRepositoryStore().ifPresent(repositoryStore -> {
                    IStatus validationStatus = validateFile(file, repositoryStore);
                    file.setValidationStatus(validationStatus);
                    if (validationStatus.getSeverity() == IStatus.ERROR) {
                        archiveModel.setValidationStatus(validationStatus);
                    }
                });
            }
            store.addFile(file);
        } else if (segments.size() > 1 && directStoreChild) { // Folder
            final Iterable<String> folderParentSegments = concat(parentSegments, segments.subList(0, 1));

            final ImportFolderFileStoreModel folder = new ImportFolderFileStoreModel(
                    toEntryPath(folderParentSegments), store);
            parseFolder(store.addFolder(folder), segments.subList(1, segments.size()),
                    Lists.newArrayList(folderParentSegments),
                    resourcesToOpen, false, archiveModel, repository);
        } else if (segments.size() > 1 && !directStoreChild) {
            final Iterable<String> folderParentSegments = concat(parentSegments, segments.subList(0, 1));
            final ImportFolderModel folder = new ImportFolderModel(
                    toEntryPath(concat(parentSegments, segments.subList(0, 1))), store);
            parseFolder(store.addFolder(folder), segments.subList(1, segments.size()),
                    Lists.newArrayList(folderParentSegments),
                    resourcesToOpen, false, archiveModel, repository);
        }
    }

    public ImportFileModel createFileModel(AbstractFolderModel parentStore, List<String> segments,
            List<String> parentSegments, IRepository repository) {
        String filePath = toEntryPath(concat(parentSegments, segments));
        if (isResourceFile(filePath)) {
            return new ImportResourceFileModel(filePath, parentStore, repository.getProject());
        }
        return new ImportFileModel(filePath, parentStore);
    }

    private ImportFileStoreModel createImportModelFileStore(AbstractFolderModel parentStore,
            String fileName,
            String filePath) {
        Optional<IRepositoryFileStore> fileStore = parentStore.getParentRepositoryStore()
                .map(repositoryStore -> repositoryStore.getChild(fileName, true));
        if (fileStore.isPresent() && (fileStore.get() instanceof ISmartImportable)) {
            return new SmartImportFileStoreModel(new BosArchive(archiveFile), filePath, parentStore,
                    (ISmartImportable) fileStore.get());
        }
        return new ImportFileStoreModel(filePath, parentStore);
    }

    private boolean isResourceFile(String filePath) {
        String[] segments = filePath.split("/");
        return segments.length > 1 && segments[1].equals("src");
    }

    public IStatus validateFile(AbstractFileModel file, IRepositoryStore<IRepositoryFileStore> repositoryStore) {
        try (ZipFile archive = getZipFile();
                InputStream is = archive.getInputStream(archive.getEntry(file.getPath()));) {
            return repositoryStore.validate(file.getFileName(), is);
        } catch (IOException e) {
            return ValidationStatus.error(String.format("Failed to read %s", file.getFileName()));
        }
    }

    private boolean isDiagram(ImportFileStoreModel file) {
        return file.getFileName().endsWith(".proc");
    }

    private boolean isLegacySoapXSD(AbstractFolderModel store, ImportFileStoreModel file) {
        return (file.getFileName().equals("soap-envelope.xsd") || file.getFileName().equals("soap-encoding.xsd"))
                && "xsd".equals(store.getFolderName());
    }

    protected boolean isALegacyProfile(AbstractFolderModel store, final ImportFileStoreModel file) {
        return file.getFileName().endsWith(".profile") && "profiles".equals(store.getFolderName());
    }

    private boolean shouldReadNameInJSON(AbstractFolderModel store, final ImportFileModel file) {
        return isAWebRepositoryStore(store)
                && file.getFileName().startsWith(file.getParent().get().getFolderName())
                && file.getFileName().endsWith(".json");
    }

    private boolean isAWebRepositoryStore(AbstractFolderModel store) {
        return store.getParentRepositoryStore().filter(repoWithName("web_page")
                .or(repoWithName("web_fragments"))
                .or(repoWithName("web_widgets")))
                .isPresent();
    }

    private Predicate<IRepositoryStore<IRepositoryFileStore>> repoWithName(String name) {
        return repo -> name.equals(repo.getName());
    }

    private String extractNameFromJSON(AbstractFileModel file) {
        try (ZipFile zipFile = new ZipFile(archiveFile);
                InputStream is = zipFile.getInputStream(zipFile.getEntry(file.getPath()))) {
            return (String) objectMapper.readValue(is, new TypeReference<Map<String, Object>>() {
            })
                    .get("name");
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
        return file.getFileName();
    }

    private ZipFile getZipFile() throws IOException {
        return new ZipFile(archiveFile);
    }
}
