package org.eclipse.gmf.tooling.runtime;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class GMFToolingRuntimePlugin extends AbstractUIPlugin {

    public static final String ID = "org.eclipse.gmf.tooling.runtime"; //$NON-NLS-1$

    private static GMFToolingRuntimePlugin ourInstance;

    private LogHelper myLogHelper;

    public void start(BundleContext context) throws Exception {
        super.start(context);
        ourInstance = this;
        myLogHelper = new LogHelper(this);
    }

    public void stop(BundleContext context) throws Exception {
        ourInstance = null;
        super.stop(context);
    }

    public static GMFToolingRuntimePlugin getInstance() {
        return ourInstance;
    }

    /**
     * @since 3.2
     */
    public LogHelper getLogHelper() {
        return myLogHelper;
    }

}
