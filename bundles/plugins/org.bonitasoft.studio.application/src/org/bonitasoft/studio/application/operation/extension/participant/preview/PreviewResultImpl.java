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

import org.bonitasoft.studio.common.repository.extension.update.preview.ChangePreview;
import org.bonitasoft.studio.common.repository.extension.update.preview.PreviewMessageProvider;
import org.bonitasoft.studio.common.repository.extension.update.preview.PreviewResult;
import org.eclipse.swt.widgets.Shell;

public class PreviewResultImpl implements PreviewResult {

    private List<ChangePreview> changes = new ArrayList<>();

    @Override
    public boolean shouldOpenPreviewDialog() {
        return changes.stream().anyMatch(ChangePreview::showInPreviewDialog);
    }

    @Override
    public boolean canProceed() {
        // If at least one change is not a breaking changes
        return changes.stream().anyMatch(c -> !c.hasBreakingChanges());
    }

    @Override
    public List<ChangePreview> getChanges() {
        return changes;
    }

    @Override
    public void addChange(ChangePreview change) {
        changes.add(change);
    }

    @Override
    public int open(Shell shell, PreviewMessageProvider messageProvider) {
        return new PreviewConfirmationDialog(shell, this, messageProvider).open();
    }

}
