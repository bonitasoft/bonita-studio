/**
 * Copyright (C) 2016 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bos.wizard;

import static java.util.Objects.requireNonNull;

import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Event;

public class ImportActionSelector {

    private ImportArchiveModel archiveModel;
    private final Viewer viewer;

    public ImportActionSelector(Viewer viewer) {
        requireNonNull(viewer);
        this.viewer = viewer;
    }

    public void setArchiveModel(ImportArchiveModel archiveModel) {
        this.archiveModel = archiveModel;
    }

    public void selectKeepAll(Event e) {
        requireNonNull(archiveModel);
        applyImportAction(ImportAction.KEEP);
    }

    public void selectOverwriteAll(Event e) {
        requireNonNull(archiveModel);
        applyImportAction(ImportAction.OVERWRITE);
    }

    private void applyImportAction(ImportAction importAction) {
        archiveModel.getStores().stream()
                .filter(folder -> folder.isConflicting())
                .forEach(folder -> applyImportAction(folder, importAction));
        viewer.refresh();
    }

    private void applyImportAction(AbstractFolderModel folder, ImportAction importAction) {
        folder.getFiles().stream()
                .filter(AbstractFileModel::isConflicting)
                .forEach(f -> {
                    f.setImportAction(importAction);
                });
        folder.getFolders().stream()
                .filter(f -> folder.isConflicting())
                .forEach(f -> applyImportAction(f, importAction));
    }

}
