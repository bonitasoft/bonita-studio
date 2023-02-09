package org.bonitasoft.studio.importer.bos.model;

import java.util.stream.Stream;

public class ImportFolderModel extends AbstractFolderModel {

    public ImportFolderModel(String folderPath, AbstractFolderModel parent) {
        super(folderPath, parent);
    }

    @Override
    public Stream<ImportableUnit> importableUnits() {
        return Stream.empty();
    }

}
