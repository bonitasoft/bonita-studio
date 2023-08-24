package org.bonitasoft.studio.importer.bos.model;

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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

import com.google.common.base.Splitter;

public class BosArchive {

    private static final String MANIFEST_ENTRY = "/MANIFEST";
    private static final String VERSION = "version";
    private static final String TO_OPEN = "toOpen";
    private static final Properties FALLBACK_PROPERTIES = new Properties();
    
    static {
        FALLBACK_PROPERTIES.put(VERSION, "6.0.0");
    }

    private String bonitaVersion;
    private final File archiveFile;
    private MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
    private Model mavenProject;

    public BosArchive(File archiveFile) {
        this.archiveFile = archiveFile;
    }

    public File getArchiveFile() {
        return archiveFile;
    }

    public ImportArchiveModel toImportModel(IRepository repository, IProgressMonitor monitor)
            throws IOException {
        final IStatus validationStatus = validate();
        final ImportArchiveModel archiveModel = new ImportArchiveModel(this);
        archiveModel.setValidationStatus(validationStatus);
        if (!validationStatus.isOK()) {
            return archiveModel;
        }
        final Set<String> resourcesToOpen = getResourcesToOpen();
        var entryHandler = getBosArchiveEntryHandler();
        try (ZipFile zipFile = new ZipFile(archiveFile)) {
            int entryCount = (int) zipFile.stream().filter(entry -> !entry.isDirectory()).count();
            monitor.beginTask(org.bonitasoft.studio.importer.bos.i18n.Messages.parsingArchive,
                    entryCount);
            zipFile.stream()
                    .filter(entry -> !entry.isDirectory())
                    .forEach(entry -> {
                        entryHandler.parseEntry(entry, archiveModel, repository, resourcesToOpen);
                        monitor.worked(1);
                    });
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return archiveModel;
    }

  
    public BosArchiveEntryHandler getBosArchiveEntryHandler() {
        Model model = getRootMavenModel();
        if(model != null && !model.getModules().isEmpty()) {
            return new MultiModuleBosArchiveEntryHandler(archiveFile);
        }
        return new DefaultBosArchiveEntryHandler(archiveFile);
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
        var rootProject = getRootMavenModel();
        if (rootProject != null && ProjectMetadata.getBonitaRuntimeVersion(rootProject) != null) {
            bonitaVersion = ProjectMetadata.getBonitaRuntimeVersion(rootProject);
        } else {
            final Properties manifest = readManifest();
            String manifestVersion = manifest.getProperty(VERSION);
            DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion(manifestVersion);
            this.bonitaVersion = String.format("%s.%s.%s", defaultArtifactVersion.getMajorVersion(),
                    defaultArtifactVersion.getMinorVersion(), defaultArtifactVersion.getIncrementalVersion());
        }
        if (!canImport(this.bonitaVersion)) {
            return ValidationStatus
                    .error(NLS.bind(Messages.incompatibleProductVersion,
                            ProductVersion
                                    .toMinorVersionString(ProductVersion.minorVersion(ProductVersion.CURRENT_VERSION)),
                            ProductVersion.toMinorVersionString(ProductVersion.minorVersion(bonitaVersion))));
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
        return AbstractRepositoryStore.REPO_STORE_ORDER.keySet().stream()
                .anyMatch(
                        storeName -> Pattern.compile(String.format("^.*/%s/.*$", storeName)).matcher(entry.getName())
                                .find());
    }

    public String getBonitaVersion() {
        return bonitaVersion;
    }

    public ZipFile getZipFile() throws IOException {
        return new ZipFile(archiveFile);
    }

    public String getFileName() {
        return archiveFile.getName();
    }

    public List<InputStreamSupplier> loadJarInputStreamSuppliers() {
        try (var zipFile = getZipFile()) {
            return zipFile.stream()
                    .filter(entry -> entry.getName().matches("[^/]*/lib/.*.jar"))
                    .map(entry -> new ArchiveInputStreamSupplier(getArchiveFile(), entry))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
        return Collections.emptyList();
    }
    
    public Model getRootMavenModel() {
        try (ZipFile zipFile = new ZipFile(archiveFile)) {
            return zipFile.stream()
                .filter(entry -> entry.getName().matches("[^/]*/pom.xml"))
                .findFirst()
                .flatMap(this::loadMavenModel)
                .orElse(null);
          } catch (final IOException e) {
              throw new RuntimeException(e);
          }
    }

    public Model getMavenProject() {
        if (mavenProject == null) {
            try (ZipFile zipFile = new ZipFile(archiveFile)) {
              var rootProject = zipFile.stream()
                  .filter(entry -> entry.getName().matches("[^/]*/pom.xml"))
                  .findFirst()
                  .flatMap(this::loadMavenModel)
                  .orElse(null);
               Stream<ZipEntry> entries = (Stream<ZipEntry>) zipFile.stream();
               ZipEntry pomEntry = entries.filter(entry -> entry.getName().matches("[^/]*/app/pom.xml"))
                    .findFirst()
                    .orElse(null);
                if(pomEntry != null) {
                    mavenProject = loadMavenModel(pomEntry).orElse(null);
                }else {
                    mavenProject = rootProject;
                }
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
        return mavenProject;
    }

    private Optional<Model> loadMavenModel(ZipEntry pomEntry) {
        try (ZipFile zipFile = new ZipFile(archiveFile);
                InputStream inputStream = zipFile.getInputStream(pomEntry)) {
            return Optional.of(mavenXpp3Reader.read(inputStream));
        } catch (final IOException | XmlPullParserException e) {
            BonitaStudioLog.error("Failed to load pom file from archive", e);
            return Optional.empty();
        }
    }

}
