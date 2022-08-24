/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.la.application.core;

import java.io.File;
import java.io.IOException;

import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.operation.DeployCustomPageOperation;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.maven.operation.BuildCustomPageOperation;
import org.bonitasoft.studio.theme.ThemeFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class DeployThemeOperation extends DeployCustomPageOperation {

    private ThemeFileStore themeFileStore;

    public DeployThemeOperation(PageAPI pageApi, HttpClientFactory httpClientFactory, ThemeFileStore themeFileStore) {
        super(pageApi, httpClientFactory);
        this.themeFileStore = themeFileStore;
    }

    @Override
    protected File getArchiveFile(IProgressMonitor monitor) {
        try {
            return themeFileStore.getArchiveFile();
        } catch (IOException e) {
            try {
                BuildCustomPageOperation buildOperation = themeFileStore.newBuildOperation();
                buildOperation.run(monitor);
                return themeFileStore.getArchiveFile();
            } catch (CoreException | ReadFileStoreException | IOException e1) {
                throw new RuntimeException(
                        String.format("No theme archive found for %s.", themeFileStore.getPageId()), e);
            }
        }
    }

    @Override
    protected String getCustomPageId() {
        return themeFileStore.getPageId();
    }

    @Override
    protected String getCustomPageLabel() {
        try {
            return themeFileStore.getContent().getDisplayName();
        } catch (ReadFileStoreException e) {
            return themeFileStore.getDisplayName();
        }
    }

    @Override
    protected String taskName() {
        return String.format(Messages.deployingTheme, themeFileStore.getDisplayName());
    }

    @Override
    protected String getCustomPageType() {
        return "theme";
    }

}
