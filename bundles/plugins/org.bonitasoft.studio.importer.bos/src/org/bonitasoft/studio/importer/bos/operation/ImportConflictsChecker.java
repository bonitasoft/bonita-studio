package org.bonitasoft.studio.importer.bos.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.eclipse.core.runtime.IProgressMonitor;

public class ImportConflictsChecker {

    private static final String LEGACY_BDM_FILENAME = "bdm.zip";
    private final AbstractRepository currentRepository;

    public ImportConflictsChecker(AbstractRepository currentRepository) {
        Objects.requireNonNull(currentRepository);
        this.currentRepository = currentRepository;
    }

    public ImportArchiveModel checkConflicts(BosArchive bosArchive, IProgressMonitor monitor) throws IOException {
        final ImportArchiveModel importModel = bosArchive.toImportModel(currentRepository, monitor);
        performAnalysis(bosArchive, importModel, monitor);
        return importModel;
    }

    private void performAnalysis(BosArchive bosArchive, ImportArchiveModel importModel, IProgressMonitor monitor) {
        monitor.beginTask(Messages.analyseBosArchive, importModel.getStores().size());
        importModel.getStores().stream().forEach(store -> {
            compareStore(store, bosArchive);
            monitor.worked(1);
        });
        monitor.done();
    }

    private void compareStore(AbstractFolderModel importedStore, BosArchive bosArchive) {
        currentRepository.getAllStores().stream()
                .filter(aStoreInCurrentRepo -> Objects.equals(aStoreInCurrentRepo.getName(), importedStore.getFolderName()))
                .findFirst()
                .ifPresent(storeInCurrentRepo -> {
                    if (Objects.equals(storeInCurrentRepo.getName(),
                            DatabaseConnectorPropertiesRepositoryStore.STORE_NAME)) {
                        compareDatabaseConnectorProperties(storeInCurrentRepo, importedStore, bosArchive);
                    } else {
                        final File file = storeInCurrentRepo.getResource().getLocation().toFile();
                        File[] files = file.listFiles();
                        if(files == null) {
                            files = new File[0];
                        }
                        compareFolders(bosArchive, files, importedStore.getFolders());
                        compareFiles(bosArchive, files, importedStore.getFiles());
                    }
                });
    }

    // We can't use checksum to compare .properties file because there is always a comment with the creation date at the top of the .properties file -> always conflicting. 
    protected void compareDatabaseConnectorProperties(IRepositoryStore<? extends IRepositoryFileStore> storeInCurrentRepo,
            AbstractFolderModel importedStore, BosArchive bosArchive) {
        List<DatabaseConnectorPropertiesFileStore> currentPropertiesFiles = (List<DatabaseConnectorPropertiesFileStore>) storeInCurrentRepo
                .getChildren();
        importedStore.getFiles().stream()
                .forEach(importedPropertiesFile -> {
                    findConflinctingDatabaseConnectorPropertiesInCurrentRepo(importedPropertiesFile, currentPropertiesFiles,
                            bosArchive).ifPresent(currentPropertiesFile -> {
                                compareConflinctingDatabaseConnectorPropertiesfiles(bosArchive, importedPropertiesFile,
                                        currentPropertiesFile);
                            });
                });
    }

    private Optional<DatabaseConnectorPropertiesFileStore> findConflinctingDatabaseConnectorPropertiesInCurrentRepo(
            AbstractFileModel importedPropertiesFile,
            List<DatabaseConnectorPropertiesFileStore> currentPropertiesFiles, BosArchive bosArchive) {
        return currentPropertiesFiles.stream()
                .filter(currentPropertiesFile -> Objects.equals(currentPropertiesFile.getName(),
                        importedPropertiesFile.getFileName()))
                .findFirst();
    }

