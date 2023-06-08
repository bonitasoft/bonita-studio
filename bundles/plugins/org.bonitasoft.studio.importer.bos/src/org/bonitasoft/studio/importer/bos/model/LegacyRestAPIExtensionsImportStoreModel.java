package org.bonitasoft.studio.importer.bos.model;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;

public class LegacyRestAPIExtensionsImportStoreModel extends ImportStoreModel {

    public LegacyRestAPIExtensionsImportStoreModel(String folderPath,
            IRepositoryStore<IRepositoryFileStore> repositoryStore) {
        super(folderPath, repositoryStore);
    }
    
}
