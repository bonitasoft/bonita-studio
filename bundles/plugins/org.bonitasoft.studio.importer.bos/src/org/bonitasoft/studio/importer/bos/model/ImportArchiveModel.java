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
        return Collections.unmodifiableList(stores);
    }

    public boolean isConflicting() {
        return getStores().stream().anyMatch(ImportStoreModel::isConflicting);
    }

    public BosArchive getBosArchive() {
        return bosArchive;
    }
}
