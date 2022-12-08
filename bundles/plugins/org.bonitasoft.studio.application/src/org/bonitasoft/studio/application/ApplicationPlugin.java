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

import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

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

    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        configureLogbackInBundle(context.getBundle());
        plugin = this;
    }

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

    private void configureLogbackInBundle(Bundle bundle) throws JoranException, IOException {
        var context = LoggerFactory.getILoggerFactory();
        if (context instanceof LoggerContext) {
            JoranConfigurator jc = new JoranConfigurator();
            jc.setContext((LoggerContext) context);
            ((LoggerContext) context).reset();

            // overriding the log directory property programmatically
            String logDirProperty = System.getProperty("org.bonitasoft.studio.logdir",
                    Platform.getLogFileLocation().toFile().getParentFile().getAbsolutePath());
            ((LoggerContext) context).putProperty("LOG_DIR", logDirProperty);

            // this assumes that the logback.xml file is in the root of the bundle.
            var logbackConfigFileUrl = FileLocator.find(bundle, new Path("logback.xml"), null);
            try (var is = logbackConfigFileUrl.openStream()) {
                jc.doConfigure(logbackConfigFileUrl.openStream());
            }
        }
    }

}
