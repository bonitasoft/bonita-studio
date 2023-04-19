/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.edit;

import org.eclipse.emf.common.EMFPlugin;

import org.eclipse.emf.common.util.ResourceLocator;

/**
 * This is the central singleton for the Process edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class ProcessEditPlugin extends EMFPlugin {
	/**
     * Keep track of the singleton.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final ProcessEditPlugin INSTANCE = new ProcessEditPlugin();

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
	public ProcessEditPlugin() {
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
	}

}
