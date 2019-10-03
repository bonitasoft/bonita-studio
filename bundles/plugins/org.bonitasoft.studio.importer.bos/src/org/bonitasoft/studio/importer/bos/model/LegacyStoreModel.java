package org.bonitasoft.studio.importer.bos.model;

import java.util.Optional;

import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;

public class LegacyStoreModel extends ImportStoreModel {

    public LegacyStoreModel(String folderPath) {
        super(folderPath, null);
    }

    @Override
    protected Optional<IRepositoryStore<IRepositoryFileStore>> getParentRepositoryStore() {
        return Optional.empty();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.bos.model.AbstractFolderModel#getImage()
     */
    @Override
    public Image getImage() {
        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.bos.model.AbstractImportModel#isConflicting()
     */
    @Override
    public boolean isConflicting() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.importer.bos.model.AbstractImportModel#getStatus()
     */
    @Override
    public ConflictStatus getStatus() {
        return ConflictStatus.NONE;
    }

}
