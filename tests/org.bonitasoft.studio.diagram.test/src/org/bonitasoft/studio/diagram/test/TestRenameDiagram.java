package org.bonitasoft.studio.diagram.test;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestRenameDiagram extends SWTBotGefTestCase {


    // Before and After
    private static boolean disablePopup;

    @BeforeClass
    public static void setUpBeforeClass() {
        disablePopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
    }


    @AfterClass
    public static void tearDownAfterClass() {
        FileActionDialog.setDisablePopup(disablePopup);
    }

    @Override
    @After
    public void tearDown(){
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
        bot.saveAllEditors();
    }


    @Test
    public void testFirstSaveRenaming(){
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, true);
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        MainProcess diagram = (MainProcess) ((IGraphicalEditPart) gmfEditor.mainEditPart().part()).resolveSemanticElement();
        String originalName = diagram.getName();
        bot.menu("Diagram").menu("Save").click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));
        assertTrue("OK should be enabled",bot.button(IDialogConstants.OK_LABEL).isEnabled());

        final String newName = originalName +" renamed"+System.currentTimeMillis();
        bot.textWithLabel(org.bonitasoft.studio.common.Messages.name, 0).setText(newName);

        bot.button(IDialogConstants.OK_LABEL).click();
        assertEquals(newName +" (1.0)", bot.activeEditor().getTitle());
        assertFalse("Editor is dirty", bot.activeEditor().isDirty());

        //Disable dialog
        SWTBotTestUtil.createNewDiagram(bot);
        botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());
        diagram = (MainProcess) ((IGraphicalEditPart) gmfEditor.mainEditPart().part()).resolveSemanticElement();
        originalName = diagram.getName();
        bot.menu("Diagram").menu("Save").click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));
        assertTrue("OK should be enabled",bot.button(IDialogConstants.OK_LABEL).isEnabled());

        bot.checkBox(org.bonitasoft.studio.application.i18n.Messages.doNotDisplayForOtherDiagrams).select();
        bot.button(IDialogConstants.OK_LABEL).click();
        assertEquals(originalName +" (1.0)", bot.activeEditor().getTitle());
        assertFalse("Editor is dirty", bot.activeEditor().isDirty());

        SWTBotTestUtil.createNewDiagram(bot);
        bot.menu("Diagram").menu("Save").click();
        assertFalse(bot.activeShell().getText().equals(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));
        assertFalse("Editor is dirty", bot.activeEditor().isDirty());
    }

    @Test
    public void testRenameMenu(){
        SWTBotTestUtil.createNewDiagram(bot);

        bot.menu("Diagram").menu("Save").click();

        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        final MainProcess diagram = (MainProcess) ((IGraphicalEditPart) gmfEditor.mainEditPart().part()).resolveSemanticElement();
        final String originalName = diagram.getName();
        bot.menu("Diagram").menu("Rename...").click();
        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));

        assertTrue("OK should be enabled",bot.button(IDialogConstants.OK_LABEL).isEnabled());

        final String newName = originalName +" renamed"+System.currentTimeMillis();
        bot.textWithLabel(org.bonitasoft.studio.common.Messages.name, 0).setText(newName);

        bot.button(IDialogConstants.OK_LABEL).click();
        assertEquals(newName +" (1.0)", bot.activeEditor().getTitle());
        assertFalse("Editor is dirty", bot.activeEditor().isDirty());
    }


}
