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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.menu.AbstractBotMenu;
import org.bonitasoft.studio.swtbot.framework.application.menu.BotEditMenu;
import org.bonitasoft.studio.swtbot.framework.application.menu.BotOrganizationMenu;
import org.bonitasoft.studio.swtbot.framework.bdm.BotBdmEditor;
import org.bonitasoft.studio.swtbot.framework.bdm.ImportBdmWizardBot;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.configuration.BotConfigureDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.export.BotExportBOSDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.importer.BotImportBOSDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.importer.BotImportOtherDialog;
import org.bonitasoft.studio.swtbot.framework.la.BotApplicationEditor;
import org.bonitasoft.studio.swtbot.framework.la.DeleteApplicationWizardBot;
import org.bonitasoft.studio.swtbot.framework.la.OpenApplicationWizardBot;
import org.bonitasoft.studio.swtbot.framework.la.SelectApplicationToDeployWizardBot;
import org.bonitasoft.studio.swtbot.framework.team.BotTeamMenu;
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
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("File")), 40000);
        final SWTBotMenu menu = bot.menu("File");
        menu.menu("New diagram").click();
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

    public BotOpenDiagramDialog open() {
        bot.menu("File").menu("Open diagram...").click();
        bot.waitUntil(Conditions.shellIsActive("Open an existing diagram"));
        return new BotOpenDiagramDialog(bot);
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

    public BotEditMenu editMenu() {
        openMenu("Edit");
        return new BotEditMenu(bot);
    }

    public BotOrganizationMenu organizationMenu() {
        openMenu("Organization");
        return new BotOrganizationMenu(bot);
    }

    public BotConfigureDialog configure() {
        if (SWTBotTestUtil.testingBosSp()) {
            bot.waitUntil(Conditions
                    .widgetIsEnabled(bot.toolbarDropDownButtonWithId(SWTBotConstants.SWTBOT_ID_CONFIGURE_TOOLITEM)));
            bot.toolbarDropDownButtonWithId(SWTBotConstants.SWTBOT_ID_CONFIGURE_TOOLITEM).click();
        } else {
            bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_CONFIGURE_TOOLITEM)));
            bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_CONFIGURE_TOOLITEM).click();
        }
        final DiagramEditor editor = (DiagramEditor) bot.activeEditor().getReference().getEditor(true);
        final IGraphicalEditPart ep = (IGraphicalEditPart) editor.getDiagramGraphicalViewer().getSelectedEditParts().get(0);
        final Pool selectedProcess = ModelHelper.getFirstContainerOfType(ep.resolveSemanticElement(), Pool.class);
        return new BotConfigureDialog(bot, selectedProcess.getName() + " (" + selectedProcess.getVersion() + ")");
    }

    public BotExportBOSDialog export() {
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_EXPORT_TOOLITEM).click();
        return new BotExportBOSDialog(bot);
    }

    public BotImportBOSDialog importBOSArchive() {
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_IMPORT_TOOLITEM).click();
        return new BotImportBOSDialog(bot);
    }

    public BotImportOtherDialog importOther() {
        waitForMainShell(bot);
        bot.menu("File").menu("Import").menu("Other...").click();
        return new BotImportOtherDialog(bot);
    }

    public BotImportOtherDialog importFromOtherWorkspace() {
        waitForMainShell(bot);
        bot.menu("File").menu("Import").menu("From another Workspace...").click();
        return new BotImportOtherDialog(bot);
    }

    public OpenApplicationWizardBot openApplication() {
        waitForMainShell(bot);
        bot.menu("Development").menu("Application Descriptors").menu("Open...").click();
        return new OpenApplicationWizardBot(bot, Messages.openExistingApplication);
    }

    public void newApplicationDescriptorFile() {
        waitForMainShell(bot);
        bot.menu("Development").menu("Application Descriptors").menu("New...").click();
        bot.waitUntil(Conditions.waitForEditor(IsInstanceOf.instanceOf(IEditorReference.class)));
    }

    public SelectApplicationToDeployWizardBot deployApplicationFile() {
        waitForMainShell(bot);
        bot.menu("Development").menu("Application Descriptors").menu("Deploy...").click();
        return new SelectApplicationToDeployWizardBot(bot, Messages.deployExistingApplication);
    }

    public DeleteApplicationWizardBot deleteApplicationDescriptor() {
        waitForMainShell(bot);
        bot.menu("Development").menu("Application Descriptors").menu("Delete...").click();
        return new DeleteApplicationWizardBot(bot, Messages.deleteExistingApplication);
    }

    public BotBdmEditor defineBDM() {
        waitForMainShell(bot);
        bot.menu("Development").menu("Business Data Model").menu("Define...").click();
        return new BotBdmEditor(bot);
    }

    public ImportBdmWizardBot importBDM() {
        waitForMainShell(bot);
        bot.menu("Development").menu("Business Data Model").menu("Import...").click();
        bot.waitUntil(
                Conditions.shellIsActive(org.bonitasoft.studio.businessobject.i18n.Messages.importBdm));
        bot.shell(org.bonitasoft.studio.businessobject.i18n.Messages.importBdm).activate().setFocus();
        return new ImportBdmWizardBot(bot);
    }

    public BotDeployDialog openDeploy() {
        bot.menu("File").menu("Deploy...").click();
        return new BotDeployDialog(bot);
    }

    public BotTeamMenu teamMenu() {
        waitForMainShell(bot);
        openMenu("Team");
        return new BotTeamMenu(bot);
    }

    public BotApplicationEditor newApplicationContainer() {
        waitForMainShell(bot);
        bot.menu("Development").menu("Application Descriptors").menu("New...").click();
        bot.waitUntil(Conditions.waitForEditor(IsInstanceOf.instanceOf(IEditorReference.class)));
        return new BotApplicationEditor(bot, bot.activeEditor());
    }
}
