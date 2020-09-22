package org.bonitasoft.studio.importer.bos.model;

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Sets.newHashSet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.smartImport.ISmartImportable;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.json.JSONException;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;

public class BosArchive {

    private static final String MANIFEST_ENTRY = "/MANIFEST";
    private static final String VERSION = "version";
    private static final String TO_OPEN = "toOpen";
    private static final Properties FALLBACK_PROPERTIES = new Properties();
    private String version;

    static {
        FALLBACK_PROPERTIES.put(VERSION, "6.0.0");
    }

    private boolean openAll = false;
    private final File archiveFile;

    public BosArchive(File archiveFile) {
        this.archiveFile = archiveFile;
    }

    public ImportArchiveModel toImportModel(AbstractRepository repository, IProgressMonitor monitor) throws IOException {
        final IStatus validationStatus = validate();
        final ImportArchiveModel archiveModel = new ImportArchiveModel(this);
        archiveModel.setValidationStatus(validationStatus);
        if (!validationStatus.isOK()) {
            return archiveModel;
        }
        final Set<String> resourcesToOpen = getResourcesToOpen();
        try (ZipFile zipFile = new ZipFile(archiveFile)) {
            int entryCount = (int) zipFile.stream().filter(entry -> !entry.isDirectory()).count();
            monitor.beginTask(org.bonitasoft.studio.importer.bos.i18n.Messages.parsingArchive,
                    entryCount);
            zipFile.stream()
                    .filter(entry -> !entry.isDirectory())
                    .forEach(entry -> {
                        parseEntry(entry, archiveModel, repository, resourcesToOpen);
                        monitor.worked(1);
                    });
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return archiveModel;
    }

    private void parseEntry(ZipEntry entry, ImportArchiveModel archiveModel, IRepository repository,
            Set<String> resourcesToOpen) {
        final String entryName = entry.getName();
        final List<String> segments = Splitter.on('/').splitToList(entryName);
        segments.stream()
                .filter(segment -> repository.getRepositoryStoreByName(segment).isPresent()
                        || isLegacyFormRepo(segment) || "README.adoc".equals(segment))
                .forEach(segment -> handleSegment(archiveModel, segment, segments, repository, resourcesToOpen));
    }

    private boolean isLegacyFormRepo(String segment) {
        return AbstractRepository.LEGACY_REPOSITORIES.contains(segment);
    }

    private void handleSegment(ImportArchiveModel archiveModel, String segment, final List<String> segments,
            IRepository repository, Set<String> resourcesToOpen) {
        final List<String> parentSegments = segments.subList(0, 2);
        Optional<IRepositoryStore<? extends IRepositoryFileStore>> repositoryStoreByName = repository
                .getRepositoryStoreByName(segment);
        if (repositoryStoreByName.isPresent()) {
            final ImportStoreModel store = new ImportStoreModel(Joiner.on('/').join(parentSegments),
                    (IRepositoryStore<IRepositoryFileStore>) repositoryStoreByName.get());

            parseFolder(archiveModel.addStore(store), segments.subList(2, segments.size()), parentSegments,
                    resourcesToOpen,
                    true, archiveModel);
            if (store.getChildren().length == 0) {
                archiveModel.removeStore(store);
            }
        } else if (AbstractRepository.LEGACY_REPOSITORIES.contains(segment)) {
            archiveModel.addStore(new LegacyStoreModel(Joiner.on('/').join(parentSegments)));
        } else {
            archiveModel.addStore(new RootFileModel(segment, Joiner.on('/').join(parentSegments)));
        }
    }

    private void parseFolder(AbstractFolderModel store, List<String> segments, List<String> parentSegments,
            Set<String> resourcesToOpen, boolean directStoreChild, ImportArchiveModel archiveModel) {
        if (segments.size() == 1
                && directStoreChild) { // File
            String filePath = Joiner.on('/').join(concat(parentSegments, segments));
            ImportFileStoreModel file = createImportModelFileStore(store, segments.get(0), filePath);
            if (!isALegacyProfile(store, file) && !isLegacySoapXSD(store, file)) {
                file.setToOpen(openAll || resourcesToOpen.contains(file.getFileName()));
                file.getParentRepositoryStore().ifPresent(repositoryStore -> {
                    IStatus validationStatus = validateFile(file, repositoryStore);
                    file.setValidationStatus(validationStatus);
                    if (validationStatus.getSeverity() == IStatus.ERROR) {
                        archiveModel.setValidationStatus(validationStatus);
                    }
                });
                store.addFile(file);
            }
        } else if (segments.size() == 1 && !directStoreChild) {
            final ImportFileModel file = new ImportFileModel(Joiner.on('/').join(concat(parentSegments, segments)),
                    store);
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
                    Joiner.on('/').join(folderParentSegments), store);
            parseFolder(store.addFolder(folder), segments.subList(1, segments.size()),
                    Lists.newArrayList(folderParentSegments),
                    resourcesToOpen, false, archiveModel);
        } else if (segments.size() > 1 && !directStoreChild) {
            final Iterable<String> folderParentSegments = concat(parentSegments, segments.subList(0, 1));
            final ImportFolderModel folder = new ImportFolderModel(
                    Joiner.on('/').join(concat(parentSegments, segments.subList(0, 1))), store);
            parseFolder(store.addFolder(folder), segments.subList(1, segments.size()),
                    Lists.newArrayList(folderParentSegments),
                    resourcesToOpen, false, archiveModel);
        }
    }

