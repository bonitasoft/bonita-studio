package org.bonitasoft.studio.importer.bos.model;

import java.util.Objects;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class ImportFileStoreModel extends AbstractFileModel implements ImportableUnit {

    public ImportFileStoreModel(String filePath, AbstractFolderModel parent) {
        super(filePath, parent);
    }

    // for test purpose
    public ImportFileStoreModel(String filePath, ImportStoreModel parent, ConflictStatus status) {
        super(filePath, parent, status);
    }

    @Override
    public IRepositoryFileStore doImport(ZipFile archive, IProgressMonitor monitor) {
        return getParentRepositoryStore()
                .map(store -> {
                    try {
                        return store.importArchiveData(
                                new ImportArchiveData(archive, archive.getEntry(path),
                                        getImportAction() == ImportAction.OVERWRITE),
                                monitor);
                    } catch (final CoreException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElse(null);
    }

    public boolean isStoreResource() {
        int lastIndexOf = getName().lastIndexOf(".");
        if (lastIndexOf != -1 && getName().length() > lastIndexOf + 1 && getParentRepositoryStore().isPresent()) {
            String fileExtension = getName().substring(lastIndexOf + 1, getName().length());
            return getParentRepositoryStore().get().getCompatibleExtensions().stream()
                    .anyMatch(supportedExtension -> Objects.equals(fileExtension.toLowerCase(),
                            supportedExtension.toLowerCase()));
        }
        return false;
    }

    @Override
    public String getName() {
        return displayName.orElse(getFileName());
    }

}
