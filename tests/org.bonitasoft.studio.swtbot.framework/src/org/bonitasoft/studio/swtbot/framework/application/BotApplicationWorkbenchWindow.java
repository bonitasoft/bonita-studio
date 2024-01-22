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
package org.bonitasoft.studio.swtbot.framework.application;

import java.text.MessageFormat;

import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.studio.application.coolbar.PreferenceCoolbarItem;
import org.bonitasoft.studio.application.views.overview.ProjectOverviewEditorPart;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.application.editor.BotProjectOverviewEditor;
import org.bonitasoft.studio.swtbot.framework.application.menu.AbstractBotMenu;
import org.bonitasoft.studio.swtbot.framework.bdm.BotBdmEditor;
import org.bonitasoft.studio.swtbot.framework.bdm.ImportBdmWizardBot;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.configuration.BotConfigureDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.export.BotExportBOSDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.importer.BotImportBOSDialog;
import org.bonitasoft.studio.swtbot.framework.la.BotApplicationEditor;
import org.bonitasoft.studio.swtbot.framework.la.DeleteApplicationWizardBot;
import org.bonitasoft.studio.swtbot.framework.la.OpenApplicationWizardBot;
import org.bonitasoft.studio.swtbot.framework.la.SelectApplicationToDeployWizardBot;
import org.bonitasoft.studio.swtbot.framework.preferences.BotPreferencesDialog;
import org.bonitasoft.studio.swtbot.framework.team.git.BotGitCloneDialog;
import org.bonitasoft.studio.swtbot.framework.team.git.BotGitInitDialog;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.matchers.WithId;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.eclipse.ui.IEditorReference;

/**
 * Application workbench.
 * 
 * @author Joachim Segala
 */
public class BotApplicationWorkbenchWindow extends AbstractBotMenu {

    public BotApplicationWorkbenchWindow(final SWTGefBot bot) {
        super(bot);
    }

