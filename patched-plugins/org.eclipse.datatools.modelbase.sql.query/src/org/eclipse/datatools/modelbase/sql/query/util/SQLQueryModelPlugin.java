/*
 * Created on Apr 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.datatools.modelbase.sql.query.util;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;



/**
 * Life cycle method {@link Plugin#start(org.osgi.framework.BundleContext)} is
 * needed to register this plugin's <code>SQLQuerySourceWriter</code>.
 * 
 * @author ckadner
 */
public class SQLQueryModelPlugin extends Plugin
{
    // the tag NON-NLS-1 marks the first String in the line as non translateable
    // non national language String
    public static final String PLUGIN_ID = "org.eclipse.datatools.modelbase.sql.query"; //$NON-NLS-1$
    public static final String EXTENSION_POINT_ID = "sourceWriterExtension"; //$NON-NLS-1$
    public static final String EXTENSION_CONFIGURATION_PLUGINJAVA = "pluginJava"; //$NON-NLS-1$
    public static final String EXTENSION_CONFIG_PLUGINJAVA_ATTRIBUTE_CLASS = "class"; //$NON-NLS-1$
    public static final String EXTENSION_CONFIGURATION_SOURCEWRITER = "sourceWriter"; //$NON-NLS-1$
    public static final String EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_PACKAGE_NAME = "packageName"; //$NON-NLS-1$
    public static final String EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_IMPL_PACKAGE_NAME = "implPackageName"; //$NON-NLS-1$
    public static final String EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_SOURCEWRITER_CLASS = "class"; //$NON-NLS-1$

    /** The shared instance */
    private static SQLQueryModelPlugin plugin;

    
    /**
     * 
     */
    public SQLQueryModelPlugin()
    {
        super();
        plugin = this;
        registerSourceWriter();
    }

    
    /**
     * Returns the shared instance.
     * @return the shared <code>SQLQueryModelPlugin</code> instance or
     *   <code>null</code> if the plugin was not yet loaded.
     */
    public static SQLQueryModelPlugin getDefault() {
        return plugin;
    }

    
    /**
	 * Life cycle method needed to register this plugin's
	 * <code>SQLQuerySourceWriter</code>.
	 * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception
    {
        super.start(context);
        
        // register extensions to SQL Query model
        registerSourceWriter();
    }
    
    /**
     * Registers plugin's <code>SQLQuerySourceWriter</code>.
     */
    private void registerSourceWriter()
    {
        SQLQuerySourceWriterProvider swProvider =
            SQLQuerySourceWriterProvider.getInstance();
        
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(
                        PLUGIN_ID, EXTENSION_POINT_ID); //$NON-NLS-1$ //$NON-NLS-2$
        
        if (extensionPoint != null)
        {
            boolean extensionFound = false;
	        
	        // get all Plugins extending the sourceWriterExtension point
	        IExtension[] extensions = extensionPoint.getExtensions();
	        for (int i = 0; i < extensions.length; ++i)
	        {
	            
	            IConfigurationElement[] configElements = extensions[i]
	                            .getConfigurationElements();

	            // flag to mark we are in the extension point of the currently
	            // loaded plugin
	            boolean isThisExtension = false;
		            
	            
	            // TODO perf: this code goes through the whole regitry for every loaded plugin extension,
	            // optimized to hold in Map    only make it once regardyng dynamic (un)load
	            
	            for (int j = 0; j < configElements.length; ++j)
	            {
		            // only register the plugin that this code is running in, that
		            // is only for the plugin currently loaded! lazy loading!!!
	                // ...check if the Plugin.java specified in extension-point
	                // is the Plugin.java this code is running in
		            if (configElements[j].getName().equals(EXTENSION_CONFIGURATION_PLUGINJAVA))
		            {
		                String pluginJava =
		                    configElements[j].getAttribute(EXTENSION_CONFIG_PLUGINJAVA_ATTRIBUTE_CLASS);
		                
		                if (this.getClass().getName().equals(pluginJava))
		                {
			                isThisExtension = true;
		                }
		                break;
		            }
	            }
	            
	            if (isThisExtension)
	            {
		            for (int j = 0; j < configElements.length; ++j)
		            {
			            
		                if (configElements[j].getName().equals(EXTENSION_CONFIGURATION_SOURCEWRITER))
		                {
		                    extensionFound = true;
		                    IConfigurationElement configElement = configElements[j];
		                    Class sourceWriterClass = null;

		                    
		                    String packageName = 
		                        configElements[j].getAttribute(EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_PACKAGE_NAME); //$NON-NLS-1$
		                    String implPackageName =
		                        configElements[j].getAttribute(EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_IMPL_PACKAGE_NAME); //$NON-NLS-1$

		                    try
                            {
                                SQLQuerySourceWriter sourceWriter = 
                                    (SQLQuerySourceWriter) configElements[j].createExecutableExtension(EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_SOURCEWRITER_CLASS); //$NON-NLS-1$
                                sourceWriterClass = sourceWriter.getClass();
                            }
                            catch (CoreException e)
                            {
                                logErrorInstanciateSourceWriter(j, configElement, e, packageName, implPackageName);

                            }
		
		                    if (sourceWriterClass != null)
		                    {
			                    if (packageName != null)
	                            {
			                    	swProvider.registerSourceWriter(sourceWriterClass, packageName);
			                	}
			                    else
			                    {
			                        logErrorRegisterSourceWriterPackage(i, sourceWriterClass);
			                    }
			                    
			                    if (implPackageName != null)
	                            {
			                    	swProvider.registerSourceWriter(sourceWriterClass, implPackageName);
			                	}
			                    else
			                    {
			                        logErrorRegisterSourceWriterImplPackage(i, sourceWriterClass);
			                    }

		                    }
		                    else
		                    {
		                        logErrorRegisterSourceWriterClass(i);
		                    }
		                }
		            }
	            }
	        }
	        
	        if (!extensionFound)
	        {
	            logErrorNoExtensionConfigSourceWriter();
	        }
        }
        else
        {
            logErrorNoSourceWriterExtensionPoint();
        }
    }

    
    /**
     * 
     */
    private void logErrorNoSourceWriterExtensionPoint()
    {
        getLog().log( new Status(
                        IStatus.ERROR, 
                        this.getBundle().getSymbolicName(), 0,
                        "extension-point could not be found for "
                        + PLUGIN_ID + "." + EXTENSION_POINT_ID, null));
    }

