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
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.swtbot.framework.application.menu.AbstractBotMenu;
import org.bonitasoft.studio.swtbot.framework.application.menu.BotEditMenu;
import org.bonitasoft.studio.swtbot.framework.application.menu.BotOrganizationMenu;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.configuration.BotConfigureDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.export.BotExportBOSDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.importer.BotImportBOSDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.importer.BotImportOtherDialog;
import org.bonitasoft.studio.swtbot.framework.la.DeleteApplicationWizardBot;
import org.bonitasoft.studio.swtbot.framework.la.NewApplicationWizardBot;
import org.bonitasoft.studio.swtbot.framework.la.OpenApplicationWizardBot;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.matchers.WithId;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;

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
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Diagram")), 40000);
        final SWTBotMenu menu = bot.menu("Diagram");
        menu.menu("New").click();
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
        bot.toolbarButton("Open").click();
        bot.waitUntil(Conditions.shellIsActive("Open an existing diagram"));
        return new BotOpenDiagramDialog(bot);
    }

    public BotApplicationWorkbenchWindow save() {
        bot.toolbarButton("Save").click();
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
        bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Diagram")), 40000);
        final SWTBotMenu menu = bot.menu("Diagram");
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
            bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarDropDownButton("Configure")));
            bot.toolbarDropDownButton("Configure").click();
        } else {
            bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarButton("Configure")));
            bot.toolbarButton("Configure").click();
        }
        final DiagramEditor editor = (DiagramEditor) bot.activeEditor().getReference().getEditor(true);
        final IGraphicalEditPart ep = (IGraphicalEditPart) editor.getDiagramGraphicalViewer().getSelectedEditParts().get(0);
        final Pool selectedProcess = ModelHelper.getFirstContainerOfType(ep.resolveSemanticElement(), Pool.class);
        return new BotConfigureDialog(bot, selectedProcess.getName() + " (" + selectedProcess.getVersion() + ")");
    }

    public BotExportBOSDialog export() {
        bot.toolbarButton("Export").click();
        return new BotExportBOSDialog(bot);
    }

    public BotImportBOSDialog importBOSArchive() {
        bot.toolbarButton("Import").click();
        return new BotImportBOSDialog(bot);
    }

    public BotImportOtherDialog importOther() {
        SWTBotTestUtil.waitUntilBonitaBPmShellIsActive(bot,
                RepositoryManager.getInstance().getCurrentRepository().getName());
        bot.menu("Diagram").menu("Import").menu("Other...").click();
        return new BotImportOtherDialog(bot);
    }

    public OpenApplicationWizardBot openApplication() {
        bot.menu("Development").menu("Application Descriptor").menu("Open...").click();
        return new OpenApplicationWizardBot(bot, Messages.openExistingApplication);
    }

    public NewApplicationWizardBot newApplicationContainer() {
        bot.menu("Development").menu("Application Descriptor").menu("New...").click();
        return new NewApplicationWizardBot(bot, Messages.newApplicationDescriptorTitle);
    }

    public DeleteApplicationWizardBot deleteApplicationDescriptor() {
        bot.menu("Development").menu("Application Descriptor").menu("Delete...").click();
        return new DeleteApplicationWizardBot(bot, Messages.deleteExistingApplication);
    }

}
