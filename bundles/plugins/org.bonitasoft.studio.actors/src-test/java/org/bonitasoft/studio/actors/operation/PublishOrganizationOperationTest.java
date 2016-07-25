/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.actors.operation;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.model.organization.Users;
import org.junit.Test;


public class PublishOrganizationOperationTest {

    @Test
    public void testToStringOrganization() throws IOException {
        final Organization organization = OrganizationFactory.eINSTANCE.createOrganization();
        final Users users = OrganizationFactory.eINSTANCE.createUsers();
        organization.setUsers(users);
        final User userWithUTF8Chars = OrganizationFactory.eINSTANCE.createUser();
        userWithUTF8Chars.setFirstName("社員番号");
        users.getUser().add(userWithUTF8Chars);
        final String string = new CleanPublishOrganizationOperation(organization).toString(organization);
        assertThat(string).contains("社員番号");
    }
}
