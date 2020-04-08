/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.validation.ValidationPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * The activator class controls the plug-in life cycle
 */
public class ApplicationPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.bonitasoft.studio.application"; //$NON-NLS-1$

    public static final String BAR_DEFAULT_PATH = "barPath";

    // The shared instance
    private static ApplicationPlugin plugin;

    /**
     * The constructor
     */
    public ApplicationPlugin() {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        init(context);
    }

    private void init(final BundleContext context) throws CoreException {
        if (getDialogSettings().get(BAR_DEFAULT_PATH) == null) {
            getDialogSettings().put(BAR_DEFAULT_PATH, System.getProperty("user.home"));
        }
        try {
            ValidationPlugin.getDefault().getBundle().start();
            if (ConsolePlugin.getDefault() == null) {
                new ConsolePlugin();
            }
        } catch (final BundleException e) {
            BonitaStudioLog.error(e);
        }
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
    public static ApplicationPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given
     * plug-in relative path
     *
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(final String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
