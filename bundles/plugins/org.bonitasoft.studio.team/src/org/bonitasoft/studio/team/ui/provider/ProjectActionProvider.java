package org.bonitasoft.studio.team.ui.provider;

import java.util.Collections;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.ui.handler.SwitchRepositoriesWorkspaceHandler;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;

public class ProjectActionProvider extends CommonActionProvider {

    private static final String SWITCH_PROJECT_ACTION_ID = "org.bonitasoft.studio.switchProject";
    private static final String NEW_PROJECT_ACTION_ID = "org.bonitasoft.studio.newProject";
    private static final String NEW_PROJECT_COMMAND_ID = "org.bonitasoft.studio.application.newproject.command";
    private CommandExecutor commandExecutor = new CommandExecutor();

    @Override
    public void fillActionBars(IActionBars actionBars) {
        super.fillActionBars(actionBars);
        IContributionItem item = actionBars.getMenuManager().find(SWITCH_PROJECT_ACTION_ID);
        if (item == null) {
            addSwitchProjectAction(actionBars);
        }
        item = actionBars.getMenuManager().find(NEW_PROJECT_ACTION_ID);
        if (item == null) {
            addNewProjectAction(actionBars);
        }

    }

    protected void addSwitchProjectAction(IActionBars actionBars) {
        Action action = new Action(Messages.switchRepository,
                TeamPlugin.getImageDescriptor("/icons/change-repository.png")) {

            @Override
            public void run() {
                try {
                    new SwitchRepositoriesWorkspaceHandler().execute(null);
                } catch (ExecutionException e) {
                    BonitaStudioLog.error(e);
                }
            }
        };
        action.setId(SWITCH_PROJECT_ACTION_ID);
        action.setToolTipText(Messages.switchRepository);
        actionBars.getMenuManager().add(action);
    }

    protected void addNewProjectAction(IActionBars actionBars) {
        String commandName = commandExecutor.getCommandName(NEW_PROJECT_COMMAND_ID);
        Action action = new Action(Messages.createNewLocalRepo,
                TeamPlugin.getImageDescriptor("/icons/new-local-repository.png")) {

            @Override
            public void run() {
                commandExecutor.executeCommand(NEW_PROJECT_COMMAND_ID, Collections.emptyMap());
            }
        };
        action.setId(NEW_PROJECT_ACTION_ID);
        action.setToolTipText(commandName);
        actionBars.getMenuManager().add(action);
    }

}
