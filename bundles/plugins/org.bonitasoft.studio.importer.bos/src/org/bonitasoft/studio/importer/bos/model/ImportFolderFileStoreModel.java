package org.bonitasoft.studio.importer.bos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.graphics.Image;

public class ImportFolderFileStoreModel extends AbstractFolderModel implements ImportableUnit {

    public ImportFolderFileStoreModel(String folderPath, AbstractFolderModel parent) {
        super(folderPath, parent);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.bos.model.IPresentable#getImage()
     */
    @Override
    public Image getImage() {
        return getParentRepositoryStore().map(store -> {
            final IRepositoryFileStore fileStore = store.createRepositoryFileStore(getFolderName());
            return fileStore != null ? fileStore.getIcon() : store.getIcon();
        }).orElse(null);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.bos.model.ImportableUnit#doImport(java.util.zip.ZipFile, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public IRepositoryFileStore doImport(ZipFile archive, IProgressMonitor monitor) {
        return getParentRepositoryStore()
                .map(store -> {
                    try {
                        return store.importArchiveData(getFolderName(),
                                buildImportArchiveData(this, archive),
                                monitor);
                    } catch (final CoreException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElse(null);
    }

    private List<ImportArchiveData> buildImportArchiveData(AbstractFolderModel folder, ZipFile archive) {
        final List<ImportArchiveData> importArchiveData = new ArrayList<>();
        folder.getFiles().stream()
                .forEach(file -> { // fileStores
                    importArchiveData.add(new ImportArchiveData(archive, archive.getEntry(file.getPath()),
                            file.getImportAction() == ImportAction.OVERWRITE));
                });
        folder.getFolders().stream()
                .forEach(f -> {
                    buildImportArchiveData(f, archive).stream().forEach(importArchiveData::add);
                });
        return importArchiveData;
    }

    @Override
    public String getName() {
        return getText();
    }

}
