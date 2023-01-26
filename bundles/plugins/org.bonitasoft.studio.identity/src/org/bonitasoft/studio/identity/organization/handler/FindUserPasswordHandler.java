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
package org.bonitasoft.studio.identity.organization.handler;

import java.util.Objects;
import java.util.Optional;

import javax.inject.Named;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.PasswordType;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.model.organization.Users;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.eclipse.e4.core.di.annotations.Execute;

public class FindUserPasswordHandler {

    @Execute
    public Optional<String> execute(RepositoryAccessor repositoryAccessor, @Named("userName") String userName) {
        String activeOrganizationFileName = new ActiveOrganizationProvider().getActiveOrganizationFileName();
        return Optional.ofNullable(repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class)
                .getChild(activeOrganizationFileName, true))
                .map(t -> {
                    try {
                        return t.getContent();
                    } catch (ReadFileStoreException e) {
                       BonitaStudioLog.warning(e.getMessage(), IdentityPlugin.PLUGIN_ID);
                       return null;
                    }
                })
                .map(Organization::getUsers)
                .map(Users::getUser)
                .map(users -> users.stream()
                        .filter(user -> Objects.equals(user.getUserName(), userName))
                        .findFirst().orElse(null))
                .map(User::getPassword)
                .map(PasswordType::getValue);
    }

}
