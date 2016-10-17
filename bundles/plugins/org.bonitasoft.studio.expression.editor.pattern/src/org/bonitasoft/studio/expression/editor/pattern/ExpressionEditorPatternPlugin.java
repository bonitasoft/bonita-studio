package org.bonitasoft.studio.expression.editor.pattern;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ExpressionEditorPatternPlugin extends AbstractUIPlugin {

	// The plug-in ID
    public static final String PLUGIN_ID = "org.bonitasoft.studio.expression.editor.pattern"; //$NON-NLS-1$

	// The shared instance
	private static ExpressionEditorPatternPlugin plugin;
	
	/**
	 * The constructor
	 */
	public ExpressionEditorPatternPlugin() {
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
	public static ExpressionEditorPatternPlugin getDefault() {
		return plugin;
	}

}
