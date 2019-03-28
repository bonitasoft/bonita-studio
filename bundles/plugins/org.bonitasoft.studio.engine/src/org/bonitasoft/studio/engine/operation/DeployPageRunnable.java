/**
 * Copyright (C) 2017 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.operation;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;

import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.studio.designer.core.bar.CustomPageBarResourceFactory;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.i18n.Messages;

import com.google.common.io.Files;

public class DeployPageRunnable extends DeployCustomPageOperation {

    private final WebPageFileStore pageFileStore;
    private final CustomPageBarResourceFactory customPageBarResourceFactory;

    public DeployPageRunnable(PageAPI pageApi,
            HttpClientFactory httpClientFactory,
            CustomPageBarResourceFactory customPageBarResourceFactory,
            WebPageFileStore pageFileStore) {
        super(pageApi, httpClientFactory);
        this.pageFileStore = requireNonNull(pageFileStore);
        this.customPageBarResourceFactory = requireNonNull(customPageBarResourceFactory);
    }

    @Override
    protected File getArchiveFile() {
        File tmpFile = null;
        try {
            tmpFile = File.createTempFile(getCustomPageId(), ".zip");
            Files.write(customPageBarResourceFactory.export(pageFileStore.getId()), tmpFile);
            return tmpFile;
        } catch (final IOException e) {
            if (tmpFile != null) {
                tmpFile.delete();
            }
            throw new RuntimeException("Failed to export page from UI Designer", e);
        }
    }

    @Override
    protected String getCustomPageId() {
        return "custompage_" + pageFileStore.getCustomPageName();
    }

    @Override
    protected String taskName() {
        return String.format(Messages.deployingPage, pageFileStore.getDisplayName());
    }

    @Override
    protected String getCustomPageLabel() {
        return pageFileStore.getDisplayName();
    }

}
