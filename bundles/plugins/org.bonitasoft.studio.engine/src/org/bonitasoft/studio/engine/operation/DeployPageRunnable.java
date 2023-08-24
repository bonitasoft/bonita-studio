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
import java.nio.file.Files;

import org.apache.http.HttpException;
import org.bonitasoft.bonita2bar.form.FormBuilder;
import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.engine.page.Page;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;

public class DeployPageRunnable extends DeployCustomPageOperation {

    private final WebPageFileStore pageFileStore;
    private final FormBuilder formBuilder;
    private File tmpFile;

    public DeployPageRunnable(PageAPI pageApi,
            HttpClientFactory httpClientFactory,
            FormBuilder formBuilder,
            WebPageFileStore pageFileStore) {
        super(pageApi, httpClientFactory);
        this.pageFileStore = requireNonNull(pageFileStore);
        this.formBuilder = requireNonNull(formBuilder);
    }

    @Override
    protected File getArchiveFile(IProgressMonitor monitor) {
        try {
            tmpFile = File.createTempFile(getCustomPageId(), ".zip");
            Files.write(tmpFile.toPath(), formBuilder.export(pageFileStore.getId()));
            return tmpFile;
        } catch (IOException e) {
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
        return String.format(Messages.deployingPage, pageLabel());
    }

    @Override
    protected String getCustomPageLabel() {
        return pageLabel();
    }

    private String pageLabel() {
        return IDisplayable.toDisplayName(pageFileStore).orElseGet(pageFileStore::getName);
    }

    @Override
    protected String getCustomPageType() {
        return pageFileStore.getType();
    }

    @Override
    public Page deploy(IProgressMonitor monitor) throws IOException, HttpException {
        try {
            return super.deploy(monitor);
        } finally {
            try {
                if (tmpFile != null) {
                    Files.delete(tmpFile.toPath());
                }
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

}
