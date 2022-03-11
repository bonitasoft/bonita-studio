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

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.net.HttpClientFactory;
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
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.edapt.migration.MigrationException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MigrateUIDOperation implements IRunnableWithStatus {

    private PageDesignerURLFactory pageDesignerURLBuilder;
    private MultiStatus status = new MultiStatus(UIDesignerPlugin.PLUGIN_ID, 0, "", null);
    private ObjectMapper objectMapper = new ObjectMapper();

    public MigrateUIDOperation(PageDesignerURLFactory pageDesignerURLBuilder) {
        this.pageDesignerURLBuilder = pageDesignerURLBuilder;
    }

    public MigrateUIDOperation() {

    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.migratingUID, IProgressMonitor.UNKNOWN);
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
        SubMonitor subMonitor = SubMonitor.convert(monitor, Messages.migratingUID, children.size());
        for (WebWidgetFileStore fileStore : children) {
            if (fileStore.validate().matches(IStatus.WARNING)) {
                status.add(migrateWidget(urlBuilder, fileStore, subMonitor));
            }
            subMonitor.worked(1);
        }
        subMonitor.done();
    }

    private void migrateUnusedFragments(PageDesignerURLFactory urlBuilder, WebFragmentRepositoryStore repositoryStore,
            IProgressMonitor monitor) throws InvocationTargetException {
        List<WebFragmentFileStore> children = repositoryStore.getChildren();
        SubMonitor subMonitor = SubMonitor.convert(monitor, Messages.migratingUID, children.size());
        for (WebFragmentFileStore fileStore : children) {
            if (fileStore.validate().matches(IStatus.WARNING)) {
                status.add(migrateFragment(urlBuilder, fileStore, subMonitor));
            }
            subMonitor.worked(1);
        }
        subMonitor.done();
    }

    private IStatus migrateFragment(PageDesignerURLFactory urlBuilder, WebFragmentFileStore fileStore,
            IProgressMonitor monitor) throws InvocationTargetException {
        URI uri = null;
        String fragmentId = fileStore.getId();
        monitor.subTask(String.format(Messages.migratingFragment, fragmentId));
        try {
            uri = urlBuilder.migrateFragment(fragmentId).toURI();
        } catch (MalformedURLException | URISyntaxException e1) {
            throw new InvocationTargetException(new MigrationException(e1));
        }
        HttpRequest request = HttpRequest.newBuilder(uri)
                .timeout(Duration.ofSeconds(10))
                .PUT(BodyPublishers.noBody()).build();
        HttpResponse<InputStream> response = retriesUntil(request, 200, 5, 500);
        if (response == null) {
            throw new InvocationTargetException(new IOException("Failed to put on " + uri));
        }
        return parseMigrationResponse(fragmentId, response);
    }

    private HttpResponse<InputStream> retriesUntil(HttpRequest request, int expectedStatus, int nbOfRetries,
            int delayBetweenRetries) {
        int retry = nbOfRetries;
        while (retry >= 0) {
            try {
                HttpResponse<InputStream> httpResponse = HttpClientFactory.INSTANCE.send(request, BodyHandlers.ofInputStream());
                if (expectedStatus == httpResponse.statusCode()) {
                    return httpResponse;
                }
                retry--;
                Thread.sleep(delayBetweenRetries);
            } catch (IOException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

    private IStatus migrateWidget(PageDesignerURLFactory urlBuilder, WebWidgetFileStore fileStore,
            IProgressMonitor monitor) throws InvocationTargetException {
        URI uri = null;
        String widgetId = fileStore.getId();
        monitor.subTask(String.format(Messages.migratingCustomWidget, widgetId));
        try {
            uri = urlBuilder.migrateWidget(widgetId).toURI();
        } catch (MalformedURLException | URISyntaxException e1) {
            throw new InvocationTargetException(new MigrationException(e1));
        }
        HttpRequest request = HttpRequest.newBuilder(uri)
                .timeout(Duration.ofSeconds(10))
                .PUT(BodyPublishers.noBody()).build();
        HttpResponse<InputStream> response = retriesUntil(request, 200, 5, 500);
        if (response == null) {
            throw new InvocationTargetException(new IOException("Failed to put on " + uri));
        }
        return parseMigrationResponse(widgetId, response);
    }

    protected void migrate(IProgressMonitor monitor, PageDesignerURLFactory urlBuilder,
            WebPageRepositoryStore repositoryStore) throws InvocationTargetException {
        List<WebPageFileStore> children = repositoryStore.getChildren();
        SubMonitor subMonitor = SubMonitor.convert(monitor, Messages.migratingUID, children.size());
        for (WebPageFileStore fileStore : children) {
            status.add(migratePage(urlBuilder, fileStore, subMonitor));
            subMonitor.worked(1);
        }
        subMonitor.done();
    }

    protected IStatus migratePage(PageDesignerURLFactory urlBuilder, WebPageFileStore fileStore,
            IProgressMonitor monitor) throws InvocationTargetException {
        URI uri = null;
        String pageId = fileStore.getId();
        monitor.subTask(String.format(Messages.migratingPage, pageId));
        try {
            uri = urlBuilder.migratePage(pageId).toURI();
        } catch (MalformedURLException | URISyntaxException e1) {
            throw new InvocationTargetException(new MigrationException(e1));
        }
        HttpRequest request = HttpRequest.newBuilder(uri)
                .timeout(Duration.ofSeconds(10))
                .PUT(BodyPublishers.noBody()).build();
        HttpResponse<InputStream> response = retriesUntil(request, 200, 5, 500);
        if (response == null) {
            throw new InvocationTargetException(new IOException("Failed to put on " + uri));
        }
        return parseMigrationResponse(pageId, response);
    }

    protected IStatus parseMigrationResponse(String artifactId, HttpResponse<InputStream> response) {
        try (var is = response.body()) {
            Map migrationReport = objectMapper.readValue(is, Map.class);
            switch ((String) migrationReport.get("status")) {
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
        } catch (IOException e) {
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
