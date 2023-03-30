/**
 * Copyright (C) 2015 Bonitasoft S.A.
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

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.junit.Test;

public class PreferenceInitializerTest {

    @Test
    public void testLegacyModeDeactivatedByDefault() throws Exception {
        final PreferenceInitializer preferenceInitializer = new PreferenceInitializer();
        preferenceInitializer.initializeDefaultPreferences();
        
        assertThat(preferenceInitializer.getBonitaPreferenceStore().getDefaultBoolean(BonitaPreferenceConstants.SHOW_LEGACY_6X_MODE)).isFalse();
    }

    @Test
    public void should_disable_mark_occurence() throws Exception {
        final PreferenceInitializer preferenceInitializer = new PreferenceInitializer();
        preferenceInitializer.initializeDefaultPreferences();

        assertThat(preferenceInitializer.getJdtUiPreferenceStore().getBoolean(PreferenceConstants.EDITOR_MARK_OCCURRENCES)).isFalse();
    }
    
    @Test
    public void should_ignore_missingSerialUID() throws Exception {
        final PreferenceInitializer preferenceInitializer = new PreferenceInitializer();
        preferenceInitializer.initializeDefaultPreferences();

        assertThat(JavaCore.getOption(JavaCore.COMPILER_PB_MISSING_SERIAL_VERSION)).isEqualTo(JavaCore.IGNORE);
    }
    
    @Test
    public void should_enable_autobuild() throws Exception {
        final PreferenceInitializer preferenceInitializer = new PreferenceInitializer();
        preferenceInitializer.initializeDefaultPreferences();

        assertThat(ResourcesPlugin.getPlugin().getPluginPreferences().getBoolean(ResourcesPlugin.PREF_AUTO_BUILDING)).isTrue();
    }

  
}
