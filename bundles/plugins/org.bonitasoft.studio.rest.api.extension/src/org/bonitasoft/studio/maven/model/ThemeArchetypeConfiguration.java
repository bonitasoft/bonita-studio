/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.model;

import java.util.Properties;

import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.maven.i18n.Messages;

public class ThemeArchetypeConfiguration extends CustomPageArchetypeConfiguration {

    public static ThemeArchetypeConfiguration defaultArchetypeConfiguration() {
        final ThemeArchetypeConfiguration configuration = new ThemeArchetypeConfiguration();
        configuration.setPageName("myCustomTheme");
        configuration.setPageDisplayName(Messages.defaultThemeDisplayName);
        configuration.setPageDescription(Messages.defaultThemeDescription);
        configuration.setGroupId(defaultGroupId() + ".theme");
        configuration.setVersion("1.0.0-SNAPSHOT");
        return configuration;
    }

    @Override
    public Properties toProperties() {
        final Properties properties = new Properties();
        properties.setProperty("name", getPageName());
        properties.setProperty("displayName", getPageDisplayName());
        properties.setProperty("description", getPageDescription());
        properties.setProperty("wrapper","false");
        return properties;
    }

    @Override
    public String getArtifactLabel() {
        return Messages.themesRepositoryName;
    }
    
    private static String defaultGroupId() {
        return CommonRepositoryPlugin.getDefault().getPreferenceStore()
                .getString(RepositoryPreferenceConstant.DEFAULT_GROUPID);
    }

}
