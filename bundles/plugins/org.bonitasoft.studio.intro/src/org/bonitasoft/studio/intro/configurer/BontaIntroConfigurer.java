/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.intro.configurer;

import java.util.Objects;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.ui.intro.config.IntroConfigurer;
import org.eclipse.ui.intro.config.IntroElement;

public class BontaIntroConfigurer extends IntroConfigurer {

    @Override
    public String getVariable(String variableName) {
        switch (variableName) {
            case "theme":
                return isDarkTheme()
                        ? "themes/dark_theme.css"
                        : "themes/default_theme.css";
            default:
                return null;
        }
    }

    private boolean isDarkTheme() {
        String value = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getString(BonitaPreferenceConstants.STUDIO_THEME_PREFERENCE);
        return Objects.equals(value, BonitaPreferenceConstants.DARK_THEME);
    }

    @Override
    public IntroElement[] getGroupChildren(String pageId, String groupId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String resolvePath(String extensionId, String path) {
        // TODO Auto-generated method stub
        return null;
    }

}
