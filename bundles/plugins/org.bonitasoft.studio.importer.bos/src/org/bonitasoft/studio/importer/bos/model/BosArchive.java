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
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class BosArchive extends ZipFile {

    private static final String MANIFEST_ENTRY = "/MANIFEST";
    private static final String VERSION = "version";
    private static final String TO_OPEN = "toOpen";
    private static final Properties FALLBACK_PROPERTIES = new Properties();
    static {
        FALLBACK_PROPERTIES.put(VERSION, "6.0.0");
    }

    private boolean openAll = false;

    public BosArchive(File archiveFile)
            throws IOException {
        super(archiveFile);
    }

    public ImportArchiveModel toImportModel(Repository repository) throws IOException {
        final IStatus validationStatus = validate();
        if (!validationStatus.isOK()) {
            throw new IOException(Messages.unvalidBossArchive);
        }
        final Set<String> resourcesToOpen = getResourcesToOpen();

        final ImportArchiveModel archiveModel = new ImportArchiveModel(this);
        stream().filter(e -> !e.isDirectory()).forEach(e -> parseEntry(e, archiveModel, repository, resourcesToOpen));
        return archiveModel;
    }

    private void parseEntry(ZipEntry entry, ImportArchiveModel archiveModel, IRepository repository,
            Set<String> resourcesToOpen) {

        final String entryName = entry.getName();
        final List<String> segments = Splitter.on('/').splitToList(entryName);
        segments.stream()
                .filter(segment -> repository.getRepositoryStoreByName(segment).isPresent())
                .forEach(segment -> handleSegment(archiveModel, segment, segments, repository, resourcesToOpen));
    }

    private void handleSegment(ImportArchiveModel archiveModel, String segment, final List<String> segments,
            IRepository repository, Set<String> resourcesToOpen) {
        final List<String> parentSegments = segments.subList(0, 2);
        final ImportStoreModel store = new ImportStoreModel(Joiner.on('/').join(parentSegments),
                (IRepositoryStore<IRepositoryFileStore>) repository.getRepositoryStoreByName(segment).get());

        parseFolder(archiveModel.addStore(store), segments.subList(2, segments.size()), parentSegments, resourcesToOpen,
                true);
    }

    private void parseFolder(AbstractFolderModel store, List<String> segments, List<String> parentSegments,
            Set<String> resourcesToOpen, boolean directStoreChild) {
        if (segments.size() == 1
                && directStoreChild) { // File
            final ImportFileStoreModel file = new ImportFileStoreModel(Joiner.on('/').join(concat(parentSegments, segments)),
                    store);
            file.setToOpen(openAll || resourcesToOpen.contains(file.getFileName()));
            store.addFile(file);
        } else if (segments.size() == 1 && !directStoreChild) {
            store.addFile(new ImportFileModel(Joiner.on('/').join(concat(parentSegments, segments)), store));
        } else if (segments.size() > 1 && directStoreChild) { // Folder
            final Iterable<String> folderParentSegments = concat(parentSegments, segments.subList(0, 1));
            final ImportFolderFileStoreModel folder = new ImportFolderFileStoreModel(
                    Joiner.on('/').join(folderParentSegments), store);
            parseFolder(store.addFolder(folder), segments.subList(1, segments.size()),
                    Lists.newArrayList(folderParentSegments),
                    resourcesToOpen, false);
        } else if (segments.size() > 1 && !directStoreChild) {
            final Iterable<String> folderParentSegments = concat(parentSegments, segments.subList(0, 1));
            final ImportFolderModel folder = new ImportFolderModel(
                    Joiner.on('/').join(concat(parentSegments, segments.subList(0, 1))), store);
            parseFolder(store.addFolder(folder), segments.subList(1, segments.size()),
                    Lists.newArrayList(folderParentSegments),
                    resourcesToOpen, false);
        }
    }

    protected IStatus validate() {
        try {
            final MultiStatus status = new MultiStatus(BosArchiveImporterPlugin.PLUGIN_ID, 0, "", null);
            status.add(validateZipStructure());
            status.add(validateArchiveCompatibility());
            return status;
        } catch (final IOException e) {
            return new Status(IStatus.ERROR, BosArchiveImporterPlugin.PLUGIN_ID,
                    String.format("Failed to create a zip file from %s", toString()), e);
        }
    }

    private IStatus validateArchiveCompatibility() throws IOException {
        final Properties manifest = readManifest();
        String version = manifest.getProperty(VERSION);
        if (version.contains("-")) {
            version = version.split("-")[0];
        }
        if (!ProductVersion.canBeImported(version)) {
            return ValidationStatus
                    .error(Messages.bind(Messages.incompatibleProductVersion, ProductVersion.CURRENT_VERSION, version));
        }
        return ValidationStatus.ok();
    }

    private Properties readManifest() throws IOException {
        return stream()
                .filter(entry -> entry.getName().endsWith(MANIFEST_ENTRY))
                .findFirst()
                .flatMap(this::loadManifestProperties)
                .orElse(FALLBACK_PROPERTIES);
    }

    private Optional<Properties> loadManifestProperties(ZipEntry manifestEntry) {
        final Properties properties = new Properties();
        try (InputStream inputStream = getInputStream(manifestEntry)) {
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
        if (stream().noneMatch(this::matchRepositoryFormat)) {
            return ValidationStatus.error("Not a valid BOS archive structure");
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
}
