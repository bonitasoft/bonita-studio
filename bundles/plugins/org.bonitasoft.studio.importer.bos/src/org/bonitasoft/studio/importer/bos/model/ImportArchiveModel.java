package org.bonitasoft.studio.importer.bos.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ImportArchiveModel {

    private final List<ImportStoreModel> stores = new ArrayList<>();
    private final BosArchive bosArchive;

    public ImportArchiveModel(BosArchive bosArchive) {
        this.bosArchive = bosArchive;
    }

    public ImportStoreModel addStore(ImportStoreModel store) {
        return stores.stream()
                .filter(s -> Objects.equals(s.getPath(), store.getPath()))
                .findFirst()
                .orElseGet(() -> {
                    this.stores.add(store);
                    return store;
                });
    }

    public List<ImportStoreModel> getStores() {
        Collections.sort(stores, (f1, f2) -> f1.getFolderName().compareTo(f2.getFolderName()));
        return Collections.unmodifiableList(stores);
    }

    public boolean isConflicting() {
        return stores.stream().anyMatch(ImportStoreModel::isConflicting);
    }

    public boolean sameContentAsTarget() {
        return stores.stream().allMatch(ImportStoreModel::hasSameContent);
    }

    public ConflictStatus getStatus() {
        if (isConflicting()) {
            return ConflictStatus.CONFLICTING;
        } else if (sameContentAsTarget()) {
            return ConflictStatus.SAME_CONTENT;
        }
        return ConflictStatus.NONE;
    }

    public BosArchive getBosArchive() {
        return bosArchive;
    }

    /**
     * set all the conflict status to none
     */
    public void resetStatus() {
        stores.stream().forEach(store -> store.resetStatus());
    }
}
