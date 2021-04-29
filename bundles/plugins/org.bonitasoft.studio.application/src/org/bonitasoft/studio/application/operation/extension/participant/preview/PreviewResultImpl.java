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
package org.bonitasoft.studio.application.operation.extension.participant.preview;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Shell;

public class PreviewResultImpl implements PreviewResult {

    private List<ChangePreview> changes = new ArrayList<>();

    @Override
    public boolean hasChanges() {
        return !changes.isEmpty();
    }

    @Override
    public boolean canProceed() {
        // If no breaking changes found
        return changes.stream().anyMatch(c -> !c.hasBreakingChanges());
    }

    @Override
    public List<ChangePreview> getChanges() {
        return changes;
    }

    public void addChange(ChangePreview change) {
        changes.add(change);
    }

    public int open(Shell shell) {
       return new PreviewConfirmationDialog(shell, this).open();
    }

}
