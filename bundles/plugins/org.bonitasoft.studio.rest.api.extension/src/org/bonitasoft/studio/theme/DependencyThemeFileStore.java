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
package org.bonitasoft.studio.theme;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.plugin.analyze.report.model.Theme;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.operation.GetApiSessionOperation;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.ImportProjectException;
import org.bonitasoft.studio.maven.operation.DeployCustomPageProjectOperation;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class DependencyThemeFileStore extends ThemeFileStore {

    private Theme theme;

    public DependencyThemeFileStore(Theme theme, ExtensionRepositoryStore parentStore) {
        super(new File(theme.getArtifact().getFile()).getName(), parentStore);
        this.theme = theme;
    }

    @Override
    protected ThemeExtensionDescriptor doGetContent() throws ReadFileStoreException {
        return new DependencyThemeExtensionDescriptor(theme);
    }

    @Override
    public File getArchiveFile() {
        return new File(theme.getArtifact().getFile());
    }

    @Override
    public String getDescription() {
        return theme.getDescription();
    }

    @Override
    public String getName() {
        return getArchiveFile().getName();
    }
    
    @Override
    public String getContentType() {
        return ExtensionRepositoryStore.THEME_CONTENT_TYPE;
    }

    @Override
    public void importProject() throws ImportProjectException {
        // Nothing to import
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public IStatus deploy(APISession session, Map<String, Object> options, IProgressMonitor monitor) {
        GetApiSessionOperation apiSessionOperation = new GetApiSessionOperation();
        try {
            APISession apiSession = apiSessionOperation.execute();
            BOSEngineManager bosEngineManager = BOSEngineManager.getInstance();
            var deployOperation = new DeployCustomPageProjectOperation(
                    bosEngineManager.getPageAPI(apiSession),
                    new HttpClientFactory(),
                    this);

            deployOperation.run(monitor);
            return deployOperation.getStatus();
        } catch (InvocationTargetException | InterruptedException | BonitaHomeNotSetException | ServerAPIException
                | UnknownAPITypeException e) {
            return  Status.error("Failed to deployed deployed", e);
        } finally {
            apiSessionOperation.logout();
        }
    }


    @Override
    public <X> X getAdapter(Class<X> adapter) {
        if (adapter.isAssignableFrom(Theme.class)) {
            return (X) theme;
        }
        return super.getAdapter(adapter);
    }
    
    @Override
    public boolean isInClasspath() {
    	return true;
    }
}
