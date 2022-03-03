/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.organization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.identity.organization.validator.OrganizationValidator;
import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

/**
 * @author Aur�lie Zara
 */

public class TestImportOrganization {

    @Test
    public void testImportOrganization() throws Exception {
        final OrganizationRepositoryStore organizationStore = RepositoryManager.getInstance()
                .getRepositoryStore(OrganizationRepositoryStore.class);
        String organizationName = "OrganizationTest.xml";
        final File toImport = getFileToImport(organizationName);
        assertTrue("organization to import does not exist", toImport.exists());
        try (FileInputStream fis = new FileInputStream(toImport)) {
            organizationStore.importInputStream(toImport.getName(), fis);
        }
        final String orgaNameWithBosExtansion = organizationName.substring(0, organizationName.indexOf(".")) + "."
                + OrganizationRepositoryStore.ORGANIZATION_EXT;
        final Organization organizationTest = organizationStore.getChild(orgaNameWithBosExtansion, true).getContent();
        assertNotNull(orgaNameWithBosExtansion + " was not imported", organizationTest);
        assertTrue("not all groups have been imported", organizationTest.getGroups().getGroup().size() == 6);
        assertTrue("not all roles have been imported", organizationTest.getRoles().getRole().size() == 3);
        assertTrue("not all users have been imported", organizationTest.getUsers().getUser().size() == 2);
        final User user1 = organizationTest.getUsers().getUser().get(0);
        final User user2 = organizationTest.getUsers().getUser().get(1);
        assertEquals("user1 firstname is not imported correctly", user1.getFirstName(), "Aurore");
        assertEquals("user1 lastname is not imported correctly", user1.getLastName(), "Richard");
        final ArrayList<Membership> userms = new ArrayList<Membership>();
        final ArrayList<Membership> userms2 = new ArrayList<Membership>();
        for (final Membership ms : organizationTest.getMemberships().getMembership()) {
            if (ms.getUserName().equals(user1.getUserName())) {
                userms.add(ms);
            } else {
                if (ms.getUserName().equals(user2.getUserName())) {
                    userms2.add(ms);
                }
            }
        }
        assertEquals("memberships are not imported correctly", userms.size(), 1);
        assertEquals("memberships are not imported correctly", userms2.size(), 2);
        assertTrue(new OrganizationValidator().validate(organizationTest).isOK());
    }

    @Test
    public void testImportAlphaOrganization() throws Exception {
        final OrganizationRepositoryStore organizationStore = RepositoryManager.getInstance()
                .getRepositoryStore(OrganizationRepositoryStore.class);
        String organizationName = "ACME-Alpha.xml";
        final File toImport = getFileToImport(organizationName);
        assertTrue("organization to import does not exist", toImport.exists());
        try (FileInputStream fis = new FileInputStream(toImport)) {
            String id = toImport.getName();
            organizationStore.importInputStream(id, fis);
            final String orgaNameWithBosExtansion = organizationName.substring(0, organizationName.indexOf(".")) + "."
                    + OrganizationRepositoryStore.ORGANIZATION_EXT;
            final Organization organizationTest = organizationStore.getChild(orgaNameWithBosExtansion, true).getContent();
            assertNotNull(orgaNameWithBosExtansion + " was not imported", organizationTest);
        }
    }

    @Test
    public void testImportCorruptedOrganization() throws IOException {
        final OrganizationRepositoryStore organizationStore = RepositoryManager.getInstance()
                .getRepositoryStore(OrganizationRepositoryStore.class);
        final File toImport = getFileToImport("toto.xml");
        assertTrue("organization to import does not exist", toImport.exists());
        try (FileInputStream fis = new FileInputStream(toImport)) {
            String id = toImport.getName();
            final IRepositoryFileStore fStore = organizationStore.importInputStream(id, fis);
            assertNull(id + " was imported", fStore);
        }
    }

    @Test
    public void testImportOldOrganization() throws Exception {
        final OrganizationRepositoryStore organizationStore = RepositoryManager.getInstance()
                .getRepositoryStore(OrganizationRepositoryStore.class);
        final File toImport = getFileToImport("oldOrganizationTest.xml");
        assertTrue("organization to import does not exist", toImport.exists());
        try (FileInputStream fis = new FileInputStream(toImport)) {
            String id = toImport.getName();
            final IRepositoryFileStore fStore = organizationStore.importInputStream(id, fis);
            assertNotNull(id + " was imported", fStore);
        }
    }

    private File getFileToImport(String organizationName) throws IOException {
        final URL archiveURL = TestImportOrganization.class.getResource(organizationName);
        assertNotNull("filePath should not be null", archiveURL.getPath());
        return new File(FileLocator.toFileURL(archiveURL).getFile());
    }

}
