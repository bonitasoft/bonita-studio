/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.extension;

import java.util.List;
import java.util.Map;

import org.bonitasoft.plugin.analyze.report.model.Theme;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.extension.update.preview.ChangePreview;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.i18n.Messages;

public class ThemeRemovedChange implements ChangePreview, ApplicationChange {

    private Theme theme;
    private Map<ApplicationFileStore, List<String>> appsToRefactor;

    public ThemeRemovedChange(Theme theme, Map<ApplicationFileStore, List<String>> appsToRefactor) {
        this.theme = theme;
        this.appsToRefactor = appsToRefactor;
    }

    @Override
    public void apply() {
        for (var entry : appsToRefactor.entrySet()) {
            var fileStore = entry.getKey();
            var tokens = entry.getValue();
            if (tokens != null) {
                try {
                    var appNodeContainer = fileStore.getContent();
                    appNodeContainer.getApplications().stream()
                            .filter(app -> entry.getValue().contains(app.getToken()))
                            .forEach(app -> app.setTheme(null));
                    fileStore.save(appNodeContainer);
                } catch (ReadFileStoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }

    }

    @Override
    public String getDescription() {
        return String.format(Messages.themeRemovedPreviewMessage, theme.getDisplayName(), theme.getName());
    }

    @Override
    public List<ChangePreview> getDetails() {
        return List.of();
    }

    @Override
    public Kind getKind() {
        return Kind.REMOVE;
    }

    @Override
    public boolean hasBreakingChanges() {
        return false;
    }

    @Override
    public ChangePreview getParent() {
        return null;
    }

    @Override
    public boolean showInPreviewDialog() {
        return true;
    }

}
