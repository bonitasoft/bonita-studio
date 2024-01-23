package org.bonitasoft.studio.tests.environment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.EnvironmentsProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.tests.util.ProjectUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class EnvironmentDialogIT {

    private SWTGefBot bot = new SWTGefBot();
    private EnvironmentsProjectExplorerBot envExplorerBot;
    private static final String EMPLOYEE_ACTOR = "Employee actor";

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Before
    public void init() {
        envExplorerBot = new EnvironmentsProjectExplorerBot(bot);
    }
    
    @After
    public void cleanup() throws CoreException {
        envExplorerBot.setAsActiveEnvironment("Local");
        ProjectUtil.cleanProject();
    }

    @Test
    public void should_display_details_new_environment_selected() {
        new BotApplicationWorkbenchWindow(bot).createNewDiagram();
        var newEnv = "testDev";

        envExplorerBot.newEnvironment(newEnv);
        assertEnvExist(newEnv);
        var envDialog = envExplorerBot.open(newEnv);
        assertEquals(envDialog.getName(), newEnv);

        envDialog.close();
    }

    @Test
    public void should_modify_details_new_environment_selected() {
        var newEnv = "Qualification";
        var modEnv = "MOD_TEST2";
        var modDesc = "Testing description";
        // Creating a diag and getting the process
        var myDiag = new BotApplicationWorkbenchWindow(bot).createNewDiagram();
        Pool process = (Pool) myDiag.activeProcessDiagramEditor().getSelectedSemanticElement();
        final String processLabel = process.getName() + " (" + process.getVersion() + ")";

        envExplorerBot.setAsActiveEnvironment(newEnv);

        // Adding a new group to the configuration "qualification"
        addingGroupToConfigurationPool(processLabel, newEnv);

        var envDialog = envExplorerBot.open(newEnv);

        envDialog.setName(modEnv);
        envDialog.setDescription(modDesc);
        envDialog.modify();

        if (bot.button(IDialogConstants.YES_LABEL).isVisible()) {
            bot.button(IDialogConstants.YES_LABEL).click();
        }

        assertEnvExist(modEnv);
        envDialog = envExplorerBot.open(modEnv);
        assertEquals(envDialog.getName(), modEnv);
        assertEquals(envDialog.getDescription(), modDesc);
        envDialog.close();

        // Checking if the existing configuration has been updated
        myDiag.activeProcessDiagramEditor().selectElement(process.getName());
        bot.toolbarDropDownButtonWithId(SWTBotConstants.SWTBOT_ID_CONFIGURE_TOOLITEM).click();
        bot.table().getTableItem("Actor mapping").select();
        var itemTree = bot.tree().getTreeItem(EMPLOYEE_ACTOR).getNode("Groups").expand();
        assertEquals(itemTree.getNode(0).getText(), "/acme");
        bot.button(IDialogConstants.FINISH_LABEL).click();

    }

    private void addingGroupToConfigurationPool(String processLabel, String configurationLabel) {
        new BotApplicationWorkbenchWindow(bot).configure(configurationLabel);
        bot.waitUntil(Conditions.shellIsActive(configurationLabel + " configuration for " + processLabel));
        bot.table().getTableItem("Actor mapping").select();
        SWTBotShell activeShell = bot.activeShell();

        //Map to a group
        bot.tree().getTreeItem(EMPLOYEE_ACTOR + " -- Not mapped").select();
        bot.button("Groups...").click();
        bot.tableWithId(SWTBotConstants.SWTBOT_ID_ACTOR_MAPPING_GROUPS_TABLE).getTableItem(0).check();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        activeShell.setFocus();

        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    @Test
    public void should_modify_details_new_environment_selected_with_multiples_diagrams() {
        new BotApplicationWorkbenchWindow(bot).createNewDiagram();
        new BotApplicationWorkbenchWindow(bot).createNewDiagram();
        
        var newEnv = "Production";
        var modEnv = "MOD_TEST";
        var modDesc = "Testing description";
        var envDialog = envExplorerBot.open(newEnv);

        envDialog.setName(modEnv);
        envDialog.setDescription(modDesc);
        envDialog.modify();

        assertEnvExist(modEnv);
        envDialog = envExplorerBot.open(modEnv);
        assertEquals(modEnv, envDialog.getName());
        assertEquals(modDesc, envDialog.getDescription());
        envDialog.close();
    }

    private void assertEnvExist(String newEnv) {
        assertThat(envExplorerBot.getEnvironmentTreeItem(newEnv)).isNotNull();
    }
}
