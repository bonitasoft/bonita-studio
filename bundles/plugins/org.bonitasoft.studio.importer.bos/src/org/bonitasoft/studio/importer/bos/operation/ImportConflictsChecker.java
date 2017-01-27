package org.bonitasoft.studio.importer.bos.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import java.util.zip.ZipEntry;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.ConflictStatus;
import org.bonitasoft.studio.importer.bos.model.ImportAction;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.tools.AbstractTool.Input;

public class ImportConflictsChecker {

    private final Repository currentRepository;

    public ImportConflictsChecker(Repository currentRepository) {
        Objects.requireNonNull(currentRepository);
        this.currentRepository = currentRepository;
    }

    public ImportArchiveModel checkConflicts(BosArchive bosArchive, IProgressMonitor monitor) throws IOException {
        monitor.beginTask(Messages.archiveAnalysis, IProgressMonitor.UNKNOWN);
        final ImportArchiveModel importModel = bosArchive.toImportModel(currentRepository);
        performAnalysis(bosArchive, importModel);
        return importModel;
    }

    private void performAnalysis(BosArchive bosArchive, ImportArchiveModel importModel) {
        importModel.getStores().stream().forEach(store -> compareStore(store, bosArchive));
    }

    private void compareStore(AbstractFolderModel importedStore, BosArchive bosArchive) {
        currentRepository.getAllStores().stream()
                .filter(aStoreInCurrentRepo -> Objects.equals(aStoreInCurrentRepo.getName(), importedStore.getFolderName()))
                .findFirst()
                .ifPresent(storeInCurrentRepo -> {
                    final File file = storeInCurrentRepo.getResource().getLocation().toFile();
                    compareFolders(bosArchive, file.listFiles(), importedStore.getFolders());
                    compareFiles(bosArchive, file.listFiles(), importedStore.getFiles());
                });
    }

    private void compareFolders(BosArchive bosArchive, File[] foldersFromCurrentRepo,
            List<AbstractFolderModel> foldersFromArchive) {
        foldersFromArchive.stream()
                .forEach(folderFromArchive -> Stream.of(foldersFromCurrentRepo)
                        .filter(File::isDirectory)
                        .forEach(folderInCurrentRepo -> {
                            if (Objects.equals(folderInCurrentRepo.getName(), folderFromArchive.getFolderName())) {
                                compareFolders(bosArchive, folderInCurrentRepo.listFiles(), folderFromArchive.getFolders());
                                compareFiles(bosArchive, folderInCurrentRepo.listFiles(), folderFromArchive.getFiles());
                            }
                        }));
    }

    private void compareFiles(BosArchive bosArchive, File[] filesFromCurrentRepo, List<AbstractFileModel> filesFromArchive) {
        filesFromArchive.stream().forEach(fileFromArchive -> Stream.of(filesFromCurrentRepo)
                .filter(File::isFile)
                .filter(sameFileName(fileFromArchive))
                .findFirst()
                .ifPresent(fileFromCurrentRepo -> compareChecksums(bosArchive, fileFromArchive, fileFromCurrentRepo)));

    }

    private void compareChecksums(BosArchive bosArchive, AbstractFileModel fileFromArchive, File fileFromCurrentRepo) {
        if (differentChecksum(fileFromArchive, fileFromCurrentRepo, bosArchive)) {
            fileFromArchive.setStatus(ConflictStatus.CONFLICTING);
        } else {
            fileFromArchive.setImportAction(ImportAction.KEEP);
        }
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
