package org.bonitasoft.studio.importer.bos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.graphics.Image;

public class ImportFolderFileStoreModel extends AbstractFolderModel implements ImportableUnit {

    public ImportFolderFileStoreModel(String folderPath, AbstractFolderModel parent) {
        super(folderPath, parent);
    }

    @Override
    public Image getImage() {
        return getParentRepositoryStore().map(IRepositoryStore::getIcon).orElse(null);
    }


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
