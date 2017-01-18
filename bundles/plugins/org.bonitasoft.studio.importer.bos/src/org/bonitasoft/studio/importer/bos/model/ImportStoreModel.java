package org.bonitasoft.studio.importer.bos.model;

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

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.bos.model.AbstractFolderModel#getImage()
     */
    @Override
    public Image getImage() {
        return repositoryStore.getIcon();
    }

    public Stream<ImportableUnit> importableUnits() {
        return Stream.concat(getFiles().stream().filter(ImportableUnit.class::isInstance).map(ImportableUnit.class::cast),
                getFolders().stream().filter(ImportableUnit.class::isInstance).map(ImportableUnit.class::cast));
    }
}
