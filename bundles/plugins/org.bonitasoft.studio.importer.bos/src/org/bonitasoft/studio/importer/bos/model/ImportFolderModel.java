package org.bonitasoft.studio.importer.bos.model;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class ImportFolderModel extends AbstractFolderModel {

    public ImportFolderModel(String folderPath, AbstractFolderModel parent) {
        super(folderPath, parent);
    }

    @Override
    public Image getImage() {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
    }

}
