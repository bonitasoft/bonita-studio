/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class BusinessObjectPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.bonitasoft.studio.businessobject"; //$NON-NLS-1$

    // The shared instance
    private static BusinessObjectPlugin plugin;

    /**
     * The constructor
     */
    public BusinessObjectPlugin() {
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
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static BusinessObjectPlugin getDefault() {
        return plugin;
    }

    public static Image getImage(final String imageName) {
        final ImageRegistry reg = getDefault().getImageRegistry();

        Image result = reg.get(imageName);

        if (result != null && !result.isDisposed()) {//prevent from bad dispose
            return result;
        }

        final ImageDescriptor descriptor = ImageDescriptor.createFromURL(getDefault().getBundle().getResource(imageName));
        if (descriptor != null) {
            result = descriptor.createImage();
        }

        reg.remove(imageName);
        if (result != null) {
            reg.put(imageName, result);
        }

        return result;
    }
}
