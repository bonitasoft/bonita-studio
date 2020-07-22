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
package org.bonitasoft.studio.tests.actors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.bonitasoft.studio.actors.model.organization.PasswordType;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.organization.BotManageOrganizationWizard;
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
    public void testImportExportWithPasswordUpdated() throws IOException {
        importOrganizationProgrammatically();

        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotManageOrganizationWizard manageOrganizationWizard = botApplicationWorkbenchWindow.organizationMenu()
                .manage();
        manageOrganizationWizard.selectOrganization("OrganizationWithEncryptedPassword");

        manageOrganizationWizard.next(/* Group page */).next(/* Role page */).next();
        manageOrganizationWizard.manageUsers().selectUser("User1").setPassword("updatedPassord");
        manageOrganizationWizard.finish();

        SWTBotTestUtil.waitUntilRootShellIsActive(bot);

        final OrganizationRepositoryStore organizationStore = RepositoryManager.getInstance()
                .getRepositoryStore(OrganizationRepositoryStore.class);
        final OrganizationFileStore orgaFileStore = organizationStore.getChild("OrganizationWithEncryptedPassword."
                + OrganizationRepositoryStore.ORGANIZATION_EXT, true);
        final PasswordType password = orgaFileStore.getContent().getUsers().getUser().get(0).getPassword();
        Assert.assertEquals("The passsword value should have been updated.", "updatedPassord", password.getValue());
        Assert.assertFalse("The password has been updated and the value of the attribute encrypted should be set to false",
                password.isEncrypted());
    }

    private void importOrganizationProgrammatically() throws IOException, FileNotFoundException {
        final OrganizationRepositoryStore organizationStore = RepositoryManager.getInstance()
                .getRepositoryStore(OrganizationRepositoryStore.class);
        final String organizationName = "OrganizationWithEncryptedPassword.xml";
        final URL archiveURL = TestOrganizationPassword.class.getResource(organizationName);
        assertNotNull("filePath should not be null", archiveURL.getPath());
        final File toImport = new File(FileLocator.toFileURL(archiveURL).getFile());
        assertTrue("organization to import does not exist", toImport.exists());
        FileInputStream fis = new FileInputStream(toImport);
        String id = toImport.getName();
        organizationStore.importInputStream(id, fis);
        if (fis != null) {
            fis.close();
        }
    }

}
