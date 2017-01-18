package org.bonitasoft.studio.importer.bos.model;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class ImportFileModel extends AbstractFileModel {

    public ImportFileModel(String filePath, AbstractFolderModel parent) {
        super(filePath, parent);
    }

    public ImportFileModel(String filePath, ImportStoreModel parent, ConflictStatus status) {
        super(filePath, parent, status);
    }

    @Override
    public Image getImage() {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
    }

}
