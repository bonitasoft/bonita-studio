/*
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.preferences;

import java.util.Locale;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.eclipse.ui.internal.util.PrefUtil;

/**
 * @author Romain Bioteau
 *         Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer implements BonitaPreferenceConstants {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    @Override
    public void initializeDefaultPreferences() {
        final IPreferenceStore store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        final IPreferenceStore webStore = WebBrowserUIPlugin.getInstance().getPreferenceStore();
        final IEclipsePreferences node = DefaultScope.INSTANCE.getNode(ResourcesPlugin.PI_RESOURCES);
        node.put(ResourcesPlugin.PREF_ENCODING, "UTF-8");
        final String consolePortSpecifiedAsSystemProperty = System.getProperty(CONSOLE_PORT);
        if (consolePortSpecifiedAsSystemProperty != null
                && !consolePortSpecifiedAsSystemProperty.isEmpty()) {
            try {
                store.setDefault(CONSOLE_PORT, Integer.parseInt(consolePortSpecifiedAsSystemProperty));
            } catch (final Exception e) {
                store.setDefault(CONSOLE_PORT, DEFAULT_PORT);
            }
        } else {
            store.setDefault(CONSOLE_PORT, DEFAULT_PORT);
        }
        store.setDefault(CONSOLE_HOST, DEFAULT_HOST);
        webStore.setDefault(CONSOLE_BROWSER_CHOICE, EXTERNAL_BROWSER);

        store.setDefault(USER_NAME, USER_NAME_DEFAULT);
        store.setDefault(USER_PASSWORD, USER_PASSWORD_DEFAULT);
        store.setDefault(SHOW_CONDITION_ON_TRANSITION, "false");
        store.setDefault(DELETE_TENANT_ON_EXIT, true);
        store.setDefault(LOAD_ORGANIZATION, true);
        store.setDefault(AUTOMATIC_ID, "true");
        store.setDefault(TOGGLE_STATE_FOR_PUBLISH_ORGANIZATION, MessageDialogWithToggle.NEVER);
        store.setDefault(PUBLISH_ORGANIZATION, false);

        Locale defaultStudioLocal = Locale.getDefault();
        boolean defaultLocalExists = false;
        for (final Locale locale : LocaleUtil.getStudioLocales()) {
            if (locale.getLanguage().equals(defaultStudioLocal.getLanguage())) {
                defaultStudioLocal = locale;
                defaultLocalExists = true;
                break;
            }
        }

        String defaultLocaleValue = store.getString(DEFAULT_STUDIO_LOCALE);// Default value is compute on the first the studio is run
                                                                           // only because Locale.getDefault() is based on osgi.nl
                                                                           // property
        if (defaultLocaleValue == null || defaultLocaleValue.isEmpty()) {
            store.setValue(DEFAULT_STUDIO_LOCALE,
                    defaultLocalExists ? defaultStudioLocal.getLanguage() : Locale.ENGLISH.getLanguage());
            defaultLocaleValue = store.getString(DEFAULT_STUDIO_LOCALE);
        }

        store.setDefault(CURRENT_UXP_LOCALE, defaultLocaleValue != null ? defaultLocaleValue : Locale.ENGLISH.getLanguage());
        store.setDefault(CURRENT_STUDIO_LOCALE, defaultLocaleValue != null ? defaultLocaleValue : Locale.ENGLISH.getLanguage());

        store.setDefault(BonitaCoolBarPreferenceConstant.COOLBAR_DEFAULT_SIZE, BonitaCoolBarPreferenceConstant.NORMAL);
        store.setDefault(APLLICATION_DEPLOYMENT_MODE, ALL_IN_BAR);
        store.setDefault(PREF_ENABLE_VALIDATION, true);
        store.setDefault(DEFAULT_USERXP_THEME, "default");
        store.setDefault(DEFAULT_APPLICATION_THEME, "Default Application");
        store.setDefault(DEFAULT_ORGANIZATION, DEFAULT_ORGANIZATION_NAME);
        store.setDefault(VALIDATION_BEFORE_RUN, true);
        store.setDefault(ASK_RENAME_ON_FIRST_SAVE, true);
        store.setDefault(ALWAYS_USE_SCRIPTING_MODE, false);
        PrefUtil.getAPIPreferenceStore().setValue(IWorkbenchPreferenceConstants.DISABLE_OPEN_EDITOR_IN_PLACE, true);
    }

}
