package org.bonitasoft.studio.importer.bos.model;

import org.bonitasoft.studio.common.model.ConflictStatus;

public class ImportFileModel extends AbstractFileModel {

    public ImportFileModel(String filePath, AbstractFolderModel parent) {
        super(filePath, parent);
    }

    public ImportFileModel(String filePath, ImportStoreModel parent, ConflictStatus status) {
        super(filePath, parent, status);
    }
}
