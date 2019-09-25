package org.bonitasoft.studio.importer.bos.model;

import static java.util.Objects.requireNonNull;

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.swt.graphics.Image;

public abstract class AbstractFileModel extends AbstractImportModel implements IPresentable {

    protected ImportAction importAction = ImportAction.OVERWRITE;
    private final String fileName;
    private boolean toOpen;

    public AbstractFileModel(String filePath, AbstractFolderModel parent) {
        super(filePath, parent);
        requireNonNull(parent);
        final String[] segments = filePath.split("/");
        this.fileName = segments[segments.length - 1];
    }

    public AbstractFileModel(String filePath, AbstractFolderModel parent, ConflictStatus status) {
        this(filePath, parent);
        this.status = status;
    }

    public ImportAction getImportAction() {
        return importAction;
    }

    public String getFileName() {
        return fileName;
    }

    public void setImportAction(ImportAction importAction) {
        this.importAction = importAction;
    }

    public void setToOpen(boolean toOpen) {
        this.toOpen = toOpen;
    }

    public boolean shouldOpen() {
        return toOpen;
    }

    @Override
    public String getText() {
        return displayName.orElse(getFileName());
    }

    @Override
    public Image getImage() {
        return getParentRepositoryStore().map(IRepositoryStore::getIcon).orElse(null);
    }

    public void resetStatus() {
        this.status = ConflictStatus.NONE;
    }

    public boolean shouldBeImported() {
        return importAction != ImportAction.KEEP;
    }

}
