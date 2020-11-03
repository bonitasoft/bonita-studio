/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.diagram;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestRenameDiagram {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Test
    public void testFirstSaveRenaming() {
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, true);
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        MainProcess diagram = (MainProcess) ((IGraphicalEditPart) gmfEditor.mainEditPart().part()).resolveSemanticElement();
        String originalName = diagram.getName();
        bot.menu("File").menu("Save").click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));
        assertTrue("OK should be enabled", bot.button(IDialogConstants.OK_LABEL).isEnabled());

        final String newName = originalName + " renamed" + System.currentTimeMillis();
        bot.textWithLabel(org.bonitasoft.studio.common.Messages.name, 0).setText(newName);

        bot.button(IDialogConstants.OK_LABEL).click();
        final String editorTitle = newName + " (1.0)";
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return editorTitle.equals(bot.activeEditor().getTitle());
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "The editor title (" + bot.activeEditor().getTitle() + ") doesn't match the new name of the diagram "
                        + editorTitle + "\n" +
                        "Please attach Studio log from .metadata/.logs folder on [BS-9265]";
            }
        });
        assertFalse("Editor is dirty", bot.activeEditor().isDirty());

        //Disable dialog
        SWTBotTestUtil.createNewDiagram(bot);
        botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());
        diagram = (MainProcess) ((IGraphicalEditPart) gmfEditor.mainEditPart().part()).resolveSemanticElement();
        originalName = diagram.getName();
        bot.menu("File").menu("Save").click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));
        assertTrue("OK should be enabled", bot.button(IDialogConstants.OK_LABEL).isEnabled());

        bot.checkBox(org.bonitasoft.studio.application.i18n.Messages.doNotDisplayForOtherDiagrams).select();
        bot.button(IDialogConstants.OK_LABEL).click();
        assertEquals(originalName + " (1.0)", bot.activeEditor().getTitle());
        assertFalse("Editor is dirty", bot.activeEditor().isDirty());

        SWTBotTestUtil.createNewDiagram(bot);
        bot.menu("File").menu("Save").click();
        bot.waitWhile(Conditions.shellIsActive("Progress Information"));
        assertFalse(bot.activeShell().getText().equals(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));
        assertFalse("Editor is dirty", bot.activeEditor().isDirty());
    }

    @Test
    public void testRenameMenu() {
        SWTBotTestUtil.createNewDiagram(bot);

        bot.menu("File").menu("Save").click();

        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        final MainProcess diagram = (MainProcess) ((IGraphicalEditPart) gmfEditor.mainEditPart().part())
                .resolveSemanticElement();
        final String originalName = diagram.getName();
        bot.menu("File").menu("Rename diagram...").click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));

        assertTrue("OK should be enabled", bot.button(IDialogConstants.OK_LABEL).isEnabled());

        final String newName = originalName + " renamed" + System.currentTimeMillis();
        bot.textWithLabel(org.bonitasoft.studio.common.Messages.name, 0).setText(newName);

        bot.button(IDialogConstants.OK_LABEL).click();
        assertEquals(newName + " (1.0)", bot.activeEditor().getTitle());
        assertFalse("Editor is dirty", bot.activeEditor().isDirty());
    }

    @Test
    public void testRenameDiagramOnce() throws Exception {

        final boolean tmpDisablePopup = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getDefaultBoolean(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, true);

        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotTestUtil.changeDiagramName(bot, "NewDiagramName");

        // TimeOUt if a the pop up has been reopened (see BS-9819)
        bot.waitWhile(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));

        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, tmpDisablePopup);
    }

}
