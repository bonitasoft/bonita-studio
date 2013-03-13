/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.tests.organization;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Group;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.actors.tests.SWTbot.SWTBotActorFilterUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author aurelie Zara
 * 
 */

@RunWith(SWTBotJunit4ClassRunner.class)
public class OrganizationCreationTest extends SWTBotGefTestCase {

    @Test
    public void testAddOrganization() {
        final String firstName1 = "Coralie";
        final String lastName1 = "Auclair";
        final String firstName2 = "Vincent";
        final String lastName2 = "Gilbert";
        final String firstName3 = "Martin";
        final String lastName3 = "Dupuis";
        final String organizationName = "Organization1";
        final String[] membership1 = { "/Group1", "Role1" };
        final ArrayList<String[]> membershipList1 = new ArrayList<String[]>();
        membershipList1.add(membership1);
        final ArrayList<String[]> membershipList2 = new ArrayList<String[]>();
        final String[] membership2 = { "/Group1/Group3", "Role2" };
        membershipList2.add(membership2);
        final String[] membership3 = { "/Group1/Group2/Group4", "Role3" };
        membershipList2.add(membership3);
        SWTBotActorFilterUtil.activateNewOrganizationWizard(bot);
        final SWTBotShell shell = bot.shell(Messages.manageOrganizationTitle);
        bot.button(Messages.add).click();
        bot.table(0).select(organizationName);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(Messages.addParentGroup).click();
        bot.tree().select("Group1");
        bot.button(Messages.addSubGroup).click();
        bot.tree().select("Group1");
        bot.button(Messages.addSubGroup).click();
        bot.tree().getTreeItem("Group1").getNode("Group2").select();
        bot.button(Messages.addSubGroup).click();
        bot.button(Messages.addParentGroup).click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(Messages.add).click();
        bot.button(Messages.add).click();
        bot.button(Messages.add).click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        addNewUSer("user1",firstName1, lastName1,"", membershipList1);
        SWTBotTable table = bot.table();
        assertEquals("First Name " + firstName1 + " in table should be edited",
                firstName1, table.cell(0, Messages.firstName));
        assertEquals("Last Name " + lastName1 + " in table should be edited",
                lastName1, table.cell(0, Messages.lastName));
        addNewUSer("user2",firstName2, lastName2,"user1", membershipList2);
        assertEquals("First Name " + firstName2 + " in table should be edited",
                firstName2, table.cell(1, Messages.firstName));
        assertEquals("Last Name " + lastName2 + " in table should be edited",
                lastName2, table.cell(1, Messages.lastName));
        addNewUSer("user3",firstName3, lastName3,"user2", membershipList2);
        assertEquals("First Name " + firstName3 + " in table should be edited",
                firstName3, table.cell(2, Messages.firstName));
        assertEquals("Last Name " + lastName3 + " in table should be edited",
                lastName3, table.cell(2, Messages.lastName));

        bot.table().select(0);
        bot.comboBoxWithLabel(Messages.manager).setSelection("user3");
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)),10000);
        bot.button(IDialogConstants.FINISH_LABEL).click();

        bot.waitUntil(Conditions.shellIsActive(Messages.organizationValidationFailed),10000);
        bot.button(IDialogConstants.OK_LABEL).click();

        bot.table().select(0);
        bot.comboBoxWithLabel(Messages.manager).setSelection("");

        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)),10000);
        bot.button(IDialogConstants.FINISH_LABEL).click();

        bot.waitUntil(Conditions.shellCloses(shell));

        OrganizationRepositoryStore store = (OrganizationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class);
        OrganizationFileStore fileStore = store.getChild(organizationName+"."+OrganizationRepositoryStore.ORGANIZATION_EXT);
        Organization orga = fileStore.getContent();
        assertNotNull(orga);
        int nbRootGroup = 0 ;
        for(Group g : orga.getGroups().getGroup()){
            if(g.getParentPath() == null){
                nbRootGroup++;
            }
        }
        assertEquals("There should be two root groups",2, nbRootGroup);
        synchronizeOrganization(organizationName);
        synchronizeOrganization("ACME");
    }

    private void addNewUSer(String username,String firstName, String lastName,String manager,
            List<String[]> memberShip) {
        bot.button(Messages.add).click();
        bot.tabItem(Messages.general).activate();
        bot.textWithLabel(Messages.userName).setText(username);
        if(manager != null && !manager.isEmpty()){
            bot.comboBoxWithLabel(Messages.manager).setSelection(manager);
        }
        bot.textWithLabel(Messages.firstName).setText(firstName);
        bot.textWithLabel(Messages.lastName).setText(lastName);
        bot.tabItem(Messages.membership).activate();
        for (int i = 0; i < memberShip.size(); i++) {
            bot.button(Messages.addMembership).click();
            bot.comboBoxWithLabel("Group", i * 2).setSelection(
                    memberShip.get(i)[0]);
            bot.comboBoxWithLabel("Role", i * 2).setSelection(
                    memberShip.get(i)[1]);
        }
    }

    private void synchronizeOrganization(String organizationName) {
        SWTBotActorFilterUtil.activateSynchronizeOrganizationWizard(bot);
        bot.table().select(organizationName);
        bot.button(Messages.synchronize).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.synchronizeInformationTitle),
                1500000);
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    /**
     * @author Florine Boudin
     * @throws InterruptedException
     */
    @Test
    public void addNewUsersInACMETest() throws InterruptedException  {

        //SWTBotTestUtil.createNewDiagram(bot);
        // open shell "Manage organization"
        bot.menu("Organization").menu("Manage...").click();
        bot.waitUntil(Conditions.shellIsActive(Messages.manageOrganizationTitle));

        SWTBotTable table = bot.table();
        Assert.assertNotNull(table);

        System.out.println("Table size = "+ table.columnCount()+" x "+table.rowCount());

        // Set Description of the new Organisation
        int idxBonita = table.indexOf("ACME  (active)", 0);
        Assert.assertTrue("Error: No ACME found in the table", idxBonita!=-1);

        // go to the next shell
        table.click(idxBonita, 0);

        for(int i=0;i<3;i++){
            Thread.sleep(1000);
            Assert.assertTrue("Error: The NEXT label button is unavailable", bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
            bot.button(IDialogConstants.NEXT_LABEL).click();
        }


        // in the user shell, get table of user list
        table = bot.table();
        Assert.assertNotNull("Error: No user table found", table);

        int nbUsers = table.rowCount();

        //add new user Elton John
        SWTBotButton addButton = bot.button("Add");

        addButton.click();

        Assert.assertEquals("Error : wrong number of added users", nbUsers+1, table.rowCount());

        bot.textWithLabel(Messages.userName).setText("elton.john");
        bot.textWithLabel(Messages.password).setText("bpm");


        bot.comboBoxWithLabel(Messages.manager).setSelection("william.jobs");
        Assert.assertEquals("Error: Manager is not selected","william.jobs", bot.comboBoxWithLabel(Messages.manager).getText());

        bot.tabItem("General").activate();
        bot.textWithLabel(Messages.firstName).setText("Elton");
        bot.textWithLabel(Messages.lastName).setText("John");

        Assert.assertEquals("Error: First name user is not setted","Elton", bot.textWithLabel(Messages.firstName).getText());
        Assert.assertEquals("Error: Last name user is not setted","John", bot.textWithLabel(Messages.lastName).getText());


        bot.tabItem(Messages.membership).activate();
        bot.button(Messages.addMembership).click();
        bot.comboBoxWithLabel("Group").setSelection("/acme");
        bot.comboBoxWithLabel("Role").setSelection("member");

        // Finish the user add
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)));
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }


}
