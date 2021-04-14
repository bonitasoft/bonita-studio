package org.bonitasoft.studio.importer.bos.model;

import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.swt.graphics.Image;

public class ImportStoreModel extends AbstractFolderModel {

    private final IRepositoryStore<IRepositoryFileStore> repositoryStore;

    public ImportStoreModel(String folderPath, AbstractFolderModel parent,
            IRepositoryStore<IRepositoryFileStore> repositoryStore) {
        super(folderPath, parent);
        this.repositoryStore = repositoryStore;
    }

    public ImportStoreModel(String folderPath, IRepositoryStore<IRepositoryFileStore> repositoryStore) {
        super(folderPath, null);
        this.repositoryStore = repositoryStore;
    }

    public IRepositoryStore<IRepositoryFileStore> getRepositoryStore() {
        return repositoryStore;
    }

    @Override
    protected Optional<IRepositoryStore<IRepositoryFileStore>> getParentRepositoryStore() {
        return Optional.ofNullable(getRepositoryStore());
    }

    @Override
    public Image getImage() {
        return repositoryStore.getIcon();
    }

    public Stream<ImportableUnit> importableUnits() {
        return Stream.concat(importableFiles(), importableFolders());
    }

    private Stream<ImportableUnit> importableFolders() {
        return getFolders().stream()
                .filter(ImportableUnit.class::isInstance)
                .map(ImportableUnit.class::cast);
    }

    private Stream<ImportableUnit> importableFiles() {
        return getFiles().stream()
                .filter(AbstractFileModel::shouldBeImported)
                .filter(ImportableUnit.class::isInstance)
                .map(ImportableUnit.class::cast);
    }
}
