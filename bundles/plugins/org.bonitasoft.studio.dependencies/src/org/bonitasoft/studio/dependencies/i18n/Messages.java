/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.dependencies.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 *
 */
public class Messages extends NLS {

    static{
        NLS.initializeMessages("messages", Messages.class) ;
    }

    public static String dependenciesRepository;
    public static String userClassPathDescription;
    public static String missingDependenciesInRepository;
    public static String selectJars;
    public static String importJar;
    public static String manageJarTitle;
    public static String removeJar;
    public static String beginToRemoveJars;
    public static String removingJar;
    public static String informationDeleteJarTitle;
    public static String informationDeleteJarMessage;
    public static String search;
	public static String popUpMessage;
	public static String selectMissingJarTitle;
	public static String zipFileIsCorrupted;
	public static String dependencyExistsInRuntimeContainer;
	public static String dependencyExistsInRuntimeContainerWithAnotherVersion;
    public static String addingJar;
    public static String beginToAddJars;
    public static String updateProcessConfigurations;
    public static String updatingDependencyInConfigurations;
    public static String removingDependencyInConfigurations;


}
