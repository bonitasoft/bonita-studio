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

import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.profile.repository.ApplicationProfileFileStore;
import org.bonitasoft.studio.la.profile.repository.ApplicationProfileRepositoryStore;
import org.bonitasoft.studio.la.profile.repository.DefaultProfileContribution;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

public class ExportDefaultProfilesHandler {

    @Execute
    public void exportDefaultProfiles(Shell activeShell, RepositoryAccessor repositoryAccessor,
            ExportProfileFileAction exportAction) {
        final ApplicationProfileRepositoryStore profileRepositoryStore = repositoryAccessor
                .getRepositoryStore(ApplicationProfileRepositoryStore.class);
        final ApplicationProfileFileStore defaultProfile = profileRepositoryStore
                .getChild(DefaultProfileContribution.DEFAULT_PROFILE_FILENAME);

        //TODO add default profile content validation
        exportAction.export(activeShell, defaultProfile);
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        final ApplicationProfileRepositoryStore profileRepositoryStore = repositoryAccessor
                .getRepositoryStore(ApplicationProfileRepositoryStore.class);
        return Optional.ofNullable(profileRepositoryStore.getChild(DefaultProfileContribution.DEFAULT_PROFILE_FILENAME))
                .isPresent();
    }

}
