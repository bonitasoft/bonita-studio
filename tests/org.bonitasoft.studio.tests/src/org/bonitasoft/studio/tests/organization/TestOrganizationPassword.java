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
package org.bonitasoft.studio.tests.organization;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.identity.organization.model.organization.PasswordType;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.organization.BotOrganizationEditor;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestOrganizationPassword {

    private SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Test
    public void testImportExportWithPasswordUpdated() throws IOException, ReadFileStoreException {
        importOrganizationProgrammatically();

        BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        BotOrganizationEditor botOrganizationEditor = botApplicationWorkbenchWindow.organizationMenu().open()
                .select("OrganizationWithEncryptedPassword.organization").open();

        botOrganizationEditor
                .userPage()
                .setPassword("User1 User1", "updatedPassord")
                .save()
                .close();

        SWTBotTestUtil.waitUntilRootShellIsActive(bot);

        OrganizationRepositoryStore organizationStore = RepositoryManager.getInstance()
                .getRepositoryStore(OrganizationRepositoryStore.class);
        OrganizationFileStore orgaFileStore = organizationStore.getChild("OrganizationWithEncryptedPassword."
                + OrganizationRepositoryStore.ORGANIZATION_EXT, true);
        PasswordType password = orgaFileStore.getContent().getUsers().getUser().get(0).getPassword();
        Assert.assertEquals("The passsword value should have been updated.", "updatedPassord", password.getValue());
        Assert.assertFalse("The password has been updated and the value of the attribute encrypted should be set to false",
                password.isEncrypted());
    }

    private void importOrganizationProgrammatically() throws IOException, FileNotFoundException {
        OrganizationRepositoryStore organizationStore = RepositoryManager.getInstance()
                .getRepositoryStore(OrganizationRepositoryStore.class);
        String organizationName = "OrganizationWithEncryptedPassword.xml";
        URL archiveURL = TestOrganizationPassword.class.getResource(organizationName);
        assertNotNull("filePath should not be null", archiveURL.getPath());
        File toImport = new File(FileLocator.toFileURL(archiveURL).getFile());
        assertTrue("organization to import does not exist", toImport.exists());
        FileInputStream fis = new FileInputStream(toImport);
        String id = toImport.getName();
        organizationStore.importInputStream(id, fis);
        if (fis != null) {
            fis.close();
        }
    }

}