    private ImportFileStoreModel createImportModelFileStore(AbstractFolderModel parentStore, String fileName,
            String filePath) {
        Optional<IRepositoryFileStore> fileStore = parentStore.getParentRepositoryStore()
                .map(repositoryStore -> repositoryStore.getChild(fileName, true));
        if (fileStore.isPresent() && (fileStore.get() instanceof ISmartImportable)) {
            return new SmartImportFileStoreModel(this, filePath, parentStore, (ISmartImportable) fileStore.get());
        }
        return new ImportFileStoreModel(filePath, parentStore);
    }

    public IStatus validateFile(AbstractFileModel file, IRepositoryStore<IRepositoryFileStore> repositoryStore) {
        try (ZipFile archive = getZipFile();
                InputStream is = archive.getInputStream(archive.getEntry(file.getPath()));) {
            return repositoryStore.validate(file.getFileName(), is);
        } catch (IOException e) {
            return ValidationStatus.error(String.format("Failed to read %s", file.getFileName()));
        }
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
            return new org.json.JSONObject(new String(ByteStreams.toByteArray(is), Charsets.UTF_8))
                    .getString("name");
        } catch (final IOException | JSONException e) {
            BonitaStudioLog.error(e);
        }
        return file.getFileName();
    }

    public ZipEntry getEntry(String name) {
        try (ZipFile zipFile = new ZipFile(archiveFile)) {
            return zipFile.getEntry(name);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected IStatus validate() {
        try {
            IStatus status = validateZipStructure();
            if (status.isOK()) {
                status = validateArchiveCompatibility();
            }
            return status;
        } catch (final IOException e) {
            return new Status(IStatus.ERROR, BosArchiveImporterPlugin.PLUGIN_ID,
                    String.format("Failed to create a zip file from %s", toString()), e);
        }
    }

    private IStatus validateArchiveCompatibility() throws IOException {
        final Properties manifest = readManifest();
        String version = manifest.getProperty(VERSION);
        DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion(version);
        this.version = String.format("%s.%s.%s", defaultArtifactVersion.getMajorVersion(),
                defaultArtifactVersion.getMinorVersion(), defaultArtifactVersion.getIncrementalVersion());
        if (!canImport(this.version)) {
            return ValidationStatus
                    .error(Messages.bind(Messages.incompatibleProductVersion,
                            ProductVersion
                                    .toMinorVersionString(ProductVersion.minorVersion(ProductVersion.CURRENT_VERSION)),
                            ProductVersion.toMinorVersionString(ProductVersion.minorVersion(version))));
        }
        return ValidationStatus.ok();
    }

    public boolean canImport(String version) {
        return ProductVersion.canBeImported(version);
    }

    private Properties readManifest() throws IOException {
        try (ZipFile zipFile = new ZipFile(archiveFile)) {
            return zipFile.stream()
                    .filter(entry -> entry.getName().endsWith(MANIFEST_ENTRY))
                    .findFirst()
                    .flatMap(this::loadManifestProperties)
                    .orElse(FALLBACK_PROPERTIES);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Properties> loadManifestProperties(ZipEntry manifestEntry) {
        final Properties properties = new Properties();
        try (ZipFile zipFile = new ZipFile(archiveFile);
                InputStream inputStream = zipFile.getInputStream(manifestEntry)) {
            properties.load(inputStream);
        } catch (final IOException e) {
            BonitaStudioLog.error("Failed to load manifest fron archive", e);
            return Optional.of(FALLBACK_PROPERTIES);
        }
        return Optional.of(properties);
    }

    public Set<String> getResourcesToOpen() throws IOException {
        final Properties manifestProperties = readManifest();
        final String toOpen = manifestProperties.getProperty(TO_OPEN);
        if (toOpen != null) {
            return newHashSet(Splitter.on(",").split(toOpen));
        }
        openAll = true;
        return Collections.emptySet();
    }

    private IStatus validateZipStructure() {
        try (ZipFile zipFile = new ZipFile(archiveFile)) {
            if (zipFile.stream().noneMatch(this::matchRepositoryFormat)) {
                return ValidationStatus.error("Not a valid BOS archive structure");
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return ValidationStatus.ok();
    }

    private boolean matchRepositoryFormat(ZipEntry entry) {
        return allRepositoryStores().stream().map(IRepositoryStore::getName)
                .anyMatch(
                        storeName -> Pattern.compile(String.format("^.*/%s/.*$", storeName)).matcher(entry.getName())
                                .find());
    }

    public List<IRepositoryStore<? extends IRepositoryFileStore>> allRepositoryStores() {
        return RepositoryManager.getInstance().getCurrentRepository().getAllStores();
    }

    public String getVersion() {
        return version;
    }

    public ZipFile getZipFile() throws IOException {
        return new ZipFile(archiveFile);
    }

    public String getFileName() {
        return archiveFile.getName();
    }
}
