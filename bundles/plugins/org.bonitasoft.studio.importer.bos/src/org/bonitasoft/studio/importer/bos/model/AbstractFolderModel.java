package org.bonitasoft.studio.importer.bos.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractFolderModel extends AbstractImportModel implements IPresentable {

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
        if (!file.getValidationStatus().isOK()) {
            setValidationStatus(file.getValidationStatus());
        }
        this.files.add(file);
    }

    public List<AbstractFileModel> getFiles() {
        return files;
    }

    public List<AbstractFolderModel> getFolders() {
        return folders;
    }

    public Object[] getChildren() {
        return Stream.concat(getFolders().stream(),
                getFiles()
                        .stream()
                        .sorted(conflictingFirst()))
                .toArray();
    }

    private Comparator<? super AbstractFileModel> conflictingFirst() {
        return (f1, f2) -> {
            if (f1.isConflicting() && !f2.isConflicting()) {
                return -1;
            } else if (f2.isConflicting() && !f1.isConflicting()) {
                return 1;
            }
            return f1.getFileName().compareTo(f2.getFileName());
        };
    }

    @Override
    public String getText() {
        return displayName.orElse(getFolderName());
    }

    public void resetStatus() {
        this.status = ConflictStatus.NONE;
        getFolders().stream().forEach(AbstractFolderModel::resetStatus);
        getFiles().stream().forEach(AbstractFileModel::resetStatus);
    }

}
