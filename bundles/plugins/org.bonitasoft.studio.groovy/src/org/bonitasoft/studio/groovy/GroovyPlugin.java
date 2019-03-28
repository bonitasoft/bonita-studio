package org.bonitasoft.studio.groovy;


import java.lang.reflect.Field;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.codehaus.jdt.groovy.integration.LanguageSupport;
import org.codehaus.jdt.groovy.integration.LanguageSupportFactory;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class GroovyPlugin extends Plugin  {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bonitasoft.studio.groovy"; //$NON-NLS-1$

	// The shared instance
	private static GroovyPlugin plugin;

	/**
	 * The constructor
	 */
	public GroovyPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		Field languageSupportField = LanguageSupportFactory.class.getDeclaredField("languageSupport");
		languageSupportField.setAccessible(true);
		languageSupportField.set(null, new CustomGroovyLanguageSupport());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static GroovyPlugin getDefault() {
		return plugin;
	}

	public static void logError(Throwable e) {
		BonitaStudioLog.error(e) ;
	}

	public static void logError(String message, Throwable e) {
		BonitaStudioLog.error(e) ;
	}


}
