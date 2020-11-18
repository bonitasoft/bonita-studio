/*
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.preferences.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 */
public class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }

    public static String deleteTenantOnExit;
    public static String reloadDefaultOrganization;
    public static String consoleLocaleLabel;
    public static String studioLocalLabel;
    public static String restartQuestion_title;
    public static String restartQuestion_msg;
    public static String defaultCoolbarAppearance;
    public static String small;
    public static String normal;
    public static String BonitaPreferenceDialog_Advanced;
    public static String BonitaPreferenceDialog_appearance;
    public static String BonitaPreferenceDialog_back;
    public static String BonitaPreferenceDialog_Browser;
    public static String BonitaPreferenceDialog_database;
    public static String BonitaPreferenceDialog_Deployment;
    public static String BonitaPreferenceDialog_general;
    public static String BonitaPreferenceDialog_Java;
    public static String BonitaPreferenceDialog_language;
    public static String BonitaPreferenceDialog_Other;
    public static String BonitaPreferenceDialog_preferences;
    public static String BonitaPreferenceDialog_Proxy;
    public static String BonitaPreferenceDialog_Remote_Engine;
    public static String BonitaPreferenceDialog_RunMode;
    public static String BonitaPreferenceDialog_search;
    public static String BonitaPreferenceDialog_Web;
    public static String BonitaPreferenceDialog_legacy6x;
    public static String warMode;
    public static String embeddedMode;
    public static String BonitaPreferenceDialog_UserXP_Settings;
    public static String BonitaPreferenceDialog_userProfile;
    public static String EclipsePreferences;
    public static String showPassword;
    public static String defaultAppliThemeLabel;
    public static String validateBeforeRun;
    public static String BonitaPreferenceDialog_DBConnectors;
    public static String askRenameDiagram;
    public static String doNotDisplayConnectorDefConfirmationMessage;
    public static String noBrowserFoundTitle;
    public static String noBrowserFoundMsg;
    public static String BonitaPreferenceDialog_Validation;
    public static String light;
    public static String dark;
    public static String theme;
    public static String themeChangedTitle;
    public static String themeChanged;
    public static String notifyBdmDeploymentRequiredMessage;
}
