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
package org.bonitasoft.studio.preferences;

import java.util.Locale;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.internal.core.IInternalDebugCoreConstants;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.preferences.IDebugPreferenceConstants;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IPreferenceConstants;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.eclipse.ui.internal.util.PrefUtil;

/**
 * @author Romain Bioteau
 *         Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer implements BonitaPreferenceConstants {


    @Override
    public void initializeDefaultPreferences() {
        final IPreferenceStore store = getBonitaPreferenceStore();
        final IPreferenceStore webStore = getWebBrowserPreferenceStore();
        setUTF8DefaultEncoding();
        initDefaultWebPreferences(store, webStore);

        store.setDefault(SHOW_CONDITION_ON_TRANSITION, "false");
        store.setDefault(DELETE_TENANT_ON_EXIT, false);
        store.setDefault(LOAD_ORGANIZATION, false);
        store.setDefault(AUTOMATIC_ID, "true");

        initDefaultLocalesPreference(store);

        store.setDefault(BonitaCoolBarPreferenceConstant.COOLBAR_DEFAULT_SIZE, BonitaCoolBarPreferenceConstant.SMALL);
        store.setDefault(APLLICATION_DEPLOYMENT_MODE, ALL_IN_BAR);
        store.setDefault(PREF_ENABLE_VALIDATION, true);
        store.setDefault(DEFAULT_USERXP_THEME, "default");
        store.setDefault(DEFAULT_APPLICATION_THEME, "Default Application");
        store.setDefault(VALIDATION_BEFORE_RUN, true);
        store.setDefault(ASK_RENAME_ON_FIRST_SAVE, true);
        store.setDefault(ALWAYS_USE_SCRIPTING_MODE, false);
        store.setDefault(SHOW_LEGACY_6X_MODE, false);
        store.setDefault(UID_JVM_OPTS, "-Xmx256m");
        store.setDefault(BonitaPreferenceConstants.CUSTOM_PAGE_DEBUG, false);
        getAPIPreferenceStore().setValue(IWorkbenchPreferenceConstants.DISABLE_OPEN_EDITOR_IN_PLACE, true);

        initDefaultDebugPreferences();

        final IPreferenceStore jdtUIStore = getJDTPreferenceStore();
        jdtUIStore.setValue(PreferenceConstants.EDITOR_MARK_OCCURRENCES, Boolean.FALSE);

        getWorbenchPreferences().setDefault(IPreferenceConstants.RUN_IN_BACKGROUND, false);
    }


    protected IPreferenceStore getWorbenchPreferences() {
        return WorkbenchPlugin.getDefault().getPreferenceStore();
    }


    protected IPreferenceStore getJDTPreferenceStore() {
        return PreferenceConstants.getPreferenceStore();
    }

    protected IPreferenceStore getAPIPreferenceStore() {
        return PrefUtil.getAPIPreferenceStore();
    }

    protected void setUTF8DefaultEncoding() {
        final IEclipsePreferences node = DefaultScope.INSTANCE.getNode(ResourcesPlugin.PI_RESOURCES);
        node.put(ResourcesPlugin.PREF_ENCODING, "UTF-8");
    }

    protected IPreferenceStore getWebBrowserPreferenceStore() {
        return WebBrowserUIPlugin.getInstance().getPreferenceStore();
    }

    protected IPreferenceStore getBonitaPreferenceStore() {
        return BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
    }

    protected void initDefaultWebPreferences(final IPreferenceStore bonitaStore, final IPreferenceStore webStore) {
        final String consolePortSpecifiedAsSystemProperty = System.getProperty(CONSOLE_PORT);
        if (consolePortSpecifiedAsSystemProperty != null
                && !consolePortSpecifiedAsSystemProperty.isEmpty()) {
            try {
                bonitaStore.setDefault(CONSOLE_PORT, Integer.parseInt(consolePortSpecifiedAsSystemProperty));
            } catch (final Exception e) {
                bonitaStore.setDefault(CONSOLE_PORT, DEFAULT_PORT);
            }
        } else {
            bonitaStore.setDefault(CONSOLE_PORT, DEFAULT_PORT);
        }
        bonitaStore.setDefault(CONSOLE_HOST, DEFAULT_HOST);
        webStore.setDefault(CONSOLE_BROWSER_CHOICE, EXTERNAL_BROWSER);
    }

    protected void initDefaultLocalesPreference(final IPreferenceStore bonitaPreferenceStore) {
        Locale defaultStudioLocal = Locale.getDefault();
        boolean defaultLocalExists = false;
        for (final Locale locale : getStudioLocales()) {
            if (locale.getLanguage().equals(defaultStudioLocal.getLanguage())) {
                defaultStudioLocal = locale;
                defaultLocalExists = true;
                break;
            }
        }

        String defaultLocaleValue = bonitaPreferenceStore.getString(DEFAULT_STUDIO_LOCALE);// Default value is compute on the first the studio is run
        // only because Locale.getDefault() is based on osgi.nl
        // property
        if (defaultLocaleValue == null || defaultLocaleValue.isEmpty()) {
            bonitaPreferenceStore.setValue(DEFAULT_STUDIO_LOCALE,
                    defaultLocalExists ? defaultStudioLocal.getLanguage() : Locale.ENGLISH.getLanguage());
            defaultLocaleValue = bonitaPreferenceStore.getString(DEFAULT_STUDIO_LOCALE);
        }

        bonitaPreferenceStore.setDefault(CURRENT_UXP_LOCALE, defaultLocaleValue(defaultLocaleValue));
        bonitaPreferenceStore.setDefault(CURRENT_STUDIO_LOCALE, defaultLocaleValue(defaultLocaleValue));
        LocaleUtil.DEFAULT_LOCALE = Locale.forLanguageTag(defaultLocaleValue(defaultLocaleValue));
    }

    private String defaultLocaleValue(final String defaultLocaleValue) {
        return defaultLocaleValue != null ? defaultLocaleValue : Locale.ENGLISH.getLanguage();
    }

    protected Locale[] getStudioLocales() {
        return LocaleUtil.getStudioLocales();
    }

    protected void initDefaultDebugPreferences() {
        if (PlatformUI.isWorkbenchRunning()) {
            DebugUIPlugin.getDefault().getPreferenceStore().setDefault(IDebugPreferenceConstants.CONSOLE_OPEN_ON_OUT, false);
            DebugUIPlugin.getDefault().getPreferenceStore().setDefault(IDebugPreferenceConstants.CONSOLE_OPEN_ON_ERR, false);
            DebugPlugin.getDefault().getPluginPreferences().setDefault(
                    IInternalDebugCoreConstants.PREF_ENABLE_STATUS_HANDLERS,
                    false);
        }
    }

}
