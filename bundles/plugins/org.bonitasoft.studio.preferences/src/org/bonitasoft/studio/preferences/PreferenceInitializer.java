/*
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.preferences;

import java.util.Locale;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.eclipse.ui.internal.util.PrefUtil;

/**
 * @author Romain Bioteau
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        IPreferenceStore webStore = WebBrowserUIPlugin.getInstance().getPreferenceStore();
        ResourcesPlugin.getPlugin().getPluginPreferences().setDefault(ResourcesPlugin.PREF_ENCODING, "UTF-8");
        final String consolePortSpecifiedAsSystemProperty = System.getProperty(BonitaPreferenceConstants.CONSOLE_PORT);
        if(consolePortSpecifiedAsSystemProperty != null
                && !consolePortSpecifiedAsSystemProperty.isEmpty()){
            try{
                int port = Integer.parseInt(consolePortSpecifiedAsSystemProperty);
                store.setDefault(BonitaPreferenceConstants.CONSOLE_PORT, port);
            } catch (Exception e) {
                store.setDefault(BonitaPreferenceConstants.CONSOLE_PORT, 8080);
            }
        } else {
            store.setDefault(BonitaPreferenceConstants.CONSOLE_PORT, 8080);
        }
        store.setDefault(BonitaPreferenceConstants.CONSOLE_HOST, "localhost");
        webStore.setDefault(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.EXTERNAL_BROWSER);

        store.setDefault(BonitaPreferenceConstants.USER_NAME, BonitaPreferenceConstants.USER_NAME_DEFAULT);
        store.setDefault(BonitaPreferenceConstants.USER_PASSWORD, BonitaPreferenceConstants.USER_PASSWORD_DEFAULT);
        store.setDefault(BonitaPreferenceConstants.SHOW_CONDITION_ON_TRANSITION, "false");
        store.setDefault(BonitaPreferenceConstants.DELETE_TENANT_ON_EXIT, true);
        store.setDefault(BonitaPreferenceConstants.LOAD_ORGANIZATION, true);
        store.setDefault(BonitaPreferenceConstants.AUTOMATIC_ID, "true");
        store.setDefault(BonitaPreferenceConstants.TOGGLE_STATE_FOR_PUBLISH_ORGANIZATION,MessageDialogWithToggle.NEVER);
        store.setDefault(BonitaPreferenceConstants.PUBLISH_ORGANIZATION, false);

        Locale defaultLocal = Locale.getDefault();
        boolean defaultLocalExists=false;
        for (Locale locale : BonitaPreferenceConstants.AVAILABLE_LOCALES) {
        	if(locale.getLanguage().equals(defaultLocal.getLanguage())){
        		defaultLocal = locale;
        		defaultLocalExists = true;
        		break;
        	}
        }

        boolean defaultUseXPLocalExists=false;
        for (Locale locale : BonitaPreferenceConstants.AVAILABLE_LOCALES_USER_XP) {
        	if(locale.getLanguage().equals(defaultLocal.getLanguage())){
        		defaultLocal = locale;
        		defaultUseXPLocalExists = true;
        		break;
        	}
        }

        if(store.getString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE) == null || store.getString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE).isEmpty() ){
            store.setDefault(BonitaPreferenceConstants.CURRENT_UXP_LOCALE, defaultUseXPLocalExists? defaultLocal.getLanguage():Locale.ENGLISH.getLanguage());
            store.setValue(BonitaPreferenceConstants.CURRENT_UXP_LOCALE, defaultUseXPLocalExists? defaultLocal.getLanguage():Locale.ENGLISH.getLanguage());
        }
       String defaultLocaleValue = store.getString(BonitaPreferenceConstants.DEFAULT_STUDIO_LOCALE);//Default value is compute on the first the studio is run only because Locale.getDefault() is based on osgi.nl property
        if( defaultLocaleValue == null || defaultLocaleValue.isEmpty()){
        	store.setValue(BonitaPreferenceConstants.DEFAULT_STUDIO_LOCALE, defaultLocalExists? defaultLocal.getLanguage():Locale.ENGLISH.getLanguage());
        	defaultLocaleValue = store.getString(BonitaPreferenceConstants.DEFAULT_STUDIO_LOCALE);
        }
    	store.setDefault(BonitaPreferenceConstants.CURRENT_STUDIO_LOCALE, defaultLocaleValue != null? defaultLocaleValue:Locale.ENGLISH.getLanguage());

        store.setDefault(BonitaCoolBarPreferenceConstant.COOLBAR_DEFAULT_SIZE, BonitaCoolBarPreferenceConstant.NORMAL) ;
        store.setDefault(BonitaPreferenceConstants.APLLICATION_DEPLOYMENT_MODE, BonitaPreferenceConstants.ALL_IN_BAR) ;
        store.setDefault(BonitaPreferenceConstants.PREF_ENABLE_VALIDATION, true) ;
        store.setDefault(BonitaPreferenceConstants.DEFAULT_USERXP_THEME, "default") ;
        store.setDefault(BonitaPreferenceConstants.DEFAULT_APPLICATION_THEME, "Default Application") ;
        store.setDefault(BonitaPreferenceConstants.DEFAULT_ORGANIZATION, BonitaPreferenceConstants.DEFAULT_ORGANIZATION_NAME) ;
        store.setDefault(BonitaPreferenceConstants.VALIDATION_BEFORE_RUN, true) ;
        store.setDefault(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, true);
        store.setDefault(BonitaPreferenceConstants.ALWAYS_USE_SCRIPTING_MODE, false);
        PrefUtil.getAPIPreferenceStore().setValue(IWorkbenchPreferenceConstants.DISABLE_OPEN_EDITOR_IN_PLACE, true);
    }

}
