/**
 * Copyright (C) 2010 BonitaSoft S.A.
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

package org.bonitasoft.studio.engine.preferences;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.designer.core.preference.DesignerPreferenceConstants;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.preferences.extension.IPreferenceInitializerExtension;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;

public class EnginePreferencesInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        final IPreferenceStore store = EnginePlugin.getDefault().getPreferenceStore();
        store.setDefault(EnginePreferenceConstants.CURRENT_CONFIG, EnginePreferenceConstants.DEFAULT_CONFIG);
        store.setDefault(EnginePreferenceConstants.REMOTE_DEPLOYMENT_CHOICE, EnginePreferenceConstants.STANDARD_MODE);
        store.setDefault(EnginePreferenceConstants.TOGGLE_STATE_FOR_NO_INITIATOR, MessageDialogWithToggle.NEVER);
        store.setDefault(EnginePreferenceConstants.TOGGLE_STATE_FOR_CONTRACT_AND_NOFORM_AND_INITIATOR,
                MessageDialogWithToggle.NEVER);
        store.setDefault(EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_EXIT_PREF, false);
        store.setDefault(EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_INSTALL, false);
        store.setDefault(DesignerPreferenceConstants.FORCE_INTERNAL_FORM_MAPPING, true);
        store.setDefault(EnginePreferenceConstants.LAZYLOAD_ENGINE, false);
        store.setDefault(EnginePreferenceConstants.TOMCAT_XMX_OPTION, 512);
        store.setDefault(EnginePreferenceConstants.TOMCAT_EXTRA_PARAMS, getDefaultOr(EnginePreferenceConstants.TOMCAT_EXTRA_PARAMS,"-DnoCacheCustomPage=true"));

        if (PlatformUI.isWorkbenchRunning()) {
            DebugUITools.getPreferenceStore().setValue(
                    org.eclipse.debug.internal.ui.IInternalDebugUIConstants.PREF_SAVE_DIRTY_EDITORS_BEFORE_LAUNCH,
                    MessageDialogWithToggle.NEVER);
        }
    }

    protected String getDefaultOr(String key, String defaultValue) {
        IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(IPreferenceInitializerExtension.EXTENSION_POINT_ID);
        for (IConfigurationElement iConfigurationElement : elements) {
            try {
                IPreferenceInitializerExtension extension = (IPreferenceInitializerExtension) iConfigurationElement.createExecutableExtension("class");
                if(extension.appliesTo(EnginePlugin.getDefault().getPreferenceStore())) {
                    return extension
                            .getDefaultString(key)
                            .orElse(defaultValue);
                }
            } catch (CoreException e) {
               BonitaStudioLog.error(e);
            }
        }
        return defaultValue;
    }
}