    private void compareConflinctingDatabaseConnectorPropertiesfiles(BosArchive bosArchive,
            AbstractFileModel importedPropertiesFile, DatabaseConnectorPropertiesFileStore currentPropertiesFile) {
        ZipEntry entry = bosArchive.getEntry(importedPropertiesFile.getPath());
        try (InputStream inputStream = bosArchive.getZipFile().getInputStream(entry)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            try {
                importedPropertiesFile.setStatus(properties.equals(currentPropertiesFile.getContent())
                        ? ConflictStatus.SAME_CONTENT
                        : ConflictStatus.CONFLICTING);
            } catch (ReadFileStoreException e) {
                importedPropertiesFile.setStatus(ConflictStatus.CONFLICTING);
            }
            importedPropertiesFile.setImportAction(importedPropertiesFile.isConflicting()
                    ? ImportAction.KEEP
                    : ImportAction.OVERWRITE);
        } catch (IOException e) {
            BonitaStudioLog.error(
                    "An error occured while comparing databaseConnectorProperties during import", e);
            importedPropertiesFile.setStatus(ConflictStatus.CONFLICTING);
        } ;
    }

    private void compareFolders(BosArchive bosArchive, File[] foldersFromCurrentRepo,
            List<AbstractFolderModel> foldersFromArchive) {
        foldersFromArchive.stream()
                .forEach(folderFromArchive -> Stream.of(foldersFromCurrentRepo)
                        .filter(File::isDirectory)
                        .forEach(folderInCurrentRepo -> {
                            if (Objects.equals(folderInCurrentRepo.getName(), folderFromArchive.getFolderName())) {
                                File[] files = folderInCurrentRepo.listFiles();
                                if(files == null) {
                                    files = new File[0];
                                }
                                compareFolders(bosArchive, files, folderFromArchive.getFolders());
                                compareFiles(bosArchive, files, folderFromArchive.getFiles());
                            }
                        }));
    }

    private void compareFiles(BosArchive bosArchive, File[] filesFromCurrentRepo, List<AbstractFileModel> filesFromArchive) {
        filesFromArchive.stream().forEach(fileFromArchive -> Stream.of(filesFromCurrentRepo)
                .filter(File::isFile)
                .filter(conflictingFileName(fileFromArchive))
                .findFirst()
                .ifPresent(fileFromCurrentRepo -> compareChecksums(bosArchive, fileFromArchive, fileFromCurrentRepo)));

    }

    private void compareChecksums(BosArchive bosArchive, AbstractFileModel fileFromArchive, File fileFromCurrentRepo) {
        if (differentChecksum(fileFromArchive, fileFromCurrentRepo, bosArchive)) {
            fileFromArchive.setStatus(ConflictStatus.CONFLICTING);
        } else {
            fileFromArchive.setImportAction(ImportAction.KEEP);
            fileFromArchive.setStatus(ConflictStatus.SAME_CONTENT);
        }
    }

    //Handle the case of legacy bdm name in archive
    private Predicate<File> conflictingFileName(AbstractFileModel fileFromArchive) {
        return fileFromArchive.getParent()
                .map(AbstractFolderModel::getFolderName)
                .filter("bdm"::equals)
                .flatMap(filename -> Optional
                        .ofNullable(sameFileName(fileFromArchive).or(isLegacyBDM(fileFromArchive.getFileName()))))
                .orElse(sameFileName(fileFromArchive));
    }

    private Predicate<File> isLegacyBDM(String fileName) {
        return fileInCurrentRepo -> Objects.equals(fileInCurrentRepo.getName(),
                fileName) || fileName.equals(LEGACY_BDM_FILENAME);
    }

    private Predicate<File> sameFileName(AbstractFileModel fileFromArchive) {
        return fileInCurrentRepo -> Objects.equals(fileInCurrentRepo.getName(),
                fileFromArchive.getFileName());
    }

    private boolean differentChecksum(AbstractFileModel fileFromArchive, File fileInCurrentRepo,
            BosArchive bosArchive) {
        return bosArchive.getEntry(fileFromArchive.getPath())
                .getCrc() != computeChecksum(fileInCurrentRepo);
    }

    private long computeChecksum(File file) {
        try (CheckedInputStream contentStream = new CheckedInputStream(new FileInputStream(file), new CRC32())) {
            final byte[] readBuffer = new byte[128];
            int read = contentStream.read(readBuffer);
            while (read != -1) {
                read = contentStream.read(readBuffer);
            }
            return contentStream.getChecksum().getValue();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

}
