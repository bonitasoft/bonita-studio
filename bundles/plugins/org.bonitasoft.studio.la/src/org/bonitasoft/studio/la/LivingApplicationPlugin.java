package org.bonitasoft.studio.la;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class LivingApplicationPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.bonitasoft.studio.la"; //$NON-NLS-1$

    // The shared instance
    private static LivingApplicationPlugin plugin;

    /**
     * The constructor
     */
    public LivingApplicationPlugin() {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
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
    public static LivingApplicationPlugin getDefault() {
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
