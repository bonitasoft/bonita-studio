/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.configuration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Romain Bioteau
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ConfigurationDialogIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    private static final String EMPLOYEE_ACTOR = "Employee actor";

    @Before
    public void setUp() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        final List<SWTBotGefEditPart> runnableEPs = gmfEditor.editParts(new BaseMatcher<EditPart>() {

            @Override
            public boolean matches(final Object item) {
                return item instanceof PoolEditPart;
            }

            @Override
            public void describeTo(final Description description) {

            }
        });
        Assert.assertFalse(runnableEPs.isEmpty());
        gmfEditor.select(runnableEPs.get(0));
    }

    @Test
    public void importDiagramWithCustomActorMappingAndOpenConfiguration() throws IOException {
        new BotApplicationWorkbenchWindow(bot).importBOSArchive()
                .setArchive(ConfigurationDialogIT.class.getResource("TestImport-1.0.bos")).finish();
        new BotApplicationWorkbenchWindow(bot).configure().finish();
    }

    @Test
    public void testOpenDialog() {
        openDialog();
    }

    private void openDialog() {
        new BotApplicationWorkbenchWindow(bot).configure();
        bot.table().getTableItem("Parameters").select();
        bot.table().getTableItem("Actor mapping").select();
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    @Test
    public void testAdvancedCheckbox() {
        new BotApplicationWorkbenchWindow(bot).configure();
        if (bot.checkBox().isChecked()) {
            bot.checkBox().click();
        }
        boolean notFound = false;
        try {
            bot.table().getTableItem("Java dependencies").select();
        } catch (final WidgetNotFoundException e) {
            notFound = true;
        }
        assertTrue("Java dependencies menu should not be visible", notFound);

        if (!bot.checkBox().isChecked()) {
            bot.checkBox().click();
        }
        bot.table().getTableItem("Java dependencies").select();

        bot.button(IDialogConstants.FINISH_LABEL).click();

        //VALIDATE CHECKBOX STATE RESTORED
        new BotApplicationWorkbenchWindow(bot).configure();
        assertTrue("Advanced checbox should be ckecked", bot.checkBox().isChecked());
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    @Test
    public void testMappingOfActors() {
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        final IGraphicalEditPart part = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess) part.resolveSemanticElement();
        final Pool pool = (Pool) model.getElements().get(0);
        final String processLabel = pool.getName() + " (" + pool.getVersion() + ")";
        new BotApplicationWorkbenchWindow(bot).configure();
        bot.waitUntil(Conditions.shellIsActive("Local configuration for " + processLabel));
        bot.table().getTableItem("Actor mapping").select();
        SWTBotShell activeShell = bot.activeShell();

        //Remove default mapping
        bot.tree().getTreeItem(EMPLOYEE_ACTOR).select();
        bot.button("Groups...").click();
        bot.table().getTableItem(0).uncheck();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        activeShell.setFocus();

        //Map to a group
        bot.tree().getTreeItem(EMPLOYEE_ACTOR + " -- Not mapped").select();
        bot.button("Groups...").click();
        bot.table().getTableItem(0).check();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        activeShell.setFocus();

        //Map to a role
        bot.tree().getTreeItem(EMPLOYEE_ACTOR).select();
        bot.button("Roles...").click();
        bot.table().getTableItem(0).check();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        activeShell.setFocus();

        //Map to a user
        bot.tree().getTreeItem(EMPLOYEE_ACTOR).select();
        bot.button("Users...").click();
        bot.table().getTableItem(0).check();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        activeShell.setFocus();

        //Map to a membership
        bot.tree().getTreeItem(EMPLOYEE_ACTOR).select();
        bot.button("Memberships...").click();
        bot.button("Add membership...").click();
        assertFalse("Finish button should be disabled", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.comboBoxWithLabel("Group").setSelection(1);
        assertFalse("Finish button should be disabled", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.comboBoxWithLabel("Role").setSelection(1);
        assertTrue("Finish button should be enabled", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.FINISH_LABEL).click();

        activeShell.setFocus();

        bot.tree().getTreeItem(EMPLOYEE_ACTOR).getNode("Groups").expand();
        bot.tree().getTreeItem(EMPLOYEE_ACTOR).getNode("Roles").expand();
        bot.tree().getTreeItem(EMPLOYEE_ACTOR).getNode("Users").expand();
        bot.tree().getTreeItem(EMPLOYEE_ACTOR).getNode("Membership").expand();

        bot.button(IDialogConstants.FINISH_LABEL).click();
        new BotApplicationWorkbenchWindow(bot).configure();
        bot.waitUntil(Conditions.shellIsActive("Local configuration for " + processLabel));
        bot.table().select("Actor mapping");
        bot.tree().getTreeItem(EMPLOYEE_ACTOR).getNode("Groups").expand();
        bot.tree().getTreeItem(EMPLOYEE_ACTOR).getNode("Roles").expand();
        bot.tree().getTreeItem(EMPLOYEE_ACTOR).getNode("Users").expand();
        bot.tree().getTreeItem(EMPLOYEE_ACTOR).getNode("Membership").expand();

        bot.button(IDialogConstants.FINISH_LABEL).click();
    }


}
