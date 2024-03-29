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
package org.bonitasoft.studio.common.repository.preferences;

import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.egit.core.Activator;
import org.eclipse.egit.core.GitCorePreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class RepositoryPreferenceInitializer extends AbstractPreferenceInitializer
        implements RepositoryPreferenceConstant {

    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = CommonRepositoryPlugin.getDefault().getPreferenceStore();
        store.setDefault(DEFAULT_GROUPID, DEFAULT_GROUPID_VALUE);

        IPreferenceStore gitPreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE,
                Activator.PLUGIN_ID);
        gitPreferenceStore.setDefault(GitCorePreferences.core_autoShareProjects, false);
    }

}
