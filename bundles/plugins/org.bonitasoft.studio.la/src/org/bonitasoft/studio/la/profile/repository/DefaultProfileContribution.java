/**
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terMrs of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.la.profile.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.engine.profile.xml.ProfileMappingNode;
import org.bonitasoft.engine.profile.xml.ProfileNode;
import org.bonitasoft.engine.profile.xml.ProfilesNode;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.repository.DefaultOrganizationContribution;
import org.bonitasoft.studio.common.repository.model.IFileStoreContribution;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;

public class DefaultProfileContribution implements IFileStoreContribution {

    private static final String DEFAULT_PROFILE_FILENAME = "default_profile.xml";
    private static final String DEFAULT_USER_ID = "User";
    private static final String DEFAULT_ADMINISTRATOR_ID = "Administrator";
    private static final String DEFAULT_PROCESS_MANAGER_ID = "Process manager";
    private List<User> defaultUsers = new ArrayList<>();

    @Override
    public boolean appliesTo(IRepositoryStore<? extends IRepositoryFileStore> repository) {
        return repository instanceof ApplicationProfileRepositoryStore;
    }

    @Override
    public void execute(IRepositoryStore<? extends IRepositoryFileStore> repository) {
        if (Objects.isNull(repository.getChild(DEFAULT_PROFILE_FILENAME))) {
            IRepositoryFileStore file = repository.createRepositoryFileStore(DEFAULT_PROFILE_FILENAME);
            ProfilesNode defaultProfile = new ProfilesNode(Arrays.asList(createProfile(DEFAULT_USER_ID),
                    createProfile(DEFAULT_ADMINISTRATOR_ID), createProfile(DEFAULT_PROCESS_MANAGER_ID)));
            file.save(defaultProfile);
        }
    }

    private ProfileNode createProfile(String profileName) {
        ProfileNode profileNode = new ProfileNode(profileName, true);
        profileNode.setParentProfileEntries(null); // so tag won't be visible in xml
        addAllUsers(profileNode);
        return profileNode;
    }

    protected void addAllUsers(ProfileNode profile) {
        ProfileMappingNode mappingNode = new ProfileMappingNode();
        mappingNode.setUsers(getDefaultUsers()
                .stream()
                .map(User::getUserName)
                .collect(Collectors.toList()));
        profile.setProfileMapping(mappingNode);
    }

    protected List<User> getDefaultUsers() {
        if (defaultUsers.isEmpty()) {
            defaultUsers = new DefaultOrganizationContribution().getDefaultUsers();
        }
        return defaultUsers;
    }
}
