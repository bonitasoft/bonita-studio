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
package org.bonitasoft.studio.rest.api.extension.core.repository;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.plugin.analyze.report.model.RestAPIExtension;
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

public class DependencyRestAPIExtensionFileStore extends RestAPIExtensionFileStore {

    private RestAPIExtension extension;

    public DependencyRestAPIExtensionFileStore(RestAPIExtension extension,
            ExtensionRepositoryStore parentStore) {
        super(new File(extension.getArtifact().getFile()).getName(), parentStore);
        this.extension = extension;
    }

    @Override
    protected RestAPIExtensionDescriptor doGetContent() throws ReadFileStoreException {
        return new DependencyRestAPIExtensionDescriptor(extension);
    }

    @Override
    public File getArchiveFile() {
        return new File(extension.getArtifact().getFile());
    }

    @Override
    public String getDescription() {
        return extension.getDescription();
    }

    @Override
    public String getName() {
        return getArchiveFile().getName();
    }

    @Override
    public String getContentType() {
        return ExtensionRepositoryStore.API_EXTENSION_CONTENT_TYPE;
    }
    
    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public void importProject() throws ImportProjectException {
        // Nothing to import
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
            return Status.error("Failed to deployed rest api extension", e);
        } finally {
            apiSessionOperation.logout();
        }
    }

    @Override
    public <X> X getAdapter(Class<X> adapter) {
        if (adapter.isAssignableFrom(RestAPIExtension.class)) {
            return (X) extension;
        }
        return super.getAdapter(adapter);
    }
    
    @Override
    public boolean isInClasspath() {
    	return true;
    }
}
