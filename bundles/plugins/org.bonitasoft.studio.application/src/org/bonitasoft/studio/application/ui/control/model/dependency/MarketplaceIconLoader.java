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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageDataProvider;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

public class MarketplaceIconLoader {

    private LocalResourceManager manager;
    private RGB iconBackground;
    private File localStore;

    public MarketplaceIconLoader(File localStore, LocalResourceManager manager,
            RGB iconBackground) {
        this.localStore = localStore;
        this.manager = manager;
        this.iconBackground = iconBackground;
    }

    public Image load(BonitaArtifactDependency dependency) {
        Image icon = null;
        Path target = iconAssetPath(dependency.getIcon());
        if (target != null && target.toFile().exists()) {
            try {
                icon = getIcon(target.toUri().toURL());
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        if (icon == null) {
            icon = Objects.equals(dependency.getType(), BonitaMarketplace.CONNECTOR_TYPE)
                    ? Pics.getImage(PicsConstants.connectorDefaultIcon)
                    : Pics.getImage(PicsConstants.actorfilterDefaultIcon);
        }
        if (icon.getBounds().height != ICON_SIZE
                || icon.getBounds().width != ICON_SIZE) {
            icon = resize(icon);
        }
        return icon;
    }

    private Path iconAssetPath(String iconPath) {
        if (iconPath == null) {
            return null;
        }
        return localStore.toPath()
                .resolve("icons")
                .resolve(iconPath);
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

}
