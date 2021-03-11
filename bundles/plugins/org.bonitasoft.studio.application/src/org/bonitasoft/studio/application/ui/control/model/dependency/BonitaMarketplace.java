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

import static org.bonitasoft.studio.application.ui.control.BonitaMarketplacePage.ICON_SIZE;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageDataProvider;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.forms.widgets.ResourceManagerManger;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class BonitaMarketplace {

    private static BonitaMarketplace INSTANCE;

    private static final String LATEST_RELEASE_URL = "https://api.github.com/repos/bonitasoft/bonita-marketplace/releases/latest";
    private static final String ASSET_URL = "https://github.com/bonitasoft/bonita-marketplace/releases/download";
    private static final String CONNECTORS_ASSET_NAME = "connectors.json";
    private static final String ACTORS_FILTERS_ASSET_NAME = "actorfilters.json";
    private static final String DATABASE_DRIVERS_ASSET_NAME = "drivers.json";

    public static final String CONNECTOR_TYPE = "Connector";
    public static final String ACTOR_FILTER_TYPE = "Actor filter";
    public static final String DATABASE_DRIVER_TYPE = "Database driver";

    private List<BonitaArtifactDependency> dependencies;
    private LocalResourceManager manager;
    private RGB iconBackground;

    private BonitaMarketplace() {
        manager = new ResourceManagerManger().getResourceManager(Display.getDefault());

        // Correspond to the css class wizardHighlightBackground -> we can't use css here :'(
        iconBackground = PreferenceUtil.isDarkTheme()
                ? new RGB(47, 47, 47)
                : new RGB(245, 245, 245);
    }

    public static BonitaMarketplace getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BonitaMarketplace();
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
        String latestTag = getLatestTag();
        String connectorsUrl = String.format("%s/%s/%s", ASSET_URL, latestTag, CONNECTORS_ASSET_NAME);
        String actorFiltersUrl = String.format("%s/%s/%s", ASSET_URL, latestTag, ACTORS_FILTERS_ASSET_NAME);
        String driversUrl = String.format("%s/%s/%s", ASSET_URL, latestTag, DATABASE_DRIVERS_ASSET_NAME);
        ArtifactDependencyLoader loader = new ArtifactDependencyLoader();
        dependencies = loader.load(createURL(connectorsUrl));
        dependencies.addAll(loader.load(createURL(actorFiltersUrl)));
        dependencies.addAll(loader.load(createURL(driversUrl)));
        dependencies.forEach(this::createIcon);
    }

    public boolean isLoaded() {
        return dependencies != null;
    }

    private void createIcon(BonitaArtifactDependency dep) {
        Image icon = null;
        if (dep.getIcon() != null) {
            icon = getIcon(createURL(dep.getIcon()));
        }
        if (icon == null) {
            icon = Objects.equals(dep.getType(), CONNECTOR_TYPE)
                    ? Pics.getImage(PicsConstants.connectorDefaultIcon)
                    : Pics.getImage(PicsConstants.actorfilterDefaultIcon);
        }
        if (icon.getBounds().height != ICON_SIZE
                || icon.getBounds().width != ICON_SIZE) {
            icon = resize(icon);
        }
        dep.setIconImage(icon);
    }

    private Image getIcon(URL url) {
        try {
            ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url);
            return manager.createImage(imageDescriptor);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    private Image resize(Image image) {
        ImageDataProvider provider = new ImageDataProvider() {

            @Override
            public ImageData getImageData(int zoom) {
                // pixel colors will be copied from the bas eimage, the depth and the palette data is only used to defined the default bg
                ImageData imageData = new ImageData(ICON_SIZE * zoom / 100, ICON_SIZE * zoom / 100,
                        1, new PaletteData(new RGB[] { iconBackground }));
                imageData.transparentPixel = imageData.palette.getPixel(iconBackground);
                return imageData;
            }
        };
        Image scaled = manager.createImage(ImageDescriptor.createFromImageDataProvider(provider));
        GC gc = new GC(scaled);
        gc.setAntialias(SWT.ON);
        gc.setInterpolation(SWT.HIGH);
        gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, ICON_SIZE, ICON_SIZE);
        gc.dispose();
        return scaled;
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
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
