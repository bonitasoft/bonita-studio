package org.bonitasoft.studio.document;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class DocumentPlugin extends AbstractUIPlugin {

    public static final String PLUGIN_ID = "org.bonitasoft.studio.document";

    private static DocumentPlugin plugin;

    public DocumentPlugin() {

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

    public static DocumentPlugin getDefault() {
        return plugin;
    }

}
