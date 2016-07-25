/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.provider;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;

/**
 * This is the central singleton for the Connector edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class ConnectorEditPlugin extends EMFPlugin {
    /**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final ConnectorEditPlugin INSTANCE = new ConnectorEditPlugin();

    /**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static Implementation plugin;


    /**
	 * Create the instance.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorEditPlugin() {
		super
		  (new ResourceLocator [] {
		   });
	}

    /**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
    @Override
    public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}



    /**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
    public static Implementation getPlugin() {
		return plugin;
	}

    /**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static class Implementation extends EclipsePlugin {

    	private ScopedPreferenceStore preferenceStore;


		/**
		 * Creates an instance.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        public Implementation() {
			super();

			// Remember the static instance.
			//
			plugin = this;
		}

        public IPreferenceStore getPreferenceStore() {
            // Create the preference store lazily.
            if (preferenceStore == null) {
                preferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, getSymbolicName());
            }
            return preferenceStore;
        }


        @Override
        public void stop(final BundleContext context) throws Exception {
        	super.stop(context);
        	preferenceStore = null;
        }
    }

}
