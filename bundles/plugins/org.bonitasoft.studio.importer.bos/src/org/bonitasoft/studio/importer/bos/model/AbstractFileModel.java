package org.bonitasoft.studio.importer.bos.model;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.swt.graphics.Image;

public abstract class AbstractFileModel extends AbstractImportModel implements IPresentable {

    private ConflictStatus status = ConflictStatus.NONE;
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

    public ConflictStatus getStatus() {
        return status;
    }

    public void setImportAction(ImportAction importAction) {
        this.importAction = importAction;
    }

    public void setStatus(ConflictStatus status) {
        this.status = status;
    }

    public boolean isConflicting() {
        return status == ConflictStatus.CONFLICTING;
    }

    public void setToOpen(boolean toOpen) {
        this.toOpen = toOpen;
    }

    public boolean shouldOpen() {
        return toOpen;
    }

    @Override
    public String getText() {
        return getFileName();
    }

    @Override
    public Image getImage() {
        final Optional<IRepositoryStore<IRepositoryFileStore>> repositoryStore = getParentRepositoryStore();
        return repositoryStore.map(store -> {
            final IRepositoryFileStore fileStore = store.createRepositoryFileStore(getFileName());
            return fileStore != null ? fileStore.getIcon() : store.getIcon();
        }).orElse(null);
    }

}
