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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Locale;

import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PreferenceInitializerTest {

    @Mock
    public IPreferenceStore apiPrefStore, webPrefStore, bonitaPrefStore, jdtPrefStore, dslPreferenceStore, workbenchStore;

    @Test
    public void testLegacyModeDeactivatedByDefault() throws Exception {
        final PreferenceInitializer preferenceInitializer = spy(new PreferenceInitializer());
        setupMocks(preferenceInitializer);

        preferenceInitializer.initializeDefaultPreferences();

        verify(bonitaPrefStore).setDefault(BonitaPreferenceConstants.SHOW_LEGACY_6X_MODE, false);
    }

    @Test
    public void should_disable_mark_occurence() throws Exception {
        final PreferenceInitializer preferenceInitializer = spy(new PreferenceInitializer());
        setupMocks(preferenceInitializer);

        preferenceInitializer.initializeDefaultPreferences();

        verify(jdtPrefStore).setValue(PreferenceConstants.EDITOR_MARK_OCCURRENCES, Boolean.FALSE);
    }


    private void setupMocks(final PreferenceInitializer preferenceInitializer) {
        doReturn(apiPrefStore).when(preferenceInitializer).getAPIPreferenceStore();
        doReturn(webPrefStore).when(preferenceInitializer).getWebBrowserPreferenceStore();
        doReturn(bonitaPrefStore).when(preferenceInitializer).getBonitaPreferenceStore();
        doReturn(jdtPrefStore).when(preferenceInitializer).getJDTPreferenceStore();
        doReturn(workbenchStore).when(preferenceInitializer).getWorbenchPreferences();
        doReturn(new Locale[] {}).when(preferenceInitializer).getStudioLocales();
        doNothing().when(preferenceInitializer).setUTF8DefaultEncoding();
        doNothing().when(preferenceInitializer).initDefaultDebugPreferences();
    }
}
