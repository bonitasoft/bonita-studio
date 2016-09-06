/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.application;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.swtbot.framework.application.menu.AbstractBotMenu;
import org.bonitasoft.studio.swtbot.framework.application.menu.BotEditMenu;
import org.bonitasoft.studio.swtbot.framework.application.menu.BotOrganizationMenu;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.configuration.BotConfigureDialog;
import org.bonitasoft.studio.swtbot.framework.diagram.export.BotExportBOSDialog;
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
}
