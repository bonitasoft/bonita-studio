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
package org.bonitasoft.studio.actors.repository;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.model.organization.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationFileStoreTest {

    @Mock
    private OrganizationFileStore ofs;

    @Test
    public void testExport() throws IOException {
        final Organization orga = OrganizationFactory.eINSTANCE.createOrganization();
        final User user = OrganizationFactory.eINSTANCE.createUser();
        user.setFirstName("ブリヂストンプラントエンジニアリング(株)");
        final Users users = OrganizationFactory.eINSTANCE.createUsers();
        orga.setUsers(users);
        users.getUser().add(user);
        Mockito.doReturn(orga).when(ofs).getContent();
        Mockito.doCallRealMethod().when(ofs).export(Mockito.anyString());

        final File exportedFile = File.createTempFile("testExportWIthUTF8" + new Date().getTime(), ".xml");
        exportedFile.delete();
        ofs.export(exportedFile.getAbsolutePath());

        try {
            final Scanner scanner = new Scanner(exportedFile, "UTF-8");

            //now read the file line by line...
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                if (line.contains("ブリヂストンプラントエンジニアリング(株)")) {
                    scanner.close();
                    return;
                }
            }
            scanner.close();
        } catch (final FileNotFoundException e) {
            //handle this
        }
        fail("UTF-8 characters are not well exported");
    }

}