    public BotProcessDiagramPerspective createNewDiagram() {
        final int nbEditorsBefore = bot.editors().size();
        bot.waitUntil(Conditions.waitForWidget(WithId.withId(SWTBotConstants.SWTBOT_ID_MAIN_SHELL)), 40000);
        bot.waitUntil(Conditions.shellIsActive(bot.shellWithId(SWTBotConstants.SWTBOT_ID_MAIN_SHELL).getText()), 40000);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarDropDownButtonWithId("org.bonitasoft.studio.coolbar.new")),
                40000);
        bot.toolbarDropDownButtonWithId("org.bonitasoft.studio.coolbar.new")
                .menuItem(org.bonitasoft.studio.application.i18n.Messages.processDiagram)
                .click();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditorsBefore + 1 == bot.editors().size();
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Editor for new diagram has not been opened";
            }
        }, 30000, 100);
        return new BotProcessDiagramPerspective(bot);
    }

    public BotApplicationWorkbenchWindow save() {
        bot.activeEditor().setFocus();
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).click();
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return !BotApplicationWorkbenchWindow.this.bot.activeEditor().isDirty();
            }

            @Override
            public String getFailureMessage() {
                return "The save took too much time";
            }
        }, 40000);
        return this;
    }

    public BotApplicationWorkbenchWindow close() {
        final int nbEditorsBefore = bot.editors().size();
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("File")), 40000);
        final SWTBotMenu menu = bot.menu("File");
        menu.menu("Close").click();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditorsBefore - 1 == bot.editors().size();
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Editor for new diagram has not been opened";
            }
        }, 30000, 100);
        return this;
    }

    public BotConfigureDialog configure() {
        bot.waitUntil(Conditions
                .widgetIsEnabled(bot.toolbarDropDownButtonWithId(SWTBotConstants.SWTBOT_ID_CONFIGURE_TOOLITEM)));
        bot.toolbarDropDownButtonWithId(SWTBotConstants.SWTBOT_ID_CONFIGURE_TOOLITEM).click();
        final DiagramEditor editor = (DiagramEditor) bot.activeEditor().getReference().getEditor(true);
        final IGraphicalEditPart ep = (IGraphicalEditPart) editor.getDiagramGraphicalViewer().getSelectedEditParts()
                .get(0);
        final Pool selectedProcess = ModelHelper.getFirstContainerOfType(ep.resolveSemanticElement(), Pool.class);
        return new BotConfigureDialog(bot, selectedProcess.getName() + " (" + selectedProcess.getVersion() + ")");
    }

    public BotConfigureDialog configure(String environment) {
        bot.waitUntil(Conditions
                .widgetIsEnabled(bot.toolbarDropDownButtonWithId(SWTBotConstants.SWTBOT_ID_CONFIGURE_TOOLITEM)));
        bot.toolbarDropDownButtonWithId(SWTBotConstants.SWTBOT_ID_CONFIGURE_TOOLITEM).click();
        final DiagramEditor editor = (DiagramEditor) bot.activeEditor().getReference().getEditor(true);
        final IGraphicalEditPart ep = (IGraphicalEditPart) editor.getDiagramGraphicalViewer().getSelectedEditParts()
                .get(0);
        final Pool selectedProcess = ModelHelper.getFirstContainerOfType(ep.resolveSemanticElement(), Pool.class);
        return new BotConfigureDialog(bot, environment,
                selectedProcess.getName() + " (" + selectedProcess.getVersion() + ")");
    }

    public BotExportBOSDialog export() {
    	bot.waitUntil(Conditions.waitForWidget(WithId.withId(SWTBotConstants.SWTBOT_ID_MAIN_SHELL)), 40000);
    	bot.shellWithId(SWTBotConstants.SWTBOT_ID_MAIN_SHELL).activate();
        bot.waitUntil(Conditions.shellIsActive(bot.shellWithId(SWTBotConstants.SWTBOT_ID_MAIN_SHELL).getText()), 40000);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("File")), 40000);
        bot.menu("File").menu("Export project").click();
        return new BotExportBOSDialog(bot);
    }

    public BotImportBOSDialog importBOSArchive() {
    	bot.waitUntil(Conditions.waitForWidget(WithId.withId(SWTBotConstants.SWTBOT_ID_MAIN_SHELL)), 40000);
    	bot.shellWithId(SWTBotConstants.SWTBOT_ID_MAIN_SHELL).activate();
        bot.waitUntil(Conditions.shellIsActive(bot.shellWithId(SWTBotConstants.SWTBOT_ID_MAIN_SHELL).getText()), 40000);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("File")), 40000);
        bot.menu("File").menu("Import project").click();
        return new BotImportBOSDialog(bot);
    }

    public OpenApplicationWizardBot openApplication() {
        waitForMainShell(bot);
        bot.getDisplay().asyncExec(() -> commandExecutor.executeCommand("org.bonitasoft.studio.la.open.command", null));
        return new OpenApplicationWizardBot(bot, Messages.openExistingApplication);
    }

    public void newApplicationDescriptorFile() {
        waitForMainShell(bot);
        bot.getDisplay().asyncExec(() -> commandExecutor.executeCommand("org.bonitasoft.studio.la.new.command", null));
        bot.waitUntil(Conditions.waitForEditor(IsInstanceOf.instanceOf(IEditorReference.class)));
    }

    public SelectApplicationToDeployWizardBot deployApplicationFile() {
        waitForMainShell(bot);
        bot.getDisplay()
                .asyncExec(() -> commandExecutor.executeCommand("org.bonitasoft.studio.la.deploy.command", null));
        return new SelectApplicationToDeployWizardBot(bot, Messages.deployExistingApplication);
    }

    public DeleteApplicationWizardBot deleteApplicationDescriptor() {
        waitForMainShell(bot);
        bot.getDisplay()
                .asyncExec(() -> commandExecutor.executeCommand("org.bonitasoft.studio.la.delete.command", null));
        return new DeleteApplicationWizardBot(bot, Messages.deleteExistingApplication);
    }

    public BotBdmEditor defineBDM() {
        waitForMainShell(bot);
        bot.getDisplay()
                .asyncExec(() -> commandExecutor.executeCommand("org.bonitasoft.studio.businessobject.define", null));
        return new BotBdmEditor(bot);
    }

    public ImportBdmWizardBot importBDM() {
        waitForMainShell(bot);
        bot.getDisplay().asyncExec(
                () -> commandExecutor.executeCommand("org.bonitasoft.studio.businessobject.command.import", null));
        bot.waitUntil(
                Conditions.shellIsActive(org.bonitasoft.studio.businessobject.i18n.Messages.importBdm));
        bot.shell(org.bonitasoft.studio.businessobject.i18n.Messages.importBdm).activate().setFocus();
        return new ImportBdmWizardBot(bot);
    }

    public BotDeployDialog openDeploy() {
        bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_DEPLOY_TOOLITEM)));
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_DEPLOY_TOOLITEM).click();
        return new BotDeployDialog(bot);
    }

    public BotApplicationEditor newApplicationContainer() {
        waitForMainShell(bot);
        bot.getDisplay().asyncExec(() -> commandExecutor.executeCommand("org.bonitasoft.studio.la.new.command", null));
        bot.waitUntil(Conditions.waitForEditor(IsInstanceOf.instanceOf(IEditorReference.class)));
        return new BotApplicationEditor(bot, bot.activeEditor());
    }

    public BotPreferencesDialog openPreferences() {
        bot.waitUntil(Conditions
                .widgetIsEnabled(bot.toolbarButtonWithId(PreferenceCoolbarItem.PREFERENCE_COOLBAR_ITEM_ID)));
        bot.toolbarButtonWithId(PreferenceCoolbarItem.PREFERENCE_COOLBAR_ITEM_ID).click();
        return new BotPreferencesDialog(bot);
    }

    public BotProjectOverviewEditor openProjectOverview() {
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_OPEN_PROJECT_DETAILS_TOOLITEM).click();
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return BotApplicationWorkbenchWindow.this.bot.editorById(ProjectOverviewEditorPart.ID) != null;
            }

            @Override
            public String getFailureMessage() {
                return "Failed to open the project overview.";
            }
        }, 15000);
        return new BotProjectOverviewEditor(bot);
    }

    public BotGitInitDialog shareWithGit() {
        waitForMainShell(bot);
        bot.menu("File").menu("Share with Git").click();
        return new BotGitInitDialog(bot);
    }

    public BotGitCloneDialog gitClone() {
        bot.menu("File").menu("Clone").click();
        return new BotGitCloneDialog(bot);
    }

    /**
     * Wait until the "build" operations are finished, to prevent them from stacking up and triggering timeouts.
     * 
     * @throws InterruptedException
     */
    public void waitEndOfBuilds() throws TimeoutException, InterruptedException {
        waitEndOfBuilds(0L);
    }

    /**
     * Wait until the "build" operations are finished, to prevent them from stacking up and triggering timeouts.
     * 
     * @param timeout the timeout after which a TimeoutException is thrown (0 for no timeout).
     * @throws InterruptedException
     */
    public void waitEndOfBuilds(long timeout) throws TimeoutException, InterruptedException {
        Runnable join = () -> {
            try {
                BuildScheduler.joinOnBuildRule();
            } catch (IllegalStateException | OperationCanceledException e) {
                BonitaStudioLog.error(e);
            } catch (InterruptedException e) {
                BonitaStudioLog.error(e);
                Thread.currentThread().interrupt();
            }
        };
        Thread thread = new Thread(join, "Join on Build rule");
        thread.start();
        thread.join(timeout);
        if (thread.isAlive()) {
            throw new TimeoutException(
                    MessageFormat.format("Some build jobs were still active after {0} ms.", timeout));
        }
    }

}
