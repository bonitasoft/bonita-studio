/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.repository.WebFragmentFileStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetFileStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.Context;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class MigrateUIDOperation implements IRunnableWithStatus {

    private PageDesignerURLFactory pageDesignerURLBuilder;
    private MultiStatus status = new MultiStatus(UIDesignerPlugin.PLUGIN_ID, 0, "", null);

    public MigrateUIDOperation(PageDesignerURLFactory pageDesignerURLBuilder) {
        this.pageDesignerURLBuilder = pageDesignerURLBuilder;
    }

    public MigrateUIDOperation() {

    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.subTask(Messages.migratingUID);
        PageDesignerURLFactory urlBuilder = pageDesignerURLBuilder == null
                ? new PageDesignerURLFactory(getPreferenceStore()) : pageDesignerURLBuilder;
        WebPageRepositoryStore repositoryStore = RepositoryManager.getInstance()
                .getRepositoryStore(WebPageRepositoryStore.class);
        migrate(monitor, urlBuilder, repositoryStore);
        WebFragmentRepositoryStore fragmentStore = RepositoryManager.getInstance()
                .getRepositoryStore(WebFragmentRepositoryStore.class);
        WebWidgetRepositoryStore widgetStore = RepositoryManager.getInstance()
                .getRepositoryStore(WebWidgetRepositoryStore.class);
        migrateUnusedFragments(urlBuilder, fragmentStore, monitor);
        migrateUnusedWidgets(urlBuilder, widgetStore, monitor);
    }

    private void migrateUnusedWidgets(PageDesignerURLFactory urlBuilder, WebWidgetRepositoryStore widgetStore,
            IProgressMonitor monitor) throws InvocationTargetException {
        List<WebWidgetFileStore> children = widgetStore.getChildren();
        for (WebWidgetFileStore fileStore : children) {
            if (fileStore.validate().matches(IStatus.WARNING)) {
                status.add(migrateWidget(urlBuilder, fileStore, monitor));
            }
        }
    }

    private void migrateUnusedFragments(PageDesignerURLFactory urlBuilder, WebFragmentRepositoryStore repositoryStore,
            IProgressMonitor monitor) throws InvocationTargetException {
        List<WebFragmentFileStore> children = repositoryStore.getChildren();
        for (WebFragmentFileStore fileStore : children) {
            if (fileStore.validate().matches(IStatus.WARNING)) {
                status.add(migrateFragment(urlBuilder, fileStore, monitor));
            }
        }
    }

    private IStatus migrateFragment(PageDesignerURLFactory urlBuilder, WebFragmentFileStore fileStore,
            IProgressMonitor monitor) throws InvocationTargetException {
        URI uri = null;
        String fragmentId = fileStore.getId();
        try {
            uri = urlBuilder.migrateFragment(fragmentId).toURI();
        } catch (MalformedURLException | URISyntaxException e1) {
            throw new InvocationTargetException(new MigrationException(e1));
        }
        Context currentContext = Context.getCurrent();
        try {
            ClientResource clientResource = new ClientResource(uri);
            clientResource.setRetryOnError(true);
            clientResource.setRetryDelay(500);
            clientResource.setRetryAttempts(5);
            Representation response = clientResource.put(new EmptyRepresentation());
            return parseMigrationResponse(fragmentId, response);
        } catch (ResourceException e) {
            throw new InvocationTargetException(new MigrationException(e),
                    "Failed to post on " + uri);
        } finally {
            Context.setCurrent(currentContext);
        }
    }

    private IStatus migrateWidget(PageDesignerURLFactory urlBuilder, WebWidgetFileStore fileStore,
            IProgressMonitor monitor) throws InvocationTargetException {
        URI uri = null;
        String widgetId = fileStore.getId();
        try {
            uri = urlBuilder.migrateWidget(widgetId).toURI();
        } catch (MalformedURLException | URISyntaxException e1) {
            throw new InvocationTargetException(new MigrationException(e1));
        }
        Context currentContext = Context.getCurrent();
        try {
            ClientResource clientResource = new ClientResource(uri);
            clientResource.setRetryOnError(true);
            clientResource.setRetryDelay(500);
            clientResource.setRetryAttempts(5);
            Representation response = clientResource.put(new EmptyRepresentation());
            return parseMigrationResponse(widgetId, response);
        } catch (ResourceException e) {
            throw new InvocationTargetException(new MigrationException(e),
                    "Failed to post on " + uri);
        } finally {
            Context.setCurrent(currentContext);
        }
    }

    protected void migrate(IProgressMonitor monitor, PageDesignerURLFactory urlBuilder,
            WebPageRepositoryStore repositoryStore) throws InvocationTargetException {
        List<WebPageFileStore> children = repositoryStore.getChildren();
        monitor.beginTask("", children.size());
        for (WebPageFileStore fileStore : children) {
            status.add(migratePage(urlBuilder, fileStore, monitor));
            monitor.worked(1);
        }
    }

    protected IStatus migratePage(PageDesignerURLFactory urlBuilder, WebPageFileStore fileStore,
            IProgressMonitor monitor) throws InvocationTargetException {
        URI uri = null;
        String pageId = fileStore.getId();
        monitor.setTaskName(String.format(Messages.migratingPage, pageId));
        try {
            uri = urlBuilder.migratePage(pageId).toURI();
        } catch (MalformedURLException | URISyntaxException e1) {
            throw new InvocationTargetException(new MigrationException(e1));
        }
        Context currentContext = Context.getCurrent();
        try {
            ClientResource clientResource = new ClientResource(uri);
            clientResource.setRetryOnError(true);
            clientResource.setRetryDelay(500);
            clientResource.setRetryAttempts(5);
            Representation response = clientResource.put(new EmptyRepresentation());
            return parseMigrationResponse(pageId, response);
        } catch (ResourceException e) {
            throw new InvocationTargetException(new MigrationException(e),
                    "Failed to post on " + uri);
        } finally {
            Context.setCurrent(currentContext);
        }
    }

    protected IStatus parseMigrationResponse(String artifactId, Representation response) {
        try {
            String repsonseBody = new BufferedReader(
                    new InputStreamReader(response.getStream(), StandardCharsets.UTF_8))
                            .lines()
                            .collect(Collectors.joining(System.lineSeparator()));
            JSONObject status = new JSONObject(repsonseBody);
            switch (status.getString("status")) {
                case "incompatible":
                    return ValidationStatus.error(String.format(Messages.migrationNotPossible, artifactId));
                case "error":
                    return ValidationStatus.error(String.format(Messages.migrationError, artifactId));
                case "warning":
                    return ValidationStatus.warning(String.format(Messages.migrationWarning, artifactId));
                case "success":
                case "none:":
                default:
                    return ValidationStatus.ok();
            }
        } catch (IOException | JSONException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error(e.getMessage(), e);
        }
    }

    protected IEclipsePreferences getPreferenceStore() {
        return InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID);
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
