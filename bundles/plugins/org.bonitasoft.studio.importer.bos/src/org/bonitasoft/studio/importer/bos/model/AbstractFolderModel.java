package org.bonitasoft.studio.importer.bos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFolderModel extends AbstractImportModel implements IPresentable {

    private ConflictStatus status = ConflictStatus.NONE;
    private final List<AbstractFolderModel> folders = new ArrayList<>();
    private final List<AbstractFileModel> files = new ArrayList<>();
    private final String folderName;

    public AbstractFolderModel(String path, AbstractFolderModel parent) {
        super(path, parent);
        final String[] segments = path.split("/");
        folderName = segments[segments.length - 1];
    }

    public String getFolderName() {
        return folderName;
    }

    public AbstractFolderModel addFolder(AbstractFolderModel folder) {
        return folders.stream()
                .filter(f -> Objects.equals(f.getPath(), folder.getPath()))
                .findFirst()
                .orElseGet(() -> {
                    this.folders.add(folder);
                    return folder;
                });
    }

    public void addFile(AbstractFileModel file) {
        this.files.add(file);
    }

    public List<AbstractFileModel> getFiles() {
        return files;
    }

    public List<AbstractFolderModel> getFolders() {
        return folders;
    }

    public boolean isConflicting() {
        return status == ConflictStatus.CONFLICTING;
    }

    public void setStatus(ConflictStatus status) {
        this.status = status;
        if (parent.isPresent() && isConflicting() && !parent.get().isConflicting()) {
            parent.get().setStatus(status);
        }
    }

    public ConflictStatus getStatus() {
        return status;
    }

    @Override
    public String getText() {
        return getFolderName();
    }

}
