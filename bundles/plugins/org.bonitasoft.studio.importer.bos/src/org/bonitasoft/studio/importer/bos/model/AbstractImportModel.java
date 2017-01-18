package org.bonitasoft.studio.importer.bos.model;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;

public abstract class AbstractImportModel {

    protected AbstractFolderModel parent;
    protected String path;

    public AbstractImportModel(String path, AbstractFolderModel parent) {
        requireNonNull(path);
        this.path = path;
        this.parent = parent;
    }

    public AbstractFolderModel getParent() {
        return parent;
    }

    public String getPath() {
        return path;
    }

    protected Optional<IRepositoryStore<IRepositoryFileStore>> getParentRepositoryStore() {
        AbstractFolderModel folder = getParent();
        while (folder != null && !(folder instanceof ImportStoreModel)) {
            folder = folder.getParent();
        }
        return folder instanceof ImportStoreModel ? Optional.of(((ImportStoreModel) folder).getRepositoryStore())
                : Optional.empty();
    }

}
