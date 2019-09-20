/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.deploy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.process.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.business.application.ApplicationSearchDescriptor;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.page.PageNotFoundException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.operation.UndeployProcessOperation;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.application.BotDeployDialog;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class DeployWizardIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Before
    public void cleanRepository() throws Exception {
        new UndeployProcessOperation(BOSEngineManager.getInstance())
                .undeployAll().run(Repository.NULL_PROGRESS_MONITOR);
        Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        currentRepository.getAllStores().stream().filter(store -> !OrganizationRepositoryStore.class.isInstance(store))
                .flatMap(store -> store.getChildren().stream())
                .filter(fStore -> fStore.canBeDeleted()).forEach(IRepositoryFileStore::delete);
    }

    @Test
    public void should_deploy_a_project_using_wizard() throws Exception {
        BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);

        BotDeployDialog botDeployDialog = botApplicationWorkbenchWindow.importBOSArchive()
                .setArchive(DeployWizardIT.class.getResource("/DeployAll-1.0.bos"))
                .importAndDeploy();

        assertThat(botDeployDialog.isCleanBDMDatabaseEnabled()).isTrue();
        assertThat(botDeployDialog.isCleanBDMDatabaseSelected()).isFalse();
        assertThat(botDeployDialog.isValidateSelected()).isTrue();
        assertThat(botDeployDialog.isDefaultUserEnabled()).isTrue();

        botDeployDialog.runValidation(false);

        //Check select none/all/latest
        botDeployDialog.selectNone();
        assertThat(botDeployDialog.isDeployEnabled()).isFalse();
        botDeployDialog.selectAll();
        assertThat(botDeployDialog.isDeployEnabled()).isTrue();
        botDeployDialog.selectLatest();
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("1.0").isChecked()).isFalse();
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("2.0").isChecked()).isTrue();

        //Check username validation
        botDeployDialog.setDefaultUser("john.doe");
        assertThat(botDeployDialog.isDeployEnabled()).isFalse();
        botDeployDialog.setDefaultUser("helen.kelly");
        assertThat(botDeployDialog.isDeployEnabled()).isTrue();

        botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Organization").getItems()[0].uncheck();
        assertThat(botDeployDialog.isDefaultUserEnabled()).isFalse();

        long timeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 500;
            bot.ccomboBoxWithLabelInGroup(Messages.environment, Messages.deployOptions);
            fail("Environment combo should not be visible in community edition");
        } catch (WidgetNotFoundException e) {
            // Environment combo should not be there in community
        } finally {
            SWTBotPreferences.TIMEOUT = timeout;
        }

        botDeployDialog.deploy();
        assertThat(bot.button(IDialogConstants.OPEN_LABEL).isEnabled()).isTrue();
        assertThat(bot.ccomboBox().getText()).contains("My App -- User");
        bot.button(IDialogConstants.CLOSE_LABEL).click();

        checkDeployedContent();

        //Check last selected elements are saved
        botDeployDialog = botApplicationWorkbenchWindow.openDeploy();
        assertThat(botDeployDialog.isValidateSelected()).isFalse();
        assertThat(
                botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Organization").getItems()[0].isChecked())
                        .isFalse();
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("1.0").isChecked()).isFalse();
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("2.0").isChecked()).isTrue();

        botDeployDialog.cancel();
    }

    private void checkDeployedContent() throws LoginException, BonitaHomeNotSetException, ServerAPIException,
            UnknownAPITypeException, ProcessDefinitionNotFoundException, PageNotFoundException, SearchException {
        BOSEngineManager manager = BOSEngineManager.getInstance();
        APISession apiSession = manager.loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
        try {
            ProcessAPI processAPI = manager.getProcessAPI(apiSession);
            PageAPI pageAPI = manager.getPageAPI(apiSession);
            ApplicationAPI appAPI = manager.getApplicationAPI(apiSession);

            processAPI.getProcessDefinitionId("Pool", "2.0");
            processAPI.getProcessDefinitionId("TestProcess", "1.0");
            try {
                processAPI.getProcessDefinitionId("Pool", "1.0");
                fail("Pool 1.0 should not have been deployed");
            } catch (ProcessDefinitionNotFoundException e) {
                //Pool 1.0 should not have been deployed
            }
            pageAPI.getPageByName("custompage_newPage");
            assertThat(appAPI
                    .searchApplications(
                            new SearchOptionsBuilder(0, 1).filter(ApplicationSearchDescriptor.TOKEN, "myApp").done())
                    .getCount()).isEqualTo(1);
        } finally {
            if (apiSession != null) {
                manager.logoutDefaultTenant(apiSession);
            }
        }

    }

}
