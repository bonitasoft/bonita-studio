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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.engine.profile.xml.ProfileNode;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.User;
import org.junit.Test;

public class DefaultProfileContributionTest {

    @Test
    public void should_add_all_users() {
        DefaultProfileContribution defaultProfileContribution = spy(new DefaultProfileContribution());
        doReturn(createDefaultOrganization()).when(defaultProfileContribution).getDefaultUsers();

        ProfileNode userProfile = new ProfileNode("user", true);
        defaultProfileContribution.addAllUsers(userProfile);

        List<String> users = userProfile.getProfileMapping().getUsers();
        assertThat(users).contains("walter.bates", "april.sanchez");
    }

    private List<User> createDefaultOrganization() {
        User walterBates = OrganizationFactory.eINSTANCE.createUser();
        walterBates.setUserName("walter.bates");
        User aprilSanchez = OrganizationFactory.eINSTANCE.createUser();
        aprilSanchez.setUserName("april.sanchez");

        return Arrays.asList(walterBates, aprilSanchez);
    }
}
