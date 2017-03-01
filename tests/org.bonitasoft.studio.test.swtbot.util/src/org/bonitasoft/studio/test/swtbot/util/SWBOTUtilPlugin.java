package org.bonitasoft.studio.test.swtbot.util;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class SWBOTUtilPlugin implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    /*
     * (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext bundleContext) throws Exception {
        SWBOTUtilPlugin.context = bundleContext;
    }

    /*
     * (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception {
        SWBOTUtilPlugin.context = null;
    }

}