    /**
     * @param configurationNumber
     * @param configElement
     * @param ce
     * @param packageName
     * @param implPackageName
     */
    private void logErrorInstanciateSourceWriter(int configurationNumber, IConfigurationElement configElement, CoreException ce, String packageName, String implPackageName)
    {
        String msg = "unable to instanciate SourceWriter "
            + configElement.getAttribute(EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_SOURCEWRITER_CLASS)
            + " in extension-point: "
            + PLUGIN_ID
            + "."
            + EXTENSION_POINT_ID
            + " configuration["
            + configurationNumber
            + "]: "
            + EXTENSION_CONFIGURATION_SOURCEWRITER
            + " for "
            + EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_PACKAGE_NAME
            + " " + packageName
            + " and "
            + EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_IMPL_PACKAGE_NAME
            + " " + implPackageName;
        getLog().log( new Status(
            IStatus.ERROR, 
            this.getBundle().getSymbolicName(),
            0, msg, ce));
    }

    /**
     * 
     */
    private void logErrorNoExtensionConfigSourceWriter()
    {
        String msg = "configuration: " +
                        EXTENSION_CONFIGURATION_SOURCEWRITER +
                        " in extension-point: " +
                        PLUGIN_ID + "." + EXTENSION_POINT_ID +
                        " could not be found.";
        getLog().log( new Status(
                        IStatus.ERROR, 
                        this.getBundle().getSymbolicName(),
                        0, msg, null));
    }

    /**
     * @param configurationNumber
     */
    private void logErrorRegisterSourceWriterClass(int configurationNumber)
    {
        String msg = "unable to register SourceWriter "
                        + " in extension-point: "
                        + PLUGIN_ID + "."
                        + EXTENSION_POINT_ID
                        + " configuration[" + configurationNumber + "]: "
                        + EXTENSION_CONFIGURATION_SOURCEWRITER
                        + ", "
                        + EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_SOURCEWRITER_CLASS
                        + " is null";
        getLog().log( new Status(
                        IStatus.ERROR, 
                        this.getBundle().getSymbolicName(),
                        0, msg, null));
    }

    /**
     * @param configurationNumber
     * @param sourceWriterClass
     */
    private void logErrorRegisterSourceWriterImplPackage(int configurationNumber, Class sourceWriterClass)
    {
        String msg = "unable to register SourceWriter "
                        + sourceWriterClass.getName()
                        + " for "
                        + EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_IMPL_PACKAGE_NAME
                        + " in extension-point: "
                        + PLUGIN_ID
                        + "."
                        + EXTENSION_POINT_ID
                        + " configuration["
                        + configurationNumber
                        + "]: "
                        + EXTENSION_CONFIGURATION_SOURCEWRITER
                        + ", "
                        + EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_IMPL_PACKAGE_NAME
                        + " is null";
        getLog().log( new Status(
                        IStatus.ERROR, 
                        this.getBundle().getSymbolicName(),
                        0, msg, null));
    }

    /**
     * @param confiurationNumber
     * @param sourceWriterClass
     */
    private void logErrorRegisterSourceWriterPackage(int confiurationNumber, Class sourceWriterClass)
    {
        String msg = "unable to register SourceWriter "
            			+ sourceWriterClass.getName()
            			+ " for "
            			+ EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_PACKAGE_NAME
                        + " in extension-point: "
                        + PLUGIN_ID + "."
                        + EXTENSION_POINT_ID
                        + " configuration[" + confiurationNumber + "]: "
                        + EXTENSION_CONFIGURATION_SOURCEWRITER
                        + ", "
                        + EXTENSION_CONFIG_SOURCEWRITER_ATTRIBUTE_PACKAGE_NAME
                        + " is null";
        getLog().log( new Status(
                        IStatus.ERROR, 
                        this.getBundle().getSymbolicName(),
                        0, msg, null));
    }

    
    
    
}
