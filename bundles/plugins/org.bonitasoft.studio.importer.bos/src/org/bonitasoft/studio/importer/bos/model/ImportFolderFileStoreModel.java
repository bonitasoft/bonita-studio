package org.bonitasoft.studio.importer.bos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class ImportFolderFileStoreModel extends AbstractFolderModel implements ImportableUnit {

    public ImportFolderFileStoreModel(String folderPath, AbstractFolderModel parent) {
        super(folderPath, parent);
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
        return displayName.orElse(getFolderName());
    }

    @Override
    public Stream<ImportableUnit> importableUnits() {
        return Stream.empty();
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (adapter.isAssignableFrom(IRepositoryStore.class)) {
            if (getParent().isPresent()) {
                var folderModel = getParent().get();
                if (folderModel instanceof LegacyRestAPIExtensionsImportStoreModel
                        || folderModel instanceof LegacyThemesImportStoreModel) {
                    return null;
                }
            }
            return (T) getParentRepositoryStore().orElse(null);
        }
        return super.getAdapter(adapter);
    }

}
