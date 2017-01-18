package org.bonitasoft.studio.importer.bos.operation;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.ConflictStatus;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.eclipse.core.runtime.IProgressMonitor;

public class ImportConflictsChecker {

    private Repository currentRepository;

    public ImportConflictsChecker(Repository currentRepository) {
        Objects.requireNonNull(currentRepository);
        this.currentRepository = currentRepository;
    }

    public ImportArchiveModel checkConflicts(BosArchive bosArchive, IProgressMonitor monitor) throws IOException {
        ImportArchiveModel importModel = bosArchive.toImportModel(currentRepository);
        performAnalysis(importModel);
        return importModel;
    }

    private void performAnalysis(ImportArchiveModel importModel) {
        importModel.getStores().stream().forEach(this::compareStore);
    }

    private void compareStore(AbstractFolderModel importedStore) {

        currentRepository.getAllStores().stream()
                .filter(aStoreInCurrentRepo -> Objects.equals(aStoreInCurrentRepo.getName(), importedStore.getFolderName()))
                .findFirst()
                .ifPresent(storeInCurrentRepo -> {
                    File file = storeInCurrentRepo.getResource().getLocation().toFile();
                    compareFolders(file.listFiles(), importedStore.getFolders());
                    compareFiles(file.listFiles(), importedStore.getFiles());
                });
    }

    private void compareFolders(File[] foldersFromCurrentRepo, List<AbstractFolderModel> foldersFromArchive) {
        foldersFromArchive.stream()
                .forEach(folderFromArchive -> Stream.<File> of(foldersFromCurrentRepo)
                        .filter(f -> f.isDirectory())
                        .forEach(folderInCurrentRepo -> {
                            if (Objects.equals(folderInCurrentRepo.getName(), folderFromArchive.getFolderName())) {
                                compareFolders(folderInCurrentRepo.listFiles(), folderFromArchive.getFolders());
                                compareFiles(folderInCurrentRepo.listFiles(), folderFromArchive.getFiles());
                            }
                        }));
    }

    private void compareFiles(File[] filesFromCurrentRepo, List<AbstractFileModel> filesFromArchive) {
        filesFromArchive.stream()
                .filter(fileFromArchive -> Stream.<File> of(filesFromCurrentRepo)
                        .filter(f -> f.isFile()).anyMatch(fileInCurrentRepo -> {
                            String s1 = fileInCurrentRepo.getName(), s2 = fileFromArchive.getFileName();
                            boolean a = Objects.equals(fileInCurrentRepo.getName(),
                                    fileFromArchive.getFileName());
                            return a;
                        }))
                .forEach(this::setConflict);
    }

    private void setConflict(AbstractFileModel conflictingFile) {
        conflictingFile.setStatus(ConflictStatus.CONFLICTING);
    }
}
