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
package org.bonitasoft.studio.application.ui.control.model.dependency;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.RedirectURLBuilder;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.net.HttpClientFactory;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.forms.widgets.ResourceManagerManger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BonitaMarketplace {

    private static final String MARKETPLACE_VERSION_PROPERTY = "marketplace.version";

    private static final String BONITA_MARKETPLACE_ARCHIVE_NAME = "bonita-marketplace";

    private static final String MARKETPLACE = "marketplace";

    private static BonitaMarketplace INSTANCE;

    private static final String LATEST_RELEASE_URL = RedirectURLBuilder.create("725");
    private static final String ASSET_URL = RedirectURLBuilder.create("726");
    private static final String MARKETPLACE_DESCRIPTOR_NAME = "marketplace.json";
    private static final String MARKETPLACE_METADATA = MARKETPLACE_VERSION_PROPERTY;

    public static final String CONNECTOR_TYPE = "Connector";
    public static final String ACTOR_FILTER_TYPE = "Actor filter";
    public static final String DATABASE_DRIVER_TYPE = "Database driver";

    private List<BonitaArtifactDependency> dependencies;
    private LocalResourceManager manager;
    private RGB iconBackground;
    private File localStore;
    private File cacheFolder;
    private boolean synchronizeMarketplace = true;
    private ObjectMapper objectMapper;

    private BonitaMarketplace() {
        objectMapper = new ObjectMapper();
        manager = new ResourceManagerManger().getResourceManager(Display.getDefault());
        // Correspond to the css class wizardHighlightBackground -> we can't use css here :'(
        iconBackground = PreferenceUtil.isDarkTheme()
                ? new RGB(47, 47, 47)
                : new RGB(245, 245, 245);
        cacheFolder = ApplicationPlugin.getDefault().getStateLocation().toFile();
        localStore = new File(cacheFolder, MARKETPLACE);
    }

    private void checkStoreContent(IProgressMonitor monitor) throws IOException {
        monitor.beginTask(Messages.fetchingExtensions, IProgressMonitor.UNKNOWN);
        String currentVersion = readMetadata();
        try {
            String latestVersion = getLatestTag();
            if (Strings.isNullOrEmpty(currentVersion)
                    || !new File(localStore, MARKETPLACE_DESCRIPTOR_NAME).exists()
                    || !Objects.equals(currentVersion, latestVersion)) {
                downloadMarketplace(latestVersion, monitor);
            }
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            // Do not retry to access remote marketplace
            synchronizeMarketplace = false;
            if (Strings.hasText(currentVersion)) {
                BonitaNotificator.openInfoNotification(Messages.cannotAccessMarketplace,
                        Messages.cannotUpdateMarketplaceMessage);
            } else {
                Display.getDefault()
                        .syncExec(() -> MessageDialog.openWarning(Display.getDefault().getActiveShell(),
                                Messages.cannotAccessMarketplace,
                                Messages.cannotLoadMarketplaceMessage));
            }

        }
    }

    private void downloadMarketplace(String latestVersion, IProgressMonitor monitor)
            throws IOException {
        Path tmpFile = Files.createTempFile(BONITA_MARKETPLACE_ARCHIVE_NAME, ".zip");
        String assetUrl = RedirectURLBuilder.handleURLRedirection(ASSET_URL);
        var url = createURL(String.format("%s/%s/%s-%s.zip", assetUrl,
                latestVersion,
                BONITA_MARKETPLACE_ARCHIVE_NAME,
                latestVersion));
        if (url != null) {
            download(url,
                    tmpFile,
                    monitor);
            extract(tmpFile, cacheFolder.toPath());
            Files.delete(tmpFile);
            updateMetadata(latestVersion);
        }
    }

    private void updateMetadata(String latestVersion) {
        var metadataFile = getMetadataFile();
        if (metadataFile.exists()) {
            metadataFile.delete();
        }
        try {
            metadataFile.createNewFile();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        try (var os = Files.newOutputStream(metadataFile.toPath())) {
            var p = new Properties();
            p.setProperty(MARKETPLACE_VERSION_PROPERTY, latestVersion);
            p.store(os, null);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public File getMetadataFile() {
        return new File(cacheFolder, MARKETPLACE_METADATA);
    }

    private String readMetadata() {
        var metadataFile = getMetadataFile();
        if (metadataFile.exists()) {
            try (var is = Files.newInputStream(metadataFile.toPath())) {
                var p = new Properties();
                p.load(is);
                return p.getProperty(MARKETPLACE_VERSION_PROPERTY);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return null;
    }

    private void extract(Path sourceZip, Path target) {
        if (target.toFile().exists()) {
            FileUtil.deleteDir(target.toFile());
        }
        try {
            PlatformUtil.unzipZipFiles(sourceZip.toFile(), target.toFile(),
                    AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    private void download(URL url, Path target, IProgressMonitor monitor) throws IOException {
        var connection = url.openConnection();
        double completeFileSize = connection.getContentLength();
        try (var readableByteChannel = Channels.newChannel(url.openStream());
                var fileOutputStream = new FileOutputStream(target.toFile());
                var fileChannel = fileOutputStream.getChannel()) {
            long chunkSize = 500;
            var position = 0;
            monitor.beginTask(Messages.fetchingExtensions, (int) completeFileSize);
            monitor.subTask("Downloading: " + url.toString());
            while (position < completeFileSize) {
                position += fileOutputStream.getChannel().transferFrom(readableByteChannel, position, chunkSize);
                monitor.worked((int) chunkSize);
            }
        }
    }

    public static BonitaMarketplace getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BonitaMarketplace();
        }
        return INSTANCE;
    }

    public List<BonitaArtifactDependency> getDependencies() {
        if (dependencies == null || dependencies.isEmpty()) {
            loadDependencies(new NullProgressMonitor());
        }
        return dependencies;
    }

    public void synchronizeMarketplace() {
        this.synchronizeMarketplace = true;
    }

    public void loadDependencies(IProgressMonitor monitor) {
        if (dependencies == null || dependencies.isEmpty()) {
            var loader = new ArtifactDependencyLoader(
                    new MarketplaceIconLoader(localStore, manager, iconBackground));
            if (synchronizeMarketplace) {
                try {
                    checkStoreContent(monitor);
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            } else if (!localStore.toPath().resolve(MARKETPLACE_DESCRIPTOR_NAME).toFile().exists()) {
                dependencies = Collections.emptyList();
            }
            dependencies = loader.load(localStore.toPath().resolve(MARKETPLACE_DESCRIPTOR_NAME));
        }
    }

    private String getLatestTag() throws IOException {
        var url = createURL(LATEST_RELEASE_URL);
        if (url != null) {
            Map<String, Object> release = doGet(url);
            return (String) release.get("tag_name");
        }
        return null;
    }

    private Map<String, Object> doGet(URL url) throws IOException {
        try {
            HttpResponse<InputStream> response = HttpClientFactory.INSTANCE
                    .send(HttpRequest.newBuilder(url.toURI()).GET().build(), BodyHandlers.ofInputStream());
            try (var is = response.body()) {
                return objectMapper.readValue(is, new TypeReference<Map<String, Object>>() {
                });
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new IOException(url + " GET request failed ", e);
        }
    }

    private URL createURL(String url) {
        if (url == null || url.isBlank()) {
            return null;
        }
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
