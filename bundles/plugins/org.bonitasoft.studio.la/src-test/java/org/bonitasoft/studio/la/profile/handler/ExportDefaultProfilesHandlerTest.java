/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.profile.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.profile.repository.ApplicationProfileFileStore;
import org.bonitasoft.studio.la.profile.repository.ApplicationProfileRepositoryStore;
import org.bonitasoft.studio.la.profile.repository.DefaultProfileContribution;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExportDefaultProfilesHandlerTest {

    @Mock
    private Shell activeShell;
    @Mock
    private RepositoryAccessor repositoryAccessor;
    @Mock
    private ExportProfileFileAction exportAction;
    @Mock
    private ApplicationProfileFileStore defaultProfileFileStore;
    @Mock
    private ApplicationProfileRepositoryStore profileStore;

    @Test
    public void should_export_default_profile() throws Exception {
        final ExportDefaultProfilesHandler exportDefaultProfilesHandler = new ExportDefaultProfilesHandler();
        when(repositoryAccessor.getRepositoryStore(ApplicationProfileRepositoryStore.class)).thenReturn(profileStore);
        when(profileStore.getChild(DefaultProfileContribution.DEFAULT_PROFILE_FILENAME)).thenReturn(defaultProfileFileStore);

        exportDefaultProfilesHandler.exportDefaultProfiles(activeShell, repositoryAccessor, exportAction);

        verify(exportAction).export(activeShell, defaultProfileFileStore);
    }

    @Test
    public void should_be_executable_if_default_profile_exists() throws Exception {
        final ExportDefaultProfilesHandler exportDefaultProfilesHandler = new ExportDefaultProfilesHandler();
        when(repositoryAccessor.getRepositoryStore(ApplicationProfileRepositoryStore.class)).thenReturn(profileStore);
        when(profileStore.getChild(DefaultProfileContribution.DEFAULT_PROFILE_FILENAME)).thenReturn(defaultProfileFileStore);

        exportDefaultProfilesHandler.exportDefaultProfiles(activeShell, repositoryAccessor, exportAction);

        assertThat(exportDefaultProfilesHandler.canExecute(repositoryAccessor)).isTrue();
    }

    @Test
    public void should_not_be_executable_if_default_profile_does_not_exist() throws Exception {
        final ExportDefaultProfilesHandler exportDefaultProfilesHandler = new ExportDefaultProfilesHandler();
        when(repositoryAccessor.getRepositoryStore(ApplicationProfileRepositoryStore.class)).thenReturn(profileStore);

        exportDefaultProfilesHandler.exportDefaultProfiles(activeShell, repositoryAccessor, exportAction);

        assertThat(exportDefaultProfilesHandler.canExecute(repositoryAccessor)).isFalse();
    }

}
