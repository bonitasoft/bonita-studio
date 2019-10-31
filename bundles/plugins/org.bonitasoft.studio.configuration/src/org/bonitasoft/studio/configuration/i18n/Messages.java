/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.configuration.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 */
public class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages";

    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    public static String configurationWizardTitle;
    public static String configurationWizardDesc;
    public static String otherProcesses;
    public static String selectProcessToConfigureTitle;
    public static String selectProcessToConfigureDesc;
    public static String mustSelectProcessError;
    public static String mustSelectExportError;
    public static String exportWizardPageTitle;
    public static String exportWizardPageDesc;
    public static String importWizardPageTitle;
    public static String importWizardPageDesc;
    public static String exporting;
    public static String importing;
    public static String runConfigurationTitle;
    public static String runConfigurationDesc;
    public static String ConfigureButtonLabel;
    public static String username;
    public static String password;
    public static String runLoginMessage;
    public static String usernameIsMissingForConfiguration;
    public static String manageJars;
    public static String connector;
    public static String actorfilter;
    public static String validator;
    public static String datatypes;
    public static String missingJarFileInRepository;
    public static String add;
    public static String remove;
    public static String others;
    public static String groovyScripts;
    public static String hiearachical;
    public static String raw;
    public static String hiearachicalViewDesc;
    public static String rawViewDesc;
    public static String jarName;
    public static String inculdedBy;
    public static String applicationDependencies;
    public static String applicationDependenciesConfigurationDescription;
    public static String displayAdvancedConfiguration;
    public static String configurationTitle;
    public static String authenticatedUser;
    public static String anonymousUser;
    public static String anonymousUserMessage;
    public static String anonymousUserNameMissingMessage;
    public static String synchronizingConfiguration;
    public static String exportFailedTitle;
    public static String javaDependencies;
    public static String javaDependenciesConfigurationDescription;
    public static String unknownUser;

}
