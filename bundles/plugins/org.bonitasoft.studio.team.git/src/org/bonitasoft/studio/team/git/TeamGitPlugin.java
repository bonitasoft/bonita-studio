/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class TeamGitPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.bonitasoft.studio.team.git"; //$NON-NLS-1$

    // The shared instance
    private static TeamGitPlugin plugin;

    /**
     * The constructor
     */
    public TeamGitPlugin() {
    }

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static TeamGitPlugin getDefault() {
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

    public static ImageDescriptor getImageDescriptor(String imageName) {
        final ImageRegistry reg = getDefault().getImageRegistry();

        ImageDescriptor result = reg.getDescriptor(imageName);

        if (result != null) {
            return result;
        }

        final ImageDescriptor descriptor = ImageDescriptor.createFromURL(getDefault().getBundle().getResource(imageName));
        if (descriptor != null) {
            result = descriptor;
        }

        reg.remove(imageName);
        if (result != null) {
            reg.put(imageName, result);
        }

        return result;
    }
}
