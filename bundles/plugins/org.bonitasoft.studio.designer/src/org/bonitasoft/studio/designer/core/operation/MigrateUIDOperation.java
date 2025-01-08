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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Map;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.UIDWorkspaceSynchronizer;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;

public class MigrateUIDOperation implements IRunnableWithStatus {

    private PageDesignerURLFactory pageDesignerURLBuilder = new PageDesignerURLFactory(getPreferenceStore());
    private IStatus status = new MultiStatus(UIDesignerPlugin.PLUGIN_ID, 0, "", null);
    private Path uidWorkspace;
    private String logs = "UI Designer logs not found.";

    public MigrateUIDOperation(PageDesignerURLFactory pageDesignerURLBuilder) {
        this.pageDesignerURLBuilder = pageDesignerURLBuilder;
    }

    public MigrateUIDOperation() {

    }

    /**
     * When true, a standalone UID instance is started on the given workspace path
     */
    public MigrateUIDOperation useStandaloneUIDAt(Path uidWorkspace) {
        this.uidWorkspace = uidWorkspace;
        return this;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.migratingUID, IProgressMonitor.UNKNOWN);
        UIDWorkspaceSynchronizer.disable();
        if (uidWorkspace != null) {
            try {
                removeUidProvidedWidgets();
            } catch (CoreException e) {
                throw new InvocationTargetException(e);
            }
            Path logFile;
            try {
                logFile = Files.createTempFile("uid-migration", ".log");
                logFile.toFile().deleteOnExit();
            } catch (IOException e) {
                status = Status.error("Failed to create temporary folder.", e);
                throw new InvocationTargetException(e);
            }
            try (var uidInstance = UIDesignerServerManager.getInstance().startStandalone(uidWorkspace, logFile.toFile(),
                    monitor)) {
                migrateArtifacts(uidInstance.getUrlBuilder(), monitor);
            } catch (IOException e) {
                status = Status.error("An error occured during UID artifacts migration.", e);
                throw new InvocationTargetException(e);
            } finally {
                UIDWorkspaceSynchronizer.enable();
                try {
                    logs = Files.readString(logFile);
                    if(Files.isWritable(logFile)) {
                        Files.delete(logFile);
                    }
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
        } else {
            try {
                uidWorkspace = RepositoryManager.getInstance().getCurrentProject().orElseThrow().getAppProject()
                        .getLocation().toFile().toPath();
                migrateArtifacts(pageDesignerURLBuilder, monitor);
            } finally {
                UIDWorkspaceSynchronizer.enable();
            }
        }
    }

    private void migrateArtifacts(PageDesignerURLFactory urlBuilder, IProgressMonitor monitor)
            throws InvocationTargetException {
        migrateWidgets(urlBuilder, monitor);
        migrateFragments(urlBuilder, monitor);
        migratePages(monitor, urlBuilder);
    }

    private void migrateWidgets(PageDesignerURLFactory urlBuilder,
            IProgressMonitor monitor) throws InvocationTargetException {
        BonitaStudioLog.info("Migrating UI Designer custom widgets...");
        Path widgetFolder = uidWorkspace.resolve("web_widgets");
        var submonitor = SubMonitor.convert(monitor);
        var widgetVisitor = new WidgetVisitorImpl(widgetFolder, urlBuilder, submonitor);
        try {
            Files.walkFileTree(widgetFolder, widgetVisitor);
        } catch (IOException e) {
            throw new InvocationTargetException(e);
        } finally {
            submonitor.done();
        }
        if (widgetVisitor.getError() != null) {
            status =  Status.error("An error occured during UID artifacts migration.", widgetVisitor.getError());
            throw new InvocationTargetException(widgetVisitor.getError());
        }
    }

    private void migrateFragments(PageDesignerURLFactory urlBuilder,
            IProgressMonitor monitor) throws InvocationTargetException {
        BonitaStudioLog.info("Migrating UI Designer fragments...");
        Path fragmentFolder = uidWorkspace.resolve("web_fragments");
        var submonitor = SubMonitor.convert(monitor);
        var fragmentVisitor = new FragmentVisitorImpl(fragmentFolder, urlBuilder, submonitor);
        try {
            Files.walkFileTree(fragmentFolder, fragmentVisitor);
        } catch (IOException e) {
            throw new InvocationTargetException(e);
        } finally {
            submonitor.done();
        }
        if (fragmentVisitor.getError() != null) {
            status =  Status.error("An error occured during UID artifacts migration.", fragmentVisitor.getError());
            throw new InvocationTargetException(fragmentVisitor.getError());
        }
    }

    protected void migratePages(IProgressMonitor monitor, PageDesignerURLFactory urlBuilder)
            throws InvocationTargetException {
        BonitaStudioLog.info("Migrating UI Designer pages...");
        Path pageFolder = uidWorkspace.resolve("web_page");
        var submonitor = SubMonitor.convert(monitor);
        var pageVisitor = new PageVisitorImpl(pageFolder, urlBuilder, submonitor);
        try {
            Files.walkFileTree(pageFolder, pageVisitor);
        } catch (IOException e) {
            throw new InvocationTargetException(e);
        } finally {
            submonitor.done();
        }
        if (pageVisitor.getError() != null) {
            status =  Status.error("An error occured during UID artifacts migration.", pageVisitor.getError());
            throw new InvocationTargetException(pageVisitor.getError());
        }
    }

    protected void removeUidProvidedWidgets() throws CoreException {
        var widgetsFolder = uidWorkspace.resolve("web_widgets");
        if (Files.exists(widgetsFolder)) {
            try {
                Files.find(widgetsFolder,
                        1,
                        // Provided widget folder matcher
                        (path, attr) -> path.getFileName().toString().startsWith("pb") && Files.isDirectory(path))
                        .forEach(widget -> {
                            try {
                                FileUtil.deleteDir(widget);
                            } catch (IOException e) {
                                throw new UncheckedIOException(e);
                            }
                        });
            } catch (IOException | UncheckedIOException e) {
                throw new CoreException(Status.error("Failed to delete provided widgets.", e));
            }
        }
    }

    protected IEclipsePreferences getPreferenceStore() {
        return InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID);
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

    public String getLogs() {
        return logs;
    }

    class PageVisitorImpl extends UIDArtifactMigrationVisitor {

        public PageVisitorImpl(Path pageFolder, PageDesignerURLFactory urlBuilder, IProgressMonitor monitor) {
            super(pageFolder, urlBuilder, monitor);
        }

        @Override
        protected FileVisitResult migrateArtifact(File file, PageDesignerURLFactory urlBuilder,
                IProgressMonitor monitor) {
            try {
                var page = objectMapper.readValue(file, Map.class);
                var type = page.get("type");
                if ("page".equals(type) || "layout".equals(type) || "form".equals(type)) {
                    URI uri = null;
                    String pageId = (String) page.get("id");
                    monitor.subTask(String.format(Messages.migratingPage, pageId));
                    uri = urlBuilder.migratePage(pageId).toURI();
                    HttpRequest request = HttpRequest.newBuilder(uri)
                            .timeout(Duration.ofMinutes(1))
                            .PUT(BodyPublishers.noBody()).build();
                    HttpResponse<InputStream> response = retriesUntil(request, 200, 10, 2000);
                    monitor.worked(1);
                    if (response == null) {
                        error = new IOException("Failed to put on " + uri);
                        return FileVisitResult.TERMINATE;
                    }
                    if(status instanceof MultiStatus multiStatus) {
                        multiStatus.add(parseMigrationResponse(pageId, response));
                    }
                }
            } catch (IOException | URISyntaxException e) {
                error = e;
                return FileVisitResult.TERMINATE;
            }
            return FileVisitResult.SKIP_SIBLINGS;
        }

    }

    class FragmentVisitorImpl extends UIDArtifactMigrationVisitor {

        public FragmentVisitorImpl(Path fragmentsFolder, PageDesignerURLFactory urlBuilder, IProgressMonitor monitor) {
            super(fragmentsFolder, urlBuilder, monitor);
        }

        @Override
        protected FileVisitResult migrateArtifact(File file, PageDesignerURLFactory urlBuilder,
                IProgressMonitor monitor) {
            try {
                var fragment = objectMapper.readValue(file, Map.class);
                var type = fragment.get("type");
                if ("fragment".equals(type)) {
                    URI uri = null;
                    String fragmentId = (String) fragment.get("id");
                    monitor.subTask(String.format(Messages.migratingFragment, fragmentId));
                    uri = urlBuilder.migrateFragment(fragmentId).toURI();
                    HttpRequest request = HttpRequest.newBuilder(uri)
                            .timeout(Duration.ofMinutes(1))
                            .PUT(BodyPublishers.noBody()).build();
                    HttpResponse<InputStream> response = retriesUntil(request, 200, 10, 2000);
                    monitor.worked(1);
                    if (response == null) {
                        error = new IOException("Failed to put on " + uri);
                        return FileVisitResult.TERMINATE;
                    }
                    if(status instanceof MultiStatus multiStatus) {
                        multiStatus.add(parseMigrationResponse(fragmentId, response));
                    }
                    
                }
            } catch (IOException | URISyntaxException e) {
                error = e;
                return FileVisitResult.TERMINATE;
            }
            return FileVisitResult.SKIP_SIBLINGS;
        }

    }

    class WidgetVisitorImpl extends UIDArtifactMigrationVisitor {

        public WidgetVisitorImpl(Path widgetsFolder, PageDesignerURLFactory urlBuilder, IProgressMonitor monitor) {
            super(widgetsFolder, urlBuilder, monitor);
        }

        @Override
        protected FileVisitResult migrateArtifact(File file, PageDesignerURLFactory urlBuilder,
                IProgressMonitor monitor) {
            try {
                var widget = objectMapper.readValue(file, Map.class);
                var type = widget.get("type");
                var custom = (Boolean) widget.get("custom");
                if ("widget".equals(type) && custom != null && custom) {
                    URI uri = null;
                    String widgetId = (String) widget.get("id");
                    monitor.subTask(String.format(Messages.migratingCustomWidget, widgetId));
                    uri = urlBuilder.migrateWidget(widgetId).toURI();
                    HttpRequest request = HttpRequest.newBuilder(uri)
                            .timeout(Duration.ofMinutes(1))
                            .PUT(BodyPublishers.noBody()).build();
                    HttpResponse<InputStream> response = retriesUntil(request, 200, 10, 2000);
                    monitor.worked(1);
                    if (response == null) {
                        error = new IOException("Failed to put on " + uri);
                        return FileVisitResult.TERMINATE;
                    }
                    if(status instanceof MultiStatus multiStatus) {
                        multiStatus.add(parseMigrationResponse(widgetId, response));
                    }
                }
            } catch (IOException | URISyntaxException e) {
                error = e;
                return FileVisitResult.TERMINATE;
            }
            return FileVisitResult.SKIP_SIBLINGS;
        }

    }

}
