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
package org.bonitasoft.studio.common.repository.ui;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;

public class OverwriteFileFilter {

    private final String targetFolderPath;

    public OverwriteFileFilter(String targetFolderPath) {
        this.targetFolderPath = Objects.requireNonNull(targetFolderPath);
    }

    public List<IRepositoryFileStore> resolveConflicts(List<IRepositoryFileStore> selection) {
        return selection.stream()
                .filter(this::isConflicting)
                .collect(Collectors.toList());
    }

    /**
     * Ask the user if he wants to overwrite conflicting file. A conflicting file not overwritten is not kept by the filter.
     */
    private boolean isConflicting(IRepositoryFileStore fileStore) {
        final File target = new File(new File(targetFolderPath), fileStore.getName());
        if (target.exists()) {
            if (FileActionDialog.overwriteQuestion(fileStore.getName())) {
                PlatformUtil.delete(target, Repository.NULL_PROGRESS_MONITOR);
            } else {
                return false;
            }
        }
        return true;
    }

}
