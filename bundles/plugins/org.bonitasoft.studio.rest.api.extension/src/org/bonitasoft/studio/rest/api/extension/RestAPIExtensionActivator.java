/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.google.common.io.Files;

/**
 * The activator class controls the plug-in life cycle
 */
public class RestAPIExtensionActivator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.bonitasoft.studio.rest.api.extension"; //$NON-NLS-1$

    // The shared instance
    private static RestAPIExtensionActivator plugin;

    /**
     * The constructor
     */
    public RestAPIExtensionActivator() {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        initializeProblemViewFilters();
    }

    private void initializeProblemViewFilters() {
        IPreferenceStore preferenceStore = IDEWorkbenchPlugin.getDefault().getPreferenceStore();
        try {
            preferenceStore.setDefault(
                    "org.eclipse.ui.internal.views.markers.CachedMarkerBuilderorg.bonitasoft.studio.rest.api.extension.problemView",
                    Files.toString(
                            new File(FileLocator.toFileURL(RestAPIExtensionActivator.class.getResource("problemView.xml"))
                                    .getFile()),
                            Charset.defaultCharset()));
        } catch (IOException e) {
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
    public static RestAPIExtensionActivator getDefault() {
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
