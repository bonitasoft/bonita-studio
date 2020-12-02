/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.ui.handler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.e4.core.di.annotations.Execute;

public class GetAllUsersHandler {

    @Execute
    public List<String> execute(RepositoryAccessor repositoryAccessor) {
        String fileName = getActiveOrgaFileName();
        if (fileName != null) {
            OrganizationFileStore fileStore = repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class)
                    .getChild(fileName, true);
            if (fileStore != null) {
                try {
                    return fileStore.getContent().getUsers().getUser()
                            .stream()
                            .map(User::getUserName)
                            .sorted()
                            .collect(Collectors.toList());
                } catch (ReadFileStoreException e) {
                    BonitaStudioLog.warning(e.getMessage(), ActorsPlugin.PLUGIN_ID);
                    return Collections.emptyList();
                }
            }
        }
        return Collections.emptyList();
    }

    protected String getActiveOrgaFileName() {
        return new ActiveOrganizationProvider().getActiveOrganizationFileName();
    }

}
