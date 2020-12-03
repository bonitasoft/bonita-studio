/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.team.preferences;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.team.svn.ui.preferences.SVNTeamPreferences;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TeamPreferenceInitializerTest {

    private TeamPreferenceInitializer teamPreferenceInitializer;
    private IPreferenceStore svnPreferenceStore;


    @Before
    public void setUp() throws Exception {
        teamPreferenceInitializer = new TeamPreferenceInitializer();
        svnPreferenceStore = new PreferenceStore();
    }

    @Test
    public void testSVNConnectorAvailable() {
        Assertions.assertThat(TeamPreferenceInitializer.DEFAULT_SVNKIT).isEqualTo("org.eclipse.team.svn.connector.svnkit18");
    }

    @Test
    public void should_initializeSvnConnectorDefaultValue_set_default_value_to_svnkit17() throws Exception {
        teamPreferenceInitializer.initializeSvnConnectorDefaultValue(svnPreferenceStore);

        final String coreName = SVNTeamPreferences.fullCoreName(SVNTeamPreferences.CORE_SVNCONNECTOR_NAME);

        assertThat(svnPreferenceStore.getDefaultString(coreName)).isEqualTo(TeamPreferenceInitializer.DEFAULT_SVNKIT);
    }
}
