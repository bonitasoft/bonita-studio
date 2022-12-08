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
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.operation.UndeployProcessOperation;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.application.BotDeployDialog;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
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
    @After
    public void cleanRepository() throws Exception {
        new UndeployProcessOperation(BOSEngineManager.getInstance())
                .undeployAll().run(AbstractRepository.NULL_PROGRESS_MONITOR);
        var currentRepository = RepositoryManager.getInstance().getCurrentRepository().orElseThrow();;
        currentRepository.getAllStores().stream().filter(store -> !OrganizationRepositoryStore.class.isInstance(store))
                .flatMap(store -> store.getChildren().stream())
                .filter(IRepositoryFileStore::canBeDeleted)
                .forEach(IRepositoryFileStore::delete);
    }

    @Test
    public void should_deploy_a_project_using_wizard() throws Exception {
        BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);

        BotDeployDialog botDeployDialog = botApplicationWorkbenchWindow.importBOSArchive()
                .setArchive(DeployWizardIT.class.getResource("/DeployAll-1.0.bos"))
                .currentRepository()
                .next()
                .next()
                .importAndDeploy();

        assertThat(botDeployDialog.isCleanBDMDatabaseEnabled()).isTrue();
        assertThat(botDeployDialog.isCleanBDMDatabaseSelected()).isFalse();
        assertThat(botDeployDialog.isValidateSelected()).isTrue();
        assertThat(botDeployDialog.isDefaultUserEnabled()).isTrue();

        botDeployDialog.runValidation(false);

        botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").expand();
        botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool").expand();

        //Check select none/all/latest
        botDeployDialog.selectNone();
        assertThat(botDeployDialog.isDeployEnabled()).isFalse();
        botDeployDialog.selectAll();
        assertThat(botDeployDialog.isDeployEnabled()).isTrue();
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("1.0  MonDiagramme-1.0.proc").isChecked()).isTrue();
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("2.0  MonDiagramme-2.0.proc").isChecked()).isTrue();

        botDeployDialog.selectLatest();// Check 'selectLatestVersion' option -> Old process should not be checked anymore
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("1.0  MonDiagramme-1.0.proc").isChecked()).isFalse();
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("2.0  MonDiagramme-2.0.proc").isChecked()).isTrue();

        botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("1.0  MonDiagramme-1.0.proc").check(); // Try to check the old process with the 'selectLatestVersion' option -> should not work
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("1.0  MonDiagramme-1.0.proc").isChecked()).isFalse();

        botDeployDialog.selectAllVersions();// Uncheck 'selectLatestVersion' option -> Old process can be checked/unchecked now
        botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("1.0  MonDiagramme-1.0.proc").check();
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("1.0  MonDiagramme-1.0.proc").isChecked()).isTrue();
        botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("1.0  MonDiagramme-1.0.proc").uncheck();
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("1.0  MonDiagramme-1.0.proc").isChecked()).isFalse();

        //Check username validation
        botDeployDialog.setDefaultUser("john.doe");
        assertThat(botDeployDialog.isDeployEnabled()).isFalse();
        botDeployDialog.setDefaultUser("helen.kelly");
        assertThat(botDeployDialog.isDeployEnabled()).isTrue();

        botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Organizations").getItems()[0].uncheck();
        assertThat(botDeployDialog.isDefaultUserEnabled()).isTrue();

        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getAllItems())
                .extracting(SWTBotTreeItem::getText)
                .doesNotContain("REST API extensions");

        botDeployDialog.deploy();
        assertThat(bot.button(IDialogConstants.OPEN_LABEL).isEnabled()).isTrue();
        assertThat(bot.comboBox().getText()).contains("My App as User");
        bot.button(IDialogConstants.CLOSE_LABEL).click();

        checkDeployedContent();

        //Check last selected elements are saved
        botDeployDialog = botApplicationWorkbenchWindow.openDeploy();
        assertThat(botDeployDialog.isValidateSelected()).isFalse();
        assertThat(
                botDeployDialog.artifactsTree().getSWTBotWidget()
                        .getTreeItem(org.bonitasoft.studio.identity.i18n.Messages.organizations).getItems()[0].isChecked())
                                .isFalse();
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("1.0  MonDiagramme-1.0.proc").isChecked()).isFalse();
        assertThat(botDeployDialog.artifactsTree().getSWTBotWidget().getTreeItem("Processes").getNode("Pool")
                .getNode("2.0  MonDiagramme-2.0.proc").isChecked()).isTrue();

        botDeployDialog.cancel();
    }

    private void checkDeployedContent() throws LoginException, BonitaHomeNotSetException, ServerAPIException,
            UnknownAPITypeException, ProcessDefinitionNotFoundException, PageNotFoundException, SearchException {
        BOSEngineManager manager = BOSEngineManager.getInstance();
        APISession apiSession = manager.loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
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
