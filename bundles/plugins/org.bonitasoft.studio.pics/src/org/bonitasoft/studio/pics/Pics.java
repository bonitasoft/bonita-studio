/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.pics;

import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;

import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.forms.widgets.ResourceManagerManger;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Pics extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.bonitasoft.studio.pics"; //$NON-NLS-1$

    // The shared instance
    private static Pics plugin;

    private static ImageDescriptor wizban;
    private static final ResourceManagerManger RMM = new ResourceManagerManger();

    private static class ClassPathResourceImageDescriptor extends ImageDescriptor {

        private final String file;

        public ClassPathResourceImageDescriptor(final String file) {
            this.file = file;
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.jface.resource.ImageDescriptor#getImageData()
         */
        @Override
        public ImageData getImageData() {
            InputStream resourceAsStream = Pics.class.getResourceAsStream(file);
            if (resourceAsStream != null) {
                return new ImageData(resourceAsStream);
            }
            return new ImageData(1, 1, 16, new PaletteData(new RGB[] {}));
        }

    }

    /**
     * @return
     */
    public static ImageDescriptor getWizban() {
        if (wizban == null) {
            wizban = isDarkTheme()
                    ? new ClassPathResourceImageDescriptor("/icons/wizban/wizardDark.png")
                    : new ClassPathResourceImageDescriptor("/icons/wizban/wizard.png");
        }
        return wizban;
    }

    private static boolean isDarkTheme() {
        try {
            IThemeEngine engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);
            if (engine != null && engine.getActiveTheme() != null
                    && Objects.equals(engine.getActiveTheme().getId(),
                            "org.bonitasoft.studio.preferences.theme.dark")) {
                return true;
            }
        } catch (IllegalStateException e) {
            // Workbench not created yet, ignored.
        }
        return false;
    }

    /**
     * Returns the given image. The image will be managed by the plugin's
     * image registry.
     *
     * @param imageName a pathname relative to the icons directory of
     *        this project.
     */
    public static Image getImage(final String imageName) {
        if (PicsConstants.hint.equals(imageName) && plugin != null) {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
        }
        return getImage(imageName, plugin);
    }

    public static Image getImage(final String imageName, final AbstractUIPlugin plugin) {
        if (plugin == null) {
            return null;
        }
        final ImageDescriptor descriptor = getImageDescriptor(imageName, plugin);
        if (descriptor != null) {
            LocalResourceManager manager = RMM.getResourceManager(Display.getDefault());
            return manager.createImage(descriptor);
        }
        return null;
    }

    public static ImageDescriptor getImageDescriptor(final String imageName, final AbstractUIPlugin plugin) {
        if (plugin == null) {
            return ImageDescriptor.getMissingImageDescriptor();
        }
        String resourceName = imageName;
        if (isDarkTheme()) {
            String extension = getExtension(imageName);
            String id = imageName.substring(0, imageName.lastIndexOf(extension));
            resourceName = id + "_dark" + extension;
        }

        URL resource = plugin.getBundle().getResource("/icons/" + resourceName);
        if (resource != null) {
            return ImageDescriptor.createFromURL(resource);
        }
        return ImageDescriptor.createFromURL(plugin.getBundle().getResource("/icons/" + imageName));
    }

    private static String getExtension(String imageName) {
        if (imageName.indexOf(".") != -1) {
            return imageName.substring(imageName.lastIndexOf("."), imageName.length());
        }
        return null;
    }

    /**
     * Returns the given image descriptor. The caller will be responsible
     * for deallocating the image if it creates the image from the descriptor
     *
     * @param imageName is a pathname relative to the icons directory
     *        within this project.
     */
    public static ImageDescriptor getImageDescriptor(final String imageName) {
        return getImageDescriptor(imageName, plugin);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * @param connectorCategories
     * @param element
     */
    public static Image getImage(final String connectorCategories, final String element) {
        return getImage(connectorCategories + element + ".png"); //$NON-NLS-1$
    }

    public static Image getFlag(final Locale locale) {
        return getImage("/flags/" + locale.getCountry() + ".png"); //$NON-NLS-1$
    }

    public static Cursor getOpenedHandCursor() {
        return Display.getDefault().getSystemCursor(SWT.CURSOR_HAND);
    }

    public static Cursor getClosedHandCursor() {
        return Display.getDefault().getSystemCursor(SWT.CURSOR_SIZEALL);
    }

}
