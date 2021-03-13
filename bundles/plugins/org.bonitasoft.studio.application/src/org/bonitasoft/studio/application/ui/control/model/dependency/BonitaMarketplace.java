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
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.forms.widgets.ResourceManagerManger;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class BonitaMarketplace {

    private static final String MARKETPLACE = "marketplace";

    private static BonitaMarketplace INSTANCE;

    private static final String LATEST_RELEASE_URL = "https://api.github.com/repos/bonitasoft/bonita-marketplace/releases/latest";
    private static final String ASSET_URL = "https://github.com/bonitasoft/bonita-marketplace/releases/download";
    private static final String CONNECTORS_ASSET_NAME = "connectors.json";
    private static final String ACTORS_FILTERS_ASSET_NAME = "actorfilters.json";
    private static final String DATABASE_DRIVERS_ASSET_NAME = "drivers.json";

    public static final String CONNECTOR_TYPE = "Connector";
    public static final String ACTOR_FILTER_TYPE = "Actor filter";
    public static final String DATABASE_DRIVER_TYPE = "Database driver";

    private static final String MARKETPLACE_METADATA = "marketplace.version";

    private List<BonitaArtifactDependency> dependencies;
    private LocalResourceManager manager;
    private RGB iconBackground;

    private File localStore;

    private BonitaMarketplace(IProgressMonitor monitor) {
        manager = new ResourceManagerManger().getResourceManager(Display.getDefault());
        // Correspond to the css class wizardHighlightBackground -> we can't use css here :'(
        iconBackground = PreferenceUtil.isDarkTheme()
                ? new RGB(47, 47, 47)
                : new RGB(245, 245, 245);
        try {
            checkStoreContent(monitor);
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    private void checkStoreContent(IProgressMonitor monitor) throws IOException {
        IPath stateLocation = ApplicationPlugin.getDefault().getStateLocation();
        localStore = new File(stateLocation.toFile(), MARKETPLACE);
        String currentVersion = readMetadata();
        String latestVersion = getLatestTag();
        if (currentVersion == null || currentVersion.isBlank() || !localStore.exists() || !Objects.equals(currentVersion, latestVersion)) {
            Path tmpFile = Files.createTempFile("bonita-marketplace", ".zip");
            download(createURL(String.format("%s/%s/%s-%s.zip", ASSET_URL, latestVersion, "bonita-marketplace", latestVersion)),
                    tmpFile,
                    monitor);
            extract(tmpFile, stateLocation.toFile().toPath());
            Files.delete(tmpFile);
            updateMetadata(latestVersion);
        }
    }

    private void updateMetadata(String latestVersion) {
        File metadataFile = getMetadataFile();
        if (metadataFile.exists()) {
            metadataFile.delete();
        }
        try {
            metadataFile.createNewFile();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        try (OutputStream os = Files.newOutputStream(metadataFile.toPath())) {
            Properties p = new Properties();
            p.setProperty("marketplace.version", latestVersion);
            p.store(os, null);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public File getMetadataFile() {
        IPath stateLocation = ApplicationPlugin.getDefault().getStateLocation();
        return new File(stateLocation.toFile(), MARKETPLACE_METADATA);
    }

    private String readMetadata() {
        File metadataFile = getMetadataFile();
        if (metadataFile.exists()) {
            try (InputStream is = Files.newInputStream(metadataFile.toPath())) {
                Properties p = new Properties();
                p.load(is);
                return p.getProperty("marketplace.version");
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
        URLConnection connection = url.openConnection();
        double completeFileSize = connection.getContentLength();
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(target.toFile());
                FileChannel fileChannel = fileOutputStream.getChannel()) {
            long chunkSize = 500;
            int position = 0;
            monitor.beginTask(Messages.fetchingExtensions, (int) completeFileSize);
            monitor.subTask("Downloading: " + target.getFileName().toString());
            while (position < completeFileSize) {
                position += fileOutputStream.getChannel().transferFrom(readableByteChannel, position, chunkSize);
                monitor.worked((int) chunkSize);
            }
        }
    }

    public static BonitaMarketplace getInstance(IProgressMonitor monitor) {
        if (INSTANCE == null) {
            INSTANCE = new BonitaMarketplace(monitor);
        }
        return INSTANCE;
    }

    public List<BonitaArtifactDependency> getDependencies() {
        if (dependencies == null) {
            loadDependencies();
        }
        return dependencies;
    }

    /**
     * To test the UI with a local file, comment this method content and use the loader with the URL of the local file
     * ex:
     * dependencies = loader.load(new File("/Users/adrien/bonita/bonita-marketplace/build/connectors.json").toURI().toURL());
     */
    public void loadDependencies() {
        if(dependencies == null) {
            ArtifactDependencyLoader loader = new ArtifactDependencyLoader();
            dependencies = loader.load(localStore.toPath().resolve("marketplace.json"));
            dependencies.stream()
                    .forEach(dep -> {
                        MarketplaceIconLoader iconLoader = new MarketplaceIconLoader(dep, manager, iconBackground);
                        dep.setIconImage(iconLoader.load(iconAssetPath(dep.getIcon())));
                    });
        }
    }

    private Path iconAssetPath(String iconPath) {
        if (iconPath == null) {
            return null;
        }
        return localStore.toPath()
                .resolve("icons")
                .resolve(iconPath);
    }

    private String getLatestTag() {
        JSONObject release = doGet(createURL(LATEST_RELEASE_URL));
        try {
            return release.getString("tag_name");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject doGet(URL url) {
        try {
            return new JSONObject(new ClientResource(url.toURI()).get().getText());
        } catch (ResourceException | JSONException | IOException | URISyntaxException e) {
            throw new RuntimeException(e);
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
