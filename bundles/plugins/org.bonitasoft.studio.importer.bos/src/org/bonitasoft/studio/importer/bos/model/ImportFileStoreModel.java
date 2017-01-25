package org.bonitasoft.studio.importer.bos.model;

import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class ImportFileStoreModel extends AbstractFileModel implements ImportableUnit {

    public ImportFileStoreModel(String filePath, AbstractFolderModel parent) {
        super(filePath, parent);
    }

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
                                        importAction == ImportAction.OVERWRITE),
                                monitor);
                    } catch (final CoreException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElse(null);
    }

}
