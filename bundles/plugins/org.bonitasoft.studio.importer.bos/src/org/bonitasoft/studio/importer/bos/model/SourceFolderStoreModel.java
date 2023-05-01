/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.importer.bos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SourceFolderStoreModel extends ImportFolderModel {

    public SourceFolderStoreModel(String path) {
        super(path, null);
    }

    @Override
    public Stream<ImportableUnit> importableUnits() {
        List<ImportableUnit> units = new ArrayList<>();
        retrieveImportableUnits(getFolders(), units);
        return units.stream();
    }

    private void retrieveImportableUnits(List<AbstractFolderModel> folders, List<ImportableUnit> units) {
        for (AbstractFolderModel folder : folders) {
            folder.getFiles().stream().filter(AbstractFileModel::shouldBeImported)
                    .filter(ImportableUnit.class::isInstance)
                    .map(ImportableUnit.class::cast)
                    .forEach(units::add);
            if(!folder.getFolders().isEmpty()) {
                retrieveImportableUnits(folder.getFolders(), units);
            }
        }
    }

}
